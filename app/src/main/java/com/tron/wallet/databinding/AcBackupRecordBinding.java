package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.NoNetView;
import com.tronlinkpro.wallet.R;
public final class AcBackupRecordBinding implements ViewBinding {
    public final RecyclerView backupList;
    public final ImageView ivWarning;
    public final NoNetView noDataView;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcBackupRecordBinding(LinearLayout linearLayout, RecyclerView recyclerView, ImageView imageView, NoNetView noNetView) {
        this.rootView = linearLayout;
        this.backupList = recyclerView;
        this.ivWarning = imageView;
        this.noDataView = noNetView;
    }

    public static AcBackupRecordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcBackupRecordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_backup_record, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcBackupRecordBinding bind(View view) {
        int i = R.id.backup_list;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.backup_list);
        if (recyclerView != null) {
            i = R.id.iv_warning;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_warning);
            if (imageView != null) {
                i = R.id.no_data_view;
                NoNetView noNetView = (NoNetView) ViewBindings.findChildViewById(view, R.id.no_data_view);
                if (noNetView != null) {
                    return new AcBackupRecordBinding((LinearLayout) view, recyclerView, imageView, noNetView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
