package com.tron.wallet.business.tabmy.myhome.settings.unittest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.tabmy.myhome.settings.UnitTestActivity;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.databinding.AcWalletChangeTestBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import org.tron.walletserver.Wallet;
public class WalletChangeFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private AcWalletChangeTestBinding binding;
    View bt1;
    View bt2;
    private Wallet selectedPublicWallet;

    @Override
    protected void processData() {
        setHeaderBar("观察钱包转换");
        this.selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        this.bt1.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (selectedPublicWallet.isWatchNotPaired()) {
                    WalletUtils.updateWatchWalletType(selectedPublicWallet.getWalletName(), 9);
                    Toast("修改成功");
                }
            }
        });
        this.bt2.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (selectedPublicWallet.isWatchCold()) {
                    WalletUtils.updateWatchWalletType(selectedPublicWallet.getWalletName(), 4);
                    Toast("修改成功");
                }
            }
        });
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        if (getActivity() instanceof UnitTestActivity) {
            ((UnitTestActivity) getActivity()).onLeftButtonClick();
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AcWalletChangeTestBinding inflate = AcWalletChangeTestBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        setType(1);
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.bt1 = this.binding.bt1;
        this.bt2 = this.binding.bt2;
    }
}
