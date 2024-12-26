package com.tron.wallet.business.tabmy.walletmanage;

import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcChangenameBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.InvalidNameException;
public class ChangeNameActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcChangenameBinding binding;
    Button btNext;
    EditText etName;
    View rlErrorName;
    private RxManager rxManager;
    TextView tvNameError;
    private String walletName;
    private int walletPos;

    @Override
    protected void setLayout() {
        AcChangenameBinding inflate = AcChangenameBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.change_name));
    }

    public void mappingId() {
        this.rlErrorName = this.binding.rlErrorName;
        this.tvNameError = this.binding.tvNameError;
        this.etName = this.binding.etName;
        this.btNext = this.binding.btNext;
    }

    @Override
    protected void processData() {
        this.rxManager = new RxManager();
        this.walletName = getIntent().getStringExtra(TronConfig.WALLET_DATA);
        this.walletPos = getIntent().getIntExtra(TronConfig.WALLET_DATA2, -1);
        this.etName.setHint(this.walletName);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                etName.setFocusable(true);
                etName.setFocusableInTouchMode(true);
                etName.requestFocus();
                ((InputMethodManager) etName.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(etName, 0);
            }
        }, 1000L);
        this.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                showError(false);
                if (StringTronUtil.getText(etName).length() == 0) {
                    btNext.setEnabled(false);
                } else {
                    btNext.setEnabled(true);
                }
            }
        });
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    etName.setBackgroundResource(R.drawable.roundborder_232c41_6);
                } else {
                    etName.setBackgroundResource(R.drawable.roundborder_ebedf0_6);
                }
            }
        });
        AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.ENTER_WALLET_MANAGER_CHANGE_NAME_PAGE);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }

    public void setClick() {
        this.binding.btNext.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                try {
                    String trim = StringTronUtil.getTextTrimEnd(etName).trim();
                    if (walletName.equals(trim)) {
                        ToastSuc(R.string.success);
                        finish();
                    } else if (StringTronUtil.isEmpty(trim)) {
                        showError(true);
                        etName.setBackgroundResource(R.drawable.roundborder_ff5959);
                        tvNameError.setText(getString(R.string.error_null_name));
                    } else if (!StringTronUtil.validataLegalString2(trim) || trim.length() > 28) {
                        showError(true);
                        etName.setBackgroundResource(R.drawable.roundborder_ff5959);
                        tvNameError.setText(StringTronUtil.getResString(R.string.error_name2));
                    } else if (WalletUtils.existWallet(trim)) {
                        showError(true);
                        etName.setBackgroundResource(R.drawable.roundborder_ff5959);
                        tvNameError.setText(StringTronUtil.getResString(R.string.error_existwallet));
                    } else {
                        try {
                            WalletUtils.changeWalletName(walletName, trim);
                            rxManager.post(Event.SELECTEDWALLET, "111");
                        } catch (DuplicateNameException e) {
                            LogUtils.e(e);
                            tvNameError.setText(StringTronUtil.getResString(R.string.error_name2));
                        }
                        Intent intent = new Intent();
                        intent.putExtra(TronConfig.WALLET_DATA, trim);
                        intent.putExtra(TronConfig.WALLET_DATA2, walletPos);
                        setResult(-1, intent);
                        ToastSuc(R.string.success);
                        finish();
                    }
                } catch (InvalidNameException e2) {
                    LogUtils.e(e2);
                    showError(true);
                    etName.setBackgroundResource(R.drawable.roundborder_ff5959);
                    tvNameError.setText(StringTronUtil.getResString(R.string.error_name2));
                }
            }
        });
    }

    private void checkInputName() {
        try {
            Editable text = this.etName.getText();
            String trim = text.toString().trim();
            int selectionEnd = Selection.getSelectionEnd(text);
            int i = 0;
            for (int i2 = 0; i2 < trim.length(); i2++) {
                if (trim.charAt(i2) >= ' ') {
                }
                i++;
                if (i > 28) {
                    this.etName.setText(trim.substring(0, i2));
                    Editable text2 = this.etName.getText();
                    if (selectionEnd > text2.length()) {
                        selectionEnd = text2.length();
                    }
                    Selection.setSelection(text2, selectionEnd);
                }
            }
        } catch (Exception e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    public void showError(boolean z) {
        this.rlErrorName.setVisibility(z ? View.VISIBLE : View.GONE);
    }
}
