package com.tron.wallet.business.tabdapp.dapphistory.bean;

import com.google.gson.annotations.SerializedName;
public class DAppDataBean {
    private String address;
    @SerializedName("classify_id")
    private int classifyId;
    private int dataType;
    @SerializedName("home_url")
    private String homeUrl;
    private String icon;
    private int id;
    public Long identifierId;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("is_anonymous")
    private int isAnonymous;
    @SerializedName("is_authorized")
    private int isAuthorized;
    @SerializedName("is_white")
    private int isWhite;
    private String name;
    @SerializedName("horiz_vert")
    private int screenOrder;
    private boolean selected;
    private String slogan;
    private long timeStamp;
    private String title;

    public String getAddress() {
        return this.address;
    }

    public int getClassifyId() {
        return this.classifyId;
    }

    public int getDataType() {
        return this.dataType;
    }

    public String getHomeUrl() {
        return this.homeUrl;
    }

    public String getIcon() {
        return this.icon;
    }

    public int getId() {
        return this.id;
    }

    public Long getIdentifierId() {
        return this.identifierId;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public int getIsAnonymous() {
        return this.isAnonymous;
    }

    public int getIsAuthorized() {
        return this.isAuthorized;
    }

    public int getIsWhite() {
        return this.isWhite;
    }

    public String getName() {
        return this.name;
    }

    public int getScreenOrder() {
        return this.screenOrder;
    }

    public String getSlogan() {
        return this.slogan;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setClassifyId(int i) {
        this.classifyId = i;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public void setHomeUrl(String str) {
        this.homeUrl = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setIdentifierId(Long l) {
        this.identifierId = l;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setIsAnonymous(int i) {
        this.isAnonymous = i;
    }

    public void setIsAuthorized(int i) {
        this.isAuthorized = i;
    }

    public void setIsWhite(int i) {
        this.isWhite = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setScreenOrder(int i) {
        this.screenOrder = i;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void setSlogan(String str) {
        this.slogan = str;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public DAppDataBean(Long l, String str, int i, int i2, String str2, String str3, String str4, String str5, int i3, int i4, int i5, int i6, String str6, String str7, long j, int i7) {
        this.identifierId = l;
        this.address = str;
        this.id = i;
        this.classifyId = i2;
        this.name = str2;
        this.imageUrl = str3;
        this.homeUrl = str4;
        this.slogan = str5;
        this.isWhite = i3;
        this.isAuthorized = i4;
        this.isAnonymous = i5;
        this.screenOrder = i6;
        this.title = str6;
        this.icon = str7;
        this.timeStamp = j;
        this.dataType = i7;
    }

    public DAppDataBean() {
        this.screenOrder = 1;
    }
}
