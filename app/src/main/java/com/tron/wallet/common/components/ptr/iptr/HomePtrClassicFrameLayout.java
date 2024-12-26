package com.tron.wallet.common.components.ptr.iptr;

import android.content.Context;
import android.util.AttributeSet;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.iptr.HomePtrClassicHeader;
public class HomePtrClassicFrameLayout extends PtrFrameLayout {
    private HomePtrClassicHeader mPtrClassicHeader;

    public HomePtrClassicHeader getHeader() {
        return this.mPtrClassicHeader;
    }

    public HomePtrClassicFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public HomePtrClassicFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    public HomePtrClassicFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    private void initViews() {
        HomePtrClassicHeader homePtrClassicHeader = new HomePtrClassicHeader(getContext());
        this.mPtrClassicHeader = homePtrClassicHeader;
        setHeaderView(homePtrClassicHeader);
        addPtrUIHandler(this.mPtrClassicHeader);
    }

    public void setUiChangeListener(HomePtrClassicHeader.onUIChangeListener onuichangelistener) {
        HomePtrClassicHeader homePtrClassicHeader = this.mPtrClassicHeader;
        if (homePtrClassicHeader != null) {
            homePtrClassicHeader.setUiChangeListener(onuichangelistener);
        }
    }

    public void setStatusListener(HomePtrClassicHeader.onStatusListener onstatuslistener) {
        HomePtrClassicHeader homePtrClassicHeader = this.mPtrClassicHeader;
        if (homePtrClassicHeader != null) {
            homePtrClassicHeader.setStatusListener(onstatuslistener);
        }
    }
}
