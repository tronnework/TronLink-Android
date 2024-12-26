package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
import org.tron.common.crypto.datatypes.Type;
public abstract class NumericType implements Type<BigInteger> {
    private String type;
    BigInteger value;

    @Override
    public int bytes32PaddedLength() {
        return Type.-CC.$default$bytes32PaddedLength(this);
    }

    public abstract int getBitSize();

    @Override
    public String getTypeAsString() {
        return this.type;
    }

    @Override
    public BigInteger getValue() {
        return this.value;
    }

    public NumericType(String str, BigInteger bigInteger) {
        this.type = str;
        this.value = bigInteger;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NumericType numericType = (NumericType) obj;
        if (this.type.equals(numericType.type)) {
            BigInteger bigInteger = this.value;
            BigInteger bigInteger2 = numericType.value;
            return bigInteger != null ? bigInteger.equals(bigInteger2) : bigInteger2 == null;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        BigInteger bigInteger = this.value;
        return hashCode + (bigInteger != null ? bigInteger.hashCode() : 0);
    }
}
