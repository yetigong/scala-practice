package com.scalapractice

import scala.util.Random

object ReserviorSampling {
  def sample[A] (input: List[A]): Option[A] = {
    if (input.isEmpty) {
      None
    } else {
      val result = input.foldLeft((None, 0))((tuple, elem) => {
        val probability = 1.0 / (tuple._2 + 1)
        val swap = Random().nextDouble() < probability
        if (swap)
          (Some(elem), tuple._2 + 1)
        else
          (tuple._1, tuple._2 + 1)
      })
      result._1
    }
  }

  def simulate[A] (input: List[A], count: Int): Map[A, Int] = {
    val emptyMap = Map[A, Int]()
    Range(1, count).toList.foldLeft(emptyMap)((counters, simulationId) => {
      sample(input).map(v => {
        val count = counters.getOrElse(v, 0) + 1
        counters.updated(v, count)
      }).getOrElse(counters)
    })
  }
  def main(args: Array[String]): Unit = {
    println("result for empty list" + sample(Nil))

    val input = List("A", "B", "C", "D", "E")
    val times = 1000000
    println(s"simulations results for samping $input $times is ${simulate(input, times)}")
  }
}
