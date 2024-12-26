package com.tron.wallet.business.tabswap.mvp;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean;
import com.tron.wallet.business.tabswap.mvp.Contract;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.adapter.SwapAdapter;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FragmentSwapBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class SwapFragment extends LazyLoadFragment<SwapPresenter, SwapModel> implements Contract.View {
    private FragmentSwapBinding binding;
    View llUnderstand;
    View rlNotSupport;
    RecyclerView rvMain;
    private SwapAdapter swapAdapter;

    @Override
    public SwapAdapter getAdapter() {
        return this.swapAdapter;
    }

    @Override
    protected void refreshLoad() {
    }

    @Override
    public void showNoNetNotice() {
    }

    @Override
    protected void processData() {
        setupRecyclerView();
        ((SwapPresenter) this.mPresenter).getRecord();
    }

    private void setupRecyclerView() {
        this.rvMain.setLayoutManager(new WrapContentLinearLayoutManager(getIContext(), 1, false));
        SwapAdapter swapAdapter = new SwapAdapter(((SwapPresenter) this.mPresenter).mRxManager);
        this.swapAdapter = swapAdapter;
        swapAdapter.setOnConfirmButtonClickListener(((SwapPresenter) this.mPresenter).getOnBtnConfirmClickListener());
        this.swapAdapter.setOnAmountChangedCallback(((SwapPresenter) this.mPresenter).getAmountChangedListener());
        this.swapAdapter.setOnTokenChangedListener(((SwapPresenter) this.mPresenter).getTokenChangedListener());
        this.rvMain.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (swapAdapter.getRecordsCount() <= 0 || recyclerView.getChildAdapterPosition(view) != recyclerView.getAdapter().getItemCount() - 1) {
                    return;
                }
                rect.bottom = UIUtils.dip2px(40.0f);
            }
        });
        this.rvMain.setAdapter(this.swapAdapter);
        this.swapAdapter.notifyDataSetChanged();
        RecyclerView.ItemAnimator itemAnimator = this.rvMain.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(0L);
            itemAnimator.setAddDuration(0L);
            itemAnimator.setMoveDuration(0L);
            itemAnimator.setRemoveDuration(0L);
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(0);
        FragmentSwapBinding inflate = FragmentSwapBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId(root);
        return root;
    }

    public void mappingId(View view) {
        this.rvMain = this.binding.rvMain;
        this.rlNotSupport = this.binding.ilNotSupport.rlNotSupport;
        this.llUnderstand = this.binding.ilNotSupport.llUnderstand;
    }

    @Override
    public void onGetInitTokens(SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, boolean z) {
        SwapAdapter swapAdapter = this.swapAdapter;
        if (swapAdapter != null) {
            swapAdapter.notifyTokenHeader(new Pair<>(swapTokenBean, swapTokenBean2), z);
            this.swapAdapter.notifySwapTokenInfo(SwapTokenInfoBean.buildEmpty(), null, 0, null, z);
            this.swapAdapter.updateButtonEnabled(false);
        }
    }

    @Override
    public void updateSwapTokenValues(int i, String str, String str2) {
        SwapAdapter swapAdapter = this.swapAdapter;
        if (swapAdapter != null) {
            swapAdapter.notifySwapValue(i, str, str2);
        }
    }

    @Override
    public void updateSwapTokenInfo(SwapTokenInfoBean swapTokenInfoBean, List<SwapInfoOutput.InfoData> list, int i, View.OnClickListener onClickListener, boolean z) {
        SwapAdapter swapAdapter = this.swapAdapter;
        if (swapAdapter != null) {
            swapAdapter.notifySwapTokenInfo(swapTokenInfoBean, list, i, onClickListener, z);
        }
    }

    @Override
    public void notifyInfoVisible(boolean z) {
        SwapAdapter swapAdapter = this.swapAdapter;
        if (swapAdapter != null) {
            swapAdapter.notifySwapInfoVisible(z);
        }
    }

    @Override
    protected void firstLoad() {
        if (this.mPresenter != 0) {
            ((SwapPresenter) this.mPresenter).getInitTokens(true);
        }
    }

    @Override
    public void onVisible() {
        onVisibleChanged(false);
    }

    @Override
    public void onInvisible() {
        super.onInvisible();
        onVisibleChanged(true);
        if (getIContext() == null || getActivity() == null || getActivity().getCurrentFocus() == null) {
            return;
        }
        try {
            ((InputMethodManager) getIContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void onVisibleChanged(boolean z) {
        if (this.mPresenter != 0) {
            if (!z) {
                if (this.isFirstLoad) {
                    firstLoad();
                    this.isFirstLoad = false;
                    return;
                }
                ((SwapPresenter) this.mPresenter).getInitTokens(false);
                return;
            }
            ((SwapPresenter) this.mPresenter).onInvisible();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.mPresenter != 0) {
            ((SwapPresenter) this.mPresenter).stopCheckTranscation();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mPresenter != 0) {
            ((SwapPresenter) this.mPresenter).getRecord();
            ((SwapPresenter) this.mPresenter).startCheckTranscationState();
        }
        showNoNetNotice();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != 0) {
            ((SwapPresenter) this.mPresenter).onDestroy();
        }
    }

    @Override
    public void showLoadingWindow(final boolean z) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showLoadingWindow$0(z);
            }
        });
    }

    public void lambda$showLoadingWindow$0(boolean z) {
        if (z) {
            super.showLoadingDialog(getString(R.string.wait_loading));
        } else {
            super.dismissLoadingDialog();
        }
    }

    @Override
    public void showLoading(final boolean z) {
        runOnUIThread(new OnMainThread() {
            @Override
            public final void doInUIThread() {
                lambda$showLoading$1(z);
            }
        });
    }

    public void lambda$showLoading$1(boolean z) {
        SwapAdapter swapAdapter = this.swapAdapter;
        if (swapAdapter != null) {
            swapAdapter.notifySwapInfoLoadingState(z);
        }
    }

    @Override
    public void updateBalance(String str, String str2) {
        SwapAdapter swapAdapter = this.swapAdapter;
        if (swapAdapter != null) {
            swapAdapter.updateBalance(str, str2);
        }
    }

    public void showNotSupportView() {
        View view = this.llUnderstand;
        if (view == null || this.rlNotSupport == null || this.rvMain == null) {
            return;
        }
        view.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view2) {
                CommonWebActivityV3.start((Context) getActivity(), IRequest.getIpUnderstandUrl(), "", -2, false);
            }
        });
        this.rlNotSupport.setVisibility(View.VISIBLE);
        this.rvMain.setVisibility(View.GONE);
        this.rvMain.setClickable(false);
        this.rvMain.setFocusable(false);
    }
}
