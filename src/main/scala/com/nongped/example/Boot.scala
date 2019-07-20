package com.nongped.example

import com.nongped.core.CassandraService
import org.apache.logging.log4j.scala.Logging

object Boot extends App with Logging {
  val cassandraService = new CassandraService()
  logger.info("Hello Nongped API V.2")
  cassandraService.connect()
}
