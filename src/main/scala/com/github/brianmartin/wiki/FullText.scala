package com.github.brianmartin.wiki

import de.l3s.boilerpipe.extractors.KeepEverythingExtractor

object FullText {
  
  def apply(html: String): Option[String] = {
    try {
      Some(KeepEverythingExtractor.INSTANCE.getText(html))
    } catch {
      case _ => None
    }
  }

}