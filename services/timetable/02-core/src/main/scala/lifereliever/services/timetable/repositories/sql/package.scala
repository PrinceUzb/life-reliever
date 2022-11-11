package lifereliever.services.timetable.repositories

import skunk.Codec
import skunk.codec.all.varchar

import lifereliever.services.timetable.domain.AcademyKind
import lifereliever.services.timetable.domain.AcademyName
import lifereliever.support.skunk.codecs.nes

package object sql {
  val academyName: Codec[AcademyName] = nes.imap[AcademyName](AcademyName.apply)(_.value)
  val academyKind: Codec[AcademyKind] =
    varchar.eimap[AcademyKind](str =>
      AcademyKind.values.find(_.entryName == str).toRight("type not found ")
    )(_.entryName)
}
