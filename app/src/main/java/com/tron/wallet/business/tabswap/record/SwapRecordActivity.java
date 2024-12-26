package com.tron.wallet.business.tabswap.record;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.tabswap.record.SwapRecordContract;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.databinding.AcSwapTokenRecordBinding;
import com.tronlinkpro.wallet.R;
public class SwapRecordActivity extends BaseActivity<SwapRecordPresenter, SwapRecordModel> implements SwapRecordContract.View {
    private AcSwapTokenRecordBinding binding;
    View llNoDataView;
    View mHolderView;
    RecyclerView mRecyclerView;
    View noNetContainer;
    PtrHTFrameLayout rcFrame;
    TextView tvNoData;

    @Override
    public View getHolderView() {
        return this.mHolderView;
    }

    @Override
    public PtrFrameLayout getRcFrame() {
        return this.rcFrame;
    }

    @Override
    public RecyclerView getRcList() {
        return this.mRecyclerView;
    }

    @Override
    public void onLeftButtonClick() {
        finish();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, SwapRecordActivity.class));
    }

    @Override
    protected void setLayout() {
        AcSwapTokenRecordBinding inflate = AcSwapTokenRecordBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getString(R.string.swap_record));
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.mRecyclerView = this.binding.recyclerView;
        this.mHolderView = this.binding.rcHolderList;
        this.rcFrame = this.binding.rcFrame;
        this.noNetContainer = this.binding.tvNoNet;
        this.tvNoData = this.binding.tvNoData;
        this.llNoDataView = this.binding.llNodata;
    }

    @Override
    protected void processData() {
        if (this.mPresenter != 0) {
            ((SwapRecordPresenter) this.mPresenter).init();
            ((SwapRecordPresenter) this.mPresenter).getData();
        }
    }

    @Override
    public void updateUI(boolean z, boolean z2) {
        if (z) {
            showEmptyView(true);
            this.rcFrame.setVisibility(View.GONE);
            return;
        }
        showEmptyView(false);
        this.mHolderView.setVisibility(View.GONE);
        this.rcFrame.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoNetError(boolean z) {
        if (z) {
            if (this.mRecyclerView.getVisibility() == 8) {
                this.noNetContainer.setVisibility(View.VISIBLE);
                return;
            }
            return;
        }
        this.noNetContainer.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView(boolean z) {
        if (z) {
            this.noNetContainer.setVisibility(View.GONE);
            this.llNoDataView.setVisibility(View.VISIBLE);
            return;
        }
        this.llNoDataView.setVisibility(View.GONE);
    }
}
