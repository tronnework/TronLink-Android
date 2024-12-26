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
public final class ActivityBackupPriKeyBinding implements ViewBinding {
    public final BackupItemView backAddr;
    public final BackupItemView backAk;
    public final BackupItemView backAsk;
    public final BackupItemView backIvk;
    public final BackupItemView backNk;
    public final BackupItemView backNsk;
    public final BackupItemView backOvk;
    public final BackupItemView backPri;
    public final BackupItemView backSk;
    public final Button btnDone;
    public final LinearLayout llMore;
    public final LinearLayout llOtherKeys;
    public final LinearLayout mainContent;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ActivityBackupPriKeyBinding(LinearLayout linearLayout, BackupItemView backupItemView, BackupItemView backupItemView2, BackupItemView backupItemView3, BackupItemView backupItemView4, BackupItemView backupItemView5, BackupItemView backupItemView6, BackupItemView backupItemView7, BackupItemView backupItemView8, BackupItemView backupItemView9, Button button, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4) {
        this.rootView = linearLayout;
        this.backAddr = backupItemView;
        this.backAk = backupItemView2;
        this.backAsk = backupItemView3;
        this.backIvk = backupItemView4;
        this.backNk = backupItemView5;
        this.backNsk = backupItemView6;
        this.backOvk = backupItemView7;
        this.backPri = backupItemView8;
        this.backSk = backupItemView9;
        this.btnDone = button;
        this.llMore = linearLayout2;
        this.llOtherKeys = linearLayout3;
        this.mainContent = linearLayout4;
    }

    public static ActivityBackupPriKeyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityBackupPriKeyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_backup_pri_key, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityBackupPriKeyBinding bind(View view) {
        int i = R.id.back_addr;
        BackupItemView backupItemView = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_addr);
        if (backupItemView != null) {
            i = R.id.back_ak;
            BackupItemView backupItemView2 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_ak);
            if (backupItemView2 != null) {
                i = R.id.back_ask;
                BackupItemView backupItemView3 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_ask);
                if (backupItemView3 != null) {
                    i = R.id.back_ivk;
                    BackupItemView backupItemView4 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_ivk);
                    if (backupItemView4 != null) {
                        i = R.id.back_nk;
                        BackupItemView backupItemView5 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_nk);
                        if (backupItemView5 != null) {
                            i = R.id.back_nsk;
                            BackupItemView backupItemView6 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_nsk);
                            if (backupItemView6 != null) {
                                i = R.id.back_ovk;
                                BackupItemView backupItemView7 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_ovk);
                                if (backupItemView7 != null) {
                                    i = R.id.back_pri;
                                    BackupItemView backupItemView8 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_pri);
                                    if (backupItemView8 != null) {
                                        i = R.id.back_sk;
                                        BackupItemView backupItemView9 = (BackupItemView) ViewBindings.findChildViewById(view, R.id.back_sk);
                                        if (backupItemView9 != null) {
                                            i = R.id.btn_done;
                                            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_done);
                                            if (button != null) {
                                                i = R.id.ll_more;
                                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_more);
                                                if (linearLayout != null) {
                                                    i = R.id.ll_other_keys;
                                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_other_keys);
                                                    if (linearLayout2 != null) {
                                                        i = R.id.main_content;
                                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.main_content);
                                                        if (linearLayout3 != null) {
                                                            return new ActivityBackupPriKeyBinding((LinearLayout) view, backupItemView, backupItemView2, backupItemView3, backupItemView4, backupItemView5, backupItemView6, backupItemView7, backupItemView8, backupItemView9, button, linearLayout, linearLayout2, linearLayout3);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
