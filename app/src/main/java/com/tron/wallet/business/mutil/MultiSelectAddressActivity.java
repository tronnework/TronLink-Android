package com.tron.wallet.business.mutil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressContract;
import com.tron.wallet.business.mutil.ResStringProvider;
import com.tron.wallet.business.mutil.bean.MultiAddressOutput;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.mutil.bean.MultiSourcePageEnum;
import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Activity;
import com.tron.wallet.business.stakev2.stake.StakeHomeActivity;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.business.tronpower.stake.StakeTRXActivity;
import com.tron.wallet.business.tronpower.unstake.UnStakeActivity;
import com.tron.wallet.business.vote.component.VoteMainActivity;
import com.tron.wallet.business.web.CommonWebActivityV3;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.SimpleListFragment;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.EllipsizeTextViewUtils;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcMultiSelectAddressBinding;
import com.tron.wallet.databinding.ItemInputAddressBinding;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import j$.util.Objects;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;
public class MultiSelectAddressActivity extends BaseActivity<MultiSelectAddressPresenter, MultiSelectAddressModel> implements MultiSelectAddressContract.View {
    public static String MultiSourcePageEnumKey = "MultiSourcePageEnumKey";
    private AcMultiSelectAddressBinding binding;
    private SimpleListFragment dataFragment;
    FrameLayout frameSearch;
    private ItemInputAddressBinding inputBinding;
    private MultiSourcePageEnum multiSourcePageEnum;
    private NameAddressExtraBean recentNameAddressExtraBean;
    private ResStringProvider resStringProvider;
    View rlData;
    private SimpleListFragment searchFragment;
    private DataStatHelper.StatAction statAction;
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), new ActivityResultCallback() {
        @Override
        public final void onActivityResult(Object obj) {
            lambda$new$0((ScanIntentResult) obj);
        }
    });
    private PermissionHelper permissionHelper = new PermissionHelper(this, new PermissionInterface() {
        @Override
        public int getPermissionsRequestCode() {
            return 2001;
        }

        @Override
        public String[] getPermissions() {
            return new String[]{"android.permission.CAMERA"};
        }

        @Override
        public void requestPermissionsSuccess() {
            ScannerActivity.start(barcodeLauncher);
        }

        @Override
        public void requestPermissionsFail() {
            ToastError(R.string.error_permission);
        }
    });

    @Override
    public MultiSourcePageEnum getMultiSourcePageEnum() {
        return this.multiSourcePageEnum;
    }

    @Override
    public NameAddressExtraBean getRecentNameAddressExtraBean() {
        return this.recentNameAddressExtraBean;
    }

    public static void start(Context context, MultiSourcePageEnum multiSourcePageEnum) {
        Intent intent = new Intent(context, MultiSelectAddressActivity.class);
        intent.putExtra(MultiSourcePageEnumKey, multiSourcePageEnum);
        context.startActivity(intent);
    }

    public static void start(Context context, MultiSourcePageEnum multiSourcePageEnum, Bundle bundle) {
        Intent intent = new Intent(context, MultiSelectAddressActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(MultiSourcePageEnumKey, multiSourcePageEnum);
        context.startActivity(intent);
    }

    public static void start(Context context, MultiSourcePageEnum multiSourcePageEnum, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent(context, MultiSelectAddressActivity.class);
        intent.putExtra(MultiSourcePageEnumKey, multiSourcePageEnum);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    public static void start(Context context, TokenBean tokenBean, MultiSourcePageEnum multiSourcePageEnum) {
        Intent intent = new Intent(context, MultiSelectAddressActivity.class);
        intent.putExtra(TronConfig.TRC, tokenBean);
        intent.putExtra(MultiSourcePageEnumKey, multiSourcePageEnum);
        context.startActivity(intent);
    }

    public void lambda$new$0(ScanIntentResult scanIntentResult) {
        setScanAndPasteEdiTextContent(scanIntentResult.getContents());
    }

    public void setScanAndPasteEdiTextContent(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return;
        }
        if (StringTronUtil.isAddressValid(str)) {
            showEdiTextError(false);
            this.inputBinding.etInputAddress.setText(str);
            NameAddressExtraBean createNameAddressExtraBean = ((MultiSelectAddressPresenter) this.mPresenter).createNameAddressExtraBean(str);
            if (createNameAddressExtraBean != null) {
                this.inputBinding.etInputAddress.setText(getEdiTextSpecialStyle(createNameAddressExtraBean));
                this.inputBinding.etInputAddress.setClickable(false);
                this.inputBinding.etInputAddress.setFocusable(false);
                return;
            }
            this.inputBinding.etInputAddress.setText(str);
        } else if (str.contains("...")) {
            showEdiTextError(false);
        } else {
            this.inputBinding.etInputAddress.setText(str);
            showEdiTextError(true, new ResStringProvider.ErrorViewText(ErrorView.Level.ERROR, getString(R.string.incor_address_format)));
            this.inputBinding.errorView.updateWarning(ErrorView.Level.ERROR);
            this.inputBinding.etInputAddress.clearFocus();
        }
    }

    public void setClick() {
        NoDoubleClickListener2 noDoubleClickListener2 = new NoDoubleClickListener2() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.iv_back:
                        exit();
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.MultiSelectAddress.CLICK_MULTI_SELECT_ADDRESS_BACK, Integer.valueOf(resStringProvider.getLogEventParam()));
                        return;
                    case R.id.iv_delete:
                        inputBinding.etInputAddress.setText("");
                        showDataView();
                        inputBinding.etInputAddress.setClickable(true);
                        inputBinding.etInputAddress.setFocusable(true);
                        inputBinding.etInputAddress.setFocusableInTouchMode(true);
                        inputBinding.etInputAddress.requestFocus();
                        return;
                    case R.id.iv_scan:
                        permissionHelper.requestPermissions();
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.MultiSelectAddress.CLICK_MULTI_SELECT_ADDRESS_SCAN, Integer.valueOf(resStringProvider.getLogEventParam()));
                        return;
                    case R.id.iv_tip2:
                        PopupWindowUtil.showPopupText(mContext, getResources().getString(R.string.accounts_with_control_authority), findViewById(R.id.iv_tip2), true);
                        return;
                    case R.id.tv_paste:
                        String pasteString = ((MultiSelectAddressPresenter) mPresenter).getPasteString();
                        AnalyticsHelper.logEventFormat(AnalyticsHelper.MultiSelectAddress.CLICK_MULTI_SELECT_ADDRESS_PASTE, Integer.valueOf(resStringProvider.getLogEventParam()));
                        if (StringTronUtil.isEmpty(pasteString)) {
                            return;
                        }
                        setScanAndPasteEdiTextContent(pasteString);
                        return;
                    case R.id.tv_tutorial:
                        AnalyticsHelper.logEvent(AnalyticsHelper.MultiSelectAddress.CLICK_MULTI_SELECT_ADDRESS_TUTORIAL);
                        CommonWebActivityV3.start(mContext, resStringProvider.getTutorialUrl(), getString(R.string.tutorial), -2, false);
                        return;
                    default:
                        return;
                }
            }
        };
        this.inputBinding.tvPaste.setOnClickListener(noDoubleClickListener2);
        this.inputBinding.ivDelete.setOnClickListener(noDoubleClickListener2);
        this.inputBinding.ivScan.setOnClickListener(noDoubleClickListener2);
        this.binding.tvTutorial.setOnClickListener(noDoubleClickListener2);
        this.binding.ivTip2.setOnClickListener(noDoubleClickListener2);
        this.binding.ivBack.setOnClickListener(noDoubleClickListener2);
    }

    @Override
    protected void setLayout() {
        AcMultiSelectAddressBinding inflate = AcMultiSelectAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        setView(root, 0);
        this.inputBinding = ItemInputAddressBinding.bind(root.findViewById(R.id.rl_address));
    }

    @Override
    protected void processData() {
        this.frameSearch = this.binding.frameSearch;
        this.rlData = this.binding.rlData;
        setClick();
        this.multiSourcePageEnum = (MultiSourcePageEnum) getIntent().getSerializableExtra(MultiSourcePageEnumKey);
        this.statAction = (DataStatHelper.StatAction) getIntent().getSerializableExtra(TronConfig.StatAction_Key);
        if (this.multiSourcePageEnum == null) {
            this.multiSourcePageEnum = MultiSourcePageEnum.Transfer;
        }
        this.resStringProvider = ResStringProvider.init(this.multiSourcePageEnum);
        this.binding.tvMainTitle.setText(this.resStringProvider.getTitle());
        this.binding.tvStep.setText(this.resStringProvider.getTitleStep());
        ((MultiSelectAddressPresenter) this.mPresenter).addTextChangedListener(this.inputBinding.etInputAddress);
        ((MultiSelectAddressPresenter) this.mPresenter).getMultiAddress();
        this.inputBinding.errorView.updateWarning(ErrorView.Level.ERROR);
        this.inputBinding.etInputAddress.setHint(R.string.multi_input_hint);
        this.inputBinding.tvTitle.setText(R.string.account_in_control);
        int dip2px = UIUtils.dip2px(15.0f);
        TouchDelegateUtils.expandViewTouchDelegate(this.inputBinding.ivDelete, 0, dip2px, dip2px, dip2px);
        addKeyBoardEvent();
        this.binding.btnNext.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AnalyticsHelper.logEventFormat(AnalyticsHelper.MultiSelectAddress.CLICK_MULTI_SELECT_ADDRESS_NEXT, Integer.valueOf(resStringProvider.getLogEventParam()));
                if (SpAPI.THIS.getCurIsMainChain()) {
                    clickNext();
                } else {
                    clickNextFromDAppChain();
                }
            }
        });
    }

    public void addKeyBoardEvent() {
        KeyboardUtil.assistActivity(this, new KeyboardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int i) {
            }

            @Override
            public void keyBoardHide(int i) {
                if (inputBinding.etInputAddress == null || TextUtils.isEmpty(inputBinding.etInputAddress.getText())) {
                    return;
                }
                MultiSelectAddressActivity multiSelectAddressActivity = MultiSelectAddressActivity.this;
                multiSelectAddressActivity.setScanAndPasteEdiTextContent(multiSelectAddressActivity.inputBinding.etInputAddress.getText().toString().trim());
                hideSoftKeyboard();
            }
        });
    }

    public void hideSoftKeyboard() {
        try {
            KeyboardUtils.hideSoftInput(getWindow().getDecorView());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void showDataView(List<NameAddressExtraBean> list) {
        showDataView();
        if (this.dataFragment == null) {
            SimpleListFragment simpleListFragment = SimpleListFragment.getInstance();
            this.dataFragment = simpleListFragment;
            simpleListFragment.setEmptyTextRes(this.resStringProvider.getDataEmptyText());
        }
        this.dataFragment.updateData(list, getOnItemSelectCallback());
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_data, this.dataFragment).commitAllowingStateLoss();
    }

    @Override
    public void showSearchView(List<NameAddressExtraBean> list) {
        this.frameSearch.setVisibility(list.isEmpty() ? View.GONE : View.VISIBLE);
        this.rlData.setVisibility(View.GONE);
        if (this.searchFragment == null) {
            SimpleListFragment simpleListFragment = SimpleListFragment.getInstance();
            this.searchFragment = simpleListFragment;
            simpleListFragment.setEmptyTextRes(this.resStringProvider.getDataEmptyText());
        }
        this.searchFragment.updateData(list, getOnItemSelectCallback());
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_search, this.searchFragment).commitAllowingStateLoss();
    }

    @Override
    public void showDataView() {
        this.rlData.setVisibility(View.VISIBLE);
        this.binding.splitLine.setVisibility(View.VISIBLE);
        this.frameSearch.setVisibility(View.GONE);
    }

    @Override
    public void setButtonStatus(boolean z) {
        this.binding.btnNext.setClickable(z);
        this.binding.btnNext.setEnabled(z);
    }

    @Override
    public void showEdiTextError(boolean z, ResStringProvider.ErrorViewText errorViewText) {
        if (z && !StringTronUtil.isEmpty(errorViewText.text)) {
            this.inputBinding.errorView.setVisibility(View.VISIBLE);
            this.rlData.setVisibility(View.GONE);
            this.binding.splitLine.setVisibility(View.GONE);
            this.inputBinding.errorView.setText((CharSequence) errorViewText.text, true);
            this.inputBinding.errorView.updateWarning(errorViewText.level);
            return;
        }
        this.inputBinding.errorView.setVisibility(View.GONE);
        this.rlData.setVisibility(View.VISIBLE);
        this.binding.splitLine.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEdiTextError(boolean z) {
        showEdiTextError(z, ResStringProvider.ErrorViewText.empty());
    }

    @Override
    public void showEdiTextError(boolean z, MultiAddressOutput multiAddressOutput) {
        showEdiTextError(z, this.resStringProvider.getInputError(multiAddressOutput));
    }

    @Override
    public void updateEdiTextStatus(String str) {
        if (StringTronUtil.isEmpty(str)) {
            this.inputBinding.ivDelete.setVisibility(View.GONE);
            this.inputBinding.tvPaste.setVisibility(View.VISIBLE);
            return;
        }
        this.inputBinding.ivDelete.setVisibility(View.VISIBLE);
        this.inputBinding.tvPaste.setVisibility(View.GONE);
    }

    @Override
    public String getInputAddress(String str) {
        return (String) getInputAddressAndName(str).first;
    }

    private android.util.Pair<java.lang.String, java.lang.String> getInputAddressAndName(java.lang.String r6) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.mutil.MultiSelectAddressActivity.getInputAddressAndName(java.lang.String):android.util.Pair");
    }

    public void clickNext() {
        String trim = this.inputBinding.etInputAddress.getText().toString().trim();
        if (StringTronUtil.isEmpty(trim)) {
            return;
        }
        MultiSignPermissionData multiSignPermissionData = new MultiSignPermissionData();
        Pair<String, String> inputAddressAndName = getInputAddressAndName(trim);
        String str = (String) inputAddressAndName.first;
        String str2 = (String) inputAddressAndName.second;
        MultiAddressOutput multiAddressOutputByAddress = ((MultiSelectAddressPresenter) this.mPresenter).getMultiAddressOutputByAddress(str);
        if (multiAddressOutputByAddress != null) {
            if (multiAddressOutputByAddress.getOwnerPermission() != null && multiAddressOutputByAddress.getOwnerPermission().isHas()) {
                multiSignPermissionData = MultiSignPermissionData.getOwnerBean();
            } else {
                multiSignPermissionData.setTransferTRXPermission(multiAddressOutputByAddress.hasTransferTRXPermission());
                multiSignPermissionData.setTransferTRC10Permission(multiAddressOutputByAddress.hasTransferAssetPermission());
                multiSignPermissionData.setTransferTRC20Permission(multiAddressOutputByAddress.hasTriggerPermission());
                multiSignPermissionData.setUnStakePermission(multiAddressOutputByAddress.hasUnStakePermission());
                multiSignPermissionData.setStakePermission(multiAddressOutputByAddress.hasStakePermission());
                multiSignPermissionData.setWithdrawBalancePermission(multiAddressOutputByAddress.hasWithdrawBalancePermission());
                multiSignPermissionData.setVoteWitnessPermission(multiAddressOutputByAddress.hasVoteWitnessPermission());
                multiSignPermissionData.setFreezeBalanceV2Permission(multiAddressOutputByAddress.hasFreezeBalanceV2Permission());
                multiSignPermissionData.setUnfreezeBalanceV2Permission(multiAddressOutputByAddress.hasUnfreezeBalanceV2Permission());
                multiSignPermissionData.setWithdrawExpireUnfreezePermission(multiAddressOutputByAddress.hasWithdrawExpireUnfreezePermission());
                multiSignPermissionData.setDelegateResourcePermission(multiAddressOutputByAddress.hasDelegateResourcePermission());
                multiSignPermissionData.setUnDelegateResourcePermission(multiAddressOutputByAddress.hasUnDelegateResourcePermission());
                multiSignPermissionData.setCancelAllUnfreezePermission(multiAddressOutputByAddress.hasCancelAllUnfreezePermission());
            }
            next(multiSignPermissionData, str, str2);
        }
    }

    public static class fun6 {
        static final int[] $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum;

        static {
            int[] iArr = new int[MultiSourcePageEnum.values().length];
            $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum = iArr;
            try {
                iArr[MultiSourcePageEnum.UnStake.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Stake.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Vote.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.Resources.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[MultiSourcePageEnum.StakeV2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void next(MultiSignPermissionData multiSignPermissionData, String str, String str2) {
        int i = fun6.$SwitchMap$com$tron$wallet$business$mutil$bean$MultiSourcePageEnum[this.multiSourcePageEnum.ordinal()];
        if (i == 1) {
            UnStakeActivity.startFromMultiSign(this, null, str, str2, multiSignPermissionData);
        } else if (i == 2) {
            StakeTRXActivity.start(this, str, str2, null, this.statAction);
        } else if (i == 3) {
            VoteMainActivity.startByFinance(this, null, true, str, str2, multiSignPermissionData, this.statAction);
            exit();
        } else if (i == 4) {
            ResourceManagementV2Activity.start(this, getIntent().getIntExtra(ResourceManagementV2Activity.RESOURCE_TYPE, 0), null, true, str, str2, multiSignPermissionData);
            exit();
        } else if (i == 5) {
            StakeHomeActivity.start(this, null, true, this.statAction, str, multiSignPermissionData);
            exit();
        } else {
            SelectSendAddressActivity.startFromMultiSign(this, null, (TokenBean) getIntent().getParcelableExtra(TronConfig.TRC), str2, str, multiSignPermissionData);
        }
    }

    public void clickNextFromDAppChain() {
        String trim = this.inputBinding.etInputAddress.getText().toString().trim();
        MultiSignPermissionData ownerBean = MultiSignPermissionData.getOwnerBean();
        if (SpAPI.THIS.getCurIsMainChain() || !StringTronUtil.isAddressValid(trim)) {
            return;
        }
        next(ownerBean, trim, "");
    }

    private Consumer<NameAddressExtraBean> getOnItemSelectCallback() {
        return new Consumer<NameAddressExtraBean>() {
            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }

            @Override
            public void accept(NameAddressExtraBean nameAddressExtraBean) {
                hideSoftKeyboard();
                inputBinding.etInputAddress.setText(getEdiTextSpecialStyle(nameAddressExtraBean));
                inputBinding.etInputAddress.setClickable(false);
                inputBinding.etInputAddress.setFocusable(false);
                showDataView();
            }
        };
    }

    public String getEdiTextSpecialStyle(NameAddressExtraBean nameAddressExtraBean) {
        this.recentNameAddressExtraBean = nameAddressExtraBean;
        StringBuffer stringBuffer = new StringBuffer();
        CharSequence name = nameAddressExtraBean.getName();
        if (name == null || name.length() == 0) {
            stringBuffer.append(nameAddressExtraBean.getAddress());
            return stringBuffer.toString();
        }
        return EllipsizeTextViewUtils.ellipseNameOnly(this.inputBinding.etInputAddress, String.format("%s\n(%s)", name, nameAddressExtraBean.getAddress()), StringUtils.LF);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.permissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }
}
