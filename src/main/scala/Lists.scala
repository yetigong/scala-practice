object Lists {
  def convertListToString(input: List[Int], word: String): List[String] = {
    input.map(num => word * num)
  }

  /**
   * Generate sequence from a list
   * @param start
   * @param n
   * @return
   */
    
  def generateSequence(start: Int, n: Int): List[Int] = {
    if (n == 0) Nil
    else {
      start :: generateSequence(start+1, n-1)
    }
  }

  /**
   * Find all prime numbers in the original input
   * @param input
   * @return
   */
  def getPrimeNumbers(input: List[Int]): List[Int] = {
    input.filter(i => isPrime(i))
  }

  def isPrime(i: Int): Boolean ={
    if (i <= 1) false
    else if (i == 2) true
    else {
      val seq = generateSequence(2, i/2)
      !seq.exists(divisor => i % divisor == 0)
    }
  }

  /**
   * get digits from a list of numbers into a flattened list. For example, List(12, 34) => List(1, 2, 3, 4)
   * @param input
   * @return
   */
  def getDigits(input: List[Int]): List[Int] = {
    input.flatMap(num => getDigitFromNumber(num))
  }

  def getDigitFromNumber(i: Int): List[Int] = {
    if (i < 10) List(i)
    else {
      val lastDigit = i % 10
      val remainder = i / 10
      getDigitFromNumber(remainder) :+ lastDigit
    }
  }


  def main(args: Array[String]): Unit = {
    println(s"exercise 1 result is ${convertListToString(List(1, 2, 3), "scala")}")

    val seq = generateSequence(1, 10)
    println(s"exercise 2 result is ${seq}")
    println(s"exercise 3 result is ${getPrimeNumbers(seq)}")

    println(s"exercise 4 result is ${getDigits(List(123, 456, 78))}")
  }
}
