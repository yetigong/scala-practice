package com.scalapractice

object Palindrome {
  def isPalindrome(input: String): Boolean = {
    input.toLowerCase.reverse == input.toLowerCase
  }
  def main(args: Array[String]): Unit = {
    val input = List("aba",
      "abc", "tacocat", "abA", "Aa")
    val result = input.map(s => isPalindrome(s))
    println(result)
  }
}
