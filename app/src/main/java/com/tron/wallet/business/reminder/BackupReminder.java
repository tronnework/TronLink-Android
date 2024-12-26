package com.tron.wallet.business.reminder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class BackupReminder {
    public static final String FROM_WALLET_MANAGER = "fromWalletManager";
    private Context context;
    private String from;
    private String info;
    private onActionListener onActionListener;
    private Wallet wallet;

    public interface onActionListener {
        void onClickCancel(Wallet wallet);

        void onClickConfirm(Wallet wallet);
    }

    public BackupReminder(Context context, Wallet wallet, String str, String str2) {
        this(context, wallet, str, str2, null);
    }

    public BackupReminder(Context context, Wallet wallet, String str, String str2, onActionListener onactionlistener) {
        this.context = context;
        this.wallet = wallet;
        this.from = str;
        this.info = str2;
        this.onActionListener = onactionlistener;
    }

    public static boolean isWalletBackup(Wallet wallet) {
        return wallet == null || !WalletUtils.hasMnemonic(wallet.getWalletName()) || SpAPI.THIS.isBackUp(wallet.getWalletName()) || wallet.getCreateType() != 0;
    }

    public static void showBackupPopup(Context context, Wallet wallet, String str) {
        new BackupReminder(context, wallet, str, null).show();
    }

    public static void showBackupPopup(Context context, Wallet wallet, String str, onActionListener onactionlistener) {
        showBackupPopup(context, wallet, str, null, onactionlistener);
    }

    public static void showBackupPopup(Context context, Wallet wallet, String str, String str2) {
        new BackupReminder(context, wallet, str, str2).show();
    }

    public static void showBackupPopup(Context context, Wallet wallet, String str, String str2, onActionListener onactionlistener) {
        new BackupReminder(context, wallet, str, str2, onactionlistener).show();
    }

    public void goBackup() {
        if (FROM_WALLET_MANAGER.equals(this.from)) {
            return;
        }
        WalletDetailActivity.startActivity(this.context, this.wallet.getWalletName(), this.wallet.isShieldedWallet());
    }

    public void show() {
        new XPopup.Builder(this.context).maxWidth(XPopupUtils.getScreenWidth(this.context)).dismissOnTouchOutside(false).popupAnimation(PopupAnimation.NoAnimation).asCustom(new fun1(this.context)).show();
    }

    public class fun1 extends CenterPopupView {
        @Override
        public int getImplLayoutId() {
            return R.layout.popup_security_alert;
        }

        fun1(Context context) {
            super(context);
        }

        @Override
        public void onCreate() {
            super.onCreate();
            View findViewById = findViewById(R.id.btn_confirm);
            View findViewById2 = findViewById(R.id.btn_cancel);
            if (!StringTronUtil.isEmpty(info)) {
                ((TextView) findViewById(R.id.info)).setText(info);
            }
            findViewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    BackupReminder.1.this.lambda$onCreate$0(view);
                }
            });
            findViewById2.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    BackupReminder.1.this.lambda$onCreate$1(view);
                }
            });
        }

        public void lambda$onCreate$0(View view) {
            if (onActionListener != null) {
                onActionListener.onClickConfirm(wallet);
            }
            goBackup();
            dismiss();
        }

        public void lambda$onCreate$1(View view) {
            if (onActionListener != null) {
                onActionListener.onClickCancel(wallet);
            }
            dismiss();
        }
    }
}
