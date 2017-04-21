package rx.swing.examples.fontgen

import java.awt._
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent

import rx.{Expr, Rx, Var}
import rx.Implicits._

object CharPreviewComponent {
  private val graphics = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_BINARY).createGraphics()
}

class CharPreviewComponent(char: Rx[Char], font: Rx[Font]) {
  val metrics = font.map(CharPreviewComponent.graphics.getFontMetrics)

  val charWidth = Expr(metrics().charWidth(char()))

  val charHeight = Expr(metrics().getAscent + metrics().getDescent)

  val bitmap = Expr {
    val img = new BufferedImage(charWidth(), charHeight(), BufferedImage.TYPE_BYTE_BINARY)
    val g = img.createGraphics()
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, img.getWidth, img.getHeight())
    g.setColor(Color.BLACK)
    g.setFont(font())
    g.drawChars(Array(char()), 0, 1, 0, metrics().getAscent)
    img
  }

  val component = new JComponent {
    setPreferredSize(new Dimension(30, 20))
    override def paintComponent(g: Graphics): Unit = {
      g.drawImage(bitmap(), 0, 0, null)
    }
  }
}
