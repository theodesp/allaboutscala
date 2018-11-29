package com.allaboutscala.ch3

object functions1 extends App {
  def favouriteDonut(): String = "Glazed Donut"

  val myFavoriteDonut = favouriteDonut()
  println(s"My favorite donut is $myFavoriteDonut")

  // In general, you should define your functions without parenthesis
  // if you are defining a function that does not have any side effects.
  def dislikedDonut: String = "Blueberry Donut"

  val myLeastFavoriteDonut = dislikedDonut
  println(s"My least favorite donut is $myLeastFavoriteDonut")

  println("How to define and use a function with no return type")

  def printDonutSalesReport(): Unit = {
    // lookup sales data in database and create some report
    println("Printing donut sales report... done!")
  }

  printDonutSalesReport()

  println("Use type inference to define function with no return type")

  def printReport {
    // lookup sales data in database and create some report
    println("Printing donut report... done!")
  }

  printReport
}
