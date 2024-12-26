package com.tron.wallet.common.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.CheckAccountActivatedCallback;
import com.tron.wallet.common.interfaces.CheckAccountNotActivatedCallback;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import com.tronlinkpro.wallet.R;
import org.tron.common.utils.ByteArray;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
public class AccountUtils {
    private static AccountUtils instance;

    public static void lambda$showPop$0(View view) {
    }

    private AccountUtils() {
    }

    public static AccountUtils getInstance() {
        if (instance == null) {
            synchronized (AccountUtils.class) {
                if (instance == null) {
                    instance = new AccountUtils();
                }
            }
        }
        return instance;
    }

    public void checkAccountIsActivatedFromLocalWithTokenBean(Context context, CheckAccountActivatedCallback checkAccountActivatedCallback, CheckAccountNotActivatedCallback checkAccountNotActivatedCallback, TokenBean tokenBean) {
        if (!checkAccountIsNotActivated(WalletUtils.getAccount(context, WalletUtils.getSelectedWallet().getWalletName()))) {
            checkAccountActivatedCallback.isActivated();
        } else if (checkAccountNotActivatedCallback == null) {
            showPop(context, tokenBean);
        } else {
            checkAccountNotActivatedCallback.notActivated();
        }
    }

    public void checkAccountIsActivatedFromLocal(Context context, CheckAccountActivatedCallback checkAccountActivatedCallback, CheckAccountNotActivatedCallback checkAccountNotActivatedCallback) {
        checkAccountIsActivatedFromLocalWithTokenBean(context, checkAccountActivatedCallback, checkAccountNotActivatedCallback, null);
    }

    private void showPop(final Context context, final TokenBean tokenBean) {
        AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
            @Override
            public final void doInUIThread() {
                ConfirmCustomPopupView.getBuilder(r0).setTitle(r0.getResources().getString(R.string.not_activated_tips)).setTitleBold(true).setTitleSize(16).setConfirmText(r0.getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        AccountUtils.lambda$showPop$0(view);
                    }
                }).setShowCancelBtn(true).setCancelText(r0.getResources().getString(R.string.multisig_transfer)).setCancleListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        AccountUtils.lambda$showPop$1(r1, r2, view);
                    }
                }).build().show();
            }
        });
    }

    public static void lambda$showPop$1(Context context, TokenBean tokenBean, View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.MultiSignature.CLICK_MULTI_SIGNATURE_POP);
        Intent intent = new Intent(context, MultiSelectAddressActivity.class);
        if (tokenBean != null) {
            intent.putExtra(TronConfig.TRC, tokenBean);
        }
        context.startActivity(intent);
    }

    public void checkAccountIsActivatedFromNetWork(final Context context, final String str, final CheckAccountActivatedCallback checkAccountActivatedCallback, final CheckAccountNotActivatedCallback checkAccountNotActivatedCallback, final TokenBean tokenBean) {
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public final void doOnBackground() {
                lambda$checkAccountIsActivatedFromNetWork$3(str, checkAccountNotActivatedCallback, context, tokenBean, checkAccountActivatedCallback);
            }
        });
    }

    public void lambda$checkAccountIsActivatedFromNetWork$3(java.lang.String r1, com.tron.wallet.common.interfaces.CheckAccountNotActivatedCallback r2, android.content.Context r3, com.tron.wallet.common.bean.token.TokenBean r4, com.tron.wallet.common.interfaces.CheckAccountActivatedCallback r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.AccountUtils.lambda$checkAccountIsActivatedFromNetWork$3(java.lang.String, com.tron.wallet.common.interfaces.CheckAccountNotActivatedCallback, android.content.Context, com.tron.wallet.common.bean.token.TokenBean, com.tron.wallet.common.interfaces.CheckAccountActivatedCallback):void");
    }

    public Protocol.Account getAccount(byte[] bArr) throws ConnectErrorException {
        try {
            return TronAPI.queryAccount(bArr, false);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
            throw new ConnectErrorException(e.getMessage());
        }
    }

    public static boolean isNullAccount(Protocol.Account account) {
        return account == null || account.toString().length() <= 0;
    }

    public static boolean checkAccountIsNotActivated(Protocol.Account account) {
        return account.getLatestOprationTime() == 0 && account.getCreateTime() == 0 && account.getAddress() != null && ByteArray.isEmpty(account.getAddress().toByteArray());
    }
}
