package rx

class Subscription[Event](val publisher: Publisher[Event], val listener: Listener[Event]) {
  def end(): Unit = {
    publisher.removeListener(listener)
  }
}