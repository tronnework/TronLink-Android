package com.tron.wallet.business.tabswap.bean;

import android.text.TextUtils;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import com.tron.wallet.common.bean.token.TokenBean;
import java.io.Serializable;
import java.util.List;
public class SwapTokenBean implements Serializable {
    private static final String FAKE_TRX = "T9yD14Nj9j7xAB4dbGeiX9h8unkKHxuWwb";
    private String balanceStr;
    private int decimal;
    private String exchange;
    private String homeUrl;
    private String inputAmount;
    private boolean isTrx;
    private String logo;
    private String name;
    private List<String> reserve;
    private String symbol;
    private String token;

    public String getBalanceStr() {
        return this.balanceStr;
    }

    public int getDecimal() {
        return this.decimal;
    }

    public String getExchange() {
        return this.exchange;
    }

    public String getHomeUrl() {
        return this.homeUrl;
    }

    public String getInputAmount() {
        return this.inputAmount;
    }

    public String getLogo() {
        return this.logo;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getReserve() {
        return this.reserve;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getToken() {
        return this.token;
    }

    public boolean isTrx() {
        return this.isTrx;
    }

    public void setBalanceStr(String str) {
        this.balanceStr = str;
    }

    public void setDecimal(int i) {
        this.decimal = i;
    }

    public void setExchange(String str) {
        this.exchange = str;
    }

    public void setHomeUrl(String str) {
        this.homeUrl = str;
    }

    public void setInputAmount(String str) {
        this.inputAmount = str;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setReserve(List<String> list) {
        this.reserve = list;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTrx(boolean z) {
        this.isTrx = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SwapTokenBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SwapTokenBean) {
            SwapTokenBean swapTokenBean = (SwapTokenBean) obj;
            if (swapTokenBean.canEqual(this) && isTrx() == swapTokenBean.isTrx() && getDecimal() == swapTokenBean.getDecimal()) {
                String homeUrl = getHomeUrl();
                String homeUrl2 = swapTokenBean.getHomeUrl();
                if (homeUrl != null ? homeUrl.equals(homeUrl2) : homeUrl2 == null) {
                    String balanceStr = getBalanceStr();
                    String balanceStr2 = swapTokenBean.getBalanceStr();
                    if (balanceStr != null ? balanceStr.equals(balanceStr2) : balanceStr2 == null) {
                        String inputAmount = getInputAmount();
                        String inputAmount2 = swapTokenBean.getInputAmount();
                        if (inputAmount != null ? inputAmount.equals(inputAmount2) : inputAmount2 == null) {
                            List<String> reserve = getReserve();
                            List<String> reserve2 = swapTokenBean.getReserve();
                            if (reserve != null ? reserve.equals(reserve2) : reserve2 == null) {
                                String name = getName();
                                String name2 = swapTokenBean.getName();
                                if (name != null ? name.equals(name2) : name2 == null) {
                                    String logo = getLogo();
                                    String logo2 = swapTokenBean.getLogo();
                                    if (logo != null ? logo.equals(logo2) : logo2 == null) {
                                        String token = getToken();
                                        String token2 = swapTokenBean.getToken();
                                        if (token != null ? token.equals(token2) : token2 == null) {
                                            String symbol = getSymbol();
                                            String symbol2 = swapTokenBean.getSymbol();
                                            if (symbol != null ? symbol.equals(symbol2) : symbol2 == null) {
                                                String exchange = getExchange();
                                                String exchange2 = swapTokenBean.getExchange();
                                                return exchange != null ? exchange.equals(exchange2) : exchange2 == null;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
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
        int decimal = (((isTrx() ? 79 : 97) + 59) * 59) + getDecimal();
        String homeUrl = getHomeUrl();
        int hashCode = (decimal * 59) + (homeUrl == null ? 43 : homeUrl.hashCode());
        String balanceStr = getBalanceStr();
        int hashCode2 = (hashCode * 59) + (balanceStr == null ? 43 : balanceStr.hashCode());
        String inputAmount = getInputAmount();
        int hashCode3 = (hashCode2 * 59) + (inputAmount == null ? 43 : inputAmount.hashCode());
        List<String> reserve = getReserve();
        int hashCode4 = (hashCode3 * 59) + (reserve == null ? 43 : reserve.hashCode());
        String name = getName();
        int hashCode5 = (hashCode4 * 59) + (name == null ? 43 : name.hashCode());
        String logo = getLogo();
        int hashCode6 = (hashCode5 * 59) + (logo == null ? 43 : logo.hashCode());
        String token = getToken();
        int hashCode7 = (hashCode6 * 59) + (token == null ? 43 : token.hashCode());
        String symbol = getSymbol();
        int hashCode8 = (hashCode7 * 59) + (symbol == null ? 43 : symbol.hashCode());
        String exchange = getExchange();
        return (hashCode8 * 59) + (exchange != null ? exchange.hashCode() : 43);
    }

    public String toString() {
        return "SwapTokenBean(homeUrl=" + getHomeUrl() + ", balanceStr=" + getBalanceStr() + ", isTrx=" + isTrx() + ", inputAmount=" + getInputAmount() + ", reserve=" + getReserve() + ", name=" + getName() + ", logo=" + getLogo() + ", token=" + getToken() + ", symbol=" + getSymbol() + ", decimal=" + getDecimal() + ", exchange=" + getExchange() + ")";
    }

    public SwapTokenBean() {
        this.isTrx = false;
        this.inputAmount = "";
        this.exchange = "";
    }

    public SwapTokenBean(String str, String str2, boolean z, String str3, List<String> list, String str4, String str5, String str6, String str7, int i, String str8) {
        this.homeUrl = str;
        this.balanceStr = str2;
        this.isTrx = z;
        this.inputAmount = str3;
        this.reserve = list;
        this.name = str4;
        this.logo = str5;
        this.token = str6;
        this.symbol = str7;
        this.decimal = i;
        this.exchange = str8;
    }

    public static SwapTokenBean fromTokenBean(TokenBean tokenBean) {
        String shortName;
        SwapTokenBean swapTokenBean = new SwapTokenBean();
        swapTokenBean.isTrx = tokenBean.type == 0;
        swapTokenBean.setBalanceStr(String.valueOf(tokenBean.balance));
        String str = "TRX";
        if (swapTokenBean.isTrx) {
            shortName = "TRX";
        } else {
            shortName = TextUtils.isEmpty(tokenBean.getName()) ? tokenBean.getShortName() : tokenBean.getName();
        }
        swapTokenBean.setName(shortName);
        swapTokenBean.setHomeUrl(tokenBean.homePage);
        swapTokenBean.setLogo(tokenBean.logoUrl);
        swapTokenBean.setDecimal(swapTokenBean.isTrx ? 6 : tokenBean.precision);
        if (swapTokenBean.isTrx()) {
            swapTokenBean.setToken(FAKE_TRX);
        } else {
            swapTokenBean.setToken((tokenBean.type == 2 || !TextUtils.isEmpty(tokenBean.getContractAddress())) ? tokenBean.getContractAddress() : tokenBean.getAddress());
        }
        if (!swapTokenBean.isTrx) {
            str = TextUtils.isEmpty(tokenBean.getShortName()) ? tokenBean.getName() : tokenBean.getShortName();
        }
        swapTokenBean.setSymbol(str);
        return swapTokenBean;
    }

    public static TokenBean toTokenBean(SwapTokenBean swapTokenBean) {
        String symbol;
        TokenBean tokenBean = new TokenBean();
        tokenBean.type = swapTokenBean.isTrx ? 0 : 2;
        tokenBean.setBalanceStr(String.valueOf(swapTokenBean.balanceStr));
        if (swapTokenBean.isTrx) {
            symbol = "TRX";
        } else {
            symbol = TextUtils.isEmpty(swapTokenBean.getName()) ? swapTokenBean.getSymbol() : swapTokenBean.getName();
        }
        tokenBean.setName(symbol);
        tokenBean.setHomePage(swapTokenBean.homeUrl);
        tokenBean.setLogoUrl(swapTokenBean.logo);
        tokenBean.setPrecision(swapTokenBean.isTrx ? 6 : swapTokenBean.decimal);
        if (swapTokenBean.isTrx()) {
            tokenBean.setContractAddress("");
        } else {
            tokenBean.setContractAddress(swapTokenBean.getToken());
        }
        tokenBean.setShortName(swapTokenBean.isTrx() ? "TRX" : swapTokenBean.getName());
        return tokenBean;
    }

    public static SwapTokenBean extend(SwapWhiteListOutput.Data data) {
        SwapTokenBean swapTokenBean = new SwapTokenBean();
        swapTokenBean.setName(data.getSymbol());
        swapTokenBean.setToken(data.getAddress());
        swapTokenBean.setLogo(data.getLogoURI());
        swapTokenBean.setSymbol(data.getSymbol());
        swapTokenBean.setDecimal(data.getDecimals());
        swapTokenBean.setHomeUrl("");
        swapTokenBean.setBalanceStr(TokenHolder.PLACE_HOLDER);
        return swapTokenBean;
    }
}
