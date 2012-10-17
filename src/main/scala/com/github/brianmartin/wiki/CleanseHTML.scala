package com.github.brianmartin.wiki
import de.l3s.boilerpipe.extractors.ArticleExtractor

object CleanseHTML {
  
  def apply(html: String): String = {
    ArticleExtractor.INSTANCE.getText(html)
  }

}