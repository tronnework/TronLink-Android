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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.tabdapp.browser.base.adapter.BrowserBookMarkAdapter;
import com.tron.wallet.business.tabdapp.browser.base.contract.BookMarkManagerContract;
import com.tron.wallet.business.tabdapp.browser.base.contract.BookMarkManagerModel;
import com.tron.wallet.business.tabdapp.browser.base.contract.BookMarkManagerPresneter;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserBookMarkBean;
import com.tron.wallet.business.tabdapp.browser.tabs.BookMarkManagerFragment;
import com.tron.wallet.business.tabdapp.home.utils.DAppAnalyseUtils;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.RVItemTouchHelper;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.interfaces.OnItemSelectedListener;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FragmentBrowserBookmarkBinding;
import com.tronlinkpro.wallet.R;
import java.util.List;
public class BookMarkManagerFragment extends BaseFragment<BookMarkManagerPresneter, BookMarkManagerModel> implements BookMarkManagerContract.View {
    private BrowserBookMarkAdapter adapter;
    private FragmentBrowserBookmarkBinding binding;
    View holderView;
    RecyclerView mRecyclerView;
    NestedScrollView noDataView;
    NoNetView noNetView;
    PtrHTFrameLayout ptrLayout;
    private RVItemTouchHelper touchHelper;

    public static BookMarkManagerFragment getInstance() {
        return new BookMarkManagerFragment();
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
                ((BookMarkManagerPresneter) mPresenter).requestData(true);
            }
        });
        this.mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext(), 1, false));
        BrowserBookMarkAdapter browserBookMarkAdapter = new BrowserBookMarkAdapter(getContext(), new BrowserBookMarkAdapter.ItemCallback() {
            @Override
            public void onItemClicked(View view, BrowserBookMarkBean browserBookMarkBean, int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppBookMarkEvent.CLICK_FAV);
                DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.DAppBookMarkEvent.DAPP_HISTORY_TAG, browserBookMarkBean.getUrl());
                ((BookMarkManagerPresneter) mPresenter).jumpToViewPage(browserBookMarkBean);
            }

            @Override
            public void onItemSelected(View view, BrowserBookMarkBean browserBookMarkBean, int i) {
                adapter.getItemCount();
            }

            @Override
            public void onItemLongClicked(final View view, final BrowserBookMarkBean browserBookMarkBean, int[] iArr, final int i) {
                view.setSelected(true);
                new DeleteItemPopup(getIContext(), browserBookMarkBean, new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(PopupWindow popupWindow, int i2, int i3, Object obj) {
                        boolean remveBookMark = ((BookMarkManagerPresneter) mPresenter).remveBookMark(browserBookMarkBean, i);
                        AnalyticsHelper.logEvent(AnalyticsHelper.DAppBookMarkEvent.CLICK_LONG_CLICK_DELTEE);
                        if (remveBookMark) {
                            adapter.remove(browserBookMarkBean, i);
                            if (adapter.getDatas().size() <= 0) {
                                showNoDatasPage();
                            }
                            toast(getString(R.string.bookmark_remove_success));
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
        this.adapter = browserBookMarkAdapter;
        this.mRecyclerView.setAdapter(browserBookMarkAdapter);
        ((SimpleItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        RVItemTouchHelper rVItemTouchHelper = new RVItemTouchHelper(this.adapter);
        this.touchHelper = rVItemTouchHelper;
        rVItemTouchHelper.attachToRecyclerView(this.mRecyclerView);
        this.touchHelper.setDraggable(true);
        this.adapter.setTouchhelper(this.touchHelper);
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        ((BookMarkManagerPresneter) this.mPresenter).requestData(true);
    }

    public void lambda$processData$0(View view) {
        ((BookMarkManagerPresneter) this.mPresenter).requestData(false);
    }

    public void backOut() {
        if (this.adapter.isSortChanged()) {
            this.adapter.resetSortChanged();
            ((BookMarkManagerPresneter) this.mPresenter).saveSortedDatas(this.adapter.getDatas());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        backOut();
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentBrowserBookmarkBinding inflate = FragmentBrowserBookmarkBinding.inflate(layoutInflater, viewGroup, false);
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

    @Override
    public void showNoDatasPage() {
        this.noDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateList(List<BrowserBookMarkBean> list) {
        BrowserBookMarkAdapter browserBookMarkAdapter = this.adapter;
        if (browserBookMarkAdapter != null) {
            browserBookMarkAdapter.notifyDataChanged(list);
        }
        if (list == null || list.size() <= 0) {
            return;
        }
        this.holderView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    public static class DeleteItemPopup extends PopupWindow {
        private Context context;
        private int popupHeight;
        private int popupWidth;

        public DeleteItemPopup(Context context, final BrowserBookMarkBean browserBookMarkBean, final OnItemSelectedListener onItemSelectedListener, PopupWindow.OnDismissListener onDismissListener) {
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
            textView.setText(R.string.browser_remove_from_bookmark);
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, context.getDrawable(R.drawable.roundborder_88_8));
            stateListDrawable.addState(new int[]{-16842919}, context.getDrawable(R.color.transparent));
            textView.setBackground(stateListDrawable);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    BookMarkManagerFragment.DeleteItemPopup.this.lambda$new$0(onItemSelectedListener, browserBookMarkBean, view);
                }
            });
            inflate.measure(0, 0);
            this.popupHeight = inflate.getMeasuredHeight();
            this.popupWidth = inflate.getMeasuredWidth();
        }

        public void lambda$new$0(OnItemSelectedListener onItemSelectedListener, BrowserBookMarkBean browserBookMarkBean, View view) {
            onItemSelectedListener.onItemSelected(this, 1, 1, browserBookMarkBean);
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
