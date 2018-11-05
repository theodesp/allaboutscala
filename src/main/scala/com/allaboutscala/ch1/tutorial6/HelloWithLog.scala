package com.allaboutscala.ch1.tutorial6

import com.typesafe.scalalogging.LazyLogging

object HelloWithLog extends App with LazyLogging {
  logger.info("Hello world of log")
}
