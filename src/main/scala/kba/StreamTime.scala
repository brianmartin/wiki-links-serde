package kba

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

object StreamTime extends ThriftStructCodec[StreamTime] {
  val Struct = new TStruct("StreamTime")
  val EpochTicksField = new TField("epochTicks", TType.DOUBLE, 1)
  val ZuluTimestampField = new TField("zuluTimestamp", TType.STRING, 2)

  def encode(_item: StreamTime, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): StreamTime = decode(_iprot)

  def apply(
    `epochTicks`: Double,
    `zuluTimestamp`: String
  ): StreamTime = new Immutable(
    `epochTicks`,
    `zuluTimestamp`
  )

  def unapply(_item: StreamTime): Option[Product2[Double, String]] = Some(_item)

  object Immutable extends ThriftStructCodec[StreamTime] {
    def encode(_item: StreamTime, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `epochTicks`: Double = 0.0
      var _got_epochTicks = false
      var `zuluTimestamp`: String = null
      var _got_zuluTimestamp = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* epochTicks */
              _field.`type` match {
                case TType.DOUBLE => {
                  `epochTicks` = {
                    _iprot.readDouble()
                  }
                  _got_epochTicks = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* zuluTimestamp */
              _field.`type` match {
                case TType.STRING => {
                  `zuluTimestamp` = {
                    _iprot.readString()
                  }
                  _got_zuluTimestamp = true
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
        `epochTicks`,
        `zuluTimestamp`
      )
    }
  }

  /**
   * The default read-only implementation of StreamTime.  You typically should not need to
   * directly reference this class; instead, use the StreamTime.apply method to construct
   * new instances.
   */
  class Immutable(
    val `epochTicks`: Double,
    val `zuluTimestamp`: String
  ) extends StreamTime

  /**
   * This Proxy trait allows you to extend the StreamTime trait with additional state or
   * behavior and implement the read-only methods from StreamTime using an underlying
   * instance.
   */
  trait Proxy extends StreamTime {
    protected def _underlyingStreamTime: StreamTime
    def `epochTicks`: Double = _underlyingStreamTime.`epochTicks`
    def `zuluTimestamp`: String = _underlyingStreamTime.`zuluTimestamp`
  }
}

trait StreamTime extends ThriftStruct
  with Product2[Double, String]
  with java.io.Serializable
{
  import StreamTime._

  def `epochTicks`: Double
  def `zuluTimestamp`: String

  def _1 = `epochTicks`
  def _2 = `zuluTimestamp`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `epochTicks_item` = `epochTicks`
      _oprot.writeFieldBegin(EpochTicksField)
      _oprot.writeDouble(`epochTicks_item`)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `zuluTimestamp_item` = `zuluTimestamp`
      _oprot.writeFieldBegin(ZuluTimestampField)
      _oprot.writeString(`zuluTimestamp_item`)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `epochTicks`: Double = this.`epochTicks`,
    `zuluTimestamp`: String = this.`zuluTimestamp`
  ): StreamTime = new Immutable(
    `epochTicks`,
    `zuluTimestamp`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[StreamTime]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 2

  override def productElement(n: Int): Any = n match {
    case 0 => `epochTicks`
    case 1 => `zuluTimestamp`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "StreamTime"
}