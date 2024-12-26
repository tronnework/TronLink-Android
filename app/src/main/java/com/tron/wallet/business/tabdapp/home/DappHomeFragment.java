package com.tron.wallet.business.tabdapp.home;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.BrowserWebView;
import com.tron.wallet.business.tabdapp.component.DAppDialog;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.business.web.WebConstant;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.databinding.FragmentDappHomeV2Binding;
import io.reactivex.functions.Consumer;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
public class DappHomeFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    public static final int PAGE_BROWSER = 1;
    public static final int PAGE_MAIN = 0;
    private FragmentDappHomeV2Binding binding;
    private DAppBrowserFragment dAppBrowserFragment;
    private DAppMainFragment dAppMainFragment;
    View ivIndicator;
    private ObjectAnimator ivIndicatorAnimator;
    private String outerLinkUrl;
    private BrowserTabManager tabManager;
    ViewPager2 viewPager;
    final List<Fragment> fragments = new ArrayList();
    private final List<Long> itemHashCodes = new ArrayList();
    private EventListener eventListener = new EventListener() {
        @Override
        public void expandTabView(boolean z) {
        }
    };
    private boolean tabExpanded = true;
    private RxManager rxManager = new RxManager();

    public interface EventListener {
        void expandTabView(boolean z);
    }

    public void pullFromOutside(String str) {
        this.outerLinkUrl = str;
        if (str == null) {
            return;
        }
        DAppDialog.showOutsideLinkWarning(getIContext(), this.outerLinkUrl, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$pullFromOutside$0((Boolean) obj);
            }
        });
    }

    public void lambda$pullFromOutside$0(Boolean bool) throws Exception {
        if (bool.booleanValue() && this.dAppBrowserFragment != null) {
            DappBean dappBean = new DappBean();
            dappBean.setHomeUrl(this.outerLinkUrl);
            showPage(1);
            DappBean findInCacheDApp = this.dAppMainFragment.findInCacheDApp(dappBean);
            if (findInCacheDApp != null) {
                dappBean = findInCacheDApp;
            }
            this.dAppBrowserFragment.handleNewDAppClicked(dappBean, true);
        }
        this.outerLinkUrl = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.tabManager.setDefaultSnapshot(this.dAppMainFragment);
        DAppBrowserFragment dAppBrowserFragment = this.dAppBrowserFragment;
        if (dAppBrowserFragment != null) {
            this.tabManager.addTitleChangedObserver(dAppBrowserFragment.getTitleChangedListener());
            this.dAppBrowserFragment.handleResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        BrowserTabManager browserTabManager = this.tabManager;
        browserTabManager.getTabAt(browserTabManager.getCurrentTabIndex()).setCachedSnapshotValid(false);
        DAppBrowserFragment dAppBrowserFragment = this.dAppBrowserFragment;
        if (dAppBrowserFragment != null) {
            this.tabManager.removeTitleObserver(dAppBrowserFragment.getTitleChangedListener());
        }
    }

    @Override
    public void onDestroy() {
        this.rxManager.clear();
        super.onDestroy();
    }

    @Override
    protected void processData() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.home.DappHomeFragment.processData():void");
    }

    public void lambda$processData$1() {
        DAppBrowserFragment dAppBrowserFragment = this.dAppBrowserFragment;
        if (dAppBrowserFragment == null || dAppBrowserFragment.browserView == null || !this.dAppBrowserFragment.browserView.equals(this.tabManager.getBrowserContent())) {
            return;
        }
        BrowserTabManager browserTabManager = this.tabManager;
        showPage(browserTabManager.getViewType(browserTabManager.getCurrentTabIndex()) != 1 ? 0 : 1);
    }

    public void lambda$processData$2(View view) {
        if (this.tabExpanded) {
            return;
        }
        expandTabView(true);
    }

    public void lambda$processData$3(Object obj) throws Exception {
        BrowserWebView webView = this.tabManager.getWebView();
        if (webView == null || !WebConstant.LANDSCAPE.equals(webView.getScreenMode()) || this.tabExpanded) {
            return;
        }
        expandTabView(true);
    }

    public void showPage(int i) {
        if (i < 0 || i > 1) {
            return;
        }
        this.viewPager.setCurrentItem(i);
        int i2 = i == 0 ? 1 : 0;
        BrowserTabManager browserTabManager = this.tabManager;
        browserTabManager.updateViewType(browserTabManager.getCurrentTabIndex(), i2 ^ 1);
        if (i == 1) {
            this.tabManager.checkAndReloadWebPage();
        } else {
            expandTabView(true);
        }
    }

    private void expandTabView(boolean z) {
        this.eventListener.expandTabView(z);
        if (this.tabExpanded == z) {
            return;
        }
        this.tabExpanded = z;
        ObjectAnimator objectAnimator = this.ivIndicatorAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        if (z) {
            this.ivIndicator.setVisibility(View.GONE);
        } else {
            this.ivIndicator.setVisibility(View.VISIBLE);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ivIndicator, "alpha", 0.0f, 1.0f);
            this.ivIndicatorAnimator = ofFloat;
            ofFloat.setDuration(200L);
            this.ivIndicatorAnimator.start();
        }
        if (z) {
            AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.PRESS_ENABLE_IMMERSIVE);
        } else {
            AnalyticsHelper.logEvent(AnalyticsHelper.DAppWebEvent.PRESS_QUIT_IMMERSIVE);
        }
    }

    private FragmentStateAdapter getAdapter() {
        return new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return 2;
            }

            @Override
            public Fragment createFragment(int i) {
                return fragments.get(i % getItemCount());
            }

            @Override
            public long getItemId(int i) {
                return fragments.get(i % getItemCount()).hashCode();
            }

            @Override
            public boolean containsItem(long j) {
                return itemHashCodes.contains(Long.valueOf(j));
            }
        };
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentDappHomeV2Binding inflate = FragmentDappHomeV2Binding.inflate(layoutInflater, viewGroup, false);
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
        this.viewPager = this.binding.frameContent;
        this.ivIndicator = this.binding.ivIndicator;
    }

    public boolean handleBackPressed() {
        DAppBrowserFragment dAppBrowserFragment;
        ViewPager2 viewPager2 = this.viewPager;
        if ((viewPager2 == null || viewPager2.getCurrentItem() == 1) && (dAppBrowserFragment = this.dAppBrowserFragment) != null) {
            return dAppBrowserFragment.handleBackPressed();
        }
        return false;
    }

    private void recreateFragments() {
        this.dAppMainFragment = new DAppMainFragment();
        this.dAppBrowserFragment = new DAppBrowserFragment();
        this.fragments.add(0, this.dAppMainFragment);
        this.fragments.add(1, this.dAppBrowserFragment);
        this.itemHashCodes.add(Long.valueOf(this.dAppMainFragment.hashCode()));
        this.itemHashCodes.add(Long.valueOf(this.dAppBrowserFragment.hashCode()));
        this.dAppBrowserFragment.registerWebScrollListener(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$recreateFragments$4(obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.dAppMainFragment.addItemClickListener(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$recreateFragments$6((DappBean) obj);
            }
        });
    }

    public void lambda$recreateFragments$4(Object obj) {
        BrowserWebView webView = this.tabManager.getWebView();
        if ((webView == null || !WebConstant.LANDSCAPE.equals(webView.getScreenMode())) && (obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            expandTabView(false);
        }
    }

    public void lambda$recreateFragments$6(final DappBean dappBean) throws Exception {
        if (StringTronUtil.isEmpty(dappBean.getHomeUrl()) || "\"\"".equals(dappBean.getHomeUrl())) {
            return;
        }
        DAppDialog.showOutsideLinkWarning(getIContext(), dappBean.getHomeUrl(), new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$recreateFragments$5(dappBean, (Boolean) obj);
            }
        });
    }

    public void lambda$recreateFragments$5(DappBean dappBean, Boolean bool) throws Exception {
        DAppBrowserFragment dAppBrowserFragment;
        if (!bool.booleanValue() || (dAppBrowserFragment = this.dAppBrowserFragment) == null) {
            return;
        }
        dAppBrowserFragment.handleNewDAppClicked(dappBean, true);
        showPage(1);
    }

    public void addEventListener(EventListener eventListener) {
        if (eventListener == null) {
            this.eventListener = new EventListener() {
                @Override
                public void expandTabView(boolean z) {
                }
            };
        } else {
            this.eventListener = eventListener;
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        DAppBrowserFragment dAppBrowserFragment = this.dAppBrowserFragment;
        if (dAppBrowserFragment != null) {
            dAppBrowserFragment.onActivityResult(i, i2, intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        DAppBrowserFragment dAppBrowserFragment = this.dAppBrowserFragment;
        if (dAppBrowserFragment != null) {
            dAppBrowserFragment.onRequestPermissionsResult(i, strArr, iArr);
        }
    }
}
