package edu.yale.its.tp.cas.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import edu.yale.its.tp.cas.ticket.*;
import edu.yale.its.tp.cas.auth.*;

/**
 * Handles logins for the Central Authentication Service.
 */
public class Login extends HttpServlet {

  //*********************************************************************
  // Constants

  // cookie IDs
  private static final String TGC_ID = "CASTGC";
  private static final String PRIVACY_ID = "CASPRIVACY";

  // parameters
  private static final String SERVICE = "service";
  private static final String RENEW = "renew";
  private static final String GATEWAY = "gateway";

  //*********************************************************************
  // Private state

  private GrantorCache tgcCache;
  private ServiceTicketCache stCache;
  private AuthHandler handler;
  private String loginForm, genericSuccess, serviceSuccess, 
    confirmService, redirect;
  private ServletContext app;

  //*********************************************************************
  // Initialization 

  public void init(ServletConfig config) throws ServletException {
    // retrieve the context and the caches
    app = config.getServletContext();
    tgcCache = (GrantorCache) app.getAttribute("tgcCache");
    stCache = (ServiceTicketCache) app.getAttribute("stCache");

    try {
      // create an instance of the right authentication handler
      String handlerName =
        app.getInitParameter("edu.yale.its.tp.cas.authHandler");
      if (handlerName == null)
        throw new ServletException("need edu.yale.its.tp.cas.authHandler");
      handler = (AuthHandler) Class.forName(handlerName).newInstance();
      if (!(handler instanceof TrustHandler)
          && !(handler instanceof PasswordHandler))
        throw new ServletException("unrecognized handler type: " + 
          handlerName);
    } catch (InstantiationException ex) {
      throw new ServletException(ex.toString());
    } catch (ClassNotFoundException ex) {
      throw new ServletException(ex.toString());
    } catch (IllegalAccessException ex) {
      throw new ServletException(ex.toString());
    }

    // retrieve a relative URL for the login form
    loginForm = app.getInitParameter("edu.yale.its.tp.cas.loginForm");
    serviceSuccess = app.getInitParameter("edu.yale.its.tp.cas.serviceSuccess");
    genericSuccess = app.getInitParameter("edu.yale.its.tp.cas.genericSuccess");
    confirmService = app.getInitParameter("edu.yale.its.tp.cas.confirmService");
    redirect = app.getInitParameter("edu.yale.its.tp.cas.redirect");
    if (loginForm == null || genericSuccess == null || redirect == null
        || confirmService == null)
      throw new ServletException("need edu.yale.its.tp.cas.loginForm, "
        + "-genericSuccess, -serviceSuccess, -redirect, and -confirmService");
  }


  //*********************************************************************
  // Request handling

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // avoid caching (in the stupidly numerous ways we must)
    response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires", 0 );

    // check to see whether we've been sent a valid TGC
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
	if (cookies[i].getName().equals(TGC_ID)) {
	  TicketGrantingTicket t = 
	    (TicketGrantingTicket) tgcCache.getTicket(cookies[i].getValue());
	  if (t == null)
	    continue;
          // on "renew", clear this ticket; otherwise, let the user through
	  if (request.getParameter(RENEW) != null)
            tgcCache.deleteTicket(cookies[i].getValue());
          else {
            grantForService(request, response, t,
              request.getParameter(SERVICE), false);
            return;
          }
	}
      }
    }

    // if not, but if we're passed "gateway", then simply bounce back
    if (request.getParameter(SERVICE) != null &&
        request.getParameter(GATEWAY) != null) {
      request.setAttribute("serviceId", request.getParameter(SERVICE));
      app.getRequestDispatcher(redirect).forward(request, response);
    }

    // if not, then see if our AuthHandler can help
    if (handler instanceof TrustHandler) {
      // try to get a trusted username by interpreting the request
      String trustedUsername = ((TrustHandler) handler).getUsername(request);
      if (trustedUsername != null) {
        // success:  create ticket, send it, and grant a service ticket
        TicketGrantingTicket t = sendTgc(trustedUsername, request, response);
	sendPrivacyCookie(request, response);
        grantForService(request, response, t, request.getParameter(SERVICE),
	  true);
      } else {
        // failure: nothing else to be done
        throw new ServletException("unable to authenticate user");
      }
    } else if (handler instanceof PasswordHandler
        && request.getParameter("username") != null
        && request.getParameter("password") != null) {
      if (((PasswordHandler) handler).authenticate(request,
          request.getParameter("username"), request.getParameter("password"))) {
        // success:  create ticket, send it, and grant a service ticket
        TicketGrantingTicket t =
          sendTgc(request.getParameter("username"), request, response);
	sendPrivacyCookie(request, response);
        grantForService(request, response, t, request.getParameter(SERVICE),
	  true);
        return;
      } else {
        // failure: record failed password authentication
        request.setAttribute("edu.yale.its.tp.cas.badUsernameOrPassword", "");
      }
    }

    // record the service in the request
    request.setAttribute("edu.yale.its.tp.cas.service",
      request.getParameter(SERVICE));

    // no success yet, so forward to the login form
    app.getRequestDispatcher(loginForm).forward(request, response);
  }

  /**
   * Grants a service ticket for the given service, using the given
   * TicketGrantingTicket.  If no 'service' is specified, simply
   * forward to message conveying generic success.
   */
  private void grantForService(HttpServletRequest request,
                               HttpServletResponse response,
                               TicketGrantingTicket t,
                               String serviceId,
			       boolean first)
      throws ServletException, IOException  {
    try {
      PrintWriter out = response.getWriter();
      if (serviceId != null) {
        ServiceTicket st = new ServiceTicket(t, serviceId);
        String token = stCache.addTicket(st);
        request.setAttribute("serviceId", serviceId);
        request.setAttribute("token", token);
	if (!first && privacyRequested(request))
	  app.getRequestDispatcher(confirmService).forward(request, response);
	else
	  app.getRequestDispatcher(serviceSuccess).forward(request, response);
      } else
        app.getRequestDispatcher(genericSuccess).forward(request, response);
    } catch (TicketException ex) {
      throw new ServletException(ex.toString());
    }
  }

  /**
   * Creates, sends (to the given ServletResponse), and returns a
   * TicketGrantingTicket for the given username.
   */
  private TicketGrantingTicket sendTgc(String username,
               HttpServletRequest request,
               HttpServletResponse response) throws ServletException {
    try {
      TicketGrantingTicket t = new TicketGrantingTicket(username);
      String token = tgcCache.addTicket(t);
      Cookie tgc = new Cookie(TGC_ID, token);
      tgc.setSecure(true);
      tgc.setMaxAge(-1);
      tgc.setDomain(request.getServerName());
      tgc.setPath(request.getContextPath());
      response.addCookie(tgc);
      return t;
    } catch (TicketException ex) {
      throw new ServletException(ex.toString());
    }
  }

  /**
   * If the user has so requested, creates and sends (to the given
   * ServletResponse) a cookie recording the fact that the user wants to
   * be warned before using CAS's single-sign-on capabilities.
   */
  private void sendPrivacyCookie(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException {
    if (request.getParameter("warn") != null) {
      Cookie privacy = new Cookie(PRIVACY_ID, "enabled");
      privacy.setSecure(true);
      privacy.setMaxAge(-1);
      privacy.setDomain(request.getServerName());
      privacy.setPath(request.getContextPath());
      response.addCookie(privacy);
    }
  }

  /**
   * Returns true if privacy has been requested, false otherwise.
   */
  private boolean privacyRequested(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++)
      if (cookies[i].getName().equals(PRIVACY_ID))
	return true;
    return false;
  }

}
