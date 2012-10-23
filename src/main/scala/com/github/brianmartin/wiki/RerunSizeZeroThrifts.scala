package com.github.brianmartin.wiki

import java.io.File

object RerunSizeZeroThrifts {

  import Runner._

  def main(args: Array[String]): Unit = {

    val idsToRerun: Seq[Int] = readFileString(new File(args(0))).split("\n").map(_.toInt)
    val pagesDir = new File(args(2))
    val googleDir = new File(args(3))
    val thriftDir = new File(args(4))

    if (!googleDir.isDirectory() ||
        !thriftDir.isDirectory())
      println("Usage: ./run pages-chunk-dir google-dir thrift-dir")

    for (id <- idsToRerun) {
      try {
        val pageFile = new File(pagesDir.getAbsolutePath() + "/" + id)
        println(id + "\t" + pageFile.getAbsolutePath())
        val googleFile = new File(googleDir.getAbsolutePath() + ("/%09d" format id))
        val thriftFile = new File(thriftDir.getAbsolutePath() + ("/%09d.thrift" format id))
        thriftFile.delete()
        thriftFile.createNewFile()
        serialize(id, pageFile, googleFile, thriftFile)
      } catch {
        case e => {
          println("skipping " + id)
          println(e.getCause())
          println(e.getMessage())
          println(e.getStackTraceString)
        }
      }
    }

  }
}
