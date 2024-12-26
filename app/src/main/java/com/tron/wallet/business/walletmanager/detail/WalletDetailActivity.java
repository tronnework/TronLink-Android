package com.tron.wallet.business.walletmanager.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.addwallet.AddWalletActivityV2;
import com.tron.wallet.business.create.creatwallet.AddWalletType;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.security.home.SecurityHomeActivity;
import com.tron.wallet.business.tabmy.dealsign.DealSignActivity;
import com.tron.wallet.business.tabmy.myhome.dappauthorized.DappAuthorizedActivity;
import com.tron.wallet.business.tabmy.walletmanage.ChangeNameActivity;
import com.tron.wallet.business.tabmy.walletmanage.ChangePasswordActivity;
import com.tron.wallet.business.transfer.multisignature.MultiSignatureManagerActivity;
import com.tron.wallet.business.walletmanager.backup.BackKeystoreActivity;
import com.tron.wallet.business.walletmanager.backup.BackPrivateKeyActivity;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordActivity;
import com.tron.wallet.business.walletmanager.backup.tips.BackupTipsActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.BackUpMnemonicActivity;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.business.walletmanager.detail.WalletDetailContract;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletExplainActivity;
import com.tron.wallet.business.walletmanager.relation.RelationActivity;
import com.tron.wallet.business.walletmanager.selectwallet.SelectWalletActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.welcome.EmptyWalletActivity;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tron.wallet.common.components.dialog.Common2Dialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.OnIClickListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcWalletDetailBinding;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.wallet.WalletType;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tron.wallet.net.NetUtil;
import com.tron.wallet.net.SocketManager;
import com.tronlinkpro.wallet.R;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.tron.common.utils.ByteArray;
import org.tron.net.CipherException;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class WalletDetailActivity extends BaseActivity<WalletDetailPresenter, WalletDetailModel> implements WalletDetailContract.View {
    public static final String WATCH_UPDATE_COLD_RESULT_SUCCESS = "watch_update_cold_result_success";
    private Wallet acWallet;
    LinearLayout backUpLayout;
    View backUpRl;
    private AcWalletDetailBinding binding;
    View changePasswordView;
    private Common2Dialog common2Dialog;
    RelativeLayout dappAuthorized;
    TextView dealSignCount;
    RelativeLayout delete;
    View editNameView;
    private boolean hasSocketConnect;
    private boolean hasSwitchWallet;
    ImageView hdBg;
    private HdTronRelationshipBean hdTronRelationshipBean;
    private Intent intent;
    ImageView ivAdd;
    ImageView ivCardIcon;
    private ImageView ivCopy;
    ImageView ivShieldAdd;
    View ivSwitch;
    View ivTip;
    View line2;
    RelativeLayout llCard;
    private LinearLayout llCommonLeft;
    View llIndex;
    View llMulPathLayout;
    private String mWalletName;
    MultiSignPopupTextView multiSignFlagView;
    private NumberFormat numberFormat;
    private String password;
    View permissionLine;
    private int pwdType;
    RelativeLayout rlApprove;
    RelativeLayout rlColdPairLayout;
    RelativeLayout rlKeystore;
    LinearLayout rlManagerApprove;
    RelativeLayout rlMnemonic;
    RelativeLayout rlPrivatekey;
    RelativeLayout rlRelationLayout;
    View rlSafetyCheck;
    RelativeLayout rlSignManager;
    RelativeLayout rlSignWait;
    RelativeLayout rlSwitchShield;
    private RxManager rxManager;
    TextView tvAddress;
    TextView tvBackupHistory;
    TextView tvChainName;
    TextView tvCommonTitle;
    TextView tvLinkedAccountCount;
    TextView tvMulInfo;
    TextView tvName;
    TextView tvPath;
    public boolean isOpen = false;
    private int relationWalletCount = 0;

    public interface BackupCallback {
        void callback();
    }

    @Override
    public Wallet getCurrentWallet() {
        return this.acWallet;
    }

    @Override
    public void setCurrentWallet(Wallet wallet) {
        this.acWallet = wallet;
    }

    @Override
    public void setHasSwitchWallet(boolean z) {
        this.hasSwitchWallet = z;
    }

    @Override
    public void setHdWalletRelationShip(HdTronRelationshipBean hdTronRelationshipBean) {
        this.hdTronRelationshipBean = hdTronRelationshipBean;
    }

    public static void startActivity(Context context, String str, boolean z) {
        Intent intent = new Intent();
        intent.setClass(context, WalletDetailActivity.class);
        intent.putExtra(TronConfig.WALLET_NAME, str);
        intent.putExtra(TronConfig.SHIELD_IS_TRC20, z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcWalletDetailBinding inflate = AcWalletDetailBinding.inflate(getLayoutInflater());
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
        this.llCard = this.binding.topCard;
        this.ivCardIcon = this.binding.bgIcon;
        this.tvAddress = this.binding.tvAddress;
        this.tvPath = this.binding.tvPath;
        this.editNameView = this.binding.ivNameEdit;
        this.changePasswordView = this.binding.llPasswordChange;
        this.rlRelationLayout = this.binding.relationLayout;
        this.rlColdPairLayout = this.binding.rlColdPair;
        this.ivTip = this.binding.ivTip;
        this.rlSwitchShield = this.binding.rlSwitchShield;
        this.tvLinkedAccountCount = this.binding.tvLinkedAccountCount;
        this.tvName = this.binding.tvName;
        this.tvCommonTitle = this.binding.tvCommonTitle;
        this.tvChainName = this.binding.tvChainname;
        this.ivShieldAdd = this.binding.ivShieldAdd;
        this.tvMulInfo = this.binding.mulInfo;
        this.dealSignCount = this.binding.dealSignCount;
        this.rlMnemonic = this.binding.rlMnemonic;
        this.line2 = this.binding.line2;
        this.rlPrivatekey = this.binding.rlPrivatekey;
        this.rlKeystore = this.binding.rlKeystore;
        this.tvBackupHistory = this.binding.tvViewBackupHistory;
        this.delete = this.binding.delete;
        this.ivAdd = this.binding.ivAdd;
        this.hdBg = this.binding.hdBg;
        this.rlManagerApprove = this.binding.managerApproveLayout;
        this.rlSignManager = this.binding.rlSignManager;
        this.permissionLine = this.binding.permissionLine;
        this.rlSignWait = this.binding.rlSignWait;
        this.rlApprove = this.binding.rlApprove;
        this.dappAuthorized = this.binding.dappAuthorized;
        this.backUpLayout = this.binding.llBackUp;
        this.backUpRl = this.binding.rlBackUp;
        this.llMulPathLayout = this.binding.mulPathLayout;
        this.llIndex = this.binding.llIndex;
        this.multiSignFlagView = this.binding.flagMultiSign;
        this.ivSwitch = this.binding.ivSwitch;
        this.rlSafetyCheck = this.binding.rlSafetyCheck;
        this.llCommonLeft = this.binding.llCommonLeft;
        this.ivCopy = this.binding.ivCopy;
    }

    public void setTopCard() {
        int i;
        int i2;
        this.tvCommonTitle.setText(R.string.wallet_detail);
        this.tvChainName.setText(NetUtil.getNetName());
        this.rlRelationLayout.setVisibility(View.GONE);
        this.ivSwitch.setVisibility(View.VISIBLE);
        if (SpAPI.THIS.isCold()) {
            this.ivSwitch.setVisibility(View.GONE);
            this.rlSignManager.setVisibility(View.GONE);
            this.rlSignWait.setVisibility(View.GONE);
            this.dappAuthorized.setVisibility(View.GONE);
            this.llIndex.setVisibility(View.GONE);
            this.rlManagerApprove.setVisibility(View.GONE);
            if (WalletUtils.isNonHDWallet(this.acWallet)) {
                this.backUpLayout.setWeightSum(2.0f);
                this.rlMnemonic.setVisibility(View.GONE);
                this.line2.setVisibility(View.GONE);
            }
            this.rlSwitchShield.setVisibility(View.GONE);
            this.ivShieldAdd.setVisibility(View.GONE);
            if (this.acWallet.isWatchOnly()) {
                this.backUpLayout.setVisibility(View.GONE);
                this.backUpRl.setVisibility(View.GONE);
            }
            i = R.drawable.bg_wallet_detail_cold;
            i2 = R.mipmap.bg_icon_cold;
        } else {
            if (this.acWallet.isSamsungWallet()) {
                this.backUpLayout.setVisibility(View.GONE);
                this.backUpRl.setVisibility(View.GONE);
                this.llIndex.setVisibility(View.GONE);
                this.changePasswordView.setVisibility(View.GONE);
                resetOperations();
                i2 = R.mipmap.bg_icon_samsung;
            } else if (LedgerWallet.isLedger(this.acWallet)) {
                this.backUpLayout.setVisibility(View.GONE);
                this.backUpRl.setVisibility(View.GONE);
                this.llIndex.setVisibility(View.GONE);
                this.changePasswordView.setVisibility(View.GONE);
                resetOperations();
                i2 = R.mipmap.bg_icon_ledger;
            } else if (this.acWallet.isWatchOnly()) {
                LogUtils.e("WacthCold", "WacthCold = " + this.acWallet.isWatchCold());
                this.backUpLayout.setVisibility(View.GONE);
                this.backUpRl.setVisibility(View.GONE);
                this.llIndex.setVisibility(View.GONE);
                this.changePasswordView.setVisibility(View.GONE);
                this.dappAuthorized.setVisibility(View.VISIBLE);
                if (this.acWallet.isWatchCold()) {
                    resetOperations();
                    i = R.drawable.bg_wallet_detail_watch_cold;
                    i2 = R.mipmap.bg_icon_watch_cold;
                } else {
                    this.rlColdPairLayout.setVisibility(View.VISIBLE);
                    this.rlManagerApprove.setVisibility(View.GONE);
                    this.rlSignWait.setVisibility(View.GONE);
                    i = R.drawable.bg_wallet_detail_watch;
                    i2 = R.mipmap.bg_icon_watch;
                }
            } else {
                this.changePasswordView.setVisibility(View.VISIBLE);
                this.backUpLayout.setVisibility(View.VISIBLE);
                this.backUpRl.setVisibility(View.VISIBLE);
                if (WalletUtils.isNonHDWallet(this.acWallet)) {
                    this.backUpLayout.setWeightSum(2.0f);
                    this.rlMnemonic.setVisibility(View.GONE);
                    this.line2.setVisibility(View.GONE);
                    this.llIndex.setVisibility(View.GONE);
                } else {
                    this.backUpLayout.setWeightSum(3.0f);
                    this.rlMnemonic.setVisibility(View.VISIBLE);
                    this.line2.setVisibility(View.VISIBLE);
                    this.llIndex.setVisibility(View.VISIBLE);
                    this.llMulPathLayout.setVisibility(View.VISIBLE);
                    updateRelationInfo();
                }
                resetOperations();
                i = R.drawable.bg_wallet_detail_hd;
                i2 = R.mipmap.bg_icon_hd;
            }
            i = R.drawable.bg_wallet_detail_samsung_ledger;
        }
        this.llCard.setBackground(getDrawable(i));
        this.ivCardIcon.setImageResource(i2);
        this.tvName.setText(this.acWallet.getWalletName());
        this.tvAddress.setText(this.acWallet.getAddress());
        try {
            if (this.acWallet != null) {
                this.multiSignFlagView.setVisibility(TRXAccountBalanceController.getInstance(getIContext()).queryByAddress(this.acWallet.getAddress()).getAccountType() == 1 ? View.VISIBLE : View.GONE);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (this.llIndex.getVisibility() == 8 && this.multiSignFlagView.getVisibility() == 8) {
            this.llMulPathLayout.setVisibility(View.GONE);
        } else {
            this.llMulPathLayout.setVisibility(View.VISIBLE);
        }
    }

    private void resetOperations() {
        this.rlColdPairLayout.setVisibility(View.GONE);
        this.rlSignManager.setVisibility(View.VISIBLE);
        this.rlApprove.setVisibility(View.VISIBLE);
        this.rlSafetyCheck.setVisibility(View.VISIBLE);
        this.rlSignWait.setVisibility(View.VISIBLE);
        this.rlManagerApprove.setVisibility(View.VISIBLE);
        this.dappAuthorized.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate2(Bundle bundle) {
        super.onCreate2(bundle);
        if (bundle != null) {
            String string = bundle.getString(TronConfig.WALLET_NAME);
            if (StringTronUtil.isEmpty(string)) {
                return;
            }
            this.mWalletName = string;
            this.acWallet = WalletUtils.getWallet(string);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Wallet wallet = this.acWallet;
        if (wallet != null) {
            bundle.putString(TronConfig.WALLET_NAME, wallet.getWalletName());
        }
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        if (intent != null && this.acWallet == null) {
            String stringExtra = intent.getStringExtra(TronConfig.WALLET_NAME);
            this.mWalletName = stringExtra;
            this.acWallet = WalletUtils.getWallet(stringExtra);
        }
        if (this.acWallet == null) {
            back();
            return;
        }
        ((WalletDetailPresenter) this.mPresenter).addSome();
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(3);
        setTopCard();
        initClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.ENTER_WALLET_MANAGER_PAGE);
        this.rxManager = new RxManager();
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.dapp_authorized:
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppConnectPage.CLICK_CONNECT_MANAGER_PAGE);
                    DappAuthorizedActivity.start(mContext, acWallet.getWalletName());
                    return;
                case R.id.delete:
                    pwdType = 1;
                    AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_DELETE);
                    showDialog(new OnIClickListener() {
                        @Override
                        public final void onClick() {
                            WalletDetailActivity.1.this.lambda$onNoDoubleClick$4();
                        }
                    }, true);
                    return;
                case R.id.iv_copy:
                    AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_COPY_ADDRESS);
                    if (BackupReminder.isWalletBackup(acWallet)) {
                        UIUtils.copy(acWallet.getAddress());
                        WalletDetailActivity walletDetailActivity = WalletDetailActivity.this;
                        walletDetailActivity.toast(walletDetailActivity.getString(R.string.already_copy));
                        return;
                    }
                    WalletDetailActivity walletDetailActivity2 = WalletDetailActivity.this;
                    walletDetailActivity2.toast(walletDetailActivity2.getResources().getString(R.string.backup_first_tip));
                    return;
                case R.id.iv_shield_add:
                    Intent intent = new Intent(WalletDetailActivity.this, AddWalletActivityV2.class);
                    intent.putExtra(AddWalletType.INTENT_KEY_WALLET_TYPE, 1);
                    startActivity(intent);
                    return;
                case R.id.iv_switch:
                    SelectWalletActivity.startActivityFromDetail(WalletDetailActivity.this, true);
                    return;
                case R.id.iv_tip:
                    AnalyticsHelper.logEvent(relationWalletCount > 0 ? AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_HELP : AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_OPEN_RELATE_HELP);
                    WalletDetailActivity walletDetailActivity3 = WalletDetailActivity.this;
                    PopupWindowUtil.showPopupText(walletDetailActivity3, walletDetailActivity3.getResources().getString(relationWalletCount > 0 ? R.string.account_manager_related_mnemonic_tip : R.string.open_related_account_tip), ivTip, false);
                    return;
                case R.id.ll_common_left:
                    back();
                    return;
                case R.id.relation_layout:
                    if (relationWalletCount > 0) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT);
                        Intent intent2 = new Intent();
                        intent2.setClass(WalletDetailActivity.this, RelationActivity.class);
                        intent2.putExtra(TronConfig.WALLET_NAME, acWallet.getWalletName());
                        intent2.putExtra(TronConfig.WALLET_DATA, hdTronRelationshipBean != null ? hdTronRelationshipBean.getNonHd() : false);
                        goForResult(intent2, TronConfig.TOSELECTRELATION);
                        return;
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_OPEN_RELATE);
                    showDialog(new OnIClickListener() {
                        @Override
                        public final void onClick() {
                            WalletDetailActivity.1.this.lambda$onNoDoubleClick$2();
                        }
                    });
                    return;
                case R.id.rl_approve:
                    AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_APPROVED);
                    String approveListUrl = IRequest.getApproveListUrl(acWallet.getAddress());
                    if (SpAPI.THIS.isCold()) {
                        Toast((int) R.string.not_support_cold_wallet);
                        return;
                    } else if (StringTronUtil.isEmpty(approveListUrl)) {
                        return;
                    } else {
                        CommonWebActivityV3.start(getIContext(), approveListUrl, getString(R.string.contract_approve), -2, true);
                        return;
                    }
                case R.id.rl_cold_pair:
                    AnalyticsHelper.logEvent(AnalyticsHelper.PairWatchColdWallet.WALLET_MANEGE_COLD_WALLET_PAIR_CLICK);
                    PairColdWalletExplainActivity.start(WalletDetailActivity.this);
                    return;
                case R.id.rl_keystore:
                    AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_BACKUP_KEYSTORE);
                    backTips(2, new fun1());
                    return;
                case R.id.rl_mnemonic:
                    backTips(0, new fun2());
                    return;
                case R.id.rl_privatekey:
                    AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_BACKUP_PRI_KEY);
                    backTips(1, new fun3());
                    return;
                case R.id.rl_switch_shield:
                    SelectWalletActivity.startActivity((Context) WalletDetailActivity.this, false);
                    return;
                default:
                    return;
            }
        }

        public void lambda$onNoDoubleClick$2() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public final void doOnBackground() {
                    WalletDetailActivity.1.this.lambda$onNoDoubleClick$1();
                }
            });
        }

        public void lambda$onNoDoubleClick$1() {
            try {
                final Wallet wallet = WalletUtils.getWallet(acWallet.getWalletName(), password);
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        WalletDetailActivity.1.this.lambda$onNoDoubleClick$0(wallet);
                    }
                });
            } catch (Exception e) {
                dismissLoadingDialog();
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
        }

        public void lambda$onNoDoubleClick$0(Wallet wallet) {
            String str;
            dismissLoadingDialog();
            if (wallet.getECKey() == null || wallet.getECKey().getPrivKeyBytes() == null) {
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
                return;
            }
            PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
            try {
                str = WalletUtils.mnemonic(acWallet.getWalletName(), password);
            } catch (IOException e) {
                LogUtils.e(e);
                str = null;
                HDTronWalletController.insertWalletAndSyncNonHDFlag(acWallet, new Wallet(str, new WalletPath()).getAddress());
                updateRelationInfo();
            } catch (CipherException e2) {
                LogUtils.e(e2);
                str = null;
                HDTronWalletController.insertWalletAndSyncNonHDFlag(acWallet, new Wallet(str, new WalletPath()).getAddress());
                updateRelationInfo();
            }
            HDTronWalletController.insertWalletAndSyncNonHDFlag(acWallet, new Wallet(str, new WalletPath()).getAddress());
            updateRelationInfo();
        }

        public class fun1 implements BackupCallback {
            fun1() {
            }

            @Override
            public void callback() {
                pwdType = 2;
                showDialog(new OnIClickListener() {
                    @Override
                    public final void onClick() {
                        WalletDetailActivity.1.1.this.lambda$callback$2();
                    }
                });
            }

            public void lambda$callback$2() {
                AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                    @Override
                    public final void doOnBackground() {
                        WalletDetailActivity.1.1.this.lambda$callback$1();
                    }
                });
            }

            public void lambda$callback$1() {
                try {
                    final Wallet wallet = WalletUtils.getWallet(acWallet.getWalletName(), password);
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            WalletDetailActivity.1.1.this.lambda$callback$0(wallet);
                        }
                    });
                } catch (Exception e) {
                    dismissLoadingDialog();
                    PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
                    SentryUtil.captureException(e);
                    LogUtils.e(e);
                }
            }

            public void lambda$callback$0(Wallet wallet) {
                dismissLoadingDialog();
                if (wallet.getECKey() == null || wallet.getECKey().getPrivKeyBytes() == null) {
                    PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
                    return;
                }
                PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
                intent = new Intent(mContext, BackKeystoreActivity.class);
                intent.putExtra(TronConfig.WALLET_DATA, acWallet.getWalletName());
                intent.putExtra(TronConfig.WALLET_PASSWORD, password);
                intent.putExtra(TronConfig.WALLET_extra, wallet.getKeyStore());
                intent.putExtra(TronConfig.SHIELD_IS_TRC20, false);
                go(intent);
            }
        }

        public class fun2 implements BackupCallback {
            fun2() {
            }

            @Override
            public void callback() {
                pwdType = 4;
                if (SpAPI.THIS.isBackUp(acWallet.getWalletName()) && !SpAPI.THIS.isCheckedNoMoreShow(SpAPI.SET_TIPS_NO_MORE_SHOW_MNEMONIC)) {
                    BackupTipsActivity.start(mContext, 0, acWallet.getWalletName(), false);
                } else {
                    showDialog(new OnIClickListener() {
                        @Override
                        public final void onClick() {
                            WalletDetailActivity.1.2.this.lambda$callback$3();
                        }
                    });
                }
            }

            public void lambda$callback$3() {
                AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                    @Override
                    public final void doOnBackground() {
                        WalletDetailActivity.1.2.this.lambda$callback$2();
                    }
                });
            }

            public void lambda$callback$2() {
                try {
                    final String mnemonic = WalletUtils.mnemonic(acWallet.getWalletName(), password);
                    PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            WalletDetailActivity.1.2.this.lambda$callback$0(mnemonic);
                        }
                    });
                } catch (Exception e) {
                    dismissLoadingDialog();
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            WalletDetailActivity.1.2.this.lambda$callback$1();
                        }
                    });
                    LogUtils.e(e);
                    SentryUtil.captureException(e);
                }
            }

            public void lambda$callback$0(String str) {
                dismissLoadingDialog();
                mFirebaseAnalytics.logEvent("Backup_Mnemonic", null);
                BackUpMnemonicActivity.start(mContext, str, acWallet.getWalletName());
            }

            public void lambda$callback$1() {
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
            }
        }

        public class fun3 implements BackupCallback {
            fun3() {
            }

            @Override
            public void callback() {
                pwdType = 3;
                showDialog(new OnIClickListener() {
                    @Override
                    public final void onClick() {
                        WalletDetailActivity.1.3.this.lambda$callback$3();
                    }
                });
            }

            public void lambda$callback$3() {
                AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                    @Override
                    public final void doOnBackground() {
                        WalletDetailActivity.1.3.this.lambda$callback$2();
                    }
                });
            }

            public void lambda$callback$2() {
                try {
                    final Wallet wallet = WalletUtils.getWallet(acWallet.getWalletName(), password);
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            WalletDetailActivity.1.3.this.lambda$callback$0(wallet);
                        }
                    });
                } catch (Exception e) {
                    dismissLoadingDialog();
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            WalletDetailActivity.1.3.this.lambda$callback$1();
                        }
                    });
                    LogUtils.e(e);
                }
            }

            public void lambda$callback$0(Wallet wallet) {
                dismissLoadingDialog();
                if (wallet.getECKey() != null && wallet.getECKey().getPrivKeyBytes() != null) {
                    PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
                    if (wallet.isShieldedWallet()) {
                        return;
                    }
                    intent = new Intent(mContext, BackPrivateKeyActivity.class);
                    intent.putExtra(TronConfig.WALLET_DATA, acWallet.getWalletName());
                    intent.putExtra(TronConfig.WALLET_extra, ByteArray.toHexString(wallet.getECKey().getPrivKeyBytes()));
                    go(intent);
                    return;
                }
                dismissLoadingDialog();
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
            }

            public void lambda$callback$1() {
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
            }
        }

        public void lambda$onNoDoubleClick$4() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public final void doOnBackground() {
                    WalletDetailActivity.1.this.lambda$onNoDoubleClick$3();
                }
            });
        }

        public void lambda$onNoDoubleClick$3() {
            try {
                Wallet wallet = WalletUtils.getWallet(acWallet.getWalletName(), password);
                if ((wallet != null && wallet.getECKey() != null && wallet.getECKey().getPrivKeyBytes() != null && wallet.getECKey().getPrivKeyBytes().length != 0 && wallet.getAddress() != null && SpAPI.THIS.getWalletAddress(acWallet.getWalletName()).equals(wallet.getAddress())) || (wallet != null && wallet.isWatchOnly())) {
                    PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
                    if (acWallet.isShieldedWallet()) {
                        try {
                            Set<String> walletNames = WalletUtils.getWalletNames();
                            walletNames.remove(acWallet.getWalletName());
                            for (String str : walletNames) {
                                Wallet wallet2 = WalletUtils.getWallet(str);
                                if (wallet2 != null && wallet2.getAddress() != null) {
                                    wallet2.getAddress().equals(acWallet.getAddress());
                                }
                            }
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                    }
                    SpAPI.THIS.removeWallet(acWallet.getWalletName());
                    WalletType.removeWatchWallet(acWallet);
                    HDTronWalletController.deleteHdTronRelationshipBean(acWallet.getWalletName());
                    RxBus.getInstance().post(Event.DELETE_WALLET, acWallet.getWalletName());
                    TronApplication.WALlET_AES.put(wallet.getWalletName(), "");
                    Toast((int) R.string.delete_already);
                    Object[] objArr = new Object[1];
                    objArr[0] = Integer.valueOf(acWallet.isWatchOnly() ? 2 : 1);
                    AnalyticsHelper.logEventFormat(AnalyticsHelper.WalletManagerPage.EVENT_WALLET_MANAGER_DELETE_WALLET, objArr);
                    Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                    if (WalletUtils.getPublicWalletNames().size() <= 0) {
                        Intent intent = new Intent(WalletDetailActivity.this, EmptyWalletActivity.class);
                        intent.setFlags(268468224);
                        startActivity(intent);
                    } else if (selectedPublicWallet != null && !acWallet.getWalletName().equals(selectedPublicWallet.getWalletName())) {
                        rxManager.post(Event.SELECTEDWALLET, SpAPI.THIS.getSelectedWallet());
                    }
                    SocketManager.getInstance().disconnect();
                    back();
                    return;
                }
                dismissLoadingDialog();
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
            } catch (Exception e2) {
                dismissLoadingDialog();
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
                LogUtils.e(e2);
            }
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new fun1();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    public void setV(boolean z, View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(z ? View.VISIBLE : View.GONE);
        }
    }

    private void initClick() {
        this.editNameView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_WALLET_NAME);
                if (acWallet != null) {
                    intent = new Intent(mContext, ChangeNameActivity.class);
                    intent.putExtra(TronConfig.WALLET_DATA, acWallet.getWalletName());
                    WalletDetailActivity walletDetailActivity = WalletDetailActivity.this;
                    walletDetailActivity.goForResult(walletDetailActivity.intent, 2020);
                }
            }
        });
        this.changePasswordView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_WALLET_PASSWORD);
                if (acWallet != null) {
                    intent = new Intent(mContext, ChangePasswordActivity.class);
                    intent.putExtra(TronConfig.WALLET_DATA, acWallet.getWalletName());
                    WalletDetailActivity walletDetailActivity = WalletDetailActivity.this;
                    walletDetailActivity.goForResult(walletDetailActivity.intent, TronConfig.TOCHANGEPASSWORD);
                }
            }
        });
        this.rlSignManager.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_PERMISSION);
                if (!TronConfig.hasNet) {
                    IToast.getIToast().setImage(R.mipmap.toast_error).show(R.string.no_network);
                } else if (!SpAPI.THIS.isCold()) {
                    if (acWallet != null) {
                        WalletDetailActivity walletDetailActivity = WalletDetailActivity.this;
                        MultiSignatureManagerActivity.start(walletDetailActivity, walletDetailActivity.acWallet.getWalletName());
                    }
                } else {
                    ToastError(R.string.cold);
                }
            }
        });
        this.rlSignWait.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_MULTI_SIGN);
                if (!SpAPI.THIS.isCold()) {
                    if (acWallet != null) {
                        WalletDetailActivity walletDetailActivity = WalletDetailActivity.this;
                        DealSignActivity.start((Context) walletDetailActivity, walletDetailActivity.acWallet.getWalletName(), hasSocketConnect, true);
                        return;
                    }
                    return;
                }
                ToastError(R.string.cold);
            }
        });
        this.tvBackupHistory.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.BackHistoryPage.WALLET_DETAILS_BACKUP_HISTORY);
                BackupRecordActivity.start(WalletDetailActivity.this, true, false);
            }
        });
        this.rlSafetyCheck.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                go(SecurityHomeActivity.class);
            }
        });
        this.llCommonLeft.setOnClickListener(getOnClickListener());
        this.ivCopy.setOnClickListener(getOnClickListener());
        this.ivSwitch.setOnClickListener(getOnClickListener());
        this.rlRelationLayout.setOnClickListener(getOnClickListener());
        this.rlMnemonic.setOnClickListener(getOnClickListener());
        this.rlPrivatekey.setOnClickListener(getOnClickListener());
        this.rlKeystore.setOnClickListener(getOnClickListener());
        this.delete.setOnClickListener(getOnClickListener());
        this.rlApprove.setOnClickListener(getOnClickListener());
        this.dappAuthorized.setOnClickListener(getOnClickListener());
        this.rlSwitchShield.setOnClickListener(getOnClickListener());
        this.ivShieldAdd.setOnClickListener(getOnClickListener());
        this.rlColdPairLayout.setOnClickListener(getOnClickListener());
        this.ivTip.setOnClickListener(getOnClickListener());
    }

    public void updateRelationInfo() {
        HdTronRelationshipBean hdWalletRelationShip = HDTronWalletController.getHdWalletRelationShip(this.acWallet.getWalletName());
        if (hdWalletRelationShip != null && !hdWalletRelationShip.getNonHd()) {
            List<Wallet> queryWalletRelationship2 = HDTronWalletController.queryWalletRelationship2(this.acWallet.getWalletName(), HDTronWalletController.Order.CreateTime);
            if (queryWalletRelationship2 != null && queryWalletRelationship2.size() > 0) {
                this.relationWalletCount = queryWalletRelationship2.size();
                TextView textView = this.tvLinkedAccountCount;
                StringBuilder sb = new StringBuilder();
                sb.append(getResources().getString(R.string.associate_account));
                sb.append("\t");
                sb.append(this.relationWalletCount - 1);
                textView.setText(sb.toString());
                this.rlRelationLayout.setVisibility(View.VISIBLE);
                updateWalletPath();
                return;
            }
            this.rlRelationLayout.setVisibility(View.GONE);
            this.llIndex.setVisibility(View.GONE);
            return;
        }
        this.llIndex.setVisibility(View.GONE);
        this.rlRelationLayout.setVisibility(View.GONE);
    }

    public void showDialog(final OnIClickListener onIClickListener, boolean z) {
        String string;
        Wallet wallet = this.acWallet;
        if (wallet == null) {
            return;
        }
        int createType = wallet.getCreateType();
        int i = R.string.delected_wallet;
        if (createType == 7) {
            string = getString(R.string.delected_samsung_wallet_desc);
        } else if (this.acWallet.isWatchOnly()) {
            if (z) {
                string = getString(R.string.deleted2);
            }
            string = "";
        } else {
            i = R.string.wallet_manager_enter_password;
            if (z) {
                string = getString(R.string.deleted2);
            }
            string = "";
        }
        this.common2Dialog = new Common2Dialog(this.mContext).setTitle(getString(i)).setInnerTitle(string).setBtListener(getString(R.string.ok2), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showDialog$0(onIClickListener, view);
            }
        });
        if (!this.acWallet.isWatchOnly()) {
            this.common2Dialog.addEditext();
        }
        this.common2Dialog.show();
    }

    public void lambda$showDialog$0(OnIClickListener onIClickListener, View view) {
        this.password = this.common2Dialog.getEditextText();
        this.common2Dialog.dismiss();
        showLoadingDialog();
        if (this.acWallet.isWatchOnly()) {
            dismissLoadingDialog();
            onIClickListener.onClick();
        } else if (TextUtils.isEmpty(this.password)) {
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

    public void showDialog(OnIClickListener onIClickListener) {
        showDialog(onIClickListener, false);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            ((WalletDetailPresenter) this.mPresenter).onActivityResult(i, i2, intent);
        }
    }

    @Override
    public void updateWalletName(String str) {
        getIntent().putExtra(TronConfig.WALLET_NAME, str);
        this.mWalletName = str;
        this.tvName.setText(str);
    }

    @Override
    public void updateWalletPath() {
        WalletPath mnemonicPath = WalletUtils.getMnemonicPath(this.acWallet.getMnemonicPathString(), this.acWallet.getWalletName());
        if (mnemonicPath != null) {
            this.llIndex.setVisibility(View.VISIBLE);
            this.llMulPathLayout.setVisibility(View.VISIBLE);
            this.tvPath.setText(getResources().getString(R.string.select_wallet_path, WalletPath.buildPathString(mnemonicPath)));
            this.tvAddress.setText(this.acWallet.getAddress());
        }
    }

    @Override
    public void updateCardBg() {
        this.acWallet = WalletUtils.getSelectedWallet();
        setTopCard();
    }

    @Override
    public void updateWaitSignCount(int i) {
        if (i > 0) {
            this.dealSignCount.setVisibility(View.VISIBLE);
            TextView textView = this.dealSignCount;
            textView.setText(i + "");
            this.tvMulInfo.setText(R.string.deal_sign_transaction);
            return;
        }
        this.dealSignCount.setVisibility(View.GONE);
        this.tvMulInfo.setText(R.string.request_transaction);
    }

    @Override
    public void updateWalletToColdWatch() {
        this.rlColdPairLayout.setVisibility(View.GONE);
        this.rlManagerApprove.setVisibility(View.VISIBLE);
        this.rlSignWait.setVisibility(View.VISIBLE);
        this.llCard.setBackground(getDrawable(R.drawable.bg_wallet_detail_watch_cold));
        this.ivCardIcon.setImageResource(R.mipmap.bg_icon_watch_cold);
    }

    public void backTips(int i, BackupCallback backupCallback) {
        if (i == 0) {
            backupCallback.callback();
        } else if (i == 1) {
            if (!SpAPI.THIS.isCheckedNoMoreShow(SpAPI.SET_TIPS_NO_MORE_SHOW_PRIVATE_KEY)) {
                BackupTipsActivity.start(this, i, this.acWallet.getWalletName());
            } else {
                backupCallback.callback();
            }
        } else if (i != 2) {
        } else {
            if (!SpAPI.THIS.isCheckedNoMoreShow(SpAPI.SET_TIPS_NO_MORE_SHOW_KEYSTORE)) {
                BackupTipsActivity.start(this, i, this.acWallet.getWalletName());
            } else {
                backupCallback.callback();
            }
        }
    }

    public void back() {
        if (this.hasSwitchWallet) {
            Intent intent = new Intent(this.mContext, MainTabActivity.class);
            intent.setFlags(67108864);
            go(intent);
            super.exit();
            return;
        }
        super.exit();
    }
}
