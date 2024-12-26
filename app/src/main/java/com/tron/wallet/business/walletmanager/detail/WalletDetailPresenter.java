package com.tron.wallet.business.walletmanager.detail;

import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.detail.WalletDetailContract;
import com.tron.wallet.business.walletmanager.detail.WalletDetailPresenter;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.SocketManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Response;
import okhttp3.WebSocket;
import org.tron.walletserver.Wallet;
public class WalletDetailPresenter extends WalletDetailContract.Presenter {
    private Gson gson;
    private boolean hasSocketConnect;
    private WalletSocketListener socketListener;

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        ((WalletDetailContract.View) this.mView).setHasSwitchWallet(true);
        ((WalletDetailContract.View) this.mView).updateCardBg();
    }

    public void setDealSignCount(String str) {
        final int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                DealSignOutput.DataBeanX dataBeanX = (DealSignOutput.DataBeanX) this.gson.fromJson(str,  DealSignOutput.DataBeanX.class);
                if (dataBeanX != null && dataBeanX.data != null) {
                    if (dataBeanX.data.size() > 0) {
                        int i2 = 0;
                        while (i < dataBeanX.data.size()) {
                            try {
                                if (dataBeanX.data.get(i).isSign == 0) {
                                    i2++;
                                }
                                i++;
                            } catch (JsonSyntaxException e) {
                                e = e;
                                i = i2;
                                SentryUtil.captureException(e);
                                LogUtils.e(e);
                                ((WalletDetailContract.View) this.mView).runOnUIThread(new OnMainThread() {
                                    @Override
                                    public final void doInUIThread() {
                                        lambda$setDealSignCount$1(i);
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
        ((WalletDetailContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$setDealSignCount$1(i);
            }
        });
    }

    public void lambda$setDealSignCount$1(int i) {
        ((WalletDetailContract.View) this.mView).updateWaitSignCount(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (this.socketListener != null) {
                SocketManager.getInstance().removeListener(this.socketListener);
                this.socketListener = null;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void addSome() {
        this.socketListener = new WalletSocketListener();
        this.gson = new Gson();
        if (((WalletDetailContract.View) this.mView).getCurrentWallet() != null) {
            refreshNotNetSign(((WalletDetailContract.View) this.mView).getCurrentWallet().getAddress());
        }
        getHdWalletRelationShip();
    }

    private void getHdWalletRelationShip() {
        Wallet currentWallet = ((WalletDetailContract.View) this.mView).getCurrentWallet();
        if (currentWallet != null) {
            Observable.just(currentWallet).flatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource just;
                    just = Observable.just(HDTronWalletController.getHdWalletRelationShip(((Wallet) obj).getWalletName()));
                    return just;
                }
            }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<HdTronRelationshipBean>() {
                @Override
                public void onFailure(String str, String str2) {
                }

                @Override
                public void onResponse(String str, HdTronRelationshipBean hdTronRelationshipBean) {
                    if (mView != 0) {
                        ((WalletDetailContract.View) mView).setHdWalletRelationShip(hdTronRelationshipBean);
                    }
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "getHdWalletRelationShip"));
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra(TronConfig.WALLET_DATA);
            if (i == 2021) {
                return;
            }
            if (i == 2030) {
                ((WalletDetailContract.View) this.mView).setCurrentWallet(WalletUtils.getWallet(stringExtra));
                ((WalletDetailContract.View) this.mView).updateWalletName(stringExtra);
                ((WalletDetailContract.View) this.mView).updateWalletPath();
                WalletUtils.setSelectedWallet(stringExtra);
                SocketManager.getInstance().removeListener(this.socketListener);
                refreshNotNetSign(WalletUtils.getWallet(stringExtra).getAddress());
                getHdWalletRelationShip();
            } else if (i == 2031) {
                if (intent.getBooleanExtra(WalletDetailActivity.WATCH_UPDATE_COLD_RESULT_SUCCESS, false)) {
                    ((WalletDetailContract.View) this.mView).updateWalletToColdWatch();
                }
            } else {
                HDTronWalletController.updateHdTronRelationshipBeanWalletName(((WalletDetailContract.View) this.mView).getCurrentWallet().getWalletName(), stringExtra);
                ((WalletDetailContract.View) this.mView).updateWalletName(stringExtra);
                ((WalletDetailContract.View) this.mView).setCurrentWallet(WalletUtils.getWallet(stringExtra));
                getHdWalletRelationShip();
            }
        }
    }

    public void refreshNotNetSign(String str) {
        if (SpAPI.THIS.isCold()) {
            return;
        }
        if (this.socketListener != null) {
            SocketManager.getInstance().removeListener(this.socketListener);
        }
        SocketManager.getInstance().start(str, 0, true).addListenter(this.socketListener);
    }

    public class WalletSocketListener implements SocketManager.SocketListener {
        @Override
        public void onClosed(WebSocket webSocket, int i, String str) {
        }

        @Override
        public void onClosing(WebSocket webSocket, int i, String str) {
        }

        public WalletSocketListener() {
        }

        @Override
        public void onMessage(WebSocket webSocket, String str) {
            if (!TextUtils.isEmpty(str.toString())) {
                setDealSignCount(str);
            } else {
                ((WalletDetailContract.View) mView).updateWaitSignCount(0);
            }
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            hasSocketConnect = false;
            ((WalletDetailContract.View) mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    WalletDetailPresenter.WalletSocketListener.this.lambda$onFailure$0();
                }
            });
        }

        public void lambda$onFailure$0() {
            ((WalletDetailContract.View) mView).updateWaitSignCount(0);
        }

        @Override
        public void open(WebSocket webSocket, Response response) {
            hasSocketConnect = true;
        }
    }
}
