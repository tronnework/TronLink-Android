package com.tron.wallet.business.tronpower.unstake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.wallet.business.tronpower.unstake.adapter.UnStakeResourceAdapter;
import com.tron.wallet.business.tronpower.unstake.adapter.UnStakeResourceBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.databinding.FragmentUnstakeResourcesBinding;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
public class ResourceListFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private UnStakeResourceAdapter adapter;
    private FragmentUnstakeResourcesBinding binding;
    ImageView ivLoading;
    NoNetView noDataView;
    private boolean previousSelectable = true;
    RecyclerView rvList;

    public static ResourceListFragment getInstance() {
        Bundle bundle = new Bundle();
        ResourceListFragment resourceListFragment = new ResourceListFragment();
        resourceListFragment.setArguments(bundle);
        return resourceListFragment;
    }

    public List<UnStakeResourceBean> getTotalData() {
        UnStakeResourceAdapter unStakeResourceAdapter = this.adapter;
        if (unStakeResourceAdapter == null) {
            return null;
        }
        return unStakeResourceAdapter.getData();
    }

    @Override
    protected void processData() {
        UnStakeResourceAdapter unStakeResourceAdapter = new UnStakeResourceAdapter();
        this.adapter = unStakeResourceAdapter;
        unStakeResourceAdapter.bindToRecyclerView(this.rvList);
        RecyclerView.ItemAnimator itemAnimator = this.rvList.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(0L);
        }
        this.rvList.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
    }

    public void updateSelectableState(final boolean z) {
        UnStakeResourceAdapter unStakeResourceAdapter = this.adapter;
        if (unStakeResourceAdapter == null || this.previousSelectable == z) {
            return;
        }
        this.previousSelectable = z;
        Collection.-EL.stream(unStakeResourceAdapter.getData()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                boolean z2 = z;
                r2.setState((((UnStakeResourceBean) obj).getState() & (-65536)) + (!r2 ? 1 : 0));
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RecyclerView recyclerView = this.rvList;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
    }

    public void setEmptyData() {
        UnStakeResourceAdapter unStakeResourceAdapter = this.adapter;
        if (unStakeResourceAdapter == null) {
            return;
        }
        unStakeResourceAdapter.setNewData(new ArrayList());
        this.adapter.setEnableLoadMore(false);
    }

    public void updateData(boolean z, boolean z2, List<UnStakeResourceBean> list, androidx.core.util.Consumer<UnStakeResourceBean> consumer, BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener) {
        UnStakeResourceAdapter unStakeResourceAdapter = this.adapter;
        if (unStakeResourceAdapter == null) {
            this.noDataView.setVisibility(View.VISIBLE);
            this.ivLoading.setVisibility(View.GONE);
            this.rvList.setVisibility(View.GONE);
            return;
        }
        unStakeResourceAdapter.setItemClickCallback(consumer);
        if (!list.isEmpty()) {
            this.rvList.setVisibility(View.VISIBLE);
            this.ivLoading.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.GONE);
            if (z) {
                this.adapter.addData(0, list, list.size());
                return;
            }
            if (requestLoadMoreListener != null && this.rvList != null && !this.adapter.isLoadMoreEnable()) {
                this.adapter.setOnLoadMoreListener(requestLoadMoreListener, this.rvList);
            }
            this.adapter.addData((java.util.Collection) list);
            this.adapter.loadMoreComplete();
            return;
        }
        boolean z3 = true;
        this.adapter.loadMoreEnd(true);
        z3 = (z2 && this.adapter.getData().isEmpty()) ? false : false;
        this.noDataView.setVisibility(z3 ? View.VISIBLE : View.GONE);
        this.rvList.setVisibility(z3 ? View.GONE : View.VISIBLE);
        if (z2) {
            this.ivLoading.setVisibility(View.GONE);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentUnstakeResourcesBinding inflate = FragmentUnstakeResourcesBinding.inflate(layoutInflater, viewGroup, false);
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
        this.ivLoading = this.binding.ivLoading;
        this.noDataView = this.binding.noDataView;
    }
}
