package com.tron.wallet.business.security.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.R;
public class SecurityHorizontalView extends RelativeLayout {
    private String buttonText;

    public SecurityHorizontalView(Context context) {
        this(context, null);
    }

    public SecurityHorizontalView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SecurityHorizontalView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SecurityHorizontalView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SecurityHorizontalView, i, i2);
        this.buttonText = obtainStyledAttributes.getString(0);
        obtainStyledAttributes.recycle();
        View inflate = View.inflate(context, com.tronlinkpro.wallet.R.layout.security_button, null);
        ((TextView) inflate.findViewById(com.tronlinkpro.wallet.R.id.tv_text)).setText(this.buttonText);
        addView(inflate, new RelativeLayout.LayoutParams(-1, -2));
    }
}
