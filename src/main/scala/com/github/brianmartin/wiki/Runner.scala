package com.github.brianmartin.wiki

import kba._

import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TIOStreamTransport

import java.io.BufferedOutputStream
import java.io.File
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
    val (outStream, outProto) = ThriftSerializerFactory.getWriter(file)
    
    s.write(outProto)
    
    outStream.flush()
    outStream.close()
    
    val (inStream, inProto) = ThriftSerializerFactory.getReader(file)
    
    val time = StreamTime.decode(inProto)
    println(time.epochTicks)
    println(time.zuluTimestamp)
    
    inStream.close()

  }

}

object ThriftSerializerFactory {
  
  def getWriter(f: File) = {
    val stream = new BufferedOutputStream(new FileOutputStream(f), 2048)
    val protocol= new TBinaryProtocol(new TIOStreamTransport(stream))
    (stream, protocol)
  }
  
  def getReader(f: File) = {
    val stream = new BufferedInputStream(new FileInputStream(f), 2048)
    val protocol = new TBinaryProtocol(new TIOStreamTransport(stream))
    (stream, protocol)
  }
  
}
