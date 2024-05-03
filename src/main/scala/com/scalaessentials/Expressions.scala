package com.scalaessentials

object Expressions {
  val mathExpressions = 2 + 3 * 5

  val codeBlock = {
    val localVal = 78
    localVal + 10
  }

  val somethingElse = 1
  def main(args: Array[String]): Unit = {
    print(codeBlock)
    print("abc")
  }
}
