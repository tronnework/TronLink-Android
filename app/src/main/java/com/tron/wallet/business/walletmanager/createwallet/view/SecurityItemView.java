package com.tron.wallet.business.walletmanager.createwallet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.tronlinkpro.wallet.R;
public class SecurityItemView extends RelativeLayout {
    public SecurityItemView(Context context) {
        super(context);
        addTimeline(context);
    }

    public SecurityItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        addTimeline(context);
    }

    public SecurityItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        addTimeline(context);
    }

    private void addTimeline(Context context) {
        addView(View.inflate(context, R.layout.security_item_view, null));
    }
}
