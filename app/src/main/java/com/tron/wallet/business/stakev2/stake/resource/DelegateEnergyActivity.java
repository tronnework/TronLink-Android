package com.tron.wallet.business.stakev2.stake.resource;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.business.stakev2.stake.resource.DelegateContract;
import com.tron.wallet.business.stakev2.stake.resource.DelegateEnergyActivity;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.common.bean.RecentSendBean;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.DelegateResourceLockedView;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tron.wallet.common.components.StakePercentView;
import com.tron.wallet.common.components.SwitchButton;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.TimeUtil;
import com.tron.wallet.databinding.AcDelegateEnergyBinding;
import com.tron.wallet.db.Controller.RecentSendController;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import kotlin.time.DurationKt;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
import org.tron.walletserver.Wallet;
public class DelegateEnergyActivity extends BaseActivity<DelegatePresenter, EmptyModel> implements DelegateContract.View {
    public static final String KEY_FREEZE_TRX = "key_freezetrx";
    public static final String kEY_CAN_DELEGATED = "key_can_delegated";
    private AcDelegateEnergyBinding binding;
    TextView btnNext;
    private long canDelegated;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private String controllerName;
    CurrencyEditText currencyEditText;
    DelegateResourceLockedView delegateResourceLockedView;
    View errorLayoutView;
    private String fmtAddress;
    private long freezeTrx;
    private boolean fromMultiSign;
    private String inputCount;
    private long lastInput;
    private NumberFormat mNumberFormat;
    private MultiSignPermissionData multiSignPermissionData;
    private String receiverAddress;
    private String resultCount;
    private Wallet selectWallet;
    StakeHeaderView stakeHeader;
    StakePercentView stakePercentView;
    SwitchButton switchButton;
    RelativeLayout topTabLayout;
    TextView tvAvailableTitle;
    TextView tvCanDelegated;
    TextView tvErrorTips;
    TextView tvFreezeTrx;
    TextView tvMultiSignWarning;
    TextView tvResourceTab;
    TextView tvResult;
    TextView tvStakeTitle;
    TextView tvTRXTab;
    private boolean threeDaysLockIsOpen = false;
    private boolean hasInputNoError = false;
    private boolean isInputTrx = false;
    private ResState resState = ResState.Energy;

    @Override
    public DelegateResourceLockedView getDelegateResourceLockedView() {
        return this.delegateResourceLockedView;
    }

    public static void start(Context context, Protocol.Account account, boolean z, String str, String str2, long j, long j2, String str3) {
        Intent intent = new Intent(context, DelegateEnergyActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(SelectSendAddressActivity.KEY_RECEIVER_ADDRESS, str3);
        intent.putExtra("key_can_delegated", j);
        intent.putExtra("key_freezetrx", j2);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcDelegateEnergyBinding inflate = AcDelegateEnergyBinding.inflate(getLayoutInflater());
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
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
        this.topTabLayout = this.binding.tab.tab;
        this.tvAvailableTitle = this.binding.tab.availableTitle;
        this.tvStakeTitle = this.binding.tab.inStakeTitle;
        this.tvTRXTab = this.binding.tab.trxTab;
        this.tvResourceTab = this.binding.tab.resourceTab;
        this.tvCanDelegated = this.binding.tab.availableCount;
        this.tvFreezeTrx = this.binding.tab.inStakeCount;
        this.currencyEditText = this.binding.tab.etInput;
        this.tvResult = this.binding.tab.tvResult;
        this.switchButton = (SwitchButton) this.binding.getRoot().findViewById(R.id.switch_button);
        this.btnNext = this.binding.btnNext;
        this.errorLayoutView = this.binding.tab.liErrorTips;
        this.tvErrorTips = this.binding.tab.tvErrorTips;
        this.stakePercentView = this.binding.tab.percentView;
        this.delegateResourceLockedView = this.binding.resourceLockSwitchLayout;
    }

    @Override
    protected void processData() {
        this.topTabLayout.setBackgroundResource(R.drawable.roundborder_fff6ec_8);
        this.tvAvailableTitle.setText(R.string.energy_you_can_delegate);
        this.tvStakeTitle.setText(R.string.use_withheld);
        this.currencyEditText.setHint(R.string.input_energy_delegate);
        this.tvResourceTab.setText(R.string.energy);
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.mNumberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(6);
        this.receiverAddress = getIntent().getStringExtra(SelectSendAddressActivity.KEY_RECEIVER_ADDRESS);
        this.controllerAddress = getIntent().getStringExtra("key_controller_address");
        this.fromMultiSign = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.multiSignPermissionData = (MultiSignPermissionData) getIntent().getParcelableExtra("key_multi_sign_permissions");
        this.receiverAddress = getIntent().getStringExtra(SelectSendAddressActivity.KEY_RECEIVER_ADDRESS);
        this.stakeHeader.setHeader(getString(!this.fromMultiSign ? R.string.delegate_energy : R.string.multi_delegate), "(2/2)", null);
        this.canDelegated = getIntent().getLongExtra("key_can_delegated", 0L);
        this.freezeTrx = getIntent().getLongExtra("key_freezetrx", 0L);
        this.stakeHeader.setOnHeaderClickListener(new StakeHeaderView.OnHeaderClickListener() {
            @Override
            public void onQuestion() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onQuestion(this);
            }

            @Override
            public void onRightClick() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onRightClick(this);
            }

            @Override
            public void onLeftClick() {
                exit();
            }
        });
        this.stakeHeader.hideIconV2();
        initSwitch();
        initCurrencyEditText();
        if (this.fromMultiSign) {
            handleMultiSignIntent();
        }
        this.tvCanDelegated.setText(this.mNumberFormat.format(this.canDelegated));
        TextView textView = this.tvFreezeTrx;
        textView.setText(this.mNumberFormat.format(this.freezeTrx) + " TRX");
        this.btnNext.setOnClickListener(new fun2());
        this.stakePercentView.setOnClickPercentListener(new StakePercentView.OnClickPercentListener() {
            @Override
            public void onClickPercent(float f, TextView textView2, StakePercentView.Percent percent) {
                errorLayoutView.setVisibility(View.GONE);
                if (isInputTrx) {
                    DelegateEnergyActivity delegateEnergyActivity = DelegateEnergyActivity.this;
                    delegateEnergyActivity.inputCount = delegateEnergyActivity.roundingModeDown(BigDecimalUtils.mul_(Long.valueOf(delegateEnergyActivity.freezeTrx), Float.valueOf(f))).toPlainString();
                    DelegateEnergyActivity delegateEnergyActivity2 = DelegateEnergyActivity.this;
                    delegateEnergyActivity2.resultCount = delegateEnergyActivity2.roundingModeDown(delegateEnergyActivity2.calculateEnergyByTrx(BigDecimalUtils.toBigDecimal(delegateEnergyActivity2.inputCount))).toPlainString();
                    try {
                        lastInput = NumberFormat.getNumberInstance(Locale.US).parse(inputCount).longValue();
                    } catch (ParseException e) {
                        LogUtils.e(e);
                    }
                    currencyEditText.setText(mNumberFormat.format(Long.valueOf(inputCount)));
                    if (Long.valueOf(resultCount).longValue() > 0) {
                        tvResult.setText(String.format(getResources().getString(R.string.lend_resource_estimate_energy), mNumberFormat.format(Long.valueOf(resultCount))));
                        tvResult.setVisibility(View.VISIBLE);
                    }
                    DelegateEnergyActivity delegateEnergyActivity3 = DelegateEnergyActivity.this;
                    delegateEnergyActivity3.updateResourceNew(Long.valueOf(delegateEnergyActivity3.resultCount).longValue());
                } else {
                    DelegateEnergyActivity delegateEnergyActivity4 = DelegateEnergyActivity.this;
                    delegateEnergyActivity4.inputCount = delegateEnergyActivity4.roundingModeDown(BigDecimalUtils.mul_(Long.valueOf(delegateEnergyActivity4.canDelegated), Float.valueOf(f))).toPlainString();
                    DelegateEnergyActivity delegateEnergyActivity5 = DelegateEnergyActivity.this;
                    delegateEnergyActivity5.resultCount = delegateEnergyActivity5.roundingModeUp(delegateEnergyActivity5.calculateTrxByEnergy(BigDecimalUtils.toBigDecimal(delegateEnergyActivity5.inputCount))).toPlainString();
                    try {
                        lastInput = NumberFormat.getNumberInstance(Locale.US).parse(inputCount).longValue();
                    } catch (ParseException e2) {
                        LogUtils.e(e2);
                    }
                    currencyEditText.setText(mNumberFormat.format(Long.valueOf(inputCount)));
                    if (Long.valueOf(resultCount).longValue() > 0) {
                        tvResult.setText(String.format(getResources().getString(R.string.lend_resource_estimate_trx), mNumberFormat.format(Long.valueOf(resultCount))));
                        tvResult.setVisibility(View.VISIBLE);
                    }
                    DelegateEnergyActivity delegateEnergyActivity6 = DelegateEnergyActivity.this;
                    delegateEnergyActivity6.updateResourceNew(Long.valueOf(delegateEnergyActivity6.inputCount).longValue());
                }
                int i = fun7.$SwitchMap$com$tron$wallet$common$components$StakePercentView$Percent[percent.ordinal()];
                if (i == 1) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_25);
                } else if (i == 2) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_50);
                } else if (i == 3) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_75);
                } else if (i != 4) {
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_100);
                }
            }
        });
        AnalyticsHelper.ResourceDelegatePage.logMultiEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_SHOW, this.fromMultiSign);
        initDeleteGageView();
        ((DelegatePresenter) this.mPresenter).getResourceDate(this.controllerAddress, this.receiverAddress, TronConfig.RESOURCE_ENERGY);
    }

    public class fun2 extends NoDoubleClickListener {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            Wallet selectedPublicWallet;
            try {
                selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            } catch (Exception e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
                DelegateEnergyActivity delegateEnergyActivity = DelegateEnergyActivity.this;
                delegateEnergyActivity.toast(delegateEnergyActivity.getString(R.string.net_error));
            }
            if (selectedPublicWallet != null && selectedPublicWallet.getCreateType() == 7) {
                DelegateEnergyActivity delegateEnergyActivity2 = DelegateEnergyActivity.this;
                delegateEnergyActivity2.toast(delegateEnergyActivity2.getString(R.string.no_samsung_to_shield));
                return;
            }
            DelegateEnergyActivity delegateEnergyActivity3 = DelegateEnergyActivity.this;
            delegateEnergyActivity3.addRecentReceiveAddress(delegateEnergyActivity3.controllerAddress, receiverAddress);
            DelegateEnergyActivity delegateEnergyActivity4 = DelegateEnergyActivity.this;
            delegateEnergyActivity4.fmtAddress = delegateEnergyActivity4.receiverAddress;
            String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(receiverAddress);
            if (!TextUtils.isEmpty(nameByAddress)) {
                DelegateEnergyActivity delegateEnergyActivity5 = DelegateEnergyActivity.this;
                delegateEnergyActivity5.fmtAddress = String.format(String.format("%s\n(%s)", nameByAddress, delegateEnergyActivity5.receiverAddress), new Object[0]);
            }
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    DelegateEnergyActivity.2.this.lambda$onNoDoubleClick$0();
                }
            });
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_NEXT);
        }

        public void lambda$onNoDoubleClick$0() {
            String str;
            long j = TimeUtil.defaultLockPeriod;
            if (threeDaysLockIsOpen) {
                j = delegateResourceLockedView.getTimeSelect() / 3000;
                DelegateEnergyActivity delegateEnergyActivity = DelegateEnergyActivity.this;
                str = TimeUtil.formatTime(delegateEnergyActivity, delegateEnergyActivity.delegateResourceLockedView.getTimeSelect());
            } else {
                str = "";
            }
            String str2 = str;
            GrpcAPI.TransactionExtention delegateResource = TronAPI.delegateResource(controllerAddress, receiverAddress, Common.ResourceCode.ENERGY, 1000000 * Long.valueOf(isInputTrx ? inputCount : resultCount).longValue(), threeDaysLockIsOpen, j);
            if (delegateResource != null) {
                if (TransactionUtils.checkTransactionEmpty(delegateResource)) {
                    ToastSuc(R.string.create_transaction_fail);
                    return;
                }
                DelegateEnergyActivity delegateEnergyActivity2 = DelegateEnergyActivity.this;
                ConfirmTransactionNewActivity.startActivity(delegateEnergyActivity2, ParamBuildUtils.getDelegatedResourceTransactionParamBuilder(!delegateEnergyActivity2.fromMultiSign, fmtAddress, str2, true, 1, Long.valueOf(!isInputTrx ? inputCount : resultCount).longValue(), Long.valueOf(!isInputTrx ? resultCount : inputCount).longValue(), delegateResource.getTransaction()));
                return;
            }
            DelegateEnergyActivity delegateEnergyActivity3 = DelegateEnergyActivity.this;
            delegateEnergyActivity3.toast(delegateEnergyActivity3.getString(R.string.net_error));
        }
    }

    static class fun7 {
        static final int[] $SwitchMap$com$tron$wallet$common$components$StakePercentView$Percent;

        static {
            int[] iArr = new int[StakePercentView.Percent.values().length];
            $SwitchMap$com$tron$wallet$common$components$StakePercentView$Percent = iArr;
            try {
                iArr[StakePercentView.Percent.PERCENT25.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$StakePercentView$Percent[StakePercentView.Percent.PERCENT50.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$StakePercentView$Percent[StakePercentView.Percent.PERCENT75.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$common$components$StakePercentView$Percent[StakePercentView.Percent.PERCENT100.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override
    public void initDeleteGageView() {
        this.delegateResourceLockedView.setResourceType(TronConfig.RESOURCE_ENERGY);
        this.delegateResourceLockedView.setOnDelegateResourceLockedListener(new DelegateResourceLockedView.OnDelegateResourceLockedListener() {
            @Override
            public void onTimeUpdate(long j) {
            }

            @Override
            public void onPickedShowCallback() {
                hideSoftKeyboard();
            }

            @Override
            public void onError(boolean z) {
                updateTheNextBtnStatus();
            }
        });
    }

    @Override
    public void updateTheNextBtnStatus() {
        if (this.delegateResourceLockedView.isHasError() || !this.hasInputNoError) {
            this.btnNext.setEnabled(false);
        } else {
            this.btnNext.setEnabled(true);
        }
    }

    public void addRecentReceiveAddress(String str, String str2) {
        try {
            RecentSendBean recentSendBean = new RecentSendBean();
            recentSendBean.setTransactionType(1);
            recentSendBean.setSendAddress(str);
            recentSendBean.setReceiverAddress(str2);
            recentSendBean.setTimestamp(Long.valueOf(System.currentTimeMillis()));
            RecentSendController.getInstance().insertOrReplace(recentSendBean);
        } catch (Throwable th) {
            SentryUtil.captureException(th);
        }
    }

    private void initCurrencyEditText() {
        TextDotUtils.setTextChangedListener_Dot(this.currencyEditText);
        this.currencyEditText.setHint(R.string.input_energy_delegate);
        this.tvResourceTab.setText(R.string.energy);
        this.stakePercentView.setPowerState(this.resState);
        this.currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = getInputText();
                long j = 0;
                try {
                    if (!StringTronUtil.isEmpty(inputText)) {
                        j = NumberFormat.getNumberInstance(Locale.US).parse(inputText).longValue();
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
                if (j != lastInput) {
                    stakePercentView.setPowerState(resState);
                    lastInput = j;
                }
                if (StringTronUtil.isEmpty(inputText)) {
                    errorLayoutView.setVisibility(View.GONE);
                    revertInput();
                    hasInputNoError = false;
                    updateTheNextBtnStatus();
                    stakePercentView.setPowerState(resState);
                } else if (isInputTrx) {
                    inputCount = String.valueOf(j);
                    if (BigDecimalUtils.MoreThan(BigDecimalUtils.toBigDecimal(inputCount), Long.valueOf(freezeTrx))) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        revertInput();
                        tvErrorTips.setText(getResources().getString(R.string.out_the_max_delegate));
                        hasInputNoError = false;
                        updateTheNextBtnStatus();
                        return;
                    }
                    if (BigDecimalUtils.equalsZero(inputCount)) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        tvErrorTips.setText(getResources().getString(R.string.less_delegate_trx_is_one));
                        revertInput();
                        hasInputNoError = false;
                        updateTheNextBtnStatus();
                        resultCount = "0";
                    } else {
                        errorLayoutView.setVisibility(View.GONE);
                        DelegateEnergyActivity delegateEnergyActivity = DelegateEnergyActivity.this;
                        delegateEnergyActivity.resultCount = delegateEnergyActivity.roundingModeDown(delegateEnergyActivity.calculateEnergyByTrx(BigDecimalUtils.toBigDecimal(delegateEnergyActivity.inputCount))).toPlainString();
                        tvResult.setText(String.format(getResources().getString(R.string.lend_resource_estimate_energy), mNumberFormat.format(Long.valueOf(resultCount))));
                        tvResult.setVisibility(View.VISIBLE);
                        hasInputNoError = true;
                        updateTheNextBtnStatus();
                    }
                    DelegateEnergyActivity delegateEnergyActivity2 = DelegateEnergyActivity.this;
                    delegateEnergyActivity2.updateResourceNew(Long.valueOf(delegateEnergyActivity2.resultCount).longValue());
                } else {
                    inputCount = String.valueOf(j);
                    if (BigDecimalUtils.MoreThan(BigDecimalUtils.toBigDecimal(inputCount), Long.valueOf(canDelegated))) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        revertInput();
                        tvErrorTips.setText(getResources().getString(R.string.out_the_max_delegate));
                        hasInputNoError = false;
                        updateTheNextBtnStatus();
                        return;
                    }
                    if (BigDecimalUtils.equalsZero(inputCount)) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        tvErrorTips.setText(getResources().getString(R.string.resource_delegate_need_more_than_0));
                        revertInput();
                        hasInputNoError = false;
                        updateTheNextBtnStatus();
                        resultCount = "0";
                    } else {
                        errorLayoutView.setVisibility(View.GONE);
                        DelegateEnergyActivity delegateEnergyActivity3 = DelegateEnergyActivity.this;
                        delegateEnergyActivity3.resultCount = delegateEnergyActivity3.roundingModeUp(delegateEnergyActivity3.calculateTrxByEnergy(BigDecimalUtils.toBigDecimal(delegateEnergyActivity3.inputCount))).toPlainString();
                        tvResult.setText(String.format(getResources().getString(R.string.lend_resource_estimate_trx), mNumberFormat.format(Long.valueOf(resultCount))));
                        tvResult.setVisibility(View.VISIBLE);
                        hasInputNoError = true;
                        updateTheNextBtnStatus();
                    }
                    DelegateEnergyActivity delegateEnergyActivity4 = DelegateEnergyActivity.this;
                    delegateEnergyActivity4.updateResourceNew(Long.valueOf(delegateEnergyActivity4.inputCount).longValue());
                }
            }
        });
    }

    @Override
    public void initSwitch() {
        this.switchButton.setClickable(false);
        this.switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(SwitchButton switchButton, boolean z) {
                lambda$initSwitch$0(switchButton, z);
            }
        });
    }

    public void lambda$initSwitch$0(SwitchButton switchButton, boolean z) {
        this.threeDaysLockIsOpen = z;
        this.delegateResourceLockedView.showLockedInfoView(z);
        if (z) {
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_LOCK);
        } else {
            ((DelegatePresenter) this.mPresenter).revertTheLockResourceSetting();
        }
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id != R.id.resource_tab) {
                    if (id == R.id.trx_tab && !isInputTrx) {
                        tvTRXTab.setBackgroundResource(R.drawable.roundborder_white_radius_3);
                        tvTRXTab.setTextColor(getResources().getColor(R.color.black_23));
                        tvResourceTab.setBackgroundColor(Color.argb(0, 0, 0, 0));
                        tvResourceTab.setTextColor(getResources().getColor(R.color.black_6d));
                        isInputTrx = true;
                        currencyEditText.setText("");
                        currencyEditText.setHint(R.string.input_trx_delegate);
                        revertInput();
                        errorLayoutView.setVisibility(View.GONE);
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_TRX);
                    }
                } else if (isInputTrx) {
                    tvResourceTab.setBackgroundResource(R.drawable.roundborder_white_radius_3);
                    tvResourceTab.setTextColor(getResources().getColor(R.color.black_23));
                    tvTRXTab.setBackgroundColor(Color.argb(0, 0, 0, 0));
                    tvTRXTab.setTextColor(getResources().getColor(R.color.black_6d));
                    isInputTrx = false;
                    currencyEditText.setText("");
                    currencyEditText.setHint(R.string.input_energy_delegate);
                    revertInput();
                    errorLayoutView.setVisibility(View.GONE);
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_2_CLICK_ENERGY);
                }
            }
        };
        this.binding.tab.trxTab.setOnClickListener(noDoubleClickListener2);
        this.binding.tab.resourceTab.setOnClickListener(noDoubleClickListener2);
    }

    public BigDecimal calculateEnergyByTrx(BigDecimal bigDecimal) {
        return BigDecimalUtils.div_(BigDecimalUtils.mul_(bigDecimal, Long.valueOf(this.canDelegated)), Long.valueOf(this.freezeTrx));
    }

    public BigDecimal calculateTrxByEnergy(BigDecimal bigDecimal) {
        return BigDecimalUtils.div_(BigDecimalUtils.mul_(bigDecimal, Long.valueOf(this.freezeTrx)), Long.valueOf(this.canDelegated));
    }

    public BigDecimal roundingModeUp(BigDecimal bigDecimal) {
        return bigDecimal.setScale(0, 0);
    }

    public BigDecimal roundingModeDown(BigDecimal bigDecimal) {
        return bigDecimal.setScale(0, 1);
    }

    public String getInputText() {
        return this.currencyEditText.getText().toString().trim();
    }

    private void handleMultiSignIntent() {
        final String str = this.controllerAddress;
        String stringExtra = getIntent().getStringExtra("key_controller_name");
        this.controllerName = stringExtra;
        if (!TextUtils.isEmpty(stringExtra)) {
            str = String.format("%s (%s)", this.controllerName, str);
        }
        this.tvMultiSignWarning.setText(getString(R.string.multi_sign_controller_tips_delegate, new Object[]{str}));
        this.tvMultiSignWarning.post(new Runnable() {
            @Override
            public final void run() {
                lambda$handleMultiSignIntent$1(str);
            }
        });
        this.tvMultiSignWarning.setVisibility(View.VISIBLE);
    }

    public void lambda$handleMultiSignIntent$1(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void updateLockTimeView(final long j, final long j2) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateLockTimeView$2(j, j2);
            }
        });
    }

    public void lambda$updateLockTimeView$2(long j, long j2) {
        long j3;
        this.switchButton.setClickable(true);
        this.delegateResourceLockedView.setTimeSelect(j);
        this.delegateResourceLockedView.setFrozenBalance(j2);
        this.delegateResourceLockedView.setRemainingTimeTextView(j);
        this.delegateResourceLockedView.setTime(j);
        this.delegateResourceLockedView.setUnDelegateTimeTextView(j);
        long longValue = roundingModeDown(calculateEnergyByTrx(BigDecimalUtils.div_(Long.valueOf(j2), Integer.valueOf((int) DurationKt.NANOS_IN_MILLIS)))).longValue();
        if (StringTronUtil.isNullOrEmpty(this.inputCount) || StringTronUtil.isEmpty(this.resultCount)) {
            j3 = 0;
        } else {
            j3 = Long.valueOf(this.isInputTrx ? this.resultCount : this.inputCount).longValue();
        }
        this.delegateResourceLockedView.setLockedResource(longValue, longValue + j3, !this.isInputTrx ? this.inputCount : "");
    }

    @Override
    public void revertInput() {
        long j;
        this.tvResult.setVisibility(View.GONE);
        this.inputCount = "0";
        this.resultCount = "0";
        long longValue = roundingModeDown(calculateEnergyByTrx(BigDecimalUtils.div_(Long.valueOf(this.delegateResourceLockedView.getFrozenBalance()), Integer.valueOf((int) DurationKt.NANOS_IN_MILLIS)))).longValue();
        if (StringTronUtil.isEmpty(this.inputCount) || StringTronUtil.isEmpty(this.resultCount)) {
            j = 0;
        } else {
            j = Long.valueOf(this.isInputTrx ? this.resultCount : this.inputCount).longValue();
        }
        this.delegateResourceLockedView.setLockedResource(longValue, longValue + j, null);
    }

    @Override
    public void updateResourceNew(long j) {
        BigDecimal div_ = BigDecimalUtils.div_(Long.valueOf(this.delegateResourceLockedView.getFrozenBalance()), Integer.valueOf((int) DurationKt.NANOS_IN_MILLIS));
        String str = !this.isInputTrx ? this.inputCount : "";
        long longValue = roundingModeDown(calculateEnergyByTrx(div_)).longValue();
        this.delegateResourceLockedView.setLockedResource(longValue, longValue + j, str);
    }

    @Override
    public void updateDelegateViewData(long j, long j2) {
        this.delegateResourceLockedView.setHasLockTime(true);
        this.delegateResourceLockedView.setResource(j2);
        this.delegateResourceLockedView.setLeftTime(j);
    }

    @Override
    public void hideSoftKeyboard() {
        try {
            KeyboardUtils.hideSoftInput(getWindow().getDecorView());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
