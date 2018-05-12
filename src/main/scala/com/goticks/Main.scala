package com.goticks

import com.typesafe.config.{Config, ConfigFactory}

object Main extends App {
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")
  
  println(s"Config => host: $host, port: $port")
}
