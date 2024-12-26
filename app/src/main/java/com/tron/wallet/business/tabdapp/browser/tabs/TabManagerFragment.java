package com.tron.wallet.business.tabdapp.browser.tabs;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.bean.BrowserTabHistoryBean;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserTabHistoryManager;
import com.tron.wallet.business.tabdapp.browser.grid.adapter.BrowserTabsAdapter;
import com.tron.wallet.business.tabdapp.browser.grid.adapter.BrowserTabsDataProvider;
import com.tron.wallet.business.tabdapp.browser.grid.adapter.LinearSpacingItemDecoration;
import com.tron.wallet.business.tabdapp.browser.grid.animator.DraggableItemAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.databinding.FragmentBrowserTabManagerBinding;
import java.util.ArrayList;
public class TabManagerFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private static final int REFRESH_LIST = 16;
    private FragmentBrowserTabManagerBinding binding;
    BrowserTabManager browserTabManager;
    private BrowserTabsDataProvider dataProvider;
    private Handler mHandler;
    private GridLayoutManager mLayoutManager;
    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;
    private RecyclerView.Adapter mWrappedAdapter;
    BrowserTabsAdapter myItemAdapter;
    RecyclerView recyclerView;

    private boolean supportsViewElevation() {
        return true;
    }

    public static TabManagerFragment getInstance() {
        return new TabManagerFragment();
    }

    @Override
    protected void processData() {
        BrowserTabManager browserTabManager = BrowserTabManager.getInstance();
        this.browserTabManager = browserTabManager;
        if (browserTabManager.getBrowserContent() == null) {
            getActivity().finish();
            return;
        }
        this.mLayoutManager = new GridLayoutManager(requireContext(), 2, 1, false);
        this.mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                if (message.what == 16) {
                    dataProvider.refreshTabData();
                    myItemAdapter.notifyDataSetChanged();
                }
                super.handleMessage(message);
            }
        };
        RecyclerViewDragDropManager recyclerViewDragDropManager = new RecyclerViewDragDropManager();
        this.mRecyclerViewDragDropManager = recyclerViewDragDropManager;
        recyclerViewDragDropManager.setInitiateOnLongPress(true);
        this.mRecyclerViewDragDropManager.setInitiateOnMove(false);
        this.mRecyclerViewDragDropManager.setLongPressTimeout(750);
        this.mRecyclerViewDragDropManager.setDragStartItemAnimationDuration(250);
        this.mRecyclerViewDragDropManager.setDraggingItemAlpha(0.99f);
        this.mRecyclerViewDragDropManager.setDraggingItemScale(1.0f);
        this.mRecyclerViewDragDropManager.setDraggingItemRotation(0.0f);
        this.dataProvider = new BrowserTabsDataProvider(this.browserTabManager);
        BrowserTabsAdapter browserTabsAdapter = new BrowserTabsAdapter(this.dataProvider);
        this.myItemAdapter = browserTabsAdapter;
        browserTabsAdapter.setTabChangedLister(new BrowserTabsAdapter.TabChangedLister() {
            @Override
            public void onTabClose(int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_DELETE);
                boolean z = browserTabManager.getTabCount() == 1;
                browserTabManager.close(i);
                myItemAdapter.notifyItemRemoved(i);
                if (myItemAdapter.getItemCount() == 1) {
                    dataProvider.refreshTabData();
                    myItemAdapter.notifyDataSetChanged();
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(16), 500L);
                } else {
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(16), 50L);
                }
                if (z) {
                    getActivity().finish();
                }
            }

            @Override
            public void onClick(int i) {
                AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_SELECT_TAB);
                browserTabManager.setTab(i);
                getActivity().finish();
            }
        });
        this.mWrappedAdapter = this.mRecyclerViewDragDropManager.createWrappedAdapter(this.myItemAdapter);
        DraggableItemAnimator draggableItemAnimator = new DraggableItemAnimator();
        this.recyclerView.addItemDecoration(new LinearSpacingItemDecoration(getIContext()));
        this.recyclerView.setLayoutManager(this.mLayoutManager);
        this.recyclerView.setAdapter(this.mWrappedAdapter);
        this.recyclerView.setItemAnimator(draggableItemAnimator);
        this.mRecyclerViewDragDropManager.attachRecyclerView(this.recyclerView);
        this.recyclerView.scrollToPosition(this.browserTabManager.getCurrentTabIndex());
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentBrowserTabManagerBinding inflate = FragmentBrowserTabManagerBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        ConstraintLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.recyclerView = this.binding.recyclerView;
    }

    public void clearBrowserTabs() {
        BrowserTabManager browserTabManager = this.browserTabManager;
        if (browserTabManager != null) {
            browserTabManager.clearBrowserTabs();
        }
        this.dataProvider.refreshTabData();
        this.myItemAdapter.notifyDataSetChanged();
        getActivity().finish();
    }

    public void openNewTab() {
        BrowserTabManager browserTabManager = this.browserTabManager;
        if (browserTabManager != null) {
            browserTabManager.startNewTab();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (BrowserTabManager.getInstance().getBrowserContent() != null) {
            ArrayList<BrowserTabHistoryBean> allTabs = BrowserTabManager.getInstance().getBrowserContent().getAllTabs();
            for (int i = 0; i < allTabs.size(); i++) {
                BrowserTabHistoryBean browserTabHistoryBean = allTabs.get(i);
                if (BrowserTabManager.getInstance().getViewType(i) == 0) {
                    browserTabHistoryBean.setUrl(BrowserConstant.DEFAULT_URL);
                    browserTabHistoryBean.setDappAuthUrl(BrowserConstant.DEFAULT_URL);
                    browserTabHistoryBean.setMain(true);
                }
            }
            LogUtils.e("LOAD_TAB_HISTORY", "browserWebViewArrayList= " + allTabs.toString());
            BrowserTabHistoryManager.getInstance().addListAndRemoveOldData(allTabs);
        }
    }
}
