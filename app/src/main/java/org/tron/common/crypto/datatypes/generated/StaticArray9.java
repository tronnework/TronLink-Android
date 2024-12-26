package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray9<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray9(List<T> list) {
        super(9, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray9(T... tArr) {
        super(9, tArr);
    }

    public StaticArray9(Class<T> cls, List<T> list) {
        super(cls, 9, list);
    }

    @SafeVarargs
    public StaticArray9(Class<T> cls, T... tArr) {
        super(cls, 9, tArr);
    }
}
