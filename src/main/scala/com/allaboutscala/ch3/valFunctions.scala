package com.allaboutscala.ch3

object valFunctions extends App {
  println(
    "How to define a higher order function which takes another function as parameter")

  def totalCostWithDiscountFunctionParameter(donutType: String)(quantity: Int)(
      f: Double => Double): Double = {
    println(s"Calculating total cost for $quantity $donutType")
    val totalCost = 2.50 * quantity
    f(totalCost)
  }

  println("How to define and pass a val function to a higher order function")
  val applyDiscountValueFunction = (totalCost: Double) => {
    val discount = 2 // assume you fetch discount from database
    totalCost - discount
  }
  println(
    s"Total cost of 5 Glazed Donuts with discount val function = ${totalCostWithDiscountFunctionParameter(
      "Glazed Donut")(5)(applyDiscountValueFunction)}")

  println("Assume a pre-calculated total cost amount")
  val totalCost: Double = 10

  println("How to define a val function to apply discount to total cost")
  val applyDiscountValFunction = (amount: Double) => {
    println("Apply discount function")
    val discount = 2 // fetch discount from database
    amount - discount
  }

  println("How to call a val function")
  println(
    s"Total cost of 5 donuts with discount = ${applyDiscountValFunction(totalCost)}")

  println("How to define a val function to apply tax to total cost")
  val applyTaxValFunction = (amount: Double) => {
    println("Apply tax function")
    val tax = 1 // fetch tax from database
    amount + tax
  }

  println("How to call andThen on a val function")
  println(
    s"Total cost of 5 donuts = ${(applyDiscountValFunction andThen applyTaxValFunction)(totalCost)}")

  println("How to call compose on a val function")
  println(
    s"Total cost of 5 donuts = ${(applyDiscountValFunction compose applyTaxValFunction)(totalCost)}")

}
