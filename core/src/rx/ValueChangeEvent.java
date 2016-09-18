package rx;

import javax.annotation.Nonnull;

/**
 * This is in java for no-parentheses access to fields from non-scala code.
 */
public class ValueChangeEvent<T> {
    @Nonnull public final T value;
    @Nonnull public final T oldValue;

    public ValueChangeEvent(@Nonnull T value, @Nonnull T oldValue) {
        this.value = value;
        this.oldValue = oldValue;
    }
}