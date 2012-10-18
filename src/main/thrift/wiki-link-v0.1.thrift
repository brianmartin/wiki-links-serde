namespace java kba.wiki
namespace py kba.wiki

struct PageContentItem {
  // original download, raw byte array
  1: string raw, 
  
  // all visible text, e.g. from boilerpipe 1.2.0 KeepEverything
  2: optional string fullText, 
  
  // all visible text, e.g. from boilerpipe 1.2.0 Article text extractor
  3: optional string articleText, 

  // a correctly parsed and reformatted HTML version of raw with each
  // HTML tag on its own line separate from lines with visible text,
  // made with, e.g., BeautifulSoup(raw).prettify()
  4: optional string dom 
}

struct Mention {

  1: string wiki_url,
  
  2: string anchor_text,
  
  3: i32 raw_text_offset

}

struct RareWord {

  1: string word
  
  2: i32 offset
  
}

struct WikiLinkItem {
  // id from google release
  1: i32 doc_id, 
  
  // the original URL string obtain from some source
  2: string url, 

  // primary content
  3: PageContentItem content,   
  
  // rare words from google data
  4: list<RareWord> rare_words,

  // array of annotation objects for the document
  5: list<Mention> mentions
}
