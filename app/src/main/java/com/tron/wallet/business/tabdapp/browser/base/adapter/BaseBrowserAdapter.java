package com.tron.wallet.business.tabdapp.browser.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.tabdapp.browser.bean.BaseWebViewPageInfo;
import com.tron.wallet.common.adapter.holder.NoMoreFooterHolder;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public abstract class BaseBrowserAdapter<T> extends RecyclerView.Adapter<BaseHolder> {
    protected static final int ITEM_TYPE_CONTENT = 2;
    protected static final int ITEM_TYPE_HISTORY_DATE = 11;
    protected static final int ITEM_TYPE_NORMAL = 0;
    protected static final int ITEM_TYPE_NO_MORE = 1;
    List<T> datas = new ArrayList();
    ItemCallback itemCallback;
    Context mContext;
    boolean showNoMoreItem;

    public interface ItemCallback {

        public final class -CC {
            public static void $default$onItemLongClicked(ItemCallback _this, View view, BaseWebViewPageInfo baseWebViewPageInfo, int[] iArr, int i) {
            }
        }

        void onItemClicked(BaseWebViewPageInfo baseWebViewPageInfo, int i);

        void onItemLongClicked(View view, BaseWebViewPageInfo baseWebViewPageInfo, int[] iArr, int i);

        void onItemSelected(BaseWebViewPageInfo baseWebViewPageInfo, int i);
    }

    public abstract BaseHolder getViewHolder(ViewGroup viewGroup, int i);

    public boolean isShowNoMoreItem() {
        return this.showNoMoreItem;
    }

    @Override
    public abstract void onBindViewHolder(BaseHolder baseHolder, int i);

    public void setShowNoMoreItem(boolean z) {
        this.showNoMoreItem = z;
    }

    public BaseBrowserAdapter(Context context, ItemCallback itemCallback) {
        this.mContext = context;
        this.itemCallback = itemCallback;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new NoMoreFooterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assets_no_more, viewGroup, false), viewGroup.getContext());
        }
        BaseHolder viewHolder = getViewHolder(viewGroup, i);
        if (i == 2) {
            viewHolder.itemView.getLayoutParams().height = UIUtils.dip2px(70.0f);
        }
        return viewHolder;
    }

    public void notifyDataChanged(List<T> list) {
        this.datas = list;
        if (list != null && list.size() <= 5) {
            setShowNoMoreItem(false);
        } else {
            setShowNoMoreItem(true);
        }
        notifyDataSetChanged();
    }

    public void notifyDataChanged(List<T> list, boolean z) {
        if (z) {
            this.datas = list;
        } else {
            this.datas.addAll(list);
        }
        List<T> list2 = this.datas;
        if (list2 != null && list2.size() <= 5) {
            setShowNoMoreItem(false);
        } else {
            setShowNoMoreItem(true);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        List<T> list = this.datas;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return this.showNoMoreItem ? this.datas.size() + 1 : this.datas.size();
    }
}
