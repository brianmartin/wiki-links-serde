package com.github.brianmartin.wiki

import com.github.brianmartin.wiki._
import com.github.brianmartin.wiki.implicits._
import org.scalatest.FlatSpec
import kba.wiki._
import java.io.File
import java.nio.charset.Charset

class ThriftSpec extends FlatSpec {
  
  val tmpFile = File.createTempFile("tmp", ".out")
  
  "A WikiLinkItem" should "be serializable" in {
	 val s = WikiLinkItem(
	    docId = 1,
	    urlHash = "hash",
	    urlOriginal = "http://somewhere.com/page.html",
	    content = PageContentItem(
	        raw = "raw html",
	        encoding = "UTF-8"
	    )
	 )

     val (outStream, outProto) = ThriftSerializerFactory.getWriter(tmpFile)
    
     s.write(outProto)
    
     outStream.flush()
     outStream.close()
  }
  
  "A WikiLinkItem" should "be deserializable" in {
    
    val charset = Charset.forName("UTF-8");
	val cd = charset.newDecoder()
    
    val (inStream, inProto) = ThriftSerializerFactory.getReader(tmpFile)
    
    val wli = WikiLinkItem.decode(inProto)
    println(wli.docId)
	
    cd.reset()
    println(cd.decode(wli.content.raw))
    
    inStream.close()
  }

}