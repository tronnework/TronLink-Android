package com.tron.wallet.business.walletmanager.backup;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.backup.BackupContract;
import com.tron.wallet.business.walletmanager.backup.record.BackupHistoryManager;
import com.tron.wallet.common.components.BackupItemView;
import com.tron.wallet.common.components.dialog.CommonNoticeDialog;
import com.tron.wallet.common.components.dialog.MnemonicQRDialog;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcBackkeystoreBinding;
import com.tron.wallet.databinding.ItemBackupTip1Binding;
import com.tron.wallet.databinding.ItemBackupTip2Binding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import org.tron.walletserver.Wallet;
public class BackKeystoreActivity extends BaseActivity<BackPresenter, EmptyModel> implements BackupContract.View, BackupItemView.IOnClickListener {
    private AcBackkeystoreBinding binding;
    Button btnDone;
    private CommonNoticeDialog dialog;
    private boolean isShieldManage;
    BackupItemView itemPri;
    ImageView ivWarningBack;
    View llContent;
    View llTip1;
    View llTip2;
    TextView tvTip;
    TextView tvTip2_1;
    TextView tvTipTitle;
    private Wallet wallet;

    @Override
    protected boolean keepSecureFlag() {
        return true;
    }

    @Override
    protected void setLayout() {
        AcBackkeystoreBinding inflate = AcBackkeystoreBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        ItemBackupTip1Binding itemBackupTip1Binding = this.binding.itemBackupTip1;
        ItemBackupTip2Binding itemBackupTip2Binding = this.binding.itemBackupTip2;
        setView(root, 3);
        mappingId(itemBackupTip1Binding, itemBackupTip2Binding);
        setHeaderBar(getString(R.string.back_keystore));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId(ItemBackupTip1Binding itemBackupTip1Binding, ItemBackupTip2Binding itemBackupTip2Binding) {
        this.itemPri = this.binding.backPri;
        this.ivWarningBack = (ImageView) this.binding.backPri.findViewById(R.id.iv_warning_background);
        this.llContent = this.binding.backPri.findViewById(R.id.ll_content);
        this.llTip1 = itemBackupTip1Binding.llTip1;
        this.llTip2 = itemBackupTip2Binding.llTip2;
        this.tvTipTitle = itemBackupTip2Binding.tvKeepTitle;
        this.tvTip2_1 = itemBackupTip2Binding.tvTip21;
        this.tvTip = itemBackupTip2Binding.tvTip;
        this.btnDone = this.binding.btnDone;
    }

    @Override
    protected void processData() {
        getWindow().setFlags(8192, 8192);
        this.isShieldManage = getIntent().getBooleanExtra(TronConfig.SHIELD_IS_TRC20, false);
        showLoadingPage();
        final String stringExtra = getIntent().getStringExtra(TronConfig.WALLET_DATA);
        final String stringExtra2 = getIntent().getStringExtra(TronConfig.WALLET_PASSWORD);
        this.itemPri.setIOnClickListener(this);
        ViewGroup.LayoutParams layoutParams = this.llContent.getLayoutParams();
        layoutParams.height = UIUtils.dip2px(260.0f);
        this.llContent.setLayoutParams(layoutParams);
        this.ivWarningBack.setImageDrawable(getDrawable(R.mipmap.ic_backup_keystore));
        ViewGroup.LayoutParams layoutParams2 = this.ivWarningBack.getLayoutParams();
        layoutParams2.height = UIUtils.dip2px(360.0f);
        this.ivWarningBack.setLayoutParams(layoutParams2);
        this.tvTipTitle.setText(getResources().getString(R.string.keep_keystore));
        this.tvTip2_1.setText(getResources().getString(R.string.backup_keystore_tip1));
        this.tvTip.setText(String.format(getString(R.string.create_wallet_hint_8), getString(R.string.keystore)));
        this.btnDone.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((BackPresenter) mPresenter).onBackupFinish();
            }
        });
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public final void doOnBackground() {
                lambda$processData$1(stringExtra, stringExtra2);
            }
        });
        AnalyticsHelper.logEvent(AnalyticsHelper.WalletManagerPage.ENTER_WALLET_MANAGER_BACKUP_KEYSTORE_PAGE);
    }

    public void lambda$processData$1(String str, String str2) {
        try {
            this.wallet = WalletUtils.getWallet(str, str2);
            AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                @Override
                public final void doInUIThread() {
                    lambda$processData$0();
                }
            });
        } catch (Exception e) {
            dismissLoadingDialog();
            ToastError(R.string.error_password);
            finish();
            LogUtils.e(e);
        }
    }

    public void lambda$processData$0() {
        this.itemPri.updateContentText(this.wallet.getKeyStore());
        dismissLoadingPage();
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    private void showDialog() {
        CommonNoticeDialog cancleBt = new CommonNoticeDialog(this.mContext).setTitle(R.string.risk_warning).setContent(R.string.risk_warning_keystore).setBtListener(R.string.copy_continue, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.copy(wallet.getKeyStore(), true);
                itemPri.updateTips(true);
                dialog.dismiss();
            }
        }).setCancleBt(R.string.copy_no, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        this.dialog = cancleBt;
        cancleBt.show();
    }

    @Override
    public void onClickQrCode(int i, String str, BackupItemView backupItemView) {
        Bitmap createQrBitmap = ((BackPresenter) this.mPresenter).createQrBitmap(this, str);
        if (createQrBitmap == null) {
            return;
        }
        showQrCodeDialog(createQrBitmap);
    }

    @Override
    public void onClickCopy(int i, String str, BackupItemView backupItemView) {
        if (backupItemView.getCopied()) {
            return;
        }
        showDialog();
    }

    @Override
    public void onWarningDialogDismiss() {
        this.llTip1.setVisibility(View.GONE);
        this.llTip2.setVisibility(View.VISIBLE);
        this.btnDone.setEnabled(true);
        BackupHistoryManager.getInstance().addNewBackupRecord(2, this.isShieldManage);
    }

    private void showQrCodeDialog(Bitmap bitmap) {
        MnemonicQRDialog mnemonicQRDialog = new MnemonicQRDialog(this, bitmap);
        mnemonicQRDialog.setTitle(R.string.keystore_qrcode);
        mnemonicQRDialog.setCanceledOnTouchOutside(true);
        mnemonicQRDialog.show();
    }
}
