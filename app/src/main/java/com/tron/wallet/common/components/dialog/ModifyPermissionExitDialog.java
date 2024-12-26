package com.tron.wallet.common.components.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tron.wallet.databinding.DgModifyPermissionExitBinding;
import com.tronlinkpro.wallet.R;
public class ModifyPermissionExitDialog implements View.OnClickListener {
    DgModifyPermissionExitBinding binding;
    private Context mContext;
    private final Dialog mDialog;
    private OnExitListener mOnExitListener;

    public interface OnExitListener {
        void onClickExit(View view);
    }

    public ModifyPermissionExitDialog setOnExitListener(OnExitListener onExitListener) {
        this.mOnExitListener = onExitListener;
        return this;
    }

    public ModifyPermissionExitDialog(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_modify_permission_exit, (ViewGroup) null);
        this.binding = DgModifyPermissionExitBinding.bind(inflate);
        initClickListener();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.mDialog = dialog;
        setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
    }

    private void initClickListener() {
        this.binding.tvCancle.setOnClickListener(this);
        this.binding.tvExit.setOnClickListener(this);
    }

    public ModifyPermissionExitDialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.mDialog.setCancelable(z);
        }
        return this;
    }

    public void dismiss() {
        if (this.mDialog.isShowing()) {
            this.mDialog.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_cancle) {
            dismiss();
        } else if (id != R.id.tv_exit) {
        } else {
            dismiss();
            this.mOnExitListener.onClickExit(view);
        }
    }

    public void show() {
        if (this.mDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        this.mDialog.show();
    }
}
