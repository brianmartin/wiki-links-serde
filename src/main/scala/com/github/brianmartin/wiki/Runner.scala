package com.github.brianmartin.wiki

import kba._
import java.io.File
import scala.io.Source

object Runner {

  def main(args: Array[String]): Unit = {

    val s = StreamTime(
        epochTicks = 1,
        zuluTimestamp = "hello"
      )
    
//    val s = StreamItem(
//        docId = "1",
//        absUrl= null,
//        schost = null,
//        originalUrl = null,
//        source = null,
//        title = null,
//        body = null,
//        anchor = null,
//        sourceMetadata = null,
//        streamId = null,
//        streamTime = null,
//        annotation = null
//      )

    
    val file = new File("/Users/brian/wrk-umass/wiki-links-thrift/thrifting1/test.out")
    val (outStream, outProto) = ThriftSerializerFactory.getWriter(file)
    
    s.write(outProto)
    
    outStream.flush()
    outStream.close()
    
    val (inStream, inProto) = ThriftSerializerFactory.getReader(file)
    
    val time = StreamTime.decode(inProto)
    println(time.epochTicks)
    println(time.zuluTimestamp)
    
    inStream.close()
    
    val htmlFile = new File("/Users/brian/wrk-umass/wiki-links-thrift/wiki-link-chunk-000000/pages/000000/377")
    val htmlStr  = Source.fromFile(htmlFile).getLines().mkString("\n")
    
    println(CleanseHTML(htmlStr))

  }

}
