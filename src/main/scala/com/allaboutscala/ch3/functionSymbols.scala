package com.allaboutscala.ch3

object functionSymbols extends App {
  println("How to create and instantiate a class")
  val donutCostCalculator = new DonutCostCalculator()

  println("How to call function whose name is just the symbol -")
  println(s"Calling function - = ${donutCostCalculator.-(10.5)}")

  println("How to call a function using the operator style notation")
  println(
    s"Calling function - with operator style notation = ${donutCostCalculator - 10.5}")

}

class DonutCostCalculator {

  // We are hard-coding the totalCost value for simplicity.
  val totalCost = 100

  def minusDiscount(discount: Double): Double = {
    totalCost - discount
  }

  // How to define function whose name is just the symbol minus -
  def -(discount: Double): Double = {
    totalCost - discount
  }
}
