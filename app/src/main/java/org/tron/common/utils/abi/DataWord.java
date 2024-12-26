package org.tron.common.utils.abi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.utils.ByteUtil;
public class DataWord implements Comparable<DataWord> {
    public static final BigInteger MAX_VALUE;
    public static final DataWord ZERO;
    public static final DataWord ZERO_EMPTY_ARRAY;
    public static final BigInteger _2_256;
    private byte[] data;

    public byte[] getData() {
        return this.data;
    }

    static {
        BigInteger pow = BigInteger.valueOf(2L).pow(256);
        _2_256 = pow;
        MAX_VALUE = pow.subtract(BigInteger.ONE);
        ZERO = new DataWord(new byte[32]);
        ZERO_EMPTY_ARRAY = new DataWord(new byte[0]);
    }

    public DataWord() {
        this.data = new byte[32];
    }

    public DataWord(int i) {
        this(ByteBuffer.allocate(4).putInt(i));
    }

    public DataWord(long j) {
        this(ByteBuffer.allocate(8).putLong(j));
    }

    private DataWord(ByteBuffer byteBuffer) {
        this.data = new byte[32];
        ByteBuffer allocate = ByteBuffer.allocate(32);
        byte[] array = byteBuffer.array();
        System.arraycopy(array, 0, allocate.array(), 32 - array.length, array.length);
        this.data = allocate.array();
    }

    @JsonCreator
    public DataWord(String str) {
        this(Hex.decode(str));
    }

    public DataWord(ByteArrayWrapper byteArrayWrapper) {
        this(byteArrayWrapper.getData());
    }

    public DataWord(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        this.data = bArr2;
        if (bArr == null) {
            this.data = ByteUtil.EMPTY_BYTE_ARRAY;
        } else if (bArr.length == 32) {
            this.data = bArr;
        } else if (bArr.length <= 32) {
            System.arraycopy(bArr, 0, bArr2, 32 - bArr.length, bArr.length);
        } else {
            throw new RuntimeException("Data word can't exceed 32 bytes: " + bArr);
        }
    }

    public byte[] getNoLeadZeroesData() {
        return ByteUtil.stripLeadingZeroes(this.data);
    }

    public byte[] getLast20Bytes() {
        byte[] bArr = this.data;
        return Arrays.copyOfRange(bArr, 12, bArr.length);
    }

    public BigInteger value() {
        return new BigInteger(1, this.data);
    }

    public int intValue() {
        int i = 0;
        for (byte b : this.data) {
            i = (i << 8) + (b & 255);
        }
        return i;
    }

    public int intValueSafe() {
        int bytesOccupied = bytesOccupied();
        int intValue = intValue();
        if (bytesOccupied > 4 || intValue < 0) {
            return Integer.MAX_VALUE;
        }
        return intValue;
    }

    public long longValue() {
        long j = 0;
        for (byte b : this.data) {
            j = (j << 8) + (b & 255);
        }
        return j;
    }

    public long longValueSafe() {
        int bytesOccupied = bytesOccupied();
        long longValue = longValue();
        if (bytesOccupied > 8 || longValue < 0) {
            return Long.MAX_VALUE;
        }
        return longValue;
    }

    public BigInteger ssValue() {
        return new BigInteger(this.data);
    }

    public String bigIntValue() {
        return new BigInteger(this.data).toString();
    }

    public boolean isZero() {
        for (byte b : this.data) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isNegative() {
        return (this.data[0] & 128) == 128;
    }

    public DataWord and(DataWord dataWord) {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            bArr[i] = (byte) (bArr[i] & dataWord.data[i]);
            i++;
        }
    }

    public DataWord or(DataWord dataWord) {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            bArr[i] = (byte) (bArr[i] | dataWord.data[i]);
            i++;
        }
    }

    public DataWord xor(DataWord dataWord) {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            bArr[i] = (byte) (bArr[i] ^ dataWord.data[i]);
            i++;
        }
    }

    public void negate() {
        byte[] bArr;
        if (isZero()) {
            return;
        }
        int i = 0;
        while (true) {
            bArr = this.data;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = (byte) (~bArr[i]);
            i++;
        }
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte[] bArr2 = this.data;
            byte b = (byte) ((bArr2[length] + 1) & 255);
            bArr2[length] = b;
            if (b != 0) {
                return;
            }
        }
    }

    public void bnot() {
        if (isZero()) {
            this.data = ByteUtil.copyToArray(MAX_VALUE);
        } else {
            this.data = ByteUtil.copyToArray(MAX_VALUE.subtract(value()));
        }
    }

    public void add(DataWord dataWord) {
        byte[] bArr = new byte[32];
        int i = 0;
        for (int i2 = 31; i2 >= 0; i2--) {
            int i3 = (this.data[i2] & 255) + (dataWord.data[i2] & 255) + i;
            bArr[i2] = (byte) i3;
            i = i3 >>> 8;
        }
        this.data = bArr;
    }

    public void add2(DataWord dataWord) {
        this.data = ByteUtil.copyToArray(value().add(dataWord.value()).and(MAX_VALUE));
    }

    public void mul(DataWord dataWord) {
        this.data = ByteUtil.copyToArray(value().multiply(dataWord.value()).and(MAX_VALUE));
    }

    public void div(DataWord dataWord) {
        if (dataWord.isZero()) {
            and(ZERO);
        } else {
            this.data = ByteUtil.copyToArray(value().divide(dataWord.value()).and(MAX_VALUE));
        }
    }

    public void ssDiv(DataWord dataWord) {
        if (dataWord.isZero()) {
            and(ZERO);
        } else {
            this.data = ByteUtil.copyToArray(ssValue().divide(dataWord.ssValue()).and(MAX_VALUE));
        }
    }

    public void sub(DataWord dataWord) {
        this.data = ByteUtil.copyToArray(value().subtract(dataWord.value()).and(MAX_VALUE));
    }

    public void exp(DataWord dataWord) {
        this.data = ByteUtil.copyToArray(value().modPow(dataWord.value(), _2_256));
    }

    public void mod(DataWord dataWord) {
        if (dataWord.isZero()) {
            and(ZERO);
        } else {
            this.data = ByteUtil.copyToArray(value().mod(dataWord.value()).and(MAX_VALUE));
        }
    }

    public void ssMod(DataWord dataWord) {
        if (dataWord.isZero()) {
            and(ZERO);
            return;
        }
        BigInteger mod = ssValue().abs().mod(dataWord.ssValue().abs());
        if (ssValue().signum() == -1) {
            mod = mod.negate();
        }
        this.data = ByteUtil.copyToArray(mod.and(MAX_VALUE));
    }

    public void addmod(DataWord dataWord, DataWord dataWord2) {
        if (dataWord2.isZero()) {
            this.data = new byte[32];
        } else {
            this.data = ByteUtil.copyToArray(value().add(dataWord.value()).mod(dataWord2.value()).and(MAX_VALUE));
        }
    }

    public void mulmod(DataWord dataWord, DataWord dataWord2) {
        if (isZero() || dataWord.isZero() || dataWord2.isZero()) {
            this.data = new byte[32];
        } else {
            this.data = ByteUtil.copyToArray(value().multiply(dataWord.value()).mod(dataWord2.value()).and(MAX_VALUE));
        }
    }

    @JsonValue
    public String toString() {
        return Hex.toHexString(this.data);
    }

    public String toPrefixString() {
        byte[] noLeadZeroesData = getNoLeadZeroesData();
        if (noLeadZeroesData.length == 0) {
            return "";
        }
        if (noLeadZeroesData.length < 7) {
            return Hex.toHexString(noLeadZeroesData);
        }
        return Hex.toHexString(noLeadZeroesData).substring(0, 6);
    }

    public String shortHex() {
        String upperCase = Hex.toHexString(getNoLeadZeroesData()).toUpperCase();
        return "0x" + upperCase.replaceFirst("^0+(?!$)", "");
    }

    public DataWord clone() {
        return new DataWord(Arrays.clone(this.data));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return java.util.Arrays.equals(this.data, ((DataWord) obj).data);
    }

    public int hashCode() {
        return java.util.Arrays.hashCode(this.data);
    }

    @Override
    public int compareTo(DataWord dataWord) {
        if (dataWord == null || dataWord.getData() == null) {
            return -1;
        }
        byte[] bArr = this.data;
        return (int) Math.signum(FastByteComparisons.compareTo(bArr, 0, bArr.length, dataWord.getData(), 0, dataWord.getData().length));
    }

    public void signExtend(byte b) {
        if (b >= 0) {
            if (b <= 31) {
                byte b2 = ssValue().testBit((b * 8) + 7) ? (byte) -1 : (byte) 0;
                for (int i = 31; i > b; i--) {
                    this.data[31 - i] = b2;
                }
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public int bytesOccupied() {
        int firstNonZeroByte = ByteUtil.firstNonZeroByte(this.data);
        if (firstNonZeroByte == -1) {
            return 0;
        }
        return 32 - firstNonZeroByte;
    }

    public boolean isHex(String str) {
        return Hex.toHexString(this.data).equals(str);
    }

    public String asString() {
        return new String(getNoLeadZeroesData());
    }

    public String toHexString() {
        return Hex.toHexString(this.data);
    }
}
