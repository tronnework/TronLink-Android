package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray27<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray27(List<T> list) {
        super(27, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray27(T... tArr) {
        super(27, tArr);
    }

    public StaticArray27(Class<T> cls, List<T> list) {
        super(cls, 27, list);
    }

    @SafeVarargs
    public StaticArray27(Class<T> cls, T... tArr) {
        super(cls, 27, tArr);
    }
}
