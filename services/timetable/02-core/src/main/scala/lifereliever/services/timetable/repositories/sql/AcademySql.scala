package lifereliever.services.timetable.repositories.sql

import skunk._
import skunk.codec.all.bool
import skunk.codec.all.timestamp
import skunk.implicits._

import lifereliever.services.timetable.domain.Academy
import lifereliever.services.timetable.domain.AcademyId
import lifereliever.support.skunk.codecs.identification

object AcademySql {
  val academyId: Codec[AcademyId] = identification[AcademyId]

  private val Columns = academyId ~ academyName ~ academyKind ~ timestamp ~ bool
  val codec: Codec[Academy] =
    Columns.imap {
      case id ~ name ~ kind ~ createdAt ~ _ =>
        Academy(id, name, kind, createdAt)
    }(a => a.id ~ a.name ~ a.kind ~ a.createAt ~ false)

  val insert: Command[Academy] =
    sql"""INSERT INTO timetable.academy VALUES ($codec)""".command
}
