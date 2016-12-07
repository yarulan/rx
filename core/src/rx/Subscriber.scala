package rx

trait Subscriber {
  implicit protected val subscriber: Subscriber = this
}