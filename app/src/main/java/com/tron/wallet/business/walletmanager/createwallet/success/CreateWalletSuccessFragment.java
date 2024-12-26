package com.tron.wallet.business.walletmanager.createwallet.success;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.databinding.ContentSecurityWalletBinding;
public class CreateWalletSuccessFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private ContentSecurityWalletBinding binding;

    public void mappingId() {
    }

    @Override
    protected void processData() {
    }

    public static CreateWalletSuccessFragment getInstance(String str) {
        CreateWalletSuccessFragment createWalletSuccessFragment = new CreateWalletSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TronConfig.WALLET_extra, str);
        createWalletSuccessFragment.setArguments(bundle);
        return createWalletSuccessFragment;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ContentSecurityWalletBinding inflate = ContentSecurityWalletBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        return inflate.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}
