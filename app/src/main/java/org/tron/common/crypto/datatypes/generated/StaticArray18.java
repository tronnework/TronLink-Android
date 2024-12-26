package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray18<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray18(List<T> list) {
        super(18, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray18(T... tArr) {
        super(18, tArr);
    }

    public StaticArray18(Class<T> cls, List<T> list) {
        super(cls, 18, list);
    }

    @SafeVarargs
    public StaticArray18(Class<T> cls, T... tArr) {
        super(cls, 18, tArr);
    }
}
