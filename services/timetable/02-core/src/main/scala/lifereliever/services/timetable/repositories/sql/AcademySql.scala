package lifereliever.services.timetable.repositories.sql

import skunk._
import skunk.codec.all.bool
import skunk.codec.all.timestamp
import skunk.implicits._

import lifereliever.services.timetable.domain.Academy
import lifereliever.services.timetable.domain.AcademyId
import lifereliever.services.timetable.domain.SearchFilters
import lifereliever.support.skunk.codecs.identification
import lifereliever.support.skunk.syntax.all.skunkSyntaxFragmentOps

object AcademySql {
  val academyId: Codec[AcademyId] = identification[AcademyId]

  private val Columns = academyId ~ academyName ~ academyKind ~ timestamp ~ bool
  val codec: Codec[Academy] =
    Columns.imap {
      case id ~ name ~ kind ~ createdAt ~ _ =>
        Academy(id, name, kind, createdAt)
    }(a => a.id ~ a.name ~ a.kind ~ a.createAt ~ false)

  private def searchFilter(filters: SearchFilters): List[Option[AppliedFragment]] =
    List(
      filters.startDate.map(sql"created_at >= $timestamp"),
      filters.endDate.map(sql"created_at <= $timestamp"),
    )

  val insert: Command[Academy] =
    sql"""INSERT INTO timetable.academy VALUES ($codec)""".command

  private def searchQuery(total: Boolean, filters: SearchFilters): AppliedFragment = {
    val countOrData = if (total) "COUNT(*)" else "*"
    val baseQuery: Fragment[Void] = sql"""SELECT #$countOrData FROM timetable.academy"""
    baseQuery(Void).whereAndOpt(searchFilter(filters))
  }

  def select(filters: SearchFilters): AppliedFragment = searchQuery(total = false, filters)
  def total(filters: SearchFilters): AppliedFragment = searchQuery(total = true, filters)
}
