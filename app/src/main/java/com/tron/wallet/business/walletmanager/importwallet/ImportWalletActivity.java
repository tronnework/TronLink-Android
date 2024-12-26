package com.tron.wallet.business.walletmanager.importwallet;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import com.facebook.common.util.UriUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.util.KeyboardUtils;
import com.lxj.xpopup.util.XPopupUtils;
import com.tron.tron_base.frame.base.BaseActivity;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletContract;
import com.tron.wallet.business.walletmanager.input.TextChangedListener;
import com.tron.wallet.common.components.CommonTitleDescriptionEditView;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.ErrorImportMnemonicLayout;
import com.tron.wallet.common.components.SimpleDraweeViewGif;
import com.tron.wallet.common.components.dialog.ThirdInputNoticeDialog;
import com.tron.wallet.common.components.flowlayout.TagAdapter;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tron.wallet.common.components.mnemonicflowlayout.AssociationalListener;
import com.tron.wallet.common.components.mnemonicflowlayout.FlowLayout;
import com.tron.wallet.common.components.mnemonicflowlayout.MnemonicTagAdapter;
import com.tron.wallet.common.components.mnemonicflowlayout.MnemonicTagFlowLayout;
import com.tron.wallet.common.components.mnemonicflowlayout.TagScrollView;
import com.tron.wallet.common.components.qr.ScannerActivity;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.AnalyticsHelper;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.NoDoubleClickListener2;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tron.wallet.common.utils.PopupWindowUtil;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.SoftHideKeyBoardUtil;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.common.utils.WalletNameGeneratorUtils;
import com.tron.wallet.databinding.AcImportWalletBinding;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tron.common.crypto.MnemonicUtils;
import org.tron.walletserver.InvalidNameException;
public class ImportWalletActivity extends BaseActivity<ImportWalletPresenter, ImportWalletModel> implements PermissionInterface, ImportWalletContract.View {
    public static final int MAX_NAME_LINGTH = 28;
    public static final String TAG = "ImportWalletActivity";
    private MnemonicTagAdapter adapter;
    AppBarLayout appBar;
    private AcImportWalletBinding binding;
    Button btNext;
    TextView btnPaste;
    CheckBox cbFour;
    CheckBox cbOne;
    CheckBox cbThree;
    CheckBox cbTwo;
    private BasePopupView checkPopupView;
    private boolean contentFlag;
    ErrorEdiTextLayout eetContent;
    ErrorImportMnemonicLayout errorImportMnemonicLayout;
    private BasePopupView errorPopupView;
    EditText etContent;
    CommonTitleDescriptionEditView etImportWalletName;
    CommonTitleDescriptionEditView etImportWalletPassword;
    CommonTitleDescriptionEditView etImportWalletPasswordAgain;
    TagFlowLayout flAssociational;
    MnemonicTagFlowLayout flowLayout;
    SimpleDraweeViewGif gifImage;
    private Handler handler;
    ArrayList hotBeanList;
    CommonTitleDescriptionEditView importContent;
    ImageView ivQRScan;
    String keyWord;
    private ViewTreeObserver.OnGlobalLayoutListener keyboardListener;
    LinearLayout liImportWallet;
    LinearLayout llContentError;
    View llNameError;
    View llPasswordAgainError;
    RelativeLayout ll_mnemonic;
    TagAdapter mAssociationalAdapter;
    private LayoutInflater mInflater;
    private PermissionHelper mPermissionHelper;
    NestedScrollView nestedScrollView;
    private String password;
    private String passwordAgain;
    private boolean passwordFlag;
    RelativeLayout rlBottomNext;
    RelativeLayout rlPassChecks;
    TagScrollView scrollView;
    View stateView;
    CollapsingToolbarLayout toolBarLayout;
    Toolbar toolbar;
    TextView tvContentError;
    TextView tvFour;
    TextView tvHasHDtips;
    TextView tvKeyCount;
    TextView tvNameError;
    TextView tvOne;
    TextView tvPasswordAgainError;
    TextView tvThree;
    TextView tvTwo;
    View viewContent;
    private String walletName;
    private String[] allWords = new String[0];
    private transient int type = 0;
    int scrollinstance = 0;
    private boolean isShielded = false;
    private boolean isCheckThirdInput = false;
    String lastMnemonic = "";
    private NoDoubleClickListener nextListener = new NoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View view) {
            if (liImportWallet.getVisibility() == 8) {
                if (type == 1) {
                    if (!MnemonicUtils.validateMnemonic(adapter.formatDataString())) {
                        tvContentError.setText(getString(R.string.enter_useful_content));
                        llContentError.setVisibility(View.VISIBLE);
                        return;
                    }
                    initMnomonicInputPass();
                } else if (type == 3) {
                    String obj = importContent.getText().toString();
                    if (!obj.contains("{") || !obj.contains("}")) {
                        tvContentError.setText(getString(R.string.enter_useful_content));
                        llContentError.setVisibility(View.VISIBLE);
                        return;
                    }
                    initKeystoreInputPass();
                } else {
                    String obj2 = importContent.getText().toString();
                    if (obj2.length() <= 64 && IsHexadecimal(obj2)) {
                        initPrikeyInputName();
                        return;
                    }
                    tvContentError.setText(getString(R.string.enter_useful_content));
                    llContentError.setVisibility(View.VISIBLE);
                }
            } else if (type == 3) {
                if (mPresenter != 0) {
                    ((ImportWalletPresenter) mPresenter).importWalletWithKeyStore(StringTronUtil.getText(importContent), walletName, StringTronUtil.getText(etImportWalletPassword));
                }
            } else if (type == 1) {
                if (mPresenter != 0) {
                    ((ImportWalletPresenter) mPresenter).importWalletWithMnemonic(adapter.formatDataString(), walletName, StringTronUtil.getText(etImportWalletPassword), StringTronUtil.getText(etImportWalletPasswordAgain));
                }
            } else if (type != 2 || mPresenter == 0) {
            } else {
                ((ImportWalletPresenter) mPresenter).importWalletWithPrivateKey(StringTronUtil.getText(importContent), walletName, StringTronUtil.getText(etImportWalletPassword), StringTronUtil.getText(etImportWalletPasswordAgain));
            }
        }
    };

    public static void lambda$checkDialog$3() {
    }

    public static void lambda$inputErrorDialog$4() {
    }

    @Override
    public int getPermissionsRequestCode() {
        return 2000;
    }

    @Override
    public View getRootView() {
        return this.viewContent;
    }

    public void initMultiPopView() {
    }

    @Override
    public boolean isShield() {
        return this.isShielded;
    }

    public static void start(Context context, boolean z) {
        Intent intent = new Intent(context, ImportWalletActivity.class);
        intent.putExtra(TronConfig.WALLET_extra, z);
        context.startActivity(intent);
    }

    @Override
    protected void setLayout() {
        setBackground(getResources().getColor(R.color.white), 0);
        AcImportWalletBinding inflate = AcImportWalletBinding.inflate(getLayoutInflater());
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
        this.toolbar = this.binding.toolbar;
        this.toolBarLayout = this.binding.toolbarLayout;
        this.gifImage = this.binding.gifImage;
        this.stateView = this.binding.stateView;
        this.appBar = this.binding.appBar;
        this.btNext = this.binding.btnNextStep;
        this.ivQRScan = this.binding.contentImportWallet.ivQr;
        this.importContent = this.binding.contentImportWallet.importContent;
        this.btnPaste = this.binding.contentImportWallet.btnPaste;
        this.tvKeyCount = this.binding.contentImportWallet.tvKeyCount;
        this.tvHasHDtips = this.binding.contentImportWallet.tvHasHdTips;
        this.llContentError = this.binding.contentImportWallet.llContentError;
        this.tvContentError = this.binding.contentImportWallet.tvContentError;
        this.errorImportMnemonicLayout = this.binding.contentImportWallet.eetMnemonic;
        this.scrollView = this.binding.contentImportWallet.scrollview;
        this.ll_mnemonic = this.binding.contentImportWallet.mnemonicLayout;
        this.flowLayout = this.binding.contentImportWallet.idFlowlayout;
        this.flAssociational = this.binding.contentImportWallet.flAssociational;
        this.eetContent = this.binding.contentImportWallet.eetContent;
        this.etContent = this.binding.contentImportWallet.etContent;
        this.nestedScrollView = this.binding.contentImportWallet.scrollAddWatchWallet;
        this.liImportWallet = this.binding.contentImportWallet.liImportWalletInfo;
        this.etImportWalletName = this.binding.contentImportWallet.importWalletName;
        this.etImportWalletPassword = this.binding.contentImportWallet.importWalletPassword;
        this.etImportWalletPasswordAgain = this.binding.contentImportWallet.importWalletPasswordAgain;
        this.rlPassChecks = this.binding.contentImportWallet.rlPassChecks;
        this.rlBottomNext = this.binding.rlBottomNextstep;
        this.tvOne = this.binding.contentImportWallet.tvOne;
        this.cbOne = this.binding.contentImportWallet.cbOne;
        this.cbTwo = this.binding.contentImportWallet.cbTwo;
        this.tvTwo = this.binding.contentImportWallet.tvTwo;
        this.cbThree = this.binding.contentImportWallet.cbThree;
        this.tvThree = this.binding.contentImportWallet.tvThree;
        this.cbFour = this.binding.contentImportWallet.cbFour;
        this.tvFour = this.binding.contentImportWallet.tvFour;
        this.llNameError = this.binding.contentImportWallet.llNameError;
        this.tvNameError = this.binding.contentImportWallet.tvNameError;
        this.llPasswordAgainError = this.binding.contentImportWallet.llPasswordAgainError;
        this.tvPasswordAgainError = this.binding.contentImportWallet.tvPasswordAgainError;
        this.viewContent = this.binding.viewContent;
    }

    @Override
    protected void processData() {
        if (getIntent() != null) {
            this.isShielded = getIntent().getBooleanExtra(TronConfig.WALLET_extra, false);
        }
        setSupportActionBar(this.toolbar);
        initMultiPopView();
        this.handler = new Handler();
        if (SpAPI.THIS.isCold()) {
            this.toolBarLayout.setTitle(getString(R.string.import_cold_wallet));
        } else {
            this.toolBarLayout.setTitle(getString(R.string.import_wallet));
        }
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SoftHideKeyBoardUtil.assistActivity(this, new SoftHideKeyBoardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int i) {
                if (i > 0) {
                    rlBottomNext.setVisibility(View.GONE);
                }
                if (importContent.isFocused()) {
                    nestedScrollView.scrollTo(0, importContent.getTop());
                } else if (etImportWalletName.isFocused()) {
                    nestedScrollView.scrollTo(0, etImportWalletName.getTop());
                } else if (etImportWalletPassword.isFocused()) {
                    nestedScrollView.scrollTo(0, etImportWalletPassword.getTop());
                } else if (etImportWalletPasswordAgain.isFocused()) {
                    nestedScrollView.scrollTo(0, etImportWalletPasswordAgain.getTop());
                }
            }

            @Override
            public void keyBoardHide(int i) {
                if (i > 0) {
                    rlBottomNext.setVisibility(View.VISIBLE);
                }
            }
        });
        this.gifImage.setGif(R.drawable.import_2, 1);
        this.importContent.setExFocused(false);
        showMnemonicInputView(true);
        initAssociationalView();
        initFlowLayout();
        initKeyboardListener();
        initType();
        initNext();
        initNamePassContent();
    }

    private void initNext() {
        this.ivQRScan.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                requestPermissions();
            }
        });
        this.btNext.setOnClickListener(this.nextListener);
    }

    private void initNamePassContent() {
        setClearTextClick(this.etImportWalletPasswordAgain);
        setClearTextClick(this.etImportWalletPassword);
        this.etImportWalletName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                ImportWalletActivity importWalletActivity = ImportWalletActivity.this;
                importWalletActivity.walletName = importWalletActivity.etImportWalletName.getText().toString().trim();
                if (z) {
                    expandedBar();
                    if (TextUtils.isEmpty(walletName)) {
                        etImportWalletName.setRightImageResId(0);
                        return;
                    }
                    etImportWalletName.setRightImageResId(R.mipmap.input_clear);
                    ImportWalletActivity importWalletActivity2 = ImportWalletActivity.this;
                    importWalletActivity2.setClearTextClick(importWalletActivity2.etImportWalletName);
                    return;
                }
                etImportWalletName.setRightImageResId(0);
            }
        });
        addTextChangedListener(this.etImportWalletName, new TextChangedListener() {
            @Override
            public final void afterTextChanged(Editable editable) {
                lambda$initNamePassContent$0(editable);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$beforeTextChanged(this, charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$onTextChanged(this, charSequence, i, i2, i3);
            }
        });
        addTextChangedListener(this.etImportWalletPassword, new TextChangedListener() {
            @Override
            public final void afterTextChanged(Editable editable) {
                lambda$initNamePassContent$1(editable);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$beforeTextChanged(this, charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$onTextChanged(this, charSequence, i, i2, i3);
            }
        });
        addTextChangedListener(this.etImportWalletPasswordAgain, new TextChangedListener() {
            @Override
            public final void afterTextChanged(Editable editable) {
                lambda$initNamePassContent$2(editable);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$beforeTextChanged(this, charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextChangedListener.-CC.$default$onTextChanged(this, charSequence, i, i2, i3);
            }
        });
        this.etImportWalletPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    expandedBar();
                }
            }
        });
        this.etImportWalletPasswordAgain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    expandedBar();
                }
            }
        });
        this.importContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    if (!isCheckThirdInput) {
                        isCheckThirdInput = true;
                        checkThirdPartyInput();
                    }
                    if (liImportWallet.getVisibility() == 0) {
                        liImportWallet.setVisibility(View.GONE);
                        btNext.setText(R.string.next_step);
                        checkContent();
                    }
                    expandedBar();
                    checkContent();
                    return;
                }
                checkContent();
            }
        });
        this.flowLayout.setFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    importContent.setExFocused(true);
                    if (liImportWallet.getVisibility() == 0) {
                        liImportWallet.setVisibility(View.GONE);
                        btNext.setText(R.string.next_step);
                    }
                    if (llContentError.getVisibility() == 0 && flowLayout.getMistakeCount() == 0 && adapter.getCount() <= 24) {
                        llContentError.setVisibility(View.GONE);
                    }
                    checkContent();
                    return;
                }
                importContent.setExFocused(false);
            }
        });
    }

    public void lambda$initNamePassContent$0(Editable editable) {
        this.walletName = editable.toString().trim();
        this.llNameError.setVisibility(View.GONE);
        if (TextUtils.isEmpty(this.walletName)) {
            this.etImportWalletName.setRightImageResId(0);
        } else {
            this.etImportWalletName.setRightImageResId(R.mipmap.input_clear);
        }
        if (TextUtils.isEmpty(this.walletName)) {
            showNameError(getResources().getString(R.string.error_name3));
        } else {
            checkInputName();
        }
        backListener();
    }

    public void lambda$initNamePassContent$1(Editable editable) {
        checkPasswordAgain();
        if (TextUtils.isEmpty(this.password)) {
            this.etImportWalletPassword.setRightImageResId(0);
        } else {
            this.etImportWalletPassword.setRightImageResId(R.mipmap.input_clear);
        }
        checkPassword(this.password);
        backListener();
    }

    public void lambda$initNamePassContent$2(Editable editable) {
        this.llPasswordAgainError.setVisibility(View.GONE);
        checkPasswordAgain();
        if (TextUtils.isEmpty(this.passwordAgain)) {
            this.etImportWalletPasswordAgain.setRightImageResId(0);
            this.btNext.setEnabled(false);
            return;
        }
        this.etImportWalletPasswordAgain.setRightImageResId(R.mipmap.input_clear);
        backListener();
    }

    public void checkThirdPartyInput() {
        boolean z = false;
        for (InputMethodInfo inputMethodInfo : ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).getInputMethodList()) {
            CharSequence loadLabel = inputMethodInfo.loadLabel(getPackageManager());
            LogUtils.d(TAG, "getList: " + ((Object) loadLabel) + "   " + inputMethodInfo.getId() + "   " + inputMethodInfo.getPackageName());
            if (inputMethodInfo != null && checkIsSystemInput(inputMethodInfo.getId())) {
                z = true;
            }
        }
        String string = Settings.Secure.getString(getContentResolver(), "default_input_method");
        LogUtils.d(TAG, "mDefaultInputMethodCls:  " + string);
        if (!TextUtils.isEmpty(string)) {
            String str = string.split("/")[0];
            LogUtils.d(TAG, "mDefaultInputMethodPkg: " + str);
            if (TextUtils.isEmpty(string) || checkIsSystemInput(string)) {
                return;
            }
            if (z) {
                showThirdInputNoticeDialog(true);
                return;
            } else {
                showThirdInputNoticeDialog(false);
                return;
            }
        }
        LogUtils.d(TAG, "mDefaultInputMethodCls: NULL");
    }

    private boolean checkIsSystemInput(String str) {
        if (str != null) {
            return str.contains("com.android.inputmethod") || str.contains("com.sec.android.inputmethod") || str.contains("com.samsung.android.honeyboard");
        }
        return false;
    }

    private void showThirdInputNoticeDialog(boolean z) {
        if (SpAPI.THIS.getIsNeverShowThirdInputNotice()) {
            return;
        }
        final ThirdInputNoticeDialog thirdInputNoticeDialog = new ThirdInputNoticeDialog(getIContext(), z);
        if (z) {
            thirdInputNoticeDialog.setBtFirstListener(getString(R.string.switch_input), new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ThirdPartyInputAlert.ThirdPartyInputAlert_Switch_input);
                    thirdInputNoticeDialog.dismiss();
                    if (thirdInputNoticeDialog.isChecked()) {
                        SpAPI.THIS.setIsNeverShowThirdInputNotice(true);
                    }
                    showSwitchInputMethod();
                }
            });
            thirdInputNoticeDialog.setBtSecondListener(getString(R.string.know_secure_risk_continue), new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ThirdPartyInputAlert.ThirdPartyInputAlert_Continue);
                    if (thirdInputNoticeDialog.isChecked()) {
                        SpAPI.THIS.setIsNeverShowThirdInputNotice(true);
                    }
                    thirdInputNoticeDialog.dismiss();
                }
            });
        } else {
            thirdInputNoticeDialog.setBtFirstListener(getString(R.string.know_secure_risk_continue), new NoDoubleClickListener2() {
                @Override
                protected void onNoDoubleClick(View view) {
                    AnalyticsHelper.logEvent(AnalyticsHelper.ThirdPartyInputAlert.ThirdPartyInputAlert_Continue);
                    if (thirdInputNoticeDialog.isChecked()) {
                        SpAPI.THIS.setIsNeverShowThirdInputNotice(true);
                    }
                    thirdInputNoticeDialog.dismiss();
                }
            });
        }
        thirdInputNoticeDialog.setCanceledOnTouchOutside(false);
        thirdInputNoticeDialog.show();
    }

    public void showSwitchInputMethod() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showInputMethodPicker();
    }

    public void setClearTextClick(final CommonTitleDescriptionEditView commonTitleDescriptionEditView) {
        commonTitleDescriptionEditView.setRightDrawableClick(new CommonTitleDescriptionEditView.RightDrawableClick() {
            @Override
            public void onRightDrawableClick(View view) {
                commonTitleDescriptionEditView.setText("");
                if (R.id.et_password == commonTitleDescriptionEditView.getId()) {
                    checkPassword("");
                }
            }
        });
    }

    private void addTextChangedListener(EditText editText, final TextChangedListener textChangedListener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                textChangedListener.beforeTextChanged(charSequence, i, i2, i3);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                textChangedListener.onTextChanged(charSequence, i, i2, i3);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                textChangedListener.afterTextChanged(editable);
            }
        });
    }

    private void initInputNames() {
        this.etImportWalletName.setText("");
        this.etImportWalletPassword.setText("");
        this.etImportWalletPasswordAgain.setText("");
    }

    public void initMnomonicInputPass() {
        this.liImportWallet.setVisibility(View.VISIBLE);
        initInputNames();
        this.btNext.setText(R.string.import_mnemonic);
        this.btNext.setEnabled(false);
        this.etImportWalletName.setText(WalletNameGeneratorUtils.generateWalletName(1, this.isShielded));
        this.etImportWalletPassword.setHint(getString(R.string.input_password));
        this.etImportWalletPasswordAgain.setVisibility(View.VISIBLE);
        this.rlPassChecks.setVisibility(View.VISIBLE);
        this.btNext.setOnClickListener(this.nextListener);
        this.etImportWalletName.requestFocus();
    }

    public void initKeystoreInputPass() {
        this.liImportWallet.setVisibility(View.VISIBLE);
        initInputNames();
        this.btNext.setText(R.string.import_keystore);
        this.btNext.setEnabled(false);
        this.etImportWalletName.setText(WalletNameGeneratorUtils.generateWalletName(3, this.isShielded));
        this.liImportWallet.setVisibility(View.VISIBLE);
        this.etImportWalletPassword.setHint(getString(R.string.enter_the_corresponding_password));
        this.rlPassChecks.setVisibility(View.INVISIBLE);
        this.etImportWalletPasswordAgain.setVisibility(View.INVISIBLE);
        this.btNext.setOnClickListener(this.nextListener);
        this.etImportWalletName.requestFocus();
    }

    public void initPrikeyInputName() {
        this.liImportWallet.setVisibility(View.VISIBLE);
        initInputNames();
        this.btNext.setText(R.string.import_private);
        this.btNext.setEnabled(false);
        this.etImportWalletPassword.setHint(getString(R.string.input_password));
        this.etImportWalletPasswordAgain.setVisibility(View.VISIBLE);
        this.rlPassChecks.setVisibility(View.VISIBLE);
        this.etImportWalletName.setText(WalletNameGeneratorUtils.generateWalletName(2, this.isShielded));
        this.btNext.setOnClickListener(this.nextListener);
        this.etImportWalletName.requestFocus();
    }

    private void initType() {
        this.errorImportMnemonicLayout.setVisibility(View.GONE);
        this.eetContent.setVisibility(View.GONE);
        changePasteRemove(0);
        this.importContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return i == 66;
            }
        });
        this.importContent.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return !Pattern.compile("[A-Za-z0-9{},:\\s\"-]*").matcher(charSequence).matches() ? "" : charSequence;
            }
        }});
        this.importContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String trim = charSequence.toString().trim();
                if (type == 1 && trim.length() == 0) {
                    if (flowLayout.getMistakeCount() > 0) {
                        tvContentError.setText(R.string.incorrectg_mnemonic_string);
                        llContentError.setVisibility(View.VISIBLE);
                        return;
                    }
                    return;
                }
                if (i3 != 0) {
                    if (i3 > 5 && !trim.startsWith("{") && trim.contains(" ")) {
                        flowLayout.pasteText(trim);
                        changeType(1);
                    } else {
                        adapter.resetData(new ArrayList());
                        flowLayout.getEditText().setText(charSequence.toString().toLowerCase());
                        flowLayout.updateAssociational(charSequence.toString().trim().toLowerCase());
                    }
                } else {
                    flowLayout.updateAssociational(trim.trim().toLowerCase());
                }
                if (charSequence.toString().equals(" ")) {
                    importContent.setText("");
                    return;
                }
                if (charSequence.toString().endsWith(" ")) {
                    if (type == 2) {
                        importContent.setText(charSequence.toString().trim());
                        importContent.setSelection(importContent.getText().toString().length());
                    } else if (StringTronUtil.isAllNumber(charSequence.toString().trim())) {
                        importContent.setText(charSequence.toString().trim());
                        importContent.setSelection(importContent.getText().toString().length());
                        return;
                    } else {
                        adapter.resetData(new ArrayList());
                        flowLayout.pasteText(charSequence.toString().toLowerCase());
                        changeType(1);
                        flowLayout.getEditText().requestFocus();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (type != 1 || flowLayout.getMistakeCount() <= 0) {
                                    return;
                                }
                                tvContentError.setText(R.string.incorrectg_mnemonic_string);
                                llContentError.setVisibility(View.VISIBLE);
                            }
                        }, 300L);
                    }
                }
                if (StringTronUtil.isEmpty(trim)) {
                    if (tvHasHDtips.getVisibility() == 0) {
                        tvHasHDtips.setVisibility(View.GONE);
                    }
                } else if (trim.startsWith("{")) {
                    changeType(3);
                } else if (trim.length() > 2 && trim.length() <= 64 && IsHexadecimal(trim)) {
                    if (type != 2 && trim.length() > 2 && flAssociational.getAdapter().getCount() == 0) {
                        changeType(2);
                    } else if (llContentError.getVisibility() == 0) {
                        llContentError.setVisibility(View.GONE);
                    }
                } else if (IsHexadecimal(trim)) {
                } else {
                    if (flAssociational.getAdapter().getCount() > 0) {
                        if (llContentError.getVisibility() == 0) {
                            llContentError.setVisibility(View.GONE);
                        }
                    } else if (type == 1) {
                    } else {
                        if (type == 2) {
                            tvContentError.setText(getText(R.string.imvalid_private_key));
                            llContentError.setVisibility(View.VISIBLE);
                        } else if (flAssociational.getAdapter().getCount() < 1) {
                            tvContentError.setText(getText(R.string.enter_useful_content));
                            llContentError.setVisibility(View.VISIBLE);
                        } else {
                            llContentError.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String trim = editable.toString().trim();
                if (trim == null || trim.length() < 1) {
                    tvKeyCount.setVisibility(View.GONE);
                    if (llContentError.getVisibility() == 0 && flowLayout.getMistakeCount() == 0 && adapter.formatDataString().trim().split("\\s+").length <= 24) {
                        llContentError.setVisibility(View.GONE);
                    }
                    if (adapter != null && StringTronUtil.isEmpty(adapter.formatDataString())) {
                        btNext.setEnabled(false);
                        if (llContentError.getVisibility() == 0) {
                            llContentError.setVisibility(View.GONE);
                        }
                        type = 0;
                        changePasteRemove(0);
                        return;
                    }
                    changePasteRemove(1);
                    return;
                }
                changePasteRemove(1);
                if (type == 2 && trim != null) {
                    setKeyCount(trim.length());
                    if (trim.length() >= 3) {
                        if (trim.length() <= 64) {
                            contentFlag = true;
                        } else {
                            tvContentError.setText(getString(R.string.too_long_private_key));
                            llContentError.setVisibility(View.VISIBLE);
                        }
                    }
                } else if (type == 3) {
                    if (trim.startsWith("{") && trim.endsWith("}")) {
                        contentFlag = true;
                    }
                } else if (type != 1) {
                    if (isLegalInput(trim)) {
                        IsHexadecimal(trim);
                    } else if (llContentError.getVisibility() == 8) {
                        tvContentError.setText(R.string.enter_useful_content);
                        llContentError.setVisibility(View.VISIBLE);
                    }
                }
                checkContent();
            }
        });
        MnemonicTagFlowLayout mnemonicTagFlowLayout = this.flowLayout;
        if (mnemonicTagFlowLayout != null) {
            mnemonicTagFlowLayout.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    int length = adapter.formatDataString().trim().split("\\s+").length;
                    if (llContentError.getVisibility() == 0 && flowLayout.getMistakeCount() == 0 && length <= 24) {
                        llContentError.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    public void setKeyCount(int i) {
        if (i <= 0) {
            this.tvKeyCount.setVisibility(View.GONE);
            return;
        }
        this.tvKeyCount.setVisibility(View.VISIBLE);
        String str = i + "/64";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#232C41"));
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor("#9BA4B6"));
        spannableStringBuilder.setSpan(foregroundColorSpan, str.indexOf("/"), str.length(), 17);
        spannableStringBuilder.setSpan(foregroundColorSpan2, 0, str.indexOf("/"), 17);
        this.tvKeyCount.setText(spannableStringBuilder);
        if (i > 64) {
            if (this.type == 1) {
                this.llContentError.setVisibility(View.GONE);
                return;
            }
            this.llContentError.setVisibility(View.VISIBLE);
            this.tvContentError.setText(R.string.imvalid_private_key);
        }
    }

    public void changePasteRemove(int i) {
        if (i == 0) {
            this.btnPaste.setText(R.string.paste);
            this.btnPaste.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboardManager == null || clipboardManager.getPrimaryClip() == null || clipboardManager.getPrimaryClip().getItemCount() <= 0 || clipboardManager.getPrimaryClip().getItemAt(0).getText() == null) {
                        return;
                    }
                    pasteText(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
                    changePasteRemove(1);
                    checkContent();
                }
            });
        } else if (StringTronUtil.isEmpty(this.importContent.getText().toString()) && this.adapter.getCount() == 0 && StringTronUtil.isEmpty(this.adapter.formatDataString())) {
        } else {
            this.btnPaste.setText(R.string.clear);
            this.btnPaste.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    checkDialog(new OnConfirmListener() {
                        @Override
                        public void onConfirm() {
                            if (liImportWallet.getVisibility() == 0) {
                                liImportWallet.setVisibility(View.GONE);
                                nestedScrollView.invalidate();
                            }
                            if (type == 1) {
                                adapter.resetData(new ArrayList());
                                flowLayout.clearAllWords();
                                flowLayout.getEditText().clearFocus();
                            } else {
                                importContent.setHint(R.string.scan_to_enter_content);
                            }
                            if (tvHasHDtips.getVisibility() == 0) {
                                tvHasHDtips.setVisibility(View.GONE);
                            }
                            changeType(0);
                            tvKeyCount.setVisibility(View.GONE);
                            changePasteRemove(0);
                            importContent.requestFocus();
                        }
                    });
                }
            });
        }
    }

    public void checkDialog(OnConfirmListener onConfirmListener) {
        if (this.checkPopupView == null) {
            this.checkPopupView = PopupWindowUtil.getConfirmCancelPopupViewBlackWhite(getIContext(), getString(R.string.check_clear_input), "", R.string.cancel, R.string.confirm, onConfirmListener, new OnCancelListener() {
                @Override
                public final void onCancel() {
                    ImportWalletActivity.lambda$checkDialog$3();
                }
            }, false);
        }
        if (this.checkPopupView.isShow()) {
            return;
        }
        this.checkPopupView.show();
    }

    public void inputErrorDialog(OnConfirmListener onConfirmListener) {
        if (this.errorPopupView == null) {
            this.errorPopupView = PopupWindowUtil.getConfirmCancelPopupViewBlackWhite(getIContext(), getString(R.string.make_sure_formated_content), "", R.string.cancel, R.string.confirm, onConfirmListener, new OnCancelListener() {
                @Override
                public final void onCancel() {
                    ImportWalletActivity.lambda$inputErrorDialog$4();
                }
            }, true);
        }
        if (this.errorPopupView.isShow()) {
            return;
        }
        this.errorPopupView.show();
    }

    public void pasteText(String str) {
        if (str == null) {
            return;
        }
        if (str.contains("{")) {
            changeType(3);
            this.importContent.setText(str);
            CommonTitleDescriptionEditView commonTitleDescriptionEditView = this.importContent;
            commonTitleDescriptionEditView.setSelection(commonTitleDescriptionEditView.getText().toString().trim().length());
        } else if (str.length() <= 64 && IsHexadecimal(str)) {
            changeType(1);
            this.importContent.setText(str);
            CommonTitleDescriptionEditView commonTitleDescriptionEditView2 = this.importContent;
            commonTitleDescriptionEditView2.setSelection(commonTitleDescriptionEditView2.getText().toString().trim().length());
        } else if (str.contains(" ")) {
            changeType(1);
            this.errorImportMnemonicLayout.setVisibility(View.VISIBLE);
            this.importContent.setEnabled(false);
            this.importContent.setExFocused(true);
            this.importContent.setHint("");
            this.flowLayout.pasteText(str);
            this.flowLayout.requestEditFocus();
        } else {
            this.importContent.setText(str);
            CommonTitleDescriptionEditView commonTitleDescriptionEditView3 = this.importContent;
            commonTitleDescriptionEditView3.setSelection(commonTitleDescriptionEditView3.getText().toString().trim().length());
            if (this.importContent.getText().toString().length() <= 0) {
                return;
            }
            this.llContentError.setVisibility(View.VISIBLE);
        }
        changePasteRemove(1);
        checkContent();
    }

    public void changeType(int i) {
        this.type = i;
        changeToType();
    }

    private void changeToType() {
        int i = this.type;
        if (i == 1) {
            this.errorImportMnemonicLayout.setVisibility(View.VISIBLE);
            this.adapter.clearWrongDatas();
            this.tvContentError.setText(R.string.incorrectg_mnemonic_string);
            this.importContent.setText("");
            this.importContent.setEnabled(false);
            this.importContent.setExFocused(true);
            this.tvKeyCount.setVisibility(View.GONE);
            this.importContent.setHint("");
            if (this.liImportWallet.getVisibility() == 0) {
                this.btNext.setText(R.string.import_mnemonic);
            }
        } else if (i == 3) {
            this.adapter.resetData(new ArrayList());
            this.tvKeyCount.setVisibility(View.GONE);
            this.eetContent.setVisibility(View.GONE);
            this.importContent.setHint(R.string.scan_to_enter_content);
            this.errorImportMnemonicLayout.setVisibility(View.GONE);
            this.llContentError.setVisibility(View.GONE);
            this.importContent.setEnabled(true);
            if (this.liImportWallet.getVisibility() == 0) {
                this.btNext.setText(R.string.import_keystore);
            }
            if (this.tvHasHDtips.getVisibility() == 0) {
                this.tvHasHDtips.setVisibility(View.GONE);
            }
        } else if (i == 2) {
            this.adapter.resetData(new ArrayList());
            this.eetContent.setVisibility(View.GONE);
            this.importContent.setEnabled(true);
            this.errorImportMnemonicLayout.setVisibility(View.GONE);
            this.llContentError.setVisibility(View.GONE);
            if (this.liImportWallet.getVisibility() == 0) {
                this.btNext.setText(R.string.import_private);
            }
            if (this.tvHasHDtips.getVisibility() == 0) {
                this.tvHasHDtips.setVisibility(View.GONE);
            }
        } else {
            this.adapter.resetData(new ArrayList());
            this.eetContent.setVisibility(View.GONE);
            this.importContent.setEnabled(true);
            this.btNext.setText(R.string.next_step);
            this.importContent.setHint(R.string.scan_to_enter_content);
            this.importContent.setText("");
            this.errorImportMnemonicLayout.setVisibility(View.GONE);
            this.llContentError.setVisibility(View.GONE);
            this.importContent.requestFocus();
        }
    }

    private void initFlowLayout() {
        this.mInflater = LayoutInflater.from(this);
        this.scrollView.setTouchListener(new TagScrollView.TouchListener() {
            @Override
            public void longTouch() {
            }

            @Override
            public void touch() {
                flowLayout.requestEditFocus();
            }
        });
        MnemonicTagAdapter mnemonicTagAdapter = new MnemonicTagAdapter(this.allWords) {
            @Override
            public TextView getView(FlowLayout flowLayout, int i, String str) {
                TextView textView = (TextView) mInflater.inflate(R.layout.import_tag_item, (ViewGroup) flowLayout, false);
                textView.setText(str);
                return textView;
            }

            @Override
            public View getInputView(FlowLayout flowLayout) {
                return mInflater.inflate(R.layout.item_mnemonic_edit, (ViewGroup) flowLayout, false);
            }
        };
        this.adapter = mnemonicTagAdapter;
        this.flowLayout.setAdapter(mnemonicTagAdapter);
        this.flowLayout.setEmptyTagListener(new MnemonicTagFlowLayout.onEmptyTagListener() {
            @Override
            public void onLastTagDelete() {
                if (StringTronUtil.isEmpty(adapter.formatDataString()) && type == 1) {
                    errorImportMnemonicLayout.setVisibility(View.GONE);
                    changeType(0);
                    importContent.requestFocus();
                }
            }
        });
        this.flowLayout.setEditContentListener(new MnemonicTagFlowLayout.onEditContentListener() {
            @Override
            public void onContentInput() {
                int length = adapter.formatDataString().trim().split("\\s+").length;
                if (llContentError.getVisibility() == 0 && flowLayout.getMistakeCount() == 0 && length <= 24) {
                    llContentError.setVisibility(View.GONE);
                } else if (flowLayout.getMistakeCount() > 0) {
                    tvContentError.setText(R.string.incorrectg_mnemonic_string);
                    llContentError.setVisibility(View.VISIBLE);
                }
            }
        });
        this.flowLayout.setRemoveItemListener(new MnemonicTagFlowLayout.onRemoveItemListener() {
            @Override
            public void removeItem(int i) {
                if (type == 1 && flowLayout.getMistakeCount() == 0 && adapter.getCount() <= 24 && llContentError.getVisibility() == 0) {
                    llContentError.setVisibility(View.GONE);
                }
                checkContent();
            }
        });
        this.flowLayout.setAssociationalListener(new AssociationalListener() {
            @Override
            public void update(String str, ArrayList arrayList) {
                if (arrayList.size() > 0) {
                    keyWord = str;
                    flAssociational.setVisibility(View.VISIBLE);
                } else {
                    flAssociational.setVisibility(View.GONE);
                }
                hotBeanList.clear();
                hotBeanList.addAll(arrayList);
                mAssociationalAdapter.notifyDataChanged();
                if (StringTronUtil.isEmpty(str) && StringTronUtil.isEmpty(adapter.formatDataString()) && type == 1) {
                    errorImportMnemonicLayout.setVisibility(View.GONE);
                    changeType(0);
                    importContent.requestFocus();
                }
            }

            @Override
            public void hasMistake(int i) {
                if (i > 0) {
                    if (type == 1 && flowLayout.getMistakeCount() > 0) {
                        llContentError.setVisibility(View.VISIBLE);
                        tvContentError.setText(R.string.incorrectg_mnemonic_string);
                    }
                    contentFlag = false;
                    String obj = importContent.getText().toString();
                    if (obj.length() > 1 && obj.length() < 5 && obj.endsWith(" ")) {
                        llContentError.setVisibility(View.VISIBLE);
                        tvContentError.setText(R.string.incorrectg_mnemonic_string);
                    }
                } else {
                    llContentError.setVisibility(View.GONE);
                }
                checkContent();
            }

            @Override
            public void onTagChanged() {
                if (adapter != null && adapter.getCount() > 0) {
                    contentFlag = true;
                } else {
                    contentFlag = false;
                }
            }
        });
    }

    private void initAssociationalView() {
        this.hotBeanList = new ArrayList();
        TagAdapter tagAdapter = new TagAdapter(this.hotBeanList) {
            @Override
            public View getView(com.tron.wallet.common.components.flowlayout.FlowLayout flowLayout, int i, Object obj) {
                TextView textView = (TextView) LayoutInflater.from(getIContext()).inflate(R.layout.item_associational, (ViewGroup) flAssociational, false);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(obj.toString());
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_23)), 0, keyWord.length(), 17);
                textView.setText(spannableStringBuilder);
                return textView;
            }
        };
        this.mAssociationalAdapter = tagAdapter;
        this.flAssociational.setAdapter(tagAdapter);
        this.flAssociational.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, com.tron.wallet.common.components.flowlayout.FlowLayout flowLayout) {
                flowLayout.setEditText(hotBeanList.get(i).toString(), true);
                if (type != 1) {
                    changeType(1);
                }
                importContent.setSelection(importContent.getText().length());
                flowLayout.getEditText().requestFocus();
                flowLayout.getEditText().requestFocusFromTouch();
                ((InputMethodManager) flowLayout.getEditText().getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(flowLayout.getEditText(), 0);
                return false;
            }
        });
    }

    private void initKeyboardListener() {
        this.keyboardListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                nestedScrollView.getWindowVisibleDisplayFrame(rect);
                int i = rect.bottom;
                int i2 = rect.top;
                nestedScrollView.getHeight();
                ImportWalletActivity.getDecorViewInvisibleHeight((Activity) getIContext());
                int height = scrollView.getHeight();
                int height2 = importContent.getHeight() - UIUtils.dip2px(30.0f);
                if (adapter != null && importContent.getText().length() == 0 && adapter.getCount() == 0) {
                    ViewGroup.LayoutParams layoutParams = importContent.getLayoutParams();
                    layoutParams.height = -2;
                    importContent.setLayoutParams(layoutParams);
                    ViewGroup.LayoutParams layoutParams2 = flowLayout.getLayoutParams();
                    layoutParams2.height = -2;
                    flowLayout.setLayoutParams(layoutParams2);
                } else if (height != height2) {
                    if (height > height2) {
                        if (adapter.getCount() != 0) {
                            ViewGroup.LayoutParams layoutParams3 = importContent.getLayoutParams();
                            layoutParams3.height = height + UIUtils.dip2px(30.0f);
                            importContent.setLayoutParams(layoutParams3);
                            return;
                        }
                        ViewGroup.LayoutParams layoutParams4 = importContent.getLayoutParams();
                        layoutParams4.height = -2;
                        importContent.setLayoutParams(layoutParams4);
                        return;
                    }
                    ViewGroup.LayoutParams layoutParams5 = flowLayout.getLayoutParams();
                    layoutParams5.height = height2;
                    flowLayout.setLayoutParams(layoutParams5);
                }
            }
        };
        this.nestedScrollView.getViewTreeObserver().addOnGlobalLayoutListener(this.keyboardListener);
    }

    public static int getDecorViewInvisibleHeight(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView == null) {
            return KeyboardUtils.sDecorViewInvisibleHeightPre;
        }
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int abs = Math.abs(decorView.getBottom() - rect.bottom);
        if (abs <= XPopupUtils.getNavBarHeight(activity.getWindow())) {
            return 0;
        }
        return abs;
    }

    private void changeNext() {
        if (this.type == 3) {
            if (this.contentFlag) {
                this.btNext.setEnabled(true);
            } else {
                this.btNext.setEnabled(false);
            }
        } else if (this.contentFlag) {
            this.btNext.setEnabled(true);
        } else {
            this.btNext.setEnabled(false);
        }
    }

    public void checkContent() {
        if (this.type == 0 && StringTronUtil.isEmpty(this.importContent.getText().toString()) && this.adapter.getCount() > 0) {
            this.type = 1;
        }
        int i = this.type;
        if (i == 1) {
            int length = this.adapter.formatDataString().trim().split("\\s+").length;
            if (length == 12 || length == 15 || length == 18 || length == 24) {
                this.btNext.setEnabled(true);
                checkIfShowHasHDTips();
            } else if (length > 24) {
                this.tvContentError.setText(getString(R.string.incorrectg_mnemonic_string));
                this.llContentError.setVisibility(View.VISIBLE);
                this.btNext.setEnabled(false);
            } else if (this.btNext.isEnabled()) {
                this.btNext.setEnabled(false);
                return;
            } else {
                return;
            }
        } else if (i == 3) {
            String obj = this.importContent.getText().toString();
            if (obj.contains("{") && obj.contains("}")) {
                this.btNext.setEnabled(true);
            } else if (this.btNext.isEnabled()) {
                this.btNext.setEnabled(false);
                return;
            } else {
                return;
            }
        } else if (i == 2) {
            String obj2 = this.importContent.getText().toString();
            if (obj2.length() <= 64 && IsHexadecimal(obj2)) {
                if (this.liImportWallet.getVisibility() == 8) {
                    this.btNext.setEnabled(true);
                }
            } else if (this.btNext.isEnabled()) {
                this.btNext.setEnabled(false);
                return;
            } else {
                return;
            }
        }
        if (this.llContentError.getVisibility() == 0) {
            this.btNext.setEnabled(false);
        }
    }

    private void checkIfShowHasHDTips() {
        MnemonicTagAdapter mnemonicTagAdapter = this.adapter;
        if (mnemonicTagAdapter != null) {
            String formatDataString = mnemonicTagAdapter.formatDataString();
            if (this.lastMnemonic.equals(formatDataString)) {
                return;
            }
            this.lastMnemonic = formatDataString;
            ((ImportWalletPresenter) this.mPresenter).startCheckIsLocalHD(formatDataString);
        }
    }

    public void showMnemonicInputView(boolean z) {
        this.ll_mnemonic.setVisibility(z ? View.VISIBLE : View.GONE);
        this.flAssociational.setVisibility(z ? View.VISIBLE : View.GONE);
        this.llContentError.setVisibility(View.GONE);
        this.tvContentError.setText(R.string.incorrectg_mnemonic_string);
    }

    private Uri getUriFromDrawableRes(int i) {
        return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build();
    }

    public boolean IsHexadecimal(String str) {
        return Pattern.compile("[A-Fa-f0-9]+$").matcher(str).matches();
    }

    public boolean isLegalInput(String str) {
        return Pattern.compile("[A-Za-z0-9{}\"-]*").matcher(str).matches();
    }

    public String getFirstAssociationalWord() {
        ArrayList arrayList = this.hotBeanList;
        return (arrayList == null || arrayList.size() <= 0) ? "" : (String) this.hotBeanList.get(0);
    }

    protected void requestPermissions() {
        PermissionHelper permissionHelper = new PermissionHelper(this, this);
        this.mPermissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    @Override
    public void showLocalHDTips(boolean z) {
        this.lastMnemonic = "";
        if (z) {
            this.tvHasHDtips.setVisibility(View.VISIBLE);
        } else {
            this.tvHasHDtips.setVisibility(View.GONE);
        }
    }

    @Override
    public String[] getPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    @Override
    public void requestPermissionsSuccess() {
        toScan();
    }

    @Override
    public void requestPermissionsFail() {
        ToastError(R.string.error_permission);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionHelper permissionHelper = this.mPermissionHelper;
        if (permissionHelper == null || !permissionHelper.requestPermissionsResult(i, strArr, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult != null) {
            if (parseActivityResult.getContents() != null) {
                String trim = intent.getStringExtra("SCAN_RESULT").trim();
                String[] split = trim.replaceAll("[^a-z0-9A-Z\\s\\n]", " ").trim().trim().split("\\s+");
                if (StringTronUtil.isEmpty(trim)) {
                    showContentInputError();
                } else if (trim.trim().startsWith("{") && trim.contains("}")) {
                    if (this.liImportWallet.getVisibility() == 0) {
                        this.liImportWallet.setVisibility(View.GONE);
                        this.btNext.setText(R.string.next_step);
                    }
                    changeType(3);
                    this.importContent.setText(trim.trim());
                    CommonTitleDescriptionEditView commonTitleDescriptionEditView = this.importContent;
                    commonTitleDescriptionEditView.setSelection(commonTitleDescriptionEditView.getText().toString().trim().length());
                } else if (trim.length() <= 64 && IsHexadecimal(trim)) {
                    if (this.liImportWallet.getVisibility() == 0) {
                        this.liImportWallet.setVisibility(View.GONE);
                        this.btNext.setText(R.string.next_step);
                    }
                    changeType(2);
                    this.importContent.setText(trim.trim());
                    CommonTitleDescriptionEditView commonTitleDescriptionEditView2 = this.importContent;
                    commonTitleDescriptionEditView2.setSelection(commonTitleDescriptionEditView2.getText().toString().trim().length());
                } else if (split != null && split.length > 1) {
                    if (this.liImportWallet.getVisibility() == 0) {
                        this.liImportWallet.setVisibility(View.GONE);
                        this.btNext.setText(R.string.next_step);
                    }
                    for (String str : split) {
                        if (!this.flowLayout.checkWord(str)) {
                            showContentInputError();
                            return;
                        }
                    }
                    this.adapter.resetData(new ArrayList());
                    this.flowLayout.pasteText(trim);
                    this.flowLayout.requestEditFocus();
                    changePasteRemove(1);
                    changeType(1);
                } else {
                    showContentInputError();
                }
                checkContent();
                return;
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    private void showContentInputError() {
        inputErrorDialog(new OnConfirmListener() {
            @Override
            public void onConfirm() {
                if (type == 1) {
                    flowLayout.clearAllWords();
                    changeType(2);
                } else {
                    importContent.setText("");
                }
                changePasteRemove(0);
                importContent.setSelection(importContent.getText().length());
                importContent.requestFocus();
            }
        });
    }

    private void toScan() {
        ScannerActivity.start(this);
    }

    public void backListener() {
        if (this.liImportWallet.getVisibility() == 8) {
            this.btNext.setText(R.string.next_step);
            checkContent();
            return;
        }
        requestInputData();
        if (checkButton(this.walletName, this.password, this.passwordAgain)) {
            this.btNext.setEnabled(true);
        } else {
            this.btNext.setEnabled(false);
        }
    }

    private boolean checkButton(String str, String str2, String str3) {
        return this.type == 3 ? (StringTronUtil.isEmpty(str2, str) || !StringTronUtil.validataLegalString2(str) || WalletUtils.existWallet(str)) ? false : true : !StringTronUtil.isEmpty(str2, str, str3) && StringTronUtil.isOkPasswordTwo(str2) && str3.equals(str2) && StringTronUtil.validataLegalString2(str) && !WalletUtils.existWallet(str);
    }

    public void expandedBar() {
        this.appBar.setExpanded(false);
    }

    private void checkPasswordAgain() {
        requestInputData();
        if (StringTronUtil.isEmpty(this.passwordAgain)) {
            this.llPasswordAgainError.setVisibility(View.GONE);
        } else if (!this.passwordAgain.equals(this.password) && !StringTronUtil.isEmpty(this.password) && this.passwordAgain.length() > this.password.length() / 2) {
            this.llPasswordAgainError.setVisibility(View.VISIBLE);
            this.tvPasswordAgainError.setText(R.string.no_same);
        } else {
            this.llPasswordAgainError.setVisibility(View.GONE);
        }
    }

    private void requestInputData() {
        String text = StringTronUtil.getText(this.etImportWalletName);
        this.walletName = text;
        if (StringTronUtil.isEmpty(text)) {
            showNameError(getResources().getString(R.string.error_name3));
        } else {
            try {
                this.walletName = StringTronUtil.trimEnd(this.walletName.toCharArray());
            } catch (InvalidNameException e) {
                LogUtils.e(e);
                showNameError(getResources().getString(R.string.error_name2));
                this.walletName = "";
            }
        }
        this.password = StringTronUtil.getText(this.etImportWalletPassword);
        this.passwordAgain = StringTronUtil.getText(this.etImportWalletPasswordAgain);
    }

    private void checkInputName() {
        try {
            String trimEnd = StringTronUtil.trimEnd(this.walletName.toCharArray());
            this.walletName = trimEnd;
            if (StringTronUtil.isEmpty(trimEnd)) {
                showNameError(getResources().getString(R.string.error_name3));
            } else if (!StringTronUtil.validataLegalString2(this.walletName)) {
                showNameError(getResources().getString(R.string.error_name2));
            } else if (WalletUtils.existWallet(this.walletName)) {
                showNameError(getResources().getString(R.string.exist_wallet_name));
            } else {
                try {
                    Editable text = this.etImportWalletName.getText();
                    String trim = text.toString().trim();
                    int selectionEnd = Selection.getSelectionEnd(text);
                    int i = 0;
                    for (int i2 = 0; i2 < trim.length(); i2++) {
                        if (trim.charAt(i2) >= ' ') {
                        }
                        i++;
                        if (i > 28) {
                            this.etImportWalletName.setText(trim.substring(0, i2));
                            Editable text2 = this.etImportWalletName.getText();
                            if (selectionEnd > text2.length()) {
                                selectionEnd = text2.length();
                            }
                            Selection.setSelection(text2, selectionEnd);
                        }
                    }
                } catch (Exception e) {
                    SentryUtil.captureException(e);
                    LogUtils.e(e);
                }
            }
        } catch (InvalidNameException e2) {
            LogUtils.e(e2);
            showNameError(getResources().getString(R.string.error_name2));
        }
    }

    public void showNameError(String str) {
        this.llNameError.setVisibility(View.VISIBLE);
        this.tvNameError.setText(str);
    }

    public void checkPassword(String str) {
        Pattern compile = Pattern.compile(".*[A-Z]+.*");
        Pattern compile2 = Pattern.compile(".*[a-z]+.*");
        Pattern compile3 = Pattern.compile(".*[0-9]+.*");
        Matcher matcher = compile.matcher(str);
        Matcher matcher2 = compile2.matcher(str);
        Matcher matcher3 = compile3.matcher(str);
        if (matcher.matches()) {
            changeView(this.cbOne, this.tvOne, true);
        } else {
            changeView(this.cbOne, this.tvOne, false);
        }
        if (matcher2.matches()) {
            changeView(this.cbTwo, this.tvTwo, true);
        } else {
            changeView(this.cbTwo, this.tvTwo, false);
        }
        if (matcher3.matches()) {
            changeView(this.cbThree, this.tvThree, true);
        } else {
            changeView(this.cbThree, this.tvThree, false);
        }
        if (str.length() >= 8) {
            changeView(this.cbFour, this.tvFour, true);
        } else {
            changeView(this.cbFour, this.tvFour, false);
        }
    }

    public void changeView(CheckBox checkBox, TextView textView, boolean z) {
        if (z) {
            checkBox.setChecked(true);
            textView.setSelected(true);
            return;
        }
        checkBox.setChecked(false);
        textView.setSelected(false);
    }
}
