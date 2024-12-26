package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray24<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray24(List<T> list) {
        super(24, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray24(T... tArr) {
        super(24, tArr);
    }

    public StaticArray24(Class<T> cls, List<T> list) {
        super(cls, 24, list);
    }

    @SafeVarargs
    public StaticArray24(Class<T> cls, T... tArr) {
        super(cls, 24, tArr);
    }
}
