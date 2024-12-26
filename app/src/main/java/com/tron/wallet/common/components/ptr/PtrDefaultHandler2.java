package com.tron.wallet.common.components.ptr;

import android.view.View;
public abstract class PtrDefaultHandler2 extends PtrDefaultHandler implements PtrHandler2 {
    public static boolean canChildScrollDown(View view) {
        return view.canScrollVertically(1);
    }

    public static boolean checkContentCanBePulledUp(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return !canChildScrollDown(view);
    }

    @Override
    public boolean checkCanDoLoadMore(PtrFrameLayout ptrFrameLayout, View view, View view2) {
        return checkContentCanBePulledUp(ptrFrameLayout, view, view2);
    }
}
