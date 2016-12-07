package rx.model

import java.util.{ArrayList => JavaArrayList, List => JavaList}

import rx.{VarRx, Var, VarList}

abstract class FieldValue {
  val modified: VarRx[Boolean]

  def setOwner(list: ListModel[_]): Unit = {

  }
}

abstract class FormModel {
  def field[T](initialValue: T) = new FieldModel[T](initialValue)
//  def list[T] = new ListModel[T](this)
}


class FieldModel[T](initialValue: T) extends FieldValue {
  val value: Var[T] = Var(initialValue)

  override val modified: VarRx[Boolean] = ??? //value.map(_ != initialValue)
}





class SettingsModel extends FormModel {
//  val mappings = list[Mapping]
  val showTrail = field[Boolean](false)
}

class Mapping extends FormModel {
  val gesture = field[Option[String]](None)
  val action = field[Option[String]](None)
}

