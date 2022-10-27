import Dependencies.Libraries

name         := "mailer"
organization := "uz.scala"
libraryDependencies ++=
  Libraries.Cats.all ++
    Seq(
      Libraries.mailer,
      Libraries.Logging.log4cats,
    )

dependsOn(LocalProject("common"))
