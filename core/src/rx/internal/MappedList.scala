package rx.internal

import rx.{RxList, VarList}

class MappedList[T, U](list: RxList[T], f: T => U) extends VarList[U] {
  ???
//  list.onChange(true, e => {
//    if (e.added) {
//      val items = new java.util.ArrayList[U]()
//      var i = 0
//      while(i < e.items.size()) {
//        items.add(f(e.items.get(i)))
//        i += 1
//      }
//      insert(e.position, items)
//    }
//  })
}