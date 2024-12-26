package com.jakewharton.rxrelay2;

import io.reactivex.functions.Predicate;
public class AppendOnlyLinkedArrayList<T> {
    private final int capacity;
    private final Object[] head;
    private int offset;
    private Object[] tail;

    public interface NonThrowingPredicate<T> extends Predicate<T> {
        @Override
        boolean test(T t);
    }

    public AppendOnlyLinkedArrayList(int i) {
        this.capacity = i;
        Object[] objArr = new Object[i + 1];
        this.head = objArr;
        this.tail = objArr;
    }

    public void add(T t) {
        int i = this.capacity;
        int i2 = this.offset;
        if (i2 == i) {
            Object[] objArr = new Object[i + 1];
            this.tail[i] = objArr;
            this.tail = objArr;
            i2 = 0;
        }
        this.tail[i2] = t;
        this.offset = i2 + 1;
    }

    public void forEachWhile(NonThrowingPredicate<? super T> nonThrowingPredicate) {
        int i;
        int i2 = this.capacity;
        for (Object[] objArr = this.head; objArr != null; objArr = objArr[i2]) {
            while (i < i2) {
                Object obj = objArr[i];
                i = (obj == null || nonThrowingPredicate.test(obj)) ? 0 : i + 1;
            }
        }
    }

    public void accept(Relay<? super T> relay) {
        int i = this.capacity;
        for (Object[] objArr = this.head; objArr != null; objArr = objArr[i]) {
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[i2];
                if (obj == null) {
                    break;
                }
                relay.accept(obj);
            }
        }
    }
}
