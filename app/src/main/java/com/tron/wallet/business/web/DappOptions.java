package com.tron.wallet.business.web;

import java.io.Serializable;
public class DappOptions implements Serializable {
    private static final long serialVersionUID = 105058910;
    private String icon;
    private boolean injectZTron = false;

    public String getIcon() {
        return this.icon;
    }

    public boolean isInjectZTron() {
        return this.injectZTron;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setInjectZTron(boolean z) {
        this.injectZTron = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DappOptions;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DappOptions) {
            DappOptions dappOptions = (DappOptions) obj;
            if (dappOptions.canEqual(this) && isInjectZTron() == dappOptions.isInjectZTron()) {
                String icon = getIcon();
                String icon2 = dappOptions.getIcon();
                return icon != null ? icon.equals(icon2) : icon2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = isInjectZTron() ? 79 : 97;
        String icon = getIcon();
        return ((i + 59) * 59) + (icon == null ? 43 : icon.hashCode());
    }

    public String toString() {
        return "DappOptions(icon=" + getIcon() + ", injectZTron=" + isInjectZTron() + ")";
    }

    public static class DappOptionsBuild {
        private DappOptions dappOptions = new DappOptions();

        public DappOptions build() {
            return this.dappOptions;
        }

        public DappOptionsBuild addIcon(String str) {
            this.dappOptions.icon = str;
            return this;
        }

        public DappOptionsBuild addInjectZTron(boolean z) {
            this.dappOptions.injectZTron = z;
            return this;
        }
    }
}
