package rx.internal

import rx.{Rx, ValueChangeEvent, VarRx}

class MappedRx[T, U](that: Rx[T], f: T => U) extends VarRx[U] {
  _value = f(that.value)

  that.onChange(this, e => {
    val oldValue = _value
    _value = f(e.value)
    fireListeners(ValueChangeEvent(_value, oldValue))
  })
}