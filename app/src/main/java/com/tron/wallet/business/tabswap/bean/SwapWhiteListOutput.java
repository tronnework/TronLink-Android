package com.tron.wallet.business.tabswap.bean;

import android.text.TextUtils;
import com.tron.wallet.business.addassets2.bean.BaseOutput;
import java.util.List;
public class SwapWhiteListOutput extends BaseOutput {
    String name;
    List<Data> tokens;

    public String getName() {
        return this.name;
    }

    public List<Data> getTokens() {
        return this.tokens;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTokens(List<Data> list) {
        this.tokens = list;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof SwapWhiteListOutput;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SwapWhiteListOutput) {
            SwapWhiteListOutput swapWhiteListOutput = (SwapWhiteListOutput) obj;
            if (swapWhiteListOutput.canEqual(this)) {
                String name = getName();
                String name2 = swapWhiteListOutput.getName();
                if (name != null ? name.equals(name2) : name2 == null) {
                    List<Data> tokens = getTokens();
                    List<Data> tokens2 = swapWhiteListOutput.getTokens();
                    return tokens != null ? tokens.equals(tokens2) : tokens2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String name = getName();
        int hashCode = name == null ? 43 : name.hashCode();
        List<Data> tokens = getTokens();
        return ((hashCode + 59) * 59) + (tokens != null ? tokens.hashCode() : 43);
    }

    @Override
    public String toString() {
        return "SwapWhiteListOutput(name=" + getName() + ", tokens=" + getTokens() + ")";
    }

    public SwapWhiteListOutput() {
    }

    public SwapWhiteListOutput(String str, List<Data> list) {
        this.name = str;
        this.tokens = list;
    }

    public static class Data {
        private String address;
        private int chainId;
        private int decimals;
        private String logoURI;
        private String name;
        private String symbol;

        public String getAddress() {
            return this.address;
        }

        public int getChainId() {
            return this.chainId;
        }

        public int getDecimals() {
            return this.decimals;
        }

        public String getLogoURI() {
            return this.logoURI;
        }

        public String getName() {
            return this.name;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setChainId(int i) {
            this.chainId = i;
        }

        public void setDecimals(int i) {
            this.decimals = i;
        }

        public void setLogoURI(String str) {
            this.logoURI = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setSymbol(String str) {
            this.symbol = str;
        }

        public String toString() {
            return "SwapWhiteListOutput.Data(address=" + getAddress() + ", logoURI=" + getLogoURI() + ", symbol=" + getSymbol() + ", name=" + getName() + ", chainId=" + getChainId() + ", decimals=" + getDecimals() + ")";
        }

        public Data() {
        }

        public Data(String str, String str2, String str3, String str4, int i, int i2) {
            this.address = str;
            this.logoURI = str2;
            this.symbol = str3;
            this.name = str4;
            this.chainId = i;
            this.decimals = i2;
        }

        public static Data fromSwapTokenBean(SwapTokenBean swapTokenBean) {
            Data data = new Data();
            data.address = swapTokenBean.getToken();
            data.logoURI = swapTokenBean.getLogo();
            data.decimals = swapTokenBean.getDecimal();
            data.symbol = swapTokenBean.getSymbol();
            data.name = swapTokenBean.getName();
            return data;
        }

        public boolean equals(Data data) {
            return TextUtils.equals(this.address, data.getAddress()) && TextUtils.equals(this.symbol, data.getSymbol()) && this.chainId == data.getChainId() && this.decimals == data.getDecimals();
        }
    }
}
