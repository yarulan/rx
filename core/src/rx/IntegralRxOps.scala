package rx

class IntegralRxOps[T](x: Rx[T])(implicit ops: Integral[T]) {
  def +(y: Rx[T]): Rx[T] = Expr(ops.plus(x(), y()))
  def -(y: Rx[T]): Rx[T] = Expr(ops.minus(x(), y()))
  def *(y: Rx[T]): Rx[T] = Expr(ops.times(x(), y()))
  def /(y: Rx[T]): Rx[T] = Expr(ops.quot(x(), y()))
  def %(y: Rx[T]): Rx[T] = Expr(ops.rem(x(), y()))
}