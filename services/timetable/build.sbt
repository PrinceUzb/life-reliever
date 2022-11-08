import Dependencies.Libraries

name         := "users"
organization := "baby-med"
scalaVersion := "2.13.10"

lazy val `services_timetable-domain` = project
  .in(file("00-domain"))
  .dependsOn(
    LocalProject("common")     % CompileAndTest,
    LocalProject("test-tools") % CompileAndTest,
  )

lazy val `services_timetable-protocol` =
  project
    .in(file("01-protocol"))
    .dependsOn(
      `services_timetable-domain` % CompileAndTest,
      LocalProject("supports_services"),
    )
    .settings(
      libraryDependencies ++= Seq(
        Libraries.`cats-tagless-macros`
      )
    )
    .enablePlugins(SrcGenPlugin)

lazy val `services_timetable-core` =
  project
    .in(file("02-core"))
    .dependsOn(
      `services_timetable-protocol`    % CompileAndTest,
      LocalProject("support_database") % CompileAndTest,
    )

lazy val `services_timetable-server` =
  project
    .in(file("03-server"))
    .dependsOn(`services_timetable-core`)

lazy val `services_timetable-runner` =
  project
    .in(file("04-runner"))
    .dependsOn(`services_timetable-server`)
    .settings(
      libraryDependencies ++= Seq(
        Libraries.GRPC.server
      )
    )
    .settings(DockerImagePlugin.serviceSetting("timetable"))
    .enablePlugins(DockerImagePlugin, JavaAppPackaging, DockerPlugin)

aggregateProjects(
  `services_timetable-domain`,
  `services_timetable-protocol`,
  `services_timetable-core`,
  `services_timetable-server`,
  `services_timetable-runner`,
)
