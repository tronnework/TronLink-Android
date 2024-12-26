package com.tron.wallet.business.tronpower.unstake.adapter;

import android.text.TextUtils;
import com.tron.wallet.business.stakev2.bean.DelegatedResourceOutput;
public class UnStakeResourceBean {
    String address;
    long expiredTime;
    Group group;
    String name;
    DelegatedResourceOutput.DelegatedResource source;
    int state;
    long trxCount;
    Type type;

    public enum Group {
        SELF,
        OTHER
    }

    public interface State {
        public static final int MASK = -65536;
        public static final int NORMAL = 65536;
        public static final int SELECTED = 131072;
        public static final int SHIFT = 16;
        public static final int UNCHECKABLE = 1;
        public static final int UNEXPIRED = 196608;
    }

    public enum Type {
        BANDWIDTH,
        ENERGY
    }

    public String getAddress() {
        return this.address;
    }

    public long getExpiredTime() {
        return this.expiredTime;
    }

    public Group getGroup() {
        return this.group;
    }

    public String getName() {
        return this.name;
    }

    public DelegatedResourceOutput.DelegatedResource getSource() {
        return this.source;
    }

    public int getState() {
        return this.state;
    }

    public long getTrxCount() {
        return this.trxCount;
    }

    public Type getType() {
        return this.type;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setExpiredTime(long j) {
        this.expiredTime = j;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSource(DelegatedResourceOutput.DelegatedResource delegatedResource) {
        this.source = delegatedResource;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void setTrxCount(long j) {
        this.trxCount = j;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public UnStakeResourceBean() {
    }

    public UnStakeResourceBean(long j, String str, int i, Group group, Type type, String str2, DelegatedResourceOutput.DelegatedResource delegatedResource, long j2) {
        this.trxCount = j;
        this.address = str;
        this.state = i;
        this.group = group;
        this.type = type;
        this.name = str2;
        this.source = delegatedResource;
        this.expiredTime = j2;
    }

    public String toString() {
        return "UnStakeResourceBean(trxCount=" + getTrxCount() + ", address=" + getAddress() + ", state=" + getState() + ", group=" + getGroup() + ", type=" + getType() + ", name=" + getName() + ", source=" + getSource() + ", expiredTime=" + getExpiredTime() + ")";
    }

    public boolean equals(UnStakeResourceBean unStakeResourceBean) {
        return this.trxCount == unStakeResourceBean.getTrxCount() && TextUtils.equals(this.address, unStakeResourceBean.address) && this.source.equals(unStakeResourceBean.source);
    }
}
