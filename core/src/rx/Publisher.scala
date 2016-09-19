package rx

import java.lang.ref.{ReferenceQueue, WeakReference}
import java.util.{ArrayList => JavaArrayList, WeakHashMap}
import javax.annotation.Nonnull

abstract class Publisher[Event] {
  private val listeners = new WeakHashMap[Object, Listener[Event]]()

  @Nonnull
  def onChange(subscriber: Object, @Nonnull listener: Listener[Event]): Subscription[Event] = {
    listeners.put(subscriber, listener)
    new Subscription(this, listener)
  }

  @Nonnull
  def onChange(subscriber: Object, @Nonnull fireInitialEvent: Boolean, @Nonnull listener: Listener[Event]): Subscription[Event] = {
    if (fireInitialEvent) {
      listener.eventHappened(createInitialEvent())
    }
    onChange(subscriber, listener)
  }

  private[rx] def fireListeners(@Nonnull event: Event): Unit = {
    val listenersDefCopy = listeners.values()
    val iterator = listenersDefCopy.iterator()
    while (iterator.hasNext) {
      val listener = iterator.next()
      listener.eventHappened(event)
    }
  }

  @Nonnull
  protected def createInitialEvent(): Event

  private[rx] def removeListener(@Nonnull listener: Listener[Event]): Unit = {
    listeners.remove(listener)
  }
}