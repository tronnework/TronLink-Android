package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray16<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray16(List<T> list) {
        super(16, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray16(T... tArr) {
        super(16, tArr);
    }

    public StaticArray16(Class<T> cls, List<T> list) {
        super(cls, 16, list);
    }

    @SafeVarargs
    public StaticArray16(Class<T> cls, T... tArr) {
        super(cls, 16, tArr);
    }
}
