import Dependencies.Libraries

ThisBuild / scalaVersion := "2.13.10"

name := "life-reliever"

lazy val `life-reliever` = project
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
        Libraries.Logging.all ++
        Libraries.Cats.all ++
        Libraries.Refined.all ++
        Libraries.Circe.all ++
        Libraries.Ciris.all ++
        Libraries.Enumeratum.all ++
        Seq(
          Libraries.newtype,
          Libraries.`monocle-core`,
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
    name := "test-tools",
    libraryDependencies ++=
      Libraries.Testing.all ++
        Libraries.Http4s.all,
  )
  .dependsOn(common)

addCommandAlias(
  "styleCheck",
  "all scalafmtSbtCheck; scalafmtCheckAll; Test / compile; scalafixAll --check",
)

Global / lintUnusedKeysOnLoad := false
Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalafixDependencies += Dependencies.Libraries.`organize-imports`
ThisBuild / scalafixScalaBinaryVersion := scalaBinaryVersion.value
ThisBuild / semanticdbEnabled          := true
ThisBuild / semanticdbVersion          := scalafixSemanticdb.revision
