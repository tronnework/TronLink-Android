package com.tron.wallet.business.tabdapp.browser.tabs;

import android.graphics.Bitmap;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
public class BrowserTab extends DappBean {
    private boolean active;
    private Bitmap cachedSnapshot;
    private boolean cachedSnapshotValid;
    private int index;
    private boolean replaceTitle;
    private int viewType = 0;
    private boolean canBackToMain = true;

    public int getViewType() {
        return this.viewType;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isCanBackToMain() {
        return this.canBackToMain;
    }

    public boolean isReplaceTitle() {
        return this.replaceTitle;
    }

    public void setCachedSnapshotValid(boolean z) {
        this.cachedSnapshotValid = z;
    }

    public void setCanBackToMain(boolean z) {
        this.canBackToMain = z;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setReplaceTitle(boolean z) {
        this.replaceTitle = z;
    }

    public void setViewType(int i) {
        this.viewType = i;
    }

    public void setActive(boolean z) {
        if (this.active && !z) {
            try {
                takeSnapshot();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        this.active = z;
    }

    public Bitmap getCachedSnapshot() {
        Bitmap bitmap = this.cachedSnapshot;
        return (bitmap == null || !this.cachedSnapshotValid || bitmap.isRecycled()) ? takeSnapshot() : this.cachedSnapshot;
    }

    public Bitmap takeSnapshot() {
        Bitmap bitmap = this.cachedSnapshot;
        if (bitmap != null && !this.cachedSnapshotValid) {
            bitmap.recycle();
            this.cachedSnapshot = null;
        }
        if (this.cachedSnapshot == null) {
            this.cachedSnapshot = BrowserTabManager.getInstance().getBitmap(this.index);
            this.cachedSnapshotValid = true;
        }
        return this.cachedSnapshot;
    }
}
