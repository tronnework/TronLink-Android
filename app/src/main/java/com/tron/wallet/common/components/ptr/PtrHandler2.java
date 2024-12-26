package com.tron.wallet.common.components.ptr;

import android.view.View;
public interface PtrHandler2 extends PtrHandler {
    boolean checkCanDoLoadMore(PtrFrameLayout ptrFrameLayout, View view, View view2);

    void onLoadMoreBegin(PtrFrameLayout ptrFrameLayout);
}
