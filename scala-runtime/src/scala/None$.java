package scala;

import java.util.NoSuchElementException;

public class None$<T> extends Option<T> {
    public static None$ MODULE$ = new None$();

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public T get() {
        throw new NoSuchElementException("None.get");
    }
}
