package com.allaboutscala.ch2

object comprehension extends App {
  println("Step 1: A simple for loop from 1 to 5 inclusive")
  for (numberOfDonuts <- 1 to 5) {
    println(s"Number of donuts to buy = $numberOfDonuts")
  }

  println("\nStep 2: A simple for loop from 1 to 5, where 5 is NOT inclusive")
  for (numberOfDonuts <- 1 until 5) {
    println(s"Number of donuts to buy = $numberOfDonuts")
  }

  println("\nStep 3: Filter values using if conditions in for loop")
  val donutIngredients =
    List("flour", "sugar", "egg yolks", "syrup", "flavouring")
  for (ingredient <- donutIngredients if ingredient == "sugar") {
    println(s"Found sweetening ingredient = $ingredient")
  }

  println(
    "\nStep 4: Filter values using if conditions in for loop and return the result back using the yield keyword")
  val sweeteningIngredients = for {
    ingredient <- donutIngredients
    if ingredient == "sugar" || ingredient == "syrup"
  } yield ingredient
  println(s"Sweetening ingredients = $sweeteningIngredients")
}
