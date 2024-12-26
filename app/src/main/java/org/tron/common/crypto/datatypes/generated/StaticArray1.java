package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray1<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray1(List<T> list) {
        super(1, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray1(T... tArr) {
        super(1, tArr);
    }

    public StaticArray1(Class<T> cls, List<T> list) {
        super(cls, 1, list);
    }

    @SafeVarargs
    public StaticArray1(Class<T> cls, T... tArr) {
        super(cls, 1, tArr);
    }
}
