package org.tron.common.utils.abi;
public class TronException extends Exception {
    public TronException() {
    }

    public TronException(String str) {
        super(str);
    }

    public TronException(String str, Throwable th) {
        super(str, th);
    }
}
