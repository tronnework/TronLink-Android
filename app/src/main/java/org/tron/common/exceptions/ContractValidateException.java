package org.tron.common.exceptions;

import org.tron.common.utils.abi.TronException;
public class ContractValidateException extends TronException {
    public ContractValidateException() {
    }

    public ContractValidateException(String str) {
        super(str);
    }

    public ContractValidateException(String str, Throwable th) {
        super(str, th);
    }
}
