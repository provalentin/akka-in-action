package com.goticks

import akka.actor.{Actor, Props}

object TicketSeller {
  def props(event: String) = Props(new TicketSeller(event: String))

  case object GetEvent
  
  case class Ticket(id: Int)
}

class TicketSeller(event: String) extends Actor {
  import TicketSeller._
  
  var tickets = Vector.empty[Ticket]
  
  def receive = {
    case GetEvent => sender() ! Some(BoxOffice.Event(event, tickets.size))
  }
}

