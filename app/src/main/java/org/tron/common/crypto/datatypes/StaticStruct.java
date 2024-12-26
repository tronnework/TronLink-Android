package org.tron.common.crypto.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class StaticStruct extends StaticArray<Type> implements StructType {
    private final List<Class<Type>> itemTypes;

    public StaticStruct(List<Type> list) {
        super(Type.class, list.size(), list);
        this.itemTypes = new ArrayList();
        for (Type type : list) {
            this.itemTypes.add(type.getClass());
        }
    }

    @SafeVarargs
    public StaticStruct(Type... typeArr) {
        this(Arrays.asList(typeArr));
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
