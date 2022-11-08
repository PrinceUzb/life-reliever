import Dependencies.Libraries

name         := "database"
organization := "IT-Forelead"
scalaVersion := "2.13.10"

dependsOn(
  LocalProject("common")     % CompileAndTest,
  LocalProject("test-tools") % CompileAndTest,
)
libraryDependencies ++=
  Libraries.Skunk.all ++
    Seq(
      Libraries.Flyway.`flyway-core`
    )
