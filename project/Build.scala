import sbt._
import sbt.Keys._
import com.twitter.sbt._

object Thrifting1Build extends Build {
  val finagleVersion = "5.0.0"

  lazy val thrifting1Build = Project(
    id = "wiki-link-thrifting1",
    base = file("."),
    settings = Project.defaultSettings ++ 
      StandardProject.newSettings ++
      CompileThriftScrooge.newSettings ++ 
      Seq(
        name := "Thrifting stage-1 for Wiki Link",
        organization := "com.github.brianmartin",
        version := "0.1-SNAPSHOT",
        scalaVersion := "2.9.2",
        resolvers += "twitter-repo" at "http://maven.twttr.com",
        libraryDependencies ++= Seq(
          "org.apache.thrift" % "libthrift" % "0.8.0",
          "org.slf4j" % "slf4j-api" % "1.6.6",
          "com.twitter" % "finagle-core" % finagleVersion,
          "com.twitter" % "finagle-thrift" % finagleVersion,
          "org.jboss.netty" % "netty" % "3.2.6.Final",
          "com.twitter" %% "scrooge-runtime" % "3.0.1"
        ),
        publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))
      )
  )
}

