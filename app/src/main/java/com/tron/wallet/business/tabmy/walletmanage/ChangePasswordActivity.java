package com.tron.wallet.business.tabmy.walletmanage;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmy.walletmanage.ChangePasswordActivity;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcChangepasswordBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class ChangePasswordActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcChangepasswordBinding binding;
    private String confirmPassword;
    ErrorEdiTextLayout errorConfirmPassword;
    ErrorEdiTextLayout errorNewpassword;
    ErrorEdiTextLayout errorOldpassword;
    EditText etConfirmPassword;
    EditText etNewpassword;
    EditText etOldpassword;
    ImageView ivConfirm;
    private String newpassword;
    Button ok;
    private String oldPassword;
    private String walletName;

    @Override
    protected void setLayout() {
        AcChangepasswordBinding inflate = AcChangepasswordBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 1);
        setHeaderBar(getString(R.string.change_password));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.etOldpassword = this.binding.etOldpassword;
        this.etNewpassword = this.binding.etNewpassword;
        this.etConfirmPassword = this.binding.etConfirmPassword;
        this.ok = this.binding.ok;
        this.errorOldpassword = this.binding.errorOldpassword;
        this.errorNewpassword = this.binding.errorNewpassword;
        this.errorConfirmPassword = this.binding.errorConfirmPassword;
        this.ivConfirm = this.binding.ivConfirm;
    }

    @Override
    protected void processData() {
        this.walletName = getIntent().getStringExtra(TronConfig.WALLET_DATA);
        textChanged();
        AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.ENTER_WALLET_MANAGER_CHANGE_PASSWORD_PAGE);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            ChangePasswordActivity changePasswordActivity = ChangePasswordActivity.this;
            changePasswordActivity.oldPassword = StringTronUtil.getText(changePasswordActivity.etOldpassword);
            ChangePasswordActivity changePasswordActivity2 = ChangePasswordActivity.this;
            changePasswordActivity2.newpassword = StringTronUtil.getText(changePasswordActivity2.etNewpassword);
            ChangePasswordActivity changePasswordActivity3 = ChangePasswordActivity.this;
            changePasswordActivity3.confirmPassword = StringTronUtil.getText(changePasswordActivity3.etConfirmPassword);
            if (StringTronUtil.isEmpty(oldPassword, newpassword, confirmPassword)) {
                ToastError(R.string.isnull);
            } else if (StringTronUtil.isEmpty(oldPassword)) {
                errorOldpassword.showError();
                errorOldpassword.setTextError(getString(R.string.error_password));
            } else if (!StringTronUtil.isOkPasswordTwo(newpassword)) {
                ivConfirm.setVisibility(View.VISIBLE);
                errorNewpassword.showError();
            } else if (!StringTronUtil.isOkPasswordTwo(confirmPassword) || !newpassword.equals(confirmPassword)) {
                errorConfirmPassword.showError();
                errorConfirmPassword.setTextError(getString(R.string.error_disagree));
            } else {
                showLoadingDialog();
                AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                    @Override
                    public final void doOnBackground() {
                        ChangePasswordActivity.1.this.lambda$onNoDoubleClick$4();
                    }
                });
            }
        }

        public void lambda$onNoDoubleClick$4() {
            ChangePasswordActivity changePasswordActivity = ChangePasswordActivity.this;
            if (PasswordInputUtils.canPwdInput(changePasswordActivity, changePasswordActivity.walletName, 10)) {
                try {
                    Wallet wallet = WalletUtils.getWallet(walletName, oldPassword);
                    if (wallet.getECKey() != null && wallet.getECKey().getPrivKeyBytes() != null) {
                        WalletUtils.changePassword(wallet, newpassword, oldPassword);
                        ChangePasswordActivity changePasswordActivity2 = ChangePasswordActivity.this;
                        PasswordInputUtils.updateSuccessPwd(changePasswordActivity2, changePasswordActivity2.walletName, 10);
                        mFirebaseAnalytics.logEvent("Change_Wallet_Password", null);
                        runOnUiThread(new Runnable() {
                            @Override
                            public final void run() {
                                ChangePasswordActivity.1.this.lambda$onNoDoubleClick$0();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public final void run() {
                                ChangePasswordActivity.1.this.lambda$onNoDoubleClick$1();
                            }
                        });
                    }
                    return;
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public final void run() {
                            ChangePasswordActivity.1.this.lambda$onNoDoubleClick$2(e);
                        }
                    });
                    return;
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    ChangePasswordActivity.1.this.lambda$onNoDoubleClick$3();
                }
            });
        }

        public void lambda$onNoDoubleClick$0() {
            setResult(-1, new Intent());
            ToastSuc(R.string.success2);
            finish();
        }

        public void lambda$onNoDoubleClick$1() {
            dismissLoadingDialog();
            errorNewpassword.showError();
            errorNewpassword.setTextError(getString(R.string.error_password));
            ChangePasswordActivity changePasswordActivity = ChangePasswordActivity.this;
            PasswordInputUtils.updatePwdInput(changePasswordActivity, changePasswordActivity.walletName, 10);
        }

        public void lambda$onNoDoubleClick$2(Exception exc) {
            dismissLoadingDialog();
            errorOldpassword.showError();
            errorOldpassword.setTextError(getString(R.string.error_password));
            ChangePasswordActivity changePasswordActivity = ChangePasswordActivity.this;
            PasswordInputUtils.updatePwdInput(changePasswordActivity, changePasswordActivity.walletName, 10);
            LogUtils.e(exc);
        }

        public void lambda$onNoDoubleClick$3() {
            dismissLoadingDialog();
            ChangePasswordActivity changePasswordActivity = ChangePasswordActivity.this;
            changePasswordActivity.toast(changePasswordActivity.mContext.getString(R.string.password_input_error));
        }
    }

    public void setClick() {
        this.binding.ok.setOnClickListener(new fun1());
    }

    public void textChanged() {
        this.etOldpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                errorOldpassword.hideError();
                buttonChanged();
            }
        });
        this.etOldpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                lambda$textChanged$0(view, z);
            }
        });
        this.etNewpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                errorNewpassword.hideError();
                buttonChanged();
            }
        });
        this.etNewpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public final void onFocusChange(View view, boolean z) {
                lambda$textChanged$1(view, z);
            }
        });
        this.etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                try {
                    ChangePasswordActivity changePasswordActivity = ChangePasswordActivity.this;
                    changePasswordActivity.confirmPassword = StringTronUtil.getText(changePasswordActivity.etConfirmPassword);
                    if (confirmPassword.length() == 0) {
                        ivConfirm.setVisibility(View.INVISIBLE);
                        errorConfirmPassword.hideError();
                    }
                    if (StringTronUtil.getText(etConfirmPassword).length() > 1 && StringTronUtil.getText(etNewpassword).length() > 1) {
                        if (StringTronUtil.isOkPasswordTwo(confirmPassword) && newpassword != null && newpassword.equals(confirmPassword)) {
                            errorConfirmPassword.hideError();
                            ivConfirm.setVisibility(View.VISIBLE);
                            ivConfirm.setBackgroundResource(R.mipmap.ic_right);
                        } else {
                            ivConfirm.setVisibility(View.GONE);
                        }
                    }
                    buttonChanged();
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                }
            }
        });
    }

    public void lambda$textChanged$0(View view, boolean z) {
        if (z) {
            return;
        }
        String text = StringTronUtil.getText(this.etOldpassword);
        this.oldPassword = text;
        if (StringTronUtil.isEmpty(text)) {
            this.errorOldpassword.showError();
            this.errorOldpassword.setTextError(getString(R.string.error_password3));
        }
    }

    public void lambda$textChanged$1(View view, boolean z) {
        if (z) {
            return;
        }
        String text = StringTronUtil.getText(this.etNewpassword);
        this.newpassword = text;
        if (StringTronUtil.isOkPasswordTwo(text)) {
            return;
        }
        this.errorNewpassword.showError();
    }

    public void buttonChanged() {
        if (!StringTronUtil.isEmpty(StringTronUtil.getText(this.etOldpassword)) && !StringTronUtil.isEmpty(StringTronUtil.getText(this.etNewpassword)) && !StringTronUtil.isEmpty(StringTronUtil.getText(this.etConfirmPassword))) {
            this.ok.setEnabled(true);
        } else {
            this.ok.setEnabled(false);
        }
    }
}
