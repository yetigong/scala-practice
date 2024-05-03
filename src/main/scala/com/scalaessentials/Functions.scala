package com.scalaessentials

object Functions {
  def function1(input: String, count: Int): String = {
    input + ":" + count
  }
  def main(args: Array[String]): Unit = {
    println(function1("abc", 5))
  }
}
