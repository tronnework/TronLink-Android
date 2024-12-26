package org.tron.common.crypto.datatypes;
public class Utf8String implements Type<String> {
    public static final Utf8String DEFAULT = new Utf8String("");
    public static final String TYPE_NAME = "string";
    private String value;

    @Override
    public String getTypeAsString() {
        return TYPE_NAME;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    public Utf8String(String str) {
        this.value = str;
    }

    @Override
    public int bytes32PaddedLength() {
        return this.value.isEmpty() ? 32 : 64;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.value;
        String str2 = ((Utf8String) obj).value;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        String str = this.value;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
