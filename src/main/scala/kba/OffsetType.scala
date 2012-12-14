package kba

import org.apache.thrift.TEnum

object OffsetType {
  case object Owpl extends OffsetType(0, "Owpl")
  case object Byteoffset extends OffsetType(1, "Byteoffset")
  case object Charoffset extends OffsetType(2, "Charoffset")

  def apply(value: Int): OffsetType = {
    value match {
      case 0 => Owpl
      case 1 => Byteoffset
      case 2 => Charoffset
      case _ => throw new NoSuchElementException(value.toString)
    }
  }

  def get(value: Int): Option[OffsetType] = {
    value match {
      case 0 => scala.Some(Owpl)
      case 1 => scala.Some(Byteoffset)
      case 2 => scala.Some(Charoffset)
      case _ => scala.None
    }
  }

  def valueOf(name: String): Option[OffsetType] = {
    name.toLowerCase match {
      case "owpl" => scala.Some(OffsetType.Owpl)
      case "byteoffset" => scala.Some(OffsetType.Byteoffset)
      case "charoffset" => scala.Some(OffsetType.Charoffset)
      case _ => scala.None
    }
  }
}

abstract class OffsetType(val value: Int, val name: String) extends TEnum {
  def getValue = value
}
