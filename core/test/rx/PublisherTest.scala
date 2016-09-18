package rx

import javax.annotation.Nonnull

import org.scalatest.{FunSpec, Matchers}

class PublisherTest extends FunSpec with Matchers {
  describe("Publisher") {
    it("should cleanup listeners") {
      val publisher = new Publisher[Unit] {
        @Nonnull override protected def createInitialEvent(): Unit = ()
      }

      var listener1WasCalled = false
      var listener2WasCalled = false

      publisher.onChange(new Object, _ => { listener1WasCalled = true })
      publisher.onChange(this, _ => { listener2WasCalled = true })

      System.gc()
      Thread.sleep(100)

      publisher.fireListeners()

      listener1WasCalled shouldBe false
      listener2WasCalled shouldBe true
    }
  }
}