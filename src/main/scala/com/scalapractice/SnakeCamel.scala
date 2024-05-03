package com.scalapractice

object SnakeCamel {
  def snakeToCamel(input: String): String = {
    def helper(input: String, index: Int, prevUnderScore: Boolean, output: String): String = {
      if (index == input.length) output
      else {
        val currChar = input.charAt(index)
        if (currChar == '_') {
          helper(input, index+1, true, output)
        } else {
          if (prevUnderScore) {
            helper(input, index+1, false, output.appended(currChar.toUpper))
          } else {
            helper(input, index+1, false, output.appended(currChar))
          }
        }
      }
    }

    helper(input, 0, false, "")
  }

  def main(args: Array[String]): Unit = {
    val input = List("a_b", "snake_to_camel", "_abc", "abc_")
    input.foreach(i => println(snakeToCamel(i)))
  }
}
