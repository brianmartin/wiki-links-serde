package com.github.brianmartin.wiki

import edu.umass.cs.iesl.wiki.WikiLinkItem
import java.io.File

object Viewer {
  
  def apply(f: File): String = {
    val (stream, proto) = ThriftSerializerFactory.getReader(f)
    val wli = WikiLinkItem.decode(proto)
    stream.close()
    
    val sb = new StringBuffer
    
    sb.append("docID: " + wli.docId + "\n")
    sb.append("url: " + wli.url + "\n")
    sb.append("mention.head: " + wli.mentions.head + "\n")
    sb.append("rareWords.head: " + wli.rareWords.head + "\n")
    
    val rawLength = wli.content.raw match {
      case Some(raw) => raw.limit()
      case None => -1
    }
    sb.append("raw length: " + rawLength + "\n")
    
    sb.toString
    
  }

}

class WikiLinkItemIterator(f: File) extends Iterator[WikiLinkItem] {
  
  import org.apache.thrift.transport.TTransportException

  var done = false
  val (stream, proto) = ThriftSerializerFactory.getReader(f)
  private var _next: Option[WikiLinkItem] = getNext()

  private def getNext(): Option[WikiLinkItem] = try { 
    Some(WikiLinkItem.decode(proto))
  } catch { case _: TTransportException => { done = true; stream.close(); None }}

  def hasNext(): Boolean = !done && (_next != None || { _next = getNext(); _next != None })

  def next(): WikiLinkItem = if (hasNext()) _next match {
    case Some(wli) => { _next = None; wli }
    case None => { throw new Exception("Next on empty iterator.") }
  } else throw new Exception("Next on empty iterator.")

}