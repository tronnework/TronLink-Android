package com.tron.wallet.business.addassets2.bean;

import com.tron.wallet.net.NetMessageData;
public class NewAssetsMessage extends NetMessageData {
    private int count;

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    @Override
    public String toString() {
        return "NewAssetsMessage(count=" + getCount() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof NewAssetsMessage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NewAssetsMessage) {
            NewAssetsMessage newAssetsMessage = (NewAssetsMessage) obj;
            return newAssetsMessage.canEqual(this) && super.equals(obj) && getCount() == newAssetsMessage.getCount();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (super.hashCode() * 59) + getCount();
    }
}
