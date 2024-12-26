package com.tron.wallet.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.common.adapter.holder.DealSignHolder;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.config.TronConfig;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class DealSignAdapter extends BaseQuickAdapter<DealSignOutput.DataBeanX.DataBean, BaseViewHolder> {
    private Context mContext;
    private String mWalletName;
    private int state;

    public DealSignAdapter(Context context, List<DealSignOutput.DataBeanX.DataBean> list, int i, String str) {
        super(R.layout.item_deal_sign, list);
        this.mContext = context;
        this.state = i;
        this.mWalletName = str;
        CustomLoadMoreView customLoadMoreView = new CustomLoadMoreView();
        customLoadMoreView.setNoMoreText(R.string.no_more_asset, new Object[0]);
        setLoadMoreView(customLoadMoreView);
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new DealSignHolder(LayoutInflater.from(this.mContext).inflate(this.mLayoutResId, viewGroup, false));
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, DealSignOutput.DataBeanX.DataBean dataBean) {
        if (!(baseViewHolder instanceof DealSignHolder) || dataBean == null || baseViewHolder == null) {
            return;
        }
        if (TronConfig.openList != null && TronConfig.openList.size() > 0 && TronConfig.openList.contains(dataBean.hash)) {
            dataBean.isOpen = true;
        }
        ((DealSignHolder) baseViewHolder).bindHolder(this.mContext, dataBean, this.state, this.mWalletName, baseViewHolder.getAbsoluteAdapterPosition(), getItemCount());
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder baseViewHolder) {
        super.onViewAttachedToWindow((DealSignAdapter) baseViewHolder);
        if (baseViewHolder instanceof DealSignHolder) {
            DealSignOutput.DataBeanX.DataBean dataBean = getData().get(baseViewHolder.getAbsoluteAdapterPosition());
            if (dataBean != null) {
                ((DealSignHolder) baseViewHolder).refreshTime(dataBean.expireTime);
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(BaseViewHolder baseViewHolder) {
        super.onViewDetachedFromWindow((DealSignAdapter) baseViewHolder);
        if (baseViewHolder instanceof DealSignHolder) {
            ((DealSignHolder) baseViewHolder).getCvCountdownView().stop();
        }
    }

    public void notifyData1(List<DealSignOutput.DataBeanX.DataBean> list) {
        setNewData(list);
    }
}
