package my.space.simple.scala

import akka.actor.Actor
import org.json4s.NoTypeHints
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write
import spray.http._
import spray.routing._

class MySprayService extends Actor with HttpService {

  override def receive = runRoute(simpleRoute ~ healthRoute)

  override implicit def actorRefFactory = context

  implicit val formats = Serialization.formats(NoTypeHints)

  val simpleRoute =
    path("hello") {
      get {
        respondWithMediaType(MediaTypes.`application/json`) {
          complete {
            HttpResponse(200, write(Message(1,"Hello World")))
          }
        }
      }
    }

  val healthRoute =
  path("health") {
    get {
      complete("Healthy")
    }
  }
}

case class Message(code: Int, message: String)