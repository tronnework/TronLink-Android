package com.tron.wallet.net;
public class NetMessageData {
    private Long accountMsgId;
    private String address;
    private String msgId;
    private Long timestamp;

    public Long getAccountMsgId() {
        return this.accountMsgId;
    }

    public String getAddress() {
        return this.address;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setAccountMsgId(Long l) {
        this.accountMsgId = l;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NetMessageData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NetMessageData) {
            NetMessageData netMessageData = (NetMessageData) obj;
            if (netMessageData.canEqual(this)) {
                Long timestamp = getTimestamp();
                Long timestamp2 = netMessageData.getTimestamp();
                if (timestamp != null ? timestamp.equals(timestamp2) : timestamp2 == null) {
                    Long accountMsgId = getAccountMsgId();
                    Long accountMsgId2 = netMessageData.getAccountMsgId();
                    if (accountMsgId != null ? accountMsgId.equals(accountMsgId2) : accountMsgId2 == null) {
                        String address = getAddress();
                        String address2 = netMessageData.getAddress();
                        if (address != null ? address.equals(address2) : address2 == null) {
                            String msgId = getMsgId();
                            String msgId2 = netMessageData.getMsgId();
                            return msgId != null ? msgId.equals(msgId2) : msgId2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Long timestamp = getTimestamp();
        int hashCode = timestamp == null ? 43 : timestamp.hashCode();
        Long accountMsgId = getAccountMsgId();
        int hashCode2 = ((hashCode + 59) * 59) + (accountMsgId == null ? 43 : accountMsgId.hashCode());
        String address = getAddress();
        int hashCode3 = (hashCode2 * 59) + (address == null ? 43 : address.hashCode());
        String msgId = getMsgId();
        return (hashCode3 * 59) + (msgId != null ? msgId.hashCode() : 43);
    }

    public String toString() {
        return "NetMessageData(address=" + getAddress() + ", msgId=" + getMsgId() + ", timestamp=" + getTimestamp() + ", accountMsgId=" + getAccountMsgId() + ")";
    }
}
