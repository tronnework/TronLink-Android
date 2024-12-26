package com.tron.wallet.common.components.qr;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.journeyapps.barcodescanner.BarcodeView;
import com.tron.wallet.common.utils.UIUtils;
public class CustBarcodeView extends BarcodeView {
    public CustBarcodeView(Context context) {
        super(context);
    }

    public CustBarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustBarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public Rect calculateFramingRect(Rect rect, Rect rect2) {
        Rect calculateFramingRect = super.calculateFramingRect(rect, rect2);
        calculateFramingRect.top = UIUtils.dip2px(108.0f);
        calculateFramingRect.bottom = (calculateFramingRect.top + calculateFramingRect.right) - calculateFramingRect.left;
        return calculateFramingRect;
    }
}
