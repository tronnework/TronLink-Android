package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray10<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray10(List<T> list) {
        super(10, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray10(T... tArr) {
        super(10, tArr);
    }

    public StaticArray10(Class<T> cls, List<T> list) {
        super(cls, 10, list);
    }

    @SafeVarargs
    public StaticArray10(Class<T> cls, T... tArr) {
        super(cls, 10, tArr);
    }
}
