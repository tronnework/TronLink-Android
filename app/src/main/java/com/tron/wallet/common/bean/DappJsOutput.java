package com.tron.wallet.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.net.SignatureManager;
import java.util.List;
import java.util.Map;
public class DappJsOutput {
    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public static class DataBean {
        private AccountActiveFeeParams accountActiveFeeParams;
        private BalanceLimit balanceLimit;
        private DappScore dappScore;
        private Financial financial;
        private Stop stop;
        private Map<String, String> swapFee;
        private SwapParamsBean swapParams;
        private TransferCost transferCost;
        private TronWebParamsBean tronWebParams;
        private Version version;

        public static class AccountActiveFeeParams {
            private String baseActiveFee;
            private String dappBaseActiveFee;
            private String dappExtraActiveFee;
            private String dappFeeBandwidth;
            private String extraActiveFee;
            private String feeBandwidth;
            private String feeEnergy;
            private String freeNetLimit;
            private String memoFee;
            private int triggerType;

            public String getBaseActiveFee() {
                return this.baseActiveFee;
            }

            public String getDappBaseActiveFee() {
                return this.dappBaseActiveFee;
            }

            public String getDappExtraActiveFee() {
                return this.dappExtraActiveFee;
            }

            public String getDappFeeBandwidth() {
                return this.dappFeeBandwidth;
            }

            public String getExtraActiveFee() {
                return this.extraActiveFee;
            }

            public String getFeeBandwidth() {
                return this.feeBandwidth;
            }

            public String getFeeEnergy() {
                return this.feeEnergy;
            }

            public String getFreeNetLimit() {
                return this.freeNetLimit;
            }

            public String getMemoFee() {
                return this.memoFee;
            }

            public int getTriggerType() {
                return this.triggerType;
            }

            public void setBaseActiveFee(String str) {
                this.baseActiveFee = str;
            }

            public void setDappBaseActiveFee(String str) {
                this.dappBaseActiveFee = str;
            }

            public void setDappExtraActiveFee(String str) {
                this.dappExtraActiveFee = str;
            }

            public void setDappFeeBandwidth(String str) {
                this.dappFeeBandwidth = str;
            }

            public void setExtraActiveFee(String str) {
                this.extraActiveFee = str;
            }

            public void setFeeBandwidth(String str) {
                this.feeBandwidth = str;
            }

            public void setFeeEnergy(String str) {
                this.feeEnergy = str;
            }

            public void setFreeNetLimit(String str) {
                this.freeNetLimit = str;
            }

            public void setMemoFee(String str) {
                this.memoFee = str;
            }

            public void setTriggerType(int i) {
                this.triggerType = i;
            }
        }

        public static class BalanceLimit {
            private double assetThousandthLimit;
            private double assetValueLimit;
            private long nftCountLimit;

            public double getAssetThousandthLimit() {
                return this.assetThousandthLimit;
            }

            public double getAssetValueLimit() {
                return this.assetValueLimit;
            }

            public long getNftCountLimit() {
                return this.nftCountLimit;
            }

            public void setAssetThousandthLimit(double d) {
                this.assetThousandthLimit = d;
            }

            public void setAssetValueLimit(double d) {
                this.assetValueLimit = d;
            }

            public void setNftCountLimit(long j) {
                this.nftCountLimit = j;
            }
        }

        public static class SwapParamsBean {
            private String justFee;

            public String getJustFee() {
                return this.justFee;
            }

            public void setJustFee(String str) {
                this.justFee = str;
            }
        }

        public static class TransferCost {
            private int energyCost;
            private int netCost;

            public int getEnergyCost() {
                return this.energyCost;
            }

            public int getNetCost() {
                return this.netCost;
            }

            public void setEnergyCost(int i) {
                this.energyCost = i;
            }

            public void setNetCost(int i) {
                this.netCost = i;
            }
        }

        public static class TronWebParamsBean {
            private String sunWebHash;
            private String sunWebUrl;
            private String tronWebHash;
            private String tronWebUrl;
            private String tronWebVersion;

            public String getSunWebHash() {
                return this.sunWebHash;
            }

            public String getSunWebUrl() {
                return this.sunWebUrl;
            }

            public String getTronWebHash() {
                return this.tronWebHash;
            }

            public String getTronWebUrl() {
                return this.tronWebUrl;
            }

            public String getTronWebVersion() {
                return this.tronWebVersion;
            }

            public void setSunWebHash(String str) {
                this.sunWebHash = str;
            }

            public void setSunWebUrl(String str) {
                this.sunWebUrl = str;
            }

            public void setTronWebHash(String str) {
                this.tronWebHash = str;
            }

            public void setTronWebUrl(String str) {
                this.tronWebUrl = str;
            }

            public void setTronWebVersion(String str) {
                this.tronWebVersion = str;
            }
        }

        public AccountActiveFeeParams getAccountActiveFeeParams() {
            return this.accountActiveFeeParams;
        }

        public BalanceLimit getBalanceLimit() {
            return this.balanceLimit;
        }

        public DappScore getDappScore() {
            return this.dappScore;
        }

        public Financial getFinancial() {
            return this.financial;
        }

        public Stop getStop() {
            return this.stop;
        }

        public Map<String, String> getSwapFee() {
            return this.swapFee;
        }

        public SwapParamsBean getSwapParams() {
            return this.swapParams;
        }

        public TransferCost getTransferCost() {
            return this.transferCost;
        }

        public TronWebParamsBean getTronWebParams() {
            return this.tronWebParams;
        }

        public Version getVersion() {
            return this.version;
        }

        public void setAccountActiveFeeParams(AccountActiveFeeParams accountActiveFeeParams) {
            this.accountActiveFeeParams = accountActiveFeeParams;
        }

        public void setBalanceLimit(BalanceLimit balanceLimit) {
            this.balanceLimit = balanceLimit;
        }

        public void setDappScore(DappScore dappScore) {
            this.dappScore = dappScore;
        }

        public void setFinancial(Financial financial) {
            this.financial = financial;
        }

        public void setStop(Stop stop) {
            this.stop = stop;
        }

        public void setSwapFee(Map<String, String> map) {
            this.swapFee = map;
        }

        public void setSwapParams(SwapParamsBean swapParamsBean) {
            this.swapParams = swapParamsBean;
        }

        public void setTransferCost(TransferCost transferCost) {
            this.transferCost = transferCost;
        }

        public void setTronWebParams(TronWebParamsBean tronWebParamsBean) {
            this.tronWebParams = tronWebParamsBean;
        }

        public void setVersion(Version version) {
            this.version = version;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof DataBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof DataBean) {
                DataBean dataBean = (DataBean) obj;
                if (dataBean.canEqual(this)) {
                    TronWebParamsBean tronWebParams = getTronWebParams();
                    TronWebParamsBean tronWebParams2 = dataBean.getTronWebParams();
                    if (tronWebParams != null ? tronWebParams.equals(tronWebParams2) : tronWebParams2 == null) {
                        SwapParamsBean swapParams = getSwapParams();
                        SwapParamsBean swapParams2 = dataBean.getSwapParams();
                        if (swapParams != null ? swapParams.equals(swapParams2) : swapParams2 == null) {
                            BalanceLimit balanceLimit = getBalanceLimit();
                            BalanceLimit balanceLimit2 = dataBean.getBalanceLimit();
                            if (balanceLimit != null ? balanceLimit.equals(balanceLimit2) : balanceLimit2 == null) {
                                AccountActiveFeeParams accountActiveFeeParams = getAccountActiveFeeParams();
                                AccountActiveFeeParams accountActiveFeeParams2 = dataBean.getAccountActiveFeeParams();
                                if (accountActiveFeeParams != null ? accountActiveFeeParams.equals(accountActiveFeeParams2) : accountActiveFeeParams2 == null) {
                                    TransferCost transferCost = getTransferCost();
                                    TransferCost transferCost2 = dataBean.getTransferCost();
                                    if (transferCost != null ? transferCost.equals(transferCost2) : transferCost2 == null) {
                                        DappScore dappScore = getDappScore();
                                        DappScore dappScore2 = dataBean.getDappScore();
                                        if (dappScore != null ? dappScore.equals(dappScore2) : dappScore2 == null) {
                                            Version version = getVersion();
                                            Version version2 = dataBean.getVersion();
                                            if (version != null ? version.equals(version2) : version2 == null) {
                                                Financial financial = getFinancial();
                                                Financial financial2 = dataBean.getFinancial();
                                                if (financial != null ? financial.equals(financial2) : financial2 == null) {
                                                    Stop stop = getStop();
                                                    Stop stop2 = dataBean.getStop();
                                                    if (stop != null ? stop.equals(stop2) : stop2 == null) {
                                                        Map<String, String> swapFee = getSwapFee();
                                                        Map<String, String> swapFee2 = dataBean.getSwapFee();
                                                        return swapFee != null ? swapFee.equals(swapFee2) : swapFee2 == null;
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
            return false;
        }

        public int hashCode() {
            TronWebParamsBean tronWebParams = getTronWebParams();
            int hashCode = tronWebParams == null ? 43 : tronWebParams.hashCode();
            SwapParamsBean swapParams = getSwapParams();
            int hashCode2 = ((hashCode + 59) * 59) + (swapParams == null ? 43 : swapParams.hashCode());
            BalanceLimit balanceLimit = getBalanceLimit();
            int hashCode3 = (hashCode2 * 59) + (balanceLimit == null ? 43 : balanceLimit.hashCode());
            AccountActiveFeeParams accountActiveFeeParams = getAccountActiveFeeParams();
            int hashCode4 = (hashCode3 * 59) + (accountActiveFeeParams == null ? 43 : accountActiveFeeParams.hashCode());
            TransferCost transferCost = getTransferCost();
            int hashCode5 = (hashCode4 * 59) + (transferCost == null ? 43 : transferCost.hashCode());
            DappScore dappScore = getDappScore();
            int hashCode6 = (hashCode5 * 59) + (dappScore == null ? 43 : dappScore.hashCode());
            Version version = getVersion();
            int hashCode7 = (hashCode6 * 59) + (version == null ? 43 : version.hashCode());
            Financial financial = getFinancial();
            int hashCode8 = (hashCode7 * 59) + (financial == null ? 43 : financial.hashCode());
            Stop stop = getStop();
            int hashCode9 = (hashCode8 * 59) + (stop == null ? 43 : stop.hashCode());
            Map<String, String> swapFee = getSwapFee();
            return (hashCode9 * 59) + (swapFee != null ? swapFee.hashCode() : 43);
        }

        public String toString() {
            return "DappJsOutput.DataBean(tronWebParams=" + getTronWebParams() + ", swapParams=" + getSwapParams() + ", balanceLimit=" + getBalanceLimit() + ", accountActiveFeeParams=" + getAccountActiveFeeParams() + ", transferCost=" + getTransferCost() + ", dappScore=" + getDappScore() + ", version=" + getVersion() + ", financial=" + getFinancial() + ", stop=" + getStop() + ", swapFee=" + getSwapFee() + ")";
        }

        public static class DappScore {
            private int walletConnect = 10;
            private int dappAccess = 1;
            private int dappSign = 15;
            private int wane = 1;

            public int getDappAccess() {
                return this.dappAccess;
            }

            public int getDappSign() {
                return this.dappSign;
            }

            public int getWalletConnect() {
                return this.walletConnect;
            }

            public int getWane() {
                return this.wane;
            }

            public void setDappAccess(int i) {
                this.dappAccess = i;
            }

            public void setDappSign(int i) {
                this.dappSign = i;
            }

            public void setWalletConnect(int i) {
                this.walletConnect = i;
            }

            public void setWane(int i) {
                this.wane = i;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof DappScore;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof DappScore) {
                    DappScore dappScore = (DappScore) obj;
                    return dappScore.canEqual(this) && getWalletConnect() == dappScore.getWalletConnect() && getDappAccess() == dappScore.getDappAccess() && getDappSign() == dappScore.getDappSign() && getWane() == dappScore.getWane();
                }
                return false;
            }

            public int hashCode() {
                return ((((((getWalletConnect() + 59) * 59) + getDappAccess()) * 59) + getDappSign()) * 59) + getWane();
            }

            public String toString() {
                return "DappJsOutput.DataBean.DappScore(walletConnect=" + getWalletConnect() + ", dappAccess=" + getDappAccess() + ", dappSign=" + getDappSign() + ", wane=" + getWane() + ")";
            }
        }

        public static class Version {
            private String currency;
            private String filterSmall;
            private String iosVersion;
            private int stakeEnable;
            private int stakeExpireDay = 3;

            public String getCurrency() {
                return this.currency;
            }

            public String getFilterSmall() {
                return this.filterSmall;
            }

            public String getIosVersion() {
                return this.iosVersion;
            }

            public int getStakeEnable() {
                return this.stakeEnable;
            }

            public int getStakeExpireDay() {
                return this.stakeExpireDay;
            }

            public void setCurrency(String str) {
                this.currency = str;
            }

            public void setFilterSmall(String str) {
                this.filterSmall = str;
            }

            public void setIosVersion(String str) {
                this.iosVersion = str;
            }

            public void setStakeEnable(int i) {
                this.stakeEnable = i;
            }

            public void setStakeExpireDay(int i) {
                this.stakeExpireDay = i;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Version;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Version) {
                    Version version = (Version) obj;
                    if (version.canEqual(this) && getStakeEnable() == version.getStakeEnable() && getStakeExpireDay() == version.getStakeExpireDay()) {
                        String iosVersion = getIosVersion();
                        String iosVersion2 = version.getIosVersion();
                        if (iosVersion != null ? iosVersion.equals(iosVersion2) : iosVersion2 == null) {
                            String filterSmall = getFilterSmall();
                            String filterSmall2 = version.getFilterSmall();
                            if (filterSmall != null ? filterSmall.equals(filterSmall2) : filterSmall2 == null) {
                                String currency = getCurrency();
                                String currency2 = version.getCurrency();
                                return currency != null ? currency.equals(currency2) : currency2 == null;
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
                int stakeEnable = ((getStakeEnable() + 59) * 59) + getStakeExpireDay();
                String iosVersion = getIosVersion();
                int hashCode = (stakeEnable * 59) + (iosVersion == null ? 43 : iosVersion.hashCode());
                String filterSmall = getFilterSmall();
                int hashCode2 = (hashCode * 59) + (filterSmall == null ? 43 : filterSmall.hashCode());
                String currency = getCurrency();
                return (hashCode2 * 59) + (currency != null ? currency.hashCode() : 43);
            }

            public String toString() {
                return "DappJsOutput.DataBean.Version(iosVersion=" + getIosVersion() + ", filterSmall=" + getFilterSmall() + ", currency=" + getCurrency() + ", stakeEnable=" + getStakeEnable() + ", stakeExpireDay=" + getStakeExpireDay() + ")";
            }
        }

        public static class Stop {
            @JsonProperty(FirebaseAnalytics.Param.CURRENCY)
            private List<String> currency;
            @JsonProperty(SignatureManager.Constants.Lang)
            private List<String> lang;

            public List<String> getCurrency() {
                return this.currency;
            }

            public List<String> getLang() {
                return this.lang;
            }

            @JsonProperty(FirebaseAnalytics.Param.CURRENCY)
            public void setCurrency(List<String> list) {
                this.currency = list;
            }

            @JsonProperty(SignatureManager.Constants.Lang)
            public void setLang(List<String> list) {
                this.lang = list;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Stop;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Stop) {
                    Stop stop = (Stop) obj;
                    if (stop.canEqual(this)) {
                        List<String> lang = getLang();
                        List<String> lang2 = stop.getLang();
                        if (lang != null ? lang.equals(lang2) : lang2 == null) {
                            List<String> currency = getCurrency();
                            List<String> currency2 = stop.getCurrency();
                            return currency != null ? currency.equals(currency2) : currency2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }

            public int hashCode() {
                List<String> lang = getLang();
                int hashCode = lang == null ? 43 : lang.hashCode();
                List<String> currency = getCurrency();
                return ((hashCode + 59) * 59) + (currency != null ? currency.hashCode() : 43);
            }

            public String toString() {
                return "DappJsOutput.DataBean.Stop(lang=" + getLang() + ", currency=" + getCurrency() + ")";
            }
        }

        public class Financial {
            private int financialShow;
            private String financialSingaporeUrl;
            private String financialUrl;

            public int getFinancialShow() {
                return this.financialShow;
            }

            public String getFinancialSingaporeUrl() {
                return this.financialSingaporeUrl;
            }

            public String getFinancialUrl() {
                return this.financialUrl;
            }

            public void setFinancialShow(int i) {
                this.financialShow = i;
            }

            public void setFinancialSingaporeUrl(String str) {
                this.financialSingaporeUrl = str;
            }

            public void setFinancialUrl(String str) {
                this.financialUrl = str;
            }

            public Financial() {
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Financial;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Financial) {
                    Financial financial = (Financial) obj;
                    if (financial.canEqual(this) && getFinancialShow() == financial.getFinancialShow()) {
                        String financialUrl = getFinancialUrl();
                        String financialUrl2 = financial.getFinancialUrl();
                        if (financialUrl != null ? financialUrl.equals(financialUrl2) : financialUrl2 == null) {
                            String financialSingaporeUrl = getFinancialSingaporeUrl();
                            String financialSingaporeUrl2 = financial.getFinancialSingaporeUrl();
                            return financialSingaporeUrl != null ? financialSingaporeUrl.equals(financialSingaporeUrl2) : financialSingaporeUrl2 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }

            public int hashCode() {
                String financialUrl = getFinancialUrl();
                int financialShow = ((getFinancialShow() + 59) * 59) + (financialUrl == null ? 43 : financialUrl.hashCode());
                String financialSingaporeUrl = getFinancialSingaporeUrl();
                return (financialShow * 59) + (financialSingaporeUrl != null ? financialSingaporeUrl.hashCode() : 43);
            }

            public String toString() {
                return "DappJsOutput.DataBean.Financial(financialUrl=" + getFinancialUrl() + ", financialSingaporeUrl=" + getFinancialSingaporeUrl() + ", financialShow=" + getFinancialShow() + ")";
            }
        }
    }
}
