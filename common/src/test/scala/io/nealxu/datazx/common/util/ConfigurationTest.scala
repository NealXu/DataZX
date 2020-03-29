package io.nealxu.datazx.common.util

import org.scalatest.FlatSpecLike

class ConfigurationTest extends FlatSpecLike {

  private val jsonStr =
    """
    |{
    |  "user": "neal",
    |  "isMale": "true",
    |  "skills": ["linux", "java", "scala"]
    |  "a": {
    |    "b": {
    |      "c": [1, 2, 3]
    |    }
    |  }
    |}
    """.stripMargin

  private val config = Configuration.from(jsonStr)

  "Get boolean from json object" should "work as expect" in {
    assertResult(true)(config.getBoolean("isMale"))
  }

  "Get list element from json object" should "work as expect" in {
    assertResult("java")(config.getString("skills[1]"))
  }

}
