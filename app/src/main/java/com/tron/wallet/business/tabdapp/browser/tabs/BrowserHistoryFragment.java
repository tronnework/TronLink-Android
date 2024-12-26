package com.tron.wallet.business.tabdapp.browser.tabs;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.tabdapp.browser.base.adapter.BaseBrowserAdapter;
import com.tron.wallet.business.tabdapp.browser.base.adapter.BrowserHistoryAdapter;
import com.tron.wallet.business.tabdapp.browser.base.history.BrowserHistoryContract;
import com.tron.wallet.business.tabdapp.browser.base.history.BrowserHistoryModel;
import com.tron.wallet.business.tabdapp.browser.base.history.BrowserHistoryPresneter;
import com.tron.wallet.business.tabdapp.browser.bean.BaseWebViewPageInfo;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserHistoryBean;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserHistoryFragment;
import com.tron.wallet.business.tabdapp.home.utils.DAppAnalyseUtils;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.interfaces.OnItemSelectedListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FragmentBrowserHistoryBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class BrowserHistoryFragment extends BaseFragment<BrowserHistoryPresneter, BrowserHistoryModel> implements BrowserHistoryContract.View {
    BrowserHistoryAdapter adapter;
    private FragmentBrowserHistoryBinding binding;
    View holderView;
    RecyclerView mRecyclerView;
    NestedScrollView noDataView;
    NoNetView noNetView;
    PtrHTFrameLayout ptrLayout;

    public static BrowserHistoryFragment getInstance() {
        return new BrowserHistoryFragment();
    }

    @Override
    protected void processData() {
        dismissLoadingPage();
        this.ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, mRecyclerView, view2) && !(adapter != null);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ((BrowserHistoryPresneter) mPresenter).requestData(true);
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        BrowserHistoryAdapter browserHistoryAdapter = new BrowserHistoryAdapter(getContext(), new ArrayList(), new BaseBrowserAdapter.ItemCallback() {
            @Override
            public void onItemSelected(BaseWebViewPageInfo baseWebViewPageInfo, int i) {
            }

            @Override
            public void onItemClicked(BaseWebViewPageInfo baseWebViewPageInfo, int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppHistoryEvent.CLICK_ITEM);
                DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.DAppHistoryEvent.DAPP_MENU_TAG, baseWebViewPageInfo.getUrl());
                ((BrowserHistoryPresneter) mPresenter).jumpToViewPage((BrowserHistoryBean) baseWebViewPageInfo);
            }

            @Override
            public void onItemLongClicked(final View view, final BaseWebViewPageInfo baseWebViewPageInfo, int[] iArr, final int i) {
                view.setSelected(true);
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppHistoryEvent.CLICK_LONG_CLICK);
                new DeleteItemPopup(getIContext(), baseWebViewPageInfo, new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(PopupWindow popupWindow, int i2, int i3, Object obj) {
                        boolean remveHistory = ((BrowserHistoryPresneter) mPresenter).remveHistory((BrowserHistoryBean) baseWebViewPageInfo, i);
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppHistoryEvent.CLICK_LONG_CLICK_DELTEE);
                        if (remveHistory) {
                            if (adapter.getDatas().size() == 2) {
                                showNoDatasPage();
                                ((BrowserHistoryPresneter) mPresenter).requestData(true);
                            }
                            adapter.remove(baseWebViewPageInfo, i);
                        }
                        popupWindow.dismiss();
                    }
                }, new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        view.setSelected(false);
                    }
                }).showAtTouchPoint(view, iArr);
            }
        });
        this.adapter = browserHistoryAdapter;
        this.mRecyclerView.setAdapter(browserHistoryAdapter);
        ((SimpleItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadmore();
            }
        }, this.mRecyclerView);
        ((BrowserHistoryPresneter) this.mPresenter).requestData(true);
    }

    public void lambda$processData$0(View view) {
        ((BrowserHistoryPresneter) this.mPresenter).requestData(false);
    }

    public void loadmore() {
        int size = this.adapter.getData().size();
        if (size == 0 || (((BrowserHistoryPresneter) this.mPresenter).getStatIndex() < size && size >= 20)) {
            ((BrowserHistoryPresneter) this.mPresenter).loadMore();
            return;
        }
        BrowserHistoryAdapter browserHistoryAdapter = this.adapter;
        if (browserHistoryAdapter != null) {
            if (browserHistoryAdapter.getDatas() != null && this.adapter.getDatas().size() <= 5) {
                this.adapter.loadMoreEnd(true);
                this.adapter.loadMoreComplete();
                return;
            }
            this.adapter.loadMoreEnd();
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentBrowserHistoryBinding inflate = FragmentBrowserHistoryBinding.inflate(layoutInflater, viewGroup, false);
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
        this.holderView = this.binding.rcHolderList;
        this.noNetView = this.binding.netErrorView;
        this.noDataView = this.binding.llNodata;
    }

    public void clearHistory() {
        ((BrowserHistoryPresneter) this.mPresenter).clearAllHistory();
        ((BrowserHistoryPresneter) this.mPresenter).requestData(true);
        showNoDatasPage();
    }

    @Override
    public void showNoDatasPage() {
        this.holderView.setVisibility(View.GONE);
        this.mRecyclerView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateList(List<BrowserHistoryBean> list, boolean z) {
        BrowserHistoryAdapter browserHistoryAdapter = this.adapter;
        if (browserHistoryAdapter != null) {
            browserHistoryAdapter.notifyDataChanged(list, z);
        }
        if (!z) {
            if (list.size() < 20) {
                this.adapter.loadMoreComplete();
                this.adapter.loadMoreEnd();
                return;
            }
            this.adapter.loadMoreComplete();
        } else if (list != null && list.size() > 0) {
            this.holderView.setVisibility(View.GONE);
            this.noDataView.setVisibility(View.GONE);
            this.mRecyclerView.setVisibility(View.VISIBLE);
            if (list.size() >= 20) {
                this.adapter.loadMoreComplete();
            } else if (list != null && list.size() <= 5) {
                this.adapter.loadMoreComplete();
                this.adapter.loadMoreEnd(true);
            } else {
                this.adapter.loadMoreEnd();
            }
        } else {
            showNoDatasPage();
        }
    }

    public static class DeleteItemPopup extends PopupWindow {
        private Context context;
        private int popupHeight;
        private int popupWidth;

        public DeleteItemPopup(Context context, final BaseWebViewPageInfo baseWebViewPageInfo, final OnItemSelectedListener onItemSelectedListener, PopupWindow.OnDismissListener onDismissListener) {
            this.context = context;
            View inflate = LayoutInflater.from(context).inflate(R.layout.popup_del_browser_history, (ViewGroup) null);
            setContentView(inflate);
            setWidth(-2);
            setHeight(-2);
            setOutsideTouchable(true);
            setFocusable(true);
            setClippingEnabled(false);
            setOnDismissListener(onDismissListener);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_delete);
            textView.setText(R.string.delete);
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, context.getDrawable(R.drawable.roundborder_88_8));
            stateListDrawable.addState(new int[]{-16842919}, context.getDrawable(R.color.transparent));
            textView.setBackground(stateListDrawable);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    BrowserHistoryFragment.DeleteItemPopup.this.lambda$new$0(onItemSelectedListener, baseWebViewPageInfo, view);
                }
            });
            inflate.measure(0, 0);
            this.popupHeight = inflate.getMeasuredHeight();
            this.popupWidth = inflate.getMeasuredWidth();
        }

        public void lambda$new$0(OnItemSelectedListener onItemSelectedListener, BaseWebViewPageInfo baseWebViewPageInfo, View view) {
            onItemSelectedListener.onItemSelected(this, 1, 1, baseWebViewPageInfo);
        }

        public void showAtTouchPoint(View view, int[] iArr) {
            int height;
            int i;
            int[] iArr2 = new int[2];
            view.getLocationOnScreen(iArr2);
            if (iArr2[1] + iArr[1] + this.popupHeight + UIUtils.dip2px(15.0f) > UIUtils.getScreenHeight(this.context)) {
                height = (view.getHeight() - iArr[1]) + this.popupHeight;
            } else {
                height = view.getHeight() - iArr[1];
            }
            int i2 = -height;
            if (iArr2[0] + iArr[0] + this.popupWidth + UIUtils.dip2px(10.0f) > UIUtils.getScreenWidth(this.context)) {
                i = iArr[0] - this.popupWidth;
                if (i < UIUtils.dip2px(10.0f)) {
                    i = UIUtils.dip2px(10.0f);
                }
            } else {
                i = iArr[0];
            }
            showAsDropDown(view, i, i2);
        }
    }
}
