package com.tron.wallet.business.security.check.environment;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.common.utils.ProxyCheckUtil;
public class NetCheck {
    public static ResultStatusEnum getResult() {
        try {
            if (ProxyCheckUtil.isWifiProxy(AppContextUtil.getContext())) {
                return ResultStatusEnum.Safe;
            }
            return ResultStatusEnum.Waring;
        } catch (Exception e) {
            LogUtils.e(e);
            return ResultStatusEnum.Error;
        }
    }
}
