package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray6<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray6(List<T> list) {
        super(6, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray6(T... tArr) {
        super(6, tArr);
    }

    public StaticArray6(Class<T> cls, List<T> list) {
        super(cls, 6, list);
    }

    @SafeVarargs
    public StaticArray6(Class<T> cls, T... tArr) {
        super(cls, 6, tArr);
    }
}
