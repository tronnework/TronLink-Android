package com.tron.wallet.common.components.ptr;

import android.view.View;
public abstract class PtrDefaultHandler implements PtrHandler {
    public static boolean canChildScrollUp(View view) {
        return view.canScrollVertically(-1);
    }

    public static boolean checkContentCanBePulledDown(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return !canChildScrollUp(view);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return checkContentCanBePulledDown(ptrFrameLayout, view, view2);
    }
}
