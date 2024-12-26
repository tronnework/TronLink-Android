package com.tron.wallet.business.walletmanager.backupmnemonic.fg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.walletmanager.backup.record.BackupHistoryManager;
import com.tron.wallet.business.walletmanager.backupmnemonic.BackUpMnemonicActivity;
import com.tron.wallet.business.walletmanager.backupmnemonic.Page;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.FgBackupMnemonicShadeBinding;
import com.tronlinkpro.wallet.R;
public class ShowMnemonicPreviousFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgBackupMnemonicShadeBinding binding;
    Button btShow;
    private boolean isFromCreate;
    private boolean isShieldManage;
    TextView textView;

    public static ShowMnemonicPreviousFragment getInstance(boolean z) {
        ShowMnemonicPreviousFragment showMnemonicPreviousFragment = new ShowMnemonicPreviousFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_show_timeline_view", z);
        showMnemonicPreviousFragment.setArguments(bundle);
        return showMnemonicPreviousFragment;
    }

    @Override
    protected void processData() {
        this.isShieldManage = getArguments().getBoolean(TronConfig.SHIELD_IS_TRC20, false);
        this.isFromCreate = getArguments().getBoolean("is_show_timeline_view", false);
        this.textView.setText(String.format(getResources().getString(R.string.create_wallet_hint_6), getResources().getString(R.string.mnemonic)));
        this.btShow.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                ((BackUpMnemonicActivity) getActivity()).JumpTo(Page.BackUpShow);
                BackupHistoryManager.getInstance().addNewBackupRecord(0, isShieldManage, isFromCreate);
            }
        });
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgBackupMnemonicShadeBinding inflate = FgBackupMnemonicShadeBinding.inflate(layoutInflater, viewGroup, false);
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
        this.textView = this.binding.tvCenter;
        this.btShow = this.binding.btShow;
    }
}
