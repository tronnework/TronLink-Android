package org.tron.net.input;
public class TriggerContractRequest {
    private boolean abiPro;
    private String methodABI;
    private byte[] ower;
    private String contractAddrStr = null;
    private String methodStr = null;
    private String argsStr = null;
    private boolean isHex = false;
    private long feeLimit = 225000000;
    private long callValue = 0;
    private long tokenCallValue = 0;
    private String tokenId = null;

    public String getArgsStr() {
        return this.argsStr;
    }

    public long getCallValue() {
        return this.callValue;
    }

    public String getContractAddrStr() {
        return this.contractAddrStr;
    }

    public long getFeeLimit() {
        return this.feeLimit;
    }

    public String getMethodABI() {
        return this.methodABI;
    }

    public String getMethodStr() {
        return this.methodStr;
    }

    public byte[] getOwer() {
        return this.ower;
    }

    public long getTokenCallValue() {
        return this.tokenCallValue;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public boolean isAbiPro() {
        return this.abiPro;
    }

    public boolean isHex() {
        return this.isHex;
    }

    public void setAbiPro(boolean z) {
        this.abiPro = z;
    }

    public void setArgsStr(String str) {
        this.argsStr = str;
    }

    public void setCallValue(long j) {
        this.callValue = j;
    }

    public void setContractAddrStr(String str) {
        this.contractAddrStr = str;
    }

    public void setFeeLimit(long j) {
        this.feeLimit = j;
    }

    public void setHex(boolean z) {
        this.isHex = z;
    }

    public void setMethodABI(String str) {
        this.methodABI = str;
    }

    public void setMethodStr(String str) {
        this.methodStr = str;
    }

    public void setOwer(byte[] bArr) {
        this.ower = bArr;
    }

    public void setTokenCallValue(long j) {
        this.tokenCallValue = j;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }
}
