package com.tron.wallet.common.bean.update;
public class UploadHistoryResponse {
    private String address;
    private int dapp_id;
    private String dapp_name;

    public String getAddress() {
        return this.address;
    }

    public int getDapp_id() {
        return this.dapp_id;
    }

    public String getDapp_name() {
        return this.dapp_name;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setDapp_id(int i) {
        this.dapp_id = i;
    }

    public void setDapp_name(String str) {
        this.dapp_name = str;
    }

    public String toString() {
        return "UploadHistoryResponse{address='" + this.address + "', dapp_id=" + this.dapp_id + ", dapp_name='" + this.dapp_name + "'}";
    }
}
