package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray26<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray26(List<T> list) {
        super(26, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray26(T... tArr) {
        super(26, tArr);
    }

    public StaticArray26(Class<T> cls, List<T> list) {
        super(cls, 26, list);
    }

    @SafeVarargs
    public StaticArray26(Class<T> cls, T... tArr) {
        super(cls, 26, tArr);
    }
}
