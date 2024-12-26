package com.tron.wallet.business.addassets2.bean;

import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import java.util.List;
public class NftData {
    private int count;
    private List<NftTokenBean> token;

    public int getCount() {
        return this.count;
    }

    public List<NftTokenBean> getToken() {
        return this.token;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setToken(List<NftTokenBean> list) {
        this.token = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NftData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NftData) {
            NftData nftData = (NftData) obj;
            if (nftData.canEqual(this) && getCount() == nftData.getCount()) {
                List<NftTokenBean> token = getToken();
                List<NftTokenBean> token2 = nftData.getToken();
                return token != null ? token.equals(token2) : token2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        List<NftTokenBean> token = getToken();
        return ((getCount() + 59) * 59) + (token == null ? 43 : token.hashCode());
    }

    public String toString() {
        return "NftData(count=" + getCount() + ", token=" + getToken() + ")";
    }
}
