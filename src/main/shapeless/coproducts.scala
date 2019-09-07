object coproducts extends App {
  import shapeless._

  type ISB = Int :+: String :+: Boolean :+: CNil

  val isb = Coproduct[ISB]("foo")

  println(isb.select[Int]) // None
  isb.select[String] // Some("foo")

  object sizeM extends Poly1 {
    implicit def caseInt = at[Int](i => (i, i))
    implicit def caseString = at[String](s => (s, s.length))
    implicit def caseBoolean = at[Boolean](b => (b, 1))
  }

  val m = isb map sizeM

  println(m.select[(String, Int)]) // Some((foo,3))

  import record._, union._, syntax.singleton._

  type U = Union.`'i -> Int, 's -> String, 'b -> Boolean`.T

  val u = Coproduct[U]('s ->> "foo") // Inject a String into the union at label 's

  println(u.get('i)) // None
  println(u.get('s)) // Some("foo")
  println(u.get('b)) // None
}