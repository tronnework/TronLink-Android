package com.tron.wallet.business.stakev2.managementv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Activity;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Contract;
import com.tron.wallet.business.tronpower.stake2.StakeTRX2Activity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.components.CircularProgressView;
import com.tron.wallet.common.components.ptr.PtrDefaultHandler;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayout;
import com.tron.wallet.common.components.ptr.PtrHandler;
import com.tron.wallet.common.config.CommonBundleKeys;
import com.tron.wallet.common.task.AccountUpdater;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.NumUtils;
import com.tron.wallet.common.utils.OwnerPermissionCheckUtils;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.databinding.AcResourcesV2Binding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class ResourceManagementV2Activity extends BaseActivity<ResourceManagementV2Presenter, ResourceManagementV2Model> implements ResourceManagementV2Contract.View {
    public static final int RESOURCE_BANDWIDTH = 1;
    public static final int RESOURCE_ENERGY = 0;
    public static final String RESOURCE_TYPE = "resourceType";
    private AcResourcesV2Binding binding;
    TextView btGo;
    View btMultiSign;
    Button btSend;
    private Protocol.Account controllerAccount;
    private String controllerAddress;
    private String controllerName;
    CircularProgressView cpBandwidth;
    CircularProgressView cpEnergy;
    private boolean fromMultiSign;
    View ivBack;
    View ivBackground;
    View ivHelp;
    View llBtnGo;
    View llContent;
    View llHeaderBandwidth;
    View llHeaderEnergy;
    private NumberFormat numberFormat;
    private PagerAdapter pagerAdapter;
    private MultiSignPermissionData permissionData;
    PtrHTFrameLayout ptrLayout;
    private ResourceBandwidthFragment resourceBandwidthFragment;
    private ResourceEnergyFragment resourceEnergyFragment;
    private int resourceType = 0;
    private ResourcesBean resourcesBean;
    private Wallet selectWallet;
    TextView tvBandwidth;
    TextView tvBandwidthTotal;
    TextView tvEnergy;
    TextView tvEnergyTotal;
    TextView tvMultiSignWarning;
    TextView tvTitle;
    ViewPager2 viewPager;

    public Protocol.Account getControllerAccount() {
        return this.controllerAccount;
    }

    public MultiSignPermissionData getPermissionData() {
        return this.permissionData;
    }

    public static void start(Context context, int i) {
        Intent intent = new Intent(context, ResourceManagementV2Activity.class);
        intent.putExtra(RESOURCE_TYPE, i);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    public static void start(Context context, int i, Protocol.Account account, boolean z, String str, String str2, MultiSignPermissionData multiSignPermissionData) {
        Intent intent = new Intent(context, ResourceManagementV2Activity.class);
        intent.putExtra(RESOURCE_TYPE, i);
        intent.putExtra("key_account", account);
        intent.putExtra("key_controller_address", str);
        intent.putExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(CommonBundleKeys.KEY_PERMISSION_DATA, multiSignPermissionData);
        intent.setFlags(67108864);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        AcResourcesV2Binding inflate = AcResourcesV2Binding.inflate(getLayoutInflater());
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
        this.ivBack = this.binding.ivBack;
        this.tvTitle = this.binding.tvMainTitle;
        this.ivHelp = this.binding.ivHelp;
        this.btMultiSign = this.binding.tvMultiSign;
        this.ptrLayout = this.binding.rcFrame;
        this.llContent = this.binding.llScroll;
        this.ivBackground = this.binding.ivBackground;
        this.llHeaderEnergy = this.binding.llHeaderEnergy;
        this.llHeaderBandwidth = this.binding.llHeaderBandwidth;
        this.tvEnergy = this.binding.tvEnergy;
        this.tvEnergyTotal = this.binding.tvEnergyTotal;
        this.tvBandwidth = this.binding.tvBandwidth;
        this.tvBandwidthTotal = this.binding.tvBandwidthTotal;
        this.cpEnergy = this.binding.progressEnergy;
        this.cpBandwidth = this.binding.progressBandwidth;
        this.llBtnGo = this.binding.llBtGo;
        this.btGo = this.binding.btGo;
        this.viewPager = this.binding.viewpager;
        this.btSend = this.binding.btSend;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        startActivity(intent);
        exit();
    }

    @Override
    protected void processData() {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.numberFormat = numberInstance;
        numberInstance.setMaximumFractionDigits(3);
        initUI();
        ((ResourceManagementV2Presenter) this.mPresenter).setConfig(this.fromMultiSign, this.controllerAddress);
        ((ResourceManagementV2Presenter) this.mPresenter).start();
    }

    private void parseIntentArgs() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra(RESOURCE_TYPE, 0);
        if (intExtra == 1) {
            this.resourceType = intExtra;
        } else {
            this.resourceType = 0;
        }
        this.controllerAccount = (Protocol.Account) intent.getSerializableExtra("key_account");
        this.fromMultiSign = intent.getBooleanExtra(CommonBundleKeys.KEY_FROM_MULTI_SIGN, false);
        this.controllerAddress = intent.getStringExtra("key_controller_address");
        this.permissionData = (MultiSignPermissionData) intent.getParcelableExtra(CommonBundleKeys.KEY_PERMISSION_DATA);
        this.controllerName = intent.getStringExtra("key_controller_name");
        this.selectWallet = WalletUtils.getSelectedPublicWallet();
        if (!this.fromMultiSign && TextUtils.isEmpty(this.controllerAddress)) {
            Wallet wallet = this.selectWallet;
            this.controllerAddress = wallet != null ? wallet.getAddress() : "";
        } else if (this.fromMultiSign) {
            final String str = this.controllerAddress;
            if (!TextUtils.isEmpty(this.controllerName)) {
                str = String.format("%s (%s)", this.controllerName, str);
            }
            this.tvMultiSignWarning.setText(getString(R.string.multi_controller_tips, new Object[]{str}));
            this.tvMultiSignWarning.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$parseIntentArgs$0(str);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
            this.btMultiSign.setVisibility(View.GONE);
            this.tvTitle.setText(R.string.multisig);
            this.ivHelp.setVisibility(View.GONE);
        }
    }

    public void lambda$parseIntentArgs$0(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    private void initUI() {
        this.ivBack.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                exit();
            }
        });
        this.btMultiSign.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, resourceType);
                MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
            }
        });
        this.ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, llContent, view2);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ((ResourceManagementV2Presenter) mPresenter).refreshAccountResources();
            }
        });
        this.cpEnergy.setProgressColors(new int[]{getResources().getColor(R.color.yellow_E2B380)});
        this.cpBandwidth.setProgressColors(new int[]{getResources().getColor(R.color.green_57BFAD)});
        TouchDelegateUtils.expandViewTouchDelegate(this.ivHelp, 10);
        this.ivHelp.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEvent(AnalyticsHelper.ResourcePage.CLICK_RESOURCE_PAGE_HELP);
                showResourceIntro();
            }
        });
        this.llHeaderEnergy.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                bringEnergyToFront();
            }
        });
        this.llHeaderBandwidth.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                bringBandWidthToFront();
            }
        });
        this.viewPager.setOffscreenPageLimit(2);
        this.viewPager.setUserInputEnabled(false);
        PagerAdapter pagerAdapter = new PagerAdapter((FragmentActivity) this.mContext);
        this.pagerAdapter = pagerAdapter;
        this.viewPager.setAdapter(pagerAdapter);
        if (this.resourceType == 0) {
            bringEnergyToFront();
        } else {
            bringBandWidthToFront();
        }
        this.btGo.setOnClickListener(new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                CommonWebActivityV3.start(mContext, IRequest.getGoToJustLendUrl(), "", -2, true);
            }
        });
        showGoToJustLend();
        if (this.fromMultiSign) {
            this.btSend.setVisibility(View.GONE);
        }
        this.btSend.setOnClickListener(new fun8());
    }

    public class fun8 extends NoDoubleClickListener2 {
        fun8() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            AnalyticsHelper.ResourcePageV2.logMultiEvent(resourceType == 0 ? AnalyticsHelper.ResourcePageV2.RESOURCE_ENERGY_GET : AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH_GET, fromMultiSign);
            if (!fromMultiSign) {
                OwnerPermissionCheckUtils.checkWithPopup(mContext, ((ResourceManagementV2Presenter) mPresenter).getAccount(), new int[]{R.string.stake_account_unactive, R.string.multistake}, new int[]{R.string.lack_of_stake_permission, R.string.multistake}, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        ResourceManagementV2Activity.8.this.lambda$onNoDoubleClick$0((Void) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                }, new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        ResourceManagementV2Activity.8.this.lambda$onNoDoubleClick$1((Void) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
                return;
            }
            if (permissionData != null && !permissionData.isFreezeBalanceV2Permission()) {
                ResourceManagementV2Activity resourceManagementV2Activity = ResourceManagementV2Activity.this;
                resourceManagementV2Activity.toast(resourceManagementV2Activity.getResources().getString(R.string.do_not_have_multi_permission, getResources().getString(R.string.stake_2)));
                return;
            }
            StakeTRX2Activity.startWithCheckOwnerPermission(mContext, resourceType == 0, ((ResourceManagementV2Presenter) mPresenter).getAccount(), DataStatHelper.StatAction.Stake, controllerAddress);
        }

        public void lambda$onNoDoubleClick$0(Void r5) {
            StakeTRX2Activity.startWithCheckOwnerPermission(mContext, resourceType == 0, ((ResourceManagementV2Presenter) mPresenter).getAccount(), DataStatHelper.StatAction.Stake, controllerAddress);
        }

        public void lambda$onNoDoubleClick$1(Void r3) {
            Bundle bundle = new Bundle();
            bundle.putInt(ResourceManagementV2Activity.RESOURCE_TYPE, resourceType);
            MultiSelectAddressActivity.start(getIContext(), MultiSourcePageEnum.Resources, bundle);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ResourceEnergyFragment resourceEnergyFragment = this.resourceEnergyFragment;
        if (resourceEnergyFragment != null && resourceEnergyFragment.isAdded()) {
            supportFragmentManager.putFragment(bundle, "ResourceEnergyFragment0", this.resourceEnergyFragment);
        }
        ResourceBandwidthFragment resourceBandwidthFragment = this.resourceBandwidthFragment;
        if (resourceBandwidthFragment == null || !resourceBandwidthFragment.isAdded()) {
            return;
        }
        supportFragmentManager.putFragment(bundle, "ResourceBandwidthFragment1", this.resourceBandwidthFragment);
    }

    @Override
    public void onCreate2(Bundle bundle) {
        super.onCreate2(bundle);
        parseIntentArgs();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (bundle != null) {
            this.resourceEnergyFragment = (ResourceEnergyFragment) supportFragmentManager.getFragment(bundle, "ResourceEnergyFragment0");
            this.resourceBandwidthFragment = (ResourceBandwidthFragment) supportFragmentManager.getFragment(bundle, "ResourceBandwidthFragment1");
        }
        if (this.resourceEnergyFragment == null) {
            this.resourceEnergyFragment = new ResourceEnergyFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean(CommonBundleKeys.KEY_FROM_MULTI_SIGN, this.fromMultiSign);
            bundle2.putString("key_controller_address", this.controllerAddress);
            bundle2.putString("key_controller_name", this.controllerName);
            bundle2.putParcelable(CommonBundleKeys.KEY_PERMISSION_DATA, this.permissionData);
            this.resourceEnergyFragment.setArguments(bundle2);
        }
        if (this.resourceBandwidthFragment == null) {
            this.resourceBandwidthFragment = new ResourceBandwidthFragment();
            Bundle bundle3 = new Bundle();
            bundle3.putBoolean(CommonBundleKeys.KEY_FROM_MULTI_SIGN, this.fromMultiSign);
            bundle3.putString("key_controller_address", this.controllerAddress);
            bundle3.putString("key_controller_name", this.controllerName);
            bundle3.putParcelable(CommonBundleKeys.KEY_PERMISSION_DATA, this.permissionData);
            this.resourceBandwidthFragment.setArguments(bundle3);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mPresenter != 0) {
            ((ResourceManagementV2Presenter) this.mPresenter).refreshAccountResources();
        }
        AccountUpdater.singleShot(0L);
    }

    @Override
    public void updateUI(ResourcesBean resourcesBean) {
        this.ptrLayout.refreshComplete();
        if (resourcesBean == null || !resourcesBean.isValid()) {
            return;
        }
        this.resourcesBean = resourcesBean;
        this.tvEnergy.setText(NumUtils.numFormatToK(resourcesBean.getEnergyUsable()));
        TextView textView = this.tvEnergyTotal;
        textView.setText("/" + NumUtils.numFormatToK(resourcesBean.getEnergyTotal()));
        this.cpEnergy.setProgress(resourcesBean.getEnergyTotal() == 0 ? 0.0f : ((float) resourcesBean.getEnergyUsable()) / ((float) resourcesBean.getEnergyTotal()));
        this.tvBandwidth.setText(NumUtils.numFormatToK(resourcesBean.getBandwidthUsable()));
        TextView textView2 = this.tvBandwidthTotal;
        textView2.setText("/" + NumUtils.numFormatToK(resourcesBean.getBandwidthTotal()));
        this.cpBandwidth.setProgress(resourcesBean.getBandwidthTotal() != 0 ? ((float) resourcesBean.getBandwidthUsable()) / ((float) resourcesBean.getBandwidthTotal()) : 0.0f);
        this.resourceEnergyFragment.updateData(resourcesBean);
        this.resourceBandwidthFragment.updateData(resourcesBean);
        this.resourceEnergyFragment.getArguments().putSerializable("key_account", ((ResourceManagementV2Presenter) this.mPresenter).getAccount());
        this.resourceBandwidthFragment.getArguments().putSerializable("key_account", ((ResourceManagementV2Presenter) this.mPresenter).getAccount());
    }

    @Override
    public void updateUI(int i, long j, long j2) {
        if (i == 0) {
            this.resourceBandwidthFragment.updateData(j, j2);
        } else if (i == 1) {
            this.resourceEnergyFragment.updateData(j, j2);
        }
    }

    @Override
    public void showResourceIntro() {
        new XPopup.Builder(this).maxWidth(XPopupUtils.getScreenWidth(this)).dismissOnTouchOutside(true).popupAnimation(PopupAnimation.NoAnimation).asCustom(new fun9(this)).show();
    }

    public class fun9 extends CenterPopupView {
        @Override
        public int getImplLayoutId() {
            return R.layout.popup_resources_help;
        }

        fun9(Context context) {
            super(context);
        }

        @Override
        public void onCreate() {
            super.onCreate();
            findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    ResourceManagementV2Activity.9.this.lambda$onCreate$0(view);
                }
            });
        }

        public void lambda$onCreate$0(View view) {
            dismiss();
        }
    }

    private void showGoToJustLend() {
        if (IRequest.isShasta() || this.fromMultiSign || this.resourceType == 1) {
            this.llBtnGo.setVisibility(View.GONE);
        } else {
            this.llBtnGo.setVisibility(View.VISIBLE);
        }
    }

    public void bringEnergyToFront() {
        this.resourceType = 0;
        this.llHeaderEnergy.bringToFront();
        Drawable background = this.llHeaderEnergy.getBackground();
        background.setTint(getResources().getColor(R.color.white));
        background.setTintMode(PorterDuff.Mode.SRC_IN);
        this.llHeaderBandwidth.getBackground().setTint(getResources().getColor(R.color.orange_FDECD9));
        background.setTintMode(PorterDuff.Mode.SRC_IN);
        this.ivBackground.setBackground(getResources().getDrawable(R.drawable.bg_resources_manager_v2));
        this.viewPager.setCurrentItem(0, false);
        this.btSend.setText(getResources().getString(R.string.resource_get_energy));
        showGoToJustLend();
        AnalyticsHelper.ResourcePageV2.logMultiEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_ENERGY, this.fromMultiSign);
    }

    public void bringBandWidthToFront() {
        this.resourceType = 1;
        this.llHeaderBandwidth.bringToFront();
        Drawable background = this.llHeaderBandwidth.getBackground();
        background.setTint(getResources().getColor(R.color.white));
        background.setTintMode(PorterDuff.Mode.SRC_IN);
        this.llHeaderEnergy.getBackground().setTint(getResources().getColor(R.color.green_D5F0));
        background.setTintMode(PorterDuff.Mode.SRC_IN);
        this.ivBackground.setBackground(getResources().getDrawable(R.drawable.bg_resources_manager_v2_band));
        this.viewPager.setCurrentItem(1, false);
        this.btSend.setText(getResources().getString(R.string.resource_get_bandwidth));
        showGoToJustLend();
        AnalyticsHelper.ResourcePageV2.logMultiEvent(AnalyticsHelper.ResourcePageV2.RESOURCE_BANDWIDTH, this.fromMultiSign);
    }

    public class PagerAdapter extends FragmentStateAdapter {
        @Override
        public int getItemCount() {
            return 2;
        }

        public PagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public Fragment createFragment(int i) {
            return i == 0 ? resourceEnergyFragment : resourceBandwidthFragment;
        }
    }
}
