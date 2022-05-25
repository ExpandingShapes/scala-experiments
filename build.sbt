name := "scala-experiments"

lazy val root = project.in(file(".")).aggregate(scala2, scala3)

lazy val scala2 = project
  .in(file("scala2"))
  .settings(scalaVersion := "2.13.8")

lazy val scala3 = project
  .in(file("scala3"))
  .settings(scalaVersion := "3.1.2")
