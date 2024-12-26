package org.tron.common.crypto;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.crypto.KeyAgreement;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tron.common.crypto.cryptohash.Keccak256;
import org.tron.common.crypto.jce.ECKeyAgreement;
import org.tron.common.crypto.jce.ECKeyFactory;
import org.tron.common.crypto.jce.ECKeyPairGenerator;
import org.tron.common.crypto.jce.ECSignatureFactory;
import org.tron.common.crypto.jce.TronCastleProvider;
import org.tron.common.utils.BIUtil;
import org.tron.common.utils.ByteUtil;
public class ECKey implements Serializable, SignInterface {
    public static final ECDomainParameters CURVE;
    public static final ECParameterSpec CURVE_SPEC;
    public static final BigInteger HALF_CURVE_ORDER;
    private static final SecureRandom secureRandom;
    private static final long serialVersionUID = -728224901792295832L;
    private transient byte[] nodeId;
    private final PrivateKey privKey;
    private final Provider provider;
    protected final ECPoint pub;
    private transient byte[] pubKeyHash;
    private static final Logger log = LoggerFactory.getLogger("crypto");
    private static final BigInteger SECP256K1N = new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16);

    public static class MissingPrivateKeyException extends RuntimeException {
    }

    public ECPoint getPubKeyPoint() {
        return this.pub;
    }

    public boolean hasPrivKey() {
        return this.privKey != null;
    }

    public boolean isPubKeyOnly() {
        return this.privKey == null;
    }

    static {
        X9ECParameters byName = SECNamedCurves.getByName("secp256k1");
        CURVE = new ECDomainParameters(byName.getCurve(), byName.getG(), byName.getN(), byName.getH());
        CURVE_SPEC = new ECParameterSpec(byName.getCurve(), byName.getG(), byName.getN(), byName.getH());
        HALF_CURVE_ORDER = byName.getN().shiftRight(1);
        secureRandom = new SecureRandom();
    }

    public ECKey() {
        this(secureRandom);
    }

    public ECKey(Provider provider, SecureRandom secureRandom2) {
        this.provider = provider;
        KeyPair generateKeyPair = ECKeyPairGenerator.getInstance(provider, secureRandom2).generateKeyPair();
        this.privKey = generateKeyPair.getPrivate();
        PublicKey publicKey = generateKeyPair.getPublic();
        if (publicKey instanceof BCECPublicKey) {
            this.pub = ((BCECPublicKey) publicKey).getQ();
        } else if (publicKey instanceof ECPublicKey) {
            this.pub = extractPublicKey((ECPublicKey) publicKey);
        } else {
            throw new AssertionError("Expected Provider " + provider.getName() + " to produce a subtype of ECPublicKey, found " + publicKey.getClass());
        }
    }

    public ECKey(SecureRandom secureRandom2) {
        this(TronCastleProvider.getInstance(), secureRandom2);
    }

    public ECKey(byte[] bArr, boolean z) {
        if (z) {
            BigInteger bigInteger = new BigInteger(1, bArr);
            this.privKey = privateKeyFromBigInteger(bigInteger);
            this.pub = CURVE.getG().multiply(bigInteger);
        } else {
            this.privKey = null;
            this.pub = CURVE.getCurve().decodePoint(bArr);
        }
        this.provider = TronCastleProvider.getInstance();
    }

    public ECKey(Provider provider, @Nullable PrivateKey privateKey, ECPoint eCPoint) {
        this.provider = provider;
        if (privateKey == null || isECPrivateKey(privateKey)) {
            this.privKey = privateKey;
            if (eCPoint == null) {
                throw new IllegalArgumentException("Public key may not be null");
            }
            this.pub = eCPoint;
            return;
        }
        throw new IllegalArgumentException("Expected EC private key, given a private key object with class " + privateKey.getClass().toString() + " and algorithm " + privateKey.getAlgorithm());
    }

    public ECKey(@Nullable BigInteger bigInteger, ECPoint eCPoint) {
        this(TronCastleProvider.getInstance(), privateKeyFromBigInteger(bigInteger), eCPoint);
    }

    private static ECPoint extractPublicKey(ECPublicKey eCPublicKey) {
        java.security.spec.ECPoint w = eCPublicKey.getW();
        return CURVE.getCurve().createPoint(w.getAffineX(), w.getAffineY());
    }

    private static boolean isECPrivateKey(PrivateKey privateKey) {
        return (privateKey instanceof ECPrivateKey) || privateKey.getAlgorithm().equals("EC");
    }

    private static PrivateKey privateKeyFromBigInteger(BigInteger bigInteger) {
        if (bigInteger == null) {
            return null;
        }
        try {
            return ECKeyFactory.getInstance(TronCastleProvider.getInstance()).generatePrivate(new ECPrivateKeySpec(bigInteger, CURVE_SPEC));
        } catch (InvalidKeySpecException unused) {
            throw new AssertionError("Assumed correct key spec statically");
        }
    }

    public static ECPoint compressPoint(ECPoint eCPoint) {
        return CURVE.getCurve().decodePoint(eCPoint.getEncoded(true));
    }

    public static ECPoint decompressPoint(ECPoint eCPoint) {
        return CURVE.getCurve().decodePoint(eCPoint.getEncoded(false));
    }

    public static ECKey fromPrivate(BigInteger bigInteger) {
        return new ECKey(bigInteger, CURVE.getG().multiply(bigInteger));
    }

    public static ECKey fromPrivate(byte[] bArr) {
        return fromPrivate(new BigInteger(1, bArr));
    }

    public static ECKey fromPrivateAndPrecalculatedPublic(BigInteger bigInteger, ECPoint eCPoint) {
        return new ECKey(bigInteger, eCPoint);
    }

    public static ECKey fromPrivateAndPrecalculatedPublic(byte[] bArr, byte[] bArr2) {
        check(bArr != null, "Private key must not be null");
        check(bArr2 != null, "Public key must not be null");
        return new ECKey(new BigInteger(1, bArr), CURVE.getCurve().decodePoint(bArr2));
    }

    public static ECKey fromPublicOnly(ECPoint eCPoint) {
        return new ECKey((BigInteger) null, eCPoint);
    }

    public static ECKey fromPublicOnly(byte[] bArr) {
        return new ECKey((BigInteger) null, CURVE.getCurve().decodePoint(bArr));
    }

    public static byte[] publicKeyFromPrivate(BigInteger bigInteger, boolean z) {
        return CURVE.getG().multiply(bigInteger).getEncoded(z);
    }

    public static byte[] pubBytesWithoutFormat(ECPoint eCPoint) {
        byte[] encoded = eCPoint.getEncoded(false);
        return Arrays.copyOfRange(encoded, 1, encoded.length);
    }

    public static ECKey fromNodeId(byte[] bArr) {
        check(bArr.length == 64, "Expected a 64 byte node id");
        byte[] bArr2 = new byte[65];
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        bArr2[0] = 4;
        return fromPublicOnly(bArr2);
    }

    public static byte[] signatureToKeyBytes(byte[] bArr, String str) throws SignatureException {
        try {
            byte[] decode = Base64.decode(str);
            if (decode.length < 65) {
                throw new SignatureException("Signature truncated, expected 65 bytes and got " + decode.length);
            }
            return signatureToKeyBytes(bArr, ECDSASignature.fromComponents(Arrays.copyOfRange(decode, 1, 33), Arrays.copyOfRange(decode, 33, 65), (byte) (decode[0] & 255)));
        } catch (RuntimeException e) {
            throw new SignatureException("Could not decode base64", e);
        }
    }

    public static byte[] signatureToKeyBytes(byte[] bArr, ECDSASignature eCDSASignature) throws SignatureException {
        check(bArr.length == 32, "messageHash argument has length " + bArr.length);
        int i = eCDSASignature.v;
        if (i < 27 || i > 34) {
            throw new SignatureException("Header byte out of range: " + i);
        }
        if (i >= 31) {
            i -= 4;
        }
        byte[] recoverPubBytesFromSignature = recoverPubBytesFromSignature(i - 27, eCDSASignature, bArr);
        if (recoverPubBytesFromSignature != null) {
            return recoverPubBytesFromSignature;
        }
        throw new SignatureException("Could not recover public key from signature");
    }

    public static byte[] signatureToAddress(byte[] bArr, String str) throws SignatureException {
        return Hash.computeAddress(signatureToKeyBytes(bArr, str));
    }

    public static byte[] signatureToAddress(byte[] bArr, ECDSASignature eCDSASignature) throws SignatureException {
        return Hash.computeAddress(signatureToKeyBytes(bArr, eCDSASignature));
    }

    public static ECKey signatureToKey(byte[] bArr, String str) throws SignatureException {
        return fromPublicOnly(signatureToKeyBytes(bArr, str));
    }

    public static ECKey signatureToKey(byte[] bArr, ECDSASignature eCDSASignature) throws SignatureException {
        return fromPublicOnly(signatureToKeyBytes(bArr, eCDSASignature));
    }

    public static boolean verify(byte[] bArr, ECDSASignature eCDSASignature, byte[] bArr2) {
        ECDSASigner eCDSASigner = new ECDSASigner();
        ECDomainParameters eCDomainParameters = CURVE;
        eCDSASigner.init(false, new ECPublicKeyParameters(eCDomainParameters.getCurve().decodePoint(bArr2), eCDomainParameters));
        try {
            return eCDSASigner.verifySignature(bArr, eCDSASignature.r, eCDSASignature.s);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return verify(bArr, ECDSASignature.decodeFromDER(bArr2), bArr3);
    }

    public static boolean isPubKeyCanonical(byte[] bArr) {
        byte b = bArr[0];
        return b == 4 ? bArr.length == 65 : (b == 2 || b == 3) && bArr.length == 33;
    }

    @Nullable
    public static byte[] recoverPubBytesFromSignature(int i, ECDSASignature eCDSASignature, byte[] bArr) {
        check(i >= 0, "recId must be positive");
        check(eCDSASignature.r.signum() >= 0, "r must be positive");
        check(eCDSASignature.s.signum() >= 0, "s must be positive");
        check(bArr != null, "messageHash must not be null");
        ECDomainParameters eCDomainParameters = CURVE;
        BigInteger n = eCDomainParameters.getN();
        BigInteger add = eCDSASignature.r.add(BigInteger.valueOf(i / 2).multiply(n));
        if (add.compareTo(((ECCurve.Fp) eCDomainParameters.getCurve()).getQ()) >= 0) {
            return null;
        }
        ECPoint decompressKey = decompressKey(add, (i & 1) == 1);
        if (decompressKey.multiply(n).isInfinity()) {
            BigInteger mod = BigInteger.ZERO.subtract(new BigInteger(1, bArr)).mod(n);
            BigInteger modInverse = eCDSASignature.r.modInverse(n);
            return ((ECPoint.Fp) ECAlgorithms.sumOfTwoMultiplies(eCDomainParameters.getG(), modInverse.multiply(mod).mod(n), decompressKey, modInverse.multiply(eCDSASignature.s).mod(n))).getEncoded(false);
        }
        return null;
    }

    @Nullable
    public static byte[] recoverAddressFromSignature(int i, ECDSASignature eCDSASignature, byte[] bArr) {
        byte[] recoverPubBytesFromSignature = recoverPubBytesFromSignature(i, eCDSASignature, bArr);
        if (recoverPubBytesFromSignature == null) {
            return null;
        }
        return Hash.computeAddress(recoverPubBytesFromSignature);
    }

    @Nullable
    public static ECKey recoverFromSignature(int i, ECDSASignature eCDSASignature, byte[] bArr) {
        byte[] recoverPubBytesFromSignature = recoverPubBytesFromSignature(i, eCDSASignature, bArr);
        if (recoverPubBytesFromSignature == null) {
            return null;
        }
        return fromPublicOnly(recoverPubBytesFromSignature);
    }

    private static ECPoint decompressKey(BigInteger bigInteger, boolean z) {
        X9IntegerConverter x9IntegerConverter = new X9IntegerConverter();
        ECDomainParameters eCDomainParameters = CURVE;
        byte[] integerToBytes = x9IntegerConverter.integerToBytes(bigInteger, x9IntegerConverter.getByteLength(eCDomainParameters.getCurve()) + 1);
        integerToBytes[0] = (byte) (z ? 3 : 2);
        return eCDomainParameters.getCurve().decodePoint(integerToBytes);
    }

    private static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    @Override
    public byte[] getAddress() {
        if (this.pubKeyHash == null) {
            this.pubKeyHash = Hash.computeAddress(this.pub);
        }
        return this.pubKeyHash;
    }

    @Override
    public String signHash(byte[] bArr) {
        return sign(bArr).toBase64();
    }

    @Override
    public byte[] Base64toBytes(String str) {
        byte[] decode = Base64.decode(str);
        return ByteUtil.appendByte(Arrays.copyOfRange(decode, 1, 65), (byte) (decode[0] - 27));
    }

    @Override
    public byte[] signToAddress(byte[] bArr, String str) throws SignatureException {
        return Hash.computeAddress(signatureToKeyBytes(bArr, str));
    }

    @Override
    public byte[] getNodeId() {
        if (this.nodeId == null) {
            this.nodeId = pubBytesWithoutFormat(this.pub);
        }
        return this.nodeId;
    }

    @Override
    public byte[] hash(byte[] bArr) {
        return new Keccak256().digest(bArr);
    }

    @Override
    public byte[] getPrivateKey() {
        return getPrivKeyBytes();
    }

    @Override
    public byte[] getPubKey() {
        return this.pub.getEncoded(false);
    }

    public BigInteger getPrivKey() {
        PrivateKey privateKey = this.privKey;
        if (privateKey == null) {
            throw new MissingPrivateKeyException();
        }
        if (privateKey instanceof BCECPrivateKey) {
            return ((BCECPrivateKey) privateKey).getD();
        }
        throw new MissingPrivateKeyException();
    }

    public String toStringWithPrivate() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        PrivateKey privateKey = this.privKey;
        if (privateKey != null && (privateKey instanceof BCECPrivateKey)) {
            sb.append(" priv:");
            sb.append(Hex.toHexString(((BCECPrivateKey) this.privKey).getD().toByteArray()));
        }
        return sb.toString();
    }

    public ECDSASignature doSign(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Expected 32 byte input to ECDSA signature, not " + bArr.length);
        }
        PrivateKey privateKey = this.privKey;
        if (privateKey == null) {
            throw new MissingPrivateKeyException();
        }
        if (privateKey instanceof BCECPrivateKey) {
            ECDSASigner eCDSASigner = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
            eCDSASigner.init(true, new ECPrivateKeyParameters(((BCECPrivateKey) this.privKey).getD(), CURVE));
            BigInteger[] generateSignature = eCDSASigner.generateSignature(bArr);
            return new ECDSASignature(generateSignature[0], generateSignature[1]).toCanonicalised();
        }
        try {
            Signature rawInstance = ECSignatureFactory.getRawInstance(this.provider);
            rawInstance.initSign(this.privKey);
            rawInstance.update(bArr);
            return ECDSASignature.decodeFromDER(rawInstance.sign()).toCanonicalised();
        } catch (InvalidKeyException | SignatureException e) {
            throw new RuntimeException("ECKey signing error", e);
        }
    }

    @Override
    public ECDSASignature sign(byte[] bArr) {
        ECDSASignature doSign = doSign(bArr);
        int i = 0;
        byte[] encoded = this.pub.getEncoded(false);
        while (true) {
            if (i >= 4) {
                i = -1;
                break;
            }
            byte[] recoverPubBytesFromSignature = recoverPubBytesFromSignature(i, doSign, bArr);
            if (recoverPubBytesFromSignature != null && Arrays.equals(recoverPubBytesFromSignature, encoded)) {
                break;
            }
            i++;
        }
        if (i == -1) {
            throw new RuntimeException("Could not construct a recoverable key. This should never happen.");
        }
        doSign.v = (byte) (i + 27);
        return doSign;
    }

    public BigInteger keyAgreement(ECPoint eCPoint) {
        PrivateKey privateKey = this.privKey;
        if (privateKey == null) {
            throw new MissingPrivateKeyException();
        }
        if (privateKey instanceof BCECPrivateKey) {
            ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
            BigInteger d = ((BCECPrivateKey) this.privKey).getD();
            ECDomainParameters eCDomainParameters = CURVE;
            eCDHBasicAgreement.init(new ECPrivateKeyParameters(d, eCDomainParameters));
            return eCDHBasicAgreement.calculateAgreement(new ECPublicKeyParameters(eCPoint, eCDomainParameters));
        }
        try {
            KeyAgreement eCKeyAgreement = ECKeyAgreement.getInstance(this.provider);
            eCKeyAgreement.init(this.privKey);
            eCKeyAgreement.doPhase(ECKeyFactory.getInstance(this.provider).generatePublic(new ECPublicKeySpec(eCPoint, CURVE_SPEC)), true);
            return new BigInteger(1, eCKeyAgreement.generateSecret());
        } catch (IllegalStateException | InvalidKeyException | InvalidKeySpecException e) {
            throw new RuntimeException("ECDH key agreement failure", e);
        }
    }

    public byte[] decryptAES(byte[] bArr) {
        PrivateKey privateKey = this.privKey;
        if (privateKey == null) {
            throw new MissingPrivateKeyException();
        }
        if (!(privateKey instanceof BCECPrivateKey)) {
            


return null;

//throw new UnsupportedOperationException(
Cannot use the private key as an AES key");
        }
        AESEngine aESEngine = new AESEngine();
        SICBlockCipher sICBlockCipher = new SICBlockCipher(aESEngine);
        sICBlockCipher.init(false, new ParametersWithIV(new KeyParameter(BigIntegers.asUnsignedByteArray(((BCECPrivateKey) this.privKey).getD())), new byte[16]));
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        while (i < bArr.length) {
            sICBlockCipher.processBlock(bArr, i, bArr2, i);
            i += aESEngine.getBlockSize();
            if (bArr.length - i < aESEngine.getBlockSize()) {
                break;
            }
        }
        if (bArr.length - i > 0) {
            byte[] bArr3 = new byte[16];
            System.arraycopy(bArr, i, bArr3, 0, bArr.length - i);
            sICBlockCipher.processBlock(bArr3, 0, bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, i, bArr.length - i);
        }
        return bArr2;
    }

    public boolean verify(byte[] bArr, byte[] bArr2) {
        return verify(bArr, bArr2, getPubKey());
    }

    public boolean verify(byte[] bArr, ECDSASignature eCDSASignature) {
        return verify(bArr, eCDSASignature, getPubKey());
    }

    public boolean isPubKeyCanonical() {
        return isPubKeyCanonical(this.pub.getEncoded(false));
    }

    @Override
    @Nullable
    public byte[] getPrivKeyBytes() {
        PrivateKey privateKey = this.privKey;
        if (privateKey != null && (privateKey instanceof BCECPrivateKey)) {
            return ByteUtil.bigIntegerToBytes(((BCECPrivateKey) privateKey).getD(), 32);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ECKey eCKey = (ECKey) obj;
        PrivateKey privateKey = this.privKey;
        if (privateKey == null || privateKey.equals(eCKey.privKey)) {
            ECPoint eCPoint = this.pub;
            return eCPoint == null || eCPoint.equals(eCKey.pub);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(getPubKey());
    }

    public static class ECDSASignature implements SignatureInterface {
        public final BigInteger r;
        public final BigInteger s;
        public byte v;

        public ECDSASignature(BigInteger bigInteger, BigInteger bigInteger2) {
            this.r = bigInteger;
            this.s = bigInteger2;
        }

        public ECDSASignature(byte[] bArr, byte[] bArr2, byte b) {
            this.r = new BigInteger(1, bArr);
            this.s = new BigInteger(1, bArr2);
            this.v = b;
        }

        private static ECDSASignature fromComponents(byte[] bArr, byte[] bArr2) {
            return new ECDSASignature(new BigInteger(1, bArr), new BigInteger(1, bArr2));
        }

        public static ECDSASignature fromComponents(byte[] bArr, byte[] bArr2, byte b) {
            ECDSASignature fromComponents = fromComponents(bArr, bArr2);
            fromComponents.v = b;
            return fromComponents;
        }

        public static boolean validateComponents(BigInteger bigInteger, BigInteger bigInteger2, byte b) {
            if ((b == 27 || b == 28) && !BIUtil.isLessThan(bigInteger, BigInteger.ONE) && !BIUtil.isLessThan(bigInteger2, BigInteger.ONE) && BIUtil.isLessThan(bigInteger, ECKey.SECP256K1N)) {
                return BIUtil.isLessThan(bigInteger2, ECKey.SECP256K1N);
            }
            return false;
        }

        public static ECDSASignature decodeFromDER(byte[] bArr) {
            ASN1InputStream aSN1InputStream;
            ASN1InputStream aSN1InputStream2 = null;
            try {
                try {
                    aSN1InputStream = new ASN1InputStream(bArr);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
            try {
                DLSequence dLSequence = (DLSequence) aSN1InputStream.readObject();
                if (dLSequence == null) {
                    throw new RuntimeException("Reached past end of ASN.1 stream.");
                }
                try {
                    ECDSASignature eCDSASignature = new ECDSASignature(((ASN1Integer) dLSequence.getObjectAt(0)).getPositiveValue(), ((ASN1Integer) dLSequence.getObjectAt(1)).getPositiveValue());
                    try {
                        aSN1InputStream.close();
                    } catch (IOException unused) {
                    }
                    return eCDSASignature;
                } catch (ClassCastException e2) {
                    throw new IllegalArgumentException(e2);
                }
            } catch (IOException e3) {
                e = e3;
                throw new RuntimeException(e);
            } catch (Throwable th2) {
                th = th2;
                aSN1InputStream2 = aSN1InputStream;
                if (aSN1InputStream2 != null) {
                    try {
                        aSN1InputStream2.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        }

        @Override
        public boolean validateComponents() {
            return validateComponents(this.r, this.s, this.v);
        }

        public ECDSASignature toCanonicalised() {
            return this.s.compareTo(ECKey.HALF_CURVE_ORDER) > 0 ? new ECDSASignature(this.r, ECKey.CURVE.getN().subtract(this.s)) : this;
        }

        public String toBase64() {
            byte[] bArr = new byte[65];
            bArr[0] = this.v;
            System.arraycopy(ByteUtil.bigIntegerToBytes(this.r, 32), 0, bArr, 1, 32);
            System.arraycopy(ByteUtil.bigIntegerToBytes(this.s, 32), 0, bArr, 33, 32);
            return new String(Base64.encode(bArr), Charset.forName("UTF-8"));
        }

        @Override
        public byte[] toByteArray() {
            byte b = this.v;
            if (b >= 27) {
                b = (byte) (b - 27);
            }
            return ByteUtil.merge(ByteUtil.bigIntegerToBytes(this.r, 32), ByteUtil.bigIntegerToBytes(this.s, 32), new byte[]{b});
        }

        public String toHex() {
            return Hex.toHexString(toByteArray());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ECDSASignature eCDSASignature = (ECDSASignature) obj;
            if (this.r.equals(eCDSASignature.r)) {
                return this.s.equals(eCDSASignature.s);
            }
            return false;
        }

        public int hashCode() {
            return (this.r.hashCode() * 31) + this.s.hashCode();
        }
    }
}
