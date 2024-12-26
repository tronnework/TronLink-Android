package com.tron.wallet.common.components.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.DgMnemonicQrBinding;
import com.tronlinkpro.wallet.R;
public class MnemonicQRDialog implements View.OnClickListener {
    DgMnemonicQrBinding binding;
    private final Dialog dialog;
    Context mContext;
    ImageView mQRCodeIv;
    TextView mTvTitle;

    public MnemonicQRDialog(Context context, Bitmap bitmap) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_mnemonic_qr, (ViewGroup) null);
        this.binding = DgMnemonicQrBinding.bind(inflate);
        mappingIds();
        initClickListener();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.dialog = dialog;
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        dialog.setContentView(inflate, new ViewGroup.LayoutParams(defaultDisplay.getWidth(), defaultDisplay.getHeight() - UIUtils.getNavigationBarHeight()));
        setCanceledOnTouchOutside(true);
        this.mQRCodeIv.setImageBitmap(bitmap);
    }

    private void initClickListener() {
        this.binding.ivClose.setOnClickListener(this);
    }

    private void mappingIds() {
        this.mQRCodeIv = this.binding.ivQrcode;
        this.mTvTitle = this.binding.tvTitle;
    }

    public void setTitle(int i) {
        this.mTvTitle.setText(i);
    }

    public MnemonicQRDialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
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

    @Override
    public void onClick(View view) {
        if (view.getId() != R.id.iv_close) {
            return;
        }
        dismiss();
    }
}
