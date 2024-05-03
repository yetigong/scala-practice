package com.scalapractice

/**
 * for a given transister, the actual range of resistence is a range
 *
 * [resistance * (1-tolerance), resistence * (1 + tolerence)
 *
 * Tolerance is presented as %
 */
sealed trait Resistor {
  def resistance: Double
  def tolerance: Double
}

enum E6(val resistance: Double) extends Resistor {
  val tolerance = 0.2
  case TenOhm extends E6(10.0)
  case FifteenOhm extends E6(15.0)
  case TwentyTwoOhm extends E6(22.0)
  case ThirtyThreeOhm extends E6(33.0)
  case FortySevenOhm extends E6(47.0)
  case SixtyEightOhm extends E6(68.0)
}

enum E12(val resistance: Double) extends Resistor {
  val tolerance = 0.1

  case TenOhm extends E12(10.0)
  case TwelveOhm extends E12(12.0)
  case FifteenOhm extends E12(15.0)
  case EighteenOhm extends E12(18.0)
  case TwentyTwoOhm extends E12(22.0)
  case TwentySevenOhm extends E12(27.0)
  case ThirtyThreeOhm extends E12(33.0)
  case ThirtyNineOhm extends E12(39.0)
  case FortySevenOhm extends E12(47.0)
  case FiftySixOhm extends E12(56.0)
  case SixtyEightOhm extends E12(68.0)
  case EightyTwoOhm extends E12(82.0)
}

object Resistance {
  def range(resistors: List[Resistor]): (Double, Double) =
    resistors.map(r => Tuple2(r.resistance * (1 - r.tolerance), r.resistance * (1 + r.tolerance)))
      .reduceLeft((v1, v2) => (v1._1 + v2._1, v1._2 + v2._2))

  def main(args: Array[String]): Unit = {
    val input = List(E6.TenOhm, E6.FifteenOhm, E12.FifteenOhm, E12.TwelveOhm)
    println(range(input))
  }
}
