package com.tron.wallet.business.addassets2.bean.asset;
public class AssetsHomeOutput {
    public int code;
    public AssetsHomeData data;
    public String message;

    public String toString() {
        return "AssetsHomeOutput{code=" + this.code + ", message='" + this.message + "', data=" + this.data.toString() + '}';
    }
}
