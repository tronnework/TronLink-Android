package org.tron.config;

import com.facebook.stetho.dumpapp.Framer;
public interface Parameter {

    public interface CommonConstant {
        public static final int ADDRESS_SIZE = 21;
        public static final byte ADD_PRE_FIX_BYTE = 65;
        public static final String ADD_PRE_FIX_STRING = "41";
        public static final int BASE58CHECK_ADDRESS_SIZE = 35;
    }

    public interface CreateWalletType {
        public static final int TYPE_CREATE_WALLET = 0;
        public static final int TYPE_IMPORT_COLD = 9;
        public static final int TYPE_IMPORT_KEYSTORE = 3;
        public static final int TYPE_IMPORT_LEDGER = 8;
        public static final int TYPE_IMPORT_MNEMONIC = 1;
        public static final int TYPE_IMPORT_MNEMONIC_HD = 5;
        public static final int TYPE_IMPORT_MNEMONIC_NO_HD = 6;
        public static final int TYPE_IMPORT_OBSERVED = 4;
        public static final int TYPE_IMPORT_PRIKEY = 2;
        public static final int TYPE_IMPORT_SAMSUNG_HD = 7;
    }

    public static class NetConstant {
        public static int triggerType = 1;
    }

    public interface ResConstant {
        public static final int BANDWIDTH_COST = 70;
        public static final int SIGNATURE_COST = 65;
        public static final double feeBandWidth = 0.001d;
        public static final long feeLimit = 225000000;
    }

    public interface ShieldConstant {
        public static final String PAYMENT_ADDRESS_FORMAT_WRONG = "paymentAddress format is wrong";
        public static final int ZC_DIVERSIFIER_SIZE = 11;
        public static final int ZC_OUTPUT_DESC_MAX_SIZE = 10;
        public static final byte[] ZTRON_EXPANDSEED_PERSONALIZATION = {90, 116, 114, 111, 110, Framer.STDIN_REQUEST_FRAME_PREFIX, 69, Framer.EXIT_FRAME_PREFIX, 112, 97, 110, 100, 83, 101, 101, 100};
    }
}
