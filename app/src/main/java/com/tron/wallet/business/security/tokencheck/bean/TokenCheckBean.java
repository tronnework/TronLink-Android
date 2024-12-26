package com.tron.wallet.business.security.tokencheck.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.wallet.common.config.TronConfig;
import java.io.Serializable;
public class TokenCheckBean implements Serializable {
    private static final long serialVersionUID = 1;
    @JsonProperty("balanceStr")
    private String balanceStr;
    @JsonProperty("blackListType")
    private Integer blackListType;
    @JsonProperty("cnyCount")
    private Double cnyCount;
    @JsonProperty("cnyPrice")
    private String cnyPrice;
    @JsonProperty("contractAddress")
    private String contractAddress;
    public Long id;
    @JsonProperty("increaseTotalSupply")
    private Integer increaseTotalSupply;
    @JsonProperty("isProxy")
    private Integer isProxy;
    @JsonProperty(FirebaseAnalytics.Param.LEVEL)
    private String level;
    @JsonProperty("logoUrl")
    private String logoUrl;
    @JsonProperty(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;
    @JsonProperty("openSource")
    private Integer openSource;
    @JsonProperty(TronConfig.PRECISION)
    private Integer precision;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("tokenId")
    private String tokenId;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("usdCount")
    private Double usdCount;
    @JsonProperty("usdPrice")
    private String usdPrice;

    public String getBalanceStr() {
        return this.balanceStr;
    }

    public Integer getBlackListType() {
        return this.blackListType;
    }

    public Double getCnyCount() {
        return this.cnyCount;
    }

    public String getCnyPrice() {
        return this.cnyPrice;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getIncreaseTotalSupply() {
        return this.increaseTotalSupply;
    }

    public Integer getIsProxy() {
        return this.isProxy;
    }

    public String getLevel() {
        return this.level;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public String getName() {
        return this.name;
    }

    public Integer getOpenSource() {
        return this.openSource;
    }

    public Integer getPrecision() {
        return this.precision;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public Integer getType() {
        return this.type;
    }

    public Double getUsdCount() {
        return this.usdCount;
    }

    public String getUsdPrice() {
        return this.usdPrice;
    }

    public void setBalanceStr(String str) {
        this.balanceStr = str;
    }

    public void setBlackListType(Integer num) {
        this.blackListType = num;
    }

    public void setCnyCount(Double d) {
        this.cnyCount = d;
    }

    public void setCnyPrice(String str) {
        this.cnyPrice = str;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setIncreaseTotalSupply(Integer num) {
        this.increaseTotalSupply = num;
    }

    public void setIsProxy(Integer num) {
        this.isProxy = num;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOpenSource(Integer num) {
        this.openSource = num;
    }

    public void setPrecision(Integer num) {
        this.precision = num;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public void setUsdCount(Double d) {
        this.usdCount = d;
    }

    public void setUsdPrice(String str) {
        this.usdPrice = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TokenCheckBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TokenCheckBean) {
            TokenCheckBean tokenCheckBean = (TokenCheckBean) obj;
            if (tokenCheckBean.canEqual(this)) {
                Long id = getId();
                Long id2 = tokenCheckBean.getId();
                if (id != null ? id.equals(id2) : id2 == null) {
                    Integer type = getType();
                    Integer type2 = tokenCheckBean.getType();
                    if (type != null ? type.equals(type2) : type2 == null) {
                        Double usdCount = getUsdCount();
                        Double usdCount2 = tokenCheckBean.getUsdCount();
                        if (usdCount != null ? usdCount.equals(usdCount2) : usdCount2 == null) {
                            Double cnyCount = getCnyCount();
                            Double cnyCount2 = tokenCheckBean.getCnyCount();
                            if (cnyCount != null ? cnyCount.equals(cnyCount2) : cnyCount2 == null) {
                                Integer precision = getPrecision();
                                Integer precision2 = tokenCheckBean.getPrecision();
                                if (precision != null ? precision.equals(precision2) : precision2 == null) {
                                    Integer blackListType = getBlackListType();
                                    Integer blackListType2 = tokenCheckBean.getBlackListType();
                                    if (blackListType != null ? blackListType.equals(blackListType2) : blackListType2 == null) {
                                        Integer increaseTotalSupply = getIncreaseTotalSupply();
                                        Integer increaseTotalSupply2 = tokenCheckBean.getIncreaseTotalSupply();
                                        if (increaseTotalSupply != null ? increaseTotalSupply.equals(increaseTotalSupply2) : increaseTotalSupply2 == null) {
                                            Integer openSource = getOpenSource();
                                            Integer openSource2 = tokenCheckBean.getOpenSource();
                                            if (openSource != null ? openSource.equals(openSource2) : openSource2 == null) {
                                                Integer isProxy = getIsProxy();
                                                Integer isProxy2 = tokenCheckBean.getIsProxy();
                                                if (isProxy != null ? isProxy.equals(isProxy2) : isProxy2 == null) {
                                                    String name = getName();
                                                    String name2 = tokenCheckBean.getName();
                                                    if (name != null ? name.equals(name2) : name2 == null) {
                                                        String shortName = getShortName();
                                                        String shortName2 = tokenCheckBean.getShortName();
                                                        if (shortName != null ? shortName.equals(shortName2) : shortName2 == null) {
                                                            String contractAddress = getContractAddress();
                                                            String contractAddress2 = tokenCheckBean.getContractAddress();
                                                            if (contractAddress != null ? contractAddress.equals(contractAddress2) : contractAddress2 == null) {
                                                                String tokenId = getTokenId();
                                                                String tokenId2 = tokenCheckBean.getTokenId();
                                                                if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                                                                    String balanceStr = getBalanceStr();
                                                                    String balanceStr2 = tokenCheckBean.getBalanceStr();
                                                                    if (balanceStr != null ? balanceStr.equals(balanceStr2) : balanceStr2 == null) {
                                                                        String usdPrice = getUsdPrice();
                                                                        String usdPrice2 = tokenCheckBean.getUsdPrice();
                                                                        if (usdPrice != null ? usdPrice.equals(usdPrice2) : usdPrice2 == null) {
                                                                            String cnyPrice = getCnyPrice();
                                                                            String cnyPrice2 = tokenCheckBean.getCnyPrice();
                                                                            if (cnyPrice != null ? cnyPrice.equals(cnyPrice2) : cnyPrice2 == null) {
                                                                                String logoUrl = getLogoUrl();
                                                                                String logoUrl2 = tokenCheckBean.getLogoUrl();
                                                                                if (logoUrl != null ? logoUrl.equals(logoUrl2) : logoUrl2 == null) {
                                                                                    String level = getLevel();
                                                                                    String level2 = tokenCheckBean.getLevel();
                                                                                    return level != null ? level.equals(level2) : level2 == null;
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
        Long id = getId();
        int hashCode = id == null ? 43 : id.hashCode();
        Integer type = getType();
        int hashCode2 = ((hashCode + 59) * 59) + (type == null ? 43 : type.hashCode());
        Double usdCount = getUsdCount();
        int hashCode3 = (hashCode2 * 59) + (usdCount == null ? 43 : usdCount.hashCode());
        Double cnyCount = getCnyCount();
        int hashCode4 = (hashCode3 * 59) + (cnyCount == null ? 43 : cnyCount.hashCode());
        Integer precision = getPrecision();
        int hashCode5 = (hashCode4 * 59) + (precision == null ? 43 : precision.hashCode());
        Integer blackListType = getBlackListType();
        int hashCode6 = (hashCode5 * 59) + (blackListType == null ? 43 : blackListType.hashCode());
        Integer increaseTotalSupply = getIncreaseTotalSupply();
        int hashCode7 = (hashCode6 * 59) + (increaseTotalSupply == null ? 43 : increaseTotalSupply.hashCode());
        Integer openSource = getOpenSource();
        int hashCode8 = (hashCode7 * 59) + (openSource == null ? 43 : openSource.hashCode());
        Integer isProxy = getIsProxy();
        int hashCode9 = (hashCode8 * 59) + (isProxy == null ? 43 : isProxy.hashCode());
        String name = getName();
        int hashCode10 = (hashCode9 * 59) + (name == null ? 43 : name.hashCode());
        String shortName = getShortName();
        int hashCode11 = (hashCode10 * 59) + (shortName == null ? 43 : shortName.hashCode());
        String contractAddress = getContractAddress();
        int hashCode12 = (hashCode11 * 59) + (contractAddress == null ? 43 : contractAddress.hashCode());
        String tokenId = getTokenId();
        int hashCode13 = (hashCode12 * 59) + (tokenId == null ? 43 : tokenId.hashCode());
        String balanceStr = getBalanceStr();
        int hashCode14 = (hashCode13 * 59) + (balanceStr == null ? 43 : balanceStr.hashCode());
        String usdPrice = getUsdPrice();
        int hashCode15 = (hashCode14 * 59) + (usdPrice == null ? 43 : usdPrice.hashCode());
        String cnyPrice = getCnyPrice();
        int hashCode16 = (hashCode15 * 59) + (cnyPrice == null ? 43 : cnyPrice.hashCode());
        String logoUrl = getLogoUrl();
        int hashCode17 = (hashCode16 * 59) + (logoUrl == null ? 43 : logoUrl.hashCode());
        String level = getLevel();
        return (hashCode17 * 59) + (level != null ? level.hashCode() : 43);
    }

    public String toString() {
        return "TokenCheckBean(id=" + getId() + ", type=" + getType() + ", name=" + getName() + ", shortName=" + getShortName() + ", contractAddress=" + getContractAddress() + ", tokenId=" + getTokenId() + ", balanceStr=" + getBalanceStr() + ", usdCount=" + getUsdCount() + ", cnyCount=" + getCnyCount() + ", usdPrice=" + getUsdPrice() + ", cnyPrice=" + getCnyPrice() + ", logoUrl=" + getLogoUrl() + ", precision=" + getPrecision() + ", blackListType=" + getBlackListType() + ", increaseTotalSupply=" + getIncreaseTotalSupply() + ", openSource=" + getOpenSource() + ", isProxy=" + getIsProxy() + ", level=" + getLevel() + ")";
    }

    public TokenCheckBean(Long l, Integer num, String str, String str2, String str3, String str4, String str5, Double d, Double d2, String str6, String str7, String str8, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str9) {
        this.id = l;
        this.type = num;
        this.name = str;
        this.shortName = str2;
        this.contractAddress = str3;
        this.tokenId = str4;
        this.balanceStr = str5;
        this.usdCount = d;
        this.cnyCount = d2;
        this.usdPrice = str6;
        this.cnyPrice = str7;
        this.logoUrl = str8;
        this.precision = num2;
        this.blackListType = num3;
        this.increaseTotalSupply = num4;
        this.openSource = num5;
        this.isProxy = num6;
        this.level = str9;
    }

    public TokenCheckBean() {
    }
}
