package org.tron.common.crypto.jce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.KeyAgreement;
public final class ECKeyAgreement {
    public static final String ALGORITHM = "ECDH";
    private static final String algorithmAssertionMsg = "Assumed the JRE supports EC key agreement";

    private ECKeyAgreement() {
    }

    public static KeyAgreement getInstance() {
        try {
            return KeyAgreement.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(algorithmAssertionMsg, e);
        }
    }

    public static KeyAgreement getInstance(String str) throws NoSuchProviderException {
        try {
            return KeyAgreement.getInstance(ALGORITHM, str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(algorithmAssertionMsg, e);
        }
    }

    public static KeyAgreement getInstance(Provider provider) {
        try {
            return KeyAgreement.getInstance(ALGORITHM, provider);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(algorithmAssertionMsg, e);
        }
    }
}
