package com.tron.wallet.business.tabdapp.home;

import android.content.Intent;
import android.net.Uri;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.finance.bean.Result;
import com.tron.wallet.business.finance.bean.ScanQROutput;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.controller.MostVisitDAppController;
import com.tron.wallet.business.tabdapp.home.DAppBrowserContract;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component.ConfirmModel;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.common.config.ErrorConstant;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UriUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.functions.Consumer;
import org.tron.protos.Protocol;
public class DAppBrowserPresenter extends DAppBrowserContract.Presenter {
    @Override
    public void getData() {
    }

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.DAPP, new Consumer() {
            @Override
            public final void accept(Object obj) {
                BrowserTabManager.getInstance().checkAndReloadWebPage();
            }
        });
        this.mRxManager.on(Event.EVENT_REPORT_URL_DONE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$1(obj);
            }
        });
    }

    public void lambda$onStart$1(Object obj) throws Exception {
        if ((obj instanceof String) && obj.equals("close")) {
            ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().goBack();
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null && parseActivityResult.getContents() != null) {
            String stringExtra = intent.getStringExtra("SCAN_RESULT");
            Result<ScanQROutput> result = new Result<>();
            result.setData(new ScanQROutput());
            result.getData().setQrCode(stringExtra);
            ((DAppBrowserContract.View) this.mView).getBrowserContent().loadJsResult(result);
        }
        if (i2 != -1 || intent == null) {
            if (intent == null && i == 20001) {
                Result result2 = new Result();
                result2.setCode(ErrorConstant.openFileChooserError);
                ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().onReceiveValue(Uri.EMPTY);
                ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().loadJs("openFileChooser", result2);
                return;
            }
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("cancle", false);
        LogUtils.i("DAppSignMessage:cancle:", booleanExtra + "");
        if (booleanExtra) {
            ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().post(new Runnable() {
                @Override
                public final void run() {
                    lambda$onActivityResult$2(r2);
                }
            });
            ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getAndroidJs().revertIsExist();
        } else if (i == 2022) {
            try {
                ConfirmModel confirmModel = (ConfirmModel) intent.getSerializableExtra("currentmodel");
                String stringExtra2 = intent.getStringExtra("transactionString");
                LogUtils.i("DAppSignMessage2", stringExtra2);
                String stringExtra3 = intent.getStringExtra("password");
                boolean booleanExtra2 = intent.getBooleanExtra("selectedModel", false);
                boolean booleanExtra3 = intent.getBooleanExtra("hasPassword", false);
                if (!StringTronUtil.isEmpty(stringExtra2)) {
                    final String str = "javascript:" + ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getAndroidJs().getFunctionName() + "('" + stringExtra2 + "')";
                    ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().post(new Runnable() {
                        @Override
                        public final void run() {
                            lambda$onActivityResult$3(str);
                        }
                    });
                    MostVisitDAppController.getInstance().dAppSigned(((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getWebUrl());
                    try {
                        Protocol.Transaction packTransaction = WalletUtils.packTransaction(stringExtra2);
                        AnalyticsHelper.TransactionReporting.TransactionReportingApp(packTransaction, true);
                        DataStatHelper.transactionReportingApp(packTransaction, true);
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                }
                ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getAndroidJs().setModel(booleanExtra2, confirmModel, booleanExtra3, stringExtra3);
                ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getAndroidJs().revertIsExist();
            } catch (Exception e2) {
                LogUtils.e(e2);
            }
        } else if (i != 2024) {
            if (i == 20001) {
                try {
                    ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().onReceiveValue(UriUtils.INSTANCE.getImageUri(((DAppBrowserContract.View) this.mView).getIContext(), intent));
                } catch (Exception e3) {
                    SentryUtil.captureException(e3);
                }
            } else if (i != 2031 || intent.getBooleanExtra(WalletDetailActivity.WATCH_UPDATE_COLD_RESULT_SUCCESS, false)) {
            } else {
                ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().post(new Runnable() {
                    @Override
                    public final void run() {
                        lambda$onActivityResult$5(r2);
                    }
                });
                ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getAndroidJs().revertIsExist();
            }
        } else {
            String stringExtra4 = intent.getStringExtra("sign_key");
            LogUtils.i("DAppSignMessage3", stringExtra4);
            if (StringTronUtil.isEmpty(stringExtra4)) {
                return;
            }
            final String str2 = "javascript:" + ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getAndroidJs().getFunctionName() + "('" + stringExtra4 + "')";
            ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().post(new Runnable() {
                @Override
                public final void run() {
                    lambda$onActivityResult$4(str2);
                }
            });
            ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getAndroidJs().revertIsExist();
            MostVisitDAppController.getInstance().dAppSigned(((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().getWebUrl());
        }
    }

    public void lambda$onActivityResult$2(String str) {
        ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().lambda$loadJs$4(str);
    }

    public void lambda$onActivityResult$3(String str) {
        ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().lambda$loadJs$4(str);
    }

    public void lambda$onActivityResult$4(String str) {
        ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().lambda$loadJs$4(str);
    }

    public void lambda$onActivityResult$5(String str) {
        ((DAppBrowserContract.View) this.mView).getBrowserContent().getWebView().lambda$loadJs$4(str);
    }
}
