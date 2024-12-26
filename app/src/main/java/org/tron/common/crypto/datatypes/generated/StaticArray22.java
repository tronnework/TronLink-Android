package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray22<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray22(List<T> list) {
        super(22, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray22(T... tArr) {
        super(22, tArr);
    }

    public StaticArray22(Class<T> cls, List<T> list) {
        super(cls, 22, list);
    }

    @SafeVarargs
    public StaticArray22(Class<T> cls, T... tArr) {
        super(cls, 22, tArr);
    }
}
