package com.tron.wallet.business.nft;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.nft.contract.NftHistoryContract;
import com.tron.wallet.business.nft.contract.NftHistoryPresenter;
import com.tron.wallet.business.transfer.TokenDetailModel;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.CommonTabLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.databinding.AcNftHistoryBinding;
import com.tron.wallet.databinding.ItemNftHistoryBarBinding;
import com.tronlinkpro.wallet.R;
public class NftHistoryActivity extends BaseActivity<NftHistoryPresenter, TokenDetailModel> implements NftHistoryContract.View {
    private ItemNftHistoryBarBinding barBinding;
    private AcNftHistoryBinding binding;
    View contentRootView;
    CoordinatorLayout nestedLayout;
    ImageView progressView;
    PtrHTFrameLayoutV2 ptrView;
    ViewPager2 vpContent;
    CommonTabLayout xTablayout;

    @Override
    public PtrHTFrameLayoutV2 getFrameLayout() {
        return this.ptrView;
    }

    @Override
    public ViewPager2 getViewPager() {
        return this.vpContent;
    }

    @Override
    public CommonTabLayout getXTablayout() {
        return this.xTablayout;
    }

    public static void start(Context context, String str, TokenBean tokenBean) {
        Intent intent = new Intent(context, NftHistoryActivity.class);
        intent.putExtra(TronConfig.CONTRACT_ADDRESS, str);
        intent.putExtra(TronConfig.TOKEN_DATA, tokenBean);
        context.startActivity(intent);
    }

    public static void start(Context context, String str, int i, TokenBean tokenBean) {
        Intent intent = new Intent(context, NftHistoryActivity.class);
        intent.putExtra(TronConfig.CONTRACT_ADDRESS, str);
        intent.putExtra(TronConfig.TOKEN_DATA, tokenBean);
        intent.putExtra("from", i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcNftHistoryBinding inflate = AcNftHistoryBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        this.barBinding = ItemNftHistoryBarBinding.bind(inflate.getRoot().findViewById(R.id.content_root));
        setView(this.binding.getRoot(), 1);
        setHeaderBar(getString(R.string.nft_records));
        getHeaderHolder().itemView.setBackgroundColor(getIContext().getResources().getColor(R.color.white));
    }

    public void mappingId() {
        this.ptrView = this.binding.pullToRefresh;
        this.progressView = this.binding.progressView;
        this.xTablayout = this.barBinding.xTablayout;
        this.vpContent = this.barBinding.vpContent;
        this.nestedLayout = this.binding.rcList;
        this.contentRootView = this.barBinding.contentRoot;
    }

    @Override
    public boolean isIDestroyed() {
        return isDestroyed();
    }

    @Override
    public void showLoadingPage() {
        super.showLoadingPage();
    }

    @Override
    public void dismissLoadingPage() {
        super.dismissLoadingPage();
    }

    @Override
    protected void processData() {
        mappingId();
        ((NftHistoryPresenter) this.mPresenter).addSome();
        this.contentRootView.bringToFront();
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public Intent getIIntent() {
        return getIntent();
    }

    public void setCanRefresh(boolean z) {
        ((NftHistoryPresenter) this.mPresenter).setCanRefresh(z);
    }
}
