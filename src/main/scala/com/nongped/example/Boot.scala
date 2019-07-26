package com.nongped.example

import com.nongped.core.CassandraService
import org.apache.logging.log4j.scala.Logging

object Boot extends App with Logging {
  val cassandraService = new CassandraService()
  val x                = 10
  logger.info("Hello Nongped API V.2")
  cassandraService.connect()
}

class thisShouldReportInSonarQube {}
