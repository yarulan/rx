package rx

class Var[T](initialValue: T) extends Rx[T] {
  value = initialValue

  protected def setValue(newValue: T): Unit = {
    val oldValue = value
    value = newValue
    fireListeners(new ValueChangeEvent(newValue, oldValue))
  }

  /**
    * Scala API.
    */
  def apply(newValue: T): Unit = {
    setValue(newValue)
  }

  /**
    * Scala API.
    */
  def :=(newValue: T): Unit = {
    setValue(newValue)
  }

  /**
    * Kotlin API.
    */
  def invoke(newValue: T): Unit = {
    setValue(newValue)
  }

  override def toString: String = {
    val builder = new java.lang.StringBuilder
    builder.append("Var(")
    builder.append(value.toString)
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