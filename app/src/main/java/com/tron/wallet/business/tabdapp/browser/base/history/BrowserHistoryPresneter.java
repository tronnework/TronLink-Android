package com.tron.wallet.business.tabdapp.browser.base.history;

import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.base.history.BrowserHistoryContract;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
public class BrowserHistoryPresneter extends BrowserHistoryContract.Presenter {
    private int limit = 20;
    private int offset = 0;
    private String lastDateStr = null;

    public int getStatIndex() {
        return this.offset;
    }

    @Override
    protected void onStart() {
    }

    public void loadMore() {
        this.offset += this.limit;
        requestData(false);
    }

    @Override
    public void requestData(final boolean z) {
        if (z) {
            this.offset = 0;
            this.lastDateStr = null;
        }
        ((BrowserHistoryContract.Model) this.mModel).queryHistory(this.limit, this.offset).subscribe(new Consumer<List<BrowserHistoryBean>>() {
            @Override
            public void accept(List<BrowserHistoryBean> list) throws Exception {
                SimpleDateFormat simpleDateFormat;
                ArrayList arrayList = new ArrayList();
                if (list != null && list.size() > 0) {
                    Iterator<BrowserHistoryBean> it = list.iterator();
                    BrowserHistoryBean next = it.next();
                    Date date = new Date(next.timestamp);
                    if (LanguageUtils.languageZH(((BrowserHistoryContract.View) mView).getIContext())) {
                        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
                    } else {
                        simpleDateFormat = new SimpleDateFormat("EEEE,MMMM dd, yyyy");
                    }
                    String format = simpleDateFormat.format(date);
                    if (lastDateStr == null || !lastDateStr.equals(format)) {
                        lastDateStr = format;
                        arrayList.add(new BrowserHistoryBean(format, format, -1));
                    }
                    arrayList.add(next);
                    while (it.hasNext()) {
                        BrowserHistoryBean next2 = it.next();
                        if (next2.timestamp != 0) {
                            Date date2 = new Date(next2.timestamp);
                            if (simpleDateFormat.format(date).equals(simpleDateFormat.format(date2))) {
                                arrayList.add(next2);
                            } else {
                                lastDateStr = simpleDateFormat.format(date2);
                                arrayList.add(new BrowserHistoryBean(simpleDateFormat.format(date2), simpleDateFormat.format(date2), -1));
                                date.setTime(next2.timestamp);
                                arrayList.add(next2);
                            }
                        }
                    }
                }
                ((BrowserHistoryContract.View) mView).updateList(arrayList, z);
            }
        });
    }

    @Override
    public void jumpToViewPage(BrowserHistoryBean browserHistoryBean) {
        BrowserTabManager browserTabManager = BrowserTabManager.getInstance();
        if (browserTabManager != null) {
            DappBean dappBean = new DappBean();
            dappBean.setName(browserHistoryBean.getTitle());
            dappBean.setHomeUrl(browserHistoryBean.getUrl());
            dappBean.setImageUrl(browserHistoryBean.getIconUrl());
            browserTabManager.startURL(dappBean, false, true);
        }
        ((BrowserHistoryContract.View) this.mView).exit();
    }

    @Override
    public boolean remveHistory(BrowserHistoryBean browserHistoryBean, int i) {
        return ((BrowserHistoryContract.Model) this.mModel).remveHistory(browserHistoryBean, i);
    }

    @Override
    public void clearAllHistory() {
        ((BrowserHistoryContract.Model) this.mModel).clearAllHistory();
    }
}
