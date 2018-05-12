package com.goticks

import akka.actor.{Actor, Props}

object TicketSeller {
  def props(event: String) = Props(new TicketSeller(event: String))

  case object GetEvent
}

class TicketSeller(event: String) extends Actor {
  import TicketSeller._
  
  var tickets = Vestor.empty[Ticket]
  
  def receive = {
    case GetEvent(event) => sender() ! Some(BoxOffice.Event(event, tickets.size))
  }
}

