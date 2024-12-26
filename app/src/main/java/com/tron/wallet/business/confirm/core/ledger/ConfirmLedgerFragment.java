package com.tron.wallet.business.confirm.core.ledger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.confirm.core.ConfirmTransactionContract;
import com.tron.wallet.business.confirm.core.ConfirmTransactionModel;
import com.tron.wallet.business.confirm.core.ConfirmTransactionPresenter;
import com.tron.wallet.business.confirm.core.ledger.ConfirmLedgerFragment;
import com.tron.wallet.business.confirm.core.ledger.LedgerViewUpdate;
import com.tron.wallet.business.confirm.core.pending.ConfirmPendingFragment;
import com.tron.wallet.business.confirm.core.pending.State;
import com.tron.wallet.business.confirm.parambuilder.bean.BaseParam;
import com.tron.wallet.business.ledger.manage.EquipmentAdapter;
import com.tron.wallet.business.ledger.manage.EquipmentBean;
import com.tron.wallet.business.ledger.manage.EquipmentItemListener;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.ledger.search.PermissionLedgerHelper;
import com.tron.wallet.business.pull.IntentResult;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceDaoManager;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FgConfirmLedgerBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tron.wallet.ledger.bleclient.BleUtils;
import com.tron.wallet.ledger.bleclient.LedgerTrx;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.ledger.bleclient.Transport;
import com.tron.wallet.ledger.blemodule.Device;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.util.encoders.Hex;
import org.tron.api.GrpcAPI;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class ConfirmLedgerFragment extends BaseFragment<ConfirmTransactionPresenter, ConfirmTransactionModel> implements ConfirmTransactionContract.View {
    private EquipmentAdapter adapter;
    private BaseParam baseParam;
    private FgConfirmLedgerBinding binding;
    private ConfirmPendingFragment confirmPendingFragment;
    private LedgerTrx ledgerTrx;
    private LedgerViewUpdate ledgerViewUpdate;
    private Wallet mWallet;
    private PermissionLedgerHelper permissionHelper;
    private boolean signByHash;
    private int transactionType;
    private Transport transport;
    private String unSignMessage;

    @Override
    public BaseParam getBaseParam() {
        return this.baseParam;
    }

    @Override
    public String getpassword() {
        return ConfirmTransactionContract.View.-CC.$default$getpassword(this);
    }

    public void mappingId() {
    }

    @Override
    public void setButtonEnable(boolean z) {
        ConfirmTransactionContract.View.-CC.$default$setButtonEnable(this, z);
    }

    public void setParam(BaseParam baseParam) {
        this.baseParam = baseParam;
    }

    @Override
    public void setRootV(boolean z) {
    }

    @Override
    public void showErrorText() {
        ConfirmTransactionContract.View.-CC.$default$showErrorText(this);
    }

    @Override
    public void showErrorText(String str) {
        ConfirmTransactionContract.View.-CC.$default$showErrorText(this, str);
    }

    @Override
    public void updateUI() {
        ConfirmTransactionContract.View.-CC.$default$updateUI(this);
    }

    @Override
    protected void processData() {
        BaseParam baseParam;
        if (this.mPresenter != 0) {
            ((ConfirmTransactionPresenter) this.mPresenter).onStart();
            this.mWallet = ((ConfirmTransactionPresenter) this.mPresenter).getCurrentWallet();
        }
        if (this.mWallet == null || (baseParam = this.baseParam) == null) {
            StringBuilder sb = new StringBuilder("ConfirmLedgerFragment: ");
            sb.append(this.mWallet);
            SentryUtil.captureMessage(sb.toString() == null ? "mWallet == null" : "baseParam == null");
            exit();
            return;
        }
        this.unSignMessage = baseParam.getMessage();
        this.transactionType = this.baseParam.getType();
        this.ledgerViewUpdate = new LedgerViewUpdate(this.mContext, getView());
        this.permissionHelper.checkPermissions(new PermissionLedgerHelper.OnPermissionResultCallback() {
            @Override
            public void onSuccess() {
                verifyTransactionsAndSend();
            }

            @Override
            public void onBluetoothFail() {
                Toast((int) R.string.bluetooth_not_enable_toast);
                exit();
            }

            @Override
            public void onLocationFail() {
                Toast((int) R.string.access_location_toast);
                exit();
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgConfirmLedgerBinding inflate = FgConfirmLedgerBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.permissionHelper = new PermissionLedgerHelper(this);
    }

    public void verifyTransactionsAndSend() {
        if (this.transactionType == 14 && !StringTronUtil.isEmpty(this.unSignMessage)) {
            preloadCachedDevice();
            return;
        }
        List<byte[]> bytes = this.baseParam.getTransactionBean().getBytes();
        if (!bytes.isEmpty()) {
            boolean z = true;
            for (byte[] bArr : bytes) {
                try {
                    z = BleUtils.verifyTransactionData(Hex.toHexString(Protocol.Transaction.parseFrom(bArr).getRawData().toByteArray()));
                    continue;
                } catch (InvalidProtocolBufferException e) {
                    LogUtils.e(e);
                    continue;
                }
                if (!z) {
                    break;
                }
            }
            if (!z) {
                ConfirmCustomPopupView.getBuilder(getIContext()).setTitle(getString(R.string.ledger_data_too_big_tip)).setTitleSize(16).setTitleBold(false).setConfirmText(getResources().getString(R.string.dapp17)).setConfirmListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        lambda$verifyTransactionsAndSend$0(view);
                    }
                }).setCancelText(getString(R.string.return2)).setCancleListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        lambda$verifyTransactionsAndSend$1(view);
                    }
                }).setAutoDismissOutSide(false).setHasShadowBg(false).build().show();
                return;
            }
        }
        preloadCachedDevice();
    }

    public void lambda$verifyTransactionsAndSend$0(View view) {
        this.signByHash = true;
        preloadCachedDevice();
    }

    public void lambda$verifyTransactionsAndSend$1(View view) {
        exit();
    }

    private void setSignByHash() {
        List<byte[]> bytes = this.baseParam.getTransactionBean().getBytes();
        if (bytes.isEmpty()) {
            return;
        }
        for (byte[] bArr : bytes) {
            try {
                Protocol.Transaction parseFrom = Protocol.Transaction.parseFrom(bArr);
                if (parseFrom != null && !"".equals(parseFrom.toString()) && parseFrom.getRawData() != null && parseFrom.getRawData().getContractCount() > 0) {
                    switch (fun3.$SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[parseFrom.getRawData().getContract(0).getType().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            LedgerTrx ledgerTrx = this.ledgerTrx;
                            if (ledgerTrx != null && !ledgerTrx.supportStakeV2()) {
                                this.signByHash = true;
                                break;
                            }
                            break;
                        case 6:
                            this.signByHash = true;
                            break;
                    }
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (this.signByHash) {
                return;
            }
        }
    }

    public static class fun3 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.FreezeBalanceV2Contract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeBalanceV2Contract.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WithdrawExpireUnfreezeContract.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.DelegateResourceContract.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnDelegateResourceContract.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.CancelAllUnfreezeV2Contract.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public class fun2 extends EquipmentItemListener {
        fun2() {
        }

        @Override
        public void itemClick(int i, EquipmentBean equipmentBean) {
            AnalyticsHelper.logEvent(AnalyticsHelper.LedgerPage.CLICK_LEDGER_CONFIRM_TRANSACTION_NAME);
            if (equipmentBean == null || equipmentBean.getDevice() == null) {
                return;
            }
            BleRepoDevice device = equipmentBean.getDevice();
            ledgerViewUpdate.setEquipmentName(device.getName());
            ledgerViewUpdate.setStatus(LedgerViewUpdate.Status.GifStart);
            transport = BleClientManager.getInstance().getTransport(device.getMac());
            ((ConfirmTransactionPresenter) mPresenter).addDisposable(transport.open(TronConfig.ACCOUNT_UPDATE_FOREGROUND_INTERVAL).compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    ConfirmLedgerFragment.2.this.lambda$itemClick$0((Device) obj);
                }
            }, ledgerViewUpdate.ToastOpen()));
        }

        public void lambda$itemClick$0(Device device) throws Exception {
            ledgerTrx = new LedgerTrx(transport);
            ledgerViewUpdate.setGifStatus(LedgerProgressView.STATUS.OPEN);
            openTronApp();
        }
    }

    public void preloadCachedDevice() {
        this.adapter = new EquipmentAdapter(getIContext(), EquipmentAdapter.TYPE.TRANSFER, new fun2());
        ((ConfirmTransactionPresenter) this.mPresenter).addDisposable(BleDeviceDaoManager.getInstance().queryAll().subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$preloadCachedDevice$3((List) obj);
            }
        }));
    }

    public void lambda$preloadCachedDevice$3(List list) throws Exception {
        final ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            Collection.-EL.stream(list).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    arrayList.add(new EquipmentBean((BleRepoDevice) obj));
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        this.ledgerViewUpdate.setLocalDeviceList(getLayoutInflater(), this.adapter, arrayList);
        this.ledgerViewUpdate.setStatus(LedgerViewUpdate.Status.List);
    }

    public void openTronApp() {
        try {
            Transport transport = this.transport;
            if (transport != null && transport.isConnected()) {
                final String buildPath = WalletPath.buildPath(this.mWallet.getMnemonicPathString());
                final List<byte[]> bytes = this.baseParam.getTransactionBean().getBytes();
                if (bytes.isEmpty() && StringTronUtil.isEmpty(this.unSignMessage)) {
                    return;
                }
                ((ConfirmTransactionPresenter) this.mPresenter).addDisposable(this.ledgerTrx.openApp().compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        lambda$openTronApp$5(buildPath, bytes, (Boolean) obj);
                    }
                }, this.ledgerViewUpdate.ToastOpenTronApp()));
                return;
            }
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$openTronApp$6();
                }
            });
        } catch (Exception e) {
            SentryUtil.captureMessage("ledger:openTronApp:" + e.getMessage());
            LogUtils.e(e);
        }
    }

    public void lambda$openTronApp$5(String str, final List list, Boolean bool) throws Exception {
        ((ConfirmTransactionPresenter) this.mPresenter).addDisposable(this.ledgerTrx.getAddress(str).compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$openTronApp$4(list, (LedgerTrx.Address) obj);
            }
        }, this.ledgerViewUpdate.ToastGetAddress()));
    }

    public void lambda$openTronApp$4(List list, LedgerTrx.Address address) throws Exception {
        if (this.mWallet.getAddress().equals(address.getAddress())) {
            this.ledgerViewUpdate.setGifStatus(LedgerProgressView.STATUS.CONFIRM);
            int i = this.transactionType;
            if ((i == 17 || i == 14 || i == 102 || i == 104 || i == 103 || i == 16) && !StringTronUtil.isEmpty(this.unSignMessage)) {
                signMessage(this.unSignMessage);
                return;
            } else {
                send(list);
                return;
            }
        }
        this.ledgerViewUpdate.setStatus(LedgerViewUpdate.Status.AddressError);
    }

    public void lambda$openTronApp$6() {
        Toast(R.string.device_disconnected);
    }

    public void send(List<byte[]> list) {
        final String buildPath = WalletPath.buildPath(this.mWallet.getMnemonicPathString());
        Observable fromIterable = Observable.fromIterable(list);
        ((ConfirmTransactionPresenter) this.mPresenter).addDisposable(Observable.zip(fromIterable, fromIterable.concatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$send$7;
                lambda$send$7 = lambda$send$7(buildPath, (byte[]) obj);
                return lambda$send$7;
            }
        }), new BiFunction() {
            @Override
            public final Object apply(Object obj, Object obj2) {
                Protocol.Transaction addSignature;
                addSignature = TransactionUtils.addSignature(Protocol.Transaction.parseFrom((byte[]) obj), (byte[]) obj2);
                return addSignature;
            }
        }).toList().compose(RxSchedulers2.io_main_single()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$send$10((List) obj);
            }
        }, this.ledgerViewUpdate.ToastSign()));
    }

    public io.reactivex.ObservableSource lambda$send$7(java.lang.String r3, byte[] r4) throws java.lang.Exception {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.confirm.core.ledger.ConfirmLedgerFragment.lambda$send$7(java.lang.String, byte[]):io.reactivex.ObservableSource");
    }

    public void lambda$send$10(List list) throws Exception {
        if (list.isEmpty()) {
            SentryUtil.captureMessage("ledger:signTransactionList-isEmpty");
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$send$9();
                }
            });
        } else if (this.transactionType == 14) {
            revertToDapp(WalletUtils.printTransaction((Protocol.Transaction) list.get(0)));
        } else {
            ((ConfirmTransactionPresenter) this.mPresenter).broadcastTransaction(0, list, this.baseParam.getType());
        }
    }

    public void lambda$send$9() {
        Toast(R.string.unknown);
    }

    public void signMessage(String str) {
        try {
            String buildPath = WalletPath.buildPath(this.mWallet.getMnemonicPathString());
            int i = this.transactionType;
            if (i == 16) {
                str = Hex.toHexString(TransactionUtils.getMessageHash(str));
            } else if (i == 102) {
                str = Hex.toHexString(TransactionUtils.getMessageHashV2(ByteArray.fromString(str)));
            } else if (i == 104) {
                str = Hex.toHexString(TransactionUtils.getMessageHashV2((byte[]) new Gson().fromJson("[" + str + "]",  byte[].class)));
            } else if (i == 103) {
                str = Hex.toHexString(TransactionUtils.getMessageHashV2(ByteArray.fromHexString(str)));
            } else if (str.startsWith("0x")) {
                str = str.replace("0x", "");
            }
            this.ledgerTrx.signTransactionHash(buildPath, str).compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$signMessage$11((byte[]) obj);
                }
            }, this.ledgerViewUpdate.ToastSign());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lambda$signMessage$11(byte[] bArr) throws Exception {
        String enCodeSignature = TransactionUtils.enCodeSignature(bArr);
        if (this.baseParam.getPageFrom() == BaseParam.PageFrom.DeepLink) {
            ((ConfirmTransactionPresenter) this.mPresenter).backToDeepLink(IntentResult.Succeeded, enCodeSignature);
        } else {
            revertToDapp(enCodeSignature);
        }
    }

    public void revertToDapp(String str) {
        try {
            Intent intent = new Intent();
            intent.putExtra("SIGNSTRING", str);
            getActivity().setResult(-1, intent);
            exit();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LedgerViewUpdate ledgerViewUpdate = this.ledgerViewUpdate;
        if (ledgerViewUpdate == null || !ledgerViewUpdate.isSearchingBle()) {
            BleClientManager.getInstance().destroyClient();
        }
        LedgerViewUpdate ledgerViewUpdate2 = this.ledgerViewUpdate;
        if (ledgerViewUpdate2 != null) {
            ledgerViewUpdate2.unBind();
        }
    }

    @Override
    public Intent getIIntent() {
        return this.mContext.getIntent();
    }

    @Override
    public boolean isActives() {
        return this.baseParam.isActives();
    }

    @Override
    public void showLoadingFragment() {
        this.ledgerViewUpdate.showLoadingFragment();
        ConfirmPendingFragment confirmPendingFragment = ConfirmPendingFragment.getInstance(this.baseParam);
        this.confirmPendingFragment = confirmPendingFragment;
        if (confirmPendingFragment.isAdded()) {
            getChildFragmentManager().beginTransaction().show(this.confirmPendingFragment).commitAllowingStateLoss();
        } else {
            getChildFragmentManager().beginTransaction().add(R.id.fl_main, this.confirmPendingFragment).show(this.confirmPendingFragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void updateLoadingFragment(final GrpcAPI.Return r2, final State state, final int i) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateLoadingFragment$12(state, r2, i);
            }
        });
    }

    public void lambda$updateLoadingFragment$12(State state, GrpcAPI.Return r4, int i) {
        ConfirmPendingFragment confirmPendingFragment = this.confirmPendingFragment;
        if (confirmPendingFragment != null) {
            confirmPendingFragment.update(state, r4.toByteArray(), this.baseParam, i);
        }
    }

    @Override
    public void onKeyDownChild(int i, KeyEvent keyEvent) {
        ConfirmPendingFragment confirmPendingFragment = this.confirmPendingFragment;
        if (confirmPendingFragment != null) {
            confirmPendingFragment.onKeyDownChild(i, keyEvent);
        }
    }

    public void update() {
        if (this.mPresenter != 0) {
            ((ConfirmTransactionPresenter) this.mPresenter).onStart();
        }
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) {
        PermissionLedgerHelper permissionLedgerHelper = this.permissionHelper;
        if (permissionLedgerHelper == null || permissionLedgerHelper.getPermissionHelper() == null) {
            return;
        }
        this.permissionHelper.getPermissionHelper().requestPermissionsResult(i, strArr, iArr);
    }
}
