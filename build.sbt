import Dependencies.Libraries

ThisBuild / scalaVersion := "2.13.10"

name := "life-reliever"

lazy val root = project
  .in(file("."))
  .aggregate(
    integrations,
    supports,
    services,
    `test-tools`,
  )

lazy val common = project
  .in(file("common"))
  .settings(
    name := "common",
    libraryDependencies ++=
      Libraries.Derevo.all ++
        Libraries.Refined.all ++
        Libraries.Circe.all ++
        Libraries.Ciris.all ++
        Libraries.Enumeratum.all ++
        Seq(
          Libraries.newtype
        ),
  )

lazy val integrations = project
  .in(file("integrations"))
  .settings(
    name := "integrations"
  )

lazy val supports = project
  .in(file("supports"))
  .settings(
    name := "supports"
  )

lazy val services = project
  .in(file("services"))
  .settings(
    name := "services"
  )

lazy val `test-tools` = project
  .in(file("test"))
  .settings(
    name := "test-tools"
  )
