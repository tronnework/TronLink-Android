package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray29<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray29(List<T> list) {
        super(29, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray29(T... tArr) {
        super(29, tArr);
    }

    public StaticArray29(Class<T> cls, List<T> list) {
        super(cls, 29, list);
    }

    @SafeVarargs
    public StaticArray29(Class<T> cls, T... tArr) {
        super(cls, 29, tArr);
    }
}
