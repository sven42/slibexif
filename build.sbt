//import com.typesafe.sbt.SbtSite.site
import sbt._
import Keys._

version in ThisBuild := "0.3.2-SNAPSHOT"

val dependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.6" % "test" cross CrossVersion.binary
)

lazy val commonSettings: SettingsDefinition = Seq(
  libraryDependencies ++= dependencies,
  organization := "net.n12n.exif",
  scalaVersion in ThisBuild := "2.13.5",
  scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature"),
  scalacOptions in doc ++= Seq("-diagrams", "-doc-title Scala Exif Library", "-implicits"),
  testOptions ++= Seq(Tests.Argument("-oSDW"))
)

lazy val slibexifSettings = commonSettings ++ Seq(
  name := "slibexif",
  crossScalaVersions := Seq("2.12.1", "2.13.5"),
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  credentials += Credentials(Path.userHome / ".sbt" / "sonatype"),
  pomExtra := <url>https://github.com/ngrossmann/slibexif</url>
    <licenses>
      <license>
        <name>GNU LESSER GENERAL PUBLIC LICENSE, Version 3</name>
        <url>http://www.gnu.org/licenses/lgpl.txt</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>https://github.com/ngrossmann/slibexif</url>
    </scm>
    <developers>
      <developer>
        <id>ngrossmann</id>
        <name>Niklas Grossmann</name>
        <url>https://github.com/ngrossmann</url>
      </developer>
    </developers>
)

lazy val slibexif = (project in file("."))
  .settings(slibexifSettings)
//.settings(site.settings : _*)//.settings(site.includeScaladoc() : _*)

lazy val examples = (project in file("examples"))
  .dependsOn(slibexif)
