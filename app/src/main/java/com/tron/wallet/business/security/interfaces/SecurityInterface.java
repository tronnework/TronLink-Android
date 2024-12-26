package com.tron.wallet.business.security.interfaces;

import com.tron.wallet.business.security.ExecuteStatusEnum;
import com.tron.wallet.business.security.SecurityResult;
public interface SecurityInterface {
    ExecuteStatusEnum getExecuteStatus();

    SecurityResult getResult();

    void securityOnDestroy();

    void securityStart();

    void securityStop();

    void setSecurityResultInterface(SecurityResultInterface securityResultInterface);
}
