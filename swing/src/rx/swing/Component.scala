package rx.swing

import javax.swing.JComponent

import rx.Var

trait Component {
  val component: JComponent

  val x = Var(0)
  val y = Var(0)
  val width = Var(0)
  val height = Var(0)

  x.onChange(this, _ => updateBounds())
  y.onChange(this, _ => updateBounds())
  width.onChange(this, _ => updateBounds())
  height.onChange(this, _ => updateBounds())

  private def updateBounds(): Unit = {
    println(s"${this}.updateBounds", x.value, y.value, width.value, height.value)
    component.setBounds(x.value, y.value, width.value, height.value)
  }
}

