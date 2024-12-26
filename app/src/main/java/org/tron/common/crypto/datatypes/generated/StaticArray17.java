package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray17<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray17(List<T> list) {
        super(17, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray17(T... tArr) {
        super(17, tArr);
    }

    public StaticArray17(Class<T> cls, List<T> list) {
        super(cls, 17, list);
    }

    @SafeVarargs
    public StaticArray17(Class<T> cls, T... tArr) {
        super(cls, 17, tArr);
    }
}
