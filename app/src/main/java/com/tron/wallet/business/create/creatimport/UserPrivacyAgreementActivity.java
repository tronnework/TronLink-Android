package com.tron.wallet.business.create.creatimport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.ledger.search.SearchLedgerActivity;
import com.tron.wallet.business.samsung.SamsungSDKWrapper;
import com.tron.wallet.business.walletmanager.createwallet.CreateWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.AddWatchWalletActivity;
import com.tron.wallet.business.walletmanager.importwallet.ImportSamsungActivity;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletActivity;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.ChainUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityUserPrivacyAgreementBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.tron.walletserver.Wallet;
public class UserPrivacyAgreementActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String FROM_SETTING = "FROM_SETTING";
    private static final String URL = "https://dapp.tronlink.org/#/useragreement";
    private static final String URL_EN = "https://dapp.tronlink.org/#/useragreementen";
    private ActivityUserPrivacyAgreementBinding binding;
    Button btAccept;
    ImageView ivback;
    private boolean mIsFromSetting;
    LinearLayout root;
    private RxManager rxManager;
    MyWebView webview;

    public static void start(Context context, boolean z) {
        Intent intent = new Intent(context, UserPrivacyAgreementActivity.class);
        intent.putExtra(FROM_SETTING, z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        ActivityUserPrivacyAgreementBinding inflate = ActivityUserPrivacyAgreementBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        setHeaderBar(getString(R.string.user_agreement));
        mappingId();
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.root = this.binding.root;
        this.ivback = this.binding.ivCommonLeft;
        this.btAccept = this.binding.btAccept;
        this.webview = this.binding.webview;
    }

    @Override
    protected void processData() {
        TouchDelegateUtils.expandViewTouchDelegate(this.ivback, 10);
        if (getIntent() != null) {
            boolean booleanExtra = getIntent().getBooleanExtra(FROM_SETTING, false);
            this.mIsFromSetting = booleanExtra;
            if (booleanExtra) {
                this.btAccept.setVisibility(View.GONE);
            }
        }
        loadContent();
        AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.ENTER_TERM_PAGE);
        if (this.rxManager == null) {
            this.rxManager = new RxManager();
        }
        this.rxManager.on(Event.JumpToMain, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
    }

    public void lambda$processData$0(Object obj) throws Exception {
        if (isFinishing() && isDestroyed()) {
            return;
        }
        finish();
    }

    private void loadContent() {
        String str = LanguageUtils.languageZH(AppContextUtil.getContext()) ? "file:///android_asset/agreement.html" : "file:///android_asset/agreement_en.html";
        this.webview.setVisibility(View.VISIBLE);
        this.webview.loadUrl(str);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String str2) {
                if ((str2.startsWith(ChainUtil.Request_HTTP) || str2.startsWith("https://")) && str2.endsWith(".pdf")) {
                    webView.loadUrl("file:///android_asset/pdf.html?" + str2);
                    return true;
                } else if (str2.startsWith(ChainUtil.Request_HTTP) || str2.startsWith("https://")) {
                    return false;
                } else {
                    try {
                        mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str2)));
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                    return true;
                }
            }
        });
        webViewScroolChangeListener();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                int id = view.getId();
                if (id == R.id.bt_accept) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.StartPage.CLICK_TERM_PAGE_AGREE);
                    SpAPI.THIS.setReadUserAgreement();
                    goNext(getIntent().getIntExtra("go", 1));
                    exit();
                } else if (id == R.id.iv_common_left) {
                    finish();
                } else if (id != R.id.iv_common_right) {
                } else {
                    CommonWebActivityV3.start((Context) UserPrivacyAgreementActivity.this, LanguageUtils.languageZH(AppContextUtil.getContext()) ? UserPrivacyAgreementActivity.URL : UserPrivacyAgreementActivity.URL_EN, (String) null, true, new WebOptions.WebOptionsBuild().addNeedOutside(true).build());
                }
            }
        };
        this.binding.btAccept.setOnClickListener(noDoubleClickListener);
        this.binding.root.setOnClickListener(noDoubleClickListener);
        this.binding.ivCommonLeft.setOnClickListener(noDoubleClickListener);
        this.binding.ivCommonRight.setOnClickListener(noDoubleClickListener);
    }

    public void goNext(int i) {
        if (i == 1) {
            CreateWalletActivity.start(this.mContext, false);
        } else if (i == 2) {
            ImportWalletActivity.start(this, false);
        } else if (i == 4) {
            go(AddWatchWalletActivity.class);
        } else if (i == 7) {
            if (TextUtils.isEmpty(SamsungSDKWrapper.checkSeedHashEmpty(this, false))) {
                SamsungSDKWrapper.getoSamsungKeystoreWallet(this, 16);
            } else {
                importSamsungWallet();
            }
        } else if (i == 8) {
            SearchLedgerActivity.start(this);
        } else if (i != 9) {
        } else {
            PairColdWalletActivity.start(getIContext(), 0);
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    private void webViewScroolChangeListener() {
        this.webview.setOnCustomScroolChangeListener(new MyWebView.ScrollInterface() {
            @Override
            public void onSChanged(int i, int i2, int i3, int i4) {
                if ((webview.getContentHeight() * webview.getScale()) - (webview.getHeight() + webview.getScrollY()) < UIUtils.dip2px(10.0f)) {
                    btAccept.setEnabled(true);
                    btAccept.setText(R.string.agree_to_not_ask_again);
                    return;
                }
                btAccept.setEnabled(false);
                btAccept.setText(R.string.swipe_all_terms);
            }
        });
    }

    public String getFromAssets(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResources().getAssets().open(str)));
            String str2 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return str2;
                }
                str2 = str2 + readLine;
            }
        } catch (Exception e) {
            LogUtils.e(e);
            return "";
        }
    }

    private void importSamsungWallet() {
        SamsungSDKWrapper.importSamsungWallet(this, new SamsungSDKWrapper.getAddressCallBack() {
            @Override
            public void onSuccess(String str, String str2) {
                Wallet wallet;
                for (String str3 : WalletUtils.getWalletNames()) {
                    if (!TextUtils.isEmpty(str3) && (wallet = WalletUtils.getWallet(str3)) != null && wallet.getAddress().equals(str2)) {
                        RxBus.getInstance().post(Event.SELECTEDWALLET, str3);
                        WalletUtils.setSelectedWallet(str3);
                        runOnUIThread(new OnMainThread() {
                            @Override
                            public void doInUIThread() {
                                toast(getString(R.string.samsung_wallet_existed));
                            }
                        });
                        return;
                    }
                }
                ImportSamsungActivity.start(UserPrivacyAgreementActivity.this, str2);
            }

            @Override
            public void onFailure(String str) {
                IToast.getIToast().setImage(R.mipmap.broadcast_fail).show(getString(R.string.sx_import_fail));
            }
        });
    }
}
