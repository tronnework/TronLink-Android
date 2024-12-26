package com.tron.wallet.business.addassets2.bean;
public class KVBean {
    public Long id;
    private String key;
    private String value;

    public Long getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public KVBean(Long l, String str, String str2) {
        this.id = l;
        this.key = str;
        this.value = str2;
    }

    public KVBean() {
    }
}
