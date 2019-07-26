import Dependencies._
import sbt._
import Keys._
import sbt.librarymanagement.TrackLevel.TrackIfMissing

// Bare style
// This syntax is recommended for "ThisBuild" scoped settings and adding plugins.
ThisBuild / scalaVersion := "2.12.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.nongped"

coverageMinimum := 70
coverageFailOnMinimum := false
coverageHighlighting := true
publishArtifact in Test := false
parallelExecution in Test := false
sonarUseExternalConfig := true

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)
//export SONAR_SCANNER_HOME=/usr/local/bin/sonar-scanner

lazy val NPAPI = (project in file("."))
  .aggregate(NPAPI_CORE, NPAPI_UTIL)
  .dependsOn(NPAPI_CORE)
  .enablePlugins(JavaAppPackaging)
  .settings(
    inThisBuild(
      Seq(
        trackInternalDependencies := TrackIfMissing,
        exportJars := true
      )
    ),
    commonSettings,
    name := "NPAPI",
    hello := {
      println("Hello from the taskKey.")
    }
  )

lazy val NPAPI_CORE = (project in file("core"))
  .dependsOn(NPAPI_UTIL)
  .settings(
    commonSettings,
    name := "NPAPI_CORE",
    libraryDependencies ++= Seq(derby)
  )

lazy val NPAPI_UTIL = (project in file("util"))
  .settings(
    commonSettings,
    name := "NPAPI_UTIL"
  )
