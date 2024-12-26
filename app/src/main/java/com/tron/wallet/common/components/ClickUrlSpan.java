package com.tron.wallet.common.components;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import com.tron.wallet.business.web.CommonWebActivityV3;
public class ClickUrlSpan extends URLSpan {
    private final int Color;
    private Context context;
    private String url;

    public ClickUrlSpan(Context context, String str) {
        super(str);
        this.Color = -12813069;
        this.url = str;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        CommonWebActivityV3.start(this.context, this.url, "", -2, false);
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(-12813069);
        textPaint.setUnderlineText(false);
    }
}
