package com.tron.wallet.business.tabmy.myhome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.business.ledger.manage.EquipmentManageActivity;
import com.tron.wallet.business.message.MessageCenterActivity;
import com.tron.wallet.business.message.db.TransactionMessageManager;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.tabmy.about.AboutActivity;
import com.tron.wallet.business.tabmy.advancedfeatures.AdvancedFeaturesActivity;
import com.tron.wallet.business.tabmy.allhistory.TrxAllHistoryActivity;
import com.tron.wallet.business.tabmy.myhome.addressbook.AddressBookActivity;
import com.tron.wallet.business.tabmy.myhome.settings.UnitTestActivity;
import com.tron.wallet.business.tabswap.bean.AppStatusOutput;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordActivity;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceController;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.CommonWebTitleActivity;
import com.tron.wallet.common.components.BadgeButton;
import com.tron.wallet.common.components.dialog.Common8Dialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgMyBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.BleUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.tron.walletserver.Wallet;
public class MyFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private static final String TAG = "MyFragment";
    RelativeLayout about;
    RelativeLayout addressBook;
    View advancedFeatures;
    RelativeLayout announcement;
    private AppStatusOutput appStatus;
    View backupLayout;
    private FgMyBinding binding;
    View dd;
    private Common8Dialog dialog;
    RelativeLayout friendInvitation;
    RelativeLayout hardwareManagement;
    RelativeLayout help;
    private int i = 0;
    View ivHelpDot;
    private long l1;
    View lineAdvancedFeatures;
    View migrateDot;
    private BasePopupView popupView;
    FrameLayout rlBell;
    private RxManager rxManager;
    RelativeLayout setting;
    RelativeLayout shieldWalletManager;
    View transferHistory;
    BadgeButton tvBell;
    TextView tvHasNewVersion;
    TextView tvHelp;
    View unitTestView;
    View vAn;
    RelativeLayout walletManager;

    @Override
    protected void processData() {
        LogUtils.i(TAG, "processData");
        this.rxManager = new RxManager();
        this.appStatus = SpAPI.THIS.getAppStatus();
        this.rxManager.on(Event.DD2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
        this.rxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$1(obj);
            }
        });
        this.rxManager.on(Event.MSG_CENTER_UPDATE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$2(obj);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.rlBell, UIUtils.dip2px(15.0f), UIUtils.dip2px(15.0f), UIUtils.dip2px(15.0f), UIUtils.dip2px(15.0f));
        if (SpAPI.THIS.isCold()) {
            this.tvHelp.setText(R.string.help2);
            this.transferHistory.setVisibility(View.GONE);
            this.friendInvitation.setVisibility(View.GONE);
            this.addressBook.setVisibility(View.GONE);
            this.lineAdvancedFeatures.setVisibility(View.VISIBLE);
            this.binding.safetyAcademy.setVisibility(View.GONE);
        } else {
            this.lineAdvancedFeatures.setVisibility(View.VISIBLE);
            this.binding.safetyAcademy.setVisibility(View.VISIBLE);
        }
        if (SpAPI.THIS.getCurrentChainName().equals("MainChain")) {
            setShieldManagerVisibility(0);
        } else {
            setShieldManagerVisibility(8);
        }
        initClick();
        if (this.appStatus.isHideShieldManager()) {
            setShieldManagerVisibility(8);
        }
        this.unitTestView.setVisibility(View.GONE);
        if (IRequest.isNile()) {
            this.rlBell.setVisibility(View.GONE);
            this.rlBell.setClickable(false);
        }
    }

    public void lambda$processData$0(Object obj) throws Exception {
        showOrHide();
    }

    public void lambda$processData$1(Object obj) throws Exception {
        if (SpAPI.THIS.getCurrentChainName().equals("MainChain")) {
            setShieldManagerVisibility(0);
        } else {
            setShieldManagerVisibility(8);
        }
    }

    public void lambda$processData$2(Object obj) throws Exception {
        if (obj instanceof Long) {
            updateMessageUI(((Long) obj).longValue());
        }
    }

    private void checkLedgerItemVisible() {
        if (BleUtils.isSupportBle() && !SpAPI.THIS.isCold() && SpAPI.THIS.getCurIsMainChain()) {
            BleDeviceController.getInstance().empty().subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$checkLedgerItemVisible$3((Boolean) obj);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    LogUtils.e((Throwable) obj);
                }
            });
        } else {
            this.hardwareManagement.setVisibility(View.GONE);
        }
    }

    public void lambda$checkLedgerItemVisible$3(Boolean bool) throws Exception {
        this.hardwareManagement.setVisibility(bool.booleanValue() ? View.GONE : View.VISIBLE);
    }

    private void updateMessage() {
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$updateMessage$5();
            }
        });
    }

    public void lambda$updateMessage$5() {
        updateMessageUI(TransactionMessageManager.getInstance().queryUnread());
    }

    private void updateMessageUI(final long j) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateMessageUI$6(j);
            }
        });
    }

    public void lambda$updateMessageUI$6(long j) {
        if (IRequest.isNile()) {
            return;
        }
        if (j == 0) {
            this.tvBell.setVisibility(View.GONE);
        } else if (j > 99) {
            this.tvBell.setBadgeTextDef("99+");
            this.tvBell.setVisibility(View.VISIBLE);
        } else {
            this.tvBell.setBadgeTextDef(String.valueOf(j));
            this.tvBell.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgMyBinding inflate = FgMyBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    public void mappingId() {
        this.dd = this.binding.dd;
        this.tvHasNewVersion = this.binding.tvHasNewVersion;
        this.vAn = this.binding.vAn;
        this.tvHelp = this.binding.tvHelp;
        this.hardwareManagement = this.binding.hardwareManagement;
        this.transferHistory = this.binding.transferHistory;
        this.backupLayout = this.binding.backupLayout;
        this.help = this.binding.help;
        this.setting = this.binding.setting;
        this.about = this.binding.about;
        this.announcement = this.binding.announcement;
        this.walletManager = this.binding.walletManager;
        this.shieldWalletManager = this.binding.shieldWalletManager;
        this.friendInvitation = this.binding.friendInvitation;
        this.addressBook = this.binding.addressBook;
        this.advancedFeatures = this.binding.advancedFeatures;
        this.rlBell = this.binding.rlBell;
        this.tvBell = this.binding.tvBell;
        this.unitTestView = this.binding.unitTest;
        this.lineAdvancedFeatures = this.binding.lineAdvancedFeatures;
        this.migrateDot = this.binding.ivDotTip;
        this.ivHelpDot = this.binding.ivHelpDotTip;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLedgerItemVisible();
        showOrHide();
        AnalyticsHelper.logEvent(AnalyticsHelper.HomePage.ENTER_HOME_PAGE_TAB_MY);
        if (SpAPI.THIS.getMigrateWarning() || TextUtils.equals(MigrateConfig.APP_ID_GLOBAL, "com.tronlinkpro.wallet") || SpAPI.THIS.isCold()) {
            this.migrateDot.setVisibility(View.GONE);
        }
    }

    private void initClick() {
        this.transferHistory.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_HISTORY);
                go(TrxAllHistoryActivity.class);
            }
        });
        this.backupLayout.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.BackHistoryPage.MY_WALLET_BACKUP_HISTORY);
                BackupRecordActivity.start(getIContext());
            }
        });
        this.hardwareManagement.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                EquipmentManageActivity.startActivity(getContext());
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_LEDGER);
            }
        });
        this.ivHelpDot.setVisibility((SpAPI.THIS.getWalletIntroHasShow() || SpAPI.THIS.isCold()) ? View.GONE : View.VISIBLE);
        this.help.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_HELP);
                if (SpAPI.THIS.isCold()) {
                    UIUtils.toColdWallet(mContext);
                    return;
                }
                CommonWebActivityV3.start((Context) mContext, IRequest.getWalletGuideUrl(), "", -2, true);
                SpAPI.THIS.setWalletIntroHasShow();
            }
        });
        this.setting.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                go(SettingActivity.class);
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_SETTING);
            }
        });
        this.rlBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, MessageCenterActivity.class));
            }
        });
        this.about.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_ABOUT);
                go(AboutActivity.class);
            }
        });
        this.announcement.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (SpAPI.THIS.isCold()) {
                    Toast((int) R.string.not_support_cold_wallet);
                    return;
                }
                if (getReceiveNoticeStatus()) {
                    vAn.setVisibility(View.GONE);
                    SpAPI.THIS.setNoticeList(TronConfig.receiveNoticeData);
                    rxManager.post(Event.DD, "announcement");
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_ANNOUNCEMENT);
                CommonWebActivityV3.start(getIContext(), TextUtils.equals("2", SpAPI.THIS.useLanguage()) ? TronConfig.HTML_GUIDE_zh : TronConfig.HTML_GUIDE_en, getIContext().getString(R.string.announcement), -2, false);
            }
        });
        this.friendInvitation.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Wallet selectedWallet = WalletUtils.getSelectedWallet();
                if (selectedWallet == null) {
                    return;
                }
                if (selectedWallet.isShieldedWallet()) {
                    MyFragment myFragment = MyFragment.this;
                    myFragment.toast(myFragment.getString(R.string.shield_wallet_can_not_use_this_account));
                    return;
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_FRIEND_INVITATION);
                TronConfig.address = selectedWallet.getAddress();
                TronConfig.walletName = selectedWallet.getWalletName();
                CommonWebTitleActivity.start(getActivity(), TronConfig.HTML_SHARE, getResources().getString(R.string.friend_invitation));
            }
        });
        this.walletManager.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Wallet selectedWallet = WalletUtils.getSelectedWallet();
                if (selectedWallet != null) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_WALLET_MANAGEMENT);
                    WalletDetailActivity.startActivity(mContext, selectedWallet.getWalletName(), false);
                }
            }
        });
        this.binding.safetyAcademy.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                CommonWebActivityV3.start(view.getContext(), "https://dapp.tronlink.org/#/safetyAcademy", "", 1, true);
            }
        });
        this.addressBook.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AddressBookActivity.start(mContext, AddressBookActivity.TYPE_FROM_MY);
                AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_ADDRESS_BOOK);
            }
        });
        this.advancedFeatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$initClick$7(view);
            }
        });
    }

    public void lambda$initClick$7(View view) {
        go(AdvancedFeaturesActivity.class);
        AnalyticsHelper.logEvent(AnalyticsHelper.MyPage.CLICK_PROFILE_ADVANCED_FEATURES);
    }

    private void lambda$initClick$8(View view) {
        go(UnitTestActivity.class);
    }

    private boolean existShieldAccount() {
        Wallet wallet;
        for (String str : WalletUtils.getWalletNames()) {
            if (!TextUtils.isEmpty(str) && (wallet = WalletUtils.getWallet(str)) != null && wallet.isShieldedWallet()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        this.i = 0;
        StatusBarUtils.setLightStatusBar(getActivity(), true);
    }

    private void showOrHide() {
        int i = 0;
        if (SpAPI.THIS.isCold()) {
            this.vAn.setVisibility(View.GONE);
            this.dd.setVisibility(View.GONE);
        } else {
            if (TronConfig.updateOutput != null && TronConfig.updateOutput.data != null && !StringTronUtil.isEmpty(TronConfig.updateOutput.data.version)) {
                if (!IRequest.isShasta() && (TronConfig.updateOutput.data.upgrade || TronConfig.updateOutput.data.force)) {
                    this.dd.setVisibility(View.VISIBLE);
                    this.tvHasNewVersion.setText(R.string.discover_new_version);
                    TouchDelegateUtils.expandViewTouchDelegate(this.tvHasNewVersion, UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f), UIUtils.dip2px(10.0f));
                } else {
                    this.dd.setVisibility(View.GONE);
                }
            }
            if (getReceiveNoticeStatus()) {
                this.vAn.setVisibility(View.VISIBLE);
            } else {
                this.vAn.setVisibility(View.GONE);
            }
        }
        if ((IRequest.isRelease() || IRequest.isTest() || IRequest.isPrerelease()) && SpAPI.THIS.getCurIsMainChain() && !SpAPI.THIS.isCold()) {
            this.rlBell.setVisibility(View.VISIBLE);
            updateMessage();
            this.rlBell.setClickable(true);
        } else {
            this.rlBell.setVisibility(View.GONE);
            this.rlBell.setClickable(false);
        }
        this.ivHelpDot.setVisibility((SpAPI.THIS.getWalletIntroHasShow() || SpAPI.THIS.isCold()) ? 8 : 8);
    }

    public boolean getReceiveNoticeStatus() {
        List<String> noticeList = SpAPI.THIS.getNoticeList();
        return (noticeList.isEmpty() || noticeList.contains(TronConfig.receiveNoticeData)) ? false : true;
    }

    public void setShieldManagerVisibility(int i) {
        this.shieldWalletManager.setVisibility(View.GONE);
    }
}
