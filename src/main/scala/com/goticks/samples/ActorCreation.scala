package com.goticks.samples

import akka.actor._

case class PlayMusic
case class StopMusic

case class StartPlay
case class StopPlay

class MusicController extends Actor {
  def receive = {
    case PlayMusic => {
      println("controller: PlayMusic")
      val player = system.actorOf(Props[MusicPlayer], "player")
      player ! StartPlay
    }  
    case StopMusic => println("controller: StopMusic")
    case _ => println("controller: unknown msg")
  }
}

class MusicPlayer extends Actor {
  def receive = {
    case StartPlay => println("player: Start Play")
    case StopPlay  => println("player: stop Play")
    case _ => println("player: unknown msg")
  }
}

class MusicPlayerController extends App {
  val system = ActorSystem("player")
  val controller = system.actorOf(Props[MusicController], "controller")
  controller ! PlayMusic
  
  system.terminate()
  
}
