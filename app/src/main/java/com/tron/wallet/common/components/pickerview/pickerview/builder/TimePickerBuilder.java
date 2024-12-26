package com.tron.wallet.common.components.pickerview.pickerview.builder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.tron.wallet.common.components.pickerview.pickerview.configure.PickerOptions;
import com.tron.wallet.common.components.pickerview.pickerview.listener.CustomListener;
import com.tron.wallet.common.components.pickerview.pickerview.listener.OnTimeSelectChangeListener;
import com.tron.wallet.common.components.pickerview.pickerview.listener.OnTimeSelectListener;
import com.tron.wallet.common.components.pickerview.pickerview.view.TimePickerView;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
import java.util.Calendar;
public class TimePickerBuilder {
    private PickerOptions mPickerOptions;

    public TimePickerBuilder(Context context, OnTimeSelectListener onTimeSelectListener) {
        PickerOptions pickerOptions = new PickerOptions(2);
        this.mPickerOptions = pickerOptions;
        pickerOptions.context = context;
        this.mPickerOptions.timeSelectListener = onTimeSelectListener;
    }

    public TimePickerBuilder setGravity(int i) {
        this.mPickerOptions.textGravity = i;
        return this;
    }

    public TimePickerBuilder addOnCancelClickListener(View.OnClickListener onClickListener) {
        this.mPickerOptions.cancelListener = onClickListener;
        return this;
    }

    public TimePickerBuilder setType(boolean[] zArr) {
        this.mPickerOptions.type = zArr;
        return this;
    }

    public TimePickerBuilder setSubmitText(String str) {
        this.mPickerOptions.textContentConfirm = str;
        return this;
    }

    public TimePickerBuilder isDialog(boolean z) {
        this.mPickerOptions.isDialog = z;
        return this;
    }

    public TimePickerBuilder setCancelText(String str) {
        this.mPickerOptions.textContentCancel = str;
        return this;
    }

    public TimePickerBuilder setTitleText(String str) {
        this.mPickerOptions.textContentTitle = str;
        return this;
    }

    public TimePickerBuilder setSubmitColor(int i) {
        this.mPickerOptions.textColorConfirm = i;
        return this;
    }

    public TimePickerBuilder setCancelColor(int i) {
        this.mPickerOptions.textColorCancel = i;
        return this;
    }

    public TimePickerBuilder setDecorView(ViewGroup viewGroup) {
        this.mPickerOptions.decorView = viewGroup;
        return this;
    }

    public TimePickerBuilder setBgColor(int i) {
        this.mPickerOptions.bgColorWheel = i;
        return this;
    }

    public TimePickerBuilder setTitleBgColor(int i) {
        this.mPickerOptions.bgColorTitle = i;
        return this;
    }

    public TimePickerBuilder setTitleColor(int i) {
        this.mPickerOptions.textColorTitle = i;
        return this;
    }

    public TimePickerBuilder setSubCalSize(int i) {
        this.mPickerOptions.textSizeSubmitCancel = i;
        return this;
    }

    public TimePickerBuilder setTitleSize(int i) {
        this.mPickerOptions.textSizeTitle = i;
        return this;
    }

    public TimePickerBuilder setContentTextSize(int i) {
        this.mPickerOptions.textSizeContent = i;
        return this;
    }

    public TimePickerBuilder setItemVisibleCount(int i) {
        this.mPickerOptions.itemsVisibleCount = i;
        return this;
    }

    public TimePickerBuilder isAlphaGradient(boolean z) {
        this.mPickerOptions.isAlphaGradient = z;
        return this;
    }

    public TimePickerBuilder setDate(Calendar calendar) {
        this.mPickerOptions.date = calendar;
        return this;
    }

    public TimePickerBuilder setLayoutRes(int i, CustomListener customListener) {
        this.mPickerOptions.layoutRes = i;
        this.mPickerOptions.customListener = customListener;
        return this;
    }

    public TimePickerBuilder setRangDate(Calendar calendar, Calendar calendar2) {
        this.mPickerOptions.startDate = calendar;
        this.mPickerOptions.endDate = calendar2;
        return this;
    }

    public TimePickerBuilder setLineSpacingMultiplier(float f) {
        this.mPickerOptions.lineSpacingMultiplier = f;
        return this;
    }

    public TimePickerBuilder setDividerColor(int i) {
        this.mPickerOptions.dividerColor = i;
        return this;
    }

    public TimePickerBuilder setDividerType(WheelView.DividerType dividerType) {
        this.mPickerOptions.dividerType = dividerType;
        return this;
    }

    @Deprecated
    public TimePickerBuilder setBackgroundId(int i) {
        this.mPickerOptions.outSideColor = i;
        return this;
    }

    public TimePickerBuilder setOutSideColor(int i) {
        this.mPickerOptions.outSideColor = i;
        return this;
    }

    public TimePickerBuilder setTextColorCenter(int i) {
        this.mPickerOptions.textColorCenter = i;
        return this;
    }

    public TimePickerBuilder setTextColorOut(int i) {
        this.mPickerOptions.textColorOut = i;
        return this;
    }

    public TimePickerBuilder isCyclic(boolean z) {
        this.mPickerOptions.cyclic = z;
        return this;
    }

    public TimePickerBuilder setOutSideCancelable(boolean z) {
        this.mPickerOptions.cancelable = z;
        return this;
    }

    public TimePickerBuilder setLunarCalendar(boolean z) {
        this.mPickerOptions.isLunarCalendar = z;
        return this;
    }

    public TimePickerBuilder setLabel(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mPickerOptions.label_year = str;
        this.mPickerOptions.label_month = str2;
        this.mPickerOptions.label_day = str3;
        this.mPickerOptions.label_hours = str4;
        this.mPickerOptions.label_minutes = str5;
        this.mPickerOptions.label_seconds = str6;
        return this;
    }

    public TimePickerBuilder setTextXOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mPickerOptions.x_offset_year = i;
        this.mPickerOptions.x_offset_month = i2;
        this.mPickerOptions.x_offset_day = i3;
        this.mPickerOptions.x_offset_hours = i4;
        this.mPickerOptions.x_offset_minutes = i5;
        this.mPickerOptions.x_offset_seconds = i6;
        return this;
    }

    public TimePickerBuilder isCenterLabel(boolean z) {
        this.mPickerOptions.isCenterLabel = z;
        return this;
    }

    public TimePickerBuilder setTimeSelectChangeListener(OnTimeSelectChangeListener onTimeSelectChangeListener) {
        this.mPickerOptions.timeSelectChangeListener = onTimeSelectChangeListener;
        return this;
    }

    public TimePickerView build() {
        return new TimePickerView(this.mPickerOptions);
    }
}
