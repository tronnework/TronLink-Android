package com.tron.wallet.ledger.blemodule;
public interface OnEventCallback<T> {
    void onEvent(T t);
}
