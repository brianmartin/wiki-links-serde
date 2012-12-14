package kba

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

object ContentItem extends ThriftStructCodec[ContentItem] {
  val Struct = new TStruct("ContentItem")
  val RawField = new TField("raw", TType.STRING, 1)
  val EncodingField = new TField("encoding", TType.STRING, 2)
  val CleansedField = new TField("cleansed", TType.STRING, 3)
  val NerField = new TField("ner", TType.STRING, 4)
  val HtmlField = new TField("html", TType.STRING, 5)

  def encode(_item: ContentItem, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): ContentItem = decode(_iprot)

  def apply(
    `raw`: ByteBuffer,
    `encoding`: String,
    `cleansed`: Option[ByteBuffer] = None,
    `ner`: Option[ByteBuffer] = None,
    `html`: Option[ByteBuffer] = None
  ): ContentItem = new Immutable(
    `raw`,
    `encoding`,
    `cleansed`,
    `ner`,
    `html`
  )

  def unapply(_item: ContentItem): Option[Product5[ByteBuffer, String, Option[ByteBuffer], Option[ByteBuffer], Option[ByteBuffer]]] = Some(_item)

  object Immutable extends ThriftStructCodec[ContentItem] {
    def encode(_item: ContentItem, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `raw`: ByteBuffer = null
      var _got_raw = false
      var `encoding`: String = null
      var _got_encoding = false
      var `cleansed`: ByteBuffer = null
      var _got_cleansed = false
      var `ner`: ByteBuffer = null
      var _got_ner = false
      var `html`: ByteBuffer = null
      var _got_html = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* raw */
              _field.`type` match {
                case TType.STRING => {
                  `raw` = {
                    _iprot.readBinary()
                  }
                  _got_raw = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* encoding */
              _field.`type` match {
                case TType.STRING => {
                  `encoding` = {
                    _iprot.readString()
                  }
                  _got_encoding = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 3 => { /* cleansed */
              _field.`type` match {
                case TType.STRING => {
                  `cleansed` = {
                    _iprot.readBinary()
                  }
                  _got_cleansed = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 4 => { /* ner */
              _field.`type` match {
                case TType.STRING => {
                  `ner` = {
                    _iprot.readBinary()
                  }
                  _got_ner = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 5 => { /* html */
              _field.`type` match {
                case TType.STRING => {
                  `html` = {
                    _iprot.readBinary()
                  }
                  _got_html = true
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
        `raw`,
        `encoding`,
        if (_got_cleansed) Some(`cleansed`) else None,
        if (_got_ner) Some(`ner`) else None,
        if (_got_html) Some(`html`) else None
      )
    }
  }

  /**
   * The default read-only implementation of ContentItem.  You typically should not need to
   * directly reference this class; instead, use the ContentItem.apply method to construct
   * new instances.
   */
  class Immutable(
    val `raw`: ByteBuffer,
    val `encoding`: String,
    val `cleansed`: Option[ByteBuffer] = None,
    val `ner`: Option[ByteBuffer] = None,
    val `html`: Option[ByteBuffer] = None
  ) extends ContentItem

  /**
   * This Proxy trait allows you to extend the ContentItem trait with additional state or
   * behavior and implement the read-only methods from ContentItem using an underlying
   * instance.
   */
  trait Proxy extends ContentItem {
    protected def _underlyingContentItem: ContentItem
    def `raw`: ByteBuffer = _underlyingContentItem.`raw`
    def `encoding`: String = _underlyingContentItem.`encoding`
    def `cleansed`: Option[ByteBuffer] = _underlyingContentItem.`cleansed`
    def `ner`: Option[ByteBuffer] = _underlyingContentItem.`ner`
    def `html`: Option[ByteBuffer] = _underlyingContentItem.`html`
  }
}

trait ContentItem extends ThriftStruct
  with Product5[ByteBuffer, String, Option[ByteBuffer], Option[ByteBuffer], Option[ByteBuffer]]
  with java.io.Serializable
{
  import ContentItem._

  def `raw`: ByteBuffer
  def `encoding`: String
  def `cleansed`: Option[ByteBuffer]
  def `ner`: Option[ByteBuffer]
  def `html`: Option[ByteBuffer]

  def _1 = `raw`
  def _2 = `encoding`
  def _3 = `cleansed`
  def _4 = `ner`
  def _5 = `html`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `raw_item` = `raw`
      _oprot.writeFieldBegin(RawField)
      _oprot.writeBinary(`raw_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `encoding_item` = `encoding`
      _oprot.writeFieldBegin(EncodingField)
      _oprot.writeString(`encoding_item`)
      _oprot.writeFieldEnd()
    }
    if (`cleansed`.isDefined) {
      val `cleansed_item` = `cleansed`.get
      _oprot.writeFieldBegin(CleansedField)
      _oprot.writeBinary(`cleansed_item`)
      _oprot.writeFieldEnd()
    }
    if (`ner`.isDefined) {
      val `ner_item` = `ner`.get
      _oprot.writeFieldBegin(NerField)
      _oprot.writeBinary(`ner_item`)
      _oprot.writeFieldEnd()
    }
    if (`html`.isDefined) {
      val `html_item` = `html`.get
      _oprot.writeFieldBegin(HtmlField)
      _oprot.writeBinary(`html_item`)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `raw`: ByteBuffer = this.`raw`,
    `encoding`: String = this.`encoding`,
    `cleansed`: Option[ByteBuffer] = this.`cleansed`,
    `ner`: Option[ByteBuffer] = this.`ner`,
    `html`: Option[ByteBuffer] = this.`html`
  ): ContentItem = new Immutable(
    `raw`,
    `encoding`,
    `cleansed`,
    `ner`,
    `html`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[ContentItem]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 5

  override def productElement(n: Int): Any = n match {
    case 0 => `raw`
    case 1 => `encoding`
    case 2 => `cleansed`
    case 3 => `ner`
    case 4 => `html`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "ContentItem"
}