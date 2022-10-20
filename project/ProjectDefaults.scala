import sbt.Keys._
import sbt._

object ProjectDefaults extends AutoPlugin {
  object autoImport {
    lazy val unstash = taskKey[Unit]("Unstash cached compiled files")
    lazy val CompileAndTest = "compile->compile;test->test"
    lazy val TestOnly = "test->test"
  }

  override def trigger = allRequirements

  override def projectSettings: Seq[Def.Setting[_]] = testing ++ other

  override def buildSettings: Seq[Def.Setting[_]] = {
    sys.props += "packaging.type" -> "jar"
    Nil
  }

  private lazy val testing: Seq[Def.Setting[_]] =
    inConfig(IntegrationTest)(Defaults.itSettings) ++ Seq(
      testFrameworks += new TestFramework("weaver.framework.CatsEffect"),
      Test / fork               := true,
      Test / parallelExecution  := true,
      Test / testForkedParallel := true,
    )

  private lazy val other = Seq(
    publishMavenStyle := true,
    isSnapshot        := true,
    version                                := "1.0",
    scalaVersion                           := "2.13.10",
    Compile / doc / sources                := Seq.empty,
    Compile / packageDoc / publishArtifact := false,
  )
}
