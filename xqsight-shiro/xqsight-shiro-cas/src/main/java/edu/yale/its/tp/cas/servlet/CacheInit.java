package edu.yale.its.tp.cas.servlet;

import edu.yale.its.tp.cas.ticket.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Sets up shared ticket caches for the CAS web application.
 */
public class CacheInit implements ServletContextListener {

  //*********************************************************************
  // Constants

  private static final int GRANTING_TIMEOUT_DEFAULT = 2 * 60 * 60;
  private static final int SERVICE_TIMEOUT_DEFAULT = 5 * 60;

  //*********************************************************************
  // Initialization 

  public void contextInitialized(ServletContextEvent sce) {
    // retrieve appropriate initialization parameters
    ServletContext app = sce.getServletContext();
    String grantingTimeoutString = app.getInitParameter(
      "edu.yale.its.tp.cas.grantingTimeout");
    String serviceTimeoutString = app.getInitParameter(
      "edu.yale.its.tp.cas.serviceTimeout");
    int grantingTimeout, serviceTimeout;
    try {
      grantingTimeout = Integer.parseInt(grantingTimeoutString);
    } catch (NumberFormatException ex) {
      grantingTimeout = GRANTING_TIMEOUT_DEFAULT;
    } catch (NullPointerException ex) {
      grantingTimeout = GRANTING_TIMEOUT_DEFAULT;
    }
    try {
      serviceTimeout = Integer.parseInt(serviceTimeoutString);
    } catch (NumberFormatException ex) {
      serviceTimeout = SERVICE_TIMEOUT_DEFAULT;
    } catch (NullPointerException ex) {
      serviceTimeout = SERVICE_TIMEOUT_DEFAULT;
    }

    // set up the caches...
    GrantorCache tgcCache =
      new GrantorCache(TicketGrantingTicket.class, grantingTimeout);
    GrantorCache pgtCache = 
      new GrantorCache(ProxyGrantingTicket.class, grantingTimeout);
    ServiceTicketCache stCache =
      new ServiceTicketCache(ServiceTicket.class, serviceTimeout);
    ServiceTicketCache ptCache = 
      new ServiceTicketCache(ProxyTicket.class, serviceTimeout);

    // ... and share them
    app.setAttribute("tgcCache", tgcCache);
    app.setAttribute("pgtCache", pgtCache);
    app.setAttribute("stCache", stCache);
    app.setAttribute("ptCache", ptCache);
  }

  public void contextDestroyed(ServletContextEvent sce) { }

}
