package com.tron.wallet.business.web;

import android.content.Context;
import android.text.TextUtils;
public class CommonWebSetting {
    private static String SCHEME_BROWSER = "browser://";
    private static String SCHEME_COMMON = "https://";
    private int code;
    private Context context;
    private boolean fromDappSearch;
    private String icon;
    private String identifier;
    private boolean isDapp;
    private boolean isLoadZTronJs;
    private boolean isUseCache;
    private boolean outside;
    private String screenModel;
    private String title;
    private String url;
    private String walletName;

    public int getCode() {
        return this.code;
    }

    public Context getContext() {
        return this.context;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getScreenModel() {
        return this.screenModel;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public boolean isDapp() {
        return this.isDapp;
    }

    public boolean isFromDappSearch() {
        return this.fromDappSearch;
    }

    public boolean isLoadZTronJs() {
        return this.isLoadZTronJs;
    }

    public boolean isOutside() {
        return this.outside;
    }

    public boolean isUseCache() {
        return this.isUseCache;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDapp(boolean z) {
        this.isDapp = z;
    }

    public void setFromDappSearch(boolean z) {
        this.fromDappSearch = z;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setLoadZTronJs(boolean z) {
        this.isLoadZTronJs = z;
    }

    public void setOutside(boolean z) {
        this.outside = z;
    }

    public void setScreenModel(String str) {
        this.screenModel = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setUseCache(boolean z) {
        this.isUseCache = z;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof CommonWebSetting;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CommonWebSetting) {
            CommonWebSetting commonWebSetting = (CommonWebSetting) obj;
            if (commonWebSetting.canEqual(this) && isUseCache() == commonWebSetting.isUseCache() && getCode() == commonWebSetting.getCode() && isDapp() == commonWebSetting.isDapp() && isLoadZTronJs() == commonWebSetting.isLoadZTronJs() && isFromDappSearch() == commonWebSetting.isFromDappSearch() && isOutside() == commonWebSetting.isOutside()) {
                Context context = getContext();
                Context context2 = commonWebSetting.getContext();
                if (context != null ? context.equals(context2) : context2 == null) {
                    String url = getUrl();
                    String url2 = commonWebSetting.getUrl();
                    if (url != null ? url.equals(url2) : url2 == null) {
                        String title = getTitle();
                        String title2 = commonWebSetting.getTitle();
                        if (title != null ? title.equals(title2) : title2 == null) {
                            String icon = getIcon();
                            String icon2 = commonWebSetting.getIcon();
                            if (icon != null ? icon.equals(icon2) : icon2 == null) {
                                String walletName = getWalletName();
                                String walletName2 = commonWebSetting.getWalletName();
                                if (walletName != null ? walletName.equals(walletName2) : walletName2 == null) {
                                    String screenModel = getScreenModel();
                                    String screenModel2 = commonWebSetting.getScreenModel();
                                    if (screenModel != null ? screenModel.equals(screenModel2) : screenModel2 == null) {
                                        String identifier = getIdentifier();
                                        String identifier2 = commonWebSetting.getIdentifier();
                                        return identifier != null ? identifier.equals(identifier2) : identifier2 == null;
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
        int code = (((((((((((isUseCache() ? 79 : 97) + 59) * 59) + getCode()) * 59) + (isDapp() ? 79 : 97)) * 59) + (isLoadZTronJs() ? 79 : 97)) * 59) + (isFromDappSearch() ? 79 : 97)) * 59) + (isOutside() ? 79 : 97);
        Context context = getContext();
        int hashCode = (code * 59) + (context == null ? 43 : context.hashCode());
        String url = getUrl();
        int hashCode2 = (hashCode * 59) + (url == null ? 43 : url.hashCode());
        String title = getTitle();
        int hashCode3 = (hashCode2 * 59) + (title == null ? 43 : title.hashCode());
        String icon = getIcon();
        int hashCode4 = (hashCode3 * 59) + (icon == null ? 43 : icon.hashCode());
        String walletName = getWalletName();
        int hashCode5 = (hashCode4 * 59) + (walletName == null ? 43 : walletName.hashCode());
        String screenModel = getScreenModel();
        int hashCode6 = (hashCode5 * 59) + (screenModel == null ? 43 : screenModel.hashCode());
        String identifier = getIdentifier();
        return (hashCode6 * 59) + (identifier != null ? identifier.hashCode() : 43);
    }

    public String toString() {
        return "CommonWebSetting(context=" + getContext() + ", url=" + getUrl() + ", title=" + getTitle() + ", isUseCache=" + isUseCache() + ", icon=" + getIcon() + ", walletName=" + getWalletName() + ", code=" + getCode() + ", isDapp=" + isDapp() + ", screenModel=" + getScreenModel() + ", isLoadZTronJs=" + isLoadZTronJs() + ", fromDappSearch=" + isFromDappSearch() + ", identifier=" + getIdentifier() + ", outside=" + isOutside() + ")";
    }

    private CommonWebSetting() {
        this.isUseCache = true;
        this.isDapp = false;
        this.screenModel = "1";
        this.isLoadZTronJs = true;
        this.fromDappSearch = false;
        this.outside = false;
    }

    public static Builder newBuilder(Context context, String str) {
        return new Builder(context, str);
    }

    public static class Builder {
        CommonWebSetting setting;

        public CommonWebSetting build() {
            return this.setting;
        }

        private Builder(Context context, String str) {
            CommonWebSetting commonWebSetting = new CommonWebSetting();
            this.setting = commonWebSetting;
            commonWebSetting.setContext(context);
            handleScheme(str);
        }

        private void handleScheme(String str) {
            if (!TextUtils.isEmpty(str) && str.startsWith(CommonWebSetting.SCHEME_BROWSER)) {
                this.setting.setOutside(true);
                this.setting.setUrl(str.replace(CommonWebSetting.SCHEME_BROWSER, CommonWebSetting.SCHEME_COMMON));
                return;
            }
            this.setting.setUrl(str);
        }

        public Builder setTitle(String str) {
            this.setting.setTitle(str);
            return this;
        }

        public Builder setUseCache(boolean z) {
            this.setting.setUseCache(z);
            return this;
        }

        public Builder setIcon(String str) {
            this.setting.setIcon(str);
            return this;
        }

        public Builder setWalletName(String str) {
            this.setting.setWalletName(str);
            return this;
        }

        public Builder setCode(int i) {
            this.setting.setCode(i);
            return this;
        }

        public Builder setDapp(boolean z) {
            this.setting.setDapp(z);
            return this;
        }

        public Builder setScreenModel(String str) {
            this.setting.setScreenModel(str);
            return this;
        }

        public Builder setLoadZTronJs(boolean z) {
            this.setting.setLoadZTronJs(z);
            return this;
        }

        public Builder setFromDappSearch(boolean z) {
            this.setting.setFromDappSearch(z);
            return this;
        }

        public Builder setIdentifier(String str) {
            this.setting.setIdentifier(str);
            return this;
        }

        public Builder setIsOutside(boolean z) {
            this.setting.setOutside(z);
            return this;
        }
    }
}
