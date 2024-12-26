package com.tron.wallet.business.security.approvecheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.StatusBarUtils;
import com.tron.wallet.business.security.approvecheck.ApproveCheckContract;
import com.tron.wallet.business.security.approvecheck.bean.ApproveListDatabeanOutput;
import com.tron.wallet.common.adapter.base.BaseFragmentAdapter;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivityApproveCheckBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ApproveCheckActivity extends BaseActivity<ApproveCheckPresenter, ApproveCheckModel> implements ApproveCheckContract.View {
    ApprovedListFragment approveByProjectListFragment;
    ApprovedListFragment approveByTokenListFragment;
    private ActivityApproveCheckBinding binding;
    boolean isInited;
    XTabLayoutV2 tabLayout;
    TextView tvAddressAmount;
    TextView tvContractAmount;
    TextView tvRiskAmount;
    ViewPager2 viewPager;

    private void setClick() {
    }

    @Override
    protected void setLayout() {
        ActivityApproveCheckBinding inflate = ActivityApproveCheckBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        mappingId();
        setClick();
        setHeaderBar(getString(R.string.approve_check));
        StatusBarUtils.setLightStatusBar(this, false);
        getHeaderHolder().tvCommonTitle.setText(R.string.approve_check);
        getHeaderHolder().rlRight.setVisibility(View.VISIBLE);
        getHeaderHolder().ivCommonLeft.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                onLeftButtonClick();
            }
        });
        getHeaderHolder().ivQr.setVisibility(View.VISIBLE);
        getHeaderHolder().ivQr.setImageDrawable(getDrawable(R.mipmap.token_title_right_icon));
        getHeaderHolder().ivQr.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_TIPS);
                UIUtils.toApproveCheckDetail(getIContext());
            }
        });
        getHeaderHolder().itemView.setBackgroundColor(getResources().getColor(R.color.gray_F8FAFE));
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvRiskAmount = this.binding.tvRiskAmount;
        this.tvContractAmount = this.binding.tvContractAmount;
        this.tvAddressAmount = this.binding.tvAddressAmount;
        this.tabLayout = this.binding.xTabLayout;
        this.viewPager = this.binding.vpContent;
    }

    @Override
    protected void processData() {
        AnalyticsHelper.logEvent(AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_SHOW);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initViewPager(bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.isInited) {
            return;
        }
        this.isInited = true;
        String address = WalletUtils.getSelectedWallet().getAddress();
        if (ApproveListManager.getInstance().getData() != null) {
            if (ApproveListManager.getInstance().getData().getData().getTokens() == null) {
                ((ApproveCheckPresenter) this.mPresenter).reQuestApproveList("token", address);
            } else {
                updateApproveList("token", ApproveListManager.getInstance().getData());
            }
            if (ApproveListManager.getInstance().getData().getData().getProjects() == null) {
                ((ApproveCheckPresenter) this.mPresenter).reQuestApproveList("project", address);
                return;
            } else {
                updateApproveList("project", ApproveListManager.getInstance().getData());
                return;
            }
        }
        ((ApproveCheckPresenter) this.mPresenter).reQuestApproveList("token", address);
        ((ApproveCheckPresenter) this.mPresenter).reQuestApproveList("project", address);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ApprovedListFragment approvedListFragment = this.approveByTokenListFragment;
        if (approvedListFragment != null && approvedListFragment.isAdded()) {
            supportFragmentManager.putFragment(bundle, "ApproveTokenListFragment", this.approveByTokenListFragment);
        }
        ApprovedListFragment approvedListFragment2 = this.approveByProjectListFragment;
        if (approvedListFragment2 == null || !approvedListFragment2.isAdded()) {
            return;
        }
        supportFragmentManager.putFragment(bundle, "ApproveProjectListFragment", this.approveByProjectListFragment);
    }

    private void initViewPager(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (bundle != null) {
            this.approveByTokenListFragment = (ApprovedListFragment) supportFragmentManager.getFragment(bundle, "ApproveTokenListFragment");
            this.approveByProjectListFragment = (ApprovedListFragment) supportFragmentManager.getFragment(bundle, "ApproveProjectListFragment");
        }
        if (this.approveByTokenListFragment == null) {
            this.approveByTokenListFragment = ApprovedListFragment.getInstance("token");
        }
        if (this.approveByProjectListFragment == null) {
            this.approveByProjectListFragment = ApprovedListFragment.getInstance("project");
        }
        arrayList.add(this.approveByTokenListFragment);
        arrayList.add(this.approveByProjectListFragment);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.mContext.getResources().getString(R.string.approve_check_by_token));
        arrayList2.add(this.mContext.getResources().getString(R.string.approve_check_by_project));
        this.viewPager.setAdapter(new PagerAdapter2(this, arrayList, arrayList2));
        this.viewPager.setCurrentItem(0);
        this.viewPager.setOffscreenPageLimit(2);
        this.viewPager.setUserInputEnabled(false);
        this.tabLayout.setupWithViewPager(this.viewPager);
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
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    str = AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_BY_TOKEN;
                } else {
                    str = AnalyticsHelper.SecurityApprovePage.SECURITY_APPROVE_CLICK_BY_PROJECT;
                }
                AnalyticsHelper.logEvent(str);
            }
        });
    }

    @Override
    public void updateApproveList(String str, ApproveListDatabeanOutput approveListDatabeanOutput) {
        if (approveListDatabeanOutput != null) {
            if ("token".equals(str)) {
                this.approveByTokenListFragment.updateList(approveListDatabeanOutput);
                if (approveListDatabeanOutput.getData() != null && approveListDatabeanOutput.getData().getTokens() != null && approveListDatabeanOutput.getData().getTokens().size() > 0) {
                    if (SpAPI.THIS.isUsdPrice()) {
                        if (BigDecimalUtils.between(approveListDatabeanOutput.getData().getTotalUsdCount(), 0, Double.valueOf(0.001d))) {
                            this.tvRiskAmount.setText("$<0.001");
                        } else {
                            TextView textView = this.tvRiskAmount;
                            textView.setText("$" + StringTronUtil.formatNumber3Truncate(new BigDecimal(approveListDatabeanOutput.getData().getTotalUsdCount())));
                        }
                    } else if (BigDecimalUtils.between(approveListDatabeanOutput.getData().getTotalCnyCount(), 0, Double.valueOf(0.001d))) {
                        TextView textView2 = this.tvRiskAmount;
                        textView2.setText(((Object) Html.fromHtml("&yen;")) + "<0.001");
                    } else {
                        TextView textView3 = this.tvRiskAmount;
                        textView3.setText(((Object) Html.fromHtml("&yen;")) + StringTronUtil.formatNumber3Truncate(new BigDecimal(approveListDatabeanOutput.getData().getTotalCnyCount())));
                    }
                    this.tvContractAmount.setText(approveListDatabeanOutput.getData().getApproveContractCount());
                    this.tvAddressAmount.setText(approveListDatabeanOutput.getData().getApproveAddrCount());
                    if (BigDecimalUtils.equalsZero((Number) new BigDecimal(approveListDatabeanOutput.getData().getTotalUsdCount()))) {
                        this.tvRiskAmount.setTextColor(getResources().getColor(R.color.green_57bfad));
                        return;
                    }
                    return;
                }
                this.tvRiskAmount.setTextColor(getResources().getColor(R.color.green_57bfad));
                if (SpAPI.THIS.isUsdPrice()) {
                    if (BigDecimalUtils.between(approveListDatabeanOutput.getData().getTotalUsdCount(), 0, Double.valueOf(0.001d))) {
                        this.tvRiskAmount.setText("$<0.001");
                    } else {
                        TextView textView4 = this.tvRiskAmount;
                        textView4.setText("$" + StringTronUtil.formatNumber3Truncate(new BigDecimal(approveListDatabeanOutput.getData().getTotalUsdCount())));
                    }
                } else if (BigDecimalUtils.between(approveListDatabeanOutput.getData().getTotalCnyCount(), 0, Double.valueOf(0.001d))) {
                    TextView textView5 = this.tvRiskAmount;
                    textView5.setText(((Object) Html.fromHtml("&yen;")) + "<0.001");
                } else {
                    TextView textView6 = this.tvRiskAmount;
                    textView6.setText(((Object) Html.fromHtml("&yen;")) + StringTronUtil.formatNumber3Truncate(new BigDecimal(approveListDatabeanOutput.getData().getTotalCnyCount())));
                }
                this.tvContractAmount.setText("0");
                this.tvAddressAmount.setText("0");
                return;
            }
            ApproveListManager.getInstance().updateProjectData(approveListDatabeanOutput.getData());
            this.approveByProjectListFragment.updateList(approveListDatabeanOutput);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ApprovedListFragment approvedListFragment = this.approveByTokenListFragment;
        if (approvedListFragment != null) {
            approvedListFragment.onActivityResult(i, i2, intent);
        }
        ApprovedListFragment approvedListFragment2 = this.approveByProjectListFragment;
        if (approvedListFragment2 != null) {
            approvedListFragment2.onActivityResult(i, i2, intent);
        }
    }

    @Override
    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        if (hashMap != null) {
            AddressMapUtils.getInstance().setAddressMap(hashMap);
        }
    }

    public void requestList(String str) {
        ((ApproveCheckPresenter) this.mPresenter).reQuestApproveList(str, WalletUtils.getSelectedWallet().getAddress());
    }

    public class PagerAdapter2 extends BaseFragmentAdapter {
        private List<Fragment> mFragmentList;
        private ArrayList<String> mTipsView;

        public PagerAdapter2(FragmentActivity fragmentActivity, List<Fragment> list, List<String> list2) {
            super(fragmentActivity);
            this.mFragmentList = list;
            this.mTipsView = (ArrayList) list2;
        }

        @Override
        public Fragment createFragment(int i) {
            return this.mFragmentList.get(i);
        }

        @Override
        public int getItemCount() {
            return this.mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return this.mTipsView.get(i);
        }
    }
}
