package lifereliever.support.mailer.data

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

import eu.timepit.refined.types.string.NonEmptyString
import lifereliever.support.mailer.data.types.Subtype
import types.Subtype.HTML

case class Html(
    value: NonEmptyString,
    charset: Charset = StandardCharsets.UTF_8,
    subtype: Subtype = HTML,
    headers: List[Header] = Nil,
  )
