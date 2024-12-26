package com.tron.wallet.common.config;

import com.tron.wallet.business.message.TransactionMessage;
import com.tronlinkpro.wallet.R;
public class ContractReturnMessage {
    public static int getMessageResource(String str) {
        char c;
        switch (str.hashCode()) {
            case -2072786018:
                if (str.equals("ILLEGAL_OPERATION")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1881023004:
                if (str.equals(TransactionMessage.TYPE_REVERT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -967469255:
                if (str.equals("STACK_TOO_LARGE")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -285133281:
                if (str.equals("OUT_OF_ENERGY")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -64175976:
                if (str.equals("OUT_OF_MEMORY")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 299586596:
                if (str.equals("OUT_OF_TIME")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 433141802:
                if (str.equals("UNKNOWN")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 668282641:
                if (str.equals("TRANSFER_FAILED")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return R.string.FAIL_REVERT;
            case 1:
                return R.string.FAIL_OUT_OF_ENERGY;
            case 2:
                return R.string.FAIL_OUT_OF_TIME;
            case 3:
                return R.string.FAIL_ILLEGAL_OPERATION;
            case 4:
                return R.string.FAIL_TRANSFER_FAILED;
            case 5:
                return R.string.FAIL_OUT_OF_MEMORY;
            case 6:
                return R.string.FAIL_STACK_TOO_LARGE;
            default:
                return R.string.FAIL_UNKNOWN;
        }
    }
}
