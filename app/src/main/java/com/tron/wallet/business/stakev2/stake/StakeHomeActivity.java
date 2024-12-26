package com.tron.wallet.business.stakev2.stake;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.stakev2.stake.StakeHomeActivity;
import com.tron.wallet.business.stakev2.stake.StakeHomeContract;
import com.tron.wallet.business.stakev2.stake.pop.stakev1.StakeDetailListBottomPopup;
import com.tron.wallet.business.stakev2.stake.pop.unfreezing.UNStakeListBottomPopup;
import com.tron.wallet.business.stakev2.unstake.UnStakeV2Activity;
import com.tron.wallet.business.tronpower.management.ResourceManagementActivity;
import com.tron.wallet.business.tronpower.stake2.StakeTRX2Activity;
import com.tron.wallet.business.tronpower.unstake.UnStakeActivity;
import com.tron.wallet.business.vote.bean.FastAprBean;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.business.vote.component.VoteSelectSRActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.VoteAprCalculator;
import com.tron.wallet.databinding.AcStakeHomeBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class StakeHomeActivity extends BaseActivity<StakeHomePresenter, StakeHomeModel> implements StakeHomeContract.View {
    View ResourceBar;
    private GrpcAPI.AccountResourceMessage accountResourceMessage;
    View actionLayout;
    private AnalyticsType analyticsType;
    private AcStakeHomeBinding binding;
    TextView btnWithDraw;
    View cardNoStakeV2;
    View cardStakeV2;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private String controllerName;
    private boolean fromMultiSign;
    ImageView ivQuestionV1;
    View ivResourceGo;
    View ivStake;
    ImageView ivStakeV1Arrow;
    View ivVoteGo;
    private NumberFormat mNumberFormat;
    private ResourcesBean mResourcesBean;
    long mTotalV1;
    long mTotalV2;
    View morePlaceHolder;
    View noStakeLayout;
    private MultiSignPermissionData permissionData;
    View scrollView;
    private Wallet selectWallet;
    StakeDetailListBottomPopup stakeDetailListBottomPopup;
    StakeHeaderView stakeHeader;
    View stakeV2CardBottomView;
    private BasePopupView stakeV2PopupView;
    TextView tvAllStakeTrxCount;
    TextView tvAvailableVotingTps;
    TextView tvBandwidth;
    TextView tvBandwidthBar;
    TextView tvBandwidthTotal;
    TextView tvBandwidthTrx;
    TextView tvCurrentApr;
    TextView tvCurrentAprTitle;
    TextView tvEnergy;
    TextView tvEnergyBar;
    TextView tvEnergyTotal;
    TextView tvEnergyTrx;
    TextView tvMultiSignWarning;
    TextView tvNoStakeDes;
    TextView tvStakeLearnMore;
    TextView tvStakeV1Count;
    TextView tvStakeV1CountUnit;
    TextView tvStakeV2Count;
    TextView tvUnStakingCount;
    TextView tvVoteEntrance;
    TextView tvWithDrawAvailableTrx;
    View unFreezeV1View;
    View unStakeView;
    View unStakingCountArrow;
    View unStakingView;
    View unWithdrawView;
    private BasePopupView unstakePopupView;
    View voteLayout;
    boolean hasShowTheLearnMorePop = false;
    private boolean hasAnimation = true;
    private DataStatHelper.StatAction statAction = DataStatHelper.StatAction.Stake;

    public enum AnalyticsType {
        ALL,
        V1,
        V2,
        NO
    }

    public enum BarAnimationType {
        BANDWIDTH,
        ENERGY,
        ALL
    }

    public enum ClickType {
        MUL,
        UNFREEZING,
        WITHDRAW,
        CANCELUNSTAKE,
        STAKE_V1,
        GO_VOTE,
        KNOW,
        UN_STAKE,
        VOTE,
        RESOURCE,
        STAKE
    }

    public static void startWithCheckOwnerPermission(final Context context, final Protocol.Account account, final String str, final DataStatHelper.StatAction statAction) {
        OwnerPermissionCheckUtils.checkWithPopup(context, account, new int[]{R.string.stake_account_unactive, R.string.multistake}, new int[]{R.string.lack_of_stake_permission, R.string.multistake}, new Consumer() {
            @Override
            public final void accept(Object obj) {
                Void r5 = (Void) obj;
                StakeHomeActivity.start(context, account, false, statAction, str, null);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                Void r2 = (Void) obj;
                MultiSelectAddressActivity.start(context, MultiSourcePageEnum.StakeV2);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void start(Context context, Protocol.Account account, boolean z, DataStatHelper.StatAction statAction, String str, MultiSignPermissionData multiSignPermissionData) {
        Intent intent = new Intent(context, StakeHomeActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        context.startActivity(intent);
    }

    public static String getAboutStakeV2HelpUrl() {
        return IRequest.getAboutStakeV2HelpUrl();
    }

    @Override
    protected void setLayout() {
        AcStakeHomeBinding inflate = AcStakeHomeBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.stakeHeader = this.binding.stakeHeader;
        this.tvStakeLearnMore = this.binding.tvLearnStakeV2;
        this.tvAllStakeTrxCount = this.binding.allStakeTrxCount;
        this.tvStakeV2Count = this.binding.tvStakeV2Count;
        this.tvStakeV1Count = this.binding.tvStakeV1Count;
        this.tvStakeV1CountUnit = this.binding.tvStakeV1CountUnit;
        this.ivStakeV1Arrow = this.binding.v1StakeArrow;
        this.tvUnStakingCount = this.binding.unStakingCount;
        this.tvWithDrawAvailableTrx = this.binding.withDrawAvailableTrx;
        this.ResourceBar = this.binding.resourceBar;
        this.tvEnergyBar = this.binding.tvEnergyBar;
        this.tvBandwidthBar = this.binding.tvBandwidthBar;
        this.tvEnergyTrx = this.binding.energyTrx;
        this.tvBandwidthTrx = this.binding.bandwidthTrx;
        this.btnWithDraw = this.binding.tvV2CanWithdraw;
        this.tvAvailableVotingTps = this.binding.voteRights;
        this.tvVoteEntrance = this.binding.tvVoteEntrance;
        this.ivVoteGo = this.binding.ivVoteGo;
        this.ivResourceGo = this.binding.ivResourceGo;
        this.tvCurrentApr = this.binding.currentApr;
        this.tvCurrentAprTitle = this.binding.voteAprTitle;
        this.tvEnergy = this.binding.tvEnergy;
        this.tvEnergyTotal = this.binding.tvEnergyTotal;
        this.tvBandwidth = this.binding.tvBandwidth;
        this.tvBandwidthTotal = this.binding.tvBandwidthTotal;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
        this.unStakeView = this.binding.btnUnstake;
        this.unStakingView = this.binding.unstakingLayout;
        this.unStakingCountArrow = this.binding.v2UnstakeArrow;
        this.unWithdrawView = this.binding.withdrawLayout;
        this.stakeV2CardBottomView = this.binding.stakeV2LineBottom;
        this.unFreezeV1View = this.binding.tvV1Unfreeze;
        this.actionLayout = this.binding.llAction;
        this.noStakeLayout = this.binding.noStakeLayout.rlNoStake;
        this.scrollView = this.binding.scrollLayout;
        this.ivQuestionV1 = this.binding.ivQuestionV1;
        this.cardStakeV2 = this.binding.cardStakeV2;
        this.ivStake = this.binding.ivStake;
        this.cardNoStakeV2 = this.binding.cardNoStakeV2;
        this.voteLayout = this.binding.voteLayout;
        this.morePlaceHolder = this.binding.viewPlaceHolder;
        this.tvNoStakeDes = this.binding.noStakeLayout.tvJoinStakeDescription;
    }

    @Override
    protected void processData() {
        this.stakeHeader.setHeader(getString(R.string.stake), null, getString(R.string.multi_sign_title));
        this.stakeHeader.setStyleLight();
        this.stakeHeader.hideIconV2();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.mNumberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(6);
        parseIntentArgs();
        if (this.fromMultiSign) {
            this.stakeHeader.setTvMultiGone();
        }
        if (!this.fromMultiSign) {
            this.stakeHeader.showQuestion();
            this.stakeHeader.ivQuestion.setImageResource(R.mipmap.ic_question_tips_white);
        }
        ((StakeHomePresenter) this.mPresenter).setConfig(this.fromMultiSign, this.controllerAddress);
        ((StakeHomePresenter) this.mPresenter).start();
        this.stakeHeader.setOnHeaderClickListener(new StakeHeaderView.OnHeaderClickListener() {
            @Override
            public void onLeftClick() {
            }

            @Override
            public void onQuestion() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onQuestion(this);
            }

            @Override
            public void onRightClick() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onRightClick(this);
            }
        });
        this.stakeHeader.setOnHeaderClickListener(new StakeHeaderView.OnHeaderClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {
                MultiSelectAddressActivity.start(mContext, MultiSourcePageEnum.StakeV2);
                StakeHomeActivity stakeHomeActivity = StakeHomeActivity.this;
                stakeHomeActivity.doAnalyticsEvent(stakeHomeActivity.analyticsType, ClickType.MUL);
            }

            @Override
            public void onQuestion() {
                showV2PopWindow();
            }
        });
        if (SpAPI.THIS.getHasShowStakeHomePop() == 0) {
            showV2PopWindow();
            SpAPI.THIS.setHasShowStakeHomePop();
            AnalyticsHelper.logEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_POP_FIRST_SHOW);
        }
        initV1Click();
        initV2CLick();
        TouchDelegateUtils.expandViewTouchDelegate(this.stakeHeader.ivQuestion, 5, 10, 10, 10);
    }

    private void initV2CLick() {
        this.btnWithDraw.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                withdrawExpireUnfreeze();
            }
        });
    }

    private void initV1Click() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (BigDecimalUtils.MoreThan((Object) BigDecimalUtils.toBigDecimal(Long.valueOf(mTotalV1)), (Object) 0)) {
                    StakeHomeActivity stakeHomeActivity = StakeHomeActivity.this;
                    stakeHomeActivity.stakeDetailListBottomPopup = StakeDetailListBottomPopup.showPopup(stakeHomeActivity.getIContext(), fromMultiSign, null, controllerAccount, controllerAddress, permissionData);
                }
                StakeHomeActivity stakeHomeActivity2 = StakeHomeActivity.this;
                stakeHomeActivity2.doAnalyticsEvent(stakeHomeActivity2.analyticsType, ClickType.UN_STAKE);
            }
        };
        this.ivStakeV1Arrow.setOnClickListener(noDoubleClickListener);
        this.tvStakeV1Count.setOnClickListener(noDoubleClickListener);
        this.tvStakeV1CountUnit.setOnClickListener(noDoubleClickListener);
        this.unFreezeV1View.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                unStakeV1();
            }
        });
    }

    public class fun6 extends NoDoubleClickListener2 {
        fun6() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.btn_stake:
                case R.id.btn_stake_no_stake:
                case R.id.btn_stake_no_v2:
                    stakeV2();
                    StakeHomeActivity stakeHomeActivity = StakeHomeActivity.this;
                    stakeHomeActivity.doAnalyticsEvent(stakeHomeActivity.analyticsType, ClickType.STAKE);
                    return;
                case R.id.btn_unstake:
                    if ((BigDecimalUtils.MoreThan((Object) BigDecimalUtils.toBigDecimal(Long.valueOf(mTotalV2)), (Object) 0) && BigDecimalUtils.MoreThan((Object) BigDecimalUtils.toBigDecimal(Long.valueOf(mTotalV1)), (Object) 0)) || controllerAccount == null) {
                        showUnFreezePopWindow();
                    } else {
                        unStakeV2();
                    }
                    StakeHomeActivity stakeHomeActivity2 = StakeHomeActivity.this;
                    stakeHomeActivity2.doAnalyticsEvent(stakeHomeActivity2.analyticsType, ClickType.UN_STAKE);
                    return;
                case R.id.iv_question_v1:
                    PopupWindowUtil.showPopupText(mContext, StringTronUtil.getResString(R.string.no_longer_support_stake), ivQuestionV1, false);
                    return;
                case R.id.learn_more_no_stake:
                case R.id.no_stake_v2_learn_more:
                case R.id.tv_learn_stake_v2:
                    jumpToLearnArticle();
                    StakeHomeActivity stakeHomeActivity3 = StakeHomeActivity.this;
                    stakeHomeActivity3.doAnalyticsEvent(stakeHomeActivity3.analyticsType, ClickType.KNOW);
                    return;
                case R.id.resource_layout:
                    if (fromMultiSign) {
                        return;
                    }
                    jumpToResourcePage();
                    StakeHomeActivity stakeHomeActivity4 = StakeHomeActivity.this;
                    stakeHomeActivity4.doAnalyticsEvent(stakeHomeActivity4.analyticsType, ClickType.RESOURCE);
                    return;
                case R.id.tv_vote_entrance:
                    if (fromMultiSign) {
                        return;
                    }
                    OwnerPermissionCheckUtils.checkWithPopup(mContext, controllerAccount, new int[]{R.string.vote_not_activated_popup, R.string.vote_by_multi_sign}, new int[]{R.string.vote_no_owner_permission, R.string.vote_by_multi_sign}, new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            StakeHomeActivity.6.this.lambda$onNoDoubleClick$0((Void) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    }, new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            StakeHomeActivity.6.this.lambda$onNoDoubleClick$1((Void) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                    StakeHomeActivity stakeHomeActivity5 = StakeHomeActivity.this;
                    stakeHomeActivity5.doAnalyticsEvent(stakeHomeActivity5.analyticsType, ClickType.GO_VOTE);
                    return;
                case R.id.unstake_layout:
                    UNStakeListBottomPopup.showPopup(getIContext(), new UNStakeListBottomPopup.OnClickListener() {
                        @Override
                        public void onClick(Wallet wallet) {
                            cancelUnStake();
                        }
                    }, null, mResourcesBean.getUnFreezingList());
                    StakeHomeActivity stakeHomeActivity6 = StakeHomeActivity.this;
                    stakeHomeActivity6.doAnalyticsEvent(stakeHomeActivity6.analyticsType, ClickType.UNFREEZING);
                    return;
                case R.id.vote_layout:
                    if (fromMultiSign) {
                        return;
                    }
                    VoteMainActivity.startWithCheckOwnerPermission(mContext, controllerAccount, controllerAddress);
                    StakeHomeActivity stakeHomeActivity7 = StakeHomeActivity.this;
                    stakeHomeActivity7.doAnalyticsEvent(stakeHomeActivity7.analyticsType, ClickType.VOTE);
                    return;
                default:
                    return;
            }
        }

        public void lambda$onNoDoubleClick$0(Void r11) {
            DataStatHelper.StatAction statAction;
            try {
                Context iContext = getIContext();
                Protocol.Account account = controllerAccount;
                boolean z = fromMultiSign;
                String str = controllerAddress;
                String str2 = controllerName;
                MultiSignPermissionData multiSignPermissionData = permissionData;
                long votingTps = mResourcesBean.getVotingTps();
                if (statAction == DataStatHelper.StatAction.FinanceStake) {
                    statAction = DataStatHelper.StatAction.FinanceVote;
                } else {
                    statAction = DataStatHelper.StatAction.Vote;
                }
                VoteSelectSRActivity.start(iContext, account, z, str, str2, multiSignPermissionData, 5, votingTps, statAction);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }

        public void lambda$onNoDoubleClick$1(Void r2) {
            MultiSelectAddressActivity.start(mContext, MultiSourcePageEnum.Vote);
        }
    }

    public void setClick() {
        6 r0 = new fun6();
        this.binding.unstakeLayout.setOnClickListener(r0);
        this.binding.btnStake.setOnClickListener(r0);
        this.binding.btnUnstake.setOnClickListener(r0);
        this.binding.tvVoteEntrance.setOnClickListener(r0);
        this.binding.voteLayout.setOnClickListener(r0);
        this.binding.resourceLayout.setOnClickListener(r0);
        this.binding.tvLearnStakeV2.setOnClickListener(r0);
        this.binding.ivQuestionV1.setOnClickListener(r0);
        this.binding.noStakeV2LearnMore.setOnClickListener(r0);
        this.binding.noStakeLayout.learnMoreNoStake.setOnClickListener(r0);
        this.binding.noStakeLayout.btnStakeNoStake.setOnClickListener(r0);
        this.binding.btnStakeNoV2.setOnClickListener(r0);
    }

    @Override
    public void updateUI(ResourcesBean resourcesBean, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        this.controllerAccount = account;
        this.accountResourceMessage = accountResourceMessage;
        this.mResourcesBean = resourcesBean;
        analyticsType();
        this.mTotalV1 = resourcesBean.getEnergyFromSelfTrxV1() + resourcesBean.getBandwidthFromSelfTrxV1() + resourcesBean.getEnergyForOthersV1() + resourcesBean.getBandwidthForOthersV1();
        this.mTotalV2 = resourcesBean.getStakingV2TotalTrx();
        if (StringTronUtil.isEmpty(SpAPI.THIS.getWalletStakedMap().get(this.controllerAddress)) && BigDecimalUtils.equalsZero((Number) Long.valueOf(this.mTotalV1)) && BigDecimalUtils.equalsZero((Number) Long.valueOf(this.mTotalV2)) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(resourcesBean.getUnFreezingTrx()))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(resourcesBean.getWithDrawAvailableTrx())))) {
            showNoStakedView();
            AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_NO_PAGE_SHOW, this.fromMultiSign);
            ((StakeHomePresenter) this.mPresenter).getMaxVoteApr();
            return;
        }
        HiddenNoStakedView();
        SpAPI.THIS.setWalletStaked(this.controllerAddress);
        if (BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV2))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(resourcesBean.getWithDrawAvailableTrx()))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(resourcesBean.getUnFreezingTrx())))) {
            this.cardStakeV2.setVisibility(View.GONE);
            this.ivStake.setVisibility(View.GONE);
            this.cardNoStakeV2.setVisibility(View.VISIBLE);
        } else {
            this.cardStakeV2.setVisibility(View.VISIBLE);
            this.ivStake.setVisibility(View.VISIBLE);
            this.cardNoStakeV2.setVisibility(View.GONE);
            updateV2ResourceBar(resourcesBean);
        }
        if (BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV2))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(resourcesBean.getUnFreezingTrx()))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(resourcesBean.getWithDrawAvailableTrx())))) {
            this.actionLayout.setVisibility(View.GONE);
        } else {
            this.actionLayout.setVisibility(View.VISIBLE);
        }
        if (BigDecimalUtils.Equal((Object) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV1)), (Object) 0)) {
            this.ivStakeV1Arrow.setVisibility(View.GONE);
        } else {
            this.ivStakeV1Arrow.setVisibility(View.VISIBLE);
        }
        if (!BigDecimalUtils.Equal((Object) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV1)), (Object) 0) && BigDecimalUtils.Equal((Object) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV2)), (Object) 0)) {
            this.unFreezeV1View.setVisibility(View.VISIBLE);
        } else {
            this.unFreezeV1View.setVisibility(View.GONE);
        }
        this.tvAllStakeTrxCount.setText(this.mNumberFormat.format(resourcesBean.getStakedTrx()));
        this.tvStakeV1Count.setText(this.mNumberFormat.format(this.mTotalV1));
        this.tvStakeV2Count.setText(this.mNumberFormat.format(this.mTotalV2));
        if (BigDecimalUtils.MoreThan((Object) Long.valueOf(resourcesBean.getUnFreezingTrx()), (Object) 0)) {
            this.unStakingView.setVisibility(View.VISIBLE);
            this.unStakingCountArrow.setVisibility(View.VISIBLE);
            this.tvUnStakingCount.setText(this.mNumberFormat.format(resourcesBean.getUnFreezingTrx()));
        } else {
            this.unStakingView.setVisibility(View.GONE);
            this.unStakingCountArrow.setVisibility(View.GONE);
        }
        if (resourcesBean.getWithDrawAvailableTrx() > 0) {
            this.unWithdrawView.setVisibility(View.VISIBLE);
            this.tvWithDrawAvailableTrx.setText(this.mNumberFormat.format(resourcesBean.getWithDrawAvailableTrx()));
        } else {
            this.unWithdrawView.setVisibility(View.GONE);
        }
        if (BigDecimalUtils.equalsZero((Number) Long.valueOf(resourcesBean.getWithDrawAvailableTrx())) && BigDecimalUtils.equalsZero((Number) Long.valueOf(resourcesBean.getUnFreezingTrx()))) {
            this.stakeV2CardBottomView.setVisibility(View.GONE);
        } else {
            this.stakeV2CardBottomView.setVisibility(View.VISIBLE);
        }
        updateVoteUI(resourcesBean);
        updateResourceUI(resourcesBean);
        if (this.analyticsType == AnalyticsType.ALL) {
            AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_SHOW, this.fromMultiSign);
        } else if (this.analyticsType == AnalyticsType.V1) {
            AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_PAGE_SHOW, this.fromMultiSign);
        } else if (this.analyticsType == AnalyticsType.V2) {
            AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_SHOW, this.fromMultiSign);
        }
    }

    private void showNoStakedView() {
        this.scrollView.setVisibility(View.GONE);
        this.actionLayout.setVisibility(View.GONE);
        this.noStakeLayout.setVisibility(View.VISIBLE);
    }

    private void HiddenNoStakedView() {
        this.scrollView.setVisibility(View.VISIBLE);
        this.actionLayout.setVisibility(View.VISIBLE);
        this.noStakeLayout.setVisibility(View.GONE);
    }

    @Override
    public void updateVoteApr(StakeHomeAprBean stakeHomeAprBean) {
        FastAprBean calculateApr = VoteAprCalculator.calculateApr(stakeHomeAprBean.getMyVotes().getData(), stakeHomeAprBean.getTop3Witness().getData());
        if (stakeHomeAprBean.getMyVotes() != null && stakeHomeAprBean.getMyVotes().getData().size() > 0) {
            this.tvCurrentApr.setText(String.format("%s%%", VoteAprCalculator.formatAprPercent(calculateApr.getCurrentApr())));
            this.tvCurrentAprTitle.setText(getResources().getString(R.string.stake_home_current_apr));
            this.tvCurrentAprTitle.setVisibility(View.VISIBLE);
            return;
        }
        this.tvCurrentApr.setText(String.format("%s%%", VoteAprCalculator.formatAprPercent(calculateApr.getFastApr())));
        this.tvCurrentAprTitle.setText(getResources().getString(R.string.vote_highest_apr));
        this.tvCurrentAprTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyPage() {
        this.scrollView.setVisibility(View.VISIBLE);
        this.btnWithDraw.setVisibility(View.GONE);
        this.ivStakeV1Arrow.setVisibility(View.GONE);
        this.unStakingCountArrow.setVisibility(View.GONE);
    }

    @Override
    public void hiddenTheStakeV1Pop() {
        StakeDetailListBottomPopup stakeDetailListBottomPopup = this.stakeDetailListBottomPopup;
        if (stakeDetailListBottomPopup == null || !stakeDetailListBottomPopup.isShow()) {
            return;
        }
        this.stakeDetailListBottomPopup.dismiss();
    }

    private void updateVoteUI(ResourcesBean resourcesBean) {
        this.voteLayout.setVisibility(View.VISIBLE);
        this.morePlaceHolder.setVisibility(View.GONE);
        if (BigDecimalUtils.equalsZero((Number) Long.valueOf(resourcesBean.getAvailableVotingTps())) || this.fromMultiSign) {
            this.tvVoteEntrance.setVisibility(View.GONE);
        } else {
            this.tvVoteEntrance.setVisibility(View.VISIBLE);
        }
        this.ivVoteGo.setVisibility(this.fromMultiSign ? View.GONE : View.VISIBLE);
        this.ivResourceGo.setVisibility(this.fromMultiSign ? View.GONE : View.VISIBLE);
        this.tvAvailableVotingTps.setText(this.mNumberFormat.format(resourcesBean.getAvailableVotingTps()));
    }

    private void updateResourceUI(ResourcesBean resourcesBean) {
        this.tvEnergy.setText(NumUtils.numFormatToK(resourcesBean.getEnergyUsable()));
        TextView textView = this.tvEnergyTotal;
        textView.setText("/" + NumUtils.numFormatToK(resourcesBean.getEnergyTotal()));
        this.tvBandwidth.setText(NumUtils.numFormatToK(resourcesBean.getBandwidthUsable()));
        TextView textView2 = this.tvBandwidthTotal;
        textView2.setText("/" + NumUtils.numFormatToK(resourcesBean.getBandwidthTotal()));
    }

    private void parseIntentArgs() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.fromMultiSign = intent.getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.hasShowTheLearnMorePop = intent.getBooleanExtra(CommonBundleKeys.HAS_SHOW_THE_LEARN_MORE_POP, false);
        this.controllerAddress = intent.getStringExtra("key_controller_address");
        this.permissionData = (MultiSignPermissionData) intent.getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
        this.selectWallet = WalletUtils.getSelectedPublicWallet();
        if (!this.fromMultiSign && TextUtils.isEmpty(this.controllerAddress)) {
            Wallet wallet = this.selectWallet;
            this.controllerAddress = wallet != null ? wallet.getAddress() : "";
            this.tvMultiSignWarning.setVisibility(View.GONE);
        } else if (this.fromMultiSign) {
            Wallet walletForAddress = WalletUtils.getWalletForAddress(this.controllerAddress);
            if (walletForAddress != null) {
                this.controllerName = walletForAddress.getWalletName();
            }
            final String str = this.controllerAddress;
            if (!TextUtils.isEmpty(this.controllerName)) {
                str = String.format("%s (%s)", this.controllerName, str);
            }
            this.tvMultiSignWarning.setText(getString(R.string.multi_controller_tips, new Object[]{str}));
            this.tvMultiSignWarning.setTextColor(getResources().getColor(R.color.blue_9d));
            this.tvMultiSignWarning.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$parseIntentArgs$2(str);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
            this.stakeHeader.setHeader(getString(R.string.multi_sign_title), "", "");
        }
    }

    public void lambda$parseIntentArgs$2(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.white)));
    }

    @Override
    public void updateNoStakeApr(String str) {
        this.tvNoStakeDes.setText(SpannableUtils.getTextColorSpannable(getString(R.string.stake_v2_no_stake_desc, new Object[]{str}), getString(R.string.stake_v2_no_stake_desc2, new Object[]{str}), getResources().getColor(R.color.white), true));
    }

    void showV2PopWindow() {
        BasePopupView asCustom = new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this) {
            @Override
            public int getImplLayoutId() {
                return R.layout.stake_v2_popup_view;
            }

            @Override
            public void onCreate() {
                super.onCreate();
                ((Button) findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_POP_MORE_CLICK, fromMultiSign);
                    }
                });
                ((Button) findViewById(R.id.btn_confirm)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jumpToLearnArticle();
                        dismiss();
                        AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_POP_KNOW_CLICK, fromMultiSign);
                    }
                });
            }
        });
        this.stakeV2PopupView = asCustom;
        asCustom.show();
    }

    void showUnFreezePopWindow() {
        BasePopupView asCustom = new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new CenterPopupView(this) {
            @Override
            public int getImplLayoutId() {
                return R.layout.stake_v2_unfreeze_popup_view;
            }

            @Override
            public void onCreate() {
                ImageView imageView = (ImageView) findViewById(R.id.iv_common_right);
                TextView textView = (TextView) findViewById(R.id.tv_unfreeze_v2_count);
                View findViewById = findViewById(R.id.rl_unfreeze_v2);
                View findViewById2 = findViewById(R.id.rl_unfreeze_v1);
                TextView textView2 = (TextView) findViewById(R.id.tv_unfreeze_v1_count);
                if (mTotalV2 > 0) {
                    textView.setText(mNumberFormat.format(mTotalV2));
                }
                if (mTotalV1 > 0) {
                    textView2.setText(mNumberFormat.format(mTotalV1));
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                findViewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unStakeV2();
                        dismiss();
                        AnalyticsHelper.logEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_POP_V2_CLICK);
                    }
                });
                findViewById2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unStakeV1();
                        dismiss();
                        AnalyticsHelper.logEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_POP_V1_CLICK);
                    }
                });
            }
        });
        this.unstakePopupView = asCustom;
        asCustom.show();
        AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_POP_SHOW, this.fromMultiSign);
    }

    public void unStakeV1() {
        if (!this.fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(this.controllerAccount)) {
            OwnerPermissionCheckUtils.showMultiSignDialog(this.mContext, R.string.unstake_lack_owner_permission, R.string.unstake_multi_sign, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$unStakeV1$3((Void) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            return;
        }
        MultiSignPermissionData multiSignPermissionData = this.permissionData;
        if (multiSignPermissionData != null && !multiSignPermissionData.isUnStakePermission()) {
            toast(getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.trans_type_stake_v1)));
        } else if (this.fromMultiSign) {
            UnStakeActivity.startFromMultiSign(this.mContext, this.controllerAccount, this.controllerAddress, "", this.permissionData);
        } else {
            UnStakeActivity.start(this.mContext, this.controllerAccount);
        }
    }

    public void lambda$unStakeV1$3(Void r2) {
        MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.StakeV2);
    }

    public void unStakeV2() {
        if (!this.fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(this.controllerAccount)) {
            OwnerPermissionCheckUtils.showMultiSignDialog(this.mContext, R.string.unstake_lack_owner_permission, R.string.unstake_multi_sign, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$unStakeV2$4((Void) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            return;
        }
        MultiSignPermissionData multiSignPermissionData = this.permissionData;
        if (multiSignPermissionData != null && !multiSignPermissionData.isUnfreezeBalanceV2Permission()) {
            toast(getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.unstake_2)));
        } else if (this.fromMultiSign) {
            UnStakeV2Activity.startFromMultiSign(this, this.controllerAddress, this.controllerAccount, this.accountResourceMessage);
        } else {
            UnStakeV2Activity.start(this, this.controllerAccount, this.accountResourceMessage);
        }
    }

    public void lambda$unStakeV2$4(Void r2) {
        MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.StakeV2);
    }

    public void stakeV2() {
        if (!this.fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(this.controllerAccount)) {
            OwnerPermissionCheckUtils.showMultiSignDialog(this.mContext, R.string.lack_of_stake_permission, R.string.multistake, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$stakeV2$5((Void) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            return;
        }
        MultiSignPermissionData multiSignPermissionData = this.permissionData;
        if (multiSignPermissionData != null && !multiSignPermissionData.isFreezeBalanceV2Permission()) {
            toast(getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.stake_v2)));
        } else if (this.fromMultiSign) {
            StakeTRX2Activity.start(this, this.controllerAddress, this.controllerName, this.controllerAccount, this.statAction);
        } else {
            StakeTRX2Activity.startWithCheckOwnerPermission(this, true, this.controllerAccount, this.statAction, this.controllerAddress);
        }
    }

    public void lambda$stakeV2$5(Void r2) {
        MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.StakeV2);
    }

    private void updateV2ResourceBar(ResourcesBean resourcesBean) {
        LinearLayout.LayoutParams layoutParams;
        LinearLayout.LayoutParams layoutParams2;
        long energyFromSelfTrxV2 = resourcesBean.getEnergyFromSelfTrxV2() + resourcesBean.getEnergyDeletedToOthersTrxV2();
        long bandwidthFromSelfTrxV2 = resourcesBean.getBandwidthFromSelfTrxV2() + resourcesBean.getBandwidthDeletedToOthersTrxV2();
        this.tvEnergyTrx.setText(this.mNumberFormat.format(energyFromSelfTrxV2));
        this.tvBandwidthTrx.setText(this.mNumberFormat.format(bandwidthFromSelfTrxV2));
        if (BigDecimalUtils.equalsZero((Number) Long.valueOf(energyFromSelfTrxV2)) && BigDecimalUtils.equalsZero((Number) Long.valueOf(bandwidthFromSelfTrxV2))) {
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(UIUtils.dip2px(0.0f), UIUtils.dip2px(16.0f));
            layoutParams3.weight = 5.0f;
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(UIUtils.dip2px(0.0f), UIUtils.dip2px(16.0f));
            layoutParams4.weight = 5.0f;
            this.tvEnergyBar.setLayoutParams(layoutParams3);
            this.tvBandwidthBar.setLayoutParams(layoutParams4);
            this.tvEnergyBar.setBackgroundResource(R.drawable.bg_stake_home_energy_bar_empty);
            this.tvBandwidthBar.setBackgroundResource(R.drawable.bg_stake_home_band_bar_empty);
            this.tvEnergyBar.setVisibility(View.VISIBLE);
            this.tvBandwidthBar.setVisibility(View.VISIBLE);
        } else if (BigDecimalUtils.equalsZero((Number) Long.valueOf(energyFromSelfTrxV2)) || BigDecimalUtils.equalsZero((Number) Long.valueOf(bandwidthFromSelfTrxV2))) {
            if (BigDecimalUtils.equalsZero((Number) Long.valueOf(bandwidthFromSelfTrxV2))) {
                this.tvBandwidthBar.setLayoutParams(new LinearLayout.LayoutParams(-2, UIUtils.dip2px(16.0f)));
                this.tvBandwidthBar.setBackgroundResource(R.drawable.bg_stake_home_band_bar_empty);
                this.tvEnergyBar.setBackgroundResource(R.drawable.bg_stake_home_energy_bar);
                if (this.hasAnimation) {
                    this.tvBandwidthBar.setVisibility(View.VISIBLE);
                    this.tvBandwidthBar.post(new Runnable() {
                        @Override
                        public final void run() {
                            lambda$updateV2ResourceBar$6();
                        }
                    });
                    return;
                }
                this.tvBandwidthBar.setVisibility(View.VISIBLE);
                return;
            }
            this.tvEnergyBar.setLayoutParams(new LinearLayout.LayoutParams(-2, UIUtils.dip2px(16.0f)));
            this.tvEnergyBar.setBackgroundResource(R.drawable.bg_stake_home_energy_bar_empty);
            this.tvBandwidthBar.setBackgroundResource(R.drawable.bg_stake_home_band_bar);
            if (this.hasAnimation) {
                this.tvEnergyBar.setVisibility(View.VISIBLE);
                this.tvEnergyBar.post(new Runnable() {
                    @Override
                    public final void run() {
                        lambda$updateV2ResourceBar$7();
                    }
                });
                return;
            }
            this.tvEnergyBar.setVisibility(View.VISIBLE);
        } else {
            int screenWidth = UIUtils.getScreenWidth(this) - UIUtils.dip2px(60.0f);
            TextPaint paint = this.tvBandwidthBar.getPaint();
            TextPaint paint2 = this.tvEnergyBar.getPaint();
            int measureText = (int) paint.measureText(getString(R.string.bandwidth));
            int measureText2 = (int) paint2.measureText(getString(R.string.energy));
            double d = energyFromSelfTrxV2;
            double d2 = energyFromSelfTrxV2 + bandwidthFromSelfTrxV2;
            double d3 = d / d2;
            if (BigDecimalUtils.MoreThan(Double.valueOf(d3), Double.valueOf(0.9d))) {
                d3 = 0.9d;
            }
            if (BigDecimalUtils.lessThanOrEqual(Double.valueOf(d3), Double.valueOf(0.1d))) {
                d3 = 0.1d;
            }
            double d4 = screenWidth;
            if (BigDecimalUtils.LessThan(Double.valueOf(BigDecimalUtils.mul(d4, d3)), Integer.valueOf(measureText2 + UIUtils.dip2px(11.0f)))) {
                layoutParams = new LinearLayout.LayoutParams(-2, UIUtils.dip2px(16.0f));
            } else {
                layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(0.0f), UIUtils.dip2px(16.0f));
                layoutParams.weight = Float.parseFloat(String.valueOf(d3)) * 10.0f;
            }
            double d5 = bandwidthFromSelfTrxV2 / d2;
            double d6 = BigDecimalUtils.MoreThan(Double.valueOf(d5), Double.valueOf(0.9d)) ? 0.9d : d5;
            double d7 = BigDecimalUtils.lessThanOrEqual(Double.valueOf(d6), Double.valueOf(0.1d)) ? 0.1d : d6;
            if (BigDecimalUtils.LessThan(Double.valueOf(BigDecimalUtils.mul(d4, d7)), Integer.valueOf(measureText))) {
                layoutParams2 = new LinearLayout.LayoutParams(-2, UIUtils.dip2px(16.0f));
            } else {
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(UIUtils.dip2px(0.0f), UIUtils.dip2px(16.0f));
                layoutParams5.weight = Float.parseFloat(String.valueOf(d7)) * 10.0f;
                layoutParams2 = layoutParams5;
            }
            this.tvEnergyBar.setLayoutParams(layoutParams);
            this.tvBandwidthBar.setLayoutParams(layoutParams2);
            this.tvEnergyBar.setBackgroundResource(R.drawable.bg_stake_home_energy_bar);
            this.tvBandwidthBar.setBackgroundResource(R.drawable.bg_stake_home_band_bar);
            if (this.hasAnimation) {
                this.tvEnergyBar.setVisibility(View.INVISIBLE);
                this.tvBandwidthBar.setVisibility(View.INVISIBLE);
                this.tvEnergyBar.post(new Runnable() {
                    @Override
                    public final void run() {
                        lambda$updateV2ResourceBar$8();
                    }
                });
                return;
            }
            this.tvEnergyBar.setVisibility(View.VISIBLE);
            this.tvBandwidthBar.setVisibility(View.VISIBLE);
        }
    }

    public void lambda$updateV2ResourceBar$6() {
        startBarAnimation(BarAnimationType.ENERGY);
    }

    public void lambda$updateV2ResourceBar$7() {
        startBarAnimation(BarAnimationType.BANDWIDTH);
    }

    public void lambda$updateV2ResourceBar$8() {
        startBarAnimation(BarAnimationType.ALL);
    }

    public void cancelUnStake() {
        if (!TronConfig.hasNet) {
            toast(getString(R.string.net_error));
        } else if (!this.fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(this.controllerAccount)) {
            OwnerPermissionCheckUtils.showMultiSignDialog(this.mContext, R.string.cancel_unstaking_no_permission, R.string.cancel_unstaking_multisig, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$cancelUnStake$9((Void) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        } else {
            MultiSignPermissionData multiSignPermissionData = this.permissionData;
            if (multiSignPermissionData != null && !multiSignPermissionData.isCancelAllUnfreezePermission()) {
                toast(getResources().getString(R.string.cancel_unstaking_account_no_permission));
                return;
            }
            ((StakeHomePresenter) this.mPresenter).cancelAllUnsake(this.mResourcesBean.getWithDrawAvailableTrx(), this.fromMultiSign, this.mResourcesBean);
            doAnalyticsEvent(this.analyticsType, ClickType.CANCELUNSTAKE);
        }
    }

    public void lambda$cancelUnStake$9(Void r2) {
        MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.StakeV2);
    }

    public void withdrawExpireUnfreeze() {
        if (!TronConfig.hasNet) {
            toast(getString(R.string.net_error));
        } else if (this.mResourcesBean.getWithDrawAvailableTrx() > 0) {
            if (!this.fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(this.controllerAccount)) {
                OwnerPermissionCheckUtils.showMultiSignDialog(this.mContext, R.string.lack_of_withdraw_permission, R.string.multi_withdraw, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$withdrawExpireUnfreeze$10((Void) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
                return;
            }
            MultiSignPermissionData multiSignPermissionData = this.permissionData;
            if (multiSignPermissionData != null && !multiSignPermissionData.isWithdrawExpireUnfreezePermission()) {
                toast(getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.withdraw_expire_unfreeze)));
                return;
            }
            ((StakeHomePresenter) this.mPresenter).withdrawExpireUnfreeze(this.mResourcesBean.getWithDrawAvailableTrx(), this.fromMultiSign);
            doAnalyticsEvent(this.analyticsType, ClickType.WITHDRAW);
        }
    }

    public void lambda$withdrawExpireUnfreeze$10(Void r2) {
        MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.StakeV2);
    }

    public void jumpToResourcePage() {
        ResourceManagementActivity.start(this.mContext, 0);
    }

    public void jumpToLearnArticle() {
        CommonWebActivityV3.start(this.mContext, getAboutStakeV2HelpUrl(), getString(R.string.tutorial), -2, false);
    }

    private void startBarAnimation(final BarAnimationType barAnimationType) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (barAnimationType == BarAnimationType.ALL || barAnimationType == BarAnimationType.ENERGY) {
                    tvEnergyBar.setVisibility(View.VISIBLE);
                    tvEnergyBar.setPivotX(0.0f);
                    tvEnergyBar.setScaleX(floatValue);
                }
                if (barAnimationType == BarAnimationType.ALL || barAnimationType == BarAnimationType.BANDWIDTH) {
                    tvBandwidthBar.setVisibility(View.VISIBLE);
                    tvBandwidthBar.setPivotX(tvBandwidthBar.getMeasuredWidth());
                    tvBandwidthBar.setScaleX(floatValue);
                }
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (barAnimationType == BarAnimationType.ALL || barAnimationType == BarAnimationType.ENERGY) {
                    tvEnergyBar.setScaleX(1.0f);
                }
                if (barAnimationType == BarAnimationType.ALL || barAnimationType == BarAnimationType.BANDWIDTH) {
                    tvBandwidthBar.setScaleX(1.0f);
                }
            }
        });
        ofFloat.setDuration(300L);
        ofFloat.start();
        this.hasAnimation = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.controllerAccount != null) {
            ((StakeHomePresenter) this.mPresenter).loadData();
            ((StakeHomePresenter) this.mPresenter).getMaxVoteAprOrCurrentApr();
        }
    }

    private void analyticsType() {
        if (StringTronUtil.isEmpty(SpAPI.THIS.getWalletStakedMap().get(this.controllerAddress)) && BigDecimalUtils.equalsZero((Number) Long.valueOf(this.mTotalV1)) && BigDecimalUtils.equalsZero((Number) Long.valueOf(this.mTotalV2)) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getUnFreezingTrx()))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getWithDrawAvailableTrx())))) {
            this.analyticsType = AnalyticsType.NO;
        } else if (!BigDecimalUtils.equalsZero((Number) Long.valueOf(this.mTotalV1)) && (!BigDecimalUtils.equalsZero((Number) Long.valueOf(this.mTotalV2)) || !BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getUnFreezingTrx()))) || !BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getWithDrawAvailableTrx()))))) {
            this.analyticsType = AnalyticsType.ALL;
        } else if (!BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV1))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV2))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getWithDrawAvailableTrx()))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getUnFreezingTrx())))) {
            this.analyticsType = AnalyticsType.V1;
        } else if (BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV1)))) {
            if (BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mTotalV2))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getWithDrawAvailableTrx()))) && BigDecimalUtils.equalsZero((Number) BigDecimalUtils.toBigDecimal(Long.valueOf(this.mResourcesBean.getUnFreezingTrx())))) {
                return;
            }
            this.analyticsType = AnalyticsType.V2;
        }
    }

    public static class fun11 {
        static final int[] $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType;

        static {
            int[] iArr = new int[ClickType.values().length];
            $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType = iArr;
            try {
                iArr[ClickType.MUL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.UNFREEZING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.WITHDRAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.STAKE_V1.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.GO_VOTE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.KNOW.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.UN_STAKE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.VOTE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.RESOURCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[ClickType.STAKE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public void doAnalyticsEvent(AnalyticsType analyticsType, ClickType clickType) {
        switch (fun11.$SwitchMap$com$tron$wallet$business$stakev2$stake$StakeHomeActivity$ClickType[clickType.ordinal()]) {
            case 1:
                if (analyticsType == AnalyticsType.NO) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_NO_PAGE_MUL_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_MUl_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_MUl_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 2:
                if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_UNFREEZEING_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_UNFREEZEING_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 3:
                if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_WITHDRAW_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_WITHDRAW_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 4:
            default:
                return;
            case 5:
                if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_GOVOTE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V1) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_PAGE_GOVOTE_CLICK_, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_GOVOTE_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 6:
                if (analyticsType == AnalyticsType.NO) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_NO_PAGE_KNOWSTAKE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_KNOW_STAKE_V2_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_KNOW_STAKE_V2_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 7:
                if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_UNSTAKE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V1) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_PAGE_UNSTAKE_CLICK_, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_UNSTAKE_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 8:
                if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_VOTE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V1) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_PAGE_VOTE_CLICK_, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_VOTE_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 9:
                if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_RESOURCE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V1) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_PAGE_RESOURCE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_RESOURCE_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
            case 10:
                if (analyticsType == AnalyticsType.NO) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_NO_PAGE_QUIDESTAKE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.ALL) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V1_V2_PAGE_STAKE_CLICK, this.fromMultiSign);
                    return;
                } else if (analyticsType == AnalyticsType.V2) {
                    AnalyticsHelper.StakeHomePage.logMultiEvent(AnalyticsHelper.StakeHomePage.STAKE_V2_PAGE_STAKE_CLICK, this.fromMultiSign);
                    return;
                } else {
                    return;
                }
        }
    }
}
