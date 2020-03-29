package io.nealxu.datazx.common.exception

import io.nealxu.datazx.common.spi.ErrorCode

object CommonErrorCode {

  case object CONFIG_ERROR extends ErrorCode {
    override def getCode: String = "Common-00"
    override def getDescription: String = "config error."
  }

  case object CONVERT_NOT_SUPPORT extends ErrorCode {
    override def getCode: String = "Common-01"
    override def getDescription: String = "convert not support."
  }

  case object CONVERT_OVER_FLOW extends ErrorCode {
    override def getCode: String = "Common-02"
    override def getDescription: String = "convert overflow."
  }

  case object RETRY_FAIL extends ErrorCode {
    override def getCode: String = "Common-10"
    override def getDescription: String = "retry fail."
  }

  case object RUNTIME_ERROR extends ErrorCode {
    override def getCode: String = "Common-11"
    override def getDescription: String = "runtime error."
  }

  case object HOOK_INTERNAL_ERROR extends ErrorCode {
    override def getCode: String = "Common-12"
    override def getDescription: String = "hook internal error."
  }

  case object SHUT_DOWN_TASK extends ErrorCode {
    override def getCode: String = "Common-20"
    override def getDescription: String = "shutdown task."
  }

  case object WAIT_TIME_EXCEED extends ErrorCode {
    override def getCode: String = "Common-21"
    override def getDescription: String = "wait time exceed."
  }

  case object TASK_HUNG_EXPIRED extends ErrorCode {
    override def getCode: String = "Common-22"
    override def getDescription: String = "task hung expired."
  }

}

