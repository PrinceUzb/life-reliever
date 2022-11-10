package lifereliever.services.timetable.repositories

import cats.effect.Concurrent
import cats.effect.kernel.Resource
import cats.implicits.toFlatMapOps
import cats.implicits.toFunctorOps
import skunk.Session

import lifereliever.domain.ID
import lifereliever.effects.Calendar
import lifereliever.effects.GenUUID
import lifereliever.services.timetable.domain.Academy
import lifereliever.services.timetable.domain.AcademyId
import lifereliever.services.timetable.domain.AcademyKind
import lifereliever.services.timetable.domain.AcademyName
import lifereliever.services.timetable.domain.SearchFilters
import lifereliever.support.skunk.syntax.all._

trait AcademyRepository[F[_]] {
  def create(kind: AcademyKind, name: AcademyName): F[Academy]
  def get(filter: SearchFilters): F[List[Academy]]
}

object AcademyRepository {
  def make[F[_]: GenUUID: Calendar: Concurrent](
      implicit
      session: Resource[F, Session[F]]
    ): AcademyRepository[F] = new AcademyRepository[F] {
    import lifereliever.services.timetable.repositories.sql.AcademySql._
    override def create(kind: AcademyKind, name: AcademyName): F[Academy] =
      for {
        id <- ID.make[F, AcademyId]
        now <- Calendar[F].currentDateTime
        academy = Academy(id, name, kind, now)
        _ <- insert.execute(academy)
      } yield academy

    override def get(filter: SearchFilters): F[List[Academy]] = ???
  }
}
