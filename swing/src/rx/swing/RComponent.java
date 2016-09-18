package rx.swing;

public class RComponent<T> {
    public final T component;

    public RComponent(T component) {
        this.component = component;
    }
}