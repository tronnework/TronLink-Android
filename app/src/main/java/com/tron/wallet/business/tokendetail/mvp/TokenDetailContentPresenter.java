package com.tron.wallet.business.tokendetail.mvp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.cache.DiskLruCacheHelper;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailContentContract;
import com.tron.wallet.business.transfer.TransactionDetailActivity;
import com.tron.wallet.common.adapter.token.TokenDetailContentAdapter;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.token.OfficialType;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.bean.token.TransferOutput;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import com.tron.wallet.common.components.RecycleViewDivider;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TransactionHistoryProvider;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.tron.walletserver.Wallet;
public class TokenDetailContentPresenter extends TokenDetailContentContract.Presenter implements ICallback<TransferOutput> {
    private static final String TAG = "TokenDetailContentPresenter";
    private List<ChainBean> allChainJson;
    private int allTotal;
    private TokenDetailContentAdapter contentAdapter;
    private Map<String, Boolean> contractMap;
    private DiskLruCacheHelper diskLruCacheHelper;
    private boolean hasData;
    private int index;
    private boolean isLoadingLocalData;
    private boolean isMainChain;
    private boolean isMapping;
    private boolean isRefreshing;
    private Comparator<? super TransactionHistoryBean> mComparator;
    private TokenBean mToken;
    private ChainBean mainChain;
    private Wallet selectedWallet;
    private ChainBean sideChain;
    private int startIndex;
    private Timer timer;
    private String tokenType;
    private final String ALLTRANSFER = "ALLTRANSFER";
    private final String RECEIVETRANSFER = "RECEIVETRANSFER";
    private final String SENTTRANSFER = "SENTTRANSFER";
    private final String DEPOSITORWITHDRAW = "DEPOSITORWITHDRAW";
    private boolean hasPending = false;
    private List<TransactionHistoryBean> list = new ArrayList();
    private int limit = 20;
    private boolean isPullRefresh = false;
    private String typeStr = "tokenAddress";
    private boolean isFirstLoaded = true;
    private boolean isFilterSmalling = false;

    private void startRefresh() {
        this.isRefreshing = true;
    }

    public String getTabTitle(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "" : "DEPOSITORWITHDRAW" : "RECEIVETRANSFER" : "SENTTRANSFER" : "ALLTRANSFER";
    }

    @Override
    public boolean isCurRefresh() {
        return this.isPullRefresh;
    }

    @Override
    void netWorkChange() {
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void setmToken(TokenBean tokenBean) {
        this.mToken = tokenBean;
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
        this.mRxManager.on(Event.PENDING_SUCCESS, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
        this.mRxManager.on(Event.PENDING_FAIL, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$2(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if ((obj instanceof String) && ((String) obj).equals("WITHDRAW_DEPOSIT_TYPE")) {
            addLocalHistoryToListAndShow();
        }
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        String str = (String) obj;
        if (this.list != null) {
            for (int i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).getHash().equals(str)) {
                    this.list.get(i).setConfirmed(0);
                    this.list.get(i).setTx_status(0);
                    this.contentAdapter.notifyItemChanged(i);
                    return;
                }
            }
        }
    }

    public void lambda$onStart$2(Object obj) throws Exception {
        String str = (String) obj;
        if (this.list != null) {
            for (int i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).getHash().equals(str)) {
                    this.list.get(i).setConfirmed(0);
                    this.list.get(i).setTx_status(2);
                    this.contentAdapter.notifyItemChanged(i);
                    return;
                }
            }
        }
    }

    @Override
    public void addSome() {
        init();
        initRecycleView();
    }

    private void addTimer() {
        if (this.timer == null && !((TokenDetailContentContract.View) this.mView).isIDestroyed()) {
            this.timer = new Timer();
        }
        if (this.index == 0) {
            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    LogUtils.e(TokenDetailContentPresenter.TAG, "getTransaction addTimer ");
                    List<TransactionHistoryBean> pendingTransactionByToken = TransactionHistoryProvider.getPendingTransactionByToken(selectedWallet.getAddress(), mToken);
                    LogUtils.e(TokenDetailContentPresenter.TAG, "pendinglist.size:   " + pendingTransactionByToken.size());
                    for (int i = 0; i < pendingTransactionByToken.size(); i++) {
                        final TransactionHistoryBean transactionHistoryBean = pendingTransactionByToken.get(i);
                        LogUtils.e(TokenDetailContentPresenter.TAG, "getTransactionInfo  transactionHistoryBean: " + transactionHistoryBean.getHash());
                        Observable<TransactionInfoBean> transactionInfo = ((TokenDetailContentContract.Model) mModel).getTransactionInfo(transactionHistoryBean.getHash());
                        ICallback<TransactionInfoBean> iCallback = new ICallback<TransactionInfoBean>() {
                            @Override
                            public void onFailure(String str, String str2) {
                            }

                            @Override
                            public void onSubscribe(Disposable disposable) {
                            }

                            @Override
                            public void onResponse(String str, TransactionInfoBean transactionInfoBean) {
                                String contractRet = transactionInfoBean.getContractRet();
                                if (TransactionMessage.TYPE_SUCCESS.equals(contractRet) && !transactionInfoBean.isRevert()) {
                                    TransactionHistoryProvider.removeLocalTransaction(transactionHistoryBean.getHash());
                                    mRxManager.post(Event.PENDING_SUCCESS, transactionHistoryBean.getHash());
                                } else if ("" == contractRet || contractRet == null) {
                                } else {
                                    TransactionHistoryProvider.removeLocalTransaction(transactionHistoryBean.getHash());
                                    mRxManager.post(Event.PENDING_FAIL, transactionHistoryBean.getHash());
                                }
                            }
                        };
                        TokenDetailContentPresenter tokenDetailContentPresenter = TokenDetailContentPresenter.this;
                        transactionInfo.subscribe(new IObserver(iCallback, tokenDetailContentPresenter.getTabTitle(tokenDetailContentPresenter.index)));
                    }
                }
            }, 5000L, 5000L);
        }
    }

    @Override
    public void setFilterSmallValue(boolean z) {
        this.isFilterSmalling = true;
        this.contentAdapter.notifyData(this.list, this.allTotal, this.tokenType, this.index);
        refresh();
    }

    @Override
    public void init() {
        this.tokenType = ((TokenDetailContentContract.View) this.mView).getTokenType();
        this.mToken = ((TokenDetailContentContract.View) this.mView).getToken();
        this.selectedWallet = WalletUtils.getSelectedWallet();
        this.index = ((TokenDetailContentContract.View) this.mView).getIndex();
        try {
            this.diskLruCacheHelper = new DiskLruCacheHelper(((TokenDetailContentContract.View) this.mView).getIContext());
            getLocalData();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (this.mRxManager == null) {
            this.mRxManager = new RxManager();
        }
        this.isMapping = ((TokenDetailContentContract.View) this.mView).getIsMapping();
        this.isMainChain = SpAPI.THIS.getCurrentChain().isMainChain;
        this.selectedWallet = WalletUtils.getSelectedWallet();
        List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
        this.allChainJson = allChainJson;
        if (allChainJson != null && allChainJson.size() > 0) {
            for (int i = 0; i < this.allChainJson.size(); i++) {
                if (this.allChainJson.get(i).isMainChain) {
                    this.mainChain = this.allChainJson.get(i);
                } else {
                    this.sideChain = this.allChainJson.get(i);
                }
            }
        }
        this.mComparator = new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return TokenDetailContentPresenter.lambda$init$3((TransactionHistoryBean) obj, (TransactionHistoryBean) obj2);
            }
        };
    }

    public static int lambda$init$3(TransactionHistoryBean transactionHistoryBean, TransactionHistoryBean transactionHistoryBean2) {
        return (int) (transactionHistoryBean2.block_timestamp - transactionHistoryBean.block_timestamp);
    }

    public void refresh() {
        if (!TronConfig.hasNet) {
            List<TransactionHistoryBean> list = this.list;
            if (list == null || list.size() == 0) {
                ((TokenDetailContentContract.View) this.mView).showNoNetError();
            } else {
                ((TokenDetailContentContract.View) this.mView).showTokenDetailView();
            }
            IToast.getIToast().show(R.string.time_out);
            return;
        }
        this.isPullRefresh = true;
        this.startIndex = 0;
        startRefresh();
        List<TransactionHistoryBean> list2 = this.list;
        if (list2 != null && list2.size() > 0) {
            if (((TokenDetailContentContract.View) this.mView).getHolderView().getVisibility() == 0) {
                ((TokenDetailContentContract.View) this.mView).showPlaceHolderView();
            } else if (((TokenDetailContentContract.View) this.mView).getNoNetView().getVisibility() == 0) {
                ((TokenDetailContentContract.View) this.mView).showNoNetError();
            } else if (((TokenDetailContentContract.View) this.mView).getRecycleView().getVisibility() == 8) {
                ((TokenDetailContentContract.View) this.mView).showTokenDetailView();
            }
        }
        getData();
    }

    private void initRecycleView() {
        LoadMoreRecyclerView recycleView = ((TokenDetailContentContract.View) this.mView).getRecycleView();
        recycleView.setLayoutManager(new WrapContentLinearLayoutManager(((TokenDetailContentContract.View) this.mView).getIContext(), 1, false));
        if (this.contentAdapter == null) {
            this.contentAdapter = new TokenDetailContentAdapter(this.mToken, this.list, (HashMap) this.contractMap, this.allTotal, this.tokenType, ((TokenDetailContentContract.View) this.mView).getIContext(), this.index);
            if (((TokenDetailContentContract.View) this.mView).getRecycleView() != null) {
                ((TokenDetailContentContract.View) this.mView).getRecycleView().setLayoutManager(new LinearLayoutManager(((TokenDetailContentContract.View) this.mView).getIContext(), 1, false));
                ((TokenDetailContentContract.View) this.mView).getRecycleView().addItemDecoration(new RecycleViewDivider(((TokenDetailContentContract.View) this.mView).getIContext(), 1, UIUtils.dip2px(1.0f), ((TokenDetailContentContract.View) this.mView).getIContext().getResources().getColor(R.color.gray_eef3fd), UIUtils.dip2px(15.0f)));
                ((TokenDetailContentContract.View) this.mView).getRecycleView().setAdapter(this.contentAdapter);
            }
        }
        this.contentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recycleView);
        this.contentAdapter.setPreLoadNumber(10);
        List<TransactionHistoryBean> list = this.list;
        if (list != null && list.size() > 0) {
            ((TokenDetailContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((TokenDetailContentContract.View) this.mView).getRecycleView().setVisibility(View.VISIBLE);
            ((TokenDetailContentContract.View) this.mView).getEmptyView().setVisibility(View.GONE);
        }
        this.contentAdapter.setmItemClickListener(new TokenDetailContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                TransactionHistoryBean transactionHistoryBean = (TransactionHistoryBean) list.get(i);
                if (transactionHistoryBean.getConfirmed() == -1) {
                    IToast.getIToast().show(R.string.transaction_confirm);
                } else {
                    TransactionDetailActivity.start(((TokenDetailContentContract.View) mView).getIContext(), transactionHistoryBean, mToken, ((TextView) view.findViewById(R.id.tv_count)).getText().toString());
                }
            }
        });
    }

    private void updateUI() {
        List<TransactionHistoryBean> list = this.list;
        if (list != null && list.size() != 0) {
            ((TokenDetailContentContract.View) this.mView).showTokenDetailView();
        } else if (this.isLoadingLocalData) {
            if (!TronConfig.hasNet) {
                ((TokenDetailContentContract.View) this.mView).getRecycleView().setVisibility(View.GONE);
                ((TokenDetailContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
                ((TokenDetailContentContract.View) this.mView).showNoNetError();
            } else {
                ((TokenDetailContentContract.View) this.mView).showPlaceHolderView();
            }
        } else {
            ((TokenDetailContentContract.View) this.mView).showEmptyView();
        }
        TokenDetailContentAdapter tokenDetailContentAdapter = this.contentAdapter;
        if (tokenDetailContentAdapter == null) {
            this.contentAdapter = new TokenDetailContentAdapter(this.mToken, this.list, (HashMap) this.contractMap, this.allTotal, this.tokenType, ((TokenDetailContentContract.View) this.mView).getIContext(), this.index);
            if (((TokenDetailContentContract.View) this.mView).getRecycleView() != null) {
                ((TokenDetailContentContract.View) this.mView).getRecycleView().setLayoutManager(new LinearLayoutManager(((TokenDetailContentContract.View) this.mView).getIContext(), 1, false));
                ((TokenDetailContentContract.View) this.mView).getRecycleView().addItemDecoration(new RecycleViewDivider(((TokenDetailContentContract.View) this.mView).getIContext(), 1, UIUtils.dip2px(1.0f), ((TokenDetailContentContract.View) this.mView).getIContext().getResources().getColor(R.color.gray_F0F2F3)), UIUtils.dip2px(20.0f));
                ((TokenDetailContentContract.View) this.mView).getRecycleView().setAdapter(this.contentAdapter);
                return;
            }
            throw new NullPointerException("RecycleView can not be null");
        }
        tokenDetailContentAdapter.notifyData(this.list, this.allTotal, this.tokenType, this.index);
    }

    public void loadMore() {
        if (this.isRefreshing) {
            return;
        }
        int size = this.contentAdapter.getData().size();
        if (size == 0 || (this.startIndex < size && size >= 20)) {
            this.startIndex += this.limit;
            getData();
        } else if (this.contentAdapter != null) {
            List<TransactionHistoryBean> list = this.list;
            if (list != null && list.size() <= 5) {
                this.contentAdapter.loadMoreEnd(true);
                this.contentAdapter.loadMoreComplete();
                return;
            }
            this.contentAdapter.loadMoreEnd();
        }
    }

    @Override
    public String getCurrentAddress() {
        return this.selectedWallet.getAddress();
    }

    @Override
    public void getData() {
        List<TransactionHistoryBean> list;
        if (!TronConfig.hasNet && ((list = this.list) == null || list.size() == 0)) {
            ((TokenDetailContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((TokenDetailContentContract.View) this.mView).showNoNetError();
            return;
        }
        getTRXTransfer();
    }

    @Override
    public void getTRXTransfer() {
        try {
            if (this.tokenType == null) {
                init();
            }
            boolean filterSmallValue = SpAPI.THIS.getFilterSmallValue();
            TokenBean tokenBean = this.mToken;
            boolean z = ((tokenBean == null || !BigDecimalUtils.lessThanOrEqual(tokenBean.getUsdPrice(), BigDecimal.ZERO)) && !OfficialType.isScamOrUnSafeToken(this.mToken)) ? filterSmallValue : false;
            if (!TronConfig.hasNet && this.isFirstLoaded) {
                ((TokenDetailContentContract.View) this.mView).showNoNetError();
                this.isPullRefresh = false;
            } else if (M.M_TRX.equals(this.tokenType) && this.index != 3) {
                ((TokenDetailContentContract.Model) this.mModel).getTRXTransfer(this.selectedWallet.getAddress(), this.startIndex, this.limit, 0L, System.currentTimeMillis(), this.index, SpAPI.THIS.getCurIsMainChain(), true, z).subscribe(new IObserver(this, getTabTitle(this.index)));
            } else if (M.M_TRZ.equals(this.tokenType)) {
                this.selectedWallet.isShieldedWallet();
            } else if (M.M_TRC10.equals(this.tokenType) && this.index != 3) {
                if (this.selectedWallet.isShieldedWallet()) {
                    this.isPullRefresh = false;
                } else {
                    ((TokenDetailContentContract.Model) this.mModel).getTRX10Transfer(this.selectedWallet.getAddress(), this.startIndex, this.limit, 0L, System.currentTimeMillis(), this.index, true, this.mToken.getId(), z).subscribe(new IObserver(this, getTabTitle(this.index)));
                }
            } else if (!M.M_TRC20.equals(this.tokenType) || this.index == 3) {
            } else {
                ((TokenDetailContentContract.Model) this.mModel).getTRXTransfer20(this.selectedWallet.getAddress(), this.startIndex, this.limit, 0L, System.currentTimeMillis(), this.index, true, this.mToken.getContractAddress(), z).subscribe(new IObserver(this, getTabTitle(this.index)));
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    long getTokenId() {
        TokenBean tokenBean = this.mToken;
        if (tokenBean != null) {
            return Long.parseLong(tokenBean.getId().equals("") ? "0" : this.mToken.getId());
        }
        return 0L;
    }

    @Override
    public void addLocalHistoryToListAndShow() {
        List<TransactionHistoryBean> localTransaction = TransactionHistoryProvider.getLocalTransaction(this.list, this.tokenType, this.mToken, getTokenid_address(), this.selectedWallet.getAddress(), SpAPI.THIS.getFilterSmallValue());
        this.list = localTransaction;
        TokenDetailContentAdapter tokenDetailContentAdapter = this.contentAdapter;
        if (tokenDetailContentAdapter != null) {
            tokenDetailContentAdapter.notifyData(localTransaction, this.allTotal, this.tokenType, this.index);
        }
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
        if (this.timer != null || ((TokenDetailContentContract.View) this.mView).isIDestroyed()) {
            return;
        }
        Timer timer2 = new Timer();
        this.timer = timer2;
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ((LinearLayoutManager) ((TokenDetailContentContract.View) mView).getRecycleView().getLayoutManager()).findFirstVisibleItemPosition();
                    if (((LinearLayoutManager) ((TokenDetailContentContract.View) mView).getRecycleView().getLayoutManager()).findLastVisibleItemPosition() < 10) {
                        startIndex = 0;
                        getTRXTransfer();
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }, 5000L, 3000L);
    }

    private void getDepositOrWithdrawList() {
        String str;
        boolean z = this.isMainChain;
        String str2 = z ? "deposit" : "withdraw";
        ChainBean chainBean = this.sideChain;
        if (chainBean != null) {
            String str3 = z ? chainBean.mainChainContract : chainBean.sideChainContract;
            if (M.M_TRC10.equals(this.tokenType)) {
                str = this.mToken.getId();
            } else if (M.M_TRC20.equals(this.tokenType)) {
                str = this.isMainChain ? this.mToken.getContractAddress() : this.mToken.getMaincontractAddress();
            } else {
                str = "_";
            }
            ((TokenDetailContentContract.Model) this.mModel).getWithdrawOrDeposit(str, this.selectedWallet.getAddress(), str3, this.startIndex, this.limit, str2).subscribe(new IObserver(this, "DEPOSITORWITHDRAW"));
        }
    }

    @Override
    public void onResponse(String str, TransferOutput transferOutput) {
        this.isPullRefresh = false;
        if (this.isRefreshing) {
            this.isRefreshing = false;
        }
        if (transferOutput == null || transferOutput.data == null) {
            onFailure(str, "");
            return;
        }
        this.allTotal = transferOutput.total;
        if (!SpAPI.THIS.getCurIsMainChain() && transferOutput != null && transferOutput.data != null) {
            for (int i = 0; i < transferOutput.data.size(); i++) {
                TransactionHistoryBean transactionHistoryBean = transferOutput.data.get(i);
                if (StringTronUtil.isEmpty(transactionHistoryBean.getContract_type())) {
                    if (M.M_TRX.equals(this.tokenType)) {
                        transactionHistoryBean.setContract_type("TransferContract");
                    } else if (M.M_TRC10.equals(this.tokenType)) {
                        transactionHistoryBean.setContract_type("TransferAssetContract");
                    } else if (M.M_TRC20.equals(this.tokenType)) {
                        transactionHistoryBean.setContract_type("TriggerSmartContract");
                        transactionHistoryBean.setEvent_type("Transfer");
                    }
                }
            }
        }
        if (!this.isLoadingLocalData) {
            if (this.isFirstLoaded) {
                this.isFirstLoaded = false;
            }
            if (this.allTotal != 0) {
                ((TokenDetailContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            }
        } else if (this.hasData) {
            ((TokenDetailContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
        }
        if (this.isFilterSmalling) {
            if (((TokenDetailContentContract.View) this.mView).getVisible()) {
                this.mRxManager.post(Event.FILTER_SMALL_VALUE, 11);
            } else {
                this.mRxManager.post(Event.FILTER_SMALL_VALUE, 1);
            }
            this.isFilterSmalling = false;
        }
        if (this.startIndex != 0) {
            if (transferOutput.getData().isEmpty()) {
                List<TransactionHistoryBean> list = this.list;
                if (list != null && list.size() <= 5) {
                    this.contentAdapter.loadMoreComplete();
                    this.contentAdapter.loadMoreEnd(true);
                    return;
                }
                this.contentAdapter.loadMoreEnd();
                return;
            }
            this.list.addAll(transferOutput.getData());
            if (transferOutput.getData().size() == this.limit) {
                this.contentAdapter.loadMoreComplete();
                this.contentAdapter.loadMoreEnd();
            } else {
                List<TransactionHistoryBean> list2 = this.list;
                if (list2 != null && list2.size() <= 5) {
                    this.contentAdapter.loadMoreComplete();
                    this.contentAdapter.loadMoreEnd(true);
                } else {
                    this.contentAdapter.loadMoreEnd();
                }
            }
        } else {
            this.list = transferOutput.getData();
            HashMap<String, Boolean> contractMap = transferOutput.getContractMap();
            this.contractMap = contractMap;
            this.contentAdapter.setContractMap(contractMap);
            if (!this.isLoadingLocalData) {
                addToDICK_CACHE(str, transferOutput);
            }
            int i2 = this.index;
            if (i2 == 0 || i2 == 1) {
                this.list = TransactionHistoryProvider.getLocalTransaction(this.list, this.tokenType, this.mToken, getTokenid_address(), this.selectedWallet.getAddress(), SpAPI.THIS.getFilterSmallValue());
            }
            if (transferOutput.getData().size() == this.limit) {
                this.contentAdapter.setEnableLoadMore(true);
                this.contentAdapter.loadMoreComplete();
            } else {
                List<TransactionHistoryBean> list3 = this.list;
                if (list3 != null && list3.size() <= 5) {
                    this.contentAdapter.loadMoreEnd(true);
                    this.contentAdapter.loadMoreComplete();
                } else {
                    this.contentAdapter.loadMoreEnd();
                }
            }
        }
        List<TransactionHistoryBean> list4 = this.list;
        if (list4 != null && list4.size() > 0) {
            for (int i3 = 0; i3 < this.list.size(); i3++) {
                if (this.list.get(i3).getDecimals() == 0 && this.mToken != null) {
                    this.list.get(i3).setDecimals(this.mToken.getPrecision());
                }
            }
        }
        updateUI();
        if (this.isLoadingLocalData) {
            this.isLoadingLocalData = false;
        } else {
            hideRefresh();
        }
        this.contentAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void onFailure(String str, String str2) {
        hideRefresh();
        if (this.isPullRefresh) {
            this.isPullRefresh = false;
            IToast.getIToast().show(R.string.time_out);
        }
        if (this.isFirstLoaded) {
            this.isFirstLoaded = false;
        }
        int i = this.startIndex;
        if (i == 0) {
            if (((TokenDetailContentContract.View) this.mView).getHolderView().getVisibility() == 0) {
                ((TokenDetailContentContract.View) this.mView).showNoNetError();
            } else if (((TokenDetailContentContract.View) this.mView).getRecycleView().getVisibility() != 0) {
                ((TokenDetailContentContract.View) this.mView).showNoNetError();
            }
        } else {
            this.startIndex = i - this.limit;
            this.contentAdapter.loadMoreFail();
            ((TokenDetailContentContract.View) this.mView).showNoNetError();
        }
        if (this.isFilterSmalling) {
            ((TokenDetailContentContract.View) this.mView).showNoNetError();
            this.mRxManager.post(Event.FILTER_SMALL_VALUE, -1);
            this.isFilterSmalling = false;
        }
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mRxManager.add(disposable);
    }

    private void hideRefresh() {
        this.isRefreshing = false;
        ((TokenDetailContentContract.View) this.mView).hideRefresh();
    }

    public void addToDICK_CACHE(String str, TransferOutput transferOutput) {
        if (transferOutput != null) {
            try {
                if (transferOutput.getData() == null || transferOutput.getData().size() == 0) {
                    return;
                }
                int i = this.index;
                if (i == 0) {
                    if (!str.equals("ALLTRANSFER")) {
                        return;
                    }
                } else if (i == 1) {
                    if (!str.equals("SENTTRANSFER")) {
                        return;
                    }
                } else if (i == 2) {
                    if (!str.equals("RECEIVETRANSFER")) {
                        return;
                    }
                } else if (i == 3 && !str.equals("DEPOSITORWITHDRAW")) {
                    return;
                }
                this.diskLruCacheHelper.put(getDICK_KEY(str), GsonUtils.toGsonString(transferOutput));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public String getDICK_KEY(String str) {
        boolean filterSmallValue = SpAPI.THIS.getFilterSmallValue();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String tokenid_address = getTokenid_address();
            stringBuffer.append(IRequest.getTronLinkHost()).append("&&").append(SpAPI.THIS.getCurrentChainName()).append("&&").append(this.selectedWallet.getAddress()).append("&&").append(CustomTokenStatus.TRANSFER).append("&&").append(str).append("&&").append(StringTronUtil.isEmpty(tokenid_address) ? "TRX" : tokenid_address).append("&&").append(filterSmallValue);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return stringBuffer.toString();
    }

    public String getTokenid_address() {
        if (M.M_TRC20.equals(this.tokenType)) {
            return this.mToken.getContractAddress();
        }
        return M.M_TRC10.equals(this.tokenType) ? this.mToken.getId() : "";
    }

    private void changeData(List<TransactionHistoryBean> list) {
        long shieldFee = SpAPI.THIS.getShieldFee();
        for (int i = 0; i < list.size(); i++) {
            TransactionHistoryBean transactionHistoryBean = list.get(i);
            if (transactionHistoryBean.getDirection() == 1 && transactionHistoryBean.getContract_type() != null && transactionHistoryBean.getContract_type().equals("ShieldedTransferContract")) {
                list.get(i).amount = (Long.parseLong(transactionHistoryBean.getAmount()) - shieldFee) + "";
            }
        }
    }

    @Override
    public void getLocalData() {
        if (this.diskLruCacheHelper == null) {
            return;
        }
        ((TokenDetailContentContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getLocalData$5();
            }
        });
    }

    public void lambda$getLocalData$5() {
        final TransferOutput transferOutput;
        try {
            int i = this.index;
            final String str = i == 0 ? "ALLTRANSFER" : i == 1 ? "SENTTRANSFER" : i == 2 ? "RECEIVETRANSFER" : i == 3 ? "DEPOSITORWITHDRAW" : "";
            this.isLoadingLocalData = true;
            String asString = this.diskLruCacheHelper.getAsString(getDICK_KEY(str));
            if (asString != null && asString.length() > 0) {
                transferOutput = (TransferOutput) GsonUtils.gsonToBean(asString, TransferOutput.class);
                if (transferOutput == null || transferOutput.getData() == null || transferOutput.getData().size() == 0) {
                    transferOutput = new TransferOutput();
                    transferOutput.setData(new ArrayList());
                } else {
                    this.hasData = true;
                }
            } else {
                transferOutput = new TransferOutput();
                transferOutput.setData(new ArrayList());
            }
            ((TokenDetailContentContract.View) this.mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$getLocalData$4(str, transferOutput);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mRxManager != null) {
            this.mRxManager.clear();
        }
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
    }
}
