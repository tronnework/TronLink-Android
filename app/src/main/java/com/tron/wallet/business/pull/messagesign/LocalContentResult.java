package com.tron.wallet.business.pull.messagesign;

import com.tron.wallet.business.pull.PullResult;
public class LocalContentResult extends PullResult {
    private String signedData;

    public String getSignedData() {
        return this.signedData;
    }

    public void setSignedData(String str) {
        this.signedData = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof LocalContentResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LocalContentResult) {
            LocalContentResult localContentResult = (LocalContentResult) obj;
            if (localContentResult.canEqual(this)) {
                String signedData = getSignedData();
                String signedData2 = localContentResult.getSignedData();
                return signedData != null ? signedData.equals(signedData2) : signedData2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String signedData = getSignedData();
        return 59 + (signedData == null ? 43 : signedData.hashCode());
    }

    @Override
    public String toString() {
        return "LocalContentResult(signedData=" + getSignedData() + ")";
    }
}
