package com.tron.wallet.business.tabmy.myhome;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.migrate.MigrateActivity;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.tabmy.myhome.SettingActivity;
import com.tron.wallet.business.tabmy.myhome.dappauthorized.DappAuthorizedActivity;
import com.tron.wallet.business.tabmy.myhome.settings.MessageNotificationActivity;
import com.tron.wallet.business.tabmy.myhome.settings.NodeSpeedTestActivity;
import com.tron.wallet.business.tabmy.myhome.settings.ServerSelectActivity;
import com.tron.wallet.business.tabmy.myhome.settings.bean.NodeBean;
import com.tron.wallet.business.upgraded.HdUpdateActivity;
import com.tron.wallet.business.welcome.WelcomeActivity;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.Item;
import com.tron.wallet.common.components.SwitchButton;
import com.tron.wallet.common.components.popupwindow.CommonPopWindow;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.config.UpgradeParamSetting;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.rb.RB;
import com.tron.wallet.databinding.AcSettingBinding;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.cli.HelpFormatter;
public class SettingActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private static final String TAG = "SettingActivity";
    private AcSettingBinding binding;
    View changeHdView;
    private CommonPopWindow commonPopWindow;
    RelativeLayout console;
    TextView getTvNetWorkTitle;
    boolean isUsd;
    View ivMigrateDot;
    LinearLayout llNodeRoot;
    private List<Item> mList1 = new ArrayList();
    private List<Item> mList2 = new ArrayList();
    private RxManager mRxManager;
    RelativeLayout messageNotification;
    View migrateView;
    RelativeLayout node;
    private OkHttpClient okHttpClient;
    RelativeLayout rlMultiImage;
    RelativeLayout rlNetwork;
    RelativeLayout rlServerRoot;
    RelativeLayout rlSwitchVersion;
    LinearLayout root;
    TextView server_name;
    private boolean stopped;
    SwitchButton switchButton;
    private ScheduledFuture<?> taskFuture;
    TextView tvLanguage;
    TextView tvMoney;
    TextView tvNetWorkName;
    TextView tvNode;
    TextView tvNodeSpeed;
    int type;

    @Override
    protected void setLayout() {
        AcSettingBinding inflate = AcSettingBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        ScrollView root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.setting));
    }

    public void mappingId() {
        this.switchButton = this.binding.switchButton;
        this.tvLanguage = this.binding.tvLanguage;
        this.tvMoney = this.binding.tvMoney;
        this.tvNode = this.binding.tvNode;
        this.root = this.binding.root;
        this.node = this.binding.node;
        this.rlNetwork = this.binding.network;
        this.llNodeRoot = this.binding.llNodeRoot;
        this.getTvNetWorkTitle = this.binding.networkSettingTitle;
        this.tvNetWorkName = this.binding.tvNetworkName;
        this.tvNodeSpeed = this.binding.tvNodeSpeed;
        this.rlServerRoot = this.binding.server;
        this.server_name = this.binding.serverName;
        this.console = this.binding.console;
        this.rlSwitchVersion = this.binding.switchVersion;
        this.messageNotification = this.binding.messageNotification;
        this.rlMultiImage = this.binding.rlMultiImage;
        this.ivMigrateDot = this.binding.ivDotTip;
        this.migrateView = this.binding.migrate;
        this.changeHdView = this.binding.changeHd;
    }

    @Override
    protected void processData() {
        initSwitch();
        String useLanguage = SpAPI.THIS.useLanguage();
        useLanguage.hashCode();
        char c = 65535;
        switch (useLanguage.hashCode()) {
            case 49:
                if (useLanguage.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (useLanguage.equals("2")) {
                    c = 1;
                    break;
                }
                break;
            case 51:
                if (useLanguage.equals("3")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.tvLanguage.setText(getString(R.string.english));
                this.isUsd = true;
                this.type = 1;
                break;
            case 1:
                this.tvLanguage.setText(getString(R.string.chinese));
                this.isUsd = false;
                this.type = 2;
                break;
            case 2:
                this.tvLanguage.setText(getString(R.string.japanese));
                this.isUsd = true;
                this.type = 3;
                break;
        }
        this.tvMoney.setText(SpAPI.THIS.isUsdPrice() ? "USD" : "CNY");
        this.mList1.clear();
        this.mList2.clear();
        this.mList1.add(new Item(R.mipmap.english, getString(R.string.english), this.type == 1));
        if (TronConfig.languageLocalConfig != 1 && TronConfig.languageLocalConfig != 4) {
            this.mList1.add(new Item(R.mipmap.chinese, getString(R.string.chinese), this.type == 2));
        }
        this.mList2.add(new Item(R.mipmap.usd, "USD", SpAPI.THIS.isUsdPrice()));
        if (TronConfig.languageLocalConfig != 2 && TronConfig.languageLocalConfig != 4) {
            this.mList2.add(new Item(R.mipmap.cny, "CNY", !SpAPI.THIS.isUsdPrice()));
        }
        if (SpAPI.THIS.isCold()) {
            this.getTvNetWorkTitle.setVisibility(View.GONE);
            this.rlNetwork.setVisibility(View.GONE);
            this.node.setVisibility(View.GONE);
            this.rlServerRoot.setVisibility(View.GONE);
            this.changeHdView.setVisibility(View.GONE);
        } else {
            this.getTvNetWorkTitle.setVisibility(View.VISIBLE);
            this.rlNetwork.setVisibility(View.VISIBLE);
            this.node.setVisibility(View.VISIBLE);
            this.rlServerRoot.setVisibility(View.VISIBLE);
        }
        RxManager rxManager = new RxManager();
        this.mRxManager = rxManager;
        rxManager.on(Event.SWITCH_CHAIN, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
        initNodeSetting();
        initNetWorkSetting();
        if (SpAPI.THIS.isCold()) {
            this.rlMultiImage.setVisibility(View.GONE);
        }
        this.mFirebaseAnalytics.logEvent("Click_Setting", null);
    }

    public void lambda$processData$0(Object obj) throws Exception {
        initNodeSetting();
    }

    protected void initNodeSetting() {
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.PRE_RELEASE) {
            if (SpAPI.THIS.getCurIsMainChain()) {
                this.tvNetWorkName.setText(R.string.node_mainnet);
            } else {
                this.tvNetWorkName.setText(R.string.node_dappchain);
            }
        } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            this.tvNetWorkName.setText(R.string.node_shasta);
        } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.TEST) {
            this.tvNetWorkName.setText(R.string.switch_nile);
        }
    }

    public long startTestNode(String str, boolean z) {
        final long startSocketSpeedNode = startSocketSpeedNode(str, z);
        if (startSocketSpeedNode > 0) {
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$startTestNode$1(startSocketSpeedNode);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    lambda$startTestNode$2();
                }
            });
        }
        return startSocketSpeedNode;
    }

    public void lambda$startTestNode$1(long j) {
        TextView textView = this.tvNodeSpeed;
        textView.setText(j + " ms");
    }

    public void lambda$startTestNode$2() {
        this.tvNodeSpeed.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
    }

    public long startSocketSpeedNode(final String str, boolean z) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        FutureTask futureTask = (FutureTask) newSingleThreadExecutor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                long currentTimeMillis = System.currentTimeMillis();
                long j = 0;
                Socket socket = null;
                try {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception e) {
                    e = e;
                }
                if (str.contains(":")) {
                    String[] split = str.split(":");
                    Socket socket2 = new Socket(split[0], Integer.parseInt(split[1]));
                    try {
                        socket2.isConnected();
                        j = System.currentTimeMillis() - currentTimeMillis;
                        LogUtils.i("alex", "test node runtime : " + j);
                        socket2.close();
                    } catch (Exception e2) {
                        e = e2;
                        socket = socket2;
                        LogUtils.i("alex", "test node runtime : " + e.toString());
                        if (socket != null) {
                            socket.close();
                        }
                        return Long.valueOf(j);
                    } catch (Throwable th2) {
                        th = th2;
                        socket = socket2;
                        if (socket != null) {
                            socket.close();
                        }
                        throw th;
                    }
                    return Long.valueOf(j);
                }
                throw new IllegalArgumentException("IP is Not correct");
            }
        });
        newSingleThreadExecutor.execute(futureTask);
        try {
            return ((Long) futureTask.get(6L, TimeUnit.SECONDS)).longValue();
        } catch (TimeoutException e) {
            LogUtils.e(e);
            return -1L;
        } catch (Exception e2) {
            LogUtils.e(e2);
            return 0L;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.tvNetWorkName.setVisibility(View.VISIBLE);
        if (SpAPI.THIS.isShasta()) {
            this.tvNetWorkName.setText(R.string.node_shasta);
        }
        if (SpAPI.THIS.isCold()) {
            this.messageNotification.setVisibility(View.GONE);
            this.changeHdView.setVisibility(View.GONE);
        } else {
            this.messageNotification.setVisibility(View.VISIBLE);
        }
        if (this.stopped) {
            initNetWorkSetting();
        }
        if (TextUtils.equals(MigrateConfig.APP_ID_GLOBAL, "com.tronlinkpro.wallet") || SpAPI.THIS.isCold()) {
            this.migrateView.setVisibility(View.GONE);
        } else if (SpAPI.THIS.getMigrateWarning()) {
            this.ivMigrateDot.setVisibility(View.GONE);
        }
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                SettingActivity.lambda$onResume$3(observableEmitter);
            }
        }).compose(RxSchedulers.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onResume$4((Boolean) obj);
            }
        });
    }

    public static void lambda$onResume$3(ObservableEmitter observableEmitter) throws Exception {
        List<HdTronRelationshipBean> queryAllRelationBeans = HDTronWalletController.queryAllRelationBeans();
        HashSet hashSet = new HashSet();
        boolean z = false;
        boolean z2 = false;
        for (HdTronRelationshipBean hdTronRelationshipBean : queryAllRelationBeans) {
            if (!hdTronRelationshipBean.getNonHd()) {
                z2 = true;
            }
            if (!hashSet.contains(hdTronRelationshipBean.getRelationshipHDAddress())) {
                hashSet.add(hdTronRelationshipBean.getRelationshipHDAddress());
            }
        }
        if (z2 && hashSet.size() > 1) {
            z = true;
        }
        observableEmitter.onNext(Boolean.valueOf(z));
        observableEmitter.onComplete();
    }

    public void lambda$onResume$4(Boolean bool) throws Exception {
        if (UpgradeParamSetting.showChangedHdEdit() && bool.booleanValue() && !SpAPI.THIS.isCold()) {
            this.changeHdView.setVisibility(View.VISIBLE);
        } else {
            this.changeHdView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopTimer();
    }

    public class fun2 extends TimerTask {
        fun2() {
        }

        @Override
        public void run() {
            if (SpAPI.THIS.getServerUserPrefer() == 0) {
                updateUSASpeed();
            } else {
                updateSigaporeSpeed();
            }
            final NodeBean nodeBean = new NodeBean(SpAPI.THIS.getCurrentChain().fullNode);
            ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
                @Override
                public final void run() {
                    SettingActivity.2.this.lambda$run$0(nodeBean);
                }
            });
        }

        public void lambda$run$0(NodeBean nodeBean) {
            startTestNode(nodeBean.getAddress(), true);
        }
    }

    private void initNetWorkSetting() {
        this.stopped = false;
        this.okHttpClient = new OkHttpClient();
        this.taskFuture = Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new fun2(), 0L, 2000L, TimeUnit.MILLISECONDS);
    }

    public class fun3 extends NoDoubleClickListener2 {
        fun3() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.change_hd:
                    go(HdUpdateActivity.class);
                    return;
                case R.id.console:
                    go(ConsoleActivity.class);
                    return;
                case R.id.dapp_connection:
                    AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_DAPP_CONNECTION_MANAGEMENT);
                    DappAuthorizedActivity.start(mContext, WalletUtils.getSelectedWallet().getWalletName());
                    return;
                case R.id.language:
                    AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_LANGUAGE);
                    commonPopWindow = new CommonPopWindow((Activity) mContext, mList1);
                    commonPopWindow.setListener(new CommonPopWindow.OnItemClickListener() {
                        @Override
                        public final void onItem(int i) {
                            SettingActivity.3.this.lambda$onNoDoubleClick$0(i);
                        }
                    });
                    commonPopWindow.showAtLocation(root, 81, 0, 0);
                    return;
                case R.id.message_notification:
                    AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_NOTIFICATIONS);
                    go(MessageNotificationActivity.class);
                    return;
                case R.id.migrate:
                    go(MigrateActivity.class);
                    SpAPI.THIS.setMigrateWarning(true);
                    return;
                case R.id.money:
                    AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_CURRENCY_UNIT);
                    commonPopWindow = new CommonPopWindow((Activity) mContext, mList2);
                    commonPopWindow.setListener(new CommonPopWindow.OnItemClickListener() {
                        @Override
                        public final void onItem(int i) {
                            SettingActivity.3.this.lambda$onNoDoubleClick$1(i);
                        }
                    });
                    commonPopWindow.showAtLocation(root, 81, 0, 0);
                    return;
                case R.id.network:
                    AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_SWITCH_NETWORK);
                    go(ChainSettingActivity.class);
                    return;
                case R.id.node:
                    AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_SWITCH_NODE);
                    goNodesettingActivity();
                    return;
                case R.id.server:
                    AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_SWITCH_SERVER);
                    go(ServerSelectActivity.class);
                    return;
                case R.id.switch_version:
                    go(SwitchVersionActivity.class);
                    return;
                default:
                    return;
            }
        }

        public void lambda$onNoDoubleClick$0(int i) {
            if (i == 0) {
                if ("2".equals(SpAPI.THIS.useLanguage()) || "3".equals(SpAPI.THIS.useLanguage())) {
                    SpAPI.THIS.setLanguage("1");
                    LanguageUtils.setLocal(mContext);
                    LanguageUtils.onConfigurationChanged(getApplicationContext());
                    Intent intent = new Intent(mContext, WelcomeActivity.class);
                    intent.setFlags(268468224);
                    mContext.startActivity(intent);
                    finish();
                }
            } else if (i == 1) {
                if ("1".equals(SpAPI.THIS.useLanguage()) || "3".equals(SpAPI.THIS.useLanguage())) {
                    SpAPI.THIS.setLanguage("2");
                    LanguageUtils.setLocal(mContext);
                    LanguageUtils.onConfigurationChanged(getApplicationContext());
                    Intent intent2 = new Intent(mContext, WelcomeActivity.class);
                    intent2.setFlags(268468224);
                    mContext.startActivity(intent2);
                    finish();
                }
            } else if ("1".equals(SpAPI.THIS.useLanguage()) || "2".equals(SpAPI.THIS.useLanguage())) {
                SpAPI.THIS.setLanguage("3");
                LanguageUtils.setLocal(mContext);
                LanguageUtils.onConfigurationChanged(getApplicationContext());
                Intent intent3 = new Intent(mContext, WelcomeActivity.class);
                intent3.setFlags(268468224);
                mContext.startActivity(intent3);
                finish();
            }
            processData();
            commonPopWindow.dismiss();
        }

        public void lambda$onNoDoubleClick$1(int i) {
            if (i == 0) {
                SpAPI.THIS.setIsUsdPrice(true);
            } else {
                SpAPI.THIS.setIsUsdPrice(false);
            }
            processData();
            if (mRxManager == null) {
                mRxManager = new RxManager();
            }
            mRxManager.post(RB.RB_PRICE_SWITCH, "11");
            commonPopWindow.dismiss();
        }
    }

    public void setClick() {
        3 r0 = new fun3();
        this.binding.language.setOnClickListener(r0);
        this.binding.money.setOnClickListener(r0);
        this.binding.node.setOnClickListener(r0);
        this.binding.changeHd.setOnClickListener(r0);
        this.binding.console.setOnClickListener(r0);
        this.binding.switchVersion.setOnClickListener(r0);
        this.binding.server.setOnClickListener(r0);
        this.binding.messageNotification.setOnClickListener(r0);
        this.binding.dappConnection.setOnClickListener(r0);
        this.binding.migrate.setOnClickListener(r0);
        this.binding.network.setOnClickListener(r0);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_CLICK_BACK);
        finish();
    }

    @Override
    public void onDestroy() {
        CommonPopWindow commonPopWindow = this.commonPopWindow;
        if (commonPopWindow != null) {
            commonPopWindow.dismiss();
        }
        RxManager rxManager = this.mRxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
        super.onDestroy();
        stopTimer();
    }

    private void initSwitch() {
        if (SpAPI.THIS.getQRMultiImageIsOpen()) {
            this.switchButton.setChecked(true);
        } else {
            this.switchButton.setChecked(false);
        }
        this.switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(SwitchButton switchButton, boolean z) {
                SettingActivity.lambda$initSwitch$5(switchButton, z);
            }
        });
    }

    public static void lambda$initSwitch$5(SwitchButton switchButton, boolean z) {
        AnalyticsHelper.logEvent(AnalyticsHelper.Setting.CLICK_SETTING_QR_CODE_SPLIT);
        SpAPI.THIS.setQRMultiImageIsOpen(z);
    }

    public void goNodesettingActivity() {
        if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.NILETEST || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.PRE_RELEASE || IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.TEST) {
            int i = 0;
            if (SpAPI.THIS.getCurIsMainChain()) {
                List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
                while (i < allChainJson.size()) {
                    ChainBean chainBean = allChainJson.get(i);
                    if (chainBean.isMainChain) {
                        NodeSpeedTestActivity.start(this, chainBean);
                        return;
                    }
                    i++;
                }
                return;
            }
            List<ChainBean> allChainJson2 = SpAPI.THIS.getAllChainJson();
            if (allChainJson2 != null) {
                while (i < allChainJson2.size()) {
                    ChainBean chainBean2 = allChainJson2.get(i);
                    if (!chainBean2.isMainChain) {
                        NodeSpeedTestActivity.start(this, chainBean2);
                    }
                    i++;
                }
            }
        } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            ChainBean chainBean3 = new ChainBean();
            chainBean3.isMainChain = true;
            chainBean3.chainId = "Shasta";
            chainBean3.isSelect = true;
            chainBean3.chainName = "MainChain";
            chainBean3.eventServer = "";
            chainBean3.fullNode = "grpc.shasta.trongrid.io:50051";
            chainBean3.mainChainContract = "";
            NodeSpeedTestActivity.start(this, chainBean3);
        }
    }

    public void updateUSASpeed() {
        FirebasePerfOkHttpClient.enqueue(this.okHttpClient.newCall(new Request.Builder().url("https://list.tronlink.org/api/v1/wallet/getCoinCapTrxPrice").get().build()), new fun4(System.currentTimeMillis()));
    }

    public class fun4 implements Callback {
        final long val$startTime;

        fun4(long j) {
            this.val$startTime = j;
        }

        @Override
        public void onFailure(Call call, IOException iOException) {
            LogUtils.d(SettingActivity.TAG, "onFailure:" + iOException.toString());
            runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    SettingActivity.4.this.lambda$onFailure$0();
                }
            });
        }

        public void lambda$onFailure$0() {
            server_name.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                response.close();
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (stopped) {
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            SettingActivity settingActivity = SettingActivity.this;
            final long j = this.val$startTime;
            settingActivity.runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    SettingActivity.4.this.lambda$onResponse$1(currentTimeMillis, j);
                }
            });
        }

        public void lambda$onResponse$1(long j, long j2) {
            TextView textView = server_name;
            textView.setText((j - j2) + " ms");
        }
    }

    public void updateSigaporeSpeed() {
        FirebasePerfOkHttpClient.enqueue(this.okHttpClient.newCall(new Request.Builder().url("http://52.76.86.154:8080/api/v1/wallet/getCoinCapTrxPrice").get().build()), new fun5(System.currentTimeMillis()));
    }

    public class fun5 implements Callback {
        final long val$startTime;

        fun5(long j) {
            this.val$startTime = j;
        }

        @Override
        public void onFailure(Call call, IOException iOException) {
            LogUtils.d(SettingActivity.TAG, "onFailure:" + iOException.toString());
            runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    SettingActivity.5.this.lambda$onFailure$0();
                }
            });
        }

        public void lambda$onFailure$0() {
            server_name.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                response.close();
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (stopped) {
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            SettingActivity settingActivity = SettingActivity.this;
            final long j = this.val$startTime;
            settingActivity.runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    SettingActivity.5.this.lambda$onResponse$1(currentTimeMillis, j);
                }
            });
        }

        public void lambda$onResponse$1(long j, long j2) {
            TextView textView = server_name;
            textView.setText((j - j2) + " ms");
        }
    }

    private void stopTimer() {
        this.stopped = true;
        ScheduledFuture<?> scheduledFuture = this.taskFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }
}
