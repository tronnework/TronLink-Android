package com.tron.wallet.business.security.tokencheck.view;
public class TokenCheckTag {
    String content;
    String logEvent;
    String popInfo;
    int type;

    public String getContent() {
        return this.content;
    }

    public String getLogEvent() {
        return this.logEvent;
    }

    public String getPopInfo() {
        return this.popInfo;
    }

    public int getType() {
        return this.type;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setLogEvent(String str) {
        this.logEvent = str;
    }

    public void setPopInfo(String str) {
        this.popInfo = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TokenCheckTag;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TokenCheckTag) {
            TokenCheckTag tokenCheckTag = (TokenCheckTag) obj;
            if (tokenCheckTag.canEqual(this) && getType() == tokenCheckTag.getType()) {
                String content = getContent();
                String content2 = tokenCheckTag.getContent();
                if (content != null ? content.equals(content2) : content2 == null) {
                    String popInfo = getPopInfo();
                    String popInfo2 = tokenCheckTag.getPopInfo();
                    if (popInfo != null ? popInfo.equals(popInfo2) : popInfo2 == null) {
                        String logEvent = getLogEvent();
                        String logEvent2 = tokenCheckTag.getLogEvent();
                        return logEvent != null ? logEvent.equals(logEvent2) : logEvent2 == null;
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
        String content = getContent();
        int type = ((getType() + 59) * 59) + (content == null ? 43 : content.hashCode());
        String popInfo = getPopInfo();
        int hashCode = (type * 59) + (popInfo == null ? 43 : popInfo.hashCode());
        String logEvent = getLogEvent();
        return (hashCode * 59) + (logEvent != null ? logEvent.hashCode() : 43);
    }

    public String toString() {
        return "TokenCheckTag(content=" + getContent() + ", type=" + getType() + ", popInfo=" + getPopInfo() + ", logEvent=" + getLogEvent() + ")";
    }
}
