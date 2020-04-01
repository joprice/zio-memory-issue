import java.util.concurrent.Executors

import org.http4s.Status.Successful
import org.http4s._
import org.http4s.client.blaze.BlazeClientBuilder
import zio._
import zio.duration._
import zio.interop.catz._
import zio.clock._

import scala.concurrent.ExecutionContext

object ZioBlazeClient extends App {

  val url = Uri.unsafeFromString("http://localhost:8080")

  val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] =
    ZIO.runtime[Clock].flatMap { implicit rt =>
      BlazeClientBuilder[Task](ec)
        .resource
        .use { client =>

          val get: Task[Unit] = {
            client.fetch(Request[Task](uri = url)) {
              case Successful(_) =>
                Task.succeed(())
              case _ =>
                Task.fail(new Throwable("not working"))
            }
          }

          (get.ignore *> sleep(10.milliseconds)).forever.provide(rt.environment)
        }
        .as(0)
        .orDie
    }
}
