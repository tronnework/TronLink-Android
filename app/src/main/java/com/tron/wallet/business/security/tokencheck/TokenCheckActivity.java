package com.tron.wallet.business.security.tokencheck;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.business.security.SecurityResult;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.business.security.tokencheck.result.risktoken.RiskTokenListFragment;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcTokencheckBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
public class TokenCheckActivity extends BaseActivity<TokenCheckPresenter, TokenCheckModel> {
    public static String TOKEN_CHECK_RESULT = "token_check_result";
    public static String TYPE_RISK_TYPE = "token_risk_type";
    public static int TYPE_RISK_TYPE_HIGH = 2;
    public static int TYPE_RISK_TYPE_MIDDLE = 1;
    public static int TYPE_RISK_TYPE_NO_RISK;
    AcTokencheckBinding binding;
    TextView highRiskCountTextView;
    ViewPager2 mViewPager;
    TextView middleRiskCountTextView;
    RiskTokenListFragment middleRiskTokenListFragment;
    private PagerAdapter pagerAdapter;
    RiskTokenListFragment riskTokenListFragment;
    int riskType;
    XTabLayoutV2 tabLayout;
    SecurityResult tokenCheckResult;
    TextView topRiskCountTextView;
    private ArrayList<String> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void setLayout() {
        AcTokencheckBinding inflate = AcTokencheckBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        StatusBarUtils.setLightStatusBar(this, false);
        getHeaderHolder().tvCommonTitle.setText(R.string.token_check_title);
        getHeaderHolder().rlRight.setVisibility(View.VISIBLE);
        getHeaderHolder().ivQr.setVisibility(View.VISIBLE);
        getHeaderHolder().ivQr.setImageDrawable(getDrawable(R.mipmap.token_title_right_icon));
        getHeaderHolder().ivQr.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TIPS);
                UIUtils.toTokenCheckPage(getIContext());
            }
        });
        getHeaderHolder().itemView.setBackgroundColor(getResources().getColor(R.color.gray_F8FAFE));
    }

    private void mappingId() {
        this.topRiskCountTextView = this.binding.acTokenCheckHead.topRiskCount;
        this.highRiskCountTextView = this.binding.acTokenCheckHead.highRiskCount;
        this.middleRiskCountTextView = this.binding.acTokenCheckHead.middleRiskCount;
        this.tabLayout = this.binding.llTab;
        this.mViewPager = this.binding.viewpager;
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_SHOW);
        this.tokenCheckResult = (SecurityResult) getIntent().getSerializableExtra(TOKEN_CHECK_RESULT);
        initTopView();
        this.riskTokenListFragment = RiskTokenListFragment.getInstance(this.tokenCheckResult.getTokenCheckData(), RiskTokenListFragment.TYPE_RISK);
        this.middleRiskTokenListFragment = RiskTokenListFragment.getInstance(this.tokenCheckResult.getTokenCheckData(), RiskTokenListFragment.TYPE_MIDDLE_RISK);
        this.mTabEntities.add(getResources().getString(R.string.token_check_high_risk));
        this.mTabEntities.add(getResources().getString(R.string.token_check_middle_risk));
        this.mFragmentList.add(this.riskTokenListFragment);
        this.mFragmentList.add(this.middleRiskTokenListFragment);
        initViewPager();
        initTabLayout();
    }

    private void initTopView() {
        int i = 0;
        int size = (this.tokenCheckResult.getTokenCheckData() == null || this.tokenCheckResult.getTokenCheckData().getData() == null || this.tokenCheckResult.getTokenCheckData().getData().getHighRiskList() == null) ? 0 : this.tokenCheckResult.getTokenCheckData().getData().getHighRiskList().size();
        if (this.tokenCheckResult.getTokenCheckData() != null && this.tokenCheckResult.getTokenCheckData().getData() != null && this.tokenCheckResult.getTokenCheckData().getData().getMediumRiskList() != null) {
            i = this.tokenCheckResult.getTokenCheckData().getData().getMediumRiskList().size();
        }
        this.topRiskCountTextView.setText(String.valueOf(size));
        this.highRiskCountTextView.setText(String.valueOf(size));
        this.middleRiskCountTextView.setText(String.valueOf(i));
        if (size > 0) {
            this.riskType = TYPE_RISK_TYPE_HIGH;
        } else if (i > 0) {
            this.riskType = TYPE_RISK_TYPE_MIDDLE;
        } else {
            this.riskType = TYPE_RISK_TYPE_NO_RISK;
        }
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
                String str;
                if (tab.getPosition() == 0) {
                    str = AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAB_HIGH_RISK;
                } else {
                    str = AnalyticsHelper.SecurityTokenCheckPage.SECURITY_TOKEN_CHECK_CLICK_TAB_MEDIUM_RISK;
                }
                AnalyticsHelper.logEvent(str);
                mViewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    private void initViewPager() {
        this.mViewPager.setOffscreenPageLimit(2);
        PagerAdapter pagerAdapter = new PagerAdapter(this);
        this.pagerAdapter = pagerAdapter;
        this.mViewPager.setAdapter(pagerAdapter);
        this.mViewPager.setCurrentItem(0);
        this.mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int i) {
                super.onPageSelected(i);
                LogUtils.e("onPageSelected", "onPageSelected position = " + i);
                if (i == 0) {
                    riskTokenListFragment.resetSearch();
                } else if (i == 1) {
                    middleRiskTokenListFragment.resetSearch();
                }
            }
        });
    }

    public void deleteToken(TokenCheckBean tokenCheckBean, int i) {
        if (i == RiskTokenListFragment.TYPE_RISK) {
            this.tokenCheckResult.getTokenCheckData().getData().getHighRiskList().remove(tokenCheckBean);
            SecurityResult securityResult = this.tokenCheckResult;
            securityResult.setRiskNum(securityResult.getRiskNum() - 1);
            TextView textView = this.highRiskCountTextView;
            textView.setText("" + this.tokenCheckResult.getTokenCheckData().getData().getHighRiskList().size());
        } else if (i == RiskTokenListFragment.TYPE_MIDDLE_RISK) {
            this.tokenCheckResult.getTokenCheckData().getData().getMediumRiskList().remove(tokenCheckBean);
            SecurityResult securityResult2 = this.tokenCheckResult;
            securityResult2.setWaringNum(securityResult2.getWaringNum() - 1);
            TextView textView2 = this.middleRiskCountTextView;
            textView2.setText("" + this.tokenCheckResult.getTokenCheckData().getData().getMediumRiskList().size());
        }
        ((TokenCheckPresenter) this.mPresenter).deleteToken(tokenCheckBean);
    }

    public void ignoreToken(TokenCheckBean tokenCheckBean, int i) {
        ((TokenCheckPresenter) this.mPresenter).ignoreToken(tokenCheckBean);
        if (i == RiskTokenListFragment.TYPE_RISK) {
            this.tokenCheckResult.getTokenCheckData().getData().getHighRiskList().remove(tokenCheckBean);
            SecurityResult securityResult = this.tokenCheckResult;
            securityResult.setRiskNum(securityResult.getRiskNum() - 1);
        } else if (i == RiskTokenListFragment.TYPE_MIDDLE_RISK) {
            this.tokenCheckResult.getTokenCheckData().getData().getMediumRiskList().remove(tokenCheckBean);
            SecurityResult securityResult2 = this.tokenCheckResult;
            securityResult2.setWaringNum(securityResult2.getWaringNum() - 1);
        }
        initTopView();
    }

    @Override
    public void onLeftButtonClick() {
        exit();
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    public void exit() {
        Intent intent = new Intent();
        intent.putExtra(TOKEN_CHECK_RESULT, this.tokenCheckResult);
        setResult(-1, intent);
        super.exit();
    }

    public class PagerAdapter extends BaseFragmentAdapter {
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
    }
}
