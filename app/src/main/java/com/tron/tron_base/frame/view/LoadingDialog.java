package com.tron.tron_base.frame.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.tron.tron_base.R;
public class LoadingDialog {
    private Context context;
    private Dialog dialog;
    private ImageView iv_loading;
    private TextView tv;

    public Context getContext() {
        return this.context;
    }

    public LoadingDialog(Context context) {
        this.context = context;
        extracted(context, true, false);
    }

    public LoadingDialog(Context context, boolean z) {
        this.context = context;
        extracted(context, z, false);
    }

    public LoadingDialog(Context context, boolean z, boolean z2) {
        this.context = context;
        extracted(context, z, z2);
    }

    private void extracted(Context context, boolean z, boolean z2) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_loading, (ViewGroup) null);
        this.iv_loading = (ImageView) inflate.findViewById(R.id.iv_loading);
        this.tv = (TextView) inflate.findViewById(R.id.tv_loading);
        Dialog dialog = new Dialog(context, z2 ? R.style.loading_dialog_2 : R.style.loading_dialog);
        this.dialog = dialog;
        dialog.setCanceledOnTouchOutside(z);
        this.dialog.setCancelable(z);
        this.dialog.setContentView(inflate);
    }

    public void setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
    }

    public void show() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.iv_loading, "rotation", 0.0f, 360.0f);
        ofFloat.setDuration(600L);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.start();
        if (((Activity) getContext()).isFinishing()) {
            return;
        }
        this.dialog.show();
    }

    public void show(String str) {
        this.tv.setText(str);
        show();
    }

    public void show(int i) {
        this.tv.setText(i);
        show();
    }

    public void dismiss() {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
