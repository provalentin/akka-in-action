package com.goticks

object BoxOffice {
  
  case object GetEvents
  
  case class Event(name: String, tickets: Int)
  case class Events(events: Vector[Event])
  
}
