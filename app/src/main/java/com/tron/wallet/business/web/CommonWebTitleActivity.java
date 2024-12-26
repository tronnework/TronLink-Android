package com.tron.wallet.business.web;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.tabdapp.jsbridge.BaseAndroidtoJs;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.databinding.AcHelpBinding;
import com.tronlinkpro.wallet.R;
public class CommonWebTitleActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    public static final String TITLE = "title";
    public static final String URL = "url";
    private AcHelpBinding binding;
    private boolean isError;
    NoNetView noNetView;
    private String title;
    private String url;
    MyWebView webview;

    public static void start(Context context, String str, String str2) {
        Intent intent = new Intent(context, CommonWebTitleActivity.class);
        intent.putExtra("url", str);
        intent.putExtra("title", str2);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcHelpBinding inflate = AcHelpBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.webview = this.binding.webview;
        this.noNetView = this.binding.noNetView;
    }

    public void getData() {
        Intent intent = getIntent();
        this.title = intent.getStringExtra("title");
        this.url = intent.getStringExtra("url");
    }

    @Override
    protected void processData() {
        getData();
        setHeaderBar(this.title);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        this.webview.setProgressChanged(new MyWebView.ProgressChanged() {
            @Override
            public final void onProgressChanged(WebView webView, int i) {
                lambda$processData$1(webView, i);
            }
        });
        this.webview.getSettings().setCacheMode(2);
        this.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return false;
            }

            @Override
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (!isError) {
                    dismissLoadingPage();
                    noNetView.setVisibility(View.GONE);
                    return;
                }
                showErrorPage();
            }

            @Override
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                showErrorPage();
                isError = true;
            }
        });
        this.webview.loadUrl(this.url);
        this.webview.addJavascriptInterface(new BaseAndroidtoJs(this, null, this.url, this.webview), "iTron");
    }

    public void lambda$processData$0(View view) {
        onRefreshButtonClick();
    }

    public void lambda$processData$1(WebView webView, int i) {
        if (this.noNetView.getVisibility() != 0) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.noNetView.getLayoutParams();
        if (i >= 100) {
            layoutParams.topMargin = 0;
        } else if (i > 0) {
            layoutParams.topMargin = 8;
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onReLoadButtonClick() {
        super.onReLoadButtonClick();
        this.isError = false;
        this.webview.loadUrl(this.url);
    }

    @Override
    public void onRefreshButtonClick() {
        super.onRefreshButtonClick();
        this.isError = false;
        this.webview.reload();
    }

    @Override
    public void showErrorPage() {
        this.noNetView.setVisibility(View.VISIBLE);
        ToastAsBottom(R.string.dapp_error);
    }
}
