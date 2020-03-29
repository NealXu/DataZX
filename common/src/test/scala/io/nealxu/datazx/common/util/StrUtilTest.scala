package io.nealxu.datazx.common.util

import org.scalatest.FlatSpecLike

class StrUtilTest extends FlatSpecLike {

  "Byte number converts to string" should "work as expect" in {
    val kb: Long = 1024
    val mb = 1024 * kb
    val gb = 1024 * mb
    val tb = 1024 * gb

    val byteNumInTb = (23 * tb + 0.1 * tb).toLong
    assertResult("23.10TB")(StrUtil.stringify(byteNumInTb))

    val byteNumInGb = 23 * gb
    assertResult("23.00GB")(StrUtil.stringify(byteNumInGb))

    val byteNumInMb = 23 * mb
    assertResult("23.00MB")(StrUtil.stringify(byteNumInMb))

    val byteNumInKb = 23 * kb
    assertResult("23.00KB")(StrUtil.stringify(byteNumInKb))

    val byteNum = 23
    assertResult("23B")(StrUtil.stringify(byteNum))
  }

  "Replace variable in json string with system property" should "work as expect" in {
    val prop = "java_home"
    val value = "/c/program/jdk"
    System.setProperty(prop, value)
    val jsonStr =
    """
        |{
        |  "user": "neal"
        |  "jdk": "${java_home}"
        |}
    """.stripMargin

    val jsonStrAfterReplaceVar =
      """
        |{
        |  "user": "neal"
        |  "jdk": "/c/program/jdk"
        |}
    """.stripMargin
    assertResult(jsonStrAfterReplaceVar)(StrUtil.replaceVariable(jsonStr))
  }

  "Replace variables in json string without system property" should "work as expect" in {
    val jsonStr =
      """
        |{
        |  "user": "neal"
        |  "python": "${python_home}"
        |}
    """.stripMargin

    assertResult(jsonStr)(StrUtil.replaceVariable(jsonStr))
  }

}
