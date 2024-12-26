package com.tron.wallet.business.security.check;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.security.ExecuteStatusEnum;
import com.tron.wallet.business.security.ResultStatusEnum;
import com.tron.wallet.business.security.SecurityResult;
import com.tron.wallet.business.security.check.environment.ClipboardCheck;
import com.tron.wallet.business.security.check.environment.NetCheck;
import com.tron.wallet.business.security.check.environment.OfficialCheck;
import com.tron.wallet.business.security.check.environment.RootCheck;
import com.tron.wallet.business.security.check.environment.SimulatorCheck;
import com.tron.wallet.business.security.interfaces.SecurityInterface;
import com.tron.wallet.business.security.interfaces.SecurityResultInterface;
public class EnvironmentCheck implements SecurityInterface {
    private ResultStatusEnum clipboardResult;
    private ResultStatusEnum netResult;
    private ResultStatusEnum officialResult;
    private ResultStatusEnum rootResult;
    private SecurityResult securityResult;
    private SecurityResultInterface securityResultInterface;
    private ResultStatusEnum simulatorResult;
    private ExecuteStatusEnum executeStatusEnum = ExecuteStatusEnum.NotStart;
    private boolean itemError = false;
    Runnable runnable = new Runnable() {
        @Override
        public final void run() {
            lambda$new$0();
        }
    };

    @Override
    public ExecuteStatusEnum getExecuteStatus() {
        return this.executeStatusEnum;
    }

    @Override
    public SecurityResult getResult() {
        return this.securityResult;
    }

    @Override
    public void securityOnDestroy() {
    }

    @Override
    public void setSecurityResultInterface(SecurityResultInterface securityResultInterface) {
        this.securityResultInterface = securityResultInterface;
    }

    public void lambda$new$0() {
        SecurityResultInterface securityResultInterface;
        check();
        this.securityResult = new SecurityResult();
        SecurityResult.EnvironmentCheckData environmentCheckData = new SecurityResult.EnvironmentCheckData();
        environmentCheckData.setRootCheck(this.rootResult);
        environmentCheckData.setSimulatorCheck(this.simulatorResult);
        environmentCheckData.setNetCheck(this.netResult);
        environmentCheckData.setClipboardCheck(this.clipboardResult);
        environmentCheckData.setOfficialCheck(this.officialResult);
        this.securityResult.setEnvironmentCheckData(environmentCheckData);
        setRiskNum(this.rootResult, this.simulatorResult, this.netResult, this.clipboardResult, this.officialResult);
        ExecuteStatusEnum executeStatusEnum = this.itemError ? ExecuteStatusEnum.Error : ExecuteStatusEnum.Success;
        this.executeStatusEnum = executeStatusEnum;
        this.securityResult.setExecuteStatusEnum(executeStatusEnum);
        SecurityResult securityResult = this.securityResult;
        if (securityResult == null || (securityResultInterface = this.securityResultInterface) == null) {
            return;
        }
        securityResultInterface.onThreadReturnResult(securityResult);
    }

    private void check() {
        this.executeStatusEnum = ExecuteStatusEnum.Detecting;
        try {
            this.rootResult = RootCheck.getResult();
        } catch (Exception e) {
            this.rootResult = ResultStatusEnum.Error;
            this.itemError = true;
            LogUtils.e(e);
        }
        try {
            this.simulatorResult = SimulatorCheck.getResult();
        } catch (Exception e2) {
            LogUtils.e(e2);
            this.itemError = true;
            this.simulatorResult = ResultStatusEnum.Error;
        }
        try {
            this.netResult = NetCheck.getResult();
        } catch (Exception e3) {
            LogUtils.e(e3);
            this.itemError = true;
            this.netResult = ResultStatusEnum.Error;
        }
        try {
            this.officialResult = OfficialCheck.getResult();
        } catch (Exception e4) {
            LogUtils.e(e4);
            this.itemError = true;
            this.officialResult = ResultStatusEnum.Error;
        }
    }

    public void setRiskNum(ResultStatusEnum... resultStatusEnumArr) {
        int i;
        int i2;
        if (resultStatusEnumArr == null || resultStatusEnumArr.length == 0) {
            return;
        }
        int i3 = 0;
        try {
            int length = resultStatusEnumArr.length;
            i2 = 0;
            i = 0;
            while (i3 < length) {
                try {
                    ResultStatusEnum resultStatusEnum = resultStatusEnumArr[i3];
                    if (resultStatusEnum == ResultStatusEnum.Risk) {
                        i2++;
                    } else if (resultStatusEnum == ResultStatusEnum.Waring) {
                        i++;
                    } else if (resultStatusEnum == ResultStatusEnum.Error) {
                        this.itemError = true;
                    }
                    i3++;
                } catch (Exception e) {
                    e = e;
                    i3 = i2;
                    LogUtils.e(e);
                    i2 = i3;
                    this.securityResult.setRiskNum(i2);
                    this.securityResult.setWaringNum(i);
                }
            }
        } catch (Exception e2) {
            e = e2;
            i = 0;
        }
        this.securityResult.setRiskNum(i2);
        this.securityResult.setWaringNum(i);
    }

    @Override
    public void securityStart() {
        try {
            this.clipboardResult = ClipboardCheck.getResult();
        } catch (Exception e) {
            LogUtils.e(e);
            this.itemError = true;
            this.clipboardResult = ResultStatusEnum.Error;
        }
        ThreadPoolManager.newInstance().addExecuteTask(this.runnable);
    }

    @Override
    public void securityStop() {
        if (this.executeStatusEnum == ExecuteStatusEnum.Success) {
            return;
        }
        this.executeStatusEnum = ExecuteStatusEnum.Suspend;
    }
}
