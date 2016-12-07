package rx

case class ValueChangeEvent[T](value: T, oldValue: T)