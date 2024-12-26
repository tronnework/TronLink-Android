package org.tron.common.exceptions;
public class BadItemException extends StoreException {
    public BadItemException() {
    }

    public BadItemException(String str) {
        super(str);
    }

    public BadItemException(String str, Throwable th) {
        super(str, th);
    }
}
