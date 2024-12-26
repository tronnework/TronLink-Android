package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.BackupItemView;
import com.tronlinkpro.wallet.R;
public final class AcBackkeystoreBinding implements ViewBinding {
    public final BackupItemView backPri;
    public final Button btnDone;
    public final ItemBackupTip1Binding itemBackupTip1;
    public final ItemBackupTip2Binding itemBackupTip2;
    public final LinearLayout mainContent;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcBackkeystoreBinding(LinearLayout linearLayout, BackupItemView backupItemView, Button button, ItemBackupTip1Binding itemBackupTip1Binding, ItemBackupTip2Binding itemBackupTip2Binding, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.backPri = backupItemView;
        this.btnDone = button;
        this.itemBackupTip1 = itemBackupTip1Binding;
        this.itemBackupTip2 = itemBackupTip2Binding;
        this.mainContent = linearLayout2;
    }

    public static AcBackkeystoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcBackkeystoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_backkeystore, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcBackkeystoreBinding bind(View view) {
        int i = R.id.back_pri;
        BackupItemView backupItemView = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_pri);
        if (backupItemView != null) {
            i = R.id.btn_done;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_done);
            if (button != null) {
                i = R.id.item_backup_tip1;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.item_backup_tip1);
                if (findChildViewById != null) {
                    ItemBackupTip1Binding bind = ItemBackupTip1Binding.bind(findChildViewById);
                    i = R.id.item_backup_tip2;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.item_backup_tip2);
                    if (findChildViewById2 != null) {
                        ItemBackupTip2Binding bind2 = ItemBackupTip2Binding.bind(findChildViewById2);
                        i = R.id.main_content;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.main_content);
                        if (linearLayout != null) {
                            return new AcBackkeystoreBinding((LinearLayout) view, backupItemView, button, bind, bind2, linearLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
