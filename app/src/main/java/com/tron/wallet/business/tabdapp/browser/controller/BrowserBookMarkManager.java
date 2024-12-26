package com.tron.wallet.business.tabdapp.browser.controller;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import java.util.ArrayList;
import java.util.List;
public class BrowserBookMarkManager {
    public static final int MAX_COUNT = 300;
    private static final String TAG = "BrowserBookMarkManager";
    BrowserBookMarkController browserBookMarkController;
    private long totalCount;

    public static class Nested {
        private static BrowserBookMarkManager manager = new BrowserBookMarkManager();
    }

    private BrowserBookMarkManager() {
        BrowserBookMarkController browserBookMarkController = BrowserBookMarkController.getInstance();
        this.browserBookMarkController = browserBookMarkController;
        this.totalCount = browserBookMarkController.getCount();
    }

    public List<BrowserBookMarkBean> queryBookMarks() {
        return this.browserBookMarkController.getBrowserBookMarkSortList();
    }

    public static BrowserBookMarkManager getInstance() {
        return Nested.manager;
    }

    public boolean addNewMark(String str, String str2, String str3) {
        LogUtils.d(TAG, "addNewMark url: " + str + "   title:  " + str2);
        if (this.browserBookMarkController.haveExists(str)) {
            return false;
        }
        return this.browserBookMarkController.insertOrReplace(new BrowserBookMarkBean(str, str2, str3, false));
    }

    public boolean isExists(String str) {
        return this.browserBookMarkController.haveExists(str);
    }

    public void insertMultiObject(List<BrowserBookMarkBean> list, boolean z) {
        this.browserBookMarkController.insertMultiObject(list, z);
    }

    public boolean removeSingle(String str) {
        return this.browserBookMarkController.removeSingle(str);
    }

    public List<BrowserBookMarkBean> search(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.browserBookMarkController.queryRaw("where URL like '%" + str + "%'", null));
        List<BrowserBookMarkBean> queryRaw = this.browserBookMarkController.queryRaw("where TITLE like '%" + str + "%'", null);
        for (int i = 0; i < queryRaw.size(); i++) {
            if (!arrayList.contains(queryRaw.get(i))) {
                arrayList.add(queryRaw.get(i));
            }
        }
        return arrayList;
    }

    public long getBookMarkCount() {
        return this.browserBookMarkController.getCount();
    }
}
