package com.tron.wallet.business.reminder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.detail.WalletDetailActivity;
import com.tron.wallet.common.components.dialog.CustomDialog;
import com.tron.wallet.common.utils.HookUtils;
import com.tron.wallet.common.utils.RootUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
public class RootCheckerReminder extends BaseReminder {
    public static final String ID = "RootCheckerReminder";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void start() {
        LogUtils.d(ID, "start:" + getId());
        if (RootUtils.isRootSystem() || HookUtils.isHook(AppContextUtil.getmApplication())) {
            setState(ReminderState.NEED_SHOW);
        } else {
            setState(ReminderState.UNNEEDED_SHOW);
        }
    }

    @Override
    public void show(final Context context) {
        LogUtils.d(ID, "show:" + getId());
        super.show(context);
        final Activity activity = (Activity) context;
        final boolean z = WalletUtils.getSelectedWallet() == null || WalletUtils.getSelectedWallet().isWatchOnly();
        final boolean z2 = WalletUtils.getSelectedWallet() == null || WalletUtils.getSelectedWallet().isBackUp();
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        final CustomDialog build = builder.style(R.style.loading_dialog).heightDimenRes(-2).widthDimenRes(-1).setGravity(17).view(R.layout.dg_root_dialog).build();
        build.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public final void onDismiss(DialogInterface dialogInterface) {
                lambda$show$0(dialogInterface);
            }
        });
        View view = builder.getView();
        TextView textView = (TextView) view.findViewById(R.id.tv_cancle);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_ok);
        if (z) {
            textView2.setText(R.string.exit);
        } else if (z2) {
            textView2.setText(R.string.exit);
        } else {
            textView2.setText(R.string.backup_assets);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                build.dismiss();
                if (z) {
                    activity.finish();
                } else if (!z2) {
                    build.dismiss();
                    WalletDetailActivity.startActivity(context, WalletUtils.getSelectedWallet().getWalletName(), false);
                } else {
                    activity.finish();
                }
            }
        });
        build.show();
        build.setCanceledOnTouchOutside(false);
    }

    public void lambda$show$0(DialogInterface dialogInterface) {
        setState(ReminderState.HAS_SHOW);
    }
}
