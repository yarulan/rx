import Build._
import Deps._

val core = defmod("core")
  .settings(libraryDependencies ++= Seq(scalatest))
  .settings(scalacOptions += "-Xexperimental")

val swing = defmod("swing")
  .dependsOn(core)
  .settings(scalacOptions += "-Xexperimental")

val root = defmod("root", Some(".")).aggregate(core, swing)