package com.tron.wallet.business.tabdapp.browser.tabs;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTabManagerContract;
import com.tron.wallet.business.vote.adapter.VoteContentVpAdapter;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.ToastUtil;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.List;
public class BrowserTabsManagerPresenter extends BrowserTabManagerContract.Presenter {
    BookMarkManagerFragment bookMarkManagerFragment;
    BrowserHistoryFragment browserHistoryFragment;
    private int tabIndex;
    TabManagerFragment tabManagerFragment;
    private List<Fragment> fragments = new ArrayList();
    private List<String> titleLists = new ArrayList();

    @Override
    protected void onStart() {
    }

    @Override
    public void getData() {
        getIntent();
        initFragmentData(((BrowserTabManagerContract.View) this.mView).getIContext());
        initTabLayout();
        if (this.tabIndex != 0) {
            ((BrowserTabManagerContract.View) this.mView).getViewPager().setCurrentItem(this.tabIndex);
        }
    }

    private void getIntent() {
        this.tabIndex = ((BrowserTabManagerContract.View) this.mView).getIIntent().getIntExtra(BrowserConstant.CHONTROL_INDEX, 0);
    }

    private void initFragmentData(Context context) {
        this.fragments.clear();
        this.tabManagerFragment = TabManagerFragment.getInstance();
        this.bookMarkManagerFragment = BookMarkManagerFragment.getInstance();
        this.browserHistoryFragment = BrowserHistoryFragment.getInstance();
        this.fragments.add(this.tabManagerFragment);
        this.fragments.add(this.bookMarkManagerFragment);
        this.fragments.add(this.browserHistoryFragment);
        this.titleLists.clear();
        this.titleLists.add(context.getResources().getString(R.string.browser_mutil_windows));
        this.titleLists.add(context.getResources().getString(R.string.browser_bookmark));
        this.titleLists.add(context.getResources().getString(R.string.browser_history));
    }

    private void initTabLayout() {
        ((BrowserTabManagerContract.View) this.mView).getViewPager().setAdapter(new VoteContentVpAdapter((FragmentActivity) ((BrowserTabManagerContract.View) this.mView).getIContext(), this.fragments, this.titleLists));
        ((BrowserTabManagerContract.View) this.mView).getViewPager().setUserInputEnabled(false);
        ((BrowserTabManagerContract.View) this.mView).getXTablayout().setupWithViewPager(((BrowserTabManagerContract.View) this.mView).getViewPager());
        ((BrowserTabManagerContract.View) this.mView).getXTablayout().setOnTabSelectedListener(new XTabLayoutV2.ViewPagerOnTabSelectedListener(((BrowserTabManagerContract.View) this.mView).getViewPager()) {
            @Override
            public void onTabSelected(XTabLayoutV2.Tab tab) {
                super.onTabSelected(tab);
                if (tab.getPosition() == 0) {
                    ((BrowserTabManagerContract.View) mView).getBrowserTabBottomToolbar().showTabManagerLayout();
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_TABs);
                } else if (tab.getPosition() == 1) {
                    ((BrowserTabManagerContract.View) mView).getBrowserTabBottomToolbar().showBookMarkManagerLayout();
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_FAVORITES);
                } else {
                    ((BrowserTabManagerContract.View) mView).getBrowserTabBottomToolbar().showHistoryManagerLayout();
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppTabsEvent.CLICK_History);
                }
                ((BrowserTabManagerContract.View) mView).resetButtons();
            }
        });
        int i = this.tabIndex;
        if (i == 0) {
            ((BrowserTabManagerContract.View) this.mView).getBrowserTabBottomToolbar().showTabManagerLayout();
        } else if (i == 1) {
            ((BrowserTabManagerContract.View) this.mView).getBrowserTabBottomToolbar().showBookMarkManagerLayout();
        } else {
            ((BrowserTabManagerContract.View) this.mView).getBrowserTabBottomToolbar().showHistoryManagerLayout();
        }
    }

    @Override
    public void clearBrowserTabs() {
        TabManagerFragment tabManagerFragment = this.tabManagerFragment;
        if (tabManagerFragment != null) {
            tabManagerFragment.clearBrowserTabs();
        }
    }

    @Override
    public void newBrowserTab() {
        TabManagerFragment tabManagerFragment = this.tabManagerFragment;
        if (tabManagerFragment != null) {
            if (tabManagerFragment.browserTabManager.getTabCount() >= 10) {
                ToastUtil.getInstance().showToast(((BrowserTabManagerContract.View) this.mView).getIContext(), R.string.dapp_browser_max_count_warning);
                return;
            }
            this.tabManagerFragment.openNewTab();
            ((BrowserTabManagerContract.View) this.mView).exit();
        }
    }

    @Override
    public void clearBrowserHistory() {
        BrowserHistoryFragment browserHistoryFragment = this.browserHistoryFragment;
        if (browserHistoryFragment != null) {
            browserHistoryFragment.clearHistory();
        }
    }
}
