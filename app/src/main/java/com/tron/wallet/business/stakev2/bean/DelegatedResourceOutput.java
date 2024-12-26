package com.tron.wallet.business.stakev2.bean;

import android.text.TextUtils;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import j$.util.Objects;
import java.util.List;
public class DelegatedResourceOutput extends BaseOutput {
    private List<DelegatedResource> data;

    public List<DelegatedResource> getData() {
        return this.data;
    }

    public void setData(List<DelegatedResource> list) {
        this.data = list;
    }

    @Override
    public String toString() {
        return "DelegatedResourceOutput(super=" + super.toString() + ", data=" + getData() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof DelegatedResourceOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DelegatedResourceOutput) {
            DelegatedResourceOutput delegatedResourceOutput = (DelegatedResourceOutput) obj;
            if (delegatedResourceOutput.canEqual(this) && super.equals(obj)) {
                List<DelegatedResource> data = getData();
                List<DelegatedResource> data2 = delegatedResourceOutput.getData();
                return data != null ? data.equals(data2) : data2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        List<DelegatedResource> data = getData();
        return (hashCode * 59) + (data == null ? 43 : data.hashCode());
    }

    public static boolean isValid(DelegatedResourceOutput delegatedResourceOutput) {
        return (delegatedResourceOutput.getCode() != 0 || delegatedResourceOutput.getData() == null || delegatedResourceOutput.getData().size() == 0) ? false : true;
    }

    public static class DelegatedResource {
        public static final String energy = "ENERGY";
        private long expireTime;
        private long frozenBalance;
        private boolean isSelect;
        private String ownerAddress;
        private String receiverAddress;
        private String resource;
        private String resourceValue;

        public long getExpireTime() {
            return this.expireTime;
        }

        public long getFrozenBalance() {
            return this.frozenBalance;
        }

        public String getOwnerAddress() {
            return this.ownerAddress;
        }

        public String getReceiverAddress() {
            return this.receiverAddress;
        }

        public String getResource() {
            return this.resource;
        }

        public String getResourceValue() {
            return this.resourceValue;
        }

        public boolean isSelect() {
            return this.isSelect;
        }

        public void setExpireTime(long j) {
            this.expireTime = j;
        }

        public void setFrozenBalance(long j) {
            this.frozenBalance = j;
        }

        public void setOwnerAddress(String str) {
            this.ownerAddress = str;
        }

        public void setReceiverAddress(String str) {
            this.receiverAddress = str;
        }

        public void setResource(String str) {
            this.resource = str;
        }

        public void setResourceValue(String str) {
            this.resourceValue = str;
        }

        public void setSelect(boolean z) {
            this.isSelect = z;
        }

        public String toString() {
            return "DelegatedResourceOutput.DelegatedResource(ownerAddress=" + getOwnerAddress() + ", receiverAddress=" + getReceiverAddress() + ", resource=" + getResource() + ", frozenBalance=" + getFrozenBalance() + ", expireTime=" + getExpireTime() + ", resourceValue=" + getResourceValue() + ", isSelect=" + isSelect() + ")";
        }

        public boolean equals(Object obj) {
            if (obj instanceof DelegatedResource) {
                DelegatedResource delegatedResource = (DelegatedResource) obj;
                return TextUtils.equals(this.ownerAddress, delegatedResource.ownerAddress) && TextUtils.equals(this.receiverAddress, delegatedResource.receiverAddress) && TextUtils.equals(this.resource, delegatedResource.resource) && TextUtils.equals(this.resourceValue, delegatedResource.resourceValue) && this.frozenBalance == delegatedResource.frozenBalance && this.expireTime == delegatedResource.expireTime;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.ownerAddress, this.receiverAddress, this.resource, Long.valueOf(this.frozenBalance), Long.valueOf(this.expireTime), this.resourceValue);
        }
    }
}
