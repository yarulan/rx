package rx;

/**
 * This is in java for void return type so that Kotlin lambdas don't have to return Scala's unit or vice versa.
 */
public interface Listener<Event> {
    void eventHappened(Event event);
}