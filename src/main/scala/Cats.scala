import cats.kernel.Semigroup

object Cats {
  val combined = Semigroup[List[Int]].combine(List(1, 2, 3), List(4, 5, 6))

  val aMap = Map("foo" -> Map("bar" -> 5))
  val anotherMap = Map("foo" -> Map("bar" -> 6))
  val combinedMap = Semigroup[Map[String, Map[String, Int]]].combine(aMap, anotherMap)
  def main(args: Array[String]): Unit = {
    println(combined)
    println(Semigroup[Option[Int]].combine(Option(1), Option(2)))
    println(Semigroup[Option[Int]].combine(Option(1), None))
    println(combinedMap)
  }
}
