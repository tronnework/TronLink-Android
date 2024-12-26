package com.tron.wallet.business.finance;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.SpUtils;
import com.tron.wallet.business.confirm.core.pending.AnimaUtil;
import com.tron.wallet.business.finance.FinanceFragment;
import com.tron.wallet.business.finance.bean.Result;
import com.tron.wallet.business.finance.bean.ScanQROutput;
import com.tron.wallet.business.finance.bean.SwitchWalletResult;
import com.tron.wallet.business.finance.mvp.FinanceContract;
import com.tron.wallet.business.finance.mvp.FinanceModel;
import com.tron.wallet.business.finance.mvp.FinancePresenter;
import com.tron.wallet.business.stakev2.managementv2.ResourcesBean;
import com.tron.wallet.business.tabdapp.browser.BrowserWebView;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component.ConfirmModel;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.business.tabswap.SwapMainFragment;
import com.tron.wallet.business.tronpower.stake2.StakeTRX2Activity;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.business.web.DappOptions;
import com.tron.wallet.business.web.WebOptions;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.FinanceSortPopupView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AccountUtils;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UriUtils;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.FragmentFinanceBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class FinanceFragment extends LazyLoadFragment<FinancePresenter, FinanceModel> implements FinanceContract.View {
    public static final int ALERT_MASK = 26;
    public static final int FINANCE_SORT = 260;
    public static final int FINANCIAL_ASSETS_LOADED = 262;
    public static final int FINANCIAL_TOKEN_LIST_LOADED = 263;
    public static final int GOTO_FINANCE_HOME = 259;
    public static final int GOTO_NATIVE_STAKE = 258;
    private static final String KEY_INIT_URL = "key_init_url";
    private static final String KEY_IS_FINANCIAL = "key_is_financial";
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_WEBOPTIONS = "key_web_options";
    public static final int LOAD_TIMME_OUT = 265;
    public static final int SHOW_FINANCE_AMOUNT = 261;
    public static final int STAKE = 272;
    public static final int SWITCH_WALLET = 257;
    public static final String TAG = "FinacialAcToJs";
    public static final int VOTE = 264;
    private FragmentFinanceBinding binding;
    BrowserWebView browserWebView;
    RelativeLayout financeRoot;
    ImageView ivLoadingView;
    View llUnderstand;
    View loadingView;
    RxManager mRxManager;
    NoNetView noNetView;
    private BrowserWebView.RenderProcessListener renderProcessListener;
    View rlNotSupport;
    private State state;
    private String url;
    private boolean isFinancial = true;
    private boolean isFirstEntered = true;
    private boolean isFinancialHomeLoaded = false;
    private boolean voteLock = false;
    private boolean stakeLock = false;
    private boolean isPageFinished = false;
    Handler mHandler = new fun1(Looper.getMainLooper());

    public enum State {
        Success,
        Error,
        Loading
    }

    public boolean isPageFinished() {
        return this.isPageFinished;
    }

    public static FinanceFragment getInstance(String str, String str2, boolean z, WebOptions webOptions) {
        FinanceFragment financeFragment = new FinanceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, str);
        bundle.putString(KEY_INIT_URL, str2);
        bundle.putBoolean(KEY_IS_FINANCIAL, z);
        bundle.putSerializable(KEY_WEBOPTIONS, webOptions);
        financeFragment.setArguments(bundle);
        return financeFragment;
    }

    public class fun1 extends Handler {
        fun1(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 26) {
                Fragment parentFragment = getParentFragment();
                boolean booleanValue = ((Boolean) message.obj).booleanValue();
                if (parentFragment instanceof SwapMainFragment) {
                    ((SwapMainFragment) parentFragment).alertBackground(booleanValue);
                }
            } else if (i == 272) {
                if (message.obj instanceof String) {
                    final String str = (String) message.obj;
                    if (stakeLock) {
                        loadJsCancel(str);
                        return;
                    }
                    stakeLock = true;
                    showLoadingDialog();
                    final Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
                    if (selectedPublicWallet != null) {
                        runOnThreeThread(new OnBackground() {
                            @Override
                            public final void doOnBackground() {
                                FinanceFragment.1.this.lambda$handleMessage$3(selectedPublicWallet, str);
                            }
                        });
                    }
                }
            } else if (i == 768) {
                WebOptions webOptions = (WebOptions) message.obj;
                CommonWebActivityV3.start((Context) getActivity(), webOptions.getWebUrl(), webOptions.getTitle(), true, webOptions);
            } else if (i == 2304) {
                if (((Boolean) message.obj).booleanValue()) {
                    showView(State.Error);
                    return;
                }
                isPageFinished = true;
                isFinancialHomeLoaded = true;
                showView(State.Success);
            } else if (i == 264) {
                if (message.obj instanceof String) {
                    final String str2 = (String) message.obj;
                    if (voteLock) {
                        loadJsCancel(str2);
                        return;
                    }
                    voteLock = true;
                    showLoadingDialog();
                    final Wallet selectedPublicWallet2 = WalletUtils.getSelectedPublicWallet();
                    if (selectedPublicWallet2 != null) {
                        runOnThreeThread(new OnBackground() {
                            @Override
                            public final void doOnBackground() {
                                FinanceFragment.1.this.lambda$handleMessage$1(selectedPublicWallet2, str2);
                            }
                        });
                    }
                }
            } else if (i == 265) {
                if (isFinancialHomeLoaded) {
                    return;
                }
                showView(State.Error);
            } else {
                switch (i) {
                    case 259:
                        handleBackPressed();
                        refreshData();
                        return;
                    case FinanceFragment.FINANCE_SORT:
                        FinanceSortPopupView.showUp(getActivity(), ((Integer) message.obj).intValue(), new FinanceSortPopupView.OnSelectChangedListener() {
                            @Override
                            public void onSelectChanged(int i2) {
                                FinanceFragment financeFragment = FinanceFragment.this;
                                financeFragment.loadJs("financialRecommendationSortCallback", i2 + "");
                            }
                        });
                        return;
                    case FinanceFragment.SHOW_FINANCE_AMOUNT:
                        mRxManager.post(Event.FINANCE_STATUS_HIDDEN_SHOW, message.obj);
                        return;
                    default:
                        return;
                }
            }
        }

        public void lambda$handleMessage$1(Wallet wallet, String str) {
            Protocol.Account account;
            try {
                account = TronAPI.queryAccount(wallet.getAddress(), false);
            } catch (Exception e) {
                LogUtils.e(e);
                account = WalletUtils.getAccount(mContext, wallet.getWalletName());
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    FinanceFragment.1.this.lambda$handleMessage$0();
                }
            });
            voteLock = false;
            if (!AccountUtils.checkAccountIsNotActivated(account) && account != null && !"".equals(account.toString()) && ResourcesBean.getFrozenSun(account) == 0) {
                showVoteDialog(wallet, account, str);
            } else {
                toVote(wallet, account, str);
            }
        }

        public void lambda$handleMessage$0() {
            dismissLoadingDialog();
        }

        public void lambda$handleMessage$3(Wallet wallet, String str) {
            Protocol.Account account;
            try {
                account = TronAPI.queryAccount(wallet.getAddress(), false);
            } catch (Exception e) {
                LogUtils.e(e);
                account = WalletUtils.getAccount(mContext, wallet.getWalletName());
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    FinanceFragment.1.this.lambda$handleMessage$2();
                }
            });
            stakeLock = false;
            if (AccountUtils.checkAccountIsNotActivated(account)) {
                StakeTRX2Activity.startWithCheckOwnerPermission(mContext, true, account, DataStatHelper.StatAction.FinanceStake, wallet.getAddress());
            } else {
                toStake(wallet, account, str);
            }
        }

        public void lambda$handleMessage$2() {
            dismissLoadingDialog();
        }
    }

    public void toVote(Wallet wallet, Protocol.Account account, String str) {
        loadJsCancel(str);
        VoteMainActivity.startWithCheckOwnerPermissionByFinance(this.mContext, account, wallet.getAddress(), DataStatHelper.StatAction.FinanceVote);
    }

    public void toStake(Wallet wallet, Protocol.Account account, String str) {
        loadJsCancel(str);
        StakeTRX2Activity.startWithCheckOwnerPermission(this.mContext, true, account, DataStatHelper.StatAction.FinanceStake, wallet.getAddress());
    }

    public void showVoteDialog(final Wallet wallet, final Protocol.Account account, final String str) {
        ConfirmCustomPopupView.getBuilder(this.mContext).setBtnStyle(2).setTitle(getString(R.string.finance_no_vote)).setTitleSize(16).setConfirmText(getString(R.string.finance_to_stake)).setCancelText(getString(R.string.finance_knew)).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
                super.onDismiss(basePopupView);
                loadJsCancel(str);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showVoteDialog$0(wallet, account, str, view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showVoteDialog$1(str, account, wallet, view);
            }
        }).build().show();
    }

    public void lambda$showVoteDialog$0(Wallet wallet, Protocol.Account account, String str, View view) {
        toVote(wallet, account, str);
    }

    public void lambda$showVoteDialog$1(String str, Protocol.Account account, Wallet wallet, View view) {
        loadJsCancel(str);
        StakeTRX2Activity.startWithCheckOwnerPermission(this.mContext, true, account, DataStatHelper.StatAction.FinanceStake, wallet.getAddress());
    }

    private void showStakeDialog(final Wallet wallet, final Protocol.Account account, final String str) {
        ConfirmCustomPopupView.getBuilder(this.mContext).setBtnStyle(2).setTitle(getString(R.string.stake_account_unactive)).setTitleSize(16).setConfirmText(getString(R.string.finance_to_stake)).setCancelText(getString(R.string.finance_knew)).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
                super.onDismiss(basePopupView);
                loadJsCancel(str);
            }
        }).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showStakeDialog$2(str, view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showStakeDialog$3(str, account, wallet, view);
            }
        }).build().show();
    }

    public void lambda$showStakeDialog$2(String str, View view) {
        loadJsCancel(str);
    }

    public void lambda$showStakeDialog$3(String str, Protocol.Account account, Wallet wallet, View view) {
        loadJsCancel(str);
        StakeTRX2Activity.startWithCheckOwnerPermission(this.mContext, true, account, DataStatHelper.StatAction.FinanceStake, wallet.getAddress());
    }

    public void handleBackKeyPressed() {
        if (this.isPageFinished && this.state == State.Success) {
            loadJs("onKeyBack", "");
        } else {
            handleBackPressed();
        }
    }

    public void refreshData() {
        if (this.isFirstEntered) {
            this.isFirstEntered = false;
            return;
        }
        boolean financeIsAllAccount = SpAPI.THIS.getFinanceIsAllAccount();
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (StringTronUtil.isEmpty(arguments.getString(KEY_INIT_URL))) {
                return;
            }
            loadJs("reloadPage", "");
        }
        SwitchWalletResult switchWalletResult = new SwitchWalletResult();
        if (financeIsAllAccount) {
            switchWalletResult.setAll(true);
        } else {
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            switchWalletResult.setAll(false);
            switchWalletResult.setName(selectedPublicWallet.getWalletName());
            switchWalletResult.setAddress(selectedPublicWallet.getAddress());
        }
        loadJs("homeReload", GsonUtils.toGsonString(switchWalletResult));
    }

    public void loadJs(String str, String str2) {
        final String str3 = "javascript:" + str + "('" + str2 + "')";
        BrowserWebView browserWebView = this.browserWebView;
        if (browserWebView != null) {
            browserWebView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$loadJs$4(str3);
                }
            });
        }
    }

    public void lambda$loadJs$4(String str) {
        BrowserWebView browserWebView = this.browserWebView;
        if (browserWebView != null) {
            browserWebView.lambda$loadJs$4(str);
        }
    }

    @Override
    protected void processData() {
        String str;
        final WebOptions webOptions;
        initRX();
        showView(State.Loading);
        this.url = getHostUrl();
        if (IRequest.isTest()) {
            this.url = "https://financialtest.tronlink.org/";
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.url = arguments.getString(KEY_INIT_URL);
            str = arguments.getString(KEY_TITLE);
            this.isFinancial = arguments.getBoolean(KEY_IS_FINANCIAL);
            webOptions = (WebOptions) arguments.getSerializable(KEY_WEBOPTIONS);
        } else {
            str = "";
            webOptions = null;
        }
        if (webOptions == null) {
            webOptions = new WebOptions.WebOptionsBuild().addNeedOutside(false).addUseCache(true).addInjectTronweb(true).addTitle(str).addWebUrl(this.url).addDappOptions(new DappOptions.DappOptionsBuild().addInjectZTron(false).build()).addWallerName(WalletUtils.getSelectedWallet().getWalletName()).addIsFinance(this.isFinancial).build();
        }
        BrowserWebView.RenderProcessListener renderProcessListener = new BrowserWebView.RenderProcessListener() {
            @Override
            public void onRenderProcess(WebView webView, String str2) {
                financeRoot.removeView(webView);
                browserWebView = new BrowserWebView(financeRoot.getContext());
                browserWebView.initWithWebOptions(mHandler, webOptions);
                browserWebView.setRenderProcessListener(renderProcessListener);
                financeRoot.addView(browserWebView);
                browserWebView.loadNewUrl(str2);
            }
        };
        this.renderProcessListener = renderProcessListener;
        this.browserWebView.setRenderProcessListener(renderProcessListener);
        this.browserWebView.initWithWebOptions(this.mHandler, webOptions);
        this.browserWebView.lambda$loadJs$4(this.url);
        if (arguments == null) {
            this.mHandler.sendEmptyMessageDelayed(LOAD_TIMME_OUT, 10000L);
        }
        final String str2 = this.url;
        this.noNetView.setReloadable(true);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$5(str2, view);
            }
        });
    }

    public void lambda$processData$5(String str, View view) {
        this.browserWebView.lambda$loadJs$4(str);
        showView(State.Loading);
    }

    private String getHostUrl() {
        if (((Integer) SpUtils.getParam("f_Tron", AppContextUtil.getContext(), "server_user_prefer", 0)).intValue() == 1) {
            return SpAPI.THIS.getFinanceSingaporeUrl();
        }
        return SpAPI.THIS.getFinanceUSUrl();
    }

    public void initRX() {
        RxManager rxManager = new RxManager();
        this.mRxManager = rxManager;
        rxManager.on(RB.RB_PRICE_SWITCH, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRX$6(obj);
            }
        });
        this.mRxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRX$7(obj);
            }
        });
        this.mRxManager.on(Event.ASSET_STATUS_HIDDEN_SHOW, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRX$8(obj);
            }
        });
        this.mRxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRX$9(obj);
            }
        });
    }

    public void lambda$initRX$6(Object obj) throws Exception {
        loadJs("currentCurrencyListener", SpAPI.THIS.isUsdPrice() ? "USD" : "CNY");
    }

    public void lambda$initRX$7(Object obj) throws Exception {
        boolean financeIsAllAccount = SpAPI.THIS.getFinanceIsAllAccount();
        Wallet wallet = WalletUtils.getWallet((String) obj);
        if (wallet == null || financeIsAllAccount) {
            return;
        }
        if (wallet.isWatchNotPaired()) {
            SwitchWalletResult switchWalletResult = new SwitchWalletResult();
            switchWalletResult.setAll(true);
            SpAPI.THIS.setFinanceIsAllAccount(true);
            loadJs("homeReload", GsonUtils.toGsonString(switchWalletResult));
            return;
        }
        SwitchWalletResult switchWalletResult2 = new SwitchWalletResult();
        switchWalletResult2.setAll(false);
        switchWalletResult2.setName(wallet.getWalletName());
        SpAPI.THIS.setFinanceIsAllAccount(false);
        switchWalletResult2.setAddress(wallet.getAddress());
        loadJs("homeReload", GsonUtils.toGsonString(switchWalletResult2));
    }

    public void lambda$initRX$8(Object obj) throws Exception {
        loadJs("showFinanceAmount", GsonUtils.toGsonString(Boolean.valueOf(!((Boolean) obj).booleanValue())));
    }

    public void lambda$initRX$9(Object obj) throws Exception {
        LogUtils.i(Event.NET_CHANGE + TronConfig.hasNet);
        if (TronConfig.hasNet || !this.isPageFinished) {
            return;
        }
        showView(State.Error);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FragmentFinanceBinding inflate = FragmentFinanceBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId(root);
        return root;
    }

    private void mappingId(View view) {
        this.financeRoot = this.binding.financeRoot;
        this.browserWebView = this.binding.browserView;
        this.noNetView = this.binding.noDataView;
        this.ivLoadingView = this.binding.ivLoading;
        this.loadingView = this.binding.loadingView;
        this.rlNotSupport = this.binding.getRoot().findViewById(R.id.rl_not_support);
        this.llUnderstand = this.binding.getRoot().findViewById(R.id.ll_understand);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    public boolean handleBackPressed() {
        if (getActivity() != null) {
            getActivity().finish();
            return true;
        }
        return true;
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null && parseActivityResult.getContents() != null && this.browserWebView != null) {
            String stringExtra = intent.getStringExtra("SCAN_RESULT");
            Result<ScanQROutput> result = new Result<>();
            result.setData(new ScanQROutput());
            result.getData().setQrCode(stringExtra);
            this.browserWebView.loadJsResult(result);
        }
        if (i == 100 && this.browserWebView != null) {
            String stringExtra2 = intent.getStringExtra("SCAN_RESULT");
            Result<ScanQROutput> result2 = new Result<>();
            result2.setData(new ScanQROutput());
            result2.getData().setQrCode(stringExtra2);
            this.browserWebView.loadJsResult(result2);
        }
        IntentResult parseActivityResult2 = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult2 != null && parseActivityResult2.getContents() != null && this.browserWebView != null) {
            String stringExtra3 = intent.getStringExtra("SCAN_RESULT");
            Result<ScanQROutput> result3 = new Result<>();
            result3.setData(new ScanQROutput());
            result3.getData().setQrCode(stringExtra3);
            this.browserWebView.loadJsResult(result3);
        }
        if (i2 != -1 || intent == null) {
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("cancle", false);
        LogUtils.i("DAppSignMessage:cancle:", booleanExtra + "");
        if (booleanExtra && this.browserWebView != null) {
            loadJsCancel("onerror");
            BrowserWebView browserWebView = this.browserWebView;
            if (browserWebView == null || browserWebView.getAndroidJs() == null) {
                return;
            }
            this.browserWebView.getAndroidJs().revertIsExist();
        } else if (i == 2022) {
            try {
                ConfirmModel confirmModel = (ConfirmModel) intent.getSerializableExtra("currentmodel");
                String stringExtra4 = intent.getStringExtra("transactionString");
                LogUtils.i("DAppSignMessage2", stringExtra4);
                String stringExtra5 = intent.getStringExtra("password");
                boolean booleanExtra2 = intent.getBooleanExtra("selectedModel", false);
                boolean booleanExtra3 = intent.getBooleanExtra("hasPassword", false);
                BrowserWebView browserWebView2 = this.browserWebView;
                if (browserWebView2 == null || browserWebView2.getAndroidJs() == null) {
                    return;
                }
                if (!StringTronUtil.isEmpty(stringExtra4) && !booleanExtra) {
                    final String str = "javascript:" + this.browserWebView.getAndroidJs().getFunctionName() + "('" + stringExtra4 + "')";
                    this.browserWebView.post(new Runnable() {
                        @Override
                        public final void run() {
                            lambda$onActivityResult$10(str);
                        }
                    });
                }
                this.browserWebView.getAndroidJs().setModel(booleanExtra2, confirmModel, booleanExtra3, stringExtra5);
                this.browserWebView.getAndroidJs().revertIsExist();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        } else if (i != 2024) {
            if (i == 20001) {
                try {
                    Uri imageUri = UriUtils.INSTANCE.getImageUri(getContext(), intent);
                    BrowserWebView browserWebView3 = this.browserWebView;
                    if (browserWebView3 != null) {
                        browserWebView3.onReceiveValue(imageUri);
                    }
                } catch (Exception e2) {
                    SentryUtil.captureException(e2);
                }
            }
        } else {
            String stringExtra6 = intent.getStringExtra("sign_key");
            LogUtils.i("DAppSignMessage3", stringExtra6);
            if (StringTronUtil.isEmpty(stringExtra6) || this.browserWebView == null) {
                return;
            }
            final String str2 = "javascript:" + this.browserWebView.getAndroidJs().getFunctionName() + "('" + stringExtra6 + "')";
            this.browserWebView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$onActivityResult$11(str2);
                }
            });
            this.browserWebView.getAndroidJs().revertIsExist();
        }
    }

    public void lambda$onActivityResult$10(String str) {
        this.browserWebView.lambda$loadJs$4(str);
    }

    public void lambda$onActivityResult$11(String str) {
        this.browserWebView.lambda$loadJs$4(str);
    }

    public void loadJsCancel(String str) {
        final String str2 = "javascript:" + str + "('cancel')";
        BrowserWebView browserWebView = this.browserWebView;
        if (browserWebView != null) {
            browserWebView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$loadJsCancel$12(str2);
                }
            });
        }
    }

    public void lambda$loadJsCancel$12(String str) {
        BrowserWebView browserWebView = this.browserWebView;
        if (browserWebView != null) {
            browserWebView.lambda$loadJs$4(str);
        }
    }

    public void showView(State state) {
        hideView(this.loadingView, this.browserWebView, this.noNetView);
        this.state = state;
        int i = fun6.$SwitchMap$com$tron$wallet$business$finance$FinanceFragment$State[state.ordinal()];
        if (i == 1) {
            this.browserWebView.setVisibility(View.VISIBLE);
        } else if (i == 2) {
            this.noNetView.setVisibility(View.VISIBLE);
        } else if (i != 3) {
        } else {
            this.loadingView.setVisibility(View.VISIBLE);
            AnimaUtil.animLoadingView(this.ivLoadingView);
        }
    }

    public static class fun6 {
        static final int[] $SwitchMap$com$tron$wallet$business$finance$FinanceFragment$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$tron$wallet$business$finance$FinanceFragment$State = iArr;
            try {
                iArr[State.Success.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$finance$FinanceFragment$State[State.Error.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$finance$FinanceFragment$State[State.Loading.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void hideView(View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(View.GONE);
        }
    }

    public void showNotSupportView() {
        View view = this.llUnderstand;
        if (view == null || this.rlNotSupport == null || this.browserWebView == null) {
            return;
        }
        view.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view2) {
                CommonWebActivityV3.start((Context) getActivity(), IRequest.getIpUnderstandUrl(), "", -2, false);
            }
        });
        this.rlNotSupport.setVisibility(View.VISIBLE);
        this.browserWebView.setClickable(false);
        this.browserWebView.setFocusable(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.mRxManager;
        if (rxManager != null) {
            rxManager.clear();
            this.mRxManager = null;
        }
    }
}
