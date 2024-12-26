package com.tron.wallet.business.walletmanager.backup.record;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.DateUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemBackupRecordBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.text.ParseException;
import java.util.List;
import org.tron.walletserver.Wallet;
public class BackupRecordAdapter extends BaseQuickAdapter<BackupRecordBean, BackupRecordViewHolder> {
    Context context;
    private boolean isShieldManage;
    boolean mNeedShowCurrentTag;
    List<BackupRecordBean> recordBeans;

    public BackupRecordAdapter(Context context, List<BackupRecordBean> list, boolean z, boolean z2) {
        super(R.layout.item_backup_record, list);
        this.context = context;
        this.recordBeans = list;
        this.mNeedShowCurrentTag = z;
        this.isShieldManage = z2;
    }

    @Override
    public void convert(BackupRecordViewHolder backupRecordViewHolder, BackupRecordBean backupRecordBean) {
        backupRecordViewHolder.onBind(this.context, backupRecordBean);
    }

    public class BackupRecordViewHolder extends BaseViewHolder {
        View currentTag;
        TextView tvAddress;
        TextView tvBackupType;
        TextView tvName;
        TextView tvStamp;
        TextView tvStampTitle;

        public BackupRecordViewHolder(View view) {
            super(view);
            ItemBackupRecordBinding bind = ItemBackupRecordBinding.bind(view);
            this.currentTag = bind.rlTag;
            this.tvBackupType = bind.tvNameType;
            this.tvName = bind.tvName;
            this.tvAddress = bind.tvAddress;
            this.tvStamp = bind.tvStamp;
            this.tvStampTitle = bind.tvStampTitle;
        }

        public void onBind(Context context, BackupRecordBean backupRecordBean) {
            int i = backupRecordBean.backupRecordTYpe;
            if (i == 0) {
                this.tvBackupType.setText(context.getResources().getString(R.string.back_up_mnemonic));
            } else if (i == 1) {
                this.tvBackupType.setText(context.getResources().getString(R.string.back_up_private_key));
            } else if (i == 2) {
                this.tvBackupType.setText(context.getResources().getString(R.string.back_up_keystore));
            }
            this.tvAddress.setText(backupRecordBean.getWalletAddress());
            Wallet selectedWallet = WalletUtils.getSelectedWallet();
            Wallet walletForAddress = WalletUtils.getWalletForAddress(backupRecordBean.getWalletAddress());
            if (walletForAddress == null || walletForAddress.isShieldedWallet()) {
                return;
            }
            if (mNeedShowCurrentTag && walletForAddress != null && selectedWallet != null && StringTronUtil.equals(selectedWallet.getAddress(), backupRecordBean.getWalletAddress())) {
                this.tvName.setText(selectedWallet.getWalletName());
                this.currentTag.setVisibility(View.VISIBLE);
            } else {
                if (walletForAddress != null) {
                    this.tvName.setText(walletForAddress.getWalletName());
                } else {
                    this.tvName.setText(backupRecordBean.getWalletName());
                }
                this.currentTag.setVisibility(View.GONE);
            }
            try {
                this.tvStamp.setText(DateUtils.longToString(backupRecordBean.getBackupStamp(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN));
                this.tvStamp.setVisibility(View.VISIBLE);
                this.tvStampTitle.setVisibility(View.VISIBLE);
            } catch (ParseException e) {
                this.tvStamp.setVisibility(View.GONE);
                this.tvStampTitle.setVisibility(View.GONE);
                LogUtils.e(e);
            }
        }
    }
}
