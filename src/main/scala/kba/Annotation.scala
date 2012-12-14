package kba

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

object Annotation extends ThriftStructCodec[Annotation] {
  val Struct = new TStruct("Annotation")
  val SourceField = new TField("source", TType.STRING, 1)
  val StreamTimeField = new TField("streamTime", TType.STRUCT, 2)
  val PathField = new TField("path", TType.STRING, 3)
  val OffsetField = new TField("offset", TType.STRUCT, 4)
  val LabelField = new TField("label", TType.STRUCT, 5)

  def encode(_item: Annotation, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): Annotation = decode(_iprot)

  def apply(
    `source`: String,
    `streamTime`: StreamTime,
    `path`: String,
    `offset`: Offset,
    `label`: Label
  ): Annotation = new Immutable(
    `source`,
    `streamTime`,
    `path`,
    `offset`,
    `label`
  )

  def unapply(_item: Annotation): Option[Product5[String, StreamTime, String, Offset, Label]] = Some(_item)

  object Immutable extends ThriftStructCodec[Annotation] {
    def encode(_item: Annotation, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `source`: String = null
      var _got_source = false
      var `streamTime`: StreamTime = null
      var _got_streamTime = false
      var `path`: String = null
      var _got_path = false
      var `offset`: Offset = null
      var _got_offset = false
      var `label`: Label = null
      var _got_label = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* source */
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
            case 2 => { /* streamTime */
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
            case 3 => { /* path */
              _field.`type` match {
                case TType.STRING => {
                  `path` = {
                    _iprot.readString()
                  }
                  _got_path = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 4 => { /* offset */
              _field.`type` match {
                case TType.STRUCT => {
                  `offset` = {
                    Offset.decode(_iprot)
                  }
                  _got_offset = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 5 => { /* label */
              _field.`type` match {
                case TType.STRUCT => {
                  `label` = {
                    Label.decode(_iprot)
                  }
                  _got_label = true
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
        `source`,
        `streamTime`,
        `path`,
        `offset`,
        `label`
      )
    }
  }

  /**
   * The default read-only implementation of Annotation.  You typically should not need to
   * directly reference this class; instead, use the Annotation.apply method to construct
   * new instances.
   */
  class Immutable(
    val `source`: String,
    val `streamTime`: StreamTime,
    val `path`: String,
    val `offset`: Offset,
    val `label`: Label
  ) extends Annotation

  /**
   * This Proxy trait allows you to extend the Annotation trait with additional state or
   * behavior and implement the read-only methods from Annotation using an underlying
   * instance.
   */
  trait Proxy extends Annotation {
    protected def _underlyingAnnotation: Annotation
    def `source`: String = _underlyingAnnotation.`source`
    def `streamTime`: StreamTime = _underlyingAnnotation.`streamTime`
    def `path`: String = _underlyingAnnotation.`path`
    def `offset`: Offset = _underlyingAnnotation.`offset`
    def `label`: Label = _underlyingAnnotation.`label`
  }
}

trait Annotation extends ThriftStruct
  with Product5[String, StreamTime, String, Offset, Label]
  with java.io.Serializable
{
  import Annotation._

  def `source`: String
  def `streamTime`: StreamTime
  def `path`: String
  def `offset`: Offset
  def `label`: Label

  def _1 = `source`
  def _2 = `streamTime`
  def _3 = `path`
  def _4 = `offset`
  def _5 = `label`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `source_item` = `source`
      _oprot.writeFieldBegin(SourceField)
      _oprot.writeString(`source_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `streamTime_item` = `streamTime`
      _oprot.writeFieldBegin(StreamTimeField)
      `streamTime_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `path_item` = `path`
      _oprot.writeFieldBegin(PathField)
      _oprot.writeString(`path_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `offset_item` = `offset`
      _oprot.writeFieldBegin(OffsetField)
      `offset_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `label_item` = `label`
      _oprot.writeFieldBegin(LabelField)
      `label_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `source`: String = this.`source`,
    `streamTime`: StreamTime = this.`streamTime`,
    `path`: String = this.`path`,
    `offset`: Offset = this.`offset`,
    `label`: Label = this.`label`
  ): Annotation = new Immutable(
    `source`,
    `streamTime`,
    `path`,
    `offset`,
    `label`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[Annotation]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 5

  override def productElement(n: Int): Any = n match {
    case 0 => `source`
    case 1 => `streamTime`
    case 2 => `path`
    case 3 => `offset`
    case 4 => `label`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "Annotation"
}