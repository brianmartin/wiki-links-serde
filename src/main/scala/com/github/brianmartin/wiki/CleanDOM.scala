package com.github.brianmartin.wiki
import scala.xml.PrettyPrinter
import scala.xml.XML
import scala.io.Source
import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl

object CleanDOM {
  
  def apply(filename: String): String = {
    
    // instantiate the parser
    val parser = XML.withSAXParser((new SAXFactoryImpl).newSAXParser())
    val pp = new PrettyPrinter(300, 2)
    
    // parse the file
    val htmlReader = Source.fromFile(filename)("UTF-8").bufferedReader()
    val html = parser.load(htmlReader)
    
    // pretty-print the DOM
    val sb = new StringBuilder
    pp.format(html, sb)
    sb.toString()
  }

}