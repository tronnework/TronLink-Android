package com.tron.wallet.business.tabdapp.home.utils;

import android.content.Context;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTabsManagerActivity;
import com.tron.wallet.business.tabmy.myhome.dappauthorized.DappAuthorizedActivity;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public abstract class BaseMenuClickCallback {
    public void onClickDebug(boolean z) {
    }

    public void onClickNewTab(Context context) {
        if (10 <= BrowserTabManager.getInstance().getTabCount()) {
            ToastUtil.getInstance().show(context, context.getString(R.string.dapp_browser_max_count_warning));
            return;
        }
        BrowserTabManager.getInstance().startNewTab();
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_NEW_TAB);
    }

    public void onClickRefresh() {
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_REFRESH);
    }

    public void onClickBookMark(boolean z) {
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_BOOKMARK);
    }

    public void onClickHistory(Context context) {
        BrowserTabsManagerActivity.start(context, 2);
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_HISTORY);
    }

    public void onClickBookMarkPackage(Context context) {
        BrowserTabsManagerActivity.start(context, 1);
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_BOOKMARK_MANAGER);
    }

    public void onClickSwitchWallet() {
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_SWITCH_WALLET);
    }

    public void onClickLinkManager(Context context) {
        DappAuthorizedActivity.start(context, WalletUtils.getSelectedWallet().getWalletName());
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_CONNECT_MANAGER);
    }

    public void onClickShare() {
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_SHARE);
    }

    public void onClickDappReport() {
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_REPORT);
    }

    public void onClickOpenOutside() {
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_OPEN_OUTSIDE);
    }

    public void onClickClose() {
        BrowserTabManager.getInstance().closeCurTab();
        AnalyticsHelper.logEvent(AnalyticsHelper.DappMenu.CLICK_CLOSE_TAB);
    }
}
