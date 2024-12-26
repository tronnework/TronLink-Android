package com.tron.wallet.business.tabswap.record;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.wallet.business.tabswap.record.SwapRecordContract;
import com.tron.wallet.common.adapter.SwapTokenRecordAdapter;
import com.tron.wallet.common.components.CustomLoadMoreView;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.db.Controller.JustSwapTransactionController;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tron.wallet.db.wallet.WalletUtils;
import java.util.ArrayList;
import java.util.List;
public class SwapRecordPresenter extends SwapRecordContract.Presenter {
    private SwapTokenRecordAdapter adapter;
    private List<JustSwapBean> recordList = new ArrayList();
    private int page = 1;
    private boolean loadSuccess = false;

    @Override
    protected void onStart() {
    }

    @Override
    public void getData() {
        if (this.mView == 0) {
            return;
        }
        try {
            ((SwapRecordContract.View) this.mView).runOnThreeThread(new OnBackground() {
                @Override
                public final void doOnBackground() {
                    lambda$getData$1();
                }
            });
        } catch (Exception unused) {
        }
    }

    public void lambda$getData$1() {
        String address = WalletUtils.getSelectedPublicWallet().getAddress();
        if (address == null) {
            return;
        }
        final List<JustSwapBean> notesByAddress = JustSwapTransactionController.getInstance().getNotesByAddress(address, this.page, 20);
        this.loadSuccess = true;
        ((SwapRecordContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$getData$0(notesByAddress);
            }
        });
    }

    public void lambda$getData$0(List list) {
        ((SwapRecordContract.View) this.mView).getRcFrame().refreshComplete();
        if ((list == null || list.size() == 0) && this.page == 1) {
            ((SwapRecordContract.View) this.mView).showEmptyView(true);
        } else if (list == null || list.isEmpty()) {
            this.adapter.loadMoreEnd();
        } else {
            if (this.page == 1) {
                this.recordList.clear();
                this.recordList = list;
            } else {
                this.recordList.addAll(list);
            }
            this.adapter.loadMoreComplete();
            this.adapter.notifyData(this.recordList);
            ((SwapRecordContract.View) this.mView).updateUI(true ^ this.loadSuccess, false);
        }
    }

    @Override
    public void init() {
        ((SwapRecordContract.View) this.mView).getRcFrame().setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, ((SwapRecordContract.View) mView).getRcList(), view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                onRefresh();
            }
        });
        ((SwapRecordContract.View) this.mView).getRcList().setLayoutManager(new LinearLayoutManager(((SwapRecordContract.View) this.mView).getIContext(), 1, false));
        this.adapter = new SwapTokenRecordAdapter(((SwapRecordContract.View) this.mView).getIContext(), this.recordList);
        ((SwapRecordContract.View) this.mView).getRcList().setAdapter(this.adapter);
        this.adapter.setLoadMoreView(new CustomLoadMoreView());
        this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                lambda$init$3();
            }
        }, ((SwapRecordContract.View) this.mView).getRcList());
    }

    public void lambda$init$3() {
        ((SwapRecordContract.View) this.mView).getRcList().postDelayed(new Runnable() {
            @Override
            public final void run() {
                lambda$init$2();
            }
        }, 1000L);
    }

    public void lambda$init$2() {
        this.page++;
        getData();
    }

    @Override
    public void onRefresh() {
        this.page = 1;
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
