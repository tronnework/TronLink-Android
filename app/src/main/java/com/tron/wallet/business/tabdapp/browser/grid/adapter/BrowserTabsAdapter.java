package com.tron.wallet.business.tabdapp.browser.grid.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.business.tabdapp.browser.grid.adapter.BrowserTabsDataProvider;
import com.tron.wallet.business.tabdapp.browser.grid.base.ItemDraggableRange;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.DraggableItemAdapter;
import com.tron.wallet.common.utils.BitmapUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tronlinkpro.wallet.R;
public class BrowserTabsAdapter extends RecyclerView.Adapter<MyViewHolder> implements DraggableItemAdapter<MyViewHolder> {
    private static final String TAG = "BrowserTabsAdapter";
    private static int dstWidth;
    private int mItemMoveMode = 0;
    private AbstractDataProvider mProvider;
    private TabChangedLister tabChangedLister;

    public interface TabChangedLister {
        void onClick(int i);

        void onTabClose(int i);
    }

    public TabChangedLister getTabChangedLister() {
        return this.tabChangedLister;
    }

    @Override
    public boolean onCheckCanDrop(int i, int i2) {
        return true;
    }

    @Override
    public boolean onCheckCanStartDrag(MyViewHolder myViewHolder, int i, int i2, int i3) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder myViewHolder, int i) {
        return null;
    }

    public void setItemMoveMode(int i) {
        this.mItemMoveMode = i;
    }

    public void setTabChangedLister(TabChangedLister tabChangedLister) {
        this.tabChangedLister = tabChangedLister;
    }

    public static class MyViewHolder extends AbstractDraggableItemViewHolder {
        public ImageView ivTabsMarker;
        public ImageView mClosedIcon;
        public RelativeLayout mContainer;
        public FrameLayout mRoot;
        public SimpleDraweeView mTabIcon;
        public TextView mTitle;
        public SimpleDraweeView snapshotIcon;

        public MyViewHolder(View view) {
            super(view);
            this.mContainer = (RelativeLayout) view.findViewById(R.id.container);
            this.mRoot = (FrameLayout) view.findViewById(R.id.fr_root);
            this.mTitle = (TextView) view.findViewById(R.id.tv_title);
            this.mClosedIcon = (ImageView) view.findViewById(R.id.iv_tab_close);
            this.mTabIcon = (SimpleDraweeView) view.findViewById(R.id.iv_tab_icon);
            this.snapshotIcon = (SimpleDraweeView) view.findViewById(R.id.iv_tab_snapshot);
            this.ivTabsMarker = (ImageView) view.findViewById(R.id.iv_tabs_mark);
        }
    }

    public BrowserTabsAdapter(AbstractDataProvider abstractDataProvider) {
        this.mProvider = abstractDataProvider;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int i) {
        return this.mProvider.getItem(i).getId();
    }

    @Override
    public int getItemViewType(int i) {
        return this.mProvider.getItem(i).getViewType();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_grid_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        final BrowserTabsDataProvider.ConcreteData concreteData = (BrowserTabsDataProvider.ConcreteData) this.mProvider.getItem(i);
        myViewHolder.mTitle.setText(concreteData.getText());
        myViewHolder.mClosedIcon.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (tabChangedLister != null) {
                    tabChangedLister.onTabClose(i);
                    mProvider.removeItem(i);
                }
            }
        });
        myViewHolder.mContainer.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (tabChangedLister != null) {
                    tabChangedLister.onClick(i);
                }
            }
        });
        if (concreteData.getmBitmap() != null) {
            if (dstWidth != 0) {
                myViewHolder.snapshotIcon.setImageBitmap(BitmapUtils.getSnapShotImage(concreteData.getmBitmap(), dstWidth));
            } else {
                myViewHolder.snapshotIcon.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        myViewHolder.snapshotIcon.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        BrowserTabsAdapter.dstWidth = myViewHolder.snapshotIcon.getMeasuredWidth();
                        myViewHolder.snapshotIcon.setImageBitmap(BitmapUtils.getSnapShotImage(concreteData.getmBitmap(), BrowserTabsAdapter.dstWidth));
                    }
                });
            }
        }
        if (concreteData.getViewType() == 0) {
            myViewHolder.mTabIcon.setImageResource(R.mipmap.ic_browser_main);
        } else {
            myViewHolder.mTabIcon.setImageURI(concreteData.getIconUrl());
        }
        TouchDelegateUtils.expandViewTouchDelegate(myViewHolder.mClosedIcon, 15);
        if (concreteData.ismIsCurSelected()) {
            myViewHolder.mContainer.setBackground(null);
            myViewHolder.ivTabsMarker.setVisibility(View.VISIBLE);
            myViewHolder.ivTabsMarker.setImageResource(R.mipmap.ic_tab_bg_marker);
            return;
        }
        myViewHolder.mRoot.setBackground(null);
        myViewHolder.mContainer.setBackgroundResource(R.drawable.bg_swipe_item_neutral);
        myViewHolder.ivTabsMarker.setImageResource(R.mipmap.ic_tab_bg_marker_white);
    }

    @Override
    public int getItemCount() {
        return this.mProvider.getCount();
    }

    @Override
    public void onMoveItem(int i, int i2) {
        Log.d(TAG, "onMoveItem(fromPosition = " + i + ", toPosition = " + i2 + ")");
        if (this.mItemMoveMode == 0) {
            this.mProvider.moveItem(i, i2);
        } else {
            this.mProvider.swapItem(i, i2);
        }
    }

    @Override
    public void onItemDragStarted(int i) {
        notifyDataSetChanged();
    }

    @Override
    public void onItemDragFinished(int i, int i2, boolean z) {
        notifyDataSetChanged();
    }
}
