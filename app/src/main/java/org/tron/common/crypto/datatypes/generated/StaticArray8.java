package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray8<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray8(List<T> list) {
        super(8, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray8(T... tArr) {
        super(8, tArr);
    }

    public StaticArray8(Class<T> cls, List<T> list) {
        super(cls, 8, list);
    }

    @SafeVarargs
    public StaticArray8(Class<T> cls, T... tArr) {
        super(cls, 8, tArr);
    }
}
