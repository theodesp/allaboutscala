package com.allaboutscala.ch3

object variadicParams extends App {
  def printReport(names: String*) {
    println(s"""Donut Report = ${names.mkString(" - ")}""")
  }

  println(
    "How to call function which takes variable number of String arguments")
  printReport("Glazed Donut", "Strawberry Donut", "Vanilla Donut")
  printReport("Chocolate Donut")

  println("How to pass a List to a function with variable number of arguments")
  val listDonuts: List[String] =
    List("Glazed Donut", "Strawberry Donut", "Vanilla Donut")
  printReport(listDonuts: _*)
}
