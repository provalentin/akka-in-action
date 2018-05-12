package com.goticks

import akka.actor.ActorSystem
import akka.http.scaladsl.server._
import akka.util.Timeout


class RestApi(system: ActorSystem, timeout: Timeout) extends RestRoutes {
  implicit def executionContext = system.dispatcher
  implicit val requestTimeout = timeout
  //def createBoxOffice = system.actorOf(BoxOffice.props, BoxOffice.name)
}

trait RestRoutes {
 
  def routes: Route
}
