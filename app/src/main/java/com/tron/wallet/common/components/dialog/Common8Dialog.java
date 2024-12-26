package com.tron.wallet.common.components.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.databinding.Common8dialogTipsBinding;
import com.tronlinkpro.wallet.R;
public class Common8Dialog {
    Common8dialogTipsBinding binding;
    private final Dialog dialog;
    Handler handler = new Handler();
    Context mContext;
    RelativeLayout top;
    TextView tvCancle;
    TextView tvContent;
    TextView tvOk;
    TextView tvTitle;

    public Common8Dialog(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.common_8dialog_tips, (ViewGroup) null);
        this.binding = Common8dialogTipsBinding.bind(inflate);
        mappingIds();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.dialog = dialog;
        setCanceledOnTouchOutside(true);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        dialog.setContentView(inflate, new ViewGroup.LayoutParams(defaultDisplay.getWidth(), defaultDisplay.getHeight()));
    }

    private void mappingIds() {
        this.tvTitle = this.binding.title;
        this.tvContent = this.binding.tvContent;
        this.tvCancle = this.binding.tvCancle;
        this.tvOk = this.binding.tvOk;
        this.top = this.binding.top;
    }

    public Common8Dialog setTitle(String str) {
        this.tvTitle.setText(str);
        return this;
    }

    public Common8Dialog setTitle(int i) {
        this.tvTitle.setText(i);
        return this;
    }

    public Common8Dialog setContent(int i) {
        this.tvContent.setText(i);
        return this;
    }

    public Common8Dialog setContent(String str) {
        this.tvContent.setText(str);
        return this;
    }

    public Common8Dialog setBtListener(String str, View.OnClickListener onClickListener) {
        this.tvOk.setText(str);
        this.tvOk.setOnClickListener(onClickListener);
        return this;
    }

    public Common8Dialog setBtListener(int i, View.OnClickListener onClickListener) {
        this.tvOk.setText(i);
        this.tvOk.setOnClickListener(onClickListener);
        return this;
    }

    public Common8Dialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
        return this;
    }

    public Common8Dialog setCancleBt(View.OnClickListener onClickListener) {
        this.tvCancle.setOnClickListener(onClickListener);
        return this;
    }

    public Common8Dialog setCancleBt(int i, View.OnClickListener onClickListener) {
        this.tvCancle.setText(i);
        this.tvCancle.setOnClickListener(onClickListener);
        return this;
    }

    public void show() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        this.dialog.show();
    }

    public void dismiss() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }
}
