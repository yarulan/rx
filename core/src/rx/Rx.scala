package rx

import rx.internal.MappedRx

trait Rx[T] extends Publisher[ValueChangeEvent[T]] with RxOps[T] {
  def apply(): T
  def value: T
  def onChange(subscriber: Any, fireInitialEvent: Boolean, listener: Listener[ValueChangeEvent[T]]): Subscription
}

trait RxOps[T] {
  self: Rx[T] =>

  override def onChange(subscriber: Any, listener: Listener[ValueChangeEvent[T]]): Subscription = {
    onChange(subscriber, false, listener)
  }

  def map[U](f: T => U): Rx[U] = new MappedRx(this, f)
}
