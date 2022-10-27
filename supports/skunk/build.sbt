import Dependencies.Libraries

name         := "skunk"
organization := "uz.scala"
scalaVersion := "2.13.10"

libraryDependencies ++= Libraries.Skunk.all

dependsOn(LocalProject("common"))
