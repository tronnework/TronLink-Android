package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray20<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray20(List<T> list) {
        super(20, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray20(T... tArr) {
        super(20, tArr);
    }

    public StaticArray20(Class<T> cls, List<T> list) {
        super(cls, 20, list);
    }

    @SafeVarargs
    public StaticArray20(Class<T> cls, T... tArr) {
        super(cls, 20, tArr);
    }
}
