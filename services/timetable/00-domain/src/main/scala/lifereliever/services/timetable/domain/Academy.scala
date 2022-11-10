package lifereliever.services.timetable.domain

import java.time.LocalDateTime

import derevo.circe.magnolia.decoder
import derevo.circe.magnolia.encoder
import derevo.derive

@derive(decoder, encoder)
case class Academy(
    id: AcademyId,
    name: AcademyName,
    kind: AcademyKind,
    createAt: LocalDateTime,
  )
