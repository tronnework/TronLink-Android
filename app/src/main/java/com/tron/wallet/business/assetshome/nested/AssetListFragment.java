package com.tron.wallet.business.assetshome.nested;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.wallet.business.addassets2.WatchWalletVerifier;
import com.tron.wallet.business.assetshome.AssetsAnimatorHelper;
import com.tron.wallet.business.assetshome.adapter.AssetAdapter;
import com.tron.wallet.business.nft.NftTokenListActivity;
import com.tron.wallet.business.transfer.TokenDetailActivity;
import com.tron.wallet.business.walletmanager.pairwallet.PairColdWalletDialog;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.databinding.FgAssetHomeListBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.List;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class AssetListFragment extends BaseFragment<NestedAssetsPresenter, EmptyModel> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener {
    private static final String KEY_TYPE = "data_type";
    private Protocol.Account account;
    private FgAssetHomeListBinding binding;
    View ivPlaceHolder;
    private long lastUpdateTime;
    private WrapContentLinearLayoutManager layoutManager;
    View llHolder;
    private AssetAdapter mAdapter;
    NoNetView noDataView;
    NoNetView noNetView;
    RecyclerView rvList;
    private Wallet selectedWallet;
    private int type;

    public static void lambda$setupContentView$1() {
    }

    public static AssetListFragment create(int i) {
        AssetListFragment assetListFragment = new AssetListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, i);
        assetListFragment.setArguments(bundle);
        return assetListFragment;
    }

    @Override
    protected void processData() {
        this.type = 0;
        if (getArguments() != null) {
            this.type = getArguments().getInt(KEY_TYPE);
        }
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        setupContentView(this.rvList, this.type);
    }

    public void lambda$processData$0(View view) {
        ((NestedAssetsPresenter) this.mPresenter).mRxManager.post(Event.ASSETS_RELOAD, true);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FgAssetHomeListBinding inflate = FgAssetHomeListBinding.inflate(layoutInflater, viewGroup, false);
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
        this.rvList = this.binding.rvList;
        this.llHolder = this.binding.llHolder;
        this.ivPlaceHolder = this.binding.ivPlaceHolder;
        this.noNetView = this.binding.netErrorView;
        this.noDataView = this.binding.noDataView;
    }

    private void setupContentView(RecyclerView recyclerView, int i) {
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(getContext(), 1, false);
        this.layoutManager = wrapContentLinearLayoutManager;
        recyclerView.setLayoutManager(wrapContentLinearLayoutManager);
        AssetAdapter assetAdapter = new AssetAdapter(recyclerView.getContext(), i);
        this.mAdapter = assetAdapter;
        assetAdapter.bindToRecyclerView(recyclerView);
        this.mAdapter.setOnItemClickListener(this);
        this.mAdapter.setOnItemLongClickListener(this);
        this.mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public final void onLoadMoreRequested() {
                AssetListFragment.lambda$setupContentView$1();
            }
        }, recyclerView);
        recyclerView.setItemViewCacheSize(16);
        recyclerView.setAdapter(this.mAdapter);
    }

    public void updateStart() {
        AssetAdapter assetAdapter = this.mAdapter;
        if (assetAdapter == null) {
            return;
        }
        assetAdapter.setNewData(null);
        this.llHolder.setVisibility(View.VISIBLE);
        this.ivPlaceHolder.setVisibility(View.VISIBLE);
        this.rvList.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
    }

    public void updateFail() {
        AssetAdapter assetAdapter = this.mAdapter;
        if (assetAdapter == null) {
            return;
        }
        if (assetAdapter == null || assetAdapter.getData() == null || this.mAdapter.getData().size() == 0) {
            this.ivPlaceHolder.setVisibility(View.GONE);
            this.rvList.setVisibility(View.GONE);
            this.llHolder.setVisibility(View.VISIBLE);
            this.noNetView.setVisibility(View.VISIBLE);
        }
    }

    public void updateData(final List<TokenBean> list, final boolean z, Protocol.Account account) {
        this.account = account;
        this.selectedWallet = WalletUtils.getSelectedPublicWallet();
        if (this.mAdapter == null || this.rvList == null) {
            return;
        }
        if (list == null || list.size() == 0) {
            this.rvList.setVisibility(View.GONE);
            this.llHolder.setVisibility(View.VISIBLE);
            this.ivPlaceHolder.setVisibility(View.GONE);
            this.noNetView.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.VISIBLE);
        } else {
            this.llHolder.setVisibility(View.GONE);
            this.ivPlaceHolder.setVisibility(View.GONE);
            this.noNetView.setVisibility(View.GONE);
            this.rvList.setVisibility(View.VISIBLE);
        }
        boolean shouldStartAnimator = AssetsAnimatorHelper.shouldStartAnimator("AssetsList");
        if (shouldStartAnimator) {
            this.lastUpdateTime = System.currentTimeMillis();
            setupContentView(this.rvList, this.type);
        }
        if (!shouldStartAnimator && this.mAdapter.getData() != null && this.mAdapter.getData().size() > 0 && System.currentTimeMillis() - this.lastUpdateTime < 1000) {
            this.rvList.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    lambda$updateData$2(list, z);
                }
            }, 1000L);
            return;
        }
        this.mAdapter.updateData(list, z, this.account);
        AssetAdapter assetAdapter = this.mAdapter;
        assetAdapter.loadMoreEnd(assetAdapter.getData().size() <= 5);
    }

    public void lambda$updateData$2(List list, boolean z) {
        this.mAdapter.updateData(list, z, this.account);
        AssetAdapter assetAdapter = this.mAdapter;
        assetAdapter.loadMoreEnd(assetAdapter.getData().size() <= 5);
    }

    public void updateHidePrivacy(boolean z) {
        AssetAdapter assetAdapter = this.mAdapter;
        if (assetAdapter == null) {
            return;
        }
        updateData(assetAdapter.getData(), z, this.account);
    }

    public boolean canPullDown() {
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = this.layoutManager;
        return wrapContentLinearLayoutManager != null && wrapContentLinearLayoutManager.findFirstCompletelyVisibleItemPosition() > 0;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (i < 0) {
            return;
        }
        Object item = baseQuickAdapter.getItem(i);
        if (item instanceof TokenBean) {
            TokenBean tokenBean = (TokenBean) item;
            if (tokenBean.type != 5) {
                Bundle bundle = new Bundle();
                bundle.putString("token_name", tokenBean.getName());
                FirebaseAnalytics.getInstance(view.getContext()).logEvent("item_click_token", bundle);
                TokenDetailActivity.start(view.getContext(), tokenBean, SpAPI.THIS.getPrice());
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(TronConfig.CONTRACT_ADDRESS, tokenBean.getContractAddress());
            FirebaseAnalytics.getInstance(view.getContext()).logEvent("item_click_nft_collections", bundle2);
            Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
            if (selectedPublicWallet == null) {
                return;
            }
            NftTokenListActivity.start(view.getContext(), selectedPublicWallet.getAddress(), tokenBean);
        }
    }

    public List<TokenBean> getCurrentData() {
        return this.mAdapter.getData();
    }

    @Override
    public boolean onItemLongClick(final BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Object item = baseQuickAdapter.getItem(i);
        if (item instanceof TokenBean) {
            Wallet wallet = this.selectedWallet;
            if (wallet != null && wallet.isWatchNotPaired()) {
                PairColdWalletDialog.showUp(getIContext(), null);
                return false;
            }
            final TokenBean tokenBean = (TokenBean) item;
            if (tokenBean.type == 0 || tokenBean.top == 2) {
                ToastUtil.getInstance().show(getIContext(), String.format(getIContext().getString(R.string.cannot_remove_from_homepage), tokenBean.type == 0 ? "TRX" : tokenBean.getShortName()));
                return true;
            } else if (!WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
                WatchWalletVerifier.getInstance().confirmVerify(getIContext());
                return false;
            } else {
                ConfirmCustomPopupView.Builder builder = ConfirmCustomPopupView.getBuilder(getContext());
                String string = getResources().getString(R.string.confirm_remove_title);
                Object[] objArr = new Object[1];
                objArr[0] = StringTronUtil.isEmpty(tokenBean.getShortName()) ? tokenBean.getName() : tokenBean.getShortName();
                builder.setTitle(String.format(string, objArr)).setInfo(getResources().getString(R.string.confirm_remove_info)).setBtnStyle(2).setConfirmText(getString(R.string.delete_move)).setConfirmListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view2) {
                        lambda$onItemLongClick$3(baseQuickAdapter, i, tokenBean, view2);
                    }
                }).setCancleListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view2) {
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_REMOVE_HOME_ASSET_CANCEL);
                    }
                }).build().show();
                AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.ENTER_ASSET_PAGE_REMOVE_HOME_ASSET);
                return true;
            }
        }
        return false;
    }

    public void lambda$onItemLongClick$3(BaseQuickAdapter baseQuickAdapter, int i, TokenBean tokenBean, View view) {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet == null) {
            return;
        }
        baseQuickAdapter.remove(i);
        ((NestedAssetsPresenter) this.mPresenter).unFollowAssets(selectedWallet.getAddress(), tokenBean);
        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.AssetPage.CLICK_ASSET_PAGE_REMOVE_HOME_ASSET_CONFIRM);
    }
}
