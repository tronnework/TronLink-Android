package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import com.tron.wallet.R;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.UIUtils;
public class ErrorView extends AppCompatTextView {
    private CharSequence originalText;

    public enum Level {
        INFO,
        WARNING,
        ERROR,
        NONE,
        SCAM
    }

    public ErrorView(Context context) {
        this(context, null);
    }

    public ErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ErrorView);
        int i2 = obtainStyledAttributes.getInt(0, Level.NONE.ordinal());
        obtainStyledAttributes.recycle();
        this.originalText = getText();
        Level convertInteger = convertInteger(i2);
        if (convertInteger.equals(Level.NONE)) {
            return;
        }
        updateWarning(convertInteger);
    }

    private Level convertInteger(int i) {
        if (i < 0 || i > Level.NONE.ordinal()) {
            return Level.NONE;
        }
        return Level.values()[i];
    }

    public void setText(CharSequence charSequence, boolean z) {
        super.setText(charSequence);
        if (z) {
            this.originalText = charSequence;
        }
    }

    public void updateWarning(Level level) {
        int i;
        int i2;
        Drawable drawable;
        boolean z = true;
        if (level == Level.WARNING) {
            i = com.tronlinkpro.wallet.R.mipmap.ic_error_warning;
            i2 = com.tronlinkpro.wallet.R.color.orange_FFA9;
        } else if (level == Level.INFO) {
            i2 = com.tronlinkpro.wallet.R.color.blue_3c;
            i = com.tronlinkpro.wallet.R.mipmap.stake_error_icon;
            z = false;
        } else {
            i = level == Level.SCAM ? com.tronlinkpro.wallet.R.mipmap.ic_asset_scam : com.tronlinkpro.wallet.R.mipmap.stake_error_icon;
            i2 = com.tronlinkpro.wallet.R.color.red_ec;
        }
        setTextColor(getResources().getColor(i2));
        if (z && (drawable = AppCompatResources.getDrawable(getContext(), i)) != null) {
            int dip2px = UIUtils.dip2px(14.0f);
            setText((CharSequence) SpannableUtils.getCompatImageSpan(drawable, 0, 2, dip2px, dip2px, this.originalText), false);
        }
    }
}
