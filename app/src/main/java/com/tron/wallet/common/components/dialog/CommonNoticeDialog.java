package com.tron.wallet.common.components.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.DgNoticeDialogBinding;
import com.tronlinkpro.wallet.R;
public class CommonNoticeDialog {
    DgNoticeDialogBinding binding;
    private final Dialog dialog;
    ImageView ivTitle;
    Context mContext;
    RelativeLayout top;
    Button tvCancle;
    TextView tvContent;
    Button tvCopy;
    TextView tvTitle;

    public CommonNoticeDialog(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_notice_dialog, (ViewGroup) null);
        this.binding = DgNoticeDialogBinding.bind(inflate);
        mappingIds();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.dialog = dialog;
        setCanceledOnTouchOutside(true);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        dialog.setContentView(inflate, new ViewGroup.LayoutParams(defaultDisplay.getWidth(), defaultDisplay.getHeight() - UIUtils.getNavigationBarHeight()));
    }

    private void mappingIds() {
        this.tvTitle = this.binding.title;
        this.tvContent = this.binding.tvContent;
        this.top = this.binding.top;
        this.ivTitle = this.binding.ivTitle;
        this.tvCopy = this.binding.btnConfirm;
        this.tvCancle = this.binding.btnCancel;
    }

    public CommonNoticeDialog setTitle(String str) {
        this.tvTitle.setText(str);
        return this;
    }

    public CommonNoticeDialog setTitle(int i) {
        this.tvTitle.setText(i);
        return this;
    }

    public CommonNoticeDialog setContent(int i) {
        this.tvContent.setText(i);
        return this;
    }

    public CommonNoticeDialog setContent(String str) {
        this.tvContent.setText(str);
        return this;
    }

    public CommonNoticeDialog setBtListener(String str, View.OnClickListener onClickListener) {
        this.tvCopy.setText(str);
        this.tvCopy.setOnClickListener(onClickListener);
        return this;
    }

    public CommonNoticeDialog setBtListener(int i, View.OnClickListener onClickListener) {
        this.tvCopy.setText(i);
        this.tvCopy.setOnClickListener(onClickListener);
        return this;
    }

    public CommonNoticeDialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
        return this;
    }

    public CommonNoticeDialog setCancleBt(View.OnClickListener onClickListener) {
        this.tvCancle.setOnClickListener(onClickListener);
        return this;
    }

    public CommonNoticeDialog setCancleBt(int i, View.OnClickListener onClickListener) {
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
