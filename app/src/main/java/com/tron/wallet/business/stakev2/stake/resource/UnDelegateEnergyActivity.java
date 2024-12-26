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
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.stakev2.ResState;
import com.tron.wallet.business.stakev2.stake.resource.UnDelegateContract;
import com.tron.wallet.business.stakev2.stake.resource.UnDelegateEnergyActivity;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tron.wallet.common.components.StakePercentView;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.utils.AddressNameUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.databinding.AcUndelegateEnergyBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.Common;
import org.tron.walletserver.Wallet;
public class UnDelegateEnergyActivity extends BaseActivity<UnDelegatePresenter, EmptyModel> implements UnDelegateContract.View {
    public static final String KEY_FREEZE_TRX = "key_freezetrx";
    public static final String kEY_CAN_DELEGATED = "key_can_delegated";
    public static final String kEY_RESOURCE_AVAILABLE = "resource_available";
    public static final String kEY_RESOURCE_UNAVAILABLE = "resource_unavailable";
    private AcUndelegateEnergyBinding binding;
    TextView btnNext;
    private long canDelegated;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private String controllerName;
    CurrencyEditText currencyEditText;
    View errorLayoutView;
    private long freezeTrx;
    private boolean fromMultiSign;
    private String inputCount;
    View ivAvailableTip;
    View ivUnavailableTip;
    private long lastInput;
    private NumberFormat mNumberFormat;
    private MultiSignPermissionData multiSignPermissionData;
    private float percentAvailable;
    private String receiverAddress;
    private long resourceAvailable;
    private long resourceUnavailable;
    private String resultCount;
    private Wallet selectWallet;
    StakeHeaderView stakeHeader;
    StakePercentView stakePercentView;
    RelativeLayout topTabLayout;
    TextView tvAvailableCount;
    TextView tvAvailableTitle;
    TextView tvCanDelegated;
    TextView tvErrorTips;
    TextView tvFreezeTrx;
    TextView tvMultiSignWarning;
    TextView tvResourceTab;
    TextView tvResult;
    TextView tvStakeTitle;
    TextView tvTRXTab;
    TextView tvUnAvailableCount;
    private boolean isInputTrx = false;
    private ResState resState = ResState.Energy;
    String fmtAddress = "";
    String selectAddress = "";

    public static void start(Context context, Protocol.Account account, boolean z, String str, String str2, String str3, long j, long j2, long j3, long j4) {
        Intent intent = new Intent(context, UnDelegateEnergyActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra("key_can_delegated", j);
        intent.putExtra("key_freezetrx", j2);
        intent.putExtra("resource_available", j3);
        intent.putExtra("resource_unavailable", j4);
        intent.putExtra(SelectSendAddressActivity.KEY_RECEIVER_ADDRESS, str3);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcUndelegateEnergyBinding inflate = AcUndelegateEnergyBinding.inflate(getLayoutInflater());
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
        this.tvAvailableCount = this.binding.availableTextCount;
        this.tvUnAvailableCount = this.binding.unavailableTextCount;
        this.tvResult = this.binding.tab.tvResult;
        this.btnNext = this.binding.btnNext;
        this.errorLayoutView = this.binding.tab.liErrorTips;
        this.tvErrorTips = this.binding.tab.tvErrorTips;
        this.stakePercentView = this.binding.tab.percentView;
        this.ivAvailableTip = this.binding.ivAvailableTips;
        this.ivUnavailableTip = this.binding.ivUnavailableTips;
    }

    @Override
    protected void processData() {
        this.topTabLayout.setBackgroundResource(R.drawable.roundborder_fff6ec_8);
        this.tvAvailableTitle.setText(R.string.energy_you_can_undelegate);
        this.tvStakeTitle.setText(R.string.use_stake);
        this.tvResourceTab.setText(R.string.energy);
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.mNumberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(6);
        this.receiverAddress = getIntent().getStringExtra(SelectSendAddressActivity.KEY_RECEIVER_ADDRESS);
        this.controllerAddress = getIntent().getStringExtra("key_controller_address");
        this.fromMultiSign = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.multiSignPermissionData = (MultiSignPermissionData) getIntent().getParcelableExtra("key_multi_sign_permissions");
        this.stakeHeader.setHeader(getString(!this.fromMultiSign ? R.string.undelegate_energy : R.string.multi_reclaim), null, null);
        this.canDelegated = getIntent().getLongExtra("key_can_delegated", 0L);
        this.freezeTrx = getIntent().getLongExtra("key_freezetrx", 0L);
        this.resourceAvailable = getIntent().getLongExtra("resource_available", 0L);
        this.resourceUnavailable = getIntent().getLongExtra("resource_unavailable", 0L);
        this.percentAvailable = BigDecimalUtils.div(BigDecimalUtils.toBigDecimal(Long.valueOf(this.resourceAvailable)), BigDecimalUtils.sum_(Long.valueOf(this.resourceAvailable), Long.valueOf(this.resourceUnavailable))).floatValue();
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
        initCurrencyEditText();
        if (this.fromMultiSign) {
            handleMultiSignIntent();
        }
        initData();
        this.btnNext.setOnClickListener(new fun2());
        this.stakePercentView.setOnClickPercentListener(new StakePercentView.OnClickPercentListener() {
            @Override
            public void onClickPercent(float f, TextView textView, StakePercentView.Percent percent) {
                errorLayoutView.setVisibility(View.GONE);
                if (isInputTrx) {
                    UnDelegateEnergyActivity unDelegateEnergyActivity = UnDelegateEnergyActivity.this;
                    unDelegateEnergyActivity.inputCount = unDelegateEnergyActivity.roundingModeDown(BigDecimalUtils.mul_(Long.valueOf(unDelegateEnergyActivity.freezeTrx), Float.valueOf(f))).toPlainString();
                    UnDelegateEnergyActivity unDelegateEnergyActivity2 = UnDelegateEnergyActivity.this;
                    unDelegateEnergyActivity2.resultCount = unDelegateEnergyActivity2.roundingModeDown(unDelegateEnergyActivity2.calculateEnergyByTrx(BigDecimalUtils.toBigDecimal(unDelegateEnergyActivity2.inputCount))).toPlainString();
                    try {
                        lastInput = NumberFormat.getNumberInstance(Locale.US).parse(inputCount).longValue();
                    } catch (ParseException e) {
                        LogUtils.e(e);
                    }
                    currencyEditText.setText(mNumberFormat.format(Long.valueOf(inputCount)));
                    if (Long.valueOf(resultCount).longValue() > 0) {
                        TextView textView2 = tvResult;
                        textView2.setText(mNumberFormat.format(Long.valueOf(resultCount)) + " " + getResources().getString(R.string.energy));
                        tvResult.setVisibility(View.VISIBLE);
                    }
                    UnDelegateEnergyActivity unDelegateEnergyActivity3 = UnDelegateEnergyActivity.this;
                    unDelegateEnergyActivity3.calculateResourceAvailable(unDelegateEnergyActivity3.resultCount);
                } else {
                    UnDelegateEnergyActivity unDelegateEnergyActivity4 = UnDelegateEnergyActivity.this;
                    unDelegateEnergyActivity4.inputCount = unDelegateEnergyActivity4.roundingModeDown(BigDecimalUtils.mul_(Long.valueOf(unDelegateEnergyActivity4.canDelegated), Float.valueOf(f))).toPlainString();
                    UnDelegateEnergyActivity unDelegateEnergyActivity5 = UnDelegateEnergyActivity.this;
                    unDelegateEnergyActivity5.resultCount = unDelegateEnergyActivity5.roundingModeUp(unDelegateEnergyActivity5.calculateTrxByEnergy(BigDecimalUtils.toBigDecimal(unDelegateEnergyActivity5.inputCount))).toPlainString();
                    try {
                        lastInput = NumberFormat.getNumberInstance(Locale.US).parse(inputCount).longValue();
                    } catch (ParseException e2) {
                        LogUtils.e(e2);
                    }
                    currencyEditText.setText(mNumberFormat.format(Long.valueOf(inputCount)));
                    TextView textView3 = tvResult;
                    textView3.setText(mNumberFormat.format(Long.valueOf(resultCount)) + " TRX");
                    UnDelegateEnergyActivity unDelegateEnergyActivity6 = UnDelegateEnergyActivity.this;
                    unDelegateEnergyActivity6.calculateResourceAvailable(unDelegateEnergyActivity6.inputCount);
                    tvResult.setVisibility(View.VISIBLE);
                }
                int i = fun6.$SwitchMap$com$tron$wallet$common$components$StakePercentView$Percent[percent.ordinal()];
                if (i == 1) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_ENERGY_CLICK_25);
                } else if (i == 2) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_ENERGY_CLICK_50);
                } else if (i == 3) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_ENERGY_CLICK_75);
                } else if (i != 4) {
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_ENERGY_CLICK_100);
                }
            }
        });
        AnalyticsHelper.ResourceDelegatePage.logMultiEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_ENERGY_SHOW, this.fromMultiSign);
    }

    public class fun2 extends NoDoubleClickListener {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            Wallet selectedPublicWallet;
            try {
                if (!fromMultiSign) {
                    selectAddress = WalletUtils.getSelectedPublicWallet().getAddress();
                }
                UnDelegateEnergyActivity unDelegateEnergyActivity = UnDelegateEnergyActivity.this;
                unDelegateEnergyActivity.fmtAddress = unDelegateEnergyActivity.receiverAddress;
                String nameByAddress = AddressNameUtils.getInstance().getNameByAddress(receiverAddress);
                if (!TextUtils.isEmpty(nameByAddress)) {
                    UnDelegateEnergyActivity unDelegateEnergyActivity2 = UnDelegateEnergyActivity.this;
                    unDelegateEnergyActivity2.fmtAddress = String.format(String.format("%s\n(%s)", nameByAddress, unDelegateEnergyActivity2.receiverAddress), new Object[0]);
                }
                selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            } catch (Exception e) {
                LogUtils.e(e);
                SentryUtil.captureException(e);
                UnDelegateEnergyActivity unDelegateEnergyActivity3 = UnDelegateEnergyActivity.this;
                unDelegateEnergyActivity3.toast(unDelegateEnergyActivity3.getString(R.string.net_error));
            }
            if (selectedPublicWallet != null && selectedPublicWallet.getCreateType() == 7) {
                UnDelegateEnergyActivity unDelegateEnergyActivity4 = UnDelegateEnergyActivity.this;
                unDelegateEnergyActivity4.toast(unDelegateEnergyActivity4.getString(R.string.no_samsung_to_shield));
                return;
            }
            runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    UnDelegateEnergyActivity.2.this.lambda$onNoDoubleClick$0();
                }
            });
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_ENERGY_CLICK_NEXT);
        }

        public void lambda$onNoDoubleClick$0() {
            GrpcAPI.TransactionExtention unDelegateResource = TronAPI.unDelegateResource(fromMultiSign ? controllerAddress : selectAddress, receiverAddress, Common.ResourceCode.ENERGY, Long.valueOf(!isInputTrx ? resultCount : inputCount).longValue() * 1000000);
            if (unDelegateResource != null) {
                if (TransactionUtils.checkTransactionEmpty(unDelegateResource)) {
                    ToastSuc(R.string.create_transaction_fail);
                    return;
                }
                UnDelegateEnergyActivity unDelegateEnergyActivity = UnDelegateEnergyActivity.this;
                ConfirmTransactionNewActivity.startActivity(unDelegateEnergyActivity, ParamBuildUtils.getDelegatedResourceTransactionParamBuilder(!unDelegateEnergyActivity.fromMultiSign, fmtAddress, "", false, 1, Long.valueOf(!isInputTrx ? inputCount : resultCount).longValue(), Long.valueOf(!isInputTrx ? resultCount : inputCount).longValue(), unDelegateResource.getTransaction()));
                return;
            }
            UnDelegateEnergyActivity unDelegateEnergyActivity2 = UnDelegateEnergyActivity.this;
            unDelegateEnergyActivity2.toast(unDelegateEnergyActivity2.getString(R.string.net_error));
        }
    }

    static class fun6 {
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

    private void initData() {
        this.tvCanDelegated.setText(this.mNumberFormat.format(this.canDelegated));
        TextView textView = this.tvFreezeTrx;
        textView.setText(this.mNumberFormat.format(this.freezeTrx) + " TRX");
        this.tvAvailableCount.setText("0");
        this.tvUnAvailableCount.setText("0");
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_available_tips:
                        PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.recycle_delegate_not_used), ivAvailableTip, true);
                        return;
                    case R.id.iv_unavailable_tips:
                        PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.recycle_delegate_used), ivUnavailableTip, true);
                        return;
                    case R.id.resource_tab:
                        if (isInputTrx) {
                            tvResourceTab.setBackgroundResource(R.drawable.roundborder_white_radius_3);
                            tvResourceTab.setTextColor(getResources().getColor(R.color.black_23));
                            tvTRXTab.setBackgroundColor(Color.argb(0, 0, 0, 0));
                            tvTRXTab.setTextColor(getResources().getColor(R.color.black_6d));
                            isInputTrx = false;
                            currencyEditText.setText("");
                            currencyEditText.setHint(R.string.input_energy_undelegate);
                            tvResult.setVisibility(View.GONE);
                            resetResourceAvailable();
                            errorLayoutView.setVisibility(View.GONE);
                            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_ENERGY_CLICK_ENERGY);
                            return;
                        }
                        return;
                    case R.id.trx_tab:
                        if (isInputTrx) {
                            return;
                        }
                        tvTRXTab.setBackgroundResource(R.drawable.roundborder_white_radius_3);
                        tvTRXTab.setTextColor(getResources().getColor(R.color.black_23));
                        tvResourceTab.setBackgroundColor(Color.argb(0, 0, 0, 0));
                        tvResourceTab.setTextColor(getResources().getColor(R.color.black_6d));
                        isInputTrx = true;
                        currencyEditText.setText("");
                        currencyEditText.setHint(R.string.input_trx_undelegate);
                        tvResult.setVisibility(View.GONE);
                        resetResourceAvailable();
                        errorLayoutView.setVisibility(View.GONE);
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceUnDelegatePage.UN_DELEGATE_BAND_CLICK_TRX);
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.tab.trxTab.setOnClickListener(noDoubleClickListener2);
        this.binding.tab.resourceTab.setOnClickListener(noDoubleClickListener2);
        this.binding.ivAvailableTips.setOnClickListener(noDoubleClickListener2);
        this.binding.ivUnavailableTips.setOnClickListener(noDoubleClickListener2);
    }

    private void initCurrencyEditText() {
        TextDotUtils.setTextChangedListener_Dot(this.currencyEditText);
        this.currencyEditText.setHint(R.string.input_energy_undelegate);
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
                    tvResult.setVisibility(View.GONE);
                    resetResourceAvailable();
                    btnNext.setEnabled(false);
                    stakePercentView.setPowerState(resState);
                } else if (isInputTrx) {
                    inputCount = String.valueOf(j);
                    if (BigDecimalUtils.MoreThan(BigDecimalUtils.toBigDecimal(inputCount), Long.valueOf(freezeTrx))) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        tvResult.setVisibility(View.GONE);
                        resetResourceAvailable();
                        tvErrorTips.setText(getResources().getString(R.string.out_the_max_undelegate));
                        btnNext.setEnabled(false);
                    } else if (BigDecimalUtils.equalsZero(inputCount)) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        tvErrorTips.setText(getResources().getString(R.string.less_undelegate_trx_is_one));
                        tvResult.setVisibility(View.GONE);
                        resetResourceAvailable();
                        btnNext.setEnabled(false);
                    } else {
                        errorLayoutView.setVisibility(View.GONE);
                        UnDelegateEnergyActivity unDelegateEnergyActivity = UnDelegateEnergyActivity.this;
                        unDelegateEnergyActivity.resultCount = unDelegateEnergyActivity.roundingModeDown(unDelegateEnergyActivity.calculateEnergyByTrx(BigDecimalUtils.toBigDecimal(unDelegateEnergyActivity.inputCount))).toPlainString();
                        TextView textView = tvResult;
                        textView.setText(mNumberFormat.format(Long.valueOf(resultCount)) + " " + getResources().getString(R.string.energy));
                        UnDelegateEnergyActivity unDelegateEnergyActivity2 = UnDelegateEnergyActivity.this;
                        unDelegateEnergyActivity2.calculateResourceAvailable(unDelegateEnergyActivity2.resultCount);
                        tvResult.setVisibility(View.VISIBLE);
                        btnNext.setEnabled(true);
                    }
                } else {
                    inputCount = String.valueOf(j);
                    if (BigDecimalUtils.MoreThan(BigDecimalUtils.toBigDecimal(inputCount), Long.valueOf(canDelegated))) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        tvResult.setVisibility(View.GONE);
                        resetResourceAvailable();
                        tvErrorTips.setText(getResources().getString(R.string.out_the_max_undelegate));
                        btnNext.setEnabled(false);
                    } else if (BigDecimalUtils.equalsZero(inputCount)) {
                        errorLayoutView.setVisibility(View.VISIBLE);
                        tvErrorTips.setText(getResources().getString(R.string.resource_undelegate_need_more_than_0));
                        tvResult.setVisibility(View.GONE);
                        resetResourceAvailable();
                        btnNext.setEnabled(false);
                    } else {
                        errorLayoutView.setVisibility(View.GONE);
                        UnDelegateEnergyActivity unDelegateEnergyActivity3 = UnDelegateEnergyActivity.this;
                        unDelegateEnergyActivity3.resultCount = unDelegateEnergyActivity3.roundingModeUp(unDelegateEnergyActivity3.calculateTrxByEnergy(BigDecimalUtils.toBigDecimal(unDelegateEnergyActivity3.inputCount))).toPlainString();
                        TextView textView2 = tvResult;
                        textView2.setText(mNumberFormat.format(Long.valueOf(resultCount)) + " TRX");
                        UnDelegateEnergyActivity unDelegateEnergyActivity4 = UnDelegateEnergyActivity.this;
                        unDelegateEnergyActivity4.calculateResourceAvailable(unDelegateEnergyActivity4.inputCount);
                        tvResult.setVisibility(View.VISIBLE);
                        btnNext.setEnabled(true);
                    }
                }
            }
        });
    }

    public BigDecimal calculateEnergyByTrx(BigDecimal bigDecimal) {
        return BigDecimalUtils.div_(BigDecimalUtils.mul_(this.inputCount, Long.valueOf(this.canDelegated)), Long.valueOf(this.freezeTrx));
    }

    public BigDecimal calculateTrxByEnergy(BigDecimal bigDecimal) {
        return BigDecimalUtils.div_(BigDecimalUtils.mul_(this.inputCount, Long.valueOf(this.freezeTrx)), Long.valueOf(this.canDelegated));
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
        this.tvMultiSignWarning.setText(getString(R.string.multi_sign_controller_tips_undelegate, new Object[]{str}));
        this.tvMultiSignWarning.post(new Runnable() {
            @Override
            public final void run() {
                lambda$handleMultiSignIntent$0(str);
            }
        });
        this.tvMultiSignWarning.setVisibility(View.VISIBLE);
    }

    public void lambda$handleMultiSignIntent$0(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    void calculateResourceAvailable(String str) {
        if (BigDecimalUtils.equalsZero(str)) {
            resetResourceAvailable();
            return;
        }
        this.tvAvailableCount.setText(this.mNumberFormat.format(BigDecimalUtils.mul_(Float.valueOf(this.percentAvailable), str).longValue()));
        this.tvUnAvailableCount.setText(this.mNumberFormat.format(BigDecimalUtils.sub_(str, Long.valueOf(BigDecimalUtils.mul_(Float.valueOf(this.percentAvailable), str).longValue())).longValue()));
    }

    void resetResourceAvailable() {
        this.tvAvailableCount.setText("0");
        this.tvUnAvailableCount.setText("0");
    }
}
