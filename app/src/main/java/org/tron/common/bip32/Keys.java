package org.tron.common.bip32;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.tron.common.crypto.Hash;
import org.tron.common.crypto.jce.ECKeyFactory;
import org.tron.common.utils.Utils;
import org.tron.walletserver.AddressUtil;
public class Keys {
    public static final int ADDRESS_LENGTH_IN_HEX = 40;
    public static final int ADDRESS_SIZE = 160;
    public static final int PRIVATE_KEY_LENGTH_IN_HEX = 64;
    static final int PRIVATE_KEY_SIZE = 32;
    static final int PUBLIC_KEY_LENGTH_IN_HEX = 128;
    static final int PUBLIC_KEY_SIZE = 64;

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    private Keys() {
    }

    static KeyPair createSecp256k1KeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ECKeyFactory.ALGORITHM, BouncyCastleProvider.PROVIDER_NAME);
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256k1"), Utils.getRandom());
        return keyPairGenerator.generateKeyPair();
    }

    public static ECKeyPair createEcKeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        return ECKeyPair.create(createSecp256k1KeyPair());
    }

    public static String getAddress(ECKeyPair eCKeyPair) {
        return getAddress(eCKeyPair.getPublicKey());
    }

    public static String getAddress(BigInteger bigInteger) {
        return getAddress(Numeric.toHexStringWithPrefixZeroPadded(bigInteger, 128));
    }

    public static String getAddress(String str) {
        String sha3;
        String cleanHexPrefix = Numeric.cleanHexPrefix(str);
        if (cleanHexPrefix.length() < 128) {
            cleanHexPrefix = AddressUtil.zeros(128 - cleanHexPrefix.length()) + cleanHexPrefix;
        }
        return Hash.sha3(cleanHexPrefix).substring(sha3.length() - 40);
    }

    public static byte[] getAddress(byte[] bArr) {
        byte[] sha3 = Hash.sha3(bArr);
        return Arrays.copyOfRange(sha3, sha3.length - 20, sha3.length);
    }

    public static String toChecksumAddress(String str) {
        String lowerCase = Numeric.cleanHexPrefix(str).toLowerCase();
        String cleanHexPrefix = Numeric.cleanHexPrefix(Hash.sha3String(lowerCase));
        StringBuilder sb = new StringBuilder(lowerCase.length() + 2);
        sb.append("0x");
        for (int i = 0; i < lowerCase.length(); i++) {
            if (Integer.parseInt(String.valueOf(cleanHexPrefix.charAt(i)), 16) >= 8) {
                sb.append(String.valueOf(lowerCase.charAt(i)).toUpperCase());
            } else {
                sb.append(lowerCase.charAt(i));
            }
        }
        return sb.toString();
    }

    public static byte[] serialize(ECKeyPair eCKeyPair) {
        byte[] bytesPadded = Numeric.toBytesPadded(eCKeyPair.getPrivateKey(), 32);
        byte[] bytesPadded2 = Numeric.toBytesPadded(eCKeyPair.getPublicKey(), 64);
        byte[] copyOf = Arrays.copyOf(bytesPadded, 96);
        System.arraycopy(bytesPadded2, 0, copyOf, 32, 64);
        return copyOf;
    }

    public static ECKeyPair deserialize(byte[] bArr) {
        if (bArr.length != 96) {
            throw new RuntimeException("Invalid input key size");
        }
        return new ECKeyPair(Numeric.toBigInt(bArr, 0, 32), Numeric.toBigInt(bArr, 32, 64));
    }
}
