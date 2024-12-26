package com.tron.wallet.business.security.check.environment;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.common.secure.RootBeer;
public class RootCheck {
    public static ResultStatusEnum getResult() {
        if (new RootBeer(AppContextUtil.getContext()).isRooted()) {
            return ResultStatusEnum.Risk;
        }
        return ResultStatusEnum.Safe;
    }
}
