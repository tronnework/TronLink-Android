package com.tron.wallet.db.bean;

import com.tron.wallet.common.bean.token.TransactionHistoryBean;
public class TranscationBean {
    private String amount;
    private int block;
    private long block_timestamp;
    private int confirmed;
    private String contract_type;
    private int decimals;
    private int direction;
    private String from;
    private String hash;
    private String issue_address;
    private int status;
    private String symbol;
    private String to;
    private String token_name;

    public String getAmount() {
        return this.amount;
    }

    public int getBlock() {
        return this.block;
    }

    public long getBlock_timestamp() {
        return this.block_timestamp;
    }

    public int getConfirmed() {
        return this.confirmed;
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

    public String getFrom() {
        return this.from;
    }

    public String getHash() {
        return this.hash;
    }

    public String getIssue_address() {
        return this.issue_address;
    }

    public int getStatus() {
        return this.status;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getTo() {
        return this.to;
    }

    public String getToken_name() {
        return this.token_name;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setBlock(int i) {
        this.block = i;
    }

    public void setBlock_timestamp(long j) {
        this.block_timestamp = j;
    }

    public void setConfirmed(int i) {
        this.confirmed = i;
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

    public void setFrom(String str) {
        this.from = str;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public void setIssue_address(String str) {
        this.issue_address = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public void setToken_name(String str) {
        this.token_name = str;
    }

    public TranscationBean(String str, String str2, int i, long j, int i2, String str3, int i3, int i4, String str4, String str5, String str6, String str7, String str8, int i5) {
        this.hash = str;
        this.amount = str2;
        this.block = i;
        this.block_timestamp = j;
        this.confirmed = i2;
        this.contract_type = str3;
        this.decimals = i3;
        this.direction = i4;
        this.from = str4;
        this.to = str5;
        this.issue_address = str6;
        this.symbol = str7;
        this.token_name = str8;
        this.status = i5;
    }

    public TranscationBean() {
    }

    public static TransactionHistoryBean transferToDataBean(TranscationBean transcationBean) {
        if (transcationBean == null) {
            return null;
        }
        TransactionHistoryBean transactionHistoryBean = new TransactionHistoryBean();
        transactionHistoryBean.hash = transcationBean.hash;
        transactionHistoryBean.amount = transcationBean.amount;
        transactionHistoryBean.setBlock(transcationBean.block);
        transactionHistoryBean.block_timestamp = transcationBean.block_timestamp;
        transactionHistoryBean.setConfirmed(transcationBean.confirmed);
        transactionHistoryBean.setContract_type(transcationBean.contract_type);
        transactionHistoryBean.decimals = transcationBean.decimals;
        transactionHistoryBean.setDirection(transcationBean.direction);
        transactionHistoryBean.from = transcationBean.from;
        transactionHistoryBean.to = transcationBean.to;
        transactionHistoryBean.setIssue_address(transcationBean.issue_address);
        transactionHistoryBean.setSymbol(transcationBean.symbol);
        transactionHistoryBean.setToken_name(transcationBean.token_name);
        transactionHistoryBean.tx_status = transcationBean.status;
        return transactionHistoryBean;
    }

    public static TranscationBean transferToTranscationBean(TransactionHistoryBean transactionHistoryBean) {
        if (transactionHistoryBean == null) {
            return null;
        }
        TranscationBean transcationBean = new TranscationBean();
        transcationBean.hash = transactionHistoryBean.hash;
        transcationBean.amount = transactionHistoryBean.amount;
        transcationBean.setBlock((int) transactionHistoryBean.getBlock());
        transcationBean.block_timestamp = transactionHistoryBean.block_timestamp;
        transcationBean.setConfirmed(transactionHistoryBean.getConfirmed());
        transcationBean.setContract_type(transactionHistoryBean.getContract_type());
        transcationBean.decimals = transactionHistoryBean.decimals;
        transcationBean.setDirection(transactionHistoryBean.getDirection());
        transcationBean.from = transactionHistoryBean.from;
        transcationBean.to = transactionHistoryBean.to;
        transcationBean.setIssue_address(transactionHistoryBean.getIssue_address());
        transcationBean.setSymbol(transactionHistoryBean.getSymbol());
        transcationBean.setToken_name(transactionHistoryBean.getToken_name());
        transcationBean.status = transactionHistoryBean.tx_status;
        return transcationBean;
    }
}
