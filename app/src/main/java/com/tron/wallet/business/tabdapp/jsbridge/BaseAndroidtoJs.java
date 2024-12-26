package com.tron.wallet.business.tabdapp.jsbridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.business.tabdapp.browser.BrowserWebView;
import com.tron.wallet.business.tabdapp.browser.controller.MostVisitDAppController;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.business.tabdapp.jsbridge.DappAuthorizedPopWindow;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.DappOptions;
import com.tron.wallet.business.web.WebConstant;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.ChannelUtils;
import com.tron.wallet.common.utils.HtmlUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.Controller.DappAuthorizedController;
import com.tron.wallet.db.Controller.DappBlackListController;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.DAppNonOfficialAuthorizedProjectBean;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.tron.walletserver.Wallet;
public class BaseAndroidtoJs {
    private static final String MainNet = "MainNet";
    private static final String NileNet = "NileNet";
    private static final String ShastaNet = "ShastaNet";
    private static final String TAG = "BaseAndroidtoJs";
    protected Activity activity;
    private DappAuthorizedPopWindow dappAuthorizedPopWindow;
    private DappJsListener dappJsListener;
    private DappJsScreenListener dappJsScreenListener;
    private Gson gson;
    protected Handler handler;
    protected String icon;
    protected boolean isFinance;
    protected Map<String, String> screenModelMap;
    private Wallet selectedWallet;
    protected String title;
    protected MyWebView webView;
    protected String weburl;

    @JavascriptInterface
    public void EnterFullScreen() {
    }

    public void setDappJsListener(DappJsListener dappJsListener) {
        this.dappJsListener = dappJsListener;
    }

    public void setDappJsScreenListener(DappJsScreenListener dappJsScreenListener) {
        this.dappJsScreenListener = dappJsScreenListener;
    }

    public BaseAndroidtoJs(Activity activity, Handler handler, String str, MyWebView myWebView, boolean z) {
        this.screenModelMap = new HashMap();
        this.activity = activity;
        this.handler = handler;
        this.weburl = str;
        this.webView = myWebView;
        this.isFinance = z;
    }

    public BaseAndroidtoJs(Activity activity, Handler handler, String str, MyWebView myWebView) {
        this(activity, handler, str, myWebView, false);
    }

    @JavascriptInterface
    public String getCurrentAccount() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        return selectedWallet == null ? "" : selectedWallet.getAddress();
    }

    @JavascriptInterface
    public boolean isTest() {
        return SpAPI.THIS.isShasta();
    }

    @JavascriptInterface
    public String getCurrentNet() {
        return IRequest.isShasta() ? ShastaNet : (IRequest.isNile() || IRequest.isTest()) ? NileNet : MainNet;
    }

    @JavascriptInterface
    public void openWeb(String str, String str2) {
        if (StringTronUtil.isEmpty(str2)) {
            return;
        }
        toWeb(str, str2, null, "-2");
    }

    @JavascriptInterface
    public String getCurrentVersion() {
        LogUtils.d(TAG, "selectedAccount:Android&&&4.14.3");
        return "Android&&&4.14.3&&&" + ChannelUtils.getChannel(this.activity);
    }

    @JavascriptInterface
    public String getCurrentLanguage() {
        return "2".equals(SpAPI.THIS.useLanguage()) ? "zh" : "en";
    }

    @JavascriptInterface
    public String getCurrentCurrency() {
        return SpAPI.THIS.isUsdPrice() ? "USD" : "CNY";
    }

    @JavascriptInterface
    public void selectedAccount(String str, String str2, String str3, String str4) {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.selectedWallet = selectedWallet;
        if (selectedWallet == null) {
            return;
        }
        if (selectedWallet.isShieldedWallet()) {
            showShieldDialog(str, str2, str3, str4);
        } else {
            toWeb(str, str2, str3, str4);
        }
    }

    public void toWeb(String str, String str2, String str3, String str4) {
        if (!SpAPI.THIS.getDappName(str)) {
            showDialog(str, str2, str3, str4);
            return;
        }
        DappOptions.DappOptionsBuild dappOptionsBuild = new DappOptions.DappOptionsBuild();
        dappOptionsBuild.addIcon(str3);
        dappOptionsBuild.addInjectZTron(false);
        WebOptions.WebOptionsBuild webOptionsBuild = new WebOptions.WebOptionsBuild();
        try {
            webOptionsBuild.addCode(Integer.parseInt(str4));
            webOptionsBuild.addTitle(str);
            webOptionsBuild.addWebUrl(str2);
            webOptionsBuild.addIconUrl(str3);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        webOptionsBuild.addScreenModel(getScreenModel(str2));
        webOptionsBuild.addDappOptions(dappOptionsBuild.build());
        if (this.webView instanceof BrowserWebView) {
            webOptionsBuild.addInjectTronweb(true);
            Handler handler = this.handler;
            if (handler != null) {
                this.handler.sendMessage(handler.obtainMessage(BrowserConstant.JUMP, webOptionsBuild.build()));
                return;
            }
            return;
        }
        CommonWebActivityV3.start((Context) this.activity, str2, str, true, webOptionsBuild.build());
    }

    private void showDialog(final String str, final String str2, final String str3, final String str4) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.activity);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dapp_dialog).build();
        View view = builder.getView();
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck);
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
            }
        });
        ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
                SpAPI.THIS.setDappName(str);
                FirebaseAnalytics.getInstance(activity).logEvent("View_dapp_page", null);
                toWeb(str, str2, str3, str4);
            }
        });
        build.show();
    }

    private void showShieldDialog(final String str, final String str2, final String str3, final String str4) {
        CustomDialog.Builder builder = new CustomDialog.Builder(this.activity);
        final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dg_shield_dapp).build();
        View view = builder.getView();
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck);
        ((TextView) view.findViewById(R.id.tv_cancle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
            }
        });
        ((TextView) view.findViewById(R.id.tv_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
                toWeb(str, str2, str3, str4);
            }
        });
        build.show();
    }

    @JavascriptInterface
    public void setScreenModel(String str, String str2) {
        String urlTrimToPath = HtmlUtil.urlTrimToPath(str);
        str2.hashCode();
        if (str2.equals("2")) {
            this.screenModelMap.put(urlTrimToPath, WebConstant.LANDSCAPE);
        } else if (str2.equals("3")) {
            this.screenModelMap.put(urlTrimToPath, WebConstant.SENSOR);
        } else {
            this.screenModelMap.put(urlTrimToPath, WebConstant.PORTRAIT);
        }
        DappJsScreenListener dappJsScreenListener = this.dappJsScreenListener;
        if (dappJsScreenListener != null) {
            dappJsScreenListener.onScreenModeSet(str, getScreenModel(urlTrimToPath));
        }
    }

    @JavascriptInterface
    public String getCurrScreenModel(String str) {
        String str2 = this.screenModelMap.get(HtmlUtil.urlTrimToPath(str));
        return WebConstant.LANDSCAPE.equals(str2) ? "2" : WebConstant.SENSOR.equals(str2) ? "3" : "1";
    }

    @JavascriptInterface
    public void rollToHorizontal() {
        this.screenModelMap.put(HtmlUtil.urlTrimToPath(this.weburl), WebConstant.LANDSCAPE);
        DappJsScreenListener dappJsScreenListener = this.dappJsScreenListener;
        if (dappJsScreenListener != null) {
            String str = this.weburl;
            dappJsScreenListener.onScreenModeSet(str, getScreenModel(HtmlUtil.urlTrimToPath(str)));
        }
    }

    @JavascriptInterface
    public void getSource(final String str) {
        if (str != null) {
            this.webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$getSource$0(str);
                }
            });
        }
    }

    public void lambda$getSource$0(String str) {
        this.webView.onGetHtmlSource(str);
    }

    public String getScreenModel(String str) {
        String str2 = this.screenModelMap.get(HtmlUtil.urlTrimToPath(str));
        return StringTronUtil.isEmpty(str2) ? WebConstant.PORTRAIT : str2;
    }

    @JavascriptInterface
    public void sendEvent(String str, String str2) {
        RxBus.getInstance().post(str, str2);
    }

    @JavascriptInterface
    public void requestAddress(final String str) {
        DappAuthorizedPopWindow dappAuthorizedPopWindow = this.dappAuthorizedPopWindow;
        if (dappAuthorizedPopWindow == null || !dappAuthorizedPopWindow.isShowing()) {
            DappAuthorizedPopWindow dappAuthorizedPopWindow2 = this.dappAuthorizedPopWindow;
            if (dappAuthorizedPopWindow2 != null) {
                if (dappAuthorizedPopWindow2.isShowing()) {
                    this.dappAuthorizedPopWindow.dismiss();
                }
                this.dappAuthorizedPopWindow = null;
            }
            if (BrowserConstant.DEFAULT_URL.equals(this.weburl)) {
                return;
            }
            this.webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$requestAddress$8(str);
                }
            });
        }
    }

    public void lambda$requestAddress$8(final String str) {
        MyWebView myWebView = this.webView;
        if ((myWebView instanceof BrowserWebView) && (DAppUrlUtils.isPhishingUrl(((BrowserWebView) myWebView).getUrl()) || IRequest.isDAppReportUrl(this.webView.getUrl()))) {
            return;
        }
        LogUtils.i("requestAddress", String.format("requestAddress: %s, webUrl = %s", str, this.weburl));
        final Wallet selectedWallet = WalletUtils.getSelectedWallet();
        final String address = selectedWallet == null ? "" : selectedWallet.getAddress();
        final String host = StringTronUtil.getHost(this.weburl);
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$requestAddress$7(host, address, str, selectedWallet);
            }
        });
    }

    public void lambda$requestAddress$7(final String str, final String str2, final String str3, final Wallet wallet) {
        boolean queryIsAuthorized = DappAuthorizedController.queryIsAuthorized(str, str2);
        final boolean isBlack = DappBlackListController.getInstance().isBlack(str);
        boolean isGoogleLink = isGoogleLink(str);
        TRXAccountBalanceBean queryByAddress = TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).queryByAddress(str2);
        if (queryIsAuthorized || isGoogleLink) {
            final String str4 = "javascript:" + str3 + "('" + str2 + "')";
            this.webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$requestAddress$1(str4);
                }
            });
            if (this.isFinance) {
                return;
            }
            this.webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$requestAddress$2();
                }
            });
            return;
        }
        final double totalTrx = queryByAddress == null ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : queryByAddress.getTotalTrx();
        final NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        numberInstance.setMaximumFractionDigits(6);
        this.webView.post(new Runnable() {
            @Override
            public final void run() {
                lambda$requestAddress$6(str, wallet, str2, isBlack, numberInstance, totalTrx, str3);
            }
        });
    }

    public void lambda$requestAddress$1(String str) {
        this.webView.loadUrl(str);
    }

    public void lambda$requestAddress$2() {
        MostVisitDAppController.getInstance().dAppConnected(this.weburl);
    }

    public void lambda$requestAddress$6(final String str, Wallet wallet, final String str2, final boolean z, NumberFormat numberFormat, double d, final String str3) {
        if (this.dappAuthorizedPopWindow == null) {
            DappAuthorizedPopWindow dappAuthorizedPopWindow = new DappAuthorizedPopWindow(this.activity);
            this.dappAuthorizedPopWindow = dappAuthorizedPopWindow;
            DappAuthorizedPopWindow dappAuthorizedPopWindow2 = dappAuthorizedPopWindow.setHost(str).setWalletName(wallet.getWalletName()).setWalletIcon(wallet).setWalletAddress(str2).setCanceledOnTouchOutside(true).setisInBlackList(z);
            dappAuthorizedPopWindow2.setTrxNum(numberFormat.format(d) + "TRX").setApproveListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$requestAddress$3(str3, str2, z, str, view);
                }
            }).setRejectListener(new DappAuthorizedPopWindow.OnClick() {
                @Override
                public final void OnClickListener() {
                    lambda$requestAddress$4(str3);
                }
            }).setCopyListener(new DappAuthorizedPopWindow.OnClick() {
                @Override
                public final void OnClickListener() {
                    lambda$requestAddress$5(str2);
                }
            }).show();
        }
    }

    public void lambda$requestAddress$3(String str, String str2, boolean z, String str3, View view) {
        this.webView.loadUrl("javascript:" + str + "('" + str2 + "')");
        this.dappAuthorizedPopWindow.dismiss();
        if (!z) {
            insertDataToDB(str2, str3);
        }
        if (this.isFinance) {
            return;
        }
        MostVisitDAppController.getInstance().dAppConnected(this.weburl);
    }

    public void lambda$requestAddress$4(String str) {
        this.webView.loadUrl("javascript:" + str + "('')");
    }

    public void lambda$requestAddress$5(String str) {
        UIUtils.copy(str);
        ToastUtil.getInstance().show(this.activity, R.string.copy_susscess);
    }

    @JavascriptInterface
    public void share(String str, String str2, String str3, String str4, String str5) {
        UIUtils.copy(str5);
        ToastUtil.getInstance().show(this.activity, R.string.share_success);
    }

    @JavascriptInterface
    public String getCurrentNickName() {
        if (TextUtils.isEmpty(TronConfig.walletName)) {
            TronConfig.walletName = WalletUtils.getSelectedWallet().getWalletName();
        }
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        return selectedWallet == null ? "" : selectedWallet.getWalletName();
    }

    @JavascriptInterface
    public void toBrowserLink(String str) {
        try {
            this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @JavascriptInterface
    public String getChainId() {
        try {
            return (String) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), AppContextUtil.getContext().getString(R.string.chain_name_key), "MainChain");
        } catch (Exception e) {
            LogUtils.e(e);
            return "MainChain";
        }
    }

    @JavascriptInterface
    public String getCurrentChainNode() {
        ChainBean currentChain = SpAPI.THIS.getCurrentChain();
        if (currentChain == null) {
            return "";
        }
        if (this.gson == null) {
            this.gson = new Gson();
        }
        return this.gson.toJson(currentChain);
    }

    @JavascriptInterface
    public void scanQRCode(String str) {
        DappJsListener dappJsListener = this.dappJsListener;
        if (dappJsListener != null) {
            dappJsListener.scanQRCode(str);
        }
    }

    private void insertDataToDB(final String str, final String str2) {
        if (this.isFinance) {
            return;
        }
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                BaseAndroidtoJs.lambda$insertDataToDB$9(str, str2);
            }
        });
    }

    public static void lambda$insertDataToDB$9(String str, String str2) {
        DAppNonOfficialAuthorizedProjectBean dAppNonOfficialAuthorizedProjectBean = new DAppNonOfficialAuthorizedProjectBean();
        dAppNonOfficialAuthorizedProjectBean.setWalletAddress(str);
        dAppNonOfficialAuthorizedProjectBean.setUrl(str2);
        dAppNonOfficialAuthorizedProjectBean.setType(2);
        DappAuthorizedController.insertNonOfficialAuthorizedProject(dAppNonOfficialAuthorizedProjectBean, str);
    }

    private boolean isGoogleLink(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return false;
        }
        return str.startsWith("www.google.com") || str.startsWith("google.com");
    }
}
