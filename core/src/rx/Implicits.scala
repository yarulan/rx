package rx

object Implicits extends Implicits

trait Implicits {
  implicit def anyToConst[T](x: T): Rx[T] = Const(x)
}

