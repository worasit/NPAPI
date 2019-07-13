import Dependencies._

ThisBuild / scalaVersion := "2.13.0"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.nongped"

lazy val NPAPI = (project in file("."))
  .settings(
    name := "NPAPI",
    libraryDependencies += scalaTest % Test
  )