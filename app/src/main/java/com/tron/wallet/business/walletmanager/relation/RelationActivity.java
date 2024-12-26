package com.tron.wallet.business.walletmanager.relation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.arasthel.asyncjob.AsyncJob;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.reminder.BackupReminder;
import com.tron.wallet.business.walletmanager.createaccount.CreateAccountActivity;
import com.tron.wallet.business.walletmanager.relation.RelationAccountAdapter;
import com.tron.wallet.business.walletmanager.relation.RelationActivity;
import com.tron.wallet.business.walletmanager.relation.RelationContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.components.dialog.Common2Dialog;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.OnIClickListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PasswordInputUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcRelationBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.tron.net.CipherException;
import org.tron.walletserver.Wallet;
public class RelationActivity extends BaseActivity<RelationPresenter, RelationModel> implements RelationContract.View {
    RelationAccountAdapter accountAdapter;
    private AcRelationBinding binding;
    View btCreate;
    private Common2Dialog common2Dialog;
    private boolean isShieldManage;
    private ImageView ivCommonRight;
    RecyclerView mRecyclerView;
    private String mWalletName;
    View noDataView;
    private String password;
    private int pwdType;
    private int shield_type;

    @Override
    public boolean isShieldManage() {
        return this.isShieldManage;
    }

    @Override
    protected void setLayout() {
        AcRelationBinding inflate = AcRelationBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.mRecyclerView = this.binding.list;
        this.noDataView = this.binding.noDataView;
        this.btCreate = this.binding.btCreate;
        this.ivCommonRight = this.binding.ivCommonRight;
    }

    @Override
    public void onCreate1(Bundle bundle) {
        super.onCreate1(bundle);
        if (Build.VERSION.SDK_INT == 26 || Build.VERSION.SDK_INT == 27) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(1);
        }
    }

    @Override
    protected void processData() {
        this.ivCommonRight.setOnClickListener(getOnClickListener());
        this.btCreate.setOnClickListener(getOnClickListener());
        Intent intent = getIntent();
        boolean z = false;
        if (intent != null) {
            this.isShieldManage = intent.getBooleanExtra(TronConfig.SHIELD_IS_TRC20, false);
            this.mWalletName = intent.getStringExtra(TronConfig.WALLET_NAME);
            z = intent.getBooleanExtra(TronConfig.WALLET_DATA, false);
        }
        ((RelationPresenter) this.mPresenter).getRelationWallet(this.mWalletName);
        ((RelationPresenter) this.mPresenter).getData();
        if (z) {
            this.btCreate.setVisibility(View.GONE);
        }
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int id = view.getId();
            if (id != R.id.bt_create) {
                if (id != R.id.iv_common_right) {
                    return;
                }
                AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_CLOSE);
                finish();
                return;
            }
            AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_GENERATE);
            Wallet wallet = WalletUtils.getWallet(mWalletName);
            if (wallet == null) {
                return;
            }
            if (!BackupReminder.isWalletBackup(wallet)) {
                BackupReminder.showBackupPopup(mContext, wallet, BackupReminder.FROM_WALLET_MANAGER, getString(R.string.wallet_not_backup_fail_to_create_new), new BackupReminder.onActionListener() {
                    @Override
                    public void onClickCancel(Wallet wallet2) {
                    }

                    @Override
                    public void onClickConfirm(Wallet wallet2) {
                        finish();
                    }
                });
                return;
            }
            pwdType = 2;
            showDialog(new OnIClickListener() {
                @Override
                public final void onClick() {
                    RelationActivity.1.this.lambda$onNoDoubleClick$2();
                }
            });
        }

        public void lambda$onNoDoubleClick$2() {
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public final void doOnBackground() {
                    RelationActivity.1.this.lambda$onNoDoubleClick$1();
                }
            });
        }

        public void lambda$onNoDoubleClick$1() {
            try {
                final Wallet wallet = WalletUtils.getWallet(mWalletName, password);
                AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                    @Override
                    public final void doInUIThread() {
                        RelationActivity.1.this.lambda$onNoDoubleClick$0(wallet);
                    }
                });
            } catch (Exception e) {
                dismissLoadingDialog();
                PasswordInputUtils.updatePwdInput(mContext, mWalletName, pwdType);
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
        }

        public void lambda$onNoDoubleClick$0(Wallet wallet) {
            String str;
            dismissLoadingDialog();
            if (wallet.getECKey() == null || wallet.getECKey().getPrivKeyBytes() == null) {
                PasswordInputUtils.updatePwdInput(mContext, wallet.getWalletName(), pwdType);
                return;
            }
            PasswordInputUtils.updateSuccessPwd(mContext, wallet.getWalletName(), pwdType);
            try {
                str = WalletUtils.mnemonic(wallet.getWalletName(), password);
            } catch (IOException e) {
                LogUtils.e(e);
                str = null;
                CreateAccountActivity.start(RelationActivity.this, str, wallet.getWalletName(), password, isShieldManage);
            } catch (CipherException e2) {
                LogUtils.e(e2);
                str = null;
                CreateAccountActivity.start(RelationActivity.this, str, wallet.getWalletName(), password, isShieldManage);
            }
            CreateAccountActivity.start(RelationActivity.this, str, wallet.getWalletName(), password, isShieldManage);
        }
    }

    private View.OnClickListener getOnClickListener() {
        return new fun1();
    }

    @Override
    public void updateUi(final List<Wallet> list, Map<String, AccountBalanceOutput.DataBean.BalanceListBean> map) {
        this.accountAdapter = new RelationAccountAdapter(this, list, map, WalletUtils.getWallet(this.mWalletName));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setAdapter(this.accountAdapter);
        this.accountAdapter.setOnItemClickLitener(new RelationAccountAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_SELECT);
                Intent intent = new Intent();
                intent.putExtra(TronConfig.WALLET_DATA, ((Wallet) list.get(i)).getWalletName());
                setResult(-1, intent);
                finish();
            }
        });
        this.accountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                lambda$updateUi$0(list, baseQuickAdapter, view, i);
            }
        });
        Iterator<Wallet> it = list.iterator();
        int i = 0;
        while (it.hasNext() && !this.mWalletName.equals(it.next().getWalletName())) {
            i++;
        }
        this.mRecyclerView.scrollToPosition(i);
        this.noDataView.setVisibility(list.size() > 1 ? View.GONE : View.VISIBLE);
    }

    public void lambda$updateUi$0(List list, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        try {
            if (view.getId() != R.id.iv_copy) {
                return;
            }
            AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.CLICK_WALLET_MANAGER_PAGE_RELATED_ACCOUNT_COPY);
            UIUtils.copy(((Wallet) list.get(i)).getAddress());
            toast(getString(R.string.already_copy));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void showDialog(final OnIClickListener onIClickListener) {
        final Wallet wallet = WalletUtils.getWallet(this.mWalletName);
        if (wallet == null) {
            return;
        }
        this.common2Dialog = new Common2Dialog(this.mContext).setTitle(getString(R.string.wallet_manager_enter_password)).setInnerTitle("").setBtListener(getString(R.string.ok2), new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showDialog$1(wallet, onIClickListener, view);
            }
        });
        if (!wallet.isWatchOnly()) {
            this.common2Dialog.addEditext();
        }
        this.common2Dialog.show();
    }

    public void lambda$showDialog$1(Wallet wallet, OnIClickListener onIClickListener, View view) {
        this.password = this.common2Dialog.getEditextText();
        this.common2Dialog.dismiss();
        showLoadingDialog();
        if (wallet.isWatchOnly()) {
            dismissLoadingDialog();
            onIClickListener.onClick();
        } else if (TextUtils.isEmpty(this.password)) {
            dismissLoadingDialog();
            ToastError(R.string.isnull);
        } else if (PasswordInputUtils.canPwdInput(this.mContext, wallet.getWalletName(), this.pwdType)) {
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
}
