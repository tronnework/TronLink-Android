package com.tron.wallet.business.tabmy.allhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.tabmy.allhistory.TrxAllHistoryContract;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tron.wallet.databinding.FgAllHistoryBinding;
import com.tronlinkpro.wallet.R;
public class TrxAllHistoryFragment extends BaseFragment<TrxAllHistoryPresenter, TrxAllHistoryModel> implements TrxAllHistoryContract.View {
    public static final String ADDRESS_KEY = "ADDRESS_KEY";
    public static final String POSITION_KEY = "POSITION_KEY";
    private String address;
    private FgAllHistoryBinding binding;
    View llNoDataView;
    NoNetView noNetView;
    View placeHolderView;
    private int position;
    PtrHTFrameLayoutV2 rcFrame;
    RecyclerView rcList;

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public PtrHTFrameLayoutV2 getRcFrame() {
        return this.rcFrame;
    }

    @Override
    public RecyclerView getRcList() {
        return this.rcList;
    }

    @Override
    protected void processData() {
        Bundle arguments = getArguments();
        this.position = arguments.getInt(POSITION_KEY);
        this.address = arguments.getString(ADDRESS_KEY);
        if (this.mPresenter != 0) {
            ((TrxAllHistoryPresenter) this.mPresenter).addSome();
            ((TrxAllHistoryPresenter) this.mPresenter).getData();
        }
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noNetView.showLoading();
                if (mPresenter != 0) {
                    ((TrxAllHistoryPresenter) mPresenter).onRefresh(address);
                }
            }
        });
    }

    @Override
    public void showErrorPage() {
        dismissLoadingPage();
        this.rcFrame.setVisibility(View.GONE);
        this.placeHolderView.setVisibility(View.GONE);
        this.llNoDataView.setVisibility(View.GONE);
        this.noNetView.updateLoadingState(true);
        this.noNetView.hideLoading();
        this.noNetView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        this.placeHolderView.setVisibility(View.GONE);
        this.llNoDataView.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.rcFrame.setVisibility(View.VISIBLE);
        NoNetView noNetView = this.noNetView;
        if (noNetView != null) {
            noNetView.hideLoading();
        }
    }

    @Override
    public void showNoDatasPage() {
        this.placeHolderView.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.rcFrame.setVisibility(View.GONE);
        this.llNoDataView.setVisibility(View.VISIBLE);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgAllHistoryBinding inflate = FgAllHistoryBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        setType(2);
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.rcList = this.binding.rcList;
        this.rcFrame = this.binding.rcFrame;
        this.noNetView = this.binding.netErrorView;
        this.placeHolderView = this.binding.placeholderView;
        this.llNoDataView = this.binding.llNodata;
    }

    @Override
    public void showError(boolean z) {
        if (z) {
            this.noNetView.setVisibility(View.VISIBLE);
            this.noNetView.updateLoadingState(true);
            toast(getString(R.string.time_out));
            return;
        }
        this.noNetView.setVisibility(View.GONE);
    }

    public static TrxAllHistoryFragment newInstance(String str, int i) {
        TrxAllHistoryFragment trxAllHistoryFragment = new TrxAllHistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ADDRESS_KEY, str);
        bundle.putInt(POSITION_KEY, i);
        trxAllHistoryFragment.setArguments(bundle);
        return trxAllHistoryFragment;
    }

    public void onRefresh(String str) {
        if (this.mPresenter != 0) {
            ((TrxAllHistoryPresenter) this.mPresenter).onRefresh(str);
        }
    }

    @Override
    public void onReLoadButtonClick() {
        super.onReLoadButtonClick();
        if (this.mPresenter != 0) {
            ((TrxAllHistoryPresenter) this.mPresenter).getData();
        }
    }
}
