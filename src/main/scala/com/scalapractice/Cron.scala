package com.scalapractice

enum Time {
  case All
  case At(value: Int)
}

object Time {
  def fromString(input: String): Time =
    input match {
      case "*" => All
      case number => At(number.toInt)
    }
}

final case class CronLine(
                           minute: Time,
                           hour: Time,
                           day: Time,
                           month: Time,
                           dayOfWeek: Time,
                           command: String
                         )


object Cron {
  
  def parse(input: String): CronLine = {
    val tokens = input.split(" ").toList
    tokens match {
      case min::hour::day::month::dayOfWeek::command =>
        CronLine(Time.fromString(min),
          Time.fromString(hour),
          Time.fromString(day),
          Time.fromString(month),
          Time.fromString(dayOfWeek),
          command.mkString(" "))
      case _ => throw new IllegalArgumentException(s"Invalid cron input $input")
    }
  }
  def main(args: Array[String]): Unit = {
    println(parse("* * * * * echo 'Hello'"))
    println(parse("15 2 3 4 2 echo 'Hello'"))
    println(parse("15 * 3 * * echo 'Hello'"))
  }
}
