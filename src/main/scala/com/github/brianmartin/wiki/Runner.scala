package com.github.brianmartin.wiki

import kba.wiki._
import java.nio.ByteBuffer
import io.Source
import java.nio.charset.Charset
import java.io.File
import scala.collection.mutable.ListBuffer
import java.io.BufferedReader
import java.io.FileReader
import java.io.FileInputStream
import java.nio.channels.FileChannel

object Runner {
  
  def main(args: Array[String]): Unit = {
    
    val pagesChunkDir = new File(args(0)) 
    val googleDir = new File(args(1))
    val thriftDir = new File(args(2))
    
    if (!googleDir.isDirectory() ||
        !thriftDir.isDirectory())
      println("Usage: ./run pages-chunk-dir google-dir thrift-dir")
    
    // for all the files in each chunk that don't have an extension:
    for ((pageFile,i) <- pagesChunkDir.listFiles().filter(f => !f.getName().contains(".")).map { f => f -> f.getName.toInt } ) {
      try {
        val googleFile = new File(googleDir.getAbsolutePath() + ("/%09d" format i))
        val thriftFile = new File(thriftDir.getAbsolutePath() + ("/%09d.thrift" format i))
	    thriftFile.createNewFile()
        serialize(i, pageFile, googleFile, thriftFile)
      } catch {
        case e => {
          println("skipping " + i)
          println(e.getStackTraceString)
        }
      }
	}

  }
  
  
  def readFile(f: File): String = {
    val stream = new FileInputStream(f)
    try {
      val fc = stream.getChannel();
      val bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
      return Charset.defaultCharset().decode(bb).toString()
    }
    finally {
      stream.close()
    } 
  }

  def serialize(id: Int, pageFile: File, googleFile: File, thriftFile: File): Unit = {
    
    val rawHTML = readFile(pageFile)
    
	serialize(
	    id = 0,
	    rawHTML = rawHTML,
	    outFile = thriftFile,
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

  }
  
  val URLMatch = "URL\t(.*)".r
  val MentionMatch = "MENTION\t([^\t]*)\t([0-9]*)\t(.*)".r
  val TokenMatch = "TOKEN\t([^\t]*)\t([0-9]*)".r
  
  def googleAnnotations(googleFile: File) = {
    
    var url: String = null
    var mentions = ListBuffer[Mention]()
    var rareWords = ListBuffer[RareWord]()
    
    for (line <- readFile(googleFile).split("\n")) {
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
        case _ => ()
      }
    }
    
    (url, mentions, rareWords)
  }

}
