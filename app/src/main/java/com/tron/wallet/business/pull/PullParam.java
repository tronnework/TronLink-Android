package com.tron.wallet.business.pull;
public class PullParam {
    private String action;
    private String actionId;
    private String callbackUrl;
    private String chainId;
    private String dappIcon;
    private String dappName;
    private String loginAddress;
    private String protocol;
    private String url;
    private String version;

    public String getAction() {
        return this.action;
    }

    public String getActionId() {
        return this.actionId;
    }

    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    public String getChainId() {
        return this.chainId;
    }

    public String getDappIcon() {
        return this.dappIcon;
    }

    public String getDappName() {
        return this.dappName;
    }

    public String getLoginAddress() {
        return this.loginAddress;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getUrl() {
        return this.url;
    }

    public String getVersion() {
        return this.version;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setActionId(String str) {
        this.actionId = str;
    }

    public void setCallbackUrl(String str) {
        this.callbackUrl = str;
    }

    public void setChainId(String str) {
        this.chainId = str;
    }

    public void setDappIcon(String str) {
        this.dappIcon = str;
    }

    public void setDappName(String str) {
        this.dappName = str;
    }

    public void setLoginAddress(String str) {
        this.loginAddress = str;
    }

    public void setProtocol(String str) {
        this.protocol = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof PullParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PullParam) {
            PullParam pullParam = (PullParam) obj;
            if (pullParam.canEqual(this)) {
                String action = getAction();
                String action2 = pullParam.getAction();
                if (action != null ? action.equals(action2) : action2 == null) {
                    String actionId = getActionId();
                    String actionId2 = pullParam.getActionId();
                    if (actionId != null ? actionId.equals(actionId2) : actionId2 == null) {
                        String protocol = getProtocol();
                        String protocol2 = pullParam.getProtocol();
                        if (protocol != null ? protocol.equals(protocol2) : protocol2 == null) {
                            String version = getVersion();
                            String version2 = pullParam.getVersion();
                            if (version != null ? version.equals(version2) : version2 == null) {
                                String chainId = getChainId();
                                String chainId2 = pullParam.getChainId();
                                if (chainId != null ? chainId.equals(chainId2) : chainId2 == null) {
                                    String url = getUrl();
                                    String url2 = pullParam.getUrl();
                                    if (url != null ? url.equals(url2) : url2 == null) {
                                        String dappName = getDappName();
                                        String dappName2 = pullParam.getDappName();
                                        if (dappName != null ? dappName.equals(dappName2) : dappName2 == null) {
                                            String callbackUrl = getCallbackUrl();
                                            String callbackUrl2 = pullParam.getCallbackUrl();
                                            if (callbackUrl != null ? callbackUrl.equals(callbackUrl2) : callbackUrl2 == null) {
                                                String dappIcon = getDappIcon();
                                                String dappIcon2 = pullParam.getDappIcon();
                                                if (dappIcon != null ? dappIcon.equals(dappIcon2) : dappIcon2 == null) {
                                                    String loginAddress = getLoginAddress();
                                                    String loginAddress2 = pullParam.getLoginAddress();
                                                    return loginAddress != null ? loginAddress.equals(loginAddress2) : loginAddress2 == null;
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
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String action = getAction();
        int hashCode = action == null ? 43 : action.hashCode();
        String actionId = getActionId();
        int hashCode2 = ((hashCode + 59) * 59) + (actionId == null ? 43 : actionId.hashCode());
        String protocol = getProtocol();
        int hashCode3 = (hashCode2 * 59) + (protocol == null ? 43 : protocol.hashCode());
        String version = getVersion();
        int hashCode4 = (hashCode3 * 59) + (version == null ? 43 : version.hashCode());
        String chainId = getChainId();
        int hashCode5 = (hashCode4 * 59) + (chainId == null ? 43 : chainId.hashCode());
        String url = getUrl();
        int hashCode6 = (hashCode5 * 59) + (url == null ? 43 : url.hashCode());
        String dappName = getDappName();
        int hashCode7 = (hashCode6 * 59) + (dappName == null ? 43 : dappName.hashCode());
        String callbackUrl = getCallbackUrl();
        int hashCode8 = (hashCode7 * 59) + (callbackUrl == null ? 43 : callbackUrl.hashCode());
        String dappIcon = getDappIcon();
        int hashCode9 = (hashCode8 * 59) + (dappIcon == null ? 43 : dappIcon.hashCode());
        String loginAddress = getLoginAddress();
        return (hashCode9 * 59) + (loginAddress != null ? loginAddress.hashCode() : 43);
    }

    public String toString() {
        return "PullParam(action=" + getAction() + ", actionId=" + getActionId() + ", protocol=" + getProtocol() + ", version=" + getVersion() + ", chainId=" + getChainId() + ", url=" + getUrl() + ", dappName=" + getDappName() + ", callbackUrl=" + getCallbackUrl() + ", dappIcon=" + getDappIcon() + ", loginAddress=" + getLoginAddress() + ")";
    }
}
