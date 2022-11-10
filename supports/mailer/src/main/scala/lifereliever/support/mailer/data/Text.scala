package lifereliever.support.mailer.data

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

import eu.timepit.refined.types.string.NonEmptyString

import lifereliever.support.mailer.data.types.Subtype
import lifereliever.support.mailer.data.types.Subtype.PLAIN

case class Text(
    value: NonEmptyString,
    charset: Charset = StandardCharsets.UTF_8,
    subtype: Subtype = PLAIN,
    headers: List[Header] = Nil,
  )
