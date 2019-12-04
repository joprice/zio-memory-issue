import java.util.concurrent.Executors

import cats.effect.{ExitCode, IO}
import org.http4s.Status.Successful
import org.http4s._
import org.http4s.client.blaze.BlazeClientBuilder
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext

object CatsBlazeClient extends cats.effect.IOApp {

  val url = Uri.unsafeFromString("http://localhost:8080")

  val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))

  override def run(args: List[String]): IO[ExitCode] =
    BlazeClientBuilder[IO](ec)
    .resource
    .use { client =>

      val get: IO[Unit] = {
        client.fetch(Request[IO](uri = url)) {
          case Successful(_) =>
            IO.pure(())
          case _ =>
            IO.raiseError(new Throwable("not working"))
        }
      }

      lazy val loop: IO[Unit] = for {
        _ <- get
        _ <- IO.sleep(10.milliseconds)
        _ <- loop
      } yield ()

      loop.map(_ => ExitCode.Success)
    }
}
