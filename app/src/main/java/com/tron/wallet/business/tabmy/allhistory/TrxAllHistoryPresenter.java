package com.tron.wallet.business.tabmy.allhistory;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tabmy.allhistory.TrxAllHistoryContract;
import com.tron.wallet.common.adapter.token.AllTransferAdapter;
import com.tron.wallet.common.bean.AllTransferOutput;
import com.tron.wallet.common.components.RecyclerViewUtil;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.components.ptr.PtrUIHandler;
import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
import com.tron.wallet.common.config.TronConfig;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.util.List;
public class TrxAllHistoryPresenter extends TrxAllHistoryContract.Presenter implements ICallback<AllTransferOutput> {
    private String address;
    private List<AllTransferOutput.DataBean> allList;
    private int allStart;
    private int allTotal;
    private AllTransferAdapter allTransferAdapter;
    private RecyclerViewUtil recyclerViewUtil;
    private final String ALLTRANSFER = "ALLTRANSFER";
    private final String RECEIVETRANSFER = "RECEIVETRANSFER";
    private final String SENTTRANSFER = "SENTTRANSFER";
    private int limit = 20;

    @Override
    protected void onStart() {
    }

    @Override
    public void getData() {
        int position = ((TrxAllHistoryContract.View) this.mView).getPosition();
        if (position == 0) {
            ((TrxAllHistoryContract.Model) this.mModel).getAllTRXTransfer(this.address, null, null, this.allStart, this.limit).subscribe(new IObserver(this, "ALLTRANSFER"));
        } else if (position == 1) {
            ((TrxAllHistoryContract.Model) this.mModel).getAllTRXTransfer(null, null, this.address, this.allStart, this.limit).subscribe(new IObserver(this, "SENTTRANSFER"));
        } else if (position != 2) {
        } else {
            ((TrxAllHistoryContract.Model) this.mModel).getAllTRXTransfer(null, this.address, null, this.allStart, this.limit).subscribe(new IObserver(this, "RECEIVETRANSFER"));
        }
    }

    @Override
    public void onRefresh(String str) {
        if (!TronConfig.hasNet) {
            IToast.getIToast().show(R.string.time_out);
            ((TrxAllHistoryContract.View) this.mView).dismissLoadingPage();
            ((TrxAllHistoryContract.View) this.mView).showError(true);
            ((TrxAllHistoryContract.View) this.mView).getRcFrame().refreshComplete();
            return;
        }
        this.address = str;
        this.allStart = 0;
        this.recyclerViewUtil.setLoadMoreEnable(true);
        getData();
    }

    @Override
    public void addSome() {
        this.address = ((TrxAllHistoryContract.View) this.mView).getAddress();
        ((TrxAllHistoryContract.View) this.mView).getRcFrame().setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, ((TrxAllHistoryContract.View) mView).getRcList(), view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                TrxAllHistoryPresenter trxAllHistoryPresenter = TrxAllHistoryPresenter.this;
                trxAllHistoryPresenter.onRefresh(trxAllHistoryPresenter.address);
            }
        });
        ((TrxAllHistoryContract.View) this.mView).getRcFrame().addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout, boolean z) {
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
            }

            @Override
            public void onUIReset(PtrFrameLayout ptrFrameLayout) {
                ((TrxAllHistoryActivity) ((TrxAllHistoryContract.View) mView).getIContext()).getVpContent().setUserInputEnabled(true);
            }

            @Override
            public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
                if (ptrIndicator.getCurrentPosY() > 5) {
                    ((TrxAllHistoryActivity) ((TrxAllHistoryContract.View) mView).getIContext()).getVpContent().setUserInputEnabled(false);
                }
            }
        });
        RecyclerViewUtil recyclerViewUtil = new RecyclerViewUtil(((TrxAllHistoryContract.View) this.mView).getRcList());
        this.recyclerViewUtil = recyclerViewUtil;
        recyclerViewUtil.setRecyclerViewLoadMoreListener(new RecyclerViewUtil.RecyclerViewLoadMoreListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            }

            @Override
            public void onLoadMore() {
                recyclerViewUtil.setLoadMoreEnable(false);
                if (allStart >= allTotal) {
                    try {
                        ((TrxAllHistoryContract.View) mView).ToastAsBottom(R.string.already_down);
                        return;
                    } catch (Exception e) {
                        LogUtils.e(e);
                        return;
                    }
                }
                allStart += 20;
                getData();
            }
        });
    }

    @Override
    public void onResponse(String str, AllTransferOutput allTransferOutput) {
        ((TrxAllHistoryContract.View) this.mView).dismissLoadingPage();
        ((TrxAllHistoryContract.View) this.mView).getRcFrame().refreshComplete();
        ((TrxAllHistoryContract.View) this.mView).showError(false);
        if (this.allStart != 0) {
            this.allList.addAll(allTransferOutput.data);
        } else if (allTransferOutput != null && (allTransferOutput.total == 0 || allTransferOutput.data == null || allTransferOutput.data.isEmpty())) {
            ((TrxAllHistoryContract.View) this.mView).showNoDatasPage();
            return;
        } else {
            this.allList = allTransferOutput.data;
        }
        ((TrxAllHistoryContract.View) this.mView).showContent();
        this.allTotal = allTransferOutput.total;
        AllTransferAdapter allTransferAdapter = this.allTransferAdapter;
        if (allTransferAdapter == null) {
            ((TrxAllHistoryContract.View) this.mView).getRcList().setLayoutManager(new LinearLayoutManager(((TrxAllHistoryContract.View) this.mView).getIContext(), 1, false));
            this.allTransferAdapter = new AllTransferAdapter(this.allList, ((TrxAllHistoryContract.View) this.mView).getIContext());
            ((TrxAllHistoryContract.View) this.mView).getRcList().setAdapter(this.allTransferAdapter);
        } else {
            allTransferAdapter.notifyData(this.allList);
        }
        List<AllTransferOutput.DataBean> list = this.allList;
        if (list != null && list.size() == 0) {
            ((TrxAllHistoryContract.View) this.mView).showNoDatasPage();
        }
        this.recyclerViewUtil.setLoadMoreEnable(true);
    }

    @Override
    public void onFailure(String str, String str2) {
        IToast.getIToast().show(R.string.time_out);
        ((TrxAllHistoryContract.View) this.mView).dismissLoadingPage();
        List<AllTransferOutput.DataBean> list = this.allList;
        if (list == null || list.size() == 0) {
            ((TrxAllHistoryContract.View) this.mView).showError(true);
        } else {
            this.recyclerViewUtil.setLoadMoreEnable(true);
        }
        ((TrxAllHistoryContract.View) this.mView).getRcFrame().refreshComplete();
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mRxManager.add(disposable);
    }
}
