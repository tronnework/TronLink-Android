package com.tron.wallet.business.addassets2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.addassets2.AddAssetsContract;
import com.tron.wallet.business.addassets2.bean.TokenSortType;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcAssetsBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.Iterator;
public class EditAssetsOrderActivity extends BaseActivity<AddAssetsPresent, AddAssetsModel> implements AddAssetsContract.View {
    private static final String KEY_DATA_TYPE = "data_type";
    private static final String KEY_SORT_TYPE = "sort_type";
    private AcAssetsBinding binding;
    private EditAssetsOrderFragment homeAssetsFragment;
    private int initDataType;
    private int initSortType;
    ViewPager2 mViewPager;
    RelativeLayout root;
    XTabLayoutV2 tabLayout;
    private final Handler mHandler = new Handler();
    private final ArrayList<String> mTabEntities = new ArrayList<>();
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();

    public interface UserAction {
        void selectSortType(TokenSortType tokenSortType);

        void sortCancel();

        void sortConfirm();
    }

    @Override
    public void updateSortType(TokenSortType tokenSortType) {
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, EditAssetsOrderActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int i) {
        startActivity(context, i, -1);
    }

    public static void startActivity(Context context, int i, int i2) {
        Intent intent = new Intent();
        intent.setClass(context, EditAssetsOrderActivity.class);
        intent.putExtra(KEY_DATA_TYPE, i);
        intent.putExtra(KEY_SORT_TYPE, i2);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcAssetsBinding inflate = AcAssetsBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setHeaderBar(getString(R.string.assets_management));
        getHeaderHolder().llCommonLeft.setVisibility(View.GONE);
        ViewGroup.LayoutParams layoutParams = getHeaderHolder().tvCommonTitle.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).leftMargin = UIUtils.dip2px(15.0f);
        }
        getHeaderHolder().tvCommonTitle.setLayoutParams(layoutParams);
        TextView textView = getHeaderHolder().tvCommonRight;
        textView.setTextColor(getResources().getColor(R.color.blue_3c));
        textView.setText(R.string.ok);
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$setLayout$0(view);
            }
        });
    }

    public void lambda$setLayout$0(View view) {
        onClickOkButton();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tabLayout = this.binding.llTab;
        this.mViewPager = this.binding.viewpager;
        this.root = this.binding.root;
    }

    private void onClickOkButton() {
        Iterator<Fragment> it = this.mFragmentList.iterator();
        while (it.hasNext()) {
            Fragment next = it.next();
            if (next instanceof UserAction) {
                ((UserAction) next).sortConfirm();
            }
        }
        toast(getResources().getString(R.string.saved));
        finish();
    }

    @Override
    public void onBackPressed() {
        onClickOkButton();
    }

    private void initTabLayout() {
        this.tabLayout.setupWithViewPager(this.mViewPager);
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
                objArr[0] = tab.getPosition() == 1 ? "Home" : "My";
                firebaseAnalytics.logEvent(String.format("Click_add_token_%s", objArr), null);
            }
        });
    }

    private void initViewPager() {
        this.mViewPager.setOffscreenPageLimit(2);
        this.mViewPager.setAdapter(new AssetsFragmentPagerAdapter((FragmentActivity) this.mContext));
        this.mViewPager.setCurrentItem(this.initDataType == 0 ? 0 : 1);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (this.mFragmentList.size() > 0 && this.mFragmentList.get(0) != null && this.mFragmentList.get(0).isAdded()) {
            supportFragmentManager.putFragment(bundle, "EditAssetsOrderFragment0", this.mFragmentList.get(0));
        }
        if (this.mFragmentList.size() <= 1 || this.mFragmentList.get(1) == null || !this.mFragmentList.get(1).isAdded()) {
            return;
        }
        supportFragmentManager.putFragment(bundle, "EditAssetsOrderFragment1", this.mFragmentList.get(1));
    }

    @Override
    public void onCreate2(Bundle bundle) {
        EditAssetsOrderFragment editAssetsOrderFragment;
        super.onCreate2(bundle);
        this.initDataType = getIntent().getIntExtra(KEY_DATA_TYPE, 0);
        this.initSortType = getIntent().getIntExtra(KEY_SORT_TYPE, -1);
        this.mFragmentList.clear();
        this.mTabEntities.clear();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (bundle != null) {
            this.homeAssetsFragment = (EditAssetsOrderFragment) supportFragmentManager.getFragment(bundle, "EditAssetsOrderFragment0");
            editAssetsOrderFragment = (EditAssetsOrderFragment) supportFragmentManager.getFragment(bundle, "EditAssetsOrderFragment1");
        } else {
            editAssetsOrderFragment = null;
        }
        if (this.homeAssetsFragment == null) {
            this.homeAssetsFragment = EditAssetsOrderFragment.newInstance(this.initSortType);
        }
        this.mTabEntities.add(getResources().getString(R.string.manager_assets));
        this.mFragmentList.add(this.homeAssetsFragment);
        if (AssetsConfig.canDisplayCollections()) {
            if (editAssetsOrderFragment == null) {
                editAssetsOrderFragment = EditAssetsOrderFragment.newInstance(1, this.initSortType);
            }
            this.tabLayout.setVisibility(View.VISIBLE);
            this.mTabEntities.add(getResources().getString(R.string.asset_collection));
            this.mFragmentList.add(editAssetsOrderFragment);
            return;
        }
        this.tabLayout.setVisibility(View.GONE);
    }

    @Override
    protected void processData() {
        initViewPager();
        initTabLayout();
        ((AddAssetsPresent) this.mPresenter).getAssetsSortType();
        if (this.initSortType != -1) {
            this.mHandler.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$processData$1();
                }
            });
        }
    }

    public void lambda$processData$1() {
        Iterator<Fragment> it = this.mFragmentList.iterator();
        while (it.hasNext()) {
            Fragment next = it.next();
            if (next instanceof UserAction) {
                ((UserAction) next).selectSortType(TokenSortType.SORT_BY_USER);
            }
        }
    }

    public class AssetsFragmentPagerAdapter extends BaseFragmentAdapter implements XTabLayoutV2.IXTabPagerAdapter {
        public AssetsFragmentPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
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
            View inflate = LayoutInflater.from(EditAssetsOrderActivity.this).inflate(R.layout.tab_item_with_indicator, viewGroup, false);
            ((TextView) inflate.findViewById(R.id.tv_title)).setText((CharSequence) mTabEntities.get(i));
            return inflate;
        }
    }
}
