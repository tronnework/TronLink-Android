package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray5<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray5(List<T> list) {
        super(5, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray5(T... tArr) {
        super(5, tArr);
    }

    public StaticArray5(Class<T> cls, List<T> list) {
        super(cls, 5, list);
    }

    @SafeVarargs
    public StaticArray5(Class<T> cls, T... tArr) {
        super(cls, 5, tArr);
    }
}
