package com.tron.wallet.business.transfer.selectaddress;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.lxj.xpopup.util.KeyboardUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.business.mutil.MultiSelectAddressActivity;
import com.tron.wallet.business.mutil.bean.MultiSignPermissionData;
import com.tron.wallet.business.stakev2.stake.resource.DelegateBandWidthActivity;
import com.tron.wallet.business.stakev2.stake.resource.DelegateEnergyActivity;
import com.tron.wallet.business.transfer.component.SimilarAddressView;
import com.tron.wallet.business.transfer.selectaddress.ResourceStringProvider;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressActivity;
import com.tron.wallet.business.transfer.selectaddress.SelectSendAddressContract;
import com.tron.wallet.business.transfer.selecttoken.TransferSelectTokenActivity;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
import com.tron.wallet.common.adapter.base.SimpleViewPagerAdapter;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.AddToAddressBookPopView;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.MultiLineTextPopupView;
import com.tron.wallet.common.components.SimpleListFragment;
import com.tron.wallet.common.components.TransferRiskWarningDialog;
import com.tron.wallet.common.components.TrimEditText;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.EllipsizeTextViewUtils;
import com.tron.wallet.common.utils.KeyboardUtil;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.SpannableUtils;
import com.tron.wallet.common.utils.TextDotUtils;
import com.tron.wallet.common.utils.TouchDelegateUtils;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.ActivitySendBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import j$.util.Collection;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class SelectSendAddressActivity extends BaseActivity<SelectSendAddressPresenter, SelectAddressModel> implements SelectSendAddressContract.View {
    public static final String KEY_CONTROLLER_ADDRESS = "key_controller_address";
    private static final String KEY_CONTROLLER_NAME = "key_controller_name";
    public static final String KEY_FREEZE_TRX = "key_freezetrx";
    private static final String KEY_FROM_MULTI_SIGN = "key_from_multi_sign";
    public static final String KEY_MULTI_SIGN_PERMISSIONS = "key_multi_sign_permissions";
    public static final String KEY_NFT_ITEM = "key_nft_item";
    private static final String KEY_PAGE_TYPE = "key_page_type";
    public static final String KEY_RECEIVER_ADDRESS = "key_receiver_address";
    public static final String kEY_ACCOUNT = "key_account";
    public static final String kEY_CAN_DELEGATED = "key_can_delegated";
    View addressBgView;
    ActivitySendBinding binding;
    Button btnNext;
    private long canDelegated;
    private String controllerAccountName;
    private String controllerAddress;
    private NameAddressExtraBean currentClipBean;
    private SelectSendAddressContract.InputAddressBean currentInputAddressBean;
    View dividerLine;
    ErrorView errorView;
    TrimEditText etInput;
    View flagDid;
    private long freezeTrx;
    View inputBgView;
    ImageView ivBack;
    ImageView ivDelete;
    private MultiSignPermissionData multiSignPermissionData;
    private NftItemInfo nftItemInfo;
    private PageType pageType;
    private String receiverAddress;
    RelativeLayout rlListContainer;
    View root;
    private ActivityResultLauncher<ScanOptions> scanResultLauncher;
    private SimpleListFragment searchResultFragment;
    FrameLayout searchView;
    private Protocol.Account selectAccount;
    Wallet selectWallet;
    View similarAddressView;
    XTabLayoutV2 tabLayout;
    private TokenBean tokenBean;
    TextView tvAddressBook;
    TextView tvInputMask;
    private BaseTextWatcher tvMaskWatcher;
    TextView tvMultiSign;
    TextView tvMultiSignWarning;
    TextView tvPaste;
    TextView tvStep;
    TextView tvTitle;
    ViewPager2 viewPager;
    private final SimpleListFragment[] fragments = new SimpleListFragment[3];
    private boolean hasOwnerPermission = true;
    private boolean fromMultiSign = false;
    private int resource = TronConfig.RESOURCE_ENERGY;
    int[] pageTitles = {R.string.recently_send, R.string.address_book, R.string.my_account};
    int[] emptyText = {R.string.no_send_record, R.string.empty_address_book, R.string.no_other_account};

    @Override
    public PageType getPageType() {
        return this.pageType;
    }

    @Override
    public ActivityResultLauncher<ScanOptions> getScannerResultLauncher() {
        return this.scanResultLauncher;
    }

    public static void startFromMultiSign(Context context, Protocol.Account account, TokenBean tokenBean, String str, String str2, MultiSignPermissionData multiSignPermissionData) {
        RxBus.getInstance().post("SelectSendAddressActivity", "");
        start(context, account, tokenBean, null, "", str2, str, multiSignPermissionData, true, PageType.TRANSFER);
    }

    public static void startForDelegate(Context context, Protocol.Account account, String str, String str2, PageType pageType, long j, long j2, boolean z) {
        Intent intent = new Intent(context, SelectSendAddressActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra(KEY_PAGE_TYPE, pageType);
        intent.putExtra("key_can_delegated", j);
        intent.putExtra("key_freezetrx", j2);
        intent.putExtra(KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_address", str);
        intent.putExtra("key_controller_name", str2);
        context.startActivity(intent);
    }

    public static void startCommon(Context context, TokenBean tokenBean, Protocol.Account account, String str) {
        start(context, account, tokenBean, null, str, null, null, null, false, PageType.TRANSFER);
    }

    public static void startForNft(Context context, TokenBean tokenBean, Protocol.Account account, NftItemInfo nftItemInfo) {
        start(context, account, tokenBean, nftItemInfo, null, null, null, null, false, PageType.TRANSFER);
    }

    private static void start(Context context, Protocol.Account account, TokenBean tokenBean, NftItemInfo nftItemInfo, String str, String str2, String str3, MultiSignPermissionData multiSignPermissionData, boolean z, PageType pageType) {
        Intent intent = new Intent(context, SelectSendAddressActivity.class);
        intent.putExtra("key_account", account);
        intent.putExtra("key_multi_sign_permissions", multiSignPermissionData);
        intent.putExtra("key_controller_address", str2);
        intent.putExtra(TronConfig.TRC, tokenBean);
        intent.putExtra(KEY_RECEIVER_ADDRESS, str);
        intent.putExtra(KEY_NFT_ITEM, nftItemInfo);
        intent.putExtra(KEY_FROM_MULTI_SIGN, z);
        intent.putExtra("key_controller_name", str3);
        intent.putExtra(KEY_PAGE_TYPE, pageType);
        context.startActivity(intent);
    }

    private void handleMultiSignIntent() {
        String string;
        final String str = this.controllerAddress;
        if (!TextUtils.isEmpty(this.controllerAccountName)) {
            str = String.format("%s (%s)", this.controllerAccountName, str);
        }
        try {
            if (this.pageType != PageType.DELEGATE_BANDWIDTH && this.pageType != PageType.DELEGATE_ENERGY) {
                this.tvTitle.setText(R.string.multi_sign_transfer);
                string = getString(R.string.multi_sign_controller_tips, new Object[]{str});
                this.tvStep.setText("(2/3)");
                this.tvMultiSignWarning.setText(string);
                this.tvMultiSignWarning.post(new Runnable() {
                    @Override
                    public final void run() {
                        lambda$handleMultiSignIntent$0(str);
                    }
                });
                this.tvMultiSignWarning.setVisibility(View.VISIBLE);
                this.tvMultiSign.setVisibility(View.GONE);
            }
            this.tvTitle.setText(R.string.multi_delegate);
            string = getString(R.string.multi_sign_controller_tips_delegate, new Object[]{str});
            this.tvMultiSignWarning.setText(string);
            this.tvMultiSignWarning.post(new Runnable() {
                @Override
                public final void run() {
                    lambda$handleMultiSignIntent$0(str);
                }
            });
            this.tvMultiSignWarning.setVisibility(View.VISIBLE);
            this.tvMultiSign.setVisibility(View.GONE);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void lambda$handleMultiSignIntent$0(String str) {
        String[] autoSplitText = TextDotUtils.autoSplitText(this.tvMultiSignWarning, str);
        this.tvMultiSignWarning.setText(SpannableUtils.getTextColorSpannable(autoSplitText[0], autoSplitText[1], getResources().getColor(R.color.black_23)));
    }

    @Override
    protected void setLayout() {
        ActivitySendBinding inflate = ActivitySendBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        setClick();
        setView(root, 0);
    }

    public class fun1 extends NoDoubleClickListener2 {
        fun1() {
        }

        @Override
        protected void onNoDoubleClick(View view) {
            switch (view.getId()) {
                case R.id.btn_next:
                    enterNext();
                    if (pageType == PageType.DELEGATE_ENERGY) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_CLICK_NEXT);
                        return;
                    } else if (pageType == PageType.DELEGATE_BANDWIDTH) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_BANDWIDTH_CLICK_NEXT);
                        return;
                    } else {
                        AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.getPrefix(fromMultiSign) + AnalyticsHelper.SelectSendAddress.CLICK_NEXT);
                        return;
                    }
                case R.id.iv_back:
                    exit();
                    AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.getPrefix(fromMultiSign) + "1");
                    return;
                case R.id.iv_delete:
                    resetViews();
                    currentClipBean = null;
                    currentInputAddressBean = null;
                    return;
                case R.id.iv_scan:
                    if (mPresenter != 0) {
                        ((SelectSendAddressPresenter) mPresenter).scanQr(SelectSendAddressActivity.this);
                    }
                    if (pageType == PageType.DELEGATE_ENERGY) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_CLICK_SCAN);
                        return;
                    } else if (pageType == PageType.DELEGATE_BANDWIDTH) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_BANDWIDTH_CLICK_SCAN);
                        return;
                    } else {
                        AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.getPrefix(fromMultiSign) + "2");
                        return;
                    }
                case R.id.tv_address_book:
                    if (AddressController.getInstance(SelectSendAddressActivity.this).isAddressCountMax()) {
                        runOnUIThread(new OnMainThread() {
                            @Override
                            public final void doInUIThread() {
                                SelectSendAddressActivity.1.this.lambda$onNoDoubleClick$0();
                            }
                        });
                        return;
                    }
                    SelectSendAddressActivity selectSendAddressActivity = SelectSendAddressActivity.this;
                    AddToAddressBookPopView.showUp(selectSendAddressActivity, selectSendAddressActivity.currentClipBean.getAddress().toString(), getAddressBookCallback());
                    return;
                case R.id.tv_multi_sign:
                    checkAndEnterMultiSign();
                    return;
                case R.id.tv_paste:
                    String parseClipContent = ((SelectSendAddressPresenter) mPresenter).parseClipContent();
                    etInput.setText(parseClipContent);
                    onPasteEvent(parseClipContent);
                    if (pageType == PageType.DELEGATE_ENERGY) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_CLICK_PASTE);
                        return;
                    } else if (pageType == PageType.DELEGATE_BANDWIDTH) {
                        AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_BANDWIDTH_CLICK_PASTE);
                        return;
                    } else {
                        AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.getPrefix(fromMultiSign) + "3");
                        return;
                    }
                default:
                    return;
            }
        }

        public void lambda$onNoDoubleClick$0() {
            SelectSendAddressActivity selectSendAddressActivity = SelectSendAddressActivity.this;
            selectSendAddressActivity.toast(selectSendAddressActivity.getString(R.string.address_no_add));
        }
    }

    private void setClick() {
        1 r0 = new fun1();
        this.binding.tvMultiSign.setOnClickListener(r0);
        this.binding.itemInputAddress.ivScan.setOnClickListener(r0);
        this.binding.tvAddressBook.setOnClickListener(r0);
        this.binding.itemInputAddress.tvPaste.setOnClickListener(r0);
        this.binding.itemInputAddress.ivDelete.setOnClickListener(r0);
        this.binding.ivBack.setOnClickListener(r0);
        this.binding.btnNext.setOnClickListener(r0);
    }

    private void mappingId() {
        this.root = this.binding.root;
        this.tvTitle = this.binding.tvMainTitle;
        this.tvStep = this.binding.tvStep;
        this.tabLayout = this.binding.tablayout;
        this.viewPager = this.binding.viewpager;
        this.etInput = this.binding.itemInputAddress.etInputAddress;
        this.tvPaste = this.binding.itemInputAddress.tvPaste;
        this.ivDelete = this.binding.itemInputAddress.ivDelete;
        this.tvAddressBook = this.binding.tvAddressBook;
        this.btnNext = this.binding.btnNext;
        this.errorView = this.binding.itemInputAddress.errorView;
        this.rlListContainer = this.binding.rlList;
        this.searchView = this.binding.searchResultView;
        this.tvInputMask = this.binding.itemInputAddress.tvInputMask;
        this.ivBack = this.binding.ivBack;
        this.tvMultiSignWarning = this.binding.tvMultiWarning;
        this.tvMultiSign = this.binding.tvMultiSign;
        this.dividerLine = this.binding.divider;
        this.similarAddressView = this.binding.layoutSimilarAddress;
        this.flagDid = this.binding.itemInputAddress.flagDid;
        this.inputBgView = this.binding.itemInputAddress.rlInput;
        this.addressBgView = this.binding.itemInputAddress.rlAddress;
    }

    @Override
    protected void processData() {
        this.selectWallet = WalletUtils.getSelectedPublicWallet();
        this.selectAccount = (Protocol.Account) getIntent().getSerializableExtra("key_account");
        this.receiverAddress = getIntent().getStringExtra(KEY_RECEIVER_ADDRESS);
        this.tokenBean = (TokenBean) getIntent().getParcelableExtra(TronConfig.TRC);
        this.controllerAddress = getIntent().getStringExtra("key_controller_address");
        this.fromMultiSign = getIntent().getBooleanExtra(KEY_FROM_MULTI_SIGN, false);
        this.nftItemInfo = (NftItemInfo) getIntent().getParcelableExtra(KEY_NFT_ITEM);
        this.multiSignPermissionData = (MultiSignPermissionData) getIntent().getParcelableExtra("key_multi_sign_permissions");
        this.controllerAccountName = getIntent().getStringExtra("key_controller_name");
        this.pageType = (PageType) getIntent().getSerializableExtra(KEY_PAGE_TYPE);
        this.canDelegated = getIntent().getLongExtra("key_can_delegated", 0L);
        this.freezeTrx = getIntent().getLongExtra("key_freezetrx", 0L);
        if (this.fromMultiSign) {
            ResourceStringProvider.ResourceString pageTitle = ResourceStringProvider.getPageTitle(this, this.pageType);
            if (!TextUtils.isEmpty(pageTitle.title)) {
                this.tvTitle.setText(pageTitle.title);
            }
            if (pageTitle.tabRecent > 0) {
                this.pageTitles[0] = pageTitle.tabRecent;
            }
            if (pageTitle.tabEmptyRecent > 0) {
                this.emptyText[0] = pageTitle.tabEmptyRecent;
            }
            handleMultiSignIntent();
        } else {
            this.controllerAddress = this.selectWallet.getAddress();
            updatePageType();
            AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.ENTER_COMMON);
        }
        ensureAccount(this.selectAccount).subscribe(new IObserver(getEnsureAccountCallback(), "ensure account"));
        this.scanResultLauncher = registerForActivityResult(new ScanContract(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                handleScannerResult((ScanIntentResult) obj);
            }
        });
        if (this.pageType == PageType.DELEGATE_ENERGY) {
            AnalyticsHelper.ResourceDelegatePage.logMultiEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_1_SHOW, this.fromMultiSign);
        } else if (this.pageType == PageType.DELEGATE_BANDWIDTH) {
            AnalyticsHelper.ResourceDelegatePage.logMultiEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_BAND_WIDTH_1_SHOW, this.fromMultiSign);
        }
    }

    private void updatePageType() {
        int i;
        if (this.pageType == PageType.DELEGATE_BANDWIDTH) {
            i = TronConfig.RESOURCE_BANDWIDTH;
        } else {
            i = this.pageType == PageType.DELEGATE_ENERGY ? TronConfig.RESOURCE_ENERGY : 0;
        }
        this.resource = i;
        if (this.pageType == PageType.DELEGATE_BANDWIDTH || this.pageType == PageType.DELEGATE_ENERGY) {
            this.tvMultiSign.setVisibility(View.GONE);
        }
        ResourceStringProvider.ResourceString pageTitle = ResourceStringProvider.getPageTitle(this, this.pageType);
        if (!TextUtils.isEmpty(pageTitle.title)) {
            this.tvTitle.setText(pageTitle.title);
        }
        if (pageTitle.tabRecent > 0) {
            this.pageTitles[0] = pageTitle.tabRecent;
        }
        if (pageTitle.tabEmptyRecent > 0) {
            this.emptyText[0] = pageTitle.tabEmptyRecent;
        }
    }

    private ICallback<Protocol.Account> getEnsureAccountCallback() {
        return new ICallback<Protocol.Account>() {
            @Override
            public void onResponse(String str, Protocol.Account account) {
                selectAccount = account;
                SelectSendAddressActivity selectSendAddressActivity = SelectSendAddressActivity.this;
                selectSendAddressActivity.hasOwnerPermission = ((SelectSendAddressPresenter) selectSendAddressActivity.mPresenter).hasOwnerPermission(selectWallet.getAddress(), selectAccount);
                if (fromMultiSign || hasOwnerPermission) {
                    initData();
                } else {
                    showMultiSignDialog();
                }
            }

            @Override
            public void onFailure(String str, String str2) {
                SelectSendAddressActivity selectSendAddressActivity = SelectSendAddressActivity.this;
                selectSendAddressActivity.toast(selectSendAddressActivity.getString(R.string.network_unusable));
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                ((SelectSendAddressPresenter) mPresenter).mRxManager.add(disposable);
            }
        };
    }

    private Observable<Protocol.Account> ensureAccount(final Protocol.Account account) {
        return ((SelectSendAddressPresenter) this.mPresenter).queryAccount(this.fromMultiSign ? "" : this.selectWallet.getWalletName(), this.controllerAddress).compose(new ObservableTransformer() {
            @Override
            public final ObservableSource apply(Observable observable) {
                ObservableSource lambda$ensureAccount$2;
                lambda$ensureAccount$2 = lambda$ensureAccount$2(account, observable);
                return lambda$ensureAccount$2;
            }
        });
    }

    public ObservableSource lambda$ensureAccount$2(final Protocol.Account account, Observable observable) {
        return ((SelectSendAddressPresenter) this.mPresenter).isNullAccount(account) ? observable : Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                SelectSendAddressActivity.lambda$ensureAccount$1(Protocol.Account.this, observableEmitter);
            }
        });
    }

    public static void lambda$ensureAccount$1(Protocol.Account account, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(account);
        observableEmitter.onComplete();
    }

    public void initData() {
        int dip2px = UIUtils.dip2px(15.0f);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivBack, dip2px, dip2px, dip2px, dip2px);
        TouchDelegateUtils.expandViewTouchDelegate(this.ivDelete, 0, dip2px, dip2px, dip2px);
        initViewPager();
        setupSearchResultView();
        this.tvInputMask.addTextChangedListener(getTextWatcherSingleton());
        ((SelectSendAddressPresenter) this.mPresenter).subscribeSearchEvent(this.etInput);
        ((SelectSendAddressPresenter) this.mPresenter).loadAddress(this.controllerAddress);
        this.etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean lambda$initData$3;
                lambda$initData$3 = lambda$initData$3(textView, i, keyEvent);
                return lambda$initData$3;
            }
        });
        this.etInput.setPasteCallback(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initData$4((Integer) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        KeyboardUtil.assistActivity(this, new KeyboardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int i) {
            }

            @Override
            public void keyBoardHide(int i) {
                if (etInput.getVisibility() == 8 || etInput == null || etInput.getText() == null || TextUtils.isEmpty(etInput.getText())) {
                    return;
                }
                SelectSendAddressActivity selectSendAddressActivity = SelectSendAddressActivity.this;
                selectSendAddressActivity.onPasteEvent(selectSendAddressActivity.etInput.getText(), etInput.getText().length() != 34);
            }
        });
        if (!TextUtils.isEmpty(this.receiverAddress)) {
            this.etInput.setText(this.receiverAddress);
            this.etInput.setSelection(this.receiverAddress.length());
            onPasteEvent(this.receiverAddress);
        }
        ((SelectSendAddressPresenter) this.mPresenter).mRxManager.on(Event.TRANSFER_INNER, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initData$5(obj);
            }
        });
        ((SelectSendAddressPresenter) this.mPresenter).mRxManager.on(Event.BroadcastSuccess, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initData$6(obj);
            }
        });
        ((SelectSendAddressPresenter) this.mPresenter).mRxManager.on(Event.BackToHome, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initData$7(obj);
            }
        });
        ((SelectSendAddressPresenter) this.mPresenter).mRxManager.on("SelectSendAddressActivity", new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$initData$8(obj);
            }
        });
    }

    public boolean lambda$initData$3(TextView textView, int i, KeyEvent keyEvent) {
        if ((i == 5 || i == 6) && !TextUtils.isEmpty(this.etInput.getText())) {
            onPasteEvent(this.etInput.getText());
            return true;
        }
        return false;
    }

    public void lambda$initData$4(Integer num) {
        if (TextUtils.isEmpty(this.etInput.getText())) {
            return;
        }
        onPasteEvent(this.etInput.getText());
    }

    public void lambda$initData$5(Object obj) throws Exception {
        exit();
    }

    public void lambda$initData$6(Object obj) throws Exception {
        exit();
    }

    public void lambda$initData$7(Object obj) throws Exception {
        exit();
    }

    public void lambda$initData$8(Object obj) throws Exception {
        exit();
    }

    public void showMultiSignDialog() {
        String string = getString(R.string.lack_of_owner_permission);
        if (this.pageType == PageType.DELEGATE_BANDWIDTH || this.pageType == PageType.DELEGATE_BANDWIDTH) {
            string = getString(R.string.lack_of_owner_permission_for_delegate);
        }
        ConfirmCustomPopupView.getBuilder(this).setTitle(string).setTitleSize(16).setPopupCallback(new SimpleCallback() {
            @Override
            public void onDismiss(BasePopupView basePopupView) {
                exit();
            }
        }).setConfirmText(getString(R.string.multisig)).setCancleListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$9(view);
            }
        }).setConfirmListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                lambda$showMultiSignDialog$10(view);
            }
        }).build().show();
    }

    public void lambda$showMultiSignDialog$9(View view) {
        exit();
    }

    public void lambda$showMultiSignDialog$10(View view) {
        checkAndEnterMultiSign();
        exit();
    }

    private void setupSearchResultView() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        SimpleListFragment simpleListFragment = SimpleListFragment.getInstance(false);
        this.searchResultFragment = simpleListFragment;
        beginTransaction.replace(R.id.search_result_view, simpleListFragment);
        beginTransaction.setMaxLifecycle(this.searchResultFragment, Lifecycle.State.STARTED);
        beginTransaction.commitAllowingStateLoss();
    }

    private TextWatcher getTextWatcherSingleton() {
        if (this.tvMaskWatcher == null) {
            this.tvMaskWatcher = new BaseTextWatcher(new BaseTextWatcher.AfterTextChangedCallback() {
                @Override
                public final void afterTextChanged(Editable editable, TextWatcher textWatcher) {
                    lambda$getTextWatcherSingleton$11(editable, textWatcher);
                }
            });
        }
        return this.tvMaskWatcher;
    }

    public void lambda$getTextWatcherSingleton$11(Editable editable, TextWatcher textWatcher) {
        if (this.currentClipBean == null || TextUtils.isEmpty(editable.toString())) {
            this.currentClipBean = NameAddressExtraBean.getDefault();
        }
        if (TextUtils.isEmpty(this.currentClipBean.getAddress())) {
            updateInputCheckResult(new SelectSendAddressContract.InputAddressBean());
        } else {
            ((SelectSendAddressPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, getTokenType(), this.currentClipBean);
        }
    }

    private void initViewPager() {
        int i = 0;
        while (true) {
            SimpleListFragment[] simpleListFragmentArr = this.fragments;
            if (i < simpleListFragmentArr.length) {
                simpleListFragmentArr[i] = SimpleListFragment.getInstance(true, true);
                this.fragments[i].setEmptyTextRes(this.emptyText[i]);
                i++;
            } else {
                this.viewPager.setAdapter(new SimpleViewPagerAdapter(this, this.fragments, this.pageTitles));
                this.tabLayout.setupWithViewPager(this.viewPager);
                setVisibility(0, this.dividerLine);
                this.tabLayout.setOnTabSelectedListener(new XTabLayoutV2.ViewPagerOnTabSelectedListener(this.viewPager) {
                    @Override
                    public void onTabSelected(XTabLayoutV2.Tab tab) {
                        super.onTabSelected(tab);
                        int position = tab.getPosition();
                        if (pageType == PageType.DELEGATE_ENERGY) {
                            if (position == 0) {
                                AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_CLICK_RECENT);
                            } else if (position == 1) {
                                AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_CLICK_BOOK);
                            } else if (position == 2) {
                                AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_ENERGY_CLICK_ACCOUNT);
                            }
                        } else if (pageType == PageType.DELEGATE_BANDWIDTH) {
                            if (position == 0) {
                                AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_BANDWIDTH_CLICK_RECENT);
                            } else if (position == 1) {
                                AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_BANDWIDTH_CLICK_BOOK);
                            } else if (position == 2) {
                                AnalyticsHelper.logEvent(AnalyticsHelper.ResourceDelegatePage.DELEGATE_BANDWIDTH_CLICK_ACCOUNT);
                            }
                        } else if (position == 0) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.getPrefix(fromMultiSign) + AnalyticsHelper.SelectSendAddress.CLICK_RECENT);
                        } else if (position == 1) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.getPrefix(fromMultiSign) + AnalyticsHelper.SelectSendAddress.CLICK_ADDRESS_BOOK);
                        } else if (position == 2) {
                            AnalyticsHelper.logEvent(AnalyticsHelper.SelectSendAddress.getPrefix(fromMultiSign) + AnalyticsHelper.SelectSendAddress.CLICK_MY_ACCOUNT);
                        }
                    }
                });
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
    public void updateSearchResult(List<NameAddressExtraBean> list, String str) {
        if (list.isEmpty() && ((SelectSendAddressPresenter) this.mPresenter).considerNoInput(str)) {
            setVisibility(0, this.rlListContainer, this.tvPaste);
            setVisibility(8, this.searchView, this.ivDelete, this.similarAddressView, this.flagDid);
        } else {
            setVisibility(0, this.searchView, this.ivDelete);
            setVisibility(8, this.rlListContainer, this.tvPaste, this.similarAddressView);
        }
        SimpleListFragment simpleListFragment = this.searchResultFragment;
        if (simpleListFragment != null) {
            simpleListFragment.updateData(list, getItemSelectedCallback());
        }
    }

    @Override
    public void updateAddressList(SparseArray<List<NameAddressExtraBean>> sparseArray, boolean z) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                final ArrayList arrayList = new ArrayList();
                if (sparseArray != null && sparseArray.get(i) != null) {
                    Collection.-EL.stream(sparseArray.get(i)).forEach(new Consumer() {
                        @Override
                        public final void accept(Object obj) {
                            lambda$updateAddressList$12(arrayList, (NameAddressExtraBean) obj);
                        }

                        public Consumer andThen(Consumer consumer) {
                            return Objects.requireNonNull(consumer);
                        }
                    });
                }
                this.fragments[i].updateData(arrayList, getItemSelectedCallback());
            }
        }
    }

    public void lambda$updateAddressList$12(ArrayList arrayList, NameAddressExtraBean nameAddressExtraBean) {
        if (TextUtils.equals(this.controllerAddress, nameAddressExtraBean.getAddress())) {
            return;
        }
        arrayList.add(nameAddressExtraBean);
    }

    @Override
    public void setSelectBeanToUI(NameAddressExtraBean nameAddressExtraBean) {
        this.currentClipBean = nameAddressExtraBean;
        if (TextUtils.isEmpty(nameAddressExtraBean.getAddress())) {
            return;
        }
        setVisibility(0, this.tvInputMask, this.ivDelete);
        setVisibility(8, this.etInput, this.tvPaste);
        if (TextUtils.isEmpty(nameAddressExtraBean.getName())) {
            updateMaskTextView(true, nameAddressExtraBean.getAddress());
        } else {
            updateMaskTextView(true, String.format("%s\n(%s)", nameAddressExtraBean.getName(), nameAddressExtraBean.getAddress()));
        }
        hideSoftKeyboard();
    }

    @Override
    public void updateInputCheckResult(SelectSendAddressContract.InputAddressBean inputAddressBean) {
        ErrorView.Level level;
        this.currentInputAddressBean = inputAddressBean;
        if (inputAddressBean == null || inputAddressBean.getAddressBean() == null) {
            return;
        }
        NameAddressExtraBean addressBean = inputAddressBean.getAddressBean();
        if (inputAddressBean.isDid) {
            this.currentClipBean = addressBean;
        } else if ((addressBean != null && !addressBean.equals(this.currentClipBean)) || this.currentClipBean == null || addressBean == null) {
            this.btnNext.setEnabled(false);
            return;
        }
        this.flagDid.setVisibility(inputAddressBean.isDid ? View.VISIBLE : View.GONE);
        if (TextUtils.isEmpty(addressBean.getName())) {
            ((SelectSendAddressPresenter) this.mPresenter).setTextWithoutWatcher(this.etInput, addressBean.getAddress());
            setVisibility(0, this.etInput);
            setVisibility(8, this.tvInputMask);
        } else {
            setVisibility(0, this.tvInputMask);
            setVisibility(8, this.etInput);
            if (inputAddressBean.isDid) {
                updateMaskTextView(false, String.format("%s", addressBean.getName()));
                final BasePopupView build = new MultiLineTextPopupView.Builder().setShowArrow(false).setAnchor(this.etInput).addKeyValue(getString(R.string.did_domain), addressBean.getName().toString()).addKeyValue(getString(R.string.did_account), addressBean.getAddress().toString()).build(this);
                build.show();
                Objects.requireNonNull(build);
                build.postOnAnimationDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        BasePopupView.this.dismiss();
                    }
                }, 3000L);
            } else {
                updateMaskTextView(false, String.format("%s\n(%s)", addressBean.getName(), addressBean.getAddress()));
            }
        }
        this.errorView.setText(inputAddressBean.getMessage(), true);
        int i = fun7.$SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError[inputAddressBean.getError().ordinal()];
        if (i == 2 || i == 3 || i == 4) {
            setVisibility(8, this.tvAddressBook);
            setVisibility(0, this.errorView);
            if (inputAddressBean.getError() == SelectSendAddressContract.InputError.INFO) {
                level = ErrorView.Level.INFO;
            } else if (inputAddressBean.getError() == SelectSendAddressContract.InputError.WARNING) {
                level = ErrorView.Level.WARNING;
            } else {
                level = ErrorView.Level.ERROR;
            }
            this.errorView.updateWarning(level);
            if (inputAddressBean.getError() == SelectSendAddressContract.InputError.INFO || inputAddressBean.getError() == SelectSendAddressContract.InputError.ERROR_NON_BLOCK) {
                showAddToAddressBook(addressBean);
            }
            this.btnNext.setEnabled(true);
        } else if (i == 5) {
            setVisibility(8, this.tvAddressBook);
            setVisibility(0, this.errorView);
            this.errorView.updateWarning(ErrorView.Level.SCAM);
            this.btnNext.setEnabled(true);
        } else if (i != 6) {
            setVisibility(8, this.errorView, this.similarAddressView);
            showAddToAddressBook(addressBean);
            this.btnNext.setEnabled(true);
        } else {
            setVisibility(8, this.tvAddressBook);
            setVisibility(0, this.errorView);
            this.btnNext.setEnabled(false);
            this.errorView.updateWarning(ErrorView.Level.ERROR);
        }
    }

    public static class fun7 {
        static final int[] $SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError;

        static {
            int[] iArr = new int[SelectSendAddressContract.InputError.values().length];
            $SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError = iArr;
            try {
                iArr[SelectSendAddressContract.InputError.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError[SelectSendAddressContract.InputError.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError[SelectSendAddressContract.InputError.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError[SelectSendAddressContract.InputError.ERROR_NON_BLOCK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError[SelectSendAddressContract.InputError.SCAM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$wallet$business$transfer$selectaddress$SelectSendAddressContract$InputError[SelectSendAddressContract.InputError.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void showAddToAddressBook(NameAddressExtraBean nameAddressExtraBean) {
        ((SelectSendAddressPresenter) this.mPresenter).showAddToAddressBook(nameAddressExtraBean).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                ((SelectSendAddressPresenter) mPresenter).addDisposable(disposable);
            }

            @Override
            public void onSuccess(Boolean bool) {
                setVisibility(bool.booleanValue() ? 0 : 8, tvAddressBook);
            }

            @Override
            public void onError(Throwable th) {
                LogUtils.e(th);
                SelectSendAddressActivity selectSendAddressActivity = SelectSendAddressActivity.this;
                selectSendAddressActivity.setVisibility(8, selectSendAddressActivity.tvAddressBook);
            }
        });
    }

    @Override
    public void checkInputAddress(String str) {
        int tokenType = getTokenType();
        NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
        this.currentClipBean = nameAddressExtraBean;
        nameAddressExtraBean.setAddress(str);
        ((SelectSendAddressPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, tokenType, this.currentClipBean);
    }

    @Override
    public void updateWarningSimilarAddress(Pair<CharSequence, CharSequence> pair) {
        if (pair == null || TextUtils.isEmpty((CharSequence) pair.first)) {
            setVisibility(8, this.similarAddressView);
            return;
        }
        setVisibility(8, this.searchView);
        setVisibility(0, this.similarAddressView);
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("similar_address_fragment");
        if (findFragmentByTag instanceof SimilarAddressView) {
            ((SimilarAddressView) findFragmentByTag).updateSimilarAddressInfo(pair, this.pageType);
        }
    }

    private int getTokenType() {
        TokenBean tokenBean = this.tokenBean;
        if (tokenBean != null) {
            return tokenBean.getType();
        }
        return 0;
    }

    public void handleScannerResult(ScanIntentResult scanIntentResult) {
        TrimEditText trimEditText;
        String contents = scanIntentResult.getContents();
        if (contents == null || (trimEditText = this.etInput) == null) {
            return;
        }
        trimEditText.setText(contents);
        this.etInput.setSelection(contents.length());
        onPasteEvent(contents);
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

    private void hideSoftKeyboard() {
        try {
            KeyboardUtils.hideSoftInput(getWindow().getDecorView());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private void enterMultiSign() {
        NameAddressExtraBean nameAddressExtraBean;
        try {
            Intent intent = new Intent(this, MultiSelectAddressActivity.class);
            intent.putExtra(TronConfig.TRC, this.tokenBean);
            String str = this.receiverAddress;
            if (str == null && (nameAddressExtraBean = this.currentClipBean) != null) {
                str = nameAddressExtraBean.getAddress().toString();
            }
            intent.putExtra(KEY_RECEIVER_ADDRESS, str);
            startActivity(intent);
        } catch (Throwable th) {
            LogUtils.e(th);
        }
    }

    private Consumer<NameAddressExtraBean> getItemSelectedCallback() {
        return new Consumer() {
            @Override
            public final void accept(Object obj) {
                setSelectBeanToUI((NameAddressExtraBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        };
    }

    public void enterNext() {
        if (this.pageType == PageType.DELEGATE_BANDWIDTH) {
            DelegateBandWidthActivity.start(getIContext(), this.selectAccount, this.fromMultiSign, this.controllerAddress, this.controllerAccountName, this.canDelegated, this.freezeTrx, this.currentClipBean.getAddress().toString());
        }
        if (this.pageType == PageType.DELEGATE_ENERGY) {
            DelegateEnergyActivity.start(getIContext(), this.selectAccount, this.fromMultiSign, this.controllerAddress, this.controllerAccountName, this.canDelegated, this.freezeTrx, this.currentClipBean.getAddress().toString());
        } else if (this.pageType == PageType.TRANSFER) {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$enterNext$14(view);
                }
            };
            SelectSendAddressContract.InputAddressBean inputAddressBean = this.currentInputAddressBean;
            if (inputAddressBean == null) {
                return;
            }
            if (inputAddressBean.riskType == null) {
                enterTransfer();
                return;
            }
            boolean languageZH = LanguageUtils.languageZH(getIContext());
            if (this.currentInputAddressBean.riskType == SelectSendAddressContract.RiskType.CONTRACT) {
                TransferRiskWarningDialog.show(getIContext(), new TransferRiskWarningDialog.Builder().setTitleResource(R.string.transfer_warning_title).setContentResource(R.string.transfer_warning_contract_content).setTargetWebResource(R.string.transfer_warning_enter_fake_address).setTargetWebPage(R.string.transfer_warning_page_fake_address).setTargetWebUrl(languageZH ? "https://support.tronlink.org/hc/zh-cn/articles/33319771009049-%E5%A6%82%E4%BD%95%E9%98%B2%E8%8C%83%E5%81%87%E5%9C%B0%E5%9D%80%E9%AA%97%E5%B1%80" : "https://support.tronlink.org/hc/en-us/articles/33319771009049-%E5%A6%82%E4%BD%95%E9%98%B2%E8%8C%83%E5%81%87%E5%9C%B0%E5%9D%80%E9%AA%97%E5%B1%80").setOnClickNext(onClickListener));
            } else if (this.currentInputAddressBean.riskType == SelectSendAddressContract.RiskType.RISK_ACCOUNT) {
                TransferRiskWarningDialog.show(getIContext(), new TransferRiskWarningDialog.Builder().setTitleResource(R.string.transfer_warning_title).setContentResource(R.string.transfer_warning_risky_account).setTargetWebResource(R.string.transfer_warning_enter_risky_account).setTargetWebPage(R.string.transfer_warning_page_risky_account).setTargetWebUrl(languageZH ? "https://support.tronlink.org/hc/zh-cn/articles/33319978680729-%E5%A6%82%E4%BD%95%E9%98%B2%E8%8C%83%E9%A3%8E%E9%99%A9%E8%B4%A6%E6%88%B7%E9%AA%97%E5%B1%80" : "https://support.tronlink.org/hc/en-us/articles/33319978680729-%E5%A6%82%E4%BD%95%E9%98%B2%E8%8C%83%E9%A3%8E%E9%99%A9%E8%B4%A6%E6%88%B7%E9%AA%97%E5%B1%80").setOnClickNext(onClickListener));
            } else if (this.currentInputAddressBean.riskType == SelectSendAddressContract.RiskType.MULTI_SIGN) {
                TransferRiskWarningDialog.show(getIContext(), new TransferRiskWarningDialog.Builder().setTitleResource(R.string.transfer_warning_title).setContentResource(R.string.transfer_warning_multi_sign).setTargetWebResource(R.string.transfer_warning_enter_multi_sign).setTargetWebPage(R.string.transfer_warning_page_multi_sign).setTargetWebUrl(languageZH ? "https://support.tronlink.org/hc/zh-cn/articles/33319584041625-%E6%81%B6%E6%84%8F%E6%9B%B4%E6%94%B9%E5%A4%9A%E9%87%8D%E6%9D%83%E9%99%90%E9%AA%97%E5%B1%80" : "https://support.tronlink.org/hc/en-us/articles/33319584041625-%E6%81%B6%E6%84%8F%E6%9B%B4%E6%94%B9%E5%A4%9A%E9%87%8D%E6%9D%83%E9%99%90%E9%AA%97%E5%B1%80-%E8%8B%B1%E6%96%87").setOnClickNext(onClickListener));
            } else {
                enterTransfer();
            }
        }
    }

    public void lambda$enterNext$14(View view) {
        enterTransfer();
    }

    private void enterTransfer() {
        Pair<Boolean, Integer> checkTransferSupportability = ((SelectSendAddressPresenter) this.mPresenter).checkTransferSupportability(this.selectWallet);
        boolean booleanValue = ((Boolean) checkTransferSupportability.first).booleanValue();
        int intValue = ((Integer) checkTransferSupportability.second).intValue();
        if (!booleanValue) {
            toast(getString(intValue));
        } else {
            TransferSelectTokenActivity.startActivity(this, buildParam(), this.selectAccount, ((SelectSendAddressPresenter) this.mPresenter).getReceivedAccount(), this.fromMultiSign, this.controllerAccountName, this.multiSignPermissionData, this.nftItemInfo);
        }
    }

    private TransferParam buildParam() {
        TransferParam transferParam = new TransferParam();
        transferParam.setFromAddress(this.controllerAddress);
        transferParam.setType(1);
        transferParam.setTokenBean(this.tokenBean);
        transferParam.toAddress = this.currentClipBean.getAddress().toString();
        transferParam.setAccountActive(true ^ ((SelectSendAddressPresenter) this.mPresenter).isNullAccount(((SelectSendAddressPresenter) this.mPresenter).getReceivedAccount()));
        transferParam.setHasOwnerPermission(this.hasOwnerPermission);
        return transferParam;
    }

    public void onPasteEvent(CharSequence charSequence) {
        onPasteEvent(charSequence, true);
    }

    public void onPasteEvent(CharSequence charSequence, boolean z) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        NameAddressExtraBean nameAddressExtraBean = NameAddressExtraBean.getDefault();
        this.currentClipBean = nameAddressExtraBean;
        nameAddressExtraBean.setAddress(charSequence);
        TrimEditText trimEditText = this.etInput;
        if (trimEditText != null) {
            trimEditText.clearFocus();
        }
        if (this.mPresenter != 0 && z) {
            ((SelectSendAddressPresenter) this.mPresenter).checkInputAddress(this.controllerAddress, getTokenType(), this.currentClipBean);
        }
        hideSoftKeyboard();
    }

    private void updateMaskTextView(final boolean z, final CharSequence charSequence) {
        this.tvInputMask.post(new Runnable() {
            @Override
            public final void run() {
                lambda$updateMaskTextView$15(charSequence, z);
            }
        });
        if (TextUtils.isEmpty(charSequence)) {
            setVisibility(0, this.rlListContainer);
        } else {
            setVisibility(8, this.rlListContainer, this.searchView);
        }
    }

    public void lambda$updateMaskTextView$15(CharSequence charSequence, boolean z) {
        String ellipseNameOnly = EllipsizeTextViewUtils.ellipseNameOnly(this.tvInputMask, charSequence.toString(), StringUtils.LF);
        if (TextUtils.isEmpty(ellipseNameOnly)) {
            ellipseNameOnly = charSequence.toString();
        }
        if (z) {
            this.tvInputMask.setText(ellipseNameOnly);
            return;
        }
        this.tvInputMask.removeTextChangedListener(getTextWatcherSingleton());
        this.tvInputMask.setText(ellipseNameOnly);
        this.tvInputMask.addTextChangedListener(getTextWatcherSingleton());
    }

    public void resetViews() {
        updateMaskTextView(false, "");
        this.etInput.setText("");
        this.etInput.clearFocus();
        setVisibility(8, this.tvInputMask, this.ivDelete, this.tvAddressBook, this.errorView, this.similarAddressView, this.flagDid);
        setVisibility(0, this.etInput, this.tvPaste);
        this.btnNext.setEnabled(false);
        hideSoftKeyboard();
    }

    AddToAddressBookPopView.AddressBookChangeCallback getAddressBookCallback() {
        return new AddToAddressBookPopView.AddressBookChangeCallback() {
            @Override
            public final void onAddressBookChanged(String str, String str2, String str3) {
                lambda$getAddressBookCallback$16(str, str2, str3);
            }
        };
    }

    public void lambda$getAddressBookCallback$16(String str, String str2, String str3) {
        setVisibility(8, this.tvAddressBook);
        this.fragments[1].insertData(((SelectSendAddressPresenter) this.mPresenter).insertNewAddedAddress(str, str2, str3));
    }

    public void checkAndEnterMultiSign() {
        AnalyticsHelper.logEvent(AnalyticsHelper.MultiSignature.CLICK_MULTI_SIGNATURE_TRANSFER_PAGE);
        enterMultiSign();
    }
}
