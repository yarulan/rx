package rx;

import java.util.ArrayList;
import java.util.List;

/**
 * The initial idea was to have items added and removed events.
 * Items moved and replaced events could be expressed through combination of those.
 * But that's appeared to be inappropriate for some cases.
 * For instance if we have list view and details view,
 * when we select other item, list view selection list gets empty and than populated,
 * accordingly details view gets empty and populated causing flickering.
 *
 * This is in java for no-parentheses access to fields from non-scala code.
 */
public class ListChangeEvent<T> {
    public final int removalPosition;
    public final List<T> removedItems;
    public final int additionPosition;
    public final List<T> addedItems;

    public ListChangeEvent(int removalPosition, List<T> removedItems, int additionPosition, List<T> addedItems) {
        this.removalPosition = removalPosition;
        this.removedItems = removedItems;
        this.additionPosition = additionPosition;
        this.addedItems = addedItems;
    }

    public static <T> ListChangeEvent<T> added(int additionPosition, List<T> addedItems) {
        return new ListChangeEvent<T>(0, new ArrayList<T>(0), additionPosition, addedItems);
    }

    public static <T> ListChangeEvent<T> removed(int removalPosition, List<T> removedItems) {
        return new ListChangeEvent<T>(removalPosition, removedItems, 0, new ArrayList<T>(0));
    }
}