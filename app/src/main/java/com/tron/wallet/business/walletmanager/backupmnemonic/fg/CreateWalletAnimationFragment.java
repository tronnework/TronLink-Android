package com.tron.wallet.business.walletmanager.backupmnemonic.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.walletmanager.backupmnemonic.BackUpMnemonicActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.Page;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.FgBackupMnemonicAnimationBinding;
import com.tronlinkpro.wallet.R;
public class CreateWalletAnimationFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgBackupMnemonicAnimationBinding binding;
    Button btBackup;
    SimpleDraweeViewGif gifView;

    @Override
    protected void processData() {
        this.gifView.setGif(R.drawable.gif_backup, 1);
        this.btBackup.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((BackUpMnemonicActivity) getActivity()).JumpTo(Page.BackUpShowPrevious);
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgBackupMnemonicAnimationBinding inflate = FgBackupMnemonicAnimationBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.gifView = this.binding.gifView;
        this.btBackup = this.binding.btBackup;
    }
}
