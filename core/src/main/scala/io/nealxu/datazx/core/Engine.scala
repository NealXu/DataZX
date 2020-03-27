package io.nealxu.datazx.core

import io.nealxu.datazx.common.exception.DatazxException
import io.nealxu.datazx.core.utils.container.CoreConstant
import org.apache.commons.cli.{BasicParser, Options}
import io.nealxu.datazx.core.utils.{ConfigParser, Configuration, LogSupport}

import scala.util.Try

class Engine extends LogSupport {

  def start(configuration: Configuration): Unit = {

  }

}

object Engine extends LogSupport {

  private val OPT_JOB = "job"
  private val OPT_JOB_ID = "jobid"
  private val OPT_MODE = "mode"

  private def entry(args: Array[String]): Unit = {
    val options = new Options
    options.addOption(OPT_JOB, true, "Job config.")
    options.addOption(OPT_JOB_ID, true, "Job unique id.")
    options.addOption(OPT_MODE, true, "Job runtime mode.")

    val parser = new BasicParser
    val cmdLine = parser.parse(options, args)

    val jobPath = cmdLine.getOptionValue(OPT_JOB)
    val jobId = Try { cmdLine.getOptionValue(OPT_JOB_ID).toLong }.getOrElse(-1)
    val mode = cmdLine.getOptionValue(OPT_MODE)
    if ("standalone".equalsIgnoreCase(mode)) throw new DatazxException

    val configuration = ConfigParser.parse(jobPath)
    configuration.set(CoreConstant.DATAZX_CORE_CONTAINER_JOB_ID, jobId)

    val engine = new Engine
    engine.start(configuration)
  }

  def main(args: Array[String]): Unit = {
    val exitCode = try {
      entry(args)
      0
    } catch {
      case _: Exception =>
        // TODO: ExceptionTracker
        logError(s"Failure reason of task is: ExceptionTracker")
        1
    }

    System.exit(exitCode)
  }

}
