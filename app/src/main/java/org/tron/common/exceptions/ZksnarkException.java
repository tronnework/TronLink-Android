package org.tron.common.exceptions;

import org.tron.common.utils.abi.TronException;
public class ZksnarkException extends TronException {
    public ZksnarkException() {
    }

    public ZksnarkException(String str) {
        super(str);
    }
}
