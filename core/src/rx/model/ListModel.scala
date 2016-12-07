package rx.model

import rx.{VarRx, Subscription, Var, VarList}
import java.util.{ArrayList => JavaArrayList, List => JavaList}

class ListModel[T <: FieldValue](initialValues: JavaList[T]) extends FieldValue {
  val list: VarList[T] = new VarList[T]

  override lazy val modified: VarRx[Boolean] = {
    def isModified = {
      list.getItems() == initialValues
    }
    val modified = Var(isModified)
    val subscriptions = new JavaArrayList[Subscription]()
    list.onChange(modified, e => {
      val i = e.removedItems.iterator()
      while (i.hasNext) {
        val subscription = subscriptions.get(e.removalPosition)
        subscription.end()
        subscriptions.remove(e.removalPosition)
        i.next()
      }
      var j = 0
      while (j < e.addedItems.size()) {
        val item = e.addedItems.get(j)
        val subscription = item.modified.onChange(modified, e => {
          if (e.value) {
            modified := true
          } else {
            modified := isModified
          }
        })
        subscriptions.add(e.additionPosition + j, subscription)
        j += 1
      }
    })
    modified
  }
}
