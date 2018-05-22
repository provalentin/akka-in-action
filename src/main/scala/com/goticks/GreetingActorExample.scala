import akka.actor._
case class WhoToGreet(who: String)
class GreetingActor extends Actor {
  def receive = {
    case WhoToGreet(who) => println(s"Hello ${who}")
  }
}
object GreetingActorExample extens App {
  val system = ActorSystem("Hello-Akka")
  val greeter = system.actorOf(Props["GreetingActor"], "greeter")
  greeter ! WhoToGreet("Hello-Scala")
  sytem.terminate()
}
