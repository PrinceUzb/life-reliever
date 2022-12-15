name         := "services"
scalaVersion := "2.13.10"

lazy val services_auth = project.in(file("auth"))
lazy val services_users = project.in(file("users"))
lazy val services_restaurant = project.in(file("restaurant"))

aggregateProjects(
  services_auth,
  services_users,
  services_restaurant,
)
