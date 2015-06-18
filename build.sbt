name := "Clean-csv"

version := "0.1.1"

organization := "com.cbopt"

scalaVersion := "2.11.1"

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomExtra := (
  <scm>
    <url>git@github.com:chetanbhasin/Clean-CSV.git</url>
    <connection>scm:git:git@github.com:chetanbhasin/Clean-CSV.git</connection>
  </scm>
    <developers>
      <developer>
        <id>chetanbhasin</id>
        <name>Chetan Bhasin</name>
        <url>http://chetanbhasin.com</url>
      </developer>
    </developers>)

licenses := Seq("MIT License" -> url("http://opensource.org/licenses/MIT"))

homepage := Some(url("http://github.com/chetanbhasin/Clean-CSV"))

credentials += Credentials("Sonatype Nexus Repository Manager",
  "oss.sonatype.org",
  "chetanbhasin",
  "KNy-PHf-aJ7-688")
