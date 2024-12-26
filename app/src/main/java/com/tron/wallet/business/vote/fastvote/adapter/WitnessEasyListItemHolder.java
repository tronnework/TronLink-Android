package com.tron.wallet.business.vote.fastvote.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.view.itoast.ToastUtils;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.fastvote.FastVoteActivity;
import com.tron.wallet.business.vote.fastvote.FastVotePresenter;
import com.tron.wallet.business.vote.fastvote.VoteWitnessBean;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tronlinkpro.wallet.R;
import java.util.HashMap;
public class WitnessEasyListItemHolder extends BaseViewHolder {
    private static final int[] rankingBackground = {R.color.green_57, R.color.orange_FFA9, R.color.red_ec};
    LinearLayout contentLayout;
    CurrencyEditText etInput;
    ImageView ivMinus;
    ImageView ivPlus;
    ImageView ivTips;
    private VoteWitnessBean mItem;
    private TextWatcher mTextWatcher;
    private WitnessOutput.DataBean mWitness;
    RelativeLayout rlAlreadyVoted;
    TextView tvApr;
    View tvMax;
    TextView tvName;
    TextView tvRanking;
    TextView tvTips;
    TextView tvVotesCount;

    public WitnessEasyListItemHolder(View view) {
        super(view);
        mappingId(view);
    }

    public void onBind(VoteWitnessBean voteWitnessBean, String str, HashMap<String, String> hashMap, long j, final FastVotePresenter.VoteCountChangeListener voteCountChangeListener, final WitnessEasyListAdapter witnessEasyListAdapter) {
        this.mItem = voteWitnessBean;
        if (voteWitnessBean.isVisibility()) {
            this.contentLayout.setVisibility(View.GONE);
            this.tvTips.setVisibility(View.GONE);
        } else {
            this.contentLayout.setVisibility(View.VISIBLE);
            if (witnessEasyListAdapter.getItemCount() == getLayoutPosition() + 1) {
                this.tvTips.setVisibility(View.VISIBLE);
            } else {
                this.tvTips.setVisibility(View.GONE);
            }
        }
        WitnessOutput.DataBean dataBean = voteWitnessBean.getDataBean();
        this.mWitness = dataBean;
        this.tvName.setText(dataBean.getName());
        Resources resources = this.itemView.getContext().getResources();
        long realTimeRanking = this.mWitness.getRealTimeRanking();
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
        this.tvVotesCount.setText(StringTronUtil.formatNumber3Truncate(Long.valueOf(this.mWitness.getRealTimeVotes())));
        this.tvApr.setText(String.format("%s%%", VoteAprCalculator.formatAprPercent(this.mWitness.getAnnualized_income())));
        if (this.mTextWatcher == null) {
            BaseTextWatcher baseTextWatcher = new BaseTextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                    long checkInputNumber = checkInputNumber(TextDotUtils.getText_EditText_Dot(etInput));
                    String address = mWitness != null ? mWitness.getAddress() : null;
                    String voteCount = mItem.getVoteCount();
                    if (address == null || StringTronUtil.equals(String.valueOf(checkInputNumber), voteCount)) {
                        return;
                    }
                    mItem.setVoteCount(String.valueOf(checkInputNumber));
                    voteCountChangeListener.onVoteCountChanged(address, checkInputNumber, checkInputNumber(voteCount), mWitness.getName());
                }
            };
            this.mTextWatcher = baseTextWatcher;
            this.etInput.addTextChangedListener(baseTextWatcher);
            TextDotUtils.setTextChangedListener_Dot(this.etInput);
        }
        this.etInput.setLongClickable(false);
        this.etInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteCountChangeListener.onEtClick();
            }
        });
        this.etInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                lambda$onBind$0(view, z);
            }
        });
        this.etInput.setText(voteWitnessBean.getVoteCount());
        this.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                sb.append(((FastVoteActivity) witnessEasyListAdapter.context).getLogPageTag());
                sb.append(witnessEasyListAdapter.isFromMultiSign ? AnalyticsHelper.VotePage.MULTI_SIGN : AnalyticsHelper.VotePage.SINGLE_SIGN);
                sb.append(2);
                AnalyticsHelper.AssetPage.logEvent(sb.toString());
                String text_EditText_Dot = TextDotUtils.getText_EditText_Dot(etInput);
                if (!StringTronUtil.isEmpty(text_EditText_Dot)) {
                    long longValue = Long.valueOf(text_EditText_Dot).longValue();
                    if (longValue - witnessEasyListAdapter.step < 0) {
                        etInput.setText("0");
                        etInput.setSelection(1);
                        return;
                    }
                    TextDotUtils.setText_Dot(etInput, String.valueOf(longValue - witnessEasyListAdapter.step));
                    etInput.setSelection(etInput.getText().length());
                    return;
                }
                etInput.setText("0");
                etInput.setSelection(1);
            }
        });
        this.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                sb.append(((FastVoteActivity) witnessEasyListAdapter.context).getLogPageTag());
                sb.append(witnessEasyListAdapter.isFromMultiSign ? AnalyticsHelper.VotePage.MULTI_SIGN : AnalyticsHelper.VotePage.SINGLE_SIGN);
                sb.append(2);
                AnalyticsHelper.AssetPage.logEvent(sb.toString());
                if (witnessEasyListAdapter.allVotes <= 0) {
                    ToastUtils.show((CharSequence) witnessEasyListAdapter.context.getString(R.string.vote_count_maximum_tips));
                    return;
                }
                long j2 = witnessEasyListAdapter.step;
                String text_EditText_Dot = TextDotUtils.getText_EditText_Dot(etInput);
                if (!StringTronUtil.isEmpty(text_EditText_Dot)) {
                    long longValue = Long.valueOf(text_EditText_Dot).longValue();
                    if (j2 > witnessEasyListAdapter.allVotes) {
                        TextDotUtils.setText_Dot(etInput, String.valueOf(longValue + witnessEasyListAdapter.allVotes));
                        return;
                    } else {
                        TextDotUtils.setText_Dot(etInput, String.valueOf(longValue + j2));
                        return;
                    }
                }
                TextDotUtils.setText_Dot(etInput, String.valueOf(j2));
            }
        });
        this.tvMax.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                String text_EditText_Dot = TextDotUtils.getText_EditText_Dot(etInput);
                TextDotUtils.setText_Dot(etInput, String.valueOf((!StringTronUtil.isEmpty(text_EditText_Dot) ? Long.valueOf(text_EditText_Dot).longValue() : 0L) + witnessEasyListAdapter.allVotes));
            }
        });
        if (voteWitnessBean.getDataBean().isHasChanged()) {
            Context context = this.itemView.getContext();
            final String string = context.getString(R.string.vote_sr_item_apr_tips, VoteAprCalculator.formatAprPercent(Double.valueOf(voteWitnessBean.getDataBean().getMinApy()).doubleValue()) + "%");
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

    public void lambda$onBind$0(View view, boolean z) {
        if (!z) {
            if (StringTronUtil.isEmpty(this.etInput.getText().toString())) {
                this.etInput.removeTextChangedListener(this.mTextWatcher);
                this.etInput.setText("0");
                this.etInput.setSelection(1);
                this.etInput.addTextChangedListener(this.mTextWatcher);
            }
            this.tvMax.setVisibility(View.GONE);
            return;
        }
        this.tvMax.setVisibility(View.VISIBLE);
    }

    public long checkInputNumber(CharSequence charSequence) {
        try {
            if (charSequence.length() > 0) {
                return Long.parseLong(charSequence.toString());
            }
            return 0L;
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    private void mappingId(View view) {
        this.contentLayout = (LinearLayout) view.findViewById(R.id.ll_content);
        this.tvRanking = (TextView) view.findViewById(R.id.tv_ranking);
        this.tvName = (TextView) view.findViewById(R.id.tv_witness_name);
        this.tvApr = (TextView) view.findViewById(R.id.tv_apr);
        this.tvVotesCount = (TextView) view.findViewById(R.id.tv_votes_count);
        this.tvTips = (TextView) view.findViewById(R.id.tv_vote_tips_bottom);
        this.rlAlreadyVoted = (RelativeLayout) view.findViewById(R.id.rl_flag_already_voted);
        this.etInput = (CurrencyEditText) view.findViewById(R.id.et_input);
        this.ivMinus = (ImageView) view.findViewById(R.id.vote_minus);
        this.ivPlus = (ImageView) view.findViewById(R.id.vote_plus);
        this.tvMax = view.findViewById(R.id.tv_max);
        this.ivTips = (ImageView) view.findViewById(R.id.iv_vote_apr_tips);
    }
}
