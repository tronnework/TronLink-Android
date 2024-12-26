package org.tron.net.exceptions;

import org.tron.common.utils.abi.TronException;
public class ZTronException extends TronException {
    public ZTronException() {
    }

    public ZTronException(String str) {
        super(str);
    }

    public ZTronException(String str, Throwable th) {
        super(str, th);
    }
}
