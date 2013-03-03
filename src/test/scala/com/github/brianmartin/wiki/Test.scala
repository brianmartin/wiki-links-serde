package com.github.brianmartin.wiki

import com.github.brianmartin.wiki._
import org.scalatest.FlatSpec
import edu.umass.cs.iesl.wiki._
import java.io.File
import java.nio.charset.Charset
import java.nio.ByteBuffer
import java.io.PrintWriter

class ThriftSpec extends FlatSpec {
  
  val tmpFile = File.createTempFile("tmp", ".out")
  
  "A WikiLinkItem" should "serialize" in {
	 val s = WikiLinkItem(
	    docId = 1,
	    url = "http://somewhere.com/page.html",
	    content = PageContentItem(
	        raw = Some(ByteBuffer.wrap("raw html".getBytes))
	    )
	 )

     val (outStream, outProto) = ThriftSerializerFactory.getWriter(tmpFile)
    
     s.write(outProto)
    
     outStream.flush()
     outStream.close()
  }
  
  it should "deserialize" in {
    
    val (inStream, inProto) = ThriftSerializerFactory.getReader(tmpFile)
    
    val wli = WikiLinkItem.decode(inProto)
    
    inStream.close()
    
  }

}


class CleanDOMSpec extends FlatSpec {
  
  "The DOM cleanser" should "add missing tags" in {
	val dirty = """
	    <html>
	      <head>
	      </head>
	      <body>
	        <p>
	        </p>
	        </p>
    """
	  
	val expectedClean = 
"""<html>
  <head></head>
  <body>
    <p> </p>
  </body>
</html>"""
    
    val clean = CleanDOM(dirty).get
    assert(clean == expectedClean)
    
  }
}