package org.tron.common.bip32;

import com.google.common.base.Ascii;
import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.custom.sec.SecP256K1Curve;
import org.tron.common.crypto.ECKey;
import org.tron.common.crypto.Hash;
public class Sign {
    public static final ECDomainParameters CURVE;
    private static final X9ECParameters CURVE_PARAMS;
    static final BigInteger HALF_CURVE_ORDER;
    static final String MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n";
    static final String MESSAGE_TRXPREFIX = "\u0019TRON Signed Message:\n";

    static {
        X9ECParameters byName = CustomNamedCurves.getByName("secp256k1");
        CURVE_PARAMS = byName;
        CURVE = new ECDomainParameters(byName.getCurve(), byName.getG(), byName.getN(), byName.getH());
        HALF_CURVE_ORDER = byName.getN().shiftRight(1);
    }

    static byte[] getEthereumMessagePrefix(int i) {
        return MESSAGE_TRXPREFIX.concat(String.valueOf(i)).getBytes();
    }

    static byte[] getEthereumMessageHash(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length + bArr.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, 0, bArr3, bArr2.length, bArr.length);
        return Hash.sha3(bArr3);
    }

    static byte[] getEthereumMessageHash(byte[] bArr) {
        return getEthereumMessageHash(bArr, getEthereumMessagePrefix(32));
    }

    static byte[] getEthereumMessageHashV2(byte[] bArr) {
        return getEthereumMessageHash(bArr, getEthereumMessagePrefix(bArr.length));
    }

    public static byte[] getPrefixedMessageHash(byte[] bArr) {
        return getEthereumMessageHash(bArr);
    }

    public static byte[] getPrefixedMessageHashV2(byte[] bArr) {
        return getEthereumMessageHashV2(bArr);
    }

    public static SignatureData signPrefixedMessage(byte[] bArr, ECKeyPair eCKeyPair) {
        return signMessage(getEthereumMessageHash(bArr), eCKeyPair, false);
    }

    public static SignatureData signPrefixedMessageV2(byte[] bArr, ECKeyPair eCKeyPair) {
        return signMessage(getEthereumMessageHashV2(bArr), eCKeyPair, false);
    }

    public static SignatureData signPrefixedMessage(byte[] bArr, ECKeyPair eCKeyPair, boolean z) {
        return signMessage(getEthereumMessageHash(bArr), eCKeyPair, z);
    }

    public static SignatureData signMessage(byte[] bArr, ECKeyPair eCKeyPair) {
        return signMessage(bArr, eCKeyPair, true);
    }

    public static SignatureData signMessage(byte[] bArr, ECKeyPair eCKeyPair, boolean z) {
        BigInteger publicKey = eCKeyPair.getPublicKey();
        if (z) {
            bArr = Hash.sha3(bArr);
        }
        ECKey.ECDSASignature sign = eCKeyPair.sign(bArr);
        int i = 0;
        while (true) {
            if (i >= 4) {
                i = -1;
                break;
            }
            BigInteger recoverFromSignature = recoverFromSignature(i, sign, bArr);
            if (recoverFromSignature != null && recoverFromSignature.equals(publicKey)) {
                break;
            }
            i++;
        }
        if (i == -1) {
            throw new RuntimeException("Could not construct a recoverable key. Are your credentials valid?");
        }
        return new SignatureData((byte) (i + 27), Numeric.toBytesPadded(sign.r, 32), Numeric.toBytesPadded(sign.s, 32));
    }

    public static BigInteger recoverFromSignature(int i, ECKey.ECDSASignature eCDSASignature, byte[] bArr) {
        Assertions.verifyPrecondition(i >= 0, "recId must be positive");
        Assertions.verifyPrecondition(eCDSASignature.r.signum() >= 0, "r must be positive");
        Assertions.verifyPrecondition(eCDSASignature.s.signum() >= 0, "s must be positive");
        Assertions.verifyPrecondition(bArr != null, "message cannot be null");
        ECDomainParameters eCDomainParameters = CURVE;
        BigInteger n = eCDomainParameters.getN();
        BigInteger add = eCDSASignature.r.add(BigInteger.valueOf(i / 2).multiply(n));
        if (add.compareTo(SecP256K1Curve.q) >= 0) {
            return null;
        }
        ECPoint decompressKey = decompressKey(add, (i & 1) == 1);
        if (decompressKey.multiply(n).isInfinity()) {
            BigInteger mod = BigInteger.ZERO.subtract(new BigInteger(1, bArr)).mod(n);
            BigInteger modInverse = eCDSASignature.r.modInverse(n);
            byte[] encoded = ECAlgorithms.sumOfTwoMultiplies(eCDomainParameters.getG(), modInverse.multiply(mod).mod(n), decompressKey, modInverse.multiply(eCDSASignature.s).mod(n)).getEncoded(false);
            return new BigInteger(1, Arrays.copyOfRange(encoded, 1, encoded.length));
        }
        return null;
    }

    private static ECPoint decompressKey(BigInteger bigInteger, boolean z) {
        X9IntegerConverter x9IntegerConverter = new X9IntegerConverter();
        ECDomainParameters eCDomainParameters = CURVE;
        byte[] integerToBytes = x9IntegerConverter.integerToBytes(bigInteger, x9IntegerConverter.getByteLength(eCDomainParameters.getCurve()) + 1);
        integerToBytes[0] = (byte) (z ? 3 : 2);
        return eCDomainParameters.getCurve().decodePoint(integerToBytes);
    }

    public static BigInteger signedMessageToKey(byte[] bArr, SignatureData signatureData) throws SignatureException {
        return signedMessageHashToKey(Hash.sha3(bArr), signatureData);
    }

    public static BigInteger signedPrefixedMessageToKey(byte[] bArr, SignatureData signatureData) throws SignatureException {
        return signedMessageHashToKey(getEthereumMessageHash(bArr), signatureData);
    }

    static BigInteger signedMessageHashToKey(byte[] bArr, SignatureData signatureData) throws SignatureException {
        byte[] r = signatureData.getR();
        byte[] s = signatureData.getS();
        boolean z = false;
        Assertions.verifyPrecondition(r != null && r.length == 32, "r must be 32 bytes");
        if (s != null && s.length == 32) {
            z = true;
        }
        Assertions.verifyPrecondition(z, "s must be 32 bytes");
        int v = signatureData.getV() & 255;
        if (v < 27 || v > 34) {
            throw new SignatureException("Header byte out of range: " + v);
        }
        BigInteger recoverFromSignature = recoverFromSignature(v - 27, new ECKey.ECDSASignature(new BigInteger(1, signatureData.getR()), new BigInteger(1, signatureData.getS())), bArr);
        if (recoverFromSignature != null) {
            return recoverFromSignature;
        }
        throw new SignatureException("Could not recover public key from signature");
    }

    public static BigInteger publicKeyFromPrivate(BigInteger bigInteger) {
        byte[] encoded = publicPointFromPrivate(bigInteger).getEncoded(false);
        return new BigInteger(1, Arrays.copyOfRange(encoded, 1, encoded.length));
    }

    public static ECPoint publicPointFromPrivate(BigInteger bigInteger) {
        int bitLength = bigInteger.bitLength();
        ECDomainParameters eCDomainParameters = CURVE;
        if (bitLength > eCDomainParameters.getN().bitLength()) {
            bigInteger = bigInteger.mod(eCDomainParameters.getN());
        }
        return new FixedPointCombMultiplier().multiply(eCDomainParameters.getG(), bigInteger);
    }

    public static BigInteger publicFromPoint(byte[] bArr) {
        return new BigInteger(1, Arrays.copyOfRange(bArr, 1, bArr.length));
    }

    public static class SignatureData {
        private final byte[] r;
        private final byte[] s;
        private final byte v;

        public byte[] getR() {
            return this.r;
        }

        public byte[] getS() {
            return this.s;
        }

        public byte getV() {
            return this.v;
        }

        public SignatureData(byte b, byte[] bArr, byte[] bArr2) {
            this.v = b;
            this.r = bArr;
            this.s = bArr2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SignatureData signatureData = (SignatureData) obj;
            if (this.v == signatureData.v && Arrays.equals(this.r, signatureData.r)) {
                return Arrays.equals(this.s, signatureData.s);
            }
            return false;
        }

        public int hashCode() {
            return (((this.v * Ascii.US) + Arrays.hashCode(this.r)) * 31) + Arrays.hashCode(this.s);
        }
    }
}
