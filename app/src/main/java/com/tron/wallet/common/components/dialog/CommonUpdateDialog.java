package com.tron.wallet.common.components.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.DgUpdateBinding;
import com.tronlinkpro.wallet.R;
import java.util.Timer;
import java.util.TimerTask;
public class CommonUpdateDialog {
    DgUpdateBinding binding;
    private final Dialog dialog;
    EditText etPassword;
    Handler handler = new Handler();
    Context mContext;
    TextView tvCancel;
    TextView tvInnerTitle;
    TextView tvOk;
    TextView tvTitle;

    public CommonUpdateDialog(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_update, (ViewGroup) null);
        this.binding = DgUpdateBinding.bind(inflate);
        mappingIds();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.dialog = dialog;
        setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
    }

    private void mappingIds() {
        this.tvTitle = this.binding.title;
        this.tvInnerTitle = this.binding.innerTitle;
        this.etPassword = this.binding.etPassword;
        this.tvCancel = this.binding.tvCancle;
        this.tvOk = this.binding.tvOk;
    }

    public CommonUpdateDialog setTitle(String str) {
        this.tvTitle.setText(str);
        return this;
    }

    public CommonUpdateDialog setInnerTitle(String str) {
        this.tvInnerTitle.setVisibility(View.VISIBLE);
        this.tvInnerTitle.setText(str);
        return this;
    }

    public CommonUpdateDialog hideEditext() {
        this.etPassword.setVisibility(View.GONE);
        return this;
    }

    public CommonUpdateDialog addEditext() {
        this.etPassword.setVisibility(View.VISIBLE);
        return this;
    }

    public String getEditextText() {
        return StringTronUtil.getText(this.etPassword);
    }

    public CommonUpdateDialog setBtListener(String str, View.OnClickListener onClickListener) {
        this.tvOk.setText(str);
        this.tvOk.setOnClickListener(onClickListener);
        return this;
    }

    public void clearEditText() {
        if (this.etPassword.getVisibility() == 0) {
            this.etPassword.setText("");
        }
    }

    public CommonUpdateDialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
        return this;
    }

    public CommonUpdateDialog setCancleBt(View.OnClickListener onClickListener) {
        this.tvCancel.setOnClickListener(onClickListener);
        return this;
    }

    public void show() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        this.dialog.show();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (etPassword.getVisibility() == 0) {
                            showKeyboard();
                        }
                    }
                });
            }
        }, 200L);
    }

    public void showKeyboard() {
        EditText editText = this.etPassword;
        if (editText != null) {
            editText.setFocusable(true);
            this.etPassword.setFocusableInTouchMode(true);
            this.etPassword.requestFocus();
            ((InputMethodManager) this.etPassword.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.etPassword, 0);
        }
    }

    public void dismiss() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    public void setClick() {
        this.binding.tvCancle.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (view.getId() != R.id.tv_cancle) {
                    return;
                }
                dismiss();
            }
        });
    }
}
