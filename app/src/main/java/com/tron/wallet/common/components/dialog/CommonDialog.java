package com.tron.wallet.common.components.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.DgPasswordBinding;
import com.tronlinkpro.wallet.R;
public class CommonDialog {
    DgPasswordBinding binding;
    Button cancle;
    private final Dialog dialog;
    RelativeLayout doublebutton;
    TextView innertitle;
    ImageView ivStatus;
    Button knew;
    private Context mContext;
    Button see;
    TextView title;

    public CommonDialog(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_password, (ViewGroup) null);
        this.binding = DgPasswordBinding.bind(inflate);
        mappingIds();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.dialog = dialog;
        setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
    }

    private void mappingIds() {
        this.ivStatus = this.binding.ivStatus;
        this.title = this.binding.title;
        this.innertitle = this.binding.innertitle;
        this.doublebutton = this.binding.doublebutton;
        this.knew = this.binding.knew;
        this.see = this.binding.see;
        this.cancle = this.binding.cancle;
    }

    public CommonDialog setTopImage(int i) {
        this.ivStatus.setBackgroundResource(i);
        this.ivStatus.setVisibility(View.GONE);
        return this;
    }

    public CommonDialog setSuccessImage() {
        return setTopImage(R.mipmap.tron_success);
    }

    public CommonDialog setErrorImage() {
        return setTopImage(R.mipmap.tron_error);
    }

    public CommonDialog setTitle(int i) {
        this.innertitle.setVisibility(View.GONE);
        this.title.setText(i);
        return this;
    }

    public CommonDialog setTitle(String str) {
        this.innertitle.setVisibility(View.GONE);
        this.title.setText(str);
        return this;
    }

    public CommonDialog setInnerTitle(int i) {
        this.innertitle.setVisibility(View.VISIBLE);
        this.innertitle.setText(i);
        return this;
    }

    public CommonDialog setInnerTitle(String str) {
        this.innertitle.setVisibility(View.VISIBLE);
        this.innertitle.setText(str);
        return this;
    }

    public CommonDialog setSingleButton() {
        this.doublebutton.setVisibility(View.GONE);
        this.knew.setVisibility(View.VISIBLE);
        this.knew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return this;
    }

    public CommonDialog setSingleButton(View.OnClickListener onClickListener) {
        this.doublebutton.setVisibility(View.GONE);
        this.knew.setVisibility(View.VISIBLE);
        this.knew.setOnClickListener(onClickListener);
        return this;
    }

    public CommonDialog setDoubleButton(int i, int i2, View.OnClickListener onClickListener) {
        this.doublebutton.setVisibility(View.VISIBLE);
        this.knew.setVisibility(View.GONE);
        if (!StringTronUtil.isEmpty(StringTronUtil.getResString(i))) {
            this.see.setText(i);
        }
        if (i2 != 0 && !StringTronUtil.isEmpty(StringTronUtil.getResString(i2))) {
            this.cancle.setText(i2);
        }
        this.see.setOnClickListener(onClickListener);
        this.cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return this;
    }

    public CommonDialog setDoubleButton(int i, int i2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        setDoubleButton(i, i2, onClickListener);
        this.cancle.setOnClickListener(onClickListener2);
        return this;
    }

    public CommonDialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
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
}
