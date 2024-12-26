package com.tron.wallet.business.confirm.core.pending.fg.stake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemStakeVoteAdapterHeaderBinding;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.List;
public class StakeSuccessVoteAdapter extends BaseQuickAdapter<VoteWitnessBean, ViewHolder> {
    NumberFormat numberFormat;

    public StakeSuccessVoteAdapter(Context context, List<VoteWitnessBean> list) {
        super(R.layout.item_stake_vote_adapter, list);
        this.numberFormat = NumberFormat.getNumberInstance();
        addHeaderView(LayoutInflater.from(context).inflate(R.layout.item_stake_vote_adapter_header, (ViewGroup) null));
    }

    @Override
    public void convert(ViewHolder viewHolder, VoteWitnessBean voteWitnessBean) {
        TextView textView = viewHolder.tvSrName;
        textView.setText(voteWitnessBean.getDataBean().getName() + "");
        viewHolder.tvSrApy.setText(String.format("%s%%", StringTronUtil.formatNumberTruncateNoDots(BigDecimalUtils.toBigDecimal(Double.valueOf(voteWitnessBean.getDataBean().getAnnualized_income())), 2)));
        viewHolder.tvSrVotes.setText(this.numberFormat.format(Double.parseDouble(voteWitnessBean.getVoteCount())));
    }

    public class ViewHolder extends BaseViewHolder {
        private ItemStakeVoteAdapterHeaderBinding binding;
        TextView tvSrApy;
        TextView tvSrName;
        TextView tvSrVotes;

        public ViewHolder(View view) {
            super(view);
            mappingId(view);
        }

        public void mappingId(View view) {
            ItemStakeVoteAdapterHeaderBinding bind = ItemStakeVoteAdapterHeaderBinding.bind(view);
            this.binding = bind;
            this.tvSrName = bind.tvSrName;
            this.tvSrApy = this.binding.tvSrApr;
            this.tvSrVotes = this.binding.tvSrVotes;
        }
    }
}
