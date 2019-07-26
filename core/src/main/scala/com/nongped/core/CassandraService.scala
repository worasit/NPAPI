package com.nongped.core

import com.nongped.core.services.Service
import org.apache.logging.log4j.scala.Logging

class CassandraService extends Service with Logging {

  implicit final private lazy val x = 42
  implicit final private lazy val y = 42

  override def connect(): Unit = {
    logger.info("Connect")
    if (true) print("true")
  }

  override def close(): Unit = {
    logger.info("Close")
  }

  def test(x: Int, y: Int, z: Int): Unit = {
    ???
  }
}
