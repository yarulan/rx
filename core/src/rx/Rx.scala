package rx

import javax.annotation.Nonnull

import rx.internal.MappedRx

/**
  * [[rx.Rx]] is a thing that holds a value and can notify others, when the value changes.
  */
abstract class Rx[T] extends Publisher[ValueChangeEvent[T]] {
  protected var value: T = _

  @Nonnull
  override protected def createInitialEvent(): ValueChangeEvent[T] = {
    new ValueChangeEvent[T](value, value)
  }

  @Nonnull
  def getValue: T = {
    val expr = Expr.enclosingExpr.get()
    if (expr != null) {
      expr.subscribeTo(this)
    }
    value
  }
  @Nonnull
  def getValueSilently: T = value

  /**
    * Scala API.
    */
  @Nonnull
  def apply(): T = getValue

  /**
    * Kotlin API.
    */
  @Nonnull
  def invoke: T = getValue

  def map[U](@Nonnull f: T => U): Rx[U] = new MappedRx(this, f)
}