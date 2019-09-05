object applicative extends App {
  import cats._
  import cats.implicits._

  Applicative[Option].pure(1) // Some(1)
  Applicative[List].pure(1) // List(1)

  (Applicative[List] compose Applicative[Option]).pure(1) // List(Some(1))

  Monad[Option].pure(1) // Some(1)
  Applicative[Option].pure(1) // Some(1)
}