package org.tron.walletserver;

import org.tron.common.utils.abi.TronException;
public class PermissionException extends TronException {
    public PermissionException() {
    }

    public PermissionException(String str) {
        super(str);
    }
}
