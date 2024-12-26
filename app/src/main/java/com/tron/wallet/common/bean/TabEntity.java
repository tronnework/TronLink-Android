package com.tron.wallet.common.bean;
public class TabEntity implements CustomTabEntity {
    int selectIcon;
    String title;
    int unSelectIcon;

    @Override
    public int getTabSelectedIcon() {
        return this.selectIcon;
    }

    @Override
    public String getTabTitle() {
        return this.title;
    }

    @Override
    public int getTabUnselectedIcon() {
        return this.unSelectIcon;
    }

    public TabEntity(String str, int i, int i2) {
        this.title = str;
        this.selectIcon = i;
        this.unSelectIcon = i2;
    }
}
