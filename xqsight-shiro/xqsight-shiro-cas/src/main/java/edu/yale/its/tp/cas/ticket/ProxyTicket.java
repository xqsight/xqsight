package edu.yale.its.tp.cas.ticket;

import java.util.*;

/**
 * Represents a CAS proxy ticket (PT).
 */
public class ProxyTicket extends ServiceTicket {

  //*********************************************************************
  // ProxyTicket-specific private state

  private ProxyGrantingTicket grantor;


  //*********************************************************************
  // Constructor

  /** Constructs a new, immutable proxy ticket. */
  public ProxyTicket(ProxyGrantingTicket t, String service) {
    super(t, service);
    this.grantor = t;
  }


  //*********************************************************************
  // ProxyTicket-specific public interface

  /** Retrieves the proxy ticket's lineage -- its chain of "trust." */
  public List getProxies() {
    return grantor.getProxies();
  }

}
