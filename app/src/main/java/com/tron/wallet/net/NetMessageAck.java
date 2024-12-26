package com.tron.wallet.net;

import java.util.List;
public class NetMessageAck extends NetMessage {
    private List<String> msgIdList;

    public List<String> getMsgIdList() {
        return this.msgIdList;
    }

    public void setMsgIdList(List<String> list) {
        this.msgIdList = list;
    }

    @Override
    public String toString() {
        return "NetMessageAck(msgIdList=" + getMsgIdList() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof NetMessageAck;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NetMessageAck) {
            NetMessageAck netMessageAck = (NetMessageAck) obj;
            if (netMessageAck.canEqual(this) && super.equals(obj)) {
                List<String> msgIdList = getMsgIdList();
                List<String> msgIdList2 = netMessageAck.getMsgIdList();
                return msgIdList != null ? msgIdList.equals(msgIdList2) : msgIdList2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        List<String> msgIdList = getMsgIdList();
        return (hashCode * 59) + (msgIdList == null ? 43 : msgIdList.hashCode());
    }
}
