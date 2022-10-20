name         := "services"
scalaVersion := "2.13.10"

lazy val services_example = project.in(file("example"))

aggregateProjects(
  services_example
)
