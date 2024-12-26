package com.tron.wallet.business.nft.nfttransactiondetail;

import com.tron.wallet.common.bean.NameAddressExtraBean;
public class NameAddressResultBean {
    NameAddressExtraBean nameAddressExtraBean;

    public NameAddressExtraBean getNameAddressExtraBean() {
        return this.nameAddressExtraBean;
    }

    public void setNameAddressExtraBean(NameAddressExtraBean nameAddressExtraBean) {
        this.nameAddressExtraBean = nameAddressExtraBean;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NameAddressResultBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NameAddressResultBean) {
            NameAddressResultBean nameAddressResultBean = (NameAddressResultBean) obj;
            if (nameAddressResultBean.canEqual(this)) {
                NameAddressExtraBean nameAddressExtraBean = getNameAddressExtraBean();
                NameAddressExtraBean nameAddressExtraBean2 = nameAddressResultBean.getNameAddressExtraBean();
                return nameAddressExtraBean != null ? nameAddressExtraBean.equals((Object) nameAddressExtraBean2) : nameAddressExtraBean2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        NameAddressExtraBean nameAddressExtraBean = getNameAddressExtraBean();
        return 59 + (nameAddressExtraBean == null ? 43 : nameAddressExtraBean.hashCode());
    }

    public String toString() {
        return "NameAddressResultBean(nameAddressExtraBean=" + getNameAddressExtraBean() + ")";
    }

    public NameAddressResultBean(NameAddressExtraBean nameAddressExtraBean) {
        this.nameAddressExtraBean = nameAddressExtraBean;
    }
}
