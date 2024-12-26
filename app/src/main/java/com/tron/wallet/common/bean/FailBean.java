package com.tron.wallet.common.bean;
public class FailBean {
    private String failLog;
    private String ownerAddress;
    private String status;
    private String transaction;

    public String getFailLog() {
        return this.failLog;
    }

    public String getOwnerAddress() {
        return this.ownerAddress;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTransaction() {
        return this.transaction;
    }

    public void setFailLog(String str) {
        this.failLog = str;
    }

    public void setOwnerAddress(String str) {
        this.ownerAddress = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTransaction(String str) {
        this.transaction = str;
    }
}
