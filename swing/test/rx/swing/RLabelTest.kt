package rx.swing

import org.junit.Test
import rx.Var

class RLabelTest {
    @Test
    fun `Label must be initialized`() {
        val s = Var("Hello world!")
        val label = RLabel(s)
        assert(label.component.text == "Hello world!");
    }

    @Test
    fun `Label must react on changes`() {
        val s = Var("Hello world!")
        val label = RLabel(s)
        s("Bye world!")
        assert(label.component.text == "Bye world!");
    }
}