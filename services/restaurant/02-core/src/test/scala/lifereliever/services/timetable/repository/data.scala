package lifereliever.services.timetable.repository

import cats.effect.IO
import cats.effect.Resource
import cats.implicits.toFoldableOps
import org.scalacheck.Gen
import skunk.Session

import lifereliever.services.timetable.domain.Academy
import lifereliever.services.timetable.domain.TimetableGen
import lifereliever.services.timetable.repositories.sql.AcademySql
import lifereliever.support.skunk.syntax.all.skunkSyntaxCommandOps

object data extends TimetableGen {
  implicit private def gen2instance[T](gen: Gen[T]): T = gen.sample.get
  def setup(implicit session: Resource[IO, Session[IO]]): IO[Unit] =
    setupAcademies

  object academies {
    val academy1: Academy = academyGen.get
    val values: List[Academy] = List(academy1)
  }

  private def setupAcademies(implicit session: Resource[IO, Session[IO]]): IO[Unit] =
    academies.values.traverse_ { academy =>
      AcademySql.insert.execute(academy)
    }
}
