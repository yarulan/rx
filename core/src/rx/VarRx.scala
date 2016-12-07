package rx

protected abstract class VarRx[T] extends Rx[T] with PublisherImpl[ValueChangeEvent[T]] {
  protected var _value: T = _

  private def createInitialEvent(): ValueChangeEvent[T] = {
    ValueChangeEvent[T](_value, _value)
  }

  def value: T = _value

  def apply(): T = {
    val expr = Expr.enclosingExpr.get()
    if (expr != null) {
      expr.subscribeTo(this)
    }
    _value
  }

  def onChange(subscriber: Any, fireInitialEvent: Boolean, listener: Listener[ValueChangeEvent[T]]): Subscription = {
    if (fireInitialEvent) {
      listener.eventHappened(createInitialEvent())
    }
    onChange(subscriber, listener)
  }
}



