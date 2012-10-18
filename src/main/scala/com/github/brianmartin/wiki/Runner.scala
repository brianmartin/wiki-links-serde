package com.github.brianmartin.wiki

import kba.wiki._
import java.nio.ByteBuffer
import io.Source
import java.nio.charset.Charset
import java.io.File
import scala.collection.mutable.ListBuffer

object Runner {
  
  def main(args: Array[String]): Unit = {
    
    // can be the pages/ directory or a single file for testing
    val argFile = new File(args(0)) 
    val googleDir = new File(args(1))
    
    if (argFile.isDirectory()) {
      // for all the files in each chunk that don't have an extension:
      for (d <- argFile.listFiles().filter(_.isDirectory())) {
	    for ((pageFile,i) <- d.listFiles().filter(f => !f.getName().contains(".")).map { f => f -> f.getName.toInt } ) {
	      val googleFile = new File(googleDir.getAbsolutePath() + ("/%09d" format i))
	      serialize(i, pageFile, googleFile)
		}
      }
    }
    else {
      val id = argFile.getName().toInt
      serialize(id, argFile, googleFile = new File(googleDir.getAbsolutePath() + ("%09d" format id)))
    } 
    
  }
  
  def serialize(id: Int, pageFile: File, googleFile: File): Unit = {
    
    val outFile = new File(pageFile.getAbsolutePath() + ".thrift")
    outFile.createNewFile()
    
	serialize(
	    id = 0,
	    rawHTML = Source.fromFile(pageFile).getLines().mkString("\n"),
	    outFile = outFile,
	    googleAnnotations = googleAnnotations(googleFile)
	  )
  }
  
  def serialize(id: Int, rawHTML: String, outFile: File, googleAnnotations: (String, Seq[Mention], Seq[RareWord])): Unit = {
    
	val s = WikiLinkItem(
	   docId = id,
	   url = googleAnnotations._1,
	   content = PageContentItem(
	       raw = rawHTML,
	       fullText = FullText(rawHTML),
	       articleText = ArticleText(rawHTML),
	       dom = CleanDOM(rawHTML)
	   ),
	   rareWords = googleAnnotations._3,
	   mentions = googleAnnotations._2
	)
	
    val (outStream, outProto) = ThriftSerializerFactory.getWriter(outFile)
    
    s.write(outProto)
    
    outStream.flush()
    outStream.close()
    
    ///////////////////////////////////////////
    
    //val (inStream, inProto) = ThriftSerializerFactory.getReader(outFile)
    
    //val wli = WikiLinkItem.decode(inProto)
    
    //println(wli.docId)
    //println(wli.content.articleText)
    
    //inStream.close()

  }
  
  val URLMatch = "URL\t(.*)".r
  val MentionMatch = "MENTION\t([^\t]*)\t([0-9]*)\t(.*)".r
  val TokenMatch = "TOKEN\t([^\t]*)\t([0-9]*)".r
  
  def googleAnnotations(googleFile: File) = {
    
    var url: String = null
    var mentions = ListBuffer[Mention]()
    var rareWords = ListBuffer[RareWord]()
    
    for (line <- Source.fromFile(googleFile).getLines()) {
      line match {
        case URLMatch(_url) => {
          //println("URL: |" + url)
          url = _url
        }
        case MentionMatch(text, offset, url) =>  {
          //println("Mention : " + text + " | " + offset + " | " + url)
		  mentions += Mention(wikiUrl = url, anchorText = text, rawTextOffset = offset.toInt)
        }
        case TokenMatch(word, offset) =>  {
          //println("Token: " + word + " | " + offset)
          rareWords += RareWord(word, offset.toInt)
          
        }
      }
    }
    
    (url, mentions, rareWords)
  }

}
