package org.tron.common.exceptions;

import org.tron.common.utils.abi.TronException;
public class StoreException extends TronException {
    public StoreException() {
    }

    public StoreException(String str) {
        super(str);
    }

    public StoreException(String str, Throwable th) {
        super(str, th);
    }
}
