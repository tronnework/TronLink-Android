package com.tron.wallet.business.tabswap.bean;

import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import com.tron.wallet.common.utils.BigDecimalUtils;
import java.math.BigDecimal;
import java.util.List;
public class SwapTokenInfoBean {
    private String decimal;
    private int direction;
    private String fee;
    private String inUsd;
    private String minReceived;
    private String outUsd;
    private String priceImpact;
    private String rate;
    private String received;
    private String triggerMethod;

    public String getDecimal() {
        return this.decimal;
    }

    public int getDirection() {
        return this.direction;
    }

    public String getFee() {
        return this.fee;
    }

    public String getInUsd() {
        return this.inUsd;
    }

    public String getMinReceived() {
        return this.minReceived;
    }

    public String getOutUsd() {
        return this.outUsd;
    }

    public String getPriceImpact() {
        return this.priceImpact;
    }

    public String getRate() {
        return this.rate;
    }

    public String getReceived() {
        return this.received;
    }

    public String getTriggerMethod() {
        return this.triggerMethod;
    }

    public void setDecimal(String str) {
        this.decimal = str;
    }

    public void setDirection(int i) {
        this.direction = i;
    }

    public void setFee(String str) {
        this.fee = str;
    }

    public void setInUsd(String str) {
        this.inUsd = str;
    }

    public void setMinReceived(String str) {
        this.minReceived = str;
    }

    public void setOutUsd(String str) {
        this.outUsd = str;
    }

    public void setPriceImpact(String str) {
        this.priceImpact = str;
    }

    public void setRate(String str) {
        this.rate = str;
    }

    public void setReceived(String str) {
        this.received = str;
    }

    public void setTriggerMethod(String str) {
        this.triggerMethod = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SwapTokenInfoBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SwapTokenInfoBean) {
            SwapTokenInfoBean swapTokenInfoBean = (SwapTokenInfoBean) obj;
            if (swapTokenInfoBean.canEqual(this) && getDirection() == swapTokenInfoBean.getDirection()) {
                String rate = getRate();
                String rate2 = swapTokenInfoBean.getRate();
                if (rate != null ? rate.equals(rate2) : rate2 == null) {
                    String fee = getFee();
                    String fee2 = swapTokenInfoBean.getFee();
                    if (fee != null ? fee.equals(fee2) : fee2 == null) {
                        String minReceived = getMinReceived();
                        String minReceived2 = swapTokenInfoBean.getMinReceived();
                        if (minReceived != null ? minReceived.equals(minReceived2) : minReceived2 == null) {
                            String priceImpact = getPriceImpact();
                            String priceImpact2 = swapTokenInfoBean.getPriceImpact();
                            if (priceImpact != null ? priceImpact.equals(priceImpact2) : priceImpact2 == null) {
                                String decimal = getDecimal();
                                String decimal2 = swapTokenInfoBean.getDecimal();
                                if (decimal != null ? decimal.equals(decimal2) : decimal2 == null) {
                                    String received = getReceived();
                                    String received2 = swapTokenInfoBean.getReceived();
                                    if (received != null ? received.equals(received2) : received2 == null) {
                                        String triggerMethod = getTriggerMethod();
                                        String triggerMethod2 = swapTokenInfoBean.getTriggerMethod();
                                        if (triggerMethod != null ? triggerMethod.equals(triggerMethod2) : triggerMethod2 == null) {
                                            String inUsd = getInUsd();
                                            String inUsd2 = swapTokenInfoBean.getInUsd();
                                            if (inUsd != null ? inUsd.equals(inUsd2) : inUsd2 == null) {
                                                String outUsd = getOutUsd();
                                                String outUsd2 = swapTokenInfoBean.getOutUsd();
                                                return outUsd != null ? outUsd.equals(outUsd2) : outUsd2 == null;
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
        String rate = getRate();
        int direction = ((getDirection() + 59) * 59) + (rate == null ? 43 : rate.hashCode());
        String fee = getFee();
        int hashCode = (direction * 59) + (fee == null ? 43 : fee.hashCode());
        String minReceived = getMinReceived();
        int hashCode2 = (hashCode * 59) + (minReceived == null ? 43 : minReceived.hashCode());
        String priceImpact = getPriceImpact();
        int hashCode3 = (hashCode2 * 59) + (priceImpact == null ? 43 : priceImpact.hashCode());
        String decimal = getDecimal();
        int hashCode4 = (hashCode3 * 59) + (decimal == null ? 43 : decimal.hashCode());
        String received = getReceived();
        int hashCode5 = (hashCode4 * 59) + (received == null ? 43 : received.hashCode());
        String triggerMethod = getTriggerMethod();
        int hashCode6 = (hashCode5 * 59) + (triggerMethod == null ? 43 : triggerMethod.hashCode());
        String inUsd = getInUsd();
        int hashCode7 = (hashCode6 * 59) + (inUsd == null ? 43 : inUsd.hashCode());
        String outUsd = getOutUsd();
        return (hashCode7 * 59) + (outUsd != null ? outUsd.hashCode() : 43);
    }

    public String toString() {
        return "SwapTokenInfoBean(rate=" + getRate() + ", fee=" + getFee() + ", minReceived=" + getMinReceived() + ", priceImpact=" + getPriceImpact() + ", decimal=" + getDecimal() + ", received=" + getReceived() + ", triggerMethod=" + getTriggerMethod() + ", direction=" + getDirection() + ", inUsd=" + getInUsd() + ", outUsd=" + getOutUsd() + ")";
    }

    public SwapTokenInfoBean() {
        this.fee = "0.3%";
    }

    public SwapTokenInfoBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, String str8, String str9) {
        this.rate = str;
        this.fee = str2;
        this.minReceived = str3;
        this.priceImpact = str4;
        this.decimal = str5;
        this.received = str6;
        this.triggerMethod = str7;
        this.direction = i;
        this.inUsd = str8;
        this.outUsd = str9;
    }

    public static SwapTokenInfoBean fromSwapInfoOutput(SwapInfoOutput.InfoData infoData) {
        SwapTokenInfoBean swapTokenInfoBean = new SwapTokenInfoBean();
        swapTokenInfoBean.setRate(BigDecimalUtils.div_(infoData.getAmountOut(), infoData.getAmountIn()).toPlainString());
        swapTokenInfoBean.setFee(infoData.getFee());
        List<String> symbols = infoData.getSymbols();
        swapTokenInfoBean.setMinReceived(new BigDecimal(infoData.getAmountOut()).multiply(new BigDecimal((symbols == null || symbols.isEmpty() || isTRXToWTRX(symbols.get(0), symbols.get(symbols.size() + (-1)))) ? 1.0d : 0.995d)).stripTrailingZeros().toPlainString());
        swapTokenInfoBean.setPriceImpact(infoData.getImpact());
        swapTokenInfoBean.setReceived(infoData.getAmountOut());
        swapTokenInfoBean.setInUsd(infoData.getInUsd());
        swapTokenInfoBean.setOutUsd(infoData.getOutUsd());
        return swapTokenInfoBean;
    }

    private static boolean isTRXToWTRX(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return ("TRX".equalsIgnoreCase(str) && "WTRX".equalsIgnoreCase(str2)) || ("WTRX".equalsIgnoreCase(str) && "TRX".equalsIgnoreCase(str2));
    }

    public static SwapTokenInfoBean buildEmpty() {
        SwapTokenInfoBean swapTokenInfoBean = new SwapTokenInfoBean();
        swapTokenInfoBean.setRate(TokenHolder.PLACE_HOLDER);
        swapTokenInfoBean.setFee("0");
        swapTokenInfoBean.setMinReceived("0");
        swapTokenInfoBean.setPriceImpact("0");
        return swapTokenInfoBean;
    }
}
