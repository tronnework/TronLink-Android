package org.tron.common.crypto.datatypes.primitive;

import j$.util.Objects;
import java.io.Serializable;
import java.lang.Comparable;
import org.tron.common.crypto.datatypes.Type;
public abstract class PrimitiveType<T extends Serializable & Comparable<T>> implements Type<T> {
    private final String type = getClass().getSimpleName().toLowerCase();
    private final T value;

    @Override
    public int bytes32PaddedLength() {
        return Type.-CC.$default$bytes32PaddedLength(this);
    }

    @Override
    public String getTypeAsString() {
        return this.type;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    public abstract Type toSolidityType();

    public PrimitiveType(T t) {
        this.value = t;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrimitiveType primitiveType = (PrimitiveType) obj;
        return this.type.equals(primitiveType.type) && this.value.equals(primitiveType.value);
    }

    public int hashCode() {
        return Objects.hash(this.type, this.value);
    }
}
