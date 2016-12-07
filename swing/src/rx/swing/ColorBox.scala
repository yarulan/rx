package rx.swing

import java.awt.{Color, Graphics}
import javax.swing.JComponent

import rx.{Const, Rx, Var, VarRx}

object ColorBox {
  def apply(
      x: Rx[Int] = Const(0)
    , y: Rx[Int] = Const(0)
    , width: Rx[Int] = Const(0)
    , height: Rx[Int] = Const(0)
    , color: Rx[Color] = Const(Color.white)
  ): ColorBox = {
    val box = new ColorBox
    box.x := x
    box.y := y
    box.width := width
    box.height := height
    box.color := color
    box
  }
}

class ColorBox extends Component {
  val color = Var(Color.BLUE)

  override val component = new JComponent {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)
      g.setColor(color.value)
      g.fillRect(0, 0, getWidth, getHeight)
    }
  }
}