package com.tron.wallet.business.tabdapp.browser.bean;

import java.util.List;
public class BrowserTabIndexBean {
    private int activeIndex;
    private int tabCount;
    private List<Integer> tabs;

    public int getActiveIndex() {
        return this.activeIndex;
    }

    public int getTabCount() {
        return this.tabCount;
    }

    public List<Integer> getTabs() {
        return this.tabs;
    }

    public void setActiveIndex(int i) {
        this.activeIndex = i;
    }

    public void setTabCount(int i) {
        this.tabCount = i;
    }

    public void setTabs(List<Integer> list) {
        this.tabs = list;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BrowserTabIndexBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BrowserTabIndexBean) {
            BrowserTabIndexBean browserTabIndexBean = (BrowserTabIndexBean) obj;
            if (browserTabIndexBean.canEqual(this) && getActiveIndex() == browserTabIndexBean.getActiveIndex() && getTabCount() == browserTabIndexBean.getTabCount()) {
                List<Integer> tabs = getTabs();
                List<Integer> tabs2 = browserTabIndexBean.getTabs();
                return tabs != null ? tabs.equals(tabs2) : tabs2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int activeIndex = ((getActiveIndex() + 59) * 59) + getTabCount();
        List<Integer> tabs = getTabs();
        return (activeIndex * 59) + (tabs == null ? 43 : tabs.hashCode());
    }

    public String toString() {
        return "BrowserTabIndexBean(activeIndex=" + getActiveIndex() + ", tabCount=" + getTabCount() + ", tabs=" + getTabs() + ")";
    }
}
