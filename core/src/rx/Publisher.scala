package rx

import java.util.{ArrayList => JavaArrayList}
import javax.annotation.Nonnull

abstract class Publisher[Event] {
  private val listeners = new JavaArrayList[Listener[Event]]()

  @Nonnull
  def onChange(@Nonnull listener: Listener[Event]): Subscription[Event] = {
    listeners.add(listener)
    new Subscription(this, listener)
  }

  @Nonnull
  def onChange(@Nonnull fireInitialEvent: Boolean, @Nonnull listener: Listener[Event]): Subscription[Event] = {
    if (fireInitialEvent) {
      listener.eventHappened(createInitialEvent())
    }
    onChange(listener)
  }

  protected def fireListeners(@Nonnull event: Event): Unit = {
    val listenersDefCopy = new JavaArrayList(listeners)
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