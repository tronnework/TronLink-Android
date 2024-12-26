package com.tron.wallet.business.cold;

import android.content.Intent;
import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.cold.ColdContract;
import com.tron.wallet.business.confirm.core.ConfirmTransactionNewActivity;
import com.tron.wallet.business.confirm.parambuilder.utils.ParamBuildUtils;
import com.tron.wallet.business.confirm.signed.SignedTransactionNewActivity;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.common.bean.ColdFailLogBean;
import com.tron.wallet.common.bean.QrBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.task.PriceUpdater;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.ColdFailLogBeanDaoManager;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import org.bouncycastle.util.encoders.Hex;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class ColdPresenter extends ColdContract.Presenter {
    private static final String TAG = "ColdPresenter";
    private Gson gson = new Gson();
    private Wallet mSelectedWallet;

    @Override
    public Wallet getWallet() {
        return this.mSelectedWallet;
    }

    @Override
    public void updateSelectedWallet() {
    }

    @Override
    protected void onStart() {
        PriceUpdater.start();
        TronConfig.balance10_TRX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        TronConfig.balance20 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mSelectedWallet = getColdWalletSelected();
        refreshUI();
        initRxEvent();
    }

    private void refreshUI() {
        if (this.mSelectedWallet != null) {
            ((ColdContract.View) this.mView).refreshOfflineWallet(this.mSelectedWallet);
        }
        refreshSafeTipView();
        ((ColdContract.View) this.mView).showNoNetTipView(!SpAPI.THIS.getNoNetIsClosed());
    }

    protected void initRxEvent() {
        this.mRxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$0(obj);
            }
        });
        this.mRxManager.on(Event.SELECTEDSHIELDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$1(obj);
            }
        });
        this.mRxManager.on("CONNECT", new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$2(obj);
            }
        });
        this.mRxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initRxEvent$3(obj);
            }
        });
    }

    public void lambda$initRxEvent$0(Object obj) throws Exception {
        if (this.mSelectedWallet.getWalletName().equals((String) obj)) {
            return;
        }
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mSelectedWallet = selectedWallet;
        if (selectedWallet != null) {
            refreshSafeTipView();
            TronConfig.balance10_TRX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            TronConfig.balance20 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            ((ColdContract.View) this.mView).refreshOfflineWallet(this.mSelectedWallet);
            refreshUI();
        }
    }

    public void lambda$initRxEvent$1(Object obj) throws Exception {
        String str = (String) obj;
        if (this.mSelectedWallet.getWalletName().equals(str)) {
            return;
        }
        Wallet wallet = WalletUtils.getWallet(str);
        this.mSelectedWallet = wallet;
        if (wallet != null) {
            refreshSafeTipView();
            TronConfig.balance10_TRX = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            TronConfig.balance20 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            ((ColdContract.View) this.mView).refreshOfflineWallet(this.mSelectedWallet);
            refreshUI();
        }
    }

    public void lambda$initRxEvent$2(Object obj) throws Exception {
        ((ColdContract.View) this.mView).refreshOfflineShastaView();
    }

    public void lambda$initRxEvent$3(Object obj) throws Exception {
        ((ColdContract.View) this.mView).updateColdView();
    }

    @Override
    public void backup() {
        if (this.mSelectedWallet != null) {
            WalletDetailActivity.startActivity(((ColdContract.View) this.mView).getIContext(), this.mSelectedWallet.getWalletName(), this.mSelectedWallet.isShieldedWallet());
        } else {
            WalletDetailActivity.startActivity(((ColdContract.View) this.mView).getIContext(), null, false);
        }
    }

    private void refreshSafeTipView() {
        Wallet wallet = this.mSelectedWallet;
        if (wallet != null && wallet.getCreateType() == 0 && WalletUtils.hasMnemonic(this.mSelectedWallet.getWalletName()) && !SpAPI.THIS.isBackUp(this.mSelectedWallet.getWalletName())) {
            ((ColdContract.View) this.mView).showOrHidenSafeTipView(true);
        } else {
            ((ColdContract.View) this.mView).showOrHidenSafeTipView(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean checkWalletWatchOnly() {
        Wallet wallet = this.mSelectedWallet;
        return wallet != null && wallet.isWatchOnly();
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        Wallet wallet;
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null) {
            if (parseActivityResult.getContents() != null) {
                try {
                    QrBean qrBean = (QrBean) this.gson.fromJson(intent.getStringExtra("SCAN_RESULT"),  QrBean.class);
                    String address = qrBean.getAddress();
                    if (!this.mSelectedWallet.getAddress().equals(address)) {
                        for (String str : WalletUtils.getWalletNames()) {
                            if (!TextUtils.isEmpty(str) && (wallet = WalletUtils.getWallet(str)) != null && wallet.getAddress().equals(address)) {
                                RxBus.getInstance().post(Event.SELECTEDWALLET, str);
                                if (!wallet.isShieldedWallet()) {
                                    WalletUtils.setSelectedWallet(str);
                                }
                                LogUtils.e("qys", "onActivityResult: " + str);
                                ((ColdContract.View) this.mView).toast(((ColdContract.View) this.mView).getIContext().getResources().getString(R.string.auto_exchange));
                            }
                        }
                        ((ColdContract.View) this.mView).toast(((ColdContract.View) this.mView).getIContext().getString(R.string.no_address));
                        return;
                    }
                    if (qrBean == null || qrBean.getHexList() == null || qrBean.getHexList().size() == 0) {
                        return;
                    }
                    if (qrBean.getcN() == qrBean.gettN() && (qrBean.gettN() == 0 || qrBean.gettN() == 1)) {
                        if (qrBean.getType() == 15) {
                            return;
                        }
                        if (qrBean.getType() != 98 && qrBean.getType() != 102 && qrBean.getType() != 104 && qrBean.getType() != 103 && qrBean.getType() != 101) {
                            ArrayList arrayList = new ArrayList();
                            for (String str2 : qrBean.getHexList()) {
                                arrayList.add(Protocol.Transaction.parseFrom(Hex.decode(str2)));
                            }
                            ConfirmTransactionNewActivity.startActivity(((ColdContract.View) this.mView).getIContext(), ParamBuildUtils.getColdTransactionParamBuilder(qrBean, arrayList));
                            return;
                        }
                        ConfirmTransactionNewActivity.startActivity(((ColdContract.View) this.mView).getIContext(), ParamBuildUtils.getColdSignMessageBuilder(qrBean, qrBean.getHexList().get(0), qrBean.getType()));
                        return;
                    }
                    SignedTransactionNewActivity.start((BaseActivity) ((ColdContract.View) this.mView).getIContext(), qrBean, null, TronConfig.COLD_W);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    Wallet selectedWallet = WalletUtils.getSelectedWallet();
                    ColdFailLogBean coldFailLogBean = new ColdFailLogBean();
                    coldFailLogBean.activityName = TAG;
                    coldFailLogBean.methodName = "onActivityResult";
                    coldFailLogBean.transactionStr = "";
                    coldFailLogBean.aReturn = "";
                    coldFailLogBean.address = selectedWallet != null ? selectedWallet.getAddress() : "";
                    coldFailLogBean.hasLoad = false;
                    coldFailLogBean.time = System.currentTimeMillis();
                    coldFailLogBean.error = "parse error";
                    ColdFailLogBeanDaoManager.addErrorLog(coldFailLogBean);
                    ((ColdContract.View) this.mView).ToastError(StringTronUtil.getResString(R.string.qr_detail_7));
                    return;
                }
            }
            Wallet selectedWallet2 = WalletUtils.getSelectedWallet();
            ColdFailLogBean coldFailLogBean2 = new ColdFailLogBean();
            coldFailLogBean2.activityName = TAG;
            coldFailLogBean2.methodName = "onActivityResult";
            coldFailLogBean2.transactionStr = "";
            coldFailLogBean2.aReturn = "";
            coldFailLogBean2.address = selectedWallet2 != null ? selectedWallet2.getAddress() : "";
            coldFailLogBean2.hasLoad = false;
            coldFailLogBean2.time = System.currentTimeMillis();
            coldFailLogBean2.error = "scan result.getContents() is null";
            ColdFailLogBeanDaoManager.addErrorLog(coldFailLogBean2);
            return;
        }
        Wallet selectedWallet3 = WalletUtils.getSelectedWallet();
        ColdFailLogBean coldFailLogBean3 = new ColdFailLogBean();
        coldFailLogBean3.activityName = TAG;
        coldFailLogBean3.methodName = "onActivityResult";
        coldFailLogBean3.transactionStr = "";
        coldFailLogBean3.aReturn = "";
        coldFailLogBean3.address = selectedWallet3 != null ? selectedWallet3.getAddress() : "";
        coldFailLogBean3.hasLoad = false;
        coldFailLogBean3.time = System.currentTimeMillis();
        coldFailLogBean3.error = "scan result is null";
        ColdFailLogBeanDaoManager.addErrorLog(coldFailLogBean3);
    }

    public Wallet getColdWalletSelected() {
        Wallet wallet = WalletUtils.getWallet(SpAPI.THIS.getColdWalletSelected());
        if (wallet == null || StringTronUtil.isEmpty(wallet.getAddress())) {
            SpAPI.THIS.removeColdWalletSelected();
            return WalletUtils.getSelectedWallet();
        }
        return wallet;
    }
}
