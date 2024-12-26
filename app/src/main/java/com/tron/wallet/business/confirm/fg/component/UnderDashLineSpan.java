package com.tron.wallet.business.confirm.fg.component;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.URLSpan;
import android.view.View;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.common.utils.UIUtils;
public class UnderDashLineSpan extends URLSpan {
    public UnderDashLineSpan(String str) {
        super(str);
    }

    public UnderDashLineSpan(Parcel parcel) {
        super(parcel);
    }

    @Override
    public int getSpanTypeId() {
        return super.getSpanTypeId();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    @Override
    public String getURL() {
        return super.getURL();
    }

    @Override
    public void onClick(View view) {
        UIUtils.toEnergyPenaltyDetails(AppContextUtil.getContext());
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 5.0f));
        textPaint.setColor(Color.parseColor("#232C41"));
        textPaint.setUnderlineText(false);
    }

    @Override
    public CharacterStyle getUnderlying() {
        return super.getUnderlying();
    }
}
