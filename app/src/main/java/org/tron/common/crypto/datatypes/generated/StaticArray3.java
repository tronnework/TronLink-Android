package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray3<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray3(List<T> list) {
        super(3, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray3(T... tArr) {
        super(3, tArr);
    }

    public StaticArray3(Class<T> cls, List<T> list) {
        super(cls, 3, list);
    }

    @SafeVarargs
    public StaticArray3(Class<T> cls, T... tArr) {
        super(cls, 3, tArr);
    }
}
