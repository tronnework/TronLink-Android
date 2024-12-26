package com.tron.wallet.common.bean.token;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.utils.StringTronUtil;
public class TransactionHistoryBean implements Parcelable, Comparable<TransactionHistoryBean> {
    public static final Parcelable.Creator<TransactionHistoryBean> CREATOR = new Parcelable.Creator<TransactionHistoryBean>() {
        @Override
        public TransactionHistoryBean createFromParcel(Parcel parcel) {
            return new TransactionHistoryBean(parcel);
        }

        @Override
        public TransactionHistoryBean[] newArray(int i) {
            return new TransactionHistoryBean[i];
        }
    };
    public String amount;
    private String approval_amount;
    private long block;
    public long block_timestamp;
    private boolean cheatStatus;
    private int confirmed;
    private int contractType;
    public String contract_address;
    private String contract_ret;
    private String contract_type;
    public int decimals;
    private int direction;
    private String event_type;
    public String from;
    public String hash;
    private String internal_hash;
    private boolean internal_transaction;
    private boolean isMul;
    private String issue_address;
    private String mark;
    public String memo;
    private int revert;
    private int status;
    private String symbol;
    public long timestamp;
    public String to;
    private String tokenType;
    private String token_name;
    public String transactionHash;
    private long trc10id;
    private String trc20Id;
    public int tx_status;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getApproval_amount() {
        return this.approval_amount;
    }

    public long getBlock() {
        return this.block;
    }

    public long getBlock_timestamp() {
        return this.block_timestamp;
    }

    public int getConfirmed() {
        return this.confirmed;
    }

    public int getContractType() {
        return this.contractType;
    }

    public String getContract_address() {
        return this.contract_address;
    }

    public String getContract_ret() {
        return this.contract_ret;
    }

    public String getContract_type() {
        return this.contract_type;
    }

    public int getDecimals() {
        return this.decimals;
    }

    public int getDirection() {
        return this.direction;
    }

    public String getEvent_type() {
        return this.event_type;
    }

    public String getFrom() {
        return this.from;
    }

    public String getHash() {
        return this.hash;
    }

    public String getInternal_hash() {
        return this.internal_hash;
    }

    public String getIssue_address() {
        return this.issue_address;
    }

    public String getMemo() {
        return this.memo;
    }

    public int getRevert() {
        return this.revert;
    }

    public int getStatus() {
        return this.status;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getTo() {
        return this.to;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getToken_name() {
        return this.token_name;
    }

    public String getTransactionHash() {
        return this.transactionHash;
    }

    public long getTrc10id() {
        return this.trc10id;
    }

    public String getTrc20Id() {
        return this.trc20Id;
    }

    public int getTx_status() {
        return this.tx_status;
    }

    public boolean isCheatStatus() {
        return this.cheatStatus;
    }

    public boolean isInternal_transaction() {
        return this.internal_transaction;
    }

    public boolean isMul() {
        return this.isMul;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setApproval_amount(String str) {
        this.approval_amount = str;
    }

    public void setBlock(long j) {
        this.block = j;
    }

    public void setBlock_timestamp(long j) {
        this.block_timestamp = j;
    }

    public void setCheatStatus(boolean z) {
        this.cheatStatus = z;
    }

    public void setConfirmed(int i) {
        this.confirmed = i;
    }

    public void setContractType(int i) {
        this.contractType = i;
    }

    public void setContract_address(String str) {
        this.contract_address = str;
    }

    public void setContract_ret(String str) {
        this.contract_ret = str;
    }

    public void setContract_type(String str) {
        this.contract_type = str;
    }

    public void setDecimals(int i) {
        this.decimals = i;
    }

    public void setDirection(int i) {
        this.direction = i;
    }

    public void setEvent_type(String str) {
        this.event_type = str;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public void setInternal_hash(String str) {
        this.internal_hash = str;
    }

    public void setInternal_transaction(boolean z) {
        this.internal_transaction = z;
    }

    public void setIssue_address(String str) {
        this.issue_address = str;
    }

    public void setMark(String str) {
        this.mark = str;
    }

    public void setMemo(String str) {
        this.memo = str;
    }

    public void setMul(boolean z) {
        this.isMul = z;
    }

    public void setRevert(int i) {
        this.revert = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    public void setToken_name(String str) {
        this.token_name = str;
    }

    public void setTransactionHash(String str) {
        this.transactionHash = str;
    }

    public void setTrc10id(long j) {
        this.trc10id = j;
    }

    public void setTrc20Id(String str) {
        this.trc20Id = str;
    }

    public void setTx_status(int i) {
        this.tx_status = i;
    }

    public String getMark() {
        return StringTronUtil.isEmpty(this.mark) ? "" : this.mark;
    }

    public TransactionHistoryBean() {
    }

    @Override
    public int compareTo(TransactionHistoryBean transactionHistoryBean) {
        int i = ((transactionHistoryBean.getBlock_timestamp() - getBlock_timestamp()) > 0L ? 1 : ((transactionHistoryBean.getBlock_timestamp() - getBlock_timestamp()) == 0L ? 0 : -1));
        if (i > 0) {
            return 1;
        }
        return i < 0 ? -1 : 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.amount);
        parcel.writeLong(this.block);
        parcel.writeLong(this.block_timestamp);
        parcel.writeInt(this.confirmed);
        parcel.writeString(this.contract_type);
        parcel.writeInt(this.contractType);
        parcel.writeInt(this.decimals);
        parcel.writeInt(this.direction);
        parcel.writeString(this.from);
        parcel.writeString(this.to);
        parcel.writeString(this.hash);
        parcel.writeString(this.issue_address);
        parcel.writeString(this.symbol);
        parcel.writeString(this.token_name);
        parcel.writeString(this.contract_address);
        parcel.writeLong(this.timestamp);
        parcel.writeString(this.transactionHash);
        parcel.writeString(this.memo);
        parcel.writeInt(this.tx_status);
        parcel.writeInt(this.status);
        parcel.writeString(this.tokenType);
        parcel.writeLong(this.trc10id);
        parcel.writeString(this.trc20Id);
        parcel.writeInt(this.revert);
        parcel.writeString(this.contract_ret);
        parcel.writeString(this.approval_amount);
        parcel.writeString(this.event_type);
        parcel.writeString(this.mark);
        parcel.writeString(this.internal_hash);
        parcel.writeByte(this.internal_transaction ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.cheatStatus ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel parcel) {
        this.amount = parcel.readString();
        this.block = parcel.readLong();
        this.block_timestamp = parcel.readLong();
        this.confirmed = parcel.readInt();
        this.contract_type = parcel.readString();
        this.contractType = parcel.readInt();
        this.decimals = parcel.readInt();
        this.direction = parcel.readInt();
        this.from = parcel.readString();
        this.to = parcel.readString();
        this.hash = parcel.readString();
        this.issue_address = parcel.readString();
        this.symbol = parcel.readString();
        this.token_name = parcel.readString();
        this.contract_address = parcel.readString();
        this.timestamp = parcel.readLong();
        this.transactionHash = parcel.readString();
        this.memo = parcel.readString();
        this.tx_status = parcel.readInt();
        this.status = parcel.readInt();
        this.tokenType = parcel.readString();
        this.trc10id = parcel.readLong();
        this.trc20Id = parcel.readString();
        this.revert = parcel.readInt();
        this.contract_ret = parcel.readString();
        this.approval_amount = parcel.readString();
        this.event_type = parcel.readString();
        this.mark = parcel.readString();
        this.internal_hash = parcel.readString();
        this.internal_transaction = parcel.readByte() != 0;
        this.cheatStatus = parcel.readByte() != 0;
    }

    protected TransactionHistoryBean(Parcel parcel) {
        this.amount = parcel.readString();
        this.block = parcel.readLong();
        this.block_timestamp = parcel.readLong();
        this.confirmed = parcel.readInt();
        this.contract_type = parcel.readString();
        this.contractType = parcel.readInt();
        this.decimals = parcel.readInt();
        this.direction = parcel.readInt();
        this.from = parcel.readString();
        this.to = parcel.readString();
        this.hash = parcel.readString();
        this.issue_address = parcel.readString();
        this.symbol = parcel.readString();
        this.token_name = parcel.readString();
        this.contract_address = parcel.readString();
        this.timestamp = parcel.readLong();
        this.transactionHash = parcel.readString();
        this.memo = parcel.readString();
        this.tx_status = parcel.readInt();
        this.status = parcel.readInt();
        this.tokenType = parcel.readString();
        this.trc10id = parcel.readLong();
        this.trc20Id = parcel.readString();
        this.revert = parcel.readInt();
        this.contract_ret = parcel.readString();
        this.approval_amount = parcel.readString();
        this.event_type = parcel.readString();
        this.mark = parcel.readString();
        this.internal_hash = parcel.readString();
        this.internal_transaction = parcel.readByte() != 0;
        this.cheatStatus = parcel.readByte() != 0;
    }
}
