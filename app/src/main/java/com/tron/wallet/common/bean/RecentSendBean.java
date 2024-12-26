package com.tron.wallet.common.bean;
public class RecentSendBean {
    public static final int TYPE_DELEGATE = 1;
    public static final int TYPE_TRANSFER = 0;
    private Long id;
    private String receiverAddress;
    private String sendAddress;
    private Long timestamp;
    private int transactionType;

    public Long getId() {
        return this.id;
    }

    public String getReceiverAddress() {
        return this.receiverAddress;
    }

    public String getSendAddress() {
        return this.sendAddress;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public int getTransactionType() {
        return this.transactionType;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setReceiverAddress(String str) {
        this.receiverAddress = str;
    }

    public void setSendAddress(String str) {
        this.sendAddress = str;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public void setTransactionType(int i) {
        this.transactionType = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RecentSendBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RecentSendBean) {
            RecentSendBean recentSendBean = (RecentSendBean) obj;
            if (recentSendBean.canEqual(this) && getTransactionType() == recentSendBean.getTransactionType()) {
                Long id = getId();
                Long id2 = recentSendBean.getId();
                if (id != null ? id.equals(id2) : id2 == null) {
                    Long timestamp = getTimestamp();
                    Long timestamp2 = recentSendBean.getTimestamp();
                    if (timestamp != null ? timestamp.equals(timestamp2) : timestamp2 == null) {
                        String sendAddress = getSendAddress();
                        String sendAddress2 = recentSendBean.getSendAddress();
                        if (sendAddress != null ? sendAddress.equals(sendAddress2) : sendAddress2 == null) {
                            String receiverAddress = getReceiverAddress();
                            String receiverAddress2 = recentSendBean.getReceiverAddress();
                            return receiverAddress != null ? receiverAddress.equals(receiverAddress2) : receiverAddress2 == null;
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
        Long id = getId();
        int transactionType = ((getTransactionType() + 59) * 59) + (id == null ? 43 : id.hashCode());
        Long timestamp = getTimestamp();
        int hashCode = (transactionType * 59) + (timestamp == null ? 43 : timestamp.hashCode());
        String sendAddress = getSendAddress();
        int hashCode2 = (hashCode * 59) + (sendAddress == null ? 43 : sendAddress.hashCode());
        String receiverAddress = getReceiverAddress();
        return (hashCode2 * 59) + (receiverAddress != null ? receiverAddress.hashCode() : 43);
    }

    public String toString() {
        return "RecentSendBean(id=" + getId() + ", sendAddress=" + getSendAddress() + ", receiverAddress=" + getReceiverAddress() + ", timestamp=" + getTimestamp() + ", transactionType=" + getTransactionType() + ")";
    }

    public RecentSendBean(Long l, String str, String str2, Long l2, int i) {
        this.id = l;
        this.sendAddress = str;
        this.receiverAddress = str2;
        this.timestamp = l2;
        this.transactionType = i;
    }

    public RecentSendBean() {
    }
}
