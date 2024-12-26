package com.tron.wallet.business.tabdapp.browser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tronlinkpro.wallet.R;
public class CommonWebViewClient extends WebViewClient {
    private static final String TAG = "CommonWebViewClient";
    private boolean isError;
    private final Context mContext;
    private final WebView webview;

    public CommonWebViewClient(Context context, WebView webView) {
        this.mContext = context;
        this.webview = webView;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        LogUtils.i(TAG, "shouldOverrideUrlLoading url: " + str);
        if ((str.startsWith(ChainUtil.Request_HTTP) || str.startsWith("https://")) && str.endsWith(".pdf")) {
            webView.loadUrl("file:///android_asset/pdf.html?" + str);
            return true;
        } else if (str.startsWith(ChainUtil.Request_HTTP) || str.startsWith("https://")) {
            return false;
        } else {
            try {
                this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e) {
                LogUtils.e(e);
            }
            return true;
        }
    }

    @Override
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.isError) {
            return;
        }
        this.webview.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.isError = true;
        ToastUtil.getInstance().show(this.mContext, R.string.dapp_error);
        super.onReceivedError(webView, i, str, str2);
    }

    @Override
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
    }

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
}
