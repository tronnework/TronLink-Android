package com.tron.wallet.common.components;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.web.WebConstant;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.HtmlUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tronlinkpro.wallet.R;
public class MyWebView extends WebView {
    private String cookie;
    private CookieManager cookieManager;
    Context mContext;
    private ProgressBar mProgressBar;
    public ScrollInterface mScrollInterface;
    private OnResourceReceivedListener onResourceReceivedListener;
    private OnResourceReceivedListener onResourceReceivedListenerOnce;
    private ProgressChanged progressChanged;
    private String screenMode;
    private ShowFileChooser showFileChooser;

    public interface OnResourceReceivedListener {
        void onVisited(String str, String str2, String str3);
    }

    public interface ProgressChanged {
        void onProgressChanged(WebView webView, int i);
    }

    public interface ScrollInterface {
        void onSChanged(int i, int i2, int i3, int i4);
    }

    public interface ShowFileChooser {
        boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams);
    }

    public String getScreenMode() {
        return this.screenMode;
    }

    public void setOnCustomScroolChangeListener(ScrollInterface scrollInterface) {
        this.mScrollInterface = scrollInterface;
    }

    public void setOnResourceReceivedListener(OnResourceReceivedListener onResourceReceivedListener) {
        this.onResourceReceivedListener = onResourceReceivedListener;
    }

    public void setProgressChanged(ProgressChanged progressChanged) {
        this.progressChanged = progressChanged;
    }

    public void setShowFileChooser(ShowFileChooser showFileChooser) {
        this.showFileChooser = showFileChooser;
    }

    public MyWebView(Context context) {
        super(context);
        this.screenMode = WebConstant.PORTRAIT;
        this.mContext = context;
        init();
    }

    public MyWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.screenMode = WebConstant.PORTRAIT;
        this.mContext = context;
        init();
    }

    public MyWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.screenMode = WebConstant.PORTRAIT;
        this.mContext = context;
        init();
    }

    @Override
    public void onMeasure(int i, int i2) {
        if (this.screenMode.equals(WebConstant.LANDSCAPE)) {
            int size = View.MeasureSpec.getSize(i2);
            View.MeasureSpec.getSize(i);
            int screenWidth = UIUtils.getScreenWidth(this.mContext);
            setTranslationX(screenWidth);
            setMeasuredDimension(size, screenWidth);
            return;
        }
        setTranslationX(0.0f);
        super.onMeasure(i, i2);
    }

    public void setScreenMode(String str) {
        if (this.screenMode.equals(str)) {
            return;
        }
        this.screenMode = str;
        int hashCode = str.hashCode();
        if (hashCode != -77725029) {
            if (hashCode == 1511893915) {
                str.equals(WebConstant.PORTRAIT);
            }
        } else if (str.equals(WebConstant.LANDSCAPE)) {
            setPivotX(0.0f);
            setPivotY(0.0f);
            setRotation(90.0f);
            RxBus.getInstance().post(Event.DAPP_SET_SCREEN_MODE, str);
        }
        setPivotX(0.0f);
        setPivotY(0.0f);
        setRotation(0.0f);
        RxBus.getInstance().post(Event.DAPP_SET_SCREEN_MODE, str);
    }

    public void parseFaviconUrl() {
        loadUrl("javascript:window.iTron.getSource('Resource<head>'+document.getElementsByTagName('head')[0].innerHTML+'</head>');");
    }

    public void parseFaviconUrl(OnResourceReceivedListener onResourceReceivedListener) {
        loadUrl("javascript:window.iTron.getSource('Visited<head>'+document.getElementsByTagName('head')[0].innerHTML+'</head>');");
        this.onResourceReceivedListenerOnce = onResourceReceivedListener;
    }

    public void onGetHtmlSource(String str) {
        if (str != null) {
            String findFavicon = HtmlUtil.findFavicon(getUrl(), str);
            if (this.onResourceReceivedListener != null && str.startsWith(AnalyticsHelper.ResourcePage.ENTER_RESOURCE_PAGE)) {
                this.onResourceReceivedListener.onVisited(getUrl(), super.getTitle(), findFavicon);
            } else if (this.onResourceReceivedListenerOnce == null || !str.startsWith("Visited")) {
            } else {
                this.onResourceReceivedListenerOnce.onVisited(getUrl(), super.getTitle(), findFavicon);
                this.onResourceReceivedListenerOnce = null;
            }
        }
    }

    private void init() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setSaveFormData(true);
        settings.setSavePassword(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportMultipleWindows(false);
        settings.setBuiltInZoomControls(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setBlockNetworkImage(false);
        settings.setDomStorageEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        try {
            this.mContext.getCacheDir().getAbsolutePath();
            settings.setCacheMode(-1);
            settings.setAllowFileAccess(true);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        setDrawingCacheEnabled(true);
        setLongClickable(true);
        setScrollbarFadingEnabled(true);
        setScrollBarStyle(0);
        this.mProgressBar = new ProgressBar(this.mContext, null, 16842872);
        this.mProgressBar.setLayoutParams(new LinearLayout.LayoutParams(-1, 8));
        this.mProgressBar.setProgressDrawable(this.mContext.getResources().getDrawable(R.drawable.web_progress_bar));
        addView(this.mProgressBar);
        setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) this.mProgressBar.getLayoutParams();
        layoutParams.x = i;
        layoutParams.y = i2;
        this.mProgressBar.setLayoutParams(layoutParams);
        super.onScrollChanged(i, i2, i3, i4);
        ScrollInterface scrollInterface = this.mScrollInterface;
        if (scrollInterface != null) {
            scrollInterface.onSChanged(i, i2, i3, i4);
        }
    }

    @Override
    public void setOverScrollMode(int i) {
        try {
            super.setOverScrollMode(i);
        } catch (Throwable th) {
            LogUtils.e(th);
        }
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        public WebChromeClient() {
        }

        @Override
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            ((MyWebView) webView).parseFaviconUrl();
        }

        @Override
        public void onProgressChanged(WebView webView, int i) {
            if (progressChanged != null) {
                progressChanged.onProgressChanged(webView, i);
            }
            if (i == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                if (mProgressBar.getVisibility() == 8) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(i);
            }
            super.onProgressChanged(webView, i);
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (showFileChooser == null) {
                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }
            return showFileChooser.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }
    }
}
