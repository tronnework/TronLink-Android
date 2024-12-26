package com.tron.wallet.common.bean;
public class Result2<T> {
    private int code;
    private T data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof Result2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Result2) {
            Result2 result2 = (Result2) obj;
            if (result2.canEqual(this) && getCode() == result2.getCode()) {
                String message = getMessage();
                String message2 = result2.getMessage();
                if (message != null ? message.equals(message2) : message2 == null) {
                    T data = getData();
                    Object data2 = result2.getData();
                    return data != null ? data.equals(data2) : data2 == null;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        String message = getMessage();
        int code = ((getCode() + 59) * 59) + (message == null ? 43 : message.hashCode());
        T data = getData();
        return (code * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "Result2(code=" + getCode() + ", message=" + getMessage() + ", data=" + getData() + ")";
    }
}
