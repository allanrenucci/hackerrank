name := "hackerrank"

scalaVersion := "2.12.1"

scalacOptions ++= Seq(
  "-Xlint",
  "-feature",
  "-deprecation",
  "-unchecked"
)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")