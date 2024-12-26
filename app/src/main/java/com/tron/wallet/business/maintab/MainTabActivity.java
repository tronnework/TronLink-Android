package com.tron.wallet.business.maintab;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.arasthel.asyncjob.AsyncJob;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.maintab.MainTabContract;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.common.components.MainTabView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.service.TronLinkMessagingService;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.task.TimeUpdate;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.TaskHandler;
import com.tron.wallet.databinding.AcMainBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.net.NetBroadcastReceiver;
import com.tron.wallet.net.rpc.IGrpcClient;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class MainTabActivity extends BaseActivity<MainTabPresenter, MainTabModel> implements MainTabContract.View {
    public static boolean isDestroy = false;
    private static boolean isExit = false;
    private AcMainBinding binding;
    MainTabView mainTabView;
    private NetBroadcastReceiver netBroadcastReceiver;
    ViewGroup root;
    private boolean isStart = false;
    private boolean mIsColdWallet = SpAPI.THIS.isCold();
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            MainTabActivity.isExit = false;
        }
    };

    @Override
    public ViewGroup getRoot() {
        return this.root;
    }

    @Override
    public MainTabView getTabViews() {
        return this.mainTabView;
    }

    @Override
    protected void setLayout() {
        AcMainBinding inflate = AcMainBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    public void mappingId() {
        this.root = this.binding.root;
        this.mainTabView = this.binding.llMainTab;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        isDestroy = false;
    }

    @Override
    protected void processData() {
        AppContextUtil.init(this.mContext, null, true, false);
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public final void doOnBackground() {
                IGrpcClient.THIS.initGRpc();
            }
        });
        ((MainTabPresenter) this.mPresenter).addSome();
        ((MainTabPresenter) this.mPresenter).setOnClickListener(getSupportFragmentManager());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        NetBroadcastReceiver netBroadcastReceiver = new NetBroadcastReceiver();
        this.netBroadcastReceiver = netBroadcastReceiver;
        netBroadcastReceiver.addNetWorkState(this);
        if (Build.VERSION.SDK_INT >= 26) {
            registerReceiver(this.netBroadcastReceiver, intentFilter, 2);
        } else {
            registerReceiver(this.netBroadcastReceiver, intentFilter);
        }
        refreshTab();
        ((MainTabPresenter) this.mPresenter).update();
        if (getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getBooleanExtra(TronConfig.SHIELD_IS_TRC20, false)) {
                this.handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainTabView.setTabSelected(3);
                    }
                }, 1000L);
            }
            intent.getBooleanExtra(TronConfig.NOTIFICATION_IN, false);
        }
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPresenter != 0) {
                    ((MainTabPresenter) mPresenter).getAssetsList();
                }
            }
        }, 1000L);
        handler.post(new Runnable() {
            @Override
            public final void run() {
                lambda$processData$1();
            }
        });
        Looper.myQueue().addIdleHandler(new MyIdleOnce());
    }

    public void lambda$processData$1() {
        if (SpAPI.THIS.isCold()) {
            return;
        }
        try {
            startService(new Intent(getApplicationContext(), TronLinkMessagingService.class));
        } catch (Throwable th) {
            LogUtils.e(th);
            SentryUtil.captureException(th);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.mPresenter != 0) {
            ((MainTabPresenter) this.mPresenter).onNewIntent(intent);
        }
    }

    private void refreshTab() {
        if (this.mIsColdWallet) {
            this.mainTabView.showTab(0, true);
            this.mainTabView.showTab(1, false);
            this.mainTabView.showTab(2, false);
            this.mainTabView.showTab(3, true);
        } else if (IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.SHASTA) {
            this.mainTabView.showTab(1, false);
        }
    }

    @Override
    public void refreshMarketTab(boolean z) {
        this.mIsColdWallet = z;
        refreshTab();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainTabPresenter) this.mPresenter).onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        ((MainTabPresenter) this.mPresenter).onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle, persistableBundle);
    }

    @Override
    public void onCreate2(Bundle bundle) {
        super.onCreate2(bundle);
        ((MainTabPresenter) this.mPresenter).onCreate2(bundle, getSupportFragmentManager());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (AccountUpdater.isInitialized() && PriceUpdater.isInitialized() && this.isStart) {
            if (!AccountUpdater.isRunning()) {
                AccountUpdater.start();
            }
            if (!PriceUpdater.isRunning()) {
                PriceUpdater.start();
            }
            this.isStart = !this.isStart;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (AccountUpdater.isInitialized() && PriceUpdater.isInitialized() && TimeUpdate.isInitialized()) {
            AccountUpdater.stop();
            PriceUpdater.stop();
            TimeUpdate.stop();
            this.isStart = !this.isStart;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetBroadcastReceiver netBroadcastReceiver = this.netBroadcastReceiver;
        if (netBroadcastReceiver != null) {
            unregisterReceiver(netBroadcastReceiver);
        }
        BrowserTabManager.getInstance().clear();
        TaskHandler.getInstance().exit();
        this.binding = null;
        isDestroy = true;
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                fragment.onRequestPermissionsResult(i, strArr, iArr);
            }
        }
        ((MainTabPresenter) this.mPresenter).onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ((MainTabPresenter) this.mPresenter).onActivityResult(i, i2, intent);
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (((MainTabPresenter) this.mPresenter).isSwapMode()) {
                this.mainTabView.setTabSelected(0);
                ((MainTabPresenter) this.mPresenter).isSwap = false;
                return true;
            } else if (getTabViews().getActiveTab() == 2) {
                if (((MainTabPresenter) this.mPresenter).mDappFragment == null || !((MainTabPresenter) this.mPresenter).mDappFragment.handleBackPressed()) {
                    exitSys();
                    return false;
                }
                return true;
            } else {
                exitSys();
                return false;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void exitSys() {
        if (!isExit) {
            isExit = true;
            try {
                ToastAsBottom(R.string.exit_dec);
            } catch (Exception e) {
                LogUtils.e(e);
            }
            this.handler.sendEmptyMessageDelayed(0, 2000L);
            return;
        }
        finish();
        System.exit(0);
    }

    @Override
    public void showTabView(double d) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.mainTabView.setVisibility(View.GONE);
        } else if (d == 1.0d) {
            this.mainTabView.setVisibility(View.VISIBLE);
        }
    }

    public void forceShowAgain(final String str) {
        if (StringTronUtil.isNullOrEmpty(str)) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                IToast.getImageIToast().show(str, R.mipmap.toast_icon_positive);
            }
        });
    }

    private class MyIdleOnce implements MessageQueue.IdleHandler {
        private MyIdleOnce() {
        }

        @Override
        public boolean queueIdle() {
            if (mPresenter != 0) {
                ((MainTabPresenter) mPresenter).getDappJs();
                ((MainTabPresenter) mPresenter).getDappAuthorizedProject();
                ((MainTabPresenter) mPresenter).getBlackList();
                return false;
            }
            return false;
        }
    }
}
