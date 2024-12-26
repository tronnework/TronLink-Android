package com.tron.wallet.business.security.check.environment;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.business.security.check.emulator.EmulatorCheckUtils;
public class SimulatorCheck {
    public static ResultStatusEnum getResult() {
        try {
            return EmulatorCheckUtils.checkEmulator(AppContextUtil.getContext(), new EmulatorCheckUtils.OnEmulatorCheckCallback() {
                @Override
                public final void onEmulatorResult(String str) {
                    LogUtils.w("SimulatorCheck", str);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
            return ResultStatusEnum.Error;
        }
    }
}
