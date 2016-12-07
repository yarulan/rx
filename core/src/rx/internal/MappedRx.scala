package rx.internal

import rx.{VarRx, ValueChangeEvent}

class MappedRx[T, U](that: VarRx[T], f: T => U) extends VarRx[U] {
  _value = f(that.value)

  that.onChange(this, e => {
    val oldValue = _value
    _value = f(e.value)
    fireListeners(new ValueChangeEvent(_value, oldValue))
  })
}