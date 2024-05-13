package com.scalaessentials

object Expressions {
  val mathExpressions = 2 + 3 * 5

  val codeBlock = {
    val localVal = 78
    localVal + 10
  }

  val somethingElse = 1

  val xValues = 1 to 4
  val yValues = 1 to 2
  val coordinates = for {
    x <- xValues
    y <- yValues
  } yield (x, y)
  def main(args: Array[String]): Unit = {
    print(codeBlock)
    print("abc")
    println(coordinates)
    val g: Int = 31
    println(g.toHexString)
  }
}
