package com.goticks

import akka.actor._
import akka.util.Timeout

object BoxOffice {
  def props(implicit timeout: Timeout) = Props(new BoxOffice())
  def name = "boxOffice"
  case object GetEvents
  
  case class Event(name: String, tickets: Int)
  case class Events(events: Vector[Event])
  
}

class BoxOffice(implicit timeout: Timeout) extends Actor {
  import BoxOffice._
  import context._
  
  def receive = {
    case GetEvents =>
    
    case GetEvent  =>
    
  }  
}
