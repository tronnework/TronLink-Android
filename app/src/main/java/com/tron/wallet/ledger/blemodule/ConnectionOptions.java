package com.tron.wallet.ledger.blemodule;
public class ConnectionOptions {
    private boolean autoConnect;
    private int connectionPriority;
    private RefreshGattMoment refreshGattMoment;
    private int requestMTU;
    private Long timeoutInMillis;

    public int getConnectionPriority() {
        return this.connectionPriority;
    }

    public RefreshGattMoment getRefreshGattMoment() {
        return this.refreshGattMoment;
    }

    public int getRequestMTU() {
        return this.requestMTU;
    }

    public Long getTimeoutInMillis() {
        return this.timeoutInMillis;
    }

    public ConnectionOptions(Boolean bool, int i, RefreshGattMoment refreshGattMoment, Long l, int i2) {
        this.autoConnect = bool.booleanValue();
        this.requestMTU = i;
        this.refreshGattMoment = refreshGattMoment;
        this.timeoutInMillis = l;
        this.connectionPriority = i2;
    }

    public Boolean getAutoConnect() {
        return Boolean.valueOf(this.autoConnect);
    }
}
