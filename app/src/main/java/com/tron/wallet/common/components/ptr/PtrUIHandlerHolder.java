package com.tron.wallet.common.components.ptr;

import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
class PtrUIHandlerHolder implements PtrUIHandler {
    private PtrUIHandler mHandler;
    private PtrUIHandlerHolder mNext;

    private boolean contains(PtrUIHandler ptrUIHandler) {
        PtrUIHandler ptrUIHandler2 = this.mHandler;
        return ptrUIHandler2 != null && ptrUIHandler2 == ptrUIHandler;
    }

    private PtrUIHandler getHandler() {
        return this.mHandler;
    }

    public boolean hasHandler() {
        return this.mHandler != null;
    }

    private PtrUIHandlerHolder() {
    }

    public static void addHandler(PtrUIHandlerHolder ptrUIHandlerHolder, PtrUIHandler ptrUIHandler) {
        if (ptrUIHandler == null || ptrUIHandlerHolder == null) {
            return;
        }
        if (ptrUIHandlerHolder.mHandler == null) {
            ptrUIHandlerHolder.mHandler = ptrUIHandler;
            return;
        }
        while (!ptrUIHandlerHolder.contains(ptrUIHandler)) {
            PtrUIHandlerHolder ptrUIHandlerHolder2 = ptrUIHandlerHolder.mNext;
            if (ptrUIHandlerHolder2 == null) {
                PtrUIHandlerHolder ptrUIHandlerHolder3 = new PtrUIHandlerHolder();
                ptrUIHandlerHolder3.mHandler = ptrUIHandler;
                ptrUIHandlerHolder.mNext = ptrUIHandlerHolder3;
                return;
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder2;
        }
    }

    public static PtrUIHandlerHolder create() {
        return new PtrUIHandlerHolder();
    }

    public static PtrUIHandlerHolder removeHandler(PtrUIHandlerHolder ptrUIHandlerHolder, PtrUIHandler ptrUIHandler) {
        if (ptrUIHandlerHolder == null || ptrUIHandler == null || ptrUIHandlerHolder.mHandler == null) {
            return ptrUIHandlerHolder;
        }
        PtrUIHandlerHolder ptrUIHandlerHolder2 = ptrUIHandlerHolder;
        PtrUIHandlerHolder ptrUIHandlerHolder3 = null;
        do {
            if (!ptrUIHandlerHolder.contains(ptrUIHandler)) {
                ptrUIHandlerHolder3 = ptrUIHandlerHolder;
                ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
                continue;
            } else if (ptrUIHandlerHolder3 == null) {
                ptrUIHandlerHolder2 = ptrUIHandlerHolder.mNext;
                ptrUIHandlerHolder.mNext = null;
                ptrUIHandlerHolder = ptrUIHandlerHolder2;
                continue;
            } else {
                ptrUIHandlerHolder3.mNext = ptrUIHandlerHolder.mNext;
                ptrUIHandlerHolder.mNext = null;
                ptrUIHandlerHolder = ptrUIHandlerHolder3.mNext;
                continue;
            }
        } while (ptrUIHandlerHolder != null);
        return ptrUIHandlerHolder2 == null ? new PtrUIHandlerHolder() : ptrUIHandlerHolder2;
    }

    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIReset(ptrFrameLayout);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
        } while (ptrUIHandlerHolder != null);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        if (hasHandler()) {
            PtrUIHandlerHolder ptrUIHandlerHolder = this;
            do {
                PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
                if (handler != null) {
                    handler.onUIRefreshPrepare(ptrFrameLayout);
                }
                ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
            } while (ptrUIHandlerHolder != null);
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIRefreshBegin(ptrFrameLayout);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
        } while (ptrUIHandlerHolder != null);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout, boolean z) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIRefreshComplete(ptrFrameLayout, z);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
        } while (ptrUIHandlerHolder != null);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        PtrUIHandlerHolder ptrUIHandlerHolder = this;
        do {
            PtrUIHandler handler = ptrUIHandlerHolder.getHandler();
            if (handler != null) {
                handler.onUIPositionChange(ptrFrameLayout, z, b, ptrIndicator);
            }
            ptrUIHandlerHolder = ptrUIHandlerHolder.mNext;
        } while (ptrUIHandlerHolder != null);
    }
}
