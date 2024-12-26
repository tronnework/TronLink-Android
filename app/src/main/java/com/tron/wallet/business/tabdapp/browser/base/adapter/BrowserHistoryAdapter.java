package com.tron.wallet.business.tabdapp.browser.base.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tron.wallet.business.tabdapp.browser.base.adapter.BaseBrowserAdapter;
import com.tron.wallet.business.tabdapp.browser.bean.BaseWebViewPageInfo;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemBrowserHistoryBinding;
import com.tronlinkpro.wallet.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class BrowserHistoryAdapter extends BaseQuickAdapter<BrowserHistoryBean, BrowserHistoryHolder> {
    private boolean isSortChanged;
    private BaseBrowserAdapter.ItemCallback itemCallback;
    private int[] touchLocation;

    public boolean isSortChanged() {
        return this.isSortChanged;
    }

    public void resetSortChanged() {
        this.isSortChanged = false;
    }

    public BrowserHistoryAdapter(Context context, ArrayList<BrowserHistoryBean> arrayList, BaseBrowserAdapter.ItemCallback itemCallback) {
        super(R.layout.item_browser_history, arrayList);
        this.isSortChanged = false;
        this.touchLocation = new int[2];
        this.mContext = context;
        this.itemCallback = itemCallback;
        setLoadMoreView(new CustomLoadMoreView());
    }

    @Override
    public void convert(final BrowserHistoryHolder browserHistoryHolder, final BrowserHistoryBean browserHistoryBean) {
        browserHistoryHolder.onBind(this.mContext, browserHistoryBean, 0, this.mData.size());
        browserHistoryHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$convert$0(browserHistoryBean, view);
            }
        });
        browserHistoryHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$convert$1;
                lambda$convert$1 = lambda$convert$1(view, motionEvent);
                return lambda$convert$1;
            }
        });
        browserHistoryHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public final boolean onLongClick(View view) {
                boolean lambda$convert$2;
                lambda$convert$2 = lambda$convert$2(browserHistoryBean, browserHistoryHolder, view);
                return lambda$convert$2;
            }
        });
        if (browserHistoryHolder.getItemViewType() == 1) {
            browserHistoryHolder.tvTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$convert$3(browserHistoryHolder, browserHistoryBean, view);
                }
            });
        }
    }

    public void lambda$convert$0(BrowserHistoryBean browserHistoryBean, View view) {
        BaseBrowserAdapter.ItemCallback itemCallback = this.itemCallback;
        if (itemCallback != null) {
            itemCallback.onItemClicked(browserHistoryBean, -1);
        }
    }

    public boolean lambda$convert$1(View view, MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.touchLocation[0] = (int) motionEvent.getX();
            this.touchLocation[1] = (int) motionEvent.getY();
        }
        return false;
    }

    public boolean lambda$convert$2(BrowserHistoryBean browserHistoryBean, BrowserHistoryHolder browserHistoryHolder, View view) {
        BaseBrowserAdapter.ItemCallback itemCallback = this.itemCallback;
        if (itemCallback != null) {
            itemCallback.onItemLongClicked(view, browserHistoryBean, this.touchLocation, browserHistoryHolder.getAbsoluteAdapterPosition());
            return true;
        }
        return false;
    }

    public void lambda$convert$3(BrowserHistoryHolder browserHistoryHolder, BrowserHistoryBean browserHistoryBean, View view) {
        if (this.itemCallback == null || view.getVisibility() != 0 || browserHistoryHolder.getAdapterPosition() == -1) {
            return;
        }
        this.itemCallback.onItemSelected(browserHistoryBean, browserHistoryHolder.getAbsoluteAdapterPosition());
    }

    public void notifyDataChanged(List<BrowserHistoryBean> list, boolean z) {
        if (z) {
            this.mData = list;
        } else {
            this.mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<BrowserHistoryBean> getDatas() {
        return this.mData;
    }

    @Override
    public int getItemCount() {
        if (this.mData == null || this.mData.size() == 0) {
            return 0;
        }
        return this.mData.size() + 1;
    }

    public void remove(BaseWebViewPageInfo baseWebViewPageInfo, int i) {
        this.mData.remove(i);
        int i2 = i - 1;
        if ((i2 >= 0 && ((BrowserHistoryBean) this.mData.get(i2)).getType() == -1 && this.mData.size() == i) || (i2 >= 0 && ((BrowserHistoryBean) this.mData.get(i2)).getType() == -1 && ((BrowserHistoryBean) this.mData.get(i)).getType() == -1)) {
            this.mData.remove(i2);
            notifyDataSetChanged();
            return;
        }
        notifyItemRemoved(i);
    }

    public static class BrowserHistoryHolder extends BaseViewHolder {
        public LinearLayout liDate;
        public RelativeLayout rlItem;
        SimpleDateFormat sdf;
        public SimpleDraweeView simpleDraweeView;
        public TextView tvDateStr;
        public TextView tvSubtitle;
        public TextView tvTime;
        public TextView tvTitle;

        public BrowserHistoryHolder(View view) {
            super(view);
            mappingId(view);
            this.sdf = new SimpleDateFormat("HH:mm");
        }

        public void mappingId(View view) {
            ItemBrowserHistoryBinding bind = ItemBrowserHistoryBinding.bind(view);
            this.rlItem = bind.rlItem;
            this.simpleDraweeView = bind.image;
            this.tvTitle = bind.tvTitle;
            this.tvSubtitle = bind.tvSubtitle;
            this.tvTime = bind.tvTime;
            this.liDate = bind.liDate;
            this.tvDateStr = bind.tvDateStr;
        }

        public void onBind(Context context, BaseWebViewPageInfo baseWebViewPageInfo, int i, int i2) {
            if (baseWebViewPageInfo.getType() == -1) {
                this.liDate.setVisibility(View.VISIBLE);
                this.rlItem.setVisibility(View.GONE);
                this.tvDateStr.setText(baseWebViewPageInfo.getTitle());
                return;
            }
            this.liDate.setVisibility(View.GONE);
            this.rlItem.setVisibility(View.VISIBLE);
            if (StringTronUtil.isEmpty(baseWebViewPageInfo.getTitle())) {
                this.tvTitle.setVisibility(View.GONE);
            } else {
                this.tvTitle.setVisibility(View.VISIBLE);
                this.tvTitle.setText(baseWebViewPageInfo.getTitle());
            }
            this.tvSubtitle.setText(baseWebViewPageInfo.getUrl());
            this.simpleDraweeView.setImageURI(baseWebViewPageInfo.getIconUrl());
            if (baseWebViewPageInfo instanceof BrowserHistoryBean) {
                this.tvTime.setText(this.sdf.format(new Date(((BrowserHistoryBean) baseWebViewPageInfo).timestamp)));
            }
        }
    }
}
