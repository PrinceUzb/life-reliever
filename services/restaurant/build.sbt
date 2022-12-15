import Dependencies.Libraries

name         := "restaurant"
organization := "uz.scala"
scalaVersion := "2.13.10"

lazy val `services_restaurant-domain` = project
  .in(file("00-domain"))
  .dependsOn(
    LocalProject("common")     % CompileAndTest,
    LocalProject("test-tools") % CompileAndTest,
  )

lazy val `services_restaurant-protocol` =
  project
    .in(file("01-protocol"))
    .dependsOn(
      `services_restaurant-domain` % CompileAndTest,
      LocalProject("supports_services"),
    )
    .settings(
      libraryDependencies ++= Seq(
        Libraries.`cats-tagless-macros`
      )
    )
    .enablePlugins(SrcGenPlugin)

lazy val `services_restaurant-core` =
  project
    .in(file("02-core"))
    .dependsOn(
      `services_restaurant-protocol`    % CompileAndTest,
      LocalProject("support_database") % CompileAndTest,
    )

lazy val `services_restaurant-server` =
  project
    .in(file("03-server"))
    .dependsOn(`services_restaurant-core`)

lazy val `services_restaurant-runner` =
  project
    .in(file("04-runner"))
    .dependsOn(`services_restaurant-server`)
    .settings(
      libraryDependencies ++= Seq(
        Libraries.GRPC.server
      )
    )
    .settings(DockerImagePlugin.serviceSetting("restaurant"))
    .enablePlugins(DockerImagePlugin, JavaAppPackaging, DockerPlugin)

aggregateProjects(
  `services_restaurant-domain`,
  `services_restaurant-protocol`,
  `services_restaurant-core`,
  `services_restaurant-server`,
  `services_restaurant-runner`,
)
