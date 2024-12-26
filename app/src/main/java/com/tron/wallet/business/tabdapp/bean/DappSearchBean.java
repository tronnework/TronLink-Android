package com.tron.wallet.business.tabdapp.bean;

import java.util.List;
public class DappSearchBean {
    private int code;
    private List<DataBean> data;
    private String message;

    public static class DataBean {
        private int classify_id;
        private String home_url;
        private int horiz_vert = 3;
        private String icon;
        private int id;
        private String image_url;
        private int is_anonymous;
        private int is_authorized;
        private int is_white;
        private String name;
        private String slogan;
        private String title;

        public int getClassify_id() {
            return this.classify_id;
        }

        public String getHome_url() {
            return this.home_url;
        }

        public int getHoriz_vert() {
            return this.horiz_vert;
        }

        public String getIcon() {
            return this.icon;
        }

        public int getId() {
            return this.id;
        }

        public String getImage_url() {
            return this.image_url;
        }

        public int getIs_anonymous() {
            return this.is_anonymous;
        }

        public int getIs_authorized() {
            return this.is_authorized;
        }

        public int getIs_white() {
            return this.is_white;
        }

        public String getName() {
            return this.name;
        }

        public String getSlogan() {
            return this.slogan;
        }

        public String getTitle() {
            return this.title;
        }

        public void setClassify_id(int i) {
            this.classify_id = i;
        }

        public void setHome_url(String str) {
            this.home_url = str;
        }

        public void setHoriz_vert(int i) {
            this.horiz_vert = i;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setImage_url(String str) {
            this.image_url = str;
        }

        public void setIs_anonymous(int i) {
            this.is_anonymous = i;
        }

        public void setIs_authorized(int i) {
            this.is_authorized = i;
        }

        public void setIs_white(int i) {
            this.is_white = i;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setSlogan(String str) {
            this.slogan = str;
        }

        public void setTitle(String str) {
            this.title = str;
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
