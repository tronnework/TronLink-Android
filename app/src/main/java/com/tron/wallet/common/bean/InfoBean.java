package com.tron.wallet.common.bean;

import java.util.List;
public class InfoBean {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean {
        private int frequencyHour;
        private String imageLink;
        private long lastShowTimestamp;
        private int playId;
        private int playLimit;
        private int showCounter;
        private String urlLink;

        public int getFrequencyHour() {
            return this.frequencyHour;
        }

        public String getImageLink() {
            return this.imageLink;
        }

        public long getLastShowTimestamp() {
            return this.lastShowTimestamp;
        }

        public int getPlayId() {
            return this.playId;
        }

        public int getPlayLimit() {
            return this.playLimit;
        }

        public int getShowCounter() {
            return this.showCounter;
        }

        public String getUrlLink() {
            return this.urlLink;
        }

        public void setFrequencyHour(int i) {
            this.frequencyHour = i;
        }

        public void setImageLink(String str) {
            this.imageLink = str;
        }

        public void setLastShowTimestamp(long j) {
            this.lastShowTimestamp = j;
        }

        public void setPlayId(int i) {
            this.playId = i;
        }

        public void setPlayLimit(int i) {
            this.playLimit = i;
        }

        public void setShowCounter(int i) {
            this.showCounter = i;
        }

        public void setUrlLink(String str) {
            this.urlLink = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
