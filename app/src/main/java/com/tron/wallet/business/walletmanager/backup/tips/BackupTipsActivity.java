package com.tron.wallet.business.walletmanager.backup.tips;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.backup.BackKeystoreActivity;
import com.tron.wallet.business.walletmanager.backup.BackPrivateKeyActivity;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordModel;
import com.tron.wallet.business.walletmanager.backup.tips.BackupTipsActivity;
import com.tron.wallet.business.walletmanager.backup.tips.BackupTipsContract;
import com.tron.wallet.business.walletmanager.backupmnemonic.BackUpMnemonicActivity;
import com.tron.wallet.common.components.dialog.Common2Dialog;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.OnIClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AcBackupTipsBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.common.utils.ByteArray;
import org.tron.walletserver.Wallet;
public class BackupTipsActivity extends BaseActivity<BackupTipsPresenter, BackupRecordModel> implements BackupTipsContract.View {
    public static String BACK_TYPE = "back_type";
    public static final String IS_SHOW_TIMELINE_VIEW = "is_show_timeline_view";
    public static String WALLET_NAME = "wallet_name";
    private Wallet acWallet;
    int backupType;
    private AcBackupTipsBinding binding;
    TextView btnNext;
    CheckBox checkBoxNoMore;
    private Common2Dialog common2Dialog;
    private Intent intent;
    private boolean isFromCreate;
    private boolean isNoMoreChecked;
    private boolean isShieldManage;
    private String mWalletName;
    private String password;
    private int pwdType;
    TextView tvAnswerContent1;
    TextView tvAnswerContent2;
    TextView tvAnswerContent3;
    TextView tvAnswerTitle;
    TextView tvWhatContent;
    TextView tvWhatTitle;

    public static void start(Context context, int i, String str) {
        start(context, i, str, false);
    }

    public static void start(Context context, int i, String str, boolean z) {
        Intent intent = new Intent(context, BackupTipsActivity.class);
        intent.putExtra(BACK_TYPE, i);
        intent.putExtra(WALLET_NAME, str);
        intent.putExtra("is_show_timeline_view", z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcBackupTipsBinding inflate = AcBackupTipsBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setView(root, 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvWhatTitle = this.binding.whatTitle;
        this.tvWhatContent = this.binding.whatTitleContent;
        this.tvAnswerTitle = this.binding.answerTitle;
        this.tvAnswerContent1 = this.binding.answerTitleContent1;
        this.tvAnswerContent2 = this.binding.answerTitleContent2;
        this.tvAnswerContent3 = this.binding.answerTitleContent3;
        this.checkBoxNoMore = this.binding.noMore;
        this.btnNext = this.binding.btnNext;
    }

    @Override
    protected void processData() {
        setTitleAndContent();
    }

    private void setTitleAndContent() {
        this.backupType = getIntent().getIntExtra(BACK_TYPE, -1);
        this.isShieldManage = getIntent().getBooleanExtra(TronConfig.SHIELD_IS_TRC20, false);
        this.isFromCreate = getIntent().getBooleanExtra("is_show_timeline_view", false);
        String stringExtra = getIntent().getStringExtra(WALLET_NAME);
        this.mWalletName = stringExtra;
        this.acWallet = WalletUtils.getWallet(stringExtra);
        this.tvAnswerTitle.setText(getString(R.string.learn_title_two));
        this.tvAnswerContent1.setText(getString(R.string.learn_title_two_content_one));
        this.tvAnswerContent2.setText(getString(R.string.learn_title_two_content_two));
        this.tvAnswerContent3.setText(getString(R.string.learn_title_two_content_three));
        this.checkBoxNoMore.setChecked(false);
        this.checkBoxNoMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                isNoMoreChecked = z;
                int i = backupType;
                if (i == 0) {
                    SpAPI.THIS.setIsCheckedNoMoreShow(SpAPI.SET_TIPS_NO_MORE_SHOW_MNEMONIC, isNoMoreChecked);
                } else if (i == 1) {
                    SpAPI.THIS.setIsCheckedNoMoreShow(SpAPI.SET_TIPS_NO_MORE_SHOW_PRIVATE_KEY, isNoMoreChecked);
                } else if (i != 2) {
                } else {
                    SpAPI.THIS.setIsCheckedNoMoreShow(SpAPI.SET_TIPS_NO_MORE_SHOW_KEYSTORE, isNoMoreChecked);
                }
            }
        });
        int i = this.backupType;
        if (i == 0) {
            setHeaderBar(getString(R.string.mnemonic_learn_title));
            this.tvWhatTitle.setText(getString(R.string.mnemonic_learn_detail_title));
            this.tvWhatContent.setText(getString(R.string.mnemonic_learn_content));
            this.checkBoxNoMore.setText(getString(R.string.mnemonic_learn_is_show));
            this.btnNext.setText(getString(R.string.mnemonic_learn_bottom_title));
        } else if (i == 1) {
            setHeaderBar(getString(R.string.private_key_learn_title));
            this.tvWhatTitle.setText(getString(R.string.private_key_learn_detail_title));
            this.tvWhatContent.setText(getString(R.string.private_key_learn_content));
            this.checkBoxNoMore.setText(getString(R.string.private_key_learn_is_show));
            this.btnNext.setText(getString(R.string.private_key_learn_bottom_title));
        } else if (i == 2) {
            setHeaderBar(getString(R.string.keystore_learn_title));
            this.tvWhatTitle.setText(getString(R.string.keystore_learn_detail_title));
            this.tvWhatContent.setText(getString(R.string.keystore_learn_content));
            this.checkBoxNoMore.setText(getString(R.string.keystore_learn_is_show));
            this.btnNext.setText(getString(R.string.keystore_learn_bottom_title));
        }
        this.btnNext.setOnClickListener(new fun2());
    }

    public class fun2 extends NoDoubleClickListener {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int i = backupType;
            if (i == 0) {
                pwdType = 4;
                showDialog(new OnIClickListener() {
                    @Override
                    public final void onClick() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$3();
                    }
                });
            } else if (i == 1) {
                pwdType = 3;
                showDialog(new OnIClickListener() {
                    @Override
                    public final void onClick() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$7();
                    }
                });
            } else if (i != 2) {
            } else {
                pwdType = 2;
                showDialog(new OnIClickListener() {
                    @Override
                    public final void onClick() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$10();
                    }
                });
            }
        }

        public void lambda$onNoDoubleClick$3() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public final void doOnBackground() {
                    BackupTipsActivity.2.this.lambda$onNoDoubleClick$2();
                }
            });
        }

        public void lambda$onNoDoubleClick$2() {
            try {
                final String mnemonic = WalletUtils.mnemonic(acWallet.getWalletName(), password);
                PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$0(mnemonic);
                    }
                });
            } catch (Exception e) {
                dismissLoadingDialog();
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$1();
                    }
                });
                LogUtils.e(e);
                SentryUtil.captureException(e);
            }
        }

        public void lambda$onNoDoubleClick$0(String str) {
            dismissLoadingDialog();
            mFirebaseAnalytics.logEvent("Backup_Mnemonic", null);
            BackUpMnemonicActivity.startFromCreateOrNoT(mContext, str, acWallet.getWalletName(), isFromCreate, false);
        }

        public void lambda$onNoDoubleClick$1() {
            PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
        }

        public void lambda$onNoDoubleClick$7() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public final void doOnBackground() {
                    BackupTipsActivity.2.this.lambda$onNoDoubleClick$6();
                }
            });
        }

        public void lambda$onNoDoubleClick$6() {
            try {
                final Wallet wallet = WalletUtils.getWallet(acWallet.getWalletName(), password);
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$4(wallet);
                    }
                });
            } catch (Exception e) {
                dismissLoadingDialog();
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$5();
                    }
                });
                LogUtils.e(e);
            }
        }

        public void lambda$onNoDoubleClick$4(Wallet wallet) {
            dismissLoadingDialog();
            if (wallet.getECKey() != null && wallet.getECKey().getPrivKeyBytes() != null) {
                PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
                if (wallet.isShieldedWallet()) {
                    return;
                }
                intent = new Intent(mContext, BackPrivateKeyActivity.class);
                intent.putExtra(TronConfig.WALLET_DATA, acWallet.getWalletName());
                intent.putExtra(TronConfig.WALLET_extra, ByteArray.toHexString(wallet.getECKey().getPrivKeyBytes()));
                BackupTipsActivity backupTipsActivity = BackupTipsActivity.this;
                backupTipsActivity.go(backupTipsActivity.intent);
                return;
            }
            dismissLoadingDialog();
            PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
        }

        public void lambda$onNoDoubleClick$5() {
            PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
        }

        public void lambda$onNoDoubleClick$10() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public final void doOnBackground() {
                    BackupTipsActivity.2.this.lambda$onNoDoubleClick$9();
                }
            });
        }

        public void lambda$onNoDoubleClick$9() {
            try {
                final Wallet wallet = WalletUtils.getWallet(acWallet.getWalletName(), password);
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        BackupTipsActivity.2.this.lambda$onNoDoubleClick$8(wallet);
                    }
                });
            } catch (Exception e) {
                dismissLoadingDialog();
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
        }

        public void lambda$onNoDoubleClick$8(Wallet wallet) {
            dismissLoadingDialog();
            if (wallet.getECKey() == null || wallet.getECKey().getPrivKeyBytes() == null) {
                PasswordInputUtils.updatePwdInput(mContext, acWallet.getWalletName(), pwdType);
                return;
            }
            PasswordInputUtils.updateSuccessPwd(mContext, acWallet.getWalletName(), pwdType);
            intent = new Intent(mContext, BackKeystoreActivity.class);
            intent.putExtra(TronConfig.WALLET_DATA, acWallet.getWalletName());
            intent.putExtra(TronConfig.WALLET_PASSWORD, password);
            intent.putExtra(TronConfig.WALLET_extra, wallet.getKeyStore());
            intent.putExtra(TronConfig.SHIELD_IS_TRC20, isShieldManage);
            BackupTipsActivity backupTipsActivity = BackupTipsActivity.this;
            backupTipsActivity.go(backupTipsActivity.intent);
        }
    }

    public void showDialog(OnIClickListener onIClickListener) {
        showDialog(onIClickListener, false);
    }

    public void showDialog(final OnIClickListener onIClickListener, boolean z) {
        String string;
        Wallet wallet = this.acWallet;
        if (wallet == null) {
            return;
        }
        int createType = wallet.getCreateType();
        int i = R.string.delected_wallet;
        if (createType == 7) {
            string = getString(R.string.delected_samsung_wallet_desc);
        } else if (this.acWallet.isWatchOnly()) {
            if (z) {
                string = getString(R.string.deleted2);
            }
            string = "";
        } else {
            i = R.string.wallet_manager_enter_password;
            if (z) {
                string = getString(R.string.deleted2);
            }
            string = "";
        }
        this.common2Dialog = new Common2Dialog(this.mContext).setTitle(getString(i)).setInnerTitle(string).setBtListener(getString(R.string.ok2), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showDialog$0(onIClickListener, view);
            }
        });
        if (!this.acWallet.isWatchOnly()) {
            this.common2Dialog.addEditext();
        }
        this.common2Dialog.show();
    }

    public void lambda$showDialog$0(OnIClickListener onIClickListener, View view) {
        this.password = this.common2Dialog.getEditextText();
        this.common2Dialog.dismiss();
        showLoadingDialog();
        if (this.acWallet.isWatchOnly()) {
            dismissLoadingDialog();
            onIClickListener.onClick();
        } else if (TextUtils.isEmpty(this.password)) {
            dismissLoadingDialog();
            ToastError(R.string.isnull);
        } else if (PasswordInputUtils.canPwdInput(this.mContext, this.acWallet.getWalletName(), this.pwdType)) {
            if (!StringTronUtil.isEmpty(this.password)) {
                onIClickListener.onClick();
                return;
            }
            dismissLoadingDialog();
            ToastAsBottom(R.string.et_null);
        } else {
            ToastAsBottom(R.string.password_input_error);
            dismissLoadingDialog();
        }
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        exit();
    }
}
