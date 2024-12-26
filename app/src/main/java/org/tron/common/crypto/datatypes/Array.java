package org.tron.common.crypto.datatypes;

import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.tron.common.crypto.datatypes.Type;
public abstract class Array<T extends Type> implements Type<List<T>> {
    private final Class<T> type;
    protected final List<T> value;

    public Class<T> getComponentType() {
        return this.type;
    }

    @Override
    public abstract String getTypeAsString();

    @Override
    public List<T> getValue() {
        return this.value;
    }

    @SafeVarargs
    @Deprecated
    Array(String str, T... tArr) {
        this(str, Arrays.asList(tArr));
    }

    @Deprecated
    Array(String str, List<T> list) {
        this(AbiTypes.getType(str), list);
    }

    @Deprecated
    Array(String str) {
        this(str, new ArrayList());
    }

    @SafeVarargs
    public Array(Class<T> cls, T... tArr) {
        this(cls, Arrays.asList(tArr));
    }

    public Array(Class<T> cls, List<T> list) {
        checkValid(cls, list);
        this.type = cls;
        this.value = list;
    }

    @Override
    public int bytes32PaddedLength() {
        int i = 0;
        for (T t : this.value) {
            i += t.bytes32PaddedLength();
        }
        return i;
    }

    public List<Object> getNativeValueCopy() {
        ArrayList arrayList = new ArrayList(this.value.size());
        for (T t : this.value) {
            arrayList.add(t.getValue());
        }
        return Collections.unmodifiableList(arrayList);
    }

    private void checkValid(Class<T> cls, List<T> list) {
        Objects.requireNonNull(cls);
        Objects.requireNonNull(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Array array = (Array) obj;
        if (this.type.equals(array.type)) {
            return Objects.equals(this.value, array.value);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        List<T> list = this.value;
        return hashCode + (list != null ? list.hashCode() : 0);
    }
}
