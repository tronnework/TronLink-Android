package com.tron.wallet.business.tronpower.stake;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.instructions.TrxInstructionsActivity;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.tronpower.stake.StakeTRXContract;
import com.tron.wallet.business.tronpower.stake2.StakeTRX2Activity;
import com.tron.wallet.business.tronpower.unstake.UnStakeActivity;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.interfaces.CheckAccountNotActivatedCallback;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BandwidthUtils;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcStakeTrxBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import j$.util.Collection;
import j$.util.Objects;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class StakeTRXActivity extends BaseActivity<StakeTRXPresenter, StakeTRXModel> implements StakeTRXContract.View {
    public static String ACCOUNT = "account";
    public static String ACCOUNT_RES = "accountRes";
    private static String ADDRESS = "address";
    private static final int BANDWIDTH_MULTISIGN_NUM = 550;
    private static final int BANDWIDTH_NORMAL_NUM = 265;
    public static String IS_MULTI_SIGN = "is_multi_sign";
    public static final String KEY_STAKE_AMOUNT = "key_statke_amount";
    public static final String KEY_STAKE_OWNER_ADDRESS_NAME = "key_statke_address_name";
    private static String TYPE = "type";
    public static int TYPE_BANDWIDTH = 1;
    public static int TYPE_ENERGY = 2;
    private String bandwidthInput;
    private String bandwidthTRXInput;
    private AcStakeTrxBinding binding;
    Button btnNextStep;
    EditText edInputRes;
    private String energyInput;
    private String energyTRXInput;
    CurrencyEditText etAmount;
    private boolean hasOwnerPermission;
    View ivArrow;
    View ivStakeEdit;
    View ivTips;
    ImageView ivUnStatkeArrow;
    View liBottom;
    LinearLayout liErrorTip;
    View llBg;
    View llGetBg;
    View llRoot;
    View llScroll;
    private Protocol.Account mAccount;
    private GrpcAPI.AccountResourceMessage mAccountResMessage;
    private BigDecimal mFreezeAmount;
    private String mSelectAddress;
    private String mSelectAddressName;
    private Wallet mWallet;
    private NumberFormat numberFormat;
    private NumberFormat numberFormat1;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    View rlGetShow;
    RelativeLayout rlStaked;
    SimpleDraweeView simpleDraweeView;
    private DataStatHelper.StatAction statAction;
    TextView tvAutoDeducttips;
    TextView tvAvailableAmount;
    private TextView tvCurrentPercentBg;
    TextView tvErrortips;
    TextView tvPercent100;
    TextView tvPercent25;
    TextView tvPercent50;
    TextView tvPercent75;
    TextView tvREsourcePerDay;
    TextView tvResourceAmount;
    TextView tvResourcePerTransaction;
    TextView tvStakeAmount;
    TextView tvStakeBandwidth;
    TextView tvStakeEnergy;
    TextView tvUnStatke;
    TextView tvUnderControl;
    TextView tvVotesAmount;
    private boolean isFreezeBandwidth = false;
    private double canUseTrxCount = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private boolean isMultisign = false;
    private long percentCount = -1;
    private double leftCount = -1.0d;
    private int percentIndex = -1;

    private double getBandWidth() {
        return this.isMultisign ? 550.0d : 397.5d;
    }

    public static void lambda$showPop$0(View view) {
    }

    private void resetBottomInput() {
        this.bandwidthInput = "";
        this.energyInput = "";
    }

    @Override
    public Protocol.Account getAccount() {
        return this.mAccount;
    }

    @Override
    public GrpcAPI.AccountResourceMessage getAccountResMessage() {
        return this.mAccountResMessage;
    }

    @Override
    public double getCanUseTrxCount() {
        return this.canUseTrxCount;
    }

    @Override
    public Wallet getDefaultWallet() {
        return this.mWallet;
    }

    @Override
    public EditText getEtAmount() {
        return this.etAmount;
    }

    @Override
    public Button getNextButton() {
        return this.btnNextStep;
    }

    @Override
    public String getSelectedAddress() {
        return this.mSelectAddress;
    }

    @Override
    public String getSelectedAddressName() {
        return this.mSelectAddressName;
    }

    @Override
    public DataStatHelper.StatAction getStatAction() {
        return this.statAction;
    }

    @Override
    public boolean isFreezeBandwidth() {
        return this.isFreezeBandwidth;
    }

    @Override
    public boolean isMultisign() {
        return this.isMultisign;
    }

    public static void start(Context context, String str, String str2, Protocol.Account account, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent(context, StakeTRXActivity.class);
        intent.putExtra("address", str);
        intent.putExtra("key_statke_address_name", str2);
        intent.putExtra(ACCOUNT, account);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    public static void start(final Context context, final Protocol.Account account) {
        if (TronSetting.stakeV2) {
            StakeTRX2Activity.start(context, account);
        } else {
            AccountUtils.getInstance().checkAccountIsActivatedFromLocal(context, new CheckAccountActivatedCallback() {
                @Override
                public void isActivated() {
                    Intent intent = new Intent(context, StakeTRXActivity.class);
                    intent.putExtra(StakeTRXActivity.ACCOUNT, account);
                    context.startActivity(intent);
                }
            }, new CheckAccountNotActivatedCallback() {
                @Override
                public void notActivated() {
                    StakeTRXActivity.showPop(context);
                }
            });
        }
    }

    public static void start(final Context context, final Protocol.Account account, final DataStatHelper.StatAction statAction) {
        if (TronSetting.stakeV2) {
            StakeTRX2Activity.startWithCheckOwnerPermission(context, true, account, statAction, "");
        } else {
            AccountUtils.getInstance().checkAccountIsActivatedFromLocal(context, new CheckAccountActivatedCallback() {
                @Override
                public void isActivated() {
                    Intent intent = new Intent(context, StakeTRXActivity.class);
                    intent.putExtra(StakeTRXActivity.ACCOUNT, account);
                    intent.putExtra(TronConfig.StatAction_Key, statAction);
                    context.startActivity(intent);
                }
            }, new CheckAccountNotActivatedCallback() {
                @Override
                public void notActivated() {
                    StakeTRXActivity.showPop(context);
                }
            });
        }
    }

    public static void showPop(final Context context) {
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                ConfirmCustomPopupView.getBuilder(r0).setTitle(r0.getResources().getString(R.string.stake_account_unactive)).setTitleBold(true).setTitleSize(16).setConfirmText(r0.getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        StakeTRXActivity.lambda$showPop$0(view);
                    }
                }).setShowCancelBtn(true).setCancelText(r0.getResources().getString(R.string.multisig_statking)).setCancleListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        StakeTRXActivity.lambda$showPop$1(r1, view);
                    }
                }).build().show();
            }
        });
    }

    public static void lambda$showPop$1(Context context, View view) {
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.MultisigStaking.CLICK_DIALOG);
        if (TronSetting.stakeV2) {
            MultiSelectAddressActivity.start(context, MultiSourcePageEnum.StakeV2);
        } else {
            MultiSelectAddressActivity.start(context, MultiSourcePageEnum.Stake);
        }
    }

    public static void startWithCheckOwnerPermission(final Context context, final Protocol.Account account, final DataStatHelper.StatAction statAction, String str) {
        OwnerPermissionCheckUtils.checkStakeWithPopup(context, account, R.string.stake_account_unactive, R.string.lack_of_stake_permission, new Consumer() {
            @Override
            public final void accept(Object obj) {
                Void r4 = (Void) obj;
                StakeTRXActivity.start(context, account, statAction);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                StakeTRXActivity.lambda$startWithCheckOwnerPermission$4(context, statAction, (Void) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public static void lambda$startWithCheckOwnerPermission$4(Context context, DataStatHelper.StatAction statAction, Void r2) {
        if (TronSetting.stakeV2) {
            MultiSelectAddressActivity.start(context, MultiSourcePageEnum.StakeV2, statAction);
        } else {
            MultiSelectAddressActivity.start(context, MultiSourcePageEnum.Stake, statAction);
        }
    }

    @Override
    protected void setLayout() {
        setBackground(R.color.white, 0);
        AcStakeTrxBinding inflate = AcStakeTrxBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBar(getString(R.string.stake_title));
        setCommonRight2(getResources().getString(R.string.multistake));
        getHeaderHolder().tvCommonRight2.setTextSize(14.0f);
        getHeaderHolder().tvCommonRight2.setTextColor(getResources().getColor(R.color.blue_3c));
        getHeaderHolder().tvCommonTitle2.setText(R.string.step_1_2);
        getHeaderHolder().tvCommonTitle2.setTextColor(getResources().getColor(R.color.black_6d));
        getHeaderHolder().ivCommonTitle2.setBackground(getResources().getDrawable(R.mipmap.ic_stake_trx_tips));
        if (this.isMultisign) {
            getHeaderHolder().ivCommonTitle2.setVisibility(View.GONE);
        } else {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.PV_BASE_1_2_TAG);
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getHeaderHolder().ivCommonTitle2.getLayoutParams();
        marginLayoutParams.bottomMargin = UIUtils.dip2px(2.0f);
        marginLayoutParams.leftMargin = UIUtils.dip2px(5.0f);
        getHeaderHolder().ivCommonTitle2.setLayoutParams(marginLayoutParams);
        getHeaderHolder().ivCommonTitle2.setVisibility(View.VISIBLE);
        TouchDelegateUtils.expandViewTouchDelegate(getHeaderHolder().ivCommonTitle2, 10);
        getHeaderHolder().ivCommonTitle2.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (isMultisign) {
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_TITLE_2_3_QUESTION_MARK);
                } else {
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_TITLE_QUESTION_MARK);
                }
                TrxInstructionsActivity.start(mContext, StringTronUtil.getResString(R.string.trc_stake_unstake_instructions), StringTronUtil.getResString(R.string.stake_instruction_1), mContext.getString(R.string.stake_instruction_2, numberFormat.format(TronConfig.bandwidthForFree)), StringTronUtil.getResString(R.string.stake_instruction_3), StringTronUtil.getResString(R.string.stake_instruction_4), StringTronUtil.getResString(R.string.stake_instruction_5), "", true);
                overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            }
        });
        getHeaderHolder().tvCommonRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndEnterMultiSign();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvStakeAmount = this.binding.tvStakeAmount;
        this.rlStaked = this.binding.rlStaked;
        this.tvUnStatke = this.binding.tvStakeUnstake;
        this.ivUnStatkeArrow = this.binding.ivStakeUnstakeArrow;
        this.tvStakeEnergy = this.binding.tvStakeEnergy;
        this.tvStakeBandwidth = this.binding.tvStakeBandwidth;
        this.btnNextStep = this.binding.btnNextStep;
        this.tvUnderControl = this.binding.tvUnderControlTips;
        this.simpleDraweeView = this.binding.ivTrx;
        this.etAmount = this.binding.etAmount;
        this.tvAvailableAmount = this.binding.tvAvailableAmount;
        this.tvREsourcePerDay = this.binding.tvResourcePerDay;
        this.tvPercent25 = this.binding.amountPercent25;
        this.tvPercent50 = this.binding.amountPercent50;
        this.tvPercent75 = this.binding.amountPercent75;
        this.tvPercent100 = this.binding.amountPercent100;
        this.tvAutoDeducttips = this.binding.tvAutoDeductTips;
        this.liErrorTip = this.binding.liErrorTips;
        this.tvErrortips = this.binding.tvErrorTips;
        this.llRoot = this.binding.root;
        this.llScroll = this.binding.scrollView;
        this.liBottom = this.binding.liBottomOption;
        this.tvResourceAmount = this.binding.tvResourceGetAmount;
        this.tvResourcePerTransaction = this.binding.tvResourcePerTransaction;
        this.tvVotesAmount = this.binding.tvVoteGetAmount;
        this.llBg = this.binding.llBg;
        this.edInputRes = this.binding.edInputRes;
        this.rlGetShow = this.binding.rlGetShow;
        this.ivStakeEdit = this.binding.ivStakeEdit;
        this.llGetBg = this.binding.llGetBg;
        this.ivTips = this.binding.ivTips;
        this.ivArrow = this.binding.ivArrow;
    }

    @Override
    protected void processData() {
        this.mWallet = WalletUtils.getSelectedWallet();
        if (getIntent() != null && getIntent().hasExtra("address")) {
            this.isMultisign = true;
            this.mSelectAddress = getIntent().getStringExtra("address");
            this.mSelectAddressName = getIntent().getStringExtra("key_statke_address_name");
            getHeaderHolder().tvCommonRight2.setVisibility(View.GONE);
            getHeaderHolder().tvCommonTitle2.setText(R.string.step_2_3);
            getHeaderHolder().tvCommonTitle2.setTextColor(getResources().getColor(R.color.black_6d));
            setHeaderBar(getString(R.string.multistake));
            this.tvUnStatke.setVisibility(View.GONE);
            this.ivUnStatkeArrow.setVisibility(View.GONE);
            getHeaderHolder().ivCommonTitle2.setVisibility(View.GONE);
        } else {
            this.mSelectAddress = this.mWallet.getAddress();
            this.mSelectAddressName = this.mWallet.getWalletName();
        }
        this.mAccount = (Protocol.Account) getIntent().getSerializableExtra(ACCOUNT);
        this.statAction = (DataStatHelper.StatAction) getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(0);
        NumberFormat numberInstance2 = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat1 = numberInstance2;
        numberInstance2.setMaximumFractionDigits(6);
        ensureAccount(this.mAccount).subscribe(new IObserver<>(getEnsureAccountCallback(), "ensure account"));
        updateUI(this.mAccount, this.mAccountResMessage);
        ((StakeTRXPresenter) this.mPresenter).init(this.mAccount);
        if (this.isMultisign) {
            this.tvUnderControl.setVisibility(View.VISIBLE);
            final String str = this.mSelectAddress;
            if (!TextUtils.isEmpty(this.mSelectAddressName)) {
                str = String.format("%s(%s)", this.mSelectAddressName, str);
            }
            this.tvUnderControl.setText(getString(R.string.current_under_control, new Object[]{str}));
            this.tvUnderControl.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$processData$5(str);
                }
            });
        } else {
            this.tvUnderControl.setVisibility(View.GONE);
        }
        SoftHideKeyBoardUtil.assistActivity(this, new SoftHideKeyBoardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int i) {
                liBottom.setVisibility(View.GONE);
            }

            @Override
            public void keyBoardHide(int i) {
                liBottom.setVisibility(View.VISIBLE);
                checkBtnEnable();
                edInputRes.setVisibility(View.GONE);
                rlGetShow.setVisibility(View.VISIBLE);
                etAmount.clearFocus();
                edInputRes.clearFocus();
            }
        });
        TextDotUtils.setTextChangedListener_Dot(this.etAmount);
        TextDotUtils.setTextChangedListener_Dot(this.edInputRes);
        Collection.-EL.stream(Arrays.asList(this.binding.amountPercent25, this.binding.amountPercent50, this.binding.amountPercent75, this.binding.amountPercent100, this.binding.tvStakeUnstake, this.binding.btnNextStep, this.binding.tvStakeEnergy, this.binding.tvStakeBandwidth, this.binding.ivStakeEdit, this.binding.rlTips)).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$6((View) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.etAmount.addTextChangedListener(new TextWatcher() {
            private String lastEditable;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                double d;
                String str2 = this.lastEditable;
                if (str2 == null || !str2.equals(editable.toString())) {
                    this.lastEditable = editable.toString();
                    updateInfo();
                    if (StringTronUtil.isEmpty(editable.toString())) {
                        if (liErrorTip.getVisibility() == 0) {
                            liErrorTip.setVisibility(View.GONE);
                            updateBtStatus(false);
                        }
                        if (tvAutoDeducttips.getVisibility() == 0) {
                            tvAutoDeducttips.setVisibility(View.GONE);
                        }
                        resetSelectorBackground();
                        percentIndex = -1;
                        return;
                    }
                    if (editable.toString().equals("0")) {
                        if (tvAutoDeducttips.getVisibility() == 0 && percentIndex < 1) {
                            tvAutoDeducttips.setVisibility(View.GONE);
                        }
                    } else if (editable.toString().startsWith("0") && editable.toString().length() > 1) {
                        etAmount.setText(editable.toString().substring(1));
                        return;
                    }
                    try {
                        d = numberFormat.parse(editable.toString()).doubleValue();
                    } catch (ParseException e) {
                        LogUtils.e(e);
                        d = 0.0d;
                    }
                    if (tvAutoDeducttips.getVisibility() == 0 && percentCount != d) {
                        tvAutoDeducttips.setVisibility(View.GONE);
                    }
                    if (percentCount != d) {
                        resetSelectorBackground();
                        percentIndex = -1;
                    }
                    if (percentIndex < 1) {
                        if (d > canUseTrxCount) {
                            tvErrortips.setText(R.string.insufficient_trx);
                            liErrorTip.setVisibility(View.VISIBLE);
                        } else if (canUseTrxCount < 1.0d) {
                            tvErrortips.setText(R.string.stake_min_1_trx);
                            liErrorTip.setVisibility(View.VISIBLE);
                        } else if (d < 1.0d) {
                            tvErrortips.setText(R.string.stake_min_1_trx);
                            liErrorTip.setVisibility(View.VISIBLE);
                        } else if ((canUseTrxCount - d) - getRealFee() >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            liErrorTip.setVisibility(View.GONE);
                        } else {
                            tvErrortips.setText(R.string.stake_not_enough_for_fee);
                            liErrorTip.setVisibility(View.VISIBLE);
                        }
                    }
                    if (checkBtnEnable()) {
                        updateBtStatus(true);
                    } else {
                        updateBtStatus(false);
                    }
                }
            }
        });
        this.etAmount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    if (isMultisign) {
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_2_3_INPUT);
                        return false;
                    }
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_INPUT);
                    return false;
                }
                return false;
            }
        });
        this.edInputRes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                String replaceAll = obj.contains(",") ? obj.replaceAll(",", "") : obj;
                if (!StringTronUtil.isEmpty(obj)) {
                    if (obj.startsWith("0") && obj.length() > 1) {
                        edInputRes.setText(obj.substring(1));
                        edInputRes.setSelection(obj.length() - 1);
                        return;
                    } else if (edInputRes.hasFocus()) {
                        if (isFreezeBandwidth) {
                            bandwidthInput = obj;
                            energyInput = "0";
                            bandwidthTRXInput = BigDecimalUtils.toString(bandwidth2TRX(BigDecimalUtils.toBigDecimal(replaceAll)));
                            TextDotUtils.setText_Dot(etAmount, bandwidthTRXInput);
                        } else {
                            energyInput = obj;
                            bandwidthInput = "0";
                            energyTRXInput = BigDecimalUtils.toString(energy2TRX(BigDecimalUtils.toBigDecimal(replaceAll)));
                            TextDotUtils.setText_Dot(etAmount, energyTRXInput);
                        }
                        checkResForDay(replaceAll);
                        TextDotUtils.setText_Dot(tvResourceAmount, replaceAll);
                        return;
                    } else {
                        return;
                    }
                }
                if (isFreezeBandwidth) {
                    bandwidthInput = "0";
                } else {
                    energyInput = "0";
                }
                etAmount.setText("");
            }
        });
        this.edInputRes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    edInputRes.setVisibility(View.GONE);
                    setArrowStatus(false);
                    return;
                }
                setArrowStatus(true);
            }
        });
        this.etAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    edInputRes.setVisibility(View.GONE);
                    rlGetShow.setVisibility(View.VISIBLE);
                }
            }
        });
        initEditHint();
        if (this.isFreezeBandwidth) {
            this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_bandwidth_tips), "0"));
        } else {
            this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_energy_tips), "0"));
        }
        ((StakeTRXPresenter) this.mPresenter).getData();
        if (this.isMultisign) {
            AnalyticsHelper.AssetPage.logEvent("MultisigStaking2_3");
        }
    }

    public void lambda$processData$5(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvUnderControl, str);
        this.tvUnderControl.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    public void lambda$processData$6(View view) {
        view.setOnClickListener(getOnClickListener());
    }

    @Override
    public void updateUI(Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        if (account == null) {
            return;
        }
        this.mAccount = account;
        if (this.mAccountResMessage == null) {
            this.mAccountResMessage = accountResourceMessage;
        }
        if (account != null) {
            this.canUseTrxCount = BigDecimalUtils.div(account.getBalance(), 1000000.0d, 6);
        }
        try {
            TextView textView = this.tvAvailableAmount;
            textView.setText(this.numberFormat1.format(this.canUseTrxCount) + " TRX");
        } catch (Resources.NotFoundException e) {
            LogUtils.e(e);
        }
        long delegatedFrozenBalanceForBandwidth = account.getDelegatedFrozenBalanceForBandwidth();
        int frozenCount = account.getFrozenCount();
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        for (int i = 0; i < frozenCount; i++) {
            d += account.getFrozen(i).getFrozenBalance();
        }
        long div = (long) BigDecimalUtils.div(d + delegatedFrozenBalanceForBandwidth + account.getAccountResource().getDelegatedFrozenBalanceForEnergy() + account.getAccountResource().getFrozenBalanceForEnergy().getFrozenBalance(), 1000000.0d, 6);
        TextView textView2 = this.tvStakeAmount;
        textView2.setText(this.numberFormat.format(div) + " TRX");
        if (div < 1) {
            this.tvUnStatke.setVisibility(View.GONE);
            this.ivUnStatkeArrow.setVisibility(View.GONE);
        }
    }

    private Observable<Protocol.Account> ensureAccount(final Protocol.Account account) {
        return ((StakeTRXPresenter) this.mPresenter).queryAccount(this.mSelectAddressName, this.mSelectAddress).compose(new ObservableTransformer() {
            @Override
            public final ObservableSource apply(Observable observable) {
                ObservableSource lambda$ensureAccount$8;
                lambda$ensureAccount$8 = lambda$ensureAccount$8(account, observable);
                return lambda$ensureAccount$8;
            }
        });
    }

    public ObservableSource lambda$ensureAccount$8(final Protocol.Account account, Observable observable) {
        return ((StakeTRXPresenter) this.mPresenter).isNullAccount(account) ? observable : Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                StakeTRXActivity.lambda$ensureAccount$7(Protocol.Account.this, observableEmitter);
            }
        });
    }

    public static void lambda$ensureAccount$7(Protocol.Account account, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(account);
        observableEmitter.onComplete();
    }

    private ICallback<Protocol.Account> getEnsureAccountCallback() {
        return new ICallback<Protocol.Account>() {
            @Override
            public void onResponse(String str, Protocol.Account account) {
                if (mAccount == null) {
                    mAccount = account;
                }
                StakeTRXActivity stakeTRXActivity = StakeTRXActivity.this;
                stakeTRXActivity.hasOwnerPermission = ((StakeTRXPresenter) stakeTRXActivity.mPresenter).hasOwnerPermission(mSelectAddress, mAccount);
                if (!isMultisign && AccountUtils.checkAccountIsNotActivated(mAccount)) {
                    showMultiSignDialog();
                } else if (!isMultisign && !hasOwnerPermission) {
                    showMultiSignDialog();
                }
                StakeTRXActivity stakeTRXActivity2 = StakeTRXActivity.this;
                stakeTRXActivity2.updateUI(stakeTRXActivity2.mAccount, mAccountResMessage);
            }

            @Override
            public void onFailure(String str, String str2) {
                StakeTRXActivity stakeTRXActivity = StakeTRXActivity.this;
                stakeTRXActivity.toast(stakeTRXActivity.getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                ((StakeTRXPresenter) mPresenter).mRxManager.add(disposable);
            }
        };
    }

    @Override
    public void setErrorCountStatus(boolean z, int i) {
        setErrorCountStatus(z, getResources().getString(i));
    }

    @Override
    public void updateBtStatus(boolean z) {
        this.btnNextStep.setEnabled(z);
    }

    public void setErrorCountStatus(boolean z, String str) {
        this.tvErrortips.setText(str);
        setErrorCountStatus(z);
    }

    @Override
    public void setErrorCountStatus(boolean z) {
        if (z) {
            this.liErrorTip.setVisibility(View.VISIBLE);
            updateBtStatus(false);
            return;
        }
        if (this.liErrorTip.getVisibility() == 0) {
            this.liErrorTip.setVisibility(View.GONE);
        }
        updateBtStatus(true);
    }

    @Override
    public String getResourceCount() {
        if (StringTronUtil.isEmpty(this.tvResourceAmount.getText().toString())) {
            return "0";
        }
        try {
            return "" + this.numberFormat.parse(this.tvResourceAmount.getText().toString()).longValue();
        } catch (ParseException e) {
            LogUtils.e(e);
            return "0";
        }
    }

    public void showMultiSignDialog() {
        ConfirmCustomPopupView.getBuilder(this).setTitle(getString(R.string.lack_of_stake_permission)).setTitleSize(16).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
                exit();
            }
        }).setConfirmText(getString(R.string.multisig)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$9(view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$10(view);
            }
        }).build().show();
    }

    public void lambda$showMultiSignDialog$9(View view) {
        exit();
    }

    public void lambda$showMultiSignDialog$10(View view) {
        checkAndEnterMultiSign();
        exit();
    }

    public void checkAndEnterMultiSign() {
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.MultisigStaking.CLICK_RIGHT_TEXT);
        enterMultiSign();
    }

    private void enterMultiSign() {
        try {
            MultiSelectAddressActivity.start(this, MultiSourcePageEnum.Stake);
            exit();
        } catch (Throwable th) {
            LogUtils.e(th);
        }
    }

    public void setTvPercentBg(TextView textView) {
        if (textView == null) {
            return;
        }
        resetSelectorBackground();
        this.tvCurrentPercentBg = textView;
        if (this.isFreezeBandwidth) {
            textView.setBackgroundResource(R.drawable.roundborder_57bfad);
        } else {
            textView.setBackgroundResource(R.drawable.roundborder_e2b380);
        }
        textView.setTextColor(getResources().getColor(R.color.white));
    }

    public double getRealFee() {
        if (BandwidthUtils.isBandwidthEnough(getIContext(), this.mAccountResMessage, (long) getBandWidth())) {
            if (this.isMultisign) {
                return 1.0d;
            }
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        return getMaxFee();
    }

    public boolean checkBtnEnable() {
        double d;
        CurrencyEditText currencyEditText = this.etAmount;
        if (currencyEditText != null && StringTronUtil.isEmpty(currencyEditText.getText().toString())) {
            updateBtStatus(false);
            return false;
        }
        try {
            d = this.numberFormat.parse(this.etAmount.getText().toString()).doubleValue();
        } catch (ParseException e) {
            LogUtils.e(e);
            d = 0.0d;
        }
        return d != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && this.canUseTrxCount >= d + getRealFee();
    }

    private void initEditHint() {
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.hint_enter_stake_amount));
        spannableString.setSpan(new AbsoluteSizeSpan(16, true), 0, spannableString.length(), 33);
        this.etAmount.setHint(new SpannedString(spannableString));
    }

    public void updateInfo() {
        try {
            if (!TextUtils.isEmpty(this.etAmount.getText().toString())) {
                this.tvVotesAmount.setText(this.etAmount.getText().toString());
                try {
                    BigDecimal bigDecimal = new BigDecimal(this.numberFormat.parse(this.etAmount.getText().toString().trim()).doubleValue());
                    if (this.etAmount.getText().toString().length() <= 0) {
                        bigDecimal = new BigDecimal(0);
                    }
                    this.mFreezeAmount = bigDecimal;
                } catch (Exception e) {
                    LogUtils.e(e);
                    this.etAmount.setText("0");
                    this.mFreezeAmount = new BigDecimal(0);
                }
                if (compareWithZero(this.mFreezeAmount, BigDecimal.ZERO) > 0 && !this.etAmount.getText().toString().equals("-0")) {
                    if (this.edInputRes.hasFocus()) {
                        return;
                    }
                    if (this.isFreezeBandwidth) {
                        if (StringTronUtil.isEmpty(this.bandwidthInput) || "0".equals(this.bandwidthInput) || this.etAmount.hasFocus()) {
                            this.bandwidthInput = "0";
                            LogUtils.i("abcdd", "trx->res");
                            BigDecimal expectGetBandWidth = expectGetBandWidth(this.mFreezeAmount);
                            TextDotUtils.setText_Dot(this.tvResourceAmount, expectGetBandWidth.toPlainString());
                            int floor = (int) Math.floor(BigDecimalUtils.div(expectGetBandWidth, new BigDecimal(SpAPI.THIS.getBandwidthPerTranscation())).doubleValue());
                            if (floor > 0) {
                                this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_bandwidth_tips), TextDotUtils.getDotText(String.valueOf(floor))));
                                return;
                            } else {
                                this.tvResourcePerTransaction.setText(R.string.stake_get_less_one);
                                return;
                            }
                        }
                        return;
                    } else if (StringTronUtil.isEmpty(this.energyInput) || "0".equals(this.energyInput) || this.etAmount.hasFocus()) {
                        this.energyInput = "0";
                        LogUtils.i("abcdd", "trx->res");
                        BigDecimal expectGetEnergy = expectGetEnergy(this.mFreezeAmount);
                        TextDotUtils.setText_Dot(this.tvResourceAmount, expectGetEnergy.toPlainString());
                        int floor2 = (int) Math.floor(BigDecimalUtils.div(expectGetEnergy, new BigDecimal(SpAPI.THIS.getEnergyPerTranscation())).doubleValue());
                        if (floor2 > 0) {
                            this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_energy_tips), TextDotUtils.getDotText(String.valueOf(floor2))));
                            return;
                        } else {
                            this.tvResourcePerTransaction.setText(R.string.stake_get_less_one);
                            return;
                        }
                    } else {
                        return;
                    }
                }
                this.etAmount.setText("0");
                this.tvResourceAmount.setText("0");
                this.tvVotesAmount.setText("0");
                if (this.isFreezeBandwidth) {
                    this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_bandwidth_tips), "0"));
                    return;
                } else {
                    this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_energy_tips), "0"));
                    return;
                }
            }
            this.tvResourceAmount.setText("0");
            this.tvVotesAmount.setText("0");
            if (this.isFreezeBandwidth) {
                this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_bandwidth_tips), "0"));
            } else {
                this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_energy_tips), "0"));
            }
        } catch (Exception e2) {
            LogUtils.e(e2);
            SentryUtil.captureException(e2);
        }
    }

    private void resetBottomView() {
        KeyboardUtil.closeKeybord(this);
        this.rlGetShow.setVisibility(View.VISIBLE);
        this.edInputRes.setVisibility(View.GONE);
    }

    public void resetBottomInputView() {
        resetBottomView();
        resetBottomInput();
    }

    private View.OnClickListener getOnClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.amount_percent_100:
                        resetBottomInputView();
                        StakeTRXActivity stakeTRXActivity = StakeTRXActivity.this;
                        stakeTRXActivity.setTvPercentBg(stakeTRXActivity.tvPercent100);
                        percentIndex = 4;
                        setEtAmount(1.0f);
                        return;
                    case R.id.amount_percent_25:
                        resetBottomInputView();
                        StakeTRXActivity stakeTRXActivity2 = StakeTRXActivity.this;
                        stakeTRXActivity2.setTvPercentBg(stakeTRXActivity2.tvPercent25);
                        percentIndex = 1;
                        setEtAmount(0.25f);
                        return;
                    case R.id.amount_percent_50:
                        resetBottomInputView();
                        StakeTRXActivity stakeTRXActivity3 = StakeTRXActivity.this;
                        stakeTRXActivity3.setTvPercentBg(stakeTRXActivity3.tvPercent50);
                        percentIndex = 2;
                        setEtAmount(0.5f);
                        return;
                    case R.id.amount_percent_75:
                        resetBottomInputView();
                        StakeTRXActivity stakeTRXActivity4 = StakeTRXActivity.this;
                        stakeTRXActivity4.setTvPercentBg(stakeTRXActivity4.tvPercent75);
                        percentIndex = 3;
                        setEtAmount(0.75f);
                        return;
                    case R.id.btn_next_step:
                        if (isMultisign) {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_2_3_NEXT);
                        } else {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_NEXT_BTN);
                        }
                        ((StakeTRXPresenter) mPresenter).stake();
                        return;
                    case R.id.iv_stake_edit:
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_RESOURCE_EDIT);
                        String charSequence = tvResourceAmount.getText().toString();
                        if (charSequence.contains(",")) {
                            charSequence = charSequence.replaceAll(",", "");
                        }
                        edInputRes.setText(charSequence);
                        rlGetShow.setVisibility(View.GONE);
                        edInputRes.setVisibility(View.VISIBLE);
                        edInputRes.requestFocus();
                        if (edInputRes.getText().toString() != null) {
                            edInputRes.setSelection(edInputRes.getText().toString().length());
                        }
                        SoftHideKeyBoardUtil.showSoftInput(mContext, edInputRes);
                        return;
                    case R.id.rl_tips:
                        KeyboardUtil.closeKeybord(StakeTRXActivity.this);
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_ESTIMATED_TO_GET);
                        PopupWindowUtil.showPopupText(mContext, StringTronUtil.getResString(R.string.stake_tips), ivTips, false);
                        return;
                    case R.id.tv_stake_bandwidth:
                        if (isMultisign) {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_BANDWIDTH);
                        } else {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_BANDWIDTH_TAG);
                        }
                        switchEnergyBandwidth(false);
                        if (!StringTronUtil.isEmpty(bandwidthInput) && !bandwidthInput.equals("0") && !etAmount.hasFocus() && (StringTronUtil.isEmpty(energyInput) || "0".equals(energyInput))) {
                            LogUtils.i("abcdd", "res->trx");
                            String replaceAll = bandwidthInput.contains(",") ? bandwidthInput.replaceAll(",", "") : bandwidthInput;
                            etAmount.setText(BigDecimalUtils.toString(bandwidth2TRX(BigDecimalUtils.toBigDecimal(replaceAll))));
                            TextDotUtils.setText_Dot(tvResourceAmount, replaceAll);
                            checkResForDay(replaceAll);
                        }
                        StakeTRXActivity stakeTRXActivity5 = StakeTRXActivity.this;
                        stakeTRXActivity5.setTvPercentBg(stakeTRXActivity5.tvCurrentPercentBg);
                        return;
                    case R.id.tv_stake_energy:
                        if (isMultisign) {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_ENERGY);
                        } else {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_ENERGY_TAG);
                        }
                        switchEnergyBandwidth(true);
                        if (!StringTronUtil.isEmpty(energyInput) && !energyInput.equals("0") && !etAmount.hasFocus() && (StringTronUtil.isEmpty(bandwidthInput) || "0".equals(bandwidthInput))) {
                            LogUtils.i("abcdd", "res->trx");
                            String replaceAll2 = energyInput.contains(",") ? energyInput.replaceAll(",", "") : energyInput;
                            etAmount.setText(BigDecimalUtils.toString(energy2TRX(BigDecimalUtils.toBigDecimal(replaceAll2))));
                            TextDotUtils.setText_Dot(tvResourceAmount, replaceAll2);
                            checkResForDay(replaceAll2);
                        }
                        StakeTRXActivity stakeTRXActivity6 = StakeTRXActivity.this;
                        stakeTRXActivity6.setTvPercentBg(stakeTRXActivity6.tvCurrentPercentBg);
                        return;
                    case R.id.tv_stake_unstake:
                        StakeTRXActivity stakeTRXActivity7 = StakeTRXActivity.this;
                        UnStakeActivity.start(stakeTRXActivity7, stakeTRXActivity7.mAccount);
                        AnalyticsHelper.logEvent(AnalyticsHelper.UnStakeGate.CLICK_FROM_STAKE);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void switchEnergyBandwidth(boolean z) {
        if (this.isFreezeBandwidth == (!z)) {
            return;
        }
        this.edInputRes.clearFocus();
        this.etAmount.clearFocus();
        KeyboardUtil.closeKeybord(this);
        boolean z2 = !z;
        this.isFreezeBandwidth = z2;
        if (z2) {
            this.simpleDraweeView.setImageResource(R.mipmap.icon_stake_bandwidth);
            this.llBg.setBackgroundResource(R.drawable.roundborder_f4fbfa_16);
            this.llGetBg.setBackgroundResource(R.drawable.roundborder_15_57bfad_8);
            this.tvStakeEnergy.setBackgroundResource(R.color.transparent);
            this.tvStakeBandwidth.setBackgroundResource(R.drawable.roundborder_tab_stake_trx_2);
            this.tvStakeEnergy.setTextColor(getResources().getColor(R.color.black_23));
            this.tvStakeBandwidth.setTextColor(getResources().getColor(R.color.white));
            this.tvREsourcePerDay.setText(R.string.stake_get_bandwidth_per_day);
        } else {
            this.simpleDraweeView.setImageResource(R.mipmap.icon_stake_energy);
            this.llBg.setBackgroundResource(R.drawable.roundborder_fffbf6_16);
            this.llGetBg.setBackgroundResource(R.drawable.roundborder_20_e0b688_8);
            this.tvStakeEnergy.setBackgroundResource(R.drawable.roundborder_tab_stake_trx);
            this.tvStakeBandwidth.setBackgroundResource(R.color.transparent);
            this.tvStakeEnergy.setTextColor(getResources().getColor(R.color.white));
            this.tvStakeBandwidth.setTextColor(getResources().getColor(R.color.black_23));
            this.tvREsourcePerDay.setText(R.string.stake_get_energy_per_day);
        }
        this.edInputRes.setVisibility(View.GONE);
        this.rlGetShow.setVisibility(View.VISIBLE);
        updateInfo();
    }

    private double getMaxFee() {
        if (this.isMultisign) {
            return (getBandWidth() * TronConfig.feeBandWidth) + 1.0d;
        }
        return getBandWidth() * TronConfig.feeBandWidth;
    }

    public void setEtAmount(float f) {
        double d;
        if (this.isMultisign) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_PERCENT);
        } else {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_PERCENT_BUTTON);
        }
        if (this.canUseTrxCount >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.liErrorTip.setVisibility(View.GONE);
            double d2 = f;
            this.percentCount = (long) Math.floor(this.canUseTrxCount * d2);
            if (f != 1.0f) {
                if (this.isMultisign) {
                    double d3 = this.canUseTrxCount;
                    double floor = d3 - Math.floor(d2 * d3);
                    this.leftCount = floor;
                    if (floor <= 1.0d) {
                        this.percentCount--;
                    }
                    if (this.percentCount < 0) {
                        this.percentCount = 0L;
                    }
                }
                if (this.tvAutoDeducttips.getVisibility() == 0) {
                    this.tvAutoDeducttips.setVisibility(View.GONE);
                }
                if (this.percentCount < 1) {
                    this.tvErrortips.setText(R.string.stake_min_1_trx);
                    this.liErrorTip.setVisibility(View.VISIBLE);
                }
                this.etAmount.setText(this.numberFormat.format(this.percentCount) + "");
            } else if (this.mAccountResMessage != null) {
                if (BandwidthUtils.isBandwidthEnough(getIContext(), this.mAccountResMessage, (long) getBandWidth())) {
                    if (this.isMultisign) {
                        this.percentCount--;
                        this.tvAutoDeducttips.setText(R.string.stake_fee_for_multisig);
                        this.tvAutoDeducttips.setVisibility(View.VISIBLE);
                        if (this.percentCount < 0) {
                            this.percentCount = 0L;
                        }
                    }
                    this.etAmount.setText(this.numberFormat.format(this.percentCount) + "");
                } else {
                    long floor2 = (int) Math.floor(this.canUseTrxCount - getMaxFee());
                    this.percentCount = floor2;
                    if (floor2 < 0) {
                        this.percentCount = 0L;
                    }
                    this.tvAutoDeducttips.setText(R.string.stake_insufficient_bandwidth_for);
                    this.tvAutoDeducttips.setVisibility(View.VISIBLE);
                    this.etAmount.setText(this.numberFormat.format(this.percentCount) + "");
                }
            }
        }
        try {
            d = this.numberFormat.parse(this.etAmount.getText().toString()).doubleValue();
        } catch (ParseException e) {
            LogUtils.e(e);
            d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        double d4 = this.canUseTrxCount;
        this.leftCount = d4 - Math.floor(f * d4);
        if (this.percentIndex > -1) {
            if (this.isMultisign) {
                if (BandwidthUtils.isBandwidthEnough(this.mContext, this.mAccountResMessage, (long) getBandWidth())) {
                    if (this.leftCount - 1.0d <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        this.tvAutoDeducttips.setText(getString(R.string.stake_fee_for_multisig));
                        this.tvAutoDeducttips.setVisibility(View.VISIBLE);
                    } else if (d < 1.0d) {
                        this.tvErrortips.setText(R.string.stake_min_1_trx);
                        this.liErrorTip.setVisibility(View.VISIBLE);
                    }
                } else if (this.leftCount > getBandWidth() * TronConfig.feeBandWidth) {
                    this.tvAutoDeducttips.setVisibility(View.GONE);
                    if (this.leftCount - getRealFee() >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        this.tvAutoDeducttips.setVisibility(View.GONE);
                        this.liErrorTip.setVisibility(View.GONE);
                        if (d < 1.0d) {
                            this.tvErrortips.setText(R.string.stake_min_1_trx);
                            this.liErrorTip.setVisibility(View.VISIBLE);
                        }
                    } else {
                        this.tvAutoDeducttips.setText(getString(R.string.stake_fee_for_multisig));
                        this.tvAutoDeducttips.setVisibility(View.VISIBLE);
                    }
                } else {
                    this.tvAutoDeducttips.setText(R.string.stake_insufficient_bandwidth_for);
                    this.tvAutoDeducttips.setVisibility(View.VISIBLE);
                }
            } else if (BandwidthUtils.isBandwidthEnough(this.mContext, this.mAccountResMessage, (long) getBandWidth())) {
                if (d < 1.0d) {
                    this.tvErrortips.setText(R.string.stake_min_1_trx);
                    this.liErrorTip.setVisibility(View.VISIBLE);
                }
            } else if (this.leftCount > getBandWidth() * TronConfig.feeBandWidth) {
                this.tvAutoDeducttips.setVisibility(View.GONE);
                if (d < 1.0d) {
                    this.tvErrortips.setText(R.string.stake_min_1_trx);
                    this.liErrorTip.setVisibility(View.VISIBLE);
                }
            } else {
                this.tvAutoDeducttips.setText(R.string.stake_insufficient_bandwidth_for);
                this.tvAutoDeducttips.setVisibility(View.VISIBLE);
            }
            if (this.tvAutoDeducttips.getVisibility() == 0) {
                this.liErrorTip.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && isShouldHideInput(getCurrentFocus(), motionEvent)) {
            KeyboardUtil.closeKeybord(this);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean isShouldHideInput(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    @Override
    public BigDecimal expectGetBandWidth(BigDecimal bigDecimal) {
        if (this.mAccountResMessage != null && compareWithZero(bigDecimal, BigDecimal.ZERO) == 1) {
            BigDecimal bigDecimal2 = new BigDecimal(this.mAccountResMessage.getTotalNetWeight());
            BigDecimal bigDecimal3 = new BigDecimal(this.mAccountResMessage.getTotalNetLimit());
            if (bigDecimal2.compareTo(BigDecimal.ZERO) != 0) {
                return bigDecimal.multiply(bigDecimal3).divide(bigDecimal2, 0, 1);
            }
            return new BigDecimal(0);
        }
        return new BigDecimal(0);
    }

    @Override
    public BigDecimal expectGetEnergy(BigDecimal bigDecimal) {
        if (this.mAccountResMessage != null && compareWithZero(bigDecimal, BigDecimal.ZERO) == 1) {
            BigDecimal bigDecimal2 = new BigDecimal(this.mAccountResMessage.getTotalEnergyWeight());
            BigDecimal bigDecimal3 = new BigDecimal(this.mAccountResMessage.getTotalEnergyLimit());
            if (bigDecimal2.compareTo(BigDecimal.ZERO) != 0) {
                return bigDecimal.multiply(bigDecimal3).divide(bigDecimal2.add(bigDecimal), 0, 1);
            }
            return new BigDecimal(0);
        }
        ((StakeTRXPresenter) this.mPresenter).getData();
        return new BigDecimal(0);
    }

    public BigDecimal energy2TRX(BigDecimal bigDecimal) {
        BigDecimal sub_;
        BigDecimal bigDecimal2 = new BigDecimal(this.mAccountResMessage.getTotalEnergyWeight());
        if (this.mAccountResMessage != null && bigDecimal.compareTo(BigDecimal.ZERO) != 0 && bigDecimal2.compareTo(BigDecimal.ZERO) != 0) {
            long totalEnergyLimit = this.mAccountResMessage.getTotalEnergyLimit();
            if (BigDecimalUtils.moreThanOrEqual(bigDecimal, Long.valueOf(totalEnergyLimit))) {
                sub_ = BigDecimalUtils.toBigDecimal(1);
            } else {
                sub_ = BigDecimalUtils.sub_(Long.valueOf(totalEnergyLimit), bigDecimal);
            }
            BigDecimal divide = BigDecimalUtils.mul_(bigDecimal, bigDecimal2).divide(sub_, 0, 0);
            return divide.compareTo(BigDecimal.ZERO) == 0 ? BigDecimalUtils.toBigDecimal(1) : divide;
        }
        ((StakeTRXPresenter) this.mPresenter).getData();
        return new BigDecimal(0);
    }

    public BigDecimal bandwidth2TRX(BigDecimal bigDecimal) {
        BigDecimal sub_;
        BigDecimal bigDecimal2 = new BigDecimal(this.mAccountResMessage.getTotalNetWeight());
        if (this.mAccountResMessage != null && bigDecimal.compareTo(BigDecimal.ZERO) != 0 && bigDecimal2.compareTo(BigDecimal.ZERO) != 0) {
            long totalNetLimit = this.mAccountResMessage.getTotalNetLimit();
            if (BigDecimalUtils.moreThanOrEqual(bigDecimal, Long.valueOf(totalNetLimit))) {
                sub_ = BigDecimalUtils.toBigDecimal(1);
            } else {
                sub_ = BigDecimalUtils.sub_(Long.valueOf(totalNetLimit), bigDecimal);
            }
            BigDecimal divide = BigDecimalUtils.mul_(bigDecimal, bigDecimal2).divide(sub_, 0, 0);
            return divide.compareTo(BigDecimal.ZERO) == 0 ? BigDecimalUtils.toBigDecimal(1) : divide;
        }
        ((StakeTRXPresenter) this.mPresenter).getData();
        return new BigDecimal(0);
    }

    public int getStakeAmount() {
        try {
            CurrencyEditText currencyEditText = this.etAmount;
            if (currencyEditText == null) {
                return -1;
            }
            if (StringTronUtil.isEmpty(currencyEditText.getText().toString())) {
                return 0;
            }
            return this.numberFormat.parse(this.etAmount.getText().toString()).intValue();
        } catch (ParseException e) {
            LogUtils.e(e);
            return -1;
        }
    }

    public int compareWithZero(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.compareTo(bigDecimal2);
    }

    public void checkResForDay(String str) {
        if (this.isFreezeBandwidth) {
            int floor = (int) Math.floor(BigDecimalUtils.div_(str, new BigDecimal(SpAPI.THIS.getBandwidthPerTranscation())).doubleValue());
            if (floor > 0) {
                this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_bandwidth_tips), TextDotUtils.getDotText(String.valueOf(floor))));
                return;
            } else {
                this.tvResourcePerTransaction.setText(R.string.stake_get_less_one);
                return;
            }
        }
        int floor2 = (int) Math.floor(BigDecimalUtils.div_(str, new BigDecimal(SpAPI.THIS.getEnergyPerTranscation())).doubleValue());
        if (floor2 > 0) {
            this.tvResourcePerTransaction.setText(String.format(getString(R.string.stake_get_energy_tips), TextDotUtils.getDotText(String.valueOf(floor2))));
        } else {
            this.tvResourcePerTransaction.setText(R.string.stake_get_less_one);
        }
    }

    public void setArrowStatus(boolean z) {
        if (!z) {
            this.ivArrow.setBackgroundResource(R.mipmap.icon_arrow_bottom);
        } else if (this.isFreezeBandwidth) {
            this.ivArrow.setBackgroundResource(R.mipmap.icon_arrow_top_green);
        } else {
            this.ivArrow.setBackgroundResource(R.mipmap.icon_arrow_top_yellow);
        }
    }

    public void resetSelectorBackground() {
        this.tvCurrentPercentBg = null;
        this.tvPercent25.setBackgroundResource(R.drawable.roundborder_cdd1da_r8);
        this.tvPercent25.setTextColor(getResources().getColor(R.color.black_02));
        this.tvPercent50.setBackgroundResource(R.drawable.roundborder_cdd1da_r8);
        this.tvPercent50.setTextColor(getResources().getColor(R.color.black_02));
        this.tvPercent75.setBackgroundResource(R.drawable.roundborder_cdd1da_r8);
        this.tvPercent75.setTextColor(getResources().getColor(R.color.black_02));
        this.tvPercent100.setBackgroundResource(R.drawable.roundborder_cdd1da_r8);
        this.tvPercent100.setTextColor(getResources().getColor(R.color.black_02));
    }

    @Override
    public void setButtonEnable(boolean z) {
        this.btnNextStep.setEnabled(z);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        if (this.isMultisign) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_2_3_BACK);
        } else {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_BACK);
        }
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mAccount == null || this.mAccountResMessage == null) {
            ((StakeTRXPresenter) this.mPresenter).getData();
        }
    }
}
