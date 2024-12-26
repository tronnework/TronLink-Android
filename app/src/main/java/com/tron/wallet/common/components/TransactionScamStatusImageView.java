package com.tron.wallet.common.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
public class TransactionScamStatusImageView extends AppCompatImageView {
    public TransactionScamStatusImageView(Context context) {
        this(context, null);
    }

    public TransactionScamStatusImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TransactionScamStatusImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                new MultiLineTextPopupView.Builder().setAnchor(view).setPreferredShowUp(false).setRequiredWidth((int) (XPopupUtils.getScreenWidth(getContext()) * 0.5f)).addKeyValue(getContext().getString(R.string.transaction_scam_status_tips), "").build(getContext()).show();
            }
        });
    }
}
