package lifereliever.services.timetable.setup

import cats.MonadThrow
import cats.effect.Async
import cats.effect.Resource
import cats.effect.std.Console
import org.typelevel.log4cats.Logger
import skunk.Session

import lifereliever.support.database.Migrations

case class ServiceEnvironment[F[_]: MonadThrow](
    config: Config
  )

object ServiceEnvironment {
  def make[F[_]: Async: Console: Logger]: Resource[F, ServiceEnvironment[F]] =
    for {
      config <- Resource.eval(ConfigLoader.load[F])
      _ <- Resource.eval(Migrations.run[F](config.migrations))

      _ <- ServiceResources.make[F](config)
    } yield ServiceEnvironment[F](
      config
    )
}
