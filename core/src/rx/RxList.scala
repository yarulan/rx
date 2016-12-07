package rx

import java.util.{ArrayList => JavaArrayList, List => JavaList}

import rx.internal.MappedList

/**
  * [[rx.RxList]] is a thing that holds a list of items and can notify others, when the list changes.
  */
abstract class RxList[T] extends PublisherImpl[ListChangeEvent[T]] {
  protected val list = new JavaArrayList[T]()

  def getItems(): JavaList[T] = {
    val javaList = new JavaArrayList[T]()
    javaList.addAll(list)
    javaList
  }

  def getItem(index: Int): T = {
    list.get(index)
  }

  def map[U](f: T => U): RxList[U] = new MappedList[T, U](this, f)

  lazy val size: VarRx[Integer] = {
    val size = Var(new Integer(list.size()))
    onChange(size, e => {
      size := new Integer(list.size())
    })
    size
  }

  lazy val first: VarRx[Option[T]] = {
    val first = Var(if (list.isEmpty) None else Some(list.get(0)))
    onChange(first, e => {
      first := (if (list.isEmpty) None else Some(list.get(0)))
    })
    first
  }

  protected def createInitialEvent(): ListChangeEvent[T] = {
    new ListChangeEvent(0, new JavaArrayList(0), 0, getItems())
  }
}