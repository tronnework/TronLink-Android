package org.tron.common.crypto.jce;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Signature;
import org.tron.common.utils.AnalyseUtil;
public final class ECSignatureFactory {
    public static final String RAW_ALGORITHM = "NONEwithECDSA";
    private static final String rawAlgorithmAssertionMsg = "Assumed the JRE supports NONEwithECDSA signatures";

    private ECSignatureFactory() {
    }

    public static Signature getRawInstance() {
        try {
            return Signature.getInstance(RAW_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            AnalyseUtil.log(e);
            throw new AssertionError(rawAlgorithmAssertionMsg, e);
        }
    }

    public static Signature getRawInstance(String str) throws NoSuchProviderException {
        try {
            return Signature.getInstance(RAW_ALGORITHM, str);
        } catch (NoSuchAlgorithmException e) {
            AnalyseUtil.log(e);
            throw new AssertionError(rawAlgorithmAssertionMsg, e);
        }
    }

    public static Signature getRawInstance(Provider provider) {
        try {
            return Signature.getInstance(RAW_ALGORITHM, provider);
        } catch (NoSuchAlgorithmException e) {
            AnalyseUtil.log(e);
            throw new AssertionError(rawAlgorithmAssertionMsg, e);
        }
    }
}
