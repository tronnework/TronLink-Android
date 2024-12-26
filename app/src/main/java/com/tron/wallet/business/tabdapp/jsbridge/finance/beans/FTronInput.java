package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;
public class FTronInput {
    private String currentAddress;
    private boolean isShow;
    private String projectId;
    private String title;
    private String tokenId;
    private String tokenSymbol;
    private String url;

    public String getCurrentAddress() {
        return this.currentAddress;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public String getTokenSymbol() {
        return this.tokenSymbol;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isShow() {
        return this.isShow;
    }

    public void setCurrentAddress(String str) {
        this.currentAddress = str;
    }

    public void setProjectId(String str) {
        this.projectId = str;
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setTokenSymbol(String str) {
        this.tokenSymbol = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof FTronInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FTronInput) {
            FTronInput fTronInput = (FTronInput) obj;
            if (fTronInput.canEqual(this) && isShow() == fTronInput.isShow()) {
                String tokenId = getTokenId();
                String tokenId2 = fTronInput.getTokenId();
                if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                    String tokenSymbol = getTokenSymbol();
                    String tokenSymbol2 = fTronInput.getTokenSymbol();
                    if (tokenSymbol != null ? tokenSymbol.equals(tokenSymbol2) : tokenSymbol2 == null) {
                        String projectId = getProjectId();
                        String projectId2 = fTronInput.getProjectId();
                        if (projectId != null ? projectId.equals(projectId2) : projectId2 == null) {
                            String url = getUrl();
                            String url2 = fTronInput.getUrl();
                            if (url != null ? url.equals(url2) : url2 == null) {
                                String title = getTitle();
                                String title2 = fTronInput.getTitle();
                                if (title != null ? title.equals(title2) : title2 == null) {
                                    String currentAddress = getCurrentAddress();
                                    String currentAddress2 = fTronInput.getCurrentAddress();
                                    return currentAddress != null ? currentAddress.equals(currentAddress2) : currentAddress2 == null;
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
        int i = isShow() ? 79 : 97;
        String tokenId = getTokenId();
        int hashCode = ((i + 59) * 59) + (tokenId == null ? 43 : tokenId.hashCode());
        String tokenSymbol = getTokenSymbol();
        int hashCode2 = (hashCode * 59) + (tokenSymbol == null ? 43 : tokenSymbol.hashCode());
        String projectId = getProjectId();
        int hashCode3 = (hashCode2 * 59) + (projectId == null ? 43 : projectId.hashCode());
        String url = getUrl();
        int hashCode4 = (hashCode3 * 59) + (url == null ? 43 : url.hashCode());
        String title = getTitle();
        int hashCode5 = (hashCode4 * 59) + (title == null ? 43 : title.hashCode());
        String currentAddress = getCurrentAddress();
        return (hashCode5 * 59) + (currentAddress != null ? currentAddress.hashCode() : 43);
    }

    public String toString() {
        return "FTronInput(tokenId=" + getTokenId() + ", tokenSymbol=" + getTokenSymbol() + ", projectId=" + getProjectId() + ", url=" + getUrl() + ", title=" + getTitle() + ", currentAddress=" + getCurrentAddress() + ", isShow=" + isShow() + ")";
    }
}
