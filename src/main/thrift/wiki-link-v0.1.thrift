namespace java kba.wiki
namespace py kba.wiki

struct PageContentItem {
  // original download, raw byte array
  1: string raw, 
  
  // all visible text, e.g. from boilerpipe 1.2.0 KeepEverything
  2: optional string cleansed, 

  // a correctly parsed and reformatted HTML version of raw with each
  // HTML tag on its own line separate from lines with visible text,
  // made with, e.g., BeautifulSoup(raw).prettify()
  3: optional string dom 
}

struct Mention {

  1: string wiki_url,
  
  2: string anchor_text,
  
  3: i32 raw_text_offset

}

struct WikiLinkItem {
  // id from google release
  1: i32 doc_id, 
  
  // md5
  2: string url_hash,  

  // the original URL string obtain from some source
  3: string url_original, 

  // primary content
  4: PageContentItem content,   
  
  5: optional list<string> rare_words,

  // array of annotation objects for the document
  6: optional list<Mention> mentions
}
