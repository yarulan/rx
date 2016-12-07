package rx

class Var[T](initialValue: T) extends VarRx[T] {
  private var subscription: Option[Subscription] = None
  _value = initialValue

  protected def setValue(newValue: T): Unit = {
    val oldValue = _value
    _value = newValue
    fireListeners(ValueChangeEvent(newValue, oldValue))
  }

  private def unsubscribe(): Unit = {
    subscription.foreach(_.end())
  }

  def :=(newValue: T): Unit = {
    unsubscribe()
    setValue(newValue)
  }

  def :=(expr: Rx[T]): Unit = {
    unsubscribe()
    val s = expr.onChange(this, true, e => setValue(e.value))
    subscription = Some(s)
  }

  override def toString: String = {
    val builder = new java.lang.StringBuilder
    builder.append("Var(")
    builder.append(_value.toString)
    builder.append(")")
    builder.toString
  }
}

object Var {
  def apply[T](initialValue: T): Var[T] = new Var(initialValue)

  /**
    * Java API.
    */
  def of[T](initialValue: T): Var[T] = new Var(initialValue)
}