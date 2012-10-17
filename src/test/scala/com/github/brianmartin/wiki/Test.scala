package com.github.brianmartin.wiki

import com.github.brianmartin.wiki._
import com.github.brianmartin.wiki.implicits._
import org.scalatest.FlatSpec
import kba.wiki._
import java.io.File
import java.nio.charset.Charset
import java.io.PrintWriter

class ThriftSpec extends FlatSpec {
  
  val tmpFile = File.createTempFile("tmp", ".out")
  
  "A WikiLinkItem" should "serialize" in {
	 val s = WikiLinkItem(
	    docId = 1,
	    urlHash = "hash",
	    urlOriginal = "http:/:w" +
	    		"/somewhere.com/page.html",
	    content = PageContentItem(
	        raw = "raw html"
	    )
	 )

     val (outStream, outProto) = ThriftSerializerFactory.getWriter(tmpFile)
    
     s.write(outProto)
    
     outStream.flush()
     outStream.close()
  }
  
  it should "deserialize" in {
    
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


class CleanDOMSpec extends FlatSpec {
  
  "The DOM cleanser" should "add missing tags" in {
	val tmpFile = File.createTempFile("tmp", ".out")
	val pw = new PrintWriter(tmpFile)
	pw.println("""
	    <html>
	      <head>
	      </head>
	      <body>
	        <p>
	        </p>
	        </p>
    """)
    
    pw.close()
    
    val cleanDom = CleanDOM(tmpFile.getAbsolutePath())
    assert(cleanDom == 
"""<html>
  <head></head>
  <body>
    <p> </p>
  </body>
</html>""")
    
  }
}