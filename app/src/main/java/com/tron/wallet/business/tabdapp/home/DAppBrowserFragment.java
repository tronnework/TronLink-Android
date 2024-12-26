package com.tron.wallet.business.tabdapp.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.finance.bean.Result;
import com.tron.wallet.business.finance.bean.ScanQROutput;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.business.tabdapp.browser.BrowserNaviListener;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.BrowserWebView;
import com.tron.wallet.business.tabdapp.browser.bean.BaseWebViewPageInfo;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabHistoryBean;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserBookMarkManager;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserHistoryManager;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserTabHistoryManager;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTab;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTabsManagerActivity;
import com.tron.wallet.business.tabdapp.component.BrowserLongClickPopupView;
import com.tron.wallet.business.tabdapp.component.DAppTitleView;
import com.tron.wallet.business.tabdapp.component.ISnapshot;
import com.tron.wallet.business.tabdapp.home.DAppBrowserContract;
import com.tron.wallet.business.tabdapp.home.DAppBrowserFragment;
import com.tron.wallet.business.tabdapp.home.DAppBrowserModel;
import com.tron.wallet.business.tabdapp.home.DAppBrowserPresenter;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.business.tabdapp.home.utils.BaseMenuClickCallback;
import com.tron.wallet.business.tabdapp.home.utils.BrowserMenuPopupView;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.business.transfer.share.ShareHelper;
import com.tron.wallet.business.walletmanager.selectwallet.search.SelectWalletBottomPopup;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.browser.BrowserContent;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.ErrorConstant;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FragmentDappBrowserBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.tron.walletserver.Wallet;
public class DAppBrowserFragment<P extends DAppBrowserPresenter, M extends DAppBrowserModel> extends BaseFragment<DAppBrowserPresenter, DAppBrowserModel> implements BrowserNaviListener, DAppBrowserContract.View, ISnapshot {
    public static final int FIRST_TAB_TAG = 0;
    private static final String KEY_INIT_URL = "key_init_url";
    private static final String KEY_TITLE = "key_title";
    private static final String TAG = "DAppBrowserFragment";
    private AppCompatActivity activity;
    private FragmentDappBrowserBinding binding;
    protected BrowserContent browserView;
    private boolean initialized;
    BaseWebViewPageInfo lastPageInfo;
    private PermissionHelper mPermissionHelper;
    NoNetView noNetView;
    private Consumer<Boolean> onWebScrollChanged;
    private BrowserTabManager tabManager;
    private Consumer<String> titleChangedListener;
    protected DAppTitleView titleView;
    private final List<DappBean> pendingList = new ArrayList();
    private boolean isCommonWeb = false;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 259) {
                if (getActivity() != null) {
                    getActivity().finish();
                }
            } else if (i == 768) {
                tabManager.startURL(((WebOptions) message.obj).getWebUrl());
            } else if (i == 1024) {
                Observable.just((BaseWebViewPageInfo) message.obj).subscribeOn(Schedulers.io()).subscribe(new io.reactivex.functions.Consumer<BaseWebViewPageInfo>() {
                    @Override
                    public void accept(BaseWebViewPageInfo baseWebViewPageInfo) throws Exception {
                        if (baseWebViewPageInfo != null) {
                            LogUtils.d(DAppBrowserFragment.TAG, baseWebViewPageInfo.toString());
                            if (BrowserConstant.DEFAULT_URL.equals(baseWebViewPageInfo.getUrl())) {
                                return;
                            }
                            if (baseWebViewPageInfo.isFirstPageLoaded() || lastPageInfo == null || !(lastPageInfo == null || lastPageInfo.getActualUrl().equals(baseWebViewPageInfo.getActualUrl()))) {
                                lastPageInfo = baseWebViewPageInfo;
                                if (baseWebViewPageInfo.isFirstPageLoaded() && shouldRecordHistory()) {
                                    BrowserTabManager.getInstance().insertMostVisit(baseWebViewPageInfo.getUrl(), baseWebViewPageInfo.getTitle(), baseWebViewPageInfo.getIconUrl());
                                }
                                if (shouldRecordHistory()) {
                                    BrowserHistoryManager.getInstance().addNewPage(baseWebViewPageInfo.getActualUrl(), baseWebViewPageInfo.getTitle(), baseWebViewPageInfo.getIconUrl());
                                }
                            }
                        }
                    }
                });
            } else if (i == 1280) {
                showLongPressMenus((String) message.obj);
            } else if (i != 2304) {
            } else {
                Object obj = message.obj;
                if (obj instanceof Boolean) {
                    onPageFinished(((Boolean) obj).booleanValue());
                }
            }
        }
    };
    private int currentRequestP = 0;

    @Override
    public BrowserContent getBrowserContent() {
        return this.browserView;
    }

    @Override
    public void more() {
    }

    public void registerWebScrollListener(Consumer<Boolean> consumer) {
        this.onWebScrollChanged = consumer;
    }

    public void setCommonWeb(boolean z) {
        this.isCommonWeb = z;
    }

    protected boolean shouldRecordHistory() {
        return true;
    }

    @Override
    public void tab() {
    }

    public void onPageFinished(boolean z) {
        if (z) {
            showErrorPage();
        } else {
            hideErrorPage();
        }
    }

    @Override
    public void showErrorPage() {
        if (this.noNetView == null) {
            return;
        }
        if (TronConfig.hasNet) {
            this.noNetView.setReloadDescription(R.string.web_unaccess);
            this.noNetView.setIcon(R.mipmap.ic_invalid_url);
            this.noNetView.setReloadable(false);
        } else {
            this.noNetView.setReloadDescription(R.string.net_error);
            this.noNetView.setIcon(R.mipmap.ic_no_net);
            this.noNetView.setReloadText(R.string.reload);
            this.noNetView.setReloadable(true);
        }
        this.noNetView.setInnerTopMargin(UIUtils.dip2px(80.0f));
        this.noNetView.setVisibility(View.VISIBLE);
    }

    public void hideErrorPage() {
        NoNetView noNetView = this.noNetView;
        if (noNetView != null) {
            noNetView.setVisibility(View.GONE);
        }
    }

    @Override
    public void processData() {
        String selectedWallet;
        String str;
        String str2;
        setTitleEvent();
        Consumer<Boolean> consumer = this.onWebScrollChanged;
        if (consumer != null) {
            this.browserView.registerWebScrollListener(consumer);
        }
        this.mPermissionHelper = new PermissionHelper(this.mContext, new PermissionInterface() {
            @Override
            public int getPermissionsRequestCode() {
                return 5000;
            }

            @Override
            public String[] getPermissions() {
                if (100 == currentRequestP) {
                    return new String[]{"android.permission.CAMERA"};
                }
                return new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
            }

            @Override
            public void requestPermissionsSuccess() {
                if (100 == currentRequestP) {
                    ScannerActivity.startForFragment(DAppBrowserFragment.this);
                } else {
                    goPicture();
                }
            }

            @Override
            public void requestPermissionsFail() {
                ToastUtil.getInstance().show(getContext(), R.string.error_permission1);
                if (100 == currentRequestP) {
                    Result<ScanQROutput> result = new Result<>();
                    result.setCode(ErrorConstant.scanQRError);
                    browserView.loadJsResult(result);
                    return;
                }
                Result result2 = new Result();
                result2.setCode(ErrorConstant.openFileChooserError);
                browserView.getWebView().onReceiveValue(Uri.EMPTY);
                browserView.getWebView().loadJs("openFileChooser", result2);
            }
        });
        this.browserView.setRequestPermissionListener(new BrowserWebView.RequestPermissionListener() {
            @Override
            public void onRequestPermission(String str3) {
                LogUtils.d(DAppBrowserFragment.TAG, "onRequestPermission:   " + str3);
            }

            @Override
            public void onRequestPermission(int i) {
                requestPermissions(i);
            }
        });
        BrowserTabManager browserTabManager = BrowserTabManager.getInstance();
        this.tabManager = browserTabManager;
        browserTabManager.setBrowserContent(this.browserView);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        if (WalletUtils.getSelectedWallet() != null) {
            selectedWallet = WalletUtils.getSelectedWallet().getWalletName();
        } else {
            selectedWallet = SpAPI.THIS.getSelectedWallet();
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            str = arguments.getString(KEY_INIT_URL);
            str2 = arguments.getString(KEY_TITLE);
        } else {
            str = "";
            str2 = "";
        }
        this.browserView.init(this.mHandler, new WebOptions.WebOptionsBuild().addNeedOutside(false).addUseCache(true).addTitle(str2).addWebUrl(str).addWallerName(selectedWallet).addInjectTronweb(true).build());
        if (!this.isCommonWeb) {
            List<BrowserTabHistoryBean> queryTabHistory = BrowserTabHistoryManager.getInstance().queryTabHistory();
            if (queryTabHistory.size() > 0) {
                BrowserTabManager.getInstance().setLoadingTabHistory(true);
                int i = 0;
                for (int i2 = 0; i2 < queryTabHistory.size(); i2++) {
                    BrowserTabHistoryBean browserTabHistoryBean = queryTabHistory.get(i2);
                    if (browserTabHistoryBean.getIsCurrent()) {
                        i = i2;
                    }
                    LogUtils.e("LOAD_TAB_HISTORY", "URL= " + browserTabHistoryBean.getUrl());
                    String url = browserTabHistoryBean.getUrl();
                    if (i2 == 0) {
                        if (!StringTronUtil.isEmpty(url) && !StringTronUtil.equals(BrowserConstant.DEFAULT_URL, url)) {
                            this.tabManager.startWithURL_HISTORY(url, browserTabHistoryBean.getTitle(), browserTabHistoryBean.getDappAuthUrl(), browserTabHistoryBean.getIcon(), browserTabHistoryBean.isAnonymous());
                        }
                        this.browserView.getWebView(0).setLazyLoading(true);
                    } else {
                        this.tabManager.startWithTAB_HISTORY(StringTronUtil.isEmpty(url) ? BrowserConstant.DEFAULT_URL : url, browserTabHistoryBean.getTitle(), browserTabHistoryBean.getDappAuthUrl(), browserTabHistoryBean.getIcon(), browserTabHistoryBean.isAnonymous());
                    }
                }
                this.tabManager.setTab(i);
                BrowserTabManager.getInstance().setLoadingTabHistory(false);
            }
        }
        startPending();
        this.initialized = true;
    }

    public void lambda$processData$0(View view) {
        this.tabManager.refresh();
    }

    private void setTitleEvent() {
        this.titleView.setClickBackListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                handleBackPressed();
                AnalyticsHelper.logEvent(AnalyticsHelper.BrowserVisit.CLICK_BROWSER_BACK);
            }
        }).setClickHomeListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                backToMain();
                tabManager.clearView();
                tabManager.getTabAt(tabManager.getCurrentTabIndex()).setCanBackToMain(true);
                AnalyticsHelper.logEvent(AnalyticsHelper.BrowserVisit.CLICK_BROWSER_HOME);
            }
        }).setClickTabsListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                BrowserTabsManagerActivity.start(getIContext());
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.CLICK_MULTI_TABS);
            }
        }).setClickMenuListener(new fun4());
    }

    public class fun4 extends NoDoubleClickListener2 {
        public static void lambda$onNoDoubleClick$0(Void r0) throws Exception {
        }

        fun4() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            BrowserWebView webView = tabManager.getWebView();
            boolean isDebugAble = webView.isDebugAble();
            String url = webView.getUrl();
            boolean z = BrowserBookMarkManager.getInstance().isExists(url) || BrowserBookMarkManager.getInstance().isExists(DAppUrlUtils.removeUrlSuffixParameter(url));
            boolean isInjectTronWeb = webView.isInjectTronWeb();
            String url2 = webView.getUrl();
            String[] split = IRequest.getDappReportUrl().split("/#/");
            BrowserMenuPopupView.showUp(getIContext(), titleView, 1, isDebugAble, isInjectTronWeb, z, url2 != null && url2.startsWith(split[0]) && url2.endsWith(split[1]), getMenuCallback(new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    DAppBrowserFragment.4.lambda$onNoDoubleClick$0((Void) obj);
                }
            }));
            AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.CLICK_MORE);
        }
    }

    protected void backToMain() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof DappHomeFragment) {
            ((DappHomeFragment) parentFragment).showPage(0);
        }
    }

    private void startPending() {
        if (this.pendingList.isEmpty()) {
            return;
        }
        List<DappBean> list = this.pendingList;
        this.tabManager.startURL(list.remove(list.size() - 1).getHomeUrl(), true);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentDappBrowserBinding inflate = FragmentDappBrowserBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    public void mappingId() {
        this.browserView = this.binding.browserView;
        this.titleView = this.binding.dappTitleView;
        this.noNetView = this.binding.noNetView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public boolean handleBackPressed() {
        try {
            boolean back = this.browserView.back();
            this.titleView.updateButtonStates();
            if (back) {
                return back;
            }
            BrowserTabManager browserTabManager = this.tabManager;
            if (browserTabManager.getTabAt(browserTabManager.getCurrentTabIndex()).isCanBackToMain()) {
                backToMain();
                saveCurTabs();
                return true;
            }
            return back;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handleResume();
        if (this.isCommonWeb) {
            return;
        }
        this.titleView.updateTabCount();
        this.tabManager.setBrowserSnapshot(this);
    }

    public void handleResume() {
        if (this.initialized) {
            this.tabManager.setBrowserContent(this.browserView);
            BrowserTabManager browserTabManager = this.tabManager;
            this.titleView.setData(browserTabManager.getTabAt(browserTabManager.getCurrentTabIndex()).getHomeUrl());
        }
    }

    public void handleNewDAppClicked(DappBean dappBean, boolean z) {
        BrowserTabManager browserTabManager;
        if (this.initialized && (browserTabManager = this.tabManager) != null) {
            browserTabManager.startURL(dappBean, true, z);
            BrowserTabManager browserTabManager2 = this.tabManager;
            browserTabManager2.updateTabAt(browserTabManager2.getCurrentTabIndex(), dappBean.getName(), dappBean.getHomeUrl());
            return;
        }
        this.pendingList.add(dappBean);
    }

    public void showLongPressMenus(final String str) {
        BrowserLongClickPopupView.create(getActivity()).setShowOpenNewTab(!this.isCommonWeb).setContent(str).setClickCopyListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showLongPressMenus$1(str, view);
            }
        }).setClickNewTabListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showLongPressMenus$2(str, view);
            }
        }).setClickOpenNewListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showLongPressMenus$3(str, view);
            }
        }).setClickOpenOutsideListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showLongPressMenus$4(str, view);
            }
        }).show();
        AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.LONG_PRESS_LINK);
    }

    public void lambda$showLongPressMenus$1(String str, View view) {
        UIUtils.copy(str);
        Toast((int) R.string.copy_susscess);
        AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.LONG_PRESS_COPY);
    }

    public void lambda$showLongPressMenus$2(String str, View view) {
        BrowserTabManager browserTabManager = this.tabManager;
        if (browserTabManager != null) {
            browserTabManager.startNewTab(str, null, true, false);
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.LONG_PRESS_OPEN_NEW_TAB);
    }

    public void lambda$showLongPressMenus$3(String str, View view) {
        BrowserTabManager browserTabManager = this.tabManager;
        if (browserTabManager != null) {
            browserTabManager.startURL(str, (String) null, false, true);
        }
        AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.LONG_PRESS_OPEN);
    }

    public void lambda$showLongPressMenus$4(String str, View view) {
        goIntent(str);
        AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.LONG_PRESS_OPEN_OUTSIDE);
    }

    @Override
    public void back() {
        this.browserView.back();
    }

    @Override
    public void ahead() {
        this.browserView.goForward();
    }

    @Override
    public void main() {
        this.browserView.goMain();
    }

    public void goIntent(String str) {
        try {
            getIContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public class fun8 extends BaseMenuClickCallback {
        final io.reactivex.functions.Consumer val$onClickNewTab;

        fun8(io.reactivex.functions.Consumer consumer) {
            this.val$onClickNewTab = consumer;
        }

        @Override
        public void onClickNewTab(Context context) {
            super.onClickNewTab(context);
            try {
                this.val$onClickNewTab.accept(null);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }

        @Override
        public void onClickRefresh() {
            super.onClickRefresh();
            if (tabManager != null) {
                tabManager.refresh();
            }
        }

        @Override
        public void onClickBookMark(boolean z) {
            super.onClickBookMark(z);
            if (browserView == null) {
                return;
            }
            if (z) {
                BrowserBookMarkManager.getInstance().removeSingle(browserView.getCurWebUrl());
                DAppBrowserFragment dAppBrowserFragment = DAppBrowserFragment.this;
                dAppBrowserFragment.toast(dAppBrowserFragment.getString(R.string.bookmark_remove_success));
            } else if (BrowserBookMarkManager.getInstance().getBookMarkCount() < 300) {
                BrowserBookMarkManager.getInstance().addNewMark(browserView.getCurWebUrl(), browserView.getCurTitle(), browserView.getCurIconUrl());
                DAppBrowserFragment dAppBrowserFragment2 = DAppBrowserFragment.this;
                dAppBrowserFragment2.toast(dAppBrowserFragment2.getString(R.string.add_bookmark_success));
            } else {
                DAppBrowserFragment dAppBrowserFragment3 = DAppBrowserFragment.this;
                dAppBrowserFragment3.toast(dAppBrowserFragment3.getString(R.string.bookmark_reach_limit));
            }
        }

        @Override
        public void onClickSwitchWallet() {
            super.onClickSwitchWallet();
            SelectWalletBottomPopup.showPopup(getContext(), WalletUtils.getSelectedPublicWallet(), new SelectWalletBottomPopup.OnClickListener() {
                @Override
                public final void onClick(Wallet wallet) {
                    DAppBrowserFragment.8.this.lambda$onClickSwitchWallet$0(wallet);
                }
            }, null);
        }

        public void lambda$onClickSwitchWallet$0(Wallet wallet) {
            tabManager.checkAndReloadWebPage();
        }

        @Override
        public void onClickShare() {
            super.onClickShare();
            String curWebUrl = browserView.getCurWebUrl();
            if (curWebUrl == null) {
                return;
            }
            if (TextUtils.equals(StringTronUtil.getHost(curWebUrl), "tronlinkorg.zendesk.com")) {
                ShareHelper.getInstance().shareUrl(getIContext(), curWebUrl);
            } else {
                ShareHelper.getInstance().shareUrlWithDecor(getIContext(), curWebUrl, browserView.getCurTitle());
            }
        }

        @Override
        public void onClickOpenOutside() {
            super.onClickOpenOutside();
            DAppBrowserFragment dAppBrowserFragment = DAppBrowserFragment.this;
            dAppBrowserFragment.goIntent(dAppBrowserFragment.browserView.getCurWebUrl());
        }

        @Override
        public void onClickDappReport() {
            super.onClickOpenOutside();
            browserView.getWebView().loadNewUrl(DAppUrlUtils.addQueryParameter(IRequest.getDappReportUrl(), "dapp", DAppUrlUtils.removeUrlSuffixParameter(browserView.getWebView().getUrl())));
        }

        @Override
        public void onClickDebug(boolean z) {
            browserView.getWebView().setDebugAble(z);
        }
    }

    public BaseMenuClickCallback getMenuCallback(io.reactivex.functions.Consumer<Void> consumer) {
        return new fun8(consumer);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mPresenter != 0) {
            try {
                ((DAppBrowserPresenter) this.mPresenter).onActivityResult(i, i2, intent);
            } catch (Throwable th) {
                SentryUtil.captureException(th);
            }
        }
    }

    public void requestPermissions(int i) {
        this.currentRequestP = i;
        if (Build.VERSION.SDK_INT < 33) {
            this.mPermissionHelper.requestPermissions();
        } else if (101 == this.currentRequestP) {
            goPicture();
        }
    }

    public void goPicture() {
        if (this.activity != null) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            this.activity.startActivityForResult(intent, BrowserConstant.REQUEST_CODE_GET_PIC_URI2);
        }
    }

    public Consumer<String> getTitleChangedListener() {
        if (this.titleChangedListener == null) {
            this.titleChangedListener = new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$getTitleChangedListener$5((String) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            };
        }
        return this.titleChangedListener;
    }

    public void lambda$getTitleChangedListener$5(String str) {
        BrowserTabManager browserTabManager = this.tabManager;
        BrowserTab tabAt = browserTabManager.getTabAt(browserTabManager.getCurrentTabIndex());
        DAppTitleView dAppTitleView = this.titleView;
        if (dAppTitleView != null) {
            dAppTitleView.setData(tabAt.getHomeUrl());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.isCommonWeb) {
            return;
        }
        saveCurTabs();
    }

    public void saveCurTabs() {
        ArrayList<BrowserTabHistoryBean> allTabs = getBrowserContent().getAllTabs();
        LogUtils.e("browserWebViewArrayList", allTabs.toString());
        for (int i = 0; i < allTabs.size(); i++) {
            BrowserTabHistoryBean browserTabHistoryBean = allTabs.get(i);
            if (BrowserTabManager.getInstance().getViewType(i) == 0) {
                browserTabHistoryBean.setUrl(BrowserConstant.DEFAULT_URL);
                browserTabHistoryBean.setDappAuthUrl(BrowserConstant.DEFAULT_URL);
                browserTabHistoryBean.setMain(true);
            }
        }
        LogUtils.e("LOAD_TAB_HISTORY", "browserWebViewArrayList= " + allTabs.toString());
        BrowserTabHistoryManager.getInstance().addListAndRemoveOldData(allTabs);
    }

    @Override
    public Bitmap takeSnapshot(int i, int i2, int i3) {
        if (this.noNetView.getVisibility() != 0) {
            return this.browserView.getSnapshot(i);
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.noNetView.getWidth(), this.noNetView.getHeight(), Bitmap.Config.ARGB_8888);
        this.noNetView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        try {
            if (this.mPermissionHelper.requestPermissionsResult(i, strArr, iArr)) {
                return;
            }
            super.onRequestPermissionsResult(i, strArr, iArr);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
