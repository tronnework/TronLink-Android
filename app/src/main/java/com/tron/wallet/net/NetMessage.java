package com.tron.wallet.net;

import java.util.List;
public class NetMessage {
    private Integer code;
    private List<NetMessageData> data;
    private String dataString;
    private String desc;
    private String deviceToken;
    private int msgType;
    private long seqId;

    public Integer getCode() {
        return this.code;
    }

    public List<NetMessageData> getData() {
        return this.data;
    }

    public String getDataString() {
        return this.dataString;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDeviceToken() {
        return this.deviceToken;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public long getSeqId() {
        return this.seqId;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public void setData(List<NetMessageData> list) {
        this.data = list;
    }

    public void setDataString(String str) {
        this.dataString = str;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setDeviceToken(String str) {
        this.deviceToken = str;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public void setSeqId(long j) {
        this.seqId = j;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NetMessage;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NetMessage) {
            NetMessage netMessage = (NetMessage) obj;
            if (netMessage.canEqual(this) && getMsgType() == netMessage.getMsgType() && getSeqId() == netMessage.getSeqId()) {
                Integer code = getCode();
                Integer code2 = netMessage.getCode();
                if (code != null ? code.equals(code2) : code2 == null) {
                    String desc = getDesc();
                    String desc2 = netMessage.getDesc();
                    if (desc != null ? desc.equals(desc2) : desc2 == null) {
                        String deviceToken = getDeviceToken();
                        String deviceToken2 = netMessage.getDeviceToken();
                        if (deviceToken != null ? deviceToken.equals(deviceToken2) : deviceToken2 == null) {
                            List<NetMessageData> data = getData();
                            List<NetMessageData> data2 = netMessage.getData();
                            if (data != null ? data.equals(data2) : data2 == null) {
                                String dataString = getDataString();
                                String dataString2 = netMessage.getDataString();
                                return dataString != null ? dataString.equals(dataString2) : dataString2 == null;
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
        return false;
    }

    public int hashCode() {
        long seqId = getSeqId();
        Integer code = getCode();
        int msgType = ((((getMsgType() + 59) * 59) + ((int) (seqId ^ (seqId >>> 32)))) * 59) + (code == null ? 43 : code.hashCode());
        String desc = getDesc();
        int hashCode = (msgType * 59) + (desc == null ? 43 : desc.hashCode());
        String deviceToken = getDeviceToken();
        int hashCode2 = (hashCode * 59) + (deviceToken == null ? 43 : deviceToken.hashCode());
        List<NetMessageData> data = getData();
        int hashCode3 = (hashCode2 * 59) + (data == null ? 43 : data.hashCode());
        String dataString = getDataString();
        return (hashCode3 * 59) + (dataString != null ? dataString.hashCode() : 43);
    }

    public String toString() {
        return "NetMessage(code=" + getCode() + ", desc=" + getDesc() + ", msgType=" + getMsgType() + ", seqId=" + getSeqId() + ", deviceToken=" + getDeviceToken() + ", data=" + getData() + ", dataString=" + getDataString() + ")";
    }
}
