package lifereliever.services.timetable

import cats.{Monad, MonadThrow}
import cats.data.NonEmptyList
import cats.effect.{Async, ExitCode}
import cats.effect.kernel.Resource
import cats.implicits.toFunctorOps
import lifereliever.support.services.http4s.{HealthHttpRoutes, HttpServer, HttpServerConfig}
import org.http4s.HttpRoutes
import org.http4s.circe.JsonDecoder
import org.typelevel.log4cats.Logger

object HttpModule {
  private def allRoutes[F[_]: Monad: MonadThrow: JsonDecoder: Logger]: NonEmptyList[HttpRoutes[F]] =
    NonEmptyList.of[HttpRoutes[F]](
      new HealthHttpRoutes[F].routes
    )

  def make[F[_]: Async](
      config: HttpServerConfig
    )(implicit
      logger: Logger[F]
    ): Resource[F, F[ExitCode]] =
    HttpServer.make[F](config, allRoutes[F]).map { _ =>
      logger.info(s"Timetable service http server is started").as(ExitCode.Success)
    }
}
