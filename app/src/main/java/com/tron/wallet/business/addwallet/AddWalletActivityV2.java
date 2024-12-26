package com.tron.wallet.business.addwallet;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.business.addwallet.AddWalletActivityV2;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.ledger.manage.EquipmentManageActivity;
import com.tron.wallet.business.ledger.search.SearchLedgerActivity;
import com.tron.wallet.business.samsung.SamsungSDKWrapper;
import com.tron.wallet.business.walletmanager.createaccount.CreateAccountActivity;
import com.tron.wallet.business.walletmanager.createwallet.CreateWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.AddWatchWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceController;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletActivity;
import com.tron.wallet.common.components.dialog.Common2Dialog;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.OnIClickListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.ActivityAddWalletV2Binding;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.BleUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.tron.walletserver.Wallet;
public class AddWalletActivityV2 extends BaseActivity<EmptyPresenter, EmptyModel> {
    Wallet acWallet;
    LinearLayout back;
    private ActivityAddWalletV2Binding binding;
    private Common2Dialog common2Dialog;
    private boolean hasBleDevice;
    private boolean isNile;
    View ivTip;
    View llCreateNew;
    private SamsungPlugin mPlugin;
    private String password;
    private PopupWindow popupWindow;
    private int pwdType;
    RelativeLayout rlColdPair;
    RelativeLayout rlCreate;
    RelativeLayout rlImport;
    RelativeLayout rlLedger;
    RelativeLayout rlObserver;
    RelativeLayout rlRequestAddress;
    RelativeLayout rlSamsung;
    private int shield_type;
    TextView tvObservationDes;
    TextView tvTitle;
    private boolean isClick = true;
    private int ToSelectRequestCode = 2001;
    private boolean isJumpToSamSungWallet = false;

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.gray_F7F8), 0);
        ActivityAddWalletV2Binding inflate = ActivityAddWalletV2Binding.inflate(getLayoutInflater());
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
        this.back = this.binding.llCommonLeft;
        this.tvTitle = this.binding.tvCommonTitle;
        this.rlRequestAddress = this.binding.rlRequestAddress;
        this.rlCreate = this.binding.rlCreate;
        this.rlImport = this.binding.rlImport;
        this.rlLedger = this.binding.rlLedger;
        this.rlColdPair = this.binding.rlColdPair;
        this.rlObserver = this.binding.rlObser;
        this.rlSamsung = this.binding.rlSamsung;
        this.llCreateNew = this.binding.llCreateNew;
        this.ivTip = this.binding.ivTip;
        this.tvObservationDes = this.binding.observationDes;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (this.isJumpToSamSungWallet) {
            if (!TextUtils.isEmpty(SamsungSDKWrapper.checkSeedHashEmpty(this, false))) {
                this.mPlugin.importSamsungWallet(this);
            }
            this.isJumpToSamSungWallet = false;
        }
    }

    @Override
    protected void processData() {
        this.tvTitle.setText(R.string.add_wallet);
        this.isNile = getIntent().getBooleanExtra(AddWalletType.INTENT_KEY_IS_NILE, false);
        this.shield_type = getIntent().getIntExtra(AddWalletType.INTENT_KEY_WALLET_TYPE, 0);
        if (SpAPI.THIS.isCold()) {
            this.rlCreate.setVisibility(View.VISIBLE);
            this.rlImport.setVisibility(View.VISIBLE);
            this.rlColdPair.setVisibility(View.GONE);
        } else {
            if (HDTronWalletController.hasHDWallet()) {
                this.rlRequestAddress.setVisibility(View.VISIBLE);
            } else {
                this.rlCreate.setVisibility(View.VISIBLE);
            }
            this.rlImport.setVisibility(View.VISIBLE);
            this.rlColdPair.setVisibility(View.VISIBLE);
            this.rlObserver.setVisibility(View.VISIBLE);
        }
        if (this.shield_type == 1) {
            this.tvObservationDes.setText(R.string.view_balance_detail);
        }
        if (this.shield_type == 1) {
            this.rlCreate.setVisibility(View.GONE);
            this.rlRequestAddress.setVisibility(View.GONE);
            this.rlColdPair.setVisibility(View.GONE);
        }
        this.acWallet = WalletUtils.getSelectedWallet();
        this.mPlugin = new SamsungPlugin();
        checkSamsungSDKEnable();
        checkLedgerSupport();
        initClick();
        if (this.acWallet == null) {
            finish();
        }
        if (this.rlCreate.getVisibility() == 0) {
            this.llCreateNew.setVisibility(View.GONE);
        } else {
            TouchDelegateUtils.expandViewTouchDelegate(this.ivTip, 10);
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.AddWalletPage.ENTER_ADD_WALLET_PAGE);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                StatusBarUtils.setLightStatusBar(AddWalletActivityV2.this, true);
            }
        });
        checkLedgerDeviceExist();
    }

    private void checkLedgerDeviceExist() {
        if (BleUtils.isSupportBle() && !SpAPI.THIS.isCold() && SpAPI.THIS.getCurIsMainChain()) {
            BleDeviceController.getInstance().empty().subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$checkLedgerDeviceExist$0((Boolean) obj);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    LogUtils.e((Throwable) obj);
                }
            });
        }
    }

    public void lambda$checkLedgerDeviceExist$0(Boolean bool) throws Exception {
        this.hasBleDevice = !bool.booleanValue();
    }

    private void checkLedgerSupport() {
        if (!BleUtils.isSupportBle() || SpAPI.THIS.isCold() || !SpAPI.THIS.getCurIsMainChain() || 1 == this.shield_type) {
            this.rlLedger.setVisibility(View.GONE);
        } else {
            this.rlLedger.setVisibility(View.VISIBLE);
        }
    }

    private void checkSamsungSDKEnable() {
        if (SamsungSDKWrapper.isSupportSamsungSdk() && this.shield_type == 0 && !SpAPI.THIS.isCold()) {
            this.rlSamsung.setVisibility(View.VISIBLE);
        } else {
            this.rlSamsung.setVisibility(View.GONE);
        }
    }

    private void initClick() {
        this.rlSamsung.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent("addWalletPage_6");
                if (TextUtils.isEmpty(SamsungSDKWrapper.checkSeedHashEmpty(AddWalletActivityV2.this, false))) {
                    mPlugin.showGoToSamsungWallet(AddWalletActivityV2.this, 16);
                } else {
                    mPlugin.importSamsungWallet(AddWalletActivityV2.this);
                }
            }
        });
        this.rlLedger.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.AddWalletPage.CLICK_ADD_WALLET_PAGE_LEDGER);
                if (hasBleDevice) {
                    EquipmentManageActivity.startActivity(AddWalletActivityV2.this);
                } else {
                    SearchLedgerActivity.start(AddWalletActivityV2.this);
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.AddWalletPage.CLICK_ADD_WALLET_PAGE_LEDGER);
            }
        });
    }

    public class fun4 extends NoDoubleClickListener {
        fun4() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.iv_tip:
                    AddWalletActivityV2 addWalletActivityV2 = AddWalletActivityV2.this;
                    PopupWindowUtil.showPopupText(addWalletActivityV2, addWalletActivityV2.getResources().getString(R.string.create_new_mnemonic_tip), ivTip, false);
                    return;
                case R.id.ll_common_left:
                    finish();
                    return;
                case R.id.ll_create_new:
                case R.id.rl_create:
                    AnalyticsHelper.logEvent("addWalletPage_6");
                    AddWalletActivityV2 addWalletActivityV22 = AddWalletActivityV2.this;
                    CreateWalletActivity.start(addWalletActivityV22, 1 == addWalletActivityV22.shield_type);
                    return;
                case R.id.rl_cold_pair:
                    AnalyticsHelper.logEvent(AnalyticsHelper.AddWalletPage.CLICK_ADD_WALLET_PAGE_COLD_PAIR);
                    PairColdWalletActivity.start(AddWalletActivityV2.this, 1);
                    return;
                case R.id.rl_import:
                    AnalyticsHelper.logEvent("addWalletPage_3");
                    AddWalletActivityV2 addWalletActivityV23 = AddWalletActivityV2.this;
                    ImportWalletActivity.start(addWalletActivityV23, 1 == addWalletActivityV23.shield_type);
                    return;
                case R.id.rl_obser:
                    go(AddWatchWalletActivity.class);
                    return;
                case R.id.rl_request_address:
                    AnalyticsHelper.logEvent("addWalletPage_2");
                    showLoadingDialog();
                    runOnThreeThread(new OnBackground() {
                        @Override
                        public final void doOnBackground() {
                            AddWalletActivityV2.4.this.lambda$onNoDoubleClick$2();
                        }
                    });
                    return;
                default:
                    return;
            }
        }

        public void lambda$onNoDoubleClick$2() {
            final String queryRelationShipAddress = HDTronWalletController.queryRelationShipAddress(acWallet.getWalletName());
            final HdTronRelationshipBean hdWalletRelationShip = HDTronWalletController.getHdWalletRelationShip(acWallet.getWalletName());
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    AddWalletActivityV2.4.this.lambda$onNoDoubleClick$1(queryRelationShipAddress, hdWalletRelationShip);
                }
            });
        }

        public void lambda$onNoDoubleClick$1(String str, HdTronRelationshipBean hdTronRelationshipBean) {
            dismissLoadingDialog();
            if (WalletUtils.hasMnemonic(acWallet.getWalletName()) && !StringTronUtil.isNullOrEmpty(str) && !hdTronRelationshipBean.getNonHd()) {
                AddWalletActivityV2 addWalletActivityV2 = AddWalletActivityV2.this;
                addWalletActivityV2.jumpToCreateRelation(addWalletActivityV2.acWallet.getWalletName());
                return;
            }
            if (HDTronWalletController.hasHDWallet()) {
                List<HdTronRelationshipBean> queryAllRelationFilterNonHdBeans = HDTronWalletController.queryAllRelationFilterNonHdBeans();
                Collections.sort(queryAllRelationFilterNonHdBeans, new Comparator() {
                    @Override
                    public final int compare(Object obj, Object obj2) {
                        return AddWalletActivityV2.4.lambda$onNoDoubleClick$0((HdTronRelationshipBean) obj, (HdTronRelationshipBean) obj2);
                    }
                });
                if (queryAllRelationFilterNonHdBeans == null || queryAllRelationFilterNonHdBeans.size() <= 0) {
                    return;
                }
                HdTronRelationshipBean hdTronRelationshipBean2 = queryAllRelationFilterNonHdBeans.get(0);
                if (WalletUtils.getWallet(hdTronRelationshipBean2.getWalletName()) != null) {
                    jumpToCreateRelation(hdTronRelationshipBean2.getWalletName());
                    return;
                }
                return;
            }
            AddWalletActivityV2 addWalletActivityV22 = AddWalletActivityV2.this;
            CreateWalletActivity.start(addWalletActivityV22, 1 == addWalletActivityV22.shield_type);
        }

        public static int lambda$onNoDoubleClick$0(HdTronRelationshipBean hdTronRelationshipBean, HdTronRelationshipBean hdTronRelationshipBean2) {
            return hdTronRelationshipBean2.getWalletPath().accountIndex - hdTronRelationshipBean.getWalletPath().accountIndex;
        }
    }

    public void setClick() {
        4 r0 = new fun4();
        this.binding.rlRequestAddress.setOnClickListener(r0);
        this.binding.rlCreate.setOnClickListener(r0);
        this.binding.rlImport.setOnClickListener(r0);
        this.binding.rlObser.setOnClickListener(r0);
        this.binding.rlColdPair.setOnClickListener(r0);
        this.binding.llCommonLeft.setOnClickListener(r0);
        this.binding.llCreateNew.setOnClickListener(r0);
        this.binding.ivTip.setOnClickListener(r0);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 16) {
            this.isJumpToSamSungWallet = true;
        }
        if (i != this.ToSelectRequestCode || intent == null) {
            return;
        }
        CreateAccountActivity.start(this, intent.getStringExtra(TronConfig.WALLET_extra), intent.getStringExtra(TronConfig.WALLET_DATA), intent.getStringExtra(TronConfig.WALLET_PASSWORD), 1 == this.shield_type);
    }

    public void jumpToCreateRelation(final String str) {
        this.pwdType = 2;
        showDialog(new OnIClickListener() {
            @Override
            public final void onClick() {
                lambda$jumpToCreateRelation$4(str);
            }
        });
    }

    public void lambda$jumpToCreateRelation$4(final String str) {
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public final void doOnBackground() {
                lambda$jumpToCreateRelation$3(str);
            }
        });
    }

    public void lambda$jumpToCreateRelation$3(final String str) {
        try {
            final Wallet wallet = WalletUtils.getWallet(str, this.password);
            AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                @Override
                public final void doInUIThread() {
                    lambda$jumpToCreateRelation$2(wallet, str);
                }
            });
        } catch (Exception e) {
            dismissLoadingDialog();
            PasswordInputUtils.updatePwdInput(this.mContext, str, this.pwdType);
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    public void lambda$jumpToCreateRelation$2(org.tron.walletserver.Wallet r4, java.lang.String r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.addwallet.AddWalletActivityV2.lambda$jumpToCreateRelation$2(org.tron.walletserver.Wallet, java.lang.String):void");
    }

    public void showDialog(OnIClickListener onIClickListener) {
        showDialog(onIClickListener, false);
    }

    public void showDialog(final OnIClickListener onIClickListener, boolean z) {
        if (this.acWallet == null) {
            return;
        }
        Common2Dialog btListener = new Common2Dialog(this.mContext).setTitle(getString(R.string.wallet_manager_enter_password)).setInnerTitle("").setBtListener(getString(R.string.ok2), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showDialog$5(onIClickListener, view);
            }
        });
        this.common2Dialog = btListener;
        btListener.addEditext();
        this.common2Dialog.show();
    }

    public void lambda$showDialog$5(OnIClickListener onIClickListener, View view) {
        this.password = this.common2Dialog.getEditextText();
        this.common2Dialog.dismiss();
        showLoadingDialog();
        if (TextUtils.isEmpty(this.password)) {
            dismissLoadingDialog();
            ToastError(R.string.isnull);
        } else if (PasswordInputUtils.canPwdInput(this.mContext, this.acWallet.getWalletName(), this.pwdType)) {
            if (!StringTronUtil.isEmpty(this.password)) {
                onIClickListener.onClick();
                return;
            }
            dismissLoadingDialog();
            ToastAsBottom(R.string.et_null);
        } else {
            ToastAsBottom(R.string.password_input_error);
            dismissLoadingDialog();
        }
    }
}
