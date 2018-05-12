package com.goticks

import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext

class RestApi(system: ActorSystem, timeout: Timeout) extends RestRoutes {
  implicit def executionContext = system.dispatcher
  implicit val requestTimeout = timeout
  def createBoxOffice = system.actorOf(BoxOffice.props, BoxOffice.name)
}

trait RestRoutes extends BoxOfficeApi with EventMarshalling {
  import StatusCode._ 
  def routes: Route = allEventsRoute
  
  def allEventsRoute = 
    pathPrefix("events") {
      pathEndOrSingleSlash {
        get {
          //Get /events
          onSuccess() { events =>
            complete(OK)
          }  
        } 
      }  
    }  
}

trait BoxOfficeApi {
  import BoxOffice._
  
  def createBoxOffice: ActorRef
  implicit def executionContext: ExecutionContext
  implicit def requestTimeout: Timeout
  lazy val boxOffice = createBoxOffice
  
  def getEvents() =  
  //"no more events"
    boxOffice.ask(GetEvents).mapTo[Event]
}
