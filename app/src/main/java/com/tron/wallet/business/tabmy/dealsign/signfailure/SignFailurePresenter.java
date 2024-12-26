package com.tron.wallet.business.tabmy.dealsign.signfailure;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.tabmy.dealsign.signfailure.SignFailureContract;
import com.tron.wallet.common.adapter.DealSignAdapter;
import com.tron.wallet.common.bean.DealSignOutput;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
public class SignFailurePresenter extends SignFailureContract.Presenter implements ICallback<DealSignOutput> {
    private String address;
    private List<DealSignOutput.DataBeanX.DataBean> allList;
    private int allStart;
    private int allTotal;
    private DealSignAdapter dealSignAdapter;
    private Wallet mSelectedWallet;
    private int state;
    private final String SIGNFAILURE = "SIGNFAILURE";
    private final String SIGNSUCCESS = "SIGNSUCCESS";
    private final String SIGNWAIT = "SIGNWAIT";
    private final String SIGNALREADY = "SIGNALREADY";
    private int limit = 20;

    @Override
    protected void onStart() {
        this.allList = new ArrayList();
    }

    @Override
    public void getData(int i) {
        String str;
        int i2;
        this.state = i;
        if (i == 4) {
            str = "SIGNFAILURE";
            i2 = 2;
        } else if (i == 3) {
            str = "SIGNSUCCESS";
            i2 = 1;
        } else {
            str = i == 1 ? "SIGNWAIT" : "SIGNALREADY";
            i2 = 0;
        }
        ((SignFailureContract.Model) this.mModel).getSignFailureTransfer(this.address, this.allStart, this.limit, i2, SpAPI.THIS.isShasta() ? "shasta" : "main_net").subscribe(new IObserver(this, str));
    }

    @Override
    public void addSome() {
        Wallet wallet = WalletUtils.getWallet(((SignFailureContract.View) this.mView).getWalletName());
        if (wallet != null) {
            this.address = wallet.getAddress();
        }
        this.allStart = 0;
        ((SignFailureContract.View) this.mView).getRcFrame().setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, ((SignFailureContract.View) mView).getRcList(), view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                onRefresh();
            }
        });
    }

    public void onLoadMore() {
        int i = this.allStart;
        if (i >= this.allTotal) {
            this.dealSignAdapter.loadMoreComplete();
            this.dealSignAdapter.loadMoreEnd();
            return;
        }
        this.allStart = i + 20;
        getData(this.state);
    }

    public void onRefresh() {
        this.allStart = 0;
        getData(this.state);
    }

    @Override
    public void onResponse(String str, DealSignOutput dealSignOutput) {
        ((SignFailureContract.View) this.mView).dismissLoadingPage();
        ((SignFailureContract.View) this.mView).getRcFrame().refreshComplete();
        if (this.allStart != 0) {
            if (dealSignOutput.data.data == null) {
                dealSignOutput.data.data = new ArrayList();
            } else {
                this.allList.addAll(dealSignOutput.data.data);
            }
        } else if (dealSignOutput != null && dealSignOutput.data != null && dealSignOutput.data.total == 0) {
            ((SignFailureContract.View) this.mView).showNoDatasPage();
            return;
        } else {
            this.allList = dealSignOutput.data.data;
        }
        this.allTotal = dealSignOutput.data.total;
        DealSignAdapter dealSignAdapter = this.dealSignAdapter;
        if (dealSignAdapter == null) {
            ((SignFailureContract.View) this.mView).getRcList().setLayoutManager(new LinearLayoutManager(((SignFailureContract.View) this.mView).getIContext(), 1, false));
            this.dealSignAdapter = new DealSignAdapter(((SignFailureContract.View) this.mView).getIContext(), this.allList, this.state, ((SignFailureContract.View) this.mView).getWalletName());
            ((SignFailureContract.View) this.mView).getRcList().setAdapter(this.dealSignAdapter);
            this.dealSignAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public final void onLoadMoreRequested() {
                    onLoadMore();
                }
            }, ((SignFailureContract.View) this.mView).getRcList());
        } else {
            dealSignAdapter.notifyData1(this.allList);
        }
        this.dealSignAdapter.loadMoreComplete();
        if (((SignFailureContract.View) this.mView).getRcFrame() == null || ((SignFailureContract.View) this.mView).getRcFrame().getVisibility() == 0) {
            return;
        }
        ((SignFailureContract.View) this.mView).getNoNetView().setVisibility(View.GONE);
        ((SignFailureContract.View) this.mView).getRcFrame().setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailure(String str, String str2) {
        ((SignFailureContract.View) this.mView).dismissLoadingPage();
        ((SignFailureContract.View) this.mView).showErrorPage();
        ((SignFailureContract.View) this.mView).getRcFrame().refreshComplete();
        this.dealSignAdapter.loadMoreFail();
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mRxManager.add(disposable);
    }
}
