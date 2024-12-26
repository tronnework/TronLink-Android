package com.tron.wallet.business.message;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.net.NetMessageData;
import java.lang.reflect.Field;
public class TransactionMessage extends NetMessageData implements Parcelable {
    public static final Parcelable.Creator<TransactionMessage> CREATOR = new Parcelable.Creator<TransactionMessage>() {
        @Override
        public TransactionMessage createFromParcel(Parcel parcel) {
            return new TransactionMessage(parcel);
        }

        @Override
        public TransactionMessage[] newArray(int i) {
            return new TransactionMessage[i];
        }
    };
    public static final int STATE_READ = 1;
    public static final int STATE_UNREAD = 0;
    public static final int TYPE_RECEIVE = 1;
    public static final String TYPE_REVERT = "REVERT";
    public static final int TYPE_SEND = 0;
    public static final String TYPE_SUCCESS = "SUCCESS";
    private String amount;
    private String assetId;
    private long blockNumber;
    private String contract_ret;
    private long decimal;
    private String fromAddress;
    private Long id;
    private String note;
    private int revert;
    private int state;
    private String toAddress;
    private String tokenAddress;
    private String tokenSymbol;
    private int tokenType;
    private long transferTime;
    private String txId;
    private int type;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getAssetId() {
        return this.assetId;
    }

    public long getBlockNumber() {
        return this.blockNumber;
    }

    public String getContract_ret() {
        return this.contract_ret;
    }

    public long getDecimal() {
        return this.decimal;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public Long getId() {
        return this.id;
    }

    public String getNote() {
        return this.note;
    }

    public int getRevert() {
        return this.revert;
    }

    public int getState() {
        return this.state;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public String getTokenAddress() {
        return this.tokenAddress;
    }

    public String getTokenSymbol() {
        return this.tokenSymbol;
    }

    public int getTokenType() {
        return this.tokenType;
    }

    public long getTransferTime() {
        return this.transferTime;
    }

    public String getTxId() {
        return this.txId;
    }

    public int getType() {
        return this.type;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setAssetId(String str) {
        this.assetId = str;
    }

    public void setBlockNumber(long j) {
        this.blockNumber = j;
    }

    public void setContract_ret(String str) {
        this.contract_ret = str;
    }

    public void setDecimal(long j) {
        this.decimal = j;
    }

    public void setFromAddress(String str) {
        this.fromAddress = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setRevert(int i) {
        this.revert = i;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void setToAddress(String str) {
        this.toAddress = str;
    }

    public void setTokenAddress(String str) {
        this.tokenAddress = str;
    }

    public void setTokenSymbol(String str) {
        this.tokenSymbol = str;
    }

    public void setTokenType(int i) {
        this.tokenType = i;
    }

    public void setTransferTime(long j) {
        this.transferTime = j;
    }

    public void setTxId(String str) {
        this.txId = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    @Override
    public String toString() {
        return "TransactionMessage(id=" + getId() + ", txId=" + getTxId() + ", fromAddress=" + getFromAddress() + ", toAddress=" + getToAddress() + ", tokenAddress=" + getTokenAddress() + ", tokenSymbol=" + getTokenSymbol() + ", amount=" + getAmount() + ", decimal=" + getDecimal() + ", assetId=" + getAssetId() + ", blockNumber=" + getBlockNumber() + ", transferTime=" + getTransferTime() + ", note=" + getNote() + ", tokenType=" + getTokenType() + ", type=" + getType() + ", state=" + getState() + ", revert=" + getRevert() + ", contract_ret=" + getContract_ret() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof TransactionMessage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TransactionMessage) {
            TransactionMessage transactionMessage = (TransactionMessage) obj;
            if (transactionMessage.canEqual(this) && super.equals(obj) && getDecimal() == transactionMessage.getDecimal() && getBlockNumber() == transactionMessage.getBlockNumber() && getTransferTime() == transactionMessage.getTransferTime() && getTokenType() == transactionMessage.getTokenType() && getType() == transactionMessage.getType() && getState() == transactionMessage.getState() && getRevert() == transactionMessage.getRevert()) {
                Long id = getId();
                Long id2 = transactionMessage.getId();
                if (id != null ? id.equals(id2) : id2 == null) {
                    String txId = getTxId();
                    String txId2 = transactionMessage.getTxId();
                    if (txId != null ? txId.equals(txId2) : txId2 == null) {
                        String fromAddress = getFromAddress();
                        String fromAddress2 = transactionMessage.getFromAddress();
                        if (fromAddress != null ? fromAddress.equals(fromAddress2) : fromAddress2 == null) {
                            String toAddress = getToAddress();
                            String toAddress2 = transactionMessage.getToAddress();
                            if (toAddress != null ? toAddress.equals(toAddress2) : toAddress2 == null) {
                                String tokenAddress = getTokenAddress();
                                String tokenAddress2 = transactionMessage.getTokenAddress();
                                if (tokenAddress != null ? tokenAddress.equals(tokenAddress2) : tokenAddress2 == null) {
                                    String tokenSymbol = getTokenSymbol();
                                    String tokenSymbol2 = transactionMessage.getTokenSymbol();
                                    if (tokenSymbol != null ? tokenSymbol.equals(tokenSymbol2) : tokenSymbol2 == null) {
                                        String amount = getAmount();
                                        String amount2 = transactionMessage.getAmount();
                                        if (amount != null ? amount.equals(amount2) : amount2 == null) {
                                            String assetId = getAssetId();
                                            String assetId2 = transactionMessage.getAssetId();
                                            if (assetId != null ? assetId.equals(assetId2) : assetId2 == null) {
                                                String note = getNote();
                                                String note2 = transactionMessage.getNote();
                                                if (note != null ? note.equals(note2) : note2 == null) {
                                                    String contract_ret = getContract_ret();
                                                    String contract_ret2 = transactionMessage.getContract_ret();
                                                    return contract_ret != null ? contract_ret.equals(contract_ret2) : contract_ret2 == null;
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

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        long decimal = getDecimal();
        long blockNumber = getBlockNumber();
        long transferTime = getTransferTime();
        int tokenType = (((((((((((((hashCode * 59) + ((int) (decimal ^ (decimal >>> 32)))) * 59) + ((int) (blockNumber ^ (blockNumber >>> 32)))) * 59) + ((int) (transferTime ^ (transferTime >>> 32)))) * 59) + getTokenType()) * 59) + getType()) * 59) + getState()) * 59) + getRevert();
        Long id = getId();
        int hashCode2 = (tokenType * 59) + (id == null ? 43 : id.hashCode());
        String txId = getTxId();
        int hashCode3 = (hashCode2 * 59) + (txId == null ? 43 : txId.hashCode());
        String fromAddress = getFromAddress();
        int hashCode4 = (hashCode3 * 59) + (fromAddress == null ? 43 : fromAddress.hashCode());
        String toAddress = getToAddress();
        int hashCode5 = (hashCode4 * 59) + (toAddress == null ? 43 : toAddress.hashCode());
        String tokenAddress = getTokenAddress();
        int hashCode6 = (hashCode5 * 59) + (tokenAddress == null ? 43 : tokenAddress.hashCode());
        String tokenSymbol = getTokenSymbol();
        int hashCode7 = (hashCode6 * 59) + (tokenSymbol == null ? 43 : tokenSymbol.hashCode());
        String amount = getAmount();
        int hashCode8 = (hashCode7 * 59) + (amount == null ? 43 : amount.hashCode());
        String assetId = getAssetId();
        int hashCode9 = (hashCode8 * 59) + (assetId == null ? 43 : assetId.hashCode());
        String note = getNote();
        int hashCode10 = (hashCode9 * 59) + (note == null ? 43 : note.hashCode());
        String contract_ret = getContract_ret();
        return (hashCode10 * 59) + (contract_ret != null ? contract_ret.hashCode() : 43);
    }

    public TransactionMessage(Long l, String str, String str2, String str3, String str4, String str5, String str6, long j, String str7, long j2, long j3, String str8, int i, int i2, int i3, int i4, String str9) {
        this.id = l;
        this.txId = str;
        this.fromAddress = str2;
        this.toAddress = str3;
        this.tokenAddress = str4;
        this.tokenSymbol = str5;
        this.amount = str6;
        this.decimal = j;
        this.assetId = str7;
        this.blockNumber = j2;
        this.transferTime = j3;
        this.note = str8;
        this.tokenType = i;
        this.type = i2;
        this.state = i3;
        this.revert = i4;
        this.contract_ret = str9;
    }

    public TransactionMessage() {
        this.state = 0;
    }

    public static TransactionMessage buildDefault() {
        Field[] declaredFields;
        TransactionMessage transactionMessage = new TransactionMessage();
        for (Field field : transactionMessage.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.getType() == String.class) {
                    if (field.getName().equals("txId")) {
                        field.set(transactionMessage, System.currentTimeMillis() + "#" + transactionMessage.hashCode());
                    } else {
                        field.set(transactionMessage, ":)");
                    }
                } else if (field.getType() == Integer.class) {
                    field.set(transactionMessage, 0);
                } else if (field.getType() == Long.class && !field.getName().equals("id")) {
                    field.set(transactionMessage, 0L);
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        transactionMessage.setTransferTime(System.currentTimeMillis());
        return transactionMessage;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeString(this.txId);
        parcel.writeString(this.fromAddress);
        parcel.writeString(this.toAddress);
        parcel.writeString(this.tokenAddress);
        parcel.writeString(this.tokenSymbol);
        parcel.writeString(this.amount);
        parcel.writeLong(this.decimal);
        parcel.writeString(this.assetId);
        parcel.writeLong(this.blockNumber);
        parcel.writeLong(this.transferTime);
        parcel.writeString(this.note);
        parcel.writeInt(this.tokenType);
        parcel.writeInt(this.type);
        parcel.writeInt(this.state);
        parcel.writeInt(this.revert);
        parcel.writeString(this.contract_ret);
    }

    public void readFromParcel(Parcel parcel) {
        this.id = (Long) parcel.readValue(Long.class.getClassLoader());
        this.txId = parcel.readString();
        this.fromAddress = parcel.readString();
        this.toAddress = parcel.readString();
        this.tokenAddress = parcel.readString();
        this.tokenSymbol = parcel.readString();
        this.amount = parcel.readString();
        this.decimal = parcel.readLong();
        this.assetId = parcel.readString();
        this.blockNumber = parcel.readLong();
        this.transferTime = parcel.readLong();
        this.note = parcel.readString();
        this.tokenType = parcel.readInt();
        this.type = parcel.readInt();
        this.state = parcel.readInt();
        this.revert = parcel.readInt();
        this.contract_ret = parcel.readString();
    }

    protected TransactionMessage(Parcel parcel) {
        this.state = 0;
        this.id = (Long) parcel.readValue(Long.class.getClassLoader());
        this.txId = parcel.readString();
        this.fromAddress = parcel.readString();
        this.toAddress = parcel.readString();
        this.tokenAddress = parcel.readString();
        this.tokenSymbol = parcel.readString();
        this.amount = parcel.readString();
        this.decimal = parcel.readLong();
        this.assetId = parcel.readString();
        this.blockNumber = parcel.readLong();
        this.transferTime = parcel.readLong();
        this.note = parcel.readString();
        this.tokenType = parcel.readInt();
        this.type = parcel.readInt();
        this.state = parcel.readInt();
        this.revert = parcel.readInt();
        this.contract_ret = parcel.readString();
    }
}
