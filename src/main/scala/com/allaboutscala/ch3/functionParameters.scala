package com.allaboutscala.ch3

object functionParameters extends App {
  println("How to define function with parameters")

  def calculateDonutCost(donutName: String, quantity: Int): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    // make some calculations ...
    2.50 * quantity
  }

  println("How to call a function with parameters")
  val totalCost = calculateDonutCost("Glazed Donut", 5)
  println(s"Total cost of donuts = $totalCost")

  println("How to add default values to function parameters")

  def calculateDonutCost(donutName: String,
                         quantity: Int,
                         couponCode: String = "NO CODE"): Double = {
    println(
      s"Calculating cost for $donutName, quantity = $quantity, couponCode = $couponCode")
    // make some calculations ...
    2.50 * quantity
  }

  println("How to call a function with parameters that has default values")
  val totalCostWithDiscount =
    calculateDonutCost("Glazed Donut", 4, "COUPON_12345")
  val totalCostWithoutDiscount = calculateDonutCost("Glazed Donut", 4)
}
