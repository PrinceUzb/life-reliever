package lifereliever.services.timetable.domain

import scala.collection.immutable

import enumeratum._

sealed trait AcademyKind extends EnumEntry

object AcademyKind extends CirceEnum[AcademyKind] with Enum[AcademyKind] {
  case object School extends AcademyKind
  case object Collage extends AcademyKind
  case object University extends AcademyKind

  override def values: immutable.IndexedSeq[AcademyKind] = findValues
}
