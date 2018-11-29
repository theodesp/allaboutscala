package com.allaboutscala.ch3

object functionImplicits extends App {
  println(s"How to define a function with an implicit parameter")

  def totalCost(donutType: String, quantity: Int)(
      implicit discount: Double): Double = {
    println(s"Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  println("How to define an implicit value")
  implicit val discount: Double = 0.1
  println(s"All customer will receive a ${discount * 100}% discount")

  println("How to call a function which has an implicit parameter")
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost(
    "Glazed Donut",
    5)}""")

  println("How to define a function which takes multiple implicit parameters")

  def totalCost2(donutType: String, quantity: Int)(
      implicit discount: Double,
      storeName: String): Double = {
    println(s"[$storeName] Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  // It is a good practice to encapsulate your implicit values into an Object or a Package Object.
  println("How to call a function which takes multiple implicit parameters")
  implicit val storeName: String = "Tasty Donut Store"
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost2(
    "Glazed Donut",
    5)}""")

  println(
    "How to create a wrapper String class which will extend the String type")

  class DonutString(s: String) {
    def isFavoriteDonut: Boolean = s == "Glazed Donut"
  }

  println(
    "How to create an implicit function to convert a String to the wrapper String class")

  object DonutConverstions {
    implicit def stringToDonutString(s: String): DonutString =
      new DonutString(s)
  }

  import DonutConverstions._

  println("How to create String values")
  val glazedDonut = "Glazed Donut"
  val vanillaDonut = "Vanilla Donut"

  println("How to access the custom String function called isFavaoriteDonut")
  println(s"Is Glazed Donut my favorite Donut = ${glazedDonut.isFavoriteDonut}")
  println(
    s"Is Vanilla Donut my favorite Donut = ${vanillaDonut.isFavoriteDonut}")
}
