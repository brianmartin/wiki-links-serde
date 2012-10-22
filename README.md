
Viewing serialized files
========================

```
$ ./sbt11
... Downloading deps ...
> console

scala> import com.github.brianmartin.wiki.Viewer
import com.github.brianmartin.wiki.Viewer

scala> Viewer(new
java.io.File("/Users/brian/wrk-umass/wiki-links-thrift/thrift-sample/000000003.thrift"))
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further
details.
res0: String = 
"docID: 3
url:
ftp://Autoidread:read@ftp.rrc.ru/!!!Motorola/Motorola%20MSP%20SCHOOL/Moscow%20Oct%202010/student/091102_MCD_Class.pdf
mention.head:
Mention(http://en.wikipedia.org/wiki/Bootstrapping,bootstrapping,598297)
rareWords.head: RareWord(reducing,518133)
raw length: 2297374
"
```
