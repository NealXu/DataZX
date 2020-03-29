package io.nealxu.datazx.common.util

import com.alibaba.fastjson.JSON
import java.io.{File, FileInputStream, FileNotFoundException, IOException, InputStream}

import io.nealxu.datazx.common.exception.{CommonErrorCode, DatazxException}
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.StringUtils


class Configuration {

  private var root: Object = _

  def this(jsonStr: String) = {
    this

    val jsonStrAfterReplaceVar = StrUtil.replaceVariable(jsonStr)
    require(!StringUtils.isBlank(jsonStrAfterReplaceVar))

    try {
      root = JSON.parse(jsonStrAfterReplaceVar)
    } catch {
      case e: Exception =>
        throw DatazxException.asDatazxException(
          CommonErrorCode.CONFIG_ERROR, s"invalid json: s${e.getMessage}"
        )
    }
  }

  def set(path: String, obj: Object): Object = {
    // TODO
    null
  }

}

object Configuration {

  def newDefault(): Configuration = from("{}")

  def from(jsonStr: String): Configuration = new Configuration(jsonStr)

  def from(jsonFile: File): Configuration = {
      try {
        from(IOUtils.toString(new FileInputStream(jsonFile)))
      } catch {
        case  e: FileNotFoundException =>
          throw DatazxException.asDatazxException(
            CommonErrorCode.CONFIG_ERROR, s"file not found: ${jsonFile.getAbsolutePath}"
          )
        case  e: IOException =>
          throw DatazxException.asDatazxException(
            CommonErrorCode.CONFIG_ERROR, s"read file fail: ${jsonFile.getAbsolutePath}", e
          )
      }
  }

  def from(inputStream: InputStream): Configuration = {
    try {
      from(IOUtils.toString(inputStream))
    } catch {
      case  e: IOException =>
        throw DatazxException.asDatazxException(
          CommonErrorCode.CONFIG_ERROR, s"read file fail: $e"
        )
    }
  }

  def from(jsonMap: Map[String, Object]): Configuration = from(toJsonStr(jsonMap))

  def from(objects: List[Object]): Configuration = from(toJsonStr(objects))

  def toJsonStr(obj: Object): String = JSON.toJSONString(obj)

}
