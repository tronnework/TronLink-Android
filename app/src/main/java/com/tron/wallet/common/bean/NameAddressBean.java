package com.tron.wallet.common.bean;

import j$.util.Objects;
public class NameAddressBean {
    String address;
    String name;

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "NameAddressBean(name=" + getName() + ", address=" + getAddress() + ")";
    }

    public NameAddressBean() {
    }

    public NameAddressBean(String str) {
        this.address = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NameAddressBean nameAddressBean = (NameAddressBean) obj;
        return Objects.equals(this.name, nameAddressBean.name) && Objects.equals(this.address, nameAddressBean.address);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.address);
    }
}
