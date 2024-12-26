package org.tron.common.crypto.datatypes.primitive;

import org.tron.common.crypto.datatypes.Type;
import org.tron.common.crypto.datatypes.Utf8String;
public final class Char extends PrimitiveType<Character> {
    public Char(char c) {
        super(Character.valueOf(c));
    }

    @Override
    public Type toSolidityType() {
        return new Utf8String(String.valueOf(getValue()));
    }
}
