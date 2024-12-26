package com.tron.wallet.business.addwallet;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.tron_base.frame.view.IToast;
import com.tron.wallet.business.samsung.SamsungSDKWrapper;
import com.tron.wallet.business.walletmanager.importwallet.ImportSamsungActivity;
import com.tron.wallet.common.components.dialog.Common2Dialog;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class SamsungPlugin {
    public static final int REQUESTCODE_SamsungKeystoreWallet = 16;

    public void showGoToSamsungWallet(final Activity activity, final int i) {
        final Common2Dialog common2Dialog = new Common2Dialog(activity);
        common2Dialog.setTitle(activity.getString(R.string.create_sumsung_keystore)).setInnerTitle(activity.getString(R.string.keystore_not_exist_to_create)).setBtListener(activity.getString(R.string.ok2), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                SamsungPlugin.lambda$showGoToSamsungWallet$0(Common2Dialog.this, activity, i, view);
            }
        }).setCancleBt(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                Common2Dialog.this.dismiss();
            }
        });
        common2Dialog.show();
    }

    public static void lambda$showGoToSamsungWallet$0(Common2Dialog common2Dialog, Activity activity, int i, View view) {
        common2Dialog.dismiss();
        SamsungSDKWrapper.getoSamsungKeystoreWallet(activity, i);
    }

    class fun1 implements SamsungSDKWrapper.getAddressCallBack {
        final BaseActivity val$act;

        fun1(BaseActivity baseActivity) {
            this.val$act = baseActivity;
        }

        @Override
        public void onSuccess(String str, String str2) {
            Wallet wallet;
            for (String str3 : WalletUtils.getWalletNames()) {
                if (!TextUtils.isEmpty(str3) && (wallet = WalletUtils.getWallet(str3)) != null && wallet.getAddress().equals(str2)) {
                    RxBus.getInstance().post(Event.SELECTEDWALLET, str3);
                    WalletUtils.setSelectedWallet(str3);
                    final BaseActivity baseActivity = this.val$act;
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public final void doInUIThread() {
                            r0.toast(BaseActivity.this.getString(R.string.samsung_wallet_existed));
                        }
                    });
                    return;
                }
            }
            ImportSamsungActivity.start(this.val$act, str2);
        }

        @Override
        public void onFailure(String str) {
            IToast.getIToast().setImage(R.mipmap.broadcast_fail).show(this.val$act.getString(R.string.sx_import_fail));
        }
    }

    public void importSamsungWallet(BaseActivity baseActivity) {
        SamsungSDKWrapper.importSamsungWallet(baseActivity, new fun1(baseActivity));
    }
}
