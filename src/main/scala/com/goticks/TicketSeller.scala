package com.goticks

import akka.actor.{Actor, Props}

object TicketSeller {
  def props(event: String) = Props(new TicketSeller(event: String))

  case object GetEvent
}

class TicketSeller(event: String) extends Actor {
  import TicketSeller._
  
  def receive = {
    case GetEvent => sender() ! Some(BoxOffice.Event(name, tickets.size))
  }
}

