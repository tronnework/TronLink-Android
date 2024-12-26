package com.tron.wallet.business.tabdapp.browser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.DownloadListener;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.finance.bean.Result;
import com.tron.wallet.business.finance.bean.ScanQROutput;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.business.tabdapp.browser.BrowserWebView;
import com.tron.wallet.business.tabdapp.browser.bean.BaseWebViewPageInfo;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.business.tabdapp.jsbridge.DappAcToJs;
import com.tron.wallet.business.tabdapp.jsbridge.DappJsListener;
import com.tron.wallet.business.tabdapp.jsbridge.DappJsScreenListener;
import com.tron.wallet.business.tabdapp.jsbridge.finance.FinancialAcToJs;
import com.tron.wallet.business.web.WebConstant;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.utils.BrowserUtil;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.DappJs;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.HtmlUtil;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
public class BrowserWebView extends MyWebView {
    private static final String TAG = "BrowserWebView";
    private float absOffsetX;
    private float absOffsetY;
    private AppCompatActivity activity;
    private FinancialAcToJs androidtoFinanceJs;
    private DappAcToJs androidtoJs;
    private String callbackMethod;
    private String dappAuthUrl;
    private boolean firstPagedLoaded;
    private String iconUrl;
    private Consumer<Boolean> immersiveModeCallback;
    private boolean injectTronWeb;
    private boolean isDebugAble;
    private boolean isError;
    private boolean isFinance;
    private boolean isPageFinished;
    private String lazyLoadUrl;
    private boolean lazyLoading;
    private Handler mHandler;
    float offsetX;
    float offsetY;
    private String originalTitle;
    private String originalUrl;
    private String outSideTitle;
    private RenderProcessListener renderProcessListener;
    private RequestPermissionListener requestPermissions;
    private int tabIndex;
    private String title;
    private ValueCallback<Uri[]> valueCallback;
    private WebOptions webOptions;
    private final Map<String, WebOptions> webOptionsCache;
    private String webUrl;

    public interface RenderProcessListener {
        void onRenderProcess(WebView webView, String str);
    }

    public interface RequestPermissionListener {
        void onRequestPermission(int i);

        void onRequestPermission(String str);
    }

    public DappAcToJs getAndroidJs() {
        return this.androidtoJs;
    }

    public String getDappAuthUrl() {
        return this.dappAuthUrl;
    }

    public RequestPermissionListener getRequestPermissions() {
        return this.requestPermissions;
    }

    public int getTabIndex() {
        return this.tabIndex;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public boolean isDebugAble() {
        return this.isDebugAble;
    }

    public boolean isLazyLoading() {
        return this.lazyLoading;
    }

    public void setDappAuthUrl(String str) {
        this.dappAuthUrl = str;
    }

    public void setImmersiveModeCallback(Consumer<Boolean> consumer) {
        this.immersiveModeCallback = consumer;
    }

    public void setLazyLoading(boolean z) {
        this.lazyLoading = z;
    }

    public void setRenderProcessListener(RenderProcessListener renderProcessListener) {
        this.renderProcessListener = renderProcessListener;
    }

    public void setRequestPermissions(RequestPermissionListener requestPermissionListener) {
        this.requestPermissions = requestPermissionListener;
    }

    public void setTabIndex(int i) {
        this.tabIndex = i;
    }

    public BrowserWebView(Context context) {
        super(context);
        this.webOptionsCache = new HashMap();
        this.offsetY = 0.0f;
        this.offsetX = 0.0f;
        this.injectTronWeb = true;
        this.isDebugAble = false;
        this.isPageFinished = false;
        this.isFinance = false;
        initial();
    }

    public BrowserWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.webOptionsCache = new HashMap();
        this.offsetY = 0.0f;
        this.offsetX = 0.0f;
        this.injectTronWeb = true;
        this.isDebugAble = false;
        this.isPageFinished = false;
        this.isFinance = false;
        initial();
    }

    public BrowserWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.webOptionsCache = new HashMap();
        this.offsetY = 0.0f;
        this.offsetX = 0.0f;
        this.injectTronWeb = true;
        this.isDebugAble = false;
        this.isPageFinished = false;
        this.isFinance = false;
        initial();
    }

    @Override
    public void lambda$loadJs$4(String str) {
        LogUtils.i(TAG, "loadUrl url  weburl: " + str);
        if (str == null || !str.equals(BrowserConstant.DEFAULT_URL)) {
            super.loadUrl(str);
        }
    }

    @Override
    public String getUrl() {
        return this.lazyLoading ? this.lazyLoadUrl : super.getUrl();
    }

    public void loadNewUrl(String str) {
        loadNewUrl(str, false);
    }

    public void loadNewUrl(String str, boolean z) {
        if (z) {
            this.lazyLoading = true;
            this.lazyLoadUrl = str;
        } else {
            lambda$loadJs$4(str);
        }
        this.webUrl = str;
        LogUtils.i(TAG, "loadNewUrl url  weburl: " + str);
    }

    @Override
    public void setVisibility(int i) {
        WebOptions webOptions;
        super.setVisibility(i);
        String walletName = WalletUtils.getSelectedWallet().getWalletName();
        if (getVisibility() == 0 && walletName != null && (webOptions = this.webOptions) != null && !walletName.equals(webOptions.getWalletName())) {
            this.webOptions.setWalletName(walletName);
            initWithWebOptions(this.mHandler, this.webOptions);
            if (this.lazyLoading) {
                this.lazyLoading = false;
                lambda$loadJs$4(this.lazyLoadUrl);
                return;
            }
            reload();
        } else if (getVisibility() == 0 && this.lazyLoading) {
            this.lazyLoading = false;
            lambda$loadJs$4(this.lazyLoadUrl);
        }
    }

    public void initWithWebOptions(Handler handler, WebOptions webOptions) {
        this.firstPagedLoaded = true;
        this.webOptions = webOptions;
        this.dappAuthUrl = webOptions.getDappAuthUrl();
        this.webUrl = webOptions.getWebUrl();
        this.title = webOptions.getTitle();
        this.iconUrl = webOptions.getIcon();
        this.isFinance = webOptions.isFinance();
        this.mHandler = handler;
        this.injectTronWeb = webOptions.isInjectTronWeb();
        String walletName = webOptions.getWalletName();
        if (StringTronUtil.isEmpty(this.webUrl)) {
            if (StringTronUtil.isEmpty(webOptions.getWebUrl())) {
                this.webUrl = BrowserConstant.DEFAULT_URL;
            } else {
                this.webUrl = webOptions.getWebUrl();
            }
        }
        if (this.isFinance) {
            this.androidtoFinanceJs = new FinancialAcToJs(this.activity, handler, TextUtils.isEmpty(this.dappAuthUrl) ? this.webUrl : this.dappAuthUrl, this, this.title, webOptions.getDappOptions().getIcon());
            AppCompatActivity appCompatActivity = this.activity;
            String str = TextUtils.isEmpty(this.dappAuthUrl) ? this.webUrl : this.dappAuthUrl;
            String str2 = this.title;
            String icon = webOptions.getDappOptions().getIcon();
            if (StringTronUtil.isEmpty(walletName)) {
                walletName = SpAPI.THIS.getSelectedWallet();
            }
            this.androidtoJs = new DappAcToJs(appCompatActivity, handler, str, this, str2, icon, walletName, webOptions.getCode(), this.isFinance);
        } else {
            AppCompatActivity appCompatActivity2 = this.activity;
            String str3 = TextUtils.isEmpty(this.dappAuthUrl) ? this.webUrl : this.dappAuthUrl;
            String str4 = this.title;
            String icon2 = webOptions.getDappOptions().getIcon();
            if (StringTronUtil.isEmpty(walletName)) {
                walletName = SpAPI.THIS.getSelectedWallet();
            }
            this.androidtoJs = new DappAcToJs(appCompatActivity2, handler, str3, this, str4, icon2, walletName, webOptions.getCode());
        }
        if (TextUtils.isEmpty(this.dappAuthUrl)) {
            this.dappAuthUrl = this.webUrl;
        }
        this.androidtoJs.setOnHeaderNotifyListener(new DappAcToJs.OnHeaderNotifyListener() {
            @Override
            public final void onHeaderNotify(String str5) {
                lambda$initWithWebOptions$0(str5);
            }
        });
        this.androidtoJs.setDappJsListener(new DappJsListener() {
            @Override
            public final void scanQRCode(String str5) {
                lambda$initWithWebOptions$1(str5);
            }
        });
        this.androidtoJs.setDappJsScreenListener(new DappJsScreenListener() {
            @Override
            public final void onScreenModeSet(String str5, String str6) {
                lambda$initWithWebOptions$3(str5, str6);
            }
        });
        if (this.isFinance) {
            addJavascriptInterface(this.androidtoFinanceJs, "fTron");
            addJavascriptInterface(this.androidtoJs, "iTron");
        } else {
            addJavascriptInterface(this.androidtoJs, "iTron");
        }
        WebSettings settings = getSettings();
        if (webOptions != null) {
            settings.setCacheMode(2);
        }
        if (this.webOptionsCache.containsKey(webOptions.getWebUrl())) {
            return;
        }
        this.webOptionsCache.put(webOptions.getWebUrl(), webOptions);
    }

    public void lambda$initWithWebOptions$0(String str) {
        if (StringTronUtil.isEmpty(this.webUrl)) {
            return;
        }
        lambda$loadJs$4(this.webUrl);
    }

    public void lambda$initWithWebOptions$1(String str) {
        this.callbackMethod = str;
        RequestPermissionListener requestPermissionListener = this.requestPermissions;
        if (requestPermissionListener != null) {
            requestPermissionListener.onRequestPermission(100);
        }
    }

    public void lambda$initWithWebOptions$3(final String str, final String str2) {
        post(new Runnable() {
            @Override
            public final void run() {
                lambda$initWithWebOptions$2(str, str2);
            }
        });
    }

    public void lambda$initWithWebOptions$2(String str, String str2) {
        LogUtils.d(TAG, getUrl());
        if (StringTronUtil.isEmpty(str) || !HtmlUtil.sameUrl(str, getUrl()) || getScreenMode().equals(str2)) {
            return;
        }
        setScreenMode(str2);
        if (StringTronUtil.isEmpty(this.webUrl)) {
            return;
        }
        reload();
    }

    private void initial() {
        this.activity = (AppCompatActivity) getContext();
        setLayerType(2, null);
        getSettings().setJavaScriptEnabled(true);
        if (!StringTronUtil.isEmpty(this.webUrl)) {
            lambda$loadJs$4(this.webUrl);
        }
        setWebViewClient(new WebViewClient() {
            private String lastFinishedUrl;

            @Override
            public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
                LogUtils.i(BrowserWebView.TAG, "loadurl  onRenderProcessGone url: " + renderProcessGoneDetail.toString());
                if (renderProcessListener != null) {
                    renderProcessListener.onRenderProcess(webView, this.lastFinishedUrl);
                    return true;
                }
                return super.onRenderProcessGone(webView, renderProcessGoneDetail);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                LogUtils.i(BrowserWebView.TAG, "shouldOverrideUrlLoading url: " + str);
                iconUrl = null;
                originalUrl = null;
                if (str.startsWith(PullConstants.SCHEME)) {
                    return true;
                }
                if ((!str.startsWith(ChainUtil.Request_HTTP) && !str.startsWith("https://")) || !str.endsWith(".pdf")) {
                    if (DAppUrlUtils.isPhishingUrl(BrowserWebView.super.getUrl()) && !TextUtils.equals(webOptions.getWebUrl(), str)) {
                        webOptions.setWebUrl(str);
                        BrowserWebView browserWebView = BrowserWebView.this;
                        browserWebView.initWithWebOptions(browserWebView.mHandler, webOptions);
                        return false;
                    } else if (str.startsWith(ChainUtil.Request_HTTP) || str.startsWith("https://")) {
                        return false;
                    } else {
                        try {
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }
                        return true;
                    }
                }
                webView.loadUrl("file:///android_asset/pdf.html?" + str);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String str) {
                String str2;
                super.onPageFinished(webView, str);
                LogUtils.i(BrowserWebView.TAG, "onPageFinished url: " + webView.getUrl() + "  title:  " + webView.getTitle() + "  OriginalUrl:  " + webView.getOriginalUrl());
                originalUrl = webView.getOriginalUrl();
                title = webView.getTitle();
                if (StringTronUtil.isEmpty(webUrl)) {
                    webUrl = webView.getOriginalUrl();
                }
                isPageFinished = true;
                if (mHandler != null && !isError && webView.getUrl() != null && !webView.getUrl().equals(BrowserConstant.DEFAULT_URL) && ((str2 = this.lastFinishedUrl) == null || !str2.equals(str))) {
                    parseFaviconUrl(new MyWebView.OnResourceReceivedListener() {
                        @Override
                        public void onVisited(String str3, String str4, String str5) {
                            if (!StringTronUtil.isEmpty(str5)) {
                                iconUrl = str5;
                            }
                            mHandler.sendMessage(mHandler.obtainMessage(1024, new BaseWebViewPageInfo(webUrl, str4, getIconUrl(), getUrl(), firstPagedLoaded)));
                            if (firstPagedLoaded) {
                                firstPagedLoaded = false;
                            }
                        }
                    });
                }
                this.lastFinishedUrl = str;
                if (mHandler != null) {
                    mHandler.sendMessage(mHandler.obtainMessage(BrowserConstant.PAGE_FINISHED, Boolean.valueOf(isError)));
                }
            }

            @Override
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                LogUtils.i(BrowserWebView.TAG, "onPageStarted url: " + str + " favicon ");
                this.lastFinishedUrl = null;
                if (StringTronUtil.isEmpty(webUrl)) {
                    webUrl = str;
                }
                iconUrl = null;
                originalUrl = null;
                isError = false;
                super.onPageStarted(webView, str, bitmap);
                String screenModel = androidtoJs.getScreenModel(str);
                if (getScreenMode().equals(screenModel)) {
                    return;
                }
                setScreenMode(screenModel);
            }

            @Override
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                LogUtils.i(BrowserWebView.TAG, "onReceivedError url: " + i);
                ToastUtil.getInstance().show(getContext(), R.string.dapp_error);
                isError = true;
                BrowserTabManager.getInstance().notifyWebTitleChanged(getTabIndex(), getContext().getString(R.string.web_browser), getUrl(), getIconUrl());
                super.onReceivedError(webView, i, str, str2);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }

            @Override
            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                LogUtils.d(BrowserWebView.TAG, "onReceivedHttpError:  " + webResourceResponse.getStatusCode());
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        });
        setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                WebView.HitTestResult hitTestResult = getHitTestResult();
                if (hitTestResult.getType() != 7) {
                    return false;
                }
                LogUtils.i(BrowserWebView.TAG, "hitTestResult.getType() " + hitTestResult.getType());
                if (mHandler != null) {
                    mHandler.sendMessage(mHandler.obtainMessage(BrowserConstant.WEBVIEW_LINK_LONGPRESS, hitTestResult.getExtra()));
                    return false;
                }
                return false;
            }
        });
        setProgressChanged(new fun3());
        setShowFileChooser(new MyWebView.ShowFileChooser() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                valueCallback = valueCallback;
                if (requestPermissions != null) {
                    requestPermissions.onRequestPermission(101);
                    return true;
                }
                return true;
            }
        });
        setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                try {
                    getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Throwable th) {
                    SentryUtil.captureException(th);
                }
            }
        });
        setOnResourceReceivedListener(new MyWebView.OnResourceReceivedListener() {
            @Override
            public void onVisited(String str, String str2, String str3) {
                LogUtils.d(BrowserWebView.TAG, "favicon: " + str3);
                LogUtils.i(BrowserWebView.TAG, "onVisited url: " + str + "  title:  " + str2 + "  favicon:  " + str3);
                if (!StringTronUtil.isEmpty(str3)) {
                    iconUrl = str3;
                }
                originalTitle = str2;
                if (StringTronUtil.isEmpty(title) && !StringTronUtil.isEmpty(str2)) {
                    title = str2;
                }
                if (isFinance) {
                    return;
                }
                BrowserTabManager.getInstance().notifyWebTitleChanged(getTabIndex(), BrowserWebView.super.getTitle(), getUrl(), getIconUrl());
            }
        });
    }

    public class fun3 implements MyWebView.ProgressChanged {
        fun3() {
        }

        @Override
        public void onProgressChanged(final WebView webView, int i) {
            AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                @Override
                public final void doInUIThread() {
                    BrowserWebView.3.this.lambda$onProgressChanged$0(webView);
                }
            });
        }

        public void lambda$onProgressChanged$0(WebView webView) {
            LogUtils.d(BrowserWebView.TAG, "injectTronWeb :" + injectTronWeb + " evaluateJavascript");
            if (injectTronWeb) {
                webView.evaluateJavascript(DappJs.THIS.getJsText(), null);
                if (isDebugAble) {
                    webView.evaluateJavascript(DappJs.THIS.getvConsoleJsText(), null);
                }
            }
        }
    }

    @Override
    public void onMeasure(int i, int i2) {
        if (getScreenMode().equals(WebConstant.LANDSCAPE)) {
            int size = View.MeasureSpec.getSize(i2) - UIUtils.dip2px(80.0f);
            View.MeasureSpec.getSize(i);
            int screenWidth = UIUtils.getScreenWidth(getContext());
            setTranslationX(screenWidth);
            setMeasuredDimension(size, screenWidth);
            return;
        }
        setTranslationX(0.0f);
        super.onMeasure(i, i2);
    }

    public void loadJs(String str, Result result) {
        final String str2 = "javascript:" + str + "('" + GsonUtils.toGsonString(result) + "')";
        post(new Runnable() {
            @Override
            public final void run() {
                lambda$loadJs$4(str2);
            }
        });
    }

    public void loadJsResult(Result<ScanQROutput> result) {
        loadJs(this.callbackMethod, result);
    }

    @Override
    public String getTitle() {
        return !StringTronUtil.isEmpty(this.originalTitle) ? this.originalTitle : StringTronUtil.isEmpty(this.title) ? StringTronUtil.equals(this.webUrl, BrowserConstant.DEFAULT_URL) ? AppContextUtil.getContext().getString(R.string.browser_start_page) : this.webUrl : this.title;
    }

    public String getIconUrl() {
        return StringTronUtil.isEmpty(this.iconUrl) ? BrowserUtil.getIcon(this.originalUrl) : this.iconUrl;
    }

    public Bitmap getSnapshot() {
        try {
            if (this.isPageFinished) {
                return Bitmap.createBitmap(getDrawingCache());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeResource(getResources(), R.mipmap.web_tab_default_icon);
    }

    public void setDebugAble(boolean z) {
        this.isDebugAble = z;
        reload();
    }

    public void onReceiveValue(Uri uri) {
        ValueCallback<Uri[]> valueCallback;
        if (uri == null || (valueCallback = this.valueCallback) == null) {
            return;
        }
        valueCallback.onReceiveValue(new Uri[]{uri});
    }

    @Override
    public void goBack() {
        reInitWebUrlIfNeed(true);
        super.goBack();
    }

    @Override
    public void goForward() {
        reInitWebUrlIfNeed(false);
        super.goForward();
    }

    private void reInitWebUrlIfNeed(boolean z) {
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        final String url = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() + (z ? -1 : 1)).getUrl();
        Object[] objArr = new Object[2];
        objArr[0] = z ? "backward" : "forward";
        objArr[1] = url;
        LogUtils.w(TAG, String.format("goBack, %s url = %s", objArr));
        Collection.-EL.stream(this.webOptionsCache.keySet()).filter(new Predicate() {
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
                boolean equals;
                equals = TextUtils.equals(StringTronUtil.getHost((String) obj), StringTronUtil.getHost(url));
                return equals;
            }
        }).findFirst().ifPresent(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$reInitWebUrlIfNeed$6((String) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    public void lambda$reInitWebUrlIfNeed$6(String str) {
        WebOptions webOptions = this.webOptionsCache.get(str);
        if (webOptions == null) {
            return;
        }
        if (webOptions != this.webOptions) {
            initWithWebOptions(this.mHandler, webOptions);
        }
        LogUtils.w(TAG, "find a webOptions, webOptions.getUrl = " + webOptions.getWebUrl());
    }

    public void saveCurrentWebOptions() {
        if (this.webOptions == null || getUrl() == null || TextUtils.equals(getUrl(), BrowserConstant.DEFAULT_URL)) {
            return;
        }
        this.webOptionsCache.put(getUrl(), this.webOptions);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Consumer<Boolean> consumer;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.offsetY = motionEvent.getRawY();
            this.offsetX = motionEvent.getRawX();
        } else if (action == 2) {
            float rawY = motionEvent.getRawY() - this.offsetY;
            motionEvent.getRawX();
            int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
            if (rawY < 0.0f && Math.abs(rawY) >= scaledTouchSlop && (consumer = this.immersiveModeCallback) != null) {
                consumer.accept(true);
            }
            this.offsetY = motionEvent.getRawY();
            this.offsetX = motionEvent.getRawX();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FinancialAcToJs financialAcToJs = this.androidtoFinanceJs;
        if (financialAcToJs != null) {
            financialAcToJs.clear();
        }
    }

    public boolean isAnonymous() {
        WebOptions webOptions = this.webOptions;
        return webOptions != null && webOptions.getDappOptions().isInjectZTron();
    }

    public boolean isInjectTronWeb() {
        WebOptions webOptions = this.webOptions;
        if (webOptions != null) {
            return webOptions.isInjectTronWeb();
        }
        return false;
    }
}
