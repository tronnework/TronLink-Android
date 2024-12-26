package com.jakewharton.rxrelay2;

import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.functions.Consumer;
public abstract class Relay<T> extends Observable<T> implements Consumer<T> {
    public abstract void accept(T t);

    public abstract boolean hasObservers();

    @CheckReturnValue
    public final Relay<T> toSerialized() {
        return this instanceof SerializedRelay ? this : new SerializedRelay(this);
    }
}
