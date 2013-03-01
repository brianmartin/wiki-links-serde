package com.github.brianmartin.wiki

import org.jsoup.nodes.Element
import edu.umass.cs.iesl.wiki.{Context => ContextWrapper, WikiLinkItem, Mention}
import java.util.regex.Pattern
import org.jsoup.Jsoup
import collection.mutable
import collection.mutable.{HashMap, ArrayBuffer}
import org.apache.commons.lang.StringUtils
import io.Source

/**
 * @author harshal
 * @date: 2/27/13
 */
object Context {
  val window_size = 15
  val redirectsMap = loadRedirectsMap("/iesl/canvas/harshal/data/wikipedia/extraction/redirects/redirects_final.tsv")
  val title2id = loadTitleIdMap("/iesl/canvas/harshal/data/wikipedia/wpid-title-mapping-new.csv")
  val wiki2fb = loadWikiToFreebaseMap("/iesl/canvas/harshal/data/freebase/freebase-wex-2012-08-06-freebase_wpid.tsv")

  /**
   * takes a wikilinkitem and returns a seq[Mention] with context and freebaseid filled in
   * @param wli
   * @return
   */

  def addContextAndFreebaseId(wli:WikiLinkItem):WikiLinkItem={
    def newMentionArray = ArrayBuffer[Mention]()

    val _map = mutable.HashMap[String,ArrayBuffer[Mention]]()
    wli.mentions.foreach(mention=>{
      _map(pruneUrl(mention._1)) = (_map.getOrElseUpdate(pruneUrl(mention._1),newMentionArray)+=mention)
    })

    wli.content.dom match {
      case Some(dom) => {
        val mentions = processDom(dom,_map,wli.docId)
        wli.copy(`mentions`= mentions)
      }
      case None => wli
    }
  }

  /**
   * Recursively find a large enough context for the anchor by looking at the parents
   * @param anchor
   * @param parent
   * @return  Context
   */
  @annotation.tailrec
  def getContext(anchor:Element,parent:Element):ContextWrapper = {
    //Split the actual html content for this element by the anchor's actual html
    val part = parent.html().split(Pattern.quote(anchor.toString))
    val left = if (part.length>0) Jsoup.parse(part(0)).text() else ""
    val right = if (part.length>1) Jsoup.parse(part(1)).text() else ""
    if ((left.split("""\s+""").length>=window_size && right.split("""\s+""").length>=window_size) || parent.parent() == null){
      var split = left.split("""\s+""")
      val leftStr = if (split.length<=window_size) left else split.takeRight(window_size).mkString(" ")
      split = right.split("""\s+""")
      val rightStr = if (split.length<=window_size) right else split.takeRight(window_size).mkString(" ")
      return ContextWrapper(leftStr,rightStr,anchor.text())
    }
    else{
      getContext(anchor,parent.parent())
    }
  }

  def processDom(html:String,_map:mutable.HashMap[String,ArrayBuffer[Mention]],id:Int)
  :Seq[Mention] = {
    val Matcher = """.+/(.*)""".r
    val doc = Jsoup.parse(html)
    //Get all the anchor elements from the dom to compare with wikiurl
    val anchors = doc.getElementsByTag("a").iterator()
    val mentions = ArrayBuffer[Mention]()
    while(!_map.isEmpty && anchors.hasNext){
      val anchor = anchors.next()
      val href = anchor.attr("href")
      val prunedHref = pruneUrl(href)
      if(!prunedHref.equals("")){ //Check if its a wikipedia url
        if (_map.contains(prunedHref)){
          val value = _map.get(prunedHref).get
          /**
           * there could be multiple links for the same url in the page
           * this is stored as a list in the corresponding map bucket
           * hence when a url is matched it should be removed list so it's not matched twice
           * when the array is empty remove corresponding map entry
           */
          val mention = value.remove(0)
          if(value.length==0) _map.remove(prunedHref)
          else _map(prunedHref) = value
          //some urls end with "/" which should be removed
          val wikiUrl = StringUtils.stripEnd(mention.wikiUrl,"/")
          //extract wiki page title from the url
          val title = wikiUrl match {
            case Matcher(title) => title
            case _ => {
              """(?<=http://en.wikipedia.org/wiki/)(.*)(?!/)""".r.findFirstIn(wikiUrl).get
            }
          }
          //get the wikipedia id for the title
          val idOp = title2id.get(StringUtils.stripEnd(title.toLowerCase,"_"))
          //generate a mention with context and fb id filed in
          mentions+= Mention(mention.wikiUrl, mention.anchorText,mention.rawTextOffset,
            Some(getContext(anchor,anchor.parent())),
            idOp match {
              case Some(id) => wiki2fb.get(redirectsMap.getOrElse(id,id))
              case None => None
            }
          )
        }
      }
    }
    //add the mentions not found in page back with empty context and freebase id
    mentions++=_map.values.flatten
  }

  // pruning is necessary because sites might garbled urls like: <a href="//en.wikipedia...."> (that is, no "http:")
  def pruneUrl(url: String): String = {
    val index = url.indexOf("wikipedia.org")
    if(index != -1) url.substring(index) else ""
  }

  def loadWikiToFreebaseMap(wikiToFreeBaseFile:String) : HashMap[String,String] ={
    println("Loading from "+wikiToFreeBaseFile+"...")
    val map = HashMap[String,String]()
    for(line<-Source.fromFile(wikiToFreeBaseFile).getLines()){
      val split = line.split("\t")
      if(split.size==2) map(split(1))=split(0)
    }
    map
  }
  def loadTitleIdMap(titleIdFile:String) : HashMap[String,String]={
    println("Loading from "+titleIdFile+"...")
    val map = HashMap[String,String]()
    for(line<-Source.fromFile(titleIdFile,"UTF-8").getLines()){
      val split = line.split("\t")
      if(split.size ==2) map(split(0).toLowerCase) = split(1)
    }
    map
  }
  def loadRedirectsMap(file:String) : HashMap[String,String] = {
    println("Loading from "+file+"...")
    val map = HashMap[String,String]()
    for(line<-Source.fromFile(file).getLines()){
      val split = line.split("\t")
      if(split.size ==2) map(split(0)) = split(1)
    }
    map
  }
}
