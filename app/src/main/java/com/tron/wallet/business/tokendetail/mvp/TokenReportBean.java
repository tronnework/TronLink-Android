package com.tron.wallet.business.tokendetail.mvp;
public class TokenReportBean {
    String contactInfo;
    String desc;
    String reportList;
    String tokenAddr;
    String tokenSymbol;
    String tokenType;

    public String getContactInfo() {
        return this.contactInfo;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getReportList() {
        return this.reportList;
    }

    public String getTokenAddr() {
        return this.tokenAddr;
    }

    public String getTokenSymbol() {
        return this.tokenSymbol;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setContactInfo(String str) {
        this.contactInfo = str;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setReportList(String str) {
        this.reportList = str;
    }

    public void setTokenAddr(String str) {
        this.tokenAddr = str;
    }

    public void setTokenSymbol(String str) {
        this.tokenSymbol = str;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TokenReportBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TokenReportBean) {
            TokenReportBean tokenReportBean = (TokenReportBean) obj;
            if (tokenReportBean.canEqual(this)) {
                String tokenAddr = getTokenAddr();
                String tokenAddr2 = tokenReportBean.getTokenAddr();
                if (tokenAddr != null ? tokenAddr.equals(tokenAddr2) : tokenAddr2 == null) {
                    String tokenType = getTokenType();
                    String tokenType2 = tokenReportBean.getTokenType();
                    if (tokenType != null ? tokenType.equals(tokenType2) : tokenType2 == null) {
                        String tokenSymbol = getTokenSymbol();
                        String tokenSymbol2 = tokenReportBean.getTokenSymbol();
                        if (tokenSymbol != null ? tokenSymbol.equals(tokenSymbol2) : tokenSymbol2 == null) {
                            String reportList = getReportList();
                            String reportList2 = tokenReportBean.getReportList();
                            if (reportList != null ? reportList.equals(reportList2) : reportList2 == null) {
                                String contactInfo = getContactInfo();
                                String contactInfo2 = tokenReportBean.getContactInfo();
                                if (contactInfo != null ? contactInfo.equals(contactInfo2) : contactInfo2 == null) {
                                    String desc = getDesc();
                                    String desc2 = tokenReportBean.getDesc();
                                    return desc != null ? desc.equals(desc2) : desc2 == null;
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
        String tokenAddr = getTokenAddr();
        int hashCode = tokenAddr == null ? 43 : tokenAddr.hashCode();
        String tokenType = getTokenType();
        int hashCode2 = ((hashCode + 59) * 59) + (tokenType == null ? 43 : tokenType.hashCode());
        String tokenSymbol = getTokenSymbol();
        int hashCode3 = (hashCode2 * 59) + (tokenSymbol == null ? 43 : tokenSymbol.hashCode());
        String reportList = getReportList();
        int hashCode4 = (hashCode3 * 59) + (reportList == null ? 43 : reportList.hashCode());
        String contactInfo = getContactInfo();
        int hashCode5 = (hashCode4 * 59) + (contactInfo == null ? 43 : contactInfo.hashCode());
        String desc = getDesc();
        return (hashCode5 * 59) + (desc != null ? desc.hashCode() : 43);
    }

    public String toString() {
        return "TokenReportBean(tokenAddr=" + getTokenAddr() + ", tokenType=" + getTokenType() + ", tokenSymbol=" + getTokenSymbol() + ", reportList=" + getReportList() + ", contactInfo=" + getContactInfo() + ", desc=" + getDesc() + ")";
    }
}
