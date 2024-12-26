package com.tron.wallet.business.stakev2.managementv2;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.ResourceBandwidthFragment;
import com.tron.wallet.business.stakev2.managementv2.pop.DelegateForMePopup;
import com.tron.wallet.business.transfer.selectaddress.PageType;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.FgResourcesEnergyBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.function.Consumer;
import org.apache.commons.cli.HelpFormatter;
import org.tron.protos.Protocol;
public class ResourceBandwidthFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private FgResourcesEnergyBinding binding;
    TextView btDelegate;
    TextView btRecycle;
    View btStake1OtherView;
    View btStake2DelegateView;
    View btStake2OtherView;
    View btStake2Tip;
    View btStakeManagement;
    private long canDelegated;
    private String controllName;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private long freezeTrx;
    private boolean fromMultiSign;
    ImageView ivFromOther;
    ImageView ivFromSelf;
    View llBandwidthFree;
    View llStake1Other;
    View llStake1Self;
    View llStake2Delegate;
    View llStake2Other;
    View llStake2Self;
    View llStakeFromOtherDetail;
    View llStakeFromSelfDetail;
    private NumberFormat numberFormat;
    private MultiSignPermissionData permissionData;
    private ResourcesBean resourcesBean;
    TextView tvBandwidthFree;
    TextView tvDelegateAvailable;
    TextView tvDelegated;
    TextView tvFromOther;
    TextView tvFromSelf;
    TextView tvResourceDetail;
    TextView tvResourceManagement;
    TextView tvStake1Other;
    TextView tvStake1Self;
    TextView tvStake2Delegate;
    TextView tvStake2Other;
    TextView tvStake2Self;
    private boolean viewCreated;

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FgResourcesEnergyBinding inflate = FgResourcesEnergyBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        LinearLayout root = inflate.getRoot();
        setType(0);
        mappingId();
        return root;
    }

    public void mappingId() {
        this.tvResourceDetail = this.binding.tvResourceDetail;
        this.tvFromSelf = this.binding.tvEnergyFromSelf;
        this.ivFromSelf = this.binding.ivEnergyFromSelf;
        this.llStakeFromSelfDetail = this.binding.llStakeFromSelfDetail;
        this.llStake1Self = this.binding.llStake1Self;
        this.tvStake1Self = this.binding.tvStake1Self;
        this.llStake2Self = this.binding.llStake2Self;
        this.tvStake2Self = this.binding.tvStake2Self;
        this.llStake2Delegate = this.binding.llStake2Delegate;
        this.btStake2DelegateView = this.binding.tvStake2DelegateView;
        this.tvStake2Delegate = this.binding.tvStake2DelegateAmount;
        this.tvFromOther = this.binding.tvEnergyFromOther;
        this.ivFromOther = this.binding.ivEnergyFromOther;
        this.llStakeFromOtherDetail = this.binding.llStakeFromOtherDetail;
        this.llStake1Other = this.binding.llStake1Other;
        this.btStake1OtherView = this.binding.tvStake1OtherView;
        this.tvStake1Other = this.binding.tvStake1OtherAmount;
        this.llStake2Other = this.binding.llStake2Other;
        this.btStake2OtherView = this.binding.tvStake2OtherView;
        this.tvStake2Other = this.binding.tvStake2OtherAmount;
        this.tvResourceManagement = this.binding.tvEnergyManagement;
        this.btStake2Tip = this.binding.ivStake2Tip;
        this.btStakeManagement = this.binding.btEnergyView;
        this.tvDelegated = this.binding.tvDelegated;
        this.btRecycle = this.binding.btRecycle;
        this.tvDelegateAvailable = this.binding.tvDelegateAvailable;
        this.btDelegate = this.binding.btDelegate;
        this.llBandwidthFree = this.binding.llBandwidthFree;
        this.tvBandwidthFree = this.binding.tvBandwidthFree;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.numberFormat = NumberFormat.getNumberInstance(Locale.US);
    }

    @Override
    protected void processData() {
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            this.fromMultiSign = arguments.getBoolean(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
            this.controllerAddress = arguments.getString("key_controller_address");
            this.controllName = arguments.getString("key_controller_name");
            this.permissionData = (MultiSignPermissionData) arguments.getParcelable(CommonBundleKeys.KEY_PERMISSION_DATA);
        } else {
            this.controllerAddress = WalletUtils.getSelectedWallet().getAddress();
        }
        initUI();
    }

    private void initUI() {
        int max = Math.max((int) this.btDelegate.getPaint().measureText(getResources().getString(R.string.resource_delegated)), (int) this.btRecycle.getPaint().measureText(getResources().getString(R.string.resource_recycle))) + UIUtils.dip2px(10.0f);
        this.btDelegate.getLayoutParams().width = max;
        this.btRecycle.getLayoutParams().width = max;
        this.tvResourceDetail.setText(getResources().getString(R.string.bandwidth_detail));
        this.tvResourceManagement.setText(getResources().getString(R.string.bandwidth_manager));
        this.llBandwidthFree.setVisibility(View.VISIBLE);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivFromSelf, 10);
        this.ivFromSelf.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (ivFromSelf.getVisibility() == 8) {
                    return;
                }
                if (llStakeFromSelfDetail.getVisibility() == 8) {
                    ivFromSelf.setImageResource(R.mipmap.ic_arrow_up_circle);
                    llStakeFromSelfDetail.setVisibility(View.VISIBLE);
                    return;
                }
                ivFromSelf.setImageResource(R.mipmap.ic_arrow_down_circle);
                llStakeFromSelfDetail.setVisibility(View.GONE);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.ivFromOther, 10);
        this.ivFromOther.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (ivFromOther.getVisibility() == 8) {
                    return;
                }
                if (llStakeFromOtherDetail.getVisibility() == 8) {
                    ivFromOther.setImageResource(R.mipmap.ic_arrow_up_circle);
                    llStakeFromOtherDetail.setVisibility(View.VISIBLE);
                    return;
                }
                ivFromOther.setImageResource(R.mipmap.ic_arrow_down_circle);
                llStakeFromOtherDetail.setVisibility(View.GONE);
            }
        });
        this.btStake2DelegateView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(android.view.View r18) {
                


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.stakev2.managementv2.ResourceBandwidthFragment.3.onNoDoubleClick(android.view.View):void");
            }
        });
        this.btStake1OtherView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                DelegateForMePopup.showPopup(getIContext(), controllerAddress, 1, 1, fromMultiSign, null, null);
                AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH_GIVEME);
            }
        });
        this.btStake2OtherView.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                DelegateForMePopup.showPopup(getIContext(), controllerAddress, 1, 2, fromMultiSign, null, null);
                AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH_HISAGENCY);
            }
        });
        TouchDelegateUtils.expandViewTouchDelegate(this.btStake2Tip, 10);
        this.btStake2Tip.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                new MultiLineTextPopupView.Builder().setAnchor(view).setRequiredWidth(UIUtils.dip2px(200.0f)).setOffsetX(UIUtils.dip2px(15.0f)).addKeyValue(getResources().getString(R.string.stake_2_tip), "").build(mContext).show();
            }
        });
        this.btStakeManagement.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                long j;
                MultiSignPermissionData permissionData = ((ResourceManagementV2Activity) getActivity()).getPermissionData();
                long j2 = -1;
                try {
                    j = (StringTronUtil.isEmpty(tvDelegated.getText().toString()) || !HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(tvDelegateAvailable.getText().toString())) ? -1L : numberFormat.parse(tvDelegated.getText().toString()).longValue();
                    try {
                        if (!StringTronUtil.isEmpty(tvDelegateAvailable.getText().toString()) && HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(tvDelegateAvailable.getText().toString())) {
                            j2 = numberFormat.parse(tvDelegateAvailable.getText().toString()).longValue();
                        }
                    } catch (ParseException e) {
                        e = e;
                        LogUtils.e(e);
                        StakeManageDetailActivity.start(getIContext(), controllerAddress, 1, Long.valueOf(j), Long.valueOf(j2), (Protocol.Account) getArguments().getSerializable("key_account"), Long.valueOf(freezeTrx), fromMultiSign, controllName, permissionData);
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH_LOOK);
                    }
                } catch (ParseException e2) {
                    e = e2;
                    j = -1;
                }
                StakeManageDetailActivity.start(getIContext(), controllerAddress, 1, Long.valueOf(j), Long.valueOf(j2), (Protocol.Account) getArguments().getSerializable("key_account"), Long.valueOf(freezeTrx), fromMultiSign, controllName, permissionData);
                AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH_LOOK);
            }
        });
        this.btRecycle.setOnClickListener(new fun8());
        this.btDelegate.setOnClickListener(new fun9());
    }

    public class fun8 extends NoDoubleClickListener2 {
        fun8() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            long j;
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH_RECYCLE);
            Protocol.Account account = (Protocol.Account) getArguments().getSerializable("key_account");
            if (!fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(account)) {
                OwnerPermissionCheckUtils.showMultiSignDialog(mContext, R.string.lack_of_undelegate_permission, R.string.multi_reclaim, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        ResourceBandwidthFragment.8.this.lambda$onNoDoubleClick$0((Void) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            } else if (permissionData != null && !permissionData.isUnDelegateResourcePermission()) {
                ResourceBandwidthFragment resourceBandwidthFragment = ResourceBandwidthFragment.this;
                resourceBandwidthFragment.toast(resourceBandwidthFragment.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.resource_recycle)));
            } else {
                long j2 = -1;
                try {
                    j = numberFormat.parse(tvDelegated.getText().toString()).longValue();
                    try {
                        j2 = numberFormat.parse(tvDelegateAvailable.getText().toString()).longValue();
                    } catch (ParseException e) {
                        e = e;
                        LogUtils.e(e);
                        ResourceBandwidthFragment resourceBandwidthFragment2 = ResourceBandwidthFragment.this;
                        resourceBandwidthFragment2.controllerAccount = ((ResourceManagementV2Activity) resourceBandwidthFragment2.getActivity()).getControllerAccount();
                        StakeManageDetailActivity.start(getIContext(), controllerAddress, 1, Long.valueOf(j), Long.valueOf(j2), controllerAccount, Long.valueOf(freezeTrx), fromMultiSign, controllName, permissionData);
                    }
                } catch (ParseException e2) {
                    e = e2;
                    j = -1;
                }
                ResourceBandwidthFragment resourceBandwidthFragment22 = ResourceBandwidthFragment.this;
                resourceBandwidthFragment22.controllerAccount = ((ResourceManagementV2Activity) resourceBandwidthFragment22.getActivity()).getControllerAccount();
                StakeManageDetailActivity.start(getIContext(), controllerAddress, 1, Long.valueOf(j), Long.valueOf(j2), controllerAccount, Long.valueOf(freezeTrx), fromMultiSign, controllName, permissionData);
            }
        }

        public void lambda$onNoDoubleClick$0(Void r3) {
            Bundle bundle = new Bundle();
            bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, 1);
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
        }
    }

    public class fun9 extends NoDoubleClickListener2 {
        fun9() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH_AGENCY);
            Protocol.Account account = (Protocol.Account) getArguments().getSerializable("key_account");
            if (!fromMultiSign && !OwnerPermissionCheckUtils.checkHasOwnerPermission(account)) {
                OwnerPermissionCheckUtils.showMultiSignDialog(mContext, R.string.lack_of_delegate_permission, R.string.multi_delegate, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        ResourceBandwidthFragment.9.this.lambda$onNoDoubleClick$0((Void) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
            } else if (permissionData != null && !permissionData.isDelegateResourcePermission()) {
                ResourceBandwidthFragment resourceBandwidthFragment = ResourceBandwidthFragment.this;
                resourceBandwidthFragment.toast(resourceBandwidthFragment.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.resource_delegate)));
            } else {
                SelectSendAddressActivity.startForDelegate(getIContext(), ((ResourceManagementV2Activity) getActivity()).getControllerAccount(), controllerAddress, controllName, PageType.DELEGATE_BANDWIDTH, canDelegated, freezeTrx, fromMultiSign);
            }
        }

        public void lambda$onNoDoubleClick$0(Void r3) {
            Bundle bundle = new Bundle();
            bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, 1);
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.viewCreated = true;
        updateData(this.resourcesBean);
        return onCreateView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.viewCreated = false;
        this.binding = null;
    }

    public void updateData(long j, long j2) {
        this.canDelegated = j;
        this.freezeTrx = j2;
        this.tvDelegateAvailable.setText(this.numberFormat.format(j));
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        this.btDelegate.setTextColor(getResources().getColor(i > 0 ? R.color.black_23 : R.color.gray_9B));
        this.btDelegate.setEnabled(i > 0);
    }

    public void updateData(ResourcesBean resourcesBean) {
        Resources resources;
        int i;
        this.resourcesBean = resourcesBean;
        if (!this.viewCreated || resourcesBean == null) {
            return;
        }
        this.tvFromSelf.setText(this.numberFormat.format(resourcesBean.getBandwidthFromSelfTotal()));
        if (resourcesBean.getBandwidthFromSelfTotal() > 0) {
            this.ivFromSelf.setVisibility(View.VISIBLE);
            this.llStake1Self.setVisibility(resourcesBean.getBandwidthFromSelfByStakeV1() > 0 ? View.VISIBLE : View.GONE);
            this.tvStake1Self.setText(this.numberFormat.format(resourcesBean.getBandwidthFromSelfByStakeV1()));
            this.llStake2Self.setVisibility(resourcesBean.getBandwidthFromSelfByStakeV2() > 0 ? View.VISIBLE : View.GONE);
            this.tvStake2Self.setText(this.numberFormat.format(resourcesBean.getBandwidthFromSelfByStakeV2()));
            this.llStake2Delegate.setVisibility(resourcesBean.getBandwidthDelegatedToOther() > 0 ? View.VISIBLE : View.GONE);
            TextView textView = this.tvStake2Delegate;
            textView.setText(HelpFormatter.DEFAULT_OPT_PREFIX + this.numberFormat.format(resourcesBean.getBandwidthDelegatedToOther()));
        } else {
            this.ivFromSelf.setVisibility(View.GONE);
            this.llStakeFromSelfDetail.setVisibility(View.GONE);
        }
        this.tvFromOther.setText(this.numberFormat.format(resourcesBean.getBandwidthFromOtherTotal()));
        if (resourcesBean.getBandwidthFromOtherTotal() > 0) {
            this.ivFromOther.setVisibility(View.VISIBLE);
            this.llStake1Other.setVisibility(resourcesBean.getBandwidthFromOtherByStakeV1() > 0 ? View.VISIBLE : View.GONE);
            this.tvStake1Other.setText(this.numberFormat.format(resourcesBean.getBandwidthFromOtherByStakeV1()));
            this.llStake2Other.setVisibility(resourcesBean.getBandwidthFromOtherByStakeV2() > 0 ? View.VISIBLE : View.GONE);
            this.tvStake2Other.setText(this.numberFormat.format(resourcesBean.getBandwidthFromOtherByStakeV2()));
        } else {
            this.ivFromOther.setVisibility(View.GONE);
            this.llStakeFromOtherDetail.setVisibility(View.GONE);
        }
        this.tvBandwidthFree.setText(this.numberFormat.format(resourcesBean.getBandwidthFree()));
        this.tvDelegated.setText(this.numberFormat.format(resourcesBean.getBandwidthDelegatedToOther()));
        TextView textView2 = this.btRecycle;
        if (resourcesBean.getBandwidthDelegatedToOther() > 0) {
            resources = getResources();
            i = R.color.black_23;
        } else {
            resources = getResources();
            i = R.color.gray_9B;
        }
        textView2.setTextColor(resources.getColor(i));
        this.btRecycle.setEnabled(resourcesBean.getBandwidthDelegatedToOther() > 0);
    }
}
