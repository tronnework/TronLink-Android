package com.tron.wallet.common.components.browser;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.finance.bean.Result;
import com.tron.wallet.business.finance.bean.ScanQROutput;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.business.tabdapp.browser.BrowserWebView;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabHistoryBean;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.db.wallet.WalletUtils;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
public class BrowserContent extends FrameLayout {
    private static final String TAG = "BrowserContent";
    private final AtomicInteger currentIndex;
    private Handler mHandler;
    private Consumer<Boolean> onWebScrollChanged;
    private BrowserWebView.RequestPermissionListener requestPermissionListener;
    private WebOptions webOptions;
    private final ArrayList<BrowserWebView> webViewArrayList;

    public void onDestroy() {
    }

    public void registerWebScrollListener(Consumer<Boolean> consumer) {
        this.onWebScrollChanged = consumer;
    }

    public void setRequestPermissionListener(BrowserWebView.RequestPermissionListener requestPermissionListener) {
        this.requestPermissionListener = requestPermissionListener;
    }

    public BrowserContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.webViewArrayList = new ArrayList<>();
        this.currentIndex = new AtomicInteger(-1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void init(Handler handler, WebOptions webOptions) {
        this.mHandler = handler;
        this.webOptions = webOptions;
        firstLoad();
    }

    public void firstLoad() {
        this.currentIndex.set(-1);
        startNewTab(0, BrowserConstant.DEFAULT_URL, this.webOptions);
    }

    public void checkReload() {
        String walletName = WalletUtils.getSelectedWallet().getWalletName();
        if (walletName == null || walletName.equals(this.webOptions.getWalletName())) {
            return;
        }
        this.webOptions.setWalletName(walletName);
        showTab(this.currentIndex.get());
    }

    public void startURL(String str, boolean z, WebOptions webOptions) {
        if (this.currentIndex.get() == -1) {
            startNewTab(0, str, webOptions);
        } else if (z) {
            try {
                int i = this.currentIndex.get();
                BrowserWebView remove = this.webViewArrayList.remove(i);
                addWebView(i, str, webOptions);
                removeView(remove);
                remove.removeAllViews();
                remove.destroy();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } else if (this.currentIndex.get() < 0 || this.currentIndex.get() >= getWebViewCount()) {
        } else {
            BrowserWebView browserWebView = this.webViewArrayList.get(this.currentIndex.get());
            browserWebView.saveCurrentWebOptions();
            webOptions.setWebUrl(str);
            browserWebView.initWithWebOptions(this.mHandler, webOptions);
            browserWebView.loadNewUrl(str);
        }
    }

    public void startNewTab(int i, String str, WebOptions webOptions) {
        startNewTab(i, str, webOptions, false);
    }

    public void startNewTab(int i, String str, WebOptions webOptions, boolean z) {
        if (this.currentIndex.get() >= 0 && this.currentIndex.get() < getWebViewCount()) {
            this.webViewArrayList.get(this.currentIndex.get()).setVisibility(View.GONE);
        }
        this.currentIndex.set(i);
        addWebView(i, str, webOptions, z);
    }

    private void addWebView(int i, String str, WebOptions webOptions) {
        addWebView(i, str, webOptions, false);
    }

    private void addWebView(int i, String str, WebOptions webOptions, boolean z) {
        BrowserWebView browserWebView = new BrowserWebView(getContext());
        addView(browserWebView, i, new FrameLayout.LayoutParams(-1, -1));
        browserWebView.getSettings().setJavaScriptEnabled(true);
        Consumer<Boolean> consumer = this.onWebScrollChanged;
        if (consumer != null) {
            browserWebView.setImmersiveModeCallback(consumer);
        }
        browserWebView.saveCurrentWebOptions();
        webOptions.setWebUrl(str);
        browserWebView.initWithWebOptions(this.mHandler, webOptions);
        browserWebView.setRequestPermissions(this.requestPermissionListener);
        this.webViewArrayList.add(i, browserWebView);
        LogUtils.d(TAG, "sparseArray put  " + this.currentIndex.get());
        browserWebView.setTabIndex(i);
        browserWebView.loadNewUrl(str, z);
    }

    public void showTab(int i) {
        if (this.currentIndex.get() >= 0 && this.currentIndex.get() < getWebViewCount()) {
            this.webViewArrayList.get(this.currentIndex.get()).setVisibility(View.GONE);
        }
        this.currentIndex.set(i);
        this.webViewArrayList.get(i).setVisibility(View.VISIBLE);
    }

    public BrowserWebView getWebView() {
        if (this.currentIndex.get() == -1 || this.webViewArrayList == null || this.currentIndex.get() >= getWebViewCount()) {
            return null;
        }
        BrowserWebView browserWebView = this.webViewArrayList.get(this.currentIndex.get());
        if (browserWebView.getTabIndex() != this.currentIndex.get()) {
            browserWebView.setTabIndex(this.currentIndex.get());
        }
        return browserWebView;
    }

    public BrowserWebView getWebView(int i) {
        if (this.currentIndex.get() == -1 || i >= this.webViewArrayList.size()) {
            return null;
        }
        return this.webViewArrayList.get(i);
    }

    public ArrayList<BrowserTabHistoryBean> getAllTabs() {
        ArrayList<BrowserTabHistoryBean> arrayList = new ArrayList<>();
        for (int i = 0; i < this.webViewArrayList.size(); i++) {
            BrowserWebView browserWebView = this.webViewArrayList.get(i);
            BrowserTabHistoryBean browserTabHistoryBean = new BrowserTabHistoryBean();
            browserTabHistoryBean.setTabIndex(browserWebView.getTabIndex());
            browserTabHistoryBean.setUrl(browserWebView.getUrl());
            browserTabHistoryBean.setDappAuthUrl(browserWebView.getDappAuthUrl());
            browserTabHistoryBean.setIcon(browserWebView.getIconUrl());
            browserTabHistoryBean.setTitle(browserWebView.getTitle());
            browserTabHistoryBean.setAnonymous(browserWebView.isAnonymous());
            if (i == this.currentIndex.get()) {
                browserTabHistoryBean.setIsCurrent(true);
                LogUtils.e("BrowserWebView", "currentIndex " + i);
            }
            arrayList.add(browserTabHistoryBean);
            LogUtils.e("BrowserWebView", "browserWebView URl = " + browserWebView.getUrl());
        }
        return arrayList;
    }

    public void loadJsResult(Result<ScanQROutput> result) {
        getWebView().loadJsResult(result);
    }

    public boolean back() {
        if (getWebView().canGoBack()) {
            getWebView().goBack();
            return true;
        }
        return false;
    }

    public void goForward() {
        if (getWebView().canGoForward()) {
            getWebView().goForward();
        }
    }

    public void goMain() {
        if (getWebView().canGoBack()) {
            getWebView().lambda$loadJs$4(BrowserConstant.DEFAULT_URL);
            clearView();
        }
    }

    private int getWebViewCount() {
        return this.webViewArrayList.size();
    }

    public synchronized void closeTab(int i) {
        BrowserWebView browserWebView = this.webViewArrayList.get(i);
        removeView(browserWebView);
        browserWebView.destroy();
        this.webViewArrayList.remove(i);
    }

    public void clearView() {
        getWebView().lambda$loadJs$4(BrowserConstant.DEFAULT_URL);
    }

    public void clearBrowserTabs() {
        removeAllViews();
        this.webViewArrayList.clear();
        this.currentIndex.set(-1);
        addFirstPage();
    }

    private void addFirstPage() {
        firstLoad();
    }

    public void refresh() {
        if (getWebView() != null) {
            getWebView().reload();
        }
    }

    public String getCurWebUrl() {
        return getWebView() != null ? getWebView().getUrl() : "";
    }

    public String getCurTitle() {
        return getWebView() != null ? getWebView().getTitle() : "";
    }

    public String getCurIconUrl() {
        return getWebView() != null ? getWebView().getIconUrl() : "";
    }

    public void moveItem(int i, int i2) {
        this.webViewArrayList.remove(i);
        this.webViewArrayList.add(i2, this.webViewArrayList.get(i));
    }

    public Bitmap getSnapshot(int i) {
        return this.webViewArrayList.get(i).getSnapshot();
    }

    public String getTitle(int i) {
        return this.webViewArrayList.get(i).getTitle();
    }

    public String getIconUrl(int i) {
        return this.webViewArrayList.get(i).getIconUrl();
    }
}
