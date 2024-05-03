ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-practice"
  )

libraryDependencies += "org.typelevel" %% "cats-kernel" % "2.10.0"

libraryDependencies ++= Seq(
  "org.creativescala" %% "doodle" % "0.19.0",
  "org.scalameta" %% "munit" % "0.7.29" % Test
)