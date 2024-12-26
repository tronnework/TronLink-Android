package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray12<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray12(List<T> list) {
        super(12, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray12(T... tArr) {
        super(12, tArr);
    }

    public StaticArray12(Class<T> cls, List<T> list) {
        super(cls, 12, list);
    }

    @SafeVarargs
    public StaticArray12(Class<T> cls, T... tArr) {
        super(cls, 12, tArr);
    }
}
