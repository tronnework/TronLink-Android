package org.tron.common.crypto.jce;

import android.os.Build;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import org.tron.common.utils.LogUtils;
public final class ECKeyFactory {
    public static final String ALGORITHM = "ECDSA";
    private static final String algorithmAssertionMsg = "Assumed the JRE supports EC key factories";

    private ECKeyFactory() {
    }

    public static KeyFactory getInstance() {
        return Holder.INSTANCE;
    }

    public static KeyFactory getInstance(Provider provider) {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                return KeyFactory.getInstance(ALGORITHM);
            }
            return KeyFactory.getInstance(ALGORITHM, provider);
        } catch (NoSuchAlgorithmException e) {
            try {
                return KeyFactory.getInstance(ALGORITHM, provider);
            } catch (NoSuchAlgorithmException e2) {
                LogUtils.e(e2);
                throw new AssertionError(algorithmAssertionMsg, e);
            }
        }
    }

    private static class Holder {
        private static final KeyFactory INSTANCE;

        private Holder() {
        }

        static {
            try {
                INSTANCE = KeyFactory.getInstance(ECKeyFactory.ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
                throw new AssertionError(ECKeyFactory.algorithmAssertionMsg, e);
            }
        }
    }
}
