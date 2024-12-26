package com.tron.wallet.business.tronpower.stake;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressContract;
import com.tron.wallet.business.tronpower.stake.SelectAddressPopView;
import com.tron.wallet.business.tronpower.stake.SelectStakeReceiveAccountContract;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.adapter.base.SimpleViewPagerAdapter;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.AddToAddressBookPopView;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.SimpleListFragment;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.DataStatHelper;
import com.tron.wallet.common.utils.EllipsizeTextViewUtils;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.databinding.AcSelectStakeAddressBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import kotlin.time.DurationKt;
import org.apache.commons.lang3.StringUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SelectStakeReceiveAccountActvitiy extends BaseActivity<SelectStakeReceiveAccountPresenter, SelectStakeReceiveAccountModel> implements SelectStakeReceiveAccountContract.View {
    public static final String KEY_CONTROLLER_ADDRESS = "key_controller_address";
    public static final String KEY_CONTROLLER_NAME = "key_controller_name";
    private static final String KEY_FROM_MULTI_SIGN = "key_from_multi_sign";
    private static final String KEY_IS_BANDWIDTH_OR_ENERGY = "key_is_bandwidth_or_energy";
    public static final String KEY_MULTI_SIGN_PERMISSIONS = "key_multi_sign_permissions";
    private static final String KEY_STAKE_ABOUT_ENERGY = "key_state_about_energy";
    public static final String KEY_STAKE_AMOUNT = "key_statke_amount";
    private static final String KEY_STAKE_CAN_USE_TRX = "key_stake_can_use_trx";
    public static final String kEY_ACCOUNT = "key_account";
    private String aountEnergy;
    private AcSelectStakeAddressBinding binding;
    Button btnNext;
    private double canUseTrxCount;
    CheckBox chkStakeAmount;
    private String controllerAccountName;
    private String controllerAddress;
    private NameAddressExtraBean currentClipBean;
    private boolean currentInputValid;
    View dividerLine;
    ErrorView errorView;
    TrimEditText etInputAddress;
    ImageView ivDelete;
    ImageView ivScan;
    LinearLayout liAddressBook;
    RelativeLayout rlListContainer;
    private ActivityResultLauncher<ScanOptions> scanResultLauncher;
    private SimpleListFragment searchResultFragment;
    FrameLayout searchView;
    private Protocol.Account selectAccount;
    private Wallet selectWallet;
    private DataStatHelper.StatAction statAction;
    private long stateAmount;
    XTabLayoutV2 tabLayout;
    TextView tvDefaultAddress;
    TextView tvInputMask;
    private BaseTextWatcher tvMaskWatcher;
    TextView tvPaste;
    TextView tvTitleTips;
    TextView tvUnderControl;
    ViewPager2 viewPager;
    private boolean fromMultiSign = false;
    private boolean isFreezeBandWidth = false;
    private final SimpleListFragment[] fragments = new SimpleListFragment[2];

    public Protocol.AccountOrBuilder getAccount() {
        return this.selectAccount;
    }

    @Override
    public String getAountEnergy() {
        return this.aountEnergy;
    }

    @Override
    public double getCanUseTrxCount() {
        return this.canUseTrxCount;
    }

    @Override
    public Button getNextButton() {
        return this.btnNext;
    }

    @Override
    public ActivityResultLauncher<ScanOptions> getScannerResultLauncher() {
        return this.scanResultLauncher;
    }

    @Override
    public String getSelectedAddress() {
        return this.controllerAddress;
    }

    @Override
    public DataStatHelper.StatAction getStatAction() {
        return this.statAction;
    }

    @Override
    public boolean isFreezeBandwidth() {
        return this.isFreezeBandWidth;
    }

    @Override
    public boolean isMultiSign() {
        return this.fromMultiSign;
    }

    public static void start(Context context, String str, String str2, long j, double d, boolean z, String str3, boolean z2, DataStatHelper.StatAction statAction) {
        Intent intent = new Intent(context, SelectStakeReceiveAccountActvitiy.class);
        intent.putExtra(KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_statke_amount", j);
        intent.putExtra(KEY_STAKE_CAN_USE_TRX, d);
        intent.putExtra(KEY_STAKE_ABOUT_ENERGY, str3);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_controller_name", str2);
        intent.putExtra(KEY_IS_BANDWIDTH_OR_ENERGY, z2);
        intent.putExtra(TronConfig.StatAction_Key, statAction);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setBackground(R.color.white, 0);
        AcSelectStakeAddressBinding inflate = AcSelectStakeAddressBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setView(inflate.getRoot(), 1);
        setHeaderBar(getString(R.string.stake_title));
        getHeaderHolder().tvCommonTitle2.setText(R.string.step_2_2);
        getHeaderHolder().tvCommonTitle2.setTextColor(getResources().getColor(R.color.black_6d));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    public void mappingId() {
        this.tvUnderControl = this.binding.tvUnderControl;
        this.tvTitleTips = this.binding.tvTitle;
        this.liAddressBook = this.binding.liAddAddress;
        this.etInputAddress = this.binding.etInputAddress;
        this.ivScan = this.binding.ivScan;
        this.ivDelete = this.binding.ivDelete;
        this.tvInputMask = this.binding.tvInputMask;
        this.tvDefaultAddress = this.binding.tvDefaultAddress;
        this.viewPager = this.binding.viewpager;
        this.tabLayout = this.binding.tablayout;
        this.dividerLine = this.binding.divider;
        this.tvPaste = this.binding.tvPaste;
        this.errorView = this.binding.errorView;
        this.btnNext = this.binding.btnNext;
        this.chkStakeAmount = this.binding.chkStakeAmount;
        this.searchView = this.binding.searchResultView;
        this.rlListContainer = this.binding.rlList;
    }

    private void setDappChainTips() {
        if (!SpAPI.THIS.getCurIsMainChain() && this.fromMultiSign) {
            this.tvDefaultAddress.setText(R.string.stake_default_address_tips_multisign);
        }
    }

    @Override
    protected void processData() {
        if (getIntent() != null) {
            this.selectAccount = (Protocol.Account) getIntent().getSerializableExtra("key_account");
            this.fromMultiSign = getIntent().getBooleanExtra(KEY_FROM_MULTI_SIGN, false);
            this.statAction = (DataStatHelper.StatAction) getIntent().getSerializableExtra(TronConfig.StatAction_Key);
            this.stateAmount = getIntent().getLongExtra("key_statke_amount", 0L);
            this.canUseTrxCount = getIntent().getDoubleExtra(KEY_STAKE_CAN_USE_TRX, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            this.aountEnergy = getIntent().getStringExtra(KEY_STAKE_ABOUT_ENERGY);
            this.isFreezeBandWidth = getIntent().getBooleanExtra(KEY_IS_BANDWIDTH_OR_ENERGY, false);
            if (this.fromMultiSign) {
                this.controllerAddress = getIntent().getStringExtra("key_controller_address");
                this.controllerAccountName = getIntent().getStringExtra("key_controller_name");
            }
        }
        this.selectWallet = WalletUtils.getSelectedWallet();
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).init();
        if (this.fromMultiSign) {
            handleMultiSignIntent();
            this.tvUnderControl.setVisibility(View.VISIBLE);
        } else {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.PV_BASE_2_2_TAG);
            this.controllerAddress = this.selectWallet.getAddress();
            this.tvDefaultAddress.setVisibility(View.VISIBLE);
            this.tvUnderControl.setVisibility(View.GONE);
        }
        setDappChainTips();
        setupViewPager();
        setupSearchResultView();
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).loadAddress(this.controllerAddress);
        this.scanResultLauncher = registerForActivityResult(new ScanContract(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                handleScannerResult((ScanIntentResult) obj);
            }
        });
        this.etInputAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean lambda$processData$0;
                lambda$processData$0 = lambda$processData$0(textView, i, keyEvent);
                return lambda$processData$0;
            }
        });
        this.etInputAddress.setPasteCallback(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$processData$1((Integer) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.ivDelete.setVisibility(View.VISIBLE);
        this.chkStakeAmount.setText(String.format(getString(R.string.confirm_stake_amount), StringTronUtil.formatNumber0(this.stateAmount)));
        this.chkStakeAmount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private String previousAddress;

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (fromMultiSign) {
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_3_3_CHECKBOX);
                } else {
                    AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_CHECKBOX);
                }
                SelectStakeReceiveAccountActvitiy selectStakeReceiveAccountActvitiy = SelectStakeReceiveAccountActvitiy.this;
                selectStakeReceiveAccountActvitiy.notifyButtonStatus(selectStakeReceiveAccountActvitiy.currentInputValid);
            }
        });
        if (!this.fromMultiSign) {
            this.etInputAddress.setText(this.selectWallet.getAddress());
            NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
            this.currentClipBean = nameAddressExtraBean;
            nameAddressExtraBean.setAddress(this.selectWallet.getAddress());
            this.currentClipBean.setName(this.selectWallet.getWalletName());
            this.currentClipBean.setStorage(NameAddressExtraBean.Storage.MY_ACCOUNT);
            ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, this.currentClipBean);
        } else if (!SpAPI.THIS.getCurIsMainChain()) {
            this.etInputAddress.setText(this.controllerAddress);
            NameAddressExtraBean nameAddressExtraBean2 = NameAddressExtraBean.getDefault();
            this.currentClipBean = nameAddressExtraBean2;
            nameAddressExtraBean2.setAddress(this.controllerAddress);
            this.currentClipBean.setName(this.controllerAccountName);
            ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, this.currentClipBean);
            this.tvDefaultAddress.setVisibility(View.VISIBLE);
        } else {
            this.etInputAddress.setText(this.selectWallet.getAddress());
            NameAddressExtraBean nameAddressExtraBean3 = NameAddressExtraBean.getDefault();
            this.currentClipBean = nameAddressExtraBean3;
            nameAddressExtraBean3.setStorage(NameAddressExtraBean.Storage.MY_ACCOUNT);
            this.currentClipBean.setAddress(this.selectWallet.getAddress());
            this.currentClipBean.setName(this.selectWallet.getWalletName());
            ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.selectWallet.getAddress(), this.currentClipBean);
            if (this.controllerAddress.equals(WalletUtils.getSelectedWallet().getAddress())) {
                this.tvDefaultAddress.setVisibility(View.VISIBLE);
            }
        }
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).subscribeSearchEvent(this.etInputAddress);
        NameAddressExtraBean nameAddressExtraBean4 = this.currentClipBean;
        nameAddressExtraBean4.setIndentAddress(StringTronUtil.indentAddress(nameAddressExtraBean4.getAddress().toString(), 6));
        updateMaskTextView(false, String.format("%s(%s)", this.currentClipBean.getName(), this.currentClipBean.getIndentAddress()));
        setVisibility(0, this.tvInputMask);
        setVisibility(8, this.etInputAddress);
        KeyboardUtil.assistActivity(this, new KeyboardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int i) {
            }

            @Override
            public void keyBoardHide(int i) {
                if (etInputAddress.getVisibility() == 8 || etInputAddress == null || TextUtils.isEmpty(etInputAddress.getText())) {
                    return;
                }
                SelectStakeReceiveAccountActvitiy selectStakeReceiveAccountActvitiy = SelectStakeReceiveAccountActvitiy.this;
                selectStakeReceiveAccountActvitiy.onPasteEvent(selectStakeReceiveAccountActvitiy.etInputAddress.getText());
            }
        });
        if (this.fromMultiSign) {
            AnalyticsHelper.AssetPage.logEvent("MultisigStaking3_3");
        }
    }

    public boolean lambda$processData$0(TextView textView, int i, KeyEvent keyEvent) {
        if ((i == 5 || i == 6) && !TextUtils.isEmpty(this.etInputAddress.getText())) {
            onPasteEvent(this.etInputAddress.getText());
            return true;
        }
        return false;
    }

    public void lambda$processData$1(Integer num) {
        if (TextUtils.isEmpty(this.etInputAddress.getText())) {
            return;
        }
        onPasteEvent(this.etInputAddress.getText());
    }

    private void setupSearchResultView() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        SimpleListFragment simpleListFragment = SimpleListFragment.getInstance(false);
        this.searchResultFragment = simpleListFragment;
        beginTransaction.replace(R.id.search_result_view, simpleListFragment);
        beginTransaction.setMaxLifecycle(this.searchResultFragment, Lifecycle.State.STARTED);
        beginTransaction.commitAllowingStateLoss();
    }

    private void setupViewPager() {
        int[] iArr = {R.string.address_book, R.string.my_account};
        int[] iArr2 = {R.string.empty_address_book, R.string.no_other_account};
        int i = 0;
        while (true) {
            SimpleListFragment[] simpleListFragmentArr = this.fragments;
            if (i < simpleListFragmentArr.length) {
                simpleListFragmentArr[i] = SimpleListFragment.getInstance();
                this.fragments[i].setEmptyTextRes(iArr2[i]);
                i++;
            } else {
                this.viewPager.setAdapter(new SimpleViewPagerAdapter(this, this.fragments, iArr));
                this.tabLayout.setupWithViewPager(this.viewPager);
                this.tabLayout.setOnTabSelectedListener(new XTabLayoutV2.ViewPagerOnTabSelectedListener(this.viewPager) {
                    @Override
                    public void onTabSelected(XTabLayoutV2.Tab tab) {
                        super.onTabSelected(tab);
                        int position = tab.getPosition();
                        if (position == 0) {
                            AnalyticsHelper.logEventFormat("Stake_TRX（2_2）7", new Object[0]);
                        } else if (position == 1) {
                            AnalyticsHelper.logEventFormat(AnalyticsHelper.Staking.CLICK_MY_TAB_ACCOUNT, new Object[0]);
                        }
                    }
                });
                setVisibility(0, this.dividerLine);
                try {
                    this.viewPager.getChildAt(0).setOverScrollMode(2);
                    return;
                } catch (Exception e) {
                    LogUtils.e(e);
                    return;
                }
            }
        }
    }

    @Override
    public void updateAddressList(SparseArray<List<NameAddressExtraBean>> sparseArray) {
        for (int i = 0; i < sparseArray.size(); i++) {
            this.fragments[i].updateData(new ArrayList(sparseArray.get(i)), new SelectStakeReceiveAccountActvitiyExternalSyntheticLambda3(this));
        }
    }

    public void setSelectBeanToUI(NameAddressExtraBean nameAddressExtraBean) {
        this.currentClipBean = nameAddressExtraBean;
        if (TextUtils.isEmpty(nameAddressExtraBean.getAddress())) {
            return;
        }
        AnalyticsHelper.logEventFormat(AnalyticsHelper.Staking.CLICK_CHOOSE_BY_SEARCH, new Object[0]);
        setVisibility(0, this.tvInputMask, this.ivDelete);
        setVisibility(8, this.etInputAddress, this.tvPaste);
        if (TextUtils.isEmpty(nameAddressExtraBean.getName())) {
            updateMaskTextView(true, nameAddressExtraBean.getAddress());
        } else {
            updateMaskTextView(true, String.format("%s\n(%s)", nameAddressExtraBean.getName(), nameAddressExtraBean.getAddress()));
        }
        hideSoftKeyboard();
    }

    public void handleScannerResult(ScanIntentResult scanIntentResult) {
        TrimEditText trimEditText;
        String contents = scanIntentResult.getContents();
        if (contents == null || (trimEditText = this.etInputAddress) == null) {
            return;
        }
        trimEditText.setText(contents);
        this.etInputAddress.setSelection(contents.length());
        onPasteEvent(contents);
    }

    private void handleMultiSignIntent() {
        try {
            setHeaderBar(getString(R.string.multi_sign_stake_page_title));
            getHeaderHolder().tvCommonTitle2.setText(getString(R.string.step_3_3));
            getHeaderHolder().tvCommonTitle2.setTextColor(getResources().getColor(R.color.black_6d));
            final String str = this.controllerAddress;
            if (!TextUtils.isEmpty(this.controllerAccountName)) {
                str = String.format("%s (%s)", this.controllerAccountName, str);
            }
            this.tvUnderControl.setText(getString(R.string.current_under_control, new Object[]{str}));
            this.tvUnderControl.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$handleMultiSignIntent$2(str);
                }
            });
            this.tvUnderControl.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$handleMultiSignIntent$2(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvUnderControl, str);
        this.tvUnderControl.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    public void setClick() {
        NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_next:
                        if (fromMultiSign) {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_3_3_CONFIRM);
                        } else {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_CONFIRM);
                        }
                        ((SelectStakeReceiveAccountPresenter) mPresenter).stake();
                        return;
                    case R.id.iv_address_book:
                        if (fromMultiSign) {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_3_3_ADDRESSBOOK);
                        } else {
                            AnalyticsHelper.AssetPage.logEvent("Stake_TRX（2_2）7");
                        }
                        SelectStakeReceiveAccountActvitiy selectStakeReceiveAccountActvitiy = SelectStakeReceiveAccountActvitiy.this;
                        SelectAddressPopView.showBottom(selectStakeReceiveAccountActvitiy, selectStakeReceiveAccountActvitiy.getSelectedAddressCallback(), getStakeAddress());
                        return;
                    case R.id.iv_delete:
                        if (fromMultiSign) {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_3_3_INPUT_DELETE_ICON);
                        } else {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_INPUT_DELETE_ICON);
                        }
                        resetViews();
                        ivDelete.setVisibility(View.GONE);
                        tvInputMask.setVisibility(View.GONE);
                        etInputAddress.setVisibility(View.VISIBLE);
                        currentClipBean = null;
                        if (tvDefaultAddress.getVisibility() == 0) {
                            tvDefaultAddress.setVisibility(View.GONE);
                            return;
                        }
                        return;
                    case R.id.iv_scan:
                        if (fromMultiSign) {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_3_3_INPUT_SCAN_ICON);
                        } else {
                            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_INPUT_SCAN_ICON);
                        }
                        if (mPresenter != 0) {
                            ((SelectStakeReceiveAccountPresenter) mPresenter).scanQr(SelectStakeReceiveAccountActvitiy.this);
                            return;
                        }
                        return;
                    case R.id.li_add_address:
                    case R.id.tv_add_address:
                        SelectStakeReceiveAccountActvitiy selectStakeReceiveAccountActvitiy2 = SelectStakeReceiveAccountActvitiy.this;
                        AddToAddressBookPopView.showUp(selectStakeReceiveAccountActvitiy2, selectStakeReceiveAccountActvitiy2.currentClipBean.getAddress().toString(), getAddressBookCallback());
                        return;
                    case R.id.tv_paste:
                        AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_PASTE);
                        String parseClipContent = ((SelectStakeReceiveAccountPresenter) mPresenter).parseClipContent();
                        etInputAddress.setText(parseClipContent);
                        onPasteEvent(parseClipContent);
                        return;
                    default:
                        return;
                }
            }
        };
        this.binding.ivScan.setOnClickListener(noDoubleClickListener);
        this.binding.liAddAddress.setOnClickListener(noDoubleClickListener);
        this.binding.ivDelete.setOnClickListener(noDoubleClickListener);
        this.binding.btnNext.setOnClickListener(noDoubleClickListener);
        this.binding.tvPaste.setOnClickListener(noDoubleClickListener);
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
        if (this.fromMultiSign) {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_3_3_BACK);
        } else {
            AnalyticsHelper.AssetPage.logEvent(AnalyticsHelper.Staking.CLICK_2_2_BACK);
        }
        finish();
    }

    public void onPasteEvent(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
        this.currentClipBean = nameAddressExtraBean;
        nameAddressExtraBean.setAddress(charSequence);
        this.etInputAddress.clearFocus();
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, this.currentClipBean);
        hideSoftKeyboard();
    }

    public void resetViews() {
        this.etInputAddress.setText("");
        this.etInputAddress.clearFocus();
        setVisibility(8, this.ivDelete, this.liAddressBook, this.errorView);
        setVisibility(0, this.etInputAddress, this.tvPaste);
        this.currentInputValid = false;
        notifyButtonStatus(false);
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        try {
            KeyboardUtils.hideSoftInput(getWindow().getDecorView());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    SelectAddressPopView.SelectAddressCallback getSelectedAddressCallback() {
        return new SelectAddressPopView.SelectAddressCallback() {
            @Override
            public final void onAddressSelected(String str, String str2, String str3) {
                lambda$getSelectedAddressCallback$3(str, str2, str3);
            }
        };
    }

    public void lambda$getSelectedAddressCallback$3(String str, String str2, String str3) {
        this.liAddressBook.setVisibility(View.GONE);
        this.etInputAddress.setText(str);
        if (str != null && str.equals(WalletUtils.getSelectedWallet().getAddress())) {
            this.tvDefaultAddress.setVisibility(View.VISIBLE);
        } else {
            this.tvDefaultAddress.setVisibility(View.GONE);
        }
        NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
        this.currentClipBean = nameAddressExtraBean;
        nameAddressExtraBean.setAddress(str);
        this.currentClipBean.setName(str2);
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, this.currentClipBean);
    }

    AddToAddressBookPopView.AddressBookChangeCallback getAddressBookCallback() {
        return new AddToAddressBookPopView.AddressBookChangeCallback() {
            @Override
            public final void onAddressBookChanged(String str, String str2, String str3) {
                lambda$getAddressBookCallback$4(str, str2, str3);
            }
        };
    }

    public void lambda$getAddressBookCallback$4(String str, String str2, String str3) {
        this.liAddressBook.setVisibility(View.GONE);
        this.currentClipBean = ((SelectStakeReceiveAccountPresenter) this.mPresenter).insertNewAddedAddress(str, str2, str3);
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, this.currentClipBean);
    }

    public void setVisibility(final int i, View... viewArr) {
        DesugarArrays.stream(viewArr).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                ((View) obj).setVisibility(i);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
    }

    @Override
    public void updateInputCheckResult(SelectStakeReceiveAccountContract.InputAddressBean inputAddressBean) {
        NameAddressExtraBean addressBean = inputAddressBean.getAddressBean();
        if ((addressBean != null && !addressBean.getAddress().toString().equals(this.currentClipBean.getAddress().toString())) || this.currentClipBean == null || addressBean == null) {
            this.currentInputValid = false;
            notifyButtonStatus(false);
            return;
        }
        if (TextUtils.isEmpty(addressBean.getName())) {
            ((SelectStakeReceiveAccountPresenter) this.mPresenter).setTextWithoutWatcher(this.etInputAddress, addressBean.getAddress());
            setVisibility(0, this.etInputAddress);
            setVisibility(8, this.tvInputMask);
        } else {
            setVisibility(0, this.tvInputMask);
            setVisibility(8, this.etInputAddress);
            updateMaskTextView(false, String.format("%s\n(%s)", addressBean.getName(), addressBean.getAddress()));
        }
        if (inputAddressBean.getAddressBean().getAddress().equals(WalletUtils.getSelectedWallet().getAddress())) {
            this.tvDefaultAddress.setVisibility(View.VISIBLE);
        }
        this.errorView.setText((CharSequence) inputAddressBean.getMessage(), true);
        int i = fun6.$SwitchMap$com$tron$wallet$business$tronpower$stake$SelectStakeReceiveAccountContract$InputError[inputAddressBean.getError().ordinal()];
        if (i == 2 || i == 3) {
            setVisibility(8, this.liAddressBook);
            setVisibility(0, this.errorView);
            this.errorView.updateWarning(inputAddressBean.getError() == SelectStakeReceiveAccountContract.InputError.INFO ? ErrorView.Level.INFO : ErrorView.Level.WARNING);
            if (inputAddressBean.getError().equals(SelectSendAddressContract.InputError.INFO)) {
                showAddToAddressBook(addressBean);
            }
            this.currentInputValid = true;
        } else if (i == 4) {
            this.currentInputValid = false;
            setVisibility(8, this.errorView);
        } else if (i != 5) {
            setVisibility(8, this.errorView);
            showAddToAddressBook(addressBean);
            this.currentInputValid = true;
        } else {
            setVisibility(8, this.liAddressBook);
            setVisibility(0, this.errorView);
            this.currentInputValid = false;
            this.errorView.updateWarning(ErrorView.Level.ERROR);
        }
        notifyButtonStatus(this.currentInputValid);
        if (this.errorView.getVisibility() == 0 && this.tvDefaultAddress.getVisibility() == 0) {
            this.tvDefaultAddress.setVisibility(View.GONE);
        }
    }

    public static class fun6 {
        static final int[] $SwitchMap$com$tron$wallet$business$tronpower$stake$SelectStakeReceiveAccountContract$InputError;

        static {
            int[] iArr = new int[SelectStakeReceiveAccountContract.InputError.values().length];
            $SwitchMap$com$tron$wallet$business$tronpower$stake$SelectStakeReceiveAccountContract$InputError = iArr;
            try {
                iArr[SelectStakeReceiveAccountContract.InputError.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$tronpower$stake$SelectStakeReceiveAccountContract$InputError[SelectStakeReceiveAccountContract.InputError.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$tronpower$stake$SelectStakeReceiveAccountContract$InputError[SelectStakeReceiveAccountContract.InputError.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$tronpower$stake$SelectStakeReceiveAccountContract$InputError[SelectStakeReceiveAccountContract.InputError.UNCHECKED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$tronpower$stake$SelectStakeReceiveAccountContract$InputError[SelectStakeReceiveAccountContract.InputError.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override
    public void setErrorCountStatus(boolean z, int i) {
        setErrorCountStatus(z, getResources().getString(i));
    }

    @Override
    public void setErrorCountStatus(boolean z, String str) {
        this.errorView.setText(str);
        setErrorCountStatus(z);
    }

    private void updateMaskTextView(boolean z, CharSequence charSequence) {
        String ellipseNameOnly = EllipsizeTextViewUtils.ellipseNameOnly(this.tvInputMask, charSequence.toString(), StringUtils.LF);
        if (TextUtils.isEmpty(ellipseNameOnly)) {
            ellipseNameOnly = charSequence.toString();
        }
        if (z) {
            this.tvInputMask.setText(ellipseNameOnly);
        } else {
            this.tvInputMask.removeTextChangedListener(getTextWatcherSingleton());
            this.tvInputMask.setText(ellipseNameOnly);
            this.tvInputMask.addTextChangedListener(getTextWatcherSingleton());
        }
        if (TextUtils.isEmpty(charSequence)) {
            setVisibility(0, this.rlListContainer);
        } else {
            setVisibility(8, this.rlListContainer, this.searchView);
        }
    }

    private void showAddToAddressBook(NameAddressExtraBean nameAddressExtraBean) {
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).showAddToAddressBook(nameAddressExtraBean).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                ((SelectStakeReceiveAccountPresenter) mPresenter).addDisposable(disposable);
            }

            @Override
            public void onSuccess(Boolean bool) {
                if (errorView.getVisibility() != 0) {
                    setVisibility(bool.booleanValue() ? 0 : 8, liAddressBook);
                }
            }

            @Override
            public void onError(Throwable th) {
                LogUtils.e(th);
                SelectStakeReceiveAccountActvitiy selectStakeReceiveAccountActvitiy = SelectStakeReceiveAccountActvitiy.this;
                selectStakeReceiveAccountActvitiy.setVisibility(8, selectStakeReceiveAccountActvitiy.liAddressBook);
            }
        });
    }

    @Override
    public void setErrorCountStatus(boolean z) {
        if (z) {
            this.errorView.setVisibility(View.VISIBLE);
        } else if (this.errorView.getVisibility() == 0) {
            this.errorView.setVisibility(View.GONE);
        }
    }

    private TextWatcher getTextWatcherSingleton() {
        if (this.tvMaskWatcher == null) {
            this.tvMaskWatcher = new BaseTextWatcher(new BaseTextWatcher.AfterTextChangedCallback() {
                @Override
                public final void afterTextChanged(Editable editable, TextWatcher textWatcher) {
                    lambda$getTextWatcherSingleton$6(editable, textWatcher);
                }
            });
        }
        return this.tvMaskWatcher;
    }

    public void lambda$getTextWatcherSingleton$6(Editable editable, TextWatcher textWatcher) {
        if (this.currentClipBean == null || TextUtils.isEmpty(editable.toString())) {
            this.currentClipBean = NameAddressExtraBean.getDefault();
        }
        if (TextUtils.isEmpty(this.currentClipBean.getAddress())) {
            updateInputCheckResult(new SelectStakeReceiveAccountContract.InputAddressBean());
        } else {
            ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, this.currentClipBean);
        }
    }

    @Override
    public void checkInputAddress(String str) {
        NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
        this.currentClipBean = nameAddressExtraBean;
        nameAddressExtraBean.setAddress(str);
        ((SelectStakeReceiveAccountPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, this.currentClipBean);
    }

    @Override
    public void updateSearchResult(List<NameAddressExtraBean> list, String str) {
        SimpleListFragment simpleListFragment = this.searchResultFragment;
        if (simpleListFragment == null) {
            return;
        }
        simpleListFragment.updateData(list, new SelectStakeReceiveAccountActvitiyExternalSyntheticLambda3(this));
        if (list.isEmpty() && ((SelectStakeReceiveAccountPresenter) this.mPresenter).considerNoInput(str)) {
            setVisibility(0, this.rlListContainer, this.tvPaste);
            setVisibility(8, this.searchView, this.errorView, this.ivDelete);
            return;
        }
        setVisibility(0, this.searchView, this.ivDelete);
        setVisibility(8, this.rlListContainer, this.errorView, this.tvPaste);
    }

    @Override
    public boolean isCheckConfirmAmount() {
        return this.chkStakeAmount.isChecked();
    }

    @Override
    public String getStakeAddress() {
        NameAddressExtraBean nameAddressExtraBean = this.currentClipBean;
        if (nameAddressExtraBean != null) {
            return nameAddressExtraBean.getAddress().toString();
        }
        return this.etInputAddress.getText().toString();
    }

    @Override
    public BigDecimal getStakeAmount() {
        return new BigDecimal(this.stateAmount).multiply(new BigDecimal((int) DurationKt.NANOS_IN_MILLIS));
    }

    public void notifyButtonStatus(boolean z) {
        this.btnNext.setEnabled(z && this.chkStakeAmount.isChecked());
    }
}
