object hmap extends App {
  import shapeless._
  import syntax.std.function._
  import ops.function._

  def applyProduct[P <: Product, F, L <: HList, R](p: P)(
    f: F)(implicit gen: Generic.Aux[P, L], fp: FnToProduct.Aux[F, L => R]) =
    f.toProduct(gen.to(p))

  println(applyProduct(1, 2)((_: Int) + (_: Int))) // 3
  println(applyProduct(1, 2, 3)((_: Int) * (_: Int) * (_: Int))) // 6

  class BiMapIS[K, V]
  implicit val intToString = new BiMapIS[Int, String]
  implicit val stringToInt = new BiMapIS[String, Int]

  implicit val hm = HMap[BiMapIS](23 -> "foo", "bar" -> 13)
  //val hm2 = HMap[BiMapIS](23 -> "foo", 23 -> 13)   // Does not compile

  println(hm.get(23)) // Some(foo)
  println(hm.get("bar")) // Some(13)

  val l = 23 :: "bar" :: HNil
  val m = l map hm
  println(m)
}