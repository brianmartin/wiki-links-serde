package kba

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

object StreamItem extends ThriftStructCodec[StreamItem] {
  val Struct = new TStruct("StreamItem")
  val DocIdField = new TField("docId", TType.STRING, 1)
  val AbsUrlField = new TField("absUrl", TType.STRING, 2)
  val SchostField = new TField("schost", TType.STRING, 3)
  val OriginalUrlField = new TField("originalUrl", TType.STRING, 4)
  val SourceField = new TField("source", TType.STRING, 5)
  val TitleField = new TField("title", TType.STRUCT, 6)
  val BodyField = new TField("body", TType.STRUCT, 7)
  val AnchorField = new TField("anchor", TType.STRUCT, 8)
  val SourceMetadataField = new TField("sourceMetadata", TType.STRING, 9)
  val StreamIdField = new TField("streamId", TType.STRING, 10)
  val StreamTimeField = new TField("streamTime", TType.STRUCT, 11)
  val AnnotationField = new TField("annotation", TType.LIST, 12)

  def encode(_item: StreamItem, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): StreamItem = decode(_iprot)

  def apply(
    `docId`: String,
    `absUrl`: ByteBuffer,
    `schost`: String,
    `originalUrl`: ByteBuffer,
    `source`: String,
    `title`: ContentItem,
    `body`: ContentItem,
    `anchor`: ContentItem,
    `sourceMetadata`: ByteBuffer,
    `streamId`: String,
    `streamTime`: StreamTime,
    `annotation`: Option[Seq[Annotation]] = None
  ): StreamItem = new Immutable(
    `docId`,
    `absUrl`,
    `schost`,
    `originalUrl`,
    `source`,
    `title`,
    `body`,
    `anchor`,
    `sourceMetadata`,
    `streamId`,
    `streamTime`,
    `annotation`
  )

  def unapply(_item: StreamItem): Option[Product12[String, ByteBuffer, String, ByteBuffer, String, ContentItem, ContentItem, ContentItem, ByteBuffer, String, StreamTime, Option[Seq[Annotation]]]] = Some(_item)

  object Immutable extends ThriftStructCodec[StreamItem] {
    def encode(_item: StreamItem, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `docId`: String = null
      var _got_docId = false
      var `absUrl`: ByteBuffer = null
      var _got_absUrl = false
      var `schost`: String = null
      var _got_schost = false
      var `originalUrl`: ByteBuffer = null
      var _got_originalUrl = false
      var `source`: String = null
      var _got_source = false
      var `title`: ContentItem = null
      var _got_title = false
      var `body`: ContentItem = null
      var _got_body = false
      var `anchor`: ContentItem = null
      var _got_anchor = false
      var `sourceMetadata`: ByteBuffer = null
      var _got_sourceMetadata = false
      var `streamId`: String = null
      var _got_streamId = false
      var `streamTime`: StreamTime = null
      var _got_streamTime = false
      var `annotation`: Seq[Annotation] = Seq[Annotation]()
      var _got_annotation = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* docId */
              _field.`type` match {
                case TType.STRING => {
                  `docId` = {
                    _iprot.readString()
                  }
                  _got_docId = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* absUrl */
              _field.`type` match {
                case TType.STRING => {
                  `absUrl` = {
                    _iprot.readBinary()
                  }
                  _got_absUrl = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 3 => { /* schost */
              _field.`type` match {
                case TType.STRING => {
                  `schost` = {
                    _iprot.readString()
                  }
                  _got_schost = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 4 => { /* originalUrl */
              _field.`type` match {
                case TType.STRING => {
                  `originalUrl` = {
                    _iprot.readBinary()
                  }
                  _got_originalUrl = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 5 => { /* source */
              _field.`type` match {
                case TType.STRING => {
                  `source` = {
                    _iprot.readString()
                  }
                  _got_source = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 6 => { /* title */
              _field.`type` match {
                case TType.STRUCT => {
                  `title` = {
                    ContentItem.decode(_iprot)
                  }
                  _got_title = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 7 => { /* body */
              _field.`type` match {
                case TType.STRUCT => {
                  `body` = {
                    ContentItem.decode(_iprot)
                  }
                  _got_body = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 8 => { /* anchor */
              _field.`type` match {
                case TType.STRUCT => {
                  `anchor` = {
                    ContentItem.decode(_iprot)
                  }
                  _got_anchor = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 9 => { /* sourceMetadata */
              _field.`type` match {
                case TType.STRING => {
                  `sourceMetadata` = {
                    _iprot.readBinary()
                  }
                  _got_sourceMetadata = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 10 => { /* streamId */
              _field.`type` match {
                case TType.STRING => {
                  `streamId` = {
                    _iprot.readString()
                  }
                  _got_streamId = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 11 => { /* streamTime */
              _field.`type` match {
                case TType.STRUCT => {
                  `streamTime` = {
                    StreamTime.decode(_iprot)
                  }
                  _got_streamTime = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 12 => { /* annotation */
              _field.`type` match {
                case TType.LIST => {
                  `annotation` = {
                    val _list = _iprot.readListBegin()
                    val _rv = new mutable.ArrayBuffer[Annotation](_list.size)
                    var _i = 0
                    while (_i < _list.size) {
                      _rv += {
                        Annotation.decode(_iprot)
                      }
                      _i += 1
                    }
                    _iprot.readListEnd()
                    _rv
                  }
                  _got_annotation = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case _ => TProtocolUtil.skip(_iprot, _field.`type`)
          }
          _iprot.readFieldEnd()
        }
      }
      _iprot.readStructEnd()
      new Immutable(
        `docId`,
        `absUrl`,
        `schost`,
        `originalUrl`,
        `source`,
        `title`,
        `body`,
        `anchor`,
        `sourceMetadata`,
        `streamId`,
        `streamTime`,
        if (_got_annotation) Some(`annotation`) else None
      )
    }
  }

  /**
   * The default read-only implementation of StreamItem.  You typically should not need to
   * directly reference this class; instead, use the StreamItem.apply method to construct
   * new instances.
   */
  class Immutable(
    val `docId`: String,
    val `absUrl`: ByteBuffer,
    val `schost`: String,
    val `originalUrl`: ByteBuffer,
    val `source`: String,
    val `title`: ContentItem,
    val `body`: ContentItem,
    val `anchor`: ContentItem,
    val `sourceMetadata`: ByteBuffer,
    val `streamId`: String,
    val `streamTime`: StreamTime,
    val `annotation`: Option[Seq[Annotation]] = None
  ) extends StreamItem

  /**
   * This Proxy trait allows you to extend the StreamItem trait with additional state or
   * behavior and implement the read-only methods from StreamItem using an underlying
   * instance.
   */
  trait Proxy extends StreamItem {
    protected def _underlyingStreamItem: StreamItem
    def `docId`: String = _underlyingStreamItem.`docId`
    def `absUrl`: ByteBuffer = _underlyingStreamItem.`absUrl`
    def `schost`: String = _underlyingStreamItem.`schost`
    def `originalUrl`: ByteBuffer = _underlyingStreamItem.`originalUrl`
    def `source`: String = _underlyingStreamItem.`source`
    def `title`: ContentItem = _underlyingStreamItem.`title`
    def `body`: ContentItem = _underlyingStreamItem.`body`
    def `anchor`: ContentItem = _underlyingStreamItem.`anchor`
    def `sourceMetadata`: ByteBuffer = _underlyingStreamItem.`sourceMetadata`
    def `streamId`: String = _underlyingStreamItem.`streamId`
    def `streamTime`: StreamTime = _underlyingStreamItem.`streamTime`
    def `annotation`: Option[Seq[Annotation]] = _underlyingStreamItem.`annotation`
  }
}

trait StreamItem extends ThriftStruct
  with Product12[String, ByteBuffer, String, ByteBuffer, String, ContentItem, ContentItem, ContentItem, ByteBuffer, String, StreamTime, Option[Seq[Annotation]]]
  with java.io.Serializable
{
  import StreamItem._

  def `docId`: String
  def `absUrl`: ByteBuffer
  def `schost`: String
  def `originalUrl`: ByteBuffer
  def `source`: String
  def `title`: ContentItem
  def `body`: ContentItem
  def `anchor`: ContentItem
  def `sourceMetadata`: ByteBuffer
  def `streamId`: String
  def `streamTime`: StreamTime
  def `annotation`: Option[Seq[Annotation]]

  def _1 = `docId`
  def _2 = `absUrl`
  def _3 = `schost`
  def _4 = `originalUrl`
  def _5 = `source`
  def _6 = `title`
  def _7 = `body`
  def _8 = `anchor`
  def _9 = `sourceMetadata`
  def _10 = `streamId`
  def _11 = `streamTime`
  def _12 = `annotation`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `docId_item` = `docId`
      _oprot.writeFieldBegin(DocIdField)
      _oprot.writeString(`docId_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `absUrl_item` = `absUrl`
      _oprot.writeFieldBegin(AbsUrlField)
      _oprot.writeBinary(`absUrl_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `schost_item` = `schost`
      _oprot.writeFieldBegin(SchostField)
      _oprot.writeString(`schost_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `originalUrl_item` = `originalUrl`
      _oprot.writeFieldBegin(OriginalUrlField)
      _oprot.writeBinary(`originalUrl_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `source_item` = `source`
      _oprot.writeFieldBegin(SourceField)
      _oprot.writeString(`source_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `title_item` = `title`
      _oprot.writeFieldBegin(TitleField)
      `title_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `body_item` = `body`
      _oprot.writeFieldBegin(BodyField)
      `body_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `anchor_item` = `anchor`
      _oprot.writeFieldBegin(AnchorField)
      `anchor_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `sourceMetadata_item` = `sourceMetadata`
      _oprot.writeFieldBegin(SourceMetadataField)
      _oprot.writeBinary(`sourceMetadata_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `streamId_item` = `streamId`
      _oprot.writeFieldBegin(StreamIdField)
      _oprot.writeString(`streamId_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `streamTime_item` = `streamTime`
      _oprot.writeFieldBegin(StreamTimeField)
      `streamTime_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    if (`annotation`.isDefined) {
      val `annotation_item` = `annotation`.get
      _oprot.writeFieldBegin(AnnotationField)
      _oprot.writeListBegin(new TList(TType.STRUCT, `annotation_item`.size))
      `annotation_item`.foreach { `_annotation_item_element` =>
        `_annotation_item_element`.write(_oprot)
      }
      _oprot.writeListEnd()
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `docId`: String = this.`docId`,
    `absUrl`: ByteBuffer = this.`absUrl`,
    `schost`: String = this.`schost`,
    `originalUrl`: ByteBuffer = this.`originalUrl`,
    `source`: String = this.`source`,
    `title`: ContentItem = this.`title`,
    `body`: ContentItem = this.`body`,
    `anchor`: ContentItem = this.`anchor`,
    `sourceMetadata`: ByteBuffer = this.`sourceMetadata`,
    `streamId`: String = this.`streamId`,
    `streamTime`: StreamTime = this.`streamTime`,
    `annotation`: Option[Seq[Annotation]] = this.`annotation`
  ): StreamItem = new Immutable(
    `docId`,
    `absUrl`,
    `schost`,
    `originalUrl`,
    `source`,
    `title`,
    `body`,
    `anchor`,
    `sourceMetadata`,
    `streamId`,
    `streamTime`,
    `annotation`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[StreamItem]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 12

  override def productElement(n: Int): Any = n match {
    case 0 => `docId`
    case 1 => `absUrl`
    case 2 => `schost`
    case 3 => `originalUrl`
    case 4 => `source`
    case 5 => `title`
    case 6 => `body`
    case 7 => `anchor`
    case 8 => `sourceMetadata`
    case 9 => `streamId`
    case 10 => `streamTime`
    case 11 => `annotation`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "StreamItem"
}