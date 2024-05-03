package com.scalapractice

import cats.effect.unsafe.implicits.global
import doodle.core.Color
import doodle.image.Image
import doodle.image.syntax.all.ImageOps
import doodle.java2d.java2dRenderer

import scala.annotation.tailrec

/***
 * this exercise basically creates a stack of blocks with count using the doodle library
 */
object ColoredBlocks {
  // The block used to create the stack of blocks
  val block = Image.square(40).fillColor(Color.crimson)

  // Example showing how to create a stack of blocks
  val stack = block.above(block.above(Image.empty))

  /**
   * the basic element block is defined above
   * this method basically requries us to stack N blocks where N = count
   *
   * in the example above in the stack val, it stacks two blocks. Now this method stacks N blocks.
   *
   * @param count
   * @return
   */
  def stack(count: Int): Image = {
    /**
     * It is a common pratice to use tail recursion in functional programming to achieve something like a for loop
     * Usually the helper function would require a data structure (image in this case) as a holder for intermediate values.
     *
     * @param count
     * @param image
     * @return
     */
    @tailrec
    def helper(count: Int, image: Image): Image= {
      if (count == 0) image
      else helper(count-1, block.above(image))
    }

    helper(count, Image.empty)
  }

  def main(args: Array[String]): Unit = {
    stack(5).draw()
  }
}
