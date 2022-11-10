package lifereliever.support.skunk

import java.time.ZonedDateTime

import eu.timepit.refined.types.string.NonEmptyString
import skunk.Codec
import skunk.codec.all.timestamptz
import skunk.codec.all.uuid
import skunk.codec.all.varchar

import lifereliever.EmailAddress
import lifereliever.OnlyDigits
import lifereliever.effects.IsUUID
import lifereliever.syntax.refined.commonSyntaxAutoRefineV

package object codecs {
  def identification[A: IsUUID]: Codec[A] = uuid.imap[A](IsUUID[A].uuid.get)(IsUUID[A].uuid.apply)

  val nes: Codec[NonEmptyString] = varchar.imap[NonEmptyString](identity(_))(_.value)
  val number: Codec[OnlyDigits] = varchar.imap[OnlyDigits](identity(_))(_.value)
  val email: Codec[EmailAddress] = varchar.imap[EmailAddress](identity(_))(_.value)
  val zonedDateTime: Codec[ZonedDateTime] = timestamptz.imap(_.toZonedDateTime)(_.toOffsetDateTime)
}
