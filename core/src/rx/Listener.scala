package rx

trait Listener[T] {
  def eventHappened(event: T)
}