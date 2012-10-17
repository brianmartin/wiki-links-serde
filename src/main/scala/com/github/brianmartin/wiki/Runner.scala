package com.github.brianmartin.wiki

import kba.wiki._

import java.nio.ByteBuffer

object implicits {
  implicit def string2bytebuffer(str: String): ByteBuffer = ByteBuffer.wrap(str.getBytes())
  implicit def bytes2bytebuffer(bytes: Array[Byte]): ByteBuffer = ByteBuffer.wrap(bytes)
}

object Runner {
  
  def main(args: Array[String]): Unit = {
    
    val htmlPath = "/Users/brian/wrk-umass/wiki-links-thrift/wiki-link-chunk-000000/pages/000000/377"

  }

}
