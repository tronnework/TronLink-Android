package org.tron.common.crypto.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DynamicStruct extends DynamicArray<Type> implements StructType {
    private final List<Class<Type>> itemTypes;

    public DynamicStruct(List<Type> list) {
        this(Type.class, list);
    }

    private DynamicStruct(Class<Type> cls, List<Type> list) {
        super(cls, list);
        this.itemTypes = new ArrayList();
        for (Type type : list) {
            this.itemTypes.add(type.getClass());
        }
    }

    @Override
    public int bytes32PaddedLength() {
        return super.bytes32PaddedLength() + 32;
    }

    public DynamicStruct(Type... typeArr) {
        this(Arrays.asList(typeArr));
    }

    @SafeVarargs
    public DynamicStruct(Class<Type> cls, Type... typeArr) {
        this(cls, Arrays.asList(typeArr));
    }

    @Override
    public String getTypeAsString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < this.itemTypes.size(); i++) {
            Class<Type> cls = this.itemTypes.get(i);
            if (StructType.class.isAssignableFrom(cls)) {
                sb.append(((Type) getValue().get(i)).getTypeAsString());
            } else {
                sb.append(AbiTypes.getTypeAString(cls));
            }
            if (i < this.itemTypes.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
