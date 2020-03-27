package io.nealxu.datazx.core

import io.nealxu.datazx.core.utils.LogSupport

object Engine extends LogSupport {

  private def entry(args: Array[String]): Int = {
    1
  }

  def main(args: Array[String]): Unit = {
    val exitCode = try {
      entry(args)
    } catch {
      case _: Exception =>
        // TODO: ExceptionTracker
        logError(s"Failure reason of task is: ExceptionTracker")
        1
    }

    System.exit(exitCode)
  }

}
