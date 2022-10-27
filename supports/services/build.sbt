import Dependencies.Libraries

name         := "services"
organization := "uz.scala"
scalaVersion := "2.13.10"

libraryDependencies ++=
  Libraries.GRPC.all ++
    Libraries.Http4s.all ++
    Seq(
      Libraries.Logging.log4cats,
      Libraries.izumi,
      Libraries.`meow-mtl`,
    )

dependsOn(LocalProject("common"))
