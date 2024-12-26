package com.tron.wallet.business.security.check.environment;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.SignCheck;
public class OfficialCheck {
    public static ResultStatusEnum getResult() {
        try {
            return new SignCheck(AppContextUtil.getContext(), TronConfig.cerSha1).check() ? ResultStatusEnum.Safe : ResultStatusEnum.Risk;
        } catch (Exception e) {
            LogUtils.e(e);
            return ResultStatusEnum.Error;
        }
    }
}
