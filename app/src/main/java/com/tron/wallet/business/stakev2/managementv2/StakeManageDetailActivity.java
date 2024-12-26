package com.tron.wallet.business.stakev2.managementv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.StakeManageDetailActivity;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailContract;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailModel;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailPresenter;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.business.transfer.selectaddress.PageType;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.StakeHeaderView;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.databinding.AcStakeManageDetailBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.functions.Consumer;
import j$.util.Objects;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.tron.protos.Protocol;
public class StakeManageDetailActivity extends BaseActivity<StakeManageDetailPresenter, StakeManageDetailModel> implements StakeManageDetailContract.View {
    public static final String DELEGATED_AMOUNT = "key_delegated_amount";
    public static final String DELEGATE_AVAILABLE_AMOUNT = "key_delegate_available_amount";
    public static final String FreezeTrx_AMOUNT = "key_freezeTrx_amount";
    private String address;
    private AcStakeManageDetailBinding binding;
    Button btnDelegate;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private String controllerName;
    private long delegateAvailable;
    View delegateDiveder;
    private long delegated;
    private long freezeTrx;
    private boolean fromMultiSign;
    private boolean isMultisign;
    ImageView ivHeadBackground;
    NoNetView noNetView;
    private NumberFormat numberFormat;
    private MultiSignPermissionData permissionData;
    View placeHolder;
    private int resource_type;
    RelativeLayout rlHeader;
    StakeHeaderView stakeHeader;
    XTabLayout tabLayout;
    TextView tvMultiSignWarning;
    TextView tvUsableAmount;
    TextView tvUsableTitle;
    TextView tvUsedAmount;
    TextView tvUsedTitle;
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

    public static void start(Context context, String str, int i, Long l, Long l2, Protocol.Account account, Long l3, boolean z, String str2, MultiSignPermissionData multiSignPermissionData) {
        Intent intent = new Intent(context, StakeManageDetailActivity.class);
        intent.putExtra(ResourceManagementV2Activity.RESOURCE_TYPE, i);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_delegated_amount", l);
        intent.putExtra("key_freezeTrx_amount", l3);
        intent.putExtra("key_delegate_available_amount", l2);
        intent.putExtra("key_account", account);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcStakeManageDetailBinding inflate = AcStakeManageDetailBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 0);
        mappingId();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.stakeHeader = this.binding.stakeHeader;
        this.ivHeadBackground = this.binding.ivBackground;
        this.rlHeader = this.binding.rlHeader;
        this.delegateDiveder = this.binding.divider;
        this.tvUsedTitle = this.binding.tvUsedTitle;
        this.tvUsedAmount = this.binding.tvUsedAmount;
        this.tvUsableTitle = this.binding.tvUsableTitle;
        this.tvUsableAmount = this.binding.tvUsableAmount;
        this.tabLayout = this.binding.xTablayout;
        this.viewPager = this.binding.vpContent;
        this.noNetView = this.binding.noDataView;
        this.placeHolder = this.binding.ivPlaceHolder;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
        this.btnDelegate = this.binding.btDelegate;
    }

    @Override
    protected void processData() {
        this.numberFormat = NumberFormat.getInstance();
        this.stakeHeader.setOnHeaderClickListener(new StakeHeaderView.OnHeaderClickListener() {
            @Override
            public void onQuestion() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onQuestion(this);
            }

            @Override
            public void onRightClick() {
                StakeHeaderView.OnHeaderClickListener.-CC.$default$onRightClick(this);
            }

            @Override
            public void onLeftClick() {
                finish();
            }
        });
        RxManager rxManager = new RxManager();
        rxManager.on(Event.BroadcastSuccess, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$0(obj);
            }
        });
        rxManager.on(Event.BroadcastSuccess2, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$1(obj);
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            this.resource_type = intent.getIntExtra(ResourceManagementV2Activity.RESOURCE_TYPE, 0);
            this.delegated = intent.getLongExtra("key_delegated_amount", -1L);
            this.freezeTrx = intent.getLongExtra("key_freezeTrx_amount", -1L);
            this.delegateAvailable = intent.getLongExtra("key_delegate_available_amount", -1L);
            this.address = intent.getStringExtra("key_controller_address");
            this.fromMultiSign = intent.getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
            this.controllerAccount = (Protocol.Account) intent.getSerializableExtra("key_account");
            if (this.fromMultiSign) {
                this.controllerAddress = intent.getStringExtra("key_controller_address");
                this.controllerName = intent.getStringExtra("key_controller_name");
                this.permissionData = (MultiSignPermissionData) intent.getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
            }
            long j = this.delegated;
            if (j != -1) {
                this.tvUsedAmount.setText(this.numberFormat.format(j));
            }
            long j2 = this.delegateAvailable;
            if (j2 != -1) {
                this.tvUsableAmount.setText(this.numberFormat.format(j2));
            }
            if (this.delegateAvailable <= 0 || this.freezeTrx <= 0) {
                this.btnDelegate.setVisibility(View.GONE);
            } else {
                this.btnDelegate.setVisibility(View.VISIBLE);
            }
            if (this.resource_type == 0) {
                this.rlHeader.setBackground(getDrawable(R.color.bg_energy));
                this.ivHeadBackground.setImageResource(R.mipmap.bg_stake_icon_manager_energy);
                this.stakeHeader.setHeader(getString(R.string.energy_manager), null, null);
                this.tvUsedTitle.setText(R.string.resource_energy_delegated);
                this.tvUsableTitle.setText(R.string.energy_you_can_delegate);
                this.delegateDiveder.setBackground(getDrawable(R.color.gray_FFEAD1));
            } else {
                this.rlHeader.setBackground(getDrawable(R.color.bg_bandwidth));
                this.ivHeadBackground.setImageResource(R.mipmap.bg_stake_icon_manager_bandwidth);
                this.stakeHeader.setHeader(getString(R.string.bandwidth_manager), null, null);
                this.tvUsedTitle.setText(R.string.resource_bandwidth_delegated);
                this.tvUsableTitle.setText(R.string.bandwidth_you_can_delegate);
                this.delegateDiveder.setBackground(getDrawable(R.color.green_DAF1EE));
            }
        }
        if (this.delegateAvailable == -1 || this.delegated == -1 || this.freezeTrx <= 0) {
            ((StakeManageDetailPresenter) this.mPresenter).getDelegateAvailableAmount(this.resource_type, this.fromMultiSign ? this.controllerAddress : WalletUtils.getSelectedWallet().getAddress());
        }
        if (this.resource_type == 0) {
            AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_ENERGY_LOOKPAGE_SHOW, this.fromMultiSign);
        } else {
            AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_BANDWIDTH_LOOKPAGE_SHOW, this.fromMultiSign);
        }
        initViewPager();
        if (this.fromMultiSign) {
            String stringExtra = intent.getStringExtra("key_controller_name");
            this.controllerName = stringExtra;
            final String str = this.controllerAddress;
            if (!TextUtils.isEmpty(stringExtra)) {
                str = String.format("%s (%s)", this.controllerName, str);
            }
            this.tvMultiSignWarning.setText(getString(R.string.multi_controller_tips, new Object[]{str}));
            this.tvMultiSignWarning.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$processData$2(str);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
        }
        this.btnDelegate.setOnClickListener(new fun2());
        if (this.mPresenter != 0) {
            ((StakeManageDetailPresenter) this.mPresenter).getAllAddresses();
        }
    }

    public void lambda$processData$0(Object obj) throws Exception {
        finish();
    }

    public void lambda$processData$1(Object obj) throws Exception {
        finish();
    }

    public void lambda$processData$2(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    public class fun2 extends NoDoubleClickListener2 {
        fun2() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            Protocol.Account account = controllerAccount;
            if (account == null && (account = ((StakeManageDetailPresenter) mPresenter).getAccount()) == null) {
                account = WalletUtils.getAccount(getIContext(), SpAPI.THIS.getSelectedWallet());
            }
            Protocol.Account account2 = account;
            if (resource_type == 0) {
                AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_ENERGY_LOOKPAGE_CLICK_AGENCY, fromMultiSign);
            } else {
                AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_BANDWIDTH_LOOKPAGE_CLICK_AGENCY, fromMultiSign);
            }
            if ((freezeTrx == 0 || freezeTrx == -1) && mPresenter != 0) {
                StakeManageDetailActivity stakeManageDetailActivity = StakeManageDetailActivity.this;
                stakeManageDetailActivity.freezeTrx = ((StakeManageDetailPresenter) stakeManageDetailActivity.mPresenter).getMaxSize();
            }
            if (resource_type == 1) {
                if (!fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(account2)) {
                    OwnerPermissionCheckUtils.showMultiSignDialog(mContext, R.string.lack_of_delegate_permission, R.string.multi_delegate, new java.util.function.Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            StakeManageDetailActivity.2.this.lambda$onNoDoubleClick$0((Void) obj);
                        }

                        public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                } else if (permissionData != null && !permissionData.isDelegateResourcePermission()) {
                    StakeManageDetailActivity stakeManageDetailActivity2 = StakeManageDetailActivity.this;
                    stakeManageDetailActivity2.toast(stakeManageDetailActivity2.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.resource_delegate)));
                } else {
                    SelectSendAddressActivity.startForDelegate(getIContext(), account2, controllerAddress, controllerName, PageType.DELEGATE_BANDWIDTH, delegateAvailable, freezeTrx, fromMultiSign);
                }
            } else if (permissionData != null && !permissionData.isDelegateResourcePermission()) {
                StakeManageDetailActivity stakeManageDetailActivity3 = StakeManageDetailActivity.this;
                stakeManageDetailActivity3.toast(stakeManageDetailActivity3.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.resource_delegate)));
            } else if (!fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(account2)) {
                OwnerPermissionCheckUtils.showMultiSignDialog(mContext, R.string.lack_of_delegate_permission, R.string.multi_delegate, new java.util.function.Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        StakeManageDetailActivity.2.this.lambda$onNoDoubleClick$1((Void) obj);
                    }

                    public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            } else {
                SelectSendAddressActivity.startForDelegate(getIContext(), account2, controllerAddress, controllerName, PageType.DELEGATE_ENERGY, delegateAvailable, freezeTrx, fromMultiSign);
            }
        }

        public void lambda$onNoDoubleClick$0(Void r3) {
            Bundle bundle = new Bundle();
            bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, 1);
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
        }

        public void lambda$onNoDoubleClick$1(Void r3) {
            Bundle bundle = new Bundle();
            bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, 0);
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
        }
    }

    private void showLoadingView() {
        this.placeHolder.setVisibility(View.VISIBLE);
        this.noNetView.setVisibility(View.GONE);
    }

    private void initViewPager() {
        ArrayList arrayList = new ArrayList();
        this.usedResourceDetailFragment = (StakeResourceDetailFragment) StakeResourceDetailFragment.getInstance(this.address, this.controllerAccount, this.resource_type, false, 0, this.permissionData, this.isMultisign);
        this.usableResourceDetailFragment = (StakeResourceDetailFragment) StakeResourceDetailFragment.getInstance(this.address, this.controllerAccount, this.resource_type, false, 1, this.permissionData, this.isMultisign);
        arrayList.add(this.usedResourceDetailFragment);
        arrayList.add(this.usableResourceDetailFragment);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.mContext.getResources().getString(R.string.resource_reclaimable));
        arrayList2.add(this.mContext.getResources().getString(R.string.resource_locked));
        this.viewPager.setAdapter(new StakeManagePagerAdapter(getSupportFragmentManager(), arrayList, arrayList2));
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    @Override
    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        AddressMapUtils.getInstance().setAddressMap(hashMap);
    }

    @Override
    public void updateUI(long j, long j2) {
        this.delegateAvailable = j;
        this.delegated = j2;
        this.tvUsableAmount.setText(this.numberFormat.format(j));
        if (this.mPresenter != 0 && this.freezeTrx <= 0) {
            this.freezeTrx = ((StakeManageDetailPresenter) this.mPresenter).getMaxSize();
        }
        if (j <= 0 || this.freezeTrx <= 0) {
            this.btnDelegate.setVisibility(View.GONE);
        } else {
            this.btnDelegate.setVisibility(View.VISIBLE);
        }
        this.tvUsedAmount.setText(this.numberFormat.format(this.delegated));
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
