package rx.internal

import rx.{Rx, ValueChangeEvent}

class MappedRx[T, U](that: Rx[T], f: T => U) extends Rx[U] {
  value = f(that.getValueSilently)

  that.onChange { e =>
    val oldValue = value
    value = f(e.value)
    fireListeners(new ValueChangeEvent(value, oldValue))
  }
}