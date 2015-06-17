name := "Clean-csv"

version := "0.1.1"

scalaVersion := "2.11.1"

publishMavenStyle := true

publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
          Some("snapshots" at nexus + "content/repositories/snapshots")
      else
          Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false
