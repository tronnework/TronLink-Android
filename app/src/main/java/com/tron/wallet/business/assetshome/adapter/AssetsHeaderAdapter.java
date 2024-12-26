package com.tron.wallet.business.assetshome.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.business.addassets2.bean.asset.AssetsHomeData;
import com.tron.wallet.business.assetshome.listener.HiddenSwitchListener;
import com.tron.wallet.business.walletmanager.backup.record.BackupRecordBean;
import com.tronlinkpro.wallet.R;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class AssetsHeaderAdapter extends RecyclerView.Adapter<BaseHolder> {
    private HeaderHolder headerHolder;
    private Protocol.Account mAccount;
    private GrpcAPI.AccountResourceMessage mAccountResource;
    private BackupRecordBean mBackupRecordBean;
    private AssetsHomeData mData;
    private HiddenSwitchListener mHiddenChangedListener;
    private boolean mHidePrivacy;
    private Wallet mWallet;
    private View.OnClickListener onClickSwapListener;

    @Override
    public int getItemCount() {
        return 1;
    }

    public View.OnClickListener getSwapClickListener() {
        return this.onClickSwapListener;
    }

    public AssetsHeaderAdapter setHiddenChangedListener(boolean z, HiddenSwitchListener hiddenSwitchListener) {
        this.mHidePrivacy = z;
        this.mHiddenChangedListener = hiddenSwitchListener;
        return this;
    }

    public AssetsHeaderAdapter setSwapClickListener(View.OnClickListener onClickListener) {
        this.onClickSwapListener = onClickListener;
        return this;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        HeaderHolder headerHolder = new HeaderHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_assets_header, viewGroup, false));
        this.headerHolder = headerHolder;
        headerHolder.setHiddenChangedListener(this.mHidePrivacy, this.mHiddenChangedListener);
        return this.headerHolder;
    }

    @Override
    public void onBindViewHolder(BaseHolder baseHolder, int i) {
        Wallet wallet = this.mWallet;
        boolean z = (wallet == null || this.mData == null || this.mAccount == null || this.mAccountResource == null) ? false : true;
        if ((baseHolder instanceof HeaderHolder) && z) {
            ((HeaderHolder) baseHolder).bindHolder(wallet, this.mData, this.mAccount, this.mAccountResource, this.mHidePrivacy, this.mBackupRecordBean);
        }
    }

    public void updateUI(Wallet wallet, AssetsHomeData assetsHomeData, Protocol.Account account, GrpcAPI.AccountResourceMessage accountResourceMessage, boolean z) {
        this.mWallet = wallet;
        this.mData = assetsHomeData;
        this.mAccount = account;
        this.mHidePrivacy = z;
        this.mAccountResource = accountResourceMessage;
        notifyDataSetChanged();
    }

    public void updateWallet(Wallet wallet) {
        this.mWallet = wallet;
        notifyDataSetChanged();
    }

    public void updateLoadingPrice(boolean z) {
        HeaderHolder headerHolder = this.headerHolder;
        if (headerHolder != null) {
            headerHolder.showPriceLoading(z);
        }
    }

    public void updateHiddenState(boolean z) {
        this.mHidePrivacy = z;
        notifyDataSetChanged();
    }

    public void updateBackupTips(BackupRecordBean backupRecordBean) {
        this.mBackupRecordBean = backupRecordBean;
        notifyDataSetChanged();
    }
}
