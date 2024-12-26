package com.tron.wallet.business.addassets2.bean.nft;
public class NftItemInfoOutput {
    private int code;
    private NftItemInfo data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public NftItemInfo getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(NftItemInfo nftItemInfo) {
        this.data = nftItemInfo;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "NftItemInfoOutput{code=" + this.code + ", message='" + this.message + "', data=" + this.data + '}';
    }
}
