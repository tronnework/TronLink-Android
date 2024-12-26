package com.tron.wallet.common.components.ptr;

import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
public interface PtrUIHandler {
    void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator);

    void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout);

    void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout, boolean z);

    void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout);

    void onUIReset(PtrFrameLayout ptrFrameLayout);
}
