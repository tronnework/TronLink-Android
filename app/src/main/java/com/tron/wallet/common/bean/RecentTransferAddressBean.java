package com.tron.wallet.common.bean;

import java.util.ArrayList;
public class RecentTransferAddressBean {
    public ArrayList<NameAddressBean> dataBean = new ArrayList<>();

    public ArrayList<NameAddressBean> getDataBean() {
        return this.dataBean;
    }

    public void setDataBean(ArrayList<NameAddressBean> arrayList) {
        this.dataBean = arrayList;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RecentTransferAddressBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RecentTransferAddressBean) {
            RecentTransferAddressBean recentTransferAddressBean = (RecentTransferAddressBean) obj;
            if (recentTransferAddressBean.canEqual(this)) {
                ArrayList<NameAddressBean> dataBean = getDataBean();
                ArrayList<NameAddressBean> dataBean2 = recentTransferAddressBean.getDataBean();
                return dataBean != null ? dataBean.equals(dataBean2) : dataBean2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        ArrayList<NameAddressBean> dataBean = getDataBean();
        return 59 + (dataBean == null ? 43 : dataBean.hashCode());
    }

    public String toString() {
        return "RecentTransferAddressBean(dataBean=" + getDataBean() + ")";
    }
}
