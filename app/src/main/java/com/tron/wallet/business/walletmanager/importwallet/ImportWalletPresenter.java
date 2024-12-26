package com.tron.wallet.business.walletmanager.importwallet;

import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.maintab.MainTabActivity;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletContract;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletPresenter;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.interfaces.MultiImportListener;
import com.tron.wallet.common.utils.AccountPermissionUtils;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.db.Controller.HDTronWalletController;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.tron.common.crypto.MnemonicUtils;
import org.tron.net.KeyStoreUtils;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class ImportWalletPresenter extends ImportWalletContract.Presenter {
    private Disposable disposable;
    private PopupWindow multiImportPop;

    @Override
    public void importWalletWithPrivateKey(String str, String str2, String str3, String str4) {
        boolean z;
        boolean z2;
        try {
            z = false;
            if (StringTronUtil.isPrivateKeyValid(str)) {
                z2 = true;
            } else {
                ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.privatekey_error));
                z2 = false;
            }
            if (!StringTronUtil.validataLegalString2(str2)) {
                ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_name2));
                z2 = false;
            }
            if (WalletUtils.existWallet(str2)) {
                ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_existwallet));
                z2 = false;
            }
            if (StringTronUtil.isOkPasswordTwo(str3)) {
                z = z2;
            } else {
                ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_disagree));
            }
        } catch (Exception e) {
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.privatekey_error));
            LogUtils.e(e);
        }
        if (StringTronUtil.isOkPasswordTwo(str3) && !str3.equals(str4)) {
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_disagree));
            return;
        }
        if (!z) {
            return;
        }
        ((ImportWalletContract.View) this.mView).showLoading(((ImportWalletContract.View) this.mView).getIContext().getString(R.string.improting));
        AsyncJob.doInBackground(new fun1(str2, str3, str));
    }

    public class fun1 implements AsyncJob.OnBackgroundJob {
        final String val$name;
        final String val$password;
        final String val$privateKey;

        fun1(String str, String str2, String str3) {
            this.val$name = str;
            this.val$password = str2;
            this.val$privateKey = str3;
        }

        @Override
        public void doOnBackground() {
            final Pair<Integer, String> saveWallet = ((ImportWalletContract.Model) mModel).saveWallet(((ImportWalletContract.View) mView).isShield(), this.val$name, this.val$password, this.val$privateKey, 2);
            final int intValue = ((Integer) saveWallet.first).intValue();
            final boolean checkMulti = AccountPermissionUtils.checkMulti((String) saveWallet.second);
            AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                @Override
                public final void doInUIThread() {
                    ImportWalletPresenter.1.this.lambda$doOnBackground$0(checkMulti, saveWallet, intValue);
                }
            });
        }

        public void lambda$doOnBackground$0(boolean z, Pair pair, int i) {
            ((ImportWalletContract.View) mView).dismissLoading();
            LogUtils.e("multi:" + z);
            LogUtils.e("multi:" + ((String) pair.second));
            if (i != ImportWalletModel.ERR_OK) {
                importFailureDialog(i);
            } else if (z) {
                showPop(2);
            } else {
                successNext(2);
            }
        }
    }

    @Override
    public void importWalletWithMnemonic(String str, String str2, String str3, String str4) {
        boolean z;
        boolean z2 = false;
        if (MnemonicUtils.validateMnemonic(str)) {
            z = true;
        } else {
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.incorrectg_mnemonic_string));
            z = false;
        }
        if (StringTronUtil.isEmpty(str2)) {
            ((ImportWalletContract.View) this.mView).dismissLoading();
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_name3));
            z = false;
        }
        if (!StringTronUtil.validataLegalString2(str2)) {
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_name2));
            z = false;
        }
        if (WalletUtils.existWallet(str2)) {
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_existwallet));
            z = false;
        }
        if (StringTronUtil.isOkPasswordTwo(str3)) {
            z2 = z;
        } else {
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_disagree));
        }
        if (StringTronUtil.isOkPasswordTwo(str3) && !str3.equals(str4)) {
            ((ImportWalletContract.View) this.mView).toast(StringTronUtil.getResString(R.string.error_disagree));
        } else if (z2) {
            SelectMnemonicAddressActivity.start(((ImportWalletContract.View) this.mView).getIContext(), 1, str, str2, str3, ((ImportWalletContract.View) this.mView).isShield());
        }
    }

    @Override
    public void importWalletWithKeyStore(String str, String str2, String str3) {
        ((ImportWalletContract.View) this.mView).showLoading(((ImportWalletContract.View) this.mView).getIContext().getString(R.string.start_improt));
        AsyncJob.doInBackground(new fun2(str, str3, str2));
    }

    public class fun2 implements AsyncJob.OnBackgroundJob {
        boolean flag2 = true;
        final String val$keystore;
        final String val$walletName;
        final String val$walletPassword;

        fun2(String str, String str2, String str3) {
            this.val$keystore = str;
            this.val$walletPassword = str2;
            this.val$walletName = str3;
        }

        @Override
        public void doOnBackground() {
            try {
                Pair<Integer, String> saveWallet = ((ImportWalletContract.Model) mModel).saveWallet(((ImportWalletContract.View) mView).isShield(), this.val$walletName, this.val$walletPassword, KeyStoreUtils.getPrivateWithKeyStore(this.val$keystore, this.val$walletPassword), 3);
                final int intValue = ((Integer) saveWallet.first).intValue();
                final boolean checkMulti = AccountPermissionUtils.checkMulti((String) saveWallet.second);
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        ImportWalletPresenter.2.this.lambda$doOnBackground$0(intValue, checkMulti);
                    }
                });
            } catch (Exception e) {
                this.flag2 = false;
                LogUtils.e(e);
                ((ImportWalletContract.View) mView).runOnUIThread(new OnMainThread() {
                    @Override
                    public void doInUIThread() {
                        ((ImportWalletContract.View) mView).dismissLoading();
                        importFailureDialog();
                    }
                });
            }
        }

        public void lambda$doOnBackground$0(int i, boolean z) {
            ((ImportWalletContract.View) mView).dismissLoading();
            if (i != ImportWalletModel.ERR_OK) {
                importFailureDialog(i);
            } else if (z) {
                showPop(3);
            } else {
                successNext(3);
            }
        }
    }

    public void successNext(int i) {
        WalletNameGeneratorUtils.finish(3, ((ImportWalletContract.View) this.mView).isShield());
        WalletNameGeneratorUtils.finish(i, ((ImportWalletContract.View) this.mView).isShield());
        toMain();
    }

    private void toMain() {
        Intent intent = new Intent(((ImportWalletContract.View) this.mView).getIContext(), MainTabActivity.class);
        intent.setFlags(67108864);
        ((ImportWalletContract.View) this.mView).go(intent);
        ((ImportWalletContract.View) this.mView).exit();
    }

    public void importFailureDialog() {
        try {
            CustomDialog.Builder builder = new CustomDialog.Builder(((ImportWalletContract.View) this.mView).getIContext());
            final CustomDialog build = builder.style(R.style.loading_dialog).cancelTouchout(true).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.fail_dialog).build();
            View view = builder.getView();
            TextView textView = (TextView) view.findViewById(R.id.tv_ok2);
            ((LinearLayout) view.findViewById(R.id.ll_double)).setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(R.id.tv_content)).setText(R.string.import_failure);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    build.dismiss();
                }
            });
            build.setCanceledOnTouchOutside(false);
            build.show();
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void importFailureDialog(int i) {
        try {
            CustomDialog.Builder builder = new CustomDialog.Builder(((ImportWalletContract.View) this.mView).getIContext());
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

    @Override
    protected void onStart() {
        this.mRxManager.on(Event.JumpToMain, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$onStart$0(obj);
            }
        });
    }

    public void lambda$onStart$0(Object obj) throws Exception {
        if (this.mView != 0) {
            ((ImportWalletContract.View) this.mView).exit();
        }
    }

    public void startCheckIsLocalHD(final String str) {
        Disposable disposable = this.disposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.disposable.dispose();
        }
        this.disposable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                Wallet wallet = new Wallet(str, WalletPath.createDefault());
                boolean z = false;
                if (HDTronWalletController.hasHDWallet()) {
                    String currentHdRelationshipAddress = HDTronWalletController.getCurrentHdRelationshipAddress();
                    if (StringTronUtil.isEmpty(currentHdRelationshipAddress) || !currentHdRelationshipAddress.equals(wallet.getAddress())) {
                        z = true;
                    }
                }
                observableEmitter.onNext(Boolean.valueOf(z));
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$startCheckIsLocalHD$1((Boolean) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$startCheckIsLocalHD$2((Throwable) obj);
            }
        });
    }

    public void lambda$startCheckIsLocalHD$1(Boolean bool) throws Exception {
        ((ImportWalletContract.View) this.mView).showLocalHDTips(bool.booleanValue());
    }

    public void lambda$startCheckIsLocalHD$2(Throwable th) throws Exception {
        ((ImportWalletContract.View) this.mView).showLocalHDTips(true);
    }

    public void cancel() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
    }

    public void showPop(final int i) {
        if (this.multiImportPop == null) {
            this.multiImportPop = PopupWindowUtil.showMultiImport(((ImportWalletContract.View) this.mView).getIContext(), new MultiImportListener() {
                @Override
                public void success() {
                    if (multiImportPop != null) {
                        multiImportPop.dismiss();
                    }
                }

                @Override
                public void dismiss() {
                    successNext(i);
                }
            });
        }
        this.multiImportPop.showAtLocation(((ImportWalletContract.View) this.mView).getRootView(), 17, 0, 0);
    }

    @Override
    public void onBackPressed() {
        PopupWindow popupWindow = this.multiImportPop;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
