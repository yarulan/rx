package rx.swing.examples.fontgen

import java.awt.Color
import javax.swing.JFrame

import rx._
import rx.Implicits._
import rx.swing._
import java.awt.Color._

object FontGenExample extends App {
  val frame = new Frame
  val a = ColorBox(width = 50, height = frame.height, color = blue)
  val b = ColorBox(x = 50, width = Expr(frame.width() - 50), height = frame.height, color = green)
  frame.component.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  println(frame.component.getLayout)
  frame.component.setLayout(null)
  frame.component.add(a.component)
  frame.component.add(b.component)
  frame.component.setSize(320, 240)
  frame.component.setVisible(true)
//  frame.components.add()
}
