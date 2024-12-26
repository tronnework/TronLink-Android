package com.tron.wallet.business.addassets2.bean;
public class BaseOutput {
    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BaseOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BaseOutput) {
            BaseOutput baseOutput = (BaseOutput) obj;
            if (baseOutput.canEqual(this) && getCode() == baseOutput.getCode()) {
                String message = getMessage();
                String message2 = baseOutput.getMessage();
                return message != null ? message.equals(message2) : message2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String message = getMessage();
        return ((getCode() + 59) * 59) + (message == null ? 43 : message.hashCode());
    }

    public String toString() {
        return "BaseOutput(code=" + getCode() + ", message=" + getMessage() + ")";
    }
}
