package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.view.View;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.addassets2.repository.KVGlobalController;
import com.tron.wallet.business.confirm.sign.SignTransactionNewActivity;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletType;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.LedgerWallet;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class WatchWalletVerifier {
    private static WatchWalletVerifier instance;
    private WeakReference<Context> contextWeakReference;
    private boolean currentWatchWalletHasPermission = true;
    private RxManager rxManager = new RxManager();
    private String selectedAddress;
    private Wallet selectedWallet;
    private int walletType;

    public static void lambda$readWatchWalletHasOwnerPermission$6(Boolean bool) throws Exception {
    }

    public static void lambda$setWatchWalletHasOwnerPermission$4(Boolean bool) throws Exception {
    }

    private WatchWalletVerifier() {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.selectedWallet = selectedWallet;
        if (selectedWallet != null) {
            this.selectedAddress = selectedWallet.getAddress();
            this.walletType = WalletType.getType(this.selectedWallet);
            readWatchWalletHasOwnerPermission();
        }
        this.rxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$0(obj);
            }
        });
        this.rxManager.on(Event.WATCH_WALLET_VERIFY, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$new$1(obj);
            }
        });
    }

    public void lambda$new$0(Object obj) throws Exception {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.currentWatchWalletHasPermission = true;
        if (selectedWallet == null) {
            this.selectedWallet = null;
            this.selectedAddress = null;
            this.walletType = 1;
            return;
        }
        this.selectedWallet = selectedWallet;
        this.selectedAddress = selectedWallet.getAddress();
        this.walletType = WalletType.getType(selectedWallet);
        readWatchWalletHasOwnerPermission();
    }

    public void lambda$new$1(Object obj) throws Exception {
        if (obj == null || !(obj instanceof String)) {
            return;
        }
        checkVerifyResult((String) obj);
    }

    public static WatchWalletVerifier getInstance() {
        if (instance == null) {
            synchronized (WatchWalletVerifier.class) {
                if (instance == null) {
                    instance = new WatchWalletVerifier();
                }
            }
        }
        return instance;
    }

    private void startVerify(Context context, String str) {
        Protocol.Transaction createCustomContract = TransactionUtils.createCustomContract(TronConfig.COLD_WALLET_VERIFY_CONTRACT);
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ByteArray.toHexString(createCustomContract.toByteArray()));
            QrBean qrBean = new QrBean();
            qrBean.setHexList(arrayList);
            qrBean.setType(99);
            qrBean.setAddress(str);
            SignTransactionNewActivity.start2((BaseActivity) context, qrBean, new TokenBean(), TronConfig.OB_W, null, false);
        } catch (Exception e) {
            SentryUtil.captureException(e);
        }
    }

    public void confirmVerifyDirectly(Context context, String str) {
        this.selectedAddress = str;
        if (this.walletType == 2 && !SpAPI.THIS.getCurIsMainChain()) {
            ((BaseActivity) context).toast(context.getString(R.string.not_support_dappchain));
            return;
        }
        this.contextWeakReference = new WeakReference<>(context);
        startVerify(context, str);
    }

    public void confirmVerify(Context context) {
        confirmVerify(context, R.string.watch_wallet_sign_confirm_info);
    }

    public void confirmVerify(Context context, int i) {
        confirmVerify(context, i, WalletUtils.getSelectedPublicWallet().getAddress());
    }

    public void confirmVerify(final Context context, int i, final String str) {
        if (this.walletType == 2 && !SpAPI.THIS.getCurIsMainChain()) {
            ((BaseActivity) context).toast(context.getString(R.string.not_support_dappchain));
            return;
        }
        this.contextWeakReference = new WeakReference<>(context);
        ConfirmCustomPopupView.getBuilder(context).setTitle(context.getResources().getString(R.string.sign_confirm)).setInfo(context.getResources().getString(i)).setConfirmText(context.getResources().getString(R.string.ok4)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$confirmVerify$2(context, str, view);
            }
        }).build().show();
    }

    public void lambda$confirmVerify$2(Context context, String str, View view) {
        startVerify(context, str);
        FirebaseAnalytics.getInstance(context).logEvent("Click_watch_wallet_verify", null);
    }

    private void checkVerifyResult(String str) {
        boolean z = false;
        String str2 = "";
        if (str != null) {
            try {
                str2 = TransactionUtils.getTransactionSignatureOwner(Protocol.Transaction.parseFrom(Hex.decode(((QrBean) new Gson().fromJson(str,  QrBean.class)).getHexList().get(0))));
                if (!StringTronUtil.isEmpty(str2) && str2.equals(this.selectedAddress)) {
                    setWatchWalletHasOwnerPermission(true);
                    z = true;
                }
            } catch (Exception e) {
                SentryUtil.captureException(e);
            }
        }
        Context context = this.contextWeakReference.get();
        if (context != null) {
            ((BaseActivity) context).toast(context.getResources().getString(z ? R.string.sign_success2 : R.string.sign_fail));
            WalletVerifyResult walletVerifyResult = new WalletVerifyResult();
            walletVerifyResult.setResult(z);
            walletVerifyResult.setAddress(str2);
            this.rxManager.post(Event.WATCH_WALLET_VERIFY_RESULT, walletVerifyResult);
        }
    }

    public boolean getWatchWalletHasOwnerPermission() {
        Wallet wallet = this.selectedWallet;
        if (wallet == null || this.walletType != 2 || wallet.isSamsungWallet() || LedgerWallet.isLedger(this.selectedWallet)) {
            return true;
        }
        return this.currentWatchWalletHasPermission;
    }

    public void setWatchWalletHasOwnerPermission(final boolean z) {
        if (this.walletType != 2 || this.selectedAddress == null) {
            return;
        }
        this.currentWatchWalletHasPermission = z;
        Observable.just(Boolean.valueOf(z)).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                Boolean lambda$setWatchWalletHasOwnerPermission$3;
                lambda$setWatchWalletHasOwnerPermission$3 = lambda$setWatchWalletHasOwnerPermission$3(z, (Boolean) obj);
                return lambda$setWatchWalletHasOwnerPermission$3;
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WatchWalletVerifier.lambda$setWatchWalletHasOwnerPermission$4((Boolean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SentryUtil.captureException((Throwable) obj);
            }
        });
    }

    public Boolean lambda$setWatchWalletHasOwnerPermission$3(boolean z, Boolean bool) throws Exception {
        KVGlobalController.getInstance().setValue("WatchWalletPermission" + this.selectedAddress, "" + z);
        return true;
    }

    private void readWatchWalletHasOwnerPermission() {
        if (this.walletType != 2) {
            return;
        }
        Observable.unsafeCreate(new ObservableSource<Boolean>() {
            @Override
            public void subscribe(Observer<? super Boolean> observer) {
                KVGlobalController kVGlobalController = KVGlobalController.getInstance();
                if ("true".equals(kVGlobalController.getValue("WatchWalletPermission" + selectedAddress))) {
                    currentWatchWalletHasPermission = true;
                } else {
                    currentWatchWalletHasPermission = false;
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WatchWalletVerifier.lambda$readWatchWalletHasOwnerPermission$6((Boolean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SentryUtil.captureException((Throwable) obj);
            }
        });
    }

    public static class WalletVerifyResult {
        private String address;
        private boolean result;

        public String getAddress() {
            return this.address;
        }

        public boolean isResult() {
            return this.result;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setResult(boolean z) {
            this.result = z;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof WalletVerifyResult;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof WalletVerifyResult) {
                WalletVerifyResult walletVerifyResult = (WalletVerifyResult) obj;
                if (walletVerifyResult.canEqual(this) && isResult() == walletVerifyResult.isResult()) {
                    String address = getAddress();
                    String address2 = walletVerifyResult.getAddress();
                    return address != null ? address.equals(address2) : address2 == null;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int i = isResult() ? 79 : 97;
            String address = getAddress();
            return ((i + 59) * 59) + (address == null ? 43 : address.hashCode());
        }

        public String toString() {
            return "WatchWalletVerifier.WalletVerifyResult(result=" + isResult() + ", address=" + getAddress() + ")";
        }
    }
}
