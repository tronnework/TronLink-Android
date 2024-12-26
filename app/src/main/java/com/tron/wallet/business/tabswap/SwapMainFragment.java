package com.tron.wallet.business.tabswap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.business.finance.FinanceFragment;
import com.tron.wallet.business.tabmarket.home.LazyLoadFragment;
import com.tron.wallet.business.tabmy.myhome.settings.ServerSelectActivity;
import com.tron.wallet.business.tabswap.SwapMainContract;
import com.tron.wallet.business.tabswap.mvp.SwapFragment;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.AutoSelectServer;
import com.tron.wallet.common.utils.ChannelUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.LayoutSwapMainBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
public class SwapMainFragment extends LazyLoadFragment<SwapMainPresenter, SwapMainModel> implements SwapMainContract.View {
    public static final String TYPE = "type";
    public static final int TYPE_MARKET = 1;
    public static final int TYPE_SWAP = 2;
    private LayoutSwapMainBinding binding;
    FinanceFragment financeFragment;
    View llBg;
    int[] pageTitles;
    RelativeLayout rlNetNotice;
    RelativeLayout rlServerNotice;
    SwapFragment swapFragment;
    TextView tabFinance;
    TextView tabSwap;
    TextView tvSwitchServer;
    View viewBackground;
    ViewPager viewPager;
    ArrayList<Fragment> fragments = new ArrayList<>();
    private RxManager rxManager = new RxManager();

    public enum PageStatus {
        ShowMask,
        NoMask
    }

    public enum TabStatus {
        HideFinancialTab,
        Normal
    }

    @Override
    protected void processData() {
    }

    public void setDataType(int i) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putInt("type", i);
        setArguments(arguments);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        FinanceFragment financeFragment = this.financeFragment;
        if (financeFragment != null && financeFragment.isAdded()) {
            childFragmentManager.putFragment(bundle, "SwapMainFragment:0", this.financeFragment);
        }
        SwapFragment swapFragment = this.swapFragment;
        if (swapFragment == null || !swapFragment.isAdded()) {
            return;
        }
        childFragmentManager.putFragment(bundle, "SwapMainFragment:1", this.swapFragment);
    }

    static class fun3 {
        static final int[] $SwitchMap$com$tron$wallet$business$tabswap$SwapMainFragment$TabStatus;

        static {
            int[] iArr = new int[TabStatus.values().length];
            $SwitchMap$com$tron$wallet$business$tabswap$SwapMainFragment$TabStatus = iArr;
            try {
                iArr[TabStatus.HideFinancialTab.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$tabswap$SwapMainFragment$TabStatus[TabStatus.Normal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override
    public void showPage(TabStatus tabStatus, PageStatus pageStatus) {
        int i = fun3.$SwitchMap$com$tron$wallet$business$tabswap$SwapMainFragment$TabStatus[tabStatus.ordinal()];
        if (i == 1) {
            this.fragments.add(this.swapFragment);
            this.tabFinance.setVisibility(View.GONE);
            setPageAdapter();
            updateTabView(1);
        } else if (i == 2) {
            FinanceFragment financeFragment = this.financeFragment;
            if (financeFragment != null) {
                this.fragments.add(financeFragment);
            } else {
                updateTabView(1);
            }
            this.fragments.add(this.swapFragment);
            setPageAdapter();
            showFragment(2);
        }
        if (pageStatus == PageStatus.ShowMask) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    lambda$showPage$0();
                }
            }, 1500L);
        }
    }

    public void lambda$showPage$0() {
        FinanceFragment financeFragment = this.financeFragment;
        if (financeFragment != null) {
            financeFragment.showNotSupportView();
        }
        SwapFragment swapFragment = this.swapFragment;
        if (swapFragment != null) {
            swapFragment.showNotSupportView();
        }
    }

    @Override
    protected void processData(Bundle bundle) {
        showLoadingPage();
        initFragments(bundle);
        setPageAdapter();
        ((SwapMainPresenter) this.mPresenter).getStatus();
        addTabClick();
        this.tvSwitchServer.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                go(ServerSelectActivity.class);
            }
        });
        this.rxManager.on(Event.NET_CHANGE, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$1(obj);
            }
        });
        this.rxManager.on(Event.SWITCH_SERVER, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$2(obj);
            }
        });
        this.rxManager.on(Event.ENTER_SWAP, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$3(obj);
            }
        });
    }

    public void lambda$processData$1(Object obj) throws Exception {
        if (TronConfig.hasNet) {
            this.rlNetNotice.setVisibility(View.GONE);
            return;
        }
        this.rlNetNotice.setVisibility(View.VISIBLE);
        this.rlServerNotice.setVisibility(View.GONE);
    }

    public void lambda$processData$2(Object obj) throws Exception {
        if (obj instanceof Integer) {
            setNotice(((Integer) obj).intValue());
        }
    }

    public void lambda$processData$3(Object obj) throws Exception {
        LogUtils.i("showFragment-updateTabView--:asset_enter_swap");
        if (this.viewPager == null || !isOpenFinance()) {
            return;
        }
        updateTabView(1);
        this.viewPager.setCurrentItem(1, false);
    }

    public void initFragments(Bundle bundle) {
        if (isOpenFinance()) {
            this.tabFinance.setVisibility(View.VISIBLE);
        } else {
            updateTabView(1);
            this.tabFinance.setVisibility(View.GONE);
        }
        this.fragments.clear();
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (bundle != null) {
            this.financeFragment = (FinanceFragment) childFragmentManager.getFragment(bundle, "SwapMainFragment:0");
            this.swapFragment = (SwapFragment) childFragmentManager.getFragment(bundle, "SwapMainFragment:1");
        }
        if (isOpenFinance() && this.financeFragment == null) {
            this.financeFragment = new FinanceFragment();
        }
        if (this.swapFragment == null) {
            this.swapFragment = new SwapFragment();
        }
    }

    public void setPageAdapter() {
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), this.fragments);
        this.viewPager.setOffscreenPageLimit(2);
        this.viewPager.setAdapter(myFragmentPagerAdapter);
    }

    private boolean isOpenFinance() {
        if (ChannelUtils.OFFICIAL.equals(ChannelUtils.getChannel(getActivity()))) {
            return false;
        }
        if (IRequest.isTest()) {
            return true;
        }
        return !IRequest.isNile() && SpAPI.THIS.getFinanceShow() == 1;
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setType(2);
        LayoutSwapMainBinding inflate = LayoutSwapMainBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        getActivity().getWindow().setStatusBarColor(getContext().getResources().getColor(R.color.white));
        mappingId(root);
        return root;
    }

    public void mappingId(View view) {
        LayoutSwapMainBinding bind = LayoutSwapMainBinding.bind(view);
        this.binding = bind;
        this.tabFinance = bind.tabFinance;
        this.tabSwap = this.binding.tabSwap;
        this.viewPager = this.binding.viewpagerMain;
        this.rlNetNotice = this.binding.rlNetNotice;
        this.rlServerNotice = this.binding.rlServerNotice;
        this.tvSwitchServer = this.binding.tvSwitchServer;
        this.viewBackground = this.binding.viewBackground;
        this.llBg = this.binding.llBg;
    }

    @Override
    public void onResume() {
        super.onResume();
        setNotice(AutoSelectServer.getInstance().getServerState());
        AnalyticsHelper.logEvent(AnalyticsHelper.HomePage.ENTER_HOME_PAGE_TAB_MARKET);
    }

    private void showFragment(int i) {
        if (this.viewPager != null && i == 2 && isAdded()) {
            updateTabView(1);
            if (isOpenFinance()) {
                this.viewPager.setCurrentItem(1, false);
            } else {
                this.viewPager.setCurrentItem(0, false);
            }
        }
    }

    public void addTabClick() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.tab_finance) {
                    viewPager.setCurrentItem(0);
                    updateTabView(0);
                    AnalyticsHelper.logEvent(AnalyticsHelper.MarketPage.CLICK_MARKET_PAGE_SWAP_SWAP);
                } else if (view.getId() == R.id.tab_swap) {
                    viewPager.setCurrentItem(1);
                    updateTabView(1);
                    AnalyticsHelper.logEvent(AnalyticsHelper.MarketPage.CLICK_MARKET_PAGE_SWAP_MARKETS);
                }
            }
        };
        this.tabFinance.setOnClickListener(onClickListener);
        this.tabSwap.setOnClickListener(onClickListener);
    }

    public void updateTabView(int i) {
        if (i == 0) {
            refreshColor272E35(this.llBg);
            this.tabSwap.setTextColor(getResources().getColor(R.color.white_30));
            this.tabFinance.setTextColor(getResources().getColor(R.color.white));
            return;
        }
        refreshColorWhite(this.llBg);
        this.tabSwap.setTextColor(getResources().getColor(R.color.black_02));
        this.tabFinance.setTextColor(getResources().getColor(R.color.gray_02_30));
    }

    public void refreshColor272E35(View... viewArr) {
        for (View view : viewArr) {
            view.setBackgroundColor(getResources().getColor(R.color.black_272E35));
        }
    }

    public void refreshColorWhite(View... viewArr) {
        for (View view : viewArr) {
            view.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    protected void firstLoad() {
        StatusBarUtils.setLightStatusBar(getActivity(), true);
        if (this.isFirstLoad) {
            Bundle arguments = getArguments();
            showFragment(arguments != null ? arguments.getInt("type") : 1);
        }
    }

    @Override
    protected void refreshLoad() {
        firstLoad();
    }

    @Override
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            StatusBarUtils.setLightStatusBar(getActivity(), true);
            firstLoad();
        }
        SwapFragment swapFragment = this.swapFragment;
        if (swapFragment != null) {
            swapFragment.onVisibleChanged(z);
        }
    }

    private void setNotice(int i) {
        if (i != 1) {
            if (i == 2) {
                if (TronConfig.hasNet) {
                    this.rlNetNotice.setVisibility(View.GONE);
                    if (SpAPI.THIS.isServerAuto()) {
                        return;
                    }
                    this.rlServerNotice.setVisibility(View.VISIBLE);
                    return;
                }
                this.rlNetNotice.setVisibility(View.VISIBLE);
                this.rlServerNotice.setVisibility(View.GONE);
                return;
            } else if (i != 3 && i != 4) {
                return;
            }
        }
        if (!TronConfig.hasNet) {
            this.rlNetNotice.setVisibility(View.VISIBLE);
        }
        this.rlServerNotice.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        FinanceFragment financeFragment = this.financeFragment;
        if (financeFragment != null) {
            financeFragment.onActivityResult(i, i2, intent);
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> fragments;

        @Override
        public int getItemPosition(Object obj) {
            return -2;
        }

        public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
            super(fragmentManager);
            this.fragments = arrayList;
        }

        @Override
        public Fragment getItem(int i) {
            return this.fragments.get(i);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

    public void alertBackground(boolean z) {
        if (z) {
            this.viewBackground.setVisibility(View.VISIBLE);
        } else {
            this.viewBackground.setVisibility(View.GONE);
        }
    }
}
