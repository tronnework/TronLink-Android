package com.tron.wallet.business.tabdapp.home.utils;

import android.os.Bundle;
import android.text.TextUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
public class DAppAnalyseUtils {
    public static void reportDAppHost(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        String host = DAppUrlUtils.getHost(str2);
        if (!TextUtils.isEmpty(host)) {
            str2 = host;
        }
        Bundle bundle = new Bundle();
        bundle.putString("dapp_url", str2);
        AnalyticsHelper.logEvent(str, bundle);
    }
}
