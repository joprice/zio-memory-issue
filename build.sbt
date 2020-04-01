name := "zio-memory-bug"

scalaVersion := "2.12.9"

fork in run := true

javaOptions += "-Xmx200M"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "dev.zio"       %% "zio-interop-cats" % "2.0.0.0-RC12",
  "dev.zio"       %% "zio"              % "1.0.0-RC18-2",
  "org.http4s"                          %% "http4s-dsl"                         % "0.21.2",
  "org.http4s"                          %% "http4s-blaze-client"                % "0.21.2",
  "org.http4s"                          %% "http4s-circe"                       % "0.21.2",
)
