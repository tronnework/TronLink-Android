package com.tron.wallet.business.tabdapp.browser.grid.adapter;

import android.graphics.Bitmap;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.grid.adapter.AbstractDataProvider;
import com.tron.wallet.common.utils.BitmapUtils;
import com.tronlinkpro.wallet.R;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class BrowserTabsDataProvider extends AbstractDataProvider {
    private final BrowserTabManager browserTabManager;
    private ConcreteData mLastRemovedData;
    private int mLastRemovedPosition = -1;
    private List<ConcreteData> mData = new LinkedList();

    public BrowserTabsDataProvider(BrowserTabManager browserTabManager) {
        this.browserTabManager = browserTabManager;
        refreshTabData();
    }

    public void refreshTabData() {
        this.mData.clear();
        int i = 0;
        while (i < this.browserTabManager.getTabCount()) {
            if (this.browserTabManager.getViewType(i) == 0) {
                this.mData.add(new ConcreteData(i, 0, this.browserTabManager.getCurrentTabIndex() == i, AppContextUtil.getContext().getString(R.string.browser_start_page), this.browserTabManager.getIconUrl(i), BitmapUtils.getSnapShotImage(this.browserTabManager.getTabAt(i).getCachedSnapshot())));
            } else {
                this.mData.add(new ConcreteData(i, 1, this.browserTabManager.getCurrentTabIndex() == i, this.browserTabManager.getTitle(i), this.browserTabManager.getIconUrl(i), BitmapUtils.getSnapShotImage(this.browserTabManager.getTabAt(i).getCachedSnapshot())));
            }
            i++;
        }
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public AbstractDataProvider.Data getItem(int i) {
        if (i < 0 || i >= getCount()) {
            throw new IndexOutOfBoundsException("index = " + i);
        }
        return this.mData.get(i);
    }

    @Override
    public int undoLastRemoval() {
        if (this.mLastRemovedData != null) {
            int i = this.mLastRemovedPosition;
            int size = (i < 0 || i >= this.mData.size()) ? this.mData.size() : this.mLastRemovedPosition;
            this.mData.add(size, this.mLastRemovedData);
            this.mLastRemovedData = null;
            this.mLastRemovedPosition = -1;
            return size;
        }
        return -1;
    }

    @Override
    public void moveItem(int i, int i2) {
        if (i == i2) {
            return;
        }
        this.mData.add(i2, this.mData.remove(i));
        this.mLastRemovedPosition = -1;
        this.browserTabManager.moveItem(i, i2);
    }

    @Override
    public void swapItem(int i, int i2) {
        if (i == i2) {
            return;
        }
        Collections.swap(this.mData, i2, i);
        this.mLastRemovedPosition = -1;
    }

    @Override
    public void removeItem(int i) {
        this.mLastRemovedData = this.mData.remove(i);
        this.mLastRemovedPosition = i;
    }

    public static final class ConcreteData extends AbstractDataProvider.Data {
        private Bitmap mBitmap;
        private final String mIconUrl;
        private final long mId;
        private boolean mIsCurSelected;
        private boolean mPinned;
        private final String mUrl;
        private final int mViewType;

        public String getIconUrl() {
            return this.mIconUrl;
        }

        @Override
        public long getId() {
            return this.mId;
        }

        @Override
        public String getText() {
            return this.mUrl;
        }

        @Override
        public int getViewType() {
            return this.mViewType;
        }

        public Bitmap getmBitmap() {
            return this.mBitmap;
        }

        @Override
        public boolean isPinned() {
            return this.mPinned;
        }

        @Override
        public boolean isSectionHeader() {
            return false;
        }

        public boolean ismIsCurSelected() {
            return this.mIsCurSelected;
        }

        @Override
        public void setPinned(boolean z) {
            this.mPinned = z;
        }

        public void setmBitmap(Bitmap bitmap) {
            this.mBitmap = bitmap;
        }

        public void setmIsCurSelected(boolean z) {
            this.mIsCurSelected = z;
        }

        public String toString() {
            return this.mUrl;
        }

        ConcreteData(long j, int i, boolean z, String str, String str2, Bitmap bitmap) {
            this.mId = j;
            this.mViewType = i;
            this.mUrl = str;
            this.mIconUrl = str2;
            this.mIsCurSelected = z;
            this.mBitmap = bitmap;
        }
    }
}
