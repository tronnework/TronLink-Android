package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.addassets2.MyAllAssetsActivity;
import com.tron.wallet.business.addassets2.MyAllAssetsContract;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.business.assetshome.SortHelper;
import com.tron.wallet.business.customtokens.CustomTokensActivity;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.AcMyAssetsBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Iterator;
public class MyAllAssetsActivity extends BaseActivity<MyAllAssetsPresent, MyAllAssetsModel> implements MyAllAssetsContract.View {
    private static final String KEY_DATA_TYPE = "data_type";
    private AcMyAssetsBinding binding;
    private boolean hideZeroAssets;
    ImageView ivSort;
    ViewPager2 mViewPager;
    private PagerAdapter pagerAdapter;
    XTabLayoutV2 tabLayout;
    View viewRight;
    private ArrayList<String> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private int initDataType = 0;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyAllAssetsActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int i) {
        Intent intent = new Intent();
        intent.setClass(context, MyAllAssetsActivity.class);
        intent.putExtra(KEY_DATA_TYPE, i);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcMyAssetsBinding inflate = AcMyAssetsBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        ConstraintLayout root = inflate.getRoot();
        mappingId();
        setView(root, 0);
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tabLayout = this.binding.llTab;
        this.mViewPager = this.binding.viewpager;
        this.viewRight = this.binding.tvCommonRight;
        this.ivSort = this.binding.ivMyAssetSort;
    }

    private void initTabLayout() {
        this.tabLayout.setupWithViewPager(this.mViewPager);
        this.tabLayout.setTabTextColors(R.color.red_55c5, R.color.blue_0F);
        this.tabLayout.setOnTabSelectedListener(new XTabLayoutV2.OnTabSelectedListener() {
            @Override
            public void onTabReselected(XTabLayoutV2.Tab tab) {
            }

            @Override
            public void onTabUnselected(XTabLayoutV2.Tab tab) {
            }

            @Override
            public void onTabSelected(XTabLayoutV2.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getIContext());
                Object[] objArr = new Object[1];
                objArr[0] = tab.getPosition() == 1 ? "Nft" : "My";
                firebaseAnalytics.logEvent(String.format("Click_my_token_%s", objArr), null);
            }
        });
    }

    private void initViewPager() {
        this.mViewPager.setOffscreenPageLimit(2);
        PagerAdapter pagerAdapter = new PagerAdapter((FragmentActivity) this.mContext);
        this.pagerAdapter = pagerAdapter;
        this.mViewPager.setAdapter(pagerAdapter);
        this.mViewPager.setCurrentItem(this.initDataType == 0 ? 0 : 1);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (this.mFragmentList.size() > 0 && this.mFragmentList.get(0) != null && this.mFragmentList.get(0).isAdded()) {
            supportFragmentManager.putFragment(bundle, "MyAssetsFragment0", this.mFragmentList.get(0));
        }
        if (this.mFragmentList.size() <= 1 || this.mFragmentList.get(1) == null || !this.mFragmentList.get(1).isAdded()) {
            return;
        }
        supportFragmentManager.putFragment(bundle, "MyAssetsFragment1", this.mFragmentList.get(1));
    }

    @Override
    public void onCreate2(Bundle bundle) {
        MyAssetsFragment myAssetsFragment;
        MyAssetsFragment myAssetsFragment2;
        super.onCreate2(bundle);
        this.initDataType = getIntent().getIntExtra(KEY_DATA_TYPE, 0);
        this.mFragmentList.clear();
        this.mTabEntities.clear();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (bundle != null) {
            myAssetsFragment = (MyAssetsFragment) supportFragmentManager.getFragment(bundle, "MyAssetsFragment0");
            myAssetsFragment2 = (MyAssetsFragment) supportFragmentManager.getFragment(bundle, "MyAssetsFragment1");
        } else {
            myAssetsFragment = null;
            myAssetsFragment2 = null;
        }
        if (myAssetsFragment == null) {
            myAssetsFragment = MyAssetsFragment.newInstance();
        }
        this.mTabEntities.add(getResources().getString(R.string.manager_assets));
        this.mFragmentList.add(myAssetsFragment);
        if (AssetsConfig.canDisplayCollections()) {
            if (myAssetsFragment2 == null) {
                myAssetsFragment2 = MyAssetsFragment.newInstance(1);
            }
            this.tabLayout.setVisibility(View.VISIBLE);
            this.mTabEntities.add(getResources().getString(R.string.asset_collection));
            this.mFragmentList.add(myAssetsFragment2);
            return;
        }
        this.tabLayout.setVisibility(View.GONE);
    }

    @Override
    protected void processData() {
        this.viewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$processData$0(view);
            }
        });
        initViewPager();
        initTabLayout();
        TouchDelegateUtils.expandViewTouchDelegate(this.ivSort, 10);
        ((MyAllAssetsPresent) this.mPresenter).getNewAssets();
        AnalyticsHelper.AssetsManagerPage.logEvent(AnalyticsHelper.AssetsManagerPage.ENTER_ASSETS_MANAGER_PAGE);
    }

    public void lambda$processData$0(View view) {
        AnalyticsHelper.logEvent(AnalyticsHelper.CustomTokenPage.CLICK_MY_ASSETS_CUSTOM_TOKEN);
        CustomTokensActivity.startActivity(this);
    }

    public class fun2 extends NoDoubleClickListener {
        public static void lambda$onNoDoubleClick$0() {
        }

        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            int id = view.getId();
            if (id == R.id.iv_my_asset_sort) {
                SortHelper.get().showMyAssetsSortDialog(getIContext(), ivSort, 0, new SortHelper.OnSortChangedListener() {
                    @Override
                    public void onSortChanged(PopupWindow popupWindow, TokenSortType tokenSortType, int i, boolean z, boolean z2, boolean z3) {
                        hideZeroAssets = z;
                        if (mPresenter != 0) {
                            ((MyAllAssetsPresent) mPresenter).updateFilterStatus(z, z2);
                        }
                        Iterator it = mFragmentList.iterator();
                        while (it.hasNext()) {
                            ((MyAssetsFragment) ((Fragment) it.next())).filterAndSort(z, z2);
                        }
                    }
                }, new PopupWindow.OnDismissListener() {
                    @Override
                    public final void onDismiss() {
                        MyAllAssetsActivity.2.lambda$onNoDoubleClick$0();
                    }
                });
            } else if (id == R.id.ll_common_left) {
                exit();
            } else if (id != R.id.rl_search) {
            } else {
                Intent intent = new Intent(MyAllAssetsActivity.this, SearchAssetsActivity.class);
                ActivityOptionsCompat makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(MyAllAssetsActivity.this, new Pair(findViewById(R.id.ll_search), "llSearch"));
                AnalyticsHelper.AssetsManagerPage.logEvent(AnalyticsHelper.AssetsManagerPage.CLICK_ASSETS_MANAGER_PAGE_SEARCH);
                ActivityCompat.startActivity(MyAllAssetsActivity.this, intent, makeSceneTransitionAnimation.toBundle());
            }
        }
    }

    public void setClick() {
        2 r0 = new fun2();
        this.binding.llCommonLeft.setOnClickListener(r0);
        this.binding.ivMyAssetSort.setOnClickListener(r0);
        this.binding.rlSearch.setOnClickListener(r0);
    }

    @Override
    public void showNewAssetsTips(boolean z) {
        this.pagerAdapter.showTipsView(0, z);
    }

    @Override
    public void showNewCollectionsTips(boolean z) {
        this.pagerAdapter.showTipsView(1, z);
    }

    public class PagerAdapter extends BaseFragmentAdapter implements XTabLayoutV2.IXTabPagerAdapter {
        private ArrayList<View> mTipsView;

        public PagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            this.mTipsView = new ArrayList<>();
        }

        @Override
        public Fragment createFragment(int i) {
            return (Fragment) mFragmentList.get(i);
        }

        @Override
        public int getItemCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return (CharSequence) mTabEntities.get(i);
        }

        @Override
        public View getPageCustomView(int i, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(MyAllAssetsActivity.this).inflate(R.layout.tab_item_with_indicator, viewGroup, false);
            ((TextView) inflate.findViewById(R.id.tv_title)).setText((CharSequence) mTabEntities.get(i));
            this.mTipsView.add(i, inflate.findViewById(R.id.v_new_assets_tip));
            return inflate;
        }

        public void showTipsView(int i, boolean z) {
            if (i < 0 || i >= this.mTipsView.size()) {
                return;
            }
            this.mTipsView.get(i).setVisibility(z ? View.VISIBLE : View.GONE);
        }
    }
}
