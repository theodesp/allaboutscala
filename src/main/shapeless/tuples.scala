object tuples extends App {
  import shapeless._
  import syntax.std.tuple._

  (23, "foo", true).head // 23
  println((23, "foo", true).tail) // (foo,true)
  println((23, "foo", true).drop(2)) // (true)
  (23, "foo", true).take(2) //
  println((23, "foo", true).split(1)) // ((23),(foo,true))

  val l = 23 +: ("foo", true)
  println(l) // (23,foo,true)
  val l2 = (23, "foo") :+ true // (23,foo,true)
  val l3 = (23, "foo") ++ (true, 2.0)
  l3 // (23, "foo", true, 2.0)

  import poly._

  object option extends (Id ~> Option) {
    def apply[T](t: T) = Option(t)
  }

  val ll = (23, "foo", true) map option
  println(ll) // (Some(23),Some(foo),Some(true))
  val l4 = ((23, "foo"), (), (true, 2.0)) flatMap identity // (23, "foo", true, 2.0)

  object size extends Poly1 {
    implicit def caseInt = at[Int](x => 1)
    implicit def caseString = at[String](_.length)
    implicit def caseTuple[T, U](implicit st: Case.Aux[T, Int],
                                 su: Case.Aux[U, Int]) =
      at[(T, U)](t => size(t._1) + size(t._2))
  }

  object addSize extends Poly2 {
    implicit def default[T](implicit st: size.Case.Aux[T, Int]) =
      at[Int, T] { (acc, t) =>
        acc + size(t)
      }
  }

  println((23, "foo", (13, "wibble")).foldLeft(0)(addSize)) // 11
  println((23, "foo", true).productElements) // 23 :: foo :: true :: HNil
  println((23, "foo", true).toList) // List(23, foo, true)

  import syntax.zipper._
  val l6 = (23, ("foo", true), 2.0).toZipper.right.down.put("bar").root.reify
  println(l6)

}