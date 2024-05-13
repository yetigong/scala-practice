package com.scalapractice

import scala.util.Random

object Pi {
  case class Point(x: Double, y: Double)
   def pi(iterations: Long): Double = {

     val points = Range.Long(1L, iterations, 1) map (_ => Point(Random.nextDouble(), Random.nextDouble()))
     // need to decide whether a point belongs in the circle
     // println(points)
     val pointsInCircle = points.filter(isInCircle)
     1.0 * pointsInCircle.length / points.size * 4
   }

   def isInCircle(point: Point): Boolean = {
     math.pow(point.x, 2) + math.pow(point.y, 2) <= 1
   }
  @main def test(): Unit = {
    println(pi(100000))
  }
}
