package rx

import org.scalatest.{FunSpec, Matchers}

class ExprTest extends FunSpec with Matchers {
  describe("Expr") {
    it("should be initialized") {
      val a = Var(2)
      val b = Var(3)
      val c = Expr {
        a() + b()
      }
      c() shouldBe 5
    }

    it("should be updated") {
      val a = Var(2)
      val b = Var(3)
      val c = Expr {
        a() + b()
      }
      a := 4
      c() shouldBe 7
    }

    it("should trigger change events") {
      val a = Var(2)
      val b = Expr { a() }
      var wasCalled = false
      b.onChange(this, e => {
        e.value shouldBe 4
        e.oldValue shouldBe 2
        wasCalled = true
      })
      a := 4
      wasCalled shouldBe true
    }

    it("more complex hierarchy") {
      val a = Var(2)
      val b = Var(2)
      val c = Var(2)
      val d = Var(2)
      val e = Expr(a() + b())
      val f = Expr(c() + d())
      val g = Expr(e() * f())
      g() shouldBe 16
    }
  }
}
