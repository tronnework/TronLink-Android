package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tronpower.stake.StakeTRXActivity;
import com.tron.wallet.business.vote.adapter.VoteItemAdapter;
import com.tron.wallet.common.components.dialog.Common5Dialog;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.Locale;
import org.tron.walletserver.Wallet;
public class VoteHeaderHolder extends BaseHolder {
    private int currentType;
    private Common5Dialog dialog;
    ImageView ivRewards;
    private View llGetReward;
    LinearLayout llGetvote;
    LinearLayout llTp;
    LinearLayout llVoteCount;
    protected Context mContext;
    private String mTrxReward;
    TextView myVote;
    private NumberFormat numberFormat;
    TextView remainingTp;
    TextView surplusAvailable;
    TextView totalVote;
    TextView trxReward;
    TextView tvRewards;
    private String walletName;
    private VoteItemAdapter.OnWitnessClickListener witnessClickListener;

    public int getCurrentType() {
        return this.currentType;
    }

    public VoteHeaderHolder(View view, Context context, VoteItemAdapter.OnWitnessClickListener onWitnessClickListener, String str) {
        super(view);
        this.currentType = 0;
        mappingId(view);
        this.mContext = context;
        this.numberFormat = NumberFormat.getNumberInstance(Locale.US);
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.walletName = selectedWallet == null ? selectedWallet.getWalletName() : "";
        this.witnessClickListener = onWitnessClickListener;
        this.mTrxReward = str;
        this.llGetvote.setOnClickListener(getOnClickListener());
        this.llGetReward.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.ll_getReward) {
                    if (id != R.id.ll_getvote) {
                        return;
                    }
                    goFreezeAct();
                } else if (!mTrxReward.equals("0")) {
                    if (witnessClickListener != null) {
                        witnessClickListener.getReward(mTrxReward);
                    }
                } else {
                    IToast.getIToast().show(R.string.no_vote_reward);
                }
            }
        };
    }

    public void bindHolder(long j, long j2, long j3) {
        String str;
        this.totalVote.setText(this.numberFormat.format(j));
        this.myVote.setText(this.numberFormat.format(j2));
        this.surplusAvailable.setText(this.numberFormat.format(j3));
        TextView textView = this.trxReward;
        if (StringTronUtil.isEmpty(this.mTrxReward)) {
            str = "0\tTRX";
        } else {
            str = this.mTrxReward + "\tTRX";
        }
        textView.setText(str);
        updateVoteBtStatus();
    }

    private void showDialog() {
        SpAPI.THIS.setIsFirstVoteFreeze(this.walletName, false);
        Common5Dialog cancleBt = new Common5Dialog(this.mContext).setTitle(R.string.tips).setContent(R.string.vote_freeze_tips).setBtListener(R.string.do_freezing, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StakeTRXActivity.start(mContext, null);
                dialog.dismiss();
            }
        }).setCancleBt(R.string.temporarily, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        this.dialog = cancleBt;
        cancleBt.show();
    }

    public void goFreezeAct() {
        if (SpAPI.THIS.isFirstVoteFreeze(this.walletName)) {
            showDialog();
        } else {
            StakeTRXActivity.start(this.mContext, null);
        }
    }

    public void updateVoteReword(String str) {
        String str2;
        if (StringTronUtil.isEmpty(str)) {
            str = "0";
        }
        this.mTrxReward = str;
        TextView textView = this.trxReward;
        if (StringTronUtil.isEmpty(str)) {
            str2 = "0\tTRX";
        } else {
            str2 = this.mTrxReward + "\tTRX";
        }
        textView.setText(str2);
        updateVoteBtStatus();
    }

    private void updateVoteBtStatus() {
        if (this.trxReward.getText().toString().equals("0\tTRX")) {
            this.tvRewards.setTextColor(this.mContext.getResources().getColor(R.color.white_50));
            this.ivRewards.setBackgroundResource(R.mipmap.arraw_vote_unclick);
            return;
        }
        this.tvRewards.setTextColor(this.mContext.getResources().getColor(R.color.white));
        this.ivRewards.setBackgroundResource(R.mipmap.arraw_vote_freeze);
    }

    private void mappingId(View view) {
        this.remainingTp = (TextView) view.findViewById(R.id.remaining_tp);
        this.surplusAvailable = (TextView) view.findViewById(R.id.surplus_available);
        this.llGetvote = (LinearLayout) view.findViewById(R.id.ll_getvote);
        this.llTp = (LinearLayout) view.findViewById(R.id.ll_tp);
        this.myVote = (TextView) view.findViewById(R.id.my_vote);
        this.totalVote = (TextView) view.findViewById(R.id.total_vote);
        this.llVoteCount = (LinearLayout) view.findViewById(R.id.ll_vote_count);
        this.trxReward = (TextView) view.findViewById(R.id.trx_reward);
        this.tvRewards = (TextView) view.findViewById(R.id.tv_awards);
        this.ivRewards = (ImageView) view.findViewById(R.id.iv_rewards);
        this.llGetReward = view.findViewById(R.id.ll_getReward);
    }
}
