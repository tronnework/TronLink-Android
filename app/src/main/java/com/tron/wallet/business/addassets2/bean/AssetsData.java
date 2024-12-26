package com.tron.wallet.business.addassets2.bean;

import com.google.gson.annotations.SerializedName;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.common.bean.token.TokenBean;
import java.util.List;
public class AssetsData {
    private int count;
    @SerializedName("token")
    private List<TokenBean> tokens;
    private int trc721Count;
    @SerializedName("trc721Token")
    private List<NftTokenBean> trc721Tokens;
    private String word;

    public int getCount() {
        return this.count;
    }

    public List<TokenBean> getTokens() {
        return this.tokens;
    }

    public int getTrc721Count() {
        return this.trc721Count;
    }

    public List<NftTokenBean> getTrc721Tokens() {
        return this.trc721Tokens;
    }

    public String getWord() {
        return this.word;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setTokens(List<TokenBean> list) {
        this.tokens = list;
    }

    public void setTrc721Count(int i) {
        this.trc721Count = i;
    }

    public void setTrc721Tokens(List<NftTokenBean> list) {
        this.trc721Tokens = list;
    }

    public void setWord(String str) {
        this.word = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof AssetsData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetsData) {
            AssetsData assetsData = (AssetsData) obj;
            if (assetsData.canEqual(this) && getCount() == assetsData.getCount() && getTrc721Count() == assetsData.getTrc721Count()) {
                String word = getWord();
                String word2 = assetsData.getWord();
                if (word != null ? word.equals(word2) : word2 == null) {
                    List<TokenBean> tokens = getTokens();
                    List<TokenBean> tokens2 = assetsData.getTokens();
                    if (tokens != null ? tokens.equals(tokens2) : tokens2 == null) {
                        List<NftTokenBean> trc721Tokens = getTrc721Tokens();
                        List<NftTokenBean> trc721Tokens2 = assetsData.getTrc721Tokens();
                        return trc721Tokens != null ? trc721Tokens.equals(trc721Tokens2) : trc721Tokens2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int count = ((getCount() + 59) * 59) + getTrc721Count();
        String word = getWord();
        int hashCode = (count * 59) + (word == null ? 43 : word.hashCode());
        List<TokenBean> tokens = getTokens();
        int hashCode2 = (hashCode * 59) + (tokens == null ? 43 : tokens.hashCode());
        List<NftTokenBean> trc721Tokens = getTrc721Tokens();
        return (hashCode2 * 59) + (trc721Tokens != null ? trc721Tokens.hashCode() : 43);
    }

    public String toString() {
        return "AssetsData(count=" + getCount() + ", word=" + getWord() + ", tokens=" + getTokens() + ", trc721Count=" + getTrc721Count() + ", trc721Tokens=" + getTrc721Tokens() + ")";
    }
}
