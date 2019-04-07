name := "differential"

version := "0.1"

scalaVersion := "2.12.8"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",

  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
)

scalacOptions += "-Ypartial-unification"
