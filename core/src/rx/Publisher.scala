package rx

import java.lang.ref.{ReferenceQueue, WeakReference}
import java.util.{ArrayList => JavaArrayList}
import javax.annotation.Nonnull

abstract class Publisher[Event] {
  private val listeners = new JavaArrayList[(WeakReference[Object], Listener[Event])]()

  private val queue = new ReferenceQueue[Object]()

  @Nonnull
  def onChange(subscriber: Object, @Nonnull listener: Listener[Event]): Subscription[Event] = {
    listeners.add((new WeakReference(subscriber, queue), listener))
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
    cleanupListenersIfNeeded()
    val listenersDefCopy = new JavaArrayList(listeners)
    val iterator = listenersDefCopy.iterator()
    while (iterator.hasNext) {
      val tuple = iterator.next()
      tuple._2.eventHappened(event)
    }
  }

  private def cleanupListenersIfNeeded(): Unit = {
    var ref = queue.poll()
    while (ref ne null) {
      var i = 0
      var found = false
      while (i < listeners.size() && !found) {
        if (listeners.get(i)._1 eq ref) {
          found = true
          listeners.remove(i)
        } else {
          i += 1
        }
      }
      ref = queue.poll()
    }
  }

  @Nonnull
  protected def createInitialEvent(): Event

  private[rx] def removeListener(@Nonnull listener: Listener[Event]): Unit = {
    listeners.remove(listener)
  }
}