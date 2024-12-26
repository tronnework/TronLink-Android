package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class BrowserHistoryManager {
    public static final int MAX_COUNT = 300;
    private BrowserHistoryController browserHistoryController;
    private long historyCount;

    public static class Nested {
        private static BrowserHistoryManager manager = new BrowserHistoryManager();
    }

    private BrowserHistoryManager() {
        BrowserHistoryController browserHistoryController = BrowserHistoryController.getInstance();
        this.browserHistoryController = browserHistoryController;
        this.historyCount = browserHistoryController.getHistoryCount();
    }

    public boolean removeSingle(BrowserHistoryBean browserHistoryBean) {
        return this.browserHistoryController.removeSingle(browserHistoryBean);
    }

    public List<BrowserHistoryBean> queryHistory() {
        return this.browserHistoryController.queryAll();
    }

    public List<BrowserHistoryBean> queryHistory(int i, int i2) {
        return this.browserHistoryController.getBrowserHistorySortList(i, i2);
    }

    public static BrowserHistoryManager getInstance() {
        return Nested.manager;
    }

    public boolean addNewPage(String str, String str2, String str3) {
        if (this.historyCount >= 300) {
            this.browserHistoryController.removeOldestOne();
        }
        BrowserHistoryBean queryInOneDay = this.browserHistoryController.queryInOneDay(str);
        if (queryInOneDay != null) {
            queryInOneDay.timestamp = System.currentTimeMillis();
        } else {
            queryInOneDay = new BrowserHistoryBean(str, str2, str3);
        }
        return this.browserHistoryController.insertOrReplace(queryInOneDay);
    }

    public long getHistoryCount() {
        return this.browserHistoryController.getHistoryCount();
    }

    public List<BrowserHistoryBean> search(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.browserHistoryController.queryRaw("where URL like '%" + str + "%'", null));
        List<BrowserHistoryBean> queryRaw = this.browserHistoryController.queryRaw("where TITLE like '%" + str + "%'", null);
        for (int i = 0; i < queryRaw.size(); i++) {
            if (!arrayList.contains(queryRaw.get(i))) {
                arrayList.add(queryRaw.get(i));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BrowserHistoryBean browserHistoryBean = (BrowserHistoryBean) it.next();
            if (!arrayList2.contains(browserHistoryBean) && !browserHistoryBean.getUrl().contains("google.com")) {
                arrayList2.add(browserHistoryBean);
            }
        }
        return arrayList2;
    }

    public void clearAllHistory() {
        this.browserHistoryController.clearAllData();
    }
}
