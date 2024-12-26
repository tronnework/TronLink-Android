package org.tron.common.crypto.datatypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.tron.common.crypto.datatypes.Type;
public abstract class StaticArray<T extends Type> extends Array<T> {
    public static final int MAX_SIZE_OF_STATIC_ARRAY = 32;

    @SafeVarargs
    @Deprecated
    public StaticArray(T... tArr) {
        this(tArr.length, tArr);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray(int i, T... tArr) {
        this(i, Arrays.asList(tArr));
    }

    @Deprecated
    public StaticArray(List<T> list) {
        this(list.size(), list);
    }

    @java.lang.Deprecated
    public StaticArray(int r4, java.util.List<T> r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.common.crypto.datatypes.StaticArray.<init>(int, java.util.List):void");
    }

    @SafeVarargs
    public StaticArray(Class<T> cls, T... tArr) {
        this(cls, Arrays.asList(tArr));
    }

    @SafeVarargs
    public StaticArray(Class<T> cls, int i, T... tArr) {
        this(cls, i, Arrays.asList(tArr));
    }

    public StaticArray(Class<T> cls, List<T> list) {
        this(cls, list == null ? 0 : list.size(), list);
    }

    public StaticArray(Class<T> cls, int i, List<T> list) {
        super(cls, list);
        checkValid(i);
    }

    @Override
    public List<T> getValue() {
        return Collections.unmodifiableList(this.value);
    }

    @Override
    public String getTypeAsString() {
        String typeAString;
        if (!this.value.isEmpty() && StructType.class.isAssignableFrom(this.value.get(0).getClass())) {
            typeAString = this.value.get(0).getTypeAsString();
        } else {
            typeAString = AbiTypes.getTypeAString(getComponentType());
        }
        return typeAString + "[" + this.value.size() + "]";
    }

    private void checkValid(int i) {
        if (this.value.size() > 32) {
            


return;

//throw new UnsupportedOperationException(
Static arrays with a length greater than 32 are not supported.");
        }
        if (this.value.size() == i) {
            return;
        }
        


return null;

//throw new UnsupportedOperationException(
Expected array of type [" + getClass().getSimpleName() + "] to have [" + i + "] elements.");
    }
}
