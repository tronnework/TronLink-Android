package com.tron.wallet.business.tabdapp.browser.search;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import j$.util.Objects;
import java.util.List;
public class SearchDappResultBean {
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<SearchDappBean> data;
    @SerializedName(NotificationCompat.CATEGORY_MESSAGE)
    private String msg;

    public Integer getCode() {
        return this.code;
    }

    public List<SearchDappBean> getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(Integer num) {
        this.code = num;
    }

    public void setData(List<SearchDappBean> list) {
        this.data = list;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SearchDappResultBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SearchDappResultBean) {
            SearchDappResultBean searchDappResultBean = (SearchDappResultBean) obj;
            if (searchDappResultBean.canEqual(this)) {
                Integer code = getCode();
                Integer code2 = searchDappResultBean.getCode();
                if (code != null ? code.equals(code2) : code2 == null) {
                    String msg = getMsg();
                    String msg2 = searchDappResultBean.getMsg();
                    if (msg != null ? msg.equals(msg2) : msg2 == null) {
                        List<SearchDappBean> data = getData();
                        List<SearchDappBean> data2 = searchDappResultBean.getData();
                        return data != null ? data.equals(data2) : data2 == null;
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
        Integer code = getCode();
        int hashCode = code == null ? 43 : code.hashCode();
        String msg = getMsg();
        int hashCode2 = ((hashCode + 59) * 59) + (msg == null ? 43 : msg.hashCode());
        List<SearchDappBean> data = getData();
        return (hashCode2 * 59) + (data != null ? data.hashCode() : 43);
    }

    public String toString() {
        return "SearchDappResultBean(code=" + getCode() + ", msg=" + getMsg() + ", data=" + getData() + ")";
    }

    public static class SearchDappBean {
        @SerializedName("classifyId")
        private Integer classifyId;
        @SerializedName("homeUrl")
        private String homeUrl;
        @SerializedName("imageUrl")
        private String imageUrl;
        private boolean isFromHttp;
        private String keyword;
        private int matchTitleIndex;
        private double matchTitlePercent;
        private int matchUrlIndex;
        private double matchUrlPercent;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        private Priority priority;
        @SerializedName(FirebaseAnalytics.Param.SCORE)
        private Integer score;

        public Integer getClassifyId() {
            return this.classifyId;
        }

        public String getHomeUrl() {
            return this.homeUrl;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public String getKeyword() {
            return this.keyword;
        }

        public int getMatchTitleIndex() {
            return this.matchTitleIndex;
        }

        public double getMatchTitlePercent() {
            return this.matchTitlePercent;
        }

        public int getMatchUrlIndex() {
            return this.matchUrlIndex;
        }

        public double getMatchUrlPercent() {
            return this.matchUrlPercent;
        }

        public String getName() {
            return this.name;
        }

        public Priority getPriority() {
            return this.priority;
        }

        public Integer getScore() {
            return this.score;
        }

        public boolean isFromHttp() {
            return this.isFromHttp;
        }

        public void setClassifyId(Integer num) {
            this.classifyId = num;
        }

        public void setFromHttp(boolean z) {
            this.isFromHttp = z;
        }

        public void setHomeUrl(String str) {
            this.homeUrl = str;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public void setKeyword(String str) {
            this.keyword = str;
        }

        public void setMatchTitleIndex(int i) {
            this.matchTitleIndex = i;
        }

        public void setMatchTitlePercent(double d) {
            this.matchTitlePercent = d;
        }

        public void setMatchUrlIndex(int i) {
            this.matchUrlIndex = i;
        }

        public void setMatchUrlPercent(double d) {
            this.matchUrlPercent = d;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public void setScore(Integer num) {
            this.score = num;
        }

        public String toString() {
            return "SearchDappResultBean.SearchDappBean(classifyId=" + getClassifyId() + ", name=" + getName() + ", imageUrl=" + getImageUrl() + ", homeUrl=" + getHomeUrl() + ", score=" + getScore() + ", keyword=" + getKeyword() + ", matchUrlIndex=" + getMatchUrlIndex() + ", matchTitleIndex=" + getMatchTitleIndex() + ", matchUrlPercent=" + getMatchUrlPercent() + ", matchTitlePercent=" + getMatchTitlePercent() + ", priority=" + getPriority() + ", isFromHttp=" + isFromHttp() + ")";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return Objects.equals(this.homeUrl, ((SearchDappBean) obj).homeUrl);
        }

        public int hashCode() {
            return Objects.hash(this.homeUrl);
        }

        public boolean isUrlMarching() {
            return StringTronUtil.equals(getHomeUrl().toLowerCase(), getKeyword().toLowerCase());
        }

        public boolean isTitleMarching() {
            return StringTronUtil.equals(getName().toLowerCase(), getKeyword().toLowerCase());
        }

        public void calculatePriory() {
            if (isUrlMarching()) {
                this.priority = Priority.URL_ALL_MATCHING;
            } else if (isTitleMarching()) {
                this.priority = Priority.TITLE_ALL_MATCHING;
            } else {
                this.priority = Priority.PART_MATCH;
                calculateMatchingIndexAndPercent();
            }
        }

        private void calculateMatchingIndexAndPercent() {
            if (!StringTronUtil.isEmpty(this.homeUrl)) {
                this.matchUrlIndex = this.homeUrl.toLowerCase().indexOf(this.keyword.toLowerCase());
                this.matchUrlPercent = BigDecimalUtils.div(Integer.valueOf(this.keyword.length()), Integer.valueOf(this.homeUrl.length()));
            }
            if (StringTronUtil.isEmpty(this.name)) {
                return;
            }
            this.matchTitleIndex = this.name.toLowerCase().indexOf(this.keyword.toLowerCase());
            this.matchTitlePercent = BigDecimalUtils.div(Integer.valueOf(this.keyword.length()), Integer.valueOf(this.name.length()));
        }
    }

    public enum Priority {
        URL_ALL_MATCHING(2),
        TITLE_ALL_MATCHING(1),
        PART_MATCH(0);
        
        private int value;

        public int getValue() {
            return this.value;
        }

        Priority(int i) {
            this.value = i;
        }
    }
}
