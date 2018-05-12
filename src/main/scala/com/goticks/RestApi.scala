package com.goticks

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.util.Timeout


class RestApi(system: ActorSystem, timeout: Timeout) extends RestRoutes {
  implicit def executionContext = system.dispatcher
  implicit val requestTimeout = timeout
  //def createBoxOffice = system.actorOf(BoxOffice.props, BoxOffice.name)
}

trait RestRoutes extends BoxOfficeApi with EventMarshalling {
 
  def routes: Route = allEventsRoute
  
  def allEventsRoute = 
    pathPrefix("events") {
       get {
         //Get /events
         onSuccess(getEvents()) { events =>
           complete(OK, events)
         }  
       }  
    }  
}

trait BoxOfficeApi {
  import BoxOffice._
  def getEvents() =  
    boxOffice.ask(GetEvents).mapTo[Event]
}
