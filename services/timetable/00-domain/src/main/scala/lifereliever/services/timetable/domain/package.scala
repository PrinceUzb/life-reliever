package lifereliever.services.timetable

import java.util.UUID

import derevo.cats.eqv
import derevo.cats.show
import derevo.circe.magnolia._
import derevo.derive
import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype

import lifereliever.utils.uuid

package object domain {
  @derive(decoder, encoder, eqv, show, uuid)
  @newtype case class AcademyKindId(value: UUID)

  @derive(decoder, encoder, eqv)
  @newtype case class AcademyKindName(value: NonEmptyString)

  @derive(decoder, encoder, eqv)
  @newtype case class AcademyName(value: NonEmptyString)
}
