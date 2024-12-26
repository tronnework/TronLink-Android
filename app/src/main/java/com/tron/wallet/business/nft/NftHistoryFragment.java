package com.tron.wallet.business.nft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.nft.contract.NftHistoryContentContract;
import com.tron.wallet.business.nft.contract.NftHistoryContentModel;
import com.tron.wallet.business.nft.contract.NftHistoryContentPresenter;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.databinding.FgNftHistoryContentBinding;
import org.tron.protos.Protocol;
public class NftHistoryFragment extends LazyLoadFragment<NftHistoryContentPresenter, NftHistoryContentModel> implements NftHistoryContentContract.View {
    public static final String INDEX = "index";
    private FgNftHistoryContentBinding binding;
    View llNoDataView;
    View llShastaView;
    private String mContractAddress;
    private int mIndex;
    private TokenBean mToken;
    private String mTokenType;
    View noNetContainer;
    NoNetView noNetView;
    View placeHolderView;
    LoadMoreRecyclerView rlContent;
    View root;
    private RxManager rxManager = new RxManager();
    TextView tvNoData;

    @Override
    public Fragment getContentView() {
        return this;
    }

    @Override
    public String getContractAddress() {
        return this.mContractAddress;
    }

    @Override
    public View getEmptyView() {
        return this.llNoDataView;
    }

    @Override
    public View getHolderView() {
        return this.placeHolderView;
    }

    @Override
    public int getIndex() {
        return this.mIndex;
    }

    @Override
    public LoadMoreRecyclerView getRecycleView() {
        return this.rlContent;
    }

    @Override
    public TokenBean getToken() {
        return this.mToken;
    }

    @Override
    public String getTokenType() {
        return this.mTokenType;
    }

    @Override
    public void onGetAccount(Protocol.Account account) {
    }

    @Override
    public void onRefresh() {
    }

    @Override
    protected void refreshLoad() {
    }

    public static NftHistoryFragment getInstance(int i, String str, TokenBean tokenBean, double d, String str2) {
        NftHistoryFragment nftHistoryFragment = new NftHistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", i);
        bundle.putString(TronConfig.isTRX, str);
        bundle.putParcelable(TronConfig.TRC, tokenBean);
        bundle.putDouble(TronConfig.PriceUsdOrCny, d);
        bundle.putString(TronConfig.CONTRACT_ADDRESS, str2);
        nftHistoryFragment.setArguments(bundle);
        return nftHistoryFragment;
    }

    @Override
    protected void firstLoad() {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$firstLoad$0();
            }
        });
        if (this.isFirstLoad) {
            this.isFirstLoad = false;
            refresh();
        }
    }

    public void lambda$firstLoad$0() {
        if (this.isFirstLoad) {
            if (this.llNoDataView.getVisibility() == 0) {
                this.llNoDataView.setVisibility(View.GONE);
            }
            if (this.noNetContainer.getVisibility() == 0) {
                this.noNetContainer.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showNoNetError(boolean z) {
        if (z) {
            if (this.rlContent.getVisibility() == 8) {
                this.placeHolderView.setVisibility(View.GONE);
                this.llNoDataView.setVisibility(View.GONE);
                this.noNetView.updateLoadingState(true);
                this.noNetContainer.setVisibility(View.VISIBLE);
                return;
            }
            return;
        }
        this.noNetContainer.setVisibility(View.GONE);
    }

    @Override
    public void showShastaView() {
        this.placeHolderView.setVisibility(View.GONE);
        this.llNoDataView.setVisibility(View.GONE);
        this.rlContent.setVisibility(View.GONE);
        this.noNetContainer.setVisibility(View.GONE);
        this.llShastaView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView(boolean z) {
        if (z) {
            this.placeHolderView.setVisibility(View.GONE);
            this.llShastaView.setVisibility(View.GONE);
            this.noNetContainer.setVisibility(View.GONE);
            this.llNoDataView.setVisibility(View.VISIBLE);
            return;
        }
        this.llNoDataView.setVisibility(View.GONE);
    }

    @Override
    protected void processData() {
        this.mTokenType = getArguments().getString(TronConfig.isTRX);
        this.mToken = (TokenBean) getArguments().getParcelable(TronConfig.TRC);
        this.mIndex = getArguments().getInt("index");
        this.mContractAddress = getArguments().getString(TronConfig.CONTRACT_ADDRESS);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$1(view);
            }
        });
        ((NftHistoryContentPresenter) this.mPresenter).addSome();
    }

    public void lambda$processData$1(View view) {
        refresh();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgNftHistoryContentBinding inflate = FgNftHistoryContentBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        setType(0);
        mappingId();
        return root;
    }

    public void mappingId() {
        this.rlContent = this.binding.rlContent;
        this.llNoDataView = this.binding.llNodata;
        this.llShastaView = this.binding.llShasta;
        this.tvNoData = this.binding.tvNoData;
        this.placeHolderView = this.binding.rlPlaceHolder;
        this.root = this.binding.root;
        this.noNetContainer = this.binding.tvNoNet;
        this.noNetView = this.binding.noNetView;
    }

    @Override
    public void loadMore() {
        ((NftHistoryContentPresenter) this.mPresenter).loadMore();
    }

    @Override
    public void hideRefresh() {
        this.rxManager.post(Event.TOKEN_DETAIL_REFRESH_FINISHED, "");
    }

    @Override
    public boolean isIDestroyed() {
        try {
            return getActivity().isDestroyed();
        } catch (Exception e) {
            LogUtils.e(e);
            return true;
        }
    }

    public void refresh() {
        if (this.mPresenter != 0) {
            ((NftHistoryContentPresenter) this.mPresenter).refresh();
        }
    }

    @Override
    public void onReLoadButtonClick() {
        super.onReLoadButtonClick();
        this.rxManager.post(Event.TOKEN_DETAIL, "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxManager rxManager = this.rxManager;
        if (rxManager != null) {
            rxManager.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    @Override
    public void setCanRefresh(boolean z) {
        ((NftHistoryActivity) getActivity()).setCanRefresh(z);
    }

    @Override
    public void onVisible() {
        super.onVisible();
        if (this.mPresenter != 0) {
            ((NftHistoryContentPresenter) this.mPresenter).setCanRefresh();
        }
    }
}
