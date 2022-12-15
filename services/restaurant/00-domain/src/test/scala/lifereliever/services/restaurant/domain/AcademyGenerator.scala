package lifereliever.services.restaurant.domain

import org.scalacheck.Gen

trait AcademyGenerator { self: TimetableGen =>
  val academyGen: Gen[Academy] =
    for {
      id <- academyIdGen
      name <- academyNameGen
      kind <- academyKindGen
      createdAt <- localDateTimeGen
    } yield Academy(id, name, kind, createdAt)
}
