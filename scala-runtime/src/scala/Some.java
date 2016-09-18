package scala;

public class Some<T> extends Option<T> {
    private final T value;

    public Some(T value) {
        this.value = value;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return "Some(" + value.toString() + ")";
    }
}