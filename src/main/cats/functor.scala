object functor extends App {
  import cats._

  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
    def map[A, B](fa: Option[A])(f: A => B) = fa map f
  }

  implicit val listFunctor: Functor[List] = new Functor[List] {
    def map[A, B](fa: List[A])(f: A => B) = fa map f
  }
  Functor[List].map(List("qwer", "adsfg"))(_.length) // List(4, 5)
  Functor[Option].map(Option("Hello"))(_.length) // Some(5)
  Functor[Option].map(None: Option[String])(_.length)

  val lenOption: Option[String] => Option[Int] = Functor[Option].lift(_.length)
  lenOption(Some("abcd")) // Some(4)

  val lenOption: Option[String] ⇒ Option[Int] = Functor[Option].lift(_.length)
  lenOption(Some("Hello"))

  val source = List("Cats", "is", "awesome")
  val product = Functor[List].fproduct(source)(_.length).toMap

  product.get("Cats").getOrElse(0) // 4
  product.get("is").getOrElse(0) // 2
  product.get("awesome").getOrElse(0) // 7

  val listOpt = Functor[List] compose Functor[Option]
  listOpt.map(List(Some(1), None, Some(3)))(_ + 1) // List(Some(2), None, Some(4))
}