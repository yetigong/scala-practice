package com.scalapractice

object SystemProperties {
  def main(args: Array[String]): Unit = {
    //scala.sys.SystemProperties.exclusively()
    new scala.sys.SystemProperties().foreach(pair => println(s"${pair._1}, ${pair._2}"))
  }
}
