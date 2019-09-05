object monad extends App {
  import cats._
  import cats.implicits._

  Option(Option(1)).flatten // Some(1)
  Option(None).flatten // None
  List(List(1), List(2, 3)).flatten // List(1,2,3)

  //implicit def optionMonad(implicit app: Applicative[Option]) =
  //  new Monad[Option] {
  //    // Define flatMap using Option's flatten method
  //    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] =
  //      app.map(fa)(f).flatten
  //    // Reuse this definition from Applicative.
  //    override def pure[A](a: A): Option[A] = app.pure(a)
  //  }

  Monad[Option].pure(42) // Some(42)

  //implicit val listMonad = new Monad[List] {
  //  def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)
  //  def pure[A](a: A): List[A] = List(a)
  //}

  Monad[List].flatMap(List(1, 2, 3))(x ⇒ List(x, x)) // List(1, 1, 2, 2, 3, 3)

  Monad[Option].ifM(Option(true))(Option("truthy"), Option("falsy")) // Some(truthy)

  Monad[List].ifM(List(true, false, true))(List(1, 2), List(3, 4))

  optionTMonad[List].pure(42)
}