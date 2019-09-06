object either extends  App {
  //val throwsSomeStuff: Int => Double = ???
  //
  //val throwsOtherThings: Double => String = ???
  //
  //val moreThrowing: String => List[Char] = ???
  //
  //val magic = throwsSomeStuff.andThen(throwsOtherThings).andThen(moreThrowing)


  val right: Either[String, Int] = Right(5)
  right.map(_ + 1) // Right(6)

  val left: Either[String, Int] = Left("Something went wrong")
  left.map(_ + 1) // Left("Something went wrong")

  import cats.implicits._
  import cats.Monad

  val right: Either[String, Int] = Either.right(5)
  right.flatMap(x ⇒ Either.right(x + 1)) // Right(6)

  val left: Either[String, Int] = Either.left("Something went wrong")
  left.flatMap(x ⇒ Either.right(x + 1))


  object ExceptionStyle {
    def parse(s: String): Int =
      if (s.matches("-?[0-9]+")) s.toInt
      else throw new NumberFormatException(s"${s} is not a valid integer.")

    def reciprocal(i: Int): Double =
      if (i == 0) throw new IllegalArgumentException("Cannot take reciprocal of 0.")
      else 1.0 / i

    def stringify(d: Double): String = d.toString

  }

  object EitherStyle {
    def parse(s: String): Either[NumberFormatException, Int] =
      if (s.matches("-?[0-9]+")) Either.right(s.toInt)
      else Either.left(new NumberFormatException(s"${s} is not a valid integer."))

    def reciprocal(i: Int): Either[IllegalArgumentException, Double] =
      if (i == 0) Either.left(new IllegalArgumentException("Cannot take reciprocal of 0."))
      else Either.right(1.0 / i)

    def stringify(d: Double): String = d.toString

    def magic(s: String): Either[Exception, String] =
      parse(s).flatMap(reciprocal).map(stringify)
  }

  EitherStyle.parse("Not a number").isRight // false
  EitherStyle.parse("2").isRight // true

  EitherStyle.magic("0").isRight // false
  EitherStyle.magic("1").isRight // true
  EitherStyle.magic("Not a number").isRight // false

  val result = EitherStyle.magic("2") match {
    case Left(_: NumberFormatException) ⇒ "Not a number!"
    case Left(_: IllegalArgumentException) ⇒ "Can't take reciprocal of 0!"
    case Left(_) ⇒ "Unknown error"
    case Right(result) ⇒ s"Got reciprocal: ${result}"
  }
  result

  object EitherStyleWithAdts {
    sealed abstract class Error
    final case class NotANumber(string: String) extends Error
    final case object NoZeroReciprocal extends Error

    def parse(s: String): Either[Error, Int] =
      if (s.matches("-?[0-9]+")) Either.right(s.toInt)
      else Either.left(NotANumber(s))

    def reciprocal(i: Int): Either[Error, Double] =
      if (i == 0) Either.left(NoZeroReciprocal)
      else Either.right(1.0 / i)

    def stringify(d: Double): String = d.toString

    def magic(s: String): Either[Error, String] =
      parse(s).flatMap(reciprocal).map(stringify)
  }

  val result = EitherStyleWithAdts.magic("2") match {
    case Left(EitherStyleWithAdts.NotANumber(_)) ⇒ "Not a number!"
    case Left(EitherStyleWithAdts.NoZeroReciprocal) ⇒ "Can't take reciprocal of 0!"
    case Right(result) ⇒ s"Got reciprocal: ${result}"
  }
  result

  sealed abstract class DatabaseError
  trait DatabaseValue

  object Database {
    def databaseThings(): Either[DatabaseError, DatabaseValue] = ???
  }

  sealed abstract class ServiceError
  trait ServiceValue

  object Service {
    def serviceThings(v: DatabaseValue): Either[ServiceError, ServiceValue] = ???
  }

  def doApp = Database.databaseThings().flatMap(Service.serviceThings)


  sealed abstract class AppError
  final case object DatabaseError1 extends AppError
  final case object DatabaseError2 extends AppError
  final case object ServiceError1 extends AppError
  final case object ServiceError2 extends AppError

  trait DatabaseValue

  object Database {
    def databaseThings(): Either[AppError, DatabaseValue] = ???
  }

  object Service {
    def serviceThings(v: DatabaseValue): Either[AppError, ServiceValue] = ???
  }

  def doApp = Database.databaseThings().flatMap(Service.serviceThings)

  sealed abstract class DatabaseError
  trait DatabaseValue

  object Database {
    def databaseThings(): Either[DatabaseError, DatabaseValue] = ???
  }

  sealed abstract class ServiceError
  trait ServiceValue

  object Service {
    def serviceThings(v: DatabaseValue): Either[ServiceError, ServiceValue] = ???
  }

  sealed abstract class AppError
  object AppError {
    final case class Database(error: DatabaseError) extends AppError
    final case class Service(error: ServiceError) extends AppError
  }

  def doApp: Either[AppError, ServiceValue] =
    Database.databaseThings().leftMap(AppError.Database).
      flatMap(dv => Service.serviceThings(dv).leftMap(AppError.Service))

  def awesome =
    doApp match {
      case Left(AppError.Database(_)) => "something in the database went wrong"
      case Left(AppError.Service(_)) => "something in the service went wrong"
      case Right(_) => "everything is alright!"
    }

  val right: Either[String, Int] = Right(41)
  right.map(_ + 1)

  val left: Either[String, Int] = Left("Hello")
  left.map(_ + 1)
  left.leftMap(_.reverse)

  val either: Either[NumberFormatException, Int] =
    try {
      Either.right("abc".toInt)
    } catch {
      case nfe: NumberFormatException => Either.left(nfe)
    }

  val either: Either[NumberFormatException, Int] =
    Either.catchOnly[NumberFormatException]("abc".toInt)

  Either.catchOnly[NumberFormatException]("abc".toInt).isRight
  Either.catchNonFatal(1 / 0).isLeft

  import cats.implicits._

  val right: Either[String, Int] = 7.asRight[String]

  val left: Either[String, Int] = "hello 🐈s".asLeft[Int]

}