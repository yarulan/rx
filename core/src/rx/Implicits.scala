package rx

import scala.language.implicitConversions

object Implicits extends Implicits

trait Implicits {
  implicit def anyToConst[T](x: T): Rx[T] = Const(x)
  implicit def integralRxToOps[T : Integral](x: Rx[T]): IntegralRxOps[T] = new IntegralRxOps(x)
}