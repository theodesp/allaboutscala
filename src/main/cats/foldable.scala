object foldable extends App {
  import cats._
  import cats.implicits._

  Foldable[List].foldLeft(List(1, 2, 3), 0)(_ + _) // 6
  Foldable[List].foldLeft(List("a", "b", "c"), "")(_ + _) // "abc"

  val lazyResult =
    Foldable[List].foldRight(List(1, 2, 3), Now(0))((x, rest) ⇒ Later(x + rest.value))
  lazyResult.value // 6

  Foldable[List].fold(List("a", "b", "c")) // "abc"
  Foldable[List].fold(List(1, 2, 3)) // 6

  Foldable[List].foldMap(List("a", "b", "c"))(_.length) // 3
  Foldable[List].foldMap(List(1, 2, 3))(_.toString) // "123"

  Foldable[List].foldK(List(List(1, 2), List(3, 4, 5))) // List(1, 2, 3, 4, 5)
  Foldable[List].foldK(List(None, Option("two"), Option("three"))) // Some(two)

  Foldable[List].find(List(1, 2, 3))(_ > 2) // Some(3)
  Foldable[List].find(List(1, 2, 3))(_ > 5) // None

  Foldable[List].exists(List(1, 2, 3))(_ > 2) // true
  Foldable[List].exists(List(1, 2, 3))(_ > 5) // false

  Foldable[List].forall(List(1, 2, 3))(_ <= 3) // true
  Foldable[List].forall(List(1, 2, 3))(_ < 3)  // false

  Foldable[List].toList(List(1, 2, 3))
  Foldable[Option].toList(Option(42)) // List(42)
  Foldable[Option].toList(None) // List()

  Foldable[List].filter_(List(1, 2, 3))(_ < 3) // List(1,2)
  Foldable[Option].filter_(Option(42))(_ != 42) // List()

  import cats.implicits._

  def parseInt(s: String): Option[Int] =
    Either.catchOnly[NumberFormatException](s.toInt).toOption

  Foldable[List].traverse_(List("1", "2", "3"))(parseInt) // Some(())
  Foldable[List].traverse_(List("a", "b", "c"))(parseInt) // None

  val FoldableListOption = Foldable[List].compose[Option]
  FoldableListOption.fold(List(Option(1), Option(2), Option(3), Option(4))) // 10
  FoldableListOption.fold(List(Option("1"), Option("2"), None, Option("3"))) // None

  Foldable[List].isEmpty(List(1, 2, 3)) //
  Foldable[List].dropWhile_(List(1, 2, 3))(_ < 2) // List(2,3)
  Foldable[List].takeWhile_(List(1, 2, 3))(_ < 2) // List(1)
}