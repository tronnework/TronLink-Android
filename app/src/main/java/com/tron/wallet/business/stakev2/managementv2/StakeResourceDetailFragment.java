package com.tron.wallet.business.stakev2.managementv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.StakeResourceDetailFragment;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailContract;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailModel;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailPresenter;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeResourceDetailAdapter;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailBean;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.business.stakev2.stake.resource.UnDelegateBandWidthActivity;
import com.tron.wallet.business.stakev2.stake.resource.UnDelegateEnergyActivity;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.NoNetView;
import com.tron.wallet.common.components.WrapContentLinearLayoutManager;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.utils.AddressMapUtils;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.databinding.FragmentStakeResourceDetailBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
public class StakeResourceDetailFragment extends BaseFragment<StakeManageDetailPresenter, StakeManageDetailModel> implements StakeManageDetailContract.View {
    private static final String INDEX = "key_index";
    private static final String IS_POP = "key_is_display";
    private StakeResourceDetailAdapter adapter;
    private String address;
    private FragmentStakeResourceDetailBinding binding;
    private int index;
    ImageView ivPlaceHolder;
    NoNetView noDataView;
    NoNetView noNetView;
    private NumberFormat numberFormat;
    private BaseQuickAdapter.RequestLoadMoreListener onLoadMoreListener;
    private MultiSignPermissionData permissionData;
    RecyclerView recyclerView;
    private int resource_type;
    private long pageIndex = -1;
    private boolean isPop = false;
    private boolean isMultisign = false;
    Protocol.Account account = null;

    @Override
    public void updateStakeForMeList(StakeResourceDetailForMeOutput stakeResourceDetailForMeOutput) {
    }

    @Override
    public void updateUI(long j, long j2) {
    }

    public static Fragment getInstance(String str, Protocol.Account account, int i, boolean z, int i2, MultiSignPermissionData multiSignPermissionData, boolean z2) {
        StakeResourceDetailFragment stakeResourceDetailFragment = new StakeResourceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("address", str);
        bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, i);
        bundle.putInt(INDEX, i2);
        bundle.putBoolean(IS_POP, z);
        bundle.putParcelable(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        bundle.putBoolean(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z2);
        bundle.putSerializable("key_account", account);
        stakeResourceDetailFragment.setArguments(bundle);
        return stakeResourceDetailFragment;
    }

    public List<StakeResourceDetailBean> getTotalData() {
        StakeResourceDetailAdapter stakeResourceDetailAdapter = this.adapter;
        if (stakeResourceDetailAdapter == null) {
            return null;
        }
        return stakeResourceDetailAdapter.getData();
    }

    @Override
    protected void processData() {
        if (getArguments() != null) {
            this.index = getArguments().getInt(INDEX);
            this.resource_type = getArguments().getInt(ResourceManagementV2Activity.RESOURCE_TYPE);
            this.account = (Protocol.Account) getArguments().getSerializable("key_account");
            this.address = getArguments().getString("address");
            this.isPop = getArguments().getBoolean(IS_POP, false);
            this.isMultisign = getArguments().getBoolean(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
            this.permissionData = (MultiSignPermissionData) getArguments().getParcelable(CommonBundleKeys.KEY_PERMISSION_DATA);
        }
        this.numberFormat = NumberFormat.getInstance();
        if (this.index == 0) {
            AnalyticsHelper.ResourceDetailPage.logMultiEvent(this.resource_type == 0 ? AnalyticsHelper.ResourceDetailPage.RESOURCE_ENERGY_MYAGENCY_POP_SHOW_recycle : AnalyticsHelper.ResourceDetailPage.RESOURCE_BANDWIDTH_MYAGENCY_POP_SHOW_recycle, this.isMultisign);
        } else {
            AnalyticsHelper.ResourceDetailPage.logMultiEvent(this.resource_type == 0 ? AnalyticsHelper.ResourceDetailPage.RESOURCE_ENERGY_MYAGENCY_POP_SHOW__locked : AnalyticsHelper.ResourceDetailPage.RESOURCE_BANDWIDTH_MYAGENCY_POP_SHOW_locked, this.isMultisign);
        }
        if (this.index != 0) {
            this.ivPlaceHolder.setImageResource(R.mipmap.ic_placeholder_stake_detail_recycle);
        } else if (this.isPop) {
            this.ivPlaceHolder.setImageResource(R.mipmap.ic_placeholder_stake_detail_noreclaim);
        } else {
            this.ivPlaceHolder.setImageResource(R.mipmap.ic_placeholder_stake_detail_reclaim);
        }
        StakeResourceDetailAdapter stakeResourceDetailAdapter = new StakeResourceDetailAdapter(getContext(), this.index == 1, this.isPop);
        this.adapter = stakeResourceDetailAdapter;
        stakeResourceDetailAdapter.bindToRecyclerView(this.recyclerView);
        RecyclerView.ItemAnimator itemAnimator = this.recyclerView.getItemAnimator();
        this.adapter.setItemReClaimClickListener(new fun1());
        if (itemAnimator != null) {
            itemAnimator.setChangeDuration(0L);
        }
        this.recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getContext()));
        this.noNetView.setOnReloadClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingView();
                ((StakeManageDetailPresenter) mPresenter).loadDataStakeForOther(address, resource_type == 0 ? 1 : 0, index == 1, pageIndex);
            }
        });
        if (this.index == 1) {
            this.noDataView.setReloadDescription(R.string.stake_resource_no_locked);
        } else {
            this.noDataView.setReloadDescription(R.string.stake_resource_no_available);
        }
        this.onLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ((StakeManageDetailPresenter) mPresenter).loadDataStakeForOther(address, resource_type == 0 ? 1 : 0, index == 1, pageIndex);
            }
        };
        showLoadingView();
        ((StakeManageDetailPresenter) this.mPresenter).loadDataStakeForOther(this.address, this.resource_type == 0 ? 1 : 0, this.index == 1, this.pageIndex);
    }

    public class fun1 implements StakeResourceDetailAdapter.ItemReClaimClickListener {
        fun1() {
        }

        @Override
        public void onReClaimClick(StakeResourceDetailBean stakeResourceDetailBean) {
            Intent intent;
            String str;
            String str2;
            boolean z;
            if (getActivity() instanceof StakeManageDetailPopupActivity) {
                intent = ((StakeManageDetailPopupActivity) getActivity()).getIntent();
            } else {
                intent = getActivity() instanceof StakeManageDetailActivity ? ((StakeManageDetailActivity) getActivity()).getIntent() : null;
            }
            if (isPop) {
                if (resource_type == 0) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePopPage.ENERGY_DELEGATED_CLICK_TO_RECLAIM);
                } else {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePopPage.BANDWIDTH_DELEGATED_CLICK_TO_RECLAIM);
                }
            } else if (resource_type == 0) {
                AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_ENERGY_LOOKPAGE_CLICK_RECYCLE, isMultisign);
            } else {
                AnalyticsHelper.ResourceDetailPage.logMultiEvent(AnalyticsHelper.ResourceDetailPage.RESOURCE_BANDWIDTH_LOOKPAGE_CLICK_RECYCLE, isMultisign);
            }
            long parseLong = Long.parseLong(stakeResourceDetailBean.getResourceBalance());
            long parseDouble = (long) (Double.parseDouble(stakeResourceDetailBean.getBalance()) / 1000000.0d);
            long parseLong2 = Long.parseLong(stakeResourceDetailBean.getUnusedBalance());
            long parseLong3 = Long.parseLong(stakeResourceDetailBean.getUsedBalance());
            if (intent != null) {
                if (account == null) {
                    account = (Protocol.Account) intent.getSerializableExtra("key_account");
                }
                boolean booleanExtra = intent.getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
                if (booleanExtra) {
                    String stringExtra = intent.getStringExtra("key_controller_address");
                    str2 = intent.getStringExtra("key_controller_name");
                    str = stringExtra;
                } else {
                    str = null;
                    str2 = null;
                }
                z = booleanExtra;
            } else {
                str = null;
                str2 = null;
                z = false;
            }
            if (resource_type == 0) {
                if (!z && !OwnerPermissionCheckUtils.checkHasOwnerPermission(account)) {
                    OwnerPermissionCheckUtils.showMultiSignDialog(mContext, R.string.lack_of_undelegate_permission, R.string.multi_reclaim, new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            StakeResourceDetailFragment.1.this.lambda$onReClaimClick$0((Void) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                } else if (permissionData != null && !permissionData.isUnDelegateResourcePermission()) {
                    StakeResourceDetailFragment stakeResourceDetailFragment = StakeResourceDetailFragment.this;
                    stakeResourceDetailFragment.toast(stakeResourceDetailFragment.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.resource_recycle)));
                } else {
                    UnDelegateEnergyActivity.start(getIContext(), account, z, str, str2, stakeResourceDetailBean.getReceiverAddress(), parseLong, parseDouble, parseLong2, parseLong3);
                    if (getActivity() instanceof StakeManageDetailPopupActivity) {
                        getActivity().finish();
                    }
                }
            } else if (!z && !OwnerPermissionCheckUtils.checkHasOwnerPermission(account)) {
                if (SpAPI.THIS.isShasta()) {
                    StakeResourceDetailFragment stakeResourceDetailFragment2 = StakeResourceDetailFragment.this;
                    stakeResourceDetailFragment2.toast(stakeResourceDetailFragment2.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.resource_recycle)));
                    return;
                }
                OwnerPermissionCheckUtils.showMultiSignDialog(mContext, R.string.lack_of_undelegate_permission, R.string.multi_reclaim, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        StakeResourceDetailFragment.1.this.lambda$onReClaimClick$1((Void) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            } else if (permissionData != null && !permissionData.isUnDelegateResourcePermission()) {
                StakeResourceDetailFragment stakeResourceDetailFragment3 = StakeResourceDetailFragment.this;
                stakeResourceDetailFragment3.toast(stakeResourceDetailFragment3.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.resource_recycle)));
            } else {
                UnDelegateBandWidthActivity.start(getIContext(), account, z, str, str2, stakeResourceDetailBean.getReceiverAddress(), parseLong, parseDouble, parseLong2, parseLong3);
                if (getActivity() instanceof StakeManageDetailPopupActivity) {
                    getActivity().finish();
                }
            }
        }

        public void lambda$onReClaimClick$0(Void r3) {
            Bundle bundle = new Bundle();
            bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, 0);
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
        }

        public void lambda$onReClaimClick$1(Void r3) {
            Bundle bundle = new Bundle();
            bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, 1);
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
        }
    }

    @Override
    public void updateStakeForOtherList(long j, StakeResourceDetailOutput stakeResourceDetailOutput) {
        if (stakeResourceDetailOutput == null || stakeResourceDetailOutput.getData() == null) {
            return;
        }
        if (stakeResourceDetailOutput.getData().getList() == null || stakeResourceDetailOutput.getData().getList().size() <= 0) {
            if (j == -1) {
                showEmptyView();
                return;
            } else {
                this.adapter.loadMoreEnd(true);
                return;
            }
        }
        showContentView();
        this.pageIndex = stakeResourceDetailOutput.getData().getPageIndex();
        String totalAmount = stakeResourceDetailOutput.getData().getTotalAmount();
        if (this.onLoadMoreListener != null && this.recyclerView != null && !this.adapter.isLoadMoreEnable()) {
            this.adapter.setOnLoadMoreListener(this.onLoadMoreListener, this.recyclerView);
        }
        if (j == -1) {
            this.adapter.addDataWithHeader(totalAmount, stakeResourceDetailOutput.getData().getList());
        } else {
            this.adapter.addDataWithHeader(totalAmount, stakeResourceDetailOutput.getData().getList());
        }
        if (this.pageIndex == 0) {
            this.adapter.loadMoreEnd(true);
        } else {
            this.adapter.loadMoreComplete();
        }
    }

    public void showLoadingView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.VISIBLE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showContentView() {
        this.recyclerView.setVisibility(View.VISIBLE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.GONE);
    }

    private void showEmptyView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.GONE);
        this.noDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateStakeListFail(long j) {
        showNoNetView();
    }

    @Override
    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        AddressMapUtils.getInstance().setAddressMap(hashMap);
    }

    private void showNoNetView() {
        this.recyclerView.setVisibility(View.GONE);
        this.ivPlaceHolder.setVisibility(View.GONE);
        this.noNetView.setVisibility(View.VISIBLE);
        this.noNetView.setReloadMarginTop(20.0f);
        this.noDataView.setVisibility(View.GONE);
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentStakeResourceDetailBinding inflate = FragmentStakeResourceDetailBinding.inflate(layoutInflater, viewGroup, false);
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
        this.recyclerView = this.binding.recyclerView;
        this.ivPlaceHolder = this.binding.ivPlaceHolder;
        this.noDataView = this.binding.noDataView;
        this.noNetView = this.binding.noNetView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
    }

    public void setEmptyData() {
        StakeResourceDetailAdapter stakeResourceDetailAdapter = this.adapter;
        if (stakeResourceDetailAdapter == null) {
            return;
        }
        stakeResourceDetailAdapter.setNewData(new ArrayList());
        this.adapter.setEnableLoadMore(false);
    }
}
