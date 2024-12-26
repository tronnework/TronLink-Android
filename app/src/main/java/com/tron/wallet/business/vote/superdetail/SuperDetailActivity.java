package com.tron.wallet.business.vote.superdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.tabmy.proposals.bean.SRDetailBean;
import com.tron.wallet.business.vote.bean.WitnessOutput;
import com.tron.wallet.business.vote.superdetail.SuperDetailContract;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.databinding.AcSuperRepresentativesBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.apache.commons.cli.HelpFormatter;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SuperDetailActivity extends BaseActivity<SuperDetailPresenter, SuperDetailModel> implements SuperDetailContract.View {
    public static final String KEY_ALL_VOTE_COUNT = "key_all_votes";
    public static final String KEY_RANKING = "key_ranking";
    public static final String KEY_SELECT_ADDRESS = "key_select_address";
    private static final String KEY_SHOW_VOTE_BTN = "key_show_vote";
    public static final String KEY_SR_ADDRESS = "key_sr_address";
    public static final String KEY_SR_INDEX = "key_sr_index";
    public static final String KEY_SR_NAME = "key_sr_name";
    public static final String KEY_VOTED_COUNT = "key_sr_voted_count";
    public static final String KEY_WITNESS_BEAN = "key_witness_bean";
    public static final String KEY_WITNESS_DATABEAN = "key_witness_databean";
    private Protocol.Account account;
    private AcSuperRepresentativesBinding binding;
    TextView btnCancelVote;
    ViewGroup btnNoVote;
    TextView btnUpdateVote;
    TextView btnVote;
    ViewGroup btnsVoted;
    View divider;
    private boolean fromMultiSign;
    ImageView ivCopy;
    ImageView ivTips;
    ImageView ivUrlCopy;
    private int myVotedCount;
    private MultiSignPermissionData permissionData;
    private int ranking;
    RelativeLayout rlTop;
    ViewGroup rlVoted;
    View rlYield;
    private boolean showVoteBtn;
    private int srIndex;
    private BasePopupView tipsWindow;
    TextView tvAddress;
    TextView tvBlockRadio;
    TextView tvMultiSignWarning;
    TextView tvName;
    TextView tvPercentage;
    TextView tvProduced;
    TextView tvProfits;
    TextView tvRanking;
    TextView tvTotal;
    TextView tvUrl;
    TextView tvVotingOnsiderations;
    TextView tvYield;
    TextView tvYield2;
    TextView votedNumber;
    private WitnessOutput.DataBean witness;
    private WitnessOutput witnessOutput;
    private static final int[] rankingBackground = {R.color.green_57_15, R.color.orange_FFC369, R.color.red_f9};
    public static String KEY_ACCOUNT = "key_account";
    private String srAddress = "";
    private String srName = "";
    private DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Vote;

    public String getLogMultiSignTag() {
        return this.myVotedCount > 0 ? AnalyticsHelper.SRDetail.VOTED : AnalyticsHelper.SRDetail.UNVOTED;
    }

    public static void start(Context context, Protocol.Account account, WitnessOutput witnessOutput, int i, long j, MultiSignPermissionData multiSignPermissionData, WitnessOutput.DataBean dataBean, ArrayList<WitnessOutput.DataBean> arrayList, int i2, String str, String str2, boolean z, boolean z2, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent(context, SuperDetailActivity.class);
        intent.putExtra(KEY_ACCOUNT, account);
        intent.putExtra(KEY_WITNESS_BEAN, witnessOutput);
        intent.putExtra(KEY_VOTED_COUNT, i);
        intent.putExtra(KEY_WITNESS_DATABEAN, dataBean);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        intent.putParcelableArrayListExtra(CommonBundleKeys.KEY_ALREADY_VOTED_WITNESSES, arrayList);
        intent.putExtra(KEY_RANKING, i2);
        intent.putExtra("key_all_votes", j);
        intent.putExtra("key_select_address", str2);
        intent.putExtra("key_controller_name", str);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra(KEY_SHOW_VOTE_BTN, z2);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcSuperRepresentativesBinding inflate = AcSuperRepresentativesBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvRanking = this.binding.tvRanking;
        this.tvName = this.binding.tvName;
        this.tvAddress = this.binding.tvAddress;
        this.ivCopy = this.binding.ivCopy;
        this.tvYield = this.binding.tvYield;
        this.tvYield2 = this.binding.tvYield2;
        this.rlTop = this.binding.rlTop;
        this.tvProfits = this.binding.tvProfits;
        this.divider = this.binding.divider;
        this.tvTotal = this.binding.tvTotalVote;
        this.tvPercentage = this.binding.tvPercentage;
        this.tvProduced = this.binding.tvProduced;
        this.tvUrl = this.binding.tvUrl;
        this.ivUrlCopy = this.binding.ivUrlCopy;
        this.tvVotingOnsiderations = this.binding.tvVotingOnsiderations;
        this.rlVoted = this.binding.llVoted2;
        this.votedNumber = this.binding.tvVotedNumber;
        this.tvBlockRadio = this.binding.tvBlockRadio;
        this.btnNoVote = this.binding.btnsNoVote;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
        this.btnsVoted = this.binding.btnsVoted;
        this.btnVote = this.binding.btnVote;
        this.btnCancelVote = this.binding.btnVotedCancel;
        this.btnUpdateVote = this.binding.btnVotedUpdate;
        this.rlYield = this.binding.rlYield;
        this.ivTips = this.binding.ivVoteAprTips;
    }

    @Override
    protected void processData() {
        getParam();
        DesugarArrays.stream(new View[]{this.ivCopy, this.tvVotingOnsiderations, this.ivUrlCopy, this.tvUrl, this.binding.llBack}).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0((View) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        ((SuperDetailPresenter) this.mPresenter).init(this.account, this.witnessOutput, this.witness);
        preUpdateUI();
        ((SuperDetailPresenter) this.mPresenter).mRxManager.on(Event.BroadcastSuccess, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$1(obj);
            }
        });
        ((SuperDetailPresenter) this.mPresenter).getData();
    }

    public void lambda$processData$0(View view) {
        view.setOnClickListener(getOnClickListener());
    }

    public void lambda$processData$1(Object obj) throws Exception {
        finish();
    }

    private void preUpdateUI() {
        if (this.witness == null) {
            return;
        }
        this.tvName.setText(this.srName);
        if (TextUtils.isEmpty(this.srName)) {
            this.tvName.setText(this.witness.getUrl());
            if (TextUtils.isEmpty(this.witness.getUrl())) {
                this.tvName.setText(this.witness.getAddress());
            }
        }
        int i = this.ranking;
        if (i > 3) {
            this.tvRanking.setBackgroundResource(R.drawable.round_transaction_state_bg_blue);
            this.tvRanking.setText(String.format("NO.%d ", Integer.valueOf(this.ranking)));
            this.tvRanking.setTextColor(getResources().getColor(R.color.blue_3c));
        } else {
            this.tvRanking.setText(String.format("NO.%d ", Integer.valueOf(i)));
            int i2 = this.ranking;
            if (i2 == 1) {
                this.tvRanking.setBackgroundResource(R.drawable.round_transaction_state_bg_green_super);
                this.tvRanking.setTextColor(getResources().getColor(R.color.green_31));
            } else if (i2 == 2) {
                this.tvRanking.setBackgroundResource(R.drawable.round_transaction_state_bg_yellow);
                this.tvRanking.setTextColor(getResources().getColor(R.color.yellow_FFA928));
            } else {
                this.tvRanking.setBackgroundResource(R.drawable.round_transaction_state_bg_red);
                this.tvRanking.setTextColor(getResources().getColor(R.color.red_EC));
            }
        }
        this.tvAddress.setText(this.srAddress);
        if (this.myVotedCount > 0) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.VoteResult.ENTER_VoteResult_SUCCESS);
            this.rlVoted.setVisibility(View.VISIBLE);
            this.btnsVoted.setVisibility(View.VISIBLE);
            this.btnNoVote.setVisibility(View.GONE);
            this.rlYield.setVisibility(View.GONE);
        } else {
            this.rlVoted.setVisibility(View.GONE);
            this.btnsVoted.setVisibility(View.GONE);
            this.btnNoVote.setVisibility(View.VISIBLE);
            this.rlYield.setVisibility(View.VISIBLE);
        }
        if (!this.showVoteBtn) {
            this.btnsVoted.setVisibility(View.GONE);
            this.btnNoVote.setVisibility(View.VISIBLE);
            this.btnVote.setEnabled(false);
        }
        TextView textView = this.tvProfits;
        textView.setText(String.valueOf(100 - this.witness.getBrokerage()) + "%");
        TextView textView2 = this.tvBlockRadio;
        textView2.setText(VoteAprCalculator.formatAprPercent(this.witness.getProducedEfficiency()) + "%");
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mPresenter == 0) {
                    return;
                }
                if (permissionData != null && !permissionData.isVoteWitnessPermission()) {
                    SuperDetailActivity superDetailActivity = SuperDetailActivity.this;
                    superDetailActivity.toast(superDetailActivity.getString(R.string.vote_no_vote_permission));
                } else if (fromMultiSign && WalletUtils.getSelectedWallet().isSamsungWallet()) {
                    SuperDetailActivity superDetailActivity2 = SuperDetailActivity.this;
                    superDetailActivity2.toast(superDetailActivity2.getString(R.string.transaction_type_not_support));
                } else {
                    switch (view.getId()) {
                        case R.id.btn_vote:
                            AnalyticsHelper.logEvent(getLogMultiSignTag() + 1);
                            ((SuperDetailPresenter) mPresenter).vote();
                            return;
                        case R.id.btn_vote_again:
                        case R.id.btn_vote_tool_bar:
                        default:
                            return;
                        case R.id.btn_voted_cancel:
                            AnalyticsHelper.logEvent(getLogMultiSignTag() + 3);
                            ((SuperDetailPresenter) mPresenter).voteCancel();
                            return;
                        case R.id.btn_voted_update:
                            AnalyticsHelper.logEvent(getLogMultiSignTag() + 2);
                            ((SuperDetailPresenter) mPresenter).voteUpdate();
                            return;
                    }
                }
            }
        };
        this.btnVote.setOnClickListener(noDoubleClickListener);
        this.btnUpdateVote.setOnClickListener(noDoubleClickListener);
        this.btnCancelVote.setOnClickListener(noDoubleClickListener);
        initAprUpdateTipView(this.witness);
    }

    private void initAprUpdateTipView(final WitnessOutput.DataBean dataBean) {
        if (dataBean.isHasChanged()) {
            this.ivTips.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    SuperDetailActivity superDetailActivity = SuperDetailActivity.this;
                    String string = superDetailActivity.getString(R.string.vote_sr_item_apr_tips, new Object[]{VoteAprCalculator.formatAprPercent(Double.valueOf(dataBean.getMinApy()).doubleValue()) + "%"});
                    SpannableString spannableString = new SpannableString(string + " " + getIContext().getResources().getString(R.string.vote_change_reward_tronscan));
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(getIContext().getResources().getColor(R.color.blue_3c));
                            textPaint.setUnderlineText(false);
                        }

                        @Override
                        public void onClick(View view2) {
                            CommonWebActivityV3.start(getIContext(), IRequest.getTronscanAprUrl(srAddress), "", -2, false);
                            if (tipsWindow != null) {
                                tipsWindow.dismiss();
                            }
                        }
                    }, string.length(), string.length() + 1 + getIContext().getResources().getString(R.string.vote_change_reward_tronscan).length(), 33);
                    tipsWindow = new MultiLineTextPopupView.Builder().setPreferredShowUp(false).setAnchor(view).setRequiredWidth(UIUtils.getScreenWidth(getIContext()) - (UIUtils.dip2px(35.0f) * 2)).addKeyValue(spannableString, "").build(getIContext());
                    tipsWindow.show();
                }
            });
            this.ivTips.setVisibility(View.VISIBLE);
            return;
        }
        this.ivTips.setVisibility(View.GONE);
    }

    private void getParam() {
        WitnessOutput.DataBean dataBean;
        this.witness = (WitnessOutput.DataBean) getIntent().getParcelableExtra(KEY_WITNESS_DATABEAN);
        this.ranking = getIntent().getIntExtra(KEY_RANKING, 0);
        this.myVotedCount = getIntent().getIntExtra(KEY_VOTED_COUNT, 0);
        this.account = (Protocol.Account) getIntent().getSerializableExtra(KEY_ACCOUNT);
        this.showVoteBtn = getIntent().getBooleanExtra(KEY_SHOW_VOTE_BTN, true);
        this.fromMultiSign = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.permissionData = (MultiSignPermissionData) getIntent().getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
        this.statAction = (DataStatHelper.StatAction) getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        this.srName = this.witness.getName();
        this.srAddress = this.witness.getAddress();
        Parcelable parcelableExtra = getIntent().getParcelableExtra(KEY_WITNESS_BEAN);
        if (parcelableExtra != null) {
            WitnessOutput witnessOutput = (WitnessOutput) parcelableExtra;
            this.witnessOutput = witnessOutput;
            if (witnessOutput.getData() == null) {
                return;
            }
            if (this.myVotedCount == 0 && (dataBean = this.witness) != null && !TextUtils.isEmpty(dataBean.getVoted())) {
                this.myVotedCount = Integer.parseInt(this.witness.getVoted());
            }
        } else {
            this.srAddress = getIntent().getStringExtra(KEY_SR_ADDRESS);
            this.srName = getIntent().getStringExtra(KEY_SR_NAME);
        }
        if (this.fromMultiSign) {
            Bundle bundle = new Bundle();
            bundle.putString("is_multivoter", "mutil");
            AnalyticsHelper.logEvent("sr_detail", bundle);
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putString("is_multivoter", AnalyticsHelper.VoteMainPage.PARAM_VALUE_SINGLE);
            AnalyticsHelper.logEvent("sr_detail", bundle2);
        }
        if (this.fromMultiSign) {
            final String stringExtra = getIntent().getStringExtra("key_select_address");
            String stringExtra2 = getIntent().getStringExtra("key_controller_name");
            if (!TextUtils.isEmpty(stringExtra2)) {
                stringExtra = String.format("%s (%s)", stringExtra2, stringExtra);
            }
            this.tvMultiSignWarning.setText(getString(R.string.multi_vote_controller_tips, new Object[]{stringExtra}));
            this.tvMultiSignWarning.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$getParam$2(stringExtra);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
        }
    }

    public void lambda$getParam$2(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_copy:
                        SuperDetailActivity superDetailActivity = SuperDetailActivity.this;
                        superDetailActivity.copyAndToast(superDetailActivity.tvAddress.getText().toString());
                        return;
                    case R.id.iv_url_copy:
                        SuperDetailActivity superDetailActivity2 = SuperDetailActivity.this;
                        superDetailActivity2.copyAndToast(superDetailActivity2.tvUrl.getText().toString());
                        return;
                    case R.id.ll_back:
                        finish();
                        return;
                    case R.id.tv_url:
                        if (witness == null) {
                            return;
                        }
                        String url = witness.getUrl();
                        if (TextUtils.isEmpty(url)) {
                            return;
                        }
                        if (url.indexOf(ChainUtil.Request_HTTP) < 0) {
                            url.indexOf("https://");
                        }
                        SuperDetailActivity superDetailActivity3 = SuperDetailActivity.this;
                        CommonWebActivityV3.start((Context) superDetailActivity3, url, superDetailActivity3.getString(R.string.super_representatives), false, new WebOptions.WebOptionsBuild().addCode(-2).addNeedOutside(false).build());
                        return;
                    case R.id.tv_voting_onsiderations:
                        SuperDetailActivity superDetailActivity4 = SuperDetailActivity.this;
                        CommonWebActivityV3.start((Context) superDetailActivity4, superDetailActivity4.getString(R.string.url_voting_considerations), getString(R.string.help), -2, false);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void copyAndToast(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UIUtils.copy(str);
        toast(getString(R.string.already_copy));
    }

    @Override
    public void updateUI(SRDetailBean sRDetailBean) {
        this.tvYield.setText(sRDetailBean.getYield());
        this.tvYield2.setText(sRDetailBean.getYield());
        this.votedNumber.setText(StringTronUtil.formatNumber3Truncate(Integer.valueOf(this.myVotedCount)));
        TextView textView = this.tvProfits;
        textView.setText(sRDetailBean.getProfit() + "%");
        this.tvTotal.setText(sRDetailBean.getFormattedVotes());
        TextView textView2 = this.tvPercentage;
        textView2.setText(VoteAprCalculator.formatAprPercent(this.witness.getVotesPercentage()) + "%");
        this.tvProduced.setText(StringTronUtil.formatNumber3Truncate(Long.valueOf(sRDetailBean.getBlocks())));
        if (StringTronUtil.isEmpty(sRDetailBean.getLinkUrl())) {
            this.tvUrl.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            this.ivUrlCopy.setVisibility(View.GONE);
            return;
        }
        this.tvUrl.setText(sRDetailBean.getLinkUrl());
        this.ivUrlCopy.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestCancelVoteComplete(boolean z, String str, BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
        dismissLoadingDialog();
        if (z && baseConfirmTransParamBuilder != null) {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            ConfirmTransactionNewActivity.startActivity(this, baseConfirmTransParamBuilder, selectedPublicWallet != null && selectedPublicWallet.isSamsungWallet());
        } else if (TextUtils.isEmpty(str)) {
        } else {
            toast(str);
        }
    }

    private boolean hasVotePermission() {
        MultiSignPermissionData multiSignPermissionData = this.permissionData;
        boolean z = multiSignPermissionData == null || multiSignPermissionData.isVoteWitnessPermission();
        if (!z) {
            OwnerPermissionCheckUtils.showMultiSignDialog(getIContext(), R.string.vote_no_vote_permission, null);
        }
        return z;
    }

    @Override
    public void onLeftButtonClick() {
        AnalyticsHelper.logEvent(getLogMultiSignTag() + 0);
        finish();
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            AnalyticsHelper.logEvent(getLogMultiSignTag() + 0);
        }
        return super.onKeyDown(i, keyEvent);
    }
}
