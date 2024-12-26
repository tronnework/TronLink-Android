package org.tron.common.crypto.datatypes;

import java.util.List;
import okhttp3.HttpUrl;
import org.tron.common.crypto.datatypes.Type;
public class DynamicArray<T extends Type> extends Array<T> {
    @java.lang.SafeVarargs
    @java.lang.Deprecated
    public DynamicArray(T... r4) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.common.crypto.datatypes.DynamicArray.<init>(org.tron.common.crypto.datatypes.Type[]):void");
    }

    @java.lang.Deprecated
    public DynamicArray(java.util.List<T> r4) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: org.tron.common.crypto.datatypes.DynamicArray.<init>(java.util.List):void");
    }

    @Deprecated
    private DynamicArray(String str) {
        super(AbiTypes.getType(str), new Type[0]);
    }

    @Deprecated
    public static DynamicArray empty(String str) {
        return new DynamicArray(str);
    }

    public DynamicArray(Class<T> cls, List<T> list) {
        super(cls, list);
    }

    @Override
    public int bytes32PaddedLength() {
        return super.bytes32PaddedLength() + 32;
    }

    @SafeVarargs
    public DynamicArray(Class<T> cls, T... tArr) {
        super(cls, tArr);
    }

    @Override
    public String getTypeAsString() {
        String typeAString;
        if (this.value.isEmpty()) {
            typeAString = AbiTypes.getTypeAString(getComponentType());
        } else if (StructType.class.isAssignableFrom(this.value.get(0).getClass())) {
            typeAString = this.value.get(0).getTypeAsString();
        } else {
            typeAString = AbiTypes.getTypeAString(getComponentType());
        }
        return typeAString + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
    }
}
