package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray21<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray21(List<T> list) {
        super(21, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray21(T... tArr) {
        super(21, tArr);
    }

    public StaticArray21(Class<T> cls, List<T> list) {
        super(cls, 21, list);
    }

    @SafeVarargs
    public StaticArray21(Class<T> cls, T... tArr) {
        super(cls, 21, tArr);
    }
}
