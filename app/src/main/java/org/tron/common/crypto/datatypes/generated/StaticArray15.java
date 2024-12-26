package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray15<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray15(List<T> list) {
        super(15, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray15(T... tArr) {
        super(15, tArr);
    }

    public StaticArray15(Class<T> cls, List<T> list) {
        super(cls, 15, list);
    }

    @SafeVarargs
    public StaticArray15(Class<T> cls, T... tArr) {
        super(cls, 15, tArr);
    }
}
