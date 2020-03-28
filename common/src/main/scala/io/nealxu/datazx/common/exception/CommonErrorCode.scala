package io.nealxu.datazx.common.exception

import io.nealxu.datazx.common.spi.ErrorCode

object CommonErrorCode extends Enumeration with ErrorCode {

  type CommonErrorCode = Value

  val CONFIG_ERROR: Value = Value
  val CONVERT_NOT_SUPPORT: Value = Value
  val CONVERT_OVER_FLOW: Value = Value
  val RETRY_FAIL: Value = Value
  val RUNTIME_ERROR: Value = Value
  val HOOK_INTERNAL_ERROR: Value = Value
  val SHUT_DOWN_TASK: Value = Value
  val WAIT_TIME_EXCEED: Value = Value
  val TASK_HUNG_EXPIRED: Value = Value

}
