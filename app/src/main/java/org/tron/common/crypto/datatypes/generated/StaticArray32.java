package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray32<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray32(List<T> list) {
        super(32, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray32(T... tArr) {
        super(32, tArr);
    }

    public StaticArray32(Class<T> cls, List<T> list) {
        super(cls, 32, list);
    }

    @SafeVarargs
    public StaticArray32(Class<T> cls, T... tArr) {
        super(cls, 32, tArr);
    }
}
