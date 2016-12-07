import sbt._
import sbt.Keys._
import sbt.{Project, file}

object Build {
  def defmod(id: String, base: Option[String] = None) = Project(id, file(base.getOrElse(id)))
    .settings(
      scalaVersion := "2.11.8"
      , organization := "pro.ulan.rx"
      , scalaSource in Compile := baseDirectory.value / "src"
      , scalaSource in Test := baseDirectory.value / "test"
    )
}