package com.tron.wallet.business.addassets2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.addassets2.EditAssetsOrderActivity;
import com.tron.wallet.business.addassets2.EditAssetsOrderFragment;
import com.tron.wallet.business.addassets2.HomeAssetsContract;
import com.tron.wallet.business.addassets2.adapter.AssetsListAdapter;
import com.tron.wallet.business.addassets2.adapter.HomeAssetAdapter;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.RVItemTouchHelper;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.ItemRcBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class EditAssetsOrderFragment extends BaseFragment<HomeAssetsPresenter, HomeAssetsModel> implements HomeAssetsContract.View, EditAssetsOrderActivity.UserAction {
    private static final String TAG = "HomeAssetsFragment";
    private HomeAssetAdapter adapter;
    private ItemRcBinding binding;
    View holderView;
    RecyclerView mRecyclerView;
    NoNetView noNetView;
    PtrHTFrameLayout ptrLayout;
    View sortConfirmView;
    private RVItemTouchHelper touchHelper;
    private int tokenCount = -1;
    private int dataType = 0;
    private int initSortType = -1;

    public int getTokenCount() {
        return this.tokenCount;
    }

    public static EditAssetsOrderFragment newInstance(int i) {
        return newInstance(0, i);
    }

    public static EditAssetsOrderFragment newInstance(int i, int i2) {
        EditAssetsOrderFragment editAssetsOrderFragment = new EditAssetsOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AssetsConfig.DATA_TYPE, i);
        bundle.putInt(AssetsConfig.SORT_TYPE, i2);
        editAssetsOrderFragment.setArguments(bundle);
        return editAssetsOrderFragment;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.dataType = arguments.getInt(AssetsConfig.DATA_TYPE, 0);
            this.initSortType = arguments.getInt(AssetsConfig.SORT_TYPE, -1);
        }
        dismissLoadingPage();
        ((HomeAssetsPresenter) this.mPresenter).setIsHomeAssets(this.dataType == 0);
        this.ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, mRecyclerView, view2) && !(adapter != null && adapter.isStateSort());
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ((HomeAssetsPresenter) mPresenter).requestHomeAssets(false);
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        HomeAssetAdapter homeAssetAdapter = new HomeAssetAdapter(getContext(), new fun2());
        this.adapter = homeAssetAdapter;
        this.mRecyclerView.setAdapter(homeAssetAdapter);
        ((SimpleItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        RVItemTouchHelper rVItemTouchHelper = new RVItemTouchHelper(this.adapter);
        this.touchHelper = rVItemTouchHelper;
        rVItemTouchHelper.attachToRecyclerView(this.mRecyclerView);
        this.touchHelper.setDraggable(false);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        ((HomeAssetsPresenter) this.mPresenter).setInitSortType(this.initSortType);
        ((HomeAssetsPresenter) this.mPresenter).getHomeAssets();
    }

    public class fun2 implements AssetsListAdapter.ItemCallback {
        @Override
        public void onItemLongClicked(View view, TokenBean tokenBean, int[] iArr, int i) {
            AssetsListAdapter.ItemCallback.-CC.$default$onItemLongClicked(this, view, tokenBean, iArr, i);
        }

        fun2() {
        }

        @Override
        public void onItemClicked(TokenBean tokenBean, int i) {
            ((HomeAssetsPresenter) mPresenter).showAssetsDetail(tokenBean);
        }

        @Override
        public void onItemSelected(final TokenBean tokenBean, final int i) {
            if (!WatchWalletVerifier.getInstance().getWatchWalletHasOwnerPermission()) {
                WatchWalletVerifier.getInstance().confirmVerify(getIContext());
                return;
            }
            if (BigDecimalUtils.lessThanOrEqual(Double.valueOf(tokenBean.getBalance()), 0)) {
                ((HomeAssetsPresenter) mPresenter).unFollowAssets(tokenBean, i);
            } else {
                ConfirmCustomPopupView.Builder builder = ConfirmCustomPopupView.getBuilder(getIContext());
                String string = getResources().getString(R.string.confirm_remove_title);
                Object[] objArr = new Object[1];
                objArr[0] = StringTronUtil.isEmpty(tokenBean.getShortName()) ? tokenBean.getName() : tokenBean.getShortName();
                builder.setTitle(String.format(string, objArr)).setInfo(getResources().getString(R.string.confirm_remove_info)).setConfirmListener(new View.OnClickListener() {
                    @Override
                    public final void onClick(View view) {
                        EditAssetsOrderFragment.2.this.lambda$onItemSelected$0(tokenBean, i, view);
                    }
                }).build().show();
            }
            FirebaseAnalytics.getInstance(getIContext()).logEvent("Click_add_token_home_unFollow", null);
        }

        public void lambda$onItemSelected$0(TokenBean tokenBean, int i, View view) {
            ((HomeAssetsPresenter) mPresenter).unFollowAssets(tokenBean, i);
            FirebaseAnalytics.getInstance(getIContext()).logEvent("Click_add_token_home_unFollow_confirm", null);
        }
    }

    public void lambda$processData$0(View view) {
        ((HomeAssetsPresenter) this.mPresenter).requestHomeAssets();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(2);
        ItemRcBinding inflate = ItemRcBinding.inflate(layoutInflater, viewGroup, false);
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
        this.ptrLayout = this.binding.rcFrame;
        this.mRecyclerView = this.binding.rcList;
        this.sortConfirmView = this.binding.llContainer;
        this.holderView = this.binding.rcHolderList;
        this.noNetView = this.binding.netErrorView;
    }

    @Override
    public void updateComplete(boolean z) {
        this.ptrLayout.refreshComplete();
        if (z || this.adapter.getItemCount() <= 0) {
            return;
        }
        toast(getResources().getString(R.string.time_out));
    }

    @Override
    public void updateHomeAssets(List<TokenBean> list) {
        this.noNetView.setVisibility(View.GONE);
        this.holderView.setVisibility(View.GONE);
        this.ptrLayout.setVisibility(View.VISIBLE);
        this.adapter.notifyDataChanged(list);
        this.tokenCount = list != null ? list.size() : 0;
    }

    @Override
    public void updateAssetsFollowState(TokenBean tokenBean, int i) {
        this.adapter.notifyDataRemoved(tokenBean, i);
    }

    @Override
    public void updateAssetsPrice() {
        this.adapter.notifyPriceChanged();
    }

    @Override
    public void showNoNetView() {
        this.noNetView.setVisibility(View.VISIBLE);
        this.noNetView.hideLoading();
        this.holderView.setVisibility(View.GONE);
        this.ptrLayout.setVisibility(View.GONE);
    }

    public void updateCustomSortState(boolean z) {
        HomeAssetAdapter homeAssetAdapter = this.adapter;
        if (homeAssetAdapter != null) {
            homeAssetAdapter.updateOperationState(z);
        }
        RVItemTouchHelper rVItemTouchHelper = this.touchHelper;
        if (rVItemTouchHelper != null) {
            rVItemTouchHelper.setDraggable(z);
        }
    }

    @Override
    public void selectSortType(TokenSortType tokenSortType) {
        if (tokenSortType != TokenSortType.SORT_BY_USER && this.mPresenter != 0) {
            ((HomeAssetsPresenter) this.mPresenter).setSortType(tokenSortType);
        }
        if (tokenSortType.equals(TokenSortType.SORT_BY_USER)) {
            updateCustomSortState(true);
        } else {
            updateCustomSortState(false);
        }
    }

    @Override
    public void sortConfirm() {
        this.adapter.onSortConfirm();
        ((HomeAssetsPresenter) this.mPresenter).insertSortBean(this.adapter.getChangedTokens());
        ((HomeAssetsPresenter) this.mPresenter).setSortType(TokenSortType.SORT_BY_USER);
        updateCustomSortState(false);
    }

    @Override
    public void sortCancel() {
        this.adapter.onSortCancel();
        updateCustomSortState(false);
        ((EditAssetsOrderActivity) getActivity()).updateSortType(((HomeAssetsPresenter) this.mPresenter).getSortType());
        SortHelper.get().onCustomSortCancel(this.dataType);
    }
}
