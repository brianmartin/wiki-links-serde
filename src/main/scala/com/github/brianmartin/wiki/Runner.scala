package com.github.brianmartin.wiki

import kba._
import org.apache.thrift.transport.TFileTransport
import org.apache.thrift.protocol.TBinaryProtocol
import java.io.BufferedOutputStream
import java.io.File
import org.apache.thrift.transport.TIOStreamTransport
import java.io.FileOutputStream
import java.io.FileInputStream
import java.io.BufferedInputStream

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
    val bufferedOut = new BufferedOutputStream(new FileOutputStream(file), 2048)
    val binaryOut = new TBinaryProtocol(new TIOStreamTransport(bufferedOut))
    
    s.write(binaryOut)
    bufferedOut.flush()
    bufferedOut.close()
    
    val bufferedIn = new BufferedInputStream(new FileInputStream(file), 2048)
    val binaryIn = new TBinaryProtocol(new TIOStreamTransport(bufferedIn))
    
    val sIn = StreamTime.decode(binaryIn)
    println(sIn.epochTicks)
    println(sIn.zuluTimestamp)

  }

}
