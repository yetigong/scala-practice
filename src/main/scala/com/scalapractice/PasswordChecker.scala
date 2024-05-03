package com.scalapractice

object PasswordChecker {
  /**
   * requirements:
   * 1. at least 8 chars
   * 2. >= 1 upper case, and >= 1 lowercase
   * 3. >= 1 num or punctuation chars
   *
   * @param input
   * @return
   */
  def isValidPassword(input: String): Boolean = {
    val punctuationChars = Set(',', '.', ';', ':', '?', '!', '"', "_", "(", ")")
    input.length >= 8 &&
      input.exists(c => c.isUpper) &&
      input.exists(c => c.isLower) &&
      input.exists(c => c.isDigit || punctuationChars.contains(c))
  }
  def main(args: Array[String]): Unit = {
    val input = List(
      "Pb1234456!", // true
      "pb123456!",  // false
      "ppppbbbbb",  // false
      "pbdDdeft1!",   // true
      "pb123!", //false
    )
    println(input.map(isValidPassword))
  }
}
