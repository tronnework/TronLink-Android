package com.tron.wallet.business.walletmanager.createaccount;

import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.createaccount.CreateAccountContract;
import com.tron.wallet.business.walletmanager.createwallet.CreateWalletActivity;
import com.tron.wallet.business.walletmanager.selectaddress.SelectAnotherAddressActivity;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.disposables.Disposable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class CreateAccountPresenter extends CreateAccountContract.Presenter {
    private int ToAccountRequestCode = 2001;
    private int ToHDRequestCode = 2002;
    private ArrayList<AddressItem> addressItems;
    private Wallet hdWallet;
    private String hdWalletName;
    private boolean isShielded;
    private WalletPath mnemonicPath;
    private String mnemonics;
    private ArrayList<Wallet> nextWalletList;
    private String nextWalletName;
    private String password;
    private String relationShipAddress;

    public void init() {
        this.addressItems = new ArrayList<>();
        this.nextWalletList = new ArrayList<>();
        this.mnemonics = ((CreateAccountContract.View) this.mView)._getIntent().getStringExtra(TronConfig.WALLET_extra);
        this.hdWalletName = ((CreateAccountContract.View) this.mView)._getIntent().getStringExtra(TronConfig.WALLET_DATA);
        this.password = ((CreateAccountContract.View) this.mView)._getIntent().getStringExtra(TronConfig.WALLET_PASSWORD);
        this.isShielded = ((CreateAccountContract.View) this.mView)._getIntent().getBooleanExtra(TronConfig.WALLET_DATA2, false);
        if (StringTronUtil.isEmpty(this.mnemonics, this.hdWalletName, this.password)) {
            ((CreateAccountContract.View) this.mView).exit();
            return;
        }
        ((CreateAccountContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$init$0();
            }
        });
        updateWallet(this.hdWalletName);
    }

    public void lambda$init$0() {
        if (HDTronWalletController.queryHdWalletCounts() <= 1) {
            ((CreateAccountContract.View) this.mView).hideSwitchHdButton();
        }
    }

    public void updateWallet(String str) {
        Wallet wallet = WalletUtils.getWallet(str);
        this.hdWallet = wallet;
        if (wallet == null) {
            ((CreateAccountContract.View) this.mView).exit();
            return;
        }
        ((CreateAccountContract.View) this.mView).showLoadingDialog();
        WalletPath mnemonicPath = WalletUtils.getMnemonicPath(this.hdWallet.getMnemonicPathString(), this.hdWallet.getWalletName());
        this.mnemonicPath = mnemonicPath;
        mnemonicPath.accountIndex++;
        ((CreateAccountContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$updateWallet$2();
            }
        });
    }

    public void lambda$updateWallet$2() {
        Pair<WalletPath, Wallet> nextWallet = ((CreateAccountContract.Model) this.mModel).getNextWallet(this.mnemonics);
        Wallet hDWallet = ((CreateAccountContract.Model) this.mModel).getHDWallet(this.mnemonics);
        this.mnemonicPath = (WalletPath) nextWallet.first;
        Wallet wallet = (Wallet) nextWallet.second;
        AddressItem fromWallet = AddressItem.fromWallet(wallet);
        fromWallet.setSelected(true);
        wallet.setCreateType(this.hdWallet.getCreateType());
        wallet.setWalletName(this.nextWalletName);
        wallet.setCreateTime(System.currentTimeMillis());
        wallet.setIconRes(UserIconRandom.THIS.random());
        wallet.setMnemonicPath(JSON.toJSONString(this.mnemonicPath));
        wallet.setMnemonic(this.mnemonics);
        wallet.setBackUp(this.hdWallet.isBackUp());
        wallet.setMnemonicLength(this.mnemonics.trim().split("\\s+").length);
        ((CreateAccountContract.View) this.mView).updateHDUI(hDWallet);
        ((CreateAccountContract.View) this.mView).updateItems(this.addressItems);
        updateBalance(wallet);
        this.addressItems.add(fromWallet);
        this.nextWalletList.add(wallet);
        this.relationShipAddress = getRelationShipAddress();
        this.nextWalletName = WalletNameGeneratorUtils.generateForAccountName(this.mnemonicPath.accountIndex);
        ((CreateAccountContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$updateWallet$1();
            }
        });
    }

    public void lambda$updateWallet$1() {
        ((CreateAccountContract.View) this.mView).dismissLoadingDialog();
        ((CreateAccountContract.View) this.mView).generateWalletName(this.nextWalletName);
    }

    @Override
    public void create() {
        if (this.nextWalletList == null) {
            return;
        }
        try {
            String inputWalletName = ((CreateAccountContract.View) this.mView).getInputWalletName();
            this.nextWalletName = inputWalletName;
            if (StringTronUtil.isEmpty(inputWalletName)) {
                ((CreateAccountContract.View) this.mView).updateButtonEnable(false);
                ((CreateAccountContract.View) this.mView).showNameError(StringTronUtil.getResString(R.string.error_name3));
            } else if (!StringTronUtil.validataLegalString2(this.nextWalletName)) {
                ((CreateAccountContract.View) this.mView).updateButtonEnable(false);
                ((CreateAccountContract.View) this.mView).showNameError(StringTronUtil.getResString(R.string.error_name2));
            } else if (WalletUtils.existWallet(this.nextWalletName)) {
                ((CreateAccountContract.View) this.mView).updateButtonEnable(false);
                ((CreateAccountContract.View) this.mView).showNameError(StringTronUtil.getResString(R.string.exist_wallet_name));
            } else {
                ((CreateAccountContract.View) this.mView).showLoadingDialog();
                ((CreateAccountContract.View) this.mView).runOnThreeThread(new OnBackground() {
                    @Override
                    public final void doOnBackground() {
                        lambda$create$4();
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$create$4() {
        for (int i = 0; i < this.nextWalletList.size(); i++) {
            try {
                Wallet wallet = this.nextWalletList.get(i);
                if (i == 0) {
                    String generateForAccountName = WalletNameGeneratorUtils.generateForAccountName(0);
                    if (generateForAccountName != null && generateForAccountName.equals(this.nextWalletName)) {
                        WalletNameGeneratorUtils.finishGenerateForAccountName();
                    }
                    wallet.setWalletName(this.nextWalletName);
                } else {
                    wallet.setWalletName(WalletNameGeneratorUtils.generateForAccountName(this.addressItems.get(i).getMnemonicPath().accountIndex));
                    WalletNameGeneratorUtils.finishGenerateForAccountName();
                }
                WalletUtils.saveWallet(wallet, this.password);
                LogUtils.d("CreateAccount", "saveWallet:  " + wallet.getAddress() + "  " + wallet.getWalletName() + "  " + this.password);
                HDTronWalletController.insertWallet(wallet, this.relationShipAddress);
            } catch (Exception e) {
                LogUtils.e(e);
                ((CreateAccountContract.View) this.mView).runOnUIThread(new OnMainThread() {
                    @Override
                    public final void doInUIThread() {
                        lambda$create$3();
                    }
                });
                return;
            }
        }
        toMain();
    }

    public void lambda$create$3() {
        ((CreateAccountContract.View) this.mView).dismissLoadingDialog();
    }

    @Override
    public NoDoubleClickListener btSwitchAccountListener() {
        return new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.CreateAccountPage.CLICK_CREATE_ACCOUNT_PAGE_CHANGE_ADDRESS);
                SelectAnotherAddressActivity.start(((CreateAccountContract.View) mView).getIContext(), mnemonics, addressItems, true, isShielded, ToAccountRequestCode, 0, false, ((CreateAccountContract.View) mView).getInputWalletName(), password, true);
            }
        };
    }

    @Override
    public NoDoubleClickListener btSwitchHDListener() {
        return new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
            }
        };
    }

    @Override
    public NoDoubleClickListener btTvRightListener() {
        return new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                CreateWalletActivity.start(((CreateAccountContract.View) mView).getIContext(), isShielded);
            }
        };
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<AddressItem> parcelableArrayListExtra;
        if (intent != null) {
            if (i != this.ToAccountRequestCode) {
                if (i == this.ToHDRequestCode) {
                    this.hdWalletName = intent.getStringExtra(TronConfig.WALLET_DATA);
                    this.mnemonics = intent.getStringExtra(TronConfig.WALLET_extra);
                    this.password = intent.getStringExtra(TronConfig.WALLET_PASSWORD);
                    updateWallet(this.hdWalletName);
                }
            } else if (intent.hasExtra("key_data")) {
                Serializable serializableExtra = intent.getSerializableExtra("key_data");
                if (serializableExtra instanceof AddressItem) {
                    AddressItem addressItem = (AddressItem) serializableExtra;
                    this.mnemonicPath = addressItem.getMnemonicPath();
                    Wallet wallet = new Wallet(this.mnemonics, this.mnemonicPath);
                    this.nextWalletName = WalletNameGeneratorUtils.generateForAccountName(this.mnemonicPath.accountIndex);
                    wallet.setCreateType(this.hdWallet.getCreateType());
                    wallet.setWalletName(this.nextWalletName);
                    wallet.setCreateTime(System.currentTimeMillis());
                    wallet.setIconRes(UserIconRandom.THIS.random());
                    wallet.setMnemonicPath(JSON.toJSONString(this.mnemonicPath));
                    wallet.setMnemonic(this.mnemonics);
                    wallet.setBackUp(this.hdWallet.isBackUp());
                    wallet.setMnemonicLength(this.mnemonics.trim().split("\\s+").length);
                    this.nextWalletList.add(wallet);
                    ((CreateAccountContract.View) this.mView).generateWalletName(this.nextWalletName);
                    ((CreateAccountContract.View) this.mView).updateBalance(addressItem.getBalance());
                }
            } else if (intent.hasExtra(CommonKey.KEY_DATA_ARRAY)) {
                if (Build.VERSION.SDK_INT >= 33) {
                    parcelableArrayListExtra = intent.getParcelableArrayListExtra(CommonKey.KEY_DATA_ARRAY, AddressItem.class);
                    this.addressItems = parcelableArrayListExtra;
                } else {
                    this.addressItems = intent.getParcelableArrayListExtra(CommonKey.KEY_DATA_ARRAY);
                }
                this.nextWalletList.clear();
                if (this.addressItems == null) {
                    return;
                }
                for (int i3 = 0; i3 < this.addressItems.size(); i3++) {
                    AddressItem addressItem2 = this.addressItems.get(i3);
                    this.mnemonicPath = addressItem2.getMnemonicPath();
                    Wallet wallet2 = new Wallet(this.mnemonics, this.mnemonicPath);
                    this.nextWalletName = WalletNameGeneratorUtils.generateForAccountName(this.mnemonicPath.accountIndex);
                    wallet2.setCreateType(this.hdWallet.getCreateType());
                    wallet2.setWalletName(this.nextWalletName);
                    wallet2.setCreateTime(System.currentTimeMillis());
                    wallet2.setIconRes(UserIconRandom.THIS.random());
                    wallet2.setMnemonicPath(JSON.toJSONString(this.mnemonicPath));
                    wallet2.setMnemonic(this.mnemonics);
                    wallet2.setBackUp(this.hdWallet.isBackUp());
                    wallet2.setMnemonicLength(this.mnemonics.trim().split("\\s+").length);
                    this.nextWalletList.add(wallet2);
                    if (i3 == 0) {
                        ((CreateAccountContract.View) this.mView).generateWalletName(this.nextWalletName);
                    }
                    ((CreateAccountContract.View) this.mView).updateBalance(addressItem2.getBalance());
                }
                ((CreateAccountContract.View) this.mView).updateItems(this.addressItems);
                updateBalance(this.nextWalletList);
            }
        }
    }

    private void toMain() {
        ((CreateAccountContract.View) this.mView).runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$toMain$5();
            }
        });
    }

    public void lambda$toMain$5() {
        ((CreateAccountContract.View) this.mView).dismissLoadingDialog();
        WalletUtils.setSelectedWallet(this.nextWalletList.get(0).getWalletName());
        if (SpAPI.THIS.isCold()) {
            SpAPI.THIS.setColdWalletSelected(this.nextWalletList.get(0).getWalletName());
        }
        Intent intent = new Intent(((CreateAccountContract.View) this.mView).getIContext(), MainTabActivity.class);
        intent.setFlags(67108864);
        if (this.isShielded) {
            intent.putExtra(TronConfig.SHIELD_IS_TRC20, true);
        }
        ((CreateAccountContract.View) this.mView).go(intent);
        ((CreateAccountContract.View) this.mView).exit();
    }

    private void updateBalance(Wallet wallet) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(wallet);
        updateBalance(arrayList);
    }

    private void updateBalance(List<Wallet> list) {
        ((CreateAccountContract.Model) this.mModel).getBalances(list).subscribe(new IObserver(new ICallback<AccountBalanceOutput>() {
            @Override
            public void onFailure(String str, String str2) {
            }

            @Override
            public void onResponse(String str, AccountBalanceOutput accountBalanceOutput) {
                if (accountBalanceOutput == null || accountBalanceOutput.getData() == null) {
                    return;
                }
                accountBalanceOutput.getData().getTotalBalance();
                ((CreateAccountContract.View) mView).updateBalances(accountBalanceOutput);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                mRxManager.add(disposable);
            }
        }, "CreateAccountPresenter-getBalance"));
    }

    private String getRelationShipAddress() {
        return new Wallet(this.mnemonics, new WalletPath()).getAddress();
    }
}
