package com.tron.wallet.business.addassets2.bean.nft;
public class NftInfoOutput {
    private int code;
    private NftTypeInfo data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public NftTypeInfo getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(NftTypeInfo nftTypeInfo) {
        this.data = nftTypeInfo;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public boolean isValidResponse() {
        return (getData() == null || getData().getCollectionInfoList() == null) ? false : true;
    }

    public String toString() {
        return "NftInfoOutput{code=" + this.code + ", message='" + this.message + "', data=" + this.data + '}';
    }
}
