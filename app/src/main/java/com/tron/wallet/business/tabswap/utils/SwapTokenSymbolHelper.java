package com.tron.wallet.business.tabswap.utils;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.db.SpAPI;
public class SwapTokenSymbolHelper {
    private static final String ETH_OLD_CONTRACT = "THb4CqiFdwNHsWsQCs4JhzwjMWys4aqCbF";
    private static final String SUN_OLD_CONTRACT = "TKkeiboTkxXKJpbmVFbv4a8ov5rAfRDMf9";

    public static String getSymbol(String str, String str2) {
        return ((IRequest.isPrerelease() || IRequest.isRelease()) && SpAPI.THIS.getCurIsMainChain()) ? TextUtils.equals(str2, SUN_OLD_CONTRACT) ? "SUNOLD" : TextUtils.equals(str2, ETH_OLD_CONTRACT) ? "ETHOLD" : str : str;
    }
}
