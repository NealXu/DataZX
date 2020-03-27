package io.nealxu.datazx.core.utils

import org.slf4j.{Logger, LoggerFactory}

trait LogSupport {

  protected val log: Logger = LoggerFactory.getLogger(this.getClass)

  def logError(s: String, throwable: Throwable = null): Unit = log.error(s, throwable)

  def logInfo(s: String): Unit = log.info(s)

}
