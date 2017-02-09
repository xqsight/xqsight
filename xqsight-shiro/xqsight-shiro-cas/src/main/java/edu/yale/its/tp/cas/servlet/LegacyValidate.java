package edu.yale.its.tp.cas.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import edu.yale.its.tp.cas.ticket.*;

/**
 * Handles simple ST validations for the Central Authentication Service.
 */
public class LegacyValidate extends HttpServlet {

  //*********************************************************************
  // Private state

  private ServiceTicketCache stCache;

  //*********************************************************************
  // Initialization

  public void init(ServletConfig config) throws ServletException {
    // retrieve the cache
    stCache = 
      (ServiceTicketCache) config.getServletContext().getAttribute("stCache");
  }

  //*********************************************************************
  // Request handling

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      PrintWriter out = response.getWriter();
      if (request.getParameter("service") == null
          || request.getParameter("ticket") == null) {
        out.println("no\n");
      } else {
        String ticket = request.getParameter("ticket");
        String service = request.getParameter("service");
        ServiceTicket st = (ServiceTicket) stCache.getTicket(ticket);
        if (st == null || !st.getService().equals(service))
          out.println("no\n");
        else
          out.println("yes\n" + st.getUsername());
      }
    } catch (Exception ex) {
      try {
        response.getWriter().println("no");
      } catch (IOException ignoredEx) {
        // ignore
      }
    }
  }
}
