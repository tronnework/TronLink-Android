package com.tron.wallet.business.tabmy.allhistory;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.adapter.HistoryFragmentAdapter;
import com.tron.wallet.common.components.popupwindow.SelectedAccountWindow;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.interfaces.OnSeleted2Listener;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcAllHistoryBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import org.tron.walletserver.Wallet;
public class TrxAllHistoryActivity extends BaseActivity<EmptyPresenter, EmptyModel> {
    private AcAllHistoryBinding binding;
    private TrxAllHistoryFragment f1;
    private TrxAllHistoryFragment f2;
    private TrxAllHistoryFragment f3;
    private HistoryFragmentAdapter fragmentAdapter;
    private Wallet mWallet;
    LinearLayout root;
    private SelectedAccountWindow selectedAccountWindow;
    XTabLayoutV2 tabLayout;
    ViewPager2 vpContent;

    public ViewPager2 getVpContent() {
        return this.vpContent;
    }

    @Override
    protected void setLayout() {
        AcAllHistoryBinding inflate = AcAllHistoryBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        mappingId();
        setView(root, 1);
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        this.mWallet = selectedWallet;
        if (selectedWallet != null) {
            setHeaderBarAndRightImage(selectedWallet.getWalletName(), R.mipmap.ic_account);
        } else {
            setHeaderBarAndRightImage("", R.mipmap.ic_account);
        }
        try {
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(getHeaderHolder().ivQr.getLayoutParams());
            marginLayoutParams.setMargins(0, UIUtils.dip2px(16.0f), UIUtils.dip2px(16.0f), 0);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginLayoutParams);
            layoutParams.height = UIUtils.dip2px(26.0f);
            layoutParams.width = UIUtils.dip2px(26.0f);
            getHeaderHolder().ivQr.setLayoutParams(layoutParams);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void mappingId() {
        this.tabLayout = this.binding.tablayout;
        this.vpContent = this.binding.vpContent;
        this.root = this.binding.root;
    }

    @Override
    protected void processData() {
        Wallet wallet = this.mWallet;
        String address = wallet != null ? wallet.getAddress() : "";
        this.f1 = TrxAllHistoryFragment.newInstance(address, 0);
        this.f2 = TrxAllHistoryFragment.newInstance(address, 1);
        this.f3 = TrxAllHistoryFragment.newInstance(address, 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.f1);
        arrayList.add(this.f2);
        arrayList.add(this.f3);
        HistoryFragmentAdapter historyFragmentAdapter = new HistoryFragmentAdapter(this, arrayList);
        this.fragmentAdapter = historyFragmentAdapter;
        this.vpContent.setAdapter(historyFragmentAdapter);
        this.tabLayout.setupWithViewPager(this.vpContent);
        this.vpContent.setOffscreenPageLimit(2);
        this.vpContent.setCurrentItem(0);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onRightButtonClick() {
        super.onRightButtonClick();
        if (this.selectedAccountWindow == null) {
            this.selectedAccountWindow = new SelectedAccountWindow(this, new OnSeleted2Listener() {
                @Override
                public void onSeleted(Wallet wallet) {
                    mWallet = wallet;
                    if (mWallet != null) {
                        TrxAllHistoryActivity trxAllHistoryActivity = TrxAllHistoryActivity.this;
                        trxAllHistoryActivity.setHeaderBarAndRightImage(trxAllHistoryActivity.mWallet.getWalletName(), R.mipmap.ic_account);
                    }
                    selectedAccountWindow.dismiss();
                    if (mWallet != null) {
                        if (f1 != null) {
                            f1.onRefresh(mWallet.getAddress());
                        }
                        if (f2 != null) {
                            f2.onRefresh(mWallet.getAddress());
                        }
                        if (f3 != null) {
                            f3.onRefresh(mWallet.getAddress());
                        }
                    }
                }
            });
        }
        Wallet wallet = this.mWallet;
        if (wallet != null) {
            this.selectedAccountWindow.show(this.root, wallet.getWalletName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SelectedAccountWindow selectedAccountWindow = this.selectedAccountWindow;
        if (selectedAccountWindow != null && selectedAccountWindow.isShowing()) {
            this.selectedAccountWindow.dismiss();
        }
        this.binding = null;
    }
}
