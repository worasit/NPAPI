package com.nongped.core

import com.nongped.core.services.Service
import org.apache.logging.log4j.scala.Logging

class CassandraService extends Service with Logging {

  implicit final private lazy val x = 42
  implicit final private lazy val y = 42

  override def connect(): Unit = {
    logger.info("Connect")
  }

  override def close(): Unit = {
    logger.info("Close")
    logger.info("")

    List(1).map { x =>
      x + 2
    }.filter(_ > 2)

    List(1).toIterator.buffered
      .map(_ + 2)
      .filter(_ > 2)
  }

  def test(x: Int, y: Int, z: Int): Unit = {
    ???
  }
}
