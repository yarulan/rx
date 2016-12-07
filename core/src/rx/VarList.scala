package rx

import java.util.{ArrayList => JavaArrayList, List => JavaList}

class VarList[T] extends RxList[T] {
  def insert(position: Int, items: JavaList[T]): Unit = {
    list.addAll(position, items)
    fireListeners(ListChangeEvent.added(position, items))
  }

  def add(items: JavaList[T]): Unit = {
    insert(list.size(), items)
  }

  def add(item: T): Unit = {
    val items = new JavaArrayList[T](1)
    items.add(item)
    add(items)
  }

  def clear(): Unit = {
    val oldItems = getItems()
    list.clear()
    fireListeners(ListChangeEvent.removed(0, oldItems))
  }

  def replace(newItems: JavaList[T]): Unit = {
    val oldItems = getItems()
    list.clear()
    list.addAll(newItems)
    fireListeners(new ListChangeEvent[T](0, oldItems, 0, newItems))
  }

  def remove(items: JavaList[T]): Unit = {
    var i = 0
    while (i < items.size()) {
      remove(items.get(i))
      i += 1
    }
  }

  def remove(item: T): Unit = {
    val index = list.indexOf(item)
    list.remove(index)
    val items = new JavaArrayList[T](1)
    items.add(item)
    fireListeners(ListChangeEvent.removed(index, items))
  }
}