package com.tron.wallet.common.components.pickerview.pickerview.view;

import android.view.View;
import com.tron.wallet.common.components.pickerview.pickerview.adapter.NumericWheelAdapter;
import com.tron.wallet.common.components.pickerview.pickerview.listener.ISelectTimeCallback;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.listener.OnItemSelectedListener;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tronlinkpro.wallet.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
public class WheelTime {
    private static final int DEFAULT_END_DAY = 31;
    private static final int DEFAULT_END_MONTH = 12;
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_DAY = 1;
    private static final int DEFAULT_START_MONTH = 1;
    private static final int DEFAULT_START_YEAR = 1900;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int currentYear;
    private int gravity;
    private ISelectTimeCallback mSelectChangeCallback;
    private int textSize;
    private boolean[] type;
    private View view;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_minutes;
    private WheelView wv_month;
    private WheelView wv_seconds;
    private WheelView wv_year;
    private int startYear = DEFAULT_START_YEAR;
    private int endYear = DEFAULT_END_YEAR;
    private int startMonth = 1;
    private int endMonth = 12;
    private int startDay = 1;
    private int endDay = 31;
    private boolean isLunarCalendar = false;

    public int getEndYear() {
        return this.endYear;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public View getView() {
        return this.view;
    }

    public boolean isLunarMode() {
        return this.isLunarCalendar;
    }

    public void setEndYear(int i) {
        this.endYear = i;
    }

    public void setLunarMode(boolean z) {
        this.isLunarCalendar = z;
    }

    public void setSelectChangeCallback(ISelectTimeCallback iSelectTimeCallback) {
        this.mSelectChangeCallback = iSelectTimeCallback;
    }

    public void setStartYear(int i) {
        this.startYear = i;
    }

    public WheelTime(View view, boolean[] zArr, int i, int i2) {
        this.view = view;
        this.type = zArr;
        this.gravity = i;
        this.textSize = i2;
    }

    public void setPicker(int i, int i2, int i3) {
        setPicker(i, i2, i3, 0, 0, 0);
    }

    public void setPicker(int i, int i2, int i3, int i4, int i5, int i6) {
        setSolar(i, i2, i3, i4, i5, i6);
    }

    private void setSolar(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        String[] strArr = {"1", "3", AnalyticsHelper.SelectSendAddress.CLICK_ADDRESS_BOOK, AnalyticsHelper.SelectSendAddress.CLICK_NEXT, "8", "10", "12"};
        String[] strArr2 = {AnalyticsHelper.SelectSendAddress.CLICK_RECENT, AnalyticsHelper.SelectSendAddress.CLICK_MY_ACCOUNT, "9", "11"};
        final List asList = Arrays.asList(strArr);
        final List asList2 = Arrays.asList(strArr2);
        this.currentYear = i;
        WheelView wheelView = (WheelView) this.view.findViewById(R.id.year);
        this.wv_year = wheelView;
        wheelView.setAdapter(new NumericWheelAdapter(this.startYear, this.endYear));
        this.wv_year.setCurrentItem(i - this.startYear);
        this.wv_year.setGravity(this.gravity);
        WheelView wheelView2 = (WheelView) this.view.findViewById(R.id.month);
        this.wv_month = wheelView2;
        int i9 = this.startYear;
        int i10 = this.endYear;
        if (i9 == i10) {
            wheelView2.setAdapter(new NumericWheelAdapter(this.startMonth, this.endMonth));
            this.wv_month.setCurrentItem((i2 + 1) - this.startMonth);
        } else if (i == i9) {
            wheelView2.setAdapter(new NumericWheelAdapter(this.startMonth, 12));
            this.wv_month.setCurrentItem((i2 + 1) - this.startMonth);
        } else if (i == i10) {
            wheelView2.setAdapter(new NumericWheelAdapter(1, this.endMonth));
            this.wv_month.setCurrentItem(i2);
        } else {
            wheelView2.setAdapter(new NumericWheelAdapter(1, 12));
            this.wv_month.setCurrentItem(i2);
        }
        this.wv_month.setGravity(this.gravity);
        this.wv_day = (WheelView) this.view.findViewById(R.id.day);
        boolean z = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
        int i11 = this.startYear;
        int i12 = this.endYear;
        if (i11 == i12 && this.startMonth == this.endMonth) {
            int i13 = i2 + 1;
            if (asList.contains(String.valueOf(i13))) {
                if (this.endDay > 31) {
                    this.endDay = 31;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            } else if (asList2.contains(String.valueOf(i13))) {
                if (this.endDay > 30) {
                    this.endDay = 30;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            } else if (z) {
                if (this.endDay > 29) {
                    this.endDay = 29;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            } else {
                if (this.endDay > 28) {
                    this.endDay = 28;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, this.endDay));
            }
            this.wv_day.setCurrentItem(i3 - this.startDay);
        } else if (i == i11 && (i8 = i2 + 1) == this.startMonth) {
            if (asList.contains(String.valueOf(i8))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, 31));
            } else if (asList2.contains(String.valueOf(i8))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, 30));
            } else {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, z ? 29 : 28));
            }
            this.wv_day.setCurrentItem(i3 - this.startDay);
        } else if (i == i12 && (i7 = i2 + 1) == this.endMonth) {
            if (asList.contains(String.valueOf(i7))) {
                if (this.endDay > 31) {
                    this.endDay = 31;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            } else if (asList2.contains(String.valueOf(i7))) {
                if (this.endDay > 30) {
                    this.endDay = 30;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            } else if (z) {
                if (this.endDay > 29) {
                    this.endDay = 29;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            } else {
                if (this.endDay > 28) {
                    this.endDay = 28;
                }
                this.wv_day.setAdapter(new NumericWheelAdapter(1, this.endDay));
            }
            this.wv_day.setCurrentItem(i3 - 1);
        } else {
            int i14 = i2 + 1;
            if (asList.contains(String.valueOf(i14))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (asList2.contains(String.valueOf(i14))) {
                this.wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                this.wv_day.setAdapter(new NumericWheelAdapter(this.startDay, z ? 29 : 28));
            }
            this.wv_day.setCurrentItem(i3 - 1);
        }
        this.wv_day.setGravity(this.gravity);
        WheelView wheelView3 = (WheelView) this.view.findViewById(R.id.hour);
        this.wv_hours = wheelView3;
        wheelView3.setAdapter(new NumericWheelAdapter(0, 23));
        this.wv_hours.setCurrentItem(i4);
        this.wv_hours.setGravity(this.gravity);
        WheelView wheelView4 = (WheelView) this.view.findViewById(R.id.min);
        this.wv_minutes = wheelView4;
        wheelView4.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_minutes.setCurrentItem(i5);
        this.wv_minutes.setGravity(this.gravity);
        WheelView wheelView5 = (WheelView) this.view.findViewById(R.id.second);
        this.wv_seconds = wheelView5;
        wheelView5.setAdapter(new NumericWheelAdapter(0, 59));
        this.wv_seconds.setCurrentItem(i6);
        this.wv_seconds.setGravity(this.gravity);
        this.wv_year.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i15) {
                int i16 = i15 + startYear;
                currentYear = i16;
                int currentItem = wv_month.getCurrentItem();
                if (startYear == endYear) {
                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, endMonth));
                    if (currentItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentItem);
                    }
                    int i17 = currentItem + startMonth;
                    if (startMonth == endMonth) {
                        WheelTime wheelTime = WheelTime.this;
                        wheelTime.setReDay(i16, i17, wheelTime.startDay, endDay, asList, asList2);
                    } else if (i17 == startMonth) {
                        WheelTime wheelTime2 = WheelTime.this;
                        wheelTime2.setReDay(i16, i17, wheelTime2.startDay, 31, asList, asList2);
                    } else if (i17 == endMonth) {
                        WheelTime wheelTime3 = WheelTime.this;
                        wheelTime3.setReDay(i16, i17, 1, wheelTime3.endDay, asList, asList2);
                    } else {
                        setReDay(i16, i17, 1, 31, asList, asList2);
                    }
                } else if (i16 == startYear) {
                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, 12));
                    if (currentItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentItem);
                    }
                    int i18 = currentItem + startMonth;
                    if (i18 == startMonth) {
                        WheelTime wheelTime4 = WheelTime.this;
                        wheelTime4.setReDay(i16, i18, wheelTime4.startDay, 31, asList, asList2);
                    } else {
                        setReDay(i16, i18, 1, 31, asList, asList2);
                    }
                } else if (i16 == endYear) {
                    wv_month.setAdapter(new NumericWheelAdapter(1, endMonth));
                    if (currentItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentItem);
                    }
                    int i19 = 1 + currentItem;
                    if (i19 == endMonth) {
                        WheelTime wheelTime5 = WheelTime.this;
                        wheelTime5.setReDay(i16, i19, 1, wheelTime5.endDay, asList, asList2);
                    } else {
                        setReDay(i16, i19, 1, 31, asList, asList2);
                    }
                } else {
                    wv_month.setAdapter(new NumericWheelAdapter(1, 12));
                    WheelTime wheelTime6 = WheelTime.this;
                    wheelTime6.setReDay(i16, 1 + wheelTime6.wv_month.getCurrentItem(), 1, 31, asList, asList2);
                }
                if (mSelectChangeCallback != null) {
                    mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });
        this.wv_month.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i15) {
                int i16 = i15 + 1;
                if (startYear == endYear) {
                    int i17 = (i16 + startMonth) - 1;
                    if (startMonth == endMonth) {
                        WheelTime wheelTime = WheelTime.this;
                        wheelTime.setReDay(wheelTime.currentYear, i17, startDay, endDay, asList, asList2);
                    } else if (startMonth == i17) {
                        WheelTime wheelTime2 = WheelTime.this;
                        wheelTime2.setReDay(wheelTime2.currentYear, i17, startDay, 31, asList, asList2);
                    } else if (endMonth == i17) {
                        WheelTime wheelTime3 = WheelTime.this;
                        wheelTime3.setReDay(wheelTime3.currentYear, i17, 1, endDay, asList, asList2);
                    } else {
                        WheelTime wheelTime4 = WheelTime.this;
                        wheelTime4.setReDay(wheelTime4.currentYear, i17, 1, 31, asList, asList2);
                    }
                } else if (currentYear == startYear) {
                    int i18 = (i16 + startMonth) - 1;
                    if (i18 == startMonth) {
                        WheelTime wheelTime5 = WheelTime.this;
                        wheelTime5.setReDay(wheelTime5.currentYear, i18, startDay, 31, asList, asList2);
                    } else {
                        WheelTime wheelTime6 = WheelTime.this;
                        wheelTime6.setReDay(wheelTime6.currentYear, i18, 1, 31, asList, asList2);
                    }
                } else if (currentYear == endYear) {
                    if (i16 == endMonth) {
                        WheelTime wheelTime7 = WheelTime.this;
                        wheelTime7.setReDay(wheelTime7.currentYear, wv_month.getCurrentItem() + 1, 1, endDay, asList, asList2);
                    } else {
                        WheelTime wheelTime8 = WheelTime.this;
                        wheelTime8.setReDay(wheelTime8.currentYear, wv_month.getCurrentItem() + 1, 1, 31, asList, asList2);
                    }
                } else {
                    WheelTime wheelTime9 = WheelTime.this;
                    wheelTime9.setReDay(wheelTime9.currentYear, i16, 1, 31, asList, asList2);
                }
                if (mSelectChangeCallback != null) {
                    mSelectChangeCallback.onTimeSelectChanged();
                }
            }
        });
        setChangedListener(this.wv_day);
        setChangedListener(this.wv_hours);
        setChangedListener(this.wv_minutes);
        setChangedListener(this.wv_seconds);
        boolean[] zArr = this.type;
        if (zArr.length != 6) {
            throw new IllegalArgumentException("type[] length is not 6");
        }
        this.wv_year.setVisibility(zArr[0] ? View.VISIBLE : View.GONE);
        this.wv_month.setVisibility(this.type[1] ? View.VISIBLE : View.GONE);
        this.wv_day.setVisibility(this.type[2] ? View.VISIBLE : View.GONE);
        this.wv_hours.setVisibility(this.type[3] ? View.VISIBLE : View.GONE);
        this.wv_minutes.setVisibility(this.type[4] ? View.VISIBLE : View.GONE);
        this.wv_seconds.setVisibility(this.type[5] ? View.VISIBLE : View.GONE);
        setContentTextSize();
    }

    private void setChangedListener(WheelView wheelView) {
        if (this.mSelectChangeCallback != null) {
            wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    mSelectChangeCallback.onTimeSelectChanged();
                }
            });
        }
    }

    public void setReDay(int i, int i2, int i3, int i4, List<String> list, List<String> list2) {
        int currentItem = this.wv_day.getCurrentItem();
        if (list.contains(String.valueOf(i2))) {
            if (i4 > 31) {
                i4 = 31;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i4));
        } else if (list2.contains(String.valueOf(i2))) {
            if (i4 > 30) {
                i4 = 30;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i4));
        } else if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
            if (i4 > 29) {
                i4 = 29;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i4));
        } else {
            if (i4 > 28) {
                i4 = 28;
            }
            this.wv_day.setAdapter(new NumericWheelAdapter(i3, i4));
        }
        if (currentItem > this.wv_day.getAdapter().getItemsCount() - 1) {
            this.wv_day.setCurrentItem(this.wv_day.getAdapter().getItemsCount() - 1);
        }
    }

    private void setContentTextSize() {
        this.wv_day.setTextSize(this.textSize);
        this.wv_month.setTextSize(this.textSize);
        this.wv_year.setTextSize(this.textSize);
        this.wv_hours.setTextSize(this.textSize);
        this.wv_minutes.setTextSize(this.textSize);
        this.wv_seconds.setTextSize(this.textSize);
    }

    public void setLabels(String str, String str2, String str3, String str4, String str5, String str6) {
        if (this.isLunarCalendar) {
            return;
        }
        if (str != null) {
            this.wv_year.setLabel(str);
        } else {
            this.wv_year.setLabel(this.view.getContext().getString(R.string.pickerview_year));
        }
        if (str2 != null) {
            this.wv_month.setLabel(str2);
        } else {
            this.wv_month.setLabel(this.view.getContext().getString(R.string.pickerview_month));
        }
        if (str3 != null) {
            this.wv_day.setLabel(str3);
        } else {
            this.wv_day.setLabel(this.view.getContext().getString(R.string.day));
        }
        if (str4 != null) {
            this.wv_hours.setLabel(str4);
        } else {
            this.wv_hours.setLabel(this.view.getContext().getString(R.string.hour));
        }
        if (str5 != null) {
            this.wv_minutes.setLabel(str5);
        } else {
            this.wv_minutes.setLabel(this.view.getContext().getString(R.string.minute));
        }
        if (str6 != null) {
            this.wv_seconds.setLabel(str6);
        } else {
            this.wv_seconds.setLabel(this.view.getContext().getString(R.string.pickerview_seconds));
        }
    }

    public void setTextXOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        this.wv_year.setTextXOffset(i);
        this.wv_month.setTextXOffset(i2);
        this.wv_day.setTextXOffset(i3);
        this.wv_hours.setTextXOffset(i4);
        this.wv_minutes.setTextXOffset(i5);
        this.wv_seconds.setTextXOffset(i6);
    }

    public void setCyclic(boolean z) {
        this.wv_year.setCyclic(z);
        this.wv_month.setCyclic(z);
        this.wv_day.setCyclic(z);
        this.wv_hours.setCyclic(z);
        this.wv_minutes.setCyclic(z);
        this.wv_seconds.setCyclic(z);
    }

    public String getTime() {
        StringBuilder sb = new StringBuilder();
        if (this.currentYear == this.startYear) {
            int currentItem = this.wv_month.getCurrentItem();
            int i = this.startMonth;
            if (currentItem + i == i) {
                sb.append(this.wv_year.getCurrentItem() + this.startYear);
                sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                sb.append(this.wv_month.getCurrentItem() + this.startMonth);
                sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                sb.append(this.wv_day.getCurrentItem() + this.startDay);
                sb.append(" ");
                sb.append(this.wv_hours.getCurrentItem());
                sb.append(":");
                sb.append(this.wv_minutes.getCurrentItem());
                sb.append(":");
                sb.append(this.wv_seconds.getCurrentItem());
            } else {
                sb.append(this.wv_year.getCurrentItem() + this.startYear);
                sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                sb.append(this.wv_month.getCurrentItem() + this.startMonth);
                sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                sb.append(this.wv_day.getCurrentItem() + 1);
                sb.append(" ");
                sb.append(this.wv_hours.getCurrentItem());
                sb.append(":");
                sb.append(this.wv_minutes.getCurrentItem());
                sb.append(":");
                sb.append(this.wv_seconds.getCurrentItem());
            }
        } else {
            sb.append(this.wv_year.getCurrentItem() + this.startYear);
            sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
            sb.append(this.wv_month.getCurrentItem() + 1);
            sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
            sb.append(this.wv_day.getCurrentItem() + 1);
            sb.append(" ");
            sb.append(this.wv_hours.getCurrentItem());
            sb.append(":");
            sb.append(this.wv_minutes.getCurrentItem());
            sb.append(":");
            sb.append(this.wv_seconds.getCurrentItem());
        }
        return sb.toString();
    }

    public void setRangDate(Calendar calendar, Calendar calendar2) {
        if (calendar == null && calendar2 != null) {
            int i = calendar2.get(1);
            int i2 = calendar2.get(2) + 1;
            int i3 = calendar2.get(5);
            int i4 = this.startYear;
            if (i > i4) {
                this.endYear = i;
                this.endMonth = i2;
                this.endDay = i3;
            } else if (i == i4) {
                int i5 = this.startMonth;
                if (i2 > i5) {
                    this.endYear = i;
                    this.endMonth = i2;
                    this.endDay = i3;
                } else if (i2 != i5 || i3 <= this.startDay) {
                } else {
                    this.endYear = i;
                    this.endMonth = i2;
                    this.endDay = i3;
                }
            }
        } else if (calendar == null || calendar2 != null) {
            if (calendar == null || calendar2 == null) {
                return;
            }
            this.startYear = calendar.get(1);
            this.endYear = calendar2.get(1);
            this.startMonth = calendar.get(2) + 1;
            this.endMonth = calendar2.get(2) + 1;
            this.startDay = calendar.get(5);
            this.endDay = calendar2.get(5);
        } else {
            int i6 = calendar.get(1);
            int i7 = calendar.get(2) + 1;
            int i8 = calendar.get(5);
            int i9 = this.endYear;
            if (i6 < i9) {
                this.startMonth = i7;
                this.startDay = i8;
                this.startYear = i6;
            } else if (i6 == i9) {
                int i10 = this.endMonth;
                if (i7 < i10) {
                    this.startMonth = i7;
                    this.startDay = i8;
                    this.startYear = i6;
                } else if (i7 != i10 || i8 >= this.endDay) {
                } else {
                    this.startMonth = i7;
                    this.startDay = i8;
                    this.startYear = i6;
                }
            }
        }
    }

    public void setLineSpacingMultiplier(float f) {
        this.wv_day.setLineSpacingMultiplier(f);
        this.wv_month.setLineSpacingMultiplier(f);
        this.wv_year.setLineSpacingMultiplier(f);
        this.wv_hours.setLineSpacingMultiplier(f);
        this.wv_minutes.setLineSpacingMultiplier(f);
        this.wv_seconds.setLineSpacingMultiplier(f);
    }

    public void setDividerColor(int i) {
        this.wv_day.setDividerColor(i);
        this.wv_month.setDividerColor(i);
        this.wv_year.setDividerColor(i);
        this.wv_hours.setDividerColor(i);
        this.wv_minutes.setDividerColor(i);
        this.wv_seconds.setDividerColor(i);
    }

    public void setDividerType(WheelView.DividerType dividerType) {
        this.wv_day.setDividerType(dividerType);
        this.wv_month.setDividerType(dividerType);
        this.wv_year.setDividerType(dividerType);
        this.wv_hours.setDividerType(dividerType);
        this.wv_minutes.setDividerType(dividerType);
        this.wv_seconds.setDividerType(dividerType);
    }

    public void setTextColorCenter(int i) {
        this.wv_day.setTextColorCenter(i);
        this.wv_month.setTextColorCenter(i);
        this.wv_year.setTextColorCenter(i);
        this.wv_hours.setTextColorCenter(i);
        this.wv_minutes.setTextColorCenter(i);
        this.wv_seconds.setTextColorCenter(i);
    }

    public void setTextColorOut(int i) {
        this.wv_day.setTextColorOut(i);
        this.wv_month.setTextColorOut(i);
        this.wv_year.setTextColorOut(i);
        this.wv_hours.setTextColorOut(i);
        this.wv_minutes.setTextColorOut(i);
        this.wv_seconds.setTextColorOut(i);
    }

    public void isCenterLabel(boolean z) {
        this.wv_day.isCenterLabel(z);
        this.wv_month.isCenterLabel(z);
        this.wv_year.isCenterLabel(z);
        this.wv_hours.isCenterLabel(z);
        this.wv_minutes.isCenterLabel(z);
        this.wv_seconds.isCenterLabel(z);
    }

    public void setItemsVisible(int i) {
        this.wv_day.setItemsVisibleCount(i);
        this.wv_month.setItemsVisibleCount(i);
        this.wv_year.setItemsVisibleCount(i);
        this.wv_hours.setItemsVisibleCount(i);
        this.wv_minutes.setItemsVisibleCount(i);
        this.wv_seconds.setItemsVisibleCount(i);
    }

    public void setAlphaGradient(boolean z) {
        this.wv_day.setAlphaGradient(z);
        this.wv_month.setAlphaGradient(z);
        this.wv_year.setAlphaGradient(z);
        this.wv_hours.setAlphaGradient(z);
        this.wv_minutes.setAlphaGradient(z);
        this.wv_seconds.setAlphaGradient(z);
    }
}
