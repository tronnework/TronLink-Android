package com.tron.wallet.business.walletmanager.input;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.createwallet.CreateWalletActivity;
import com.tron.wallet.business.walletmanager.input.TextChangedListener;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ContentInputWalletBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tron.walletserver.InvalidNameException;
public class InputWalletDetailFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    public static final int MAX_NAME_LENGTH = 28;
    private ContentInputWalletBinding binding;
    CheckBox cbFour;
    CheckBox cbOne;
    CheckBox cbThree;
    CheckBox cbTwo;
    private DataChangeListener dataChangeListener;
    CommonTitleDescriptionEditView inputWalletName;
    CommonTitleDescriptionEditView inputWalletPassword;
    CommonTitleDescriptionEditView inputWalletPasswordAgain;
    View llNameError;
    View llPasswordAgainError;
    private String password;
    private String passwordAgain;
    TextView tvFour;
    TextView tvNameError;
    TextView tvOne;
    TextView tvPasswordAgainError;
    TextView tvThree;
    TextView tvTwo;
    private String walletName;

    public void setOnDataChangeListener(DataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    public static InputWalletDetailFragment getInstance(String str) {
        InputWalletDetailFragment inputWalletDetailFragment = new InputWalletDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TronConfig.WALLET_extra, str);
        inputWalletDetailFragment.setArguments(bundle);
        return inputWalletDetailFragment;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ContentInputWalletBinding inflate = ContentInputWalletBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.inputWalletPassword = this.binding.inputWalletPassword;
        this.inputWalletName = this.binding.inputWalletName;
        this.inputWalletPasswordAgain = this.binding.inputWalletPasswordAgain;
        this.tvOne = this.binding.tvOne;
        this.cbOne = this.binding.cbOne;
        this.cbTwo = this.binding.cbTwo;
        this.tvTwo = this.binding.tvTwo;
        this.cbThree = this.binding.cbThree;
        this.tvThree = this.binding.tvThree;
        this.cbFour = this.binding.cbFour;
        this.tvFour = this.binding.tvFour;
        this.llNameError = this.binding.llNameError;
        this.tvNameError = this.binding.tvNameError;
        this.llPasswordAgainError = this.binding.llPasswordAgainError;
        this.tvPasswordAgainError = this.binding.tvPasswordAgainError;
    }

    @Override
    protected void processData() {
        setClearTextClick(this.inputWalletPasswordAgain);
        setClearTextClick(this.inputWalletPassword);
        setClearTextClick(this.inputWalletName);
        if (getArguments() != null) {
            this.inputWalletName.setText(getArguments().getString(TronConfig.WALLET_extra));
        }
        this.inputWalletName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    InputWalletDetailFragment inputWalletDetailFragment = InputWalletDetailFragment.this;
                    inputWalletDetailFragment.walletName = inputWalletDetailFragment.inputWalletName.getText().toString().trim();
                    if (TextUtils.isEmpty(walletName)) {
                        inputWalletName.setRightImageResId(0);
                        return;
                    } else {
                        inputWalletName.setRightImageResId(R.mipmap.input_clear);
                        return;
                    }
                }
                inputWalletName.setRightImageResId(0);
            }
        });
        addTextChangedListener(this.inputWalletName, new TextChangedListener() {
            @Override
            public final void afterTextChanged(Editable editable) {
                lambda$processData$0(editable);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$beforeTextChanged(this, charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$onTextChanged(this, charSequence, i, i2, i3);
            }
        });
        addTextChangedListener(this.inputWalletPassword, new TextChangedListener() {
            @Override
            public final void afterTextChanged(Editable editable) {
                lambda$processData$1(editable);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$beforeTextChanged(this, charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$onTextChanged(this, charSequence, i, i2, i3);
            }
        });
        addTextChangedListener(this.inputWalletPasswordAgain, new TextChangedListener() {
            @Override
            public final void afterTextChanged(Editable editable) {
                lambda$processData$2(editable);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$beforeTextChanged(this, charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$onTextChanged(this, charSequence, i, i2, i3);
            }
        });
        this.inputWalletPasswordAgain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                requestInputData();
                if (z) {
                    FragmentActivity activity = getActivity();
                    if (activity instanceof CreateWalletActivity) {
                        ((CreateWalletActivity) activity).expandedBar();
                    }
                }
            }
        });
    }

    public void lambda$processData$0(Editable editable) {
        this.walletName = editable.toString().trim();
        this.llNameError.setVisibility(View.GONE);
        if (TextUtils.isEmpty(this.walletName)) {
            showNameError(getResources().getString(R.string.error_name3));
            this.inputWalletName.setRightImageResId(0);
            backEmptyListener();
            return;
        }
        this.inputWalletName.setRightImageResId(R.mipmap.input_clear);
        checkInputName();
        backListener();
    }

    public void lambda$processData$1(Editable editable) {
        checkPasswordAgain();
        if (TextUtils.isEmpty(this.password)) {
            this.inputWalletPassword.setRightImageResId(0);
            backEmptyListener();
        } else {
            this.inputWalletPassword.setRightImageResId(R.mipmap.input_clear);
        }
        checkPassword(this.password);
        backListener();
    }

    public void lambda$processData$2(Editable editable) {
        this.llPasswordAgainError.setVisibility(View.GONE);
        checkPasswordAgain();
        if (TextUtils.isEmpty(this.passwordAgain)) {
            this.inputWalletPasswordAgain.setRightImageResId(0);
            backEmptyListener();
            return;
        }
        this.inputWalletPasswordAgain.setRightImageResId(R.mipmap.input_clear);
        backListener();
    }

    private void setClearTextClick(final CommonTitleDescriptionEditView commonTitleDescriptionEditView) {
        commonTitleDescriptionEditView.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
            @Override
            public void onRightDrawableClick(View view) {
                commonTitleDescriptionEditView.setText("");
                if (R.id.input_wallet_password == commonTitleDescriptionEditView.getId()) {
                    checkPassword("");
                }
            }
        });
    }

    private void addTextChangedListener(EditText editText, final TextChangedListener textChangedListener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                textChangedListener.beforeTextChanged(charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                textChangedListener.onTextChanged(charSequence, i, i2, i3);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                textChangedListener.afterTextChanged(editable);
            }
        });
    }

    public void backListener() {
        if (this.dataChangeListener == null) {
            return;
        }
        requestInputData();
        if (checkButton(this.walletName, this.password, this.passwordAgain)) {
            backDataListener();
        } else {
            backEmptyListener();
        }
    }

    private boolean checkButton(String str, String str2, String str3) {
        return !StringTronUtil.isEmpty(str2, str, str3) && StringTronUtil.isOkPasswordTwo(str2) && str3.equals(str2) && StringTronUtil.validataLegalString2(str) && !WalletUtils.existWallet(str);
    }

    private void backEmptyListener() {
        DataChangeListener dataChangeListener = this.dataChangeListener;
        if (dataChangeListener == null) {
            return;
        }
        dataChangeListener.onDataChangeListener(false, "", "");
    }

    private void backDataListener() {
        DataChangeListener dataChangeListener = this.dataChangeListener;
        if (dataChangeListener == null) {
            return;
        }
        dataChangeListener.onDataChangeListener(true, this.walletName, this.password);
    }

    private void checkPasswordAgain() {
        requestInputData();
        if (StringTronUtil.isEmpty(this.passwordAgain)) {
            this.llPasswordAgainError.setVisibility(View.GONE);
        } else if (!this.passwordAgain.equals(this.password) && !StringTronUtil.isEmpty(this.password) && this.passwordAgain.length() > this.password.length() / 2) {
            this.llPasswordAgainError.setVisibility(View.VISIBLE);
            this.tvPasswordAgainError.setText(R.string.no_same);
        } else {
            this.llPasswordAgainError.setVisibility(View.GONE);
        }
    }

    public void requestInputData() {
        String text = StringTronUtil.getText(this.inputWalletName);
        this.walletName = text;
        if (StringTronUtil.isEmpty(text)) {
            showNameError(getResources().getString(R.string.error_name3));
        } else {
            try {
                this.walletName = StringTronUtil.trimEnd(this.walletName.toCharArray());
            } catch (InvalidNameException e) {
                LogUtils.e(e);
                showNameError(getResources().getString(R.string.error_name2));
                this.walletName = "";
            }
        }
        this.password = StringTronUtil.getText(this.inputWalletPassword);
        this.passwordAgain = StringTronUtil.getText(this.inputWalletPasswordAgain);
    }

    private void checkInputName() {
        if (StringTronUtil.isEmpty(this.walletName)) {
            showNameError(getResources().getString(R.string.error_name3));
            return;
        }
        try {
            String trimEnd = StringTronUtil.trimEnd(this.walletName.toCharArray());
            this.walletName = trimEnd;
            if (StringTronUtil.isEmpty(trimEnd)) {
                showNameError(getResources().getString(R.string.error_name3));
            } else if (!StringTronUtil.validataLegalString2(this.walletName)) {
                showNameError(getResources().getString(R.string.error_name2));
            } else if (WalletUtils.existWallet(this.walletName)) {
                showNameError(getResources().getString(R.string.exist_wallet_name));
            } else {
                try {
                    Editable text = this.inputWalletName.getText();
                    String trim = text.toString().trim();
                    int selectionEnd = Selection.getSelectionEnd(text);
                    int i = 0;
                    for (int i2 = 0; i2 < trim.length(); i2++) {
                        if (trim.charAt(i2) >= ' ') {
                        }
                        i++;
                        if (i > 28) {
                            this.inputWalletName.setText(trim.substring(0, i2));
                            Editable text2 = this.inputWalletName.getText();
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
        } catch (InvalidNameException e2) {
            LogUtils.e(e2);
            showNameError(getResources().getString(R.string.error_name2));
        }
    }

    public void showNameError(String str) {
        this.llNameError.setVisibility(View.VISIBLE);
        this.tvNameError.setText(str);
    }

    public void checkPassword(String str) {
        Pattern compile = Pattern.compile(".*[A-Z]+.*");
        Pattern compile2 = Pattern.compile(".*[a-z]+.*");
        Pattern compile3 = Pattern.compile(".*[0-9]+.*");
        Matcher matcher = compile.matcher(str);
        Matcher matcher2 = compile2.matcher(str);
        Matcher matcher3 = compile3.matcher(str);
        if (matcher.matches()) {
            changeView(this.cbOne, this.tvOne, true);
        } else {
            changeView(this.cbOne, this.tvOne, false);
        }
        if (matcher2.matches()) {
            changeView(this.cbTwo, this.tvTwo, true);
        } else {
            changeView(this.cbTwo, this.tvTwo, false);
        }
        if (matcher3.matches()) {
            changeView(this.cbThree, this.tvThree, true);
        } else {
            changeView(this.cbThree, this.tvThree, false);
        }
        if (str.length() >= 8) {
            changeView(this.cbFour, this.tvFour, true);
        } else {
            changeView(this.cbFour, this.tvFour, false);
        }
    }

    public void changeView(CheckBox checkBox, TextView textView, boolean z) {
        if (z) {
            checkBox.setChecked(true);
            textView.setSelected(true);
            return;
        }
        checkBox.setChecked(false);
        textView.setSelected(false);
    }
}
