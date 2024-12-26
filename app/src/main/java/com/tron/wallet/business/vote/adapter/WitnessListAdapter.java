package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
public class WitnessListAdapter extends BaseQuickAdapter<WitnessOutput.DataBean, WitnessListItemHolder> {
    public WitnessListAdapter(Context context) {
        super((int) R.layout.item_vote_witness_list);
        setLoadMoreView(new CustomLoadMoreView());
        setEmptyView(createEmptyView(context));
    }

    @Override
    public void convert(WitnessListItemHolder witnessListItemHolder, WitnessOutput.DataBean dataBean) {
        witnessListItemHolder.onBind(dataBean);
    }

    public void updateData(boolean z, List<WitnessOutput.DataBean> list) {
        if (z) {
            setNewData(list);
            loadMoreComplete();
            return;
        }
        addData((Collection) list);
        if (list.isEmpty()) {
            loadMoreEnd();
        } else {
            loadMoreComplete();
        }
    }

    private View createEmptyView(Context context) {
        NoNetView noNetView = new NoNetView(context);
        noNetView.setIcon(R.mipmap.ic_no_data_new).setReloadDescription(R.string.no_records).setReloadable(false);
        noNetView.setPadding(0, UIUtils.dip2px(60.0f), 0, 0);
        return noNetView;
    }

    public static abstract class DebouncedItemClickListener implements BaseQuickAdapter.OnItemClickListener {
        public static final int MIN_CLICK_DELAY_TIME = 2000;
        private long lastClickTime = 0;

        protected abstract void onDebouncedClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

        @Override
        public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (timeInMillis - this.lastClickTime > 2000) {
                this.lastClickTime = timeInMillis;
                onDebouncedClick(baseQuickAdapter, view, i);
            }
        }
    }
}
