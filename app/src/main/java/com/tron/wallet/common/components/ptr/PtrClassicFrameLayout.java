package com.tron.wallet.common.components.ptr;

import android.content.Context;
import android.util.AttributeSet;
public class PtrClassicFrameLayout extends PtrFrameLayout {
    private PtrClassicDefaultFooter mPtrClassicFooter;
    private PtrClassicDefaultHeader mPtrClassicHeader;

    public PtrClassicDefaultHeader getHeader() {
        return this.mPtrClassicHeader;
    }

    public PtrClassicFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    private void initViews() {
        PtrClassicDefaultHeader ptrClassicDefaultHeader = new PtrClassicDefaultHeader(getContext());
        this.mPtrClassicHeader = ptrClassicDefaultHeader;
        setHeaderView(ptrClassicDefaultHeader);
        addPtrUIHandler(this.mPtrClassicHeader);
        PtrClassicDefaultFooter ptrClassicDefaultFooter = new PtrClassicDefaultFooter(getContext());
        this.mPtrClassicFooter = ptrClassicDefaultFooter;
        setFooterView(ptrClassicDefaultFooter);
        addPtrUIHandler(this.mPtrClassicFooter);
    }

    public void setLastUpdateTimeKey(String str) {
        setLastUpdateTimeHeaderKey(str);
        setLastUpdateTimeFooterKey(str);
    }

    public void setLastUpdateTimeHeaderKey(String str) {
        PtrClassicDefaultHeader ptrClassicDefaultHeader = this.mPtrClassicHeader;
        if (ptrClassicDefaultHeader != null) {
            ptrClassicDefaultHeader.setLastUpdateTimeKey(str);
        }
    }

    public void setLastUpdateTimeFooterKey(String str) {
        PtrClassicDefaultFooter ptrClassicDefaultFooter = this.mPtrClassicFooter;
        if (ptrClassicDefaultFooter != null) {
            ptrClassicDefaultFooter.setLastUpdateTimeKey(str);
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        setLastUpdateTimeHeaderRelateObject(obj);
        setLastUpdateTimeFooterRelateObject(obj);
    }

    public void setLastUpdateTimeHeaderRelateObject(Object obj) {
        PtrClassicDefaultHeader ptrClassicDefaultHeader = this.mPtrClassicHeader;
        if (ptrClassicDefaultHeader != null) {
            ptrClassicDefaultHeader.setLastUpdateTimeRelateObject(obj);
        }
    }

    public void setLastUpdateTimeFooterRelateObject(Object obj) {
        PtrClassicDefaultFooter ptrClassicDefaultFooter = this.mPtrClassicFooter;
        if (ptrClassicDefaultFooter != null) {
            ptrClassicDefaultFooter.setLastUpdateTimeRelateObject(obj);
        }
    }
}
