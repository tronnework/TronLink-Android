package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray28<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray28(List<T> list) {
        super(28, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray28(T... tArr) {
        super(28, tArr);
    }

    public StaticArray28(Class<T> cls, List<T> list) {
        super(cls, 28, list);
    }

    @SafeVarargs
    public StaticArray28(Class<T> cls, T... tArr) {
        super(cls, 28, tArr);
    }
}
