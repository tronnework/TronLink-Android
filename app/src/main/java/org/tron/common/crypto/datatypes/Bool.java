package org.tron.common.crypto.datatypes;

import org.tron.common.crypto.datatypes.Type;
public class Bool implements Type<Boolean> {
    public static final Bool DEFAULT = new Bool(false);
    public static final String TYPE_NAME = "bool";
    private boolean value;

    @Override
    public int bytes32PaddedLength() {
        return Type.-CC.$default$bytes32PaddedLength(this);
    }

    @Override
    public String getTypeAsString() {
        return TYPE_NAME;
    }

    public int hashCode() {
        return this.value ? 1 : 0;
    }

    public Bool(boolean z) {
        this.value = z;
    }

    public Bool(Boolean bool) {
        this.value = bool.booleanValue();
    }

    @Override
    public Boolean getValue() {
        return Boolean.valueOf(this.value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.value == ((Bool) obj).value;
    }
}
