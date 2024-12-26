package com.tron.wallet.db.Controller;
public class DappBlackBean {
    public Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setName(String str) {
        this.name = str;
    }

    public DappBlackBean(Long l, String str) {
        this.id = l;
        this.name = str;
    }

    public DappBlackBean() {
    }
}
