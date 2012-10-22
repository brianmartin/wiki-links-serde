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
    sb.append("raw length: " + wli.content.raw.limit() + "\n")
    
    sb.toString
    
  }

}