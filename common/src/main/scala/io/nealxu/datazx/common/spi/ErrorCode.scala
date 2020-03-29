package io.nealxu.datazx.common.spi

trait ErrorCode {

  def getCode: String

  def getDescription: String

}
