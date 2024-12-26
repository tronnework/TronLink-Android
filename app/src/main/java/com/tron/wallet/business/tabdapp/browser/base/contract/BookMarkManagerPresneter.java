package com.tron.wallet.business.tabdapp.browser.base.contract;

import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.base.contract.BookMarkManagerContract;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import io.reactivex.functions.Consumer;
import java.util.List;
public class BookMarkManagerPresneter extends BookMarkManagerContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void requestData(boolean z) {
        ((BookMarkManagerContract.Model) this.mModel).queryBookMark().subscribe(new Consumer<List<BrowserBookMarkBean>>() {
            @Override
            public void accept(List<BrowserBookMarkBean> list) throws Exception {
                ((BookMarkManagerContract.View) mView).updateList(list);
            }
        });
    }

    @Override
    public void jumpToViewPage(BrowserBookMarkBean browserBookMarkBean) {
        BrowserTabManager browserTabManager = BrowserTabManager.getInstance();
        if (browserTabManager != null) {
            DappBean dappBean = new DappBean();
            dappBean.setName(browserBookMarkBean.getTitle());
            dappBean.setHomeUrl(browserBookMarkBean.getUrl());
            dappBean.setImageUrl(browserBookMarkBean.getIconUrl());
            browserTabManager.startURL(dappBean, false, true);
        }
        ((BookMarkManagerContract.View) this.mView).exit();
    }

    @Override
    public boolean remveBookMark(BrowserBookMarkBean browserBookMarkBean, int i) {
        return ((BookMarkManagerContract.Model) this.mModel).remveBookMark(browserBookMarkBean, i);
    }

    @Override
    public void saveSortedDatas(List<BrowserBookMarkBean> list) {
        ((BookMarkManagerContract.Model) this.mModel).saveSortedDatas(list);
    }
}
