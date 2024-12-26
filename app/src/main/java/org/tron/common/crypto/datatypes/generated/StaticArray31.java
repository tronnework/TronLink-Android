package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray31<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray31(List<T> list) {
        super(31, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray31(T... tArr) {
        super(31, tArr);
    }

    public StaticArray31(Class<T> cls, List<T> list) {
        super(cls, 31, list);
    }

    @SafeVarargs
    public StaticArray31(Class<T> cls, T... tArr) {
        super(cls, 31, tArr);
    }
}
