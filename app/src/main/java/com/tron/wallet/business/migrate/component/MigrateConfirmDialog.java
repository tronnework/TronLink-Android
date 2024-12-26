package com.tron.wallet.business.migrate.component;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tronlinkpro.wallet.R;
public class MigrateConfirmDialog extends CenterPopupView {
    private int appCheckCode;
    private View.OnClickListener onClickInstall;
    private View.OnClickListener onClickMigrate;

    private void setOnClickMigrateListener(View.OnClickListener onClickListener) {
        this.onClickMigrate = onClickListener;
    }

    private void setonClickInstallListener(View.OnClickListener onClickListener) {
        this.onClickInstall = onClickListener;
    }

    @Override
    public int getImplLayoutId() {
        return R.layout.dialog_migrate_confirm;
    }

    void setLaunchCode(int i) {
        this.appCheckCode = i;
    }

    public MigrateConfirmDialog(Context context) {
        super(context);
    }

    public static void showUp(Context context, int i, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        MigrateConfirmDialog migrateConfirmDialog = new MigrateConfirmDialog(context);
        migrateConfirmDialog.setLaunchCode(i);
        migrateConfirmDialog.setonClickInstallListener(onClickListener2);
        migrateConfirmDialog.setOnClickMigrateListener(onClickListener);
        new XPopup.Builder(context).dismissOnTouchOutside(false).dismissOnBackPressed(false).asCustom(migrateConfirmDialog).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TextView textView = (TextView) findViewById(R.id.tv_warning);
        TextView textView2 = (TextView) findViewById(R.id.tv_warning_subtitle);
        final Button button = (Button) findViewById(R.id.btn_migrate);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_agree);
        findViewById(R.id.tv_cancel).setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                dismiss();
            }
        });
        int i = this.appCheckCode;
        if (i == 0) {
            textView2.setVisibility(View.VISIBLE);
            textView.setText(R.string.migrate_installed);
            button.setText(R.string.start_migrate_out);
            button.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    if (onClickMigrate != null) {
                        onClickMigrate.onClick(view);
                    }
                    dismiss();
                }
            });
        } else {
            if (i == 2) {
                textView.setText(R.string.migrate_global_version_deprecated);
                button.setText(R.string.migrate_upgrade_global);
            } else {
                textView.setText(R.string.migrate_not_installed);
                button.setText(R.string.migrate_go_market);
            }
            textView2.setVisibility(View.GONE);
            button.setOnClickListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    if (onClickInstall != null) {
                        onClickInstall.onClick(view);
                    }
                    dismiss();
                }
            });
        }
        button.setEnabled(checkBox.isChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                button.setEnabled(z);
            }
        });
    }

    @Override
    public int getMaxWidth() {
        return XPopupUtils.getScreenWidth(getContext()) - (XPopupUtils.dp2px(getContext(), 15.0f) * 2);
    }

    @Override
    public int getMaxHeight() {
        return (int) (XPopupUtils.getScreenHeight(getContext()) * 0.72d);
    }
}
