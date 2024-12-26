package com.tron.wallet.business.vote;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.vote.bean.FastAprBean;
import com.tron.wallet.business.vote.bean.VoteHeaderBean;
import com.tron.wallet.business.vote.component.Contract;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.databinding.FragmentVoteHeaderBinding;
import com.tronlinkpro.wallet.R;
public class VoteHeaderFragment extends BaseFragment<EmptyPresenter, EmptyModel> implements Contract.OnPullToRefreshCallback {
    View aprContainer;
    private FragmentVoteHeaderBinding binding;
    View btnGetProfit;
    View btnPromote;
    RelativeLayout btnVote;
    private double currentProfit;
    private VoteHeaderBean currentVotingData;
    private int flagProfitCounter = 2;
    private boolean flagProfitVisibility = false;
    View ivTips;
    View llVotingContainer;
    View llVotingRightsContainer;
    private OnHeaderViewClickCallback outerListener;
    RelativeLayout rlProfitContainer;
    View toStake;
    TextView tvApr;
    TextView tvAprTitle;
    TextView tvAvailableTitle;
    TextView tvAvailableTrx;
    TextView tvAvailableVotes;
    TextView tvProfit;
    TextView tvTotalVotes;
    TextView tvVoted;

    public interface OnHeaderViewClickCallback {
        void onClickGetProfit(double d);

        void onClickPromote();

        void onClickStake();

        void onClickTips(View view);

        void onClickVote();
    }

    private void resetFlags() {
        this.flagProfitCounter = 2;
        this.flagProfitVisibility = false;
    }

    public void setHeaderViewClickCallback(OnHeaderViewClickCallback onHeaderViewClickCallback) {
        this.outerListener = onHeaderViewClickCallback;
    }

    private void initEvents() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if ((id == R.id.to_promote || id == R.id.tv_apr) && outerListener != null) {
                    outerListener.onClickPromote();
                } else if (outerListener != null && id == R.id.to_stake) {
                    outerListener.onClickStake();
                } else if (id == R.id.btn_get_profit && outerListener != null) {
                    enableProfitButton(false);
                    outerListener.onClickGetProfit(currentProfit);
                } else if (id == R.id.btn_vote && outerListener != null) {
                    outerListener.onClickVote();
                } else if (id != R.id.iv_tips || outerListener == null) {
                } else {
                    outerListener.onClickTips(ivTips);
                }
            }
        };
        this.btnPromote.setOnClickListener(noDoubleClickListener);
        this.tvApr.setOnClickListener(noDoubleClickListener);
        this.toStake.setOnClickListener(noDoubleClickListener);
        this.btnGetProfit.setOnClickListener(noDoubleClickListener);
        this.btnVote.setOnClickListener(noDoubleClickListener);
        this.ivTips.setOnClickListener(noDoubleClickListener);
    }

    @Override
    protected void processData() {
        this.btnGetProfit.setEnabled(false);
        this.btnVote.setEnabled(false);
        this.tvApr.setEnabled(false);
        initEvents();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentVoteHeaderBinding inflate = FragmentVoteHeaderBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.tvAvailableVotes = this.binding.tvAvailableVotes;
        this.tvTotalVotes = this.binding.tvTotalVoteRights;
        this.tvVoted = this.binding.tvAlreadyVote;
        this.tvApr = this.binding.tvApr;
        this.tvAvailableTrx = this.binding.tvAvailableTrx;
        this.tvProfit = this.binding.tvProfit;
        this.rlProfitContainer = this.binding.rlProfitContainer;
        this.btnGetProfit = this.binding.btnGetProfit;
        this.btnVote = this.binding.btnVote;
        this.btnPromote = this.binding.toPromote;
        this.aprContainer = this.binding.llAprContainer;
        this.tvAprTitle = this.binding.tvAprTitle;
        this.llVotingRightsContainer = this.binding.llContainer;
        this.toStake = this.binding.toStake;
        this.tvAvailableTitle = this.binding.availableVoteRightTitle;
        this.llVotingContainer = this.binding.llVotingContainer;
        this.ivTips = this.binding.ivTips;
    }

    public void bindHeaderData(VoteHeaderBean voteHeaderBean, boolean z) {
        this.currentVotingData = voteHeaderBean;
        boolean z2 = voteHeaderBean.getTotalVotingRights() > 0;
        updateProfitVisibility(z2);
        this.btnVote.setEnabled(BigDecimalUtils.MoreThan((Object) Long.valueOf(voteHeaderBean.getTotalVotingRights()), (Object) 0));
        this.llVotingRightsContainer.setVisibility(z2 ? View.VISIBLE : View.GONE);
        if (!z2) {
            this.tvAvailableTitle.setText(R.string.vote_all_voting_rights);
        } else {
            this.tvAvailableTitle.setText(R.string.vote_available_vote_rights);
        }
        this.tvAvailableVotes.setText(StringTronUtil.formatNumber6Truncate(Long.valueOf(voteHeaderBean.getAvailableVotingRights())));
        this.tvTotalVotes.setText(StringTronUtil.formatNumber6Truncate(Long.valueOf(voteHeaderBean.getTotalVotingRights())));
        this.tvVoted.setText(StringTronUtil.formatNumber6Truncate(Long.valueOf(voteHeaderBean.getAlreadyVotedNumber())));
        this.tvAvailableTrx.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(voteHeaderBean.getAvailableTrx())));
        if (z) {
            this.toStake.setVisibility(View.GONE);
        }
        this.llVotingContainer.setVisibility(View.VISIBLE);
    }

    public void updateApr(FastAprBean fastAprBean) {
        double d;
        double currentApr = fastAprBean.getCurrentApr();
        double fastApr = fastAprBean.getFastApr();
        VoteHeaderBean voteHeaderBean = this.currentVotingData;
        if (voteHeaderBean == null || voteHeaderBean.getTotalVotingRights() <= 0) {
            this.tvAprTitle.setText(R.string.vote_highest_apr);
            d = fastApr;
        } else {
            this.tvAprTitle.setText(R.string.vote_current_apr);
            ((VoteMainActivity) getActivity()).initAprTipLayout(currentApr);
            d = currentApr;
        }
        boolean z = true;
        this.tvApr.setText(String.format("%s%%", VoteAprCalculator.formatAprPercent(d)));
        boolean z2 = currentApr < fastApr;
        VoteHeaderBean voteHeaderBean2 = this.currentVotingData;
        boolean z3 = z2 & ((voteHeaderBean2 == null || voteHeaderBean2.getTotalVotingRights() < 3) ? false : false);
        Drawable drawable = !z3 ? null : AppCompatResources.getDrawable(getIContext(), R.mipmap.ic_vote_apr);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        this.tvApr.setCompoundDrawables(null, null, drawable, null);
        this.tvApr.setEnabled(z3);
        this.btnPromote.setVisibility(z3 ? View.VISIBLE : View.GONE);
        this.aprContainer.setVisibility(View.VISIBLE);
    }

    public void updateProfit(double d) {
        this.currentProfit = d;
        int i = (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
        this.tvProfit.setText(String.format("%s TRX", (i <= 0 || d >= 0.001d) ? StringTronUtil.formatNumber3Truncate(Double.valueOf(d)) : "<0.001"));
        updateProfitVisibility(i > 0);
    }

    private void updateProfitVisibility(boolean z) {
        boolean z2 = z | this.flagProfitVisibility;
        this.flagProfitVisibility = z2;
        int i = this.flagProfitCounter - 1;
        this.flagProfitCounter = i;
        if (i > 0) {
            return;
        }
        this.rlProfitContainer.setVisibility(z2 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPullToRefresh() {
        resetFlags();
    }

    public void enableProfitButton(boolean z) {
        this.btnGetProfit.setEnabled(z);
    }
}
