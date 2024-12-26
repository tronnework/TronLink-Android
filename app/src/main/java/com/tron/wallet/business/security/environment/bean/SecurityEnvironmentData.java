package com.tron.wallet.business.security.environment.bean;

import com.tron.wallet.business.security.SecurityResult;
public class SecurityEnvironmentData {
    private SecurityResult resultData;
    private int riskNum;
    private int safeNum;
    private int waringNum;

    public SecurityResult getResultData() {
        return this.resultData;
    }

    public int getRiskNum() {
        return this.riskNum;
    }

    public int getSafeNum() {
        return this.safeNum;
    }

    public int getWaringNum() {
        return this.waringNum;
    }

    public void setResultData(SecurityResult securityResult) {
        this.resultData = securityResult;
    }

    public void setRiskNum(int i) {
        this.riskNum = i;
    }

    public void setSafeNum(int i) {
        this.safeNum = i;
    }

    public void setWaringNum(int i) {
        this.waringNum = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SecurityEnvironmentData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecurityEnvironmentData) {
            SecurityEnvironmentData securityEnvironmentData = (SecurityEnvironmentData) obj;
            if (securityEnvironmentData.canEqual(this) && getSafeNum() == securityEnvironmentData.getSafeNum() && getWaringNum() == securityEnvironmentData.getWaringNum() && getRiskNum() == securityEnvironmentData.getRiskNum()) {
                SecurityResult resultData = getResultData();
                SecurityResult resultData2 = securityEnvironmentData.getResultData();
                return resultData != null ? resultData.equals(resultData2) : resultData2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int safeNum = ((((getSafeNum() + 59) * 59) + getWaringNum()) * 59) + getRiskNum();
        SecurityResult resultData = getResultData();
        return (safeNum * 59) + (resultData == null ? 43 : resultData.hashCode());
    }

    public String toString() {
        return "SecurityEnvironmentData(safeNum=" + getSafeNum() + ", waringNum=" + getWaringNum() + ", riskNum=" + getRiskNum() + ", resultData=" + getResultData() + ")";
    }
}
