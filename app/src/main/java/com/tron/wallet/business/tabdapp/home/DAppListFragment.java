package com.tron.wallet.business.tabdapp.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.wallet.business.tabdapp.home.adapter.DappListAdapter;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class DAppListFragment extends Fragment {
    private DappListAdapter adapter;
    private View loadingView;
    private NoNetView netErrorView;
    private View noNetContainer;
    private BaseQuickAdapter.OnItemClickListener onItemClicked;
    private View.OnClickListener onReload;
    private RecyclerView rvContent;

    public void setListener(BaseQuickAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClicked = onItemClickListener;
    }

    public void setOnClickReloadListener(View.OnClickListener onClickListener) {
        this.onReload = onClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.dapp_list_inner_fragment, viewGroup);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.rvContent = (RecyclerView) view.findViewById(R.id.rv_content);
        this.loadingView = view.findViewById(R.id.iv_loading_view);
        this.netErrorView = (NoNetView) view.findViewById(R.id.no_net_view);
        this.noNetContainer = view.findViewById(R.id.net_error_view);
        View.OnClickListener onClickListener = this.onReload;
        if (onClickListener != null) {
            this.netErrorView.setOnReloadClickListener(onClickListener);
        }
        RecyclerView recyclerView = this.rvContent;
        if (recyclerView == null) {
            return;
        }
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        RecyclerView recyclerView2 = this.rvContent;
        DappListAdapter dappListAdapter = new DappListAdapter();
        this.adapter = dappListAdapter;
        recyclerView2.setAdapter(dappListAdapter);
        this.adapter.setOnItemClickListener(this.onItemClicked);
    }

    public void updateDappListLoading() {
        View view = this.noNetContainer;
        if (view != null) {
            view.setVisibility(View.GONE);
        }
        RecyclerView recyclerView = this.rvContent;
        if (recyclerView != null) {
            recyclerView.setVisibility(View.GONE);
        }
        View view2 = this.loadingView;
        if (view2 != null) {
            view2.setVisibility(View.VISIBLE);
        }
    }

    public void updateNewData(List<DappBean> list) {
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(View.GONE);
        }
        if (list == null) {
            View view2 = this.noNetContainer;
            if (view2 != null) {
                view2.setVisibility(View.VISIBLE);
            }
            RecyclerView recyclerView = this.rvContent;
            if (recyclerView != null) {
                recyclerView.setVisibility(View.GONE);
            }
        } else if (list.isEmpty()) {
            View view3 = this.noNetContainer;
            if (view3 != null) {
                view3.setVisibility(View.VISIBLE);
            }
            RecyclerView recyclerView2 = this.rvContent;
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(View.GONE);
            }
            NoNetView noNetView = this.netErrorView;
            if (noNetView != null) {
                noNetView.setIcon(R.mipmap.icon_recommend_empty);
                this.netErrorView.setReloadDescription(R.string.recommend_empty);
                this.netErrorView.setReloadable(false);
            }
        } else {
            RecyclerView recyclerView3 = this.rvContent;
            if (recyclerView3 != null) {
                recyclerView3.setVisibility(View.VISIBLE);
            }
            View view4 = this.noNetContainer;
            if (view4 != null) {
                view4.setVisibility(View.GONE);
            }
            DappListAdapter dappListAdapter = this.adapter;
            if (dappListAdapter != null) {
                dappListAdapter.setNewData(list);
            }
        }
        NoNetView noNetView2 = this.netErrorView;
        if (noNetView2 != null) {
            noNetView2.hideLoading();
        }
    }

    public void resetState() {
        RecyclerView recyclerView = this.rvContent;
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }
}
