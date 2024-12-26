package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabHistoryBean;
import java.util.List;
public class BrowserTabHistoryManager {
    private BrowserTabHistoryController browserTabHistoryController;

    public static class Nested {
        private static BrowserTabHistoryManager manager = new BrowserTabHistoryManager();
    }

    private BrowserTabHistoryManager() {
        this.browserTabHistoryController = BrowserTabHistoryController.getInstance();
    }

    public boolean removeSingle(BrowserTabHistoryBean browserTabHistoryBean) {
        return this.browserTabHistoryController.removeSingle(browserTabHistoryBean);
    }

    public boolean removeAll() {
        return this.browserTabHistoryController.deleteAll();
    }

    public List<BrowserTabHistoryBean> queryTabHistory() {
        return this.browserTabHistoryController.getBrowserTabHistorySortList();
    }

    public void addListAndRemoveOldData(List<BrowserTabHistoryBean> list) {
        this.browserTabHistoryController.insertMultiObject(list, true);
    }

    public static BrowserTabHistoryManager getInstance() {
        return Nested.manager;
    }

    public boolean addNewBean(int i, String str) {
        BrowserTabHistoryBean queryifExist = this.browserTabHistoryController.queryifExist(i);
        if (queryifExist == null) {
            queryifExist = new BrowserTabHistoryBean();
        }
        queryifExist.setTabIndex(i);
        queryifExist.setUrl(str);
        return this.browserTabHistoryController.insertOrReplace(queryifExist);
    }
}
