package lifereliever.support.services.http4s

import scala.concurrent.duration._

import cats.Monad
import cats.data.NonEmptyList
import cats.effect.Async
import cats.effect.Resource
import cats.effect.kernel.Temporal
import cats.syntax.all._
import com.comcast.ip4s.Host
import com.comcast.ip4s.Port
import org.http4s.HttpApp
import org.http4s.HttpRoutes
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.Server
import org.http4s.server.middleware._
import org.typelevel.ci.CIStringSyntax
import org.typelevel.log4cats.Logger

object HttpServer {
  private[http4s] val CorsConfig: CORSPolicy =
    CORS
      .policy
      .withAllowOriginAll
      .withAllowMethodsAll
      .withAllowHeadersIn(
        Set(
          ci"Content-Type",
          ci"Authorization",
        )
      )
      .withMaxAge(1.day)

  private[http4s] def middleware[F[_]: Monad: Temporal](http: HttpRoutes[F]): HttpRoutes[F] =
    Timeout(60.seconds)(CorsConfig(AutoSlash(http)))

  private[http4s] def httpLogger[F[_]](implicit logger: Logger[F]): Option[String => F[Unit]] =
    Option(logger.info(_))

  def make[F[_]: Async: Logger](
      config: HttpServerConfig,
      routes: NonEmptyList[HttpRoutes[F]],
    ): Resource[F, Server] = {

    val loggers: HttpApp[F] => HttpApp[F] = { http: HttpApp[F] =>
      RequestLogger.httpApp(
        config.logger.httpHeader,
        config.logger.httpBody,
        logAction = httpLogger[F],
      )(http)
    } andThen { http: HttpApp[F] =>
      ResponseLogger.httpApp(
        config.logger.httpHeader,
        config.logger.httpBody,
        logAction = httpLogger[F],
      )(http)
    }

    val httpApp = loggers(middleware[F](routes.reduce[HttpRoutes[F]](_ <+> _)).orNotFound)
    EmberServerBuilder
      .default[F]
      .withHostOption(Host.fromString("0.0.0.0"))
      .withPort(
        Port
          .fromInt(config.httpPort.value)
          .getOrElse(throw new IllegalArgumentException("Port is incorrect"))
      )
      .withHttpApp(httpApp)
      .build
  }
}
