package com.tron.wallet.business.tabswap.mvp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.BaseConfirmTransParamBuilder;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.tabdapp.bean.DappConfirmInput;
import com.tron.wallet.business.tabswap.SwapConfirmMockActivity;
import com.tron.wallet.business.tabswap.UpdateSwapTransactionStatus;
import com.tron.wallet.business.tabswap.bean.SwapConfirmBean;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapSelectBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.business.tabswap.mvp.Contract;
import com.tron.wallet.business.tabswap.mvp.SwapPresenter;
import com.tron.wallet.business.tabswap.select.SelectTokenActivity;
import com.tron.wallet.business.tabswap.utils.SwapCacheUtils;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import com.tron.wallet.common.components.SwapRouterDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.db.Controller.AssetsHomeDataDaoManager;
import com.tron.wallet.db.Controller.JustSwapTransactionController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SwapPresenter extends Contract.Presenter {
    private static final long DEFAULT_LOOP_DURATION = 10000;
    private static final int MSG_BALANCE_VALUE = 1;
    private static final int MSG_QUERY_VALUE = 0;
    private static final String TAG = "SwapPresenter";
    private static int swapCounter;
    private PublishSubject<String> amountPublishSubject;
    private volatile boolean backFromSelectPage;
    private SwapInfoOutput currentSwapOutput;
    private SwapTokenInfoBean currentSwapToken;
    private Protocol.Transaction currentTransaction;
    private Handler handler;
    private RxManager rxManager;
    private boolean swapButtonEnabled;
    private SwapConfirmBean swapConfirmBean;
    private SwapWhiteListOutput swapWhitelistOutput;
    private Timer timer;
    private SwapTokenBean tokenFrom;
    private SwapTokenBean tokenTo;
    private SwapTokenBean tokenTrx;
    private TimerTask updateSwapTransTask;
    private String LEFT_TOKEN_NAME = "TRX";
    private String RIGHT_TOKEN_NAME = "USDT";
    private final String[] amounts = new String[2];
    private boolean isExchanging = false;
    private boolean isLoadingToken = false;
    private boolean showBalancePlaceHolder = false;
    private boolean pauseLoop = false;
    private int selectedRouterIndex = 0;

    public void lambda$initEvents$10(Object obj) throws Exception {
        this.showBalancePlaceHolder = true;
    }

    public void lambda$initEvents$6(Object obj) throws Exception {
        this.isExchanging = true;
    }

    public void lambda$initEvents$8() {
        this.backFromSelectPage = false;
    }

    @Override
    public void getRecord() {
        if (this.mView == 0) {
            return;
        }
        ((Contract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getRecord$1();
            }
        });
    }

    public void lambda$getRecord$1() {
        try {
            final List<JustSwapBean> lastestNotesByAddress = JustSwapTransactionController.getInstance().getLastestNotesByAddress(WalletUtils.getSelectedWallet().getAddress());
            final boolean z = lastestNotesByAddress.size() == 6;
            if (z) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < 5; i++) {
                    arrayList.add(lastestNotesByAddress.get(i));
                }
                lastestNotesByAddress = arrayList;
            }
            ((Contract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getRecord$0(lastestNotesByAddress, z);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$getRecord$0(List list, boolean z) {
        ((Contract.View) this.mView).getAdapter().notifyRecords(list, z);
    }

    public void initTokens(final boolean z, final AssetsHomeData assetsHomeData) {
        if (assetsHomeData == null) {
            return;
        }
        SwapTokenBean swapTokenBean = this.tokenFrom;
        if (swapTokenBean != null && this.tokenTo != null && this.showBalancePlaceHolder) {
            swapTokenBean.setBalanceStr(TokenHolder.PLACE_HOLDER);
            this.tokenTo.setBalanceStr(TokenHolder.PLACE_HOLDER);
            ((Contract.View) this.mView).onGetInitTokens(this.tokenFrom, this.tokenTo, false);
        }
        this.isLoadingToken = true;
        updateInputAmounts("", "");
        ((Contract.Model) this.mModel).getWhiteListTokens(z, ((Contract.View) this.mView).getIContext()).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                Observable lambda$initTokens$3;
                lambda$initTokens$3 = lambda$initTokens$3(assetsHomeData, z, (SwapWhiteListOutput) obj);
                return lambda$initTokens$3;
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<SwapWhiteListOutput>() {
            @Override
            public void onResponse(String str, SwapWhiteListOutput swapWhiteListOutput) {
                swapWhitelistOutput = swapWhiteListOutput;
                ((Contract.View) mView).onGetInitTokens(tokenFrom, tokenTo, false);
                isLoadingToken = false;
            }

            @Override
            public void onFailure(String str, String str2) {
                isLoadingToken = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add("initTokens", disposable);
            }
        }, "SwapPresenter-getInitToken"));
    }

    public Observable lambda$initTokens$3(final AssetsHomeData assetsHomeData, final boolean z, final SwapWhiteListOutput swapWhiteListOutput) throws Exception {
        if (swapWhiteListOutput == null) {
            return Observable.just(new SwapWhiteListOutput());
        }
        final List<SwapWhiteListOutput.Data> tokens = swapWhiteListOutput.getTokens();
        if (tokens == null || tokens.isEmpty()) {
            return Observable.just(swapWhiteListOutput);
        }
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$initTokens$2(tokens, assetsHomeData, swapWhiteListOutput, z, observableEmitter);
            }
        }).delaySubscription(1L, TimeUnit.SECONDS);
    }

    public void lambda$initTokens$2(List list, AssetsHomeData assetsHomeData, SwapWhiteListOutput swapWhiteListOutput, boolean z, ObservableEmitter observableEmitter) throws Exception {
        this.tokenTrx = findToken("TRX", list, assetsHomeData.token);
        SwapTokenBean swapTokenBean = this.tokenFrom;
        String balanceStr = swapTokenBean != null ? swapTokenBean.getBalanceStr() : null;
        SwapTokenBean findToken = findToken(this.LEFT_TOKEN_NAME, list, assetsHomeData.token);
        this.tokenFrom = findToken;
        if (findToken != null && !TextUtils.isEmpty(balanceStr)) {
            this.tokenFrom.setBalanceStr(balanceStr);
        }
        SwapTokenBean swapTokenBean2 = this.tokenTo;
        String balanceStr2 = swapTokenBean2 != null ? swapTokenBean2.getBalanceStr() : null;
        SwapTokenBean findToken2 = findToken(this.RIGHT_TOKEN_NAME, list, assetsHomeData.token);
        this.tokenTo = findToken2;
        if (findToken2 != null && !TextUtils.isEmpty(balanceStr2)) {
            this.tokenTo.setBalanceStr(balanceStr2);
        }
        observableEmitter.onNext(swapWhiteListOutput);
        if (!TronConfig.hasNet && this.showBalancePlaceHolder) {
            this.tokenFrom.setBalanceStr(TokenHolder.PLACE_HOLDER);
            this.tokenTo.setBalanceStr(TokenHolder.PLACE_HOLDER);
        } else {
            try {
                SwapTokenBean swapTokenBean3 = this.tokenFrom;
                if (swapTokenBean3 != null) {
                    swapTokenBean3.setInputAmount("");
                    SwapTokenBean swapTokenBean4 = this.tokenFrom;
                    swapTokenBean4.setBalanceStr(getBalance(swapTokenBean4));
                }
                SwapTokenBean swapTokenBean5 = this.tokenTo;
                if (swapTokenBean5 != null) {
                    swapTokenBean5.setInputAmount("");
                    SwapTokenBean swapTokenBean6 = this.tokenTo;
                    swapTokenBean6.setBalanceStr(getBalance(swapTokenBean6));
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        this.showBalancePlaceHolder = false;
        observableEmitter.onNext(swapWhiteListOutput);
        SwapTokenBean swapTokenBean7 = this.tokenTrx;
        if (swapTokenBean7 != null && z) {
            SwapWhiteListOutput.Data fromSwapTokenBean = SwapWhiteListOutput.Data.fromSwapTokenBean(swapTokenBean7);
            if (!((SwapWhiteListOutput.Data) list.get(0)).equals(fromSwapTokenBean)) {
                swapWhiteListOutput.getTokens().add(0, fromSwapTokenBean);
            }
            SwapCacheUtils.getInstance().save(((Contract.View) this.mView).getIContext(), swapWhiteListOutput);
        }
        observableEmitter.onComplete();
    }

    @Override
    public void getInitTokens(final boolean z) {
        this.selectedRouterIndex = 0;
        setLoopPause(true);
        final Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet == null || this.backFromSelectPage) {
            return;
        }
        Observable.create(new ObservableOnSubscribe<AssetsHomeData>() {
            @Override
            public void subscribe(ObservableEmitter<AssetsHomeData> observableEmitter) throws Exception {
                observableEmitter.onNext(AssetsHomeDataDaoManager.getHomeData(((Contract.View) mView).getIContext(), selectedWallet.getAddress()));
                observableEmitter.onComplete();
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<AssetsHomeData>() {
            @Override
            public void onResponse(String str, AssetsHomeData assetsHomeData) {
                initTokens(z, assetsHomeData);
            }

            @Override
            public void onFailure(String str, String str2) {
                LogUtils.d(SwapPresenter.TAG, str2);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add("getInitTokens", disposable);
            }
        }, "initTokens"));
    }

    @Override
    public void stopCheckTranscation() {
        TimerTask timerTask = this.updateSwapTransTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.updateSwapTransTask = null;
        }
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }

    @Override
    public View.OnClickListener getOnBtnConfirmClickListener() {
        return new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                if (selectedPublicWallet != null && selectedPublicWallet.isWatchNotPaired()) {
                    PairColdWalletDialog.showUp(((Contract.View) mView).getIContext(), null);
                    return;
                }
                updateButtonEnabled(false);
                SwapPresenter swapPresenter = SwapPresenter.this;
                if (swapPresenter.isConfirmBeanMatch(swapPresenter.currentSwapOutput)) {
                    showConfirmDialog();
                }
            }
        };
    }

    @Override
    public TokenHolder.OnAmountChangedListener getAmountChangedListener() {
        return new TokenHolder.OnAmountChangedListener() {
            @Override
            public void onInputAmountChanged(int i, String str, boolean z) {
                swapButtonEnabled = z;
                try {
                    updateButtonEnabled(false);
                    onAmountChanged(str, i);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        };
    }

    @Override
    public TokenHolder.OnTokenChangedCallback getTokenChangedListener() {
        return new TokenHolder.OnTokenChangedCallback() {
            @Override
            public void onSelectToken(int i) {
                if (isLoadingToken) {
                    ((Contract.View) mView).toast(((Contract.View) mView).getIContext().getString(R.string.wait_loading));
                } else if (tokenFrom == null || tokenTo == null) {
                } else {
                    if (i == 0) {
                        SelectTokenActivity.start(((Contract.View) mView).getIContext(), tokenFrom, tokenTo, 0);
                    } else {
                        SelectTokenActivity.start(((Contract.View) mView).getIContext(), tokenTo, tokenFrom, 1);
                    }
                }
            }

            @Override
            public void onExchangeToken() {
                if (isLoadingToken) {
                    ((Contract.View) mView).toast(((Contract.View) mView).getIContext().getString(R.string.wait_loading));
                } else {
                    exchangeTokenPosition();
                }
            }
        };
    }

    @Override
    public void startCheckTranscationState() {
        stopCheckTranscation();
        if (this.updateSwapTransTask == null) {
            this.updateSwapTransTask = new TimerTask() {
                UpdateSwapTransactionStatus updateSwapTransactionStatus = new UpdateSwapTransactionStatus();

                @Override
                public void run() {
                    LogUtils.d(SwapPresenter.TAG, "start  update SwapTransactionStatus");
                    this.updateSwapTransactionStatus.start();
                }
            };
        }
        Timer timer = new Timer();
        this.timer = timer;
        timer.schedule(this.updateSwapTransTask, DEFAULT_LOOP_DURATION, 6000L);
    }

    @Override
    protected void onStart() {
        this.rxManager = this.mRxManager;
        this.handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 0) {
                    loopSwapInfo();
                } else if (message.what == 1) {
                    LogUtils.d(SwapPresenter.TAG, "MSG_BALANCE_VALUE updateBalance");
                    updateBalance();
                }
            }
        };
        initEvents();
        PublishSubject<String> create = PublishSubject.create();
        this.amountPublishSubject = create;
        create.subscribe(new IObserver(new ICallback<String>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, String str2) {
                amounts[0] = str2;
                sendLoopMessage(true);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                rxManager.add("onAmountChanged", disposable);
            }
        }, "onAmountChanged"));
    }

    public void setLoopPause(boolean z) {
        this.pauseLoop = z;
        if (z) {
            this.handler.removeMessages(0);
        }
    }

    public void sendLoopMessage(boolean z) {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        handler.sendEmptyMessageDelayed(0, z ? 0L : DEFAULT_LOOP_DURATION);
    }

    public void loopSwapInfo() {
        String str = this.amounts[0];
        if (str == null || this.pauseLoop || this.tokenFrom == null || this.tokenTo == null) {
            return;
        }
        querySwapInfo(str).subscribe(getSwapInfoObserver());
    }

    private Observable<SwapInfoOutput> querySwapInfo(String str) {
        return ((Contract.Model) this.mModel).querySwapInfo(this.tokenFrom, this.tokenTo.getToken(), !TextUtils.isEmpty(str) ? new BigDecimal(str).multiply(new BigDecimal(10).pow(this.tokenFrom.getDecimal())).stripTrailingZeros().toPlainString() : "0");
    }

    private Observer<SwapInfoOutput> getSwapInfoObserver() {
        return new IObserver(new ICallback<SwapInfoOutput>() {
            @Override
            public void onResponse(String str, SwapInfoOutput swapInfoOutput) {
                currentSwapOutput = swapInfoOutput;
                if (swapInfoOutput.getCode() != 0 || swapInfoOutput.getData() == null || swapInfoOutput.getData().isEmpty()) {
                    sendLoopMessage(false);
                    SwapTokenInfoBean swapTokenInfoBean = new SwapTokenInfoBean();
                    swapTokenInfoBean.setRate(TokenHolder.PLACE_HOLDER);
                    if (mView != 0) {
                        ((Contract.View) mView).updateSwapTokenInfo(swapTokenInfoBean, null, 0, null, false);
                        ((Contract.View) mView).updateSwapTokenValues(2, tokenFrom == null ? amounts[0] : tokenFrom.getInputAmount(), "");
                        return;
                    }
                    return;
                }
                SwapPresenter swapPresenter = SwapPresenter.this;
                swapPresenter.onUpdateSwapInfo(swapInfoOutput, swapPresenter.selectedRouterIndex);
                SwapPresenter swapPresenter2 = SwapPresenter.this;
                swapPresenter2.updateButtonEnabled(swapPresenter2.swapButtonEnabled);
                sendLoopMessage(false);
            }

            @Override
            public void onFailure(String str, String str2) {
                currentSwapToken = null;
                sendLoopMessage(false);
                if (!TextUtils.isEmpty(amounts[0]) || BigDecimalUtils.MoreThan((Object) amounts[0], (Object) 0)) {
                    return;
                }
                if (mView != 0) {
                    ((Contract.View) mView).updateSwapTokenValues(2, "", "");
                    ((Contract.View) mView).notifyInfoVisible(false);
                }
                updateInputAmounts("", "");
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add("SwapInfoObserver", disposable);
            }
        }, "query_swap_info");
    }

    public void onUpdateSwapInfo(SwapInfoOutput swapInfoOutput, int i) {
        List<SwapInfoOutput.InfoData> data = swapInfoOutput.getData();
        SwapInfoOutput.InfoData infoData = data.get(i);
        SwapTokenInfoBean fromSwapInfoOutput = SwapTokenInfoBean.fromSwapInfoOutput(infoData);
        this.currentSwapToken = fromSwapInfoOutput;
        if (this.mView != 0) {
            ((Contract.View) this.mView).updateSwapTokenValues(2, this.amounts[0], BigDecimalUtils.toBigDecimal(infoData.getAmountOut()).stripTrailingZeros().toPlainString());
            ((Contract.View) this.mView).updateSwapTokenInfo(fromSwapInfoOutput, data, i, new fun11(swapInfoOutput), true);
        }
        updateInputAmounts(infoData.getAmountIn(), infoData.getAmountOut());
    }

    public class fun11 extends NoDoubleClickListener2 {
        final SwapInfoOutput val$result;

        fun11(SwapInfoOutput swapInfoOutput) {
            this.val$result = swapInfoOutput;
        }

        @Override
        protected void onNoDoubleClick(View view) {
            if (this.val$result.getData() == null || this.val$result.getData().size() <= 1 || !BigDecimalUtils.Equal(this.val$result.getData().get(selectedRouterIndex).getAmountIn(), tokenFrom.getInputAmount())) {
                return;
            }
            setLoopPause(true);
            Context context = view.getContext();
            int i = selectedRouterIndex;
            final SwapInfoOutput swapInfoOutput = this.val$result;
            SwapRouterDialog.showDialog(context, i, swapInfoOutput, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    SwapPresenter.11.this.lambda$onNoDoubleClick$0(swapInfoOutput, (Integer) obj);
                }
            });
        }

        public void lambda$onNoDoubleClick$0(SwapInfoOutput swapInfoOutput, Integer num) throws Exception {
            selectedRouterIndex = num.intValue();
            onUpdateSwapInfo(swapInfoOutput, num.intValue());
            sendLoopMessage(true);
        }
    }

    private void initEvents() {
        this.rxManager.on(Event.BROADCAST_TRANSACTION, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$4(obj);
            }
        });
        this.rxManager.on(Event.SAMSUNG_KEYSTORE_BROADCAST_TRANSACTION, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$5(obj);
            }
        });
        this.rxManager.on(Event.SWAP_CONFIRM_SWAP, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$6(obj);
            }
        });
        this.rxManager.on(Event.SWAP_TRANSACTION_UPDATE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$7(obj);
            }
        });
        this.rxManager.on(Event.SWAP_SELECTED_TOKEN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$9(obj);
            }
        });
        this.rxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$10(obj);
            }
        });
        this.rxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initEvents$11(obj);
            }
        });
    }

    public void lambda$initEvents$4(Object obj) throws Exception {
        getRecord();
        this.swapConfirmBean = null;
        resetAmounts();
        onAmountChanged("", 3);
        recordTransactionCounter();
        checkAndReportTransaction(obj);
    }

    public void lambda$initEvents$5(Object obj) throws Exception {
        LogUtils.d(TAG, "SAMSUNG_KEYSTORE_BROADCAST_TRANSACTION");
        getRecord();
        this.swapConfirmBean = null;
        resetAmounts();
        updateBalance();
        this.selectedRouterIndex = 0;
        if (this.handler != null) {
            LogUtils.d(TAG, "SAMSUNG_KEYSTORE sendEmptyMessageDelayed");
            this.handler.sendEmptyMessageDelayed(1, 5000L);
            this.handler.sendEmptyMessageDelayed(1, TronConfig.PRICE_UPDATE_INTERVAL);
            this.handler.sendEmptyMessageDelayed(1, DEFAULT_LOOP_DURATION);
            this.handler.sendEmptyMessageDelayed(1, TronConfig.ACCOUNT_UPDATE_FOREGROUND_INTERVAL);
            this.handler.sendEmptyMessageDelayed(1, 60000L);
        }
    }

    public void lambda$initEvents$7(Object obj) throws Exception {
        getRecord();
    }

    public void lambda$initEvents$9(Object obj) throws Exception {
        this.backFromSelectPage = true;
        if (obj instanceof SwapSelectBean) {
            SwapSelectBean swapSelectBean = (SwapSelectBean) obj;
            updateSelectedToken(swapSelectBean.getPosition(), SwapTokenBean.extend(swapSelectBean.getSwapWhiteListOutput()));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$initEvents$8();
            }
        }, 1000L);
    }

    public void lambda$initEvents$11(Object obj) throws Exception {
        ((Contract.View) this.mView).showNoNetNotice();
    }

    public void showConfirmDialog() {
        AnalyticsHelper.logEvent(AnalyticsHelper.MarketPage.CLICK_MARKET_PAGE_SWAP_SWAP_CONFIRM);
        this.currentTransaction = null;
        if (!TronConfig.hasNet) {
            ((Contract.View) this.mView).toast(((Contract.View) this.mView).getIContext().getString(R.string.no_network));
            updateButtonEnabled(true);
            return;
        }
        SwapTokenInfoBean swapTokenInfoBean = this.currentSwapToken;
        if (swapTokenInfoBean == null) {
            toastTimeOutError();
            this.swapConfirmBean = null;
            updateButtonEnabled(true);
            return;
        }
        SwapConfirmBean fromSwapTokenInfo = SwapConfirmBean.fromSwapTokenInfo(swapTokenInfoBean);
        this.swapConfirmBean = fromSwapTokenInfo;
        fromSwapTokenInfo.setTokenFrom(this.tokenFrom);
        this.swapConfirmBean.setTokenTo(this.tokenTo);
        this.swapConfirmBean.setAmountLeft(this.amounts[0]);
        this.swapConfirmBean.setAmountRight(this.amounts[1]);
        final Wallet selectedWallet = WalletUtils.getSelectedWallet();
        String address = selectedWallet.getAddress();
        String checkUnsupportableWithMessage = checkUnsupportableWithMessage(selectedWallet);
        if (!TextUtils.isEmpty(checkUnsupportableWithMessage)) {
            ((Contract.View) this.mView).ToastSuc(checkUnsupportableWithMessage);
            updateButtonEnabled(true);
            return;
        }
        processApproveOrSwap(address, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$showConfirmDialog$13(selectedWallet, obj);
            }
        });
    }

    public void lambda$showConfirmDialog$13(final Wallet wallet, Object obj) throws Exception {
        ((Contract.View) this.mView).showLoadingWindow(true);
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$showConfirmDialog$12(wallet, observableEmitter);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Observer<BaseConfirmTransParamBuilder>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onNext(BaseConfirmTransParamBuilder baseConfirmTransParamBuilder) {
                ConfirmTransactionNewActivity.startActivity(((Contract.View) mView).getIContext(), baseConfirmTransParamBuilder, wallet.isSamsungWallet());
                ((Contract.View) mView).showLoadingWindow(false);
            }

            @Override
            public void onError(Throwable th) {
                if (th.getMessage() != null) {
                    ((Contract.View) mView).ToastSuc(th.getMessage() != null ? th.getMessage() : ((Contract.View) mView).getIContext().getString(R.string.create_transaction_fail));
                }
                ((Contract.View) mView).showLoadingWindow(false);
                updateButtonEnabled(true);
            }
        });
    }

    public void lambda$showConfirmDialog$12(Wallet wallet, ObservableEmitter observableEmitter) throws Exception {
        List<Protocol.Transaction> submitSwap = ((Contract.Model) this.mModel).submitSwap(this.currentSwapOutput, this.selectedRouterIndex, this.tokenFrom, this.tokenTo, wallet);
        if (submitSwap.isEmpty()) {
            observableEmitter.onError(new Exception(((Contract.View) this.mView).getIContext().getString(R.string.create_transaction_fail)));
            return;
        }
        this.currentTransaction = submitSwap.get(submitSwap.size() - 1);
        observableEmitter.onNext(ParamBuildUtils.getSwapParamBuilder(true, submitSwap, this.swapConfirmBean));
        observableEmitter.onComplete();
    }

    private String checkUnsupportableWithMessage(Wallet wallet) {
        try {
            Protocol.Account account = WalletUtils.getAccount(((Contract.View) this.mView).getIContext(), wallet.getWalletName());
            if (account != null && account.toString().length() != 0) {
                return !WalletUtils.checkHaveOwnerPermissions(wallet.getAddress(), account.getOwnerPermission()) ? ((Contract.View) this.mView).getIContext().getString(R.string.err_swap_no_permission) : "";
            }
            return ((Contract.View) this.mView).getIContext().getString(R.string.swap_not_support_inactive);
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }

    private void processApproveOrSwap(final String str, final Consumer<Object> consumer) {
        if (this.tokenFrom == null) {
            LogUtils.e(TAG, "From token should not be null!");
            return;
        }
        ((Contract.View) this.mView).showLoadingWindow(true);
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$processApproveOrSwap$14(str, observableEmitter);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onResponse(String str2, Boolean bool) {
                ((Contract.View) mView).showLoadingWindow(false);
                if (bool.booleanValue()) {
                    SwapConfirmMockActivity.start(((Contract.View) mView).getIContext(), JSON.toJSONString(currentSwapOutput), selectedRouterIndex, JSON.toJSONString(tokenFrom), JSON.toJSONString(tokenTo), SwapModel.getSwapContractAddress());
                    return;
                }
                Consumer consumer2 = consumer;
                if (consumer2 != null) {
                    try {
                        consumer2.accept(0);
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((Contract.View) mView).showLoadingWindow(false);
                LogUtils.e(SwapPresenter.TAG, "Swap process failed, \n" + str2 + StringUtils.LF + str3);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "processSwap"));
    }

    public void lambda$processApproveOrSwap$14(String str, ObservableEmitter observableEmitter) throws Exception {
        boolean LessThan;
        if (!this.tokenFrom.isTrx()) {
            try {
                LessThan = BigDecimalUtils.LessThan(TriggerUtils.getApproveAmount(this.tokenFrom.getToken(), str, SwapModel.getSwapContractAddress()), new BigDecimal(this.tokenFrom.getInputAmount()).multiply(new BigDecimal(Math.pow(10.0d, this.tokenFrom.getDecimal()))));
            } catch (Exception e) {
                LogUtils.w(TAG, "Failed to query approve amount ");
                LogUtils.e(e);
            }
            observableEmitter.onNext(Boolean.valueOf(LessThan));
            observableEmitter.onComplete();
        }
        LessThan = false;
        observableEmitter.onNext(Boolean.valueOf(LessThan));
        observableEmitter.onComplete();
    }

    public void exchangeTokenPosition() {
        SwapTokenBean swapTokenBean;
        this.selectedRouterIndex = 0;
        SwapTokenBean swapTokenBean2 = this.tokenFrom;
        if (swapTokenBean2 == null || (swapTokenBean = this.tokenTo) == null) {
            ((Contract.View) this.mView).toast(((Contract.View) this.mView).getIContext().getString(R.string.wait_loading));
            return;
        }
        this.tokenFrom = swapTokenBean;
        this.tokenTo = swapTokenBean2;
        String str = this.LEFT_TOKEN_NAME;
        this.LEFT_TOKEN_NAME = this.RIGHT_TOKEN_NAME;
        this.RIGHT_TOKEN_NAME = str;
        String[] strArr = this.amounts;
        String str2 = strArr[1];
        strArr[0] = str2;
        strArr[1] = "";
        swapTokenBean.setInputAmount(str2);
        this.tokenTo.setInputAmount(this.amounts[1]);
        setLoopPause(true);
        ((Contract.View) this.mView).onGetInitTokens(this.tokenFrom, this.tokenTo, false);
        onAmountChanged(this.amounts[0], 2);
    }

    public void onAmountChanged(String str, int i) {
        this.selectedRouterIndex = 0;
        setLoopPause(true);
        this.currentSwapOutput = null;
        if (this.isLoadingToken) {
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "0")) {
            updateInputAmounts("", "");
            setLoopPause(true);
            if (this.mView != 0) {
                ((Contract.View) this.mView).updateSwapTokenValues(i, "", "");
                ((Contract.View) this.mView).notifyInfoVisible(false);
            }
        }
        setLoopPause(false);
        this.amountPublishSubject.onNext(str);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void updateInputAmounts(String str, String str2) {
        String[] strArr = this.amounts;
        strArr[0] = str;
        strArr[1] = str2;
    }

    private void updateSelectedToken(final int i, final SwapTokenBean swapTokenBean) {
        this.selectedRouterIndex = 0;
        this.currentSwapToken = null;
        if (swapTokenBean == null) {
            return;
        }
        swapTokenBean.setBalanceStr(TokenHolder.PLACE_HOLDER);
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                if (i == 0) {
                    LEFT_TOKEN_NAME = swapTokenBean.getSymbol();
                    tokenFrom = swapTokenBean;
                } else {
                    RIGHT_TOKEN_NAME = swapTokenBean.getSymbol();
                    tokenTo = swapTokenBean;
                }
                resetAmounts();
                observableEmitter.onNext(true);
                SwapTokenBean swapTokenBean2 = swapTokenBean;
                swapTokenBean2.setBalanceStr(getBalance(swapTokenBean2));
                observableEmitter.onNext(true);
                observableEmitter.onComplete();
            }
        }).compose(RxSchedulers.io_main()).subscribe(new IObserver(new ICallback<Boolean>() {
            @Override
            public void onResponse(String str, Boolean bool) {
                ((Contract.View) mView).onGetInitTokens(tokenFrom, tokenTo, false);
                SwapTokenInfoBean swapTokenInfoBean = new SwapTokenInfoBean();
                swapTokenInfoBean.setRate(TokenHolder.PLACE_HOLDER);
                ((Contract.View) mView).updateSwapTokenInfo(swapTokenInfoBean, null, 0, null, false);
                backFromSelectPage = false;
            }

            @Override
            public void onFailure(String str, String str2) {
                backFromSelectPage = false;
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add("getTokenBalance", disposable);
            }
        }, "getTokenBalance"));
    }

    private void toastTimeOutError() {
        if (this.mView != 0) {
            ((Contract.View) this.mView).showNoNetNotice();
            if (TronConfig.hasNet) {
                ((Contract.View) this.mView).toast(((Contract.View) this.mView).getIContext().getString(R.string.time_out));
            }
        }
    }

    private com.tron.wallet.business.tabswap.bean.SwapTokenBean findToken(java.lang.String r5, java.util.List<com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput.Data> r6, java.util.List<com.tron.wallet.common.bean.token.TokenBean> r7) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabswap.mvp.SwapPresenter.findToken(java.lang.String, java.util.List, java.util.List):com.tron.wallet.business.tabswap.bean.SwapTokenBean");
    }

    public String getBalance(SwapTokenBean swapTokenBean) {
        Wallet selectedWallet;
        Protocol.Account account;
        if (swapTokenBean != null && (selectedWallet = WalletUtils.getSelectedWallet()) != null) {
            if (!swapTokenBean.isTrx()) {
                try {
                    return BigDecimalUtils.div_(TriggerUtils.balanceOf(swapTokenBean.getToken(), selectedWallet.getAddress()), Double.valueOf(Math.pow(10.0d, swapTokenBean.getDecimal()))).toPlainString();
                } catch (Exception unused) {
                    return "0";
                }
            }
            try {
                account = TronAPI.queryAccount(StringTronUtil.decode58Check(selectedWallet.getAddress()), false);
            } catch (Exception e) {
                LogUtils.e(e);
                account = WalletUtils.getAccount(((Contract.View) this.mView).getIContext(), selectedWallet.getWalletName());
            }
            if (account != null) {
                return BigDecimalUtils.div_(Long.valueOf(account.getBalance()), Double.valueOf(Math.pow(10.0d, 6.0d))).toPlainString();
            }
        }
        return "0";
    }

    public void updateBalance() {
        if (this.tokenFrom == null || this.tokenTo == null) {
            return;
        }
        try {
            ((Contract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$updateBalance$16();
                }
            });
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public void lambda$updateBalance$16() {
        try {
            SwapTokenBean swapTokenBean = this.tokenFrom;
            if (swapTokenBean != null && this.tokenTo != null) {
                if (swapTokenBean != null) {
                    swapTokenBean.setBalanceStr(getBalance(swapTokenBean));
                }
                SwapTokenBean swapTokenBean2 = this.tokenTo;
                if (swapTokenBean2 != null) {
                    swapTokenBean2.setBalanceStr(getBalance(swapTokenBean2));
                }
                ((Contract.View) this.mView).runOnUIThread(new OnMainThread() {
                    @Override
                    public final void doInUIThread() {
                        lambda$updateBalance$15();
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$updateBalance$15() {
        if (this.tokenFrom == null || this.tokenTo == null) {
            return;
        }
        try {
            ((Contract.View) this.mView).updateBalance(this.tokenFrom.getBalanceStr(), this.tokenTo.getBalanceStr());
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
        }
    }

    public void onInvisible() {
        this.selectedRouterIndex = 0;
        if (this.tokenFrom == null || this.tokenTo == null) {
            return;
        }
        resetAmounts();
        setLoopPause(true);
        SwapRouterDialog.dismissDialog();
    }

    public void resetAmounts() {
        SwapTokenBean swapTokenBean = this.tokenFrom;
        if (swapTokenBean != null) {
            swapTokenBean.setInputAmount("");
        }
        SwapTokenBean swapTokenBean2 = this.tokenTo;
        if (swapTokenBean2 != null) {
            swapTokenBean2.setInputAmount("");
        }
        updateInputAmounts("", "");
    }

    private void recordTransactionCounter() {
        Bundle bundle = new Bundle();
        int i = swapCounter + 1;
        swapCounter = i;
        bundle.putInt(Event.FB_SWAP_SIGNED_COUNTER, i);
        FirebaseAnalytics.getInstance(((Contract.View) this.mView).getIContext()).logEvent(Event.FB_SWAP_SIGNED, bundle);
        LogUtils.w(TAG, "swap signed counter: " + swapCounter);
    }

    private void checkAndReportTransaction(final Object obj) {
        if (this.currentTransaction == null && !(obj instanceof String)) {
            LogUtils.w(TAG, "No SWAP transaction data !");
        } else {
            Observable.create(new ObservableOnSubscribe() {
                @Override
                public final void subscribe(ObservableEmitter observableEmitter) {
                    lambda$checkAndReportTransaction$17(obj, observableEmitter);
                }
            }).delaySubscription(2000L, TimeUnit.MILLISECONDS).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<Protocol.Transaction>() {
                @Override
                public void onResponse(String str, Protocol.Transaction transaction) {
                    if (transaction != null) {
                        currentTransaction = transaction;
                        recordTransactionTRX();
                    }
                }

                @Override
                public void onFailure(String str, String str2) {
                    LogUtils.w(SwapPresenter.TAG, "SWAP data record failed");
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    addDisposable(disposable);
                }
            }, "checkAndReportTransaction"));
        }
    }

    public void lambda$checkAndReportTransaction$17(Object obj, ObservableEmitter observableEmitter) throws Exception {
        if (this.currentTransaction == null) {
            this.currentTransaction = TronAPI.getTransactionById((String) obj);
        }
        observableEmitter.onNext(this.currentTransaction);
        observableEmitter.onComplete();
    }

    public void recordTransactionTRX() {
        String plainString;
        if (this.currentTransaction == null) {
            LogUtils.w(TAG, "No SWAP transaction data !");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.currentTransaction);
        SwapTokenBean swapTokenBean = this.tokenFrom;
        if (swapTokenBean != null && swapTokenBean.isTrx()) {
            plainString = this.tokenFrom.getInputAmount();
        } else {
            SwapTokenBean swapTokenBean2 = this.tokenTo;
            if (swapTokenBean2 != null && swapTokenBean2.isTrx()) {
                plainString = this.currentSwapToken.getMinReceived();
            } else {
                plainString = new BigDecimal(this.currentSwapToken.getOutUsd()).divide(new BigDecimal(SpAPI.THIS.getUsdPrice()), RoundingMode.HALF_DOWN).stripTrailingZeros().toPlainString();
            }
        }
        LogUtils.w(TAG, "Transaction amount: " + plainString);
        try {
            DappConfirmInput dappConfirmInput = new DappConfirmInput();
            dappConfirmInput.transactionString = WalletUtils.printTransaction((Protocol.Transaction) arrayList.get(arrayList.size() - 1));
            dappConfirmInput.dappName = "instant_swap";
            dappConfirmInput.dappUrl = plainString;
            ((Contract.Model) this.mModel).recordTransaction(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(dappConfirmInput))).subscribe(new IObserver(new ICallback<Object>() {
                @Override
                public void onResponse(String str, Object obj) {
                    LogUtils.w(SwapPresenter.TAG, "SWAP data record complete");
                }

                @Override
                public void onFailure(String str, String str2) {
                    LogUtils.w(SwapPresenter.TAG, "SWAP data record failed");
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "addDappRecord"));
        } catch (Exception unused) {
        }
    }

    public boolean isConfirmBeanMatch(SwapInfoOutput swapInfoOutput) {
        SwapTokenBean swapTokenBean;
        if (swapInfoOutput == null || swapInfoOutput.getData() == null || swapInfoOutput.getData().isEmpty() || (swapTokenBean = this.tokenFrom) == null || swapTokenBean.getInputAmount() == null) {
            return false;
        }
        return BigDecimalUtils.Equal(swapInfoOutput.getData().get(0).getAmountIn(), this.tokenFrom.getInputAmount());
    }

    public void updateButtonEnabled(boolean z) {
        if (this.mView == 0 || ((Contract.View) this.mView).getAdapter() == null) {
            return;
        }
        ((Contract.View) this.mView).getAdapter().updateButtonEnabled(z);
    }
}
