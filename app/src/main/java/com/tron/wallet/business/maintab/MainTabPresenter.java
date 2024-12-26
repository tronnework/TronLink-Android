package com.tron.wallet.business.maintab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.net.SignatureManager;
import com.tron.tron_base.frame.utils.ChannelUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.BuildConfig;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.assetshome.AssetsFragment;
import com.tron.wallet.business.cold.ColdFragment;
import com.tron.wallet.business.maintab.MainTabContract;
import com.tron.wallet.business.maintab.MainTabPresenter;
import com.tron.wallet.business.maintab.bean.DeviceInfoBean;
import com.tron.wallet.business.message.db.TransactionMessageManager;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.pull.PullActivity;
import com.tron.wallet.business.reminder.ReminderManager;
import com.tron.wallet.business.tabdapp.home.DappHomeFragment;
import com.tron.wallet.business.tabdapp.jsbridge.BlackResultListBean;
import com.tron.wallet.business.tabmy.myhome.MyFragment;
import com.tron.wallet.business.tabswap.SwapMainFragment;
import com.tron.wallet.business.walletmanager.selectwallet.controller.RecentlyWalletController;
import com.tron.wallet.common.bean.AppLanguageOutput;
import com.tron.wallet.common.bean.DappJsOutput;
import com.tron.wallet.common.bean.NoticeRemindBean;
import com.tron.wallet.common.bean.Result;
import com.tron.wallet.common.bean.token.TRC20Output;
import com.tron.wallet.common.bean.update.UpdateOutput;
import com.tron.wallet.common.bean.user.SystemConfigOutput;
import com.tron.wallet.common.components.MainTabView;
import com.tron.wallet.common.components.popupwindow.ScanPopWindow;
import com.tron.wallet.common.components.popupwindow.SelectedAssetsWindow;
import com.tron.wallet.common.config.AccountFeeManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.AppStatusUtils;
import com.tron.wallet.common.utils.DappJs;
import com.tron.wallet.common.utils.Md5Util;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.Controller.DappAuthorizedController;
import com.tron.wallet.db.Controller.DappBlackListController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.AssetIssueContractDao;
import com.tron.wallet.db.bean.DappAuthorizedProjectBean;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tron.wallet.update.location.UpdateHelper;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.walletserver.Wallet;
public class MainTabPresenter extends MainTabContract.Presenter {
    private static final String TAG = "MainTabPresenter";
    public static UpdateHelper updateHelper = new UpdateHelper();
    private List<TRC20Output> all20List;
    private AssetIssueContractDao assetIssueContractDao;
    private AssetsFragment mAssetsFragment;
    private ColdFragment mColdFragment;
    DappHomeFragment mDappFragment;
    private FragmentManager mFragmentManager;
    private MyFragment mMyFragment;
    private SwapMainFragment mSwapMarketFragment;
    private String pendingOutsideLink;
    private ScanPopWindow scanPopWindow;
    private SelectedAssetsWindow selectedAssetsWindow;
    public boolean isSwap = false;
    private boolean restartAc = false;
    private boolean mIsColdWallet = SpAPI.THIS.isCold();
    private boolean isFirstClickDapp = true;

    @Override
    public boolean isSwapMode() {
        return this.isSwap;
    }

    @Override
    protected void onStart() {
    }

    @Override
    public void setOnClickListener(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        initViewListener();
        if (!this.mIsColdWallet) {
            addOrShowAssetsFragment();
        } else if (this.mColdFragment.isAdded()) {
            this.mFragmentManager.beginTransaction().show(this.mColdFragment).hide(this.mMyFragment).commitAllowingStateLoss();
        } else {
            this.mFragmentManager.beginTransaction().remove(this.mColdFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.main, this.mColdFragment, M.M_COLD).show(this.mColdFragment).hide(this.mMyFragment).commitAllowingStateLoss();
        }
    }

    public void addEvent() {
        this.mRxManager.on(Event.SHOWSCAN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$0(obj);
            }
        });
        this.mRxManager.on(Event.RESET_TAB, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$1(obj);
            }
        });
        this.mRxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$2(obj);
            }
        });
        this.mRxManager.on("CONNECT", new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$3(obj);
            }
        });
        this.mRxManager.on(Event.ENTER_SWAP, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$4(obj);
            }
        });
        this.mRxManager.on(Event.MSG_CENTER_UPDATE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$5(obj);
            }
        });
        this.mRxManager.on(Event.DD, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$6(obj);
            }
        });
        this.mRxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                MainTabPresenter.lambda$addEvent$7(obj);
            }
        });
        this.mRxManager.on(Event.SWITCH_DAPP_PAGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addEvent$8(obj);
            }
        });
    }

    public void lambda$addEvent$0(Object obj) throws Exception {
        if (obj instanceof String) {
            ScanPopWindow scanPopWindow = new ScanPopWindow((Activity) ((MainTabContract.View) this.mView).getIContext(), M.M_TRX, (String) obj);
            this.scanPopWindow = scanPopWindow;
            if (scanPopWindow.isShowing()) {
                this.scanPopWindow.dismiss();
            }
            this.scanPopWindow.showAtLocation(((MainTabContract.View) this.mView).getRoot(), 81, 0, 0);
        }
    }

    public void lambda$addEvent$1(Object obj) throws Exception {
        ((MainTabContract.View) this.mView).getTabViews().setTabSelected(0);
    }

    public void lambda$addEvent$2(Object obj) throws Exception {
        LogUtils.d(TAG, "RECEIVE SWITCH_CHAIN");
        getSystemConfig();
        AccountFeeManager.initAccountFee();
        ((MainTabContract.View) this.mView).refreshMarketTab(SpAPI.THIS.isCold());
    }

    public void lambda$addEvent$3(Object obj) throws Exception {
        LogUtils.d(TAG, "RECEIVE CONNECT");
        ((MainTabContract.View) this.mView).refreshMarketTab(SpAPI.THIS.isCold());
    }

    public void lambda$addEvent$4(Object obj) throws Exception {
        onClickSwap();
    }

    public void lambda$addEvent$5(Object obj) throws Exception {
        updateRedDot();
    }

    public void lambda$addEvent$6(Object obj) throws Exception {
        updateRedDot();
    }

    public static void lambda$addEvent$7(Object obj) throws Exception {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            RecentlyWalletController.setRecentlyWallet(selectedWallet);
        }
    }

    public void lambda$addEvent$8(Object obj) throws Exception {
        ((MainTabContract.View) this.mView).getTabViews().setTabSelected(2);
    }

    private void initViewListener() {
        ((MainTabContract.View) this.mView).getTabViews().setOnTabClickListener(new MainTabView.OnTabClickListener() {
            @Override
            public void onTabClick(int i) {
                if (i == 0) {
                    clickAssetsTab();
                    AnalyticsHelper.logEvent(AnalyticsHelper.HomePage.CLICK_HOME_PAGE_TAB_ASSET);
                    FirebaseAnalytics.getInstance(((MainTabContract.View) mView).getIContext()).setUserProperty(SignatureManager.Constants.channel, ChannelUtils.getGoogleAnalyticsChannel());
                } else if (i == 1) {
                    if (mSwapMarketFragment.isAdded()) {
                        mFragmentManager.beginTransaction().show(mSwapMarketFragment).hide(mAssetsFragment).hide(mDappFragment).hide(mMyFragment).commitAllowingStateLoss();
                    } else {
                        mFragmentManager.beginTransaction().remove(mSwapMarketFragment).commitAllowingStateLoss();
                        mFragmentManager.beginTransaction().add(R.id.main, mSwapMarketFragment, M.M_Finance).show(mSwapMarketFragment).hide(mAssetsFragment).hide(mDappFragment).hide(mMyFragment).commitAllowingStateLoss();
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.HomePage.CLICK_HOME_PAGE_TAB_MARKET);
                } else if (i != 2) {
                    if (i != 3) {
                        return;
                    }
                    clickMyTab();
                    AnalyticsHelper.logEvent(AnalyticsHelper.HomePage.CLICK_HOME_PAGE_TAB_MY);
                } else {
                    mRxManager.post(Event.DAPP, "111");
                    addOrShowDappFragment();
                    if (restartAc) {
                        mRxManager.post(RB.RB_AC_RESTART, "000");
                        restartAc = false;
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.HomePage.CLICK_HOME_PAGE_TAB_DAPP);
                }
            }
        });
    }

    private void onClickSwap() {
        this.isSwap = true;
        SwapMainFragment swapMainFragment = this.mSwapMarketFragment;
        if (swapMainFragment != null) {
            swapMainFragment.setDataType(2);
        }
        ((MainTabContract.View) this.mView).getTabViews().setTabSelected(1);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_SWAP);
    }

    private void addOrShowAssetsFragment() {
        if (this.mAssetsFragment.isAdded()) {
            this.mFragmentManager.beginTransaction().show(this.mAssetsFragment).hide(this.mDappFragment).hide(this.mMyFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
        } else {
            this.mFragmentManager.beginTransaction().add(R.id.main, this.mAssetsFragment, M.M_ASSETS).show(this.mAssetsFragment).hide(this.mDappFragment).hide(this.mMyFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
        }
    }

    public void checkLocalUpdateInfo() {
        String updateJson = SpAPI.THIS.getUpdateJson();
        if (!StringTronUtil.isEmpty(updateJson)) {
            try {
                UpdateOutput updateOutput = (UpdateOutput) new Gson().fromJson(updateJson,  UpdateOutput.class);
                if (updateOutput != null && updateOutput.data != null && BuildConfig.VERSION_NAME.equals(updateOutput.data.version)) {
                    updateOutput.data.upgrade = false;
                }
                TronConfig.updateOutput = updateOutput;
                parseUpdate(updateOutput);
                return;
            } catch (Exception e) {
                SentryUtil.captureException(e);
                this.mRxManager.post(Event.DD2, "");
                return;
            }
        }
        this.mRxManager.post(Event.DD2, "");
    }

    @Override
    public void update() {
        ((MainTabContract.Model) this.mModel).update().subscribe(new IObserver(new ICallback<UpdateOutput>() {
            @Override
            public void onResponse(String str, UpdateOutput updateOutput) {
                TronConfig.updateOutput = updateOutput;
                SpAPI.THIS.setUpdateJson(new Gson().toJson(updateOutput));
                parseUpdate(updateOutput);
            }

            @Override
            public void onFailure(String str, String str2) {
                checkLocalUpdateInfo();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "upgrade"));
    }

    public void getSystemConfig() {
        AppStatusUtils.appStatusRequest(this.mRxManager);
        ((MainTabContract.Model) this.mModel).getSystemConfig().subscribe(new IObserver(new ICallback<SystemConfigOutput>() {
            @Override
            public void onResponse(String str, SystemConfigOutput systemConfigOutput) {
                try {
                    SpAPI.THIS.setSystemTronscanUrl(systemConfigOutput.data.tronscanUrl);
                    SpAPI.THIS.setSystemTronDappUrl(systemConfigOutput.data.tronscanDappChain);
                    if (systemConfigOutput.data.usingCrtFile2020) {
                        SpAPI.THIS.setSYSTEM_USING_CRT_2020(1);
                    } else {
                        SpAPI.THIS.setSYSTEM_USING_CRT_2020(0);
                    }
                    IRequest.initBase(systemConfigOutput.data.tronscanUrl, systemConfigOutput.data.tronscanDappChain);
                } catch (Exception unused) {
                    onFailure("", "Error occurs fetching SystemConfigOutput");
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d("alex", "get system config fail " + str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "SystemConfig"));
    }

    public void parseUpdate(UpdateOutput updateOutput) {
        this.mRxManager.post(Event.DD2, updateOutput);
        updateRedDot();
    }

    public void updateRedDot() {
        ((MainTabContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$updateRedDot$9();
            }
        });
    }

    public void lambda$updateRedDot$9() {
        boolean z = this.mIsColdWallet;
        boolean receiveNoticeStatus = getReceiveNoticeStatus();
        boolean z2 = (TransactionMessageManager.getInstance().queryUnread() == 0 || IRequest.isNile() || IRequest.isShasta() || (!IRequest.isRelease() && !IRequest.isPrerelease()) || !SpAPI.THIS.getCurIsMainChain() || SpAPI.THIS.isCold()) ? false : true;
        if ((receiveNoticeStatus || z2) && !z) {
            showRedDot(true);
        } else {
            showRedDot(false);
        }
    }

    public void lambda$showRedDot$10(boolean z) {
        ((MainTabContract.View) this.mView).getTabViews().showRedDot(z);
    }

    public void showRedDot(final boolean z) {
        ((MainTabContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showRedDot$10(z);
            }
        });
    }

    public boolean getReceiveNoticeStatus() {
        List<String> noticeList = SpAPI.THIS.getNoticeList();
        return (noticeList.isEmpty() || noticeList.contains(TronConfig.receiveNoticeData)) ? false : true;
    }

    @Override
    public void addSome() {
        TronSetting.init();
        AssetsConfig.initConfig();
        AccountFeeManager.initAccountFee();
        ReminderManager.getInstance().showReminder(((MainTabContract.View) this.mView).getIContext());
        getNoticeRemind();
        getSystemConfig();
        getFee();
        sendDeviceInfo();
        addEvent();
        updateRedDot();
    }

    public void sendDeviceInfo() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet == null) {
            SentryUtil.captureException(new Exception("Failed to get select wallet !"));
            return;
        }
        ((MainTabContract.Model) this.mModel).sendDeviceInfo(selectedWallet.getAddress(), "android", SpAPI.THIS.getClientId(), "88572").subscribe(new IObserver(new ICallback<DeviceInfoBean>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, DeviceInfoBean deviceInfoBean) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "sendDeviceInfo"));
    }

    private void getNoticeRemind() {
        ((MainTabContract.Model) this.mModel).getNoticeRemind().subscribe(new IObserver(new ICallback<NoticeRemindBean>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, NoticeRemindBean noticeRemindBean) {
                if (noticeRemindBean == null || noticeRemindBean.code != 0 || StringTronUtil.isEmpty(noticeRemindBean.data)) {
                    return;
                }
                List<String> noticeList = SpAPI.THIS.getNoticeList();
                if (noticeList != null && noticeList.size() > 0) {
                    if (StringTronUtil.isEmpty(noticeRemindBean.data) || noticeList.contains(noticeRemindBean.data)) {
                        return;
                    }
                    TronConfig.receiveNoticeData = noticeRemindBean.data;
                    updateRedDot();
                    return;
                }
                updateRedDot();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getNoticeRemind"));
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        AssetsFragment assetsFragment = this.mAssetsFragment;
        if (assetsFragment != null) {
            this.mFragmentManager.putFragment(bundle, M.M_ASSETS, assetsFragment);
        }
        DappHomeFragment dappHomeFragment = this.mDappFragment;
        if (dappHomeFragment != null) {
            this.mFragmentManager.putFragment(bundle, M.M_APP, dappHomeFragment);
        }
        MyFragment myFragment = this.mMyFragment;
        if (myFragment != null) {
            this.mFragmentManager.putFragment(bundle, M.M_MY, myFragment);
        }
        SwapMainFragment swapMainFragment = this.mSwapMarketFragment;
        if (swapMainFragment != null) {
            this.mFragmentManager.putFragment(bundle, M.M_Finance, swapMainFragment);
        }
    }

    @Override
    public void onCreate2(Bundle bundle, FragmentManager fragmentManager) {
        getFragmentForFragmentManager(bundle, fragmentManager);
        if (this.mIsColdWallet) {
            initColdFragment(fragmentManager);
        } else {
            initAssetsFragment(fragmentManager);
            initDappFragment(fragmentManager);
            initMarketFragment(fragmentManager);
        }
        initMyFragment(fragmentManager);
        checkOutsideLinkFromIntent(((Activity) ((MainTabContract.View) this.mView).getIContext()).getIntent());
    }

    private void initMarketFragment(FragmentManager fragmentManager) {
        if (this.mSwapMarketFragment == null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(M.M_Finance);
            if (findFragmentByTag != null) {
                this.mSwapMarketFragment = (SwapMainFragment) findFragmentByTag;
            } else {
                this.mSwapMarketFragment = new SwapMainFragment();
            }
        }
    }

    private void initDappFragment(FragmentManager fragmentManager) {
        if (this.mDappFragment == null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(M.M_APP);
            if (findFragmentByTag != null) {
                this.mDappFragment = (DappHomeFragment) findFragmentByTag;
                this.restartAc = true;
            } else {
                this.mDappFragment = new DappHomeFragment();
            }
        }
        this.mDappFragment.addEventListener(new DappHomeFragment.EventListener() {
            @Override
            public final void expandTabView(boolean z) {
                lambda$initDappFragment$11(z);
            }
        });
    }

    public void lambda$initDappFragment$11(boolean z) {
        MainTabView tabViews = ((MainTabContract.View) this.mView).getTabViews();
        if (tabViews == null) {
            return;
        }
        tabViews.setExpanded(z);
    }

    private void initMyFragment(FragmentManager fragmentManager) {
        if (this.mMyFragment == null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(M.M_MY);
            if (findFragmentByTag != null) {
                this.mMyFragment = (MyFragment) findFragmentByTag;
            } else {
                this.mMyFragment = new MyFragment();
            }
        }
    }

    private void initAssetsFragment(FragmentManager fragmentManager) {
        if (this.mAssetsFragment == null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(M.M_ASSETS);
            if (findFragmentByTag != null) {
                this.mAssetsFragment = (AssetsFragment) findFragmentByTag;
            } else {
                this.mAssetsFragment = new AssetsFragment();
            }
        }
    }

    private void initColdFragment(FragmentManager fragmentManager) {
        if (this.mColdFragment == null) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(M.M_COLD);
            if (findFragmentByTag != null) {
                this.mColdFragment = (ColdFragment) findFragmentByTag;
            } else {
                this.mColdFragment = new ColdFragment();
            }
        }
    }

    private void getFragmentForFragmentManager(Bundle bundle, FragmentManager fragmentManager) {
        if (bundle != null) {
            if (this.mIsColdWallet) {
                this.mColdFragment = (ColdFragment) fragmentManager.getFragment(bundle, M.M_COLD);
            } else {
                this.mAssetsFragment = (AssetsFragment) fragmentManager.getFragment(bundle, M.M_ASSETS);
                this.mSwapMarketFragment = (SwapMainFragment) fragmentManager.getFragment(bundle, M.M_Finance);
                this.mDappFragment = (DappHomeFragment) fragmentManager.getFragment(bundle, M.M_APP);
            }
            this.mMyFragment = (MyFragment) fragmentManager.getFragment(bundle, M.M_MY);
        }
    }

    @Override
    public void getAssetsList() {
        ((MainTabContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getAssetsList$12();
            }
        });
    }

    public void lambda$getAssetsList$12() {
        try {
            GrpcAPI.AssetIssueList assetIssueList = TronAPI.getAssetIssueList();
            if (assetIssueList != null) {
                List<AssetIssueContractOuterClass.AssetIssueContract> assetIssueList2 = assetIssueList.getAssetIssueList();
                ArrayList arrayList = new ArrayList();
                for (AssetIssueContractOuterClass.AssetIssueContract assetIssueContract : assetIssueList2) {
                    AssetIssueContractDao assetIssueContractDao = new AssetIssueContractDao();
                    this.assetIssueContractDao = assetIssueContractDao;
                    assetIssueContractDao.id = Long.valueOf(Long.parseLong(assetIssueContract.getId()));
                    this.assetIssueContractDao.abbr = new String(assetIssueContract.getAbbr().toByteArray());
                    this.assetIssueContractDao.owner_address = StringTronUtil.encode58Check(assetIssueContract.getOwnerAddress().toByteArray());
                    this.assetIssueContractDao.name = new String(assetIssueContract.getName().toByteArray());
                    this.assetIssueContractDao.total_supply = String.valueOf(assetIssueContract.getTotalSupply());
                    this.assetIssueContractDao.trx_num = String.valueOf(assetIssueContract.getTrxNum());
                    this.assetIssueContractDao.start_time = assetIssueContract.getStartTime();
                    this.assetIssueContractDao.end_time = assetIssueContract.getEndTime();
                    this.assetIssueContractDao.precision = assetIssueContract.getPrecision();
                    this.assetIssueContractDao.url = new String(assetIssueContract.getUrl().toByteArray());
                    this.assetIssueContractDao.num = String.valueOf(assetIssueContract.getNum());
                    this.assetIssueContractDao.description = new String(assetIssueContract.getDescription().toByteArray());
                    arrayList.add(this.assetIssueContractDao);
                }
                DaoUtils.getDicInstance().deleteAll(AssetIssueContractDao.class);
                DaoUtils.getDicInstance().insertMultObject(arrayList);
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        UpdateHelper updateHelper2 = updateHelper;
        if (updateHelper2 != null) {
            updateHelper2.onPermissionResult(i, strArr, iArr);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        UpdateHelper updateHelper2 = updateHelper;
        if (updateHelper2 != null) {
            updateHelper2.onActivityResult(i, i2, intent);
        }
        DappHomeFragment dappHomeFragment = this.mDappFragment;
        if (dappHomeFragment != null) {
            dappHomeFragment.onActivityResult(i, i2, intent);
        }
        SwapMainFragment swapMainFragment = this.mSwapMarketFragment;
        if (swapMainFragment != null) {
            swapMainFragment.onActivityResult(i, i2, intent);
        }
    }

    @Override
    protected void resetNodeInfo() {
        SpAPI.THIS.setIsCustomFull(false);
        SpAPI.THIS.setIsDappCustomSol(false);
        SpAPI.THIS.setAllChainJson(null);
        SpAPI.THIS.setMainNodeList(null);
        SpAPI.THIS.setCurrentChainName("");
        SpAPI.THIS.setNotNileChainNoticeTimes(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ReminderManager.getInstance().destroy();
        ScanPopWindow scanPopWindow = this.scanPopWindow;
        if (scanPopWindow != null && scanPopWindow.isShowing()) {
            this.scanPopWindow.dismiss();
        }
        SelectedAssetsWindow selectedAssetsWindow = this.selectedAssetsWindow;
        if (selectedAssetsWindow != null && selectedAssetsWindow.isShowing()) {
            this.selectedAssetsWindow.dismiss();
        }
        this.selectedAssetsWindow = null;
    }

    public void addOrShowDappFragment() {
        if (this.isFirstClickDapp) {
            StatusBarUtils.setLightStatusBar((Activity) ((MainTabContract.View) this.mView).getIContext(), true);
            this.isFirstClickDapp = false;
        }
        if (this.mDappFragment.isAdded()) {
            this.mFragmentManager.beginTransaction().show(this.mDappFragment).hide(this.mAssetsFragment).hide(this.mMyFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
            return;
        }
        this.mFragmentManager.beginTransaction().remove(this.mDappFragment).commitAllowingStateLoss();
        this.mFragmentManager.beginTransaction().add(R.id.main, this.mDappFragment, M.M_APP).show(this.mDappFragment).hide(this.mAssetsFragment).hide(this.mMyFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
    }

    public void clickMyTab() {
        if (this.mIsColdWallet) {
            if (this.mMyFragment.isAdded()) {
                this.mFragmentManager.beginTransaction().show(this.mMyFragment).hide(this.mColdFragment).commitAllowingStateLoss();
                return;
            }
            this.mFragmentManager.beginTransaction().remove(this.mMyFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.main, this.mMyFragment, M.M_MY).show(this.mMyFragment).hide(this.mColdFragment).commitAllowingStateLoss();
        } else if (this.mMyFragment.isAdded()) {
            this.mFragmentManager.beginTransaction().show(this.mMyFragment).hide(this.mAssetsFragment).hide(this.mDappFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
        } else {
            this.mFragmentManager.beginTransaction().remove(this.mMyFragment).commitAllowingStateLoss();
            this.mMyFragment = new MyFragment();
            this.mFragmentManager.beginTransaction().add(R.id.main, this.mMyFragment, M.M_MY).show(this.mMyFragment).hide(this.mAssetsFragment).hide(this.mDappFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
        }
    }

    public void clickAssetsTab() {
        if (this.mIsColdWallet) {
            if (this.mColdFragment.isAdded()) {
                this.mFragmentManager.beginTransaction().show(this.mColdFragment).hide(this.mMyFragment).commitAllowingStateLoss();
                return;
            }
            this.mFragmentManager.beginTransaction().remove(this.mColdFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.main, this.mColdFragment, M.M_COLD).show(this.mColdFragment).hide(this.mMyFragment).commitAllowingStateLoss();
        } else if (this.mAssetsFragment.isAdded()) {
            this.mFragmentManager.beginTransaction().show(this.mAssetsFragment).hide(this.mDappFragment).hide(this.mMyFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
        } else {
            this.mFragmentManager.beginTransaction().remove(this.mAssetsFragment).commitAllowingStateLoss();
            this.mFragmentManager.beginTransaction().add(R.id.main, this.mAssetsFragment, M.M_ASSETS).show(this.mAssetsFragment).hide(this.mDappFragment).hide(this.mMyFragment).hide(this.mSwapMarketFragment).commitAllowingStateLoss();
        }
    }

    public void getFee() {
        ((MainTabContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                MainTabPresenter.lambda$getFee$13();
            }
        });
    }

    public static void lambda$getFee$13() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.maintab.MainTabPresenter.lambda$getFee$13():void");
    }

    @Override
    public void getDappJs() {
        LogUtils.d(TAG, "getDappJs");
        ((MainTabContract.Model) this.mModel).getDappJs().subscribe(new IObserver(new ICallback<DappJsOutput>() {
            @Override
            public void onResponse(String str, DappJsOutput dappJsOutput) {
                String str2;
                DappJs.THIS.updateDappJs(new Gson().toJson(dappJsOutput));
                if (dappJsOutput == null || dappJsOutput.getData() == null || dappJsOutput.getData().getTronWebParams() == null) {
                    DappJs.THIS.initLocal();
                    DappJs.THIS.initConsoleLocal();
                    return;
                }
                try {
                    str2 = Md5Util.getAssetsTronWebMD5();
                    LogUtils.d("Assets js Md5：" + str2);
                } catch (Exception e) {
                    LogUtils.e(e);
                    str2 = "";
                }
                AssetsConfig.initConfig(dappJsOutput);
                AccountFeeManager.initAccountFee(dappJsOutput);
                String dappJsString = SpAPI.THIS.getDappJsString();
                if (!StringTronUtil.isEmpty(dappJsString)) {
                    str2 = Md5Util.md5(dappJsString);
                }
                LogUtils.d(MainTabPresenter.TAG, "local js Md5:  " + str2);
                if (DappJs.THIS.doNotNeedUpgrade(dappJsOutput.getData().getTronWebParams().getTronWebVersion())) {
                    SpAPI.THIS.setDappjsString("");
                    DappJs.THIS.getTronWebByLocal();
                } else if (dappJsOutput.getData().getTronWebParams().getTronWebHash().equals(str2)) {
                    LogUtils.d(MainTabPresenter.TAG, "onLineMd5  " + dappJsOutput.getData().getTronWebParams().getTronWebHash());
                    DappJs.THIS.getTronWebByLocal();
                } else {
                    DappJs.THIS.getTronWebByUrl(dappJsOutput.getData().getTronWebParams().getTronWebUrl(), dappJsOutput.getData().getTronWebParams().getTronWebHash());
                }
                if (dappJsOutput != null && dappJsOutput.getData().getTransferCost() != null) {
                    int energyCost = dappJsOutput.getData().getTransferCost().getEnergyCost();
                    if (energyCost != SpAPI.THIS.getEnergyPerTranscation()) {
                        SpAPI.THIS.setEnergyPerTranscation(energyCost);
                    }
                    int netCost = dappJsOutput.getData().getTransferCost().getNetCost();
                    if (netCost != SpAPI.THIS.getBandwidthPerTranscation()) {
                        SpAPI.THIS.setBandwidthPerTranscation(netCost);
                    }
                }
                if (dappJsOutput != null && dappJsOutput.getData() != null && dappJsOutput.getData().getFinancial() != null) {
                    String financialUrl = dappJsOutput.getData().getFinancial().getFinancialUrl();
                    SpAPI.THIS.setFinanceShow(dappJsOutput.getData().getFinancial().getFinancialShow());
                    if (!StringTronUtil.isEmpty(financialUrl)) {
                        SpAPI.THIS.setFinanceUSUrl(financialUrl);
                    }
                    String financialSingaporeUrl = dappJsOutput.getData().getFinancial().getFinancialSingaporeUrl();
                    if (!StringTronUtil.isEmpty(financialSingaporeUrl)) {
                        SpAPI.THIS.setFinanceSingaporeUrl(financialSingaporeUrl);
                    }
                }
                if (dappJsOutput != null && dappJsOutput.getData() != null && dappJsOutput.getData().getStop() != null && IRequest.currentNetCanUpdateLanguage()) {
                    List<String> currency = dappJsOutput.getData().getStop().getCurrency();
                    List<String> lang = dappJsOutput.getData().getStop().getLang();
                    AppLanguageOutput appLanguageOutput = new AppLanguageOutput();
                    appLanguageOutput.setCurrency(currency);
                    appLanguageOutput.setLanguage(lang);
                    SpAPI.THIS.setOnLineAppLanguage(appLanguageOutput);
                }
                if (dappJsOutput != null && dappJsOutput.getData() != null && dappJsOutput.getData().getVersion() != null) {
                    String filterSmall = dappJsOutput.getData().getVersion().getFilterSmall();
                    if (!TextUtils.isEmpty(filterSmall)) {
                        TronConfig.filterSmallValue = filterSmall;
                        SpAPI.THIS.setFilterSmallValueThreshold(filterSmall);
                    }
                    if (dappJsOutput.getData().getVersion().getStakeEnable() == 1) {
                        TronSetting.stakeV2 = true;
                        SpAPI.THIS.setStakeVersion(2);
                    } else {
                        TronSetting.stakeV2 = false;
                        SpAPI.THIS.setStakeVersion(1);
                    }
                    int stakeExpireDay = dappJsOutput.getData().getVersion().getStakeExpireDay();
                    if (stakeExpireDay >= 0) {
                        TronSetting.stakeExpireDay = stakeExpireDay;
                        SpAPI.THIS.setStakeExpireDay(stakeExpireDay);
                    }
                }
                DappJs.THIS.initConsoleLocal();
            }

            @Override
            public void onFailure(String str, String str2) {
                DappJs.THIS.initConsoleLocal();
                DappJs.THIS.initLocal();
                TronConfig.filterSmallValue = SpAPI.THIS.getFilterSmallValueThreshold();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "getDappJs"));
    }

    public class fun7 implements ICallback<Result<List<DappAuthorizedProjectBean>>> {
        fun7() {
        }

        @Override
        public void onResponse(String str, Result<List<DappAuthorizedProjectBean>> result) {
            if (result == null || !"0".equals(result.code) || result.data == null || result.data.isEmpty()) {
                return;
            }
            final List<DappAuthorizedProjectBean> list = result.data;
            Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    MainTabPresenter.7.lambda$onResponse$0((DappAuthorizedProjectBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            LogUtils.i("getDappAuthorizedProject", "" + list.size());
            ((MainTabContract.View) mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    DappAuthorizedController.insertMultiOfficialAuthorizedProject(list);
                }
            });
        }

        public static void lambda$onResponse$0(DappAuthorizedProjectBean dappAuthorizedProjectBean) {
            if (dappAuthorizedProjectBean.getUrl() != null) {
                dappAuthorizedProjectBean.setUrl(StringTronUtil.getHost(dappAuthorizedProjectBean.getUrl()));
            }
            dappAuthorizedProjectBean.setType(1);
        }

        @Override
        public void onFailure(String str, String str2) {
            LogUtils.e(str2);
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            mRxManager.add(disposable);
        }
    }

    @Override
    public void getDappAuthorizedProject() {
        ((MainTabContract.Model) this.mModel).getDappAuthorizedProject().subscribe(new IObserver(new fun7(), "getDAppAuthorizedProject"));
    }

    public class fun8 implements ICallback<BlackResultListBean> {
        @Override
        public void onFailure(String str, String str2) {
        }

        @Override
        public void onSubscribe(Disposable disposable) {
        }

        fun8() {
        }

        @Override
        public void onResponse(String str, BlackResultListBean blackResultListBean) {
            if (blackResultListBean == null || blackResultListBean.getCode() != 0 || blackResultListBean.getData() == null || blackResultListBean.getData().isEmpty()) {
                return;
            }
            final List<String> data = blackResultListBean.getData();
            ((MainTabContract.View) mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    DappBlackListController.getInstance().insertBlackList(data);
                }
            });
        }
    }

    @Override
    public void getBlackList() {
        ((MainTabContract.Model) this.mModel).getBlackList().compose(RxSchedulers.io_main()).subscribe(new IObserver(new fun8(), "getBlackList"));
    }

    @Override
    public void onResume() {
        updateRedDot();
        openOutsideLink();
    }

    private void openOutsideLink() {
        if (TextUtils.isEmpty(this.pendingOutsideLink)) {
            return;
        }
        if (((MainTabContract.View) this.mView).getTabViews().getActiveTab() != 2) {
            ((MainTabContract.View) this.mView).getTabViews().setTabSelected(2);
        }
        final String host = StringTronUtil.getHost(this.pendingOutsideLink);
        ((MainTabContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$openOutsideLink$15(host);
            }
        });
    }

    public void lambda$openOutsideLink$15(String str) {
        final boolean isBlack = DappBlackListController.getInstance().isBlack(str);
        ((MainTabContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$openOutsideLink$14(isBlack);
            }
        });
    }

    public void lambda$openOutsideLink$14(boolean z) {
        if (z) {
            ((MainTabContract.View) this.mView).ToastError();
        } else {
            DappHomeFragment dappHomeFragment = this.mDappFragment;
            if (dappHomeFragment != null) {
                dappHomeFragment.pullFromOutside(this.pendingOutsideLink);
            }
        }
        this.pendingOutsideLink = null;
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (intent != null) {
            if (intent.getIntExtra(AssetsConfig.ACTION, 0) == 1 && this.mAssetsFragment != null) {
                int intExtra = intent.getIntExtra(AssetsConfig.TOKEN_TYPE, -1);
                String stringExtra = intent.getStringExtra(AssetsConfig.TOKEN_SYMBOL);
                this.mAssetsFragment.switchFragment(intExtra == 5 ? 1 : 0);
                AssetsFragment assetsFragment = this.mAssetsFragment;
                assetsFragment.ToastSuc(String.format(assetsFragment.getResources().getString(R.string.token_add_success), stringExtra));
            } else if (intent.getBooleanExtra(MigrateConfig.CALLED_FROM_MIGRATE, false)) {
                ((MainTabContract.View) this.mView).getTabViews().setTabSelected(0);
            } else {
                checkOutsideLinkFromIntent(intent);
            }
        }
    }

    private void checkOutsideLinkFromIntent(Intent intent) {
        if (intent == null || !PullActivity.ACTION.equals(intent.getAction())) {
            return;
        }
        this.pendingOutsideLink = PullActivity.handlePullActivityNewIntent((Activity) ((MainTabContract.View) this.mView).getIContext(), intent);
    }
}
