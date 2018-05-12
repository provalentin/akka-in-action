package com.goticks

import akka.actor.ActorSystem
import akka.util.Timeout


import com.typesafe.config.{Config, ConfigFactory}

object Main extends App with RequestTimeout {
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")
  
  println(s"Config => host: $host, port: $port")
  
  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher
}

trait RequestTimeout {
  import scala.concurent.duration._
  def requestTimeout(config: Config):Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.lenght, d.unit)
  }
}  
