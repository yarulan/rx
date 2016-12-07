package rx

import java.util

trait Publisher[T] {
  def onChange(subscriber: Any, listener: Listener[T]): Subscription

  def onChange(listener: Listener[T])(implicit subscriber: Subscriber): Subscription = {
    onChange(subscriber, listener)
  }
}

trait PublisherImpl[T] extends Publisher[T] {
  private val listeners = new util.WeakHashMap[Any, Listener[T]]()

  override def onChange(subscriber: Any, listener: Listener[T]): Subscription = {
    listeners.put(subscriber, listener)
    new Subscription {
      override def end(): Unit = {
        removeListener(listener)
      }
    }
  }

  protected def fireListeners(event: T): Unit = {
    val listenersDefCopy = listeners.values()
    val iterator = listenersDefCopy.iterator()
    while (iterator.hasNext) {
      val listener = iterator.next()
      listener.eventHappened(event)
    }
  }

  private def removeListener(listener: Listener[T]) = {
    listeners.values().remove(listener)
  }
}