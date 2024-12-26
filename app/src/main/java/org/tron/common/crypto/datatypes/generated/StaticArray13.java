package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray13<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray13(List<T> list) {
        super(13, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray13(T... tArr) {
        super(13, tArr);
    }

    public StaticArray13(Class<T> cls, List<T> list) {
        super(cls, 13, list);
    }

    @SafeVarargs
    public StaticArray13(Class<T> cls, T... tArr) {
        super(cls, 13, tArr);
    }
}
