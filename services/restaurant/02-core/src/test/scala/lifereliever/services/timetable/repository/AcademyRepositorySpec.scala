package lifereliever.services.timetable.repository

import cats.effect.IO
import cats.effect.Resource
import cats.implicits.toTraverseOps
import skunk.Session
import weaver.Expectations

import lifereliever.services.timetable.domain.SearchFilters
import lifereliever.services.timetable.repositories.AcademyRepository
import lifereliever.support.database.{DBSuite, TestCase }

object AcademyRepositorySpec extends DBSuite {
  override def schemaName: String = "timetable"
  override def beforeAll(implicit session: Res): IO[Unit] = data.setup

  test("Get academies") { implicit res =>
    val repo = AcademyRepository.make[F]
    case object Case1 extends TestCase[Res] {
      override def check(implicit dao: Resource[IO, Session[IO]]): IO[Expectations] =
        repo
          .get(SearchFilters.Empty)
          .map { academies =>
            assert.same(1, academies.length)
          }
    }
    List(
      Case1
    ).traverse(_.check).map(_.reduce(_ and _))
  }
}
