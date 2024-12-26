package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.interfaces.VoteSRSelectedChangeListener;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
public class VoteSelectSRAdapter extends BaseQuickAdapter<WitnessOutput.DataBean, VoteSelectSRItemHolder> {
    public final Protocol.Account account;
    private String constraint;
    private boolean isLedger;
    private final boolean isShowSelect;
    private int mSelectedCount;
    private String selectAddress;
    private boolean showFilter;
    private VoteSRSelectedChangeListener upperListener;
    private VoteSRSelectedChangeListener voteSRSelectedChangeListener;

    public void updateSearchFilter(boolean z, String str) {
        this.showFilter = z;
        this.constraint = str;
    }

    public VoteSelectSRAdapter(final Context context, Protocol.Account account, String str, boolean z, final boolean z2, List<WitnessOutput.DataBean> list, final List<WitnessOutput.DataBean> list2, VoteSRSelectedChangeListener voteSRSelectedChangeListener) {
        super((int) R.layout.item_vote_select_sr);
        setLoadMoreView(new CustomLoadMoreView());
        setEmptyView(createEmptyView(context));
        this.mContext = context;
        this.account = account;
        this.isShowSelect = z;
        this.selectAddress = str;
        this.isLedger = z2;
        this.upperListener = voteSRSelectedChangeListener;
        this.voteSRSelectedChangeListener = new VoteSRSelectedChangeListener() {
            @Override
            public void onVoteSRSelected(String str2, WitnessOutput.DataBean dataBean, boolean z3) {
                mSelectedCount = list2.size();
                boolean z4 = false;
                for (int i = 0; i < mData.size(); i++) {
                    if (((WitnessOutput.DataBean) mData.get(i)).getAddress().equals(str2) && ((z2 && mSelectedCount < 5) || (!z2 && mSelectedCount < 30))) {
                        ((WitnessOutput.DataBean) mData.get(i)).setSelected(true);
                        z4 = true;
                    }
                }
                if (upperListener != null && z4) {
                    upperListener.onVoteSRSelected(str2, dataBean, z4);
                }
                if (!z4 && z2) {
                    ToastUtil.getInstance().showToast(context, R.string.vote_ledger_less_than_five);
                } else if (!z4) {
                    ToastUtil.getInstance().showToast(context, R.string.vote_sr_less_than_30);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onVoteSRUnSelected(String str2, WitnessOutput.DataBean dataBean) {
                if (upperListener != null) {
                    upperListener.onVoteSRUnSelected(str2, dataBean);
                }
                for (int i = 0; i < mData.size(); i++) {
                    if (((WitnessOutput.DataBean) mData.get(i)).getAddress().equals(str2)) {
                        ((WitnessOutput.DataBean) mData.get(i)).setSelected(false);
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onVoteSRClicked(String str2, WitnessOutput.DataBean dataBean) {
                if (upperListener != null) {
                    upperListener.onVoteSRClicked(str2, dataBean);
                }
            }
        };
        setNewData(list);
    }

    private View createEmptyView(Context context) {
        NoNetView noNetView = new NoNetView(context);
        noNetView.setIcon(R.mipmap.ic_no_data_new).setReloadDescription(R.string.no_sr_found).setReloadable(false);
        noNetView.setPadding(0, UIUtils.dip2px(60.0f), 0, 0);
        return noNetView;
    }

    @Override
    public void convert(VoteSelectSRItemHolder voteSelectSRItemHolder, WitnessOutput.DataBean dataBean) {
        voteSelectSRItemHolder.onBind(dataBean, this.voteSRSelectedChangeListener, this.showFilter, this.constraint, this.isShowSelect);
    }

    public void updateData(boolean z, List<WitnessOutput.DataBean> list) {
        if (z) {
            setNewData(list);
        } else {
            addData((Collection) list);
        }
    }

    public void notifySearchData(List<WitnessOutput.DataBean> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        updateData(true, list);
    }

    public void notifyData(HashMap<String, String> hashMap, List<WitnessOutput.DataBean> list, boolean z) {
        updateData(z, list);
    }
}
