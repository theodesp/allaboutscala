package com.allaboutscala.ch2

object escaping extends App {
  println("\nStep 3: Using triple quotes \"\"\" to escape characters")
  val donutJson3: String =
    """{"donut_name":"Glazed Donut","taste_level":"Very Tasty","price":2.50}"""
  println(s"donutJson3 = $donutJson3")
  val donutJson4: String =
    """
      |{
      |"donut_name":"Glazed Donut",
      |"taste_level":"Very Tasty",
      |"price":2.50
      |}
    """.stripMargin
  println(s"donutJson4 = $donutJson4")
}
