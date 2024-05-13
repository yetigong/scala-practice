package com.scalapractice

enum DecisionTree {

  def decide(value: Int): Boolean = {
    this match {
      case Result(result) => result
      case Split(split, left, right) =>
        if (value <= split) left.decide(value)
        else right.decide(value)
    }
  }

  case Result(result: Boolean)
  case Split(
              split: Int,
              lessThanOrEqual: DecisionTree,
              greaterThan: DecisionTree
            )
}
object DecisionTree {

  def main(args: Array[String]): Unit = {
    val tree = Split(
      4,
      Split(2, Result(false), Result(true)),
      Split(6, Result(false), Result(true))
    )
    println(tree.decide(0)) // should be false
    println(tree.decide(1)) // should be false
    println(tree.decide(2)) // should be false
    println(tree.decide(3)) // should be true
    println(tree.decide(4)) // should be true
    println(tree.decide(5)) // should be false
    println(tree.decide(6)) // should be false
    println(tree.decide(7)) // should be true
    println(tree.decide(8)) // should be true
  }
}
