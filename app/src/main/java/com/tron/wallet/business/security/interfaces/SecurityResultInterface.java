package com.tron.wallet.business.security.interfaces;

import com.tron.wallet.business.security.SecurityResult;
public interface SecurityResultInterface {
    void onThreadReturnResult(SecurityResult securityResult);
}
