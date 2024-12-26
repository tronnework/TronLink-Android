package com.tron.wallet.business.pull;

import java.util.Random;
public class PullResult {
    private static Random random = new Random();
    private String actionId;
    private int code;
    private int id = random.nextInt();
    private String message;

    public String getActionId() {
        return this.actionId;
    }

    public int getCode() {
        return this.code;
    }

    public int getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setActionId(String str) {
        this.actionId = str;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof PullResult;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PullResult) {
            PullResult pullResult = (PullResult) obj;
            if (pullResult.canEqual(this) && getId() == pullResult.getId() && getCode() == pullResult.getCode()) {
                String actionId = getActionId();
                String actionId2 = pullResult.getActionId();
                if (actionId != null ? actionId.equals(actionId2) : actionId2 == null) {
                    String message = getMessage();
                    String message2 = pullResult.getMessage();
                    return message != null ? message.equals(message2) : message2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int id = ((getId() + 59) * 59) + getCode();
        String actionId = getActionId();
        int hashCode = (id * 59) + (actionId == null ? 43 : actionId.hashCode());
        String message = getMessage();
        return (hashCode * 59) + (message != null ? message.hashCode() : 43);
    }

    public String toString() {
        return "PullResult(id=" + getId() + ", actionId=" + getActionId() + ", code=" + getCode() + ", message=" + getMessage() + ")";
    }
}
