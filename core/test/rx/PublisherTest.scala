package rx

import org.scalatest.{FunSpec, Matchers}

class PublisherTest extends FunSpec with Matchers {
  class TestPublisher extends PublisherImpl[Unit] {
    def fire(): Unit = {
      fireListeners(())
    }
  }

  describe("Publisher") {
    it("should cleanup listeners") {
      val publisher = new TestPublisher

      var listener1WasCalled = false
      var listener2WasCalled = false

      publisher.onChange(new Object, _ => { listener1WasCalled = true })
      publisher.onChange(this, _ => { listener2WasCalled = true })

      System.gc()

      publisher.fire()

      listener1WasCalled shouldBe false
      listener2WasCalled shouldBe true
    }

    it("should stop fire listener on subscription end") {
      val publisher = new TestPublisher

      var listenerWasCalled = false

      val subscription = publisher.onChange(this, _ => { listenerWasCalled = true })

      subscription.end()

      publisher.fire()

      listenerWasCalled shouldBe false
    }
  }
}