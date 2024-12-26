package com.tron.wallet.ledger.blemodule;
public enum ConnectionState {
    CONNECTING("connecting"),
    CONNECTED("connected"),
    DISCONNECTING("disconnecting"),
    DISCONNECTED("disconnected");
    
    public final String value;

    ConnectionState(String str) {
        this.value = str;
    }
}
