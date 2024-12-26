package com.tron.wallet.business.web;

import java.io.Serializable;
public class WebOptions implements Serializable {
    private static final long serialVersionUID = 152094365;
    private String dappAuthUrl;
    private String from;
    private String icon;
    private boolean injectTronWeb;
    private boolean isFinance;
    private String title;
    private String walletName;
    private String webUrl;
    private boolean isUseCache = true;
    private String screenModel = WebConstant.PORTRAIT;
    private boolean needOutside = false;
    private int code = 1;
    private DappOptions dappOptions = new DappOptions();

    public int getCode() {
        return this.code;
    }

    public String getDappAuthUrl() {
        return this.dappAuthUrl;
    }

    public DappOptions getDappOptions() {
        return this.dappOptions;
    }

    public String getFrom() {
        return this.from;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getScreenModel() {
        return this.screenModel;
    }

    public String getTitle() {
        return this.title;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public boolean isFinance() {
        return this.isFinance;
    }

    public boolean isInjectTronWeb() {
        return this.injectTronWeb;
    }

    public boolean isNeedOutside() {
        return this.needOutside;
    }

    public boolean isUseCache() {
        return this.isUseCache;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setDappAuthUrl(String str) {
        this.dappAuthUrl = str;
    }

    public void setDappOptions(DappOptions dappOptions) {
        this.dappOptions = dappOptions;
    }

    public void setFinance(boolean z) {
        this.isFinance = z;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setInjectTronWeb(boolean z) {
        this.injectTronWeb = z;
    }

    public void setNeedOutside(boolean z) {
        this.needOutside = z;
    }

    public void setScreenModel(String str) {
        this.screenModel = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUseCache(boolean z) {
        this.isUseCache = z;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    public void setWebUrl(String str) {
        this.webUrl = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WebOptions;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof WebOptions) {
            WebOptions webOptions = (WebOptions) obj;
            if (webOptions.canEqual(this) && isUseCache() == webOptions.isUseCache() && isNeedOutside() == webOptions.isNeedOutside() && getCode() == webOptions.getCode() && isInjectTronWeb() == webOptions.isInjectTronWeb() && isFinance() == webOptions.isFinance()) {
                String screenModel = getScreenModel();
                String screenModel2 = webOptions.getScreenModel();
                if (screenModel != null ? screenModel.equals(screenModel2) : screenModel2 == null) {
                    String walletName = getWalletName();
                    String walletName2 = webOptions.getWalletName();
                    if (walletName != null ? walletName.equals(walletName2) : walletName2 == null) {
                        DappOptions dappOptions = getDappOptions();
                        DappOptions dappOptions2 = webOptions.getDappOptions();
                        if (dappOptions != null ? dappOptions.equals(dappOptions2) : dappOptions2 == null) {
                            String title = getTitle();
                            String title2 = webOptions.getTitle();
                            if (title != null ? title.equals(title2) : title2 == null) {
                                String webUrl = getWebUrl();
                                String webUrl2 = webOptions.getWebUrl();
                                if (webUrl != null ? webUrl.equals(webUrl2) : webUrl2 == null) {
                                    String dappAuthUrl = getDappAuthUrl();
                                    String dappAuthUrl2 = webOptions.getDappAuthUrl();
                                    if (dappAuthUrl != null ? dappAuthUrl.equals(dappAuthUrl2) : dappAuthUrl2 == null) {
                                        String icon = getIcon();
                                        String icon2 = webOptions.getIcon();
                                        if (icon != null ? icon.equals(icon2) : icon2 == null) {
                                            String from = getFrom();
                                            String from2 = webOptions.getFrom();
                                            return from != null ? from.equals(from2) : from2 == null;
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
        int code = (((((((((isUseCache() ? 79 : 97) + 59) * 59) + (isNeedOutside() ? 79 : 97)) * 59) + getCode()) * 59) + (isInjectTronWeb() ? 79 : 97)) * 59) + (isFinance() ? 79 : 97);
        String screenModel = getScreenModel();
        int hashCode = (code * 59) + (screenModel == null ? 43 : screenModel.hashCode());
        String walletName = getWalletName();
        int hashCode2 = (hashCode * 59) + (walletName == null ? 43 : walletName.hashCode());
        DappOptions dappOptions = getDappOptions();
        int hashCode3 = (hashCode2 * 59) + (dappOptions == null ? 43 : dappOptions.hashCode());
        String title = getTitle();
        int hashCode4 = (hashCode3 * 59) + (title == null ? 43 : title.hashCode());
        String webUrl = getWebUrl();
        int hashCode5 = (hashCode4 * 59) + (webUrl == null ? 43 : webUrl.hashCode());
        String dappAuthUrl = getDappAuthUrl();
        int hashCode6 = (hashCode5 * 59) + (dappAuthUrl == null ? 43 : dappAuthUrl.hashCode());
        String icon = getIcon();
        int hashCode7 = (hashCode6 * 59) + (icon == null ? 43 : icon.hashCode());
        String from = getFrom();
        return (hashCode7 * 59) + (from != null ? from.hashCode() : 43);
    }

    public String toString() {
        return "WebOptions(isUseCache=" + isUseCache() + ", screenModel=" + getScreenModel() + ", needOutside=" + isNeedOutside() + ", walletName=" + getWalletName() + ", code=" + getCode() + ", dappOptions=" + getDappOptions() + ", title=" + getTitle() + ", webUrl=" + getWebUrl() + ", dappAuthUrl=" + getDappAuthUrl() + ", icon=" + getIcon() + ", injectTronWeb=" + isInjectTronWeb() + ", isFinance=" + isFinance() + ", from=" + getFrom() + ")";
    }

    public static class WebOptionsBuild {
        private WebOptions webOptions = new WebOptions();

        public WebOptions build() {
            return this.webOptions;
        }

        public WebOptionsBuild addUseCache(boolean z) {
            this.webOptions.isUseCache = z;
            return this;
        }

        public WebOptionsBuild addScreenModel(String str) {
            this.webOptions.screenModel = str;
            return this;
        }

        public WebOptionsBuild addNeedOutside(boolean z) {
            this.webOptions.needOutside = z;
            return this;
        }

        public WebOptionsBuild addWallerName(String str) {
            this.webOptions.walletName = str;
            return this;
        }

        public WebOptionsBuild addDappOptions(DappOptions dappOptions) {
            this.webOptions.dappOptions = dappOptions;
            return this;
        }

        public WebOptionsBuild addCode(int i) {
            this.webOptions.code = i;
            return this;
        }

        public WebOptionsBuild addTitle(String str) {
            this.webOptions.title = str;
            return this;
        }

        public WebOptionsBuild addWebUrl(String str) {
            this.webOptions.webUrl = str;
            return this;
        }

        public WebOptionsBuild addDappAuthUrl(String str) {
            this.webOptions.dappAuthUrl = str;
            return this;
        }

        public WebOptionsBuild addIconUrl(String str) {
            this.webOptions.icon = str;
            return this;
        }

        public WebOptionsBuild addIsFinance(boolean z) {
            this.webOptions.isFinance = z;
            return this;
        }

        public WebOptionsBuild addInjectTronweb(boolean z) {
            this.webOptions.injectTronWeb = z;
            return this;
        }

        public WebOptionsBuild addFrom(String str) {
            this.webOptions.from = str;
            return this;
        }
    }
}
