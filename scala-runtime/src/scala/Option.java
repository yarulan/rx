package scala;

public abstract class Option<A> {
    public abstract boolean isEmpty();

    public abstract A get();

    public <B> Option<B> map(Function1<A, B> f) {
        return isEmpty() ? None$.MODULE$ : new Some<>(f.apply(this.get()));
    }

    public <B extends A> A getOrElse(Function0<B> f) {
        return isEmpty() ? f.apply() : this.get();
    }
}
