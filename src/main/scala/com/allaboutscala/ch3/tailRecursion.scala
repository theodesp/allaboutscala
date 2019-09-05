package com.allaboutscala.ch3

import scala.util.control.TailCalls._

object tailRecursion extends App {
  println("How to define an Array of type String")
  val arrayDonuts: Array[String] =
    Array("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")

  println("How to define a tail recursive function")
  @annotation.tailrec
  def search(donutName: String,
             donuts: Array[String],
             index: Int): Option[Boolean] = {
    if (donuts.length == index) {
      None
    } else if (donuts(index) == donutName) {
      Some(true)
    } else {
      val nextIndex = index + 1
      search(donutName, donuts, nextIndex)
    }
  }

  println("How to call a tail recursive function")
  val found = search("Glazed Donut", arrayDonuts, 0)
  println(s"Find Glazed Donut = $found")

  val notFound = search("Chocolate Donut", arrayDonuts, 0)
  println(s"Find Chocolate Donut = $notFound")

  println(
    "How to define a tail recursive function using scala.util.control.TailCalls._")
  def tailSearch(donutName: String,
                 donuts: Array[String],
                 index: Int): TailRec[Option[Boolean]] = {
    if (donuts.length == index) {
      done(None) // NOTE: done is imported from scala.util.control.TailCalls._
    } else if (donuts(index) == donutName) {
      done(Some(true))
    } else {
      val nextIndex = index + 1
      tailcall(tailSearch(donutName, donuts, nextIndex)) // NOTE: tailcall is imported from  scala.util.control.TailCalls._
    }
  }

  println(
    "How to call tail recursive function using scala.util.control.TailCalls._")
  val tailFound = tailcall(tailSearch("Glazed Donut", arrayDonuts, 0))
  println(s"Find Glazed Donut using TailCall = ${tailFound.result}") // NOTE: our returned value is wrapped so we need to get it by calling result

  val tailNotFound = tailcall(tailSearch("Chocolate Donut", arrayDonuts, 0))

  println(
    "How to define a trampoline function using scala.util.control.TailCalls")
  def verySweetDonut(donutList: List[String]): TailRec[Boolean] = {
    println(s"verySweetDonut function: donut list = $donutList")
    if (donutList.isEmpty) {
      println("verySweetDonut function: donut list isEmpty, returning false")
      done(false)
    } else {
      if (Set(donutList.head).subsetOf(
            Set("Vanilla Donut", "Strawberry Donut", "Glazed Donut"))) {
        println(
          s"verySweetDonut function: found donut list's head = ${donutList.head} to be VERY sweet, returning true")
        done(true)
      } else {
        println(
          s"verySweetDonut function: donut list's head = ${donutList.head} is NOT VERY sweet, forwarding donut list's to notSweetDonut function")
        tailcall(notSweetDonut(donutList))
      }
    }
  }

//  println(
//    s"notSweetDonut function: donut list's head = ${donutList.head} is NOT sweet, forwarding donut list's tail to verySweetDonut function")

  println(
    "How to define a trampoline function using scala.util.control.TailCalls")
  def notSweetDonut(donutList: List[String]): TailRec[Boolean] = {
    println(s"notSweetDonut function: with donut list = $donutList")
    if (donutList.isEmpty) {
      println("notSweetDonut function: donut list isEmpty, returning false")
      done(false)
    } else {
      println(
        s"notSweetDonut function: donut list's head = ${donutList.head} is NOT sweet,   forwarding donut list's tail to verySweetDonut function")
      tailcall(verySweetDonut(donutList.tail))
    }
  }

}
