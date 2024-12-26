package com.tron.wallet.business.tronpower.management;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Activity;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tronpower.management.ResourceManagementActivity;
import com.tron.wallet.business.tronpower.management.ResourceManagementContract;
import com.tron.wallet.business.tronpower.stake.StakeTRXActivity;
import com.tron.wallet.business.tronpower.unstake.UnStakeActivity;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.common.components.CircularProgressView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.WhiteEnergyProgressView;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcResourcesBinding;
import com.tron.wallet.databinding.PopupStakedDetailsBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
public class ResourceManagementActivity extends BaseActivity<ResourceManagementPresenter, ResourceManagementModel> implements ResourceManagementContract.View {
    private static String FLAG_SHOW_INTRO_POPUP = "showIntroPopup";
    View bandwidthTips;
    private AcResourcesBinding binding;
    TextView btStake;
    TextView btUnStake;
    TextView btVote;
    CircularProgressView cpBandwidth;
    CircularProgressView cpEnergy;
    View energyTips;
    View ivBack;
    View ivHelp;
    View ivTrxHelp;
    View ivVotesHelp;
    View llContent;
    View llContentInner;
    View llVote;
    private NumberFormat numberFormat;
    PtrHTFrameLayout ptrLayout;
    private ResourcesBean resourcesBean;
    TextView tvBandwidth;
    TextView tvBandwidthTotal;
    TextView tvEnergy;
    TextView tvEnergyTotal;
    View tvStakedDetails;
    TextView tvTrxBalance;
    TextView tvVotingApr;
    TextView tvVotingTps;

    public static void start(Context context) {
        start(context, true, 0);
    }

    public static void start(Context context, int i) {
        start(context, true, i);
    }

    public static void start(Context context, boolean z, int i) {
        if (TronSetting.stakeV2) {
            ResourceManagementV2Activity.start(context, i);
            return;
        }
        Intent intent = new Intent(context, ResourceManagementActivity.class);
        intent.putExtra(FLAG_SHOW_INTRO_POPUP, z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.gray_F7F8), R.mipmap.main_bg);
        AcResourcesBinding inflate = AcResourcesBinding.inflate(getLayoutInflater());
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
        this.ivBack = this.binding.ivBack;
        this.ivHelp = this.binding.ivHelp;
        this.ptrLayout = this.binding.rcFrame;
        this.llContent = this.binding.llScroll;
        this.tvStakedDetails = this.binding.tvStakedDetails;
        this.tvEnergy = this.binding.tvEnergy;
        this.tvEnergyTotal = this.binding.tvEnergyTotal;
        this.cpEnergy = this.binding.progressEnergy;
        this.tvBandwidth = this.binding.tvBandwidth;
        this.tvBandwidthTotal = this.binding.tvBandwidthTotal;
        this.cpBandwidth = this.binding.progressBandwidth;
        this.tvTrxBalance = this.binding.tvTrxBalance;
        this.btUnStake = this.binding.btUnstake;
        this.btStake = this.binding.btStake;
        this.ivTrxHelp = this.binding.ivAvailableTrxTip;
        this.llVote = this.binding.llVote;
        this.tvVotingTps = this.binding.tvVotingTps;
        this.tvVotingApr = this.binding.tvVotingApr;
        this.btVote = this.binding.btVote;
        this.ivVotesHelp = this.binding.ivAvailableVotesTip;
        this.bandwidthTips = this.binding.ivBandwidthTip;
        this.energyTips = this.binding.ivEnergyTip;
        this.llContentInner = this.binding.llContent;
    }

    @Override
    protected void processData() {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(3);
        Intent intent = getIntent();
        if (intent != null && !intent.getBooleanExtra(FLAG_SHOW_INTRO_POPUP, true)) {
            SpAPI.THIS.setResourcesIntroShowFlag(true);
        }
        initUI();
        ((ResourceManagementPresenter) this.mPresenter).start();
        AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE);
    }

    private void initUI() {
        this.ivBack.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                exit();
            }
        });
        this.energyTips.setOnClickListener(getResourceClickListener(0));
        this.bandwidthTips.setOnClickListener(getResourceClickListener(1));
        this.ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, llContent, view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ((ResourceManagementPresenter) mPresenter).refreshAccountResources();
            }
        });
        this.cpEnergy.setProgressColors(new int[]{getResources().getColor(R.color.yellow_E2B380)});
        this.cpBandwidth.setProgressColors(new int[]{getResources().getColor(R.color.green_57BFAD)});
        if (!SpAPI.THIS.getCurIsMainChain()) {
            this.llVote.setVisibility(View.GONE);
        } else {
            this.llVote.setVisibility(View.VISIBLE);
        }
        if (IRequest.isShasta()) {
            this.llVote.setVisibility(View.GONE);
        }
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelp, 10);
        this.ivHelp.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePage.CLICK_RESOURCE_PAGE_HELP);
                showResourceIntro();
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.tvStakedDetails, 10);
        this.tvStakedDetails.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePage.CLICK_RESOURCE_PAGE_STAKED_DETAIL);
                showStakedDetails();
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.ivTrxHelp, 10);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivVotesHelp, 10);
        Collection.-EL.stream(Arrays.asList(this.btVote, this.btUnStake, this.btStake, this.binding.ivAvailableTrxTip, this.binding.ivAvailableVotesTip)).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initUI$0((View) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$initUI$0(View view) {
        view.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getResourceClickListener(int i) {
        final String[] strArr = {""};
        if (i == 0) {
            strArr[0] = getString(R.string.energy_tips, new Object[]{StringTronUtil.formatNumber3Truncate(Double.valueOf(BigDecimalUtils.mul(TronConfig.feeEnergy, Math.pow(10.0d, 6.0d))))});
        } else {
            strArr[0] = getString(R.string.bandwidth_description, new Object[]{StringTronUtil.formatNumber3Truncate(Double.valueOf(BigDecimalUtils.mul(TronConfig.feeBandWidth, Math.pow(10.0d, 6.0d))))});
        }
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                new MultiLineTextPopupView.Builder().setAnchor(view).setOffsetX(UIUtils.dip2px(25.0f)).setRequiredWidth((int) (llContentInner.getMeasuredWidth() * 0.86f)).addKeyValue(strArr[0], "").build(getIContext()).show();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        AccountUpdater.singleShot(0L);
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.bt_stake:
                        ResourceManagementActivity resourceManagementActivity = ResourceManagementActivity.this;
                        StakeTRXActivity.start(resourceManagementActivity, ((ResourceManagementPresenter) resourceManagementActivity.mPresenter).getAccount());
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.StakeGate.CLICK_RESOURCE_STAKE);
                        return;
                    case R.id.bt_unstake:
                        ResourceManagementActivity resourceManagementActivity2 = ResourceManagementActivity.this;
                        UnStakeActivity.start(resourceManagementActivity2, ((ResourceManagementPresenter) resourceManagementActivity2.mPresenter).getAccount());
                        AnalyticsHelper.logEvent(AnalyticsHelper.UnStakeGate.CLICK_FROM_RES_MANAGER);
                        return;
                    case R.id.bt_vote:
                        VoteMainActivity.startWithCheckOwnerPermission(mContext, ((ResourceManagementPresenter) mPresenter).getAccount(), null);
                        AnalyticsHelper.logEvent(AnalyticsHelper.VotingGate.CLICK_RESOURCE_PAGE_VOTE);
                        return;
                    case R.id.iv_available_trx_tip:
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePage.CLICK_RESOURCE_PAGE_TRX_HELP);
                        ResourceManagementActivity resourceManagementActivity3 = ResourceManagementActivity.this;
                        PopupWindowUtil.showPopupText(resourceManagementActivity3, resourceManagementActivity3.getResources().getString(R.string.available_trx_tip), ivTrxHelp, false);
                        return;
                    case R.id.iv_available_votes_tip:
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePage.CLICK_RESOURCE_PAGE_VOTE_HELP);
                        ResourceManagementActivity resourceManagementActivity4 = ResourceManagementActivity.this;
                        PopupWindowUtil.showPopupText(resourceManagementActivity4, resourceManagementActivity4.getResources().getString(R.string.available_tron_power_tip), ivVotesHelp, false);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    @Override
    public void updateUI(ResourcesBean resourcesBean) {
        this.ptrLayout.refreshComplete();
        if (resourcesBean == null) {
            return;
        }
        this.resourcesBean = resourcesBean;
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.gray_9B));
        SpannableString spannableString = new SpannableString(this.numberFormat.format(resourcesBean.getEnergyUsable()) + "/");
        int indexOf = spannableString.toString().indexOf("/");
        spannableString.setSpan(foregroundColorSpan, indexOf, indexOf + 1, 17);
        this.tvEnergy.setText(spannableString);
        this.tvEnergyTotal.setText(this.numberFormat.format(resourcesBean.getEnergyTotal()));
        this.cpEnergy.setProgress(resourcesBean.getEnergyTotal() == 0 ? 0.0f : ((float) resourcesBean.getEnergyUsable()) / ((float) resourcesBean.getEnergyTotal()));
        SpannableString spannableString2 = new SpannableString(this.numberFormat.format(resourcesBean.getBandwidthUsable()) + "/");
        int indexOf2 = spannableString2.toString().indexOf("/");
        spannableString2.setSpan(foregroundColorSpan, indexOf2, indexOf2 + 1, 17);
        this.tvBandwidth.setText(spannableString2);
        this.tvBandwidthTotal.setText(this.numberFormat.format(resourcesBean.getBandwidthTotal()));
        this.cpBandwidth.setProgress(resourcesBean.getBandwidthTotal() != 0 ? ((float) resourcesBean.getBandwidthUsable()) / ((float) resourcesBean.getBandwidthTotal()) : 0.0f);
        this.tvTrxBalance.setText(StringTronUtil.formatNumber3Truncate(Double.valueOf(resourcesBean.getTrxBalance())));
        this.tvVotingTps.setText(this.numberFormat.format(resourcesBean.getAvailableVotingTps()));
        this.tvStakedDetails.setVisibility(((resourcesBean.getEnergyFromSelfTrxV1() + resourcesBean.getEnergyFromOthersTrxV1()) + resourcesBean.getBandwidthFromSelfTrxV1()) + resourcesBean.getBandwidthFromOthersTrxV1() > 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updateVoteApr(String str) {
        this.tvVotingApr.setText(str);
    }

    @Override
    public void showResourceIntro() {
        new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(true).popupAnimation(PopupAnimation.NoAnimation).asCustom(new fun7(this)).show();
    }

    public class fun7 extends CenterPopupView {
        @Override
        public int getImplLayoutId() {
            return R.layout.popup_resources_help;
        }

        fun7(Context context) {
            super(context);
        }

        @Override
        public void onCreate() {
            super.onCreate();
            findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    ResourceManagementActivity.7.this.lambda$onCreate$0(view);
                }
            });
        }

        public void lambda$onCreate$0(View view) {
            dismiss();
        }
    }

    public void showStakedDetails() {
        new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(true).popupAnimation(PopupAnimation.NoAnimation).asCustom(new StakedPopup(this, this.resourcesBean)).show();
    }

    public class StakedPopup extends CenterPopupView {
        View confirm;
        View llStakedBandwidth;
        View llStakedEnergy;
        WhiteEnergyProgressView progressViewBandwidth;
        WhiteEnergyProgressView progressViewEnergy;
        private ResourcesBean resourcesBean;
        TextView tvBandwidth;
        TextView tvBandwidthOthers;
        TextView tvBandwidthSelf;
        TextView tvEnergy;
        TextView tvEnergyOthers;
        TextView tvEnergySelf;

        @Override
        public int getImplLayoutId() {
            return R.layout.popup_staked_details;
        }

        public StakedPopup(Context context, ResourcesBean resourcesBean) {
            super(context);
            this.resourcesBean = resourcesBean;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            PopupStakedDetailsBinding bind = PopupStakedDetailsBinding.bind(getPopupImplView());
            this.confirm = bind.btnConfirm;
            this.llStakedEnergy = bind.llStakedForEnergy;
            this.tvEnergy = bind.tvEnergy;
            this.tvEnergySelf = bind.tvEnergySelf;
            this.tvEnergyOthers = bind.tvEnergyOthers;
            this.progressViewEnergy = bind.progressEnergy;
            this.llStakedBandwidth = bind.llStakedForBandwidth;
            this.tvBandwidth = bind.tvBandwidth;
            this.tvBandwidthSelf = bind.tvBandwidthSelf;
            this.tvBandwidthOthers = bind.tvBandwidthOthers;
            this.progressViewBandwidth = bind.progressBandwidth;
            this.confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    ResourceManagementActivity.StakedPopup.this.lambda$onCreate$0(view);
                }
            });
            if (this.resourcesBean.getEnergyFromSelfTrxV1() + this.resourcesBean.getEnergyFromOthersTrxV1() > 0) {
                TextView textView = this.tvEnergy;
                textView.setText(numberFormat.format(this.resourcesBean.getEnergyFromSelfTrxV1() + this.resourcesBean.getEnergyFromOthersTrxV1()) + " TRX");
                TextView textView2 = this.tvEnergySelf;
                textView2.setText(numberFormat.format(this.resourcesBean.getEnergyFromSelfTrxV1()) + getContext().getResources().getString(R.string.self));
                TextView textView3 = this.tvEnergyOthers;
                textView3.setText(numberFormat.format(this.resourcesBean.getEnergyFromOthersTrxV1()) + getContext().getResources().getString(R.string.others));
                this.progressViewEnergy.setShouldStartAnimate(true);
                this.progressViewEnergy.setProgressValue(((float) this.resourcesBean.getEnergyFromSelfTrxV1()) / ((float) (this.resourcesBean.getEnergyFromSelfTrxV1() + this.resourcesBean.getEnergyFromOthersTrxV1())));
            } else {
                this.llStakedEnergy.setVisibility(View.GONE);
            }
            if (this.resourcesBean.getBandwidthFromSelfTrxV1() + this.resourcesBean.getBandwidthFromOthersTrxV1() > 0) {
                TextView textView4 = this.tvBandwidth;
                textView4.setText(numberFormat.format(this.resourcesBean.getBandwidthFromSelfTrxV1() + this.resourcesBean.getBandwidthFromOthersTrxV1()) + " TRX");
                TextView textView5 = this.tvBandwidthSelf;
                textView5.setText(numberFormat.format(this.resourcesBean.getBandwidthFromSelfTrxV1()) + getContext().getResources().getString(R.string.self));
                TextView textView6 = this.tvBandwidthOthers;
                textView6.setText(numberFormat.format(this.resourcesBean.getBandwidthFromOthersTrxV1()) + getContext().getResources().getString(R.string.others));
                this.progressViewBandwidth.setShouldStartAnimate(true);
                this.progressViewBandwidth.setProgressValue(((float) this.resourcesBean.getBandwidthFromSelfTrxV1()) / ((float) (this.resourcesBean.getBandwidthFromSelfTrxV1() + this.resourcesBean.getBandwidthFromOthersTrxV1())));
                return;
            }
            this.llStakedBandwidth.setVisibility(View.GONE);
        }

        public void lambda$onCreate$0(View view) {
            dismiss();
        }
    }
}
