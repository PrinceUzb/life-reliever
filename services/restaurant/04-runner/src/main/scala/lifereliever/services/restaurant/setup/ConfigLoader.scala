package lifereliever.services.restaurant.setup

import cats.effect.Async
import cats.implicits.catsSyntaxTuple2Parallel

import lifereliever.support.services.http4s.HttpServerConfig
import lifereliever.support.skunk.DataBaseConfig
import lifereliever.syntax.refined.commonSyntaxAutoRefineV

object ConfigLoader {
  def load[F[_]: Async]: F[Config] = (
    HttpServerConfig.configValues("RESTAURANT"),
    DataBaseConfig.configValues,
  ).parMapN(Config.apply).load[F]
}
