package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabBean;
public class BrowserTabDataController extends BaseController<BrowserTabBean> {
    private static BrowserTabDataController instance;

    public BrowserTabBean getBrowserTabBean() {
        return null;
    }

    private BrowserTabDataController() {
        super(true);
    }

    public static BrowserTabDataController getInstance() {
        if (instance == null) {
            synchronized (BrowserTabDataController.class) {
                if (instance == null) {
                    instance = new BrowserTabDataController();
                }
            }
        }
        return instance;
    }
}
