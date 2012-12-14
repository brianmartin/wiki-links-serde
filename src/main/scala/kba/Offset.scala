package kba

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

object Offset extends ThriftStructCodec[Offset] {
  val Struct = new TStruct("Offset")
  val DocLevelField = new TField("docLevel", TType.BOOL, 1)
  val XpathField = new TField("xpath", TType.STRING, 2)
  val TypeField = new TField("type", TType.I32, 3)
  val FirstField = new TField("first", TType.I64, 4)
  val LengthField = new TField("length", TType.I64, 5)

  def encode(_item: Offset, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): Offset = decode(_iprot)

  def apply(
    `docLevel`: Boolean,
    `xpath`: String,
    `type`: OffsetType,
    `first`: Long,
    `length`: Long
  ): Offset = new Immutable(
    `docLevel`,
    `xpath`,
    `type`,
    `first`,
    `length`
  )

  def unapply(_item: Offset): Option[Product5[Boolean, String, OffsetType, Long, Long]] = Some(_item)

  object Immutable extends ThriftStructCodec[Offset] {
    def encode(_item: Offset, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `docLevel`: Boolean = false
      var _got_docLevel = false
      var `xpath`: String = null
      var _got_xpath = false
      var `type`: OffsetType = null
      var _got_type = false
      var `first`: Long = 0L
      var _got_first = false
      var `length`: Long = 0L
      var _got_length = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* docLevel */
              _field.`type` match {
                case TType.BOOL => {
                  `docLevel` = {
                    _iprot.readBool()
                  }
                  _got_docLevel = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* xpath */
              _field.`type` match {
                case TType.STRING => {
                  `xpath` = {
                    _iprot.readString()
                  }
                  _got_xpath = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 3 => { /* type */
              _field.`type` match {
                case TType.I32 => {
                  `type` = {
                    OffsetType(_iprot.readI32())
                  }
                  _got_type = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 4 => { /* first */
              _field.`type` match {
                case TType.I64 => {
                  `first` = {
                    _iprot.readI64()
                  }
                  _got_first = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 5 => { /* length */
              _field.`type` match {
                case TType.I64 => {
                  `length` = {
                    _iprot.readI64()
                  }
                  _got_length = true
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
        `docLevel`,
        `xpath`,
        `type`,
        `first`,
        `length`
      )
    }
  }

  /**
   * The default read-only implementation of Offset.  You typically should not need to
   * directly reference this class; instead, use the Offset.apply method to construct
   * new instances.
   */
  class Immutable(
    val `docLevel`: Boolean,
    val `xpath`: String,
    val `type`: OffsetType,
    val `first`: Long,
    val `length`: Long
  ) extends Offset

  /**
   * This Proxy trait allows you to extend the Offset trait with additional state or
   * behavior and implement the read-only methods from Offset using an underlying
   * instance.
   */
  trait Proxy extends Offset {
    protected def _underlyingOffset: Offset
    def `docLevel`: Boolean = _underlyingOffset.`docLevel`
    def `xpath`: String = _underlyingOffset.`xpath`
    def `type`: OffsetType = _underlyingOffset.`type`
    def `first`: Long = _underlyingOffset.`first`
    def `length`: Long = _underlyingOffset.`length`
  }
}

trait Offset extends ThriftStruct
  with Product5[Boolean, String, OffsetType, Long, Long]
  with java.io.Serializable
{
  import Offset._

  def `docLevel`: Boolean
  def `xpath`: String
  def `type`: OffsetType
  def `first`: Long
  def `length`: Long

  def _1 = `docLevel`
  def _2 = `xpath`
  def _3 = `type`
  def _4 = `first`
  def _5 = `length`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `docLevel_item` = `docLevel`
      _oprot.writeFieldBegin(DocLevelField)
      _oprot.writeBool(`docLevel_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `xpath_item` = `xpath`
      _oprot.writeFieldBegin(XpathField)
      _oprot.writeString(`xpath_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `type_item` = `type`
      _oprot.writeFieldBegin(TypeField)
      _oprot.writeI32(`type_item`.value)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `first_item` = `first`
      _oprot.writeFieldBegin(FirstField)
      _oprot.writeI64(`first_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `length_item` = `length`
      _oprot.writeFieldBegin(LengthField)
      _oprot.writeI64(`length_item`)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `docLevel`: Boolean = this.`docLevel`,
    `xpath`: String = this.`xpath`,
    `type`: OffsetType = this.`type`,
    `first`: Long = this.`first`,
    `length`: Long = this.`length`
  ): Offset = new Immutable(
    `docLevel`,
    `xpath`,
    `type`,
    `first`,
    `length`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[Offset]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 5

  override def productElement(n: Int): Any = n match {
    case 0 => `docLevel`
    case 1 => `xpath`
    case 2 => `type`
    case 3 => `first`
    case 4 => `length`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "Offset"
}