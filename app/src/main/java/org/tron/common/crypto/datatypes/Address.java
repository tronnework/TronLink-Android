package org.tron.common.crypto.datatypes;

import java.math.BigInteger;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.datatypes.Type;
import org.tron.walletserver.AddressUtil;
public class Address implements Type<String> {
    public static final Address DEFAULT = new Address(BigInteger.ZERO);
    public static final int DEFAULT_LENGTH = 160;
    public static final String TYPE_NAME = "address";
    private final Uint value;

    @Override
    public int bytes32PaddedLength() {
        return Type.-CC.$default$bytes32PaddedLength(this);
    }

    @Override
    public String getTypeAsString() {
        return "address";
    }

    public Uint toUint() {
        return this.value;
    }

    public Address(Uint uint) {
        this.value = uint;
    }

    public Address(BigInteger bigInteger) {
        this(160, bigInteger);
    }

    public Address(int i, BigInteger bigInteger) {
        this(new Uint(i, bigInteger));
    }

    public Address(String str) {
        this(160, AddressUtil.replace41Address(str));
    }

    public Address(String str, boolean z) {
        this(160, z ? AddressUtil.replace41Address(str) : str);
    }

    public Address(int i, String str) {
        this(i, Numeric.toBigInt(str));
    }

    public String toString() {
        return Numeric.toHexStringWithPrefixZeroPadded(this.value.getValue(), this.value.getBitSize() >> 2);
    }

    @Override
    public String getValue() {
        return toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Address address = (Address) obj;
        Uint uint = this.value;
        if (uint != null) {
            return uint.value.equals(address.value.value);
        }
        return address.value == null;
    }

    public int hashCode() {
        Uint uint = this.value;
        if (uint != null) {
            return uint.hashCode();
        }
        return 0;
    }
}
