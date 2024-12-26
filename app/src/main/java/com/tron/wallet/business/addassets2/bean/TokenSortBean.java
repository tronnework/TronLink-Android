package com.tron.wallet.business.addassets2.bean;
public class TokenSortBean {
    private String address;
    private String contractAddress;
    public Long id;
    private long index;
    private int type;

    public String getAddress() {
        return this.address;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public Long getId() {
        return this.id;
    }

    public long getIndex() {
        return this.index;
    }

    public int getType() {
        return this.type;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setIndex(long j) {
        this.index = j;
    }

    public void setType(int i) {
        this.type = i;
    }

    public TokenSortBean(Long l, int i, long j, String str, String str2) {
        this.id = l;
        this.type = i;
        this.index = j;
        this.contractAddress = str;
        this.address = str2;
    }

    public TokenSortBean() {
    }
}
