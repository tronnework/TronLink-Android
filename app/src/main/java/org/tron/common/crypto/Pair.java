package org.tron.common.crypto;
public class Pair {
    private final Object first;
    private final Object second;

    public Object getFirst() {
        return this.first;
    }

    public Object getSecond() {
        return this.second;
    }

    public Pair(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }
}
