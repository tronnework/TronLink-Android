package com.tron.wallet.common.components.countdownview;
public class DynamicConfig {
    private Builder mBuilder;

    public static class BackgroundInfo {
        private Integer borderColor;
        private Float borderRadius;
        private Float borderSize;
        private Integer color;
        private Integer divisionLineColor;
        private Float divisionLineSize;
        private boolean hasData = false;
        private Boolean isShowBorder;
        private Boolean isShowDivisionLine;
        private Float radius;
        private Float size;

        public Integer getBorderColor() {
            return this.borderColor;
        }

        public Float getBorderRadius() {
            return this.borderRadius;
        }

        public Float getBorderSize() {
            return this.borderSize;
        }

        public Integer getColor() {
            return this.color;
        }

        public Integer getDivisionLineColor() {
            return this.divisionLineColor;
        }

        public Float getDivisionLineSize() {
            return this.divisionLineSize;
        }

        public Float getRadius() {
            return this.radius;
        }

        public Float getSize() {
            return this.size;
        }

        public Boolean isShowTimeBgBorder() {
            return this.isShowBorder;
        }

        public Boolean isShowTimeBgDivisionLine() {
            return this.isShowDivisionLine;
        }

        public BackgroundInfo setBorderColor(Integer num) {
            this.hasData = true;
            this.borderColor = num;
            return this;
        }

        public BackgroundInfo setBorderRadius(Float f) {
            this.hasData = true;
            this.borderRadius = f;
            return this;
        }

        public BackgroundInfo setBorderSize(Float f) {
            this.hasData = true;
            this.borderSize = f;
            return this;
        }

        public BackgroundInfo setColor(Integer num) {
            this.hasData = true;
            this.color = num;
            return this;
        }

        public BackgroundInfo setDivisionLineColor(Integer num) {
            this.hasData = true;
            this.divisionLineColor = num;
            return this;
        }

        public BackgroundInfo setDivisionLineSize(Float f) {
            this.hasData = true;
            this.divisionLineSize = f;
            return this;
        }

        public BackgroundInfo setRadius(Float f) {
            this.hasData = true;
            this.radius = f;
            return this;
        }

        public BackgroundInfo setShowTimeBgBorder(Boolean bool) {
            this.hasData = true;
            this.isShowBorder = bool;
            return this;
        }

        public BackgroundInfo setShowTimeBgDivisionLine(Boolean bool) {
            this.hasData = true;
            this.isShowDivisionLine = bool;
            return this;
        }

        public BackgroundInfo setSize(Float f) {
            this.hasData = true;
            this.size = f;
            return this;
        }
    }

    public static class SuffixGravity {
        public static final int BOTTOM = 2;
        public static final int CENTER = 1;
        public static final int TOP = 0;
    }

    private DynamicConfig(Builder builder) {
        this.mBuilder = builder;
    }

    public Float getTimeTextSize() {
        return this.mBuilder.timeTextSize;
    }

    public Integer getTimeTextColor() {
        return this.mBuilder.timeTextColor;
    }

    public Boolean isTimeTextBold() {
        return this.mBuilder.isTimeTextBold;
    }

    public Float getSuffixTextSize() {
        return this.mBuilder.suffixTextSize;
    }

    public Integer getSuffixTextColor() {
        return this.mBuilder.suffixTextColor;
    }

    public Boolean isSuffixTimeTextBold() {
        return this.mBuilder.isSuffixTextBold;
    }

    public String getSuffix() {
        return this.mBuilder.suffix;
    }

    public String getSuffixDay() {
        return this.mBuilder.suffixDay;
    }

    public String getSuffixHour() {
        return this.mBuilder.suffixHour;
    }

    public String getSuffixMinute() {
        return this.mBuilder.suffixMinute;
    }

    public String getSuffixSecond() {
        return this.mBuilder.suffixSecond;
    }

    public String getSuffixMillisecond() {
        return this.mBuilder.suffixMillisecond;
    }

    public Integer getSuffixGravity() {
        return this.mBuilder.suffixGravity;
    }

    public Float getSuffixLRMargin() {
        return this.mBuilder.suffixLRMargin;
    }

    public Float getSuffixDayLeftMargin() {
        return this.mBuilder.suffixDayLeftMargin;
    }

    public Float getSuffixDayRightMargin() {
        return this.mBuilder.suffixDayRightMargin;
    }

    public Float getSuffixHourLeftMargin() {
        return this.mBuilder.suffixHourLeftMargin;
    }

    public Float getSuffixHourRightMargin() {
        return this.mBuilder.suffixHourRightMargin;
    }

    public Float getSuffixMinuteLeftMargin() {
        return this.mBuilder.suffixMinuteLeftMargin;
    }

    public Float getSuffixMinuteRightMargin() {
        return this.mBuilder.suffixMinuteRightMargin;
    }

    public Float getSuffixSecondLeftMargin() {
        return this.mBuilder.suffixSecondLeftMargin;
    }

    public Float getSuffixSecondRightMargin() {
        return this.mBuilder.suffixSecondRightMargin;
    }

    public Float getSuffixMillisecondLeftMargin() {
        return this.mBuilder.suffixMillisecondLeftMargin;
    }

    public Boolean isConvertDaysToHours() {
        return Boolean.valueOf(this.mBuilder.isConvertDaysToHours);
    }

    public Boolean isShowDay() {
        return this.mBuilder.isShowDay;
    }

    public Boolean isShowHour() {
        return this.mBuilder.isShowHour;
    }

    public Boolean isShowMinute() {
        return this.mBuilder.isShowMinute;
    }

    public Boolean isShowSecond() {
        return this.mBuilder.isShowSecond;
    }

    public Boolean isShowMillisecond() {
        return this.mBuilder.isShowMillisecond;
    }

    public BackgroundInfo getBackgroundInfo() {
        return this.mBuilder.backgroundInfo;
    }

    public static class Builder {
        private BackgroundInfo backgroundInfo;
        private boolean isConvertDaysToHours;
        private Boolean isShowDay;
        private Boolean isShowHour;
        private Boolean isShowMillisecond;
        private Boolean isShowMinute;
        private Boolean isShowSecond;
        private Boolean isSuffixTextBold;
        private Boolean isTimeTextBold;
        private String suffix;
        private String suffixDay;
        private Float suffixDayLeftMargin;
        private Float suffixDayRightMargin;
        private Integer suffixGravity;
        private String suffixHour;
        private Float suffixHourLeftMargin;
        private Float suffixHourRightMargin;
        private Float suffixLRMargin;
        private String suffixMillisecond;
        private Float suffixMillisecondLeftMargin;
        private String suffixMinute;
        private Float suffixMinuteLeftMargin;
        private Float suffixMinuteRightMargin;
        private String suffixSecond;
        private Float suffixSecondLeftMargin;
        private Float suffixSecondRightMargin;
        private Integer suffixTextColor;
        private Float suffixTextSize;
        private Integer timeTextColor;
        private Float timeTextSize;

        public Builder setBackgroundInfo(BackgroundInfo backgroundInfo) {
            this.backgroundInfo = backgroundInfo;
            return this;
        }

        public Builder setShowDay(Boolean bool) {
            this.isShowDay = bool;
            return this;
        }

        public Builder setShowHour(Boolean bool) {
            this.isShowHour = bool;
            return this;
        }

        public Builder setShowMillisecond(Boolean bool) {
            this.isShowMillisecond = bool;
            return this;
        }

        public Builder setShowMinute(Boolean bool) {
            this.isShowMinute = bool;
            return this;
        }

        public Builder setShowSecond(Boolean bool) {
            this.isShowSecond = bool;
            return this;
        }

        public Builder setSuffix(String str) {
            this.suffix = str;
            return this;
        }

        public Builder setSuffixDay(String str) {
            this.suffixDay = str;
            return this;
        }

        public Builder setSuffixHour(String str) {
            this.suffixHour = str;
            return this;
        }

        public Builder setSuffixMillisecond(String str) {
            this.suffixMillisecond = str;
            return this;
        }

        public Builder setSuffixMinute(String str) {
            this.suffixMinute = str;
            return this;
        }

        public Builder setSuffixSecond(String str) {
            this.suffixSecond = str;
            return this;
        }

        public Builder setTimeTextSize(float f) {
            this.timeTextSize = Float.valueOf(f);
            return this;
        }

        public Builder setTimeTextColor(int i) {
            this.timeTextColor = Integer.valueOf(i);
            return this;
        }

        public Builder setTimeTextBold(boolean z) {
            this.isTimeTextBold = Boolean.valueOf(z);
            return this;
        }

        public Builder setSuffixTextSize(float f) {
            this.suffixTextSize = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixTextColor(int i) {
            this.suffixTextColor = Integer.valueOf(i);
            return this;
        }

        public Builder setSuffixTextBold(boolean z) {
            this.isSuffixTextBold = Boolean.valueOf(z);
            return this;
        }

        public Builder setSuffixLRMargin(float f) {
            this.suffixLRMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixDayLeftMargin(float f) {
            this.suffixDayLeftMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixDayRightMargin(float f) {
            this.suffixDayRightMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixHourLeftMargin(float f) {
            this.suffixHourLeftMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixHourRightMargin(float f) {
            this.suffixHourRightMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixMinuteLeftMargin(float f) {
            this.suffixMinuteLeftMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixMinuteRightMargin(float f) {
            this.suffixMinuteRightMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixSecondLeftMargin(float f) {
            this.suffixSecondLeftMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixSecondRightMargin(float f) {
            this.suffixSecondRightMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixMillisecondLeftMargin(float f) {
            this.suffixMillisecondLeftMargin = Float.valueOf(f);
            return this;
        }

        public Builder setSuffixGravity(int i) {
            this.suffixGravity = Integer.valueOf(i);
            return this;
        }

        public Builder setConvertDaysToHours(Boolean bool) {
            this.isConvertDaysToHours = bool.booleanValue();
            return this;
        }

        private void checkData() {
            Float f = this.timeTextSize;
            if (f != null && f.floatValue() <= 0.0f) {
                this.timeTextSize = null;
            }
            Float f2 = this.suffixTextSize;
            if (f2 != null && f2.floatValue() <= 0.0f) {
                this.suffixTextSize = null;
            }
            BackgroundInfo backgroundInfo = this.backgroundInfo;
            if (backgroundInfo != null && !backgroundInfo.hasData) {
                this.backgroundInfo = null;
            }
            BackgroundInfo backgroundInfo2 = this.backgroundInfo;
            if (backgroundInfo2 != null) {
                Boolean isShowTimeBgDivisionLine = backgroundInfo2.isShowTimeBgDivisionLine();
                if (isShowTimeBgDivisionLine == null || !isShowTimeBgDivisionLine.booleanValue()) {
                    this.backgroundInfo.setDivisionLineColor(null);
                    this.backgroundInfo.setDivisionLineSize(null);
                }
                Boolean isShowTimeBgBorder = this.backgroundInfo.isShowTimeBgBorder();
                if (isShowTimeBgBorder == null || !isShowTimeBgBorder.booleanValue()) {
                    this.backgroundInfo.setBorderColor(null);
                    this.backgroundInfo.setBorderRadius(null);
                    this.backgroundInfo.setBorderSize(null);
                }
                if (this.backgroundInfo.getSize() != null && this.backgroundInfo.getSize().floatValue() <= 0.0f) {
                    this.backgroundInfo.setSize(null);
                }
            }
            Integer num = this.suffixGravity;
            if (num != null) {
                if (num.intValue() < 0 || this.suffixGravity.intValue() > 2) {
                    this.suffixGravity = null;
                }
            }
        }

        public DynamicConfig build() {
            checkData();
            return new DynamicConfig(this);
        }
    }
}
