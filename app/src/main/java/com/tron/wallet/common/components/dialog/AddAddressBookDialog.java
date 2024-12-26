package com.tron.wallet.common.components.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.DgAddaddressbookBinding;
import com.tronlinkpro.wallet.R;
import java.util.Timer;
import java.util.TimerTask;
public class AddAddressBookDialog {
    DgAddaddressbookBinding binding;
    private final Dialog dialog;
    ErrorEdiTextLayout errorAddressName;
    EditText etAddressName;
    Handler handler = new Handler();
    ImageView ivDelete;
    Context mContext;
    TextView tvCancle;
    TextView tvInnerTitle;
    TextView tvOk;
    TextView tvTitle;

    public AddAddressBookDialog(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dg_addaddressbook, (ViewGroup) null);
        this.binding = DgAddaddressbookBinding.bind(inflate);
        mappingIds();
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        this.dialog = dialog;
        setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        this.tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        this.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etAddressName.setText("");
            }
        });
        this.etAddressName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showOrHideNameError(false, "");
                AddAddressBookDialog addAddressBookDialog = AddAddressBookDialog.this;
                addAddressBookDialog.checkInputContent(addAddressBookDialog.etAddressName, 20);
            }
        });
    }

    private void mappingIds() {
        this.tvTitle = this.binding.title;
        this.tvInnerTitle = this.binding.innerTitle;
        this.etAddressName = this.binding.etAddressName;
        this.errorAddressName = this.binding.eetAddressName;
        this.ivDelete = this.binding.ivDelete;
        this.tvCancle = this.binding.tvCancle;
        this.tvOk = this.binding.tvOk;
    }

    public AddAddressBookDialog setTitle(String str) {
        this.tvTitle.setText(str);
        return this;
    }

    public AddAddressBookDialog hideTitle() {
        this.tvTitle.setVisibility(View.GONE);
        return this;
    }

    public AddAddressBookDialog setInnerTitle(String str) {
        this.tvInnerTitle.setVisibility(View.VISIBLE);
        this.tvInnerTitle.setText(str);
        return this;
    }

    public AddAddressBookDialog hideEditext() {
        this.etAddressName.setVisibility(View.GONE);
        return this;
    }

    public AddAddressBookDialog addEditext() {
        this.etAddressName.setVisibility(View.VISIBLE);
        return this;
    }

    public AddAddressBookDialog setEditextHint(int i) {
        this.etAddressName.setHint(i);
        return this;
    }

    public AddAddressBookDialog setEditextInputTypeText() {
        this.etAddressName.setInputType(1);
        return this;
    }

    public String getEditextText() {
        return StringTronUtil.getText(this.etAddressName);
    }

    public AddAddressBookDialog setBtListener(String str, View.OnClickListener onClickListener) {
        this.tvOk.setText(str);
        this.tvOk.setOnClickListener(onClickListener);
        return this;
    }

    public void clearEditText() {
        if (this.etAddressName.getVisibility() == 0) {
            this.etAddressName.setText("");
        }
    }

    public void showOrHideNameError(boolean z, String str) {
        if (z) {
            this.errorAddressName.setTextError3(str);
            this.errorAddressName.showError3();
            return;
        }
        this.errorAddressName.hideError3();
    }

    public void hideError() {
        this.errorAddressName.hideError3();
    }

    public AddAddressBookDialog setCanceledOnTouchOutside(boolean z) {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(z);
            this.dialog.setCancelable(z);
        }
        return this;
    }

    public AddAddressBookDialog setCancleBt(View.OnClickListener onClickListener) {
        this.tvCancle.setOnClickListener(onClickListener);
        return this;
    }

    public void show() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        EditText editText = this.etAddressName;
        if (editText != null && editText.getVisibility() == 0) {
            this.tvOk.setClickable(false);
            this.tvOk.setBackground(this.mContext.getDrawable(R.drawable.roundborder_dbdbdb_20));
            this.etAddressName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence != null && charSequence.length() > 0) {
                        tvOk.setClickable(true);
                        tvOk.setBackground(mContext.getDrawable(R.drawable.roundborder_135dcd_10));
                        return;
                    }
                    tvOk.setClickable(false);
                    tvOk.setBackground(mContext.getDrawable(R.drawable.roundborder_dbdbdb_20));
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable == null || editable.length() == 0) {
                        ivDelete.setVisibility(View.GONE);
                    } else {
                        ivDelete.setVisibility(View.VISIBLE);
                    }
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
                        if (etAddressName.getVisibility() == 0) {
                            showKeyboard();
                        }
                    }
                });
            }
        }, 200L);
    }

    public void showKeyboard() {
        EditText editText = this.etAddressName;
        if (editText != null) {
            editText.setFocusable(true);
            this.etAddressName.setFocusableInTouchMode(true);
            this.etAddressName.requestFocus();
            ((InputMethodManager) this.etAddressName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.etAddressName, 0);
        }
    }

    public void dismiss() {
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    public void checkInputContent(EditText editText, int i) {
        try {
            this.errorAddressName.hideError();
            Editable text = editText.getText();
            String trim = text.toString().trim();
            int selectionEnd = Selection.getSelectionEnd(text);
            int i2 = 0;
            for (int i3 = 0; i3 < trim.length(); i3++) {
                char charAt = trim.charAt(i3);
                i2 = (charAt < ' ' || charAt > 'z') ? i2 + 2 : i2 + 1;
                if (i2 > i) {
                    String substring = trim.substring(0, i3);
                    LogUtils.d("alex", "the string content is " + substring);
                    editText.setText(substring);
                    Editable text2 = editText.getText();
                    if (selectionEnd > text2.length()) {
                        selectionEnd = text2.length();
                    }
                    Selection.setSelection(text2, selectionEnd);
                    return;
                }
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }
}
