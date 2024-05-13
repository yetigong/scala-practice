package com.scalapractice

enum Expression {
  def +(that: Expression): Expression =
    println(s"this is $this, that is $that")
    Addition(this, that)

  def -(that: Expression): Expression =
    println(s"this is $this, that is $that")
    Subtraction(this, that)

  def *(that: Expression): Expression =
    println(s"this is $this, that is $that")
    Multiplication(this, that)

  def /(that: Expression): Expression =
    println(s"this is $this, that is $that")
    Division(this, that)

  case Literal(value: Double)
  case Addition(left: Expression, right: Expression)
  case Subtraction(left: Expression, right: Expression)
  case Multiplication(left: Expression, right: Expression)
  case Division(left: Expression, right: Expression)
}

object Expression {
  def apply(value: Double): Expression = Literal(value)
}

object Eval {

  import Expression.*

  def apply(expr: Expression): Double =
    expr match {
      case Literal(value) => value
      case Addition(left, right) => Eval(left) + Eval(right)
      case Subtraction(left, right) => Eval(left) - Eval(right)
      case Multiplication(left, right) => Eval(left) * Eval(right)
      case Division(left, right) => Eval(left) / Eval(right)
    }
}

/**
 * the following code is trying to test if the arithmatic operations can be always
 * honored for priority
 */
case class Person(name: String) {
  def +(that: Person): Person = {
    this.copy(name = s"(${this.name} + ${that.name})")
  }
  def *(that: Person): Person = {
    this.copy(name = s"(${this.name} * ${that.name})")
  }
}
object Calculator {

  def main(args: Array[String]): Unit = {
    println(Eval(Expression(1.0) + Expression(3.0))) // should be 4.0
    println(Eval(Expression(4.0) / Expression(2.0) + Expression(2.0))) // should be 4.0
    println(Eval(Expression(1.0) * Expression(3.0))) // should be 3.0
    println(Eval(Expression(1.0) * Expression(3.0) - Expression(2.0))) // should be 1.0
    println(Eval(Expression(1.0) + Expression(3.0) * Expression(2.0))) // should be 7.0
    println(Eval(Expression(2.0) * Expression(3.0) + Expression(5.0))) // should be 11.0

    println(Person("a") + Person("b") * Person("c"))
    println(Person("a") * Person("b") + Person("c"))
  }

}
