package org.tron.walletserver;
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String str) {
        super(str);
    }
}
