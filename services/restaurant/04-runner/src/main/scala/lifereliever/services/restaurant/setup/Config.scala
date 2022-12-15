package lifereliever.services.restaurant.setup

import lifereliever.support.database.MigrationsConfig
import lifereliever.support.services.http4s.HttpServerConfig
import lifereliever.support.skunk.DataBaseConfig
import lifereliever.syntax.refined.commonSyntaxAutoUnwrapV

case class Config(
    httpServer: HttpServerConfig,
    database: DataBaseConfig,
  ) {
  lazy val migrations: MigrationsConfig = MigrationsConfig(
    hostname = database.host,
    port = database.port,
    database = database.database,
    username = database.user,
    password = database.password.value,
    schema = "restaurant",
    location = "db/migration",
  )
}
