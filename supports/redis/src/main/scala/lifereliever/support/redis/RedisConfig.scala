package lifereliever.support.redis

import ciris._
import ciris.refined.refTypeConfigDecoder
import lifereliever.UriAddress

case class RedisConfig(uri: UriAddress)
object RedisConfig {
  def configValues: ConfigValue[Effect, RedisConfig] =
    env("REDIS_SERVER_URI")
      .as[UriAddress]
      .map(RedisConfig.apply)
}
