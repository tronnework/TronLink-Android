package com.tron.wallet.common.bean.user;

import java.util.List;
public class UserGuideOutput {
    public String code;
    public List<DataBean> data;
    public int page_index;
    public int pages;

    public static class DataBean {
        public String content;
        public float height;
        public int id;
        public String image;
        public String share_image;
        public String sort;
        public String thumbnail;
        public String title;
        public float width;
    }
}
