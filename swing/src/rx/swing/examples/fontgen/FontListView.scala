package rx.swing.examples.fontgen

import java.awt.{Component, Font, GraphicsEnvironment}
import javax.swing.{DefaultListCellRenderer, DefaultListModel, JList, ListCellRenderer}

class FontListView {
  val fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts
  val model = new DefaultListModel[Font]
  fonts.foreach(font => model.addElement(font))

  val component = new JList[Font]()
  component.setCellRenderer(X)
  component.setModel(model)

}

object X extends ListCellRenderer[Font] {
  private val delegate = new DefaultListCellRenderer
  private val xxx = delegate.asInstanceOf[ListCellRenderer[Font]]

  override def getListCellRendererComponent(list: JList[_ <: Font], value: Font, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component = {
    xxx.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
    val font = value.asInstanceOf[Font].deriveFont(32f)
    delegate.setFont(font)
    delegate.setText(s"${font.getFamily} - ${font.getFontName}")
    delegate
  }
}