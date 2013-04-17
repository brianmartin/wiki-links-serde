import sbt._
import sbt.Keys._
import com.twitter.sbt._
import com.github.retronym.SbtOneJar.oneJarSettings

object Thrifting1Build extends Build {
  val finagleVersion = "5.0.0"

  lazy val thrifting1Build = Project(
    id = "wiki-link-thrifting1",
    base = file("."),
    settings = Project.defaultSettings ++ 
      StandardProject.newSettings ++
      CompileThriftScrooge.newSettings ++ 
      oneJarSettings ++ 
      Seq(
        name := "Thrifting stage-1 for Wiki Link",
        organization := "com.github.brianmartin",
        version := "0.1-SNAPSHOT",
        scalaVersion := "2.9.2",
        resolvers ++= Seq(
          "twitter-repo" at "http://maven.twttr.com",
          "boilerpipe-m2-repo" at "https://boilerpipe.googlecode.com/svn/repo/"
        ),
        libraryDependencies ++= Seq(
          // Thrift
          "org.apache.thrift" % "libthrift" % "0.8.0",
          "org.slf4j" % "slf4j-api" % "1.6.6",
          "com.twitter" % "finagle-core" % finagleVersion,
          "com.twitter" % "finagle-thrift" % finagleVersion,
          "org.jboss.netty" % "netty" % "3.2.6.Final",
          "com.twitter" %% "scrooge-runtime" % "3.0.1",
          // Boilerpipe
          "xerces" % "xercesImpl" % "2.9.1",
          "net.sourceforge.nekohtml" % "nekohtml" % "1.9.13",
          "de.l3s.boilerpipe" % "boilerpipe" % "1.2.0",
          // Tagsoup
          "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2",
          "org.jsoup" % "jsoup" % "1.7.1",
          // JSON
          "net.liftweb" %% "lift-json" % "2.5-M1",
          // Guava
          "com.google.guava" % "guava" % "13.0",
          // Testing
          "org.scalatest" %% "scalatest" % "1.8" % "test"
        ),
        publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
      )
  )
}

