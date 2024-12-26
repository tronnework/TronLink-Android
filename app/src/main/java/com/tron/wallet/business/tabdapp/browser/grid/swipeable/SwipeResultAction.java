package com.tron.wallet.business.tabdapp.browser.grid.swipeable;
public abstract class SwipeResultAction {
    private final int mResultAction;

    public int getResultActionType() {
        return this.mResultAction;
    }

    protected void onCleanUp() {
    }

    protected void onPerformAction() {
    }

    protected void onSlideAnimationEnd() {
    }

    public SwipeResultAction(int i) {
        this.mResultAction = i;
    }

    public final void performAction() {
        onPerformAction();
    }

    public final void slideAnimationEnd() {
        onSlideAnimationEnd();
        onCleanUp();
    }
}
