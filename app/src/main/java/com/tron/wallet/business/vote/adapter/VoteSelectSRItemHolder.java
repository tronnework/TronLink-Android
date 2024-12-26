package com.tron.wallet.business.vote.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.interfaces.VoteSRSelectedChangeListener;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tronlinkpro.wallet.R;
public class VoteSelectSRItemHolder extends BaseViewHolder {
    private static final int[] rankingBackground = {R.color.green_57, R.color.orange_FFA9, R.color.red_ec};
    ForegroundColorSpan backgroundColorSpan;
    ForegroundColorSpan foregroundColorSpan;
    ImageView ivArrow;
    ImageView ivSelected;
    ImageView ivTips;
    ImageView ivVoted;
    RelativeLayout rlAlreadyVoted;
    RelativeLayout rlRoot;
    TextView tvApr;
    TextView tvMyVotes;
    TextView tvName;
    TextView tvRanking;
    TextView tvVotesCount;

    public VoteSelectSRItemHolder(View view) {
        super(view);
        this.foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#3C7CF3"));
        this.backgroundColorSpan = new ForegroundColorSpan(Color.parseColor("#232C41"));
        mappingId(view);
    }

    public void onBind(final WitnessOutput.DataBean dataBean, final VoteSRSelectedChangeListener voteSRSelectedChangeListener, boolean z, String str, boolean z2) {
        updateSymbol(dataBean, z, str);
        if (z2) {
            this.rlRoot.setBackgroundResource(R.drawable.roundborder_gray_f7f8fa_radius_8);
            this.ivSelected.setVisibility(View.VISIBLE);
            this.ivArrow.setVisibility(View.GONE);
        } else {
            this.rlRoot.setBackgroundResource(R.drawable.roundborder_white_r8);
            this.ivSelected.setVisibility(View.GONE);
            this.ivArrow.setVisibility(View.VISIBLE);
        }
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
        this.tvApr.setText(String.format("%s%%", StringTronUtil.formatNumberTruncateNoDots(BigDecimalUtils.toBigDecimal(Double.valueOf(dataBean.getAnnualized_income())), 2)));
        String voted = dataBean.getVoted();
        if (BigDecimalUtils.lessThanOrEqual(voted, 0)) {
            this.rlAlreadyVoted.setVisibility(View.GONE);
            this.ivVoted.setVisibility(View.GONE);
        } else {
            this.ivVoted.setVisibility(View.VISIBLE);
            this.rlAlreadyVoted.setVisibility(View.VISIBLE);
            this.tvMyVotes.setText(StringTronUtil.formatNumber3Truncate(Long.valueOf(Long.parseLong(voted))));
        }
        TouchDelegateUtils.expandViewTouchDelegate(this.ivSelected, 15, 15, 15, 15);
        this.ivSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (voteSRSelectedChangeListener != null) {
                    if (dataBean.isSelected()) {
                        voteSRSelectedChangeListener.onVoteSRUnSelected(dataBean.getAddress(), dataBean);
                    } else {
                        voteSRSelectedChangeListener.onVoteSRSelected(dataBean.getAddress(), dataBean, false);
                    }
                }
            }
        });
        this.itemView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                VoteSRSelectedChangeListener voteSRSelectedChangeListener2 = voteSRSelectedChangeListener;
                if (voteSRSelectedChangeListener2 != null) {
                    voteSRSelectedChangeListener2.onVoteSRClicked(dataBean.getAddress(), dataBean);
                }
            }
        });
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
        } else {
            this.ivTips.setVisibility(View.GONE);
        }
        if (dataBean.isSelected()) {
            this.ivSelected.setImageResource(R.mipmap.ic_check_selected);
        } else {
            this.ivSelected.setImageResource(R.mipmap.ic_check_unselect);
        }
    }

    private void updateSymbol(WitnessOutput.DataBean dataBean, boolean z, String str) {
        SpannableString spannableString;
        if (z && !TextUtils.isEmpty(str)) {
            String ellipsizedText = getEllipsizedText(this.tvName, dataBean.getName());
            int indexOf = ellipsizedText.toLowerCase().indexOf(str);
            if (indexOf != -1) {
                spannableString = new SpannableString(ellipsizedText);
                spannableString.setSpan(this.foregroundColorSpan, indexOf, str.length() + indexOf, 33);
            } else {
                SpannableString spannableString2 = new SpannableString(dataBean.getName());
                spannableString2.setSpan(this.backgroundColorSpan, 0, dataBean.getName().length(), 33);
                spannableString = spannableString2;
            }
            this.tvName.setText(spannableString);
            return;
        }
        this.tvName.setText(dataBean.getName());
    }

    private String getEllipsizedText(TextView textView, String str) {
        return TextUtils.ellipsize(str, textView.getPaint(), UIUtils.dip2px(280.0f), textView.getEllipsize()).toString();
    }

    private void mappingId(View view) {
        this.rlRoot = (RelativeLayout) view.findViewById(R.id.vote_select_root);
        this.tvRanking = (TextView) view.findViewById(R.id.tv_ranking);
        this.tvName = (TextView) view.findViewById(R.id.tv_witness_name);
        this.tvApr = (TextView) view.findViewById(R.id.tv_apr);
        this.tvVotesCount = (TextView) view.findViewById(R.id.vote_count);
        this.tvMyVotes = (TextView) view.findViewById(R.id.tv_voted_count);
        this.ivArrow = (ImageView) view.findViewById(R.id.iv_arrow);
        this.ivSelected = (ImageView) view.findViewById(R.id.iv_selected);
        this.rlAlreadyVoted = (RelativeLayout) view.findViewById(R.id.rl_flag_already_voted);
        this.ivTips = (ImageView) view.findViewById(R.id.iv_vote_apr_tips);
        this.ivVoted = (ImageView) view.findViewById(R.id.iv_voted);
    }
}
