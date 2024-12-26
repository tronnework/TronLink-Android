package org.tron.common.bip32;

import java.math.BigInteger;
import java.security.KeyPair;
import java.util.Arrays;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.tron.common.crypto.ECKey;
public class ECKeyPair {
    private final BigInteger privateKey;
    private final BigInteger publicKey;

    public BigInteger getPrivateKey() {
        return this.privateKey;
    }

    public BigInteger getPublicKey() {
        return this.publicKey;
    }

    public ECKeyPair(BigInteger bigInteger, BigInteger bigInteger2) {
        this.privateKey = bigInteger;
        this.publicKey = bigInteger2;
    }

    public ECKey.ECDSASignature sign(byte[] bArr) {
        ECDSASigner eCDSASigner = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        eCDSASigner.init(true, new ECPrivateKeyParameters(this.privateKey, Sign.CURVE));
        BigInteger[] generateSignature = eCDSASigner.generateSignature(bArr);
        return new ECKey.ECDSASignature(generateSignature[0], generateSignature[1]).toCanonicalised();
    }

    public static ECKeyPair create(KeyPair keyPair) {
        BigInteger d = ((BCECPrivateKey) keyPair.getPrivate()).getD();
        byte[] encoded = ((BCECPublicKey) keyPair.getPublic()).getQ().getEncoded(false);
        return new ECKeyPair(d, new BigInteger(1, Arrays.copyOfRange(encoded, 1, encoded.length)));
    }

    public static ECKeyPair create(BigInteger bigInteger) {
        return new ECKeyPair(bigInteger, Sign.publicKeyFromPrivate(bigInteger));
    }

    public static ECKeyPair create(byte[] bArr) {
        return create(Numeric.toBigInt(bArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ECKeyPair eCKeyPair = (ECKeyPair) obj;
        BigInteger bigInteger = this.privateKey;
        if (bigInteger == null ? eCKeyPair.privateKey == null : bigInteger.equals(eCKeyPair.privateKey)) {
            BigInteger bigInteger2 = this.publicKey;
            if (bigInteger2 != null) {
                return bigInteger2.equals(eCKeyPair.publicKey);
            }
            return eCKeyPair.publicKey == null;
        }
        return false;
    }

    public int hashCode() {
        BigInteger bigInteger = this.privateKey;
        int hashCode = (bigInteger != null ? bigInteger.hashCode() : 0) * 31;
        BigInteger bigInteger2 = this.publicKey;
        return hashCode + (bigInteger2 != null ? bigInteger2.hashCode() : 0);
    }
}
