package com.goticks

import akka.actor._
import akka.util.Timeout

object BoxOffice {
  def props(implicit timeout: Timeout) = Props(new BoxOffice())
  def name = "boxOffice"
  
  case class GetEvent(name: String)
  case object GetEvents
  
  case class Event(name: String, tickets: Int)
  case class Events(events: Vector[Event])
  
}

class BoxOffice(implicit timeout: Timeout) extends Actor {
  import BoxOffice._
  import context._
  
  def createTicketSeller(name: String) = 
    context.actorOf(TicketSeller.props(name), TicketSeller.name)
  
  def receive = {
    case GetEvents =>
    
    case GetEvent(name: String)  => 
      def notFound = sender() ! None
      def getEvent(child: ActorRef) = child forward TicketSeller.GetEvent
  }  
}
