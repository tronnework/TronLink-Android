package com.tron.wallet.business.stakev2.stake.pop.unfreezing;
public class UnFreezingResourceBean implements Comparable<UnFreezingResourceBean> {
    long expiredTime;
    long trxCount;
    Type type;

    public enum Type {
        BANDWIDTH,
        ENERGY
    }

    public long getExpiredTime() {
        return this.expiredTime;
    }

    public long getTrxCount() {
        return this.trxCount;
    }

    public Type getType() {
        return this.type;
    }

    public void setExpiredTime(long j) {
        this.expiredTime = j;
    }

    public void setTrxCount(long j) {
        this.trxCount = j;
    }

    public void setType(Type type) {
        this.type = type;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof UnFreezingResourceBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UnFreezingResourceBean) {
            UnFreezingResourceBean unFreezingResourceBean = (UnFreezingResourceBean) obj;
            if (unFreezingResourceBean.canEqual(this) && getTrxCount() == unFreezingResourceBean.getTrxCount() && getExpiredTime() == unFreezingResourceBean.getExpiredTime()) {
                Type type = getType();
                Type type2 = unFreezingResourceBean.getType();
                return type != null ? type.equals(type2) : type2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        long trxCount = getTrxCount();
        long expiredTime = getExpiredTime();
        Type type = getType();
        return ((((((int) (trxCount ^ (trxCount >>> 32))) + 59) * 59) + ((int) ((expiredTime >>> 32) ^ expiredTime))) * 59) + (type == null ? 43 : type.hashCode());
    }

    public UnFreezingResourceBean() {
    }

    public UnFreezingResourceBean(long j, Type type, long j2) {
        this.trxCount = j;
        this.type = type;
        this.expiredTime = j2;
    }

    public String toString() {
        return "UnFreezingResourceBean(trxCount=" + getTrxCount() + ", type=" + getType() + ", expiredTime=" + getExpiredTime() + ")";
    }

    @Override
    public int compareTo(UnFreezingResourceBean unFreezingResourceBean) {
        return UnFreezingResourceBeanExternalSyntheticBackport0.m(this.expiredTime - unFreezingResourceBean.expiredTime);
    }
}
