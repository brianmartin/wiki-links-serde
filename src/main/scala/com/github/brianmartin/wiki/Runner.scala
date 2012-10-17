package com.github.brianmartin.wiki

import kba.wiki._

import java.io.File
import java.nio.ByteBuffer
import java.nio.charset.CharsetDecoder
import java.nio.charset.Charset

import scala.io.Source

object implicits {
  implicit def string2bytebuffer(str: String): ByteBuffer = ByteBuffer.wrap(str.getBytes())
  implicit def bytes2bytebuffer(bytes: Array[Byte]): ByteBuffer = ByteBuffer.wrap(bytes)
}

object Runner {
  
  import implicits._
  
  def main(args: Array[String]): Unit = {
    
    val htmlFile = new File("/Users/brian/wrk-umass/wiki-links-thrift/wiki-link-chunk-000000/pages/000000/377")
    val htmlStr  = Source.fromFile(htmlFile).getLines().mkString("\n")
    
    println(CleanseHTML(htmlStr))

  }

}
