package com.goticks

import akka.actor.ActorSystem
import akka.util.Timeout


class RestApi(system: ActorSystem, timeout: Timeout) {
  implicit def executionContext = system.dispatcher
  implicit val requestTimeout = timeout
  //def createBoxOffice = system.actorOf(BoxOffice.props, BoxOffice.name)
}
