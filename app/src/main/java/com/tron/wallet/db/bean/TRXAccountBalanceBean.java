package com.tron.wallet.db.bean;
public class TRXAccountBalanceBean {
    private int accountType;
    private String address;
    private String chainId;
    private Long id;
    private String netType;
    private double totalTrx;
    private double trxNum;

    public int getAccountType() {
        return this.accountType;
    }

    public String getAddress() {
        return this.address;
    }

    public String getChainId() {
        return this.chainId;
    }

    public Long getId() {
        return this.id;
    }

    public String getNetType() {
        return this.netType;
    }

    public double getTotalTrx() {
        return this.totalTrx;
    }

    public double getTrxNum() {
        return this.trxNum;
    }

    public void setAccountType(int i) {
        this.accountType = i;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setChainId(String str) {
        this.chainId = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setNetType(String str) {
        this.netType = str;
    }

    public void setTotalTrx(double d) {
        this.totalTrx = d;
    }

    public void setTrxNum(double d) {
        this.trxNum = d;
    }

    public TRXAccountBalanceBean(Long l, String str, double d, double d2, String str2, int i, String str3) {
        this.id = l;
        this.address = str;
        this.totalTrx = d;
        this.trxNum = d2;
        this.chainId = str2;
        this.accountType = i;
        this.netType = str3;
    }

    public TRXAccountBalanceBean() {
    }
}
