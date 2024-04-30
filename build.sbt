ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val root = (project in file("."))
  .settings(
    name := "scala-practice"
  )

libraryDependencies += "org.typelevel" %% "cats-kernel" % "2.10.0"