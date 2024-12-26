package com.tron.wallet.business.walletmanager.importwallet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.importwallet.SelectMnemonicAddressContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.MultiImportListener;
import com.tron.wallet.common.utils.AccountPermissionUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import j$.util.Objects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.tron.common.bip39.BIP39;
import org.tron.common.bip39.ValidationException;
import org.tron.common.utils.ByteArray;
import org.tron.net.CipherException;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.I_TYPE;
import org.tron.walletserver.InvalidAddressException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.InvalidPasswordException;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectMnemonicAddressPresenter extends SelectMnemonicAddressContract.Presenter {
    protected Wallet mWallet;
    private PopupWindow multiImportPop;
    int ERR_OK = 0;
    int ERR = -1;
    int ADDR_EXISTS = 1;
    private boolean isOnlyLocalHdwallet = false;

    @Override
    public void onClickNext(Bundle bundle) {
        final String content = ((SelectMnemonicAddressContract.View) this.mView).getContent();
        final String name = ((SelectMnemonicAddressContract.View) this.mView).getName();
        final String password = ((SelectMnemonicAddressContract.View) this.mView).getPassword();
        final ArrayList<AddressItem> addressItems = ((SelectMnemonicAddressContract.View) this.mView).getAddressItems();
        final ArrayList<Wallet> walletLists = ((SelectMnemonicAddressContract.View) this.mView).getWalletLists();
        AddressItem addressItem = addressItems.get(0);
        final WalletPath mnemonicPath = addressItem != null ? addressItem.getMnemonicPath() : null;
        final boolean isCheckNoHD = ((SelectMnemonicAddressContract.View) this.mView).isCheckNoHD();
        ((SelectMnemonicAddressContract.View) this.mView).showUnCanceledLoading(((SelectMnemonicAddressContract.View) this.mView).getIContext().getString(R.string.start_improt));
        ((SelectMnemonicAddressContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$onClickNext$1(walletLists, addressItems, content, name, isCheckNoHD, password, mnemonicPath);
            }
        });
    }

    public void lambda$onClickNext$1(ArrayList arrayList, ArrayList arrayList2, String str, final String str2, boolean z, String str3, WalletPath walletPath) {
        arrayList.clear();
        ArrayList arrayList3 = new ArrayList();
        final int i = 0;
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            AddressItem addressItem = (AddressItem) arrayList2.get(i2);
            Wallet wallet = new Wallet(str, addressItem.getMnemonicPath());
            wallet.setAddress(addressItem.getAddress());
            wallet.setCreateType(1);
            if (i2 == 0) {
                wallet.setWalletName(str2);
            } else {
                String generateWalletName = WalletNameGeneratorUtils.generateWalletName(1, false);
                if (generateWalletName != null && generateWalletName.equals(str2)) {
                    WalletNameGeneratorUtils.finish(1, false);
                }
            }
            wallet.setCreateTime(System.currentTimeMillis());
            wallet.setIconRes(UserIconRandom.THIS.random());
            wallet.setMnemonicPath(JSON.toJSONString(addressItem.getMnemonicPath()));
            wallet.setMnemonic(str);
            wallet.setBackUp(true);
            wallet.setMnemonicLength(str.trim().split("\\s+").length);
            arrayList.add(wallet);
            arrayList3.add(wallet.getAddress());
        }
        final boolean checkMulti = AccountPermissionUtils.checkMulti(arrayList3);
        String currentHdRelationshipAddress = HDTronWalletController.getCurrentHdRelationshipAddress();
        Wallet wallet2 = new Wallet(str, WalletPath.createDefault());
        if (HDTronWalletController.hasHDWallet()) {
            if (!StringTronUtil.isEmpty(currentHdRelationshipAddress) && currentHdRelationshipAddress.equals(wallet2.getAddress())) {
                this.isOnlyLocalHdwallet = true;
            }
        } else {
            currentHdRelationshipAddress = wallet2.getAddress();
            List<HdTronRelationshipBean> queryAllRelationBeans = HDTronWalletController.queryAllRelationBeans();
            int i3 = 0;
            while (true) {
                if (i3 >= queryAllRelationBeans.size()) {
                    break;
                }
                HdTronRelationshipBean hdTronRelationshipBean = queryAllRelationBeans.get(i3);
                if (hdTronRelationshipBean != null && hdTronRelationshipBean.getRelationshipHDAddress() != null && hdTronRelationshipBean.getRelationshipHDAddress().equals(currentHdRelationshipAddress)) {
                    HDTronWalletController.insertWalletAndSyncNonHDFlag((Wallet) arrayList.get(0), wallet2.getAddress());
                    break;
                }
                i3++;
            }
            this.isOnlyLocalHdwallet = true;
        }
        if (z) {
            i = saveWalletWithMnemonic(false, str2, str3, str, walletPath, z);
        } else {
            List<String> saveWallet = WalletUtils.saveWallet(arrayList, str3);
            if (!HDTronWalletController.hasHDWallet() && !StringTronUtil.isEmpty(currentHdRelationshipAddress) && !StringTronUtil.isEmpty(wallet2.getAddress())) {
                currentHdRelationshipAddress = wallet2.getAddress();
            }
            if (saveWallet != null) {
                LogUtils.d("CreateAccount", "errorList.size" + saveWallet.size());
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    if (!saveWallet.contains(((Wallet) arrayList.get(i4)).getAddress()) && this.isOnlyLocalHdwallet) {
                        LogUtils.d("CreateAccount", "insertWallet:  " + ((Wallet) arrayList.get(i4)).getAddress() + currentHdRelationshipAddress);
                        HDTronWalletController.insertWallet((Wallet) arrayList.get(i4), currentHdRelationshipAddress);
                    }
                }
                if (arrayList.size() <= saveWallet.size()) {
                    i = ImportWalletModel.ERR;
                }
            }
        }
        ((SelectMnemonicAddressContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$onClickNext$0(i, checkMulti, str2);
            }
        });
    }

    public void lambda$onClickNext$0(int i, boolean z, String str) {
        ((SelectMnemonicAddressContract.View) this.mView).dismissLoading();
        if (i != ImportWalletModel.ERR_OK) {
            importFailureDialog(i);
        } else if (z) {
            showPop(str);
        } else {
            successNext(str);
        }
    }

    public void successNext(String str) {
        WalletNameGeneratorUtils.finish(1, false);
        WalletUtils.setSelectedWallet(str);
        toMain();
    }

    public boolean saveWalletWithMnemonic(String str, String str2, String str3) {
        try {
            Wallet wallet = new Wallet(I_TYPE.MNEMONIC, str3);
            wallet.setWalletName(str);
            wallet.setIconRes(UserIconRandom.THIS.random());
            wallet.setMnemonic(str3);
            wallet.setCreateTime(System.currentTimeMillis());
            wallet.setCreateType(1);
            wallet.setMnemonicLength(str3.trim().split("\\s+").length);
            WalletUtils.saveWallet(wallet, str2);
            WalletUtils.setSelectedWallet(str);
            return true;
        } catch (CipherException e) {
            LogUtils.e(e);
            return false;
        } catch (DuplicateNameException e2) {
            LogUtils.e(e2);
            return false;
        } catch (InvalidAddressException e3) {
            LogUtils.e(e3);
            return false;
        } catch (InvalidNameException e4) {
            LogUtils.e(e4);
            return false;
        } catch (InvalidPasswordException e5) {
            LogUtils.e(e5);
            return false;
        }
    }

    public Wallet create(Object... objArr) {
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj != null) {
                clsArr[i] = obj.getClass();
            } else {
                clsArr[i] = String.class;
            }
        }
        try {
            return (Wallet) Wallet.class.getConstructor(clsArr).newInstance(objArr);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            LogUtils.e(e);
            return null;
        }
    }

    public int saveWalletWithMnemonic(boolean z, String str, String str2, String str3, WalletPath walletPath, boolean z2) {
        Wallet create;
        try {
            if (z) {
                create = create(true, I_TYPE.MNEMONIC, str3);
            } else if (str3 != null && str3.trim().split("\\s+").length == 24 && z2) {
                try {
                    create = create(false, I_TYPE.PRIVATE, ByteArray.toHexString(BIP39.decode(str3, "pass")));
                } catch (ValidationException e) {
                    LogUtils.e(e);
                    create = null;
                }
            } else {
                create = create(false, str3, walletPath);
            }
            if (create == null) {
                return this.ERR;
            }
            if (addressAlreadyExist(create.getAddress())) {
                return this.ADDR_EXISTS;
            }
            create.setShieldedWallet(z);
            create.setWalletName(str);
            create.setIconRes(UserIconRandom.THIS.random());
            create.setCreateTime(System.currentTimeMillis());
            if (str3 != null && str3.contains(" ")) {
                String[] split = str3.trim().split("\\s+");
                if (split != null && split.length == 24 && z2) {
                    create.setCreateType(2);
                } else {
                    if (walletPath != null) {
                        create.setMnemonicPath(JSON.toJSONString(walletPath));
                    }
                    create.setCreateType(1);
                    create.setMnemonicLength(str3.trim().split("\\s+").length);
                    create.setMnemonic(str3);
                    if (!z) {
                        try {
                            HDTronWalletController.insertWallet(create, new Wallet(I_TYPE.MNEMONIC, str3).getAddress());
                        } catch (Exception e2) {
                            LogUtils.e(e2);
                        }
                    }
                }
            } else {
                create.setCreateType(1);
            }
            WalletUtils.saveWallet(create, str2);
            if (!z) {
                WalletUtils.setSelectedWallet(str);
            }
            if (SpAPI.THIS.isCold()) {
                SpAPI.THIS.setColdWalletSelected(str);
            }
            return this.ERR_OK;
        } catch (CipherException e3) {
            e = e3;
            LogUtils.e(e);
            return this.ERR;
        } catch (DuplicateNameException e4) {
            LogUtils.e(e4);
            return this.ADDR_EXISTS;
        } catch (InvalidAddressException e5) {
            e = e5;
            LogUtils.e(e);
            return this.ERR;
        } catch (InvalidNameException e6) {
            e = e6;
            LogUtils.e(e);
            return this.ERR;
        } catch (InvalidPasswordException e7) {
            e = e7;
            LogUtils.e(e);
            return this.ERR;
        }
    }

    private boolean addressAlreadyExist(String str) {
        return WalletUtils.getWalletForAddress(str) != null;
    }

    @Override
    public void getBalance(String str) {
        this.action.getBalance(str).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onResponse(String str2, AccountBalanceOutput accountBalanceOutput) {
                ((SelectMnemonicAddressContract.View) mView).onGetBalance(accountBalanceOutput);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "SelectAddressPresenter-getBalance"));
    }

    @Override
    public void getBalances(ArrayList<String> arrayList) {
        this.action.getBalances(arrayList).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, AccountBalanceOutput accountBalanceOutput) {
                ((SelectMnemonicAddressContract.View) mView).onGetBalance(accountBalanceOutput);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                addDisposable(disposable);
            }
        }, "SelectAddressPresenter-getBalance"));
    }

    @Override
    public void createWallet(WalletPath walletPath, final boolean z) {
        Observable<R> compose = this.action.createWallet(((SelectMnemonicAddressContract.View) this.mView).getViewIntent().getExtras(), walletPath).compose(RxSchedulers2.io_main());
        Consumer consumer = new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$createWallet$2(z, (Wallet) obj);
            }
        };
        final SelectMnemonicAddressContract.View view = (SelectMnemonicAddressContract.View) this.mView;
        Objects.requireNonNull(view);
        addDisposable(compose.subscribe(consumer, new Consumer() {
            @Override
            public final void accept(Object obj) {
                SelectMnemonicAddressContract.View.this.onCreateWalletFailed((Throwable) obj);
            }
        }));
    }

    public void lambda$createWallet$2(boolean z, Wallet wallet) throws Exception {
        if (wallet.isShieldedWallet()) {
            ((SelectMnemonicAddressContract.View) this.mView).onCreateWallet(wallet, z);
            return;
        }
        Pair<WalletPath, Wallet> nextWallet = ((SelectMnemonicAddressContract.Model) this.mModel).getNextWallet(((SelectMnemonicAddressContract.View) this.mView).getContent());
        WalletPath walletPath = (WalletPath) nextWallet.first;
        Wallet wallet2 = (Wallet) nextWallet.second;
        AddressItem.fromWallet(wallet2).setSelected(true);
        ((SelectMnemonicAddressContract.View) this.mView).onCreateWallet(wallet2, z);
        LogUtils.w("SelectAddressPresenter", String.format("onNext: walletAddress = %s", wallet.getAddress()));
    }

    @Override
    public WalletPath getPathFromWallet(Wallet wallet) {
        if (this.action != null) {
            return this.action.getWalletPath(wallet);
        }
        return WalletPath.createDefault();
    }

    @Override
    public void onClickAddress(WalletPath walletPath, boolean z) {
        if (this.action != null) {
            this.action.startChooseAddresses(((SelectMnemonicAddressContract.View) this.mView).getIContext(), ((SelectMnemonicAddressContract.View) this.mView).getViewIntent().getExtras(), ((SelectMnemonicAddressContract.View) this.mView).getAddressItems(), z, ((SelectMnemonicAddressContract.View) this.mView).getName(), ((SelectMnemonicAddressContract.View) this.mView).getPassword(), this.isOnlyLocalHdwallet);
        }
    }

    @Override
    protected void onStart() {
        ((SelectMnemonicAddressContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public void doOnBackground() {
                String currentHdRelationshipAddress = HDTronWalletController.getCurrentHdRelationshipAddress();
                if (StringTronUtil.isEmpty(currentHdRelationshipAddress)) {
                    if (HDTronWalletController.hasHDWallet()) {
                        return;
                    }
                    isOnlyLocalHdwallet = true;
                    return;
                }
                Wallet wallet = new Wallet(((SelectMnemonicAddressContract.View) mView).getContent(), WalletPath.createDefault());
                if (currentHdRelationshipAddress == null || !currentHdRelationshipAddress.equals(wallet.getAddress())) {
                    return;
                }
                isOnlyLocalHdwallet = true;
            }
        });
        this.mRxManager.on(Event.JumpToMain, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$3(obj);
            }
        });
    }

    public void lambda$onStart$3(Object obj) throws Exception {
        if (this.mView != 0) {
            ((SelectMnemonicAddressContract.View) this.mView).exit();
        }
    }

    private void toMain() {
        Intent intent = new Intent(((SelectMnemonicAddressContract.View) this.mView).getIContext(), MainTabActivity.class);
        intent.setFlags(67108864);
        if (((SelectMnemonicAddressContract.View) this.mView).isShield()) {
            intent.putExtra(TronConfig.SHIELD_IS_TRC20, true);
        }
        this.mRxManager.post(Event.JumpToMain, "");
        ((SelectMnemonicAddressContract.View) this.mView).go(intent);
        ((SelectMnemonicAddressContract.View) this.mView).exit();
    }

    private void importFailureDialog(int i) {
        try {
            CustomDialog.Builder builder = new CustomDialog.Builder(((SelectMnemonicAddressContract.View) this.mView).getIContext());
            final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
            View view = builder.getView();
            TextView textView = (TextView) view.findViewById(R.id.tv_content);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_ok2);
            ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.GONE);
            textView2.setVisibility(View.VISIBLE);
            textView.setText(i == ImportWalletModel.ADDR_EXISTS ? R.string.shield_address_already_exists : R.string.import_failure);
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    build.dismiss();
                }
            });
            build.show();
            build.setCanceledOnTouchOutside(false);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void showPop(final String str) {
        if (this.multiImportPop == null) {
            this.multiImportPop = PopupWindowUtil.showMultiImport(((SelectMnemonicAddressContract.View) this.mView).getIContext(), new MultiImportListener() {
                @Override
                public void success() {
                    if (multiImportPop != null) {
                        multiImportPop.dismiss();
                    }
                }

                @Override
                public void dismiss() {
                    successNext(str);
                }
            });
        }
        this.multiImportPop.showAtLocation(((SelectMnemonicAddressContract.View) this.mView).getRootView(), 17, 0, 0);
    }

    @Override
    public void onBackPressed() {
        PopupWindow popupWindow = this.multiImportPop;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
