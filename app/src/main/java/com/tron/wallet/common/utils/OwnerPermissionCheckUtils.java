package com.tron.wallet.common.utils;

import android.content.Context;
import android.view.View;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
import org.tron.walletserver.PermissionException;
public class OwnerPermissionCheckUtils {
    public static void lambda$showMultiSignDialog$2(View view) {
    }

    public static void lambda$showNotActivatedPopup$0(View view) {
    }

    public static boolean checkHasOwnerPermission(Protocol.Account account) {
        if (account == null) {
            return true;
        }
        try {
            return WalletUtils.checkHaveOwnerPermissions(WalletUtils.getSelectedPublicWallet().getAddress(), account.getOwnerPermission());
        } catch (PermissionException e) {
            LogUtils.e(e);
            return true;
        }
    }

    public static void checkWithPopup(Context context, Protocol.Account account, int[] iArr, int[] iArr2, Consumer<Void> consumer, Consumer<Void> consumer2) {
        if (account != null) {
            try {
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (!AccountUtils.checkAccountIsNotActivated(account)) {
                if (!WalletUtils.checkHaveOwnerPermissions(WalletUtils.getSelectedPublicWallet().getAddress(), account.getOwnerPermission())) {
                    showMultiSignDialog(context, iArr2[0], iArr2[1], consumer2);
                    return;
                }
                consumer.accept(null);
                return;
            }
        }
        showNotActivatedPopup(context, iArr[0], iArr[1], consumer2);
    }

    public static void checkWithPopup(Context context, Protocol.Account account, int i, int i2, Consumer<Void> consumer, Consumer<Void> consumer2) {
        if (account != null) {
            try {
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (!AccountUtils.checkAccountIsNotActivated(account)) {
                if (!WalletUtils.checkHaveOwnerPermissions(WalletUtils.getSelectedPublicWallet().getAddress(), account.getOwnerPermission())) {
                    showMultiSignDialog(context, i2, consumer2);
                    return;
                }
                consumer.accept(null);
                return;
            }
        }
        showNotActivatedPopup(context, i, consumer2);
    }

    public static void checkStakeWithPopup(Context context, Protocol.Account account, int i, int i2, Consumer<Void> consumer, Consumer<Void> consumer2) {
        if (account != null) {
            try {
            } catch (Exception e) {
                LogUtils.e(e);
            }
            if (!AccountUtils.checkAccountIsNotActivated(account)) {
                if (!WalletUtils.checkHaveOwnerPermissions(WalletUtils.getSelectedPublicWallet().getAddress(), account.getOwnerPermission())) {
                    showStakeMultiSignDialog(context, i2, consumer2);
                    return;
                }
                consumer.accept(null);
                return;
            }
        }
        showStakeNotActivatedPopup(context, i, consumer2);
    }

    public static void showMultiSignDialog(Context context, int i, Consumer<Void> consumer) {
        showMultiSignDialog(context, i, R.string.multisig, consumer);
    }

    public static void showStakeMultiSignDialog(Context context, int i, Consumer<Void> consumer) {
        showMultiSignDialog(context, i, R.string.multi_sign_stake_page_title, consumer);
    }

    private static void showNotActivatedPopup(Context context, int i, Consumer<Void> consumer) {
        showNotActivatedPopup(context, i, R.string.multisig, consumer);
    }

    public static void showStakeNotActivatedPopup(Context context, int i, Consumer<Void> consumer) {
        showNotActivatedPopup(context, i, R.string.multi_sign_stake_page_title, consumer);
    }

    private static void showNotActivatedPopup(Context context, int i, int i2, final Consumer<Void> consumer) {
        ConfirmCustomPopupView.getBuilder(context).setTitle(context.getResources().getString(i)).setTitleBold(true).setTitleSize(16).setConfirmText(context.getResources().getString(R.string.confirm)).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                OwnerPermissionCheckUtils.lambda$showNotActivatedPopup$0(view);
            }
        }).setShowCancelBtn(true).setCancelText(context.getResources().getString(i2)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                Consumer.this.accept(null);
            }
        }).build().show();
    }

    public static void showMultiSignDialog(Context context, int i, int i2, final Consumer<Void> consumer) {
        ConfirmCustomPopupView.getBuilder(context).setTitle(context.getString(i)).setTitleSize(16).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
            }
        }).setConfirmText(context.getString(i2)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                OwnerPermissionCheckUtils.lambda$showMultiSignDialog$2(view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                OwnerPermissionCheckUtils.lambda$showMultiSignDialog$3(Consumer.this, view);
            }
        }).build().show();
    }

    public static void lambda$showMultiSignDialog$3(Consumer consumer, View view) {
        if (consumer != null) {
            consumer.accept(null);
        }
    }
}
