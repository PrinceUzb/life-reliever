package lifereliever.services.restaurant.domain

import org.scalacheck.Gen

import lifereliever.syntax.refined.commonSyntaxAutoRefineV

trait TypeGen { _: TimetableGen =>
  val academyIdGen: Gen[AcademyId] = idGen(AcademyId.apply)

  val academyNameGen: Gen[AcademyName] = nonEmptyString.map(AcademyName(_))
  val academyKindGen: Gen[AcademyKind] = Gen.oneOf(AcademyKind.values)
}
