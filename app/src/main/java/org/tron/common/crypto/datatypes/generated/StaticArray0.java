package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray0<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray0(List<T> list) {
        super(0, new Type[0]);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray0(T... tArr) {
        super(0, tArr);
    }

    public StaticArray0(Class<T> cls, List<T> list) {
        super(cls, 0, list);
    }

    @SafeVarargs
    public StaticArray0(Class<T> cls, T... tArr) {
        super(cls, 0, tArr);
    }
}
