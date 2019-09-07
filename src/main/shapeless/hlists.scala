object hlists extends App {
  import shapeless._
  import shapeless.poly._

  object choose extends (Set ~> Option) {
    def apply[T](s: Set[T]) = s.headOption
  }

  val sets = Set(1) :: Set("foo") :: HNil

  val opts = sets map choose

  println(opts)

  import poly.identity

  //val l = (23 :: "foo" :: HNil) :: HNil :: (true :: HNil) :: HNil

  //  println(l flatMap identity)

  import syntax.zipper._

  val l = 1 :: "foo" :: 3.0 :: HNil
  println(l.toZipper.right.delete.reify)

  trait Fruit
  case class Apple() extends Fruit
  case class Pear() extends Fruit

  type FFFF = Fruit :: Fruit :: Fruit :: Fruit :: HNil
  type APAP = Apple :: Pear :: Apple :: Pear :: HNil

  val a: Apple = Apple()
  val p: Pear = Pear()

  val apap: APAP = a :: p :: a :: p :: HNil

  import scala.reflect.runtime.universe._

  println(implicitly[TypeTag[APAP]].tpe.typeConstructor <:< typeOf[FFFF]) // true

  println(apap.isInstanceOf[FFFF]) // true
  println(apap.unify.isInstanceOf[FFFF]) // true
  println(apap.toList) // List(Apple(), Pear(), Apple(), Pear())

  import syntax.typeable._

  val ffff: FFFF = apap.unify
  val precise: Option[APAP] = ffff.cast[APAP]

  println(precise) // Some(Apple() :: Pear() :: Apple() :: Pear() :: HNil)
}