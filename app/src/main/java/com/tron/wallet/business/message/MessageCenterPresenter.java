package com.tron.wallet.business.message;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.MyAssetsController;
import com.tron.wallet.business.message.MessageCenterContract;
import com.tron.wallet.business.message.MessageCenterPresenter;
import com.tron.wallet.business.message.adapter.MessageCenterAdapter;
import com.tron.wallet.business.message.db.TransactionMessageManager;
import com.tron.wallet.business.nft.dao.NftTokenBeanController;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionDetailActivity;
import com.tron.wallet.business.transfer.TransactionDetailActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.SpAPI;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;
public class MessageCenterPresenter extends MessageCenterContract.Presenter {
    private static final String TAG = "MessageCenterPresenter";
    private MessageCenterAdapter adapter;
    TransactionMessageManager.Observer newMessageObserver;
    private List<TransactionMessage> list = new ArrayList();
    private int limit = 20;

    @Override
    protected void onStart() {
        addNewMessageObserver();
        addMessageStatusObserver();
    }

    @Override
    public void getMessages() {
        ((MessageCenterContract.View) this.mView).getHolderView().setVisibility(View.VISIBLE);
        initRecycleView();
        getData();
    }

    @Override
    public void getData() {
        List<TransactionMessage> list;
        if (!TronConfig.hasNet && ((list = this.list) == null || list.size() == 0)) {
            ((MessageCenterContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((MessageCenterContract.View) this.mView).showNoNetError(true);
            return;
        }
        getMessageData();
    }

    private void getMessageData() {
        try {
            ((MessageCenterContract.Model) this.mModel).getMessageData(this.list.size(), this.limit).subscribe(new IObserver(new ICallback<List<TransactionMessage>>() {
                @Override
                public void onResponse(String str, List<TransactionMessage> list) {
                    ((MessageCenterContract.View) mView).getHolderView().setVisibility(View.GONE);
                    updateUI(list);
                }

                @Override
                public void onFailure(String str, String str2) {
                    if (list.size() > 0) {
                        adapter.loadMoreFail();
                        return;
                    }
                    ((MessageCenterContract.View) mView).getHolderView().setVisibility(View.GONE);
                    ((MessageCenterContract.View) mView).showEmptyView(true);
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "getMessageData"));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void updateUI(List<TransactionMessage> list) {
        if (list != null && list.size() > 0) {
            this.list.addAll(list);
            this.adapter.notifyDataSetChanged();
            if (this.adapter.getData().size() <= 5) {
                this.adapter.loadMoreEnd(true);
            } else {
                this.adapter.loadMoreComplete();
            }
            ((MessageCenterContract.View) this.mView).getRecyclerView().setVisibility(View.VISIBLE);
            ((MessageCenterContract.View) this.mView).showEmptyView(false);
            return;
        }
        List<TransactionMessage> list2 = this.list;
        if (list2 != null && list2.size() > 0) {
            this.adapter.loadMoreEnd();
        } else {
            ((MessageCenterContract.View) this.mView).showEmptyView(true);
        }
    }

    @Override
    public void updateAllReadStatus() {
        TransactionMessageManager.getInstance().updateAllUnread();
    }

    private void addNewMessageObserver() {
        this.newMessageObserver = new TransactionMessageManager.Observer() {
            @Override
            public final void update(List list) {
                lambda$addNewMessageObserver$0(list);
            }
        };
        TransactionMessageManager.getInstance().addObserver(this.newMessageObserver);
    }

    public void lambda$addNewMessageObserver$0(List list) {
        try {
            updateUINewMessage(list);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void addMessageStatusObserver() {
        this.mRxManager.on(Event.MSG_CENTER_STATUS_UPDATE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$addMessageStatusObserver$1(obj);
            }
        });
    }

    public void lambda$addMessageStatusObserver$1(Object obj) throws Exception {
        TransactionMessageStatusBean transactionMessageStatusBean = (TransactionMessageStatusBean) obj;
        updateUINewMessageItem(transactionMessageStatusBean.getMessage(), transactionMessageStatusBean.getPosition());
        TransactionMessageManager.getInstance().insertOrReplace(transactionMessageStatusBean.getMessage());
    }

    private void updateUINewMessage(List<TransactionMessage> list) {
        if (this.list.size() == 0 && this.adapter.getData().size() == 0) {
            updateUI(list);
            return;
        }
        this.list.addAll(0, list);
        this.adapter.notifyItemRangeInserted(0, list.size());
    }

    private void updateUINewMessageItem(TransactionMessage transactionMessage, int i) {
        this.list.set(i, transactionMessage);
        this.adapter.notifyItemRangeChanged(i, 1);
    }

    private void initRecycleView() {
        ((MessageCenterContract.View) this.mView).getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(((MessageCenterContract.View) this.mView).getIContext(), 1, false));
        if (this.adapter == null) {
            this.adapter = new MessageCenterAdapter(((MessageCenterContract.View) this.mView).getIContext(), this.list);
            if (((MessageCenterContract.View) this.mView).getRecyclerView() != null) {
                ((MessageCenterContract.View) this.mView).getRecyclerView().setAdapter(this.adapter);
            }
        }
        this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, ((MessageCenterContract.View) this.mView).getRecyclerView());
        List<TransactionMessage> list = this.list;
        if (list != null && list.size() > 0) {
            ((MessageCenterContract.View) this.mView).getHolderView().setVisibility(View.GONE);
            ((MessageCenterContract.View) this.mView).getRecyclerView().setVisibility(View.VISIBLE);
            ((MessageCenterContract.View) this.mView).showEmptyView(true);
        }
        this.adapter.setOnItemClickListener(new fun3());
    }

    public class fun3 implements MessageCenterAdapter.OnItemClickListener {
        fun3() {
        }

        @Override
        public void onItemClick(View view, final int i) {
            String tokenAddress;
            final TransactionMessage transactionMessage = (TransactionMessage) list.get(i);
            if (transactionMessage.getState() == 0) {
                transactionMessage.setState(1);
                TransactionMessageManager.getInstance().insertOrReplace(transactionMessage);
                updateItemReadStatus(transactionMessage, i);
            }
            int tokenType = transactionMessage.getTokenType();
            String str = HelpFormatter.DEFAULT_OPT_PREFIX;
            if (tokenType == 5) {
                final TransactionHistoryBean transactionHistoryBean = new TransactionHistoryBean();
                transactionHistoryBean.block_timestamp = transactionMessage.getTransferTime();
                transactionHistoryBean.from = transactionMessage.getFromAddress();
                transactionHistoryBean.hash = transactionMessage.getTxId();
                transactionHistoryBean.setContract_ret(transactionMessage.getContract_ret());
                transactionHistoryBean.timestamp = transactionMessage.getTransferTime();
                transactionHistoryBean.decimals = (int) transactionMessage.getDecimal();
                transactionHistoryBean.to = transactionMessage.getToAddress();
                transactionHistoryBean.transactionHash = transactionMessage.getTxId();
                transactionHistoryBean.contract_address = transactionMessage.getTokenAddress();
                if (transactionMessage.getType() != 0) {
                    str = Marker.ANY_NON_NULL_MARKER;
                }
                transactionHistoryBean.setMark(str);
                if (transactionMessage.getTokenType() == 5) {
                    transactionHistoryBean.setEvent_type("Transfer");
                } else {
                    transactionHistoryBean.setEvent_type("");
                }
                transactionHistoryBean.setRevert(transactionMessage.getRevert());
                ((MessageCenterContract.View) mView).runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        MessageCenterPresenter.3.this.lambda$onItemClick$1(transactionHistoryBean, transactionMessage, i);
                    }
                });
                return;
            }
            final TransactionHistoryBean transactionHistoryBean2 = new TransactionHistoryBean();
            transactionHistoryBean2.amount = transactionMessage.getAmount();
            transactionHistoryBean2.block_timestamp = transactionMessage.getTransferTime();
            transactionHistoryBean2.from = transactionMessage.getFromAddress();
            transactionHistoryBean2.hash = transactionMessage.getTxId();
            transactionHistoryBean2.setContract_ret(transactionMessage.getContract_ret());
            transactionHistoryBean2.timestamp = transactionMessage.getTransferTime();
            transactionHistoryBean2.decimals = (int) transactionMessage.getDecimal();
            transactionHistoryBean2.to = transactionMessage.getToAddress();
            transactionHistoryBean2.transactionHash = transactionMessage.getTxId();
            transactionHistoryBean2.contract_address = transactionMessage.getTokenAddress();
            if (transactionMessage.getType() != 0) {
                str = Marker.ANY_NON_NULL_MARKER;
            }
            transactionHistoryBean2.setMark(str);
            if (transactionMessage.getTokenType() == 2 || transactionMessage.getTokenType() == 5) {
                transactionHistoryBean2.setEvent_type("Transfer");
                transactionHistoryBean2.setContract_type("TriggerSmartContract");
            } else {
                transactionHistoryBean2.setEvent_type("");
            }
            transactionHistoryBean2.setRevert(transactionMessage.getRevert());
            if (transactionMessage.getTokenType() == 0) {
                tokenAddress = "0";
            } else if (transactionMessage.getTokenType() == 1) {
                tokenAddress = transactionMessage.getId() + "";
            } else {
                tokenAddress = transactionMessage.getTokenAddress();
            }
            final String str2 = tokenAddress;
            ((MessageCenterContract.View) mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    MessageCenterPresenter.3.this.lambda$onItemClick$3(str2, transactionMessage, transactionHistoryBean2, i);
                }
            });
        }

        public void lambda$onItemClick$1(final TransactionHistoryBean transactionHistoryBean, final TransactionMessage transactionMessage, final int i) {
            NftTokenBean nFTToken = NftTokenBeanController.getInstance().getNFTToken(transactionHistoryBean.getContract_address());
            TokenBean convertToTokenBean = nFTToken != null ? nFTToken.convertToTokenBean() : null;
            if (convertToTokenBean == null) {
                convertToTokenBean = new TokenBean();
            }
            final TokenBean tokenBean = convertToTokenBean;
            tokenBean.type = transactionMessage.getTokenType();
            tokenBean.shortName = transactionMessage.getTokenSymbol();
            tokenBean.id = transactionMessage.getAssetId();
            tokenBean.setContractAddress(transactionMessage.getTokenAddress());
            ((MessageCenterContract.View) mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    MessageCenterPresenter.3.this.lambda$onItemClick$0(transactionHistoryBean, tokenBean, transactionMessage, i);
                }
            });
        }

        public void lambda$onItemClick$0(TransactionHistoryBean transactionHistoryBean, TokenBean tokenBean, TransactionMessage transactionMessage, int i) {
            NftTransactionDetailActivity.start(((MessageCenterContract.View) mView).getIContext(), transactionHistoryBean, tokenBean, true, transactionMessage, i);
        }

        public void lambda$onItemClick$3(String str, final TransactionMessage transactionMessage, final TransactionHistoryBean transactionHistoryBean, final int i) {
            TokenBean tokenBean = MyAssetsController.getInstance().getTokenBean(str);
            if (tokenBean == null) {
                tokenBean = new TokenBean();
            }
            final TokenBean tokenBean2 = tokenBean;
            tokenBean2.type = transactionMessage.getTokenType();
            tokenBean2.shortName = transactionMessage.getTokenSymbol();
            ((MessageCenterContract.View) mView).runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    MessageCenterPresenter.3.this.lambda$onItemClick$2(transactionHistoryBean, tokenBean2, transactionMessage, i);
                }
            });
        }

        public void lambda$onItemClick$2(TransactionHistoryBean transactionHistoryBean, TokenBean tokenBean, TransactionMessage transactionMessage, int i) {
            TransactionDetailActivity.start(((MessageCenterContract.View) mView).getIContext(), transactionHistoryBean, tokenBean, transactionMessage.getAmount(), true, transactionMessage, i);
        }
    }

    public void updateItemReadStatus(TransactionMessage transactionMessage, int i) {
        this.list.set(i, transactionMessage);
        this.adapter.notifyItemRangeChanged(i, 1);
    }

    public void loadMore() {
        getData();
    }

    private String getUrl(String str) {
        String str2;
        if (SpAPI.THIS.getCurrentChain() != null && SpAPI.THIS.getCurrentChain().isMainChain) {
            str2 = TronConfig.TRONSCANHOST_MAINCHAIN + str;
        } else {
            str2 = TronConfig.TRONSCANHOST_DAPPCHAIN + str;
        }
        if (LanguageUtils.languageZH(AppContextUtil.getContext())) {
            return str2 + "?lang=zh";
        }
        return str2 + "?lang=en";
    }
}
