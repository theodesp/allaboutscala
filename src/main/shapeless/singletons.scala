object singletons extends App {
  import shapeless._
  import syntax.singleton._
  import syntax.std.tuple._

  val hlist = 23 :: "foo" :: true :: HNil
  println(hlist(1)) // "foo"
  val tuple = (23, "foo", true)
  tuple(1) // "foo"

  //  23.value // 23

  val (wTrue, wFalse) = (Witness(true), Witness(false))

  type True = wTrue.T
  type False = wFalse.T

  trait Select[B] { type Out }

  implicit val selInt = new Select[True] { type Out = Int }
  implicit val selString = new Select[False] { type Out = String }

  def select(b: WitnessWith[Select])(t: b.instance.Out) = t

  println(select(true)(23)) // 23
  println(select(false)("foo")) // "foo"
}