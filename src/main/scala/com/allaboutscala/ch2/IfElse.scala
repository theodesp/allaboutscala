package com.allaboutscala.ch2

object IfElse extends App {
  println("Step 1: Using if clause as a statement")
  val numberOfPeople = 20
  val donutsPerPerson = 2

  if (numberOfPeople > 10)
    println(s"Number of donuts to buy = ${numberOfPeople * donutsPerPerson}")

  println(s"\nStep 2: Using if and else clause as a statement")
  val defaultDonutsToBuy = 8

  if (numberOfPeople > 10)
    println(s"Number of donuts to buy = ${numberOfPeople * donutsPerPerson}")
  else
    println(s"Number of donuts to buy = $defaultDonutsToBuy")

  println("\nStep 3: Using if, else if, and else clause as a statement")
  if (numberOfPeople > 10) {
    println(s"Number of donuts to buy = ${numberOfPeople * donutsPerPerson}")
  } else if (numberOfPeople == 0) {
    println("Number of people is zero.")
    println("No need to buy donuts.")
  } else {
    println(s"Number of donuts to buy = $defaultDonutsToBuy")
  }

  println("\nStep 4: Using if and else clause as expression")
  val numberOfDonutsToBuy =
    if (numberOfPeople > 10) numberOfPeople * donutsPerPerson
    else defaultDonutsToBuy
  println(s"Number of donuts to buy = $numberOfDonutsToBuy")
}
