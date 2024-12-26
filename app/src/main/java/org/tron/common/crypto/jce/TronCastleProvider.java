package org.tron.common.crypto.jce;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
public final class TronCastleProvider {
    public static Provider getInstance() {
        return Holder.INSTANCE;
    }

    public static class Holder {
        private static final Provider INSTANCE;

        private Holder() {
        }

        static {
            Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
            Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
            if (provider == null) {
                provider = new BouncyCastleProvider();
            }
            INSTANCE = provider;
            provider.put("MessageDigest.TRON-KECCAK-256", "org.tron.common.crypto.cryptohash.Keccak256");
            provider.put("MessageDigest.TRON-KECCAK-512", "org.tron.common.crypto.cryptohash.Keccak512");
        }
    }
}
