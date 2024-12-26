package com.tron.wallet.business.addassets2.adapter;
public class BeanExtraData {
    private String keyword;
    private Type type;

    public enum Type {
        NAME,
        SYMBOL,
        ID,
        ADDRESS
    }

    public String getKeyword() {
        return this.keyword;
    }

    public Type getType() {
        return this.type;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public void setType(Type type) {
        this.type = type;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BeanExtraData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BeanExtraData) {
            BeanExtraData beanExtraData = (BeanExtraData) obj;
            if (beanExtraData.canEqual(this)) {
                String keyword = getKeyword();
                String keyword2 = beanExtraData.getKeyword();
                if (keyword != null ? keyword.equals(keyword2) : keyword2 == null) {
                    Type type = getType();
                    Type type2 = beanExtraData.getType();
                    return type != null ? type.equals(type2) : type2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String keyword = getKeyword();
        int hashCode = keyword == null ? 43 : keyword.hashCode();
        Type type = getType();
        return ((hashCode + 59) * 59) + (type != null ? type.hashCode() : 43);
    }

    public String toString() {
        return "BeanExtraData(keyword=" + getKeyword() + ", type=" + getType() + ")";
    }
}
