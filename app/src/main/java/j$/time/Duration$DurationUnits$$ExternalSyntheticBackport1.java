package j$.time;

import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public abstract class Duration$DurationUnits$ExternalSyntheticBackport1 {
    public static List m(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(Objects.requireNonNull(obj));
        }
        return Collections.unmodifiableList(arrayList);
    }
}
