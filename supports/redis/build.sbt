import Dependencies.Libraries

name         := "redis"
organization := "uz.scala"
scalaVersion := "2.13.10"

libraryDependencies ++= Libraries.Redis.all

dependsOn(LocalProject("common"))
