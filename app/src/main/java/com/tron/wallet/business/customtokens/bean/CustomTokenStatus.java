package com.tron.wallet.business.customtokens.bean;

import com.tronlinkpro.wallet.R;
public class CustomTokenStatus {
    public static final String ALLOWANCE = "allowance";
    public static final String APPROVE = "approve";
    public static final String APPROVE_EVENT = "Approval";
    public static final int HAD_ADDED = 4;
    public static final int HAD_RECORDED = 3;
    public static final int INVALID_ADDRESS = 1;
    public static final int LACK_FUNCTIONS = 5;
    public static final int NORMAL = 0;
    public static final int NOT_EXIST = 2;
    public static final String TOTAL_SUPPLY = "totalSupply";
    public static final String TRANSFER = "transfer";
    public static final String TRANSFER_FROM = "transferFrom";

    public static int getNoFunctionErrorStrId(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2143922635:
                if (str.equals(TRANSFER_FROM)) {
                    c = 0;
                    break;
                }
                break;
            case -793050291:
                if (str.equals(APPROVE)) {
                    c = 1;
                    break;
                }
                break;
            case 372414488:
                if (str.equals(ALLOWANCE)) {
                    c = 2;
                    break;
                }
                break;
            case 1249888867:
                if (str.equals(APPROVE_EVENT)) {
                    c = 3;
                    break;
                }
                break;
            case 1280882667:
                if (str.equals(TRANSFER)) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return R.string.token_no_transfer_from_function;
            case 1:
                return R.string.token_no_approve_function;
            case 2:
                return R.string.token_no_allowance_function;
            case 3:
                return R.string.token_no_approve_event;
            case 4:
                return R.string.token_no_transfer_function;
            default:
                return 0;
        }
    }
}
