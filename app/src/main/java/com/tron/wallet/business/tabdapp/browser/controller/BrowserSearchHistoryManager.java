package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.wallet.business.tabdapp.browser.bean.BrowserSearchBean;
import java.util.List;
public class BrowserSearchHistoryManager {
    private BrowserSearchHistoryController browserSearchHistoryController;

    public static class Nested {
        private static BrowserSearchHistoryManager manager = new BrowserSearchHistoryManager();
    }

    private BrowserSearchHistoryManager() {
        this.browserSearchHistoryController = BrowserSearchHistoryController.getInstance();
    }

    public boolean removeSingle(BrowserSearchBean browserSearchBean) {
        return this.browserSearchHistoryController.removeSingle(browserSearchBean);
    }

    public boolean removeAll() {
        return this.browserSearchHistoryController.deleteAll();
    }

    public List<BrowserSearchBean> queryHistory() {
        return this.browserSearchHistoryController.getBrowserHistorySortList();
    }

    public List<BrowserSearchBean> queryHistory(int i, int i2) {
        return this.browserSearchHistoryController.queryAll(i, i2);
    }

    public static BrowserSearchHistoryManager getInstance() {
        return Nested.manager;
    }

    public boolean addNewBean(String str) {
        BrowserSearchBean queryifExist = this.browserSearchHistoryController.queryifExist(str);
        if (queryifExist == null) {
            queryifExist = new BrowserSearchBean(str);
        }
        queryifExist.timestamp = System.currentTimeMillis();
        return this.browserSearchHistoryController.insertOrReplace(queryifExist);
    }

    public List<BrowserSearchBean> search(String str) {
        return this.browserSearchHistoryController.queryRaw("where KEY like '%" + str + "%'", null);
    }
}
