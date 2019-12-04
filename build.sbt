name := "zio-memory-bug"

scalaVersion := "2.12.9"

fork in run := true

javaOptions += "-Xmx200M"

libraryDependencies ++= Seq(
  "dev.zio"       %% "zio-interop-cats" % "2.0.0.0-RC10",
  "dev.zio"       %% "zio"              % "1.0.0-RC17",
  "org.http4s"                          %% "http4s-dsl"                         % "0.21.0-M5",
  "org.http4s"                          %% "http4s-blaze-client"                % "0.21.0-M5",
  "org.http4s"                          %% "http4s-circe"                       % "0.21.0-M5",
)

