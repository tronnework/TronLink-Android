package org.tron.common.bip39;
public class ValidationException extends Exception {
    private static final long serialVersionUID = 1;

    public ValidationException(Throwable th) {
        super(th);
    }

    public ValidationException(String str, Throwable th) {
        super(str, th);
    }

    public ValidationException(String str) {
        super(str);
    }
}
