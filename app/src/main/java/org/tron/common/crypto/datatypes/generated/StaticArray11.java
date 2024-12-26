package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray11<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray11(List<T> list) {
        super(11, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray11(T... tArr) {
        super(11, tArr);
    }

    public StaticArray11(Class<T> cls, List<T> list) {
        super(cls, 11, list);
    }

    @SafeVarargs
    public StaticArray11(Class<T> cls, T... tArr) {
        super(cls, 11, tArr);
    }
}
