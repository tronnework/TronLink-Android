package com.tron.wallet.business.nft.contract;

import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arasthel.asyncjob.AsyncJob;
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
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.bean.nft.NftTransferOutput;
import com.tron.wallet.business.customtokens.bean.CustomTokenStatus;
import com.tron.wallet.business.nft.NftHistoryActivity;
import com.tron.wallet.business.nft.NftHistoryContentAdapter;
import com.tron.wallet.business.nft.contract.NftHistoryContentContract;
import com.tron.wallet.business.nft.contract.NftHistoryContentPresenter;
import com.tron.wallet.business.nft.dao.NftTokenBeanController;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionDetailActivity;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.NftTransactionHistoryProvider;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;
import org.tron.walletserver.Wallet;
public class NftHistoryContentPresenter extends NftHistoryContentContract.Presenter implements ICallback<NftTransferOutput> {
    private List<ChainBean> allChainJson;
    private int allTotal;
    private NftHistoryContentAdapter contentAdapter;
    String contractAddress;
    private DiskLruCacheHelper diskLruCacheHelper;
    private boolean hasData;
    private int index;
    private boolean isLoadingLocalData;
    private boolean isMainChain;
    private boolean isRefreshing;
    private Comparator<? super TransactionHistoryBean> mComparator;
    private TokenBean mToken;
    private ChainBean mainChain;
    private Wallet selectedWallet;
    private ChainBean sideChain;
    private int startIndex;
    private Timer timer;
    private String tokenType;
    private final String ALLTRANSFER = "ALLTRANSFER_NFT";
    private final String SENTTRANSFER = "SENTTRANSFER_NFT";
    private final String RECEIVETRANSFER = "RECEIVETRANSFER_NFT";
    private boolean hasPending = false;
    private List<NftTransferOutput.NftTransfer> list = new ArrayList();
    private int limit = 20;
    private boolean isPullRefresh = false;
    private boolean canRefresh = true;
    private String typeStr = "tokenAddress";
    private boolean isFirstLoaded = true;

    private void startRefresh() {
        this.isRefreshing = true;
    }

    public String getTabTitle(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : "RECEIVETRANSFER_NFT" : "SENTTRANSFER_NFT" : "ALLTRANSFER_NFT";
    }

    public String getTokenid_address() {
        return this.contractAddress;
    }

    @Override
    void netWorkChange() {
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override
    protected void onStart() {
    }

    public void setmToken(TokenBean tokenBean) {
        this.mToken = tokenBean;
    }

    @Override
    public void addSome() {
        init();
        initRecycleView();
    }

    @Override
    public void init() {
        this.tokenType = ((NftHistoryContentContract.View) this.mView).getTokenType();
        this.contractAddress = ((NftHistoryContentContract.View) this.mView).getContractAddress();
        this.mToken = ((NftHistoryContentContract.View) this.mView).getToken();
        this.selectedWallet = WalletUtils.getSelectedWallet();
        this.index = ((NftHistoryContentContract.View) this.mView).getIndex();
        try {
            this.diskLruCacheHelper = new DiskLruCacheHelper(((NftHistoryContentContract.View) this.mView).getIContext());
            getLocalData();
        } catch (Exception e) {
            LogUtils.e(e);
        }
        if (this.mRxManager == null) {
            this.mRxManager = new RxManager();
        }
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
                return NftHistoryContentPresenter.lambda$init$0((TransactionHistoryBean) obj, (TransactionHistoryBean) obj2);
            }
        };
        Intent iIntent = ((NftHistoryActivity) ((NftHistoryContentContract.View) this.mView).getIContext()).getIIntent();
        if (iIntent == null || iIntent.getIntExtra("from", 0) != 1) {
            return;
        }
        addLocalHistoryToListAndShow();
    }

    public static int lambda$init$0(TransactionHistoryBean transactionHistoryBean, TransactionHistoryBean transactionHistoryBean2) {
        return (int) (transactionHistoryBean2.block_timestamp - transactionHistoryBean.block_timestamp);
    }

    public void refresh() {
        if (!TronConfig.hasNet) {
            List<NftTransferOutput.NftTransfer> list = this.list;
            if (list == null || list.size() == 0) {
                ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
                ((NftHistoryContentContract.View) this.mView).showNoNetError(true);
            } else {
                ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setVisibility(View.VISIBLE);
            }
            IToast.getIToast().show(R.string.time_out);
            return;
        }
        this.isPullRefresh = true;
        this.startIndex = 0;
        startRefresh();
        List<NftTransferOutput.NftTransfer> list2 = this.list;
        if (list2 != null && list2.size() > 0) {
            if (((NftHistoryContentContract.View) this.mView).getHolderView().getVisibility() == 0) {
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setVisibility(View.GONE);
            }
            if (((NftHistoryContentContract.View) this.mView).getRecycleView().getVisibility() == 8) {
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setVisibility(View.VISIBLE);
            }
        }
        getData();
    }

    private void initRecycleView() {
        final LoadMoreRecyclerView recycleView = ((NftHistoryContentContract.View) this.mView).getRecycleView();
        recycleView.setLayoutManager(new WrapContentLinearLayoutManager(((NftHistoryContentContract.View) this.mView).getIContext(), 1, false));
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (hasPending && i == 0) {
                    ((NftHistoryContentContract.View) mView).runOnUIThread(new OnMainThread() {
                        @Override
                        public void doInUIThread() {
                            updateUI();
                        }
                    });
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (!recycleView.canScrollVertically(-1)) {
                    canRefresh = true;
                    ((NftHistoryContentContract.View) mView).setCanRefresh(canRefresh);
                    return;
                }
                canRefresh = false;
                ((NftHistoryContentContract.View) mView).setCanRefresh(canRefresh);
            }
        });
        if (this.contentAdapter == null) {
            this.contentAdapter = new NftHistoryContentAdapter(this.mToken, this.list, this.allTotal, this.tokenType, ((NftHistoryContentContract.View) this.mView).getIContext(), this.index);
            if (((NftHistoryContentContract.View) this.mView).getRecycleView() != null) {
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setLayoutManager(new LinearLayoutManager(((NftHistoryContentContract.View) this.mView).getIContext(), 1, false));
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setAdapter(this.contentAdapter);
            }
        }
        this.contentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recycleView);
        List<NftTransferOutput.NftTransfer> list = this.list;
        if (list != null && list.size() > 0) {
            ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((NftHistoryContentContract.View) this.mView).getRecycleView().setVisibility(View.VISIBLE);
            ((NftHistoryContentContract.View) this.mView).getEmptyView().setVisibility(View.GONE);
        }
        this.contentAdapter.setmItemClickListener(new fun3());
    }

    public class fun3 implements NftHistoryContentAdapter.OnItemClickListener {
        fun3() {
        }

        @Override
        public void onItemClick(View view, int i) {
            AnalyticsHelper.logEvent(AnalyticsHelper.TokenDetailsPage.CLICK_NFT_HISTORY_PAGE_ITEM);
            final NftTransferOutput.NftTransfer nftTransfer = (NftTransferOutput.NftTransfer) list.get(i);
            if (nftTransfer.getNftConfirm() == -1) {
                IToast.getIToast().show(R.string.transaction_confirm);
                return;
            }
            final TransactionHistoryBean transactionHistoryBean = new TransactionHistoryBean();
            transactionHistoryBean.block_timestamp = nftTransfer.getBlock_ts();
            transactionHistoryBean.from = nftTransfer.getFrom_address();
            transactionHistoryBean.hash = nftTransfer.getTransaction_id();
            transactionHistoryBean.setContract_ret(nftTransfer.getContractRet());
            transactionHistoryBean.timestamp = nftTransfer.getBlock_ts();
            transactionHistoryBean.to = nftTransfer.getTo_address();
            transactionHistoryBean.transactionHash = nftTransfer.getTransaction_id();
            if (selectedWallet.getAddress().equals(nftTransfer.getFrom_address())) {
                transactionHistoryBean.setMark(HelpFormatter.DEFAULT_OPT_PREFIX);
            } else if (selectedWallet.getAddress().equals(nftTransfer.getTo_address())) {
                transactionHistoryBean.setMark(Marker.ANY_NON_NULL_MARKER);
            }
            transactionHistoryBean.setEvent_type(nftTransfer.getEvent_type());
            transactionHistoryBean.setRevert(nftTransfer.getRevert().booleanValue() ? 1 : 0);
            ((NftHistoryContentContract.View) mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    NftHistoryContentPresenter.3.this.lambda$onItemClick$1(nftTransfer, transactionHistoryBean);
                }
            });
        }

        public void lambda$onItemClick$1(NftTransferOutput.NftTransfer nftTransfer, final TransactionHistoryBean transactionHistoryBean) {
            NftTokenBean nFTToken = NftTokenBeanController.getInstance().getNFTToken(nftTransfer.getContract_address());
            final TokenBean convertToTokenBean = nFTToken != null ? nFTToken.convertToTokenBean() : null;
            if (convertToTokenBean == null) {
                convertToTokenBean = new TokenBean();
            }
            convertToTokenBean.type = 5;
            if (mToken != null) {
                convertToTokenBean.setTokenStatus(mToken.getTokenStatus());
                convertToTokenBean.name = mToken.getName();
                convertToTokenBean.shortName = mToken.getShortName();
            }
            convertToTokenBean.logoUrl = nftTransfer.getTokenInfo().getTokenLogo();
            convertToTokenBean.contractAddress = nftTransfer.getContract_address();
            convertToTokenBean.id = nftTransfer.getQuant();
            ((NftHistoryContentContract.View) mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    NftHistoryContentPresenter.3.this.lambda$onItemClick$0(transactionHistoryBean, convertToTokenBean);
                }
            });
        }

        public void lambda$onItemClick$0(TransactionHistoryBean transactionHistoryBean, TokenBean tokenBean) {
            NftTransactionDetailActivity.start(((NftHistoryContentContract.View) mView).getIContext(), transactionHistoryBean, tokenBean);
        }
    }

    public void updateUI() {
        List<NftTransferOutput.NftTransfer> list = this.list;
        if (list != null && list.size() != 0) {
            ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((NftHistoryContentContract.View) this.mView).getRecycleView().setVisibility(View.VISIBLE);
            ((NftHistoryContentContract.View) this.mView).getEmptyView().setVisibility(View.GONE);
            ((NftHistoryContentContract.View) this.mView).showNoNetError(false);
        } else if (this.isLoadingLocalData) {
            if (!TronConfig.hasNet) {
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setVisibility(View.GONE);
                ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
                ((NftHistoryContentContract.View) this.mView).showNoNetError(true);
            } else {
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setVisibility(View.GONE);
                ((NftHistoryContentContract.View) this.mView).showNoNetError(false);
                ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.VISIBLE);
            }
        } else {
            ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((NftHistoryContentContract.View) this.mView).getEmptyView().setVisibility(View.VISIBLE);
            ((NftHistoryContentContract.View) this.mView).showNoNetError(false);
        }
        NftHistoryContentAdapter nftHistoryContentAdapter = this.contentAdapter;
        if (nftHistoryContentAdapter == null) {
            this.contentAdapter = new NftHistoryContentAdapter(this.mToken, this.list, this.allTotal, this.tokenType, ((NftHistoryContentContract.View) this.mView).getIContext(), this.index);
            if (((NftHistoryContentContract.View) this.mView).getRecycleView() != null) {
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setLayoutManager(new LinearLayoutManager(((NftHistoryContentContract.View) this.mView).getIContext(), 1, false));
                ((NftHistoryContentContract.View) this.mView).getRecycleView().setAdapter(this.contentAdapter);
                return;
            }
            throw new NullPointerException("RecycleView can not be null");
        }
        nftHistoryContentAdapter.notifyData(this.list, this.allTotal, this.tokenType, this.index);
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
            List<NftTransferOutput.NftTransfer> list = this.list;
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
        List<NftTransferOutput.NftTransfer> list;
        if (!TronConfig.hasNet && ((list = this.list) == null || list.size() == 0)) {
            ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((NftHistoryContentContract.View) this.mView).showNoNetError(true);
            return;
        }
        getNftTransfer();
    }

    @Override
    public void getNftTransfer() {
        try {
            if (!TronConfig.hasNet && this.isFirstLoaded) {
                ((NftHistoryContentContract.View) this.mView).showNoNetError(true);
                this.isPullRefresh = false;
                return;
            }
            int i = this.index;
            String str = "0";
            if (i != 0) {
                if (i == 1) {
                    str = "1";
                } else if (i == 2) {
                    str = "2";
                }
            }
            ((NftHistoryContentContract.Model) this.mModel).getCollectionTransferList(this.selectedWallet.getAddress(), this.selectedWallet.getAddress(), this.contractAddress, this.startIndex, this.limit, str).subscribe(new IObserver(this, getTabTitle(this.index)));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void getShieldBlockData() {
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public final void doOnBackground() {
                lambda$getShieldBlockData$2();
            }
        });
    }

    public void lambda$getShieldBlockData$2() {
        this.list = new ArrayList();
        AccountUpdater.singleShot(0L);
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                lambda$getShieldBlockData$1();
            }
        });
    }

    public void lambda$getShieldBlockData$1() {
        NftHistoryContentAdapter nftHistoryContentAdapter = this.contentAdapter;
        List<NftTransferOutput.NftTransfer> list = this.list;
        nftHistoryContentAdapter.notifyData(list, (list == null || list.isEmpty()) ? 0 : this.list.size(), this.tokenType, this.index);
        hideRefresh();
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
        this.list = NftTransactionHistoryProvider.getLocalTransaction(this.list, this.tokenType, getTokenid_address(), this.selectedWallet.getAddress());
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
        if (this.timer != null || ((NftHistoryContentContract.View) this.mView).isIDestroyed()) {
            return;
        }
        Timer timer2 = new Timer();
        this.timer = timer2;
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ((LinearLayoutManager) ((NftHistoryContentContract.View) mView).getRecycleView().getLayoutManager()).findFirstVisibleItemPosition();
                    if (((LinearLayoutManager) ((NftHistoryContentContract.View) mView).getRecycleView().getLayoutManager()).findLastVisibleItemPosition() < 10) {
                        startIndex = 0;
                        getNftTransfer();
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }, 5000L, 3000L);
    }

    @Override
    public void onResponse(String str, NftTransferOutput nftTransferOutput) {
        this.isPullRefresh = false;
        if (nftTransferOutput == null || nftTransferOutput.getToken_transfers() == null) {
            if (this.isRefreshing) {
                this.isRefreshing = false;
            }
            onFailure(str, "");
            return;
        }
        int size = this.allTotal + nftTransferOutput.getToken_transfers().size();
        this.allTotal = size;
        if (!this.isLoadingLocalData) {
            if (this.isFirstLoaded) {
                this.isFirstLoaded = false;
            }
            if (size != 0) {
                ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            }
        } else if (this.hasData) {
            ((NftHistoryContentContract.View) this.mView).getHolderView().setVisibility(View.GONE);
        }
        if (this.startIndex != 0) {
            if (nftTransferOutput.getToken_transfers().isEmpty()) {
                List<NftTransferOutput.NftTransfer> list = this.list;
                if (list != null && list.size() <= 5) {
                    this.contentAdapter.loadMoreEnd(true);
                    this.contentAdapter.loadMoreComplete();
                    return;
                }
                this.contentAdapter.loadMoreEnd();
                return;
            }
            this.list.addAll(nftTransferOutput.getToken_transfers());
            if (nftTransferOutput.getToken_transfers().size() == this.limit) {
                this.contentAdapter.loadMoreComplete();
            } else {
                List<NftTransferOutput.NftTransfer> list2 = this.list;
                if (list2 != null && list2.size() <= 5) {
                    this.contentAdapter.loadMoreEnd(true);
                    this.contentAdapter.loadMoreComplete();
                } else {
                    this.contentAdapter.loadMoreEnd();
                }
            }
        } else {
            this.list = nftTransferOutput.getToken_transfers();
            if (!this.isLoadingLocalData) {
                addToDICK_CACHE(str, nftTransferOutput);
            }
            int i = this.index;
            if (i == 0 || i == 1) {
                this.list = NftTransactionHistoryProvider.getLocalTransaction(this.list, this.tokenType, getTokenid_address(), this.selectedWallet.getAddress());
            }
            if (nftTransferOutput.getToken_transfers().size() == this.limit) {
                this.contentAdapter.setEnableLoadMore(true);
                this.contentAdapter.loadMoreComplete();
            } else {
                List<NftTransferOutput.NftTransfer> list3 = this.list;
                if (list3 != null && list3.size() <= 5) {
                    this.contentAdapter.loadMoreEnd(true);
                    this.contentAdapter.loadMoreComplete();
                    this.contentAdapter.loadMoreComplete();
                } else {
                    this.contentAdapter.loadMoreEnd();
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
            if (((NftHistoryContentContract.View) this.mView).getHolderView().getVisibility() == 0) {
                ((NftHistoryContentContract.View) this.mView).showNoNetError(true);
                return;
            } else if (((NftHistoryContentContract.View) this.mView).getRecycleView().getVisibility() == 0) {
                return;
            } else {
                ((NftHistoryContentContract.View) this.mView).showEmptyView(false);
                ((NftHistoryContentContract.View) this.mView).showNoNetError(true);
                return;
            }
        }
        this.startIndex = i - this.limit;
        this.contentAdapter.loadMoreFail();
        ((NftHistoryContentContract.View) this.mView).showNoNetError(true);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mRxManager.add(disposable);
    }

    private void hideRefresh() {
        this.isRefreshing = false;
        ((NftHistoryContentContract.View) this.mView).hideRefresh();
    }

    public void addToDICK_CACHE(String str, NftTransferOutput nftTransferOutput) {
        if (nftTransferOutput != null) {
            try {
                if (nftTransferOutput.getToken_transfers() == null || nftTransferOutput.getToken_transfers().size() == 0) {
                    return;
                }
                int i = this.index;
                if (i == 0) {
                    if (!str.equals("ALLTRANSFER_NFT")) {
                        return;
                    }
                } else if (i == 1) {
                    if (!str.equals("SENTTRANSFER_NFT")) {
                        return;
                    }
                } else if (i == 2 && !str.equals("RECEIVETRANSFER_NFT")) {
                    return;
                }
                this.diskLruCacheHelper.put(getDICK_KEY(str), GsonUtils.toGsonString(nftTransferOutput));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    public String getDICK_KEY(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String tokenid_address = getTokenid_address();
            stringBuffer.append(IRequest.getTronLinkHost()).append("&&").append(SpAPI.THIS.getCurrentChainName()).append("&&").append(this.selectedWallet.getAddress()).append("&&").append(CustomTokenStatus.TRANSFER).append("&&").append(str).append("&&").append(StringTronUtil.isEmpty(tokenid_address) ? "TRX" : tokenid_address);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return stringBuffer.toString();
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
        try {
            ((NftHistoryContentContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$getLocalData$4();
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$getLocalData$4() {
        int i = this.index;
        final String str = i == 0 ? "ALLTRANSFER_NFT" : i == 1 ? "SENTTRANSFER_NFT" : i == 2 ? "RECEIVETRANSFER_NFT" : "";
        this.isLoadingLocalData = true;
        final NftTransferOutput nftTransferOutput = (NftTransferOutput) GsonUtils.gsonToBean(this.diskLruCacheHelper.getAsString(getDICK_KEY(str)), NftTransferOutput.class);
        if (nftTransferOutput == null || nftTransferOutput.getToken_transfers() == null || nftTransferOutput.getToken_transfers().size() == 0) {
            nftTransferOutput = new NftTransferOutput();
            nftTransferOutput.setToken_transfers(new ArrayList());
        } else {
            this.hasData = true;
        }
        ((NftHistoryContentContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$getLocalData$3(str, nftTransferOutput);
            }
        });
    }

    @Override
    public void setCanRefresh() {
        ((NftHistoryContentContract.View) this.mView).setCanRefresh(this.canRefresh);
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
