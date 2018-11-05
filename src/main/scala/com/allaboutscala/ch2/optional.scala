package com.allaboutscala.ch2

object optional extends App {
  println("Step 1: How to use Option and None - a basic example")
  val glazedDonutTaste: Option[String] = Some("Very Tasty")
  println(s"Glazed Donut taste = ${glazedDonutTaste.get}")
}
