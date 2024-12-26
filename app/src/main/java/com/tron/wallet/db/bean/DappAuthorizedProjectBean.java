package com.tron.wallet.db.bean;
public class DappAuthorizedProjectBean {
    public static final int freeAuthorizedProject = 2;
    public static final int officialAuthorizedProject = 1;
    private String icon;
    private boolean isSelected;
    private String name;
    private int type;
    private String url;
    private String walletAddress;

    public String getIcon() {
        return this.icon;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public DappAuthorizedProjectBean(String str, String str2, String str3) {
        this.url = str;
        this.name = str2;
        this.icon = str3;
    }

    public DappAuthorizedProjectBean() {
    }

    public DappAuthorizedProjectBean(String str, String str2, String str3, String str4, int i) {
        this.url = str;
        this.name = str2;
        this.icon = str3;
        this.walletAddress = str4;
        this.type = i;
    }
}
