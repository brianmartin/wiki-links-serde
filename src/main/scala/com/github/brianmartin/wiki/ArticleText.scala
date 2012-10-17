package com.github.brianmartin.wiki

import de.l3s.boilerpipe.extractors.ArticleExtractor

object ArticleText {
  
  def apply(html: String): Option[String] = {
    try {
	  Some(ArticleExtractor.INSTANCE.getText(html))
    } catch {
      case _ => None
    }
  }
}