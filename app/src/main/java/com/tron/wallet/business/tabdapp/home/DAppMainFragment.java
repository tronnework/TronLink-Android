package com.tron.wallet.business.tabdapp.home;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.OkHttpManager;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.browser.BrowserTabManager;
import com.tron.wallet.business.tabdapp.browser.controller.BrowserBookMarkManager;
import com.tron.wallet.business.tabdapp.browser.search.BrowserSearchActivity;
import com.tron.wallet.business.tabdapp.browser.tabs.BrowserTabsManagerActivity;
import com.tron.wallet.business.tabdapp.component.DAppTitleView;
import com.tron.wallet.business.tabdapp.component.ISnapshot;
import com.tron.wallet.business.tabdapp.home.DAppMainContract;
import com.tron.wallet.business.tabdapp.home.DAppMainFragment;
import com.tron.wallet.business.tabdapp.home.adapter.BannerAdapter;
import com.tron.wallet.business.tabdapp.home.adapter.DappQuickAdapter;
import com.tron.wallet.business.tabdapp.home.adapter.RecommendListItemDecoration;
import com.tron.wallet.business.tabdapp.home.bean.DappBannerBean;
import com.tron.wallet.business.tabdapp.home.bean.DappBean;
import com.tron.wallet.business.tabdapp.home.utils.AdapterNoDoubleClickListener;
import com.tron.wallet.business.tabdapp.home.utils.BaseMenuClickCallback;
import com.tron.wallet.business.tabdapp.home.utils.BrowserMenuPopupView;
import com.tron.wallet.business.tabdapp.home.utils.DAppAnalyseUtils;
import com.tron.wallet.business.tabdapp.home.utils.DAppUrlUtils;
import com.tron.wallet.business.walletmanager.selectwallet.search.SelectWalletBottomPopup;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.components.BannerRecyclerView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.components.indicator.DotViewPagerIndicator;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.FragmentBrowserHomeBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import org.tron.walletserver.Wallet;
public class DAppMainFragment extends BaseFragment<DAppMainPresenter, DAppMainModel> implements DAppMainContract.View, ISnapshot {
    private static final String TAG = "BrowserDAppMainFragment";
    AppBarLayout appBarLayout;
    private BannerAdapter bannerAdapter;
    View bannerHolderView;
    private int bannerTargetPosition;
    BannerRecyclerView bannerView;
    private FragmentBrowserHomeBinding binding;
    View bookTab;
    View browserSearchView;
    CoordinatorLayout coordinatorLayout;
    DAppTitleView dAppTitleView;
    XTabLayoutV2 dappMenuTabs;
    ViewPager2 dappRecommendBookViewPager;
    XTabLayoutV2 dappTabs;
    ViewPager2 dappViewPager;
    DotViewPagerIndicator dotIndicator;
    private DAppListFragment[] fragments;
    View headerContainerView;
    View llListContainerView;
    int[] menuTabs;
    private Consumer<DappBean> outerItemClickListener;
    PtrHTFrameLayout ptrLayout;
    private QuickInnerFragment[] quickInnerFragments;
    View toolbar;
    private final List<AppBarLayout.OnOffsetChangedListener> offsetChangedListeners = new ArrayList();
    private boolean needToRefresh = false;

    public enum DAppUIType {
        MOST_VISIT,
        ALL,
        NONE,
        BOOK
    }

    public void addItemClickListener(Consumer<DappBean> consumer) {
        this.outerItemClickListener = consumer;
    }

    @Override
    protected void processData() {
        try {
            setupBannerView();
            setupViewPager();
            setupMenuViewPager();
            setupAppBar();
            final View headerView = this.ptrLayout.getHeaderView();
            if (headerView != null) {
                headerView.post(new Runnable() {
                    @Override
                    public final void run() {
                        lambda$processData$0(headerView);
                    }
                });
            }
            this.ptrLayout.setPtrHandler(new fun1());
            if (SpAPI.THIS.getAppStatus().isMainland()) {
                this.browserSearchView.setVisibility(View.INVISIBLE);
            } else {
                this.browserSearchView.setVisibility(View.VISIBLE);
            }
            this.browserSearchView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    BrowserSearchActivity.startActivity(getActivity());
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppMain.CLICK_SEARCH);
                }
            });
            ((DAppMainPresenter) this.mPresenter).refresh();
            this.dAppTitleView.setClickTabsListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    BrowserTabsManagerActivity.start(getIContext());
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppMain.CLICK_MULTI_TABS);
                }
            }).setClickMenuListener(new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    BrowserTabManager browserTabManager = BrowserTabManager.getInstance();
                    if (browserTabManager != null && browserTabManager.getWebView() != null) {
                        boolean isDebugAble = browserTabManager.getWebView().isDebugAble();
                        String url = browserTabManager.getWebView().getUrl();
                        BrowserMenuPopupView.showUp(getIContext(), dAppTitleView, 0, isDebugAble, false, BrowserBookMarkManager.getInstance().isExists(url) || BrowserBookMarkManager.getInstance().isExists(DAppUrlUtils.removeUrlSuffixParameter(url)), getMenuClickCallback());
                    }
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppMain.CLICK_MORE);
                }
            });
            AnalyticsHelper.logEvent(AnalyticsHelper.BrowserVisit.ENTER_DAPP_HOME);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$processData$0(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = XPopupUtils.dp2px(getIContext(), 88.0f);
        view.setPadding(view.getPaddingLeft(), XPopupUtils.dp2px(getIContext(), 28.0f), view.getPaddingRight(), view.getPaddingBottom());
        view.setLayoutParams(layoutParams);
    }

    public class fun1 implements PtrHandler {
        fun1() {
        }

        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            DAppMainFragment dAppMainFragment = DAppMainFragment.this;
            return dAppMainFragment.getAppBarOffset(dAppMainFragment.appBarLayout) == 0 && !bannerView.isDragging();
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            ((DAppMainPresenter) mPresenter).refresh();
            ptrFrameLayout.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    DAppMainFragment.1.this.lambda$onRefreshBegin$0();
                }
            }, 1500L);
        }

        public void lambda$onRefreshBegin$0() {
            ptrLayout.refreshComplete();
        }
    }

    public void animTab() {
        if (getView() == null) {
            return;
        }
        getView().setScaleX(0.8f);
        getView().setScaleY(0.8f);
        getView().postOnAnimation(new Runnable() {
            @Override
            public final void run() {
                lambda$animTab$1();
            }
        });
    }

    public void lambda$animTab$1() {
        getView().animate().scaleX(1.0f).scaleY(1.0f).setDuration(300L).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mPresenter != 0 && this.needToRefresh) {
            ((DAppMainPresenter) this.mPresenter).getRecommendDapp();
            ((DAppMainPresenter) this.mPresenter).getBookDapp();
        }
        final int appBarOffset = getAppBarOffset(this.appBarLayout);
        if (appBarOffset >= 0) {
            Collection.-EL.stream(this.offsetChangedListeners).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$onResume$2(appBarOffset, (AppBarLayout.OnOffsetChangedListener) obj);
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        this.dAppTitleView.updateTabCount();
    }

    public void lambda$onResume$2(int i, AppBarLayout.OnOffsetChangedListener onOffsetChangedListener) {
        onOffsetChangedListener.onOffsetChanged(this.appBarLayout, i);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (this.needToRefresh) {
            return;
        }
        this.needToRefresh = true;
    }

    private void setupAppBar() {
        addAppBarOffsetListener(this.dAppTitleView);
        Collection.-EL.stream(this.offsetChangedListeners).forEach(new java.util.function.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$setupAppBar$3((AppBarLayout.OnOffsetChangedListener) obj);
            }

            public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.toolbar.post(new Runnable() {
            @Override
            public final void run() {
                lambda$setupAppBar$4();
            }
        });
    }

    public void lambda$setupAppBar$3(AppBarLayout.OnOffsetChangedListener onOffsetChangedListener) {
        this.appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener);
    }

    public void lambda$setupAppBar$4() {
        ViewGroup.LayoutParams layoutParams = this.toolbar.getLayoutParams();
        layoutParams.width = this.dAppTitleView.getMeasuredHeight();
        this.toolbar.setLayoutParams(layoutParams);
    }

    private void setupViewPager() {
        final int[] iArr = {R.string.recommend_application};
        DAppUIType[] dAppUITypeArr = {DAppUIType.ALL};
        this.fragments = new DAppListFragment[1];
        DAppListFragment dAppListFragment = new DAppListFragment();
        dAppListFragment.setListener(getOnDAppClickListener(dAppUITypeArr[0]));
        dAppListFragment.setOnClickReloadListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (mPresenter == 0 || dappViewPager == null) {
                    return;
                }
                ((DAppMainPresenter) mPresenter).refresh();
            }
        });
        this.fragments[0] = dAppListFragment;
        this.dappViewPager.setAdapter(new BaseFragmentAdapter(this) {
            @Override
            public Fragment createFragment(int i) {
                return fragments[i % fragments.length];
            }

            @Override
            public int getItemCount() {
                return iArr.length;
            }

            @Override
            public CharSequence getPageTitle(int i) {
                Context iContext = getIContext();
                int[] iArr2 = iArr;
                return iContext.getString(iArr2[i % iArr2.length]);
            }
        });
        this.dappTabs.setupWithViewPager(this.dappViewPager);
        this.dappViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int i) {
                if (mPresenter != 0) {
                    ((DAppMainPresenter) mPresenter).getDappList(i);
                }
            }
        });
    }

    private void setupMenuViewPager() {
        int[] iArr = {R.string.dapp_recommend, R.string.favorite_title};
        this.menuTabs = iArr;
        this.quickInnerFragments = new QuickInnerFragment[iArr.length];
        for (int i = 0; i < this.menuTabs.length; i++) {
            QuickInnerFragment quickInnerFragment = new QuickInnerFragment();
            quickInnerFragment.setListener(getOnQuickDAppClickListener());
            this.quickInnerFragments[i] = quickInnerFragment;
        }
        this.dappRecommendBookViewPager.setAdapter(new BaseFragmentAdapter(this) {
            @Override
            public Fragment createFragment(int i2) {
                return quickInnerFragments[i2 % quickInnerFragments.length];
            }

            @Override
            public int getItemCount() {
                return menuTabs.length;
            }

            @Override
            public CharSequence getPageTitle(int i2) {
                return getIContext().getString(menuTabs[i2 % menuTabs.length]);
            }
        });
        this.dappRecommendBookViewPager.setUserInputEnabled(false);
        this.dappMenuTabs.setupWithViewPager(this.dappRecommendBookViewPager);
        this.dappRecommendBookViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int i2) {
                if (mPresenter != 0) {
                    int i3 = 0;
                    if ((menuTabs.length != 2 || i2 != 0) && ((menuTabs.length == 2 && i2 == 1) || (menuTabs.length == 1 && i2 == 0))) {
                        i3 = 1;
                    }
                    ((DAppMainPresenter) mPresenter).getRecommendDapp(i3);
                }
            }
        });
    }

    private void setupBannerView() {
        getLifecycle().addObserver(this.bannerView);
        BannerAdapter bannerAdapter = new BannerAdapter();
        this.bannerAdapter = bannerAdapter;
        this.bannerView.setAdapter(bannerAdapter);
        this.bannerView.attachToPtrFrameLayout(this.ptrLayout);
        this.bannerAdapter.setOnItemClickListener(getOnDAppClickListener(DAppUIType.NONE));
        this.bannerView.setOnPageChangeCallback(new fun10());
    }

    public class fun10 extends ViewPager2.OnPageChangeCallback {
        fun10() {
        }

        @Override
        public void onPageScrolled(int i, final float f, int i2) {
            if (f == 0.0f && i2 == 0) {
                return;
            }
            int i3 = i + 1;
            int size = bannerAdapter.getData().size();
            int i4 = i % size;
            int i5 = i3 % size;
            if (i4 < 0 || i5 < 0) {
                return;
            }
            final DappBannerBean dappBannerBean = bannerAdapter.getData().get(i4);
            final DappBannerBean dappBannerBean2 = bannerAdapter.getData().get(i5);
            bannerView.postOnAnimation(new Runnable() {
                @Override
                public final void run() {
                    DAppMainFragment.10.this.lambda$onPageScrolled$0(f, dappBannerBean, dappBannerBean2);
                }
            });
        }

        public void lambda$onPageScrolled$0(float f, DappBannerBean dappBannerBean, DappBannerBean dappBannerBean2) {
            ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            try {
                BannerRecyclerView bannerRecyclerView = bannerView;
                Integer valueOf = Integer.valueOf(Color.parseColor("#" + dappBannerBean.getBackgroundColor()));
                bannerRecyclerView.setBackgroundColor(((Integer) argbEvaluator.evaluate(f, valueOf, Integer.valueOf(Color.parseColor("#" + dappBannerBean2.getBackgroundColor())))).intValue());
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }

        @Override
        public void onPageSelected(int i) {
            dotIndicator.onPositionChange(i);
            DAppMainFragment dAppMainFragment = DAppMainFragment.this;
            dAppMainFragment.bannerTargetPosition = i % dAppMainFragment.bannerAdapter.getData().size();
            try {
                Bundle bundle = new Bundle();
                DappBannerBean dappBannerBean = bannerAdapter.getData().get(i % bannerAdapter.getData().size());
                String name = dappBannerBean.getName();
                bundle.putString(AnalyticsHelper.BrowserVisit.KEY_CLICK_BANNER_NAME, name);
                AnalyticsHelper.logEvent("browserbanner_show_" + name, bundle);
                if (TextUtils.isEmpty(dappBannerBean.getFeedbackUrl())) {
                    return;
                }
                FirebasePerfOkHttpClient.enqueue(OkHttpManager.getInstance().build().newCall(new Request.Builder().url(dappBannerBean.getFeedbackUrl()).build()), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException iOException) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                    }
                });
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentBrowserHomeBinding inflate = FragmentBrowserHomeBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        PtrHTFrameLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.bookTab = this.binding.headerContainer.tabBook;
        this.bannerView = this.binding.headerContainer.bannerView;
        this.browserSearchView = this.binding.headerContainer.searchView;
        this.dappTabs = this.binding.dappTabs;
        this.dappViewPager = this.binding.dappViewPager;
        this.dappMenuTabs = this.binding.headerContainer.dappMenuTabs;
        this.dappRecommendBookViewPager = this.binding.headerContainer.dappRecommendBookViewPager;
        this.appBarLayout = this.binding.appBar;
        this.headerContainerView = this.binding.headerContainer.rlRoot;
        this.llListContainerView = this.binding.llListContainer;
        this.coordinatorLayout = this.binding.coordinator;
        this.dotIndicator = this.binding.headerContainer.dotIndicator;
        this.dAppTitleView = this.binding.dappTitleView;
        this.toolbar = this.binding.dappToolbar;
        this.bannerHolderView = this.binding.headerContainer.ivBannerHolder;
        this.ptrLayout = this.binding.rcFrame;
    }

    @Override
    public void onRequestBannersComplete(boolean z, List<DappBannerBean> list) {
        if (!z || list == null || list.isEmpty()) {
            toast(getString(R.string.time_out));
            return;
        }
        this.bannerHolderView.setVisibility(View.GONE);
        int i = this.bannerTargetPosition;
        if (i >= list.size()) {
            i = list.size() - 1;
        }
        this.bannerAdapter.setNewData(list);
        try {
            BannerRecyclerView bannerRecyclerView = this.bannerView;
            bannerRecyclerView.setBackgroundColor(Color.parseColor("#" + list.get(i).getBackgroundColor()));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        this.dotIndicator.onChange(list.size(), i);
        this.dotIndicator.setVisibility(list.size() > 1 ? 0 : 4);
        this.bannerView.startRolling();
    }

    @Override
    public void onStartRequestDappList() {
        DAppListFragment[] dAppListFragmentArr = this.fragments;
        if (dAppListFragmentArr == null || dAppListFragmentArr.length <= 0) {
            return;
        }
        int i = 0;
        while (true) {
            DAppListFragment[] dAppListFragmentArr2 = this.fragments;
            if (i >= dAppListFragmentArr2.length) {
                return;
            }
            dAppListFragmentArr2[i].updateDappListLoading();
            i++;
        }
    }

    @Override
    public void onRequestDappListComplete(boolean z, int i, List<DappBean> list) {
        if (!z || list == null) {
            toast(getString(R.string.time_out));
        }
        DAppListFragment[] dAppListFragmentArr = this.fragments;
        if (dAppListFragmentArr == null || i >= dAppListFragmentArr.length) {
            return;
        }
        dAppListFragmentArr[i].updateNewData(list);
    }

    @Override
    public void onRecommendDappComplete(boolean z, List<DappBean> list) {
        if (list == null || list.size() == 0) {
            this.dappRecommendBookViewPager.setCurrentItem(1);
            this.bookTab.setVisibility(View.VISIBLE);
            this.dappMenuTabs.setVisibility(View.GONE);
            return;
        }
        this.bookTab.setVisibility(View.INVISIBLE);
        this.dappMenuTabs.setVisibility(View.VISIBLE);
        this.quickInnerFragments[0].updateNewData(list);
    }

    @Override
    public void onBookDappComplete(boolean z, List<DappBean> list, boolean z2) {
        this.quickInnerFragments[this.menuTabs.length - 1].updateNewData(list, z2, true);
    }

    private AdapterNoDoubleClickListener<DappBean> getOnDAppClickListener(DAppUIType dAppUIType) {
        return new AdapterNoDoubleClickListener<>(new Consumer<DappBean>(dAppUIType) {
            final DAppUIType uiType;
            final DAppUIType val$type;

            {
                this.val$type = dAppUIType;
                this.uiType = dAppUIType;
            }

            @Override
            public void accept(DappBean dappBean) throws Exception {
                if (dappBean == null || getActivity() == null) {
                    return;
                }
                if ((IRequest.isNile() || IRequest.isShasta()) && (StringTronUtil.isEmpty(dappBean.getHomeUrl()) || "\"\"".equals(dappBean.getHomeUrl()))) {
                    return;
                }
                ((DAppMainPresenter) mPresenter).insertVisitedDapp(dappBean);
                if (this.uiType == DAppUIType.MOST_VISIT) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppMain.CLICK_MOST_VISIT);
                    DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.DAppMain.CLICK_MOST_VISIT, dappBean.getHomeUrl());
                } else if (this.uiType == DAppUIType.ALL) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppMain.CLICK_DAPP_IN_ALL);
                    DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.DAppMain.CLICK_DAPP_IN_ALL, dappBean.getHomeUrl());
                } else if (this.uiType == DAppUIType.NONE) {
                    Bundle bundle = new Bundle();
                    bundle.putString(AnalyticsHelper.BrowserVisit.KEY_CLICK_BANNER_NAME, dappBean.getName());
                    AnalyticsHelper.logEvent("browserbanner_click_" + dappBean.getName(), bundle);
                    DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.BrowserVisit.CLICK_BROWSER_BANNER, dappBean.getHomeUrl());
                    if (TextUtils.isEmpty(dappBean.getClkUrl())) {
                        return;
                    }
                    FirebasePerfOkHttpClient.enqueue(OkHttpManager.getInstance().build().newCall(new Request.Builder().url(dappBean.getClkUrl()).build()), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException iOException) {
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                        }
                    });
                }
            }
        }, this.outerItemClickListener);
    }

    private AdapterNoDoubleClickListener<DappBean> getOnQuickDAppClickListener() {
        return new AdapterNoDoubleClickListener<>(new Consumer<DappBean>() {
            @Override
            public void accept(DappBean dappBean) throws Exception {
                DAppUIType dAppUIType;
                if (dappBean == null || getActivity() == null) {
                    return;
                }
                int currentItem = dappRecommendBookViewPager.getCurrentItem();
                if (menuTabs.length == 2 && currentItem == 0) {
                    dAppUIType = DAppUIType.MOST_VISIT;
                } else {
                    dAppUIType = ((menuTabs.length == 2 && currentItem == 1) || (menuTabs.length == 1 && currentItem == 0)) ? DAppUIType.BOOK : null;
                }
                ((DAppMainPresenter) mPresenter).insertVisitedDapp(dappBean);
                if (dAppUIType == DAppUIType.MOST_VISIT) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.DAppMain.CLICK_MOST_VISIT);
                    DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.DAppMain.CLICK_MOST_VISIT, dappBean.getHomeUrl());
                    return;
                }
                DAppAnalyseUtils.reportDAppHost(AnalyticsHelper.DAppMain.DAPP_MAIN_TAG, dappBean.getHomeUrl());
            }
        }, this.outerItemClickListener);
    }

    @Override
    public Bitmap takeSnapshot(int i, int i2, int i3) {
        CoordinatorLayout coordinatorLayout = this.coordinatorLayout;
        if (coordinatorLayout == null) {
            return BitmapFactory.decodeResource(getResources(), R.mipmap.web_tab_default_icon);
        }
        Bitmap createBitmap = Bitmap.createBitmap(coordinatorLayout.getWidth(), this.coordinatorLayout.getHeight(), Bitmap.Config.ARGB_8888);
        this.coordinatorLayout.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public void resetState() {
        try {
            AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) ((CoordinatorLayout.LayoutParams) this.appBarLayout.getLayoutParams()).getBehavior();
            if (behavior != null) {
                behavior.setTopAndBottomOffset(0);
                this.appBarLayout.requestLayout();
            }
            DesugarArrays.stream(this.fragments).forEach(new java.util.function.Consumer() {
                @Override
                public final void accept(Object obj) {
                    ((DAppListFragment) obj).resetState();
                }

                public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
            this.appBarLayout.postDelayed(new Runnable() {
                @Override
                public final void run() {
                    lambda$resetState$5();
                }
            }, 200L);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$resetState$5() {
        this.dappViewPager.setCurrentItem(0);
    }

    public void addAppBarOffsetListener(AppBarLayout.OnOffsetChangedListener onOffsetChangedListener) {
        this.offsetChangedListeners.add(onOffsetChangedListener);
    }

    public class fun13 extends BaseMenuClickCallback {
        public static void lambda$onClickSwitchWallet$0(Wallet wallet) {
        }

        fun13() {
        }

        @Override
        public void onClickNewTab(Context context) {
            super.onClickNewTab(context);
            animTab();
        }

        @Override
        public void onClickRefresh() {
            super.onClickRefresh();
            if (mPresenter != 0) {
                bannerView.stopRolling();
                ((DAppMainPresenter) mPresenter).refresh();
            }
        }

        @Override
        public void onClickSwitchWallet() {
            super.onClickSwitchWallet();
            SelectWalletBottomPopup.showPopup(getContext(), WalletUtils.getSelectedPublicWallet(), new SelectWalletBottomPopup.OnClickListener() {
                @Override
                public final void onClick(Wallet wallet) {
                    DAppMainFragment.13.lambda$onClickSwitchWallet$0(wallet);
                }
            }, null);
        }

        @Override
        public void onClickClose() {
            super.onClickClose();
            animTab();
        }
    }

    public BaseMenuClickCallback getMenuClickCallback() {
        return new fun13();
    }

    public DappBean findInCacheDApp(DappBean dappBean) {
        if (this.mPresenter == 0) {
            return null;
        }
        return ((DAppMainPresenter) this.mPresenter).findInCache(dappBean);
    }

    public static class QuickInnerFragment extends Fragment {
        private DappQuickAdapter adapter;
        private View noBookDataView;
        private BaseQuickAdapter.OnItemClickListener onItemClicked;
        private RecyclerView rvContent;

        public void setListener(BaseQuickAdapter.OnItemClickListener onItemClickListener) {
            this.onItemClicked = onItemClickListener;
        }

        @Override
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.dapp_list_quick_inner_fragment, viewGroup);
        }

        @Override
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.rvContent = (RecyclerView) view.findViewById(R.id.rv_content);
            this.noBookDataView = view.findViewById(R.id.no_book_data_layout);
            RecyclerView recyclerView = this.rvContent;
            if (recyclerView == null) {
                return;
            }
            recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), 0, false));
            RecyclerView recyclerView2 = this.rvContent;
            DappQuickAdapter dappQuickAdapter = new DappQuickAdapter();
            this.adapter = dappQuickAdapter;
            recyclerView2.setAdapter(dappQuickAdapter);
            this.rvContent.addItemDecoration(new RecommendListItemDecoration());
            this.adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                    if (((DappQuickAdapter) baseQuickAdapter).hasMore() && i == 10) {
                        BrowserTabsManagerActivity.start(QuickInnerFragment.this.getContext(), 1);
                    } else {
                        QuickInnerFragment.this.onItemClicked.onItemClick(baseQuickAdapter, view2, i);
                    }
                }
            });
            this.noBookDataView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view2) {
                    CommonWebActivityV3.start((Context) QuickInnerFragment.this.getActivity(), IRequest.getHowToBookDUrl(), "", -2, false);
                }
            });
        }

        public void updateNewData(List<DappBean> list) {
            RecyclerView recyclerView = this.rvContent;
            if (recyclerView != null) {
                recyclerView.setVisibility(View.VISIBLE);
            }
            DappQuickAdapter dappQuickAdapter = this.adapter;
            if (dappQuickAdapter != null) {
                dappQuickAdapter.setNewData(list);
                this.adapter.notifyDataSetChanged();
            }
        }

        public void resetState() {
            RecyclerView recyclerView = this.rvContent;
            if (recyclerView != null) {
                recyclerView.smoothScrollToPosition(0);
            }
        }

        public void updateNewData(List<DappBean> list, boolean z, boolean z2) {
            if (z2 && (list.size() == 0 || list == null)) {
                RecyclerView recyclerView = this.rvContent;
                if (recyclerView != null) {
                    recyclerView.setVisibility(View.GONE);
                    this.noBookDataView.setVisibility(View.VISIBLE);
                    return;
                }
                return;
            }
            RecyclerView recyclerView2 = this.rvContent;
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(View.VISIBLE);
                this.noBookDataView.setVisibility(View.GONE);
            }
            if (!z) {
                updateNewData(list);
                return;
            }
            this.adapter.setHasMore(true);
            updateNewData(list);
        }
    }

    public int getAppBarOffset(AppBarLayout appBarLayout) {
        CoordinatorLayout.LayoutParams layoutParams;
        CoordinatorLayout.Behavior behavior;
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (appBarLayout2 == null || (layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout2.getLayoutParams()) == null || (behavior = layoutParams.getBehavior()) == null) {
            return -1;
        }
        return ((AppBarLayout.Behavior) behavior).getTopAndBottomOffset();
    }
}
