package com.tron.wallet.business.pull;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.common.utils.StringTronUtil;
public enum PullAction {
    ACTION_OPEN_DAPP("open"),
    ACTION_LOGIN(FirebaseAnalytics.Event.LOGIN),
    ACTION_SIGN("sign"),
    ACTION_TRANSFER(CustomTokenStatus.TRANSFER),
    NOT_SUPPORT("not support");
    
    private final String action;

    public String getAction() {
        return this.action;
    }

    PullAction(String str) {
        this.action = str;
    }

    public static PullAction fromAction(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return NOT_SUPPORT;
        }
        PullAction pullAction = ACTION_OPEN_DAPP;
        if (pullAction.getAction().equals(str)) {
            return pullAction;
        }
        PullAction pullAction2 = ACTION_LOGIN;
        if (pullAction2.getAction().equals(str)) {
            return pullAction2;
        }
        PullAction pullAction3 = ACTION_TRANSFER;
        if (pullAction3.getAction().equals(str)) {
            return pullAction3;
        }
        PullAction pullAction4 = ACTION_SIGN;
        return pullAction4.getAction().equals(str) ? pullAction4 : NOT_SUPPORT;
    }
}
