package com.tron.wallet.business.tabdapp.jsbridge;

import android.app.Activity;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import com.google.firebase.messaging.Constants;
import com.google.gson.Gson;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.tabdapp.bean.DappConfirmInput;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.component.ConfirmModel;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.messagesign.DappLocalActivity;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.old.DappConfirmModel;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class DappAcToJs extends BaseAndroidtoJs {
    private int code;
    private ConfirmModel currentModel;
    private String functionName;
    private boolean hasPassword;
    private IToast iToast;
    private boolean isExist;
    private boolean isSelectedModel;
    private DappConfirmModel mModel;
    public OnHeaderNotifyListener onHeaderNotifyListener;
    private String password;

    public static class MessageType {
        public static final String BytesArray = "3";
        public static final String HexStringType = "2";
        public static final String StringType = "1";
    }

    public interface OnHeaderNotifyListener {
        void onHeaderNotify(String str);
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public void revertIsExist() {
        this.isExist = false;
    }

    public void setModel(boolean z, ConfirmModel confirmModel, boolean z2, String str) {
        this.isSelectedModel = z;
        this.hasPassword = z2;
        this.currentModel = confirmModel;
        this.password = str;
    }

    public void setOnHeaderNotifyListener(OnHeaderNotifyListener onHeaderNotifyListener) {
        this.onHeaderNotifyListener = onHeaderNotifyListener;
    }

    @Deprecated
    public DappAcToJs(Activity activity, String str, MyWebView myWebView, String str2, String str3, String str4, int i) {
        this(activity, null, str, myWebView, str2, str3, str4, i);
    }

    public DappAcToJs(Activity activity, Handler handler, String str, MyWebView myWebView, String str2, String str3, String str4, int i, boolean z) {
        super(activity, handler, str, myWebView, z);
        this.isExist = false;
        this.isFinance = z;
        this.weburl = str;
        this.webView = myWebView;
        this.title = str2;
        this.icon = str3;
        this.password = null;
        this.isSelectedModel = false;
        this.hasPassword = false;
        this.code = i;
        this.currentModel = ConfirmModel.SAFE;
    }

    public DappAcToJs(Activity activity, Handler handler, String str, MyWebView myWebView, String str2, String str3, String str4, int i) {
        this(activity, null, str, myWebView, str2, str3, str4, i, false);
    }

    @JavascriptInterface
    public void signMessageV2(String str, String str2, String str3) {
        this.functionName = str2;
        if (checkSign(str)) {
            return;
        }
        DappLocalActivity.SignMessageType signMessageType = DappLocalActivity.SignMessageType.V2_Str;
        str3.hashCode();
        char c = 65535;
        switch (str3.hashCode()) {
            case 49:
                if (str3.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (str3.equals("2")) {
                    c = 1;
                    break;
                }
                break;
            case 51:
                if (str3.equals("3")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                signMessageType = DappLocalActivity.SignMessageType.V2_Str;
                break;
            case 1:
                signMessageType = DappLocalActivity.SignMessageType.V2_Hex;
                break;
            case 2:
                signMessageType = DappLocalActivity.SignMessageType.V2_BytesArray;
                break;
        }
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet.getCreateType() != 7) {
            this.isExist = true;
            DappLocalActivity.start(this.activity, this.weburl, str, signMessageType, selectedWallet.getWalletName());
            return;
        }
        if (this.iToast == null) {
            this.iToast = new IToast();
        }
        this.iToast.showAsBottomn(R.string.transaction_type_not_support);
        this.webView.post(new Runnable() {
            @Override
            public final void run() {
                lambda$signMessageV2$0(r2);
            }
        });
    }

    public void lambda$signMessageV2$0(String str) {
        this.webView.loadUrl(str);
    }

    @android.webkit.JavascriptInterface
    public void sign(java.lang.String r20, java.lang.String r21) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.jsbridge.DappAcToJs.sign(java.lang.String, java.lang.String):void");
    }

    public void lambda$sign$1() {
        this.webView.loadUrl("javascript:onerror('cancel')");
    }

    public void lambda$sign$2(boolean z) {
        if (z) {
            return;
        }
        this.webView.post(new Runnable() {
            @Override
            public final void run() {
                lambda$sign$1();
            }
        });
    }

    public void lambda$sign$3(String str) {
        this.webView.loadUrl(str);
    }

    public void lambda$sign$4(String str) {
        this.webView.loadUrl(str);
    }

    public void lambda$sign$5(String str) {
        this.webView.loadUrl(str);
    }

    private boolean checkSign(String str) {
        if (this.activity == null || this.activity.isDestroyed() || this.activity.isFinishing() || this.isExist) {
            return true;
        }
        if (StringTronUtil.isEmpty(str)) {
            this.webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$checkSign$6(r2);
                }
            });
            return true;
        }
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet == null) {
            this.webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$checkSign$7(r2);
                }
            });
            return true;
        } else if (selectedPublicWallet.isShieldedWallet()) {
            this.webView.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$checkSign$8(r2);
                }
            });
            if (this.iToast == null) {
                this.iToast = new IToast();
            }
            this.iToast.showAsBottomn(R.string.shield_toast);
            return true;
        } else {
            return false;
        }
    }

    public void lambda$checkSign$6(String str) {
        this.webView.loadUrl(str);
    }

    public void lambda$checkSign$7(String str) {
        this.webView.loadUrl(str);
    }

    public void lambda$checkSign$8(String str) {
        this.webView.loadUrl(str);
    }

    private String getSign(Protocol.Transaction transaction) {
        try {
            return WalletUtils.printTransaction(TransactionUtils.sign(TransactionUtils.setTimestamp(transaction), WalletUtils.getWallet(WalletUtils.getSelectedPublicWallet().getWalletName(), this.password).getECKey()));
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return Constants.IPC_BUNDLE_KEY_SEND_ERROR;
        }
    }

    @JavascriptInterface
    public void notifyHeader(String str) {
        OnHeaderNotifyListener onHeaderNotifyListener;
        if (this.activity == null || this.activity.isDestroyed() || this.activity.isFinishing() || (onHeaderNotifyListener = this.onHeaderNotifyListener) == null) {
            return;
        }
        onHeaderNotifyListener.onHeaderNotify(str);
    }

    public void addDappRecord(String str, String str2, String str3) {
        try {
            DappConfirmInput dappConfirmInput = new DappConfirmInput();
            dappConfirmInput.transactionString = str;
            dappConfirmInput.dappName = str2;
            dappConfirmInput.dappUrl = str3;
            RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(dappConfirmInput));
            if (this.mModel == null) {
                this.mModel = new DappConfirmModel();
            }
            this.mModel.addDappRecord(create).subscribe(new IObserver(new ICallback<Object>() {
                @Override
                public void onFailure(String str4, String str5) {
                }

                @Override
                public void onResponse(String str4, Object obj) {
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                }
            }, "addDappRecord"));
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }
}
