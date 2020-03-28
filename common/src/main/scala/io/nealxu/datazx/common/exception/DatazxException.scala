package io.nealxu.datazx.common.exception

import java.io.{PrintWriter, StringWriter}

import io.nealxu.datazx.common.spi.ErrorCode

private class DatazxException(errorCode: ErrorCode, message: String, cause: Throwable)
  extends RuntimeException(s"$errorCode - $message - ${DatazxException.getMessage(cause)}") {

  def this(errorCode: ErrorCode, message: String) = {
    this(errorCode, message, null)
  }

  def getErrorCode: ErrorCode = errorCode

}

object DatazxException {

  def asDatazxException(errorCode: ErrorCode, message: String): DatazxException = {
    new DatazxException(errorCode, message)
  }

  def asDatazxException(errorCode: ErrorCode, message: String, throwable: Throwable): DatazxException = {
    new DatazxException(errorCode, message, throwable)
  }

  private def getMessage(throwable: Throwable): String = {
    Option(throwable) match {
      case Some(t) =>
        val stringWriter = new StringWriter
        val printWriter = new PrintWriter(stringWriter)
        t.printStackTrace(printWriter)
        stringWriter.toString
      case None => ""
    }
  }

}
