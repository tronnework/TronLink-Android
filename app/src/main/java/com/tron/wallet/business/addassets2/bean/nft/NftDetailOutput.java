package com.tron.wallet.business.addassets2.bean.nft;

import java.util.List;
public class NftDetailOutput {
    private int code;
    private List<NftItemInfo> data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public List<NftItemInfo> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isValid() {
        return this.data != null;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List<NftItemInfo> list) {
        this.data = list;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
