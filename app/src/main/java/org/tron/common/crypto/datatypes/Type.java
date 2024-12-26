package org.tron.common.crypto.datatypes;
public interface Type<T> {
    public static final int MAX_BIT_LENGTH = 256;
    public static final int MAX_BYTE_LENGTH = 32;

    public final class -CC {
        public static int $default$bytes32PaddedLength(Type _this) {
            return 32;
        }
    }

    int bytes32PaddedLength();

    String getTypeAsString();

    T getValue();
}
