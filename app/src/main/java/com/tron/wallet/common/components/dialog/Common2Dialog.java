package com.tron.wallet.common.components.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.DgEtpasswordBinding;
import com.tronlinkpro.wallet.R;
import java.util.Timer;
import java.util.TimerTask;
public class Common2Dialog implements View.OnClickListener {
    DgEtpasswordBinding binding;
    private final Dialog dialog;
    EditText etPassword;
    Handler handler = new Handler();
    Context mContext;
    TextView tvCancle;
    TextView tvInnerTitle;
    TextView tvOk;
    TextView tvTitle;

    public Common2Dialog(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_etpassword, (ViewGroup) null);
        this.binding = DgEtpasswordBinding.bind(inflate);
        mappingId();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.dialog = dialog;
        setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate, new ViewGroup.LayoutParams(((Activity) context).getWindowManager().getDefaultDisplay().getWidth(), -2));
        this.tvCancle.setOnClickListener(this);
    }

    private void mappingId() {
        this.tvTitle = this.binding.title;
        this.tvInnerTitle = this.binding.innerTitle;
        this.etPassword = this.binding.etPassword;
        this.tvCancle = this.binding.tvCancle;
        this.tvOk = this.binding.tvOk;
    }

    public Common2Dialog setTitle(String str) {
        this.tvTitle.setText(str);
        return this;
    }

    public Common2Dialog hideTitle() {
        this.tvTitle.setVisibility(View.GONE);
        return this;
    }

    public Common2Dialog setInnerTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            this.tvInnerTitle.setVisibility(View.GONE);
        } else {
            this.tvInnerTitle.setVisibility(View.VISIBLE);
            this.tvInnerTitle.setText(str);
        }
        return this;
    }

    public Common2Dialog hideEditext() {
        this.etPassword.setVisibility(View.GONE);
        return this;
    }

    public Common2Dialog addEditext() {
        this.etPassword.setVisibility(View.VISIBLE);
        return this;
    }

    public Common2Dialog setEditextHint(int i) {
        this.etPassword.setHint(i);
        return this;
    }

    public Common2Dialog setEditextInputTypeText() {
        this.etPassword.setInputType(1);
        return this;
    }

    public String getEditextText() {
        return StringTronUtil.getText(this.etPassword);
    }

    public Common2Dialog setBtListener(String str, View.OnClickListener onClickListener) {
        this.tvOk.setText(str);
        this.tvOk.setOnClickListener(onClickListener);
        return this;
    }

    public void clearEditText() {
        if (this.etPassword.getVisibility() == 0) {
            this.etPassword.setText("");
        }
    }

    public Common2Dialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
        return this;
    }

    public Common2Dialog setCancleBt(View.OnClickListener onClickListener) {
        this.tvCancle.setOnClickListener(onClickListener);
        return this;
    }

    public void show() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        EditText editText = this.etPassword;
        if (editText != null && editText.getVisibility() == 0) {
            this.tvOk.setClickable(false);
            this.tvOk.setBackground(this.mContext.getDrawable(R.drawable.roundborder_dbdbdb_20));
            this.etPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence != null && charSequence.length() > 0) {
                        tvOk.setClickable(true);
                        tvOk.setBackground(mContext.getDrawable(R.drawable.ripple_roundborder_23_10));
                        return;
                    }
                    tvOk.setClickable(false);
                    tvOk.setBackground(mContext.getDrawable(R.drawable.roundborder_dbdbdb_20));
                }
            });
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

    @Override
    public void onClick(View view) {
        if (view.getId() != R.id.tv_cancle) {
            return;
        }
        dismiss();
    }
}
