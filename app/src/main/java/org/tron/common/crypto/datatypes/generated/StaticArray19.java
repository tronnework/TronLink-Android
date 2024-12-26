package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray19<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray19(List<T> list) {
        super(19, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray19(T... tArr) {
        super(19, tArr);
    }

    public StaticArray19(Class<T> cls, List<T> list) {
        super(cls, 19, list);
    }

    @SafeVarargs
    public StaticArray19(Class<T> cls, T... tArr) {
        super(cls, 19, tArr);
    }
}
