package org.tron.common.crypto.jce;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import org.tron.common.utils.AnalyseUtil;
public final class ECKeyPairGenerator {
    public static final String ALGORITHM = "EC";
    public static final String CURVE_NAME = "secp256k1";
    private static final ECGenParameterSpec SECP256K1_CURVE = new ECGenParameterSpec("secp256k1");
    private static final String algorithmAssertionMsg = "Assumed JRE supports EC key pair generation";
    private static final String keySpecAssertionMsg = "Assumed correct key spec statically";

    private ECKeyPairGenerator() {
    }

    public static KeyPair generateKeyPair() {
        return Holder.INSTANCE.generateKeyPair();
    }

    public static KeyPairGenerator getInstance(String str, SecureRandom secureRandom) throws NoSuchProviderException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", str);
            keyPairGenerator.initialize(SECP256K1_CURVE, secureRandom);
            return keyPairGenerator;
        } catch (InvalidAlgorithmParameterException e) {
            AnalyseUtil.log(e);
            throw new AssertionError(keySpecAssertionMsg, e);
        } catch (NoSuchAlgorithmException e2) {
            AnalyseUtil.log(e2);
            throw new AssertionError(algorithmAssertionMsg, e2);
        }
    }

    public static KeyPairGenerator getInstance(Provider provider, SecureRandom secureRandom) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", provider);
            keyPairGenerator.initialize(SECP256K1_CURVE, secureRandom);
            return keyPairGenerator;
        } catch (InvalidAlgorithmParameterException e) {
            AnalyseUtil.log(e);
            throw new AssertionError(keySpecAssertionMsg, e);
        } catch (NoSuchAlgorithmException e2) {
            AnalyseUtil.log(e2);
            throw new AssertionError(algorithmAssertionMsg, e2);
        }
    }

    private static class Holder {
        private static final KeyPairGenerator INSTANCE;

        private Holder() {
        }

        static {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
                INSTANCE = keyPairGenerator;
                keyPairGenerator.initialize(ECKeyPairGenerator.SECP256K1_CURVE);
            } catch (InvalidAlgorithmParameterException e) {
                AnalyseUtil.log(e);
                throw new AssertionError(ECKeyPairGenerator.keySpecAssertionMsg, e);
            } catch (NoSuchAlgorithmException e2) {
                AnalyseUtil.log(e2);
                throw new AssertionError(ECKeyPairGenerator.algorithmAssertionMsg, e2);
            }
        }
    }
}
