package com.allaboutscala.ch2

import com.allaboutscala.ch2

object Donut extends Enumeration {
  type Donut = Value

  val Glazed: ch2.Donut.Value = Value("Glazed")
  val Strawberry: ch2.Donut.Value = Value("Strawberry")
  val Plain: ch2.Donut.Value = Value("Plain")
  val Vanilla: ch2.Donut.Value = Value("Vanilla")
}

object Example extends App {
  println("\nStep 2: How to print the String value of the enumeration")
  println(s"Vanilla Donut string value = ${Donut.Vanilla}")

  println("\nStep 3: How to print the id of the enumeration")
  println(s"Vanilla Donut's id = ${Donut.Vanilla.id}")

  println("\nStep 4: How to print all the values listed in Enumeration")
  println(s"Donut types = ${Donut.values}")

  println("\nStep 5: How to pattern match on enumeration values")
  Donut.values.foreach {
    case d if d == Donut.Strawberry || d == Donut.Glazed =>
      println(s"Found favourite donut = $d")
    case _ => None
  }
}
