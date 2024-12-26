package com.tron.wallet.business.confirm.fg.bean;

import com.google.gson.annotations.SerializedName;
import com.tron.wallet.common.config.TronConfig;
import java.util.List;
public class HotInfoBean {
    @SerializedName("hot_contracts")
    private List<HotContact> hotContacts;

    public List<HotContact> getHotContacts() {
        return this.hotContacts;
    }

    public void setHotContacts(List<HotContact> list) {
        this.hotContacts = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof HotInfoBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof HotInfoBean) {
            HotInfoBean hotInfoBean = (HotInfoBean) obj;
            if (hotInfoBean.canEqual(this)) {
                List<HotContact> hotContacts = getHotContacts();
                List<HotContact> hotContacts2 = hotInfoBean.getHotContacts();
                return hotContacts != null ? hotContacts.equals(hotContacts2) : hotContacts2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<HotContact> hotContacts = getHotContacts();
        return 59 + (hotContacts == null ? 43 : hotContacts.hashCode());
    }

    public String toString() {
        return "HotInfoBean(hotContacts=" + getHotContacts() + ")";
    }

    public static class HotContact {
        @SerializedName(TronConfig.CONTRACT_ADDRESS)
        private String contactAddress;

        public String getContactAddress() {
            return this.contactAddress;
        }

        public void setContactAddress(String str) {
            this.contactAddress = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof HotContact;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof HotContact) {
                HotContact hotContact = (HotContact) obj;
                if (hotContact.canEqual(this)) {
                    String contactAddress = getContactAddress();
                    String contactAddress2 = hotContact.getContactAddress();
                    return contactAddress != null ? contactAddress.equals(contactAddress2) : contactAddress2 == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String contactAddress = getContactAddress();
            return 59 + (contactAddress == null ? 43 : contactAddress.hashCode());
        }

        public String toString() {
            return "HotInfoBean.HotContact(contactAddress=" + getContactAddress() + ")";
        }
    }
}
