package rx

import org.scalatest.{FunSpec, FunSuite, Matchers}

class VarTest extends FunSpec with Matchers {
  describe("Var") {
    it("should be initialized") {
      val a = Var(2)
      a() shouldBe 2
    }

    it("can be updated") {
      val a = Var(2)
      a := 4
      a() shouldBe 4
    }

    it("should trigger change events") {
      val a = Var(2)
      var wasCalled = false
      a.onChange(this, e => {
        e.value shouldBe 4
        e.oldValue shouldBe 2
        wasCalled = true
      })
      a := 4
      wasCalled shouldBe true
    }
  }
}