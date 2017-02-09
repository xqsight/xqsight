package edu.yale.its.tp.cas.ticket;

/**
 * Represents a CAS service ticket (ST).
 */
public class ServiceTicket extends Ticket {

  //*********************************************************************
  // Private, ticket state

  private TicketGrantingTicket grantor;
  private String service;


  //*********************************************************************
  // Constructor

  /** Constructs a new, immutable service ticket. */
  public ServiceTicket(TicketGrantingTicket t, String service) {
    this.grantor = t;
    this.service = service;
  }


  //*********************************************************************
  // Public interface

  /** Retrieves the ticket's username. */
  public String getUsername() {
    return grantor.getUsername();
  }

  /** Retrieves the ticket's service. */
  public String getService() {
    return service;
  }

  /**
   * Returns true if it would be appropriate to confer access to the
   * service returned by getService() at the present point in time,
   * false otherwise.
   */
  public boolean isValid() {
    return (!grantor.isExpired());
  }

  /** Returns the ticket's grantor. */
  public TicketGrantingTicket getGrantor() {
    return grantor;
  }
}
