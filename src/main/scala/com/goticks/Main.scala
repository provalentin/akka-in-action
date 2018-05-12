package com.goticks

import scala.concurrent.Future

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.util.Timeout


import com.typesafe.config.{Config, ConfigFactory}

object Main extends App with RequestTimeout {
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")
  
  println(s"Config => host: $host, port: $port")
  
  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher
  
  val api = new RestApi(system, requestTimeout(config)).routes
  implicit val materializer = ActorMaterializer()
  
  val bindingFutur: Future[ServerBinding] = 
    Http().bindAndHandle(api, host, port)
  
}

trait RequestTimeout {
  import scala.concurrent.duration._
  def requestTimeout(config: Config):Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}  
