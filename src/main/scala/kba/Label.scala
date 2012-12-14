package kba

import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import com.twitter.finagle.SourcedException
import scala.collection.mutable
import scala.collection.{Map, Set}

object Label extends ThriftStructCodec[Label] {
  val Struct = new TStruct("Label")
  val TargetKbField = new TField("targetKb", TType.STRING, 1)
  val SnapshotTimeField = new TField("snapshotTime", TType.STRUCT, 2)
  val TargetIdField = new TField("targetId", TType.STRING, 3)
  val RelevanceField = new TField("relevance", TType.I16, 4)
  val ConfidenceField = new TField("confidence", TType.I16, 5)

  def encode(_item: Label, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): Label = decode(_iprot)

  def apply(
    `targetKb`: String,
    `snapshotTime`: Option[StreamTime] = None,
    `targetId`: String,
    `relevance`: Option[Short] = None,
    `confidence`: Option[Short] = None
  ): Label = new Immutable(
    `targetKb`,
    `snapshotTime`,
    `targetId`,
    `relevance`,
    `confidence`
  )

  def unapply(_item: Label): Option[Product5[String, Option[StreamTime], String, Option[Short], Option[Short]]] = Some(_item)

  object Immutable extends ThriftStructCodec[Label] {
    def encode(_item: Label, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var `targetKb`: String = null
      var _got_targetKb = false
      var `snapshotTime`: StreamTime = null
      var _got_snapshotTime = false
      var `targetId`: String = null
      var _got_targetId = false
      var `relevance`: Short = 0
      var _got_relevance = false
      var `confidence`: Short = 0
      var _got_confidence = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* targetKb */
              _field.`type` match {
                case TType.STRING => {
                  `targetKb` = {
                    _iprot.readString()
                  }
                  _got_targetKb = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* snapshotTime */
              _field.`type` match {
                case TType.STRUCT => {
                  `snapshotTime` = {
                    StreamTime.decode(_iprot)
                  }
                  _got_snapshotTime = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 3 => { /* targetId */
              _field.`type` match {
                case TType.STRING => {
                  `targetId` = {
                    _iprot.readString()
                  }
                  _got_targetId = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 4 => { /* relevance */
              _field.`type` match {
                case TType.I16 => {
                  `relevance` = {
                    _iprot.readI16()
                  }
                  _got_relevance = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 5 => { /* confidence */
              _field.`type` match {
                case TType.I16 => {
                  `confidence` = {
                    _iprot.readI16()
                  }
                  _got_confidence = true
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
        `targetKb`,
        if (_got_snapshotTime) Some(`snapshotTime`) else None,
        `targetId`,
        if (_got_relevance) Some(`relevance`) else None,
        if (_got_confidence) Some(`confidence`) else None
      )
    }
  }

  /**
   * The default read-only implementation of Label.  You typically should not need to
   * directly reference this class; instead, use the Label.apply method to construct
   * new instances.
   */
  class Immutable(
    val `targetKb`: String,
    val `snapshotTime`: Option[StreamTime] = None,
    val `targetId`: String,
    val `relevance`: Option[Short] = None,
    val `confidence`: Option[Short] = None
  ) extends Label

  /**
   * This Proxy trait allows you to extend the Label trait with additional state or
   * behavior and implement the read-only methods from Label using an underlying
   * instance.
   */
  trait Proxy extends Label {
    protected def _underlyingLabel: Label
    def `targetKb`: String = _underlyingLabel.`targetKb`
    def `snapshotTime`: Option[StreamTime] = _underlyingLabel.`snapshotTime`
    def `targetId`: String = _underlyingLabel.`targetId`
    def `relevance`: Option[Short] = _underlyingLabel.`relevance`
    def `confidence`: Option[Short] = _underlyingLabel.`confidence`
  }
}

trait Label extends ThriftStruct
  with Product5[String, Option[StreamTime], String, Option[Short], Option[Short]]
  with java.io.Serializable
{
  import Label._

  def `targetKb`: String
  def `snapshotTime`: Option[StreamTime]
  def `targetId`: String
  def `relevance`: Option[Short]
  def `confidence`: Option[Short]

  def _1 = `targetKb`
  def _2 = `snapshotTime`
  def _3 = `targetId`
  def _4 = `relevance`
  def _5 = `confidence`

  override def write(_oprot: TProtocol) {
    validate()
    _oprot.writeStructBegin(Struct)
    if (true) {
      val `targetKb_item` = `targetKb`
      _oprot.writeFieldBegin(TargetKbField)
      _oprot.writeString(`targetKb_item`)
      _oprot.writeFieldEnd()
    }
    if (`snapshotTime`.isDefined) {
      val `snapshotTime_item` = `snapshotTime`.get
      _oprot.writeFieldBegin(SnapshotTimeField)
      `snapshotTime_item`.write(_oprot)
      _oprot.writeFieldEnd()
    }
    if (true) {
      val `targetId_item` = `targetId`
      _oprot.writeFieldBegin(TargetIdField)
      _oprot.writeString(`targetId_item`)
      _oprot.writeFieldEnd()
    }
    if (`relevance`.isDefined) {
      val `relevance_item` = `relevance`.get
      _oprot.writeFieldBegin(RelevanceField)
      _oprot.writeI16(`relevance_item`)
      _oprot.writeFieldEnd()
    }
    if (`confidence`.isDefined) {
      val `confidence_item` = `confidence`.get
      _oprot.writeFieldBegin(ConfidenceField)
      _oprot.writeI16(`confidence_item`)
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    `targetKb`: String = this.`targetKb`,
    `snapshotTime`: Option[StreamTime] = this.`snapshotTime`,
    `targetId`: String = this.`targetId`,
    `relevance`: Option[Short] = this.`relevance`,
    `confidence`: Option[Short] = this.`confidence`
  ): Label = new Immutable(
    `targetKb`,
    `snapshotTime`,
    `targetId`,
    `relevance`,
    `confidence`
  )

  /**
   * Checks that all required fields are non-null.
   */
  def validate() {
  }

  def canEqual(other: Any) = other.isInstanceOf[Label]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)

  override def productArity = 5

  override def productElement(n: Int): Any = n match {
    case 0 => `targetKb`
    case 1 => `snapshotTime`
    case 2 => `targetId`
    case 3 => `relevance`
    case 4 => `confidence`
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix = "Label"
}