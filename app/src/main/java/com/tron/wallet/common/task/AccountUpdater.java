package com.tron.wallet.common.task;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.rb.RbUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class AccountUpdater {
    private static final String TAG = "AccountUpdater";
    private static AccountUpdaterRunnable mAccountUpdaterRunnable;
    private static Context mContext;
    private static ExecutorService mExecutorService;
    private static long mInterval;
    private static Handler mTaskHandler;
    private static boolean running;

    public static boolean isInitialized() {
        return mContext != null;
    }

    public static boolean isRunning() {
        return running;
    }

    AccountUpdater() {
    }

    public static void init(Context context, long j) {
        if (mContext == null) {
            mContext = context;
            if (Build.VERSION.SDK_INT < 24) {
                mInterval = TronConfig.ACCOUNT_UPDATE_FOREGROUND_INTERVAL;
            } else {
                mInterval = j;
            }
            running = false;
            mTaskHandler = new Handler(Looper.getMainLooper());
            mAccountUpdaterRunnable = new AccountUpdaterRunnable();
            mExecutorService = Executors.newSingleThreadExecutor();
        }
    }

    public static void start() {
        if (SpAPI.THIS.isCold()) {
            return;
        }
        stop();
        running = true;
        mTaskHandler.post(mAccountUpdaterRunnable);
    }

    public static void startDelayed(long j) {
        stop();
        running = true;
        mTaskHandler.postDelayed(mAccountUpdaterRunnable, j);
    }

    public static void stop() {
        running = false;
        mTaskHandler.removeCallbacks(mAccountUpdaterRunnable);
    }

    public static void setInterval(long j, boolean z) {
        mInterval = j;
        if (z) {
            start();
        }
    }

    public static void singleShot(long j) {
        LogUtils.i("account update", "singleShot");
        mTaskHandler.removeCallbacks(mAccountUpdaterRunnable);
        if (j <= 0) {
            mTaskHandler.post(mAccountUpdaterRunnable);
        } else {
            mTaskHandler.postDelayed(mAccountUpdaterRunnable, j);
        }
    }

    public static class AccountUpdaterRunnable implements Runnable {
        private AccountUpdaterRunnable() {
        }

        @Override
        public void run() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                private Protocol.Account account;
                private GrpcAPI.AccountResourceMessage accountResMessage;
                private byte[] address;
                private Wallet selectedWallet;

                @Override
                public void doOnBackground() {
                    if (AccountUpdater.mContext != null) {
                        try {
                            LogUtils.i("account update", "AccountUpdaterRunnable");
                            if (this.selectedWallet == null || !SpAPI.THIS.getSelectedWallet().equals(this.selectedWallet.getWalletName())) {
                                this.selectedWallet = WalletUtils.getSelectedWallet();
                            }
                            byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(this.selectedWallet.getAddress());
                            this.address = decodeFromBase58Check;
                            this.account = TronAPI.queryAccount(decodeFromBase58Check, false);
                            this.accountResMessage = TronAPI.getAccountRes(this.address);
                            if (!SpAPI.THIS.getHasAccount(this.selectedWallet.getWalletName())) {
                                SpAPI.THIS.setHasAccount(this.selectedWallet.getWalletName(), true);
                            }
                            LogUtils.i("account update", this.accountResMessage.toString());
                            WalletUtils.saveAccount(AccountUpdater.mContext, this.selectedWallet.getWalletName(), this.account);
                            WalletUtils.saveAccountRes(AccountUpdater.mContext, this.selectedWallet.getWalletName(), this.accountResMessage);
                        } catch (Exception e) {
                            LogUtils.e("account update", "getAccount NULL");
                            LogUtils.e(e);
                        }
                    }
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            LogUtils.i("Account_UPDATER", "Account updated");
                            if (fun1.this.selectedWallet != null && fun1.this.selectedWallet.isShieldedWallet()) {
                                RbUtil.THIS.sendShieldAccounts();
                            } else {
                                RbUtil.THIS.sendAccounts();
                            }
                            if (AccountUpdater.running) {
                                AccountUpdater.mTaskHandler.removeCallbacksAndMessages(null);
                                AccountUpdater.mTaskHandler.postDelayed(AccountUpdater.mAccountUpdaterRunnable, AccountUpdater.mInterval);
                            }
                        }
                    });
                }
            }, AccountUpdater.mExecutorService);
        }
    }
}
