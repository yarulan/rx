package rx.swing.examples.fontgen

import java.awt._
import javax.swing.{JFrame, JLabel, JPanel}

import rx._
import rx.Implicits._
import rx.swing._
import java.awt.Color._
import java.io.File
import javax.imageio.ImageIO

object FontGenExample extends App {
//  val frame = new Frame
//  val a = ColorBox(x = 0,  y = 0, width = 50,               height = frame.height, color = blue )
//  val b = ColorBox(x = 50, y = 0, width = frame.width - 50, height = frame.height, color = green)
//  frame.component.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
//  frame.component.setLayout(null)
//  frame.component.add(a.component)
//  frame.component.add(b.component)
//  frame.component.setSize(320, 240)
//  frame.component.setMinimumSize(new Dimension(320, 240))
//  frame.component.setMaximumSize(new Dimension(320, 240))
//  frame.component.setVisible(true)
  val font = GraphicsEnvironment.getLocalGraphicsEnvironment.getAllFonts()(0)
  val c = new CharPreviewComponent('g', font.deriveFont(14f))
  ImageIO.write(c.bitmap(), "bmp", new File("img.bmp"))
  val frame = new JFrame()
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frame.setLayout(new BorderLayout())
//  frame.add(new JLabel("x"), BorderLayout.WEST)
  frame.add(new FontListView().component, BorderLayout.WEST)
  frame.add(new JLabel("x"), BorderLayout.EAST)
  //  val panel = new JPanel
//  panel.setLayout(new FlowLayout())
//  frame.add(new JLabel("x"), BorderLayout.CENTER)
//  panel.add(new JLabel("x"))
//  for(ch <- 'A' to 'Z') {
//    panel.add(new CharPreviewComponent(ch, font.deriveFont(12f)).component)
//  }
  frame.setSize(480, 320)
  frame.setVisible(true)
}