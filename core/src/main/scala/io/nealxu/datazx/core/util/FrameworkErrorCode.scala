package io.nealxu.datazx.core.util

import io.nealxu.datazx.common.spi.ErrorCode

object FrameworkErrorCode {

  case object INSTALL_ERROR extends ErrorCode {
    override def getCode: String = "Framework-00"
    override def getDescription: String = "INSTALL_ERROR."
  }

  case object ARGUMENT_ERROR extends ErrorCode {
    override def getCode: String = "Framework-01"
    override def getDescription: String = "ARGUMENT_ERROR."
  }

  case object RUNTIME_ERROR extends ErrorCode {
    override def getCode: String = "Framework-02"
    override def getDescription: String = "RUNTIME_ERROR."
  }

  case object CONFIG_ERROR extends ErrorCode {
    override def getCode: String = "Framework-03"
    override def getDescription: String = "CONFIG_ERROR."
  }

  case object SECRET_ERROR extends ErrorCode {
    override def getCode: String = "Framework-04"
    override def getDescription: String = "SECRET_ERROR."
  }

  case object HOOK_LOAD_ERROR extends ErrorCode {
    override def getCode: String = "Framework-05"
    override def getDescription: String = "HOOK_LOAD_ERROR."
  }

  case object HOOK_FAIL_ERROR extends ErrorCode {
    override def getCode: String = "Framework-06"
    override def getDescription: String = "HOOK_FAIL_ERROR."
  }

  case object PLUGIN_INSTALL_ERROR extends ErrorCode {
    override def getCode: String = "Framework-10"
    override def getDescription: String = "PLUGIN_INSTALL_ERROR."
  }

  case object PLUGIN_NOT_FOUND extends ErrorCode {
    override def getCode: String = "Framework-11"
    override def getDescription: String = "PLUGIN_NOT_FOUND."
  }

  case object PLUGIN_INIT_ERROR extends ErrorCode {
    override def getCode: String = "Framework-12"
    override def getDescription: String = "PLUGIN_INIT_ERROR."
  }

  case object PLUGIN_RUNTIME_ERROR extends ErrorCode {
    override def getCode: String = "Framework-13"
    override def getDescription: String = "PLUGIN_RUNTIME_ERROR ."
  }

  case object PLUGIN_DIRTY_DATA_LIMIT_EXCEED extends ErrorCode {
    override def getCode: String = "Framework-14"
    override def getDescription: String = "PLUGIN_DIRTY_DATA_LIMIT_EXCEED."
  }

  case object PLUGIN_SPLIT_ERROR extends ErrorCode {
    override def getCode: String = "Framework-15"
    override def getDescription: String = "PLUGIN_SPLIT_ERROR."
  }

  case object KILL_JOB_TIMEOUT_ERROR extends ErrorCode {
    override def getCode: String = "Framework-16"
    override def getDescription: String = "KILL_JOB_TIMEOUT_ERROR."
  }

  case object START_TASKGROUP_ERROR extends ErrorCode {
    override def getCode: String = "Framework-17"
    override def getDescription: String = "START_TASKGROUP_ERROR."
  }

  case object CALL_DATAZX_SERVICE_FAILED extends ErrorCode {
    override def getCode: String = "Framework-18"
    override def getDescription: String = "CALL_DATAZX_SERVICE_FAILED."
  }

  case object CALL_REMOTE_FAILED extends ErrorCode {
    override def getCode: String = "Framework-19"
    override def getDescription: String = "CALL_REMOTE_FAILED."
  }

  case object KILLED_EXIT_VALUE extends ErrorCode {
    override def getCode: String = "Framework-143"
    override def getDescription: String = "KILLED_EXIT_VALUE."
  }

}
