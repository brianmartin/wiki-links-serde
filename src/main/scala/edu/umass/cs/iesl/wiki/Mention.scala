package edu.umass.cs.iesl.wiki

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

object Mention extends ThriftStructCodec[Mention] {
  val Struct = new TStruct("Mention")
  val WikiUrlField = new TField("wikiUrl", TType.STRING, 1)
  val AnchorTextField = new TField("anchorText", TType.STRING, 2)
  val RawTextOffsetField = new TField("rawTextOffset", TType.I32, 3)

  def encode(_item: Mention, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): Mention = decode(_iprot)

  def apply(
    `wikiUrl`: String,
    `anchorText`: String,
    `rawTextOffset`: Int
  ): Mention = new Immutable(
    `wikiUrl`,
    `anchorText`,
    `rawTextOffset`
  )

  def unapply(_item: Mention): Option[Product3[String, String, Int]] = Some(_item)

  object Immutable extends ThriftStructCodec[Mention] {
    def encode(_item: Mention, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `wikiUrl`: String = null
      var _got_wikiUrl = false
      var `anchorText`: String = null
      var _got_anchorText = false
      var `rawTextOffset`: Int = 0
      var _got_rawTextOffset = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* wikiUrl */
              _field.`type` match {
                case TType.STRING => {
                  `wikiUrl` = {
                    _iprot.readString()
                  }
                  _got_wikiUrl = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* anchorText */
              _field.`type` match {
                case TType.STRING => {
                  `anchorText` = {
                    _iprot.readString()
                  }
                  _got_anchorText = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 3 => { /* rawTextOffset */
              _field.`type` match {
                case TType.I32 => {
                  `rawTextOffset` = {
                    _iprot.readI32()
                  }
                  _got_rawTextOffset = true
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
        `wikiUrl`,
        `anchorText`,
        `rawTextOffset`
      )
    }
  }

  /**
   * The default read-only implementation of Mention.  You typically should not need to
   * directly reference this class; instead, use the Mention.apply method to construct
   * new instances.
   */
  class Immutable(
    val `wikiUrl`: String,
    val `anchorText`: String,
    val `rawTextOffset`: Int
  ) extends Mention

  /**
   * This Proxy trait allows you to extend the Mention trait with additional state or
   * behavior and implement the read-only methods from Mention using an underlying
   * instance.
   */
  trait Proxy extends Mention {
    protected def _underlyingMention: Mention
    def `wikiUrl`: String = _underlyingMention.`wikiUrl`
    def `anchorText`: String = _underlyingMention.`anchorText`
    def `rawTextOffset`: Int = _underlyingMention.`rawTextOffset`
  }
}

trait Mention extends ThriftStruct
  with Product3[String, String, Int]
  with java.io.Serializable
{
  import Mention._

  def `wikiUrl`: String
  def `anchorText`: String
  def `rawTextOffset`: Int

  def _1 = `wikiUrl`
  def _2 = `anchorText`
  def _3 = `rawTextOffset`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `wikiUrl_item` = `wikiUrl`
      _oprot.writeFieldBegin(WikiUrlField)
      _oprot.writeString(`wikiUrl_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `anchorText_item` = `anchorText`
      _oprot.writeFieldBegin(AnchorTextField)
      _oprot.writeString(`anchorText_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `rawTextOffset_item` = `rawTextOffset`
      _oprot.writeFieldBegin(RawTextOffsetField)
      _oprot.writeI32(`rawTextOffset_item`)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `wikiUrl`: String = this.`wikiUrl`,
    `anchorText`: String = this.`anchorText`,
    `rawTextOffset`: Int = this.`rawTextOffset`
  ): Mention = new Immutable(
    `wikiUrl`,
    `anchorText`,
    `rawTextOffset`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[Mention]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 3

  override def productElement(n: Int): Any = n match {
    case 0 => `wikiUrl`
    case 1 => `anchorText`
    case 2 => `rawTextOffset`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "Mention"
}