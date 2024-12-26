package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray7<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray7(List<T> list) {
        super(7, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray7(T... tArr) {
        super(7, tArr);
    }

    public StaticArray7(Class<T> cls, List<T> list) {
        super(cls, 7, list);
    }

    @SafeVarargs
    public StaticArray7(Class<T> cls, T... tArr) {
        super(cls, 7, tArr);
    }
}
