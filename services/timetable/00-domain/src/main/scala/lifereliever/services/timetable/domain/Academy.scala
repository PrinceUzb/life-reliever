package lifereliever.services.timetable.domain

import derevo.circe.magnolia.decoder
import derevo.circe.magnolia.encoder
import derevo.derive

@derive(decoder, encoder)
case class Academy(
    id: AcademyKindId,
    name: AcademyName,
  )
