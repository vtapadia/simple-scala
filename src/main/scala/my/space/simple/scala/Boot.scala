package my.space.simple.scala

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._

object Boot extends App {
  implicit val system = ActorSystem("learn-scala")
  val service = system.actorOf(Props(new MySprayService()), "learn-scala-rest")

  implicit val timeout = Timeout(5 minutes)

  IO(Http) ? Http.Bind(service,interface = "localhost", port = 8118)


}
