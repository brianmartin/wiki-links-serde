package com.github.brianmartin.wiki

import kba.wiki._
import java.nio.ByteBuffer
import io.Source
import java.nio.charset.Charset
import java.io.File

object implicits {
  
//  val charset = Charset.forName("UTF-8");
//  val cd = charset.newDecoder()
//  val ce = charset.newEncoder()
//    
//  implicit def string2bytebuffer(str: String): ByteBuffer = {
//    ce.encode(ByteBuffer.wrap(str.getBytes()).asCharBuffer)
//  }
//  implicit def stringOpt2bytebufferOpt(strOpt: Option[String]): Option[ByteBuffer] = {
//    strOpt match {
//      case Some(str) => Some(ce.encode(ByteBuffer.wrap(str.getBytes).asCharBuffer))
//      case None => None
//    }
//  }
//  implicit def bytes2bytebuffer(bytes: Array[Byte]): ByteBuffer = ByteBuffer.wrap(bytes)
  
}

object Runner {
  
  import implicits._
  
  def main(args: Array[String]): Unit = {
    
    val argFile = new File(args(0))
    
    if (argFile.isDirectory())
	  for ((f,i) <- argFile.listFiles().zipWithIndex)
	    serialize(i, f)
    else
      serialize(0, argFile)
    
  }
  
  def serialize(id: Int, inFile: File): Unit = {
    
    val outFile = new File(inFile.getAbsolutePath() + ".thrift")
    outFile.createNewFile()
    
	serialize(
	    id = 0,
	    rawHTML = Source.fromFile(inFile).getLines().mkString("\n"),
	    outFile = outFile
	  )
  }
  
  def serialize(id: Int, rawHTML: String, outFile: File): Unit = {
    
	val s = WikiLinkItem(
	   docId = 1,
	   urlHash = "hash",
	   urlOriginal = "http://somewhere.com/page.html",
	   content = PageContentItem(
	       raw = rawHTML,
	       cleansed = ArticleText(rawHTML),
	       dom = CleanDOM(rawHTML)
	   )
	)
	
    val (outStream, outProto) = ThriftSerializerFactory.getWriter(outFile)
    
    s.write(outProto)
    
    outStream.flush()
    outStream.close()
    
    ///////////////////////////////////////////
    
    val (inStream, inProto) = ThriftSerializerFactory.getReader(outFile)
    
    val wli = WikiLinkItem.decode(inProto)
    
    println(wli.docId)
    println(wli.content.cleansed)
    
    inStream.close()

  }

}
