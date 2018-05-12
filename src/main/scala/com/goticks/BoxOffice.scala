package com.goticks

object BoxOffice {
  
  case class Event(name: String, tickets: Int)
  case class Events(events: Vector[Event])
  
}
