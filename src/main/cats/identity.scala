object identity extends  App {
  import cats._

  val x: Id[Int] = 1
  val y: Int = x

  val anId: Id[Int] = 42
  anId // 42

  import cats.Functor

  val one: Int = 1
  Functor[Id].map(one)(_ + 1) // 2
  Applicative[Id].pure(42) // 42

  import cats.Monad

  val one: Int = 1
  Monad[Id].map(one)(_ + 1) // 2
  Monad[Id].flatMap(one)(_ + 1) // 2

  val fortytwo: Int = 42
  Comonad[Id].coflatMap(fortytwo)(_ + 1) // 43

}