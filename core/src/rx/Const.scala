package rx

case class Const[T](override val value: T) extends Rx[T] {
  override def apply(): T = value


  override def onChange(subscriber: Any, fireInitialEvent: Boolean, listener: Listener[ValueChangeEvent[T]]): Subscription = {
    if (fireInitialEvent) {
      listener.eventHappened(ValueChangeEvent(value, value))
    }
    Const.dummySubscription
  }
}

object Const {
  private val dummySubscription = new Subscription {
    override def end(): Unit = {}
  }
}