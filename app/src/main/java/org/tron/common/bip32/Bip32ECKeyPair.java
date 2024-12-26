package org.tron.common.bip32;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.bouncycastle.math.ec.ECPoint;
import org.tron.common.crypto.Hash;
public class Bip32ECKeyPair extends ECKeyPair {
    public static final int HARDENED_BIT = Integer.MIN_VALUE;
    private final byte[] chainCode;
    private final int childNumber;
    private final int depth;
    private int parentFingerprint;
    private final boolean parentHasPrivate;
    private ECPoint publicKeyPoint;

    private static boolean isHardened(int i) {
        return (i & Integer.MIN_VALUE) != 0;
    }

    public byte[] getChainCode() {
        return this.chainCode;
    }

    public int getChildNumber() {
        return this.childNumber;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getParentFingerprint() {
        return this.parentFingerprint;
    }

    public Bip32ECKeyPair(BigInteger bigInteger, BigInteger bigInteger2, int i, byte[] bArr, Bip32ECKeyPair bip32ECKeyPair) {
        super(bigInteger, bigInteger2);
        this.parentHasPrivate = bip32ECKeyPair != null && bip32ECKeyPair.hasPrivateKey();
        this.childNumber = i;
        this.depth = bip32ECKeyPair == null ? 0 : bip32ECKeyPair.depth + 1;
        this.chainCode = Arrays.copyOf(bArr, bArr.length);
        this.parentFingerprint = bip32ECKeyPair != null ? bip32ECKeyPair.getFingerprint() : 0;
    }

    public static Bip32ECKeyPair create(BigInteger bigInteger, byte[] bArr) {
        return new Bip32ECKeyPair(bigInteger, Sign.publicKeyFromPrivate(bigInteger), 0, bArr, null);
    }

    public static Bip32ECKeyPair create(byte[] bArr, byte[] bArr2) {
        return create(Numeric.toBigInt(bArr), bArr2);
    }

    public static Bip32ECKeyPair generateKeyPair(byte[] bArr) {
        byte[] hmacSha512 = Hash.hmacSha512("Bitcoin seed".getBytes(), bArr);
        byte[] copyOfRange = Arrays.copyOfRange(hmacSha512, 0, 32);
        byte[] copyOfRange2 = Arrays.copyOfRange(hmacSha512, 32, 64);
        Arrays.fill(hmacSha512, (byte) 0);
        Bip32ECKeyPair create = create(copyOfRange, copyOfRange2);
        Arrays.fill(copyOfRange, (byte) 0);
        Arrays.fill(copyOfRange2, (byte) 0);
        return create;
    }

    public static Bip32ECKeyPair deriveKeyPair(Bip32ECKeyPair bip32ECKeyPair, int[] iArr) {
        if (iArr != null) {
            for (int i : iArr) {
                bip32ECKeyPair = bip32ECKeyPair.deriveChildKey(i);
            }
        }
        return bip32ECKeyPair;
    }

    private Bip32ECKeyPair deriveChildKey(int i) {
        if (!hasPrivateKey()) {
            byte[] encoded = getPublicKeyPoint().getEncoded(true);
            ByteBuffer allocate = ByteBuffer.allocate(37);
            allocate.put(encoded);
            allocate.putInt(i);
            byte[] hmacSha512 = Hash.hmacSha512(getChainCode(), allocate.array());
            byte[] copyOfRange = Arrays.copyOfRange(hmacSha512, 0, 32);
            byte[] copyOfRange2 = Arrays.copyOfRange(hmacSha512, 32, 64);
            Arrays.fill(hmacSha512, (byte) 0);
            BigInteger bigInteger = new BigInteger(1, copyOfRange);
            Arrays.fill(copyOfRange, (byte) 0);
            return new Bip32ECKeyPair(null, Sign.publicFromPoint(Sign.publicPointFromPrivate(bigInteger).add(getPublicKeyPoint()).getEncoded(true)), i, copyOfRange2, this);
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(37);
        if (isHardened(i)) {
            allocate2.put(getPrivateKeyBytes33());
        } else {
            allocate2.put(getPublicKeyPoint().getEncoded(true));
        }
        allocate2.putInt(i);
        byte[] hmacSha5122 = Hash.hmacSha512(getChainCode(), allocate2.array());
        byte[] copyOfRange3 = Arrays.copyOfRange(hmacSha5122, 0, 32);
        byte[] copyOfRange4 = Arrays.copyOfRange(hmacSha5122, 32, 64);
        Arrays.fill(hmacSha5122, (byte) 0);
        BigInteger bigInteger2 = new BigInteger(1, copyOfRange3);
        Arrays.fill(copyOfRange3, (byte) 0);
        BigInteger mod = getPrivateKey().add(bigInteger2).mod(Sign.CURVE.getN());
        return new Bip32ECKeyPair(mod, Sign.publicKeyFromPrivate(mod), i, copyOfRange4, this);
    }

    private int getFingerprint() {
        byte[] identifier = getIdentifier();
        return ((identifier[0] & 255) << 24) | (identifier[3] & 255) | ((identifier[2] & 255) << 8) | ((identifier[1] & 255) << 16);
    }

    private byte[] getIdentifier() {
        return Hash.sha256hash160(getPublicKeyPoint().getEncoded(true));
    }

    public ECPoint getPublicKeyPoint() {
        if (this.publicKeyPoint == null) {
            this.publicKeyPoint = Sign.publicPointFromPrivate(getPrivateKey());
        }
        return this.publicKeyPoint;
    }

    public byte[] getPrivateKeyBytes33() {
        byte[] bArr = new byte[33];
        byte[] bigIntegerToBytes32 = bigIntegerToBytes32(getPrivateKey());
        System.arraycopy(bigIntegerToBytes32, 0, bArr, 33 - bigIntegerToBytes32.length, bigIntegerToBytes32.length);
        return bArr;
    }

    private boolean hasPrivateKey() {
        return getPrivateKey() != null || this.parentHasPrivate;
    }

    private static byte[] bigIntegerToBytes32(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        byte[] bArr = new byte[32];
        int i = byteArray[0] == 0 ? 1 : 0;
        int length = byteArray.length;
        if (i != 0) {
            length--;
        }
        System.arraycopy(byteArray, i, bArr, 32 - length, length);
        return bArr;
    }
}
