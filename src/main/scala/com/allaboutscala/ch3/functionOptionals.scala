package com.allaboutscala.ch3

object functionOptionals extends App {
  println("How to define an Option in a function parameter")

  def calculateDonutCost(donutName: String,
                         quantity: Int,
                         couponCode: Option[String]): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    couponCode match {
      case Some(coupon) =>
        val discount = 0.1 // Let's simulate a 10% discount
        val totalCost = 2.50 * quantity * (1 - discount)
        totalCost

      case None => 2.50 * quantity
    }
  }

  println("How to call a function which has an Option parameter")
  println(s"""Total cost = ${calculateDonutCost("Glazed Donut", 5, None)}""")

  println("How to assign a default value to an Option parameter")

  def calculateDonutCostWithDefaultOptionValue(
      donutName: String,
      quantity: Int,
      couponCode: Option[String] = None): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    couponCode match {
      case Some(coupon) =>
        val discount = 0.1 // Let's simulate a 10% discount
        val totalCost = 2.50 * quantity * (1 - discount)
        totalCost

      case _ => 2.50 * quantity
    }
  }

  println(
    "How to call a function which has an Option parameter with a default value")
  println(
    s"""Total cost = ${calculateDonutCostWithDefaultOptionValue("Glazed Donut",
                                                                5)}""")
  println(
    s"""Total cost with discount = ${calculateDonutCostWithDefaultOptionValue(
      "Glazed Donut",
      5,
      Some("COUPON_1234"))}""")

  println(s"Define a function which returns an Option of type String")

  def dailyCouponCode(): Option[String] = {
    // look up in database if we will provide our customers with a coupon today
    val couponFromDb = "COUPON_1234"
    Option(couponFromDb).filter(_.nonEmpty)
  }

  println(s"Call function with Option return type using getOrElse")
  val todayCoupon: Option[String] = dailyCouponCode()
  println(
    s"Today's coupon code = ${todayCoupon.getOrElse("No Coupon's Today")}")

  println(s"Call function with Option return type using pattern matching")
  dailyCouponCode() match {
    case Some(couponCode) => println(s"Today's coupon code = $couponCode")
    case None             => println(s"Sorry there is no coupon code today!")
  }

  println(s"Call function with Option return type using map")
  dailyCouponCode().map(couponCode =>
    println(s"Today's coupon code = $couponCode"))

}
