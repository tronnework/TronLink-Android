package com.tron.wallet.business.tabdapp.browser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.migrate.MigrateActivityExternalSyntheticLambda2;
import com.tron.wallet.business.tabdapp.browser.controller.MostVisitDAppController;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTab;
import com.tron.wallet.business.tabdapp.component.ISnapshot;
import com.tron.wallet.business.tabdapp.home.DAppMainPresenter;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.business.web.DappOptions;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.components.browser.BrowserContent;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.HtmlUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Single;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.Optional;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;
public class BrowserTabManager {
    public static final int MAX_TAB_COUNT = 10;
    public static final String TAG = "BrowserTabManager";
    private static BrowserTabManager manager;
    private BrowserContent browserContent;
    private ISnapshot browserSnapshot;
    private int currentTabIndex;
    private ISnapshot defaultSnapshot;
    private final ObserverList<Observer> mObservers = new ObserverList<>();
    private final List<Consumer<String>> titleObservers = new ArrayList();
    private LinkedList<BrowserTab> mBrowserTabs = new LinkedList<>();
    private int mTabCounter = 1;
    private final HashMap<BrowserContent, Integer> tabCounterMap = new HashMap<>();
    private final HashMap<BrowserContent, Integer> currentIndexMap = new HashMap<>();
    private final HashMap<BrowserContent, LinkedList<BrowserTab>> tabsMap = new HashMap<>();
    private boolean isLoadingTabHistory = false;

    public interface Observer {
        void onTabChanged();
    }

    public interface ViewType {
        public static final int MAIN = 0;
        public static final int NONE = -1;
        public static final int WEB = 1;
    }

    public BrowserContent getBrowserContent() {
        return this.browserContent;
    }

    public int getCurrentTabIndex() {
        return this.currentTabIndex;
    }

    public int getTabCount() {
        return this.mTabCounter;
    }

    public void setBrowserSnapshot(ISnapshot iSnapshot) {
        this.browserSnapshot = iSnapshot;
    }

    public void setDefaultSnapshot(ISnapshot iSnapshot) {
        this.defaultSnapshot = iSnapshot;
    }

    public void setLoadingTabHistory(boolean z) {
        this.isLoadingTabHistory = z;
    }

    private BrowserTabManager() {
    }

    public static BrowserTabManager getInstance() {
        if (manager == null) {
            synchronized (BrowserTabManager.class) {
                if (manager == null) {
                    manager = new BrowserTabManager();
                }
            }
        }
        return manager;
    }

    public void clear() {
        synchronized (BrowserTabManager.class) {
            manager = null;
        }
    }

    public void setBrowserContent(BrowserContent browserContent) {
        if (browserContent == null || !browserContent.equals(this.browserContent)) {
            BrowserContent browserContent2 = this.browserContent;
            if (browserContent2 != null) {
                this.tabCounterMap.put(browserContent2, Integer.valueOf(this.mTabCounter));
                this.currentIndexMap.put(this.browserContent, Integer.valueOf(this.currentTabIndex));
                this.tabsMap.put(this.browserContent, this.mBrowserTabs);
            }
            this.browserContent = browserContent;
            if (!this.tabCounterMap.containsKey(browserContent)) {
                this.tabCounterMap.put(browserContent, 1);
            }
            Integer num = this.tabCounterMap.get(browserContent);
            this.mTabCounter = num == null ? 1 : num.intValue();
            if (!this.currentIndexMap.containsKey(browserContent)) {
                this.currentIndexMap.put(browserContent, 0);
            }
            Integer num2 = this.currentIndexMap.get(browserContent);
            this.currentTabIndex = num2 == null ? 0 : num2.intValue();
            if (!this.tabsMap.containsKey(browserContent)) {
                LinkedList<BrowserTab> linkedList = new LinkedList<>();
                BrowserTab browserTab = new BrowserTab();
                browserTab.setActive(true);
                linkedList.add(0, browserTab);
                this.tabsMap.put(browserContent, linkedList);
            }
            this.mBrowserTabs = this.tabsMap.get(browserContent);
        }
    }

    private void setBrowserTabVisitable() {
        int i = 0;
        while (i < this.mTabCounter) {
            BrowserTab browserTab = this.mBrowserTabs.get(i);
            browserTab.setIndex(i);
            browserTab.setActive(i == this.currentTabIndex);
            i++;
        }
    }

    public void setTab(int i) {
        LogUtils.d(TAG, "setTab:" + i);
        this.currentTabIndex = i;
        this.browserContent.showTab(i);
        setBrowserTabVisitable();
        notifyObservers();
    }

    public void startURL(String str) {
        startURL(str, false);
    }

    public void startURL(String str, boolean z) {
        startURL(str, "", z, false);
    }

    public void startURL(String str, String str2, boolean z, boolean z2) {
        startURL(str, str2, z, z2, true);
    }

    public void startURL(String str, String str2, boolean z, boolean z2, boolean z3) {
        DappBean dappBean = new DappBean();
        dappBean.setName(str2);
        dappBean.setHomeUrl(str);
        startURL(dappBean, z, z2, z3);
    }

    public void startURL(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        DappBean dappBean = new DappBean();
        dappBean.setName(str2);
        dappBean.setHomeUrl(str);
        startURL(dappBean, z, z2, z3, z4);
    }

    public void startURL(DappBean dappBean, boolean z, boolean z2) {
        startURL(dappBean, z, z2, true);
    }

    public void startURL(DappBean dappBean, boolean z, boolean z2, boolean z3) {
        startURL(dappBean, z, z2, true, false);
    }

    public void startURL(DappBean dappBean, boolean z, boolean z2, boolean z3, boolean z4) {
        String selectedWallet;
        if (WalletUtils.getSelectedWallet() != null) {
            selectedWallet = WalletUtils.getSelectedWallet().getWalletName();
        } else {
            selectedWallet = SpAPI.THIS.getSelectedWallet();
        }
        WebOptions build = new WebOptions.WebOptionsBuild().addNeedOutside(false).addUseCache(true).addIsFinance(z4).addInjectTronweb(z3).addDappOptions(new DappOptions.DappOptionsBuild().addInjectZTron(dappBean.isAnonymous()).build()).addWallerName(selectedWallet).build();
        boolean z5 = z | (getViewType(getCurrentTabIndex()) != 1);
        String addSuffixToDAppUrl = addSuffixToDAppUrl(dappBean.getHomeUrl());
        String wrapPhishingUrl = DAppUrlUtils.wrapPhishingUrl(addSuffixToDAppUrl);
        BrowserContent browserContent = this.browserContent;
        if (browserContent != null) {
            browserContent.startURL(wrapPhishingUrl, z5, build);
        }
        BrowserTab tabAt = getTabAt(getCurrentTabIndex());
        tabAt.setName(dappBean.getName());
        tabAt.setHomeUrl(addSuffixToDAppUrl);
        tabAt.setImageUrl(dappBean.getImageUrl());
        tabAt.setReplaceTitle(z2);
        tabAt.setViewType(1);
        notifyObservers();
        try {
            Bundle bundle = new Bundle();
            bundle.putString("url", dappBean.getHomeUrl());
            bundle.putString("title", dappBean.getName());
            AnalyticsHelper.logEvent(AnalyticsHelper.BrowserVisit.ENTER_BROWSER_VISIT, bundle);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void startWithURL_HISTORY(String str, String str2, String str3, String str4, boolean z) {
        WebOptions build = new WebOptions.WebOptionsBuild().addNeedOutside(false).addUseCache(true).addInjectTronweb(true).addDappAuthUrl(str3).addDappOptions(new DappOptions.DappOptionsBuild().addInjectZTron(z).build()).addWallerName(WalletUtils.getSelectedWallet().getWalletName()).build();
        boolean z2 = getViewType(getCurrentTabIndex()) != 1;
        String addSuffixToDAppUrl = addSuffixToDAppUrl(str);
        this.browserContent.startURL(DAppUrlUtils.wrapPhishingUrl(addSuffixToDAppUrl), z2, build);
        BrowserTab tabAt = getTabAt(getCurrentTabIndex());
        tabAt.setName(str2);
        tabAt.setHomeUrl(addSuffixToDAppUrl);
        tabAt.setImageUrl(str4);
        tabAt.setReplaceTitle(false);
        tabAt.setViewType(1);
        notifyObservers();
        try {
            Bundle bundle = new Bundle();
            bundle.putString("url", addSuffixToDAppUrl);
            bundle.putString("title", str2);
            AnalyticsHelper.logEvent(AnalyticsHelper.BrowserVisit.ENTER_BROWSER_VISIT, bundle);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void refresh() {
        BrowserContent browserContent = this.browserContent;
        if (browserContent != null) {
            browserContent.refresh();
        }
    }

    public void startNewTab() {
        startNewTab(BrowserConstant.DEFAULT_URL);
    }

    public void startNewTab(String str) {
        startNewTab(str, null);
    }

    public void startWithTAB_HISTORY(String str, String str2, String str3, String str4, boolean z) {
        if (getTabCount() >= 10) {
            IToast.getIToast().show(R.string.dapp_browser_max_count_warning);
            return;
        }
        String addSuffixToDAppUrl = addSuffixToDAppUrl(str);
        String wrapPhishingUrl = DAppUrlUtils.wrapPhishingUrl(addSuffixToDAppUrl);
        int i = this.mTabCounter;
        this.mTabCounter = i + 1;
        LogUtils.d(TAG, "startNewTab  mTabCounter: " + this.mTabCounter + "  currentTab  " + i);
        this.browserContent.startNewTab(i, wrapPhishingUrl, new WebOptions.WebOptionsBuild().addNeedOutside(false).addUseCache(true).addInjectTronweb(true).addDappAuthUrl(str3).addIconUrl(str4).addWebUrl(addSuffixToDAppUrl).addTitle(str2).addDappOptions(new DappOptions.DappOptionsBuild().addIcon(str4).addInjectZTron(z).build()).addWallerName(WalletUtils.getSelectedWallet().getWalletName()).build(), true);
        BrowserTab browserTab = new BrowserTab();
        browserTab.setReplaceTitle(false);
        browserTab.setCanBackToMain(true);
        if (!BrowserConstant.DEFAULT_URL.equals(wrapPhishingUrl)) {
            this.mBrowserTabs.add(i, browserTab);
            LogUtils.d(TAG, "startNewTab  updateViewType  : mTabCounter: " + this.mTabCounter + "  currentTab  " + this.currentTabIndex);
            updateViewType(i, 1);
            if (str2 == null) {
                str2 = "";
            }
            updateTabAt(i, str2, addSuffixToDAppUrl);
            return;
        }
        this.mBrowserTabs.add(i, browserTab);
    }

    public void startNewTab(String str, String str2) {
        startNewTab(str, str2, false, true, false, true);
    }

    public void startNewTab(String str, String str2, boolean z, boolean z2) {
        startNewTab(str, str2, z, true, false, z2);
    }

    public void startNewTab(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        if (getTabCount() >= 10) {
            IToast.getIToast().show(R.string.dapp_browser_max_count_warning);
            return;
        }
        String addSuffixToDAppUrl = addSuffixToDAppUrl(str);
        String wrapPhishingUrl = DAppUrlUtils.wrapPhishingUrl(addSuffixToDAppUrl);
        int i = this.mTabCounter;
        this.mTabCounter = i + 1;
        LogUtils.d(TAG, "startNewTab  mTabCounter: " + this.mTabCounter + "  currentTab  " + i);
        this.browserContent.startNewTab(i, wrapPhishingUrl, new WebOptions.WebOptionsBuild().addNeedOutside(false).addUseCache(true).addInjectTronweb(z2).addDappOptions(new DappOptions.DappOptionsBuild().addInjectZTron(z3).build()).addWallerName(WalletUtils.getSelectedWallet().getWalletName()).build());
        BrowserTab browserTab = new BrowserTab();
        browserTab.setReplaceTitle(z);
        browserTab.setCanBackToMain(z4);
        if (!BrowserConstant.DEFAULT_URL.equals(wrapPhishingUrl)) {
            this.mBrowserTabs.add(i, browserTab);
            LogUtils.d(TAG, "startNewTab  updateViewType  : mTabCounter: " + this.mTabCounter + "  currentTab  " + this.currentTabIndex);
            updateViewType(i, 1);
            updateTabAt(i, str2 == null ? "" : str2, addSuffixToDAppUrl);
        } else {
            this.mBrowserTabs.add(i, browserTab);
        }
        setTab(i);
        try {
            Bundle bundle = new Bundle();
            bundle.putString("url", addSuffixToDAppUrl);
            bundle.putString("title", str2);
            AnalyticsHelper.logEvent(AnalyticsHelper.BrowserVisit.ENTER_BROWSER_VISIT, bundle);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private String addSuffixToDAppUrl(String str) {
        return (StringTronUtil.isEmpty(str) || str.equals(BrowserConstant.DEFAULT_URL) || str.contains(BrowserConstant.SHARE_SUFFIX)) ? str : DAppUrlUtils.addUrlSuffix(str);
    }

    public void addObserver(Observer observer) {
        this.mObservers.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        this.mObservers.removeObserver(observer);
    }

    public void onDestroy() {
        manager = null;
        this.browserContent = null;
        this.mObservers.clear();
    }

    public void releaseCurrentContent(BrowserContent browserContent) {
        this.tabsMap.remove(browserContent);
        this.currentIndexMap.remove(browserContent);
        this.tabCounterMap.remove(browserContent);
        if (browserContent == null || !browserContent.equals(this.browserContent)) {
            return;
        }
        browserContent.onDestroy();
        this.browserContent = null;
    }

    public Bitmap getBitmap(int i) {
        if (getViewType(i) != 0) {
            ISnapshot iSnapshot = this.browserSnapshot;
            if (iSnapshot != null) {
                return iSnapshot.takeSnapshot(i, 0, 0);
            }
        } else if (this.browserContent.getWebView(i) != null && this.browserContent.getWebView(i).isLazyLoading()) {
            return BitmapFactory.decodeResource(AppContextUtil.getContext().getResources(), R.mipmap.web_tab_default_icon);
        } else {
            ISnapshot iSnapshot2 = this.defaultSnapshot;
            if (iSnapshot2 != null) {
                return iSnapshot2.takeSnapshot(i, 0, 0);
            }
        }
        return this.browserContent.getSnapshot(i);
    }

    public String getUrl(int i) {
        return getTabAt(i).getHomeUrl();
    }

    public void close(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this.mTabCounter) || i2 == 0) {
            return;
        }
        this.browserContent.closeTab(i);
        if (i < this.mBrowserTabs.size()) {
            this.mBrowserTabs.remove(i);
        }
        int i3 = this.mTabCounter;
        int i4 = i3 - 1;
        this.mTabCounter = i4;
        if (i4 == 0) {
            this.currentTabIndex = 0;
            this.mTabCounter = 1;
            this.browserContent.clearBrowserTabs();
            this.mBrowserTabs.clear();
            this.mBrowserTabs.add(this.currentTabIndex, new BrowserTab());
            updateViewType(this.currentTabIndex, 0);
        } else {
            int i5 = this.currentTabIndex;
            if (i5 > i) {
                this.currentTabIndex = i5 - 1;
            }
            if (this.currentTabIndex >= i4) {
                this.currentTabIndex = i3 - 2;
            }
        }
        setTab(this.currentTabIndex);
    }

    public void clearBrowserTabs() {
        this.mTabCounter = 1;
        this.currentTabIndex = 0;
        this.browserContent.clearBrowserTabs();
        this.mBrowserTabs.clear();
        this.mBrowserTabs.add(this.currentTabIndex, new BrowserTab());
        updateViewType(this.currentTabIndex, 0);
        setTab(this.currentTabIndex);
    }

    public void closeCurTab() {
        close(this.currentTabIndex);
    }

    public void moveItem(int i, int i2) {
        this.browserContent.moveItem(i, i2);
        this.mBrowserTabs.remove(i);
        this.mBrowserTabs.add(i2, this.mBrowserTabs.get(i));
        Iterator<BrowserTab> it = this.mBrowserTabs.iterator();
        int i3 = 0;
        while (it.hasNext() && !it.next().isActive()) {
            i3++;
        }
        setTab(i3);
    }

    public String getTitle(int i) {
        return getTabAt(i).getName();
    }

    public BrowserWebView getWebView() {
        BrowserContent browserContent = this.browserContent;
        if (browserContent != null) {
            return browserContent.getWebView();
        }
        return null;
    }

    public void checkAndReloadWebPage() {
        if (this.browserContent == null || this.mBrowserTabs.get(this.currentTabIndex).getViewType() != 1) {
            return;
        }
        this.browserContent.checkReload();
    }

    public void clearView() {
        BrowserContent browserContent = this.browserContent;
        if (browserContent != null) {
            browserContent.clearView();
        }
    }

    private void notifyObservers() {
        Iterator<Observer> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onTabChanged();
        }
    }

    public String getIconUrl(int i) {
        BrowserContent browserContent = this.browserContent;
        return browserContent != null ? browserContent.getIconUrl(i) : "";
    }

    public void updateViewType(int i, int i2) {
        getTabAt(i).setViewType(i2);
    }

    public int getViewType(int i) {
        return getTabAt(i).getViewType();
    }

    public BrowserTab getTabAt(int i) {
        try {
            return this.mBrowserTabs.get(i);
        } catch (Exception unused) {
            BrowserTab browserTab = new BrowserTab();
            browserTab.setName("");
            browserTab.setHomeUrl("");
            this.mBrowserTabs.add(i, browserTab);
            return browserTab;
        }
    }

    public void updateTabAt(int i, String str, String str2) {
        BrowserTab tabAt = getTabAt(i);
        tabAt.setHomeUrl(str2);
        tabAt.setName(str);
    }

    public void setReplaceTitle(int i, boolean z) {
        getTabAt(i).setReplaceTitle(z);
    }

    public void notifyWebTitleChanged(int i, final String str, String str2, String str3) {
        BrowserTab tabAt = getTabAt(i);
        if (tabAt.isReplaceTitle()) {
            tabAt.setName(str);
            tabAt.setHomeUrl(str2);
            tabAt.setImageUrl(str3);
        }
        if (tabAt.isActive() && tabAt.isReplaceTitle()) {
            Collection.-EL.stream(this.titleObservers).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    BrowserTabManager.lambda$notifyWebTitleChanged$0(str, (Consumer) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
    }

    public static void lambda$notifyWebTitleChanged$0(String str, Consumer consumer) {
        try {
            consumer.accept(str);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void addTitleChangedObserver(Consumer<String> consumer) {
        this.titleObservers.add(consumer);
    }

    public void removeTitleObserver(Consumer<String> consumer) {
        this.titleObservers.remove(consumer);
    }

    public void insertMostVisit(final String str, final String str2, final String str3) {
        Single.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return BrowserTabManager.lambda$insertMostVisit$2(str, str3, str2);
            }
        }).compose(RxSchedulers2.io_main_single()).subscribe(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                BrowserTabManager.lambda$insertMostVisit$3((DappBean) obj);
            }
        }, new MigrateActivityExternalSyntheticLambda2());
    }

    public static DappBean lambda$insertMostVisit$2(String str, String str2, String str3) throws Exception {
        try {
            final Uri parse = Uri.parse(HtmlUtil.urlTrim(str));
            Optional findFirst = Collection.-EL.stream(DAppMainPresenter.dAppListCache).filter(new Predicate() {
                public Predicate and(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                public Predicate negate() {
                    return Predicate$-CC.$default$negate(this);
                }

                public Predicate or(Predicate predicate) {
                    return Objects.requireNonNull(predicate);
                }

                @Override
                public final boolean test(Object obj) {
                    Uri uri = parse;
                    return Uri.parse(HtmlUtil.urlTrim(((DappBean) obj).getHomeUrl()));
                }
            }).findFirst();
            if (findFirst.isPresent()) {
                return (DappBean) findFirst.get();
            }
        } catch (Throwable th) {
            LogUtils.e(th);
        }
        DappBean dappBean = new DappBean();
        dappBean.setHomeUrl(str);
        dappBean.setImageUrl(str2);
        dappBean.setName(str3);
        return dappBean;
    }

    public static void lambda$insertMostVisit$3(DappBean dappBean) throws Exception {
        if (TextUtils.isEmpty(dappBean.getName())) {
            dappBean.setName(DAppUrlUtils.getHost(dappBean.getHomeUrl()));
        }
        MostVisitDAppController.getInstance().dAppVisited(dappBean.getHomeUrl(), dappBean.getName(), dappBean.getImageUrl(), dappBean.isAnonymous());
    }
}
