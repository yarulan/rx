package rx.swing

import java.awt.event.{ComponentAdapter, ComponentEvent}
import javax.swing.JFrame

import rx.{Var, VarList}

class Frame {
  val width = Var(0)
  val height = Var(0)

  val component = new JFrame()

  val components = new VarList[Component]()

//  components.onChange(this, e => {
//    for(c <- e.removedItems) {
//      component.remove(c)
//    }
//
//    for(c <- e.addedItems) {
//      component.add(c)
//    }
//  })

  component.getContentPane.addComponentListener(new ComponentAdapter {
    override def componentResized(e: ComponentEvent): Unit = {
      width := e.getComponent.getWidth
      height := e.getComponent.getHeight
    }
  })
}