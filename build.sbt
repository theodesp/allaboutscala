name := "allaboutscala"

version := "0.1"

scalaVersion := "2.12.7"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "org.typelevel" %% "cats-core" % "2.0.0-RC1"
)