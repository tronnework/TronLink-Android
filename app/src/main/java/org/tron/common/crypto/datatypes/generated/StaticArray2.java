package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray2<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray2(List<T> list) {
        super(2, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray2(T... tArr) {
        super(2, tArr);
    }

    public StaticArray2(Class<T> cls, List<T> list) {
        super(cls, 2, list);
    }

    @SafeVarargs
    public StaticArray2(Class<T> cls, T... tArr) {
        super(cls, 2, tArr);
    }
}
