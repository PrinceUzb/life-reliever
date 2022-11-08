package lifereliever.test.generators

import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.UUID

import com.comcast.ip4s.IpAddress
import org.scalacheck.Gen

import lifereliever.refinements.EmailAddress
import lifereliever.refinements.Phone
import lifereliever.syntax.all.zonedDateTimeOps
import lifereliever.syntax.refined.commonSyntaxAutoRefineV

trait CommonGenerators {
  def nonEmptyStringGen(min: Int = 3, max: Int = 100): Gen[String] =
    Gen
      .chooseNum(min, max)
      .flatMap { n =>
        Gen.buildableOfN[String, Char](n, Gen.alphaChar)
      }

  def numberGen(length: Int): Gen[String] = Gen.buildableOfN[String, Char](length, Gen.numChar)

  val boolean: Gen[Boolean] = Gen.oneOf(true, false)

  val nonEmptyAlpha: Gen[String] = Gen.listOfN(10, Gen.alphaChar).map(_.mkString)

  val nonEmptyString: Gen[String] = Gen.listOfN(10, Gen.alphaNumChar).map(_.mkString)

  lazy val zonedDateTimeNowGen: Gen[ZonedDateTime] =
    Gen.delay(Gen.const(ZonedDateTime.now().noNanos))

  def idGen[A](f: UUID => A): Gen[A] =
    Gen.uuid.map(f)

  lazy val dateGen: Gen[LocalDate] =
    for {
      year <- Gen.choose(1800, 2100)
      month <- Gen.choose(1, 12)
      day <- Gen.choose(1, 28)
    } yield LocalDate.of(year, month, day)

  lazy val localDateTimeGen: Gen[LocalDateTime] =
    for {
      year <- Gen.choose(1800, 2100)
      month <- Gen.choose(1, 12)
      day <- Gen.choose(1, 28)
      hour <- Gen.choose(0, 23)
      minute <- Gen.choose(0, 59)
    } yield LocalDateTime.of(year, month, day, hour, minute)

  lazy val emailGen: Gen[EmailAddress] =
    nonEmptyStringGen(4, 8).map(s => s"$s@mail.com": EmailAddress)

  lazy val urlGen: Gen[URL] =
    Gen.alphaLowerStr.map(domain => new URL(s"http://$domain.com"))

  val phoneGen: Gen[Phone] = numberGen(12).map("+" + _)
}
