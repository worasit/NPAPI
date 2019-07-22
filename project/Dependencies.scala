import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {
  val scalaTest     = "org.scalatest"          %% "scalatest"            % "3.0.8"
  val derby         = "org.apache.derby"       % "derby"                 % "10.4.1.3"
  val cassandraCore = "com.datastax.cassandra" % "cassandra-driver-core" % "3.7.1"
  // Example customized tasks "sbt hello"
  lazy val hello = taskKey[Unit]("An example task")

  // Common settings
  lazy val commonSettings = Seq(
    libraryDependencies ++= Seq(
      scalaTest                  % Test,
      "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0",
      "org.apache.logging.log4j" % "log4j-api" % "2.11.0",
      "org.apache.logging.log4j" % "log4j-core" % "2.11.0" % Runtime
    )
  )
}
