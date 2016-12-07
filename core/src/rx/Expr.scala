package rx

import java.util.{ArrayList => JavaArrayList, List => JavaList}

class Expr[T](val evalFun: () => T) extends VarRx[T] {
  private val subscriptions = new JavaArrayList[Subscription]()

  evaluateValue()

  private def unsubscribeFromAll(): Unit = {
    val iterator = subscriptions.iterator()
    while(iterator.hasNext) {
      val subscription = iterator.next()
      subscription.end()
    }
    subscriptions.clear()
  }

  private def evaluateValue(): Unit = {
    unsubscribeFromAll()
    val oldEnclosingExpr = Expr.enclosingExpr.get()
    Expr.enclosingExpr.set(this)
    val oldValue = _value
    _value = evalFun()
    Expr.enclosingExpr.set(oldEnclosingExpr)
    fireListeners(new ValueChangeEvent[T](_value, oldValue))
  }

  private[rx] def subscribeTo[U](rx: VarRx[U]) {
    val subscription = rx.onChange(this , _ => {
      evaluateValue()
    })
    subscriptions.add(subscription)
  }
}

object Expr {
  val enclosingExpr = new ThreadLocal[Expr[_]]

  /**
    * Scala API.
    */
  def apply[T](f: => T): Expr[T] = new Expr[T](f _)

  /**
    * Java API.
    */
  def of[T](evalFun: () => T): Expr[T] = new Expr[T](evalFun)
}