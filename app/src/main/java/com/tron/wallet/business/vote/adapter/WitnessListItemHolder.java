package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.CustomFontUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tronlinkpro.wallet.R;
public class WitnessListItemHolder extends BaseViewHolder {
    private static final int[] rankingBackground = {R.color.green_57, R.color.orange_FFA9, R.color.red_ec};
    ImageView ivTips;
    ImageView ivVoted;
    RelativeLayout rlAlreadyVoted;
    TextView tvApr;
    TextView tvMyVotes;
    TextView tvName;
    TextView tvRanking;
    TextView tvVotesCount;

    public WitnessListItemHolder(View view) {
        super(view);
        mappingId(view);
    }

    public void onBind(WitnessOutput.DataBean dataBean) {
        this.tvName.setText(getShownName(dataBean));
        Resources resources = this.itemView.getContext().getResources();
        long realTimeRanking = dataBean.getRealTimeRanking();
        if (realTimeRanking == 1) {
            this.tvRanking.setTextColor(resources.getColor(R.color.green_89));
        } else if (realTimeRanking == 2) {
            this.tvRanking.setTextColor(resources.getColor(R.color.orange_FFC3));
        } else if (realTimeRanking == 3) {
            this.tvRanking.setTextColor(resources.getColor(R.color.red_F2));
        } else {
            this.tvRanking.setTextColor(resources.getColor(R.color.black_23));
        }
        this.tvRanking.setText(String.format("No.%d ", Long.valueOf(realTimeRanking)));
        this.tvVotesCount.setText(StringTronUtil.formatNumber3Truncate(Long.valueOf(dataBean.getRealTimeVotes())));
        this.tvApr.setText(String.format("%s%%", VoteAprCalculator.formatAprPercent(dataBean.getAnnualized_income())));
        String voted = dataBean.getVoted();
        if (BigDecimalUtils.lessThanOrEqual(voted, 0)) {
            this.rlAlreadyVoted.setVisibility(View.GONE);
            this.ivVoted.setVisibility(View.GONE);
            this.tvVotesCount.setTextColor(resources.getColor(R.color.black_23));
            TextView textView = this.tvVotesCount;
            textView.setTypeface(CustomFontUtils.getTypeface(textView.getContext(), 1));
        } else {
            this.rlAlreadyVoted.setVisibility(View.VISIBLE);
            this.ivVoted.setVisibility(View.VISIBLE);
            this.tvMyVotes.setText(StringTronUtil.plainScientificNotation(BigDecimalUtils.toBigDecimal(voted), 3));
            this.tvVotesCount.setTextColor(resources.getColor(R.color.gray_9B));
            TextView textView2 = this.tvVotesCount;
            textView2.setTypeface(CustomFontUtils.getTypeface(textView2.getContext(), 0));
            this.ivVoted.setBackgroundResource(LanguageUtils.languageZH(this.itemView.getContext()) ? R.mipmap.ic_vote_item_voted_cn : R.mipmap.ic_vote_item_voted_en);
        }
        if (dataBean.isHasChanged()) {
            Context context = this.itemView.getContext();
            final String string = context.getString(R.string.vote_sr_item_apr_tips, VoteAprCalculator.formatAprPercent(Double.valueOf(dataBean.getMinApy()).doubleValue()) + "%");
            this.ivTips.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    new MultiLineTextPopupView.Builder().setPreferredShowUp(false).setAnchor(view).setRequiredWidth(UIUtils.getScreenWidth(itemView.getContext()) - (UIUtils.dip2px(35.0f) * 2)).addKeyValue(string, "").build(itemView.getContext()).show();
                }
            });
            this.ivTips.setVisibility(View.VISIBLE);
            return;
        }
        this.ivTips.setVisibility(View.GONE);
    }

    private String getShownName(WitnessOutput.DataBean dataBean) {
        if (dataBean == null) {
            return "";
        }
        String name = dataBean.getName();
        if (TextUtils.isEmpty(name)) {
            name = dataBean.getUrl();
        }
        if (TextUtils.isEmpty(name)) {
            name = dataBean.getAddress();
        }
        return name == null ? "" : name;
    }

    private void mappingId(View view) {
        this.tvRanking = (TextView) view.findViewById(R.id.tv_ranking);
        this.tvName = (TextView) view.findViewById(R.id.tv_witness_name);
        this.tvApr = (TextView) view.findViewById(R.id.tv_apr);
        this.tvVotesCount = (TextView) view.findViewById(R.id.tv_votes_count);
        this.tvMyVotes = (TextView) view.findViewById(R.id.tv_voted_count);
        this.ivVoted = (ImageView) view.findViewById(R.id.iv_voted);
        this.rlAlreadyVoted = (RelativeLayout) view.findViewById(R.id.rl_flag_already_voted);
        this.ivTips = (ImageView) view.findViewById(R.id.iv_vote_apr_tips);
    }
}
