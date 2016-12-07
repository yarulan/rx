package rx.model

import java.util.Arrays

import org.scalatest.{FunSpec, Matchers}

class ListModelTest extends FunSpec with Matchers {
  describe("ListModel") {
    describe("modified") {
      it("should work") {
        val field = new FieldModel("1")
        val model = new ListModel(Arrays.asList(field))
        model.modified() shouldBe false
        field.value := "2"
//        field.modified() shouldBe true
//        model.modified() shouldBe true
      }
    }
  }
}
