package com.tron.wallet.business.tokendetail.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailContentContract;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.databinding.FgTokendetailContentBinding;
import com.tron.wallet.db.SpAPI;
import java.util.HashMap;
import org.tron.protos.Protocol;
public class TokenDetailContentFragment extends LazyLoadFragment<TokenDetailContentPresenter, TokenDetailContentModel> implements TokenDetailContentContract.View {
    public static final String INDEX = "index";
    public static final String TOKEN_BEAN = "TOKEN_BEAN";
    public static final String TOKEN_TYPE = "TOKEN_TYPE";
    private HashMap<String, NameAddressExtraBean> addressMap;
    private FgTokendetailContentBinding binding;
    AppCompatEditText etSearch;
    private boolean isMainChain;
    private boolean isMapping;
    View llNoDataView;
    View llShastaView;
    private int mIndex;
    private double mPriceUsdOrCny;
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
    public boolean getIsMapping() {
        return this.isMapping;
    }

    @Override
    public View getNoNetView() {
        return this.noNetContainer;
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

    public static TokenDetailContentFragment getInstance(int i, String str, TokenBean tokenBean, double d) {
        TokenDetailContentFragment tokenDetailContentFragment = new TokenDetailContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", i);
        bundle.putString(TronConfig.isTRX, str);
        bundle.putParcelable(TronConfig.TRC, tokenBean);
        bundle.putDouble(TronConfig.PriceUsdOrCny, d);
        tokenDetailContentFragment.setArguments(bundle);
        return tokenDetailContentFragment;
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
    public void showNoNetError() {
        this.rlContent.setVisibility(View.GONE);
        this.placeHolderView.setVisibility(View.GONE);
        this.llNoDataView.setVisibility(View.GONE);
        this.noNetView.updateLoadingState(true);
        this.noNetContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPlaceHolderView() {
        this.llNoDataView.setVisibility(View.GONE);
        this.noNetContainer.setVisibility(View.GONE);
        this.llShastaView.setVisibility(View.GONE);
        this.rlContent.setVisibility(View.GONE);
        this.placeHolderView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTokenDetailView() {
        this.placeHolderView.setVisibility(View.GONE);
        this.llNoDataView.setVisibility(View.GONE);
        this.noNetContainer.setVisibility(View.GONE);
        this.llShastaView.setVisibility(View.GONE);
        this.rlContent.setVisibility(View.VISIBLE);
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
    public void showEmptyView() {
        this.placeHolderView.setVisibility(View.GONE);
        this.rlContent.setVisibility(View.GONE);
        this.llShastaView.setVisibility(View.GONE);
        this.noNetContainer.setVisibility(View.GONE);
        this.llNoDataView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void processData() {
        this.mTokenType = getArguments().getString(TronConfig.isTRX);
        this.mToken = (TokenBean) getArguments().getParcelable(TronConfig.TRC);
        this.mIndex = getArguments().getInt("index");
        boolean z = SpAPI.THIS.getCurrentChain().isMainChain;
        this.isMainChain = z;
        this.isMapping = z ? this.mToken.inSideChain : this.mToken.inMainChain;
        this.mPriceUsdOrCny = getArguments().getDouble(TronConfig.PriceUsdOrCny, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$1(view);
            }
        });
        ((TokenDetailContentPresenter) this.mPresenter).addSome();
        this.addressMap = ((TokenDetailActivity) getActivity()).getAllAddress();
    }

    public void lambda$processData$1(View view) {
        refresh();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgTokendetailContentBinding inflate = FgTokendetailContentBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        setType(0);
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.rlContent = this.binding.rlContent;
        this.llNoDataView = this.binding.llNodata;
        this.llShastaView = this.binding.llShasta;
        this.etSearch = this.binding.etSearch;
        this.tvNoData = this.binding.tvNoData;
        this.placeHolderView = this.binding.rlPlaceHolder;
        this.root = this.binding.root;
        this.noNetContainer = this.binding.tvNoNet;
        this.noNetView = this.binding.noNetView;
    }

    public void setFilterSmallValue(boolean z) {
        if (this.mPresenter != 0) {
            showPlaceHolderView();
            ((TokenDetailContentPresenter) this.mPresenter).setFilterSmallValue(z);
        }
    }

    @Override
    public void loadMore() {
        ((TokenDetailContentPresenter) this.mPresenter).loadMore();
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

    @Override
    public boolean getVisible() {
        return this.isVisible;
    }

    public void refresh() {
        if (this.mPresenter != 0) {
            ((TokenDetailContentPresenter) this.mPresenter).refresh();
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

    public void addLocalHistoryToListAndShow() {
        if (this.mPresenter != 0) {
            ((TokenDetailContentPresenter) this.mPresenter).addLocalHistoryToListAndShow();
        }
    }

    public boolean isCurRefresh() {
        return ((TokenDetailContentPresenter) this.mPresenter).isCurRefresh();
    }
}
