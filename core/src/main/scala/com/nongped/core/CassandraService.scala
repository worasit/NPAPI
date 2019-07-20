package com.nongped.core

import com.nongped.core.services.Service
import org.apache.logging.log4j.scala.Logging

class CassandraService extends Service with Logging {
  override def connect(): Unit = {
    logger.info("Connect")
  }

  override def close(): Unit = {
    logger.info("Close")
  }
}
