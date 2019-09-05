object apply extends App {
  import cats._

  implicit val optionApply: Apply[Option] = new Apply[Option] {
    def ap[A, B](f: Option[A => B])(fa: Option[A]): Option[B] =
      fa.flatMap(a => f.map(ff => ff(a)))

    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa map f

    override def product[A, B](fa: Option[A], fb: Option[B]): Option[(A, B)] =
      fa.flatMap(a => fb.map(b => (a, b)))
  }

  implicit val listApply: Apply[List] = new Apply[List] {
    def ap[A, B](f: List[A => B])(fa: List[A]): List[B] =
      fa.flatMap(a => f.map(ff => ff(a)))

    def map[A, B](fa: List[A])(f: A => B): List[B] = fa map f

    override def product[A, B](fa: List[A], fb: List[B]): List[(A, B)] =
      fa.zip(fb)
  }

  import cats.implicits._

  val intToString: Int ⇒ String = _.toString
  val double: Int ⇒ Int = _ * 2
  val addTwo: Int ⇒ Int = _ + 2

  Apply[Option].map(Some(1))(intToString) // Some("1")

  Apply[Option].map(Some(1))(double)
  Apply[Option].map(None)(addTwo)

  val listOpt = Apply[List] compose Apply[Option]
  val plusOne = (x: Int) ⇒ x + 1
  listOpt.ap(List(Some(plusOne)))(List(Some(1), None, Some(3))) // List(Some(2), None, Some(4))

  Apply[Option].ap(Some(intToString))(Some(1)) // Some("1")
  Apply[Option].ap(Some(double))(Some(1)) // Some(2)
  Apply[Option].ap(Some(double))(None)  // None
  Apply[Option].ap(None)(Some(1))  // None
  Apply[Option].ap(None)(None) //None

  val addArity2 = (a: Int, b: Int) ⇒ a + b
  Apply[Option].ap2(Some(addArity2))(Some(1), Some(2)) // Some(3)
  Apply[Option].ap2(Some(addArity2))(Some(1), None) // None

  val addArity3 = (a: Int, b: Int, c: Int) ⇒ a + b + c
  Apply[Option].ap3(Some(addArity3))(Some(1), Some(2), Some(3)) // Some(6)

  Apply[Option].map2(Some(1), Some(2))(addArity2) // Some(3)
  Apply[Option].map3(Some(1), Some(2), Some(3))(addArity3) // Some(6)

  Apply[Option].tuple2(Some(1), Some(2)) // Some((1,2))
  Apply[Option].tuple3(Some(1), Some(2), Some(3)) //  Some((1,2,3))

  import cats.implicits._
  val option2 = Option(1) |@| Option(2)
  val option3 = option2 |@| Option.empty[Int]

  option2 map addArity2 // Some(3)
  option3 map addArity3 // None

  option2 apWith Some(addArity2) // Some(3)
  option3 apWith Some(addArity3)  // None

}