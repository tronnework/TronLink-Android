package org.tron.common.crypto.jce;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.tron.common.utils.AnalyseUtil;
public final class ECAlgorithmParameters {
    public static final String ALGORITHM = "EC";
    public static final String CURVE_NAME = "secp256k1";

    private ECAlgorithmParameters() {
    }

    public static ECParameterSpec getParameterSpec() {
        try {
            return (ECParameterSpec) Holder.INSTANCE.getParameterSpec(ECParameterSpec.class);
        } catch (InvalidParameterSpecException e) {
            AnalyseUtil.log(e);
            throw new AssertionError("Assumed correct key spec statically", e);
        }
    }

    public static byte[] getASN1Encoding() {
        try {
            return Holder.INSTANCE.getEncoded();
        } catch (IOException e) {
            AnalyseUtil.log(e);
            throw new AssertionError("Assumed algo params has been initialized", e);
        }
    }

    private static class Holder {
        private static final AlgorithmParameters INSTANCE;
        private static final ECGenParameterSpec SECP256K1_CURVE;

        private Holder() {
        }

        static {
            ECGenParameterSpec eCGenParameterSpec = new ECGenParameterSpec("secp256k1");
            SECP256K1_CURVE = eCGenParameterSpec;
            try {
                AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("EC");
                INSTANCE = algorithmParameters;
                algorithmParameters.init(eCGenParameterSpec);
            } catch (NoSuchAlgorithmException e) {
                throw new AssertionError("Assumed the JRE supports EC algorithm params", e);
            } catch (InvalidParameterSpecException e2) {
                throw new AssertionError("Assumed correct key spec statically", e2);
            }
        }
    }
}
