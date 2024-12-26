package com.tron.wallet.business.assetshome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.arasthel.asyncjob.AsyncJob;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.addassets2.AssetsConfig;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.EditAssetsOrderActivity;
import com.tron.wallet.business.addassets2.WatchWalletVerifier;
import com.tron.wallet.business.addassets2.bean.TokenSortBean;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeOutput;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomePriceBean;
import com.tron.wallet.business.addassets2.bean.nft.NftAssetOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.FollowAssetsSortListController;
import com.tron.wallet.business.addassets2.repository.NewAssetsController;
import com.tron.wallet.business.assetshome.AssetsContract;
import com.tron.wallet.business.assetshome.AssetsPresenter;
import com.tron.wallet.business.assetshome.tipview.MultiPermissionTipView;
import com.tron.wallet.business.nft.dao.NftTokenBeanController;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tabdapp.DappUtils;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.controller.DAppVisitHistoryController;
import com.tron.wallet.business.tabdapp.dappsearch.DappSearchPresenter;
import com.tron.wallet.business.tabmy.myhome.settings.NodeSpeedTestActivity;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.bean.Price;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.dialog.ShowContentCopyDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.interfaces.OnConfirmListener;
import com.tron.wallet.common.interfaces.OnDismissListener;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.task.TimeUpdate;
import com.tron.wallet.common.utils.AccountPermissionUtils;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.AppUtils;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.SocketManager;
import com.tron.wallet.net.rpc.IGrpcClient;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import j$.util.Collection;
import j$.util.Objects;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.Response;
import okhttp3.WebSocket;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
import org.tron.walletserver.Wallet;
public class AssetsPresenter extends AssetsContract.Presenter {
    public static final String KEY_ASSET_STATUS = "key_asset_status";
    public static final String KEY_SP = "asset_status";
    private static final String TAG = "AssetsPresenter";
    private static final int UPDATEASSETS = 1;
    private Gson gson;
    private boolean hasSocketConnect;
    private volatile boolean hideLittleAssets;
    private volatile boolean hideScamToken;
    private Disposable lastDisposable;
    private LinearLayoutManager linearLayoutManager;
    private Protocol.Account mAccount;
    private GrpcAPI.AccountResourceMessage mAccountNetMessage;
    private AssetsUpdaterRunnable mAssetsUpdaterRunnable;
    private AssetsHomeData mData;
    private Handler mHandler;
    private Wallet mSelectedWallet;
    private WalletSocketListener socketListener;
    private ArrayList<TokenBean> tokenBeanArrayList;
    private TokenBean trxBean;
    private List<TokenBean> collectionData = new ArrayList();
    private HashMap<String, Integer> hash = new HashMap<>();
    private String oldShowHash = "";
    private String newShowHash = "";
    private boolean hasDealAction = false;
    private volatile boolean isLoading = false;
    private volatile boolean isFirstLoad = true;
    private int autoAssetsRefreshIndex = -1;
    private boolean mIsHidden = false;

    public static void lambda$onActivityResult$31() {
    }

    @Override
    public boolean getHideLittleAssetsFlag() {
        return this.hideLittleAssets;
    }

    @Override
    public boolean getSocketState() {
        return this.hasSocketConnect;
    }

    @Override
    public void recordDealAct() {
        this.oldShowHash = this.newShowHash;
        this.hasDealAction = true;
    }

    @Override
    protected void onStart() {
        AssetsManager.getInstance();
        WatchWalletVerifier.getInstance();
        PriceUpdater.start();
        AssetsAnimatorHelper.init();
        TronConfig.balance10_TRX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        TronConfig.balance20 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        initRcFrame();
        this.mAssetsUpdaterRunnable = new AssetsUpdaterRunnable();
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                mHandler.removeCallbacks(mAssetsUpdaterRunnable);
                mHandler.postDelayed(mAssetsUpdaterRunnable, TronConfig.ACCOUNT_UPDATE_FOREGROUND_INTERVAL);
                getReleaseData(false);
                getCollectionData();
            }
        };
        this.mHandler = handler;
        handler.postDelayed(this.mAssetsUpdaterRunnable, TronConfig.ACCOUNT_UPDATE_FOREGROUND_INTERVAL);
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mSelectedWallet = selectedWallet;
        if (selectedWallet != null && selectedWallet.isShieldedWallet()) {
            Iterator<String> it = WalletUtils.getWalletNames().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Wallet wallet = WalletUtils.getWallet(it.next());
                if (!wallet.isShieldedWallet()) {
                    WalletUtils.setSelectedWallet(wallet.getWalletName());
                    this.mSelectedWallet = wallet;
                    break;
                }
            }
        }
        if (this.mSelectedWallet != null) {
            updateAccount();
            this.mIsHidden = getAssetStatus();
            refreshSafeTipView();
            this.gson = new Gson();
            getCollectionData();
            return;
        }
        ((AssetsContract.View) this.mView).exit();
    }

    @Override
    public void switchAssetStatus(boolean z) {
        ((AssetsContract.View) this.mView).getIContext().getSharedPreferences(KEY_SP, 0).edit().putBoolean(KEY_ASSET_STATUS, z).apply();
        this.mIsHidden = z;
        this.mRxManager.post(Event.ASSET_STATUS_HIDDEN_SHOW, Boolean.valueOf(z));
        ((AssetsContract.View) this.mView).updateHidePrivacyState(this.mIsHidden);
    }

    @Override
    public boolean getAssetStatus() {
        return ((AssetsContract.View) this.mView).getIContext().getSharedPreferences(KEY_SP, 0).getBoolean(KEY_ASSET_STATUS, false);
    }

    public void onRefresh() {
        try {
            if (!IGrpcClient.THIS.getGrpcState()) {
                ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
                    @Override
                    public final void run() {
                        AssetsPresenter.lambda$onRefresh$0();
                    }
                });
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
        getReleaseData(true);
        AccountUpdater.singleShot(0L);
        AssetsManager.getInstance().refreshNewAssets();
        getCollectionData();
    }

    public static void lambda$onRefresh$0() {
        try {
            IGrpcClient.THIS.initGRpc();
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    protected void initRcFrame() {
        this.mRxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$1(obj);
            }
        });
        this.mRxManager.on(Event.BACKUP, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$2(obj);
            }
        });
        this.mRxManager.on(Event.NODE_CONNECTED_FAILED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$3(obj);
            }
        });
        this.mRxManager.on(Event.NODE_CONNECTED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$4(obj);
            }
        });
        this.mRxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$5(obj);
            }
        });
        this.mRxManager.on(Event.NODE_CONNECT, new Consumer() {
            @Override
            public final void accept(Object obj) {
                AssetsPresenter.lambda$initRcFrame$6(obj);
            }
        });
        this.mRxManager.on(RB.RB_SYNC_BLOCK, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$7(obj);
            }
        });
        LogUtils.d(TAG, "on(RB.RB_ACCOUNTS)");
        this.mRxManager.on(RB.RB_ACCOUNTS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$8(obj);
            }
        });
        this.mRxManager.on(RB.RB_PRICE_SWITCH, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$9(obj);
            }
        });
        this.mRxManager.on(RB.RB_UPDATE_SHIELD_ACCOUNTS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                AssetsPresenter.lambda$initRcFrame$10(obj);
            }
        });
        this.mRxManager.on(RB.RB_SHIELD_ACCOUNTS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$11(obj);
            }
        });
        this.mRxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$12(obj);
            }
        });
        this.mRxManager.on(RB.RB_HOMEASSET_DB, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$13(obj);
            }
        });
        this.mRxManager.on("CONNECT", new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$14(obj);
            }
        });
        this.mRxManager.on(RB.RB_TIME, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$15(obj);
            }
        });
        this.mRxManager.on(Event.ASSETS_NEW, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$16(obj);
            }
        });
        this.mRxManager.on(Event.ASSETS_HIDDEN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$17(obj);
            }
        });
        this.mRxManager.on(Event.ASSETS_SORTED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$18(obj);
            }
        });
        this.mRxManager.on(Event.UPDATESUCCESS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$19(obj);
            }
        });
        this.mRxManager.on(Event.DELETE_WALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$20(obj);
            }
        });
        this.mRxManager.on(Event.SWITCH_SERVER, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$21(obj);
            }
        });
        this.mRxManager.on(Event.FINANCE_STATUS_HIDDEN_SHOW, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$22(obj);
            }
        });
        this.mRxManager.on(Event.ASSETS_RELOAD, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRcFrame$23(obj);
            }
        });
    }

    public void lambda$initRcFrame$1(Object obj) throws Exception {
        this.hasDealAction = false;
        if (this.mSelectedWallet.getWalletName().equals((String) obj)) {
            return;
        }
        ((AssetsContract.View) this.mView).showLoading(((AssetsContract.View) this.mView).getIContext().getResources().getString(R.string.loading2));
        this.autoAssetsRefreshIndex = -1;
        showAssetsLoading(true);
        this.mSelectedWallet = WalletUtils.getSelectedWallet();
        ((AssetsContract.View) this.mView).updateWalletName(this.mSelectedWallet);
        if (this.mSelectedWallet != null) {
            ((AssetsContract.View) this.mView).onWalletChanged(this.mSelectedWallet);
            ((AssetsContract.View) this.mView).updateStart(0);
            ((AssetsContract.View) this.mView).updateStart(1);
            TronConfig.balance10_TRX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            TronConfig.balance20 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            updateAccount();
            refreshSafeTipView();
            checkAndShowMultiSignView();
            Disposable disposable = this.lastDisposable;
            if (disposable != null && !disposable.isDisposed()) {
                this.lastDisposable.dispose();
                this.mRxManager.remove(this.lastDisposable);
                this.lastDisposable = null;
            }
            ((AssetsContract.Model) this.mModel).getLocalData(this.mSelectedWallet, this.mAccount).subscribe(new IObserver(new ICallback<AssetsHomeData>() {
                @Override
                public void onFailure(String str, String str2) {
                }

                @Override
                public void onResponse(String str, AssetsHomeData assetsHomeData) {
                    mData = assetsHomeData;
                    if (mView != 0) {
                        readHideLittleAssetsFlag();
                        ((AssetsContract.View) mView).showBlockSync(mSelectedWallet);
                        ((AssetsContract.View) mView).switchFragment(0);
                        ((AssetsContract.View) mView).updateHeaderData(mSelectedWallet, mData, mAccount, mAccountNetMessage, mIsHidden);
                        AssetsPresenter assetsPresenter = AssetsPresenter.this;
                        assetsPresenter.updateAssetsListData(0, assetsPresenter.mData.getToken(), mIsHidden);
                    }
                    ((AssetsContract.View) mView).showLoading(((AssetsContract.View) mView).getIContext().getString(R.string.loading2));
                    getReleaseData(true);
                    getCollectionData();
                }

                @Override
                public void onSubscribe(Disposable disposable2) {
                    lastDisposable = disposable2;
                    mRxManager.add(disposable2);
                }
            }, "SELECTEDWALLET"));
        }
    }

    public void lambda$initRcFrame$2(Object obj) throws Exception {
        this.mSelectedWallet = WalletUtils.getSelectedWallet();
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).updateWalletName(this.mSelectedWallet);
            ((AssetsContract.View) this.mView).updateHeaderData(this.mSelectedWallet, this.mData, this.mAccount, this.mAccountNetMessage, this.mIsHidden);
        }
        refreshSafeTipView();
    }

    public void lambda$initRcFrame$3(Object obj) throws Exception {
        LogUtils.d("ShieldedAPIAssetsPresenter", "NODE_CONNECTED_FAILED  ");
        try {
            ((AssetsContract.View) this.mView).setShowSwitchNode(true);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$initRcFrame$4(Object obj) throws Exception {
        LogUtils.d("ShieldedAPIAssetsPresenter", "NODE_CONNECTED  ");
        try {
            ((AssetsContract.View) this.mView).setShowSwitchNode(false);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$initRcFrame$5(Object obj) throws Exception {
        AccountUpdater.singleShot(0L);
        netWorkChange();
    }

    public static void lambda$initRcFrame$6(Object obj) throws Exception {
        LogUtils.d(TAG, "NODE_CONNECT");
        AccountUpdater.singleShot(0L);
    }

    public void lambda$initRcFrame$7(Object obj) throws Exception {
        try {
            LogUtils.d(TAG, RB.RB_SYNC_BLOCK);
            String[] split = ((String) obj).split(":");
            String str = split[0];
            Wallet selectedWallet = WalletUtils.getSelectedWallet();
            if (selectedWallet != null && selectedWallet.getAddress().equals(str)) {
                long longValue = Long.valueOf(split[1]).longValue();
                long longValue2 = Long.valueOf(split[2]).longValue();
                if (this.mView != 0) {
                    ((AssetsContract.View) this.mView).updateSyncBlock(longValue, longValue2);
                    return;
                }
                return;
            }
            LogUtils.d(TAG, "RB_SYNC_BLOCK not current SelectedWallet");
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$initRcFrame$8(Object obj) throws Exception {
        LogUtils.d(TAG, "alex RECEIVE RB_ACCOUNTS");
        refreshData();
        showAssetsLoading(false);
    }

    public void lambda$initRcFrame$9(Object obj) throws Exception {
        LogUtils.d(TAG, "alex RECEIVE RB_PRICE_SWITCH");
        getData(false);
        showAssetsLoading(false);
    }

    public static void lambda$initRcFrame$10(Object obj) throws Exception {
        LogUtils.d("ShieldedAPIScanAssetsPresenter", "alex RECEIVE RB_SHIELD_ACCOUNTS  RB_UPDATE_SHIELD_ACCOUNTS ");
        AccountUpdater.singleShot(0L);
    }

    public void lambda$initRcFrame$11(Object obj) throws Exception {
        LogUtils.d("ShieldedAPIScanAssetsPresenter", "alex RECEIVE RB_SHIELD_ACCOUNTS");
        refreshData();
    }

    public void lambda$initRcFrame$12(Object obj) throws Exception {
        LogUtils.d(TAG, "alex  RECEIVE SWITCH_CHAIN");
        this.autoAssetsRefreshIndex = -1;
        this.hasDealAction = false;
        cleanDataWhenSwitchChain();
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).onChainChanged();
        }
        refreshData();
    }

    public void lambda$initRcFrame$13(Object obj) throws Exception {
        LogUtils.d(TAG, "alex RB_HOMEASSET_DB");
        if (this.mSelectedWallet != null) {
            if (obj != null && (obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                getDBData();
                getCollectionDbData();
            } else if (TronConfig.hasNet) {
                getReleaseData(false);
            } else {
                getDBData();
            }
        }
    }

    public void lambda$initRcFrame$14(Object obj) throws Exception {
        LogUtils.d(TAG, "alex connect");
        this.hasDealAction = false;
        if (this.mSelectedWallet != null) {
            refreshData();
            startSocket(true);
        }
    }

    public void lambda$initRcFrame$15(Object obj) throws Exception {
        LogUtils.d(TAG, "alex RB_time");
        toUpdate();
    }

    public void lambda$initRcFrame$16(Object obj) throws Exception {
        int newAssetsCount = NewAssetsController.getInstance().getNewAssetsCount(this.mSelectedWallet.getAddress());
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).showBlockSync(this.mSelectedWallet);
            ((AssetsContract.View) this.mView).updateNewAssetCount(newAssetsCount);
        }
    }

    public void lambda$initRcFrame$17(Object obj) throws Exception {
        this.mIsHidden = getAssetStatus();
        ((AssetsContract.View) this.mView).updateHidePrivacyState(this.mIsHidden);
    }

    public void lambda$initRcFrame$18(Object obj) throws Exception {
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                getDBData();
            } else {
                getCollectionDbData();
            }
        }
    }

    public void lambda$initRcFrame$19(Object obj) throws Exception {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (pair.second instanceof String) {
                reloadPermissionOnEvent((String) pair.second);
            }
        }
    }

    public void lambda$initRcFrame$20(Object obj) throws Exception {
        if (obj instanceof String) {
            reloadPermissionOnEvent((String) obj);
        }
    }

    public void lambda$initRcFrame$21(Object obj) throws Exception {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if (intValue != 1) {
                if (intValue == 2) {
                    if (this.mView == 0 || SpAPI.THIS.isServerAuto() || !TronConfig.hasNet) {
                        return;
                    }
                    ((AssetsContract.View) this.mView).showSwitchServerNotice(true);
                    return;
                } else if (intValue != 3 && intValue != 4) {
                    return;
                }
            }
            if (this.mView != 0) {
                ((AssetsContract.View) this.mView).showSwitchServerNotice(false);
            }
        }
    }

    public void lambda$initRcFrame$22(Object obj) throws Exception {
        if (obj instanceof Boolean) {
            switchAssetStatus(!((Boolean) obj).booleanValue());
        }
    }

    public void lambda$initRcFrame$23(Object obj) throws Exception {
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            onRefresh();
        }
    }

    private void reSortData(TokenSortType tokenSortType, final int i, List<TokenBean> list) {
        Observable<List<TokenBean>> sortByUser;
        if (tokenSortType == TokenSortType.SORT_BY_NAME) {
            SortHelper sortHelper = SortHelper.get();
            if (list == null) {
                list = ((AssetsContract.View) this.mView).getData(i);
            }
            sortByUser = sortHelper.sortByName(list);
        } else if (tokenSortType == TokenSortType.SORT_BY_VALUE) {
            SortHelper sortHelper2 = SortHelper.get();
            if (list == null) {
                list = ((AssetsContract.View) this.mView).getData(i);
            }
            sortByUser = sortHelper2.sortByValue(list);
        } else {
            sortByUser = tokenSortType == TokenSortType.SORT_BY_USER ? SortHelper.get().sortByUser(i) : null;
        }
        if (sortByUser != null) {
            sortByUser.subscribe(new IObserver(new ICallback<List<TokenBean>>() {
                @Override
                public void onFailure(String str, String str2) {
                }

                @Override
                public void onResponse(String str, List<TokenBean> list2) {
                    AssetsPresenter assetsPresenter = AssetsPresenter.this;
                    assetsPresenter.updateAssetsListData(i == 0 ? 0 : 1, list2, assetsPresenter.mIsHidden);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add("reSortData", disposable);
                }
            }, "sortData"));
        }
    }

    public void onSortChanged(TokenSortType tokenSortType, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("Sort_type", tokenSortType.getValue());
        FirebaseAnalytics.getInstance(((AssetsContract.View) this.mView).getIContext()).logEvent("Click_add_token_home_sort", bundle);
        if (tokenSortType == TokenSortType.SORT_BY_USER_MANUAL) {
            EditAssetsOrderActivity.startActivity(((AssetsContract.View) this.mView).getIContext(), i, TokenSortType.SORT_BY_USER.getValue());
            return;
        }
        reSortData(tokenSortType, 0, null);
        if (AssetsConfig.canDisplayCollections()) {
            reSortData(tokenSortType, 1, null);
        }
    }

    private void cleanDataWhenSwitchChain() {
        if (this.mSelectedWallet != null) {
            Observable.create(new ObservableOnSubscribe<AssetsHomeData>() {
                @Override
                public void subscribe(ObservableEmitter<AssetsHomeData> observableEmitter) throws Exception {
                    mData = AssetsManager.getInstance().getSortedFollowAssets();
                    if (mData == null) {
                        mData = new AssetsHomeData();
                        mData.token = new CopyOnWriteArrayList<>();
                    }
                    observableEmitter.onNext(mData);
                    observableEmitter.onComplete();
                }
            }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$cleanDataWhenSwitchChain$24((AssetsHomeData) obj);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    AssetsPresenter.lambda$cleanDataWhenSwitchChain$25((Throwable) obj);
                }
            });
        }
    }

    public void lambda$cleanDataWhenSwitchChain$24(AssetsHomeData assetsHomeData) throws Exception {
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).showBlockSync(this.mSelectedWallet);
            ((AssetsContract.View) this.mView).updateHeaderData(this.mSelectedWallet, this.mData, this.mAccount, this.mAccountNetMessage, this.mIsHidden);
            updateAssetsListData(0, this.mData.getToken(), this.mIsHidden);
        }
    }

    public static void lambda$cleanDataWhenSwitchChain$25(Throwable th) throws Exception {
        LogUtils.e(th);
        SentryUtil.captureException(th);
    }

    public void showAssetsLoading(boolean z) {
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).updateHeaderLoading(z);
        }
    }

    @Override
    public void refreshData() {
        if (this.mSelectedWallet != null) {
            updateAccount();
            int i = this.autoAssetsRefreshIndex + 1;
            this.autoAssetsRefreshIndex = i;
            if (i % 6 == 0) {
                getData(false);
            }
        }
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).dismissLoading();
        }
    }

    private void toUpdate() {
        int i;
        if (this.mData != null) {
            for (int i2 = 0; i2 < this.mData.getToken().size(); i2++) {
                if (this.mData.getToken().get(i2).type == 3 && (i = this.mData.getToken().get(i2).time) > 0) {
                    int i3 = i - 1;
                    this.mData.getToken().get(i2).setTime(i3);
                    this.hash.put(this.mData.getToken().get(i2).getName(), Integer.valueOf(i3));
                }
            }
            if (this.mView != 0) {
                ((AssetsContract.View) this.mView).updateHeaderData(this.mSelectedWallet, this.mData, this.mAccount, this.mAccountNetMessage, this.mIsHidden);
                updateAssetsListData(0, this.mData.token, this.mIsHidden);
            }
        }
    }

    public void getData(boolean z) {
        getReleaseData(z);
    }

    private void getShastaData() {
        ((AssetsContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getShastaData$28();
            }
        });
    }

    public void lambda$getShastaData$28() {
        try {
            Protocol.Account queryAccount = TronAPI.queryAccount(StringTronUtil.decode58Check(this.mSelectedWallet.getAddress()), false);
            this.mAccount = queryAccount;
            if (queryAccount == null) {
                return;
            }
            long stakedSun = ResourcesBean.buildStakedTrx(new ResourcesBean(), this.mAccount).getStakedSun();
            AssetsHomeData assetsHomeData = new AssetsHomeData();
            this.mData = assetsHomeData;
            assetsHomeData.setTotalTRX((this.mAccount.getBalance() + stakedSun) / 1000000.0d);
            Price tRX_price = PriceUpdater.getTRX_price();
            new BigDecimal(Double.toString(tRX_price.getPrice()));
            AssetsHomePriceBean assetsHomePriceBean = new AssetsHomePriceBean();
            if (SpAPI.THIS.isUsdPrice()) {
                assetsHomePriceBean.priceUSD = tRX_price.getPrice();
            } else {
                assetsHomePriceBean.priceCny = tRX_price.getPrice();
            }
            this.mData.setPrice(assetsHomePriceBean);
            CopyOnWriteArrayList<TokenBean> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            TokenBean tokenBean = new TokenBean();
            tokenBean.type = 0;
            tokenBean.logoUrl = "";
            tokenBean.totalBalance = (this.mAccount.getBalance() + stakedSun) / 1000000.0d;
            tokenBean.balance = (this.mAccount.getBalance() + stakedSun) / 1000000.0d;
            tokenBean.balanceStr = String.valueOf(this.mAccount.getBalance() / 1000000.0d);
            copyOnWriteArrayList.add(tokenBean);
            this.mData.token = copyOnWriteArrayList;
            ((AssetsContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getShastaData$26();
                }
            });
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
            ((AssetsContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getShastaData$27();
                }
            });
        }
    }

    public void lambda$getShastaData$26() {
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).showBlockSync(this.mSelectedWallet);
            ((AssetsContract.View) this.mView).updateHeaderData(this.mSelectedWallet, this.mData, this.mAccount, this.mAccountNetMessage, this.mIsHidden);
            updateAssetsListData(0, this.mData.token, this.mIsHidden);
        }
    }

    public void lambda$getShastaData$27() {
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).showBlockSync(this.mSelectedWallet);
            ((AssetsContract.View) this.mView).updateFail(0);
        }
    }

    public void updateAccount() {
        if (this.mSelectedWallet.isShieldedWallet()) {
            this.mAccount = WalletUtils.getAccount(((AssetsContract.View) this.mView).getIContext(), this.mSelectedWallet.getWalletName());
            return;
        }
        this.mAccount = WalletUtils.getAccount(((AssetsContract.View) this.mView).getIContext(), this.mSelectedWallet.getWalletName());
        this.mAccountNetMessage = WalletUtils.getAccountRes(((AssetsContract.View) this.mView).getIContext(), this.mSelectedWallet.getWalletName());
    }

    @Override
    public void updateSelectedWallet() {
        if (this.mSelectedWallet != null) {
            Wallet selectedWallet = WalletUtils.getSelectedWallet();
            if (!this.mSelectedWallet.getWalletName().equals(selectedWallet.getWalletName())) {
                this.mSelectedWallet = selectedWallet;
                TronConfig.balance10_TRX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                TronConfig.balance20 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                if (this.mSelectedWallet != null) {
                    updateAccount();
                }
                if (this.mView != 0) {
                    ((AssetsContract.View) this.mView).showBlockSync(this.mSelectedWallet);
                    ((AssetsContract.View) this.mView).updateHeaderData(this.mSelectedWallet, this.mData, this.mAccount, this.mAccountNetMessage, this.mIsHidden);
                }
            } else {
                this.mSelectedWallet = selectedWallet;
                ((AssetsContract.View) this.mView).updateHeaderData(this.mSelectedWallet, this.mData, this.mAccount, this.mAccountNetMessage, this.mIsHidden);
            }
        }
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).updateWalletName(this.mSelectedWallet);
        }
    }

    @Override
    void getReleaseData(final boolean z) {
        if (this.mSelectedWallet.isShieldedWallet()) {
            return;
        }
        if (!TronConfig.hasNet) {
            getDBData();
        }
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        Wallet wallet = this.mSelectedWallet;
        if (wallet != null && !TextUtils.isEmpty(wallet.getAddress())) {
            try {
                StringTronUtil.decode58Check(this.mSelectedWallet.getAddress());
            } catch (Exception e) {
                AppUtils.randomReportSentry(e);
                return;
            }
        }
        ((AssetsContract.Model) this.mModel).getAssetsHomeData(this.mSelectedWallet.getAddress()).observeOn(Schedulers.io()).filter(new Predicate<AssetsHomeOutput>() {
            @Override
            public boolean test(AssetsHomeOutput assetsHomeOutput) {
                if (assetsHomeOutput.code == 0 && assetsHomeOutput.data != null && assetsHomeOutput.data.token != null && assetsHomeOutput.data.token.size() > 0) {
                    if (mSelectedWallet != null) {
                        assetsHomeOutput.data.address = mSelectedWallet.getAddress();
                    }
                    tokenBeanArrayList = new ArrayList();
                    try {
                        LogUtils.d("AccountBalance", "insertObject:  ");
                        TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).insertObject(mSelectedWallet.getAddress(), assetsHomeOutput.data.totalTRX, assetsHomeOutput.data.token.get(0).getTrxCount());
                    } catch (Exception e2) {
                        LogUtils.e(e2);
                    }
                    for (int i = 0; i < assetsHomeOutput.data.getToken().size(); i++) {
                        TokenBean tokenBean = assetsHomeOutput.data.getToken().get(i);
                        if (tokenBean.type == 3) {
                            if (!((AssetsContract.View) mView).getStart()) {
                                tokenBean.setTime(((Integer) hash.get(tokenBean.getName())).intValue());
                            } else {
                                hash.put(assetsHomeOutput.data.getToken().get(i).name, Integer.valueOf(assetsHomeOutput.data.getToken().get(i).time));
                            }
                            tokenBeanArrayList.add(tokenBean);
                        }
                    }
                    if (((AssetsContract.View) mView).getStart() && hash.size() > 0 && tokenBeanArrayList.size() > 0) {
                        TimeUpdate.start();
                        ((AssetsContract.View) mView).setStart(false);
                    }
                    for (int i2 = 0; i2 < assetsHomeOutput.data.token.size(); i2++) {
                        assetsHomeOutput.data.token.get(i2).address = mSelectedWallet.getAddress();
                    }
                    List<TokenSortBean> tokenSortList = FollowAssetsSortListController.getInstance().getTokenSortList(mSelectedWallet.getAddress());
                    if ((tokenSortList == null || tokenSortList.size() <= 1) && assetsHomeOutput.data.token.size() > 1) {
                        FollowAssetsSortListController.getInstance().setTokenSortListByTokenBeanList(mSelectedWallet.getAddress(), assetsHomeOutput.data.token, 0);
                    }
                    AssetsHomeDataDaoManager.clearAndAdd(((AssetsContract.View) mView).getIContext(), assetsHomeOutput.data, mSelectedWallet.getAddress());
                }
                return true;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new IObserver(new ICallback<AssetsHomeOutput>() {
            @Override
            public void onResponse(String str, AssetsHomeOutput assetsHomeOutput) {
                isLoading = false;
                if (isFirstLoad) {
                    isFirstLoad = false;
                }
                if (assetsHomeOutput.code == 0 && assetsHomeOutput.data != null && assetsHomeOutput.data.token.size() > 0) {
                    getDBData();
                }
                ((AssetsContract.View) mView).dismissLoading();
                ((AssetsContract.View) mView).pullToRefreshComplete();
            }

            @Override
            public void onFailure(String str, String str2) {
                isLoading = false;
                ((AssetsContract.View) mView).dismissLoading();
                if (mView != 0) {
                    ((AssetsContract.View) mView).showBlockSync(mSelectedWallet);
                }
                if (isFirstLoad) {
                    isFirstLoad = false;
                    if (TronConfig.hasNet) {
                        IToast.getImageIToast().show(((AssetsContract.View) mView).getIContext().getString(R.string.time_out), R.mipmap.toast_icon_negative);
                    }
                } else if (z) {
                    IToast.getImageIToast().show(((AssetsContract.View) mView).getIContext().getString(R.string.time_out), R.mipmap.toast_icon_negative);
                }
                ((AssetsContract.View) mView).updateFail(0);
                ((AssetsContract.View) mView).pullToRefreshComplete();
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add("getAssetsHomeData", disposable);
            }
        }, "getReleaseData"));
    }

    @Override
    void getDBData() {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public void run() {
                synchronized (AssetsPresenter.this) {
                    if (mSelectedWallet != null) {
                        mData = AssetsManager.getInstance().getSortedFollowAssets();
                    }
                    trxBean = new TokenBean();
                    trxBean.name = "TRX";
                    if (mData == null) {
                        mData = new AssetsHomeData();
                        mData.token = new CopyOnWriteArrayList<>();
                    }
                    AssetsHomePriceBean assetsHomePriceBean = new AssetsHomePriceBean();
                    Price tRX_price = PriceUpdater.getTRX_price();
                    if (SpAPI.THIS.isUsdPrice()) {
                        assetsHomePriceBean.priceUSD = tRX_price.getPrice();
                    } else {
                        assetsHomePriceBean.priceCny = tRX_price.getPrice();
                    }
                    if (mData != null) {
                        mData.setPrice(assetsHomePriceBean);
                    }
                    if (mData != null && mData.token != null && tokenBeanArrayList != null) {
                        mData.token.addAll(0, tokenBeanArrayList);
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (mData == null || mData.token == null || mView == 0) {
                                return;
                            }
                            ((AssetsContract.View) mView).showBlockSync(mSelectedWallet);
                            ((AssetsContract.View) mView).updateHeaderData(mSelectedWallet, mData, mAccount, mAccountNetMessage, mIsHidden);
                            updateAssetsListData(0, mData.token, mIsHidden);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void backup() {
        WalletDetailActivity.startActivity(((AssetsContract.View) this.mView).getIContext(), this.mSelectedWallet.getWalletName(), false);
    }

    private void refreshSafeTipView() {
        if (!BackupReminder.isWalletBackup(this.mSelectedWallet)) {
            ((AssetsContract.View) this.mView).showOrHidenSafeTipView(true);
        } else {
            ((AssetsContract.View) this.mView).showOrHidenSafeTipView(false);
        }
    }

    @Override
    public void startSocket(boolean z) {
        ((AssetsContract.View) this.mView).updateDealSignView(0);
        Wallet wallet = this.mSelectedWallet;
        if (wallet != null && !wallet.isShieldedWallet()) {
            this.socketListener = new WalletSocketListener();
            SocketManager.getInstance().start(this.mSelectedWallet.getAddress(), 0, z).addListenter(this.socketListener);
            return;
        }
        ((AssetsContract.View) this.mView).updateDealSignView(0);
    }

    @Override
    public void stopSocket() {
        SocketManager.getInstance().disconnect();
    }

    @Override
    public void removeListener() {
        if (this.socketListener != null) {
            SocketManager.getInstance().removeListener(this.socketListener);
        }
    }

    @Override
    void netWorkChange() {
        updateNetStatus();
        if (TronConfig.hasNet) {
            startSocket(true);
        }
    }

    private void updateNetStatus() {
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).setNetNotice(TronConfig.hasNet);
        }
    }

    @Override
    public void initData() {
        readHideLittleAssetsFlag();
        getDBData();
        getCollectionDbData();
    }

    @Override
    public void onDestroy() {
        Handler handler;
        super.onDestroy();
        AssetsUpdaterRunnable assetsUpdaterRunnable = this.mAssetsUpdaterRunnable;
        if (assetsUpdaterRunnable == null || (handler = this.mHandler) == null) {
            return;
        }
        handler.removeCallbacks(assetsUpdaterRunnable);
        this.mAssetsUpdaterRunnable = null;
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ScanIntentResult parseActivityResult = ScanIntentResult.parseActivityResult(i2, intent);
        if (parseActivityResult == null || parseActivityResult.getContents() == null) {
            return;
        }
        final String trim = intent.getStringExtra("SCAN_RESULT").trim();
        if (trim != null && StringTronUtil.isAddressValid(trim)) {
            Wallet wallet = this.mSelectedWallet;
            if (wallet != null && wallet.isWatchNotPaired()) {
                PairColdWalletDialog.showUp(((AssetsContract.View) this.mView).getIContext(), null);
            } else if (!TronConfig.hasNet) {
                IToast.getImageIToast().show(((AssetsContract.View) this.mView).getIContext().getString(R.string.time_out), R.mipmap.toast_error);
            } else {
                final SpannableString spannableString = new SpannableString(trim);
                final TokenBean tokenBean = new TokenBean();
                tokenBean.setType(0);
                tokenBean.setName("TRX");
                tokenBean.setShortName("TRX");
                AssetsHomeData assetsHomeData = this.mData;
                if (assetsHomeData != null && assetsHomeData.getToken().size() > 0) {
                    tokenBean = this.mData.getToken().get(0);
                }
                int i3 = tokenBean.type;
                AccountUtils.getInstance().checkAccountIsActivatedFromLocal(((AssetsContract.View) this.mView).getIContext(), new CheckAccountActivatedCallback() {
                    @Override
                    public final void isActivated() {
                        lambda$onActivityResult$32(spannableString, tokenBean, trim);
                    }
                }, null);
            }
        } else if (StringTronUtil.isEmpty(trim)) {
        } else {
            SpannableString spannableString2 = new SpannableString(trim);
            StyleSpan styleSpan = new StyleSpan(1);
            spannableString2.setSpan(new ForegroundColorSpan(((AssetsContract.View) this.mView).getIContext().getResources().getColor(R.color.black_23)), 0, spannableString2.length(), 33);
            spannableString2.setSpan(styleSpan, 0, spannableString2.length(), 17);
            ShowContentCopyDialog.showUp(((AssetsContract.View) this.mView).getIContext(), "", spannableString2, ((AssetsContract.View) this.mView).getIContext().getString(R.string.cancel), ((AssetsContract.View) this.mView).getIContext().getString(R.string.copy), new OnConfirmListener() {
                @Override
                public final void onClick() {
                    lambda$onActivityResult$33(trim);
                }
            }, null);
        }
    }

    public void lambda$onActivityResult$32(SpannableString spannableString, final TokenBean tokenBean, final String str) {
        if (OwnerPermissionCheckUtils.checkHasOwnerPermission(this.mAccount)) {
            ShowContentCopyDialog.showUp(((AssetsContract.View) this.mView).getIContext(), ((AssetsContract.View) this.mView).getIContext().getString(R.string.asset_scan_transfer_to), spannableString, ((AssetsContract.View) this.mView).getIContext().getString(R.string.cancel), ((AssetsContract.View) this.mView).getIContext().getString(R.string.asset_scan_transfer_immediately), new OnConfirmListener() {
                @Override
                public final void onClick() {
                    lambda$onActivityResult$29(tokenBean, str);
                }
            }, null);
        } else {
            ShowContentCopyDialog.showUp(((AssetsContract.View) this.mView).getIContext(), "", spannableString, ((AssetsContract.View) this.mView).getIContext().getString(R.string.cancel), ((AssetsContract.View) this.mView).getIContext().getString(R.string.copy), new OnConfirmListener() {
                @Override
                public final void onClick() {
                    lambda$onActivityResult$30(str);
                }
            }, new OnDismissListener() {
                @Override
                public final void onClick() {
                    AssetsPresenter.lambda$onActivityResult$31();
                }
            });
        }
    }

    public void lambda$onActivityResult$29(TokenBean tokenBean, String str) {
        SelectSendAddressActivity.startCommon(((AssetsContract.View) this.mView).getIContext(), tokenBean, this.mAccount, str);
    }

    public void lambda$onActivityResult$30(String str) {
        UIUtils.copy(str);
        ((AssetsContract.View) this.mView).toast(((AssetsContract.View) this.mView).getIContext().getString(R.string.already_copy), R.mipmap.toast_icon_positive);
    }

    public void lambda$onActivityResult$33(String str) {
        UIUtils.copy(str);
        ((AssetsContract.View) this.mView).toast(((AssetsContract.View) this.mView).getIContext().getString(R.string.already_copy), R.mipmap.toast_icon_positive);
    }

    public void setDealSignCount(String str) {
        final int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                DealSignOutput.DataBeanX dataBeanX = (DealSignOutput.DataBeanX) this.gson.fromJson(str,  DealSignOutput.DataBeanX.class);
                if (dataBeanX != null && dataBeanX.data != null) {
                    if (dataBeanX.data.size() > 0) {
                        int i2 = 0;
                        boolean z = false;
                        while (i < dataBeanX.data.size()) {
                            try {
                                if (dataBeanX.data.get(i).isSign == 0) {
                                    i2++;
                                    if (!z) {
                                        this.newShowHash = dataBeanX.data.get(i).hash;
                                        z = true;
                                    }
                                }
                                i++;
                            } catch (JsonSyntaxException e) {
                                e = e;
                                i = i2;
                                LogUtils.e(e);
                                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                                    @Override
                                    public final void doInUIThread() {
                                        lambda$setDealSignCount$34(i);
                                    }
                                });
                            }
                        }
                        i = i2;
                    }
                }
            } catch (JsonSyntaxException e2) {
                e = e2;
            }
        }
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                lambda$setDealSignCount$34(i);
            }
        });
    }

    public void lambda$setDealSignCount$34(int i) {
        if (i <= 0) {
            ((AssetsContract.View) this.mView).updateDealSignView(i);
        } else if (this.hasDealAction) {
            if (this.newShowHash.equals(this.oldShowHash)) {
                return;
            }
            ((AssetsContract.View) this.mView).updateDealSignView(i);
            this.hasDealAction = false;
        } else {
            ((AssetsContract.View) this.mView).updateDealSignView(i);
        }
    }

    public void getCollectionDbData() {
        ((AssetsContract.Model) this.mModel).getNftTokens(((AssetsContract.View) this.mView).getIContext(), this.mSelectedWallet.getAddress()).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return AssetsPresenter.lambda$getCollectionDbData$35((NftAssetOutput) obj);
            }
        }).subscribe(new IObserver(new ICallback<List<TokenBean>>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, List<TokenBean> list) {
                collectionData.clear();
                collectionData.addAll(list);
                AssetsPresenter assetsPresenter = AssetsPresenter.this;
                assetsPresenter.updateAssetsListData(1, list, assetsPresenter.mIsHidden);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add("getNftTokens", disposable);
            }
        }, ""));
    }

    public static Observable lambda$getCollectionDbData$35(NftAssetOutput nftAssetOutput) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (nftAssetOutput != null && nftAssetOutput.getData() != null) {
            for (NftTokenBean nftTokenBean : nftAssetOutput.getData()) {
                arrayList.add(nftTokenBean.convertToTokenBean());
            }
        }
        return Observable.just(arrayList);
    }

    @Override
    void getCollectionData() {
        final String address = this.mSelectedWallet.getAddress();
        ((AssetsContract.Model) this.mModel).requestNftTokens(address).observeOn(Schedulers.io()).filter(new Predicate() {
            @Override
            public final boolean test(Object obj) {
                boolean lambda$getCollectionData$36;
                lambda$getCollectionData$36 = lambda$getCollectionData$36(address, (NftAssetOutput) obj);
                return lambda$getCollectionData$36;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new IObserver(new ICallback<NftAssetOutput>() {
            @Override
            public void onResponse(String str, NftAssetOutput nftAssetOutput) {
                if (nftAssetOutput != null) {
                    getCollectionDbData();
                } else {
                    onFailure(str, "Failed to request nft tokens.");
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                if (((Fragment) mView).isResumed() && ((AssetsContract.View) mView).getCurrentType() == 1) {
                    ((AssetsContract.View) mView).toast(((AssetsContract.View) mView).getIContext().getString(R.string.time_out), R.mipmap.toast_icon_negative);
                }
                ((AssetsContract.View) mView).updateFail(1);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add("requestNftTokens", disposable);
            }
        }, ""));
    }

    public boolean lambda$getCollectionData$36(String str, NftAssetOutput nftAssetOutput) throws Exception {
        if (nftAssetOutput == null || nftAssetOutput.getCode() != 0) {
            return false;
        }
        List<TokenSortBean> tokenSortList = FollowAssetsSortListController.getInstance().getTokenSortList(this.mSelectedWallet.getAddress(), 5);
        if (tokenSortList == null || tokenSortList.size() <= 1) {
            FollowAssetsSortListController.getInstance().setTokenSortListByTokenBeanList(this.mSelectedWallet.getAddress(), nftAssetOutput.getData(), 5);
        }
        NftTokenBeanController.getInstance().clearAndAdd(nftAssetOutput.getData(), str);
        return true;
    }

    public void updateAssetsListData(int i, List<TokenBean> list, boolean z) {
        Wallet wallet = this.mSelectedWallet;
        if (wallet != null && wallet.isShieldedWallet()) {
            ((AssetsContract.View) this.mView).updateNewData(i, list, z);
            return;
        }
        if (this.hideLittleAssets && list != null) {
            final ArrayList arrayList = new ArrayList();
            Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    AssetsPresenter.lambda$updateAssetsListData$37(arrayList, (TokenBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            list = arrayList;
        }
        ArrayList arrayList2 = list;
        arrayList2 = list;
        if (this.hideScamToken && list != null) {
            final ArrayList arrayList3 = new ArrayList();
            Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    AssetsPresenter.lambda$updateAssetsListData$38(arrayList3, (TokenBean) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            arrayList2 = arrayList3;
        }
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).updateNewData(i, arrayList2, z);
        }
    }

    public static void lambda$updateAssetsListData$37(List list, TokenBean tokenBean) {
        if ((tokenBean.getType() == 0 || tokenBean.getTop() == 2 || !AssetsConfig.isSmallAsset(tokenBean)) && !OfficialType.isScamOrUnSafeToken(tokenBean)) {
            list.add(tokenBean);
        }
    }

    public static void lambda$updateAssetsListData$38(List list, TokenBean tokenBean) {
        if (tokenBean.getType() == 0 || tokenBean.getTop() == 2 || !OfficialType.isScamOrUnSafeToken(tokenBean)) {
            list.add(tokenBean);
        }
    }

    public void readHideLittleAssetsFlag() {
        if (this.mSelectedWallet != null) {
            this.hideLittleAssets = ((AssetsContract.Model) this.mModel).getHideLittleAssets(this.mSelectedWallet.getAddress());
            this.hideScamToken = ((AssetsContract.Model) this.mModel).getHideScamTokenAssets(this.mSelectedWallet.getAddress());
        }
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).updateHideFiltersFlag(this.hideLittleAssets, this.hideScamToken);
        }
    }

    @Override
    public void setSortFiltersFlag(final boolean z, final boolean z2) {
        this.hideLittleAssets = z;
        this.hideScamToken = z2;
        if (this.mSelectedWallet != null) {
            TokenSortType currentType = SortHelper.get().getCurrentType();
            AssetsHomeData assetsHomeData = this.mData;
            reSortData(currentType, 0, assetsHomeData != null ? assetsHomeData.token : new ArrayList<>());
            if (AssetsConfig.canDisplayCollections()) {
                reSortData(currentType, 1, this.collectionData);
            }
            ((AssetsContract.Model) this.mModel).setHideLittleAssets(this.mSelectedWallet.getAddress(), z).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    Boolean bool = (Boolean) obj;
                    LogUtils.d(AssetsPresenter.TAG, "setHideLittleAssets:" + z);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SentryUtil.captureException((Throwable) obj);
                }
            });
            ((AssetsContract.Model) this.mModel).setHideScamTokenAssets(this.mSelectedWallet.getAddress(), z2).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    Boolean bool = (Boolean) obj;
                    LogUtils.d(AssetsPresenter.TAG, "setHideScamTokenAssets:" + z2);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SentryUtil.captureException((Throwable) obj);
                }
            });
        }
        if (this.mView != 0) {
            ((AssetsContract.View) this.mView).updateHideFiltersFlag(this.hideLittleAssets, z2);
        }
    }

    public void enterWebBrowser(String str) {
        DappUtils dappUtils = new DappUtils((Activity) ((AssetsContract.View) this.mView).getIContext());
        dappUtils.setSearchUrl(true);
        dappUtils.init(str, DappSearchPresenter.getFixedUrl(str), "", 0, 0, false, "");
        dappUtils.assetsCheckAuthorized(str, new fun10(str));
    }

    class fun10 implements OnConfirmListener {
        final String val$url;

        public static void lambda$onClick$0(Boolean bool) throws Exception {
        }

        fun10(String str) {
            this.val$url = str;
        }

        @Override
        public void onClick() {
            mRxManager.post(Event.SWITCH_DAPP_PAGE, this.val$url);
            DAppVisitHistoryController.getInstance().insertVisitHistory(this.val$url, "", "").subscribeOn(Schedulers.io()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    AssetsPresenter.10.lambda$onClick$0((Boolean) obj);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    LogUtils.e(((Throwable) obj).toString());
                }
            });
            if (BrowserTabManager.getInstance().getBrowserContent() != null) {
                BrowserTabManager.getInstance().startURL(DappSearchPresenter.getFixedUrl(this.val$url), this.val$url, false, true);
                return;
            }
            Handler handler = mHandler;
            final String str = this.val$url;
            handler.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    BrowserTabManager.getInstance().startURL(DappSearchPresenter.getFixedUrl(r0), str, false, true);
                }
            }, 300L);
        }
    }

    public void onClickSwitchNode() {
        ChainBean currentChain = SpAPI.THIS.getCurrentChain();
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            currentChain.isMainChain = true;
            currentChain.chainId = "Shasta";
            currentChain.isSelect = true;
            currentChain.chainName = "MainChain";
            currentChain.eventServer = "";
            currentChain.fullNode = "grpc.shasta.trongrid.io:50051";
            currentChain.mainChainContract = "";
        }
        NodeSpeedTestActivity.start(((AssetsContract.View) this.mView).getIContext(), currentChain);
    }

    @Override
    public void checkAndShowMultiSignView() {
        Wallet wallet;
        if (this.mView == 0 || (wallet = this.mSelectedWallet) == null || wallet.isSamsungWallet() || this.mSelectedWallet.isShieldedWallet()) {
            return;
        }
        Protocol.Account account = this.mAccount;
        if (account == null || account.toString().length() == 0) {
            Observable.just(this.mSelectedWallet.getAddress()).flatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource just;
                    just = Observable.just(new Pair(r1, TronAPI.queryAccount(StringTronUtil.decodeFromBase58Check((String) obj), false)));
                    return just;
                }
            }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new fun11(), "getAccount"));
            return;
        }
        try {
            AccountPermissionUtils.isAccountPermissionMultiSign(((AssetsContract.View) this.mView).getIContext(), this.mAccount, new BiConsumer() {
                @Override
                public final void accept(Object obj, Object obj2) {
                    lambda$checkAndShowMultiSignView$44((Boolean) obj, (String) obj2);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public class fun11 implements ICallback<Pair<String, Protocol.Account>> {
        fun11() {
        }

        @Override
        public void onResponse(String str, Pair<String, Protocol.Account> pair) {
            if (mView == 0 || pair == null || mSelectedWallet == null || !((String) pair.first).equals(mSelectedWallet.getAddress())) {
                return;
            }
            try {
                AccountPermissionUtils.isAccountPermissionMultiSign(((AssetsContract.View) mView).getIContext(), (Protocol.Account) pair.second, new BiConsumer() {
                    @Override
                    public final void accept(Object obj, Object obj2) {
                        AssetsPresenter.11.this.lambda$onResponse$0((Boolean) obj, (String) obj2);
                    }
                });
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }

        public void lambda$onResponse$0(Boolean bool, String str) throws Exception {
            ((AssetsContract.View) mView).showMultiPermissionTipView(bool.booleanValue(), mSelectedWallet.getWalletName(), str);
        }

        @Override
        public void onFailure(String str, String str2) {
            if (mView != 0) {
                ((AssetsContract.View) mView).showMultiPermissionTipView(false, null, null);
            }
        }

        @Override
        public void onSubscribe(Disposable disposable) {
            mRxManager.add(disposable);
        }
    }

    public void lambda$checkAndShowMultiSignView$44(Boolean bool, String str) throws Exception {
        ((AssetsContract.View) this.mView).showMultiPermissionTipView(bool.booleanValue(), this.mSelectedWallet.getWalletName(), str);
    }

    private void reloadPermissionOnEvent(String str) {
        try {
            MultiPermissionTipView.setCloseTimestamp(((AssetsContract.View) this.mView).getIContext(), str, 0L);
            ((AssetsContract.View) this.mView).hideMultiPermissionTipView();
            this.mHandler.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    checkAndShowMultiSignView();
                }
            }, 1000L);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public class WalletSocketListener implements SocketManager.SocketListener {
        public WalletSocketListener() {
        }

        @Override
        public void onMessage(WebSocket webSocket, String str) {
            if (!TextUtils.isEmpty(str.toString())) {
                setDealSignCount(str);
            } else {
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        AssetsPresenter.WalletSocketListener.this.lambda$onMessage$0();
                    }
                });
            }
        }

        public void lambda$onMessage$0() {
            ((AssetsContract.View) mView).updateDealSignView(0);
        }

        @Override
        public void onClosed(WebSocket webSocket, int i, String str) {
            AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                @Override
                public final void doInUIThread() {
                    AssetsPresenter.WalletSocketListener.this.lambda$onClosed$1();
                }
            });
        }

        public void lambda$onClosed$1() {
            ((AssetsContract.View) mView).updateDealSignView(0);
        }

        @Override
        public void onClosing(WebSocket webSocket, int i, String str) {
            AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                @Override
                public final void doInUIThread() {
                    AssetsPresenter.WalletSocketListener.this.lambda$onClosing$2();
                }
            });
        }

        public void lambda$onClosing$2() {
            ((AssetsContract.View) mView).updateDealSignView(0);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            hasSocketConnect = false;
            AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                @Override
                public final void doInUIThread() {
                    AssetsPresenter.WalletSocketListener.this.lambda$onFailure$3();
                }
            });
        }

        public void lambda$onFailure$3() {
            ((AssetsContract.View) mView).updateDealSignView(0);
        }

        @Override
        public void open(WebSocket webSocket, Response response) {
            hasSocketConnect = true;
        }
    }

    class AssetsUpdaterRunnable implements Runnable {
        AssetsUpdaterRunnable() {
        }

        @Override
        public void run() {
            if (mHandler != null) {
                mHandler.sendEmptyMessage(1);
            }
        }
    }
}
