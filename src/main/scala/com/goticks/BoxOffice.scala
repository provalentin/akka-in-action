package com.goticks

import akka.actor._

object BoxOffice {
  def props(implicit timeout: Timeout) = Props(new BoxOffice())
  def name = "boxOffice"
  case object GetEvents
  
  case class Event(name: String, tickets: Int)
  case class Events(events: Vector[Event])
  
}

class BoxOffice(imlicit timeout: Timeout) extends Actor {
  
}
