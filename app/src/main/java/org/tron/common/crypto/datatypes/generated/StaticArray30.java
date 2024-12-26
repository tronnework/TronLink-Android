package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray30<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray30(List<T> list) {
        super(30, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray30(T... tArr) {
        super(30, tArr);
    }

    public StaticArray30(Class<T> cls, List<T> list) {
        super(cls, 30, list);
    }

    @SafeVarargs
    public StaticArray30(Class<T> cls, T... tArr) {
        super(cls, 30, tArr);
    }
}
