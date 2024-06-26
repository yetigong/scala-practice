package com.scalaexercise

object stdlib {
  def main(args: Array[String]): Unit = {
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.collect {
      case x: Int if (x % 2 == 0) => x * 3
    }
    println(result)
  }
}
