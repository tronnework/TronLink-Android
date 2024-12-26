package com.tron.wallet.business.tabswap.utils;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.bean.DappJsOutput;
import com.tron.wallet.db.SpAPI;
import j$.util.Map;
import java.util.Map;
public class SwapFeeHelper {
    public static String getPoolFee(String str) {
        Map<String, String> swapFee;
        String dappJSOutput = SpAPI.THIS.getDappJSOutput();
        if (!TextUtils.isEmpty(dappJSOutput)) {
            try {
                DappJsOutput dappJsOutput = (DappJsOutput) JSON.parseObject(dappJSOutput, DappJsOutput.class);
                if (dappJsOutput != null && dappJsOutput.getData() != null && (swapFee = dappJsOutput.getData().getSwapFee()) != null) {
                    return (String) Map.-EL.getOrDefault(swapFee, str, "0");
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return "0";
    }
}
