package com.tron.wallet.business.pull;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.common.config.ErrorConstant;
public enum PullResultCode {
    SUCCESS(0, FirebaseAnalytics.Param.SUCCESS),
    FAIL_TO_PARSE_JSON(10001, "Incorrect JSON format"),
    FAIL_NO_ACTION(ErrorConstant.triggerError, "Missing Action"),
    FAIL_NOT_SUPPORT_ACTION(ErrorConstant.buildParamsError, "Unknown Action"),
    FAIL_NO_ACTION_ID(ErrorConstant.broadcastError, "Missing ActionId"),
    FAIL_INVALID_URL(ErrorConstant.scanQRError, "Incorrect DApp URL format"),
    FAIL_INVALID_CALLBACK_URL(ErrorConstant.cancelError, "Incorrect CallbackUrl format"),
    FAIL_NULL_DAPP_NAME(ErrorConstant.signShieldParamsError, "Empty DApp name"),
    FAIL_NOT_SUPPORT_VERSION(ErrorConstant.emptyNodeError, "Version number not supported"),
    FAIL_NOT_SUPPORT_NET(ErrorConstant.emptyEmptyAddressError, "Current network not supported"),
    FAIL_NOT_SUPPORT_DAPP(10010, "The URL is not supported to open TronLink"),
    FAIL_UNKNOW_SIGN_TYPE(10011, "Unknown SignType"),
    FAIL_INVALID_TRANSACTION_DATA(10012, "Incorrect Transaction format"),
    FAIL_INVALID_METHOD(10013, "Incorrect Method format"),
    FAIL_INVALID_MESSAGE(10014, "Incorrect Message format"),
    FAIL_INVALID_TO_ADDRESS(10015, "Incorrect toAddress"),
    FAIL_NO_FROM_ADDRESS(10016, "No wallet created in TronLink"),
    FAIL_INVALID_FROM_ADDRESS(10017, "Incorrect fromAddress"),
    FAIL_INVALID_CONTRACT_ADDRESS(10018, "Incorrect contactAddress"),
    FAIL_INVALID_CHAIN_ID(10019, "Incorrect chainId"),
    FAIL_INVALID_AMOUNT(10020, "Incorrect amount"),
    FAIL_ADDRESS_MISMATCH(10021, "The initiating address does not match the current wallet"),
    FAIL_INVALID_LOGIN_ADDRESS(10022, "Incorrect loginAddress"),
    FAIL_NOT_SUPPORT_SYSTEM_CONTRACT(10023, "System contract not support"),
    FAIL_INVALID_TOKEN_ID(10024, "Incorrect tokenId"),
    FAIL_INVALID_CONTRACT_TOKEN_TRANSFERED(10025, "TokenId & Contract address should not be exist together"),
    FAIL_FROM_TO_CANNOT_BE_SAME(10026, "fromAddress is the same as toAddress"),
    FAIL_CANCEL(300, "Action canceled"),
    FAIL_OPEN_IN_TRON(301, "DApp opened in TronLink"),
    FAIL_TRANSACTION_BROADCAST_FAIL(302, "Broadcast fail"),
    FAIL_UNKNOW_REASON(-1, "Unknown reason");
    
    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    PullResultCode(int i, String str) {
        this.code = i;
        this.message = str;
    }
}
