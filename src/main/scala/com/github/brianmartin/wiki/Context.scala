package com.github.brianmartin.wiki

import org.jsoup.nodes.Element
import edu.umass.cs.iesl.wiki.{Mention, Context}
import java.util.regex.Pattern
import org.jsoup.Jsoup
import collection.mutable
import collection.mutable.ArrayBuffer
import org.apache.commons.lang.StringUtils

/**
 * @author harshal
 * @date: 2/27/13
 */
object Context {
  val window_size = 15
  /**
   * Recursively find a large enough context for the anchor by looking at the parents
   * @param anchor
   * @param parent
   * @return  Context
   */
  @annotation.tailrec
  def getContext(anchor:Element,parent:Element):Context = {
    //Split the actual html content for this element by the anchor's actual html
    val part = parent.html().split(Pattern.quote(anchor.toString))
    val left = if (part.length>0) Jsoup.parse(part(0)).text() else ""
    val right = if (part.length>1) Jsoup.parse(part(1)).text() else ""
    if ((left.split("""\s+""").length>=window_size && right.split("""\s+""").length>=window_size) || parent.parent() == null){
      var split = left.split("""\s+""")
      val leftStr = if (split.length<=window_size) left else split.takeRight(window_size).mkString(" ")
      split = right.split("""\s+""")
      val rightStr = if (split.length<=window_size) right else split.takeRight(window_size).mkString(" ")
      return Context(leftStr,rightStr,anchor.text())
    }
    else{
      getContext(anchor,parent.parent())
    }
  }

  def processDom(html:String,_map:mutable.HashMap[String,ArrayBuffer[Mention]],id:Int)
  :(Seq[Mention],Int) = {
    val Matcher = """.+/(.*)""".r
    val doc = Jsoup.parse(html)
    val anchors = doc.getElementsByTag("a").iterator()
    val mentions = ArrayBuffer[Mention]()
    while(!_map.isEmpty && anchors.hasNext){
      val anchor = anchors.next()
      val href = anchor.attr("href")
      val prunedHref = pruneUrl(href)
      if(!prunedHref.equals("")){ //Check if its a wikipedia url
        if (_map.contains(prunedHref)){
          val value = _map.get(prunedHref).get
          val mention = value.remove(0)
          if(value.length==0) _map.remove(prunedHref)
          else _map(prunedHref) = value
          val wikiUrl = StringUtils.stripEnd(mention.wikiUrl,"/")
          val title = wikiUrl match {
            case Matcher(title) => title
            case _ => {
              """(?<=http://en.wikipedia.org/wiki/)(.*)(?!/)""".r.findFirstIn(wikiUrl).get
            }
          }
          val idOp = title2id.get(StringUtils.stripEnd(title.toLowerCase,"_"))
          mentions+= wiki.Mention(mention.wikiUrl, mention.anchorText,mention.rawTextOffset,
            Some(getContext(anchor,anchor.parent())),
            idOp match {
              case Some(id) => wiki2fb.get(id)
              case None => None
            })
        }
      }
    }

    val status = if (mentions.size==0) 1 else 0

    mentions++=_map.values.flatten

    (mentions,status)
  }

  // pruning is necessary because sites might garbled urls like: <a href="//en.wikipedia...."> (that is, no "http:")
  def pruneUrl(url: String): String = {
    val index = url.indexOf("wikipedia.org");
    if(index != -1) url.substring(index) else ""
  }

}
