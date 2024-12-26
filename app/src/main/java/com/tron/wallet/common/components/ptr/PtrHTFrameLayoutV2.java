package com.tron.wallet.common.components.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import androidx.core.view.ViewConfigurationCompat;
import androidx.viewpager2.widget.ViewPager2;
public class PtrHTFrameLayoutV2 extends PtrFrameLayout {
    private boolean isDeal;
    private boolean mIsHorizontalMove;
    private PtrClassicDefaultHeader mPtrClassicHeader;
    private int mTouchSlop;
    private ViewPager2 mViewPager;
    private float startX;
    private float startY;

    public PtrClassicDefaultHeader getHeader() {
        return this.mPtrClassicHeader;
    }

    public PtrHTFrameLayoutV2(Context context) {
        super(context);
        initViews();
    }

    public PtrHTFrameLayoutV2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    public PtrHTFrameLayoutV2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    private void initViews() {
        PtrClassicDefaultHeader ptrClassicDefaultHeader = new PtrClassicDefaultHeader(getContext());
        this.mPtrClassicHeader = ptrClassicDefaultHeader;
        setHeaderView(ptrClassicDefaultHeader);
        addPtrUIHandler(this.mPtrClassicHeader);
    }

    public void setLastUpdateTimeKey(String str) {
        PtrClassicDefaultHeader ptrClassicDefaultHeader = this.mPtrClassicHeader;
        if (ptrClassicDefaultHeader != null) {
            ptrClassicDefaultHeader.setLastUpdateTimeKey(str);
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        PtrClassicDefaultHeader ptrClassicDefaultHeader = this.mPtrClassicHeader;
        if (ptrClassicDefaultHeader != null) {
            ptrClassicDefaultHeader.setLastUpdateTimeRelateObject(obj);
        }
    }

    public void setViewPager(ViewPager2 viewPager2) {
        this.mViewPager = viewPager2;
        if (viewPager2 == null) {
            throw new IllegalArgumentException("viewPager can not be null");
        }
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(getContext()));
    }

    @Override
    public boolean dispatchTouchEvent(android.view.MotionEvent r8) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }
}
