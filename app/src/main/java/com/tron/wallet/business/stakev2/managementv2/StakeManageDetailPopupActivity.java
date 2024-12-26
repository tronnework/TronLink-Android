package com.tron.wallet.business.stakev2.managementv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailContract;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailModel;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailPresenter;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.databinding.AcStakeManageDetailPopBinding;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
public class StakeManageDetailPopupActivity extends BaseActivity<StakeManageDetailPresenter, StakeManageDetailModel> implements StakeManageDetailContract.View {
    public static final String DELEGATED_AMOUNT = "key_delegated_amount";
    public static final String DELEGATE_AVAILABLE_AMOUNT = "key_delegate_available_amount";
    public static final String FreezeTrx_AMOUNT = "key_freezeTrx_amount";
    private String address;
    private AcStakeManageDetailPopBinding binding;
    private Protocol.Account controllerAccount;
    private Long delegateAvailable;
    private Long delegated;
    private Long freezeTrx;
    private boolean isMultisign;
    NoNetView noNetView;
    private MultiSignPermissionData permissionData;
    View placeHolder;
    private int resource_type;
    XTabLayout tabLayout;
    TextView tvTag;
    private StakeResourceDetailFragment usableResourceDetailFragment;
    private StakeResourceDetailFragment usedResourceDetailFragment;
    ViewPager viewPager;

    @Override
    public void updateStakeForMeList(StakeResourceDetailForMeOutput stakeResourceDetailForMeOutput) {
    }

    @Override
    public void updateStakeForOtherList(long j, StakeResourceDetailOutput stakeResourceDetailOutput) {
    }

    @Override
    public void updateStakeListFail(long j) {
    }

    @Override
    public void updateUI(long j, long j2) {
    }

    public static void start(Context context, String str, int i, Long l, Long l2, Protocol.Account account, Long l3, boolean z, String str2, MultiSignPermissionData multiSignPermissionData) {
        Intent intent = new Intent(context, StakeManageDetailPopupActivity.class);
        intent.putExtra(ResourceManagementV2Activity.RESOURCE_TYPE, i);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_delegated_amount", l);
        intent.putExtra("key_delegate_available_amount", l2);
        intent.putExtra("key_freezeTrx_amount", l3);
        intent.putExtra("key_account", account);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcStakeManageDetailPopBinding inflate = AcStakeManageDetailPopBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
        setClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tabLayout = this.binding.xTablayout;
        this.viewPager = this.binding.vpContent;
        this.noNetView = this.binding.noDataView;
        this.placeHolder = this.binding.ivPlaceHolder;
        this.tvTag = this.binding.tvTitleTag;
    }

    @Override
    protected void processData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.resource_type = intent.getIntExtra(ResourceManagementV2Activity.RESOURCE_TYPE, 0);
            this.delegated = Long.valueOf(intent.getLongExtra("key_delegated_amount", 0L));
            this.address = intent.getStringExtra("key_controller_address");
            this.freezeTrx = Long.valueOf(intent.getLongExtra("key_freezeTrx_amount", 0L));
            this.delegateAvailable = Long.valueOf(intent.getLongExtra("key_delegate_available_amount", 0L));
            this.controllerAccount = (Protocol.Account) intent.getSerializableExtra("key_account");
            this.isMultisign = intent.getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
            this.permissionData = (MultiSignPermissionData) intent.getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
        }
        if (this.resource_type == 1) {
            this.tvTag.setText(R.string.bandwidth);
            this.tvTag.setBackgroundResource(R.drawable.bg_resource_bandwidth_tag);
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePopPage.ENERGY_DELEGATED_TO_OTHER_PAGE);
        } else {
            this.tvTag.setText(R.string.energy);
            this.tvTag.setBackgroundResource(R.drawable.bg_resource_energy_tag);
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePopPage.BANDWIDTH_DELEGATED_TO_OTHER_PAGE);
        }
        initViewPager();
        ((StakeManageDetailPresenter) this.mPresenter).getAllAddresses();
    }

    @Override
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
    }

    private void showLoadingView() {
        this.placeHolder.setVisibility(View.VISIBLE);
        this.noNetView.setVisibility(View.GONE);
    }

    private void initViewPager() {
        ArrayList arrayList = new ArrayList();
        this.usedResourceDetailFragment = (StakeResourceDetailFragment) StakeResourceDetailFragment.getInstance(this.address, this.controllerAccount, this.resource_type, true, 0, this.permissionData, this.isMultisign);
        this.usableResourceDetailFragment = (StakeResourceDetailFragment) StakeResourceDetailFragment.getInstance(this.address, this.controllerAccount, this.resource_type, true, 1, this.permissionData, this.isMultisign);
        arrayList.add(this.usedResourceDetailFragment);
        arrayList.add(this.usableResourceDetailFragment);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(getResources().getString(R.string.resource_reclaimable));
        arrayList2.add(getResources().getString(R.string.resource_locked));
        this.viewPager.setAdapter(new StakeManagePagerAdapter(getSupportFragmentManager(), arrayList, arrayList2));
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    public void setClick() {
        this.binding.ivCommonRight.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        AddressMapUtils.getInstance().setAddressMap(hashMap);
    }

    public static class StakeManagePagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragments;
        List<String> titles;

        @Override
        public int getItemPosition(Object obj) {
            return -2;
        }

        public StakeManagePagerAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> list2) {
            super(fragmentManager);
            this.fragments = list;
            this.titles = list2;
        }

        @Override
        public Fragment getItem(int i) {
            return this.fragments.get(i);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int i) {
            return this.titles.get(i);
        }
    }
}
