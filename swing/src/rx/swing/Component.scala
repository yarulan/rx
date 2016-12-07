package rx.swing

import javax.swing.JComponent

import rx.Var

trait Component {
  val component: JComponent

  val x = Var(0)
  val y = Var(0)
  val width = Var(0)
  val height = Var(0)

  x.onChange(this, e => updateBounds())
  y.onChange(this, e => updateBounds())
  width.onChange(this, e => updateBounds())
  height.onChange(this, e => updateBounds())

  private def updateBounds(): Unit = {
    println(s"${this}.updateBounds", x.value, y.value, width.value, height.value)
    component.setBounds(x.value, y.value, width.value, height.value)
  }
}