package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray4<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray4(List<T> list) {
        super(4, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray4(T... tArr) {
        super(4, tArr);
    }

    public StaticArray4(Class<T> cls, List<T> list) {
        super(cls, 4, list);
    }

    @SafeVarargs
    public StaticArray4(Class<T> cls, T... tArr) {
        super(cls, 4, tArr);
    }
}
