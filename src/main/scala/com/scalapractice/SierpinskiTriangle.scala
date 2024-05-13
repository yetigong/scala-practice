package com.scalapractice

import cats.effect.unsafe.implicits.global
import doodle.core.Color
import doodle.image.Image
import doodle.image.syntax.all.*
import doodle.java2d.*

object SierpinskiTriangle {
  val triangle = Image.equilateralTriangle(30).fillColor(Color.black)

  def sierpinski(depth: Int): Image = {
    if (depth == 1) triangle
    else {
      val subtriangle = sierpinski(depth - 1)
      subtriangle.above(subtriangle.beside(subtriangle))
    }
  }
  def main(args: Array[String]): Unit = {
    sierpinski(5).draw()
  }
}
