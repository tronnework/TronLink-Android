package org.tron.common.crypto.datatypes.generated;

import java.util.List;
import org.tron.common.crypto.datatypes.StaticArray;
import org.tron.common.crypto.datatypes.Type;
public class StaticArray25<T extends Type> extends StaticArray<T> {
    @Deprecated
    public StaticArray25(List<T> list) {
        super(25, list);
    }

    @SafeVarargs
    @Deprecated
    public StaticArray25(T... tArr) {
        super(25, tArr);
    }

    public StaticArray25(Class<T> cls, List<T> list) {
        super(cls, 25, list);
    }

    @SafeVarargs
    public StaticArray25(Class<T> cls, T... tArr) {
        super(cls, 25, tArr);
    }
}
