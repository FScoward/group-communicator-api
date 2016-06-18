name := """group-communicator-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.h2database"  %  "h2"                           % "1.4.191", // your jdbc driver here
  "org.scalikejdbc" %% "scalikejdbc"                  % "2.4.1",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.4.1",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.1",
//  "mysql"           %  "mysql-connector-java"         % "6.0.2",
  "mysql" % "mysql-connector-java" % "5.1.20",
  "org.flywaydb"    %% "flyway-play"                  % "3.0.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalikejdbcSettings
