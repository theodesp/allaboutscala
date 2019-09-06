object traveersable extends App {
  import scala.concurrent.Future

  def parseInt(s: String): Option[Int] = ???

  trait SecurityError
  trait Credentials

  def validateLogin(cred: Credentials): Either[SecurityError, Unit] = ???

  trait Profile
  trait User

  def userInfo(user: User): Future[Profile] = ???

  import cats.Semigroup
  import cats.data.{NonEmptyList, OneAnd, Validated, ValidatedNel}
  import cats.implicits._

  def parseIntEither(s: String): Either[NumberFormatException, Int] =
    Either.catchOnly[NumberFormatException](s.toInt)

  def parseIntValidated(s: String): ValidatedNel[NumberFormatException, Int] =
    Validated.catchOnly[NumberFormatException](s.toInt).toValidatedNel

  import cats.data.Reader

  trait Context
  trait Topic
  trait Result

  type Job[A] = Reader[Context, A]

  def processTopic(topic: Topic): Job[Result] = ???

  //def processTopics(topics: List[Topic]) =
  //  topics.traverse(processTopic)

  List(Option(1), Option(2), Option(3)).traverse(identity) // Some(List(1, 2, 3))
  List(Option(1), None, Option(3)).traverse(identity) // None

  List(Option(1), Option(2), Option(3)).sequence // Some(List(1, 2, 3))
  List(Option(1), None, Option(3)).sequence // None

  List(Option(1), Option(2), Option(3)).sequence // Some(List(1, 2, 3))
  List(Option(1), None, Option(3)).sequence

}