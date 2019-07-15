import Dependencies._
import sbt._
import Keys._

// Bare style
// This syntax is recommended for "ThisBuild" scoped settings and adding plugins.
ThisBuild / scalaVersion := "2.13.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.nongped"

// Example customized tasks "sbt hello"
lazy val hello = taskKey[Unit]("An example task")

// Common settings
lazy val commonSettings = Seq(
  target := {}
)


lazy val NPAPI = (project in file("."))
  .aggregate(NPAPI_CORE)
  .dependsOn(NPAPI_CORE)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "NPAPI",
    hello := {
      println("Hello from the taskKey.")
    },
    libraryDependencies += scalaTest % Test
  )

lazy val NPAPI_CORE = (project in file("core"))
  .settings(
    name := "NPAPI_CORE",
    libraryDependencies ++= Seq(derby),
    libraryDependencies += scalaTest % Test
  )

lazy val NPAPI_UTIL = (project in file("util"))
  .settings(
    name := "NPAPI_UTIL",
    libraryDependencies += scalaTest % Test
  )