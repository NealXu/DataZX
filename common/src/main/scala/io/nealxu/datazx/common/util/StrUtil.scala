package io.nealxu.datazx.common.util

import java.text.DecimalFormat

import org.apache.commons.lang3.StringUtils

import scala.collection.mutable
import scala.util.Try

object StrUtil {

  private final val KB_IN_BYTES: Long = 1024
  private final val MB_IN_BYTES: Long = 1024 * KB_IN_BYTES
  private final val GB_IN_BYTES: Long = 1024 * MB_IN_BYTES
  private final val TB_IN_BYTES: Long = 1024 * GB_IN_BYTES
  private final val df = new DecimalFormat("0.00")
  private final val VARIABLE_PATTERN = "(\\$)\\{?(\\w+)\\}?".r
  private final val SYSTEM_ENCODING =
    Try {
      System.getProperty("file.encoding")
    }.getOrElse("UTF-8")

  def stringify(byteNumber: Long): String = {
    byteNumber match {
      case _ if byteNumber / TB_IN_BYTES > 0 =>
        df.format(byteNumber.toDouble / TB_IN_BYTES.toDouble) + "TB"
      case _ if byteNumber / GB_IN_BYTES > 0 =>
        df.format(byteNumber.toDouble / GB_IN_BYTES.toDouble) + "GB"
      case _ if byteNumber / MB_IN_BYTES > 0 =>
        df.format(byteNumber.toDouble / MB_IN_BYTES.toDouble) + "MB"
      case _ if byteNumber / KB_IN_BYTES > 0 =>
        df.format(byteNumber.toDouble / KB_IN_BYTES.toDouble) + "KB"
      case _ => byteNumber.toString + "B"
    }
  }

  def replaceVariable(param: String): String = {
    val mapping = mutable.Map[String, String]()

    for (patternMatch <- VARIABLE_PATTERN.findAllMatchIn(param)) {
      val variable = patternMatch.group(2)
      val value = System.getProperty(variable) match {
        case v if !StringUtils.isBlank(v) => v
        case _ => patternMatch.group(0)
      }
      mapping.put(patternMatch.group(0), value)
    }

    mapping.foldLeft(param) { (r, x) => r.replace(x._1, x._2) }
  }

}
