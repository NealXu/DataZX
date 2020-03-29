package io.nealxu.datazx.common.util

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import io.nealxu.datazx.common.exception.{CommonErrorCode, DatazxException}
import java.io.{File, FileInputStream, FileNotFoundException, IOException, InputStream}
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.StringUtils
import scala.collection.JavaConverters._

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

  def get(path: String): Object = {
    checkPath(path)
    try {
      findObject(path)
    } catch {
      case e: Exception => null
    }
  }

  def getString(path: String): String = {
    get(path) match {
      case null => null
      case obj: Object => obj.toString
    }
  }

  def getBoolean(path: String): Boolean = {
    getString(path) match {
      case null => null
      case s if s.equalsIgnoreCase("true") => true
      case s if s.equalsIgnoreCase("false") => false
      case s => throw new IllegalArgumentException(s"not an boolean: $path $s")
    }
  }

  private def checkPath(path: String): Unit = {
    path match {
      case null =>
        throw new IllegalArgumentException(s"path is null.")
      case p =>
        StringUtils.split(p, ".").foreach { x =>
          if (StringUtils.isBlank(x))
            throw new IllegalArgumentException(s"invalid path: $p")
        }
    }
  }

  private def findObject(path: String): Object = {
    val target = this.root
    val isRootPath = StringUtils.isBlank(path)
    if (isRootPath) {
      target
    } else {

      splitPath(path).foldLeft(target) {
        (tmpTarget, pathSegment) =>
          pathSegment match {
            case mapPath if isMapPath(pathSegment) =>
              findObjectInMap(tmpTarget, mapPath)
            case listPath if isListPath(pathSegment) =>
              findObjectInList(tmpTarget, listPath)
            case _ =>
              throw new IllegalArgumentException(s"invalid path: $path $pathSegment")
          }
      }
    }
  }

  private def splitPath(path: String): List[String] = {
    StringUtils.split(path.replace("[", ".["), ".").toList
  }

  private def isMapPath(pathSegment: String): Boolean = {
    StringUtils.isNotBlank(pathSegment) && !isListPath(pathSegment)
  }

  private def isListPath(pathSegment: String): Boolean = {
    pathSegment.contains("[") && pathSegment.contains("]")
  }

  private def findObjectInMap(target: Object, pathSegment: String): Object = {
    target match {
      case map: java.util.Map[String, Object] =>
        try {
          map.asScala(pathSegment)
        } catch {
          case _: Exception =>
            throw new IllegalArgumentException(
              s"key not found: ${target.getClass.toString} $pathSegment"
            )
        }
      case _ =>
        throw new IllegalArgumentException(s"not a map: ${target.getClass.toString} $pathSegment")
    }
  }

  private def findObjectInList(target: Object, pathSegment: String): Object = {
    val index = try {
      pathSegment.replace("[", "").replace("]", "").toInt
    } catch {
      case _: Exception =>
        throw new IllegalArgumentException(s"not a list path: $pathSegment")
    }

    target match {
      case list: java.util.List[Object] =>
        try {
          list.asScala(index)
        } catch {
          case _: Exception =>
            throw new IllegalArgumentException(
              s"invalid index: ${target.getClass.toString} $pathSegment"
            )
        }
      case _ =>
        throw new IllegalArgumentException(s"not a list: ${target.getClass.toString} $pathSegment")
    }

  }
}

object Configuration {

  def newDefault(): Configuration = from("{}")

  def from(jsonStr: String): Configuration = new Configuration(jsonStr)

  def from(jsonFile: File): Configuration = {
    try {
      from(IOUtils.toString(new FileInputStream(jsonFile)))
    } catch {
      case _: FileNotFoundException =>
        throw DatazxException.asDatazxException(
          CommonErrorCode.CONFIG_ERROR, s"file not found: ${jsonFile.getAbsolutePath}"
        )
      case e: IOException =>
        throw DatazxException.asDatazxException(
          CommonErrorCode.CONFIG_ERROR, s"read file fail: ${jsonFile.getAbsolutePath}", e
        )
    }
  }

  def from(inputStream: InputStream): Configuration = {
    try {
      from(IOUtils.toString(inputStream))
    } catch {
      case e: IOException =>
        throw DatazxException.asDatazxException(
          CommonErrorCode.CONFIG_ERROR, s"read file fail: $e"
        )
    }
  }

  def from(jsonMap: Map[String, Object]): Configuration = from(toJsonStr(jsonMap))

  def from(objects: List[Object]): Configuration = from(toJsonStr(objects))

  def toJsonStr(obj: Object): String = JSON.toJSONString(obj, SerializerFeature.PrettyFormat)

}
