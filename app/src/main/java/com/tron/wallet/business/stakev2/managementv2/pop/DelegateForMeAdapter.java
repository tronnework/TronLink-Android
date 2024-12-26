package com.tron.wallet.business.stakev2.managementv2.pop;

import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeBean;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class DelegateForMeAdapter extends BaseQuickAdapter<StakeResourceDetailForMeBean, BaseViewHolder> {
    public DelegateForMeAdapter() {
        super((int) R.layout.item_delegate_for_me);
        setLoadMoreView(new CustomLoadMoreView());
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, StakeResourceDetailForMeBean stakeResourceDetailForMeBean) {
        if (baseViewHolder instanceof DelegateForMeHolder) {
            ((DelegateForMeHolder) baseViewHolder).onBind(this.mContext, stakeResourceDetailForMeBean);
        }
    }

    @Override
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i) {
        return new DelegateForMeHolder(getItemView(this.mLayoutResId, viewGroup));
    }

    @Override
    public List<StakeResourceDetailForMeBean> getData() {
        return super.getData();
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
