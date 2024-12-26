package com.tron.wallet.common.components.browser;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.tronlinkpro.wallet.R;
public class BrowserTabBottomToolbar extends FrameLayout {
    private static final String TAG = "BrowserTabBottomToolbar";
    LinearLayout liHistoryManager;
    LinearLayout liTabManager;

    public BrowserTabBottomToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.liTabManager = (LinearLayout) findViewById(R.id.li_tab_manager);
        this.liHistoryManager = (LinearLayout) findViewById(R.id.li_history_manager);
    }

    public void showTabManagerLayout() {
        this.liTabManager.setVisibility(View.VISIBLE);
        this.liHistoryManager.setVisibility(View.GONE);
    }

    public void showBookMarkManagerLayout() {
        this.liTabManager.setVisibility(View.GONE);
        this.liHistoryManager.setVisibility(View.GONE);
    }

    public void showHistoryManagerLayout() {
        this.liTabManager.setVisibility(View.GONE);
        this.liHistoryManager.setVisibility(View.VISIBLE);
    }
}
