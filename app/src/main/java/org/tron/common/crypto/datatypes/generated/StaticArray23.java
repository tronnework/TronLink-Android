package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray23<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray23(List<T> list) {
        super(23, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray23(T... tArr) {
        super(23, tArr);
    }

    public StaticArray23(Class<T> cls, List<T> list) {
        super(cls, 23, list);
    }

    @SafeVarargs
    public StaticArray23(Class<T> cls, T... tArr) {
        super(cls, 23, tArr);
    }
}
