package org.tron.common.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.tron.common.bip32.Numeric;
import org.tron.common.crypto.jce.TronCastleProvider;
import org.tron.config.Parameter;
public class Hash {
    private static final Provider CRYPTO_PROVIDER;
    private static final String HASH_256_ALGORITHM_NAME;
    private static final String HASH_512_ALGORITHM_NAME;

    static {
        Security.addProvider(TronCastleProvider.getInstance());
        CRYPTO_PROVIDER = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        HASH_256_ALGORITHM_NAME = "TRON-KECCAK-256";
        HASH_512_ALGORITHM_NAME = "TRON-KECCAK-512";
    }

    public static byte[] sha256(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sha3(String str) {
        return Numeric.toHexString(sha3(Numeric.hexStringToByteArray(str)));
    }

    public static byte[] sha3(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_256_ALGORITHM_NAME, CRYPTO_PROVIDER);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sha3(byte[] bArr, byte[] bArr2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_256_ALGORITHM_NAME, CRYPTO_PROVIDER);
            messageDigest.update(bArr, 0, bArr.length);
            messageDigest.update(bArr2, 0, bArr2.length);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sha3(byte[] bArr, int i, int i2) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_256_ALGORITHM_NAME, CRYPTO_PROVIDER);
            messageDigest.update(bArr, i, i2);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sha3String(String str) {
        return Numeric.toHexString(sha3(str.getBytes(StandardCharsets.UTF_8)));
    }

    public static byte[] sha512(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_512_ALGORITHM_NAME, CRYPTO_PROVIDER);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sha3omit12(byte[] bArr) {
        byte[] sha3 = sha3(bArr);
        byte[] copyOfRange = Arrays.copyOfRange(sha3, 11, sha3.length);
        copyOfRange[0] = Parameter.CommonConstant.ADD_PRE_FIX_BYTE;
        return copyOfRange;
    }

    public static byte[] hmacSha512(byte[] bArr, byte[] bArr2) {
        HMac hMac = new HMac(new SHA512Digest());
        hMac.init(new KeyParameter(bArr));
        hMac.update(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[64];
        hMac.doFinal(bArr3, 0);
        return bArr3;
    }

    public static byte[] sha256hash160(byte[] bArr) {
        byte[] sha256 = sha256(bArr);
        RIPEMD160Digest rIPEMD160Digest = new RIPEMD160Digest();
        rIPEMD160Digest.update(sha256, 0, sha256.length);
        byte[] bArr2 = new byte[20];
        rIPEMD160Digest.doFinal(bArr2, 0);
        return bArr2;
    }

    public static byte[] computeAddress(ECPoint eCPoint) {
        return computeAddress(eCPoint.getEncoded(false));
    }

    public static byte[] computeAddress(byte[] bArr) {
        return sha3omit12(org.bouncycastle.util.Arrays.copyOfRange(bArr, 1, bArr.length));
    }
}
