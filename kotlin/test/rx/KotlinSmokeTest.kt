package rx

import org.junit.Test

class KotlinSmokeTest {
    @Test
    fun name() {
        val a = Var(2)
        val b = Expr { a() }
        var wasCalled = false
        b.onChange(this, { e ->
            assert(e.value == 4)
            assert(e.oldValue == 2)
            wasCalled = true
        })
        a(4)
        assert(wasCalled)
    }
}