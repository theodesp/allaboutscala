package com.allaboutscala.ch3

object parameterGroup extends App {
  def totalCost(donutType: String)(quantity: Int)(discount: Double): Double = {
    println(
      s"Calculating total cost for $quantity $donutType with ${discount * 100}% discount")
    val totalCost = 2.50 * quantity
    totalCost - (totalCost * discount)
  }

  println("How to call a function with curried parameter groups")
  println(s"Total cost = ${totalCost("Glazed Donut")(10)(0.1)}")

  println(
    "How to create a partially applied function from a function with curried parameter groups")
  val totalCostForGlazedDonuts = totalCost("Glazed Donut") _

  println("How to call a partially applied function")
  println(
    s"\nTotal cost for Glazed Donuts ${totalCostForGlazedDonuts(10)(0.1)}")
}
