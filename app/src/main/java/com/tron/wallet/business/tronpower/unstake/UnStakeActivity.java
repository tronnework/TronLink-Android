package com.tron.wallet.business.tronpower.unstake;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.tronpower.unstake.UnStakeContract;
import com.tron.wallet.business.tronpower.unstake.adapter.UnStakeResourceBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.interfaces.CheckAccountNotActivatedCallback;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.databinding.ActivityUnstakeBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.ledger.blemodule.utils.Constants;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.List;
import java.util.function.Predicate;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class UnStakeActivity extends BaseActivity<UnStakePresenter, UnStakeModel> implements UnStakeContract.View {
    private static final String ERR_NULL_ACCOUNT = "null account";
    private static final int SELECTED_THRESHOLD = 5;
    private ActivityUnstakeBinding binding;
    Button btnNext;
    private String controllerAddress;
    private Protocol.Account currentAccount;
    ErrorView errorView;
    private boolean flagReload = false;
    private boolean fromMultiSign;
    ImageView ivBack;
    private Parcelable permissionData;
    private ResourceListFragment resourceFragment;
    TextView tvMainTitle;
    TextView tvMultiSign;
    TextView tvMultiSignTips;
    TextView tvSelectCount;
    TextView tvStep;
    TextView tvTotalTrx;

    public static void lambda$showNotActivatedPopup$3(View view) {
    }

    public static void start(final Context context, final Protocol.Account account) {
        AccountUtils.getInstance().checkAccountIsActivatedFromLocal(context, new CheckAccountActivatedCallback() {
            @Override
            public final void isActivated() {
                UnStakeActivity.lambda$start$0(context, account);
            }
        }, new CheckAccountNotActivatedCallback() {
            @Override
            public final void notActivated() {
                UnStakeActivity.showNotActivatedPopup(context);
            }
        });
    }

    public static void lambda$start$0(Context context, Protocol.Account account) {
        Intent intent = new Intent(context, UnStakeActivity.class);
        intent.putExtra("key_account", account);
        context.startActivity(intent);
    }

    public static void startFromMultiSign(Context context, Protocol.Account account, String str, String str2, MultiSignPermissionData multiSignPermissionData) {
        Intent intent = new Intent(context, UnStakeActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, true);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityUnstakeBinding inflate = ActivityUnstakeBinding.inflate(getLayoutInflater());
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
        this.tvMultiSign = this.binding.tvMultiSign;
        this.tvTotalTrx = this.binding.tvTotalTrx;
        this.tvSelectCount = this.binding.tvSelectedCount;
        this.btnNext = this.binding.btnNext;
        this.tvMultiSignTips = this.binding.tvMultiWarning;
        this.tvStep = this.binding.tvStep;
        this.tvMainTitle = this.binding.tvMainTitle;
        this.errorView = this.binding.errorView;
    }

    @Override
    protected void processData() {
        boolean booleanExtra = getIntent().getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.fromMultiSign = booleanExtra;
        if (booleanExtra) {
            handleMultiSign();
        } else {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet != null) {
                this.controllerAddress = selectedPublicWallet.getAddress();
            } else {
                this.controllerAddress = "";
            }
        }
        ((UnStakePresenter) this.mPresenter).setControllerAddress(this.controllerAddress);
        initEvents();
        initViews();
        this.currentAccount = (Protocol.Account) getIntent().getSerializableExtra("key_account");
        requestData(false);
    }

    private void requestData(boolean z) {
        ((UnStakePresenter) this.mPresenter).ensureAccount(z, this.currentAccount, this.controllerAddress).compose(RxSchedulers2.io_main()).subscribe(new IObserver(getAccountCallback(), "ensureAccount"));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.flagReload) {
            this.flagReload = false;
            if (this.mPresenter == 0) {
                return;
            }
            ((UnStakePresenter) this.mPresenter).reset();
            ResourceListFragment resourceListFragment = this.resourceFragment;
            if (resourceListFragment != null) {
                resourceListFragment.setEmptyData();
            }
            requestData(true);
        }
    }

    private void handleMultiSign() {
        this.controllerAddress = getIntent().getStringExtra("key_controller_address");
        String stringExtra = getIntent().getStringExtra("key_controller_name");
        this.permissionData = getIntent().getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
        final String str = this.controllerAddress;
        if (!TextUtils.isEmpty(stringExtra)) {
            str = String.format("%s (%s)", stringExtra, str);
        }
        this.tvMultiSignTips.setText(getString(R.string.unstake_multi_sign_controller_tips, new Object[]{str}));
        this.tvMultiSignTips.post(new Runnable() {
            @Override
            public final void run() {
                lambda$handleMultiSign$2(str);
            }
        });
        this.tvMultiSignTips.setVisibility(View.VISIBLE);
        this.tvMultiSign.setVisibility(View.GONE);
        this.tvStep.setVisibility(View.VISIBLE);
        this.tvMainTitle.setText(R.string.unstake_multi_sign_page_title);
    }

    public void lambda$handleMultiSign$2(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignTips, str);
        this.tvMultiSignTips.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    private ICallback<Protocol.Account> getAccountCallback() {
        return new ICallback<Protocol.Account>() {
            @Override
            public void onResponse(String str, Protocol.Account account) {
                if (AccountUtils.isNullAccount(account)) {
                    onFailure(Constants.BluetoothLogLevel.ERROR, UnStakeActivity.ERR_NULL_ACCOUNT);
                }
                currentAccount = account;
                if (fromMultiSign || ((UnStakePresenter) mPresenter).checkOwnerPermission(account)) {
                    ((UnStakePresenter) mPresenter).requestStakeResource(account);
                } else {
                    showMultiSignDialog();
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                if (TextUtils.equals(str2, UnStakeActivity.ERR_NULL_ACCOUNT)) {
                    if (resourceFragment != null) {
                        resourceFragment.setEmptyData();
                        return;
                    }
                    return;
                }
                UnStakeActivity unStakeActivity = UnStakeActivity.this;
                unStakeActivity.toast(unStakeActivity.getString(R.string.time_out));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                ((UnStakePresenter) mPresenter).addDisposable(disposable);
            }
        };
    }

    public static void showNotActivatedPopup(final Context context) {
        ConfirmCustomPopupView.getBuilder(context).setTitle(context.getResources().getString(R.string.unstake_account_unactive)).setTitleBold(true).setTitleSize(16).setConfirmText(context.getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                UnStakeActivity.lambda$showNotActivatedPopup$3(view);
            }
        }).setShowCancelBtn(true).setCancelText(context.getResources().getString(R.string.multisig_unstatking)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                MultiSelectAddressActivity.start(context, MultiSourcePageEnum.UnStake);
            }
        }).build().show();
    }

    private void initViews() {
        this.resourceFragment = ResourceListFragment.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.search_result_view, this.resourceFragment).commitAllowingStateLoss();
        if (checkNetEnvironment()) {
            this.errorView.setVisibility(View.GONE);
        }
    }

    private void initEvents() {
        this.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initEvents$5(view);
            }
        });
        this.tvMultiSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initEvents$6(view);
            }
        });
        this.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initEvents$7(view);
            }
        });
        ((UnStakePresenter) this.mPresenter).mRxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$8(obj);
            }
        });
        ((UnStakePresenter) this.mPresenter).mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$9(obj);
            }
        });
        ((UnStakePresenter) this.mPresenter).mRxManager.on(Event.BroadcastFail, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$10(obj);
            }
        });
    }

    public void lambda$initEvents$5(View view) {
        AnalyticsHelper.logEvent(this.fromMultiSign ? AnalyticsHelper.MultiSignUnStakeStep2.CLICK_BACK : AnalyticsHelper.UnStake.CLICK_BACK);
        exit();
    }

    public void lambda$initEvents$6(View view) {
        checkAndEnterMultiSign();
        AnalyticsHelper.logEvent(AnalyticsHelper.MultisigUnstaking.CLICK_RIGHT_TEXT);
    }

    public void lambda$initEvents$7(View view) {
        ((UnStakePresenter) this.mPresenter).enterConfirm(this.fromMultiSign);
        AnalyticsHelper.logEvent(AnalyticsHelper.UnStake.CLICK_CONFIRM);
    }

    public void lambda$initEvents$8(Object obj) throws Exception {
        exit();
    }

    public void lambda$initEvents$9(Object obj) throws Exception {
        exit();
    }

    public void lambda$initEvents$10(Object obj) throws Exception {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if (intValue == 0) {
                this.flagReload = true;
            } else if (intValue == 1) {
                exit();
            }
        }
    }

    @Override
    public void updateResourceResult(boolean z, boolean z2, int i, List<UnStakeResourceBean> list) {
        ResourceListFragment resourceListFragment = this.resourceFragment;
        if (resourceListFragment != null) {
            resourceListFragment.updateData(z, z2, list, getItemClickCallback(), new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public final void onLoadMoreRequested() {
                    lambda$updateResourceResult$11();
                }
            });
            updateSelectTipVisibility(i);
        }
    }

    public void lambda$updateResourceResult$11() {
        ((UnStakePresenter) this.mPresenter).requestMore();
    }

    private void updateSelectTipVisibility(int i) {
        this.tvSelectCount.setVisibility((Collection.-EL.stream(this.resourceFragment.getTotalData()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return UnStakeActivity.lambda$updateSelectTipVisibility$12((UnStakeResourceBean) obj);
            }
        }).count() > 5L ? 1 : (Collection.-EL.stream(this.resourceFragment.getTotalData()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return UnStakeActivity.lambda$updateSelectTipVisibility$12((UnStakeResourceBean) obj);
            }
        }).count() == 5L ? 0 : -1)) > 0 ? View.VISIBLE : View.GONE);
        setSelectedTextView(i);
    }

    public static boolean lambda$updateSelectTipVisibility$12(UnStakeResourceBean unStakeResourceBean) {
        try {
            return (unStakeResourceBean.getState() & (-65536)) != 196608;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    @Override
    public void updateSelectedResources(int i, long j) {
        if (j <= 0) {
            this.tvTotalTrx.setVisibility(View.GONE);
        } else {
            String str = StringTronUtil.formatNumber6Truncate(Long.valueOf(j)) + "TRX";
            this.tvTotalTrx.setText(SpannableUtils.getTextColorSpannable(getResources().getString(R.string.unstaking_total_trx, str), str, getResources().getColor(R.color.black_23)));
            this.tvTotalTrx.setVisibility(View.VISIBLE);
        }
        if (this.tvSelectCount.getVisibility() != 8) {
            setSelectedTextView(i);
        }
        this.resourceFragment.updateSelectableState(i < 5);
        this.btnNext.setEnabled(i > 0);
    }

    private void setSelectedTextView(int i) {
        this.tvSelectCount.setText(SpannableUtils.getTextColorSpannable(getString(R.string.unstake_selected_count, new Object[]{Integer.valueOf(i), 5}), String.valueOf(i), getResources().getColor(R.color.black_23)));
    }

    private androidx.core.util.Consumer<UnStakeResourceBean> getItemClickCallback() {
        return new androidx.core.util.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getItemClickCallback$13((UnStakeResourceBean) obj);
            }
        };
    }

    public void lambda$getItemClickCallback$13(UnStakeResourceBean unStakeResourceBean) {
        ((UnStakePresenter) this.mPresenter).onItemSelectStateChanged(unStakeResourceBean);
        boolean equals = unStakeResourceBean.getGroup().equals(UnStakeResourceBean.Group.SELF);
        AnalyticsHelper.logEvent(this.fromMultiSign ? equals ? AnalyticsHelper.MultiSignUnStakeStep2.CLICK_FOR_SELF : AnalyticsHelper.MultiSignUnStakeStep2.CLICK_FOR_OTHER : equals ? AnalyticsHelper.UnStake.CLICK_FOR_SELF : AnalyticsHelper.UnStake.CLICK_FOR_OTHER);
    }

    public void showMultiSignDialog() {
        ConfirmCustomPopupView.getBuilder(this).setTitle(getString(R.string.unstake_lack_owner_permission)).setTitleSize(16).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
                exit();
            }
        }).setConfirmText(getString(R.string.unstake_multi_sign_dialog_title)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$14(view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$15(view);
            }
        }).build().show();
    }

    public void lambda$showMultiSignDialog$14(View view) {
        exit();
    }

    public void lambda$showMultiSignDialog$15(View view) {
        checkAndEnterMultiSign();
        AnalyticsHelper.logEvent(AnalyticsHelper.MultisigUnstaking.CLICK_DIALOG);
    }

    private void checkAndEnterMultiSign() {
        MultiSelectAddressActivity.start(this, MultiSourcePageEnum.UnStake);
        exit();
    }

    private boolean checkNetEnvironment() {
        return !SpAPI.THIS.getCurIsMainChain() || IRequest.isShasta();
    }
}
