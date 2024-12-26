package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray14<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray14(List<T> list) {
        super(14, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray14(T... tArr) {
        super(14, tArr);
    }

    public StaticArray14(Class<T> cls, List<T> list) {
        super(cls, 14, list);
    }

    @SafeVarargs
    public StaticArray14(Class<T> cls, T... tArr) {
        super(cls, 14, tArr);
    }
}
