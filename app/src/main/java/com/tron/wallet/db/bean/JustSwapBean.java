package com.tron.wallet.db.bean;
public class JustSwapBean {
    private String address;
    private String amountIn;
    private String amountout;
    private long blockNum;
    private String contractInAddress;
    private String contractOutAddress;
    private String decimalsIn;
    private String decimalsOut;
    private String exchargeAddress;
    private String fee;
    private String hash;
    public Long id;
    private String method;
    private String minReceived;
    private String priceImpact;
    private String rate;
    private int status;
    private String symbolIn;
    private String symbolOut;
    private long timestamp;
    private String tokenAddressIn;
    private String tokenAddressOut;

    public String getAddress() {
        return this.address;
    }

    public String getAmountIn() {
        return this.amountIn;
    }

    public String getAmountout() {
        return this.amountout;
    }

    public long getBlockNum() {
        return this.blockNum;
    }

    public String getContractInAddress() {
        return this.contractInAddress;
    }

    public String getContractOutAddress() {
        return this.contractOutAddress;
    }

    public String getDecimalsIn() {
        return this.decimalsIn;
    }

    public String getDecimalsOut() {
        return this.decimalsOut;
    }

    public String getExchargeAddress() {
        return this.exchargeAddress;
    }

    public String getFee() {
        return this.fee;
    }

    public String getHash() {
        return this.hash;
    }

    public Long getId() {
        return this.id;
    }

    public String getMethod() {
        return this.method;
    }

    public String getMinReceived() {
        return this.minReceived;
    }

    public String getPriceImpact() {
        return this.priceImpact;
    }

    public String getRate() {
        return this.rate;
    }

    public int getStatus() {
        return this.status;
    }

    public String getSymbolIn() {
        return this.symbolIn;
    }

    public String getSymbolOut() {
        return this.symbolOut;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getTokenAddressIn() {
        return this.tokenAddressIn;
    }

    public String getTokenAddressOut() {
        return this.tokenAddressOut;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAmountIn(String str) {
        this.amountIn = str;
    }

    public void setAmountout(String str) {
        this.amountout = str;
    }

    public void setBlockNum(long j) {
        this.blockNum = j;
    }

    public void setContractInAddress(String str) {
        this.contractInAddress = str;
    }

    public void setContractOutAddress(String str) {
        this.contractOutAddress = str;
    }

    public void setDecimalsIn(String str) {
        this.decimalsIn = str;
    }

    public void setDecimalsOut(String str) {
        this.decimalsOut = str;
    }

    public void setExchargeAddress(String str) {
        this.exchargeAddress = str;
    }

    public void setFee(String str) {
        this.fee = str;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setMinReceived(String str) {
        this.minReceived = str;
    }

    public void setPriceImpact(String str) {
        this.priceImpact = str;
    }

    public void setRate(String str) {
        this.rate = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setSymbolIn(String str) {
        this.symbolIn = str;
    }

    public void setSymbolOut(String str) {
        this.symbolOut = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setTokenAddressIn(String str) {
        this.tokenAddressIn = str;
    }

    public void setTokenAddressOut(String str) {
        this.tokenAddressOut = str;
    }

    public JustSwapBean(Long l, long j, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, long j2, int i) {
        this.id = l;
        this.blockNum = j;
        this.hash = str;
        this.symbolIn = str2;
        this.decimalsIn = str3;
        this.tokenAddressIn = str4;
        this.address = str5;
        this.contractInAddress = str6;
        this.amountIn = str7;
        this.fee = str8;
        this.rate = str9;
        this.amountout = str10;
        this.contractOutAddress = str11;
        this.exchargeAddress = str12;
        this.tokenAddressOut = str13;
        this.method = str14;
        this.symbolOut = str15;
        this.decimalsOut = str16;
        this.minReceived = str17;
        this.priceImpact = str18;
        this.timestamp = j2;
        this.status = i;
    }

    public JustSwapBean() {
        this.status = 0;
    }

    public String toString() {
        return "JustSwapBean{id=" + this.id + ", blockNum=" + this.blockNum + ", hash='" + this.hash + "', symbolIn='" + this.symbolIn + "', decimalsIn='" + this.decimalsIn + "', tokenAddressIn='" + this.tokenAddressIn + "', address='" + this.address + "', contractInAddress='" + this.contractInAddress + "', amountIn='" + this.amountIn + "', fee='" + this.fee + "', rate='" + this.rate + "', amountout='" + this.amountout + "', contractOutAddress='" + this.contractOutAddress + "', exchargeAddress='" + this.exchargeAddress + "', tokenAddressOut='" + this.tokenAddressOut + "', method='" + this.method + "', symbolOut='" + this.symbolOut + "', decimalsOut='" + this.decimalsOut + "', minReceived='" + this.minReceived + "', priceImpact='" + this.priceImpact + "', timestamp=" + this.timestamp + ", status=" + this.status + '}';
    }
}
